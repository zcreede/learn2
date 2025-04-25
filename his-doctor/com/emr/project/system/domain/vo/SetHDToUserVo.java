package com.emr.project.system.domain.vo;

import com.emr.project.system.domain.SysDept;
import com.emr.project.system.domain.TmBsModule;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(
   value = "SetHDToUserVo",
   description = "设置用户对应的院区和科室信息对象"
)
public class SetHDToUserVo {
   @ApiModelProperty("院区")
   private SysDept hospital;
   @ApiModelProperty("部门")
   private SysDept dept;
   private TmBsModule module;

   public TmBsModule getModule() {
      return this.module;
   }

   public void setModule(TmBsModule module) {
      this.module = module;
   }

   public SysDept getHospital() {
      return this.hospital;
   }

   public void setHospital(SysDept hospital) {
      this.hospital = hospital;
   }

   public SysDept getDept() {
      return this.dept;
   }

   public void setDept(SysDept dept) {
      this.dept = dept;
   }
}
