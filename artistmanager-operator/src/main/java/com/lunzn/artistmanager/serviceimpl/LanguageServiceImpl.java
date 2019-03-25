package com.lunzn.artistmanager.serviceimpl;

import org.artistmanager.client.LanguageClient;
import org.springframework.stereotype.Service;

import com.lunzn.artistmanager.controller.req.LanguageEvt;
import com.lunzn.artistmanager.controller.resp.LanguageResp;
import com.lunzn.artistmanager.service.LanguageService;
import com.lunzn.artistmanager.system.exception.InnerException;

/**
 * 语言管理实现
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年10月30日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service
public class LanguageServiceImpl implements LanguageService
{
    
    @Override
    public LanguageResp queryLanguageList()
        throws InnerException
    {
        LanguageEvt evt = new LanguageEvt();
        return LanguageClient.queryLanguageList(evt);
    }
    
}
