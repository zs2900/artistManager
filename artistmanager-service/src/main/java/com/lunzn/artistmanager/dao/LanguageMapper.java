package com.lunzn.artistmanager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lunzn.artistmanager.model.Language;

/**
 * 
 * 语言表DAO
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年10月23日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Mapper
public interface LanguageMapper
{
    /**
     * 
     * 查询语言列表
     * <功能详细描述>
     * @return 语言列表
     * @see [类、类#方法、类#成员]
     */
    List<Language> queryLanguageList();
}