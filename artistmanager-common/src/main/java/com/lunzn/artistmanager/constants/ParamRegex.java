package com.lunzn.artistmanager.constants;

/**
 * 公用正则表达式
 * <功能详细描述>
 * 
 * @author  longguofei
 * @version  [版本号, 2017年10月19日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ParamRegex
{
    /**
     * 数字正则
     */
    String DIGIT = "^[0-9]*$";
    
    /**
     * 字符统一正则
     */
    String VARCHAR_REGEX = "^[a-zA-Z0-9-_]*$";
    
    /**
     * ip正则
     */
    String IP_REGEX = "^([0-9]{1,3}\\.){3}([0-9]{1,3})$";
}
