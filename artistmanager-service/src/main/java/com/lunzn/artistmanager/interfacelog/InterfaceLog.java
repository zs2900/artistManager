/*
 * 文 件 名:  InterfaceLog.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  ZhongYi
 * 修改时间:  2018年11月13日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.lunzn.artistmanager.interfacelog;

/**
 * 日志对象
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年11月13日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class InterfaceLog
{
    /**
     * 请求url
     */
    private String requestURL;
    
    /**
     * 请求方式
     */
    private String requestMethod;
    
    /**
     * 客户端请求ip
     */
    private String ip;
    
    /**
     * 请求参数
     */
    private String requestData;
    
    /**
     * 响应参数
     */
    private String responseData;
    
    /**
     * 耗时
     */
    private long spendTime;
    
    /**
     * 请求接口名称
     */
    private String interfaceName;
    
    /**
     * 请求接口起始时间
     */
    private String startTime;
    
    /**
     * 接口结束时间
     */
    private String endTime;
    
    /**
     * @return 返回 requestURL
     */
    public String getRequestURL()
    {
        return requestURL;
    }
    
    /**
     * 
     * 对requestURL进行赋值
     * <功能详细描述>
     * @param requestURL 请求url
     * @see [类、类#方法、类#成员]
     */
    public void setRequestURL(String requestURL)
    {
        this.requestURL = requestURL;
    }
    
    /**
     * @return 返回 requestMethod
     */
    public String getRequestMethod()
    {
        return requestMethod;
    }
    
    /**
     * 
     * 对requestMethod进行赋值
     * <功能详细描述>
     * @param requestMethod 请求方式
     * @see [类、类#方法、类#成员]
     */
    public void setRequestMethod(String requestMethod)
    {
        this.requestMethod = requestMethod;
    }
    
    /**
     * @return 返回 ip
     */
    public String getIp()
    {
        return ip;
    }
    
    /**
     * 
     * 对ip进行赋值
     * <功能详细描述>
     * @param ip 客户端ip地址
     * @see [类、类#方法、类#成员]
     */
    public void setIp(String ip)
    {
        this.ip = ip;
    }
    
    /**
     * @return 返回 requestData
     */
    public String getRequestData()
    {
        return requestData;
    }
    
    /**
     * 
     * 对requestData进行赋值
     * <功能详细描述>
     * @param requestData 请求参数
     * @see [类、类#方法、类#成员]
     */
    public void setRequestData(String requestData)
    {
        this.requestData = requestData;
    }
    
    /**
     * @return 返回 responseData
     */
    public String getResponseData()
    {
        return responseData;
    }
    
    /**
     * 
     * 对responseData进行赋值
     * <功能详细描述>
     * @param responseData 响应参数
     * @see [类、类#方法、类#成员]
     */
    public void setResponseData(String responseData)
    {
        this.responseData = responseData;
    }
    
    /**
     * @return 返回 spendTime
     */
    public long getSpendTime()
    {
        return spendTime;
    }
    
    /**
     * 
     * 对spendTime进行赋值
     * <功能详细描述>
     * @param spendTime 耗时
     * @see [类、类#方法、类#成员]
     */
    public void setSpendTime(long spendTime)
    {
        this.spendTime = spendTime;
    }
    
    /**
     * @return 返回 interfaceName
     */
    public String getInterfaceName()
    {
        return interfaceName;
    }
    
    /**
     * 
     * 对interfaceName进行赋值
     * <功能详细描述>
     * @param interfaceName 请求接口名称
     * @see [类、类#方法、类#成员]
     */
    public void setInterfaceName(String interfaceName)
    {
        this.interfaceName = interfaceName;
    }
    
    /**
     * @return 返回 startTime
     */
    public String getStartTime()
    {
        return startTime;
    }
    
    /**
     * 
     * 对startTime进行赋值
     * <功能详细描述>
     * @param startTime 请求起始时间
     * @see [类、类#方法、类#成员]
     */
    public void setStartTime(String startTime)
    {
        this.startTime = startTime;
    }
    
    /**
     * @return 返回 endTime
     */
    public String getEndTime()
    {
        return endTime;
    }
    
    /**
     * 
     * 对endTime进行赋值
     * <功能详细描述>
     * @param endTime 接口结束时间
     * @see [类、类#方法、类#成员]
     */
    public void setEndTime(String endTime)
    {
        this.endTime = endTime;
    }
    
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public String toString()
    {
        return "StartTime ";
    }
    
}
