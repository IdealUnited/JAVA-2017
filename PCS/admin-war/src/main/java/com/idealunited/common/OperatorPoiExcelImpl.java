package com.idealunited.common;

/** @Description 
 * @project 	poss-reconcile
 * @file 		OperatorPoiExcelImpl.java 
 * Copyright © 2006-2010 hnapay Corporation. All rights reserved
 * @version     1.0
 * Date				Author			Changes
 * 2010-8-9		Henry.Zeng			Create 
 */

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * @author Henry.Zeng
 * @since 2010-8-9
 * @see
 */
public class OperatorPoiExcelImpl extends AbstractOperatorPoi implements
		OperatorPoiInterface {

	@Override
	public <T> HSSFWorkbook buildExcel(List<T> list) throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Sheet1");
		sheet.setDefaultColumnWidth(20);

		HSSFCellStyle dateStyle = createDateStyle(workbook);

		HSSFCellStyle textStyle = createTextStyle(workbook);

		HSSFRow row = sheet.createRow(0);

		HSSFCellStyle cellStyle = createCellStyle(workbook);

		HSSFCellStyle normalLeftCellStyle = createNormalLeftCellStyle(workbook);

		int colIndex = 0;
		HSSFCell cell = null;
		cell = row.createCell(colIndex++);
		cell.setCellStyle(cellStyle);
		cell.setCellValue(new HSSFRichTextString("序号"));
		for (int i = 1; i < keyCount + 1; i++) {
			cell = row.createCell(colIndex++);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(new HSSFRichTextString(this.keyValueMap
					.get("thead" + i)));
		}
		int j = 0;
		for (T targetObj : list) {
			colIndex = 0;
			row = sheet.createRow(j + 1);
			cell = row.createCell(colIndex++);
			cell.setCellStyle(normalLeftCellStyle);
			cell.setCellValue(j + 1);
			if (targetClass != null && targetClass.equals(targetObj.getClass())) {

				Method[] methods = targetClass.getMethods();
				Object[] parmerArgs = null;
				String targetMethod = null;
				for (int i = 0; i < keyCount; i++) {
					for (Method method : methods) {
						targetMethod = nameArray.get(i);
						targetMethod = "get"
								+ Character.toUpperCase(targetMethod.charAt(0))
								+ targetMethod.substring(1);
						if (targetMethod.equals(method.getName())) {
							Object targetValue = method.invoke(targetObj,
									parmerArgs);
							cell = row.createCell(colIndex++);
							targetValue = targetValue == null ? ""
									: targetValue;
							if (targetValue instanceof String) {
								cell.setCellStyle(normalLeftCellStyle);
								cell.setCellValue(new HSSFRichTextString(
										(String) targetValue));
							} else if (targetValue instanceof Long
									|| targetValue instanceof BigDecimal
									|| targetValue instanceof Integer) {
								cell.setCellStyle(textStyle);
								cell.setCellValue(new HSSFRichTextString(String
										.valueOf(targetValue)));
							} else if (targetValue instanceof Date) {
								cell.setCellStyle(dateStyle);
								cell.setCellValue((Date) targetValue);
							}
							break;
						}
					}
				}
			}
			j++;
		}
		return workbook;
	}

}
