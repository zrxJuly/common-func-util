package com.zrxjuly.dateutil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author Jenny_Zhang
 * @Description 日期格式化工具类.
 *
 */
public class DateFormatUtil {
    
    /**
     * Calculate the difference between years between two dates.<br/>
     * remark: startDate < endDate.
     * <p>计算两个日期之间相差的年数</p>
     * 备注：startDate < endDate.
     */
    public static Integer getDifferYearsBetweenDates(Date startDate, Date endDate) {
        int differ = 0;
        if (startDate == null || endDate == null) {
            return null;
        }
        
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(startDate);
        c2.setTime(endDate);
        
        differ = (c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR) < 0
                ? -(c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR)):c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR));
        
        return differ;
    }
    
    /**
     * Calculate the difference between months between two dates.<br/>
     * remark: startDate < endDate.
     * <p>计算两个日期之间相差的月数</p>
     * 备注：startDate < endDate.
     */
    public static Integer getDifferMonthsBetweenDates(Date startDate, Date endDate) {
        int differ = 0;
        if (startDate == null || endDate == null) {
            return null;
        }
        
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(startDate);
        c2.setTime(endDate);
        
        int monthDiff = (c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR))*12 + c2.get(Calendar.MONTH) 
                - c1.get(Calendar.MONTH);
        differ = (monthDiff > 0) ? monthDiff : -monthDiff;
        
        return differ;
    }
    
    /**
     * Calculate the difference between days between two dates.<br/>
     * remark: startDate < endDate.
     * <p>计算两个日期之间相差的天数</p>
     * 备注：startDate < endDate.
     */
    public static Integer getDifferDaysBetweenDates(Date startDate, Date endDate) {
        int differ = 0;
        if (startDate == null || endDate == null) {
            return null;
        }
        
        int dayDiff = (int) ((endDate.getTime()-startDate.getTime())/(24*60*60*1000));
        differ = dayDiff > 0 ? dayDiff : -dayDiff;
        
        return differ;
    }
    
    /**
     * date: 指定格式化的日期.<br/>
     * addYear: 加或减的年数<br/>
     * addMonth: 加或减的月数<br/>
     * addDay: 加或减的天数<br/>
     * dateFormat: 格式化日期格式，传null则不格式化。<br/>
     *             yyyy-MM、yyyy-MM-dd、yyyy-MM-dd HH:mm、yyyy-MM-dd HH:mm:ss
     */
    public static String getDateStrAddMonth(Date date, Integer addYear, Integer addMonth, Integer addDay, 
                                            String formatType) {
        
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if (addYear != null) {
            c.add(Calendar.YEAR, addYear);
        }
        
        if (addMonth != null) {
            c.add(Calendar.MONTH, addMonth);
        }
        
        if (addDay != null) {
            c.add(Calendar.DAY_OF_MONTH, addDay);
        }
        
        Date formatDate = c.getTime();
        String returnDateStr = "";
        if (formatType != null && !"".equals(formatType)) {
            returnDateStr = getDateFormat(formatDate, formatType);
        } 
        
        return returnDateStr;
    }
    
    /**
     * Get format date.
     */
    public static String getDateFormat(Date formatDate, String formatType) {
        if (formatDate == null) {
            formatDate = new Date();
        }
        DateFormat df = new SimpleDateFormat(formatType);
        return df.format(formatDate);
    }
    
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = sdf.parse("2019-01-01");
            endDate = sdf.parse("2019-03-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
//        int getDifferYearBetweenDate = getDifferYearBetweenDates(endDate, startDate);
//        int getDifferMonthBetweenDates = getDifferMonthBetweenDates(startDate, endDate);
//        int getDifferDaysBetweenDates = getDifferDaysBetweenDates(startDate, endDate);
        String getDateAddMonth = getDateStrAddMonth(startDate, null, null, null, "yyyy-MM-dd");
        String getDateAddMonth1 = getDateStrAddMonth(startDate, 1, null, null, "yyyy-MM-dd");
        String getDateAddMonth2 = getDateStrAddMonth(startDate, null, 1, null, "yyyy-MM-dd");
        String getDateAddMonth3 = getDateStrAddMonth(startDate, null, null, 32, "yyyy-MM-dd");
        System.err.println(getDateAddMonth);
        System.err.println(getDateAddMonth1);
        System.err.println(getDateAddMonth2);
        System.err.println(getDateAddMonth3);
        String a = getDateFormat(startDate, "yyyy-MM-dd HH:mm");
        System.err.println(a);
    }
}
