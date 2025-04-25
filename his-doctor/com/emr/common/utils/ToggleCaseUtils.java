package com.emr.common.utils;

import com.github.pagehelper.Page;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ToggleCaseUtils {
   public static Map toLowerCase(Map orgMap) {
      Map<String, Object> resMap = new HashMap();
      if (orgMap != null && !orgMap.isEmpty()) {
         for(String key : orgMap.keySet()) {
            String newKey = key.toLowerCase();
            resMap.put(newKey, orgMap.get(key));
         }

         return resMap;
      } else {
         return resMap;
      }
   }

   public static List toLowerCaseList(List orgList) {
      List<Map<String, Object>> resList = new ArrayList();
      if (orgList != null && !orgList.isEmpty()) {
         for(Map orgMap : orgList) {
            Map<String, Object> resMap = new HashMap();

            for(String key : orgMap.keySet()) {
               String newKey = key.toLowerCase();
               resMap.put(newKey, orgMap.get(key));
            }

            resList.add(resMap);
         }

         return resList;
      } else {
         return resList;
      }
   }

   public static Page toLowerCaseListPage(Page orgList) {
      Page<Map<String, Object>> resList = new Page();
      if (orgList != null && !orgList.isEmpty()) {
         for(Map orgMap : orgList) {
            Map<String, Object> resMap = new HashMap();

            for(String key : orgMap.keySet()) {
               String newKey = key.toLowerCase();
               resMap.put(newKey, orgMap.get(key));
            }

            resList.add(resMap);
         }

         resList.setTotal(orgList.getTotal());
         return resList;
      } else {
         return resList;
      }
   }
}
