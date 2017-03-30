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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.idealunited.client.TxncoreChannelClientService;
import com.idealunited.client.TxncoreOrderClientService;
import com.idealunited.dto.ExpressChannel;
import com.idealunited.dto.ExpressChannelItem;
import com.idealunited.dto.TradeOrder;
import com.idealunited.inf.comm.PageUtils;
import com.idealunited.inf.dao.Page;
import com.idealunited.util.MapUtil;
import com.idealunited.util.SpringControllerUtils;
import com.idealunited.util.StringUtil;

/**
 * 订单扫描出库
 */
public class OrderScanController extends MultiActionController {

	private final Log logger = LogFactory.getLog(getClass());
	private String initView;
	private String listView;

	private TxncoreOrderClientService txncoreOrderClientService;
	private TxncoreChannelClientService txncoreChannelClientService;

	/**
	 * 产品库存管理初始页
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws Exception {

		return new ModelAndView(initView);
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
	public ModelAndView search(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Page<TradeOrder> page = PageUtils.getPage(request); // 分页
		String expressSysCode=StringUtil.null2String(request.getParameter("expressSysCode"));
		Map queryMap =new HashMap();
		queryMap.put("expressSysCode", expressSysCode);
		Page<Map> resultPage = txncoreOrderClientService.queryOrderItemInfo(queryMap, page);
		Map resultMap = new HashMap();
		resultMap.put("page", resultPage);
		resultMap.put("channelOrderItemId", expressSysCode);
		return new ModelAndView(listView, resultMap);
	}

	/**
	 * 确认出库扫描（扫描完成，标识已出库，扣库存）
	 * @param request
	 * @param response
	 * @param orderCriteria
	 * @return
	 * @throws Exception
	 */
	public ModelAndView confirmScanOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String channelOrderItemId=StringUtil.null2String(request.getParameter("channelorderItemId"));			
			String quantityOrdered=StringUtil.null2String(request.getParameter("quantityOrdered"));
			String quantityShipped=StringUtil.null2String(request.getParameter("quantityShipped"));
			String stockCode=StringUtil.null2String(request.getParameter("stockCode"));
			
			Map queryMap =new HashMap();
			queryMap.put("channelOrderItemId", channelOrderItemId);
			queryMap.put("quantityOrdered", quantityOrdered);
			queryMap.put("quantityShipped", quantityShipped);
			queryMap.put("stockCode", stockCode);
	
			Map resultMap = txncoreOrderClientService.scanAndConfirmExpress(queryMap);
			if (!StringUtil.isEmpty(resultMap.get("responseCode") + "")
					&& "0000".equals(resultMap.get("responseCode") + "")) {
				SpringControllerUtils.renderText(response, "true");
			}
			else if(!StringUtil.isEmpty(resultMap.get("responseCode") + "")
					&& "8000".equals(resultMap.get("responseCode") + "")) {
				SpringControllerUtils.renderText(response, "8000");
			}
			else{
				SpringControllerUtils.renderText(response, "false");
			}
		} catch (Exception e) {
			SpringControllerUtils.renderText(response, "fasle");
		}
		return null;
	}
	
	
	public ModelAndView comfirmExpress(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Page<TradeOrder> page = PageUtils.getPage(request); // 分页
		String expressSysCode=StringUtil.null2String(request.getParameter("expressSysCode"));
		Map queryMap =new HashMap();
		queryMap.put("expressSysCode", expressSysCode);
		Page<Map> resultPageExsit = txncoreOrderClientService.queryOrderItemInfo(queryMap, page);		
		List<Map> resultList=resultPageExsit.getResult();
		if(null==resultList||resultList.size()!=1){
			SpringControllerUtils.renderText(response, "404");
		}
		Map result=resultList.get(0);
		String channelOrderItemId=StringUtil.null2String(result.get("channelOrderItemId"));
		String quantityOrdered=StringUtil.null2String(result.get("quantityOrdered"));
		String quantityShipped=StringUtil.null2String(result.get("quantityShipped"));
		String stockCode=StringUtil.null2String(result.get("stockCode"));
		
		//判断库存不能为空
		if(StringUtil.isEmpty(stockCode)){
			SpringControllerUtils.renderText(response, "false");
			return null;
		}
		
		Map queryMap2 =new HashMap();
		queryMap2.put("channelOrderItemId", channelOrderItemId);
		queryMap2.put("quantityOrdered", quantityOrdered);
		queryMap2.put("quantityShipped", quantityShipped);
		queryMap2.put("stockCode", stockCode);

		Map resultMap2 = txncoreOrderClientService.scanAndConfirmExpress(queryMap2);
		if (!StringUtil.isEmpty(resultMap2.get("responseCode") + "")
				&& "0000".equals(resultMap2.get("responseCode") + "")) {	
			SpringControllerUtils.renderText(response, "true");
		}
		else if(!StringUtil.isEmpty(resultMap2.get("responseCode") + "")
				&& "8000".equals(resultMap2.get("responseCode") + "")) {
			SpringControllerUtils.renderText(response, "8000");
		}
		else{
			SpringControllerUtils.renderText(response, "false");
		}
		return null;
	}
	
	
	
	public void setInitView(String initView) {
		this.initView = initView;
	}

	public void setListView(String listView) {
		this.listView = listView;
	}

	public void setTxncoreOrderClientService(TxncoreOrderClientService txncoreOrderClientService) {
		this.txncoreOrderClientService = txncoreOrderClientService;
	}

	public void setTxncoreChannelClientService(TxncoreChannelClientService txncoreChannelClientService) {
		this.txncoreChannelClientService = txncoreChannelClientService;
	}

}
