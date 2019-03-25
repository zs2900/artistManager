/*
 * 文 件 名:  QueryDataDicEvt.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  ZhongYi
 * 修改时间:  2018年10月22日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.lunzn.artistmanager.controller.req;

import com.alibaba.fastjson.JSONObject;
import com.lunzn.artistmanager.message.annotations.Location;
import com.lunzn.artistmanager.message.reqest.BaseRequestData;
import com.lunzn.artistmanager.validator.annotations.Param;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年10月22日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Location(module = "", uri = "/DataDic/list")
public class QueryDataDicEvt extends BaseRequestData
{
    /**
     * 字典编码
     */
    private String dictionaryCode;
    
    /**
     * @return 返回 dictionaryCode
     */
    @Param(canBlank = false)
    public String getDictionaryCode()
    {
        return dictionaryCode;
    }
    
    /**
     * @param 对dictionaryCode进行赋值
     */
    public void setDictionaryCode(String dictionaryCode)
    {
        this.dictionaryCode = dictionaryCode;
    }
    
    /** 
     * 打印数据字典请求体对象
     * <功能详细描述>
     * @return 对象的json字符串
     * @see [类、类#方法、类#成员]
     */
    @Override
    public String toString()
    {
        return JSONObject.toJSONString(this);
    }
    
}
