/*
 * 文 件 名:  ArtistServiceImpl.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  Administrator
 * 修改时间:  2018年10月15日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.lunzn.artistmanager.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lunzn.artistmanager.cache.CacheKey;
import com.lunzn.artistmanager.cache.service.RedisService;
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
import com.lunzn.artistmanager.dao.ArtistInfoMapper;
import com.lunzn.artistmanager.dao.ArtistMapper;
import com.lunzn.artistmanager.enums.ResultCode;
import com.lunzn.artistmanager.model.ArtistDetialInfo;
import com.lunzn.artistmanager.model.ArtistI18n;
import com.lunzn.artistmanager.model.ArtistInfo;
import com.lunzn.artistmanager.model.ArtistList;
import com.lunzn.artistmanager.service.ArtistService;
import com.lunzn.artistmanager.system.exception.InnerException;
import com.lunzn.artistmanager.validator.Validator;

/**
 * 
 * 艺人管理serviceImpl
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
    /**
     * 事务超时时间
     */
    private static final int TIMEOUT = 30000;
    
    @Autowired
    private ArtistInfoMapper artistInfoMapper;
    
    @Autowired
    private ArtistMapper artistMapper;
    
    @Autowired
    private RedisService redisService;
    
    @Override
    public QueryArtistListResp queryArtistList(QueryArtistListEvt evt)
        throws InnerException
    {
        //参数校验
        Validator.getInstance().validate(QueryArtistListEvt.class, evt);
        
        //查询艺人列表
        List<ArtistList> list = artistMapper.queryArtistList(evt);
        Integer total = artistMapper.queryArtistListTotal(evt);
        
        //创建艺人列表响应对象，设置返回数据
        QueryArtistListResp queryArtistListResp = new QueryArtistListResp();
        queryArtistListResp.setRows(list);
        queryArtistListResp.setTotal(total);
        
        return queryArtistListResp;
    }
    
    @Override
    public ArtistDetialInfoResp queryArtistDetialInfo(ArtistDetialInfoEvt evt)
        throws InnerException
    {
        //参数校验
        Validator.getInstance().validate(ArtistDetialInfoEvt.class, evt);
        
        //获取缓存key
        String key = evt.getArtistCode();
        String cacheKey = CacheKey.KvMapping.artist_detialInfo_artistCode.format(key);
        
        //查询缓存
        ArtistDetialInfo artistDetialInfo = redisService.get(cacheKey, ArtistDetialInfo.class);
        
        //如果缓存中没有数据，从数据库查询
        if (artistDetialInfo == null)
        {
            //查询艺人基本信息
            ArtistInfo artistInfo = artistInfoMapper.queryArtistInfoByID(evt.getArtistCode());
            //查询艺人多语言信息
            List<ArtistI18n> artistI18ns = artistMapper.queryArtistI18nList(evt.getArtistCode());
            //添加到缓存
            if (artistInfo != null && artistI18ns != null)
            {
                //创建艺人详细信息对象
                artistDetialInfo = new ArtistDetialInfo();
                artistDetialInfo.setArtistInfo(artistInfo);
                artistDetialInfo.setArtistI18ns(artistI18ns);
                redisService.set(cacheKey, artistDetialInfo);
            }
            else
            {
                ArtistDetialInfoResp resp = new ArtistDetialInfoResp();
                resp.setRetCode(ResultCode.DATA_NOT_EXIST.getResultCode());
                resp.setRetMsg(ResultCode.DATA_NOT_EXIST.getResultMsg());
                return resp;
            }
        }
        
        ArtistDetialInfoResp resp = new ArtistDetialInfoResp();
        resp.setArtistDetialInfo(artistDetialInfo);
        return resp;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = TIMEOUT, rollbackFor = Exception.class)
    public ArtistEditResp updateArtist(ArtistEditEvt evt)
        throws InnerException
    {
        //请求参数校验
        Validator.getInstance().validate(ArtistEditEvt.class, evt);
        
        //参数校验成功，执行更新操作，返回更新响应实体
        artistInfoMapper.updateArtistInfo(evt.getArtistInfo());
        artistMapper.deleteArtistI18n(evt.getArtistInfo().getArtistCode());
        artistMapper.insertArtistI18n(evt.getArtistI18ns());
        
        //更新艺人详细信息缓存
        String cacheKey = CacheKey.KvMapping.artist_detialInfo_artistCode.format(evt.getArtistInfo().getArtistCode());
        //查询更新后的数据
        ArtistInfo artistInfo = artistInfoMapper.queryArtistInfoByID(evt.getArtistInfo().getArtistCode());
        List<ArtistI18n> artistI18ns = artistMapper.queryArtistI18nList(evt.getArtistInfo().getArtistCode());
        //创建艺人详细信息对象
        ArtistDetialInfo artistDetialInfo = new ArtistDetialInfo();
        artistDetialInfo.setArtistI18ns(artistI18ns);
        artistDetialInfo.setArtistInfo(artistInfo);
        //删除原有数据
        redisService.del(cacheKey);
        //数据添加进缓存
        redisService.set(cacheKey, artistDetialInfo);
        
        //返回更新操作响应实体
        return new ArtistEditResp();
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = TIMEOUT, rollbackFor = Exception.class)
    public ArtistAddResp addArtist(ArtistAddEvt evt)
        throws InnerException
    {
        //请求参数校验
        Validator.getInstance().validate(ArtistAddEvt.class, evt);
        
        //新增艺人操作
        artistMapper.insertArtistI18n(evt.getArtistI18ns());
        artistInfoMapper.insertArtistInfo(evt.getArtistInfo());
        
        //更新缓存
        String cacheKey = CacheKey.KvMapping.artist_detialInfo_artistCode.format(evt.getArtistInfo().getArtistCode());
        
        //查询数据库新增的数据
        ArtistInfo artistInfo = artistInfoMapper.queryArtistInfoByID(evt.getArtistInfo().getArtistCode());
        List<ArtistI18n> artistI18ns = artistMapper.queryArtistI18nList(evt.getArtistInfo().getArtistCode());
        
        //创建艺人详细信息实体
        ArtistDetialInfo artistDetialInfo = new ArtistDetialInfo();
        artistDetialInfo.setArtistI18ns(artistI18ns);
        artistDetialInfo.setArtistInfo(artistInfo);
        
        //添加到缓存
        redisService.set(cacheKey, artistDetialInfo);
        
        return new ArtistAddResp();
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = TIMEOUT, rollbackFor = Exception.class)
    public ArtistDeleteResp deleteArtist(ArtistDeleteEvt evt)
        throws InnerException
    {
        //参数校验
        Validator.getInstance().validate(ArtistDeleteEvt.class, evt);
        
        //删除数据库
        artistInfoMapper.deleteArtistInfo(evt.getArtistCode());
        artistMapper.deleteArtistI18n(evt.getArtistCode());
        
        //删除缓存
        String cacheKey = CacheKey.KvMapping.artist_detialInfo_artistCode.format(evt.getArtistCode());
        redisService.del(cacheKey);
        
        return new ArtistDeleteResp();
    }
    
}
