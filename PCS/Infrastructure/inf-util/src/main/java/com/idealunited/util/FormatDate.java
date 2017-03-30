package com.idealunited.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author lei.jiangl
 * @version
 * @data 2010-7-29 下午02:55:41 日期操作
 */
public class FormatDate {

	/**
	 * 将日期转换成yyyy-MM-dd H:mm:ss格式
	 * 
	 * @param date
	 * @return String
	 */
	public static String formatDate(Date date) {
		if (null == date) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");
		return sdf.format(date);
	}

	/**
	 * 将日期转换成指定格式字符串
	 * 
	 * @param date
	 * @param str
	 * @return
	 */
	public static String formatDate(Date date, String str) {
		SimpleDateFormat sdf = new SimpleDateFormat(str);
		return sdf.format(date);
	}

	/**
	 * 将日期字符串转换成date
	 * 
	 * @param str
	 * @return
	 */
	public static Date formatStr(String str) {
		Date date = null;
		if (!StringUtil.isEmpty( str )) {
			DateFormat sdf = new SimpleDateFormat(DateUtil.SIMPLE_DATE_FROMAT);
			try {
				date = sdf.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}

	/**
	 * 将日期字符串转换成date
	 * 
	 * @param str
	 * @param patt
	 * @return
	 */
	public static Date formatStr(String str, String patt) {
		Date date = null;
		if (str != null) {
			DateFormat sdf = new SimpleDateFormat(patt);
			try {
				date = sdf.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}

	/**
	 * 将日期字符串转换成date
	 * 
	 * @param str
	 * @return
	 */
	public static Date formatlongStr(String longstr) {
		Date date = null;
		if (longstr != null) {
			DateFormat sdf = new SimpleDateFormat(DateUtil.SIMPLE_DATE_FROMAT);
			try {
				date = sdf.parse(longstr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}

	/**
	 * 得到本月的第一天
	 * 
	 * @return
	 */
	public static String getMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMinimum(Calendar.DAY_OF_MONTH));

		return formatDate(calendar.getTime());
	}

	/**
	 * 得到指定时间月份的第一天(0:0:0)
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMonthFirstDay(Date date) {
		/*
		 * Calendar calendar = Calendar.getInstance(); calendar.setTime(date);
		 * calendar.set(Calendar.DAY_OF_MONTH, calendar
		 * .getActualMinimum(Calendar.DAY_OF_MONTH));
		 */
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				1, 0, 0, 0);
		return calendar.getTime();

	}

	/**
	 * 得到下个月的第一天
	 * 
	 * @return
	 */
	public static String getNextMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
		return formatDate(calendar.getTime());
	}

	/**
	 * 得到本月的最后一天
	 * 
	 * @return
	 */
	public static String getMonthLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return formatDate(calendar.getTime());
	}

	/**
	 * 得到批定月的最后一天(23:59:59)
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMonthLastDay(Date date) {
		/*
		 * Calendar calendar = Calendar.getInstance(); calendar.setTime(date);
		 * calendar.set(Calendar.DAY_OF_MONTH, calendar
		 * .getActualMaximum(Calendar.DAY_OF_MONTH));
		 */

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH) + 1, 1, 0, 0, 0);
		calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) - 1);

		return calendar.getTime();
	}

	/**
	 * 得到本周的第一天
	 * 
	 * @return
	 */
	public static String getWeek() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK, 2);
		return formatDate(calendar.getTime());
	}

	/**
	 * 得到本周的第一天 0:0:0
	 * 
	 * @return
	 */
	public static Date getFirstDayOfCurrentWeek() {
		String dateStr = getWeek();
		return FormatDate.formatStr(dateStr);
	}

	/**
	 * 得到n个星期前
	 * 
	 * @param n
	 * @return
	 */
	public static String getLastestWeek(int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.WEEK_OF_YEAR, calendar.get(Calendar.WEEK_OF_YEAR)
				- n);
		return formatDate(calendar.getTime());
	}

	/**
	 * 得到n个月前
	 * 
	 * @param n
	 * @return
	 */
	public static String getLastestMonth(int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONDAY, calendar.get(Calendar.MONTH) - n);
		return formatDate(calendar.getTime());
	}

	/**
	 * 得到n个月前
	 * 
	 * @param n
	 * @return
	 */
	public static Date getDateLastestMonth(int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONDAY, calendar.get(Calendar.MONTH) - n);
		return calendar.getTime();
	}

	/**
	 * 获取当天时间
	 * 
	 * @return
	 */
	public static String getDay() {
		Date date = new Date();
		return FormatDate.formatDate(date, DateUtil.SIMPLE_DATE_FROMAT);
	}

	/**
	 * 获取第二天时间
	 * 
	 * @return
	 */
	public static String getTomorrow() {
		Calendar now = Calendar.getInstance();
		now.set(Calendar.DAY_OF_YEAR, now.get(Calendar.DAY_OF_YEAR) + 1);
		return FormatDate.formatDate(now.getTime(), DateUtil.SIMPLE_DATE_FROMAT);
	}

	/**
	 * 获取n个月以后的时间字符串yyyy-MM-dd
	 * 
	 * @param day
	 * @param n
	 * @return
	 */
	public static Date getDateAfterSeveralMonth(Date day, int n) {
		Calendar c = Calendar.getInstance();
		c.setTime(day);
		c.set(Calendar.YEAR, c.get(Calendar.YEAR) + n);
		return c.getTime();
	}

	/**
	 * 获取n个 月以后的时间(毫秒)
	 * 
	 * @param day
	 * @param n
	 * @return
	 */
	public static long getDateTimeAfterSeveralMonth(Date day, int n) {
		Calendar c = Calendar.getInstance();
		c.setTime(day);
		c.set(Calendar.YEAR, c.get(Calendar.YEAR) + n);
		return c.getTimeInMillis();
	}

	/**
	 * 时间比较返回天数
	 * 
	 * @param fDate
	 * @param oDate
	 * @return
	 */
	public static int getIntervalDays(Date fDate, Date oDate) {

		if (null == fDate || null == oDate) {

			return -1;

		}

		long intervalMilli = oDate.getTime() - fDate.getTime();

		return (int) (intervalMilli / (24 * 60 * 60 * 1000));

	}

	public static int getIntervalMinutes(Date fDate, Date oDate) {
		if (null == fDate || null == oDate) {
			return -1;
		}
		long intervalMilli = oDate.getTime() - fDate.getTime();
		return (int) (intervalMilli / (1000 * 60));

	}

	/**
	 * 时间比较返回天数
	 * 
	 * @param fDate
	 * @param oDate
	 * @return
	 */
	public static int daysOfTwo(Date fDate, Date oDate) {

		Calendar aCalendar = Calendar.getInstance();

		aCalendar.setTime(fDate);

		int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);

		aCalendar.setTime(oDate);

		int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);

		return day2 - day1;

	}

	public static long sceondOfTwoDate(Date createtime, int minuttes) {
		Date datenow = new Date();
		long diff = (createtime.getTime() + minuttes * 60 * 1000 - datenow
				.getTime()) / 1000;
		if (diff <= 0) {
			diff = 0;
		}
		return diff;
	}

	public static Date getNextDay(Date sDate) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(sDate);

		calendar.set(Calendar.DAY_OF_YEAR,
				calendar.get(Calendar.DAY_OF_YEAR) + 1);

		return calendar.getTime();
	}

	/**
	 * 得到本月的第一天
	 * 
	 * @return
	 */
	public static Date getFirstDayOfCurrentMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				1, 0, 0, 0);
		return calendar.getTime();
	}

	/**
	 * 返回当前月的最后一天最后一秒
	 * 
	 * @return
	 */
	public static Date getLastTimeOfLastDayOfCurrentMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH) + 1, 1, 0, 0, 0);
		calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) - 1);
		return calendar.getTime();
	}

	/**
	 * 返回指定天的最后一秒的时间
	 * 
	 * @param sDate
	 * @return
	 */
	public static Date getLastestTimeOfDay(Date sDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sDate);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		calendar.set(Calendar.DAY_OF_YEAR,
				calendar.get(Calendar.DAY_OF_YEAR) + 1);
		calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) - 1);
		return calendar.getTime();
	}

	public static String skipDateTime(String timeStr, int skipDay) {
		String pattern = "yyyy-MM-dd HH:mm";
		Date d = parse(pattern, timeStr);
		Date date = skipDateTime(d, skipDay);
		return formatDateTime(pattern, date);
	}

	public static java.util.Date parse(String pattern, String strDateTime) {
		java.util.Date date = null;
		if (strDateTime == null || pattern == null)
			return null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			formatter.setLenient(false);
			date = formatter.parse(strDateTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String formatDateTime(String pattern, java.util.Date date) {
		String strDate = null;
		String strFormat = pattern;
		SimpleDateFormat dateFormat = null;

		if (date == null)
			return "";

		dateFormat = new SimpleDateFormat(strFormat);
		strDate = dateFormat.format(date);

		return strDate;
	}

	public static Date skipDateTime(Date d, int skipDay) {
		if (d == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.add(Calendar.DATE, skipDay);
		return calendar.getTime();
	}

	/**
	 * 给指定的时间加上指定的天数小时数和分钟数后返回。
	 */
	public static Date getDateTime(Date date, int skipDay, int skipHour,
			int skipMinute) {
		if (null == date) {
			return null;
		}

		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);

		cal.add(GregorianCalendar.DAY_OF_MONTH, skipDay);
		cal.add(GregorianCalendar.HOUR_OF_DAY, skipHour);
		cal.add(GregorianCalendar.MINUTE, skipMinute);

		return cal.getTime();
	}

	public static String getRandomDate() {
		DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(new Date());
	}

	/**
	 * 将日期字符串转换成date yyyy-MM-dd hh:ss格式
	 * 
	 * @param str
	 * @return
	 */
	public static Date formatStr2(String str) {
		Date date = null;
		if (str != null) {
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			try {
				date = sdf.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}

	public static void main(String[] args) {
		System.out.println(FormatDate.getWeek());
		System.out.println(getFirstDayOfCurrentWeek());
		System.out.println(FormatDate.getFirstDayOfCurrentMonth());
		System.out.println(FormatDate.getLastTimeOfLastDayOfCurrentMonth());
		System.out.println(FormatDate.getLastestTimeOfDay(new Date()));
		System.out.println(getDay());

		System.out.println(FormatDate.formatDate(
				getDateTime(new Date(), 0, 0, 5), "yyyy-MM-dd HH:mm"));

		System.out.println(FormatDate.formatDate(getMonthFirstDay(new Date()),
				"yyyy-MM-dd HH:mm:ss"));
		System.out.println(FormatDate.formatDate(getMonthLastDay(new Date()),
				"yyyy-MM-dd HH:mm:ss"));
	}

}