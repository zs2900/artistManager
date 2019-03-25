/*
 * 文 件 名:  IndexController.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  ZhongYi
 * 修改时间:  2018年10月23日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.lunzn.artistmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 艺人管理主页面Controller
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年10月23日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller
public class IndexController
{
    
    /**
     * 
     * 主页面
     * <功能详细描述>
     * @return 艺人列表页面
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/artist")
    public String artist()
    {
        return "artist/artistManager";
    }
}
