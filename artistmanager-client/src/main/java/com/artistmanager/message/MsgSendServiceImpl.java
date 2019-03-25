package com.artistmanager.message;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.SortedMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.IOUtils;
import com.artistmanager.util.PropertiesUtil;
import com.lunzn.artistmanager.constants.NumberKeys;
import com.lunzn.artistmanager.enums.ResultCode;
import com.lunzn.artistmanager.message.annotations.Location;
import com.lunzn.artistmanager.message.reqest.BaseRequestData;
import com.lunzn.artistmanager.message.response.BaseResponse;
import com.lunzn.artistmanager.system.exception.InnerException;
import com.lunzn.artistmanager.util.StringUtil;

/**
 * 消息发送接口
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  ouyangyijian
 * @version  [版本号, 2017年11月27日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MsgSendServiceImpl
{
    /**
     * 运行日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MsgSendServiceImpl.class);
    
    /**
     * 配置文件名称
     */
    private static final String CLIENT_CONFIG_FILENAME = "remote-server-config.properties";
    
    /**
     * URL分割符
     */
    private static final String URL_SPLIT = "/";
    
    private String serverUrl;
    
    @SuppressWarnings("unused")
    private String nodeId;
    
    @SuppressWarnings("unused")
    private String version;
    
    @SuppressWarnings("unused")
    private String signType;
    
    private String key = "contentservice";
    
    /**
     * 
     * <默认构造函数>
     */
    public MsgSendServiceImpl()
    {
        serverUrl = PropertiesUtil.getInstance().getProperty(CLIENT_CONFIG_FILENAME, key + "_server_url");
        nodeId = PropertiesUtil.getInstance().getProperty(CLIENT_CONFIG_FILENAME, key + "_node_id");
        version = PropertiesUtil.getInstance().getProperty(CLIENT_CONFIG_FILENAME, key + "_version");
        signType = PropertiesUtil.getInstance().getProperty(CLIENT_CONFIG_FILENAME, key + "_sign_type");
    }
    
    /**
     * 通过请求参数map生成字符串（用来生成签名）
     * <一句话功能简述>
     * <功能详细描述>
     * @param params
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unused")
    private static String paramToString(SortedMap<String, Object> params)
    {
        StringBuilder sb = new StringBuilder();
        //把值拼接起来
        for (Entry<String, Object> entry : params.entrySet())
        {
            Object value = entry.getValue();
            if (value != null)
            {
                sb.append(entry.getKey());
                sb.append(entry.getValue());
            }
        }
        return sb.toString();
    }
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param req 请求
     * @param respClass 响应类
     * @param <T> 响应类
     * @return 响应消息
     * @throws InnerException 
     * @see [类、类#方法、类#成员]
     */
    public <T extends BaseResponse> T sendMsg(BaseRequestData req, Class<T> respClass)
        throws InnerException
    {
        // 获取注解调用配置
        return send(req, respClass);
    }
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param evt 请求
     * @param respClass 响应类
     * @param <T> 响应类
     * @return 响应消息
     * @throws InnerException 
     * @see [类、类#方法、类#成员]
     */
    public <T extends BaseResponse> T sendLzMsg(BaseRequestData evt, Class<T> respClass)
        throws InnerException
    {
        // 获取注解调用配置
        return send(evt, respClass);
    }
    
    private <T> T send(Object evt, Class<T> respClass)
        throws InnerException
    {
        // 获取注解调用配置
        Location location = evt.getClass().getAnnotation(Location.class);
        if (null == location)
        {
            throw new InnerException("Missed location config.");
        }
        
        CloseableHttpClient client = null;
        String msg = "";
        try
        {
            client = HttpClients.createDefault();
            synchronized (this)
            {
                if (!StringUtil.isEmpty(serverUrl) && !StringUtil.isEmpty(location.uri()))
                {
                    
                    if (serverUrl.endsWith(URL_SPLIT) && location.uri().startsWith(URL_SPLIT))
                    {
                        serverUrl = serverUrl.substring(0, serverUrl.length() - 1);
                    }
                    else if (!serverUrl.endsWith(URL_SPLIT) && !location.uri().startsWith(URL_SPLIT))
                    {
                        serverUrl = serverUrl + URL_SPLIT;
                    }
                }
            }
            
            StringBuilder urlBuilder = new StringBuilder();
            urlBuilder.append(serverUrl);
            urlBuilder.append(location.uri());
            
            HttpPost post = new HttpPost(urlBuilder.toString());
            LOGGER.info(post.toString());
            //设置头信息
            post.setHeader("Content-Type", "application/json;charset=UTF-8");
            RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(NumberKeys.NUM_60000)
                .setConnectTimeout(NumberKeys.NUM_60000)
                .build();
            post.setConfig(requestConfig);
            
            /*将请求实体转成json格式*/
            String request = JSONObject.toJSONString(evt);
            StringEntity formEntity = new StringEntity(request, "UTF-8");
            formEntity.setContentType("application/json;charset=utf-8");
            post.setEntity(formEntity);
            
            //设置Body体
            HttpResponse res = client.execute(post);
            
            int resultCode = res.getStatusLine().getStatusCode();
            HttpEntity entity = res.getEntity();
            String result = EntityUtils.toString(entity);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (resultCode == HttpStatus.SC_OK)
            {
                return JSONObject.toJavaObject(jsonObject, respClass);
            }
            else
            {
                msg = "httpStatusCode=" + resultCode;
                LOGGER.error("Http request error, [statusCode:{}, msg:{}]", resultCode, result);
            }
        }
        catch (IOException e)
        {
            InnerException inner = new InnerException(e.getMessage());
            inner.setErrorCode(ResultCode.INNER_ERROR.getResultCode());
            LOGGER.error("send msg error,", e);
            throw inner;
        }
        finally
        {
            IOUtils.close(client);
        }
        throw new InnerException(ResultCode.INNER_ERROR.getResultCode(), msg);
    }
}
