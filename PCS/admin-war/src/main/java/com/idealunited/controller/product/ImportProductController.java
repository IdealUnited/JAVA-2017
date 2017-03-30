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
import java.io.InputStream;
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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.idealunited.client.TxncoreSkuClientService;
import com.idealunited.dto.ProductSku;
import com.idealunited.inf.comm.PageUtils;
import com.idealunited.inf.dao.Page;
import com.idealunited.inf.enums.ResponseCodeEnum;
import com.idealunited.poss.security.util.SessionUserHolderUtil;
import com.idealunited.util.StringUtil;

/**
 * 产品管理
 */
public class ImportProductController extends MultiActionController {

	private final Log logger = LogFactory.getLog(getClass());
	private String importView;
	private TxncoreSkuClientService txncoreSkuClientService;

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

		return new ModelAndView(importView);
	}

	/**
	 * 批量添加SKU
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView importSku(HttpServletRequest request,
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

		return new ModelAndView(importView, resultMap);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 */
	public ModelAndView upload(final HttpServletRequest request,
			final HttpServletResponse response, SkuFileUploadBean bean) {

		Map resultMap = new HashMap();
		MultipartFile file = bean.getFile();
		// no file or file is empty
		if (file == null || file.isEmpty()) {
			logger.debug("No file or file is empty");
			resultMap.put("errorMsg", "上传文件不能为空！");
			return new ModelAndView(importView, resultMap);
		}

		try {
			List<ProductSku> skus = readExcel(bean.getPlatform(),
					file.getInputStream());
			Map<String, String> map = txncoreSkuClientService.createSku(skus);
			String responseCode = map.get("responseCode");
			String responseDesc = map.get("responseDesc");
			if (!ResponseCodeEnum.SUCCESS.getCode().equals(responseCode)) {
				resultMap.put("errorMsg", responseDesc);
				return new ModelAndView(importView, resultMap);
			}
			resultMap.put("errorMsg", responseDesc);
		} catch (Exception e) {
			logger.error("parse error:", e);
			resultMap.put("errorMsg", "解析文件报错！");
			return new ModelAndView(importView, resultMap);
		}

		return new ModelAndView(importView, resultMap);
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

			in = this.getClass().getResourceAsStream(
					"/template/SkuTemplate.xlsx");
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

	public void setImportView(String importView) {
		this.importView = importView;
	}

	public void setTxncoreSkuClientService(
			TxncoreSkuClientService txncoreSkuClientService) {
		this.txncoreSkuClientService = txncoreSkuClientService;
	}

	/**
	 * 从流读入数据
	 */
	private List<ProductSku> readExcel(String platform, InputStream inputStream)
			throws IOException {

		logger.info("read Excel Start");

		XSSFWorkbook wb = new XSSFWorkbook(inputStream);

		Sheet sheet = wb.getSheetAt(0);

		List<ProductSku> rows = new ArrayList<ProductSku>();

		for (int i = 2; i <= sheet.getLastRowNum(); i++) {

			ProductSku sku = new ProductSku();
			String creator = SessionUserHolderUtil.getLoginId(); // 申请人
			sku.setCreator(creator);
			Row hssfRow = sheet.getRow(i);

			StringBuilder priorityLogistics = new StringBuilder();
			for (int j = 0, colNum = hssfRow.getLastCellNum(); j < colNum; j++) {
				Cell hssfCell = hssfRow.getCell(j);
				String value = "";
				if (null != hssfCell) {
					int cellType = hssfCell.getCellType();
					if (cellType == HSSFCell.CELL_TYPE_STRING) {
						value = hssfCell.getStringCellValue();
					} else if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
						double d = hssfCell.getNumericCellValue();
						BigDecimal b = new BigDecimal(d);
						value = b.toString();
					}
				}

				if (1 == j) {
					sku.setSkuCode(value);
				}
				if (2 == j) {
					sku.setAsin(value);
				}
				if (3 == j) {
					sku.setProductName(value);
				}
				if (4 == j) {
					sku.setRepository(value);
				}
				if (j == 5) {
					sku.setUk(value);
				}
				if (j == 6) {
					sku.setDe(value);
				}
				if (j == 7) {
					sku.setFr(value);
				}
				if (j == 8) {
					sku.setEs(value);
				}
				if (j == 9) {
					sku.setIt(value);
				}
				if (j == 10) {
					sku.setUs(value);
				}
				if (j == 11) {
					sku.setAu(value);
				}
				if (j == 12) {
					sku.setCa(value);
				}
				if (j == 13) {
					sku.setAt(value);
				}
				if (j == 14) {
					sku.setCh(value);
				}
				if (j == 15) {
					sku.setBe(value);
				}
				if (j == 16) {
					sku.setIe(value);
				}
				if (17 == j) {
					if ("销售百分比".equals(value)) {
						sku.setPercentageType(1);
					}

				}
				if (18 == j) {
					sku.setPercentage(value);
				}
			}
			sku.setPlatform(platform);

			if (!StringUtil.isEmpty(sku.getSkuCode())) {
				rows.add(sku);
			}

		}

		logger.info("Row size : " + rows.size());
		logger.info("read Exce  End");
		return rows;
	}
}
