package com.emr.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import net.sourceforge.pinyin4j.PinyinHelper;

public class PinYinUtil {
   public static String getPinYinHeadChar(String str) {
      String convert = "";

      for(int j = 0; j < str.length(); ++j) {
         char word = str.charAt(j);
         String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
         if (pinyinArray != null) {
            convert = convert + pinyinArray[0].charAt(0);
         } else {
            convert = convert + word;
         }
      }

      return convert;
   }

   public static void main(String[] args) {
      System.out.println(getPinYinHeadChar("医院名称"));
      Claims claims = (Claims)Jwts.parser().setSigningKey("abcdefghijklmnopqrstuvwxyz").parseClaimsJws("eyJhbGciOiJIUzUxMiJ9.eyJsb2dpbl91c2VyX2tleSI6IjY0MTA0OTM3LTg5NDItNDY1NS05ZTI0LTg5MTA5ZDhkZTUxOSJ9.4_E0_rK8TrCe9ylDni9oHgqgqNKZOF3f0GxfwG4I2b7bi_olq86-Fj4YH2rUXzdr6-JadCJUNXjtdcekPkXvmA").getBody();
      String uuid = (String)claims.get("login_user_key");
      System.out.println(uuid);
   }
}
