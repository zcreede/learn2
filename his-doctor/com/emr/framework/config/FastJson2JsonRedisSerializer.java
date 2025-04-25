package com.emr.framework.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.nio.charset.Charset;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.util.Assert;

public class FastJson2JsonRedisSerializer implements RedisSerializer {
   private ObjectMapper objectMapper = new ObjectMapper();
   public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
   private Class clazz;

   public FastJson2JsonRedisSerializer(Class clazz) {
      this.clazz = clazz;
   }

   public byte[] serialize(Object t) throws SerializationException {
      return t == null ? new byte[0] : JSON.toJSONString(t, new SerializerFeature[]{SerializerFeature.WriteClassName}).getBytes(DEFAULT_CHARSET);
   }

   public Object deserialize(byte[] bytes) throws SerializationException {
      if (bytes != null && bytes.length > 0) {
         String str = new String(bytes, DEFAULT_CHARSET);
         return JSON.parseObject(str, this.clazz);
      } else {
         return null;
      }
   }

   public void setObjectMapper(ObjectMapper objectMapper) {
      Assert.notNull(objectMapper, "'objectMapper' must not be null");
      this.objectMapper = objectMapper;
   }

   protected JavaType getJavaType(Class clazz) {
      return TypeFactory.defaultInstance().constructType(clazz);
   }

   static {
      ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
   }
}
