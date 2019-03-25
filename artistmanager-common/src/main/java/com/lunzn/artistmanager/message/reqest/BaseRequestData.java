package com.lunzn.artistmanager.message.reqest;

/**
 * 业务参数对象抽象类
 * <功能详细描述>
 * 
 * @author  longguofei
 * @version  [版本号, 2017年10月19日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class BaseRequestData implements IMsgRequestData
{
    private Integer pageSize;
    
    private Integer offset;
    
    /**
     * <默认构造函数>
     */
    public BaseRequestData()
    {
    }
    
    /**
     * @return 返回 pageSize
     */
    public Integer getPageSize()
    {
        return pageSize;
    }
    
    /**
     * @param 对pageSize进行赋值
     */
    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }
    
    /**
     * @return 返回 offset
     */
    public Integer getOffset()
    {
        return offset;
    }
    
    /**
     * @param 对offset进行赋值
     */
    public void setOffset(Integer offset)
    {
        this.offset = offset;
    }
    
}
