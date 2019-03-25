package com.lunzn.artistmanager.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.IOUtils;
import com.lunzn.artistmanager.constants.NumberKeys;
import com.lunzn.artistmanager.enums.ResultCode;
import com.lunzn.artistmanager.system.exception.InnerException;
import com.mysql.jdbc.StringUtils;

/**
 * 请求参数处理工具类
 * <功能详细描述>
 * 
 * @author  longguofei
 * @version  [版本号, 2017年10月24日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public final class ParameterUtil
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ParameterUtil.class);
    
    /**
     * POST
     */
    private static final String METHOD_POST = "POST";
    
    /**
     * 初始化对象
     */
    private static final ParameterUtil INSTANCE = new ParameterUtil();
    
    /**
     * <默认构造函数>
     */
    private ParameterUtil()
    {
    }
    
    /**
     * 实例化对象方法
     * <功能详细描述>
     * @return ParameterUtil
     * @see [类、类#方法、类#成员]
     */
    public static ParameterUtil newInstance()
    {
        return INSTANCE;
    }
    
    /**
     * 采集参数
     * <将请求参数收集，按Map格式返回>
     * @param request 请求体
     * @return Map<String, String> 结果
     * @throws IOException IO异常
     * @see [类、类#方法、类#成员]
     */
    public Map<String, String> collectParamMap(HttpServletRequest request)
        throws IOException
    {
        Map<String, String> map = new HashMap<String, String>();
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet())
        {
            String paramKey = entry.getKey();
            String paramVal = entry.getValue()[0];
            map.put(paramKey, paramVal);
        }
        return map;
    }
    
    /**
     * 采集参数
     * <将请求参数收集，按Json格式返回>
     * @param request 请求体
     * @return JSONObject 结果
     * @throws InnerException 内部异常
     * @see [类、类#方法、类#成员]
     */
    public JSONObject collectParamJson(HttpServletRequest request)
        throws InnerException
    {
        JSONObject json = null;
        // 如果是post的请求，要从流中读取数据
        if (METHOD_POST.equals(request.getMethod()))
        {
            if (ServletFileUpload.isMultipartContent(request))
            {
                json = new JSONObject();
                json.put("serverId", "1");
                json.put("requestTime", "20171111011011");
                json.put("version", "1.0");
                json.put("data", getFiles(request));
            }
            else
            {
                json = JSONObject.parseObject(getRequestPayload(request));
                
            }
            if (null != json && !json.isEmpty())
            {
                return json;
            }
        }
        json = new JSONObject();
        
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet())
        {
            String paramKey = entry.getKey();
            String paramVal = entry.getValue()[0];
            json.put(paramKey, paramVal);
        }
        return json;
    }
    
    private JSONObject getFiles(HttpServletRequest request)
        throws InnerException
    {
        // 读取流
        //创建ServletFileUpload实例
        ServletFileUpload fileUpload = new ServletFileUpload();
        FileItemIterator iter = null;
        FileItemStream item = null;
        InputStream is = null;
        //迭代取出
        JSONObject json = new JSONObject();
        try
        {
            //解析request请求 返回FileItemStream的iterator实例
            iter = fileUpload.getItemIterator(request);
            JSONArray arrJson = new JSONArray();
            json.put("files", arrJson);
            while (iter.hasNext())
            {
                item = iter.next();//获取文件流
                if (!item.isFormField())
                {
                    //为什么要这么取出这个流呢，因为request.getInputStream()中，表单提交上来的不仅仅包含了文件，还带有参数，就算不带参数，也还有request中本身的一些其他东西（ps：没有研究过，但是试过不带参数拿到的流也是不对的。）直接拿会导致读取出来的文件变大，图片读取失败
                    //这里主要针对图片来写的，因为我用到的是转成图片，获取图片属性。
                    is = item.openStream();
                    if (is.available() > 0)
                    {
                        String name = item.getName();
                        //获取路径 
                        String serverRoot = request.getSession().getServletContext().getRealPath("/");
                        String dir = SpringContextUtil.getInstance().getUploadFileDir();
                        String url = SpringContextUtil.getInstance().getServerUrl();
                        String suffix = "";
                        //如果是红外文件，则没有后缀名，例如：data_stb_nb_yk201_inphic_20131125,特殊处理
                        if (name.contains("."))
                        {
                            suffix = name.substring(name.lastIndexOf("."));
                        }
                        Map<String, Object> map = null;
                        // 判断是否是图片
                        if (suffix.matches(SpringContextUtil.getInstance().getImgSuffix()))
                        {
                            map =
                                FileUtil.saveImg(is, serverRoot + dir, System.currentTimeMillis() + suffix, url + dir);
                            
                        }
                        else if (StringUtils.isEmptyOrWhitespaceOnly(suffix))
                        {
                            //红外文件没有后缀名单独处理---保存名为：始文件名+当前秒
                            map = FileUtil.saveFile(is,
                                serverRoot + dir,
                                name + "_" + System.currentTimeMillis(),
                                url + dir);
                            
                        }
                        else
                        {
                            //其他文件此分支处理
                            map =
                                FileUtil.saveFile(is, serverRoot + dir, System.currentTimeMillis() + suffix, url + dir);
                        }
                        map.put("suffix", suffix);
                        arrJson.add(map);
                    }
                }
            }
        }
        catch (IOException | FileUploadException e)
        {
            LOGGER.error("读取请求参数异常" + e);
            throw new InnerException(ResultCode.INNER_ERROR.getResultCode(), ResultCode.INNER_ERROR.getResultMsg(), e);
        }
        finally
        {
            IOUtils.close(is);
        }
        return json;
    }
    
    // post请求读取数据
    private String getRequestPayload(HttpServletRequest req)
    {
        StringBuilder sb = new StringBuilder();
        try
        {
            BufferedReader reader = req.getReader();
            
            char[] buff = new char[NumberKeys.NUM_1024];
            int len;
            while ((len = reader.read(buff)) != -1)
            {
                sb.append(buff, 0, len);
            }
        }
        catch (IOException e)
        {
            LOGGER.error("读取请求参数异常" + e);
        }
        return sb.toString();
    }
}
