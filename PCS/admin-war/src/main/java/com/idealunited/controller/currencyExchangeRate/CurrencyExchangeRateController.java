/**
 *  File: CurrencyExchangeRateController.java
 *  Date: 2016年5月26日
 *  Author: chaoyue 
 *  Changes: Create
 *  Copyright 2006-2016 HNAPAY Corporation. All rights reserved.
 *  Description:
 *
 */
package com.idealunited.controller.currencyExchangeRate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.idealunited.client.TxncoreCurrencyExchangeRateClientService;
import com.idealunited.dto.CurrencyExchangeRate;
import com.idealunited.util.CurrencyCodeEnum;

/**
 * 汇率信息管理控制器
 */
public class CurrencyExchangeRateController extends MultiActionController {

	private final Log logger = LogFactory.getLog(getClass());
	private String initView;
	private String listView;
	private String updateView;

	private TxncoreCurrencyExchangeRateClientService txncoreCurrencyExchangeRateClientService;

	public void setTxncoreCurrencyExchangeRateClientService(
			TxncoreCurrencyExchangeRateClientService txncoreCurrencyExchangeRateClientService) {
		this.txncoreCurrencyExchangeRateClientService = txncoreCurrencyExchangeRateClientService;
	}

	/**
	 * 汇率管理初始页
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CurrencyCodeEnum[] currencys = CurrencyCodeEnum.values();
		Map resultMap = new HashMap();
		resultMap.put("currencys", currencys);
		return new ModelAndView(initView);
	}

	/**
	 * 查询汇率
	 * 
	 * @param request
	 * @param response
	 * @param productStock
	 * @return
	 * @throws Exception
	 */
	public ModelAndView search(HttpServletRequest request,
			HttpServletResponse response,
			CurrencyExchangeRate currencyExchangeRate) throws Exception {

		List<CurrencyExchangeRate> resultList = txncoreCurrencyExchangeRateClientService
				.query(currencyExchangeRate);
		Map resultMap = new HashMap();
		resultMap.put("rateList", resultList);
		return new ModelAndView(listView, resultMap);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param currencyExchangeRate
	 * @return
	 * @throws Exception
	 */
	public ModelAndView updateInit(HttpServletRequest request,
			HttpServletResponse response,
			CurrencyExchangeRate currencyExchangeRate) throws Exception {

		List<CurrencyExchangeRate> resultList = txncoreCurrencyExchangeRateClientService
				.query(currencyExchangeRate);
		CurrencyCodeEnum[] currencys = CurrencyCodeEnum.values();
		Map resultMap = new HashMap();
		resultMap.put("currencys", currencys);
		resultMap.put("rate", resultList.get(0));
		return new ModelAndView(updateView, resultMap);
	}

	/**
	 * 修改汇率
	 * 
	 * @param request
	 * @param response
	 * @param currencyExchangeRate
	 * @return
	 * @throws Exception
	 */
	public ModelAndView update(HttpServletRequest request,
			HttpServletResponse response,
			CurrencyExchangeRate currencyExchangeRate) throws Exception {

		boolean updateFlg = txncoreCurrencyExchangeRateClientService
				.edit(currencyExchangeRate);
		if (updateFlg) {
			response.getWriter().print(1);
		} else {
			response.getWriter().print(0);
		}
		return null;
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

}
