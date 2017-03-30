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

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.idealunited.client.TxncoreOrderClientService;
import com.idealunited.dto.TradeOrder;
import com.idealunited.inf.comm.PageUtils;
import com.idealunited.inf.dao.Page;
import com.idealunited.util.MapUtil;

/**
 * 订单管理控制器
 */
public class OrderExceptionManageController extends MultiActionController {

	private final Log logger = LogFactory.getLog(getClass());
	private String initView;
	private String listView;
	private TxncoreOrderClientService txncoreOrderClientService;

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
	public ModelAndView search(HttpServletRequest request,
			HttpServletResponse response, OrderCriteria orderCriteria)
			throws Exception {

		Page<TradeOrder> page = PageUtils.getPage(request); // 分页
		Map queryMap = MapUtil.bean2map(orderCriteria);
		Page<Map> resultPage = txncoreOrderClientService.queryOrderInfo(
				queryMap, page);
		Map resultMap = new HashMap();
		resultMap.put("page", resultPage);
		return new ModelAndView(listView, resultMap);
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
