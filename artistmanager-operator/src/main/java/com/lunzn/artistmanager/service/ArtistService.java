package com.lunzn.artistmanager.service;

import com.lunzn.artistmanager.controller.req.ArtistAddEvt;
import com.lunzn.artistmanager.controller.req.ArtistDeleteEvt;
import com.lunzn.artistmanager.controller.req.ArtistDetialInfoEvt;
import com.lunzn.artistmanager.controller.req.ArtistEditEvt;
import com.lunzn.artistmanager.controller.req.QueryArtistListEvt;
import com.lunzn.artistmanager.controller.resp.ArtistAddResp;
import com.lunzn.artistmanager.controller.resp.ArtistDeleteResp;
import com.lunzn.artistmanager.controller.resp.ArtistDetialInfoResp;
import com.lunzn.artistmanager.controller.resp.ArtistEditResp;
import com.lunzn.artistmanager.controller.resp.QueryArtistListResp;
import com.lunzn.artistmanager.system.exception.InnerException;

/**
 * 
 * 艺人管理service
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年10月29日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ArtistService
{
    
    /**
     * 
     * 查询艺人列表
     * <功能详细描述>
     * @param evt 查询艺人请求体
     * @return 艺人列表响应实体
     * @throws InnerException 自定义的服务器内部异常
     * @see [类、类#方法、类#成员]
     */
    QueryArtistListResp queryArtistList(QueryArtistListEvt evt)
        throws InnerException;
    
    /**
     * 
     * 查询艺人相信信息
     * <功能详细描述>
     * @param evt 查询艺人详细信息请求体
     * @return 艺人详细信息实体
     * @throws InnerException 自定义的服务器内部异常
     * @see [类、类#方法、类#成员]
     */
    ArtistDetialInfoResp queryArtistDetialInfo(ArtistDetialInfoEvt evt)
        throws InnerException;
    
    /**
     * 
     * 艺人信息编辑
     * <功能详细描述>
     * @param evt 信息编辑请求体
     * @return 信息编辑响应实体
     * @throws InnerException 自定义的服务器内部异常
     * @see [类、类#方法、类#成员]
     */
    ArtistEditResp editArtist(ArtistEditEvt evt)
        throws InnerException;
    
    /**
     * 
     * 新增艺人
     * <功能详细描述>
     * @param evt 新增艺人请求体
     * @return 艺人新增响应实体
     * @throws InnerException 自定义的服务器内部异常
     * @see [类、类#方法、类#成员]
     */
    ArtistAddResp addArtist(ArtistAddEvt evt)
        throws InnerException;
    
    /**
     * 
     * 删除艺人
     * <功能详细描述>
     * @param evt 删除艺人请求体
     * @return 删除艺人响应实体
     * @throws InnerException 自定义的服务器内部异常
     * @see [类、类#方法、类#成员]
     */
    ArtistDeleteResp deleteArtist(ArtistDeleteEvt evt)
        throws InnerException;
}
