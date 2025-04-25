package com.emr.project.webservice.utils;

import com.emr.common.utils.StringUtils;
import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.codec.digest.DigestUtils;

public class SignUtil {
   public static String encryptString(HashMap params, String appKey) {
      List<String> list = Lists.newArrayList(params.keySet());
      Collections.sort(list);
      StringBuffer sign = new StringBuffer();

      for(String mapKey : list) {
         Object value = params.get(mapKey);
         if (!"sign".equals(mapKey) && !"projectCode".equals(mapKey) && null != value) {
            sign.append(mapKey).append("=").append(value).append("&");
         }
      }

      if (StringUtils.isEmpty((CharSequence)sign)) {
         return null;
      } else {
         sign.append("key=").append(appKey);
         return DigestUtils.md5Hex(sign.toString()).toUpperCase();
      }
   }

   public static String mapToString(Map params) {
      StringBuffer sb = new StringBuffer();

      for(Map.Entry entry : params.entrySet()) {
         String k = (String)entry.getKey();
         Object v = entry.getValue();
         sb.append(k + "=" + v + "&");
      }

      String res = sb.toString();
      if (StringUtils.isNotBlank(res)) {
         res = res.substring(0, res.length() - 1);
      }

      return res;
   }
}
