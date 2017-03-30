/**
 *  File: OrderManageController.java
 *  Date: 2016年5月26日
 *  Author: chaoyue 
 *  Changes: Create
 *  Copyright 2006-2016 HNAPAY Corporation. All rights reserved.
 *  Description:
 *
 */
package com.idealunited.controller.order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.idealunited.client.TxncoreChannelClientService;
import com.idealunited.client.TxncoreCountryClientService;
import com.idealunited.client.TxncoreOrderClientService;
import com.idealunited.client.TxncoreProductTypeClientService;
import com.idealunited.dto.Country;
import com.idealunited.dto.ExpressChannel;
import com.idealunited.dto.ExpressChannelItem;
import com.idealunited.dto.ManaulOrderDto;
import com.idealunited.dto.ProductType;
import com.idealunited.dto.TradeOrder;
import com.idealunited.inf.comm.PageUtils;
import com.idealunited.inf.dao.Page;
import com.idealunited.inf.enums.ResponseCodeEnum;
import com.idealunited.util.CurrencyCodeEnum;
import com.idealunited.util.DateUtil;
import com.idealunited.util.MapUtil;
import com.idealunited.util.SpringControllerUtils;
import com.idealunited.util.StringUtil;

/**
 * 订单管理控制器
 */
public class OrderManaulManageController extends MultiActionController {

	private final Log logger = LogFactory.getLog(getClass());
	private String initView;
	private String listView;
	private TxncoreOrderClientService txncoreOrderClientService;
	private TxncoreChannelClientService txncoreChannelClientService;
	private TxncoreProductTypeClientService txncoreProductTypeClientService;
	private TxncoreCountryClientService txncoreCountryClientService;

	public void setTxncoreChannelClientService(
			TxncoreChannelClientService txncoreChannelClientService) {
		this.txncoreChannelClientService = txncoreChannelClientService;
	}

	public void setTxncoreProductTypeClientService(
			TxncoreProductTypeClientService txncoreProductTypeClientService) {
		this.txncoreProductTypeClientService = txncoreProductTypeClientService;
	}

	public void setTxncoreCountryClientService(
			TxncoreCountryClientService txncoreCountryClientService) {
		this.txncoreCountryClientService = txncoreCountryClientService;
	}

	/**
	 * 产品库存管理初始页
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Map resultMap = new HashMap();

		CurrencyCodeEnum[] currencys = CurrencyCodeEnum.values();

		ExpressChannel tempParam = new ExpressChannel();
		List<ExpressChannel> expressChannelList = txncoreChannelClientService
				.queryExpressChannel(tempParam);

		List<Relation> relationList = new ArrayList<Relation>();

		for (int i = 0; i < expressChannelList.size(); i++) {
			ExpressChannel expressChannel = (ExpressChannel) expressChannelList
					.get(i);
			ExpressChannelItem paramObject = new ExpressChannelItem();
			paramObject.setChannelId(expressChannel.getId());
			List<ExpressChannelItem> ExpressChannelItemList = txncoreChannelClientService
					.queryExpressChannelItem(paramObject).getResult();

			for (int j = 0; j < ExpressChannelItemList.size(); j++) {
				ExpressChannelItem expressChannelItem = (ExpressChannelItem) ExpressChannelItemList
						.get(j);
				Relation relation = new Relation();
				relation.setFatherCode(expressChannel.getId().toString());
				relation.setCode(expressChannelItem.getId().toString());
				relation.setName(expressChannelItem.getName());
				relationList.add(relation);
			}
		}
		resultMap.put("expressChannelList", expressChannelList);
		resultMap.put("currencys", currencys);
		resultMap.put("relationList", relationList);
		resultMap.put("earliestshipdate", DateUtil.getNowDate("yyyy-MM-dd"));
		List<Country> resultCountry = getCountryByKeyValue(null);
		resultMap.put("countrys", resultCountry);

		ProductType productType = new ProductType();
		Page<ProductType> productTypes = txncoreProductTypeClientService
				.queryProductType(productType, null);
		resultMap.put("productTypes", productTypes.getResult());
		return new ModelAndView(initView, resultMap);
	}

	/**
	 * 添加手工单
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView add(HttpServletRequest request,
			HttpServletResponse response, ManaulOrderDto manaulOrderDto)
			throws Exception {

		response.setCharacterEncoding("UTF-8");
		
		ProductType productType = new ProductType();
		productType.setEnType(manaulOrderDto.getEnType());
		Page<ProductType> productTypePage = txncoreProductTypeClientService.queryProductType(productType,
				null);
		
		manaulOrderDto.setZhType(productTypePage.getResult().get(0).getZhType());
		
		Map paraMap = MapUtil.bean2map(manaulOrderDto);
		//订单重复性校验
		String orderId=manaulOrderDto.getOrderId();
		String platForm=manaulOrderDto.getPlatform();
		TradeOrder t=new TradeOrder();
		t.setOrderId(orderId);
		t.setPlatform(platForm);
		Page<TradeOrder> exsitRecord = txncoreOrderClientService.queryOrder(t, null);
		if(null!=exsitRecord&&null!=exsitRecord.getResult()&&exsitRecord.getResult().size()>0){
			String reason="订单号（orderId）:"+orderId +"已存在！";
			SpringControllerUtils.renderText(response, "false|"+reason);
		}
		
		Map<String, String> resultMap = txncoreOrderClientService.createManualOrder(paraMap);
		
		if (!StringUtil.isEmpty(resultMap.get("responseCode") + "")
				&& "0000".equals(resultMap.get("responseCode") + "")) {
			SpringControllerUtils.renderText(response, "true");
		} else {
			String reason = StringUtil.null2String(resultMap
					.get("ReasonMessage"));
			SpringControllerUtils.renderText(response, "false|" + reason);
		}
		String responseCode = resultMap.get("responseCode");
		String responseDesc = resultMap.get("responseDesc");
		if (ResponseCodeEnum.SUCCESS.getCode().equals(responseCode)) {
			response.getWriter().print(1);
		} else {
			response.getWriter().print(responseDesc);
		}
		return null;
	}

	/**
	 * 查询订单
	 * 
	 * @param request
	 * @param response
	 * @param productStock
	 * @return
	 * @throws Exception
	 */
	public ModelAndView search(HttpServletRequest request,
			HttpServletResponse response, TradeOrder tradeOrder)
			throws Exception {

		Page<TradeOrder> page = PageUtils.getPage(request); // 分页

		Page<TradeOrder> resultPage = txncoreOrderClientService.queryOrder(
				tradeOrder, page);
		Map resultMap = new HashMap();
		resultMap.put("page", resultPage);
		return new ModelAndView(listView, resultMap);
	}

	/**
	 * 查询类型是否存在
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView isExistsProductType(HttpServletRequest request,
			HttpServletResponse response, ProductType productType)
			throws Exception {

		Page<ProductType> page = PageUtils.getPage(request);

		page = txncoreProductTypeClientService.queryProductType(productType,
				page);

		if (null == page || null == page.getResult()
				|| page.getResult().isEmpty()) {
			response.getWriter().println(0);
		} else {
			response.getWriter().println(1);
		}

		return null;
	}

	public ModelAndView getCountrys(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String searchKey = StringUtil.null2String(request
				.getParameter("searchKey"));
		List<Country> resultCountry = getCountryByKeyValue(searchKey);
		StringBuffer sbf = new StringBuffer();
		for (Country c : resultCountry) {
			sbf.append("<option value='" + c.getCode() + "'>");
			sbf.append(c.getZhName());
			sbf.append("</option>");
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/hmtl");
		response.getWriter().print(sbf.toString());
		return null;
	}

	private List<Country> getCountryByKeyValue(String keyValue) {
		Country country = new Country();
		Page p = new Page();
		p.setPageSize(500);
		String[] arrayFrontCountrys = new String[] { "德国", "法国", "意大利", "美国",
				"英国", "加拿大", "西班牙", "比利时", "卢森堡", "奥地利", "荷兰" };
		Page<Country> result = txncoreCountryClientService.query(country, p);
		List<Country> resultCountry = new ArrayList();
		for (Country c : result.getResult()) {
			if (Arrays.asList(arrayFrontCountrys).contains(c.getZhName())) {
				resultCountry.add(0, c);
			} else {
				resultCountry.add(c);
			}
			if (StringUtils.isNotEmpty(keyValue)
					&& !(c.getEnName().contains(keyValue) || c.getZhName()
							.contains(keyValue))) {
				resultCountry.remove(c);
			}
		}
		return resultCountry;
	}

	public void setInitView(String initView) {
		this.initView = initView;
	}

	public void setListView(String listView) {
		this.listView = listView;
	}

	public void setTxncoreOrderClientService(
			TxncoreOrderClientService txncoreOrderClientService) {
		this.txncoreOrderClientService = txncoreOrderClientService;
	}

}
