package com.lunzn.artistmanager.util;

import java.security.SecureRandom;
import java.util.Random;

/**
 * 生成编号工具类
 * 用于生成各种编号
 * 
 * @author  songXiaotong
 * @version  [版本号, 2016-3-9]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public final class CreateIDUtil
{
    /**
     * 常用字符
     */
    private static final char[] CHAR_32 = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N',
        'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9'};
    
    /**
     * <默认构造函数>
     */
    private CreateIDUtil()
    {
        super();
    }
    
    /**
     * 获取随机字符串
     * 
     * @param size   长度
     * @return String随机字符串
     */
    public static String getRandom(int size)
    {
        StringBuilder s = new StringBuilder();
        Random random = new SecureRandom();
        for (int i = 0; i < size; i++)
        {
            s.append(CHAR_32[random.nextInt(CHAR_32.length)]);
        }
        return s.toString();
    }
}
