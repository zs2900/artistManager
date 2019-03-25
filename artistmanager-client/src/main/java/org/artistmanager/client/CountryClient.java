/*
 * 文 件 名:  CountryClient.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  ZhongYi
 * 修改时间:  2018年10月19日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package org.artistmanager.client;

import com.lunzn.artistmanager.controller.req.QueryCountryListEvt;
import com.lunzn.artistmanager.controller.resp.QueryCountryListResp;
import com.lunzn.artistmanager.system.exception.InnerException;

/**
 * 国家Client
 * 提供国家相关操作接口
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年10月19日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public final class CountryClient extends AbstractContentServiceClient
{
    /**
     * 
     * 查询国家列表
     * <功能详细描述>
     * @param evt 查询国家列表请求体
     * @return 国家列表响应实体
     * @throws InnerException 自定义的服务器内部异常
     * @see [类、类#方法、类#成员]
     */
    public static QueryCountryListResp queryCountryList(QueryCountryListEvt evt)
        throws InnerException
    {
        return send(evt, QueryCountryListResp.class);
        
    }
}
