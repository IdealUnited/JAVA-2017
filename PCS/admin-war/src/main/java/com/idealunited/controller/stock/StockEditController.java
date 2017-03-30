/**
 *  File: StockManageController.java
 *  Date: 2016年5月26日
 *  Author: chaoyue 
 *  Changes: Create
 *  Copyright 2006-2016 HNAPAY Corporation. All rights reserved.
 *  Description:
 *
 */
package com.idealunited.controller.stock;

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

import com.idealunited.client.TxncoreStockClientService;
import com.idealunited.dto.ProductStock;
import com.idealunited.inf.comm.PageUtils;
import com.idealunited.inf.dao.Page;
import com.idealunited.poss.security.util.SessionUserHolderUtil;
import com.idealunited.poss.systemmanager.formbean.UserFormBean;

/**
 * 库存编辑
 */
public class StockEditController extends MultiActionController {

	private final Log logger = LogFactory.getLog(getClass());
	private String initView;
	private String listView;
	private String editView;
	private TxncoreStockClientService txncoreClientService;

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
	 * 查询库存
	 * 
	 * @param request
	 * @param response
	 * @param productStock
	 * @return
	 * @throws Exception
	 */
	public ModelAndView search(HttpServletRequest request,
			HttpServletResponse response, ProductStock productStock)
			throws Exception {

		Page<UserFormBean> page = PageUtils.getPage(request); // 分页
		Page<ProductStock> pageResult = txncoreClientService.queryProduct(
				productStock, page);
		Map resultMap = new HashMap();
		resultMap.put("page", pageResult);
		resultMap.put("resultList", pageResult.getResult());
		return new ModelAndView(listView, resultMap);
	}

	/**
	 * 跳转修改页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView toEdit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String id = request.getParameter("id");
		ProductStock productStock = new ProductStock();
		productStock.setId(Long.valueOf(id));
		Page<UserFormBean> page = PageUtils.getPage(request); // 分页
		Page<ProductStock> pageResult = txncoreClientService.queryProduct(
				productStock, page);

		ProductStock result = pageResult.getResult().get(0);
		return new ModelAndView(editView).addObject("stock", result);
	}

	/**
	 * 修改
	 * 
	 * @param request
	 * @param response
	 * @param productStock
	 * @return
	 */
	public ModelAndView edit(HttpServletRequest request,
			HttpServletResponse response, ProductStock productStock)
			throws Exception {

		String auditor = SessionUserHolderUtil.getLoginId();

		List<ProductStock> productStocks = new ArrayList<ProductStock>();
		productStocks.add(productStock);

		boolean flag = txncoreClientService.updateProduct(productStocks,
				auditor);
		if (!flag) {
			response.getWriter().print(0);
		} else {
			response.getWriter().print(1);
		}

		return null;
	}
	
	public ModelAndView del(HttpServletRequest request,
			HttpServletResponse response, ProductStock productStock)
					throws Exception {
		
		boolean flag = txncoreClientService.delProduct(productStock.getId());
		if (!flag) {
			response.getWriter().print(0);
		} else {
			response.getWriter().print(1);
		}
		
		return null;
	}

	public void setInitView(String initView) {
		this.initView = initView;
	}

	public void setListView(String listView) {
		this.listView = listView;
	}

	public void setEditView(String editView) {
		this.editView = editView;
	}

	public void setTxncoreClientService(
			TxncoreStockClientService txncoreClientService) {
		this.txncoreClientService = txncoreClientService;
	}

}
