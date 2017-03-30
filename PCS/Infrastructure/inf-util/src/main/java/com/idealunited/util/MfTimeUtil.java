package com.idealunited.util;

import java.sql.Timestamp;
import java.util.Calendar;

import org.springframework.util.Assert;

public class MfTimeUtil {

    public static int compare(Calendar max , Calendar min) {
        return new MfTime(max).compareTo(new MfTime(min));
    }
    public static int compare(final MfTime maxTime, final MfTime minTime) {
        
        return maxTime.compareTo(minTime);
    }
    /**
     * 
     * @param maxNoSe String eg hh:mm
     * @param minNose String eg hh:mm
     * @return int
     */
    public static int compare(final String maxNoSe, final String minNose) {
        MfTime time = new MfTime(maxNoSe);
        MfTime minTime = new MfTime(minNose);
        return time.compareTo(minTime);
    }
    public static Boolean isGreateThan(
        final Calendar max,
        final Calendar min) {
    Assert.notNull(max);
    Assert.notNull(min);
    return MfTimeUtil.compare(max , min) > 0;
}
    /**
     * 判断给定的mftime是否在最大和最小之间，包括想得.
     * @param min MfTime
     * @param mid MfTime
     * @param max MfTime
     * @return Boolean
     */
    public static Boolean isBetweenOrEquals(final MfTime min ,
                                      final MfTime mid,
                                      final MfTime max) {
        if (max == null || max.isAllZero()) {
            return MfTimeUtil.compare(max , min)  >= 0;
        }
        return MfTimeUtil.compare(max, mid) >= 0 && MfTimeUtil.compare(mid, min) >= 0;
    }
    
    public static Boolean isGreateThan(final Timestamp max,
            final Timestamp min) {
        Assert.notNull(max);
        Assert.notNull(min);
        Calendar minCal = Calendar.getInstance();
        Calendar maxCal = Calendar.getInstance();
        maxCal.setTime(max);
        minCal.setTime(min);
        return MfTimeUtil.compare(maxCal , minCal) > 0;
    }
    //
    /*
     * @判断是否mid在from和to中，包括边界相等,mid and from not null
     * @param  mid Timestamp
     * @param  from Timestamp
     * @param  to Timestamp
     * @return Boolean
     */
    public static Boolean isBetweenOrEquals(
            final Timestamp mid,
            final Timestamp from,
            final Timestamp to) {
        Assert.notNull(mid);
        Assert.notNull(from);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mid);
        Calendar min = Calendar.getInstance();
        min.setTime(from);
        if (to == null) {
            return MfTimeUtil.isGreateThanEquals(calendar , min);
        }
        Calendar max = Calendar.getInstance();
        max.setTime(to);
       return MfTimeUtil.isBetweenOrEquals(calendar , min , max);
    }
    public static Boolean isBetweenOrEquals(
            final Calendar mid,
            final Calendar min,
            final Calendar max) {
        Assert.notNull(mid);
        Assert.notNull(min);
        if (max == null || new MfTime(max).isAllZero()) {
            return MfTimeUtil.isGreateThanEquals(mid, min);
        }
        return MfTimeUtil.compare(max, mid) >= 0 && MfTimeUtil.compare(mid, min) >= 0;
    }
    public static Boolean isGreateThanEquals(
            final Calendar max,
            final Calendar min) {
        Assert.notNull(max);
        Assert.notNull(min);
        return MfTimeUtil.compare(max , min)  >= 0;
    }
    public static Boolean isBetween(Timestamp mid , Timestamp from ,Timestamp to) {
        Assert.notNull(mid);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mid);
        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();
        max.setTime(to);
        min.setTime(from);
        return MfTimeUtil.isBetween(calendar , min , max);
    }
    public static Boolean isBetween(final Calendar mid,
        final Calendar min,
        final Calendar max) {
        Assert.notNull(mid);
        Assert.notNull(min);
        if (max == null || new MfTime(max).isAllZero()) {
            return MfTimeUtil.isGreateThan(mid, min);
        }
        return MfTimeUtil.compare(max, mid) > 0 && MfTimeUtil.compare(mid, min) > 0;
    }
    public static void main(String args) {
       
    }
}
