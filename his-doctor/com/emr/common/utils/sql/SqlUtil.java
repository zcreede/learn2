package com.emr.common.utils.sql;

import com.emr.common.exception.BaseException;
import com.emr.common.utils.StringUtils;

public class SqlUtil {
   public static String SQL_PATTERN = "[a-zA-Z0-9_\\ \\,\\.]+";

   public static String escapeOrderBySql(String value) {
      if (StringUtils.isNotEmpty(value) && !isValidOrderBySql(value)) {
         throw new BaseException("参数不符合规范，不能进行查询");
      } else {
         return value;
      }
   }

   public static boolean isValidOrderBySql(String value) {
      return value.matches(SQL_PATTERN);
   }
}
