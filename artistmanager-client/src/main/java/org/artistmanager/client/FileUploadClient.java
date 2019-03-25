package org.artistmanager.client;

import com.lunzn.artistmanager.controller.req.UploadFileEvt;
import com.lunzn.artistmanager.controller.resp.UploadFileResp;
import com.lunzn.artistmanager.system.exception.InnerException;

/**
 * 文件上传接口
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年10月29日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class FileUploadClient extends AbstractContentServiceClient
{
    /**
     * 
     * 上传文件
     * <功能详细描述>
     * @param evt 上传文件请求体
     * @return 上传文件上传响应实体
     * @throws InnerException 自定义的服务器内部异常
     * @see [类、类#方法、类#成员]
     */
    public static UploadFileResp uploadFile(UploadFileEvt evt)
        throws InnerException
    {
        return send(evt, UploadFileResp.class);
    }
}
