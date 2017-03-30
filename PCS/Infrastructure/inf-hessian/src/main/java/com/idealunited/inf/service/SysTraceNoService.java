/**
 * 
 */
package com.idealunited.inf.service;

import com.idealunited.util.DateUtil;
import com.idealunited.util.RandomUtil;

/**
 * @author chaoyue
 * 
 */
public class SysTraceNoService {

	/**
	 * 根据系统
	 * @param systemCode
	 * @return
	 */
	public static String generateSysTraceNo(String systemCode) {
		String pattern = "yyyyMMddHHmmss";
		String date = DateUtil.formatDateTime(pattern);
		return systemCode + date + RandomUtil.randomDegital(3);
	}
	
	public static void main(String[] args) {
		System.out.println(SysTraceNoService.generateSysTraceNo("301"));
	}
}
