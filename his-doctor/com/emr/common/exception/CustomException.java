package com.emr.common.exception;

public class CustomException extends RuntimeException {
   private static final long serialVersionUID = 1L;
   private Integer code;
   private String message;

   public CustomException(String message) {
      this.message = message;
   }

   public CustomException(String message, Integer code) {
      this.message = message;
      this.code = code;
   }

   public CustomException(String message, Throwable e) {
      super(message, e);
      this.message = message;
   }

   public String getMessage() {
      return this.message;
   }

   public Integer getCode() {
      return this.code;
   }
}
