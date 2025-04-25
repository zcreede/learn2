package com.emr.framework.aspectj.lang.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Excel {
   int sort() default Integer.MAX_VALUE;

   String name() default "";

   String dateFormat() default "";

   String dictType() default "";

   String readConverterExp() default "";

   String separator() default ",";

   int scale() default -1;

   int roundingMode() default 6;

   ColumnType cellType() default Excel.ColumnType.STRING;

   double height() default (double)14.0F;

   double width() default (double)16.0F;

   String suffix() default "";

   String defaultValue() default "";

   String prompt() default "";

   String[] combo() default {};

   boolean isExport() default true;

   String targetAttr() default "";

   boolean isStatistics() default false;

   Align align() default Excel.Align.AUTO;

   Type type() default Excel.Type.ALL;

   public static enum Align {
      AUTO(0),
      LEFT(1),
      CENTER(2),
      RIGHT(3);

      private final int value;

      private Align(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }

   public static enum Type {
      ALL(0),
      EXPORT(1),
      IMPORT(2);

      private final int value;

      private Type(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }

   public static enum ColumnType {
      NUMERIC(0),
      STRING(1),
      IMAGE(2);

      private final int value;

      private ColumnType(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
