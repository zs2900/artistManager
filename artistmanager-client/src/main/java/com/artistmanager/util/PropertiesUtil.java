package com.artistmanager.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.util.IOUtils;

/**
 * classpath路径下的配置文件读取工具类
 * <功能详细描述>
 * 
 * @author  longguofei
 * @version  [版本号, 2017年11月21日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public final class PropertiesUtil
{
    
    /**
     * 运行日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesUtil.class);
    
    /**
     * 单例对象
     */
    private static final PropertiesUtil INSTANCE = new PropertiesUtil();
    
    /**
     * <默认构造函数>
     */
    private PropertiesUtil()
    {
    }
    
    /**
     * 实例化方法
     * <功能详细描述>
     * @return PropertiesUtil
     * @see [类、类#方法、类#成员]
     */
    public static PropertiesUtil getInstance()
    {
        return INSTANCE;
    }
    
    /**
     * 获取配置信息
     * <功能详细描述>
     * @param fileName 配置文件名
     * @param key 配置key
     * @return String 值
     * @see [类、类#方法、类#成员]
     */
    public String getProperty(String fileName, String key)
    {
        URL url;
        InputStream inStream = null;
        try
        {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            url = classLoader.getResource(fileName);
            if (null == url)
            {
                LOGGER.info("url is null");
                return null;
            }
            inStream = url.openStream();
            Properties prop = new Properties();
            prop.load(inStream);
            String value = prop.getProperty(key);
            return value;
        }
        catch (MalformedURLException e)
        {
            LOGGER.error("PropertiesUtil getProperty error,key:{}", key);
        }
        catch (IOException e)
        {
            LOGGER.error("PropertiesUtil getProperty error,key:{}", key);
        }
        finally
        {
            IOUtils.close(inStream);
        }
        
        return null;
    }
}
