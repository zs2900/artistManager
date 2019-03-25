/*
 * 文 件 名:  ArtistAddEvt.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  ZhongYi
 * 修改时间:  2018年10月30日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.lunzn.artistmanager.controller.req;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.lunzn.artistmanager.message.annotations.Location;
import com.lunzn.artistmanager.message.reqest.BaseRequestData;
import com.lunzn.artistmanager.model.ArtistI18n;
import com.lunzn.artistmanager.model.ArtistInfo;
import com.lunzn.artistmanager.validator.annotations.ArrayParam;
import com.lunzn.artistmanager.validator.annotations.Param;

/**
 * 新增艺人请求体
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年10月30日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Location(module = "", uri = "/artist/add")
public class ArtistAddEvt extends BaseRequestData
{
    /**
     * 多语言列表
     */
    private List<ArtistI18n> artistI18ns;
    
    /**
     * 艺人基本信息
     */
    private ArtistInfo artistInfo;
    
    /**
     * @return 返回 artistI18ns
     */
    @ArrayParam(canItemBlank = false, canBlank = false, maxLength = 2)
    public List<ArtistI18n> getArtistI18ns()
    {
        return artistI18ns;
    }
    
    /**
     * @param 对artistI18ns进行赋值
     */
    public void setArtistI18ns(List<ArtistI18n> artistI18ns)
    {
        this.artistI18ns = artistI18ns;
    }
    
    /**
     * @return 返回 artistInfo
     */
    @Param(canBlank=false)
    public ArtistInfo getArtistInfo()
    {
        return artistInfo;
    }
    
    /**
     * @param 对artistInfo进行赋值
     */
    public void setArtistInfo(ArtistInfo artistInfo)
    {
        this.artistInfo = artistInfo;
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
