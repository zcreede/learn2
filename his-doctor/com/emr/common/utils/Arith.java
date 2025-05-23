package com.emr.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Arith {
   private static final int DEF_DIV_SCALE = 10;

   private Arith() {
   }

   public static double add(double v1, double v2) {
      BigDecimal b1 = new BigDecimal(Double.toString(v1));
      BigDecimal b2 = new BigDecimal(Double.toString(v2));
      return b1.add(b2).doubleValue();
   }

   public static double sub(double v1, double v2) {
      BigDecimal b1 = new BigDecimal(Double.toString(v1));
      BigDecimal b2 = new BigDecimal(Double.toString(v2));
      return b1.subtract(b2).doubleValue();
   }

   public static double mul(double v1, double v2) {
      BigDecimal b1 = new BigDecimal(Double.toString(v1));
      BigDecimal b2 = new BigDecimal(Double.toString(v2));
      return b1.multiply(b2).doubleValue();
   }

   public static double div(double v1, double v2) {
      return div(v1, v2, 10);
   }

   public static double div(double v1, double v2, int scale) {
      if (scale < 0) {
         throw new IllegalArgumentException("The scale must be a positive integer or zero");
      } else {
         BigDecimal b1 = new BigDecimal(Double.toString(v1));
         BigDecimal b2 = new BigDecimal(Double.toString(v2));
         return b1.compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.ZERO.doubleValue() : b1.divide(b2, scale, RoundingMode.HALF_UP).doubleValue();
      }
   }

   public static double round(double v, int scale) {
      if (scale < 0) {
         throw new IllegalArgumentException("The scale must be a positive integer or zero");
      } else {
         BigDecimal b = new BigDecimal(Double.toString(v));
         BigDecimal one = new BigDecimal("1");
         return b.divide(one, scale, RoundingMode.HALF_UP).doubleValue();
      }
   }
}
