package com.emr.project.docOrder.util;

public class OrderUtil {
   public static String getNumStr(int num) {
      String numStr = String.valueOf(num);
      if (num < 10) {
         numStr = "0" + numStr;
      }

      return numStr;
   }
}
