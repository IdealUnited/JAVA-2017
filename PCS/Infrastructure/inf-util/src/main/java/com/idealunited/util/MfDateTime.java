
package com.idealunited.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.util.Assert;


public final class MfDateTime extends MfDate {
    /**
     * 最大小时数.
     */
    public final static int maxHour = 23;
    /**
     * 最大分钟数.
     */
    public final static int maxMinute = 59;
    /**
     * 最大秒数.
     */
    public final static int maxSeconds = 59;

    /**
     * Constructor.
     * @param year Year
     * @param month Month
     * @param day Day
     * @param hour Hour
     * @param minute Minute
     * @param second Second
     */
    public MfDateTime(final int year, final int month, final int day,
            final int hour, final int minute, final int second) {
        super(year, month, day);
        Assert.notNull(hour);
        Assert.notNull(minute);
        Assert.notNull(second);
        Assert.isTrue(hour <= this.maxHour && hour >= 0, "0 <= hour <=23");
        Assert.isTrue(minute <= this.maxMinute && minute >= 0,
            "0<=minute <= 59");
        Assert.isTrue(second <= this.maxSeconds && second >= 0,
                "0<=seconds<=59");
        base.set(Calendar.HOUR_OF_DAY, hour);
        base.set(Calendar.MINUTE , minute);
        base.set(Calendar.SECOND, second);
    }
    
    /**
     * 用当前的时间构造一个MfDateTime对象.
     *
     */
    public MfDateTime() {
        GregorianCalendar cal = new GregorianCalendar();
//        initialize(cal);
//        base.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR));
//        base.set(Calendar.MINUTE , cal.get(Calendar.MINUTE));
//        base.set(Calendar.SECOND, cal.get(Calendar.SECOND));
        base = cal;
    }
    
    /**
     * 用Date对象构造一个MfDateTime对象.
     * @param date
     */
    public MfDateTime(final Date date) {
    	Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
         base = new GregorianCalendar(
        		calendar.get(Calendar.YEAR),
        		calendar.get(Calendar.MONTH),
        		calendar.get(Calendar.DAY_OF_MONTH),
        		calendar.get(Calendar.HOUR_OF_DAY),
        		calendar.get(Calendar.MINUTE),
        		calendar.get(Calendar.SECOND));
    }

    /**
     * 得到小时.
     * @return int
     */
    public int getHour() {
        return base.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 得到分钟.
     * @return int
     */
    public int getMinute() {
        return base.get(Calendar.MINUTE);
    }

    /**
     * 得到秒数.
     * @return int
     */
    public int getSecond() {
        return base.get(Calendar.SECOND);
    }

    /**
     * 返回MfTime.
     * @return MfTime
     */
    public MfTime getMfTime() {
        return new MfTime(getHour(), getMinute());
    }

    /**
     * Compare with another MfDateTime.
     * @param date MfDateTime
     * @return int
     */
    public int compareTo(final MfDateTime date) {
        return this.getTime().compareTo(date.getTime());
    }
    public static void main(String[] args) {
        MfDateTime t = new MfDateTime();
        System.out.print(t.toString("yyyy-MM-dd HH:mm:ss"));
    }
}
