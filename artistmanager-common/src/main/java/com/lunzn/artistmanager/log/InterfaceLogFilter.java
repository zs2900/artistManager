package com.lunzn.artistmanager.log;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * 
 * 接口日志过滤器
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年11月5日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class InterfaceLogFilter extends Filter<ILoggingEvent>
{
    /**
     * 日志记录器名称
     */
    private String loggerName;
    
    @Override
    public FilterReply decide(ILoggingEvent event)
    {
        if (event.getLoggerName().equals(loggerName))
        {
            // 允许输入
            return FilterReply.ACCEPT;
        }
        else
        {
            // 不允许输出
            return FilterReply.DENY;
        }
    }
    
    public String getLoggerName()
    {
        return loggerName;
    }
    
    public void setLoggerName(String loggerName)
    {
        this.loggerName = loggerName;
    }
    
}
