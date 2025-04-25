package com.emr.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;

public class EncryptPSWUtil {
   public static String createEncryptPSW(String psw) {
      MessageDigest messagedigest = null;

      try {
         messagedigest = MessageDigest.getInstance("MD5");
         messagedigest.update(psw.getBytes("UTF8"));
         byte[] abyte0 = messagedigest.digest();
         return (new BASE64Encoder()).encode(abyte0);
      } catch (NoSuchAlgorithmException e) {
         throw new RuntimeException("密码加密出现异常!", e);
      } catch (UnsupportedEncodingException e) {
         throw new RuntimeException("密码加密出现异常!", e);
      }
   }
}
