package com.lunzn.artistmanager.validator.handler;

import java.lang.annotation.Annotation;

import com.lunzn.artistmanager.system.exception.InnerException;
import com.lunzn.artistmanager.system.exception.ValidateException;
import com.lunzn.artistmanager.validator.IValidatable;

/**
 * 校验处理接口类
 * 
 * @author  longguofei
 * @version  [版本号, 2017年10月18日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface Handler
{
    /**
     * 校验处理接口，由具体的子类实现
     * <功能详细描述>
     * @param annotation 注解
     * @param fieldName 字段名
     * @param fieldValue 字段值
     * @param bean 对象
     * @throws ValidateException 校验异常
     * @throws InnerException 内部异常
     * @see [类、类#方法、类#成员]
     */
    void execute(Annotation annotation, String fieldName, Object fieldValue, IValidatable bean)
        throws ValidateException, InnerException;
}
