/*
 * 文 件 名:  CountryServiceImpl.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  ZhongYi
 * 修改时间:  2018年10月19日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.lunzn.artistmanager.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunzn.artistmanager.controller.resp.QueryCountryListResp;
import com.lunzn.artistmanager.dao.CountryMapper;
import com.lunzn.artistmanager.model.Country;
import com.lunzn.artistmanager.service.CountryService;

/**
 * 国家serviceImpl
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年10月19日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service
public class CountryServiceImpl implements CountryService
{
    @Autowired
    private CountryMapper countryDao;
    
    /** 
     * 查询国家列表
     * <功能详细描述>
     * @return 国家响应实体
     * @see [类、类#方法、类#成员]
     */
    @Override
    public QueryCountryListResp queryCountryList()
    {
        List<Country> list = countryDao.queryCountryList();
        QueryCountryListResp resp = new QueryCountryListResp();
        resp.setRows(list);
        return resp;
    }
    
}
