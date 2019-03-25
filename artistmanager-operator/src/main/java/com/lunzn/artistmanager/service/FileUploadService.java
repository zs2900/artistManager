package com.lunzn.artistmanager.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.lunzn.artistmanager.controller.resp.UploadFileResp;
import com.lunzn.artistmanager.system.exception.InnerException;

/**
 * 文件上传service
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年10月29日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface FileUploadService
{
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param file 文件源
     * @param request request
     * @return 文件上传响应实体
     * @throws InnerException 自定义的服务器内部异常
     * @throws IOException 文件IO异常
     * @see [类、类#方法、类#成员]
     */
    UploadFileResp fileUpload(MultipartFile file, HttpServletRequest request)
        throws InnerException, IOException;
}
