package com.lunzn.artistmanager.message.response;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import com.lunzn.artistmanager.message.annotations.HideWhenNull;

/**
 * 返回体处理的辅助类
 * <功能详细描述>
 * 
 * @author  longguofei
 * @version  [版本号, 2017年11月10日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public final class ResponseHelper
{
    /**
     * 实例对象
     */
    private static final ResponseHelper INSTANCE = new ResponseHelper();
    
    /**
     * 相关的注解
     */
    private static Set<String> annoNames;
    
    /**
     * <默认构造函数>
     */
    private ResponseHelper()
    {
    }
    
    /**
     * 获取实例化对象
     * <功能详细描述>
     * @return ResponseHelper 返回处理辅助类
     * @see [类、类#方法、类#成员]
     */
    public static ResponseHelper getInstance()
    {
        return INSTANCE;
    }
    
    //private static Logger logger = LoggerFactory.getLogger(ResponseUtil.class);
    
    static
    {
        annoNames = new HashSet<String>();
        annoNames.add(HideWhenNull.class.getName());
    }
    
    /**
     * 隐藏null值
     * <一句话功能简述>
     * <功能详细描述>
     * @param jsonStr json字符串
     * @param resp 返回体
     * @return String 字符串
     * @see [类、类#方法、类#成员]
     */
    public String hideNullValue(String jsonStr, IResponse resp)
    {
        if (null == jsonStr || null == resp)
        {
            return jsonStr;
        }
        // 获取字段注解配置
        Map<String, Annotation> fieldAnnotation = new HashMap<String, Annotation>();
        Field[] fields = resp.getClass().getDeclaredFields();
        for (Field field : fields)
        {
            Annotation[] annotations = field.getAnnotations();
            if (null == annotations || annotations.length == 0)
            {
                continue;
            }
            String fieldName = field.getName();
            for (Annotation annotation : annotations)
            {
                String annoName = annotation.annotationType().getName();
                if (annoNames.contains(annoName))
                {
                    fieldAnnotation.put(fieldName, annotation);
                }
            }
        }
        if (CollectionUtils.isEmpty(fieldAnnotation))
        {
            return jsonStr;
        }
        // 将不需要为空且不需要返回的节点剔除
        for (Map.Entry<String, Annotation> entry : fieldAnnotation.entrySet())
        {
            String fieldName = entry.getKey();
            StringBuffer start = new StringBuffer().append('"').append(fieldName).append('"').append(":null,");
            StringBuffer end = new StringBuffer().append(',').append('"').append(fieldName).append('"').append(":null");
            if (jsonStr.contains(start))
            {
                jsonStr = jsonStr.replace(start, "");
            }
            else
            {
                jsonStr = jsonStr.replace(end, "");
            }
        }
        return jsonStr;
    }
}
