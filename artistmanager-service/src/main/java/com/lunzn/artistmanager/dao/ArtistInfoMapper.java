package com.lunzn.artistmanager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lunzn.artistmanager.model.ArtistInfo;

/**
 * 
 * 艺人信息表DAO
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年10月23日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Mapper
public interface ArtistInfoMapper
{
    /**
     * 
     * 查询艺人信息列表
     * <功能详细描述>
     * @return 艺人信息列表
     * @see [类、类#方法、类#成员]
     */
    List<ArtistInfo> queryAll();
    
    /**
     * 
     * 通过艺人编码查询艺人信息
     * <功能详细描述>
     * @param artistCode 艺人编码
     * @return 艺人信息
     * @see [类、类#方法、类#成员]
     */
    ArtistInfo queryArtistInfoByID(String artistCode);
    
    /**
     * 
     * 更新艺人基本信息
     * <功能详细描述>
     * @param artistInfo 艺人基本信息实体
     * @return 更新结果
     * @see [类、类#方法、类#成员]
     */
    Integer updateArtistInfo(ArtistInfo artistInfo);
    
    /**
     * 
     * 新增艺人基本信息
     * <功能详细描述>
     * @param artistInfo 艺人基本信息实体
     * @return 艺人基本信息新增结果
     * @see [类、类#方法、类#成员]
     */
    Integer insertArtistInfo(ArtistInfo artistInfo);
    
    /**
     * 
     * 删除艺人基本信息
     * <功能详细描述>
     * @param artistCode 艺人编码
     * @return 删除艺人基本信息结果行数
     * @see [类、类#方法、类#成员]
     */
    Integer deleteArtistInfo(String artistCode);
}