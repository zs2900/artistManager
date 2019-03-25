/*
 * 文 件 名:  ArtistEditResp.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  ZhongYi
 * 修改时间:  2018年10月25日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.lunzn.artistmanager.controller.resp;

import com.alibaba.fastjson.JSONObject;
import com.lunzn.artistmanager.message.response.BaseResponse;

/**
 * 编辑艺人响应实体
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年10月25日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ArtistEditResp extends BaseResponse
{
    
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public String toString()
    {
        return JSONObject.toJSONString(this);
    }
    
}
