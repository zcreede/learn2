package com.emr.framework.redis;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class OrderRedisCache {
   private final Logger log = LoggerFactory.getLogger(this.getClass());
   @Resource(
      name = "orderRedisTemplate"
   )
   public RedisTemplate redisTemplate;

   public void setCacheObject(final String key, final Object value) {
      this.redisTemplate.opsForValue().set(key, value);
   }

   public void setCacheObject(final String key, final Object value, final Integer timeout, final TimeUnit timeUnit) {
      this.redisTemplate.opsForValue().set(key, value, (long)timeout, timeUnit);
   }

   public boolean expire(final String key, final long timeout) {
      return this.expire(key, timeout, TimeUnit.SECONDS);
   }

   public boolean expire(final String key, final long timeout, final TimeUnit unit) {
      return this.redisTemplate.expire(key, timeout, unit);
   }

   public Object getCacheObject(final String key) {
      ValueOperations<String, T> operation = this.redisTemplate.opsForValue();
      return operation.get(key);
   }

   public boolean deleteObject(final String key) {
      return this.redisTemplate.delete(key);
   }

   public long deleteObject(final Collection collection) {
      return this.redisTemplate.delete(collection);
   }

   public long setCacheList(final String key, final List dataList) {
      Long count = this.redisTemplate.opsForList().rightPushAll(key, dataList);
      return count == null ? 0L : count;
   }

   public List getCacheList(final String key) {
      return this.redisTemplate.opsForList().range(key, 0L, -1L);
   }

   public BoundSetOperations setCacheSet(final String key, final Set dataSet) {
      BoundSetOperations<String, T> setOperation = this.redisTemplate.boundSetOps(key);
      Iterator<T> it = dataSet.iterator();

      while(it.hasNext()) {
         setOperation.add(new Object[]{it.next()});
      }

      return setOperation;
   }

   public Set getCacheSet(final String key) {
      return this.redisTemplate.opsForSet().members(key);
   }

   public void setCacheMap(final String key, final Map dataMap) {
      if (dataMap != null) {
         this.redisTemplate.opsForHash().putAll(key, dataMap);
      }

   }

   public Map getCacheMap(final String key) {
      return this.redisTemplate.opsForHash().entries(key);
   }

   public void setCacheMapValue(final String key, final String hKey, final Object value) {
      this.redisTemplate.opsForHash().put(key, hKey, value);
   }

   public Object getCacheMapValue(final String key, final String hKey) {
      HashOperations<String, String, T> opsForHash = this.redisTemplate.opsForHash();
      return opsForHash.get(key, hKey);
   }

   public List getMultiCacheMapValue(final String key, final Collection hKeys) {
      return this.redisTemplate.opsForHash().multiGet(key, hKeys);
   }

   public Collection keys(final String pattern) {
      return this.redisTemplate.keys(pattern);
   }
}
