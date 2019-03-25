package com.lunzn.artistmanager.service;

import com.lunzn.artistmanager.controller.req.UploadFileEvt;
import com.lunzn.artistmanager.controller.resp.UploadFileResp;
import com.lunzn.artistmanager.system.exception.InnerException;

/**
 * 
 * 文件服务
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年11月2日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface FileService
{
    /**
     * 
     * 上传文件到文件服务器
     * <功能详细描述>
     * @param evt 文件上传请求体
     * @return 文件上传响应实体
     * @throws InnerException 服务器内部异常
     * @see [类、类#方法、类#成员]
     */
    UploadFileResp upload(UploadFileEvt evt)
        throws InnerException;
}
