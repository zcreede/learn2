package com.emr.common.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Obj2MapUtil {
   private static Pattern linePattern = Pattern.compile("_(\\w)");
   private static Pattern humpPattern = Pattern.compile("[A-Z]");

   public static Map objectToMap(Object obj) throws IllegalAccessException {
      Map<String, Object> map = new HashMap();
      Class<?> clazz = obj.getClass();

      for(Field field : clazz.getDeclaredFields()) {
         field.setAccessible(true);
         String fieldName = field.getName();
         String field_name = lineToHump(fieldName);
         Object value = field.get(obj);
         map.put(field_name, value);
      }

      return map;
   }

   public static Map mapToMap(Map obj) throws IllegalAccessException {
      Map<String, Object> map = new HashMap();

      for(String key : obj.keySet()) {
         String fieldName = lineToHump(key.toLowerCase());
         map.put(fieldName, obj.get(key));
      }

      return map;
   }

   public static String humpToLine(String str) {
      Matcher matcher = humpPattern.matcher(str);
      StringBuffer sb = new StringBuffer();

      while(matcher.find()) {
         matcher.appendReplacement(sb, "_" + matcher.group(0).toUpperCase());
      }

      matcher.appendTail(sb);
      return sb.toString().toUpperCase();
   }

   public static String lineToHump(String str) {
      Matcher matcher = linePattern.matcher(str);
      StringBuffer sb = new StringBuffer();

      while(matcher.find()) {
         matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
      }

      matcher.appendTail(sb);
      return sb.toString();
   }
}
