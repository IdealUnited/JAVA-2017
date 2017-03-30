/**
 * @(#)MfDate.java     06/07/25
 */
package com.idealunited.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.util.Assert;

/**
 * @ 格式化日期<br>
 * 
 * @version 2.0
 * Created on 2006-07-25
 */
public class MfDate {

    // ----------------------------------------------------------- 公有静态常量

    /**
     * 定义一个defaultDatePattern. "yyyy-MM-dd HH:mm:ss"
     */
    public static final String defaultDatePattern = "yyyy-MM-dd";

    /**
     * 定义一个datePattern. "yyyy-MM-dd HH:mm:ss"
     */
    public String datePattern = "yyyy-MM-dd HH:mm:ss";
    
    public static String strPattern = "yyyyMMddHHmmss";
    
    public static String strPatternYYYYMMDD = "yyyyMMdd";

    /**
     * 返回当前系统日期前一天的 MfDate 对象.
     */
    public static final MfDate PAST = new MfDate(-1);

    /**
     * 返回当前系统日期后一天的 MfDate 对象.
     */
    public static final MfDate FUTURE = new MfDate(1);

    // ----------------------------------------------------------- 私有静态常量

    /**
     * 定义一个常量,在hashCode方法里用到.
     */
    private static final int HUNDRED = 100;

    /**
     * Magic number,它表示月份的最大数.
     */
    private static final int MONTH = 12;

    /**
     * Magic number,它表示日的最大数.
     */
    private static final int DAY = 31;

    // ----------------------------------------------------------- 私有变量
    /**
     * @see java.util.GregorianCalendar
     */
    protected GregorianCalendar base;

    // ----------------------------------------------------------- 构造函数
    /**
     * 构造一个新分配的 MfDate 对象，它表示当前系统日期.
     * @see MfDate(GregorianCalendar arg)
     * Example:
     *          MfDate mfDate = new MfDate();
     */
    public MfDate() {
        this(new GregorianCalendar());
    }

    /**
     * 构造一个新分配的 MfDate 对象，它表示指定的 GregorianCalendar 值.
     * @param arg
     *            the arg is GregorianCalendar instance
     * @see java.util.GregorianCalendar
     * Example:
     *          GregorianCalendar gcDate = new GregorianCalendar();
     *          MfDate mfDate = new MfDate(gcDate);
     */
    public MfDate(final GregorianCalendar arg) {
        Assert.notNull(arg, "arg must be not null");
        initialize(arg);
    }

    /**
     * 构造一个新分配的 MfDate 对象，它表示指定的 year, month, day 值.
     * @param year
     *            the year >0.
     * @param month
     *            the month between 1-12.
     * @param day
     *            the day of the month between 1-31.
     * Example:
     *          MfDate mfDate = new MfDate(2006,5,21);
     * @throws IllegalArgumentException
     */
    public MfDate(final int year, final int month, final int day) {
        if (0 >= year) {
            throw new  IllegalArgumentException("year must be >0");
        }
        if (1 > month || MONTH < month) {
            throw new  IllegalArgumentException("month must be >=1 && <= 12");
        }
        if (1 > day || DAY < day) {
            throw new  IllegalArgumentException("day must be >=1 && <= 31");
        }
        initialize(new GregorianCalendar(year, month - 1, day));
    }

    /**
     * 构造一个新分配的 MfDate 对象，它表示指定的 Date 值.
     * @param date
     *            the date is Date instance
     * @see java.util.Date
     * Example:
     *         Date date = new Date();
     *         MfDate mfDate = new MfDate(date);
     */
    public MfDate(final Date date) {
        Assert.notNull(date, "date must be not null");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        initialize(new GregorianCalendar(calendar.get(Calendar.YEAR), calendar
                .get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)));
    }

    /**
     * 构造一个新分配的 MfDate 对象，它表示指定的 Timestamp 值.
     * @param date
     *            the date is Timestamp instance
     */
    public MfDate(final Timestamp date) {
        Assert.notNull(date, "date must be not null");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        initialize(new GregorianCalendar(calendar.get(Calendar.YEAR), calendar
                .get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)));
    }

    /**
     * 构造一个新分配的 MfDate 对象，它表示指定的 String 值.
     * @param dateString
     *            a string to be parsed as a date
     *            格式为"年-月-日" 如：2006-7-25
     * Example:
     *          MfDate mfDate = new MfDate("2006-7-25");
     * @throws IllegalArgumentException
     */
    public MfDate(final String dateString) {
        Assert.notNull(dateString, "dateString must be not null");
        String pattern = this.defaultDatePattern;
        Date date = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            formatter.setLenient(false);
            date = formatter.parse(dateString);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            initialize(new GregorianCalendar(calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH), calendar
                            .get(Calendar.DAY_OF_MONTH)));
        } catch (ParseException e) {
            throw new IllegalArgumentException("parse dateSring error");
        }
    }
    public MfDate(final String dateString , final String datePattern) {
        this.setDatePattern(datePattern);
        Date date = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(datePattern);
            formatter.setLenient(false);
            date = formatter.parse(dateString);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            base = (new GregorianCalendar(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar
                    .get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND)));
        } catch (ParseException e) {
            throw new IllegalArgumentException("parse dateSring error");
        }
    }
    /**
     * 构造一个新分配的 MfDate 对象.
     * 它表示当前系统日期与偏移天数所得的 GregorianCalendar 值.
     * @param arg
     *            int
     * Example:
     *         Date date = new Date(-2);
     */
    public MfDate(final int arg) {
        GregorianCalendar gcDate = new GregorianCalendar();
        gcDate.add(GregorianCalendar.DAY_OF_MONTH, arg);
        initialize(gcDate);
    }

    /**
     * 构造一个新分配的 MfDate 对象，它表示指定的 MfDate 值.
     * @param mfDate
     *            the mfDate is MfDate instance
     * @see java.util.Date
     * Example:
     *         MfDate mfDate = new MfDate();
     *         MfDate mfDate1 = new MfDate(mfDate);
     */
    public MfDate(final MfDate mfDate) {
        Assert.notNull(mfDate, "mfDate must be not null");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mfDate.getTime());
        initialize(new GregorianCalendar(calendar.get(Calendar.YEAR), calendar
                .get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)));
    }
    
    
    public java.sql.Timestamp toTimeStamp(){
    	Calendar calendar = Calendar.getInstance();
    	calendar.set(Calendar.YEAR, this.getYear());
    	calendar.set(Calendar.MONTH, this.getMonth() - 1);
    	calendar.set(Calendar.DAY_OF_MONTH, this.getDay());
    	
    	return new java.sql.Timestamp(calendar.getTimeInMillis());
    }
    
    public java.util.Date toDate(){
    	Calendar calendar = Calendar.getInstance();
    	calendar.set(Calendar.YEAR, this.getYear());
    	calendar.set(Calendar.MONTH, this.getMonth() - 1);
    	calendar.set(Calendar.DAY_OF_MONTH, this.getDay());
    	
    	return calendar.getTime();
    }
    
    // ----------------------------------------------------------- 私有函数
    /**
     * 生成GregorianCalendar对象.
     * @param arg
     *            the arg is GregorianCalendar instance
     * @see java.util.GregorianCalendar
     */
    protected void initialize(final GregorianCalendar arg) {
        Assert.notNull(arg, "arg must be not null");
        GregorianCalendar result = arg;
        result.set(Calendar.HOUR_OF_DAY, 0);
        result.set(Calendar.MINUTE, 0);
        result.set(Calendar.SECOND, 0);
        result.set(Calendar.MILLISECOND, 0);
        base = result;
    }

    // ----------------------------------------------------------- 公有函数
    /**
     * 判断该 MfDate 对象是否在指定对象之后.
     * @param arg
     *            the arg is MfDate instance
     * @return boolean 如果该 MfDate 对象在指定对象之后返回true否则返回flase
     */
    public boolean after(final MfDate arg) {
        Assert.notNull(arg, "arg must be not null");
        return getTime().after(arg.getTime());
    }

    /**
     * 判断该 MfDate 对象是否在指定对象之前.
     * @param arg
     *            the arg is MfDate instance
     * @return boolean 如果该 MfDate 对象在指定对象之前返回true否则返回flase
     */
    public boolean before(final MfDate arg) {
        Assert.notNull(arg, "arg must be not null");
        return getTime().before(arg.getTime());
    }

    /**
     * 该 MfDate 对象和指定对象比较.
     * @param arg
     *            the arg is MfDate instance
     * @return int
     *          the value <code>0</code> if the argument Date is equal to
     *          this Date; a value less than <code>0</code> if this Date
     *          is before the Date argument; and a value greater than
     *         <code>0</code> if this Date is after the Date argument.
     */
    public int compareTo(final MfDate arg) {
        Assert.notNull(arg, "arg must be not null");
        return getTime().compareTo(arg.getTime());
    }

    /**
     * 判断该 MfDate 对象与指定对象是否相等.
     * @param arg
     *            the arg is MfDate instance
     * @return boolean 如果相等返回true否则返回flase
     */
    public boolean equals(final Object arg) {
        Assert.notNull(arg, "arg must be not null");
        if (!(arg instanceof MfDate)) {
            return false;
        }
        MfDate other = (MfDate) arg;
        return (base.equals(other.base));
    }

    /**
     * 返回该 MfDate 对象的哈希码.
     * @return 返回 MfDate 对象的年月日组成的hashCode
     */
    public int hashCode() {
        return getYear().intValue() * HUNDRED * HUNDRED
            + getMonth().intValue() * HUNDRED
            + getDay().intValue();
    }

    /**
     * 获得该 MfDate 对象的日期.
     * @return Date 返回该 MfDate 对象的日期
     */
    public Date getTime() {
        return base.getTime();
    }

    /**
     * 获得年.
     * @return Integer 返回该 MfDate 对象的年
     */
    public Integer getYear() {
        return new Integer(base.get(GregorianCalendar.YEAR));
    }

    /**
     * 获得月.
     * @return Integer 返回该 MfDate 对象的月
     */
    public Integer getMonth() {
        return new Integer(base.get(GregorianCalendar.MONTH) + 1);
    }

    /**
     * 获得日.
     * @return Integer 返回该 MfDate 对象的日
     */
    public Integer getDay() {
        return new Integer(base.get(GregorianCalendar.DAY_OF_MONTH));
    }

    /**
     * 判断两个Mfdate的日是否相等.
     * @param arg
     *            the arg is MfDate instance
     * @return Boolean Boolean.
     */
    public Boolean isSameDay(final MfDate arg) {
        if (this.getDay().equals(arg.getDay())) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 判断两个Mfdate的月是否相等.
     * @param arg
     *            the arg is MfDate instance
     * @return Boolean Boolean.
     */
    public Boolean isSameMonth(final MfDate arg) {
        if (this.getMonth().equals(arg.getMonth())) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 判断两个Mfdate的年是否相等.
     * @param arg
     *            the arg is MfDate instance
     * @return Boolean Boolean.
     */
    public Boolean isSameYear(final MfDate arg) {
        if (this.getYear().equals(arg.getYear())) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 日增运算.
     * @param arg 需增的日数
     * @exception IllegalArgumentException
     */
    public void addDays(final int arg) {
        if (0 >= arg) {
            throw new  IllegalArgumentException("arg must be >0");
        }
        base.add(GregorianCalendar.DAY_OF_MONTH, arg);
    }

    /**
     * 日减运算.
     * @param arg
     *            需减的日数
     * @exception IllegalArgumentException
     */
    public void minusDays(final int arg) {
        if (0 >= arg) {
            throw new  IllegalArgumentException("arg must be >0");
        }
        base.add(GregorianCalendar.DAY_OF_MONTH, -arg);
    }

    /**
     * 月增运算.
     * @param arg 需增的月数
     * @exception IllegalArgumentException
     */
    public void addMonths(final int arg) {
        if (0 >= arg) {
            throw new  IllegalArgumentException("arg must be >0");
        }
        base.add(GregorianCalendar.MONTH, arg);
    }

    /**
     * 月减运算.
     * @param arg
     *            需减的月数
     * @exception IllegalArgumentException
     */
    public void minusMonths(final int arg) {
        if (0 >= arg) {
            throw new  IllegalArgumentException("arg must be >0");
        }
        base.add(GregorianCalendar.MONTH, -arg);
    }

    /**
     * 年增运算.
     * @param arg 需增的年数
     * @exception IllegalArgumentException
     */
    public void addYears(final int arg) {
        if (0 >= arg) {
            throw new  IllegalArgumentException("arg must be >0");
        }
        base.add(GregorianCalendar.YEAR, arg);
    }

    /**
     * 年减运算.
     * @param arg
     *            需减的年数
     * @exception IllegalArgumentException
     */
    public void minusYears(final int arg) {
        if (0 >= arg) {
            throw new  IllegalArgumentException("arg must be >0");
        }
        base.add(GregorianCalendar.YEAR, -arg);
    }

    /**
     * 日偏移运算.
     * @param arg 需增的日数 正数向后偏移，负数向前偏移
     * @return Mfdate
     */
    public MfDate excursionDays(final int arg) {
        GregorianCalendar gcDate = new GregorianCalendar(
                base.get(GregorianCalendar.YEAR),
                base.get(GregorianCalendar.MONTH),
                base.get(GregorianCalendar.DAY_OF_MONTH) + arg);
        return new MfDate(gcDate);
    }

    /**
     * 月偏移运算.
     * @param arg 需增的月数 正数向后偏移，负数向前偏移
     * @return Mfdate
     */
    public MfDate excursionMonths(final int arg) {
        GregorianCalendar gcDate = new GregorianCalendar(
                base.get(GregorianCalendar.YEAR),
                base.get(GregorianCalendar.MONTH) + arg,
                base.get(GregorianCalendar.DAY_OF_MONTH));
        return new MfDate(gcDate);
    }

    /**
     * 年偏移运算.
     * @param arg 需增的年数 正数向后偏移，负数向前偏移
     * @return Mfdate
     */
    public MfDate excursionYears(final int arg) {
        GregorianCalendar gcDate = new GregorianCalendar(
                base.get(GregorianCalendar.YEAR) + arg,
                base.get(GregorianCalendar.MONTH),
                base.get(GregorianCalendar.DAY_OF_MONTH));
        return new MfDate(gcDate);
    }

    /**
     * @return 返回一个表示该 MfDate 值的 String 对象
     */
    public String toString() {
        SimpleDateFormat df =new SimpleDateFormat(this.defaultDatePattern);
        return df.format(this.base.getTime());
    }

    /**
     * @return 返回一个表示该 MfDate 值的 String 对象
     */
    public String toString(String datePattern) {
        SimpleDateFormat df = new SimpleDateFormat(datePattern);
        return df.format(this.base.getTime());
    }

    // ----------------------------------------------------------- 公有静态函数
    /**
     * 创建当前系统日期的 MfDate 对象.
     * @return 返回当前系统日期的 MfDate 对象
     */
    public static MfDate today() {
        return new MfDate();
    }

    //  ----------------------------------------------------------- 公有函数
    /**
     * 获得datePattern.
     * @return the String.
     */
    public String getDatePattern() {
        return datePattern;
    }
    /**
     * 设置datePattern.
     * @param pattern the String.
     */
    public void setDatePattern(final String pattern) {
        this.datePattern = pattern;
    }
    public static void main(String[] args) {
        MfDate date = new MfDate();
        System.out.println(date.toString("yyyy-MM-dd HH:mm:ss"));
        date = new MfDate("2005-06-8 14:14" , "yyyy-MM-dd HH:mm");
        System.out.print(date.toString("yyyy-MM-dd HH:mm"));
        
    }
}
