package com.lunzn.artistmanager.serviceimpl;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.artistmanager.client.FileUploadClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lunzn.artistmanager.controller.req.UploadFileEvt;
import com.lunzn.artistmanager.controller.resp.UploadFileResp;
import com.lunzn.artistmanager.enums.ResultCode;
import com.lunzn.artistmanager.model.FileType;
import com.lunzn.artistmanager.service.FileUploadService;
import com.lunzn.artistmanager.system.exception.InnerException;
import com.lunzn.artistmanager.util.FileUtil;

/**
 * 文件上传
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年10月29日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service
public class FileUploadServiceImpl implements FileUploadService
{
    @Override
    public UploadFileResp fileUpload(MultipartFile file, HttpServletRequest request)
        throws InnerException, IOException
    {
        if (!file.isEmpty())
        {
            String path = request.getSession().getServletContext().getRealPath("/upload");
            String fileName = file.getOriginalFilename();
            
            Integer index = fileName.lastIndexOf(".");
            String ext = fileName.substring(index);
            
            String url = "upload";
            
            Long date = System.currentTimeMillis();
            String imgName = date.toString() + ext;
            //文件上传到本地
            Map<String, Object> map = FileUtil.saveImg(file.getInputStream(), path, imgName, url);
            System.out.println(map.get("url"));
            
            UploadFileEvt evt = new UploadFileEvt();
            evt.setFileType(FileType.img);
            evt.setUploadFile(path + "/" + imgName);
            return FileUploadClient.uploadFile(evt);
        }
        else
        {
            UploadFileResp resp = new UploadFileResp();
            resp.setRetCode(ResultCode.FILE_IS_EMPTY.getResultCode());
            resp.setRetMsg(ResultCode.FILE_IS_EMPTY.getResultMsg());
            return resp;
        }
    }
    
}
