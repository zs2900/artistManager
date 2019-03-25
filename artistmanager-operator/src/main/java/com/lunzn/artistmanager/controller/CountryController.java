package com.lunzn.artistmanager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lunzn.artistmanager.controller.req.QueryCountryListEvt;
import com.lunzn.artistmanager.controller.resp.QueryCountryListResp;
import com.lunzn.artistmanager.service.CountryService;
import com.lunzn.artistmanager.system.exception.InnerException;

/**
 * 国籍管理Controller
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年10月19日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@RestController
public class CountryController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);
    
    @Autowired
    private CountryService countryService;
    
    /**
     * 
     * 查询国家列表
     * <功能详细描述>
     * @param evt 国家列表请求体
     * @return 国家列表响应实体
     * @throws InnerException 
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("country/list")
    public QueryCountryListResp queryCountry(@RequestBody QueryCountryListEvt evt)
        throws InnerException
    {
        LOGGER.info("国家列表请求体QueryCountryListEvt：{}", evt);
        
        return countryService.queryCountryList(evt);
    }
}
