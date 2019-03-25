package com.lunzn.artistmanager.cache.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lunzn.artistmanager.cache.Redis;
import com.lunzn.artistmanager.cache.service.RedisService;
import com.lunzn.artistmanager.constants.NumberKeys;
import com.lunzn.artistmanager.util.StringUtil;

/**
 * redis操作实现类
 * <功能详细描述>
 * 
 * @author  longguofei
 * @version  [版本号, 2017年11月1日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service
public class RedisServiceImpl implements RedisService
{
    @Autowired
    private Redis redis;
    
    @Autowired
    private StringRedisTemplate template;
    
    @Override
    public <T> T get(String key, Class<T> clazz)
    {
        if (null == key)
        {
            return null;
        }
        if (template.hasKey(key))
        {
            String value = template.opsForValue().get(key);
            // 将json串转为相应的对象返回
            return JSON.parseObject(value, clazz);
        }
        return null;
    }
    
    @Override
    public <T> List<T> mget(String key, Class<T> clazz)
    {
        if (null == key)
        {
            return null;
        }
        if (template.hasKey(key))
        {
            String value = template.opsForValue().get(key);
            return JSONArray.parseArray(value, clazz);
        }
        return null;
    }
    
    @Override
    public <T> List<T> mget(Collection<String> keys, Class<T> clazz)
    {
        if (CollectionUtils.isEmpty(keys))
        {
            return null;
        }
        
        List<String> listStr = template.opsForValue().multiGet(keys);
        if (CollectionUtils.isEmpty(listStr))
        {
            return null;
        }
        
        List<T> list = new ArrayList<T>();
        for (String str : listStr)
        {
            if (!StringUtils.isEmpty(str))
            {
                T item = JSON.parseObject(str, clazz);
                list.add(item);
            }
        }
        return CollectionUtils.isEmpty(list) ? null : list;
    }
    
    @Override
    public boolean set(String key, Object obj)
    {
        // 使用默认的缓存失效时间
        return set(key, obj, redis.getEffectiveTime());
    }
    
    @Override
    public boolean set(String key, Object obj, int timeout)
    {
        if (null == key)
        {
            return false;
        }
        // 将对象转为json串放入缓存
        String value = JSON.toJSONString(obj);
        template.opsForValue().set(key, value);
        // 再设置时效
        if (timeout <= NumberKeys.NUM_0)
        {
            return true;
        }
        if (template.hasKey(key))
        {
            template.expire(key, timeout, TimeUnit.SECONDS);
            System.out.println("缓存成功" + key);
        }
        return true;
    }
    
    @Override
    public <T> void mset(Map<String, T> map)
    {
        // 使用默认的缓存失效时间
        mset(map, redis.getEffectiveTime());
    }
    
    @Override
    public <T> void mset(Map<String, T> map, int timeout)
    {
        // 先批量缓存数据
        if (CollectionUtils.isEmpty(map))
        {
            return;
        }
        
        Map<String, String> cacheMap = new HashMap<String, String>();
        for (Map.Entry<String, T> entry : map.entrySet())
        {
            String key = entry.getKey();
            String value = JSON.toJSONString(entry.getValue());
            cacheMap.put(key, value);
        }
        if (CollectionUtils.isEmpty(cacheMap))
        {
            return;
        }
        template.opsForValue().multiSet(cacheMap);
        // 再为此次缓存设置时效
        if (timeout <= NumberKeys.NUM_0)
        {
            return;
        }
        for (Map.Entry<String, T> entry : map.entrySet())
        {
            String key = entry.getKey();
            if (template.hasKey(key))
            {
                template.expire(key, timeout, TimeUnit.SECONDS);
            }
        }
    }
    
    @Override
    public void del(String... keys)
    {
        if (null == keys || keys.length == 0)
        {
            return;
        }
        if (keys.length == NumberKeys.NUM_1)
        {
            // 单个删除
            template.delete(keys[0]);
            return;
        }
        @SuppressWarnings("unchecked")
        List<String> keyList = CollectionUtils.arrayToList(keys);
        // 批量删除
        template.delete(keyList);
    }
    
    @Override
    public void mdel(Collection<String> keys)
    {
        if (CollectionUtils.isEmpty(keys))
        {
            return;
        }
        // 批量删除
        template.delete(keys);
    }
    
    @Override
    public void rename(String oldKey, String newKey)
    {
        // 老key必须存在，且老key与新key不相同才执行rename
        if (null != oldKey && null != newKey && !StringUtil.isEqual(oldKey, newKey) && template.hasKey(oldKey))
        {
            template.rename(oldKey, newKey);
        }
    }
    
    @Override
    public Set<String> keys(String pattern)
    {
        // 不支持空值匹配
        if (StringUtils.isEmpty(pattern))
        {
            return null;
        }
        return template.keys(pattern);
    }
    
    @Override
    public boolean hasKey(String key)
    {
        if (StringUtils.isEmpty(key))
        {
            return false;
        }
        return template.hasKey(key);
    }
    
}
