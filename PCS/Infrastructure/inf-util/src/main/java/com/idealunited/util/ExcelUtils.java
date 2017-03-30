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
import java.util.LinkedList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;

public class ExcelUtils {

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
	public static List getListByReadShell(final Sheet sheet,
			final int beginRow, final int beginCol, final int colCount,
			final String[] colPropertity, final Class targetClass) {
		int rowCount = sheet.getRows();
		return ExcelUtils.getListByReadShellAndRowCount(sheet, beginRow,
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
	public static List getListByReadShellAndMinusCol(final Sheet sheet,
			final int beginRow, final int beginCol, final int colCount,
			final int minusCol, final String[] colPropertity,
			final Class targetClass) {
		int rowCount = sheet.getRows() - minusCol;
		return ExcelUtils.getListByReadShellAndRowCount(sheet, beginRow,
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
	public static List getListByReadShellAndRowCount(final Sheet sheet,
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
				Cell cell = null;
				try {
					cell = sheet.getCell(j, i);
				} catch (ArrayIndexOutOfBoundsException e) {
					// values.addPropertyValue(new
					// PropertyValue(colPropertity[j],""));
					break;
				}
				if (null == cell) {
					// values.addPropertyValue(colPropertity[j],"");
					break;
				}
				String value = cell.getContents();
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
	 * 用jxl写excel.
	 * 
	 * @param book
	 *            WritableWorkbook
	 * @param sheetIndex
	 *            int ，excel中sheet的标号
	 * @param sheetName
	 *            String
	 * @param viewCol
	 *            String[]
	 * @param classPropertity
	 *            String[]
	 * @param resultList
	 *            List
	 * @param bean
	 *            Class
	 * @return WritableWorkbook
	 * @throws WriteException
	 *             WriteException
	 * @throws IOException
	 *             IOException
	 */
	public static WritableWorkbook getBook(final WritableWorkbook book,
			final int sheetIndex, final String sheetName,
			final String[] viewCol, final String[] classPropertity,
			final List resultList, final Class bean) throws WriteException,
			IOException {
		WritableSheet sheet1 = book.createSheet(sheetName, sheetIndex);
		WritableCellFormat format1 = new WritableCellFormat(); // font1

		// 把水平对齐方式指定为居中
		format1.setAlignment(jxl.format.Alignment.CENTRE);
		// 把垂直对齐方式指定为居中
		format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		// format1.setFont()
		Label label = null;
		for (int i = 0, len = viewCol.length; i < len; i++) {
			label = new Label(i, 0, viewCol[i]);
			sheet1.addCell(label);
		}
		BeanWrapper sourceBw = null;
		for (int i = 0, len = resultList.size(); i < len; i++) {
			sourceBw = new BeanWrapperImpl(resultList.get(i));
			for (int j = 0, db_len = classPropertity.length; j < db_len; j++) {
				String cloName = classPropertity[j];
				if (StringUtil.isEmpty(cloName)) {
					label = new Label(j, i + 1, "");
				} else {
					label = new Label(
							j,
							i + 1,
							StringUtil.isNull(String.valueOf(sourceBw
									.getPropertyValue(classPropertity[j]))) ? " "
									: String.valueOf(sourceBw
											.getPropertyValue(classPropertity[j])));
				}
				sheet1.addCell(label);
			}
		}
		book.write();

		return book;
	}

	/**
	 * 用jxl写excel.
	 * 
	 * @param book
	 *            WritableWorkbook
	 * @param sheetIndex
	 *            int ，excel中sheet的标号
	 * @param sheetName
	 *            String
	 * @param viewCol
	 *            list 里边没个元素是数组
	 * @param classPropertity
	 *            String[]
	 * @param resultList
	 *            List
	 * @param bean
	 *            Class
	 * @return WritableWorkbook
	 * @throws WriteException
	 *             WriteException
	 * @throws IOException
	 *             IOException
	 */
	public static WritableWorkbook getBook(final WritableWorkbook book,
			final int sheetIndex, final String sheetName,
			final List<String[]> viewCol, final String[] classPropertity,
			final List resultList, final Class bean) throws WriteException,
			IOException {
		WritableSheet sheet1 = book.createSheet(sheetName, sheetIndex);
		WritableCellFormat format1 = new WritableCellFormat(); // font1

		// 把水平对齐方式指定为居中
		format1.setAlignment(jxl.format.Alignment.CENTRE);
		// 把垂直对齐方式指定为居中
		format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		// format1.setFont()
		Label label = null;
		int rows = 0;
		if (viewCol != null && viewCol.size() > 0) {
			rows = viewCol.size();
			for (int i = 0; i < rows; i++) {
				String[] cols = viewCol.get(i);
				for (int j = 0, len = cols.length; j < len; j++) {
					label = new Label(j, i, cols[j]);
					sheet1.addCell(label);
				}
			}
		}
		BeanWrapper sourceBw = null;
		for (int i = 0, len = resultList.size(); i < len; i++) {
			sourceBw = new BeanWrapperImpl(resultList.get(i));
			for (int j = 0, db_len = classPropertity.length; j < db_len; j++) {
				label = new Label(j, i + rows,
						StringUtil.isNull(String.valueOf(sourceBw
								.getPropertyValue(classPropertity[j]))) ? " "
								: String.valueOf(sourceBw
										.getPropertyValue(classPropertity[j])));
				sheet1.addCell(label);
			}
		}
		book.write();

		return book;
	}

	/**
	 * 创建一张Sheet，不write
	 * 
	 * @param book
	 * @param sheetIndex
	 * @param sheetName
	 * @param viewCol
	 * @param classPropertity
	 * @param resultList
	 * @param bean
	 * @return
	 * @throws WriteException
	 * @throws IOException
	 */
	public static WritableWorkbook getBookNotWrite(final WritableWorkbook book,
			final int sheetIndex, final String sheetName,
			final String[] viewCol, final String[] classPropertity,
			final List resultList, final Class bean) throws WriteException,
			IOException {
		WritableSheet sheet1 = book.createSheet(sheetName, sheetIndex);
		WritableCellFormat format1 = new WritableCellFormat(); // font1

		// 把水平对齐方式指定为居中
		format1.setAlignment(jxl.format.Alignment.CENTRE);
		// 把垂直对齐方式指定为居中
		format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		// format1.setFont()
		Label label = null;
		for (int i = 0, len = viewCol.length; i < len; i++) {
			label = new Label(i, 0, viewCol[i]);
			sheet1.addCell(label);
		}
		BeanWrapper sourceBw = null;
		for (int i = 0, len = resultList.size(); i < len; i++) {
			sourceBw = new BeanWrapperImpl(resultList.get(i));
			for (int j = 0, db_len = classPropertity.length; j < db_len; j++) {
				label = new Label(j, i + 1,
						StringUtil.isNull(String.valueOf(sourceBw
								.getPropertyValue(classPropertity[j]))) ? " "
								: String.valueOf(sourceBw
										.getPropertyValue(classPropertity[j])));
				sheet1.addCell(label);
			}
		}

		return book;
	}

	/**
	 * 用jxl写excel,对给定的sheet进行操作.
	 * 
	 * @param sheet
	 *            WritableSheet
	 * @param begRow
	 *            int
	 * @param viewCol
	 *            String[]
	 * @param classPropertity
	 *            String[]
	 * @param resultList
	 *            List
	 * @param bean
	 *            Class
	 * @return WritableWorkbook
	 * @throws WriteException
	 *             WriteException
	 * @throws IOException
	 *             IOException
	 */
	public static WritableSheet getSheet(final WritableSheet sheet,
			final int begRow, final String[] viewCol,
			final String[] classPropertity, final List resultList)
			throws WriteException, IOException {
		WritableCellFormat format1 = new WritableCellFormat(); // font1

		// 把水平对齐方式指定为居中
		format1.setAlignment(jxl.format.Alignment.CENTRE);
		// 把垂直对齐方式指定为居中
		format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		// format1.setFont()
		Label label = null;
		for (int i = 0, len = viewCol.length; i < len; i++) {
			label = new Label(i, begRow, viewCol[i]);
			sheet.addCell(label);
		}
		BeanWrapper sourceBw = null;
		for (int i = 0, len = resultList.size(); i < len; i++) {
			sourceBw = new BeanWrapperImpl(resultList.get(i));
			for (int j = 0, db_len = classPropertity.length; j < db_len; j++) {
				label = new Label(j, begRow + i + 1,
						StringUtil.isNull(String.valueOf(sourceBw
								.getPropertyValue(classPropertity[j]))) ? " "
								: String.valueOf(sourceBw
										.getPropertyValue(classPropertity[j])));
				sheet.addCell(label);
			}
		}
		return sheet;
	}
}
