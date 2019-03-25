/*
 * 文 件 名:  DataDicService.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  ZhongYi
 * 修改时间:  2018年10月22日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.lunzn.artistmanager.service;

import com.lunzn.artistmanager.controller.req.QueryDataDicEvt;
import com.lunzn.artistmanager.controller.resp.QueryDataDicResp;
import com.lunzn.artistmanager.system.exception.InnerException;

/**
 * 数据字典service
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年10月22日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface DataDicService
{
    /**
     * 
     * 查询数据字典列表
     * <功能详细描述>
     * @param evt 数据字典请求体
     * @return 查询数据字典列表响应实体
     * @throws InnerException 自定义的服务器内部异常
     * @see [类、类#方法、类#成员]
     */
    QueryDataDicResp queryDataDics(QueryDataDicEvt evt)
        throws InnerException;
}
