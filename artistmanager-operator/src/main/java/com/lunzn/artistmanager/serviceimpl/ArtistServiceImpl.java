package com.lunzn.artistmanager.serviceimpl;

import org.artistmanager.client.ArtistClient;
import org.springframework.stereotype.Service;

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
import com.lunzn.artistmanager.service.ArtistService;
import com.lunzn.artistmanager.system.exception.InnerException;
import com.lunzn.artistmanager.validator.Validator;

/**
 * 
 * 艺人管理实现
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年10月29日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service
public class ArtistServiceImpl implements ArtistService
{
    
    @Override
    public QueryArtistListResp queryArtistList(QueryArtistListEvt evt)
        throws InnerException
    {
        //请求参数校验
        Validator.getInstance().validate(QueryArtistListEvt.class, evt);
        
        return ArtistClient.queryArtistListResp(evt);
    }
    
    @Override
    public ArtistDetialInfoResp queryArtistDetialInfo(ArtistDetialInfoEvt evt)
        throws InnerException
    {
        //请求参数校验
        Validator.getInstance().validate(ArtistDetialInfoEvt.class, evt);
        
        //校验成功，查询艺人详细信息并返回响应实体
        return ArtistClient.queryArtistDetialInfo(evt);
    }
    
    @Override
    public ArtistEditResp editArtist(ArtistEditEvt evt)
        throws InnerException
    {
        //请求参数校验
        Validator.getInstance().validate(ArtistEditEvt.class, evt);
        
        //校验成功，编辑艺人详细信息并返回响应实体
        return ArtistClient.artistEdit(evt);
    }
    
    @Override
    public ArtistAddResp addArtist(ArtistAddEvt evt)
        throws InnerException
    {
        //请求参数校验
        Validator.getInstance().validate(ArtistAddEvt.class, evt);
        
        return ArtistClient.artistAdd(evt);
    }
    
    @Override
    public ArtistDeleteResp deleteArtist(ArtistDeleteEvt evt)
        throws InnerException
    {
        //参数校验
        Validator.getInstance().validate(ArtistDeleteEvt.class, evt);
        
        return ArtistClient.artistDelete(evt);
    }
    
}
