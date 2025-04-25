package com.emr.common.utils.ip;

import com.alibaba.fastjson.JSONObject;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.http.HttpUtils;
import com.emr.framework.config.EMRConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddressUtils {
   private static final Logger log = LoggerFactory.getLogger(AddressUtils.class);
   public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp";
   public static final String UNKNOWN = "XX XX";

   public static String getRealAddressByIP(String ip) {
      String address = "XX XX";
      if (IpUtils.internalIp(ip)) {
         return "内网IP";
      } else {
         if (EMRConfig.isAddressEnabled()) {
            try {
               String rspStr = HttpUtils.sendGet("http://whois.pconline.com.cn/ipJson.jsp", "ip=" + ip + "&json=true", "GBK");
               if (StringUtils.isEmpty(rspStr)) {
                  log.error("获取地理位置异常 {}", ip);
                  return "XX XX";
               }

               JSONObject obj = JSONObject.parseObject(rspStr);
               String region = obj.getString("pro");
               String city = obj.getString("city");
               return String.format("%s %s", region, city);
            } catch (Exception var6) {
               log.error("获取地理位置异常 {}", ip);
            }
         }

         return address;
      }
   }
}
