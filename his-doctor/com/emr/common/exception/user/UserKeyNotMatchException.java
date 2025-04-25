package com.emr.common.exception.user;

public class UserKeyNotMatchException extends UserException {
   private static final long serialVersionUID = 1L;

   public UserKeyNotMatchException() {
      super("user.key.not.match", (Object[])null);
   }
}
