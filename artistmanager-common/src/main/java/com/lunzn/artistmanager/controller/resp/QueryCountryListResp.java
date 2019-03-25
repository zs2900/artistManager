/*
 * 文 件 名:  QueryCountryListResp.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  ZhongYi
 * 修改时间:  2018年10月19日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.lunzn.artistmanager.controller.resp;

import java.util.List;

import com.lunzn.artistmanager.message.response.BaseResponse;
import com.lunzn.artistmanager.model.Country;

/**
 * 国家列表响应实体
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年10月19日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class QueryCountryListResp extends BaseResponse
{
    /**
     * 国家列表
     */
    private List<Country> rows;
    
    /**
     * @return 返回 rows
     */
    public List<Country> getRows()
    {
        return rows;
    }
    
    /**
     * @param 对rows进行赋值
     */
    public void setRows(List<Country> rows)
    {
        this.rows = rows;
    }
    
}
