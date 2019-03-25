package com.lunzn.artistmanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * operationApplication
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年10月23日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@SpringBootApplication
public class OperationApplication
{
    private static final Logger LOGGER = LoggerFactory.getLogger(OperationApplication.class);
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @see [类、类#方法、类#成员]
     */
    public void ss()
    {
        
    }
    
    /**
     * 
     * 启动主函数
     * <功能详细描述>
     * @param args 参数
     * @see [类、类#方法、类#成员]
     */
    public static void main(String[] args)
    {
        SpringApplication.run(OperationApplication.class, args);
        LOGGER.info("============= SpringBoot Start Success =============");
    }
}
