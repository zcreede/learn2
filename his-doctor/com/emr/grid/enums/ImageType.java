package com.emr.grid.enums;

public enum ImageType {
   greitBMP(1),
   greitPNG(2),
   greitJPEG(3),
   greitTIFF(5);

   private int value;

   public int getValue() {
      return this.value;
   }

   private ImageType(int value) {
      this.value = value;
   }
}
