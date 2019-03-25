/*
 * 文 件 名:  ArtistDetialInfoEvt.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  ZhongYi
 * 修改时间:  2018年10月24日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.lunzn.artistmanager.controller.req;

import com.alibaba.fastjson.JSONObject;
import com.lunzn.artistmanager.message.annotations.Location;
import com.lunzn.artistmanager.message.reqest.BaseRequestData;
import com.lunzn.artistmanager.validator.annotations.Param;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年10月24日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Location(module = "", uri = "/artist/detialInfo")
public class ArtistDetialInfoEvt extends BaseRequestData
{
    /**
     * 艺人ID
     */
    private String artistCode;
    
    /**
     * @return 返回 artistCode
     */
    @Param(canBlank = false)
    public String getArtistCode()
    {
        return artistCode;
    }
    
    /**
     * @param 对artistCode进行赋值
     */
    public void setArtistCode(String artistCode)
    {
        this.artistCode = artistCode;
    }
    
    /** 
     * 打印艺人详细信息请求体
     * <功能详细描述>
     * @return 艺人详细信息请求体的json字符串
     * @see [类、类#方法、类#成员]
     */
    @Override
    public String toString()
    {
        return JSONObject.toJSONString(this);
    }
    
}
