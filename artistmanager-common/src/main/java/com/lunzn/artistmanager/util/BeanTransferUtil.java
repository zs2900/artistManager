package com.lunzn.artistmanager.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;
import com.lunzn.artistmanager.constants.NumberKeys;

/**
 * bean与bean之间属性转换的工具类
 * <功能详细描述>
 * 
 * @author  longguofei
 * @version  [版本号, 2017年11月6日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public final class BeanTransferUtil
{
    /**
     * 运行日志记录器
     */
    private static final Logger LOG = LoggerFactory.getLogger(BeanTransferUtil.class);
    
    /**
     * 单例实体对象
     */
    private static final BeanTransferUtil INSTANCE = new BeanTransferUtil();
    
    /**
     * <默认构造函数>
     */
    private BeanTransferUtil()
    {
    }
    
    /**
     * 实例化方法（单例）
     * <功能详细描述>
     * @return BeanTransferUtil
     * @see [类、类#方法、类#成员]
     */
    public static BeanTransferUtil getInstance()
    {
        return INSTANCE;
    }
    
    /**
     * 数组转List
     * <功能详细描述> 
     * @param arr 数组
     * @param <T> 范型
     * @return List<T> list范型
     * @see [类、类#方法、类#成员]
     */
    public <T> List<T> toList(T[] arr)
    {
        if (null == arr || arr.length == NumberKeys.NUM_0)
        {
            return Lists.newArrayList();
        }
        
        return Lists.newArrayList(arr);
    }
    
    /**
     * List转为Map
     * <p>
     * 一个key对应一个item
     * </p>
     * @param list 数据
     * @param getMethodName 唯一键
     * @param vClass 数据类型
     * @param <K> key范型
     * @param <V> value范型
     * @return Map<K, V>
     * @see [类、类#方法、类#成员]
     */
    public <K, V> Map<K, V> bulidOneToOneMap(List<V> list, String getMethodName, Class<V> vClass)
    {
        if (StringUtils.isEmpty(getMethodName))
        {
            LOG.info("没有指定key的取值范围");
            return null;
            
        }
        Map<K, V> map = new HashMap<K, V>();
        if (list != null)
        {
            try
            {
                Method getMethod = vClass.getMethod(getMethodName);
                for (int i = 0; i < list.size(); i++)
                {
                    V value = list.get(i);
                    @SuppressWarnings("unchecked")
                    K key = (K)getMethod.invoke(list.get(i));
                    map.put(key, value);
                }
            }
            catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e)
            {
                LOG.info("反射取值发生异常");
                return null;
            }
        }
        
        return map;
    }
    
    /**
     * List转为Map
     * <p>
     * 一个key对应多个item
     * </p>
     * @param list 数据
     * @param getMethodName 唯一键
     * @param vClass 数据类型
     * @param <K> key范型
     * @param <V> value范型
     * @return Map<K, List<V>> 范型
     * @see [类、类#方法、类#成员]
     */
    public <K, V> Map<K, List<V>> bulidOneToManyMap(List<V> list, String getMethodName, Class<V> vClass)
    {
        if (StringUtils.isEmpty(getMethodName))
        {
            LOG.info("没有指定key的取值范围");
            return null;
            
        }
        Map<K, List<V>> map = new HashMap<K, List<V>>();
        if (list != null)
        {
            try
            {
                Method getMethod = vClass.getMethod(getMethodName);
                for (int i = 0; i < list.size(); i++)
                {
                    V item = list.get(i);
                    @SuppressWarnings("unchecked")
                    K key = (K)getMethod.invoke(list.get(i));
                    if (!map.containsKey(key))
                    {
                        map.put(key, new ArrayList<V>());
                    }
                    map.get(key).add(item);
                }
            }
            catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e)
            {
                LOG.error("反射取值发生异常");
                return null;
            }
        }
        
        return map;
    }
    
    /**
     * List克隆
     * <功能详细描述>
     * @param srcList 原List
     * @param  <T> 返回范型
     * @return 克隆List
     * @throws IOException IO异常
     * @throws ClassNotFoundException class缺失异常
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> clone(List<T> srcList)
        throws IOException, ClassNotFoundException
    {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(srcList);
        
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        List<T> destList = (List<T>)in.readObject();
        
        return destList;
    }
}
