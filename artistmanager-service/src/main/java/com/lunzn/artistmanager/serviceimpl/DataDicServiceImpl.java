package com.lunzn.artistmanager.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunzn.artistmanager.controller.req.QueryDataDicEvt;
import com.lunzn.artistmanager.controller.resp.QueryDataDicResp;
import com.lunzn.artistmanager.dao.DataDicMapper;
import com.lunzn.artistmanager.model.DataDic;
import com.lunzn.artistmanager.service.DataDicService;
import com.lunzn.artistmanager.system.exception.InnerException;
import com.lunzn.artistmanager.system.exception.ValidateException;
import com.lunzn.artistmanager.validator.Validator;

/**
 * 数据字典服务
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
    @Autowired
    private DataDicMapper dataDicDao;
    
    /** 
     * 查询数据字典
     * <功能详细描述>
     * @return 数据字典响应实体
     * @throws InnerException 
     * @throws ValidateException 
     * @see [类、类#方法、类#成员]
     */
    @Override
    public QueryDataDicResp queryDataDics(QueryDataDicEvt evt)
        throws InnerException
    {
        //参数校验
        Validator.getInstance().validate(QueryDataDicEvt.class, evt);
        
        List<DataDic> list = dataDicDao.queryDataDic(evt.getDictionaryCode());
        QueryDataDicResp resp = new QueryDataDicResp();
        resp.setRows(list);
        return resp;
    }
    
}
