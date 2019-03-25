/*
 * 文 件 名:  ArtistI18n.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  ZhongYi
 * 修改时间:  2018年10月19日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.lunzn.artistmanager.model;

import com.lunzn.artistmanager.validator.IValidatable;
import com.lunzn.artistmanager.validator.annotations.Param;

/**
 * 多语言实体类
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年10月19日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ArtistI18n implements IValidatable
{
    /**
     * 自增id
     */
    private Integer id;
    
    /**
     * 语言
     */
    private String lang;
    
    /**
     * 艺人编码
     */
    private String artistCode;
    
    /**
     * 艺人名称
     */
    private String name;
    
    /**
     * 艺术家简介
     */
    private String des;
    
    /**
     * 出生地，归属地
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
     * 用于区分艺术家变更时对多语言信息的操作状态类型
     */
    private String operationStatus;
    
    /**
     * @return 返回 id
     */
    public Integer getId()
    {
        return id;
    }
    
    /**
     * @param 对id进行赋值
     */
    public void setId(Integer id)
    {
        this.id = id;
    }
    
    /**
     * @return 返回 lang , regex = "^[a-z]{2}_[A-Z]{2}$"
     */
    @Param(canBlank = false, maxLength = 5)
    public String getLang()
    {
        return lang;
    }
    
    /**
     * @param 对lang进行赋值
     */
    public void setLang(String lang)
    {
        this.lang = lang;
    }
    
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
     * @return 返回 name
     */
    @Param(canBlank = false, maxLength = 128)
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
     * @return 返回 des
     */
    @Param(canBlank = true, maxLength = 4096)
    public String getDes()
    {
        return des;
    }
    
    /**
     * @param 对des进行赋值
     */
    public void setDes(String des)
    {
        this.des = des;
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
     * @return 返回 operationStatus
     */
    public String getOperationStatus()
    {
        return operationStatus;
    }
    
    /**
     * @param 对operationStatus进行赋值
     */
    public void setOperationStatus(String operationStatus)
    {
        this.operationStatus = operationStatus;
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
        return "ArtistI18n [id=" + id + ", lang=" + lang + ", artistCode=" + artistCode + ", name=" + name + ", des="
            + des + ", homeTown=" + homeTown + ", alias=" + alias + ", foreignName=" + foreignName
            + ", operationStatus=" + operationStatus + "]";
    }
    
}
