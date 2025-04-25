package com.emr.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class ExceptionUtil {
   public static String getExceptionMessage(Throwable e) {
      StringWriter sw = new StringWriter();
      e.printStackTrace(new PrintWriter(sw, true));
      String str = sw.toString();
      return str;
   }

   public static String getRootErrorMessage(Exception e) {
      Throwable root = ExceptionUtils.getRootCause(e);
      root = (Throwable)(root == null ? e : root);
      if (root == null) {
         return "";
      } else {
         String msg = root.getMessage();
         return msg == null ? "null" : StringUtils.defaultString(msg);
      }
   }
}
