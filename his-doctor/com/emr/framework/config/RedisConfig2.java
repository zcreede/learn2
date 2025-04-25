package com.emr.framework.config;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableCaching
public class RedisConfig2 {
   @Value("${spring.redis2.host}")
   private String hostName;
   @Value("${spring.redis2.port}")
   private int port;
   @Value("${spring.redis2.password}")
   private String password;
   @Value("${spring.redis2.database.index-db}")
   private int databaseIndexDb;
   @Value("${spring.redis2.jedis.pool.max-active}")
   private int maxActive;
   @Value("${spring.redis2.jedis.pool.max-wait}")
   private int maxWait;
   @Value("${spring.redis2.jedis.pool.max-idle}")
   private int maxIdle;
   @Value("${spring.redis2.jedis.pool.min-idle}")
   private int minIdle;
   @Value("${spring.redis2.jedis.pool.test-on-borrow}")
   private boolean testOnBorrow;
   @Value("${spring.redis2.jedis.pool.test-on-return}")
   private boolean testOnReturn;
   public static final String REDIS_TEMPLATE_INDEX_DB = "indexRedisTemplate";

   @Bean({"indexRedisTemplate"})
   public RedisTemplate indexRedisTemplate() {
      return this.getRedisTemplate(this.databaseIndexDb);
   }

   public RedisTemplate getRedisTemplate(int database) {
      RedisTemplate<String, Object> redisTemplate = new RedisTemplate();
      redisTemplate.setConnectionFactory(this.getRedisConnectionFactory(database));
      FastJson2JsonRedisSerializer serializer = new FastJson2JsonRedisSerializer(Object.class);
      ObjectMapper mapper = new ObjectMapper();
      mapper.setVisibility(PropertyAccessor.ALL, Visibility.ANY);
      mapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, DefaultTyping.NON_FINAL, As.PROPERTY);
      serializer.setObjectMapper(mapper);
      redisTemplate.setKeySerializer(new StringRedisSerializer());
      redisTemplate.setValueSerializer(serializer);
      redisTemplate.setHashKeySerializer(new StringRedisSerializer());
      redisTemplate.setHashValueSerializer(serializer);
      redisTemplate.afterPropertiesSet();
      return redisTemplate;
   }

   private RedisConnectionFactory getRedisConnectionFactory(int database) {
      JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(this.getRedisStandaloneConfig(database), this.getJedisClientConfig());
      jedisConnectionFactory.afterPropertiesSet();
      return jedisConnectionFactory;
   }

   private RedisStandaloneConfiguration getRedisStandaloneConfig(int database) {
      RedisStandaloneConfiguration redisStandaloneConfig = new RedisStandaloneConfiguration();
      redisStandaloneConfig.setHostName(this.hostName);
      redisStandaloneConfig.setPort(this.port);
      redisStandaloneConfig.setPassword(RedisPassword.of(this.password));
      redisStandaloneConfig.setDatabase(database);
      return redisStandaloneConfig;
   }

   private JedisClientConfiguration getJedisClientConfig() {
      JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
      jedisPoolConfig.setMaxTotal(this.maxActive);
      jedisPoolConfig.setMaxIdle(this.maxIdle);
      jedisPoolConfig.setMinIdle(this.minIdle);
      jedisPoolConfig.setMaxWaitMillis(Duration.ofMillis((long)this.maxWait).toMillis());
      jedisPoolConfig.setTestOnBorrow(this.testOnBorrow);
      jedisPoolConfig.setTestOnReturn(this.testOnReturn);
      JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpccb = (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder)JedisClientConfiguration.builder();
      jpccb.poolConfig(jedisPoolConfig);
      return jpccb.build();
   }

   public String getHostName() {
      return this.hostName;
   }

   public void setHostName(String hostName) {
      this.hostName = hostName;
   }

   public int getPort() {
      return this.port;
   }

   public void setPort(int port) {
      this.port = port;
   }

   public String getPassword() {
      return this.password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public int getMaxActive() {
      return this.maxActive;
   }

   public void setMaxActive(int maxActive) {
      this.maxActive = maxActive;
   }

   public int getMaxWait() {
      return this.maxWait;
   }

   public void setMaxWait(int maxWait) {
      this.maxWait = maxWait;
   }

   public int getMaxIdle() {
      return this.maxIdle;
   }

   public void setMaxIdle(int maxIdle) {
      this.maxIdle = maxIdle;
   }

   public int getMinIdle() {
      return this.minIdle;
   }

   public void setMinIdle(int minIdle) {
      this.minIdle = minIdle;
   }

   public boolean isTestOnBorrow() {
      return this.testOnBorrow;
   }

   public void setTestOnBorrow(boolean testOnBorrow) {
      this.testOnBorrow = testOnBorrow;
   }

   public boolean isTestOnReturn() {
      return this.testOnReturn;
   }

   public void setTestOnReturn(boolean testOnReturn) {
      this.testOnReturn = testOnReturn;
   }

   public int getDatabaseIndexDb() {
      return this.databaseIndexDb;
   }

   public void setDatabaseIndexDb(int databaseIndexDb) {
      this.databaseIndexDb = databaseIndexDb;
   }
}
