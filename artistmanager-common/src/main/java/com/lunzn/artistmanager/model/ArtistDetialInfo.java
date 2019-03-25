/*
 * 文 件 名:  ArtistDetialInfo.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  ZhongYi
 * 修改时间:  2018年10月24日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.lunzn.artistmanager.model;

import java.util.List;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年10月24日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ArtistDetialInfo
{
    /**
     * 艺人多语言信息
     */
    List<ArtistI18n> artistI18ns;
    
    /**
     * 艺人基本信息
     */
    ArtistInfo artistInfo;
    
    /**
     * @return 返回 artistI18ns
     */
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
    
}
