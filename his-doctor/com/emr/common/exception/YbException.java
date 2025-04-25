package com.emr.common.exception;

public class YbException extends RuntimeException {
   private static final long serialVersionUID = 1254610410171016211L;
   private String message;

   public YbException() {
   }

   public YbException(String message) {
      this.message = message;
   }

   public String getMessage() {
      return this.message;
   }

   public YbException setMessage(String message) {
      this.message = message;
      return this;
   }
}
