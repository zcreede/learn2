package com.emr.framework.redis;

import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class IndexRedisCache {
   private final Logger log = LoggerFactory.getLogger(this.getClass());
   @Resource(
      name = "indexRedisTemplate"
   )
   public RedisTemplate redisTemplate;

   public void setCacheObject(final String key, final Object value, final Integer timeout, final TimeUnit timeUnit) {
      this.redisTemplate.opsForValue().set(key, value, (long)timeout, timeUnit);
   }

   public Object getCacheObject(final String key) {
      ValueOperations<String, T> operation = this.redisTemplate.opsForValue();
      return operation.get(key);
   }

   public boolean deleteObject(final String key) {
      return this.redisTemplate.delete(key);
   }
}
