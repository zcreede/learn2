package com.emr.common.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OrderUtils {
   public static Map orderMapAsc(Map map) {
      Map<String, Object> result = new LinkedHashMap(map.size());
      map.entrySet().stream().sorted(Entry.comparingByKey()).forEachOrdered((e) -> result.put(e.getKey(), e.getValue()));
      return result;
   }

   public static String getMapMinOrMaxValueKey(Map map, String choose) {
      List<Map.Entry<String, Long>> list = new ArrayList(map.entrySet());
      Collections.sort(list, (o1, o2) -> ((Long)o1.getValue()).intValue() - ((Long)o2.getValue()).intValue());
      String key = "";
      if (choose.equals("min")) {
         key = (String)((Map.Entry)list.get(0)).getKey();
      } else if (choose.equals("max")) {
         key = (String)((Map.Entry)list.get(list.size() - 1)).getKey();
      }

      return key;
   }
}
