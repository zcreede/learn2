package com.emr.grid.enums;

public enum ExportType {
   gretXLS(1),
   gretTXT(2),
   gretHTM(3),
   gretRTF(4),
   gretPDF(5),
   gretCSV(6),
   gretIMG(7);

   private int value;

   public int getValue() {
      return this.value;
   }

   private ExportType(int value) {
      this.value = value;
   }
}
