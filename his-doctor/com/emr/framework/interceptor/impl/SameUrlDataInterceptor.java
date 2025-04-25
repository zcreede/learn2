package com.emr.framework.interceptor.impl;

import com.alibaba.fastjson.JSONObject;
import com.emr.common.filter.RepeatedlyRequestWrapper;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.http.HttpHelper;
import com.emr.framework.interceptor.RepeatSubmitInterceptor;
import com.emr.framework.redis.RedisCache;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SameUrlDataInterceptor extends RepeatSubmitInterceptor {
   public final String REPEAT_PARAMS = "repeatParams";
   public final String REPEAT_TIME = "repeatTime";
   @Value("${token.header}")
   private String header;
   @Autowired
   private RedisCache redisCache;
   private int intervalTime = 10;

   public void setIntervalTime(int intervalTime) {
      this.intervalTime = intervalTime;
   }

   public boolean isRepeatSubmit(HttpServletRequest request) {
      String nowParams = "";
      if (request instanceof RepeatedlyRequestWrapper) {
         RepeatedlyRequestWrapper repeatedlyRequest = (RepeatedlyRequestWrapper)request;
         nowParams = HttpHelper.getBodyString(repeatedlyRequest);
      }

      if (StringUtils.isEmpty(nowParams)) {
         nowParams = JSONObject.toJSONString(request.getParameterMap());
      }

      Map<String, Object> nowDataMap = new HashMap();
      nowDataMap.put("repeatParams", nowParams);
      nowDataMap.put("repeatTime", System.currentTimeMillis());
      String url = request.getRequestURI();
      String submitKey = request.getHeader(this.header);
      if (StringUtils.isEmpty(submitKey)) {
         submitKey = url;
      }

      String cacheRepeatKey = "repeat_submit:" + submitKey;
      Object sessionObj = this.redisCache.getCacheObject(cacheRepeatKey);
      if (sessionObj != null) {
         Map<String, Object> sessionMap = (Map)sessionObj;
         if (sessionMap.containsKey(url)) {
            Map<String, Object> preDataMap = (Map)sessionMap.get(url);
            if (this.compareParams(nowDataMap, preDataMap) && this.compareTime(nowDataMap, preDataMap)) {
               return true;
            }
         }
      }

      Map<String, Object> cacheMap = new HashMap();
      cacheMap.put(url, nowDataMap);
      this.redisCache.setCacheObject(cacheRepeatKey, cacheMap, this.intervalTime, TimeUnit.SECONDS);
      return false;
   }

   private boolean compareParams(Map nowMap, Map preMap) {
      String nowParams = (String)nowMap.get("repeatParams");
      String preParams = (String)preMap.get("repeatParams");
      return nowParams.equals(preParams);
   }

   private boolean compareTime(Map nowMap, Map preMap) {
      long time1 = (Long)nowMap.get("repeatTime");
      long time2 = (Long)preMap.get("repeatTime");
      return time1 - time2 < (long)(this.intervalTime * 1000);
   }
}
