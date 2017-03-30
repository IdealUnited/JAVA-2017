/**
 * 
 */
package com.idealunited.gateway.client;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.idealunited.inf.enums.SerCode;
import com.idealunited.inf.enums.SystemCodeEnum;
import com.idealunited.inf.params.HessianInvokeParam;
import com.idealunited.inf.service.HessianInvokeService;
import com.idealunited.inf.service.SysTraceNoService;
import com.idealunited.inf.utils.HessianInvokeHelper;
import com.idealunited.util.JSonUtil;

/**
 * 交易服务接口
 * 
 * @author Steven Lee
 *
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class FoBridgeClientService {

	private final Log logger = LogFactory.getLog(getClass());
	
	private HessianInvokeService invokeService;

	public void setInvokeService(HessianInvokeService invokeService) {
		this.invokeService = invokeService;
	}

	/**
	 * 获取用户付款密钥
	 * 
	 * @param partnerId
	 * @return
	 */
	public Map getMerchantConfigure(String merchantCode) {
		Map<String, String> paraMap = new HashMap();

		paraMap.put("merchantCode", merchantCode);

		String reqMsg = JSonUtil.toJSonString(paraMap);
		HessianInvokeParam param = HessianInvokeHelper.processRequest(reqMsg);
		String sysTraceNo = SysTraceNoService
				.generateSysTraceNo(SystemCodeEnum.WEBGATE.getCode());
		String result = invokeService.invoke(
				SerCode.FOBRIDGE_MERCHANT_CONFIG.getCode(), sysTraceNo,
				SystemCodeEnum.WEBGATE.getCode(),
				SystemCodeEnum.FOBRIDGE.getCode(),
				SystemCodeEnum.FOBRIDGE.getVersion(), param.getDataLength(),
				param.getMsgCompress(), param.getDataMsg());

		param.parse(result);
		HessianInvokeHelper.processResponse(param);
		result = param.getDataMsg();

		Map resultMap = JSonUtil.toObject(result, new HashMap().getClass());

		return resultMap;
	}

	/**
	 * 获取商户账户余额
	 * 
	 * @param merchantCode  商户号
	 * @param payerAcctType 付款账户类型 
	 * @return
	 */
	public Map getMerchantAccountBlance(String merchantCode,String payerAcctType) {
		
		Map<String, String> paraMap = new HashMap();
		paraMap.put("payerAcctType", payerAcctType);
		paraMap.put("merchantCode", merchantCode);
		
		if(logger.isInfoEnabled()){
			logger.info("查询参数=> "+paraMap);
		}

		String reqMsg = JSonUtil.toJSonString(paraMap);
		HessianInvokeParam param = HessianInvokeHelper.processRequest(reqMsg);
		String sysTraceNo = SysTraceNoService
				.generateSysTraceNo(SystemCodeEnum.WEBGATE.getCode());
		String result = invokeService.invoke(
				SerCode.FOBRIDGE_PAYERACCOUNT_BLANCE_QUERY.getCode(), sysTraceNo,
				SystemCodeEnum.WEBGATE.getCode(),
				SystemCodeEnum.FOBRIDGE.getCode(),
				SystemCodeEnum.FOBRIDGE.getVersion(), param.getDataLength(),
				param.getMsgCompress(), param.getDataMsg());

		param.parse(result);
		HessianInvokeHelper.processResponse(param);
		result = param.getDataMsg();

		Map resultMap = JSonUtil.toObject(result, new HashMap().getClass());

		return resultMap;
	}
}
