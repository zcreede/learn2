package com.emr.framework.config;

import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
   @Autowired
   private EMRConfig emrConfig;
   @Value("${swagger.enabled}")
   private boolean enabled;
   @Value("${swagger.pathMapping}")
   private String pathMapping;

   @Bean
   public Docket createRestApi() {
      return (new Docket(DocumentationType.OAS_30)).enable(this.enabled).apiInfo(this.apiInfo()).select().apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).paths(PathSelectors.any()).build().securitySchemes(this.securitySchemes()).securityContexts(this.securityContexts()).pathMapping(this.pathMapping);
   }

   private List securitySchemes() {
      List<SecurityScheme> apiKeyList = new ArrayList();
      apiKeyList.add(new ApiKey("Authorization", "Authorization", In.HEADER.toValue()));
      return apiKeyList;
   }

   private List securityContexts() {
      List<SecurityContext> securityContexts = new ArrayList();
      securityContexts.add(SecurityContext.builder().securityReferences(this.defaultAuth()).operationSelector((o) -> o.requestMappingPattern().matches("/.*")).build());
      return securityContexts;
   }

   private List defaultAuth() {
      AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
      AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
      authorizationScopes[0] = authorizationScope;
      List<SecurityReference> securityReferences = new ArrayList();
      securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
      return securityReferences;
   }

   private ApiInfo apiInfo() {
      return (new ApiInfoBuilder()).title("电子病历_接口文档").version("版本号:" + this.emrConfig.getVersion()).build();
   }
}
