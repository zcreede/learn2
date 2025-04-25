package com.emr.framework.config;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class JacksonConfig {
   @Bean
   public MappingJackson2HttpMessageConverter jackson2HttpMessageConverter() {
      Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
      builder.serializationInclusion(Include.NON_NULL);
      ObjectMapper objectMapper = builder.build();
      SimpleModule simpleModule = new SimpleModule();
      simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
      objectMapper.registerModule(simpleModule);
      objectMapper.configure(MapperFeature.PROPAGATE_TRANSIENT_MARKER, true);
      return new MappingJackson2HttpMessageConverter(objectMapper);
   }
}
