package com.emr.common.utils;

import com.emr.common.utils.sign.Md5Utils;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Md5PasswordEncoder implements PasswordEncoder {
   public String encode(CharSequence rawPassword) {
      return Md5Utils.hash(rawPassword.toString());
   }

   public boolean matches(CharSequence rawPassword, String encodedPassword) {
      return Md5Utils.hash(rawPassword.toString()).equals(encodedPassword);
   }
}
