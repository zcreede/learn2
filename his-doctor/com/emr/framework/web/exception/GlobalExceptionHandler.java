package com.emr.framework.web.exception;

import com.emr.common.exception.BaseException;
import com.emr.common.exception.CustomException;
import com.emr.common.exception.DemoModeException;
import com.emr.common.utils.StringUtils;
import com.emr.framework.web.domain.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
   private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

   @ExceptionHandler({BaseException.class})
   public AjaxResult baseException(BaseException e) {
      return AjaxResult.error(e.getMessage());
   }

   @ExceptionHandler({CustomException.class})
   public AjaxResult businessException(CustomException e) {
      return StringUtils.isNull(e.getCode()) ? AjaxResult.error(e.getMessage()) : AjaxResult.error(e.getCode(), e.getMessage());
   }

   @ExceptionHandler({NoHandlerFoundException.class})
   public AjaxResult handlerNoFoundException(Exception e) {
      log.error(e.getMessage(), e);
      return AjaxResult.error(404, "路径不存在，请检查路径是否正确");
   }

   @ExceptionHandler({AccessDeniedException.class})
   public AjaxResult handleAuthorizationException(AccessDeniedException e) {
      log.error(e.getMessage());
      return AjaxResult.error(403, "没有权限，请联系管理员授权");
   }

   @ExceptionHandler({AccountExpiredException.class})
   public AjaxResult handleAccountExpiredException(AccountExpiredException e) {
      log.error(e.getMessage(), e);
      return AjaxResult.error(e.getMessage());
   }

   @ExceptionHandler({UsernameNotFoundException.class})
   public AjaxResult handleUsernameNotFoundException(UsernameNotFoundException e) {
      log.error(e.getMessage(), e);
      return AjaxResult.error(e.getMessage());
   }

   @ExceptionHandler({Exception.class})
   public AjaxResult handleException(Exception e) {
      log.error(e.getMessage(), e);
      return AjaxResult.error(e.getMessage());
   }

   @ExceptionHandler({BindException.class})
   public AjaxResult validatedBindException(BindException e) {
      log.error(e.getMessage(), e);
      String message = ((ObjectError)e.getAllErrors().get(0)).getDefaultMessage();
      return AjaxResult.error(message);
   }

   @ExceptionHandler({MethodArgumentNotValidException.class})
   public Object validExceptionHandler(MethodArgumentNotValidException e) {
      log.error(e.getMessage(), e);
      String message = e.getBindingResult().getFieldError().getDefaultMessage();
      return AjaxResult.error(message);
   }

   @ExceptionHandler({DemoModeException.class})
   public AjaxResult demoModeException(DemoModeException e) {
      return AjaxResult.error("演示模式，不允许操作");
   }
}
