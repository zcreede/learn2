package com.emr.common.utils.bean;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.collections.CollectionUtils;

public class BeanUtils extends org.springframework.beans.BeanUtils {
   private static final int BEAN_METHOD_PROP_INDEX = 3;
   private static final Pattern GET_PATTERN = Pattern.compile("get(\\p{javaUpperCase}\\w*)");
   private static final Pattern SET_PATTERN = Pattern.compile("set(\\p{javaUpperCase}\\w*)");

   public static void copyBeanProp(Object dest, Object src) {
      try {
         copyProperties(src, dest);
      } catch (Exception e) {
         e.printStackTrace();
      }

   }

   public static List getSetterMethods(Object obj) {
      List<Method> setterMethods = new ArrayList();
      Method[] methods = obj.getClass().getMethods();

      for(Method method : methods) {
         Matcher m = SET_PATTERN.matcher(method.getName());
         if (m.matches() && method.getParameterTypes().length == 1) {
            setterMethods.add(method);
         }
      }

      return setterMethods;
   }

   public static List getGetterMethods(Object obj) {
      List<Method> getterMethods = new ArrayList();
      Method[] methods = obj.getClass().getMethods();

      for(Method method : methods) {
         Matcher m = GET_PATTERN.matcher(method.getName());
         if (m.matches() && method.getParameterTypes().length == 0) {
            getterMethods.add(method);
         }
      }

      return getterMethods;
   }

   public static boolean isMethodPropEquals(String m1, String m2) {
      return m1.substring(3).equals(m2.substring(3));
   }

   private static Object getFieldValueByName(String fieldName, Object o) {
      try {
         String firstLetter = fieldName.substring(0, 1).toUpperCase();
         String getter = "get" + firstLetter + fieldName.substring(1);
         Method method = o.getClass().getMethod(getter);
         Object value = method.invoke(o);
         return value;
      } catch (Exception var6) {
         return null;
      }
   }

   public static JSONObject getObjectJsonObject(Object o) throws Exception {
      JSONObject resObj = null;
      if (o != null) {
         resObj = new JSONObject();
         String[] fieldNames = getFiledName(o);

         for(int i = 0; i < fieldNames.length; ++i) {
            String fieldName = fieldNames[i];
            Object fieldValue = getFieldValueByName(fieldName, o);
            resObj.put(fieldName, fieldValue);
         }
      }

      return resObj;
   }

   public static JSONArray getObjectJsonArr(List objectList) throws Exception {
      JSONArray array = null;
      if (CollectionUtils.isNotEmpty(objectList)) {
         array = new JSONArray();

         for(Object o : objectList) {
            JSONObject resObj = getObjectJsonObject(o);
            array.add(resObj);
         }
      }

      return array;
   }

   private static String[] getFiledName(Object o) {
      Field[] fields = o.getClass().getDeclaredFields();
      String[] fieldNames = new String[fields.length];

      for(int i = 0; i < fields.length; ++i) {
         fieldNames[i] = fields[i].getName();
      }

      return fieldNames;
   }
}
