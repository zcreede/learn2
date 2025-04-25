package com.emr.grid.enums;

public enum GenerateStyle {
   grpgsOnlyForm(1),
   grpgsOnlyContent(2),
   grpgsAll(3),
   grpgsPreviewAll(4);

   private int value;

   private GenerateStyle(int value) {
      this.value = value;
   }

   public int getValue() {
      return this.value;
   }
}
