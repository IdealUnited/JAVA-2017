/** @Description 
 * @file 		QuotationController.java 
 * Copyright (c) 2006-2010 pay Corporation. All rights reserved
 * @version     1.0
 * Date				Author			Changes
 * 2010-10-2		Henry.Zeng			Create 
 */
package com.idealunited.controller.quotation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.idealunited.client.TxncoreChannelClientService;
import com.idealunited.client.TxncoreQuotationClientService;
import com.idealunited.dto.Country;
import com.idealunited.dto.ExpressChannel;
import com.idealunited.dto.Quotation;
import com.idealunited.file.model.FileQuotation;
import com.idealunited.file.parser.dto.FileParseResult;
import com.idealunited.file.service.FileService;
import com.idealunited.inf.comm.PageUtils;
import com.idealunited.inf.dao.Page;
import com.idealunited.poss.security.util.SessionUserHolderUtil;
import com.idealunited.util.BeanConvertUtil;

public class QuotationController extends MultiActionController {

	private final Log logger = LogFactory.getLog(getClass());
	private String initView;
	private String listView;
	private TxncoreQuotationClientService txncoreQuotationClientService;
	private TxncoreChannelClientService txncoreChannelClientService;
	private FileService fileService;

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ExpressChannel expressChannel = new ExpressChannel();
		List<ExpressChannel> expressChannels = txncoreChannelClientService
				.queryExpressChannel(expressChannel);
		Map resultMap = new HashMap();
		resultMap.put("expressChannels", expressChannels);
		return new ModelAndView(initView, resultMap);
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
			HttpServletResponse response, Quotation quotation) throws Exception {

		Page<Quotation> page = PageUtils.getPage(request);
		Page<Quotation> resultPage = txncoreQuotationClientService
				.queryQuotation(quotation, page);
		Map resultMap = new HashMap();
		resultMap.put("page", resultPage);
		return new ModelAndView(listView, resultMap);
	}

	/**
	 * 上传操作
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView upload(HttpServletRequest request,
			HttpServletResponse response) {

		final MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		final CommonsMultipartFile orginalFile = (CommonsMultipartFile) multipartRequest
				.getFile("file");
		String channelCode = request.getParameter("channelCode");

		String operator = SessionUserHolderUtil.getLoginId();
		try {
			FileParseResult fileParseResult = fileService.parserFileInfo(
					orginalFile.getInputStream(), orginalFile.getName(),
					channelCode);

			List<FileQuotation> list = fileParseResult.getList();

			List<Quotation> quotations = (List<Quotation>) BeanConvertUtil
					.convert(Quotation.class, list);

			txncoreQuotationClientService.createQuotation(quotations);
		} catch (Exception e) {
			logger.error("parse error:", e);
		}

		ExpressChannel expressChannel = new ExpressChannel();
		List<ExpressChannel> expressChannels = txncoreChannelClientService
				.queryExpressChannel(expressChannel);
		Map resultMap = new HashMap();
		resultMap.put("expressChannels", expressChannels);
		return new ModelAndView(initView, resultMap);
	}

	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	public void setInitView(String initView) {
		this.initView = initView;
	}

	public void setListView(String listView) {
		this.listView = listView;
	}

	public void setTxncoreQuotationClientService(
			TxncoreQuotationClientService txncoreQuotationClientService) {
		this.txncoreQuotationClientService = txncoreQuotationClientService;
	}

	public void setTxncoreChannelClientService(
			TxncoreChannelClientService txncoreChannelClientService) {
		this.txncoreChannelClientService = txncoreChannelClientService;
	}

}
