package com.emr.framework.util;

import com.emr.common.utils.sign.Base64;
import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncodeUtil {
   public static String TRANSFORMATION_DES = "DES";
   public static String TRANSFORMATION_AES = "AES";
   public static String TRANSFORMATION_TRIPLEDES = "TripleDes";

   private static String extracted(String text, String key, String transformation, boolean mode) throws Exception {
      Cipher cipher = Cipher.getInstance(transformation);
      SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), transformation);
      cipher.init(mode ? 1 : 2, secretKeySpec);
      byte[] bytes = cipher.doFinal(mode ? text.getBytes(StandardCharsets.UTF_8) : Base64.decode(text));
      return mode ? Base64.encode(bytes) : new String(bytes);
   }

   public static String extractedDesE(String text, String key) throws Exception {
      String resStr = extracted(text, key, TRANSFORMATION_DES, true);
      return resStr;
   }

   public static String extractedDesD(String text, String key) throws Exception {
      String resStr = extracted(text, key, TRANSFORMATION_DES, false);
      return resStr;
   }

   public static String extractedAesE(String text, String key) throws Exception {
      String resStr = extracted(text, key, TRANSFORMATION_AES, true);
      return resStr;
   }

   public static String extractedTripleDesD(String text, String key) throws Exception {
      String resStr = extracted(text, key, TRANSFORMATION_TRIPLEDES, false);
      return resStr;
   }

   public static String extractedTripleDesE(String text, String key) throws Exception {
      String resStr = extracted(text, key, TRANSFORMATION_TRIPLEDES, true);
      return resStr;
   }

   public static String extractedAesD(String text, String key) throws Exception {
      String resStr = extracted(text, key, TRANSFORMATION_AES, false);
      return resStr;
   }

   public static void main(String[] args) {
      String text = "你好世界！！";
      String key = "12345678";
      String transformation = "DES";
      String transformation2 = "AES";
      String transformation3 = "TripleDes";

      try {
         String extracted = extracted(text, key, transformation, true);
         System.out.println("DES加密：" + extracted);
         String extracted1 = extracted(extracted, key, transformation, false);
         System.out.println("解密：" + extracted1);
      } catch (Exception e) {
         e.printStackTrace();
      }

   }
}
