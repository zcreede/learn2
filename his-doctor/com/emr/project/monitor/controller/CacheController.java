package com.emr.project.monitor.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.web.domain.AjaxResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/monitor/cache"})
public class CacheController {
   @Autowired
   private RedisTemplate redisTemplate;

   @PreAuthorize("@ss.hasPermi('monitor:cache:list')")
   @GetMapping
   public AjaxResult getInfo() throws Exception {
      Properties info = (Properties)this.redisTemplate.execute((connection) -> connection.info());
      Properties commandStats = (Properties)this.redisTemplate.execute((connection) -> connection.info("commandstats"));
      Object dbSize = this.redisTemplate.execute((connection) -> connection.dbSize());
      Map<String, Object> result = new HashMap(3);
      result.put("info", info);
      result.put("dbSize", dbSize);
      List<Map<String, String>> pieList = new ArrayList();
      commandStats.stringPropertyNames().forEach((key) -> {
         Map<String, String> data = new HashMap(2);
         String property = commandStats.getProperty(key);
         data.put("name", StringUtils.removeStart(key, "cmdstat_"));
         data.put("value", StringUtils.substringBetween(property, "calls=", ",usec"));
         pieList.add(data);
      });
      result.put("commandStats", pieList);
      return AjaxResult.success((Object)result);
   }
}
