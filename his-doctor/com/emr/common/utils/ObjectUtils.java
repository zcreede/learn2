package com.emr.common.utils;

import com.alibaba.fastjson.JSONObject;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ObjectUtils {
   private static final String JAVAP = "java.";
   private static final String JAVADATESTR = "java.util.Date";

   public static Map objectToMap(Object obj) throws IllegalAccessException {
      Map<String, Object> map = new HashMap();
      Class<?> clazz = obj.getClass();

      for(Field field : clazz.getDeclaredFields()) {
         field.setAccessible(true);
         String fieldName = field.getName();
         Object value = field.get(obj);
         map.put(fieldName, value);
      }

      return map;
   }

   public static void removeNullValue(Map map) {
      Set set = map.keySet();
      Iterator iterator = set.iterator();

      while(iterator.hasNext()) {
         Object obj = iterator.next();
         Object value = map.get(obj);
         remove(value, iterator);
      }

   }

   private static void remove(Object obj, Iterator iterator) {
      if (obj instanceof String) {
         String str = (String)obj;
         if (StringUtils.isBlank(str)) {
            iterator.remove();
         }
      } else if (obj instanceof Collection) {
         Collection col = (Collection)obj;
         if (col == null || col.isEmpty()) {
            iterator.remove();
         }
      } else if (obj instanceof Map) {
         Map temp = (Map)obj;
         if (temp == null || temp.isEmpty()) {
            iterator.remove();
         }
      } else if (obj instanceof Object[]) {
         Object[] array = obj;
         if (array == null || array.length <= 0) {
            iterator.remove();
         }
      } else if (obj == null) {
         iterator.remove();
      }

   }

   public static JSONObject objectToJSONObject(Object obj) throws IllegalAccessException {
      JSONObject object = new JSONObject();
      Class<?> clazz = obj.getClass();

      for(Field field : clazz.getDeclaredFields()) {
         field.setAccessible(true);
         String fieldName = field.getName();
         Object value = field.get(obj);
         object.put(fieldName, value);
      }

      return object;
   }

   public static Map objectToMapString(String timeFormatStr, Object obj, String... excludeFields) throws IllegalAccessException {
      Map<String, String> map = new HashMap();
      if (excludeFields.length != 0) {
         List<String> list = Arrays.asList(excludeFields);
         objectTransfer(timeFormatStr, obj, map, list);
      } else {
         objectTransfer(timeFormatStr, obj, map, (List)null);
      }

      return map;
   }

   private static Map objectTransfer(String timeFormatStr, Object obj, Map map, List excludeFields) throws IllegalAccessException {
      boolean isExclude = false;
      String formatStr = "YYYY-MM-dd HH:mm:ss";
      if (timeFormatStr != null && !timeFormatStr.isEmpty()) {
         formatStr = timeFormatStr;
      }

      if (excludeFields != null) {
         isExclude = true;
      }

      Class<?> clazz = obj.getClass();

      for(Field field : clazz.getDeclaredFields()) {
         String fieldName = clazz.getSimpleName() + "." + field.getName();
         if (!isExclude || !excludeFields.contains(fieldName)) {
            field.setAccessible(true);
            Object value = field.get(obj);
            Class<?> valueClass = value.getClass();
            if (valueClass.isPrimitive()) {
               map.put(fieldName, value.toString());
            } else if (valueClass.getName().contains("java.")) {
               if (valueClass.getName().equals("java.util.Date")) {
                  SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
                  Date date = (Date)value;
                  String dataStr = sdf.format(date);
                  map.put(fieldName, dataStr);
               } else {
                  map.put(fieldName, value.toString());
               }
            } else {
               objectTransfer(timeFormatStr, value, map, excludeFields);
            }
         }
      }

      return map;
   }
}
