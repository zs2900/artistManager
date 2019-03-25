package com.lunzn.artistmanager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lunzn.artistmanager.controller.req.QueryArtistListEvt;
import com.lunzn.artistmanager.model.ArtistI18n;
import com.lunzn.artistmanager.model.ArtistList;

/**
 * 
 * 艺人列表
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年10月23日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Mapper
public interface ArtistMapper
{
    /**
     * 
     * 艺人信息多表查询
     * <功能详细描述>
     * @param evt 艺人列表请求实体
     * @return 艺人列表
     * @see [类、类#方法、类#成员]
     */
    List<ArtistList> queryArtistList(QueryArtistListEvt evt);
    
    /**
     * 
     * 查询艺人信息总数
     * <功能详细描述>
     * @param evt 艺人列表请求体
     * @return 艺人列表总数
     * @see [类、类#方法、类#成员]
     */
    Integer queryArtistListTotal(QueryArtistListEvt evt);
    
    /**
     * 
     * 查询艺人详细信息表中的多语言表信息
     * <功能详细描述>
     * @param artistCode 艺人ID
     * @return 艺人多语言信息list
     * @see [类、类#方法、类#成员]
     */
    List<ArtistI18n> queryArtistI18nList(String artistCode);
    
    /**
     * 
     * 更新艺人多语言表
     * <功能详细描述>
     * @param artistI18ns 艺人多语言实体列表
     * @return 更新结果
     * @see [类、类#方法、类#成员]
     */
    Integer updateArtistArtistI18n(List<ArtistI18n> artistI18ns);
    
    /**
     * 
     * 新增艺人多语言表
     * <功能详细描述>
     * @param artistI18ns 艺人多语言实体列表
     * @return 新增艺人多语言信息结果
     * @see [类、类#方法、类#成员]
     */
    Integer insertArtistI18n(List<ArtistI18n> artistI18ns);
    
    /**
     * 
     * 删除艺人多语言表信息
     * <功能详细描述>
     * @param artistCode 艺人编码
     * @return 删除艺人多语言信息结果行数
     * @see [类、类#方法、类#成员]
     */
    Integer deleteArtistI18n(String artistCode);
    
    /**
     * 
     * 存在则更新，不存在则插入
     * <功能详细描述>
     * @param artistI18ns 艺人多语言实体列表
     * @return 更新艺人多语言信息结果
     * @see [类、类#方法、类#成员]
     */
    Integer updateArtistI18ns(List<ArtistI18n> artistI18ns);
}