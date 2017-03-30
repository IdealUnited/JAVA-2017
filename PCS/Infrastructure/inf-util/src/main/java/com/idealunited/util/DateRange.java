package com.idealunited.util;

import java.util.Arrays;
import java.util.Date;

import org.springframework.util.Assert;


public final class DateRange implements Comparable {
    /**
     * 时间段的开始日期.
     */
    private final MfDate start;

    /**
     *  时间段的结束日期.
     */
    private final MfDate end;
    
    private final MfDate specMfdate = new MfDate(1900,1,1);

    /**
     * @param startP
     *            MfDate
     * @param endP
     *            MfDate
     */
    public DateRange(final MfDate startP, final MfDate endP) {
        Assert.notNull(startP, "startP must be not null");
        Assert.notNull(endP, "endP must be not null");
        this.start = startP;
        this.end = endP;
    }

    /**
     * @param startP
     *            Date
     * @param endP
     *            Date
     * @see DateRange(MfDate start, MfDate end)
     */
    public DateRange(final Date startP, final Date endP) {
        this(new MfDate(startP), new MfDate(endP));
    }

    /**
     * 得到Range 的end 日期.
     * @return end;
     */
    public MfDate getEnd() {
        return end;
    }

    /**
     * @ 得到Range 的start 日期
     * @return start
     */
    public MfDate getStart() {
        return start;
    }

    /**
     * 得到一个从当前日期减1天到给定参数MfDate end的DateRange.
     * @param endP
     *            MfDate
     * @return DateRange
     * @since 1.0
     */
    public static DateRange upTo(final MfDate endP) {
        Assert.notNull(endP, "endP must be not null");
        return new DateRange(MfDate.PAST, endP);
    }

    /**
     * 得到一个从给定参数MfDate startP到当前日期加1天的DateRange.
     * @param startP
     *            MfDate
     * @return DateRange
     * @since 1.0
     */
    public static DateRange startingOn(final MfDate startP) {
        Assert.notNull(startP, "startP must be not null");
        return new DateRange(startP, MfDate.FUTURE);
    }

    /**
     * 判断是否两个DateRange是否相等.
     * @param arg
     *            DateRange
     * @return boolean
     * @since 1.0
     */
    public boolean equals(final DateRange arg) {
        Assert.notNull(arg, "arg must be not null");
        DateRange other = (DateRange) arg;
        return start.equals(other.start) && end.equals(other.end);
    }

    /**
     * @return int
     */
    public int hashCode() {
        return start.hashCode();
    }

    /**
     * 查看MfDate的日期是否给定的日期在Range的范围内.
     * @param arg
     *            MfDate
     * @return boolean 如果arg在this的范围内返回ture
     * @since 1.0
     */
    public boolean includes(final MfDate arg) {
        Assert.notNull(arg, "arg must be not null");
        return arg.compareTo(start) > 0 && arg.compareTo(end) < 0;
    }

    /**
     * 查看DateRange 的日期是否给定的日期在Range的范围内.
     * @param arg
     *            DateRange
     * @return boolean
     * @since 1.0
     */
    public boolean includes(final DateRange arg) {
        Assert.notNull(arg, "arg must be not null");
        return this.includes(arg.start) && this.includes(arg.end);
    }
    /**
     * 包括相等或者谋一边界相等.
     * @param dateRange
     * @return boolean
     */
    public boolean includesEquals(final DateRange dateRange) {
        return this.includes(dateRange)
        || this.equals(dateRange)
        || (this.includes(dateRange.getStart()) && this.getEnd()
            .equals(dateRange.getEnd()))
        || (this.includes(dateRange.getEnd()) && this.getStart()
            .equals(dateRange.getStart()));
    }
    /**
     * 包括相等或者谋一边界相等.
     * @param date MfDate
     * @return boolean
     */
    public boolean includesEquals(final MfDate date) {
        return this.includes(date) || this.getStart().equals(date)
        || this.getEnd().equals(date);
    }
    /**
     * 对于endDate如果是1900，1，1作为最大时间的的判断.
     * @param date MfDate
     * @return 如果包含或者边界＝return true
     */
    public boolean includesEqualsSpecDate(final MfDate date) {
        assert null != date;
       
        if (date.equals(new MfDate(1900, 1, 1))
            && this.getEnd().equals(new MfDate(1900, 1, 1))) {
            return true;
        } else if (date.equals(new MfDate(1900, 1, 1))){
            return false;
        } else {
            if (this.getEnd().equals(new MfDate(1900,1,1))) {
                return this.getStart().compareTo(date) <= 0;
            }
            return this.includesEquals(date);
        }
    }
    /**
     * 对于endDate如果是1900，1，1作为最大时间的的判断.
     * @param date MfDate
     * @return 包括enddate相等的判断.
     */
    public boolean includesEqualsSpecDateNoEndDate(final MfDate date) {
        assert null != date;
      
        if (date.equals(new MfDate(1900,1,1))) {
            return false;
        }
        if (this.getEnd().equals(new MfDate(1900,1,1))) {
            return this.getStart().compareTo(date) <= 0;
        } else {
            return this.includes(date) || this.getStart().equals(date);
        }
    }
    /**
     * 判断两个DateRange是否有彼此的重叠,但如果this的end和arg的begin相等不是重叠.
     * eg:(2006-1-8,2006-1-20)和(2006-1-20,2006-1-25)没有重叠,
     * 但如果是和(2006-1-10,2006-1-30)
     * 是有重叠的,返回true.否则返回false,如果arg的enddate是1900，1，1并且
     * @param arg DateRange
     * @return boolean
     */
    public boolean coOverlapsSpecDateNoEnd(final DateRange arg) {
        assert null != arg;
        assert !this.getEnd().equals(specMfdate) && !this.isEmpty();
        assert !arg.getEnd().equals(specMfdate) && !arg.isEmpty();
        //都是特殊日期或者参数是特殊日期并且参数的开始日期大于this.start
        if (arg.getEnd().equals(specMfdate) && this.getEnd().equals(specMfdate)) {
            return true;
        }
        if (arg.end.equals(specMfdate)) {
            return this.end.compareTo(arg.start) > 0;
        }
        if (this.end.equals(specMfdate)) {
            return this.start.compareTo(arg.end) < 0;
        }
        return this.coOverlapsNoEnd(arg);
    }
    /**
     * 判断两个DateRange是否有彼此的重叠,但如果this的end和arg的begin相等不是重叠.
     * eg:(2006-1-8,2006-1-20)和(2006-1-20,2006-1-25)没有重叠,
     * 但如果是和(2006-1-10,2006-1-30)
     * 是有重叠的,返回true.否则返回false.
     * @param arg DateRange
     * @return boolean
     */
    public boolean coOverlapsNoEnd(final DateRange arg) {
        if((this.isEmpty() && arg.isEmpty())
            || (this.isEmpty() && !arg.isEmpty())
            || (!this.isEmpty() && arg.isEmpty())
            || (!this.isEmpty() && !arg.isEmpty() && (this.includes(arg.getStart()) 
            ||  this.getStart().equals(arg.getStart())            || (this.includes(arg.getEnd()))
            || arg.includes(this.getStart()) || arg.getStart().equals(this.getStart()) || arg
            .includes(this.getEnd()))
            
            )
            
        ) {
               return true;
            }
        return false;
    }
    /**
     *  判断给定参数是否和this有重叠的,有ture.
     * @param arg
     *            DateRange
     * @return boolean
     * @since 1.0
     */
    public boolean overlaps(final DateRange arg) {
        Assert.notNull(arg, "arg must be not null");
        return arg.includes(start) || arg.includes(end) || this.includes(arg);
    }

    /**
     * 得到给定参数和this之间的Range,如果有重叠,则返回一个DateRange.EMPTY.
     * @param arg
     *            DateRange
     * @return DateRange
     * @since 1.0
     */
    public DateRange gap(final DateRange arg) {
        Assert.notNull(arg, "arg must be not null");
        if (this.overlaps(arg)) {
            return DateRange.EMPTY;
        }
        DateRange lower, higher;
        if (this.compareTo(arg) < 0) {
            lower = this;
            higher = arg;
        } else {
            lower = arg;
            higher = this;
        }
       return new DateRange(lower.end.excursionDays(1), higher.start
            .excursionDays(-1));
    }

    /**
     *  比较this和DateRange arg.
     * @param arg
     *            DateRange
     * @return int 如果开始this.start > arg.start return 1;
     * < ,return -1;=,return 0;
     * @since 1.0
     */
    public int compareTo(final Object arg) {
        Assert.notNull(arg, "arg must be not null");
        if (!(arg instanceof DateRange)) {
            throw new IllegalArgumentException("arg is not DateRange");
        }
        DateRange other = (DateRange) arg;
        if (!start.equals(other.start)) {
            return start.compareTo(other.start);
        }
        return end.compareTo(other.end);

    }

    /**
     *  判断参数DateRange arg是否和this是相邻.
     * @param arg
     *            DateRange
     * @return boolean 相邻,true;
     * @since 1.0
     */
    public boolean abuts(final DateRange arg) {
        Assert.notNull(arg, "arg must be not null");
        return !this.overlaps(arg) && this.gap(arg).isEmpty();
    }

    /**
     * 判断给定参数DateRange[] args是否是连续的.
     * @param args
     *            DateRange[] args
     * @return boolean 如果是 true
     * @since 1.0
     */
    public static boolean isContiguous(final DateRange[] args) {
        Assert.notNull(args, "args must be not null");
        Arrays.sort(args);
        for (int i = 0; i < args.length - 1; i++) {
            if (!args[i].abuts(args[i + 1])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断DateRange类型的数组组成的DateRange与this相比较.
     * @param args
     *            DateRange[]
     * @return boolean 如果args不是连续的,返回false;如果连续,将args组合成
     *         一个DateRange类型,然后调用equals进行比较
     * @see equals
     * @see combination
     * @since 1.0
     */
    public boolean partitionedBy(final DateRange[] args) {
        Assert.notNull(args, "args must be not null");
        if (!isContiguous(args)) {
            return false;
        }
        return this.equals(DateRange.combination(args));
    }

    /**
     * 将几个连续的DateRange构造成一个新的DateRange.
     * @param args
     *            DateRange[]
     * @return DateRange
     * @throws IllegalArgumentException
     * @since 1.0
     */
    public static DateRange combination(final DateRange[] args) {
        Assert.notNull(args, "args must be not null");
        Arrays.sort(args);
        if (!isContiguous(args)) {
            throw new IllegalArgumentException("Unable to combine date ranges");
        }
        return new DateRange(args[0].start, args[args.length - 1].end);
    }

    /**
     * 判断是否为空.
     * @return boolean 如果end小余start就认为是空
     * @since 1.0
     */
    public boolean isEmpty() {
        return start.after(end);
    }

    /**
     * function 构造一个空的DateRange.
     * @since 1.0
     */
    public static DateRange EMPTY = new DateRange(new MfDate(2000, 4, 1),
        new MfDate(2000, 1, 1));

}
