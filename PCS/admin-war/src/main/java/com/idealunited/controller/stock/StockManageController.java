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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.idealunited.client.TxncoreRepositoryClientService;
import com.idealunited.client.TxncoreStockClientService;
import com.idealunited.common.OperatorPoiInterface;
import com.idealunited.dto.ProductStock;
import com.idealunited.dto.Repository;
import com.idealunited.inf.comm.PageUtils;
import com.idealunited.inf.dao.Page;
import com.idealunited.inf.enums.ResponseCodeEnum;
import com.idealunited.poss.security.util.SessionUserHolderUtil;
import com.idealunited.poss.systemmanager.formbean.UserFormBean;
import com.idealunited.util.DateUtil;
import com.idealunited.util.StringUtil;

/**
 * 库存管理
 */
public class StockManageController extends MultiActionController {

	private final Log logger = LogFactory.getLog(getClass());
	private String initView;
	private String listView;
	private String failView;
	private String templatePath;
	private TxncoreStockClientService txncoreClientService;
	private OperatorPoiInterface operatorPoiInterface;
	private TxncoreRepositoryClientService txncoreRepositoryClientService;
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

		Page<Repository> repositorys = txncoreRepositoryClientService
				.queryRepository();
		Map paraMap = new HashMap();
		paraMap.put("repositorys", repositorys.getResult());
		return new ModelAndView(initView, paraMap);
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
	 * 下载模版
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView download(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.info("Start");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/xlsx");
		response.setHeader("Content-Disposition",
				"attachment; filename=\"template.xlsx\"");

		InputStream in = null;
		ServletOutputStream out = null;
		try {

			in = new FileInputStream(new File(templatePath));
			int length = in.available();
			byte[] value = new byte[length];
			in.read(value, 0, length);

			out = response.getOutputStream();
			out.write(value, 0, value.length);
			out.flush();
		} catch (IOException e) {
			logger.debug("Fails to get vouch template!", e);
		} finally {
			if (in != null) {
				try {
					in.close();
					in = null;
				} catch (IOException e) {
					logger.debug(
							"fail to shut down vouch template file stream", e);
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					logger.debug("Fails to close output stream!", e);
				}
			}
		}
		logger.info("End");
		return null;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 */
	public ModelAndView upload(final HttpServletRequest request,
			final HttpServletResponse response, StockFileUploadBean bean) {

		Map resultMap = new HashMap();
		MultipartFile file = bean.getFile();
		// no file or file is empty
		if (file == null || file.isEmpty()) {
			logger.debug("No file or file is empty");
			resultMap.put("errorMsg", "上传文件不能为空！");
			return new ModelAndView(failView, resultMap);
		}

		try {
			List<ProductStock> stocks = readExcel(bean.getRepositoryId(),
					file.getInputStream());
			Map<String, String> map = txncoreClientService
					.createProduct(stocks);
			String responseCode = map.get("responseCode");
			String responseDesc = map.get("responseDesc");
			if (!ResponseCodeEnum.SUCCESS.getCode().equals(responseCode)) {
				resultMap.put("errorMsg", responseDesc);
				return new ModelAndView(failView, resultMap);
			}
		} catch (Exception e) {
			logger.error("parse error:", e);
			resultMap.put("errorMsg", "解析文件报错！");
			return new ModelAndView(failView, resultMap);
		}

		return new ModelAndView(initView, resultMap);
	}

	/**
	 * 根据库存sku查询库位号
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView queryStockCode(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String productName = request.getParameter("productName");

		if (StringUtil.isEmpty(productName)) {
			response.getWriter().print("");
			return null;
		}

		ProductStock productStock = new ProductStock();
		productStock.setName(productName);
		Page<ProductStock> resultPage = txncoreClientService.queryProduct(
				productStock, null);

		if (null != resultPage && null != resultPage.getResult()
				&& !resultPage.getResult().isEmpty()) {
			response.getWriter().print(
					resultPage.getResult().get(0).getStockCode());
		}
		return null;
	}

	/**
	 * 导出产品库存
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView export(HttpServletRequest request,
			HttpServletResponse response, ProductStock productStock)
			throws Exception {

		logger.info("Start");

		Page<ProductStock> pageResult = txncoreClientService.queryProduct(
				productStock, null);
		HSSFWorkbook workbook = operatorPoiInterface.buildExcel(pageResult
				.getResult());
		String currentDate = DateUtil.getNowDate("yyyyMMdd");
		String fileName = "ProductStock_" + currentDate + "_"
				+ System.currentTimeMillis() + ".xls";

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/xls");
		response.setHeader("content-disposition", "attachment; filename="
				+ fileName);

		OutputStream out = null;
		try {

			out = response.getOutputStream();
			workbook.write(out);

		} catch (IOException e) {
			logger.debug("Fails to get vouch template!", e);
		} finally {
			if (out != null) {
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					logger.debug("Fails to close output stream!", e);
				}
			}
		}

		logger.info("End");
		return null;
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

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}

	public void setFailView(String failView) {
		this.failView = failView;
	}

	public void setOperatorPoiInterface(
			OperatorPoiInterface operatorPoiInterface) {
		this.operatorPoiInterface = operatorPoiInterface;
	}

	public void setTxncoreRepositoryClientService(
			TxncoreRepositoryClientService txncoreRepositoryClientService) {
		this.txncoreRepositoryClientService = txncoreRepositoryClientService;
	}

	/**
	 * 从流读入数据
	 */
	private List<ProductStock> readExcel(String repositoryId,
			InputStream inputStream) throws IOException {

		logger.info("read Excel Start");

		XSSFWorkbook wb = new XSSFWorkbook(inputStream);

		Sheet sheet = wb.getSheetAt(SHEET_INDEX);

		List<ProductStock> rows = new ArrayList<ProductStock>();

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {

			ProductStock stock = new ProductStock();
			String creator = SessionUserHolderUtil.getLoginId(); // 申请人
			stock.setRepositoryId(repositoryId);
			stock.setCreator(creator);
			Row hssfRow = sheet.getRow(i);
			for (int j = 0, colNum = hssfRow.getLastCellNum(); j < colNum; j++) {
				Cell hssfCell = hssfRow.getCell(j);
				Object value = "";
				if (null != hssfCell) {
					int cellType = hssfCell.getCellType();
					if (cellType == HSSFCell.CELL_TYPE_STRING) {
						value = hssfCell.getStringCellValue();
					} else if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
						double d = hssfCell.getNumericCellValue();
						BigDecimal b = new BigDecimal(d);
						value = b;
					}
				}

				String properties = ProductStock.properties[j];
				stock.setValue(properties, value);
			}

			rows.add(stock);
		}

		logger.info("Row size : " + rows.size());
		logger.info("read Exce  End");
		return rows;
	}
}
