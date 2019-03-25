package com.lunzn.artistmanager.cache;

/**
 * CacheKey定义类
 * <功能详细描述>
 * @author  ouyangyijian
 * @version  [版本号, 2017年11月2日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CacheKey
{
    /**
     * 定义缓存key
     * <功能详细描述>
     * @author  ouyangyijian
     * @version  [版本号, 2017年11月2日]
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    public enum KvMapping
    {
        /**
         * 红外编码列表key(所有)
         */
        ircodes("ircodes"),
        /**
         * 红外编码列表key(按ircode_id分组)
         */
        ircodes_ircodeid("ircodes:ircode_id:%s"),
        /**
         * 红外编码列表key(按channel_table_id分组)
         */
        ircodes_channeltableid("ircodes:channel_table_id:%s"),
        /**
         * 红外编码列表key(按vendor_id分组)
         */
        ircodes_vendorid("ircodes:vendor_id:%s"),
        /**
         * 红外编码列表key(按ircode_type分组)
         */
        ircodes_ircodetype("ircodes:ircode_type:%s"),
        /**
         * 红外编码扫描key(按ircode_type+city_id分组)
         */
        ircodes_ircodetype_cityid("ircodes:ircode_type:%s:city_id:%s"),
        /**
         * 红外编码图片列表
         */
        ircodeimage_ircodeid("ircodeimage:ircodeid:%s"),
        /**
         * 推送消息key
         */
        informations_infoid("informations:infoid:%s"),
        /**
         * 渠道获取红外编码类型key
         */
        ircodetypes_clienttype("ircodetypes:clienttype:%s"),
        /**
         * 电视频道分组key
         */
        tvgroups("tvgroups:version:%s"),
        /**
         * 省份key
         */
        provinces_countryid("provinces:countryid:%s"),
        /**
         * 城市key
         */
        cities_provinceid("cities:province:%s"),
        
        /**
         * 运营商key
         */
        sps_cityid("sps:cityid:%s"),
        
        /**
         * 渠道下运营商列表key
         */
        channels_channeltableid("channels:channel_tab_id:%s"),
        
        /**
         * 厂商列表key
         */
        vendors_ircodetype("vendors:ircodetype:%s"),
        
        /**
         * 客户key
         */
        client_clientid("client:clientid:%s"),
        /**
         * 艺人详细信息key
         */
        artist_detialInfo_artistCode("artist:artistCode:%s");
        
        private String mapping;
        
        /**
         * <默认构造函数>
         */
        private KvMapping(String mapping)
        {
            this.mapping = mapping;
        }
        
        public String getMapping()
        {
            return mapping;
        }
        
        /**
         * 格式化key
         * <一句话功能简述>
         * <功能详细描述>
         * @param args 需要传入的参数用来格式化key
         * @return 格式化后的key
         * @see [类、类#方法、类#成员]
         */
        public String format(Object... args)
        {
            return String.format(mapping, args);
        }
    }
}
