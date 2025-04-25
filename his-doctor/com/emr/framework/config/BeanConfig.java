package com.emr.framework.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class BeanConfig implements ApplicationContextAware {
   private static ApplicationContext applicationContext;

   public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
      BeanConfig.applicationContext = applicationContext;
   }

   public static Object getBean(String className) throws BeansException, IllegalArgumentException {
      if (className != null && className.length() > 0) {
         String beanName = null;
         if (className.length() > 1) {
            beanName = className.substring(0, 1).toLowerCase() + className.substring(1);
         } else {
            beanName = className.toLowerCase();
         }

         return applicationContext != null ? applicationContext.getBean(beanName) : null;
      } else {
         throw new IllegalArgumentException("className为空");
      }
   }
}
