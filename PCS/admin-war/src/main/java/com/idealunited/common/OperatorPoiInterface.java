/** @Description 
 * @project 	poss-reconcile
 * @file 		OpeatorPoiInterface.java 
 * Copyright © 2006-2010 hnapay Corporation. All rights reserved
 * @version     1.0
 * Date				Author			Changes
 * 2010-8-9		Henry.Zeng			Create 
 */
package com.idealunited.common;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * <p>
 * 操作Excel的接口
 * </p>
 * 
 * @author Henry.Zeng
 * @since 2010-8-9
 * @see
 */
public interface OperatorPoiInterface {

	public <T> HSSFWorkbook buildExcel(List<T> sourceList) throws Exception;
}
