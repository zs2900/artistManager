package com.lunzn.artistmanager.message.reqest;

import com.alibaba.fastjson.JSONObject;
import com.lunzn.artistmanager.constants.NumberKeys;
import com.lunzn.artistmanager.constants.ParamRegex;
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
public class InnerServiceBaseRequest implements IMsgRequest
{
    
    // 数据
    private String param;
    
    // 版本
    private String version;
    
    // 客户端id
    private String client_id;
    
    // 渠道类型
    private String client_type;
    
    // 请求发送时间
    private String times;
    
    // 签名
    private String sign;
    
    /**
     * <默认构造函数>
      *
     */
    public InnerServiceBaseRequest()
    {
        // this.startTime = System.currentTimeMillis();
    }
    
    @Param(canBlank = false, maxLength = NumberKeys.NUM_64, regex = ParamRegex.VARCHAR_REGEX)
    public String getVersion()
    {
        return this.version;
    }
    
    public void setVersion(String version)
    {
        this.version = version;
    }
    
    @Param(canBlank = false)
    public String getParam()
    {
        return param;
    }
    
    public void setParam(String param)
    {
        this.param = param;
    }
    
    @Param(canBlank = false, maxLength = NumberKeys.NUM_64, regex = ParamRegex.VARCHAR_REGEX)
    public String getClient_id()
    {
        return client_id;
    }
    
    public void setClient_id(String client_id)
    {
        this.client_id = client_id;
    }
    
    @Param(canBlank = false, maxLength = NumberKeys.NUM_64, regex = ParamRegex.VARCHAR_REGEX)
    public String getClient_type()
    {
        return client_type;
    }
    
    public void setClient_type(String client_type)
    {
        this.client_type = client_type;
    }
    
    public String getTimes()
    {
        return times;
    }
    
    public void setTimes(String times)
    {
        this.times = times;
    }
    
    public String getSign()
    {
        return sign;
    }
    
    public void setSign(String sign)
    {
        this.sign = sign;
    }
    
    @Override
    public String toString()
    {
        return JSONObject.toJSONString(this);
    }
}
