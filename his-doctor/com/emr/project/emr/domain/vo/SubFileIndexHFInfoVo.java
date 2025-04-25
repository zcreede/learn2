package com.emr.project.emr.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class SubFileIndexHFInfoVo {
   private Long subFileIndexId;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date recoDate;
   private Boolean isPageFirstLine;
   private Integer beginPage;
   private Integer endPage;
   private String regionName;

   public String getRegionName() {
      return this.regionName;
   }

   public void setRegionName(String regionName) {
      this.regionName = regionName;
   }

   public Long getSubFileIndexId() {
      return this.subFileIndexId;
   }

   public void setSubFileIndexId(Long subFileIndexId) {
      this.subFileIndexId = subFileIndexId;
   }

   public Date getRecoDate() {
      return this.recoDate;
   }

   public void setRecoDate(Date recoDate) {
      this.recoDate = recoDate;
   }

   public Boolean getIsPageFirstLine() {
      return this.isPageFirstLine;
   }

   public void setIsPageFirstLine(Boolean pageFirstLine) {
      this.isPageFirstLine = pageFirstLine;
   }

   public Integer getBeginPage() {
      return this.beginPage;
   }

   public void setBeginPage(Integer beginPage) {
      this.beginPage = beginPage;
   }

   public Integer getEndPage() {
      return this.endPage;
   }

   public void setEndPage(Integer endPage) {
      this.endPage = endPage;
   }
}
