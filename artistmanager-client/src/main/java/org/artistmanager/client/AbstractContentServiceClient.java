package org.artistmanager.client;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.artistmanager.message.MsgSendServiceImpl;
import com.lunzn.artistmanager.message.annotations.Location;
import com.lunzn.artistmanager.message.reqest.BaseRequestData;
import com.lunzn.artistmanager.message.response.BaseResponse;
import com.lunzn.artistmanager.system.exception.InnerException;

/**
 * 
 * 聚合数据服务Client父类
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年11月5日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class AbstractContentServiceClient
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractContentServiceClient.class);
    
    private static MsgSendServiceImpl msgSendService = null;
    
    /**
     * 向远程服务发送请求
     * <功能详细描述>
     * @param evt 请求对象
     * @param respClass 响应对象类型
     * @param <T> 返回
     * @return 响应对象
     * @throws InnerException 框架自定义异常
     * @see [类、类#方法、类#成员]
     */
    protected static <T extends BaseResponse> T send(BaseRequestData evt, Class<T> respClass)
        throws InnerException
    {
        if (null == msgSendService)
        {
            setMsgSendService(new MsgSendServiceImpl());
        }
        // 组装请求体参数
        // 发送请求
        long startTime = System.currentTimeMillis();
        //接口开始时间格式化
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date = new Date(startTime);
        String startDate = format.format(date);
        T resp = msgSendService.sendLzMsg(evt, respClass);
        Location location = evt.getClass().getAnnotation(Location.class);
        LOGGER.info("Time:{},Interface:{}, spendTime:{}, msg:{}, resp:{}",
            startDate,
            location.uri(),
            System.currentTimeMillis() - startTime,
            JSONObject.toJSONString(evt),
            JSONObject.toJSONString(resp));
        return resp;
    }
    
    public static MsgSendServiceImpl getMsgSendService()
    {
        return msgSendService;
    }
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param msgSendService ss
     * @see [类、类#方法、类#成员]
     */
    public static void setMsgSendService(MsgSendServiceImpl msgSendService)
    {
        AbstractContentServiceClient.msgSendService = msgSendService;
    }
    
}
