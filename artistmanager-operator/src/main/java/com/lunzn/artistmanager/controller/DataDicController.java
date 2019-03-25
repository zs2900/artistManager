package com.lunzn.artistmanager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lunzn.artistmanager.controller.req.QueryDataDicEvt;
import com.lunzn.artistmanager.controller.resp.QueryDataDicResp;
import com.lunzn.artistmanager.service.DataDicService;
import com.lunzn.artistmanager.system.exception.InnerException;

/**
 * 数据字典管理Controller
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年10月22日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@RestController
public class DataDicController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(DataDicController.class);
    
    @Autowired
    private DataDicService dataDicService;
    
    /**
     * 
     * 查询数据字典列表
     * <功能详细描述>
     * @param evt 查询数据字典列表请求体
     * @return 数据字典列表响应实体
     * @throws InnerException 自定义的服务器内部异常
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/datadic/list")
    public QueryDataDicResp queryDataDicResp(@RequestBody QueryDataDicEvt evt)
        throws InnerException
    {
        LOGGER.info("查询数据字典列表请求体QueryDataDicEvt：{}", evt);
        
        return dataDicService.queryDataDic(evt);
    }
}
