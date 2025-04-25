package com.emr.common.utils;

public class CardNoGenUtil {
   public static final int DEFAULT_LENGTH = 8;

   public static String getSequence(long seq) {
      String str = String.valueOf(seq);
      int len = str.length();
      if (len >= 8) {
         return str;
      } else {
         int rest = 8 - len;
         StringBuilder sb = new StringBuilder();

         for(int i = 0; i < rest; ++i) {
            sb.append('0');
         }

         sb.append(str);
         return sb.toString();
      }
   }
}
