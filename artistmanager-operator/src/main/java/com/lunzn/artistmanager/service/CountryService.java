package com.lunzn.artistmanager.service;

import com.lunzn.artistmanager.controller.req.QueryCountryListEvt;
import com.lunzn.artistmanager.controller.resp.QueryCountryListResp;
import com.lunzn.artistmanager.system.exception.InnerException;

/**
 * 国籍管理service
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
     * 查询国家列表service
     * <功能详细描述>
     * @param evt 查询国家列表请求体
     * @return 国家列表响应实体
     * @throws InnerException 自定义的服务器内部异常
     * @see [类、类#方法、类#成员]
     */
    QueryCountryListResp queryCountryList(QueryCountryListEvt evt)
        throws InnerException;
}
