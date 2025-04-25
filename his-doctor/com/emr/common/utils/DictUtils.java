package com.emr.common.utils;

import com.emr.common.utils.spring.SpringUtils;
import com.emr.framework.redis.RedisCache;
import com.emr.project.system.domain.SysDictData;
import java.util.Collection;
import java.util.List;

public class DictUtils {
   public static final String SEPARATOR = ",";

   public static void setDictCache(String key, List dictDatas) {
      ((RedisCache)SpringUtils.getBean(RedisCache.class)).setCacheObject(getCacheKey(key), dictDatas);
   }

   public static List getDictCache(String key) {
      Object cacheObj = ((RedisCache)SpringUtils.getBean(RedisCache.class)).getCacheObject(getCacheKey(key));
      if (StringUtils.isNotNull(cacheObj)) {
         List<SysDictData> dictDatas = (List)StringUtils.cast(cacheObj);
         return dictDatas;
      } else {
         return null;
      }
   }

   public static String getDictLabel(String dictType, String dictValue) {
      return getDictLabel(dictType, dictValue, ",");
   }

   public static String getDictValue(String dictType, String dictLabel) {
      return getDictValue(dictType, dictLabel, ",");
   }

   public static String getDictLabel(String dictType, String dictValue, String separator) {
      StringBuilder propertyString = new StringBuilder();
      List<SysDictData> datas = getDictCache(dictType);
      if (StringUtils.containsAny(separator, dictValue) && StringUtils.isNotEmpty((Collection)datas)) {
         for(SysDictData dict : datas) {
            for(String value : dictValue.split(separator)) {
               if (value.equals(dict.getDictValue())) {
                  propertyString.append(dict.getDictLabel() + separator);
                  break;
               }
            }
         }
      } else {
         for(SysDictData dict : datas) {
            if (dictValue.equals(dict.getDictValue())) {
               return dict.getDictLabel();
            }
         }
      }

      return StringUtils.stripEnd(propertyString.toString(), separator);
   }

   public static String getDictValue(String dictType, String dictLabel, String separator) {
      StringBuilder propertyString = new StringBuilder();
      List<SysDictData> datas = getDictCache(dictType);
      if (StringUtils.containsAny(separator, dictLabel) && StringUtils.isNotEmpty((Collection)datas)) {
         for(SysDictData dict : datas) {
            for(String label : dictLabel.split(separator)) {
               if (label.equals(dict.getDictLabel())) {
                  propertyString.append(dict.getDictValue() + separator);
                  break;
               }
            }
         }
      } else {
         for(SysDictData dict : datas) {
            if (dictLabel.equals(dict.getDictLabel())) {
               return dict.getDictValue();
            }
         }
      }

      return StringUtils.stripEnd(propertyString.toString(), separator);
   }

   public static void removeDictCache(String key) {
      ((RedisCache)SpringUtils.getBean(RedisCache.class)).deleteObject(getCacheKey(key));
   }

   public static void clearDictCache() {
      Collection<String> keys = ((RedisCache)SpringUtils.getBean(RedisCache.class)).keys("sys_dict:*");
      ((RedisCache)SpringUtils.getBean(RedisCache.class)).deleteObject(keys);
   }

   public static String getCacheKey(String configKey) {
      return "sys_dict:" + configKey;
   }
}
