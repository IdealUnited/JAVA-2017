package com.idealunited.common;

/** @Description 
 * @project 	poss-reconcile
 * @file 		AbstractOperatorPoi.java 
 * Copyright © 2006-2010 hnapay Corporation. All rights reserved
 * @version     1.0
 * Date				Author			Changes
 * 2010-8-9		Henry.Zeng			Create 
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * <p>操作Excel</p>
 * 
 * @author Henry.Zeng
 * @since 2010-8-9
 * @see
 */
public abstract class AbstractOperatorPoi implements OperatorPoiInterface {
	
	protected final static transient Log log = LogFactory.getLog(AbstractOperatorPoi.class);	
	
	protected short cellWidth ;

	protected Integer keyCount ;
	
	protected  String keyValueString;

	/**
	 * dto 熟悉名字对应keyValueString
	 */
	protected String propertyNames ;
	/**
	 * 存放
	 */
	protected Map<String,String> keyValueMap;

	protected Class<?> targetClass ;
	
	protected List<String> nameArray;
	

	public void setKeyCount(Integer keyCount) {
		this.keyCount = keyCount;
	}

	public void setKeyValueString(String keyValueString) {
		if (keyValueString!=null && keyValueString.length()>2){
			  keyValueString=keyValueString.substring(1,keyValueString.length()-1);
		      String[] allKeyValues=keyValueString.split(",");
		      keyValueMap = new HashMap<String, String>();
		      for(String keyValue : allKeyValues  ){
		        String[] keyValues=keyValue.split("-");
		        if (keyValues.length > 1 && keyValues[1]!=null && !keyValues[1].equals("null")) 
		        	keyValueMap.put(keyValues[0], keyValues[1]);
		        else 
		        	keyValueMap.put(keyValues[0], "");
		      }
		    }
	}
	
	
	public void setPropertyNames(String propertyNames) {
		String[] strArray = propertyNames.split(",");
		nameArray = new ArrayList<String>();
		for (String name : strArray) {
			nameArray.add(name);
		}
	}
	
	

	public void setTargetClass(Class<?> targetClass) {
		this.targetClass = targetClass;
	}

	
	public void setCellWidth(short cellWidth) {
		this.cellWidth = cellWidth;
	}
	
	@Override
	abstract public <T> HSSFWorkbook buildExcel(List<T> sourceList)
			throws Exception;
	
	protected HSSFCellStyle createCurrencyStyle(HSSFWorkbook workbook) {
		HSSFDataFormat df = workbook.createDataFormat();
		HSSFCellStyle currencyStyle = workbook.createCellStyle();
		currencyStyle.setDataFormat(df.getFormat("###0.00"));
		currencyStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		currencyStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		currencyStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		currencyStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		return currencyStyle;
	}

	protected HSSFCellStyle createDateStyle(HSSFWorkbook workbook) {
		HSSFDataFormat df = workbook.createDataFormat();
		
		HSSFCellStyle dateStyle = workbook.createCellStyle();
		dateStyle.setDataFormat(df.getFormat("yyyy年mm月dd日"));
		dateStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		dateStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		dateStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		dateStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
;		return dateStyle;
	}

	protected HSSFCellStyle createTextStyle(HSSFWorkbook workbook) {
		HSSFDataFormat df = workbook.createDataFormat();
		HSSFCellStyle textStyle = workbook.createCellStyle();
		textStyle.setDataFormat(df.getFormat("text"));
		textStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		textStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		textStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		textStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		return textStyle;
	}

	protected HSSFCellStyle createCellStyle(HSSFWorkbook workbook) {
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setFont(font);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		return cellStyle;
	}

	protected HSSFCellStyle createNormalLeftCellStyle(HSSFWorkbook workbook) {
		HSSFCellStyle normalLeftCellStyle = workbook.createCellStyle();
		normalLeftCellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		normalLeftCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		normalLeftCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		normalLeftCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		normalLeftCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		return normalLeftCellStyle;
	}

	protected HSSFCellStyle createnNormalRightCellStyle(HSSFWorkbook workbook) {
		HSSFCellStyle normalRightCellStyle = workbook.createCellStyle();
		normalRightCellStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		normalRightCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		normalRightCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		normalRightCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		normalRightCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		return normalRightCellStyle;
	}

}
