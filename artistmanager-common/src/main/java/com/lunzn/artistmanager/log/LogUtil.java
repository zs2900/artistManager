package com.lunzn.artistmanager.log;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 日志记录工具类
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年11月5日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class LogUtil
{
    /**
     * 标识要从哪些消息头中获取IP地址
     */
    private static final String[] IP_FLAG_ARRAY =
        {"HTTP_X_FORWARDED_FOR", "X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP"};
    
    /**
     * 接口日志记录器
     */
    private static Logger interfaceLogger = LoggerFactory.getLogger("logger.interface.service");
    
    /**
     * 打印日志
     * <功能详细描述>
     * @param logger 日志记录器
     * @param evt 日志体
     * @see [类、类#方法、类#成员]
     */
    public static void print(Logger logger, InterfaceLogEvt evt)
    {
        String logLevel = evt.getLogLevel();
        switch (logLevel)
        {
            case "WARN":
                logger.warn(evt.toString());
                break;
            default:
                logger.info(evt.toString());
                break;
        }
    }
    
    /**
     * 打印日志
     * <功能详细描述>
     * @param logger 日志记录器
     * @param evt 日志体
     * @see [类、类#方法、类#成员]
     */
    public static void print(Logger logger, InterfaceServiceLogEvt evt)
    {
        String logLevel = evt.getLogLevel();
        switch (logLevel)
        {
            case "WARN":
                logger.warn(evt.toString());
                break;
            default:
                logger.info(evt.toString());
                break;
        }
    }
    
    /**
     * 打印接口日志
     * <功能详细描述>
     * @param evt 日志体
     * @see [类、类#方法、类#成员]
     */
    public static void printInterfaceLog(InterfaceServiceLogEvt evt)
    {
        String logLevel = evt.getLogLevel();
        switch (logLevel)
        {
            case "WARN":
                interfaceLogger.warn(evt.toString());
                break;
            default:
                interfaceLogger.info(evt.toString());
                break;
        }
    }
    
    /** 
     * 获取请求的IP地址
     * <功能详细描述>
     * @param request 请求
     * @return ip
     * @see [类、类#方法、类#成员]
     */
    public static String getIP(HttpServletRequest request)
    {
        String ip = "";
        
        for (int i = 0, l = IP_FLAG_ARRAY.length; i < l; i++)
        {
            ip = request.getHeader(IP_FLAG_ARRAY[i]);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip))
            {
                return ip;
            }
        }
        
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
