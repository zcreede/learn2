package com.emr.project.webservice.utils;

import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESHelper {
   public static final String PASSWORD = "Lanbo...";

   public static void main(String[] args) {
      String content = "手麻系统";
      byte[] encryptResult = encrypt(content, "Lanbo...");
      String encryptResultStr = parseByte2HexStr(encryptResult);
      System.out.println("加密后：" + encryptResultStr);
      byte[] decryptFrom = parseHexStr2Byte(encryptResultStr);
      byte[] decryptResult = decrypt(decryptFrom, "Lanbo...");
      System.out.println("解密后：" + new String(decryptResult));
   }

   public static byte[] encrypt(String content, String password) {
      try {
         KeyGenerator kgen = KeyGenerator.getInstance("AES");
         kgen.init(128, new SecureRandom(password.getBytes()));
         SecretKey secretKey = kgen.generateKey();
         byte[] enCodeFormat = secretKey.getEncoded();
         SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
         Cipher cipher = Cipher.getInstance("AES");
         byte[] byteContent = content.getBytes("utf-8");
         cipher.init(1, key);
         byte[] result = cipher.doFinal(byteContent);
         return result;
      } catch (Exception e) {
         e.printStackTrace();
         return null;
      }
   }

   public static byte[] decrypt(byte[] content, String password) {
      try {
         KeyGenerator kgen = KeyGenerator.getInstance("AES");
         kgen.init(128, new SecureRandom(password.getBytes()));
         SecretKey secretKey = kgen.generateKey();
         byte[] enCodeFormat = secretKey.getEncoded();
         SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
         Cipher cipher = Cipher.getInstance("AES");
         cipher.init(2, key);
         byte[] result = cipher.doFinal(content);
         return result;
      } catch (Exception e) {
         e.printStackTrace();
         return null;
      }
   }

   public static String parseByte2HexStr(byte[] buf) {
      StringBuffer sb = new StringBuffer();

      for(int i = 0; i < buf.length; ++i) {
         String hex = Integer.toHexString(buf[i] & 255);
         if (hex.length() == 1) {
            hex = '0' + hex;
         }

         sb.append(hex.toUpperCase());
      }

      return sb.toString();
   }

   public static byte[] parseHexStr2Byte(String hexStr) {
      if (hexStr.length() < 1) {
         return null;
      } else {
         byte[] result = new byte[hexStr.length() / 2];

         for(int i = 0; i < hexStr.length() / 2; ++i) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte)(high * 16 + low);
         }

         return result;
      }
   }
}
