package com.emr.project.sys.domain;

import com.emr.common.utils.StringUtils;

public enum OpeTypeEnum {
   OPERATION("手术", "1"),
   DIAGNOSTIC_OPERATION("诊断性操作", "2"),
   THERAPEUTIC_OPERATION("治疗性操作", "3"),
   INTERVENTIONAL_THERAPY("介入治疗", "4");

   private String typeName;
   private String typeCode;

   public String getTypeName() {
      return this.typeName;
   }

   public void setTypeName(String typeName) {
      this.typeName = typeName;
   }

   public String getTypeCode() {
      return this.typeCode;
   }

   public void setTypeCode(String typeCode) {
      this.typeCode = typeCode;
   }

   private OpeTypeEnum(String typeName, String typeCode) {
      this.typeName = typeName;
      this.typeCode = typeCode;
   }

   public static String getTypeNameByCode(String typeCode) {
      if (StringUtils.isEmpty(typeCode)) {
         return null;
      } else {
         OpeTypeEnum[] values = values();

         for(OpeTypeEnum enums : values) {
            String code = enums.getTypeCode();
            if (code.equals(typeCode)) {
               return enums.getTypeName();
            }
         }

         return null;
      }
   }
}
