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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.idealunited.client.TxncoreOrderClientService;
import com.idealunited.dto.ImportOrderDto;
import com.idealunited.dto.TradeOrder;
import com.idealunited.file.parser.dto.FileParseResult;
import com.idealunited.file.service.FileService;
import com.idealunited.inf.comm.PageUtils;
import com.idealunited.inf.dao.Page;
import com.idealunited.util.BeanConvertUtil;
import com.idealunited.util.MapUtil;

/**
 * 订单管理控制器
 */
public class ImportOrderController extends MultiActionController {

	private final Log logger = LogFactory.getLog(getClass());
	private String initView;
	private String listView;
	private FileService fileService;
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
	 * 导入订单
	 * 
	 * @param request
	 * @param response
	 * @param orderCriteria
	 * @return
	 * @throws Exception
	 */
	public ModelAndView importOrder(HttpServletRequest request,
			HttpServletResponse response, ImportCommand importCommand)
			throws Exception {

		FileParseResult fileParseResult = fileService.parserFileInfo(
				importCommand.getFile().getInputStream(), importCommand
						.getFile().getName(), importCommand.getPlatform());

		List<com.idealunited.file.model.ImportOrderDto> resultList = fileParseResult
				.getList();

		List<ImportOrderDto> orders = (List<ImportOrderDto>) BeanConvertUtil
				.convert(ImportOrderDto.class, resultList);

		Map resultMap = txncoreOrderClientService.importOrder(orders);
		logger.info("resultMap:" + resultMap);
		return new ModelAndView(initView, resultMap);
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
		page.setTotalCount(page.getTotalRecord());
		page.setPageNo(page.getTargetPage());
		Map resultMap = new HashMap();
		resultMap.put("page", resultPage);
		return new ModelAndView(listView, resultMap);
	}

	/**
	 * 下载操作
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView download(HttpServletRequest request,
			HttpServletResponse response) {

		String fileName = request.getParameter("fileName");

		InputStream is = this.getClass().getResourceAsStream(
				"/template/" + fileName);
		response.setContentType("application/octet-stream; charset=UTF-8");
		OutputStream out = null;
		try {
			response.setHeader("Content-Disposition", "attachment;filename=\""
					+ URLEncoder.encode(fileName, "UTF-8") + "\"");

			out = response.getOutputStream();
			byte[] bytes = new byte[1024];
			while (is.read(bytes) != -1) {
				out.write(bytes, 0, bytes.length);
			}
			out.flush();
		} catch (UnsupportedEncodingException e) {

		} catch (Exception e) {

		} finally {
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {

				}
			}
		}
		response.setStatus(HttpServletResponse.SC_OK);
		return null;
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

	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

}
