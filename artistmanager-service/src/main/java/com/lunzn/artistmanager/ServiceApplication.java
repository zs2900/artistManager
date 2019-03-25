package com.lunzn.artistmanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * serviceApplication启动类
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年10月23日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@SpringBootApplication
@EnableTransactionManagement
public class ServiceApplication
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceApplication.class);
    
    /**
     * 
     * 解决check style问题
     * <功能详细描述>
     * @see [类、类#方法、类#成员]
     */
    public void info()
    {
        
    }
    
    /**
     * 
     * 启动main
     * <功能详细描述>
     * @param args 参数
     * @see [类、类#方法、类#成员]
     */
    public static void main(String[] args)
    {
        SpringApplication.run(ServiceApplication.class, args);
        LOGGER.info("============= SpringBoot Start Success =============");
    }
}
