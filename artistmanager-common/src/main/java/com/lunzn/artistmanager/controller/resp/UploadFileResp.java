package com.lunzn.artistmanager.controller.resp;

import com.alibaba.fastjson.JSONObject;
import com.lunzn.artistmanager.message.response.BaseResponse;

/**
 * 
 * 文件上传
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年10月29日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class UploadFileResp extends BaseResponse
{
    private String url;
    
    /**
     * @return 返回 url
     */
    public String getUrl()
    {
        return url;
    }
    
    /**
     * @param 对url进行赋值
     */
    public void setUrl(String url)
    {
        this.url = url;
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
