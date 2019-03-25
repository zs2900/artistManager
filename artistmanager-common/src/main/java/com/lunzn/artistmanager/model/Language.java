package com.lunzn.artistmanager.model;

import java.io.Serializable;
import java.util.Date;

public class Language implements Serializable
{
    /**
     * 自增主键
     */
    private int id;
    
    /**
     * 语言Id
     */
    private String languageId;
    
    /**
     *语言名称 以本土语言描述
     */
    private String nativeName;
    
    /**
     * 语言名称 以英语描述
     */
    private String englishName;
    
    /**
     * 语言适用区域
     */
    private String area;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 最后修改时间
     */
    private Date updateTime;
    
    private static final long serialVersionUID = 1L;
    
    /**
     * @return 返回 id
     */
    public int getId()
    {
        return id;
    }
    
    /**
     * @param 对id进行赋值
     */
    public void setId(int id)
    {
        this.id = id;
    }
    
    /**
     * @return 返回 languageId
     */
    public String getLanguageId()
    {
        return languageId;
    }
    
    /**
     * @param 对languageId进行赋值
     */
    public void setLanguageId(String languageId)
    {
        this.languageId = languageId;
    }
    
    /**
     * @return 返回 nativeName
     */
    public String getNativeName()
    {
        return nativeName;
    }
    
    /**
     * @param 对nativeName进行赋值
     */
    public void setNativeName(String nativeName)
    {
        this.nativeName = nativeName;
    }
    
    /**
     * @return 返回 englishName
     */
    public String getEnglishName()
    {
        return englishName;
    }
    
    /**
     * @param 对englishName进行赋值
     */
    public void setEnglishName(String englishName)
    {
        this.englishName = englishName;
    }
    
    /**
     * @return 返回 area
     */
    public String getArea()
    {
        return area;
    }
    
    /**
     * @param 对area进行赋值
     */
    public void setArea(String area)
    {
        this.area = area;
    }
    
    /**
     * @return 返回 createTime
     */
    public Date getCreateTime()
    {
        return createTime;
    }
    
    /**
     * @param 对createTime进行赋值
     */
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
    
    /**
     * @return 返回 updateTime
     */
    public Date getUpdateTime()
    {
        return updateTime;
    }
    
    /**
     * @param 对updateTime进行赋值
     */
    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }
    
    /**
     * @return 返回 serialversionuid
     */
    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }
    
}