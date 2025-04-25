package com.emr.common.exception.user;

import java.util.Arrays;

public class CaptchaException extends UserException {
   private static final long serialVersionUID = 1L;

   public CaptchaException() {
      super("user.jcaptcha.error", (Object[])null);
   }

   public CaptchaException(String msg) {
      super("user.jcaptcha.error", Arrays.asList(msg).toArray());
   }
}
