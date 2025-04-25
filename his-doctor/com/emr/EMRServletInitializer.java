package com.emr;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class EMRServletInitializer extends SpringBootServletInitializer {
   protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
      return application.sources(new Class[]{EMRApplication.class});
   }
}
