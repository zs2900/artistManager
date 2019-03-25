/*
 * 文 件 名:  QueryArtistAllEvt.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  Administrator
 * 修改时间:  2018年10月16日
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
 * @author  Administrator
 * @version  [版本号, 2018年10月16日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Location(module = "service", uri = "/artist/list")
public class QueryArtistListEvt extends BaseRequestData
{
    /**
     * 艺人ID
     */
    private String artistCode;
    
    /**
     * 艺人名称
     */
    private String name;
    
    /**
     * 出生地
     */
    private String homeTown;
    
    /**
     * 别名
     */
    private String alias;
    
    /**
     * 外文名
     */
    private String foreignName;
    
    /**
     * 国籍
     */
    private String nationality;
    
    /**
     * 职业
     */
    private String artistType;
    
    /**
     * @return 返回 artistCode
     */
    @Param(canBlank = true, maxLength = 64)
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
     * @return 返回 name
     */
    @Param(canBlank = true, maxLength = 3)
    public String getName()
    {
        return name;
    }
    
    /**
     * @param 对name进行赋值
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * @return 返回 homeTown
     */
    @Param(canBlank = true, maxLength = 128)
    public String getHomeTown()
    {
        return homeTown;
    }
    
    /**
     * @param 对homeTown进行赋值
     */
    public void setHomeTown(String homeTown)
    {
        this.homeTown = homeTown;
    }
    
    /**
     * @return 返回 alias
     */
    @Param(canBlank = true, maxLength = 128)
    public String getAlias()
    {
        return alias;
    }
    
    /**
     * @param 对alias进行赋值
     */
    public void setAlias(String alias)
    {
        this.alias = alias;
    }
    
    /**
     * @return 返回 foreignName
     */
    @Param(canBlank = true, maxLength = 128)
    public String getForeignName()
    {
        return foreignName;
    }
    
    /**
     * @param 对foreignName进行赋值
     */
    public void setForeignName(String foreignName)
    {
        this.foreignName = foreignName;
    }
    
    /**
     * @return 返回 nationality
     */
    @Param(canBlank = true, maxLength = 256)
    public String getNationality()
    {
        return nationality;
    }
    
    /**
     * @param 对nationality进行赋值
     */
    public void setNationality(String nationality)
    {
        this.nationality = nationality;
    }
    
    /**
     * @return 返回 artistType
     */
    @Param(canBlank = true, maxLength = 64)
    public String getArtistType()
    {
        return artistType;
    }
    
    /**
     * @param 对artistType进行赋值
     */
    public void setArtistType(String artistType)
    {
        this.artistType = artistType;
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
