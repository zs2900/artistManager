package com.lunzn.artistmanager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lunzn.artistmanager.controller.req.UploadFileEvt;
import com.lunzn.artistmanager.controller.resp.UploadFileResp;
import com.lunzn.artistmanager.service.FileService;
import com.lunzn.artistmanager.system.exception.InnerException;

/**
 * 文件上传
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年10月29日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@RestController
public class FileUploadController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);
    
    @Autowired
    private FileService fileService;
    
    /**
     * 
     * 文件上传
     * <功能详细描述>
     * @param evt 文件上传请求体
     * @return 文件上传响应实体
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/artist/fileUpload")
    public UploadFileResp uploadFile(@RequestBody UploadFileEvt evt)
    {
        LOGGER.info("--------上传文件--------");
        try
        {
            return fileService.upload(evt);
        }
        catch (InnerException e)
        {
            UploadFileResp resp = new UploadFileResp();
            resp.setRetCode(e.getErrorCode());
            resp.setRetMsg(e.getErrorDesc());
            return resp;
        }
    }
}
