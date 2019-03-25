package com.lunzn.artistmanager.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunzn.artistmanager.controller.resp.LanguageResp;
import com.lunzn.artistmanager.dao.LanguageMapper;
import com.lunzn.artistmanager.service.LanguageService;

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
    @Autowired
    private LanguageMapper languageMapper;
    
    @Override
    public LanguageResp queryLanguangList()
    {
        LanguageResp resp = new LanguageResp();
        resp.setRows(languageMapper.queryLanguageList());
        return resp;
    }
    
}
