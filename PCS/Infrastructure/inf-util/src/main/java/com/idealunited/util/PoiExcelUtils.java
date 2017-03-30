/**
 *  File: ExcelUtils.java
 *  Description:
 *  Copyright © 2006-2011 pay.com . All rights reserved. 
 *  Date      Author      Changes
 *  2011-8-01   terry_ma     Create
 *	完成excel的基本操作，用jxl.
 */
package com.idealunited.util;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;

public class PoiExcelUtils {

	/**
	 * 根据sheet得到所有的值，通过targetClass包装后返回List.
	 * 
	 * @param sheet
	 *            Sheet
	 * @param beginRow
	 *            int,从那行开始
	 * @param beginCol
	 *            int ,从那列开始赋值
	 * @param colCount
	 *            int，excel中总的列数
	 * @param colPropertity
	 *            String[],列中对应的值在class中的字段
	 * @param targetClass
	 *            Class,List 中存放的class
	 * @return List
	 */
	public static List getListByReadShell(final XSSFSheet  sheet,
			final int beginRow, final int beginCol, final int colCount,
			final String[] colPropertity, final Class targetClass) {
		int rowCount = sheet.getLastRowNum()+1;
		return PoiExcelUtils.getListByReadShellAndRowCount(sheet, beginRow,
				beginCol, colCount, rowCount, colPropertity, targetClass);
	}

	/**
	 * 根据sheet得到所有的值，通过targetClass包装后返回List.
	 * 
	 * @param sheet
	 *            Sheet
	 * @param beginRow
	 *            int,从那行开始
	 * @param beginCol
	 *            int ,从那列开始赋值
	 * @param colCount
	 *            int，excel中总的列数
	 * @param minusCol
	 *            int 减去多少行
	 * @param colPropertity
	 *            String[],列中对应的值在class中的字段
	 * @param targetClass
	 *            Class,List 中存放的class
	 * @return List
	 */
	public static List getListByReadShellAndMinusCol(final XSSFSheet sheet,
			final int beginRow, final int beginCol, final int colCount,
			final int minusCol, final String[] colPropertity,
			final Class targetClass) {
		int rowCount = sheet.getLastRowNum() - minusCol;
		return PoiExcelUtils.getListByReadShellAndRowCount(sheet, beginRow,
				beginCol, colCount, rowCount, colPropertity, targetClass);
	}

	/**
	 * 根据sheet得到所有的值，通过targetClass包装后返回List.
	 * 
	 * @param sheet
	 *            Sheet
	 * @param beginRow
	 *            int,从那行开始
	 * @param beginCol
	 *            int ,从那列开始赋值
	 * @param colCount
	 *            int，excel中总的列数
	 * @param rowCount
	 *            int ,excel中总行数
	 * @param colPropertity
	 *            String[],列中对应的值在class中的字段
	 * @param targetClass
	 *            Class,List 中存放的class
	 * @return List
	 */
	public static List getListByReadShellAndRowCount(final XSSFSheet sheet,
			final int beginRow, final int beginCol, final int colCount,
			final int rowCount, final String[] colPropertity,
			final Class targetClass) {
		BeanWrapper sourceBw = null;
		List resultList = new LinkedList();
		boolean isEmpty;
		for (int i = beginRow; i < rowCount; i++) {
			isEmpty = true;
			sourceBw = new BeanWrapperImpl(targetClass);
			MutablePropertyValues values = new MutablePropertyValues();
			// 可以优化
			for (int j = beginCol; j < colCount; j++) {
				XSSFCell cell = null;
				try {
					cell = sheet.getRow(i).getCell(j);
				} catch (ArrayIndexOutOfBoundsException e) {
					// values.addPropertyValue(new
					// PropertyValue(colPropertity[j],""));
					break;
				}
				if (null == cell) {
					// values.addPropertyValue(colPropertity[j],"");
					break;
				}
				String value = getStringCellValue(cell);
				if (!StringUtil.isNull(value)) {
					value = value.trim();
					isEmpty = false;
				}
				PropertyDescriptor targetDesc = sourceBw
						.getPropertyDescriptor(colPropertity[j]);
				if (targetDesc.getWriteMethod() != null
						&& targetDesc.getReadMethod() != null) {
					values.addPropertyValue(new PropertyValue(colPropertity[j],
							value));
				}
			}
			if (!isEmpty) {
				sourceBw.setPropertyValues(values);
				resultList.add(sourceBw.getWrappedInstance());
			}
		}
		return resultList;
	}

	
	/**
     * 获取单元格数据内容为字符串类型的数据
     * 
     * @param cell Excel单元格
     * @return String 单元格数据内容
     */
    private static String getStringCellValue(XSSFCell cell) {
        String strCell = "";
        switch (cell.getCellType()) {
        case XSSFCell.CELL_TYPE_STRING:
            strCell = cell.getStringCellValue();
            break;
        case XSSFCell.CELL_TYPE_NUMERIC:
        	strCell = String.valueOf(cell.getNumericCellValue());
            break;
        case XSSFCell.CELL_TYPE_BOOLEAN:
            strCell = String.valueOf(cell.getBooleanCellValue());
            break;
        case XSSFCell.CELL_TYPE_BLANK:
            strCell = "";
            break;
        default:
            strCell = "";
            break;
        }
        if (strCell.equals("") || strCell == null) {
            return "";
        }
        if (cell == null) {
            return "";
        }
        return strCell;
    }
	
}
