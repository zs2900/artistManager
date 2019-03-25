/*
 * 文 件 名:  LanguageController.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  ZhongYi
 * 修改时间:  2018年10月30日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.lunzn.artistmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lunzn.artistmanager.controller.resp.LanguageResp;
import com.lunzn.artistmanager.service.LanguageService;

/**
 * 语言管理Controller
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年10月30日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@RestController
public class LanguageController
{
    @Autowired
    private LanguageService languageService;
    
    /**
     * 
     * 查询语言列表
     * <功能详细描述>
     * @return 语言列表响应实体
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/artist/languageList")
    public LanguageResp queryLanguageList()
    {
        return languageService.queryLanguangList();
    }
}
