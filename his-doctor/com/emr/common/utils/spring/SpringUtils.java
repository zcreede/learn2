package com.emr.common.utils.spring;

import com.emr.common.utils.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public final class SpringUtils implements BeanFactoryPostProcessor, ApplicationContextAware {
   private static ConfigurableListableBeanFactory beanFactory;
   private static ApplicationContext applicationContext;

   public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
      SpringUtils.beanFactory = beanFactory;
   }

   public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
      SpringUtils.applicationContext = applicationContext;
   }

   public static Object getBean(String name) throws BeansException {
      return beanFactory.getBean(name);
   }

   public static Object getBean(Class clz) throws BeansException {
      T result = (T)beanFactory.getBean(clz);
      return result;
   }

   public static boolean containsBean(String name) {
      return beanFactory.containsBean(name);
   }

   public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
      return beanFactory.isSingleton(name);
   }

   public static Class getType(String name) throws NoSuchBeanDefinitionException {
      return beanFactory.getType(name);
   }

   public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
      return beanFactory.getAliases(name);
   }

   public static Object getAopProxy(Object invoker) {
      return AopContext.currentProxy();
   }

   public static String[] getActiveProfiles() {
      return applicationContext.getEnvironment().getActiveProfiles();
   }

   public static String getActiveProfile() {
      String[] activeProfiles = getActiveProfiles();
      return StringUtils.isNotEmpty((Object[])activeProfiles) ? activeProfiles[0] : null;
   }
}
