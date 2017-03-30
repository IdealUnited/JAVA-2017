/**
 *  File: ProductTypeManageController.java
 *  Date: 2016年5月26日
 *  Author: chaoyue 
 *  Changes: Create
 *  Copyright 2006-2016 HNAPAY Corporation. All rights reserved.
 *  Description:
 *
 */
package com.idealunited.controller.productType;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.idealunited.client.TxncoreProductTypeClientService;
import com.idealunited.dto.ProductType;
import com.idealunited.inf.comm.PageUtils;
import com.idealunited.inf.dao.Page;
import com.idealunited.poss.security.util.SessionUserHolderUtil;

/**
 * 产品类型管理
 */
public class ProductTypeManageController extends MultiActionController {

	private final Log logger = LogFactory.getLog(getClass());
	private String addView;
	private String initView;
	private String listView;
	private String updateView;
	private TxncoreProductTypeClientService txncoreProductTypeClientService;

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
	 * 添加页面
	 * 
	 * @param request
	 * @param response
	 * @return
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
			HttpServletResponse response, ProductType productType)
			throws Exception {

		String creator = SessionUserHolderUtil.getLoginId();
		productType.setCreator(creator);
		txncoreProductTypeClientService.createType(productType);
		response.getWriter().print(1);
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
			HttpServletResponse response, ProductType productType)
			throws Exception {

		Page<ProductType> page = PageUtils.getPage(request); // 分页
		Page<ProductType> resultPage = txncoreProductTypeClientService
				.queryProductType(productType, page);
		Map resultMap = new HashMap();
		resultMap.put("page", page);
		resultMap.put("resultList", resultPage.getResult());
		return new ModelAndView(listView, resultMap);
	}

	/**
	 * 更新sku
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView updateInit(HttpServletRequest request,
			HttpServletResponse response, ProductType productType) {

		Page<ProductType> page = PageUtils.getPage(request); // 分页
		Page<ProductType> resultPage = txncoreProductTypeClientService
				.queryProductType(productType, page);

		ProductType productTypeResult = resultPage.getResult().get(0);
		Map resultMap = new HashMap();
		resultMap.put("productType", productTypeResult);
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
			HttpServletResponse response, ProductType productType)
			throws Exception {

		boolean updateFlag = txncoreProductTypeClientService
				.updateType(productType);

		if (!updateFlag) {
			response.getWriter().print(0);
		} else {
			response.getWriter().print(1);
		}

		return null;
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

	public void setUpdateView(String updateView) {
		this.updateView = updateView;
	}

	public void setTxncoreProductTypeClientService(
			TxncoreProductTypeClientService txncoreProductTypeClientService) {
		this.txncoreProductTypeClientService = txncoreProductTypeClientService;
	}

}
