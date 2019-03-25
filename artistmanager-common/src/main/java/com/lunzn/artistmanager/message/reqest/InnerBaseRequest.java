package com.lunzn.artistmanager.message.reqest;

import com.alibaba.fastjson.JSONObject;
import com.lunzn.artistmanager.validator.annotations.Param;

/**
 * 请求对象抽象类
 * <规定了请求体结构>
 * 
 * @author  longguofei
 * @version  [版本号, 2017年10月19日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class InnerBaseRequest implements IMsgRequest
{
    // 服务id
    private String serverId;
    
    //请求时间
    private String requestTime;
    
    // 数据
    private JSONObject data;
    
    // 版本
    private String version;
    
    // 签名
    private String digest;
    
    //用户id
    private String userId;
    
    /**
     * <默认构造函数>
      *
     */
    public InnerBaseRequest()
    {
        // this.startTime = System.currentTimeMillis();
    }
    
    public String getServerId()
    {
        return this.serverId;
    }
    
    public void setServerId(String serverId)
    {
        this.serverId = serverId;
    }
    
    public String getRequestTime()
    {
        return this.requestTime;
    }
    
    public void setRequestTime(String requestTime)
    {
        this.requestTime = requestTime;
    }
    
    public JSONObject getData()
    {
        return this.data;
    }
    
    public void setData(JSONObject data)
    {
        this.data = data;
    }
    
    @Param(canBlank = false)
    public String getVersion()
    {
        return this.version;
    }
    
    public void setVersion(String version)
    {
        this.version = version;
    }
    
    public String getDigest()
    {
        return digest;
    }
    
    public void setDigest(String digest)
    {
        this.digest = digest;
    }
    
    public String getUserId()
    {
        return userId;
    }
    
    public void setUserId(String userId)
    {
        this.userId = userId;
    }
    
    @Override
    public String toString()
    {
        return JSONObject.toJSONString(this);
    }
}
