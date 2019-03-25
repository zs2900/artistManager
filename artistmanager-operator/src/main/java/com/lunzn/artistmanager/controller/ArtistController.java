package com.lunzn.artistmanager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

/**
 * 
 * 艺人管理Controller
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年10月29日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@RestController
public class ArtistController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ArtistController.class);
    
    @Autowired
    private ArtistService artistService;
    
    /**
     * 
     * 查询艺人列表
     * <功能详细描述>
     * @param evt 查询艺人列表请求体
     * @return 艺人列表
     * @throws InnerException 自定义的服务器内部异常
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("artist/list")
    public QueryArtistListResp queryArtistList(@RequestBody QueryArtistListEvt evt)
        throws InnerException
    {
        LOGGER.info("查询艺人列表请求体QueryArtistListEvt:{}", evt);
        
        return artistService.queryArtistList(evt);
    }
    
    /**
     * 
     * 查询艺人详细信息
     * <功能详细描述>
     * @param evt 查询艺人详细信息请求体
     * @return 艺人详细信息响应实体
     * @throws InnerException 自定义的服务器内部异常
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("artist/detailInfo")
    public ArtistDetialInfoResp queryArtistDetialInfo(@RequestBody ArtistDetialInfoEvt evt)
        throws InnerException
    {
        LOGGER.info("--------查询艺人详细信息--------");
        LOGGER.info("查询艺人详细信息请求体ArtistDetialInfoEvt:{}", evt);
        
        return artistService.queryArtistDetialInfo(evt);
    }
    
    /**
     * 
     * 艺人信息编辑
     * <功能详细描述>
     * @param requestBody 艺人信息编辑请求体
     * @return 艺人编辑信息响应实体
     * @throws InnerException 自定义的服务器内部异常
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("artist/edit")
    public ArtistEditResp artistEdit(@RequestBody ArtistEditEvt requestBody)
        throws InnerException
    {
        LOGGER.info("--------编辑艺人信息--------");
        LOGGER.info("编辑艺人信息请求体ArtistEditEvt:{}", requestBody);
        
        return artistService.editArtist(requestBody);
    }
    
    /**
     * 
     * 新增艺人
     * <功能详细描述>
     * @param evt 新增艺人请求体
     * @return 新增艺人响应实体
     * @throws InnerException 自定义的服务器内部异常
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("artist/add")
    public ArtistAddResp artistAdd(@RequestBody ArtistAddEvt evt)
        throws InnerException
    {
        LOGGER.info("--------新增艺人--------");
        LOGGER.info("新增艺人请求体ArtistAddEvt：{}", evt);
        ArtistAddResp addResp = artistService.addArtist(evt);
        return addResp;
    }
    
    /**
     * 
     * 删除艺人
     * <功能详细描述>
     * @param evt 删除艺人请求体
     * @return 删除艺人响应实体
     * @throws InnerException 自定义的服务器内部异常
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("artist/delete")
    public ArtistDeleteResp artistDelete(@RequestBody ArtistDeleteEvt evt)
        throws InnerException
    {
        LOGGER.info("--------删除艺人--------");
        LOGGER.info("删除艺人请求体ArtistDeleteEvt：{}", evt);
        
        return artistService.deleteArtist(evt);
    }
    
}
