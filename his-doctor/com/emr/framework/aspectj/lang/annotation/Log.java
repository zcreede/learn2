package com.emr.framework.aspectj.lang.annotation;

import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.aspectj.lang.enums.OperatorType;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
   String title() default "";

   BusinessType businessType() default BusinessType.OTHER;

   OperatorType operatorType() default OperatorType.MANAGE;

   boolean isSaveRequestData() default true;
}
