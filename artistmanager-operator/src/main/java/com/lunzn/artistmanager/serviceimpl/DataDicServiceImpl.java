package com.lunzn.artistmanager.serviceimpl;

import org.artistmanager.client.DataDicClient;
import org.springframework.stereotype.Service;

import com.lunzn.artistmanager.controller.req.QueryDataDicEvt;
import com.lunzn.artistmanager.controller.resp.QueryDataDicResp;
import com.lunzn.artistmanager.service.DataDicService;
import com.lunzn.artistmanager.system.exception.InnerException;
import com.lunzn.artistmanager.validator.Validator;

/**
 * 数据字典管理实现类
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年10月22日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service
public class DataDicServiceImpl implements DataDicService
{
    
    @Override
    public QueryDataDicResp queryDataDic(QueryDataDicEvt evt)
        throws InnerException
    {
        //参数校验
        Validator.getInstance().validate(QueryDataDicEvt.class, evt);
        
        return DataDicClient.queryDataDic(evt);
    }
    
}
