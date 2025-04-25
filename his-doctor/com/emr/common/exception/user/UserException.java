package com.emr.common.exception.user;

import com.emr.common.exception.BaseException;

public class UserException extends BaseException {
   private static final long serialVersionUID = 1L;

   public UserException(String code, Object[] args) {
      super("user", code, args, (String)null);
   }
}
