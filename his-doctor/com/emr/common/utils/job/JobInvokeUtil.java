package com.emr.common.utils.job;

import com.emr.common.utils.StringUtils;
import com.emr.common.utils.spring.SpringUtils;
import com.emr.project.monitor.domain.SysJob;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

public class JobInvokeUtil {
   public static void invokeMethod(SysJob sysJob) throws Exception {
      String invokeTarget = sysJob.getInvokeTarget();
      String beanName = getBeanName(invokeTarget);
      String methodName = getMethodName(invokeTarget);
      List<Object[]> methodParams = getMethodParams(invokeTarget);
      if (!isValidClassName(beanName)) {
         Object bean = SpringUtils.getBean(beanName);
         invokeMethod(bean, methodName, methodParams);
      } else {
         Object bean = Class.forName(beanName).newInstance();
         invokeMethod(bean, methodName, methodParams);
      }

   }

   public static void invokeMethod(String invokeTarget) throws Exception {
      String beanName = getBeanName(invokeTarget);
      String methodName = getMethodName(invokeTarget);
      List<Object[]> methodParams = getMethodParams(invokeTarget);
      if (!isValidClassName(beanName)) {
         Object bean = SpringUtils.getBean(beanName);
         invokeMethod(bean, methodName, methodParams);
      } else {
         Object bean = Class.forName(beanName).newInstance();
         invokeMethod(bean, methodName, methodParams);
      }

   }

   private static void invokeMethod(Object bean, String methodName, List methodParams) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
      if (StringUtils.isNotNull(methodParams) && methodParams.size() > 0) {
         Method method = bean.getClass().getDeclaredMethod(methodName, getMethodParamsType(methodParams));
         method.invoke(bean, getMethodParamsValue(methodParams));
      } else {
         Method method = bean.getClass().getDeclaredMethod(methodName);
         method.invoke(bean);
      }

   }

   public static boolean isValidClassName(String invokeTarget) {
      return StringUtils.countMatches(invokeTarget, ".") > 1;
   }

   public static String getBeanName(String invokeTarget) {
      String beanName = StringUtils.substringBefore(invokeTarget, "(");
      return StringUtils.substringBeforeLast(beanName, ".");
   }

   public static String getMethodName(String invokeTarget) {
      String methodName = StringUtils.substringBefore(invokeTarget, "(");
      return StringUtils.substringAfterLast(methodName, ".");
   }

   public static List getMethodParams(String invokeTarget) {
      String methodStr = StringUtils.substringBetween(invokeTarget, "(", ")");
      if (StringUtils.isEmpty(methodStr)) {
         return null;
      } else {
         String[] methodParams = methodStr.split(",");
         List<Object[]> classs = new LinkedList();

         for(int i = 0; i < methodParams.length; ++i) {
            String str = StringUtils.trimToEmpty(methodParams[i]);
            if (StringUtils.contains(str, "'")) {
               classs.add(new Object[]{StringUtils.replace(str, "'", ""), String.class});
            } else if (!StringUtils.equals(str, "true") && !StringUtils.equalsIgnoreCase(str, "false")) {
               if (StringUtils.containsIgnoreCase(str, "L")) {
                  classs.add(new Object[]{Long.valueOf(StringUtils.replaceIgnoreCase(str, "L", "")), Long.class});
               } else if (StringUtils.containsIgnoreCase(str, "D")) {
                  classs.add(new Object[]{Double.valueOf(StringUtils.replaceIgnoreCase(str, "D", "")), Double.class});
               } else {
                  classs.add(new Object[]{Integer.valueOf(str), Integer.class});
               }
            } else {
               classs.add(new Object[]{Boolean.valueOf(str), Boolean.class});
            }
         }

         return classs;
      }
   }

   public static Class[] getMethodParamsType(List methodParams) {
      Class<?>[] classs = new Class[methodParams.size()];
      int index = 0;

      for(Object[] os : methodParams) {
         classs[index] = (Class)os[1];
         ++index;
      }

      return classs;
   }

   public static Object[] getMethodParamsValue(List methodParams) {
      Object[] classs = new Object[methodParams.size()];
      int index = 0;

      for(Object[] os : methodParams) {
         classs[index] = os[0];
         ++index;
      }

      return classs;
   }
}
