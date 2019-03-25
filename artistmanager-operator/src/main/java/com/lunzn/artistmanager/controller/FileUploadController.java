package com.lunzn.artistmanager.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lunzn.artistmanager.controller.resp.UploadFileResp;
import com.lunzn.artistmanager.service.FileUploadService;
import com.lunzn.artistmanager.system.exception.InnerException;

/**
 * 文件上传Controller
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
    private FileUploadService fileUploadService;
    
    /**
     * 
     * 上传文件
     * <功能详细描述>
     * @param files 上传文件请求体
     * @param request 上传文件请求
     * @return 上传文件响应实体
     * @throws IOException 文件IO异常
     * @throws InnerException 自定义的服务器内部异常
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("artist/fileUpload")
    public String uploadFile(@RequestParam MultipartFile files, HttpServletRequest request)
        throws InnerException, IOException
    {
        UploadFileResp resp = fileUploadService.fileUpload(files, request);
        LOGGER.info("response:{}", resp);
        return resp.toString();
    }
}
