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

import java.io.IOException;
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
 * 库存审核
 */
public class StockAuditController extends MultiActionController {

	private final Log logger = LogFactory.getLog(getClass());
	private String initView;
	private String listView;
	private TxncoreStockClientService txncoreClientService;

	/**
	 * 页序号
	 */
	private static final int SHEET_INDEX = 0;

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
	 * 审核
	 * 
	 * @param request
	 * @param response
	 * @param productStock
	 * @return
	 */
	public ModelAndView audit(HttpServletRequest request,
			HttpServletResponse response) {

		String[] ids = request.getParameterValues("id");
		String auditFlag = request.getParameter("auditFlag");
		String auditor = SessionUserHolderUtil.getLoginId();

		List<ProductStock> productStocks = new ArrayList<ProductStock>();

		if (null != ids && ids.length > 0) {
			for (String id : ids) {
				ProductStock productStock = new ProductStock();
				productStock.setId(Long.valueOf(id));
				productStock.setStatus(Integer.valueOf(auditFlag));
				productStocks.add(productStock);
			}
			boolean flag = txncoreClientService.auditProduct(productStocks,
					auditor);
			if (!flag) {
				try {
					response.getWriter().print(0);
				} catch (IOException e) {
					logger.error("error:", e);
				}
			}
		}

		Page<UserFormBean> page = PageUtils.getPage(request); // 分页
		Page<ProductStock> pageResult = txncoreClientService.queryProduct(
				new ProductStock(), page);
		Map resultMap = new HashMap();
		resultMap.put("page", pageResult);
		resultMap.put("resultList", pageResult.getResult());
		return new ModelAndView(listView, resultMap);
	}

	public void setInitView(String initView) {
		this.initView = initView;
	}

	public void setListView(String listView) {
		this.listView = listView;
	}

	public void setTxncoreClientService(
			TxncoreStockClientService txncoreClientService) {
		this.txncoreClientService = txncoreClientService;
	}

}
