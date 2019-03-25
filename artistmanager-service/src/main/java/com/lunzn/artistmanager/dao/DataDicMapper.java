package com.lunzn.artistmanager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lunzn.artistmanager.model.DataDic;

/**
 * 
 * 数据字典DAO
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年10月23日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Mapper
public interface DataDicMapper
{
    
    /**
    *  
    * 查询数据字典列表
    * <功能详细描述>
    * @param dictionaryCode 请求数据类型
    * @return 数据字典响应实体
    * @see [类、类#方法、类#成员]
    */
    List<DataDic> queryDataDic(@Param("dictionaryCode") String dictionaryCode);
}