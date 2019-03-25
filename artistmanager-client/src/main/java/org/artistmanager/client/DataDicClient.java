package org.artistmanager.client;

import com.lunzn.artistmanager.controller.req.QueryDataDicEvt;
import com.lunzn.artistmanager.controller.resp.QueryDataDicResp;
import com.lunzn.artistmanager.system.exception.InnerException;

/**
 * 数据字典Client
 * 数据字典操作接口
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年10月22日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public final class DataDicClient extends AbstractContentServiceClient
{
    /**
     * 
     * 查询数据字典列表
     * <功能详细描述>
     * @param evt 查询数据字典列表请求体
     * @return 数据字典列表响应实体
     * @throws InnerException 自定义的服务器内部异常
     * @see [类、类#方法、类#成员]
     */
    public static QueryDataDicResp queryDataDic(QueryDataDicEvt evt)
        throws InnerException
    {
        return send(evt, QueryDataDicResp.class);
        
    }
}
