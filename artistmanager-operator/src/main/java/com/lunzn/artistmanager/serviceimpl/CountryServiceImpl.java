package com.lunzn.artistmanager.serviceimpl;

import org.artistmanager.client.CountryClient;
import org.springframework.stereotype.Service;

import com.lunzn.artistmanager.controller.req.QueryCountryListEvt;
import com.lunzn.artistmanager.controller.resp.QueryCountryListResp;
import com.lunzn.artistmanager.service.CountryService;
import com.lunzn.artistmanager.system.exception.InnerException;

/**
 * 国籍管理实现类
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
    
    @Override
    public QueryCountryListResp queryCountryList(QueryCountryListEvt evt)
        throws InnerException
    {
        return CountryClient.queryCountryList(evt);
    }
    
}
