package com.lunzn.artistmanager.controller.req;

import com.lunzn.artistmanager.message.annotations.Location;
import com.lunzn.artistmanager.message.reqest.BaseRequestData;
import com.lunzn.artistmanager.model.FileType;

/**
 * 
 * 文件上传
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年10月29日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Location(module = "", uri = "/artist/fileUpload")
public class UploadFileEvt extends BaseRequestData
{
    private String uploadFile;
    
    private FileType fileType;
    
    public String getUploadFile()
    {
        return uploadFile;
    }
    
    public void setUploadFile(String uploadFile)
    {
        this.uploadFile = uploadFile;
    }
    
    public FileType getFileType()
    {
        return fileType;
    }
    
    public void setFileType(FileType fileType)
    {
        this.fileType = fileType;
    }
    
}
