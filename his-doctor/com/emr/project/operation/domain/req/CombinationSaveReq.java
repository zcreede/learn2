package com.emr.project.operation.domain.req;

import java.util.ArrayList;
import java.util.List;

public class CombinationSaveReq {
   private String shareClass;
   private String packageName;
   private String packagePym;
   private List list = new ArrayList();

   public String getPackagePym() {
      return this.packagePym;
   }

   public void setPackagePym(String packagePym) {
      this.packagePym = packagePym;
   }

   public String getShareClass() {
      return this.shareClass;
   }

   public void setShareClass(String shareClass) {
      this.shareClass = shareClass;
   }

   public String getPackageName() {
      return this.packageName;
   }

   public void setPackageName(String packageName) {
      this.packageName = packageName;
   }

   public List getList() {
      return this.list;
   }

   public void setList(List list) {
      this.list = list;
   }
}
