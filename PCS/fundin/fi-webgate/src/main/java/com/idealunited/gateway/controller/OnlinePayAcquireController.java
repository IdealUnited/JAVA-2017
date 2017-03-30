package com.idealunited.gateway.controller;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.idealunited.common.HttpRequestUtils;
import com.idealunited.fi.commons.DirectFlagEnum;
import com.idealunited.fi.commons.HTTPProtocolHandleUtil;
import com.idealunited.fi.helper.SecuritySubstance;
import com.idealunited.gateway.client.TxncoreClientService;
import com.idealunited.gateway.dto.OnlinePayCashierRequestDTO;
import com.idealunited.gateway.dto.OnlinePayCashierResponseDTO;
import com.idealunited.inf.service.ValidateService;
import com.idealunited.util.CharsetTypeEnum;
import com.idealunited.util.MapUtil;
import com.idealunited.util.MobileUtil;

/**
 * @desc 在线收单控制器
 * 
 */
public class OnlinePayAcquireController extends MultiActionController {

	private final Log logger = LogFactory
			.getLog(OnlinePayAcquireController.class);
	private ValidateService validateService;
	private String failRedirectUrl;
	private String successRedirectUrl;
	private TxncoreClientService txncoreClientService;
	private String websitePath;
	private String nativeCashierUrl;
	private String quickPayCashierUrl;

	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ModelAndView view = new ModelAndView("redirect:" + failRedirectUrl);
		// 设置请求回应的字符编码UTF-8，清缓存
		HTTPProtocolHandleUtil.setAll(request, response);

		OnlinePayCashierRequestDTO onlinePayCashierRequestDTO = HttpRequestUtils.convert(
				OnlinePayCashierRequestDTO.class, request);
		OnlinePayCashierResponseDTO gatewayResponseDTO = new OnlinePayCashierResponseDTO();
		BeanUtils.copyProperties(onlinePayCashierRequestDTO, gatewayResponseDTO,new String[]{"signMsg"});
		onlinePayCashierRequestDTO.setOnlinePayCashierResponseDTO(gatewayResponseDTO);
		onlinePayCashierRequestDTO.setUserAgent(request.getHeader("User-Agent"));
		
		logger.info("网关请求参数:" + onlinePayCashierRequestDTO);
		String tradeOrderNo=null;
		try {
			
			validateService.validate(onlinePayCashierRequestDTO);
			
		} catch (Exception e) {
			logger.info("@FI-收单失败", e);
			gatewayResponseDTO.setResultCode("9999");
			gatewayResponseDTO.setResultMsg("收单失败");
		}
		
		logger.info("人民币网关处理结果: responseCode:" + gatewayResponseDTO.getResultCode() + "responseDesc:"+gatewayResponseDTO.getResultMsg());
		
		if(StringUtils.isNotBlank(gatewayResponseDTO.getResultCode())){
			view.addObject("englishflag",false); 
			view.addObject("errorMsg",String.format("%s(%s)",
					URLEncoder.encode(gatewayResponseDTO.getResultMsg(),CharsetTypeEnum.UTF8.getDescription())
					,gatewayResponseDTO.getResultCode()));
		}else{
			view = new ModelAndView(successRedirectUrl);
			view.addAllObjects(MapUtil.bean2map(onlinePayCashierRequestDTO));
			view.addObject("tradeOrderNo", tradeOrderNo);
			view.addObject("signMsg", genSignMsg(tradeOrderNo,onlinePayCashierRequestDTO.getPartnerId()));
			
			//判斷是否是手机端访问
			if(request.getHeader("User-Agent")!=null && 
					MobileUtil.checkAgentIsMobile(request.getHeader("User-Agent"))){
				view.addObject("cashierUrl", quickPayCashierUrl);
			}else{
				
				view.addObject("cashierUrl",nativeCashierUrl);
			}
		} 
		
		if (DirectFlagEnum.DIRECT.getCode().equals(
				onlinePayCashierRequestDTO.getDirectFlag())) {
			view.addObject("direct", onlinePayCashierRequestDTO.getDirectFlag());
		}
		view.addObject("orderId", onlinePayCashierRequestDTO.getOrderId());
		view.addObject("goodsName", StringUtils.isNotBlank(onlinePayCashierRequestDTO.getGoodsName())?
				URLEncoder.encode(onlinePayCashierRequestDTO.getGoodsName(),CharsetTypeEnum.UTF8.getDescription()):"");
		view.addObject("orderAmount",onlinePayCashierRequestDTO.getOrderAmount());
		view.addObject("orderCurrencyCode", onlinePayCashierRequestDTO.getOrderCurrencyCode());
		view.addObject("returnUrl",onlinePayCashierRequestDTO.getReturnUrl());
		view.addObject("websitePath",websitePath);

		return view;
	}
	
	/**
	 * 生成加签数据
	 * @param tradeOrderNo
	 * @param payerFee
	 * @return
	 */
	private String genSignMsg(String tradeOrderNo,String partnerId){
		
		String signStr=String.format("tradeOrderNo=%s&partnerId=%s", tradeOrderNo,partnerId);
		
		try {
		
			return SecuritySubstance.genSignByMD5(signStr,
					CharsetTypeEnum.UTF8, SecuritySubstance.getPublicKey());
		
		} catch (Exception e) {
			logger.error("加签失败", e);
		}
		
		return null;
	}
	

	public void setValidateService(ValidateService validateService) {
		this.validateService = validateService;
	}

	public void setFailRedirectUrl(String failRedirectUrl) {
		this.failRedirectUrl = failRedirectUrl;
	}

	public void setSuccessRedirectUrl(String successRedirectUrl) {
		this.successRedirectUrl = successRedirectUrl;
	}

	public void setTxncoreClientService(
			TxncoreClientService txncoreClientService) {
		this.txncoreClientService = txncoreClientService;
	}

	public void setWebsitePath(String websitePath) {
		this.websitePath = websitePath;
	}

	public void setNativeCashierUrl(String nativeCashierUrl) {
		this.nativeCashierUrl = nativeCashierUrl;
	}

	public void setQuickPayCashierUrl(String quickPayCashierUrl) {
		this.quickPayCashierUrl = quickPayCashierUrl;
	}

}
