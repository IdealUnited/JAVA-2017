package com.idealunited.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.springframework.util.Assert;

/**
 * g
 * 时间类操作，不包括年月日
 */
public class MfTime {
    private final static int maxHour = 23;
    private final static int maxMinute = 59;
    /**
     * @see java.util.GregorianCalendar
     */
    private Calendar  base =  null;
    public MfTime() {
        this.init();
    }
    public MfTime(final Calendar arg) {
        Assert.notNull(arg, "arg must be not null");
        base = arg;
        this.init();
    }
    private void init() {
        base.set(Calendar.YEAR , 1900);
        base.set(Calendar.MONTH , 0);
        base.set(Calendar.DAY_OF_MONTH , 1);
    }
    public MfTime(final int hour , final int minute) {
        Assert.notNull(hour);
        Assert.notNull(minute);
        Assert.isTrue(hour <= this.maxHour && hour >= 0, "0 <= hour <=23");
        Assert.isTrue(minute <= this.maxMinute && minute >= 0,
            "0<=minute <= 59");
        base =  Calendar.getInstance();
        base.set(Calendar.HOUR_OF_DAY , hour);
        base.set(Calendar.MINUTE , minute);
       this.init();
    }
    public MfTime(final Timestamp timestamp) {
       // java.util.Calendar result = Calendar.getInstance();
        base = Calendar.getInstance();
        base.setTime(timestamp);
        this.init();
    }
    /*
     * @param args
     * eg:args = 15:30
     */
    public MfTime(String argsNoSecond) {
        base = Calendar.getInstance();
        try {
       base.setTime(new MfDate(new MfDate(1900,1,1)
            .toString()
            + " " + argsNoSecond,
            "yyyy-MM-dd HH:mm").getTime());
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }
    public MfTime(String timestampString , String patten) {
        try {
            MfDate mfDate = new MfDate(timestampString , patten);
            base = Calendar.getInstance();
            base.setTime(mfDate.getTime());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw e;
        }
    }
    public int compareTo(MfTime mfTime) {
       // base.get
        if (this.isAllZero() && mfTime.isAllZero()) {
            return 0;
        }
        if (this.isAllZero()) {
            return 1;
        }
        if (mfTime.isAllZero()) {
            return -1;
        }
        return this.getTime().compareTo(mfTime.getTime());
        }
    public boolean equals(Object mftime) {
        if (!(mftime instanceof MfTime)) {
            return false;
        }
        return this.compareTo((MfTime)mftime) == 0;
    }
    public String toString() {
    	int hour = base.get(Calendar.HOUR_OF_DAY);
    	int minute =  base.get(Calendar.MINUTE);
    	String sHour = hour < 10 ? "0" + hour : String.valueOf(hour);
    	String sMinute = minute < 10 ? "0" + minute : String.valueOf(minute);
       return sHour + ":" + sMinute;
    }
   
    public Boolean isAllZero() {
        return (this.getHour() == 0 && this.getMinute() == 0 && this
            .getSecond() == 0);
    }
    public int getHour() {
        return base.get(Calendar.HOUR_OF_DAY);
    }
    public int getMinute() {
        return base.get(Calendar.MINUTE);
    }
    public int getSecond() {
        return base.get(Calendar.SECOND);
    }
    public Date getTime() {
        return base.getTime();
    }
   
}
