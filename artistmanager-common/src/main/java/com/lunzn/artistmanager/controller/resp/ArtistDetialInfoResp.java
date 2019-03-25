/*
 * 文 件 名:  ArtistDetialInfoResp.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  ZhongYi
 * 修改时间:  2018年10月24日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.lunzn.artistmanager.controller.resp;

import com.lunzn.artistmanager.message.response.BaseResponse;
import com.lunzn.artistmanager.model.ArtistDetialInfo;

/**
 * 艺人详细信息响应实体
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年10月24日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ArtistDetialInfoResp extends BaseResponse
{
    /**
     * 艺人详细信息实体
     */
    ArtistDetialInfo artistDetialInfo;
    
    /**
     * @return 返回 artistDetialInfo
     */
    public ArtistDetialInfo getArtistDetialInfo()
    {
        return artistDetialInfo;
    }
    
    /**
     * @param 对artistDetialInfo进行赋值
     */
    public void setArtistDetialInfo(ArtistDetialInfo artistDetialInfo)
    {
        this.artistDetialInfo = artistDetialInfo;
    }
    
}
