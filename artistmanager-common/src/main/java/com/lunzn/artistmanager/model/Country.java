package com.lunzn.artistmanager.model;

/**
 * 
 * 国家实体类
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年10月19日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class Country
{
    /**
     * 自增主键
     */
    private int Id;
    
    /**
     * 国家短码
     */
    private String countryCode;
    
    /**
     * 国家名称中文
     */
    private String namezh;
    
    /**
     * 国家名称英文
     */
    private String nameen;
    
    /**
     * 所属地区部id
     */
    private String regionId;
    
    /**
     * 国家币种
     */
    private String currencyCode;
    
    /**
     * 是否已配置禁忌
     */
    private String tabooConfigured;
    
    /**
     * @return 返回 id
     */
    public int getId()
    {
        return Id;
    }
    
    /**
     * @param 对id进行赋值
     */
    public void setId(int id)
    {
        this.Id = id;
    }
    
    /**
     * @return 返回 countryCode
     */
    public String getCountryCode()
    {
        return countryCode;
    }
    
    /**
     * @param 对countryCode进行赋值
     */
    public void setCountryCode(String countryCode)
    {
        this.countryCode = countryCode;
    }
    
    /**
     * @return 返回 namezh
     */
    public String getNamezh()
    {
        return namezh;
    }
    
    /**
     * @param 对namezh进行赋值
     */
    public void setNamezh(String namezh)
    {
        this.namezh = namezh;
    }
    
    /**
     * @return 返回 nameen
     */
    public String getNameen()
    {
        return nameen;
    }
    
    /**
     * @param 对nameen进行赋值
     */
    public void setNameen(String nameen)
    {
        this.nameen = nameen;
    }
    
    /**
     * @return 返回 regionId
     */
    public String getRegionId()
    {
        return regionId;
    }
    
    /**
     * @param 对regionId进行赋值
     */
    public void setRegionId(String regionId)
    {
        this.regionId = regionId;
    }
    
    /**
     * @return 返回 currencyCode
     */
    public String getCurrencyCode()
    {
        return currencyCode;
    }
    
    /**
     * @param 对currencyCode进行赋值
     */
    public void setCurrencyCode(String currencyCode)
    {
        this.currencyCode = currencyCode;
    }
    
    /**
     * @return 返回 tabooConfigured
     */
    public String getTabooConfigured()
    {
        return tabooConfigured;
    }
    
    /**
     * @param 对tabooConfigured进行赋值
     */
    public void setTabooConfigured(String tabooConfigured)
    {
        this.tabooConfigured = tabooConfigured;
    }
    
}