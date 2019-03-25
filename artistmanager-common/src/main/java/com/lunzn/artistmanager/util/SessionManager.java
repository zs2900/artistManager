package com.lunzn.artistmanager.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

/**
 *通过管理session实现在线用户用户管理
 * 缓存所有session
 * 
 * @author  yangshu
 * @version  [版本号, 2017年11月15日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public final class SessionManager
{
    
    /**
     * session管理
     */
    private static final Map<String, HttpSession> SMAP = new HashMap<String, HttpSession>();
    
    /** 
     * <默认构造函数>
     */
    private SessionManager()
    {
    }
    
    /** 
     *加入用户的session
     * <功能详细描述>
     * @param userId 用户管理
     * @param session 用户的session
     * @see [类、类#方法、类#成员]
     */
    public static void putSession(String userId, HttpSession session)
    {
        if (StringUtil.isEmpty(userId) || session == null)
        {
            return;
        }
        checkOut(userId);
        SMAP.put(userId, session);
    }
    
    /** 
     * 移除session
     * <功能详细描述>
     * @param userId 用户ID
     * @see [类、类#方法、类#成员]
     */
    public static void checkOut(String userId)
    {
        if (StringUtil.isEmpty(userId) || SMAP.get(userId) == null)
        {
            return;
        }
        HttpSession session = SMAP.get(userId);
        SMAP.remove(userId);
        try
        {
            session.invalidate();
        }
        catch (IllegalStateException e)
        {
            e.printStackTrace();
        }
        
    }
}
