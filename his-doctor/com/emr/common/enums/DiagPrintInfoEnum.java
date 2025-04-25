package com.emr.common.enums;

import com.emr.common.utils.StringUtils;

public enum DiagPrintInfoEnum {
   DIAG_CLASS_01("主要诊断", "01"),
   DIAG_CLASS_02("其他诊断", "02"),
   DIAG_CLASS_11("主病", "11"),
   DIAG_CLASS_12("主证", "12"),
   DIAG_CLASS_13("其他主病", "13"),
   DIAG_CLASS_14("其他主证", "14");

   private String name;
   private String code;

   private DiagPrintInfoEnum(String name, String code) {
      this.name = name;
      this.code = code;
   }

   public String getName() {
      return this.name;
   }

   public String getCode() {
      return this.code;
   }

   public static String getDiagClassName(String diagClass) {
      if (StringUtils.isEmpty(diagClass)) {
         return "";
      } else {
         for(DiagPrintInfoEnum infoEnum : values()) {
            if (diagClass.equals(infoEnum.getCode())) {
               return infoEnum.getName();
            }
         }

         return "";
      }
   }
}
