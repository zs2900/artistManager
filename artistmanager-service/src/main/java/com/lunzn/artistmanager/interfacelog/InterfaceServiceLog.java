package com.lunzn.artistmanager.interfacelog;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.lunzn.artistmanager.util.IPAddressUtil;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年11月5日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Aspect
@Component
public class InterfaceServiceLog
{
    
    private static final Logger LOGGER = LoggerFactory.getLogger("Interface.Service.Log");
    
    private InterfaceLog interfaceLog = new InterfaceLog();
    
    /**
     * 
     * 日志切入点
     * <功能详细描述>
     * @see [类、类#方法、类#成员]
     */
    @Pointcut("execution(public * com.lunzn.artistmanager.controller.*Controller.*(..))") //两个..代表所有子目录，最后括号里的两个..代表所有参数
    public void logPointCut()
    {
    }
    
    /**
     * 
     * 切入点前
     * <功能详细描述>
     * @param joinPoint 切入点
     * @throws Throwable 
     * @see [类、类#方法、类#成员]
     */
    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint)
    {
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        
        interfaceLog.setRequestURL(request.getRequestURL().toString());
        interfaceLog.setIp(IPAddressUtil.getIpAddress(request));
        interfaceLog.setRequestMethod(request.getMethod());
        interfaceLog.setRequestData(Arrays.toString(joinPoint.getArgs()));
        interfaceLog.setInterfaceName(joinPoint.getSignature().getName());
        
    }
    
    /**
     * 
     * 切入点后执行
     * <功能详细描述>
     * @param ret 目标执行的结果
     * @throws Throwable 异常
     * @see [类、类#方法、类#成员]
     */
    @AfterReturning(returning = "ret", pointcut = "logPointCut()") // returning的值和doAfterReturning的参数名一致
    public void doAfterReturning(Object ret)
        throws Throwable
    {
        interfaceLog.setResponseData(ret.toString());
        LOGGER.info("起始时间: {}  请求地址 : {}  IP: {}  请求接口名称: {}  请求参数 : {}  结束时间: {}  返回值: {}  耗时: {}",
            interfaceLog.getStartTime(),
            interfaceLog.getRequestURL(),
            interfaceLog.getIp(),
            interfaceLog.getInterfaceName(),
            interfaceLog.getRequestData(),
            interfaceLog.getEndTime(),
            interfaceLog.getResponseData(),
            interfaceLog.getSpendTime());
    }
    
    /**
     * 
     * 切入点前后
     * <功能详细描述>
     * @param pjp 切入的目标方法
     * @return 执行目标方法的返回
     * @throws Throwable 异常
     * @see [类、类#方法、类#成员]
     */
    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint pjp)
        throws Throwable
    {
        long startTime = System.currentTimeMillis();
        //执行切入点方法
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        interfaceLog.setStartTime(simpleDateFormat.format(new Date(startTime)));
        Object ob = pjp.proceed();
        interfaceLog.setEndTime(simpleDateFormat.format(new Date(System.currentTimeMillis())));
        interfaceLog.setSpendTime(System.currentTimeMillis() - startTime);
        return ob;
    }
    
}
