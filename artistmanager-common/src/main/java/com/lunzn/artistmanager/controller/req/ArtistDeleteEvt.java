/*
 * 文 件 名:  ArtistDeleteEvt.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  ZhongYi
 * 修改时间:  2018年10月31日
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
 * 删除艺人请求体
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年10月31日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Location(module = "", uri = "/artist/delete")
public class ArtistDeleteEvt extends BaseRequestData
{
    private String artistCode;
    
    /**
     * @return 返回 artistCode
     */
    @Param(canBlank = false, maxLength = 64)
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
     * 打印请求体
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
