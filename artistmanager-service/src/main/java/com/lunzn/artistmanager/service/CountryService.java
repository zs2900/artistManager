/*
 * 文 件 名:  CountryService.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  ZhongYi
 * 修改时间:  2018年10月19日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.lunzn.artistmanager.service;

import com.lunzn.artistmanager.controller.resp.QueryCountryListResp;

/**
 * 国家service
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年10月19日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface CountryService
{
    /**
     * 
     * 查询国家列表
     * <功能详细描述>
     * @return  国家列表响应实体
     * @see [类、类#方法、类#成员]
     */
    QueryCountryListResp queryCountryList();
}
