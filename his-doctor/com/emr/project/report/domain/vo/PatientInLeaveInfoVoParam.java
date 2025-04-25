package com.emr.project.report.domain.vo;

import com.emr.framework.web.domain.BaseEntity;
import java.util.List;

public class PatientInLeaveInfoVoParam extends BaseEntity {
   public String queryType;
   public String hospitalizedDateBegin;
   public String hospitalizedDateEnd;
   public List deptCodeList;
   public String searchName;
   public Integer pageNum;
   public Integer pageSize;
   public String residentName;

   public String getQueryType() {
      return this.queryType;
   }

   public void setQueryType(String queryType) {
      this.queryType = queryType;
   }

   public String getHospitalizedDateBegin() {
      return this.hospitalizedDateBegin;
   }

   public void setHospitalizedDateBegin(String hospitalizedDateBegin) {
      this.hospitalizedDateBegin = hospitalizedDateBegin;
   }

   public String getHospitalizedDateEnd() {
      return this.hospitalizedDateEnd;
   }

   public void setHospitalizedDateEnd(String hospitalizedDateEnd) {
      this.hospitalizedDateEnd = hospitalizedDateEnd;
   }

   public String getSearchName() {
      return this.searchName;
   }

   public void setSearchName(String searchName) {
      this.searchName = searchName;
   }

   public Integer getPageNum() {
      return this.pageNum;
   }

   public void setPageNum(Integer pageNum) {
      this.pageNum = pageNum;
   }

   public Integer getPageSize() {
      return this.pageSize;
   }

   public void setPageSize(Integer pageSize) {
      this.pageSize = pageSize;
   }

   public String getResidentName() {
      return this.residentName;
   }

   public void setResidentName(String residentName) {
      this.residentName = residentName;
   }
}
