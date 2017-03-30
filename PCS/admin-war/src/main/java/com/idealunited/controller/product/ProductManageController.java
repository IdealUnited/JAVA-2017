/**
 *  File: ProductManageController.java
 *  Date: 2016年5月26日
 *  Author: chaoyue 
 *  Changes: Create
 *  Copyright 2006-2016 HNAPAY Corporation. All rights reserved.
 *  Description:
 *
 */
package com.idealunited.controller.product;

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

import com.idealunited.client.TxncoreSkuClientService;
import com.idealunited.client.TxncoreStockClientService;
import com.idealunited.dto.ProductSku;
import com.idealunited.dto.ProductStock;
import com.idealunited.inf.comm.PageUtils;
import com.idealunited.inf.dao.Page;
import com.idealunited.poss.security.util.SessionUserHolderUtil;

/**
 * 产品管理
 */
public class ProductManageController extends MultiActionController {

	private final Log logger = LogFactory.getLog(getClass());
	private String addView;
	private String initView;
	private String listView;
	private String updateListView;
	private String updateView;
	private String auditView;
	private String auditListView;
	private TxncoreSkuClientService txncoreSkuClientService;
	private TxncoreStockClientService txncoreClientService;

	/**
	 * 货品信息管理初始页
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
	 * 新增初始页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView addInit(HttpServletRequest request,
			HttpServletResponse response) {

		return new ModelAndView(addView);
	}

	/**
	 * 添加SKU
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView add(HttpServletRequest request,
			HttpServletResponse response, ProductSku productSku) {

		String creator = SessionUserHolderUtil.getLoginId();
		productSku.setCreator(creator);
		List<ProductSku> orders = new ArrayList<ProductSku>();
		orders.add(productSku);
		txncoreSkuClientService.createSku(orders);

		Page<ProductSku> page = PageUtils.getPage(request); // 分页
		Page<ProductSku> resultPage = txncoreSkuClientService.queryProduct(
				productSku, page);
		Map resultMap = new HashMap();
		resultMap.put("page", page);
		resultMap.put("resultList", resultPage.getResult());

		return new ModelAndView(listView, resultMap);
	}

	/**
	 * 根据产品名称获取仓位号
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView getStockCode(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String name = request.getParameter("productName");
		Page<ProductStock> page = PageUtils.getPage(request);
		ProductStock productStock = new ProductStock();
		productStock.setName(name);
		Page<ProductStock> resultPage = txncoreClientService.queryProduct(
				productStock, page);
		if (null != resultPage && null != resultPage.getResult()
				&& !resultPage.getResult().isEmpty()) {
			List<ProductStock> list = resultPage.getResult();
			response.getWriter().print(list.get(0).getStockCode());
		} else {
			response.getWriter().print(0);
		}
		return null;
	}

	/**
	 * 查询货品信息
	 * 
	 * @param request
	 * @param response
	 * @param productStock
	 * @return
	 * @throws Exception
	 */
	public ModelAndView search(HttpServletRequest request,
			HttpServletResponse response, ProductSku productSku)
			throws Exception {

		Page<ProductSku> page = PageUtils.getPage(request); // 分页
		Page<ProductSku> resultPage = txncoreSkuClientService.queryProduct(
				productSku, page);
		Map resultMap = new HashMap();
		resultMap.put("page", page);
		resultMap.put("resultList", resultPage.getResult());
		return new ModelAndView(updateListView, resultMap);
	}

	/**
	 * 更新sku
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView updateInit(HttpServletRequest request,
			HttpServletResponse response, ProductSku querySku) {

		Page<ProductSku> page = PageUtils.getPage(request); // 分页
		Page<ProductSku> resultPage = txncoreSkuClientService.queryProduct(
				querySku, page);

		ProductSku productSku = resultPage.getResult().get(0);
		Map resultMap = new HashMap();
		resultMap.put("productSku", productSku);
		return new ModelAndView(updateView, resultMap);
	}

	/**
	 * 修改
	 * 
	 * @param request
	 * @param response
	 * @param productSku
	 * @return
	 */
	public ModelAndView update(HttpServletRequest request,
			HttpServletResponse response, ProductSku productSku)
			throws Exception {

		boolean updateFlag = txncoreSkuClientService.updateSku(productSku);

		if (!updateFlag) {
			response.getWriter().print(0);
		} else {
			response.getWriter().print(1);
		}

		return null;
	}

	public ModelAndView del(HttpServletRequest request,
			HttpServletResponse response, ProductSku productSku)
			throws Exception {

		boolean updateFlag = txncoreSkuClientService.delSku(productSku
				.getSkuCode());

		if (updateFlag) {
			response.getWriter().print(1);
		} else {
			response.getWriter().print(0);
		}

		return null;
	}

	/**
	 * 审核页
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView auditInit(HttpServletRequest request,
			HttpServletResponse response) {

		return new ModelAndView(auditView);
	}

	/**
	 * 审核查询
	 * 
	 * @param request
	 * @param response
	 * @param productSku
	 * @return
	 * @throws Exception
	 */
	public ModelAndView auditSearch(HttpServletRequest request,
			HttpServletResponse response, ProductSku productSku)
			throws Exception {

		Page<ProductSku> page = PageUtils.getPage(request); // 分页
		Page<ProductSku> resultPage = txncoreSkuClientService.queryProduct(
				productSku, page);
		Map resultMap = new HashMap();
		resultMap.put("page", page);
		resultMap.put("resultList", resultPage.getResult());
		return new ModelAndView(auditListView, resultMap);
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

		String[] skuCodes = request.getParameterValues("skuCode");
		String auditFlag = request.getParameter("auditFlag");
		String auditor = SessionUserHolderUtil.getLoginId();

		List<ProductSku> productSkus = new ArrayList<ProductSku>();

		if (null != skuCodes && skuCodes.length > 0) {
			for (String skuCode : skuCodes) {
				ProductSku productSku = new ProductSku();
				productSku.setSkuCode(skuCode);
				productSku.setAuditor(auditor);
				productSku.setStatus(Integer.valueOf(auditFlag));
				productSkus.add(productSku);
			}
			boolean flag = txncoreSkuClientService.auditSku(productSkus);
			if (!flag) {
				try {
					response.getWriter().print(0);
				} catch (IOException e) {
					logger.error("error:", e);
				}
			}
		}

		Page<ProductSku> page = PageUtils.getPage(request); // 分页
		Page<ProductSku> resultPage = txncoreSkuClientService.queryProduct(
				new ProductSku(), page);
		Map resultMap = new HashMap();
		resultMap.put("page", page);
		resultMap.put("resultList", resultPage.getResult());
		return new ModelAndView(auditListView, resultMap);
	}

	public void setAddView(String addView) {
		this.addView = addView;
	}

	public void setInitView(String initView) {
		this.initView = initView;
	}

	public void setListView(String listView) {
		this.listView = listView;
	}

	public void setTxncoreSkuClientService(
			TxncoreSkuClientService txncoreSkuClientService) {
		this.txncoreSkuClientService = txncoreSkuClientService;
	}

	public void setTxncoreClientService(
			TxncoreStockClientService txncoreClientService) {
		this.txncoreClientService = txncoreClientService;
	}

	public void setUpdateView(String updateView) {
		this.updateView = updateView;
	}

	public void setUpdateListView(String updateListView) {
		this.updateListView = updateListView;
	}

	public void setAuditView(String auditView) {
		this.auditView = auditView;
	}

	public void setAuditListView(String auditListView) {
		this.auditListView = auditListView;
	}

}
