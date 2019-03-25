package com.lunzn.artistmanager.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.mysql.jdbc.StringUtils;

/**
 * 时间工具类
 * <功能详细描述>
 * 
 * @author  tianqiuming
 * @version  [版本号, 2017年9月22日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class DateUtils
{
    /**
     * 获取当前月份
     * yyyy-MM格式
     * <一句话功能简述>
     * <功能详细描述>
     * @return 年-月
     * @see [类、类#方法、类#成员]
     */
    public static String getCurrMonth()
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        return format.format(new Date());
    }
    
    /**
     * 获取上个月份
     * yyyy-MM格式
     * <一句话功能简述>
     * <功能详细描述>
     * @return 年-月
     * @see [类、类#方法、类#成员]
     */
    public static String getLastMonth()
    {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        return format.format(cal.getTime());
    }
    
    /**
     * 按照正则获取当前系统时间
     * regex有值则按照regex来，没有值默认为yyyy-MM-dd hh-mm-ss
     * <一句话功能简述>
     * <功能详细描述>
     * @param regex 时间格式
     * @return 年-月-日 时:分:秒
     * @see [类、类#方法、类#成员]
     */
    public static String getRegexDate(String regex)
    {
        if (StringUtils.isNullOrEmpty(regex))
        {
            regex = "yyyy-MM-dd HH:mm:ss";
        }
        
        SimpleDateFormat format = new SimpleDateFormat(regex);
        return format.format(System.currentTimeMillis());
    }
    
    /**
     * 格式化字符时间
     * <一句话功能简述>
     * <功能详细描述>
     * @param regex 时间表达式
     * @param times 字符串时间
     * @return 格式化后的时间
     * @throws ParseException 时间转换异常
     * @see [类、类#方法、类#成员]
     */
    public static String getRegexDate(String regex, String times)
        throws ParseException
    {
        if (StringUtils.isNullOrEmpty(regex))
        {
            regex = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat formater = new SimpleDateFormat(regex);
        Date date = formater.parse(times);
        return formater.format(date);
    }
    
    /**
     * 获取当前天 DAY_OF_MONTH
     * <一句话功能简述>
     * <功能详细描述>
     * @return 当前天
     * @see [类、类#方法、类#成员]
     */
    public static int getCurrDay()
    {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.DAY_OF_MONTH);
    }
}
