/*
 * 文 件 名:  QueryArtistListResp.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  Administrator
 * 修改时间:  2018年10月18日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.lunzn.artistmanager.controller.resp;

import java.util.List;

import com.lunzn.artistmanager.message.annotations.HideWhenNull;
import com.lunzn.artistmanager.message.response.BaseResponse;
import com.lunzn.artistmanager.model.ArtistList;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2018年10月18日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class QueryArtistListResp extends BaseResponse
{
    /**
     * 艺人列表
     */
    private List<ArtistList> rows;
    
    /**
     * 总条数
     */
    @HideWhenNull
    private Integer total;
    
    /**
     * 起始位置
     */
    @HideWhenNull
    private Integer start;
    
    /**
     * 返回条数
     */
    @HideWhenNull
    private Integer count;
    
    /**
     * @return 返回 rows
     */
    public List<ArtistList> getRows()
    {
        return rows;
    }
    
    /**
     * @param 对rows进行赋值
     */
    public void setRows(List<ArtistList> rows)
    {
        this.rows = rows;
    }
    
    /**
     * @return 返回 total
     */
    public Integer getTotal()
    {
        return total;
    }
    
    /**
     * @param 对total进行赋值
     */
    public void setTotal(Integer total)
    {
        this.total = total;
    }
    
    /**
     * @return 返回 start
     */
    public Integer getStart()
    {
        return start;
    }
    
    /**
     * @param 对start进行赋值
     */
    public void setStart(Integer start)
    {
        this.start = start;
    }
    
    /**
     * @return 返回 count
     */
    public Integer getCount()
    {
        return count;
    }
    
    /**
     * @param 对count进行赋值
     */
    public void setCount(Integer count)
    {
        this.count = count;
    }
    
}
