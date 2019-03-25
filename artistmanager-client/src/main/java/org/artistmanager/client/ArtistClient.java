package org.artistmanager.client;

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
 * 艺人管理接口
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年11月2日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public final class ArtistClient extends AbstractContentServiceClient
{
    
    /**
     * 
     * 查询艺人列表接口
     * <功能详细描述>
     * @param evt 查询艺人列表请求体
     * @return 艺人列表响应实体
     * @throws InnerException 自定义的服务器内部异常
     * @see [类、类#方法、类#成员]
     */
    public static QueryArtistListResp queryArtistListResp(QueryArtistListEvt evt)
        throws InnerException
    {
        return send(evt, QueryArtistListResp.class);
        
    }
    
    /**
     * 
     * 查询艺人详细信息接口
     * <功能详细描述>
     * @param evt 查询艺人详细信息请求体
     * @return 艺人详细信息实体
     * @throws InnerException 自定义的服务器内部异常
     * @see [类、类#方法、类#成员]
     */
    public static ArtistDetialInfoResp queryArtistDetialInfo(ArtistDetialInfoEvt evt)
        throws InnerException
    {
        
        return send(evt, ArtistDetialInfoResp.class);
        
    }
    
    /**
     * 
     * 艺人信息编辑接口
     * <功能详细描述>
     * @param evt 艺人信息编辑请求体
     * @return 艺人信息编辑响应实体
     * @throws InnerException 自定义的服务器内部异常
     * @see [类、类#方法、类#成员]
     */
    public static ArtistEditResp artistEdit(ArtistEditEvt evt)
        throws InnerException
    {
        
        return send(evt, ArtistEditResp.class);
        
    }
    
    /**
     * 
     * 新增艺人接口
     * <功能详细描述>
     * @param evt 新增艺人请求体
     * @return 新增艺人响应实体
     * @throws InnerException 自定义的服务器内部异常
     * @see [类、类#方法、类#成员]
     */
    public static ArtistAddResp artistAdd(ArtistAddEvt evt)
        throws InnerException
    {
        return send(evt, ArtistAddResp.class);
        
    }
    
    /**
     * 
     * 艺人删除接口
     * <功能详细描述>
     * @param evt 删除艺人请求体
     * @return 删除艺人响应实体
     * @throws InnerException 自定义的服务器内部异常
     * @see [类、类#方法、类#成员]
     */
    public static ArtistDeleteResp artistDelete(ArtistDeleteEvt evt)
        throws InnerException
    {
        return send(evt, ArtistDeleteResp.class);
        
    }
}
