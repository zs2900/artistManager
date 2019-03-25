package com.lunzn.artistmanager.service;

import com.lunzn.artistmanager.controller.resp.LanguageResp;
import com.lunzn.artistmanager.system.exception.InnerException;

/**
 * 语言管理service
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年10月30日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface LanguageService
{
    /**
     * 
     * 查询语言列表
     * <功能详细描述>
     * @return 语言列表响应实体
     * @throws InnerException 自定义的服务器内部异常
     * @see [类、类#方法、类#成员]
     */
    LanguageResp queryLanguageList()
        throws InnerException;
}
