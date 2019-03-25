package com.lunzn.artistmanager.util.thread;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lunzn.artistmanager.util.SpringContextUtil;

/**
 * 异步反射调用帮助类
 * 刷新缓存调用
 * 
 * @author  yangshu
 * @version  [版本号, 2017年11月15日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class ThreadHelper
{
    /**
     * 日志记录器
     */
    private static final Logger LOG = LoggerFactory.getLogger(ThreadHelper.class);
    
    /**
     * 任务器
     */
    private static final ExecutorService TACKEXCUTOR = Executors.newFixedThreadPool(30);
    
    /** 
     * 异步刷新缓存
     * <功能详细描述>
     * @param clazz 刷新缓存的类
     * @param methodName 方法
     * @param params 参数
     * @see [类、类#方法、类#成员]
     */
    public static void invokeCache(final Class<?> clazz, final String methodName, final Object... params)
    {
        LOG.info("invoke class:{} methodname :{} params:", clazz.getName(), methodName, params);
        TACKEXCUTOR.execute(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Method[] methods = clazz.getDeclaredMethods();
                    Method callMethod = null;
                    for (Method method : methods)
                    {
                        if (method.getName().equals(methodName))
                        {
                            callMethod = method;
                            break;
                        }
                    }
                    if (callMethod != null)
                    {
                        callMethod.invoke(SpringContextUtil.getBean(clazz), params);
                    }
                }
                catch (Exception e)
                {
                    LOG.error(e.getMessage());
                    e.printStackTrace();
                }
            }
        });
        
    }
}
