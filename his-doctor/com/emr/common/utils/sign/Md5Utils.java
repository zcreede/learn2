package com.emr.common.utils.sign;

import java.security.MessageDigest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

public class Md5Utils {
   private static final Logger log = LoggerFactory.getLogger(Md5Utils.class);

   private static byte[] md5(String s) {
      try {
         MessageDigest messagedigest = MessageDigest.getInstance("MD5");
         messagedigest.update(s.getBytes("UTF8"));
         byte[] abyte0 = messagedigest.digest();
         return abyte0;
      } catch (Exception e) {
         log.error("MD5 Error...", e);
         return null;
      }
   }

   private static final String toHex(byte[] hash) {
      if (hash == null) {
         return null;
      } else {
         StringBuffer buf = new StringBuffer(hash.length * 2);

         for(int i = 0; i < hash.length; ++i) {
            if ((hash[i] & 255) < 16) {
               buf.append("0");
            }

            buf.append(Long.toString((long)(hash[i] & 255), 16));
         }

         return buf.toString();
      }
   }

   public static String hash(String s) {
      try {
         return (new BASE64Encoder()).encode(md5(s));
      } catch (Exception e) {
         log.error("not supported charset...{}", e);
         return s;
      }
   }

   public static void main(String[] args) {
      System.out.println(hash("admin"));
   }
}
