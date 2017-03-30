package com.idealunited.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateStringUtil {

	public static String date2String(Date date) {
		
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		str = sdf.format(date);
		return str;
	}
	/**
	 * 日期转换
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String date2String(Date date,String pattern) {

		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		str = sdf.format(date);
		return str;
	}
	
	public static Date String2Date(String str) {

		String yyyy = str.substring(0, 4);
		String MM = str.substring(4, 6);
		String dd = str.substring(6, 8);
		String HH = str.substring(8, 10);
		String mm = str.substring(10, 12);
		String ss = str.substring(12, 14);

		Date date = new Date();
		date.setYear(Integer.valueOf(yyyy) - 1900);
		date.setMonth(Integer.valueOf(MM) - 1);
		date.setDate(Integer.valueOf(dd));
		date.setHours(Integer.valueOf(HH));
		date.setMinutes(Integer.valueOf(mm));
		date.setSeconds(Integer.valueOf(ss));

		return date;
	}
	
	public static Date parse2DateString(String str){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		 try {
		   return df.parse(str);
		 } catch (Exception ex) { 
			 ex.printStackTrace();
		 }
		 return null;
	}
	
	public static Date formatStr2Date(String strDate){
		DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");      
		Date date = null;
		try {
			date = fmt.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static String parse2String(String str){
		if(null != str && !str.isEmpty()) {
			StringBuffer dateStr = new StringBuffer();
			try{
				dateStr.append(str.substring(0, 4))
					.append(str.substring(5, 7))
					.append(str.substring(8, 10))
					.append(str.substring(11, 13))
					.append(str.substring(14, 16))
					.append(str.substring(17, 19));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return dateStr.toString();
		} else {
			return "";
		}
	}
	
	public static void main(String[] astg){
		String dateString = "2011-04-24 14:03:22";
		Date d = new Date();
		System.out.println(d.toLocaleString());
		System.out.println(DateStringUtil.parse2String(dateString));
		System.out.println(DateStringUtil.date2String(new Date(),"yyyyMMddHHmmssSSSS"));
	}
}
