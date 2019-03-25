package com.lunzn.artistmanager.message.response;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.lunzn.artistmanager.enums.ResultCode;
import com.lunzn.artistmanager.system.exception.InnerException;

/**
 * 响应结果返回基类
 * <功能详细描述>
 * 
 * @author  longguofei
 * @version  [版本号, 2017年10月19日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class BaseResponse implements IResponse
{
    //@JSONField(name = "ret")
    private int retCode;
    
    //@JSONField(name = "ret_msg")
    private String retMsg;
    
    /**
     * 异常<默认构造函数>
      * @param e 异常
     */
    public BaseResponse(InnerException e)
    {
        this.retCode = e.getErrorCode();
        this.retMsg = e.getErrorDesc();
    }
    
    /**
     * <默认构造函数>
      *
     */
    public BaseResponse()
    {
        this.retCode = ResultCode.SUCCESS.getResultCode();
        this.retMsg = ResultCode.SUCCESS.getResultMsg();
    }
    
    /**
     * <默认构造函数>
      * @param retCode 返回消息错误码
      * @param retMsg 错误消息
     */
    public BaseResponse(int retCode, String retMsg)
    {
        this.retCode = retCode;
        this.retMsg = retMsg;
    }
    
    /**
     * <默认构造函数>
      * @param resultCode 错误消息枚举
     */
    public BaseResponse(ResultCode resultCode)
    {
        if (resultCode != null)
        {
            this.retCode = resultCode.getResultCode();
            this.retMsg = resultCode.getResultMsg();
        }
    }
    
    @Override
    public int getRetCode()
    {
        return this.retCode;
    }
    
    @Override
    public void setRetCode(int retCode)
    {
        this.retCode = retCode;
    }
    
    @Override
    public String getRetMsg()
    {
        return this.retMsg;
    }
    
    @Override
    public void setRetMsg(String retMsg)
    {
        this.retMsg = retMsg;
    }
    
    @JSONField(serialize = false)
    public boolean isSuccess()
    {
        return ResultCode.SUCCESS.getResultCode() == this.retCode;
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
        return JSONObject.toJSONString(this);
    }
    
}
