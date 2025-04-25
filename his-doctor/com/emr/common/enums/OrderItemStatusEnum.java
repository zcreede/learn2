package com.emr.common.enums;

import com.emr.common.utils.StringUtils;

public enum OrderItemStatusEnum {
   STATUS_01("待发送", "01"),
   STATUS_02("已发送", "02"),
   STATUS_03("已收费", "03"),
   STATUS_04("已发药", "04"),
   STATUS_05("已执行", "05"),
   STATUS_09("报告已出", "09"),
   STATUS_11("作废", "11"),
   STATUS_12("申请退费", "12"),
   STATUS_13("已退费", "13");

   private String name;
   private String code;

   private OrderItemStatusEnum(String name, String code) {
      this.name = name;
      this.code = code;
   }

   public String getName() {
      return this.name;
   }

   public String getCode() {
      return this.code;
   }

   public static String getOrderItemStatusName(String orderStatus) {
      if (StringUtils.isEmpty(orderStatus)) {
         return "";
      } else {
         for(OrderItemStatusEnum infoEnum : values()) {
            if (orderStatus.equals(infoEnum.getCode())) {
               return infoEnum.getName();
            }
         }

         return "";
      }
   }
}
