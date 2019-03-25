package com.lunzn.artistmanager.serviceimpl;

import java.io.File;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.IOUtils;
import com.lunzn.artistmanager.constants.NumberKeys;
import com.lunzn.artistmanager.controller.req.UploadFileEvt;
import com.lunzn.artistmanager.controller.resp.UploadFileResp;
import com.lunzn.artistmanager.enums.ResultCode;
import com.lunzn.artistmanager.service.FileService;
import com.lunzn.artistmanager.system.exception.InnerException;

/**
 * 
 * 文件服务器util文件
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年11月2日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service
public class FileServiceImpl implements FileService
{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(FileServiceImpl.class);
    
    //文件服务uri
    @Value("${file.service.uri}")
    private String fileServiceUri;
    
    //文件服务uri
    @Value("${file.service.interface}")
    private String fileServiceInterface;
    
    @Value("${server.upload.dir}")
    private String uploadDir;
    
    //艺人头像图片存储地址
    @Value("${artist.dir}")
    private String artistDir;
    
    /**
     * 上传文件
     * <功能详细描述>
     * @param url 上传的url
     * @param uploadFile 文件
     * @param fileName 文件名
     * @param dir 目录
     * @return 结果
     * @throws Exception 异常
     * @see [类、类#方法、类#成员]
     */
    private JSONObject uploadFile(UploadFileEvt evt)
        throws Exception
    {
        CloseableHttpClient client = HttpClients.createDefault();
        try
        {
            LOGGER.info("Upload file begin, [evt:{}]", evt);
            long startTime = System.currentTimeMillis();
            //服务url
            StringBuilder builder = new StringBuilder(fileServiceUri);
            if (!fileServiceUri.endsWith("/") && !fileServiceInterface.startsWith("/"))
            {
                builder.append('/');
            }
            //接口名
            builder.append(fileServiceInterface);
            LOGGER.info("URL：{}", builder.toString());
            HttpPost post = new HttpPost(builder.toString());
            // 设置超时时间
            RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(NumberKeys.NUM_60000)
                .setSocketTimeout(NumberKeys.NUM_60000)
                .build();
            post.setConfig(config);
            String dir = artistDir;
            File file = new File(evt.getUploadFile());
            HttpEntity reqEntity = MultipartEntityBuilder.create()
                .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                .addBinaryBody("upload", file)
                .addTextBody("fileName", file.getName(), ContentType.create("text/html", "utf-8"))
                .addTextBody("filetype", dir, ContentType.create("text/html", "utf-8"))
                .build();
            
            post.setEntity(reqEntity);
            // 设置要上传的参数
            HttpResponse response = client.execute(post);
            // 获得返回CODE
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
            JSONObject json = JSON.parseObject(result);
            LOGGER.info("Upload file end, [spendTime:{}ms, result:{}]", (System.currentTimeMillis() - startTime), json);
            return json;
        }
        finally
        {
            IOUtils.close(client);
        }
    }
    
    @Override
    public UploadFileResp upload(UploadFileEvt evt)
        throws InnerException
    {
        try
        {
            JSONObject json = uploadFile(evt);
            UploadFileResp resp = new UploadFileResp();
            if (json.getInteger("code") == 0)
            {
                resp.setUrl(json.getJSONObject("info").getString("path"));
            }
            resp.setRetCode(json.getInteger("code"));
            resp.setRetMsg(json.getString("msg"));
            return resp;
        }
        catch (Exception e)
        {
            throw new InnerException(ResultCode.INNER_ERROR.getResultCode(), ResultCode.INNER_ERROR.getResultMsg(), e);
        }
    }
}
