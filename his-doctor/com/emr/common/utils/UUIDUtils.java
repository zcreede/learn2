package com.emr.common.utils;

import com.emr.common.core.lang.UUID;

public class UUIDUtils {
   public static String randomUUID() {
      return UUID.randomUUID().toString();
   }

   public static String simpleUUID() {
      return UUID.randomUUID().toString(true);
   }

   public static String fastUUID() {
      return UUID.fastUUID().toString();
   }

   public static String fastSimpleUUID() {
      return UUID.fastUUID().toString(true);
   }
}
