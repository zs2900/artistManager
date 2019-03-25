package com.lunzn.artistmanager.log;

import com.alibaba.fastjson.JSON;

/**
 * 
 * service接口日志实体类(为了兼容管理台)
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年11月5日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class InterfaceServiceLogEvt
{
    /**
     * 接口被调用时的事件戳
     */
    private String time;
    
    /**
     * 服务名称
     */
    private String appName;
    
    /**
     * 服务名
     */
    private String title;
    
    /**
     * 端侧版本
     */
    private String version;
    
    /**
     * 包名
     */
    private String pkgName;
    
    /**
     * 设备sn
     */
    private String sn;
    
    /**
     * tabId
     */
    private String tableId;
    
    /**
     * rom版本
     */
    private String rom;
    
    /**
     * 请求参数
     */
    private String param;
    
    /**
     * 记录接口名
     */
    private String action;
    
    /**
     * ？？
     */
    private String isXiri;
    
    /**
     * launcher版本
     */
    private String launcherVsn;
    
    /**
     * 日志等级
     */
    private String logLevel;
    
    /**
     * <默认构造函数>
      *
     */
    public InterfaceServiceLogEvt()
    {
        this.appName = "com.lz.smartcontrol";
        this.title = "超级智控";
    }
    
    public String getTime()
    {
        return time;
    }
    
    public void setTime(String time)
    {
        this.time = time;
    }
    
    public String getAppName()
    {
        return appName;
    }
    
    public void setAppName(String appName)
    {
        this.appName = appName;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    public String getVersion()
    {
        return version;
    }
    
    public void setVersion(String version)
    {
        this.version = version;
    }
    
    public String getPkgName()
    {
        return pkgName;
    }
    
    public void setPkgName(String pkgName)
    {
        this.pkgName = pkgName;
    }
    
    public String getSn()
    {
        return sn;
    }
    
    public void setSn(String sn)
    {
        this.sn = sn;
    }
    
    public String getTableId()
    {
        return tableId;
    }
    
    public void setTableId(String tableId)
    {
        this.tableId = tableId;
    }
    
    public String getRom()
    {
        return rom;
    }
    
    public void setRom(String rom)
    {
        this.rom = rom;
    }
    
    public String getParam()
    {
        return param;
    }
    
    public void setParam(String param)
    {
        this.param = param;
    }
    
    public String getAction()
    {
        return action;
    }
    
    public void setAction(String action)
    {
        this.action = action;
    }
    
    public String getIsXiri()
    {
        return isXiri;
    }
    
    public void setIsXiri(String isXiri)
    {
        this.isXiri = isXiri;
    }
    
    public String getLauncherVsn()
    {
        return launcherVsn;
    }
    
    public void setLauncherVsn(String launcherVsn)
    {
        this.launcherVsn = launcherVsn;
    }
    
    public String getLogLevel()
    {
        return logLevel;
    }
    
    public void setLogLevel(String logLevel)
    {
        this.logLevel = logLevel;
    }
    
    @Override
    public String toString()
    {
        return JSON.toJSONString(this);
    }
    
}
