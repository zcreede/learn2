package com.emr.common.exception.user;

public class CaptchaExpireException extends UserException {
   private static final long serialVersionUID = 1L;

   public CaptchaExpireException() {
      super("user.jcaptcha.expire", (Object[])null);
   }
}
