package com.lunzn.artistmanager.model;

import java.io.Serializable;

/**
 * 
 * 数据字典
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年10月19日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class DataDic implements Serializable
{
    /**
     * 字典编码
     */
    private String dictionaryCode;
    
    /**
     * 字典名称
     */
    private String dictionaryName;
    
    /**
     * 字典项编码
     */
    private String dicitemCode;
    
    /**
     * 字典项名称（中文）
     */
    private String dicitemNameZh;
    
    /**
     * 字典项名称（英文）
     */
    private String dicitemNameEn;
    
    private static final long serialVersionUID = 1L;
    
    /**
     * @return 返回 dictionaryCode
     */
    public String getDictionaryCode()
    {
        return dictionaryCode;
    }
    
    /**
     * @param 对dictionaryCode进行赋值
     */
    public void setDictionaryCode(String dictionaryCode)
    {
        this.dictionaryCode = dictionaryCode;
    }
    
    /**
     * @return 返回 dictionaryName
     */
    public String getDictionaryName()
    {
        return dictionaryName;
    }
    
    /**
     * @param 对dictionaryName进行赋值
     */
    public void setDictionaryName(String dictionaryName)
    {
        this.dictionaryName = dictionaryName;
    }
    
    /**
     * @return 返回 dicitemCode
     */
    public String getDicitemCode()
    {
        return dicitemCode;
    }
    
    /**
     * @param 对dicitemCode进行赋值
     */
    public void setDicitemCode(String dicitemCode)
    {
        this.dicitemCode = dicitemCode;
    }
    
    /**
     * @return 返回 dicitemNameZh
     */
    public String getDicitemNameZh()
    {
        return dicitemNameZh;
    }
    
    /**
     * @param 对dicitemNameZh进行赋值
     */
    public void setDicitemNameZh(String dicitemNameZh)
    {
        this.dicitemNameZh = dicitemNameZh;
    }
    
    /**
     * @return 返回 dicitemNameEn
     */
    public String getDicitemNameEn()
    {
        return dicitemNameEn;
    }
    
    /**
     * @param 对dicitemNameEn进行赋值
     */
    public void setDicitemNameEn(String dicitemNameEn)
    {
        this.dicitemNameEn = dicitemNameEn;
    }
    
    /**
     * @return 返回 serialversionuid
     */
    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }
    
}