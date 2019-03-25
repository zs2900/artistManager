package com.lunzn.artistmanager.model;

/**
 * 
 * 艺人列表bean
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年11月5日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ArtistList
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
     * 头像图片地址
     */
    private String headImg;
    
    /**
     * @return 返回 artistCode
     */
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
     * @return 返回 headImg
     */
    public String getHeadImg()
    {
        return headImg;
    }
    
    /**
     * @param 对headImg进行赋值
     */
    public void setHeadImg(String headImg)
    {
        this.headImg = headImg;
    }
    
}
