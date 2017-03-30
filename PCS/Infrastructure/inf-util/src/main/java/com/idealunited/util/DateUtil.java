package com.idealunited.util;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DateUtil {

	private static Log log = LogFactory.getLog(DateUtil.class);
	private static Date date;
	private static SimpleDateFormat sdf;
	public final static String DEFAULT_DATE_FROMAT = "yyyy-MM-dd HH:mm:ss";
	public final static String SIMPLE_DATE_FROMAT = "yyyy-MM-dd";
	public final static String QUERY_DATE_FORMAT = "yyyy-MM-dd HH:mm";
	public final static String PATTERN1 = "yyyyMMddHHmmss";
	public final static String PATTERN = "yyyyMMdd";

	private DateUtil() {
		// util class, prevent from new instance
	}

	/**
	 * 以指定格式返回当时时间
	 * 
	 * @param pattern
	 *            - 日期显示格式
	 * @return the formatted date-time string
	 * @see java.text.SimpleDateFormat
	 */
	public static String formatDateTime(String pattern) {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(pattern);
		String now = sdf.format(new java.util.Date());
		return now;
	}

	/**
	 * 以指定格式返回指定日期的字符串
	 * 
	 * @param pattern
	 *            - 日期显示格式
	 * @param date
	 *            - 需要格式 化的时间
	 * @return the formatted date-time string
	 * @see java.text.SimpleDateFormat
	 */
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

	/**
	 * 以指定格式 和指定的Local返回指定日期的字符串
	 * 
	 * @param pattern
	 *            - 时间显示格式
	 * @param date
	 *            - 指定的时间
	 * @param locale
	 *            - the locale whose date format symbols should be used
	 * @return the formatted date-time string
	 * @see java.text.SimpleDateFormat
	 */
	public static String formatDateTime(String pattern, java.util.Date date,
			Locale locale) {
		String strDate = null;
		String strFormat = pattern;
		SimpleDateFormat dateFormat = null;

		if (date == null)
			return "";

		dateFormat = new SimpleDateFormat(strFormat, locale);
		strDate = dateFormat.format(date);

		return strDate;
	}

	/**
	 * Parses a string to produce a Date.
	 * 
	 * @param pattern
	 *            - the pattern of the string
	 * @param strDateTime
	 *            - the string to be parsed
	 * @return A Date parsed from the string. In case of error, returns null.
	 */
	public static java.util.Date parse(String pattern, String strDateTime) {
		java.util.Date date = null;
		
		pattern = StringUtils.trimToEmpty( pattern );
		strDateTime = StringUtils.trimToEmpty( strDateTime );
		if ( StringUtils.isEmpty( pattern ) || StringUtils.isEmpty( strDateTime ) )
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
	
	public static Date parse(long millis){
		Calendar c1 = Calendar.getInstance();
		c1.setTimeInMillis(millis);
		return c1.getTime();
	}

	/**
	 * 把指定的日期和时间合成一个。
	 * 
	 * @param date
	 *            - the date
	 * @param time
	 *            - the time
	 * @return the composed datetime
	 */
	public static Date composeDate(Date date, Date time) {
		if (date == null || time == null)
			return null;
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(time);
		Calendar c3 = Calendar.getInstance();
		c3.set(c1.get(Calendar.YEAR), c1.get(Calendar.MONTH),
				c1.get(Calendar.DATE), c2.get(Calendar.HOUR_OF_DAY),
				c2.get(Calendar.MINUTE), c2.get(Calendar.SECOND));
		return c3.getTime();
	}

	/**
	 * 忽略掉指定日期的时间，只返回日期信息
	 * 
	 * @param date
	 * @return
	 */
	public static Date getTheDate(Date date) {
		if (date == null)
			return null;
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date);
		Calendar c2 = Calendar.getInstance();
		c2.set(c1.get(Calendar.YEAR), c1.get(Calendar.MONTH),
				c1.get(Calendar.DATE), 0, 0, 0);
		long millis = c2.getTimeInMillis();
		millis = millis - millis % 1000;
		c2.setTimeInMillis(millis);
		return c2.getTime();
	}
	
	/**
	 * 给指定(d)的日期添加指定(skipDay)的天数
	 * 
	 * @param d
	 * @param skipDay
	 *            需要添加的天数，可以为正数或负数
	 * @return
	 */
	public static Date skipDateTime(Date d, int skipDay) {
		if (d == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.add(Calendar.DATE, skipDay);
		return calendar.getTime();
	}

	public static String skipDateTimeStr(Date d, int skipDay) {
		if (d == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.add(Calendar.DATE, skipDay);
		Date date = calendar.getTime();
		return formatDateTime(SIMPLE_DATE_FROMAT, date);
	}
	
	public static String skipDateStr(Date d, int skipDay) {
		if (d == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.add(Calendar.DATE, skipDay);
		Date date = calendar.getTime();
		return formatDateTime(SIMPLE_DATE_FROMAT, date);
	}

	/**
	 * 以字符串形式返回指定日期添加指定的天数后的结果。
	 */
	public static String skipDateTime(String timeStr, int skipDay) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		Date d = parse(pattern, timeStr);
		Date date = skipDateTime(d, skipDay);
		return formatDateTime(pattern, date);
	}

	/**
	 * 同skipDateTime,但是返回的字符串只有日期部分忽略掉时间的部分
	 */
	public static String skipDate(String dateStr, int skipDay) {
		String pattern = "yyyy-MM-dd";
		Date d = parse(pattern, dateStr);
		Date date = skipDateTime(d, skipDay);
		return formatDateTime(pattern, date);
	}

	/**
	 * 给指定的时间加上指定的天数小时数和分钟数后返回。
	 */
	public static String getTime(String timeStr, int skipDay, int skipHour,
			int skipMinute) {
		if (null == timeStr) {
			return null;
		}

		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(parse("yyyy-MM-dd HH:mm:ss", timeStr));

		cal.add(GregorianCalendar.DAY_OF_MONTH, skipDay);
		cal.add(GregorianCalendar.HOUR_OF_DAY, skipHour);
		cal.add(GregorianCalendar.MINUTE, skipMinute);
		cal.get(GregorianCalendar.DAY_OF_WEEK_IN_MONTH);

		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");

		return dateFormat.format(cal.getTime());
	}

	/**
	 * 比较指定时间和当前时间，如果早于当前时间就返回True否则返回False 如指定2009-01-01和当前时间比较会返回True。
	 */
	public static boolean dateCompare(String str) {
		boolean bea = false;
		SimpleDateFormat sdf_d = new SimpleDateFormat("yyyy-MM-dd");
		String isDate = sdf_d.format(new java.util.Date());
		java.util.Date date1;
		java.util.Date date0;
		try {
			date1 = sdf_d.parse(str);
			date0 = sdf_d.parse(isDate);
			if (date0.after(date1)) {
				bea = true;
			}
		} catch (ParseException e) {
			bea = false;
		}
		return bea;
	}

	/**
	 * 具体到月的时间比较，如果早于当前时间就返回True，否则返回False
	 */
	public static boolean monthCompare(String str) {
		boolean bea = false;
		SimpleDateFormat sdf_m = new SimpleDateFormat("yyyy-MM");
		String isMonth = sdf_m.format(new java.util.Date());
		java.util.Date date1;
		java.util.Date date0;
		try {
			date1 = sdf_m.parse(str);
			date0 = sdf_m.parse(isMonth);
			if (date0.after(date1)) {
				bea = true;
			}
		} catch (ParseException e) {
			bea = false;
		}
		return bea;
	}

	/**
	 * 具体到秒的时间比较，如果早于当前时间就返回True，否则返回False
	 */
	public static boolean secondCompare(String str) {
		boolean bea = false;
		SimpleDateFormat sdf_d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String isDate = sdf_d.format(new java.util.Date());
		java.util.Date date1;
		java.util.Date date0;
		try {
			date1 = sdf_d.parse(str);
			date0 = sdf_d.parse(isDate);
			if (date0.after(date1)) {
				bea = true;
			}
		} catch (ParseException e) {
			bea = false;
		}
		return bea;
	}

	/**
	 * 比较两个具体到秒的时间，如果第一个时间早于第二个时间就返回True，否则返回False
	 * 
	 * @param data1
	 * @param date2
	 * @return
	 */
	public static boolean secondCompare(String data1, String date2) {
		boolean bea = false;
		SimpleDateFormat sdf_d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date1;
		java.util.Date date0;
		try {
			date1 = sdf_d.parse(data1);
			date0 = sdf_d.parse(date2);
			if (date0.after(date1)) {
				bea = true;
			}
		} catch (ParseException e) {
			bea = false;
		}
		return bea;
	}

	/**
	 * 判断是否为闫年
	 */
	public static boolean isLeapYear(int year) {
		if ((((year % 4) == 0) && ((year % 100) != 0)) || ((year % 4) == 0)
				&& ((year % 400) == 0)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 得到指定月份的天数
	 * 
	 * @param month
	 * @param year
	 * @return
	 */
	public static int getMonthsDays(int month, int year) {
		if ((isLeapYear(year) == true) && (month == 2)) {
			return 29;
		} else if ((isLeapYear(year) == false) && (month == 2)) {
			return 28;
		}

		if ((month == 1) || (month == 3) || (month == 5) || (month == 7)
				|| (month == 8) || (month == 10) || (month == 12)) {
			return 31;
		}
		return 30;
	}

	/**
	 * 得到当前是周几
	 * 
	 * @return
	 */
	public static String getWeekDay() {
		DateFormatSymbols symboles = new DateFormatSymbols(Locale.getDefault());
		symboles.setShortWeekdays(new String[] { "", "7", "1", "2", "3", "4",
				"5", "6" });
		SimpleDateFormat date = new SimpleDateFormat("E", symboles);
		return date.format(new Date());
	}

	/**
	 * 得到当前年份
	 */
	public static int getYear() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.YEAR);
	}

	/**
	 * 得到当前月份
	 * 
	 * @return
	 */
	public static int getMonth() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.MONTH);
	}

	/**
	 * 得到当前日期
	 * 
	 * @return
	 */
	public static int getDay() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 取得时间格式为yyyy-MM-dd日期是星期几的数字显示
	 */
	public static int getWeek(String sdate) {
		// 再转换为时间
		Date date = strToDate(sdate, "yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// int hour=c.get(Calendar.DAY_OF_WEEK);
		// hour中存的就是星期几了，其范围 1~7
		// 1=星期日 7=星期六，其他类推

		return c.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 字符串转日期
	 */
	public static Date strToDate(String strDate, String fmt) {
		sdf = new SimpleDateFormat(fmt);
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = sdf.parse(strDate, pos);
		return strtodate;
	}

	/**
	 * 日期转字符串
	 */
	public static String dateToStr(Date date, String fmt) {
		sdf = new SimpleDateFormat(fmt);
		String datetostr = sdf.format(date);
		return datetostr;
	}

	/**
	 * 获取当前日期字符串
	 * 
	 * @return
	 */
	public static String getNowDate(String fmt) {
		Date date = new Date();
		return dateToStr(date, fmt);
	}

	/**
	 * 格式化可以转为日期型的字符串, 如果源字符串为null或空字符串就直接返回源字符串, 需要输出的请自己判断
	 * 
	 * @param 源字符串
	 * @param 时间格式
	 * @return 格式化后的字符串
	 */
	public static String getDateStr(String s, String format) {

		if (StringUtils.isEmpty(s))
			return s;

		format = format == null || "".equals(format) ? "yyyy-MM-dd" : format;
		sdf = new SimpleDateFormat(format);

		try {
			date = sdf.parse(s);
		} catch (java.text.ParseException e) {
			log.debug("在DateUtil类中发生字符串转换日期异常!");
			try {
				date = DateFormat.getInstance().parse(s);
				return dateToStr(date, format);
			} catch (java.text.ParseException e1) {
				e1.printStackTrace();
			}
		}
		return sdf.format(date);
	}

	// 比较两个时间的大小
	public static boolean compareTime(Date d1, Date d2) {
		Calendar calendar1 = java.util.Calendar.getInstance();
		Calendar calendar2 = java.util.Calendar.getInstance();

		calendar1.setTime(d1);
		calendar2.setTime(d2);

		int result = calendar1.compareTo(calendar2);
		if (result == 0)
			return true;
		else if (result < 0)
			return false;
		else
			return true;
	}

	public static Date addMinute(int i) {
		// DateFormat df = new SimpleDateFormat("yyyy-MM-dd EE hh:mm:ss");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MINUTE, i);
		return c.getTime();
	}

	public static Date addHour(int i) {
		// DateFormat df = new SimpleDateFormat("yyyy-MM-dd EE hh:mm:ss");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR, i);
		return c.getTime();
	}

	public static Date addDay(int i, int hour) {
		// DateFormat df = new SimpleDateFormat("yyyy-MM-dd EE hh:mm:ss");
		Calendar c = Calendar.getInstance(TimeZone.getDefault(), Locale.CHINA);
		c.get(Calendar.HOUR_OF_DAY);
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, 0);
		c.add(Calendar.DATE, i);
		return c.getTime();
	}

	/**
	 * 返回时间
	 * 
	 * @param offset
	 *            向前后偏移的天数 0为当天
	 * @return
	 */
	public static Date getDate(int offset) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, offset);
		return new Date(calendar.getTimeInMillis());
	}

	/**
	 * 获取默认开始时间范围
	 * 
	 * @param
	 * @return
	 */
	public static Date getStartTime() {
		return getDate(-1);
	}

	/**
	 * 获取默认结束时间范围
	 * 
	 * @param
	 * @return
	 */
	public static Date getEndTime() {
		return getDate(1);
	}

	public static Map<String, Object> getInitTime() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("startTime", DateUtil.getStartTime());
		model.put("endTime", DateUtil.getEndTime());
		return model;
	}

	/**
	 * 获取日期的天
	 * 
	 * @param date
	 * @return
	 */
	public static int getDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取指定日期的小时值
	 * 
	 * @param date
	 * @return
	 */
	public static int getHour(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 获取指定日期的分钟值
	 * 
	 * @param date
	 * @return
	 */
	public static int getMinute(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MINUTE);
	}

	/**
	 * 两个时间之间相隔多少天
	 * 
	 * @param minuend
	 * @param subtrahend
	 * @return
	 */
	public static long subtraction(Date minuend, Date subtrahend) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		Date currDate = null;
		Date subDate = null;
		try {
			currDate = format.parse(format.format(minuend));
			subDate = format.parse(format.format(subtrahend));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			currDate = minuend;
			subDate = subtrahend;
		}

		long daterange = currDate.getTime() - subDate.getTime();
		long time = 1000 * 3600 * 24;

		return (daterange % time == 0) ? (daterange / time)
				: (daterange / time + 1);
	}

	/**
	 * 获取时间为星期几
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeekOfDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return (c.get(Calendar.DAY_OF_WEEK) - 1);
	}

	public static boolean existInWeek(String timeSource, int week) {
		int len = timeSource.length();
		char temp;
		for (int i = 0; i < len; i++) {
			temp = timeSource.charAt(i);
			if ('1' == temp) {
				if (i == week) {
					return true;
				}
			}
		}
		return false;
	}

	public static String getTime(String fmt, int skipDay) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new Date());

		cal.add(GregorianCalendar.DAY_OF_MONTH, skipDay);
		SimpleDateFormat dateFormat = new SimpleDateFormat(fmt);

		return dateFormat.format(cal.getTime());
	}

	public static int getYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}

	public static int getMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH);
	}
	
	public static void main(String[] args){
		System.out.println(skipDateStr(new Date(),-1));
	}
	
	public static boolean isValidDate(String str,String fmt) {
        boolean convertSuccess=true;
        SimpleDateFormat format = new SimpleDateFormat(fmt);
        try {
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            convertSuccess=false;
        } 
        return convertSuccess;
	}

	public static Date getQueryStartTime( Date date )
	{
		return getBeginningOfDay( date );
	}

	public static Date getBeginningOfDay( Date date )
	{
		if ( date == null )
		{
			return null;
		}

		Calendar calendar = Calendar.getInstance( );
		calendar.setTime( date );

		calendar.set( Calendar.HOUR_OF_DAY, 0 );
		calendar.set( Calendar.MINUTE, 0 );
		calendar.set( Calendar.SECOND, 0 );
		calendar.set( Calendar.MILLISECOND, 0 );

		return calendar.getTime( );
	}

	private static final int LAST_HOUR_OF_DAY = 23;
	private static final int LAST_MINUTE_OF_HOUR = 59;
	private static final int LAST_SECOND_OF_MINUTE = 59;
	private static final int LAST_MILLISECOND = 999;

	public static Date getQueryEndTime( Date date )
	{
		return getEndOfDay( date );
	}

	public static boolean isWorkDay(Date date ){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int weekDay = c.get(Calendar.DAY_OF_WEEK);
		if (weekDay == Calendar.MONDAY || weekDay == Calendar.TUESDAY
				|| weekDay == Calendar.THURSDAY || weekDay == Calendar.FRIDAY
				|| weekDay == Calendar.WEDNESDAY) {
			return true;
		}
		return false;
	}
	
	public static boolean isWorkHour(Calendar calendar,int h,int min){
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		if(hour > h){
			return false;
		}else if(hour == h){
			if(minute > min){
				return false;
			}else{
				return true;
			}
		}else{
			return true;
		}
	}
	
	public static Date getEndOfDay( Date date )
	{
		if ( date == null )
		{
			return null;
		}

		Calendar calendar = Calendar.getInstance( );
		calendar.setTime( date );

		calendar.set( Calendar.HOUR_OF_DAY, LAST_HOUR_OF_DAY );
		calendar.set( Calendar.MINUTE, LAST_MINUTE_OF_HOUR );
		calendar.set( Calendar.SECOND, LAST_SECOND_OF_MINUTE );
		calendar.set( Calendar.MILLISECOND, LAST_MILLISECOND );

		return calendar.getTime( );
	}
}