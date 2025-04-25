package com.emr.common.utils.reflect;

import com.emr.common.core.text.Convert;
import com.emr.common.utils.DateUtils;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.poi.ss.usermodel.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReflectUtils {
   private static final String SETTER_PREFIX = "set";
   private static final String GETTER_PREFIX = "get";
   private static final String CGLIB_CLASS_SEPARATOR = "$$";
   private static Logger logger = LoggerFactory.getLogger(ReflectUtils.class);

   public static Object invokeGetter(Object obj, String propertyName) {
      Object object = obj;

      for(String name : StringUtils.split(propertyName, ".")) {
         String getterMethodName = "get" + StringUtils.capitalize(name);
         object = invokeMethod(object, getterMethodName, new Class[0], new Object[0]);
      }

      return object;
   }

   public static void invokeSetter(Object obj, String propertyName, Object value) {
      Object object = obj;
      String[] names = StringUtils.split(propertyName, ".");

      for(int i = 0; i < names.length; ++i) {
         if (i < names.length - 1) {
            String getterMethodName = "get" + StringUtils.capitalize(names[i]);
            object = invokeMethod(object, getterMethodName, new Class[0], new Object[0]);
         } else {
            String setterMethodName = "set" + StringUtils.capitalize(names[i]);
            invokeMethodByName(object, setterMethodName, new Object[]{value});
         }
      }

   }

   public static Object getFieldValue(final Object obj, final String fieldName) {
      Field field = getAccessibleField(obj, fieldName);
      if (field == null) {
         logger.debug("在 [" + obj.getClass() + "] 中，没有找到 [" + fieldName + "] 字段 ");
         return null;
      } else {
         E result = (E)null;

         try {
            result = (E)field.get(obj);
         } catch (IllegalAccessException e) {
            logger.error("不可能抛出的异常{}", e.getMessage());
         }

         return result;
      }
   }

   public static void setFieldValue(final Object obj, final String fieldName, final Object value) {
      Field field = getAccessibleField(obj, fieldName);
      if (field == null) {
         logger.debug("在 [" + obj.getClass() + "] 中，没有找到 [" + fieldName + "] 字段 ");
      } else {
         try {
            field.set(obj, value);
         } catch (IllegalAccessException e) {
            logger.error("不可能抛出的异常: {}", e.getMessage());
         }

      }
   }

   public static Object invokeMethod(final Object obj, final String methodName, final Class[] parameterTypes, final Object[] args) {
      if (obj != null && methodName != null) {
         Method method = getAccessibleMethod(obj, methodName, parameterTypes);
         if (method == null) {
            logger.debug("在 [" + obj.getClass() + "] 中，没有找到 [" + methodName + "] 方法 ");
            return null;
         } else {
            try {
               return method.invoke(obj, args);
            } catch (Exception e) {
               String msg = "method: " + method + ", obj: " + obj + ", args: " + args + "";
               throw convertReflectionExceptionToUnchecked(msg, e);
            }
         }
      } else {
         return null;
      }
   }

   public static Object invokeMethodByName(final Object obj, final String methodName, final Object[] args) {
      Method method = getAccessibleMethodByName(obj, methodName, args.length);
      if (method == null) {
         logger.debug("在 [" + obj.getClass() + "] 中，没有找到 [" + methodName + "] 方法 ");
         return null;
      } else {
         try {
            Class<?>[] cs = method.getParameterTypes();

            for(int i = 0; i < cs.length; ++i) {
               if (args[i] != null && !args[i].getClass().equals(cs[i])) {
                  if (cs[i] == String.class) {
                     args[i] = Convert.toStr(args[i]);
                     if (StringUtils.endsWith((String)args[i], ".0")) {
                        args[i] = StringUtils.substringBefore((String)args[i], ".0");
                     }
                  } else if (cs[i] == Integer.class) {
                     args[i] = Convert.toInt(args[i]);
                  } else if (cs[i] == Long.class) {
                     args[i] = Convert.toLong(args[i]);
                  } else if (cs[i] == Double.class) {
                     args[i] = Convert.toDouble(args[i]);
                  } else if (cs[i] == Float.class) {
                     args[i] = Convert.toFloat(args[i]);
                  } else if (cs[i] == Date.class) {
                     if (args[i] instanceof String) {
                        args[i] = DateUtils.parseDate(args[i]);
                     } else {
                        args[i] = DateUtil.getJavaDate((Double)args[i]);
                     }
                  } else if (cs[i] == Boolean.TYPE || cs[i] == Boolean.class) {
                     args[i] = Convert.toBool(args[i]);
                  }
               }
            }

            return method.invoke(obj, args);
         } catch (Exception e) {
            String msg = "method: " + method + ", obj: " + obj + ", args: " + args + "";
            throw convertReflectionExceptionToUnchecked(msg, e);
         }
      }
   }

   public static Field getAccessibleField(final Object obj, final String fieldName) {
      if (obj == null) {
         return null;
      } else {
         Validate.notBlank(fieldName, "fieldName can't be blank", new Object[0]);

         for(Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
               Field field = superClass.getDeclaredField(fieldName);
               makeAccessible(field);
               return field;
            }
         }

         return null;
      }
   }

   public static Method getAccessibleMethod(final Object obj, final String methodName, final Class... parameterTypes) {
      if (obj == null) {
         return null;
      } else {
         Validate.notBlank(methodName, "methodName can't be blank", new Object[0]);

         for(Class<?> searchType = obj.getClass(); searchType != Object.class; searchType = searchType.getSuperclass()) {
            try {
               Method method = searchType.getDeclaredMethod(methodName, parameterTypes);
               makeAccessible(method);
               return method;
            }
         }

         return null;
      }
   }

   public static Method getAccessibleMethodByName(final Object obj, final String methodName, int argsNum) {
      if (obj == null) {
         return null;
      } else {
         Validate.notBlank(methodName, "methodName can't be blank", new Object[0]);

         for(Class<?> searchType = obj.getClass(); searchType != Object.class; searchType = searchType.getSuperclass()) {
            Method[] methods = searchType.getDeclaredMethods();

            for(Method method : methods) {
               if (method.getName().equals(methodName) && method.getParameterTypes().length == argsNum) {
                  makeAccessible(method);
                  return method;
               }
            }
         }

         return null;
      }
   }

   public static void makeAccessible(Method method) {
      if ((!Modifier.isPublic(method.getModifiers()) || !Modifier.isPublic(method.getDeclaringClass().getModifiers())) && !method.isAccessible()) {
         method.setAccessible(true);
      }

   }

   public static void makeAccessible(Field field) {
      if ((!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers()) || Modifier.isFinal(field.getModifiers())) && !field.isAccessible()) {
         field.setAccessible(true);
      }

   }

   public static Class getClassGenricType(final Class clazz) {
      return getClassGenricType(clazz, 0);
   }

   public static Class getClassGenricType(final Class clazz, final int index) {
      Type genType = clazz.getGenericSuperclass();
      if (!(genType instanceof ParameterizedType)) {
         logger.debug(clazz.getSimpleName() + "'s superclass not ParameterizedType");
         return Object.class;
      } else {
         Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
         if (index < params.length && index >= 0) {
            if (!(params[index] instanceof Class)) {
               logger.debug(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
               return Object.class;
            } else {
               return (Class)params[index];
            }
         } else {
            logger.debug("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: " + params.length);
            return Object.class;
         }
      }
   }

   public static Class getUserClass(Object instance) {
      if (instance == null) {
         throw new RuntimeException("Instance must not be null");
      } else {
         Class clazz = instance.getClass();
         if (clazz != null && clazz.getName().contains("$$")) {
            Class<?> superClass = clazz.getSuperclass();
            if (superClass != null && !Object.class.equals(superClass)) {
               return superClass;
            }
         }

         return clazz;
      }
   }

   public static RuntimeException convertReflectionExceptionToUnchecked(String msg, Exception e) {
      if (!(e instanceof IllegalAccessException) && !(e instanceof IllegalArgumentException) && !(e instanceof NoSuchMethodException)) {
         return e instanceof InvocationTargetException ? new RuntimeException(msg, ((InvocationTargetException)e).getTargetException()) : new RuntimeException(msg, e);
      } else {
         return new IllegalArgumentException(msg, e);
      }
   }
}
