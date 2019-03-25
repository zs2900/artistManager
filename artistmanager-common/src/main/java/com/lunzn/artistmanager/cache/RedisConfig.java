package com.lunzn.artistmanager.cache;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.JedisPoolConfig;

/**
 * redis配置
 * <功能详细描述>
 * 
 * @author  longguofei
 * @version  [版本号, 2017年10月23日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport
{
    /**
     * redis连接配置信息
     */
    @Autowired
    private Redis redis;
    
    /**
     * redis pool配置信息
     */
    @Autowired
    private RedisPool redisPool;
    
    /**
     * key值生成器
     * @return KeyGenerator key值生成器
     * @see [类、类#方法、类#成员]
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator()
    {
        return new KeyGenerator()
        {
            @Override
            public Object generate(Object target, Method method, Object... params)
            {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                if (null == params || params.length == 0)
                {
                    return sb.toString();
                }
                for (Object obj : params)
                {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }
    
    /**
     * redis连接配置
     * 
     * @return JedisConnectionFactory redis连接工厂
     * @see [类、类#方法、类#成员]
     */
    @Bean
    public JedisConnectionFactory redisConnectionFactory()
    {
        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
        
        redisConnectionFactory.setHostName(redis.getHost());
        redisConnectionFactory.setPassword(redis.getPassword());
        redisConnectionFactory.setPort(redis.getPort());
        redisConnectionFactory.setDatabase(redis.getDatabase());
        redisConnectionFactory.setTimeout(redis.getTimeout());
        
        JedisPoolConfig redisPoolConfig = new JedisPoolConfig();
        redisPoolConfig.setMaxIdle(redisPool.getMaxIdle());
        redisPoolConfig.setMinIdle(redisPool.getMinIdle());
        redisPoolConfig.setMaxWaitMillis(redisPool.getMaxWaitMillis());
        
        redisConnectionFactory.setPoolConfig(redisPoolConfig);
        
        return redisConnectionFactory;
    }
    
    /**
     * 覆盖父类的RedisTemplate
     * <解决Spring Redis默认使用JDK进行序列化和反序列化，因此被缓存对象需要实现java.io.Serializable接口，否则缓存出错问题>
     * 
     * @param factory redis连接工厂
     * @return RedisTemplate 
     * @see [类、类#方法、类#成员]
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory)
    {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer =
            new Jackson2JsonRedisSerializer<Object>(Object.class);
        jackson2JsonRedisSerializer.setObjectMapper(mapper);
        
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        
        return template;
    }
    
    /**
     * 覆盖父类的缓存管理
     * <功能详细描述>
     * @param redisTemplate redis操作子类
     * @return CacheManager
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("rawtypes")
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate)
    {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        // 默认失效时长
        cacheManager.setDefaultExpiration(redis.getEffectiveTime());
        
        return cacheManager;
    }
}
