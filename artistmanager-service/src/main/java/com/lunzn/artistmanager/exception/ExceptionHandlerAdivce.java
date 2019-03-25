package com.lunzn.artistmanager.exception;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lunzn.artistmanager.enums.ResultCode;
import com.lunzn.artistmanager.message.response.BaseResponse;
import com.lunzn.artistmanager.system.exception.InnerException;

/**
 * service端异常处理
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年11月1日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@RestControllerAdvice
public class ExceptionHandlerAdivce
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerAdivce.class);
    
    /**
     * 
     * 全局异常捕捉处理
     * <功能详细描述>
     * @param e 异常
     * @return 异常响应实体
     * @see [类、类#方法、类#成员]
     */
    @ExceptionHandler(Exception.class)
    public BaseResponse handleException(Exception e)
    {
        LOGGER.error("服务器异常Exception:{}", e.getMessage());
        BaseResponse resp = new BaseResponse();
        resp.setRetCode(ResultCode.SERVICE_ERROR.getResultCode());
        resp.setRetMsg(ResultCode.SERVICE_ERROR.getResultMsg());
        
        return resp;
    }
    
    /**
     * 
     * 拦截捕捉自定义异常 InnerException.class
     * <功能详细描述>
     * @param e 异常对象
     * @return 响应实体
     * @see [类、类#方法、类#成员]
     */
    @ExceptionHandler(value = InnerException.class)
    public BaseResponse myErrorHandler(InnerException e)
    {
        LOGGER.error("服务器内部异常:{}", e.getErrorDesc());
        BaseResponse resp = new BaseResponse(e);
        
        return resp;
    }
    
    /**
     * 
     * 拦截捕捉数据库异常 SQLException.class
     * <功能详细描述>
     * @param e 数据库异常
     * @return 响应实体
     * @see [类、类#方法、类#成员]
     */
    @ExceptionHandler(SQLException.class)
    public BaseResponse databaseError(SQLException e)
    {
        LOGGER.error("数据库异常：{}", e.getClass().getSimpleName());
        BaseResponse resp = new BaseResponse();
        resp.setRetCode(ResultCode.ERROR_DB.getResultCode());
        resp.setRetMsg(e.getMessage());
        
        return resp;
    }
}
