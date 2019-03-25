package com.lunzn.artistmanager.service;

import com.lunzn.artistmanager.controller.req.QueryDataDicEvt;
import com.lunzn.artistmanager.controller.resp.QueryDataDicResp;
import com.lunzn.artistmanager.system.exception.InnerException;

/**
 * 数据字典管理service
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
     * 查询数据字典
     * <功能详细描述>
     * @param evt 查询数据字典数据类型请求实体
     * @return 数据字典响应实体
     * @throws InnerException 自定义的服务器内部异常
     * @see [类、类#方法、类#成员]
     */
    QueryDataDicResp queryDataDic(QueryDataDicEvt evt)
        throws InnerException;
}
