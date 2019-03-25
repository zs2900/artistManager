package com.lunzn.artistmanager.model;

import java.util.Date;

import com.lunzn.artistmanager.validator.IValidatable;
import com.lunzn.artistmanager.validator.annotations.Param;

public class ArtistInfo implements IValidatable
{
    /**
     * 艺人id
     */
    private Integer artistId;
    
    /**
     * 艺人编码
     */
    private String artistCode;
    
    /**
     * 默认语言信息
     */
    private String defaultLanguage;
    
    /**
     * 职业
     */
    private String artistType;
    
    /**
     * 艺术家性别
     */
    private String sex;
    
    /**
     * 生日
     */
    private String birthday;
    
    /**
     * 国籍
     */
    private String nationality;
    
    /**
     * 血型
     */
    private String bloodGroup;
    
    /**
     * 星座
     */
    private String constellation;
    
    /**
     * 身高
     */
    private Integer height;
    
    /**
     * 体重
     */
    private Integer weight;
    
    /**
     * 是否团队
     */
    private Integer isTeam;
    
    /**
     * 名
     */
    private String firstName;
    
    /**
     *姓
     */
    private String lastName;
    
    /**
     * 艺术家检索名
     */
    private String searchName;
    
    /**
     * 归属地,出生地
     */
    private String zone;
    
    /**
     * 艺术家首字母
     */
    private String firstLetter;
    
    /**
     * 艺术家所属公司名称
     */
    private String companyName;
    
    /**
     * 艺术家出道时间
     */
    private String buildTime;
    
    /**
     * 艺术家签约公司时间
     */
    private String joininTime;
    
    /**
     * 教育程度
     */
    private String education;
    
    /**
     * 爱好
     */
    private String favorite;
    
    /**
     * 母语
     */
    private String motherTongue;
    
    /**
     * 婚姻状态
     */
    private Integer marriage;
    
    /**
     * 操作状态
     */
    private String operationStatus;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 最后修改时间
     */
    private Date updateTime;
    
    /**
     * 头像图片地址
     */
    private String headImg;
    
    private static final long serialVersionUID = 1L;
    
    /**
     * @return 返回 artistId
     */
    public Integer getArtistId()
    {
        return artistId;
    }
    
    /**
     * @param 对artistId进行赋值
     */
    public void setArtistId(Integer artistId)
    {
        this.artistId = artistId;
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
     * @return 返回 defaultLanguage
     */
    @Param(canBlank = false, maxLength = 5, regex = "^[a-z]{2}_[A-Z]{2}$")
    public String getDefaultLanguage()
    {
        return defaultLanguage;
    }
    
    /**
     * @param 对defaultLanguage进行赋值
     */
    public void setDefaultLanguage(String defaultLanguage)
    {
        this.defaultLanguage = defaultLanguage;
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
     * @return 返回 sex
     */
    @Param(canBlank = true, maxLength = 8)
    public String getSex()
    {
        return sex;
    }
    
    /**
     * @param 对sex进行赋值
     */
    public void setSex(String sex)
    {
        this.sex = sex;
    }
    
    /**
     * @return 返回 birthday
     */
    @Param(canBlank = true, maxLength = 14)
    public String getBirthday()
    {
        return birthday;
    }
    
    /**
     * @param 对birthday进行赋值
     */
    public void setBirthday(String birthday)
    {
        this.birthday = birthday;
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
     * @return 返回 bloodGroup
     */
    @Param(canBlank = true, maxLength = 10, regex = "^(A|B|AB|O|Rh)$")
    public String getBloodGroup()
    {
        return bloodGroup;
    }
    
    /**
     * @param 对bloodGroup进行赋值
     */
    public void setBloodGroup(String bloodGroup)
    {
        this.bloodGroup = bloodGroup;
    }
    
    /**
     * @return 返回 constellation
     */
    @Param(canBlank = true, maxLength = 10)
    public String getConstellation()
    {
        return constellation;
    }
    
    /**
     * @param 对constellation进行赋值
     */
    public void setConstellation(String constellation)
    {
        this.constellation = constellation;
    }
    
    /**
     * @return 返回 height
     */
    @Param(canBlank = true, maxLength = 300, regex = "^[1-9][0-9]{1,9}$")
    public Integer getHeight()
    {
        return height;
    }
    
    /**
     * @param 对height进行赋值
     */
    public void setHeight(Integer height)
    {
        this.height = height;
    }
    
    /**
     * @return 返回 weight
     */
    @Param(canBlank = true, maxLength = 1000, regex = "^[1-9][0-9]{1,9}$")
    public Integer getWeight()
    {
        return weight;
    }
    
    /**
     * @param 对weight进行赋值
     */
    public void setWeight(Integer weight)
    {
        this.weight = weight;
    }
    
    /**
     * @return 返回 isTeam
     */
    @Param(canBlank = true, maxLength = 1, regex = "^[0,1]$")
    public Integer getIsTeam()
    {
        return isTeam;
    }
    
    /**
     * @param 对isTeam进行赋值
     */
    public void setIsTeam(Integer isTeam)
    {
        this.isTeam = isTeam;
    }
    
    /**
     * @return 返回 firstName
     */
    @Param(canBlank = true, maxLength = 128)
    public String getFirstName()
    {
        return firstName;
    }
    
    /**
     * @param 对firstName进行赋值
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    
    /**
     * @return 返回 lastName
     */
    @Param(canBlank = true, maxLength = 128)
    public String getLastName()
    {
        return lastName;
    }
    
    /**
     * @param 对lastName进行赋值
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    
    /**
     * @return 返回 searchName
     */
    @Param(canBlank = true, maxLength = 128)
    public String getSearchName()
    {
        return searchName;
    }
    
    /**
     * @param 对searchName进行赋值
     */
    public void setSearchName(String searchName)
    {
        this.searchName = searchName;
    }
    
    /**
     * @return 返回 zone
     */
    @Param(canBlank = true, maxLength = 256)
    public String getZone()
    {
        return zone;
    }
    
    /**
     * @param 对zone进行赋值
     */
    public void setZone(String zone)
    {
        this.zone = zone;
    }
    
    /**
     * @return 返回 firstLetter
     */
    @Param(canBlank = true, maxLength = 1, regex = "^[A-Z]$")
    public String getFirstLetter()
    {
        return firstLetter;
    }
    
    /**
     * @param 对firstLetter进行赋值
     */
    public void setFirstLetter(String firstLetter)
    {
        this.firstLetter = firstLetter;
    }
    
    /**
     * @return 返回 companyName
     */
    @Param(canBlank = true, maxLength = 128)
    public String getCompanyName()
    {
        return companyName;
    }
    
    /**
     * @param 对companyName进行赋值
     */
    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }
    
    /**
     * @return 返回 buildTime
     */
    @Param(canBlank = true, maxLength = 14)
    public String getBuildTime()
    {
        return buildTime;
    }
    
    /**
     * @param 对buildTime进行赋值
     */
    public void setBuildTime(String buildTime)
    {
        this.buildTime = buildTime;
    }
    
    /**
     * @return 返回 joininTime
     */
    @Param(canBlank = true, maxLength = 14)
    public String getJoininTime()
    {
        return joininTime;
    }
    
    /**
     * @param 对joininTime进行赋值
     */
    public void setJoininTime(String joininTime)
    {
        this.joininTime = joininTime;
    }
    
    /**
     * @return 返回 education
     */
    @Param(canBlank = true, maxLength = 128)
    public String getEducation()
    {
        return education;
    }
    
    /**
     * @param 对education进行赋值
     */
    public void setEducation(String education)
    {
        this.education = education;
    }
    
    /**
     * @return 返回 favorite
     */
    @Param(canBlank = true, maxLength = 256)
    public String getFavorite()
    {
        return favorite;
    }
    
    /**
     * @param 对favorite进行赋值
     */
    public void setFavorite(String favorite)
    {
        this.favorite = favorite;
    }
    
    /**
     * @return 返回 motherTongue
     */
    @Param(canBlank = true, maxLength = 256)
    public String getMotherTongue()
    {
        return motherTongue;
    }
    
    /**
     * @param 对motherTongue进行赋值
     */
    public void setMotherTongue(String motherTongue)
    {
        this.motherTongue = motherTongue;
    }
    
    /**
     * @return 返回 marriage
     */
    @Param(canBlank = true, maxLength = 3, regex = "^[0,1,2,3]$")
    public Integer getMarriage()
    {
        return marriage;
    }
    
    /**
     * @param 对marriage进行赋值
     */
    public void setMarriage(Integer marriage)
    {
        this.marriage = marriage;
    }
    
    /**
     * @return 返回 operationStatus
     */
    @Param(canBlank = true, maxLength = 16)
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
     * @return 返回 headImg
     */
    @Param(canBlank = true, maxLength = 512)
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
    
    /**
     * @return 返回 serialversionuid
     */
    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }
    
}