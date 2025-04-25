package com.emr.project.docOrder.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TdCaOperationApply extends BaseEntity {
   private static final long serialVersionUID = 1L;
   @Excel(
      name = "医疗机构编码 "
   )
   @ApiModelProperty("医疗机构编码 ")
   private String hospitalCode;
   @ApiModelProperty("申请单号")
   private String applyFormNo;
   @Excel(
      name = "医嘱编号"
   )
   @ApiModelProperty("医嘱编号")
   private String orderNo;
   @Excel(
      name = "患者id"
   )
   @ApiModelProperty("患者id")
   private String patientId;
   @Excel(
      name = "病案号"
   )
   @ApiModelProperty("病案号")
   private String caseNo;
   @Excel(
      name = "住院号 "
   )
   @ApiModelProperty("住院号 ")
   private String admissionNo;
   @Excel(
      name = "入院次数 "
   )
   @ApiModelProperty("入院次数 ")
   private Integer hospitalizedCount;
   @Excel(
      name = "患者姓名"
   )
   @ApiModelProperty("患者姓名")
   private String patientName;
   @Excel(
      name = "性别"
   )
   @ApiModelProperty("性别")
   private String sex;
   @Excel(
      name = "年龄"
   )
   @ApiModelProperty("年龄")
   private String age;
   @Excel(
      name = "申请科室代码"
   )
   @ApiModelProperty("申请科室代码")
   private String applyDeptCd;
   @Excel(
      name = "申请科室名称"
   )
   @ApiModelProperty("申请科室名称")
   private String applyDeptName;
   @Excel(
      name = "申请医师编码"
   )
   @ApiModelProperty("申请医师编码")
   private String applyDocCd;
   @Excel(
      name = "申请医师姓名"
   )
   @ApiModelProperty("申请医师姓名")
   private String applyDocName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "申请时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   @ApiModelProperty("申请时间")
   private Date applyDate;
   @Excel(
      name = "执行科室编码"
   )
   @ApiModelProperty("执行科室编码")
   private String execDeptCd;
   @Excel(
      name = "执行科室"
   )
   @ApiModelProperty("执行科室")
   private String execDeptName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "拟手术时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   @ApiModelProperty("拟手术时间")
   private Date planOperTime;
   @Excel(
      name = "手术类型代码",
      readConverterExp = "1=急诊2择期"
   )
   @ApiModelProperty("手术类型代码（1急诊2择期）")
   private String operTypeCd;
   @Excel(
      name = "非计划手术标志",
      readConverterExp = "0=计划手术；1非计划手术"
   )
   @ApiModelProperty("非计划手术标志（0计划手术；1非计划手术）")
   private String unplanOperFlag;
   @Excel(
      name = "重大手术标志"
   )
   @ApiModelProperty("重大手术标志")
   private String majorOperFlag;
   @Excel(
      name = "术前诊断1编码"
   )
   @ApiModelProperty("术前诊断1编码")
   private String preoperDiag1Cd;
   @Excel(
      name = "术前诊断1名称"
   )
   @ApiModelProperty("术前诊断1名称")
   private String preoperDiag1Name;
   @Excel(
      name = "术前诊断1前缀"
   )
   @ApiModelProperty("术前诊断1前缀")
   private String preoperDiag1Prefix;
   @Excel(
      name = "术前诊断1后缀"
   )
   @ApiModelProperty("术前诊断1后缀")
   private String preoperDiag1Suffix;
   @Excel(
      name = "术前诊断2编码"
   )
   @ApiModelProperty("术前诊断2编码")
   private String preoperDiag2Cd;
   @Excel(
      name = "术前诊断2名称"
   )
   @ApiModelProperty("术前诊断2名称")
   private String preoperDiag2Name;
   @Excel(
      name = "术前诊断2前缀"
   )
   @ApiModelProperty("术前诊断2前缀")
   private String preoperDiag2Prefix;
   @Excel(
      name = "术前诊断2后缀"
   )
   @ApiModelProperty("术前诊断2后缀")
   private String preoperDiag2Suffix;
   @Excel(
      name = "术前诊断3编码"
   )
   @ApiModelProperty("术前诊断3编码")
   private String preoperDiag3Cd;
   @Excel(
      name = "术前诊断3名称"
   )
   @ApiModelProperty("术前诊断3名称")
   private String preoperDiag3Name;
   @Excel(
      name = "术前诊断3前缀"
   )
   @ApiModelProperty("术前诊断3前缀")
   private String preoperDiag3Prefix;
   @Excel(
      name = "术前诊断3后缀"
   )
   @ApiModelProperty("术前诊断2后缀")
   private String preoperDiag3Suffix;
   @Excel(
      name = "术前诊断4编码"
   )
   @ApiModelProperty("术前诊断4编码")
   private String preoperDiag4Cd;
   @Excel(
      name = "术前诊断4名称"
   )
   @ApiModelProperty("术前诊断4名称")
   private String preoperDiag4Name;
   @Excel(
      name = "术前诊断4前缀"
   )
   @ApiModelProperty("术前诊断4前缀")
   private String preoperDiag4Prefix;
   @Excel(
      name = "术前诊断4后缀"
   )
   @ApiModelProperty("术前诊断4后缀")
   private String preoperDiag4Suffix;
   @Excel(
      name = "计划手术1编码"
   )
   @ApiModelProperty("计划手术1编码")
   private String planOper1Cd;
   @Excel(
      name = "计划手术1名称"
   )
   @ApiModelProperty("计划手术1名称")
   private String planOper1Name;
   @Excel(
      name = "计划手术1前缀"
   )
   @ApiModelProperty("计划手术1前缀")
   private String planOper1Prefix;
   @Excel(
      name = "计划手术1后缀"
   )
   @ApiModelProperty("计划手术1后缀")
   private String planOper1Suffix;
   @Excel(
      name = "计划手术2编码"
   )
   @ApiModelProperty("计划手术2编码")
   private String planOper2Cd;
   @Excel(
      name = "计划手术2名称"
   )
   @ApiModelProperty("计划手术2名称")
   private String planOper2Name;
   @Excel(
      name = "计划手术2前缀"
   )
   @ApiModelProperty("计划手术2前缀")
   private String planOper2Prefix;
   @Excel(
      name = "计划手术2后缀"
   )
   @ApiModelProperty("计划手术2后缀")
   private String planOper2Suffix;
   @Excel(
      name = "计划手术3编码"
   )
   @ApiModelProperty("计划手术3编码")
   private String planOper3Cd;
   @Excel(
      name = "计划手术3名称"
   )
   @ApiModelProperty("计划手术3名称")
   private String planOper3Name;
   @Excel(
      name = "计划手术3前缀"
   )
   @ApiModelProperty("计划手术3前缀")
   private String planOper3Prefix;
   @Excel(
      name = "计划手术3后缀"
   )
   @ApiModelProperty("计划手术3后缀")
   private String planOper3Suffix;
   @Excel(
      name = "计划手术4编码"
   )
   @ApiModelProperty("计划手术4编码")
   private String planOper4Cd;
   @Excel(
      name = "计划手术4名称"
   )
   @ApiModelProperty("计划手术4名称")
   private String planOper4Name;
   @Excel(
      name = "计划手术4前缀"
   )
   @ApiModelProperty("计划手术4前缀")
   private String planOper4Prefix;
   @Excel(
      name = "计划手术4后缀"
   )
   @ApiModelProperty("计划手术4后缀")
   private String planOper4Suffix;
   @Excel(
      name = "手术级别代码"
   )
   @ApiModelProperty("手术级别代码")
   private String operLevelCd;
   @Excel(
      name = "手术级别名称"
   )
   @ApiModelProperty("手术级别名称")
   private String operLevelName;
   @Excel(
      name = "切口类型代码"
   )
   @ApiModelProperty("切口类型代码")
   private String operInciCd;
   @Excel(
      name = "切口类型名称"
   )
   @ApiModelProperty("切口类型名称")
   private String operInciName;
   @Excel(
      name = "手术体位编码"
   )
   @ApiModelProperty("手术体位编码")
   private String operPositionCd;
   @Excel(
      name = "手术体位名称"
   )
   @ApiModelProperty("手术体位名称")
   private String operPositionName;
   @Excel(
      name = "手术部位代码"
   )
   @ApiModelProperty("手术部位代码")
   private String operSiteCd;
   @Excel(
      name = "手术部位名称"
   )
   @ApiModelProperty("手术部位名称")
   private String operSiteName;
   @Excel(
      name = "手术医师编码"
   )
   @ApiModelProperty("手术医师编码")
   private String operDocCd;
   @Excel(
      name = "手术医师名称"
   )
   @ApiModelProperty("手术医师名称")
   private String operDocName;
   @Excel(
      name = "手术一助编码"
   )
   @ApiModelProperty("手术一助编码")
   private String operAid1Cd;
   @Excel(
      name = "手术一助名称"
   )
   @ApiModelProperty("手术一助名称")
   private String operAid1Name;
   @Excel(
      name = "手术二助编码"
   )
   @ApiModelProperty("手术二助编码")
   private String operAid2Cd;
   @Excel(
      name = "手术二助名称"
   )
   @ApiModelProperty("手术二助名称")
   private String operAid2Name;
   @Excel(
      name = "手术三助编码"
   )
   @ApiModelProperty("手术三助编码")
   private String operAid3Cd;
   @Excel(
      name = "手术三助名称"
   )
   @ApiModelProperty("手术三助名称")
   private String operAid3Name;
   @Excel(
      name = "巡护1代码"
   )
   @ApiModelProperty("巡护1代码")
   private String circulatNurse1Cd;
   @Excel(
      name = "巡护1 姓名"
   )
   @ApiModelProperty("巡护1 姓名")
   private String circulatNurse1Name;
   @Excel(
      name = "巡护2代码"
   )
   @ApiModelProperty("巡护2代码")
   private String circulatNurse2Cd;
   @Excel(
      name = "巡护2 姓名"
   )
   @ApiModelProperty("巡护2 姓名")
   private String circulatNurse2Name;
   @Excel(
      name = "麻醉方式代码"
   )
   @ApiModelProperty("麻醉方式代码")
   private String anestMethCd;
   @Excel(
      name = "麻醉方式名称"
   )
   @ApiModelProperty("麻醉方式名称")
   private String anestMethName;
   @Excel(
      name = "麻醉医师编码"
   )
   @ApiModelProperty("麻醉医师编码")
   private String anestCd;
   @Excel(
      name = "麻醉医师姓名"
   )
   @ApiModelProperty("麻醉医师姓名")
   private String anestName;
   @Excel(
      name = "预计手术持续时间-小时"
   )
   @ApiModelProperty("预计手术持续时间-小时")
   private Long planOperDurationHour;
   @Excel(
      name = "持续时间--分钟"
   )
   @ApiModelProperty("持续时间--分钟")
   private Long planOperDurationMin;
   @Excel(
      name = "手术间"
   )
   @ApiModelProperty("手术间")
   private String operRoom;
   @Excel(
      name = "手术间"
   )
   @ApiModelProperty("手术间编码")
   private String operRoomCd;
   @Excel(
      name = "手术台"
   )
   @ApiModelProperty("手术台")
   private String operTable;
   @Excel(
      name = "ABO血型代码"
   )
   @ApiModelProperty("ABO血型代码")
   private String aboCd;
   @Excel(
      name = "ABO血型名称"
   )
   @ApiModelProperty("ABO血型名称")
   private String aboName;
   @Excel(
      name = "Rh血型代码"
   )
   @ApiModelProperty("Rh血型代码")
   private String rhCd;
   @Excel(
      name = "Rh血型名称"
   )
   @ApiModelProperty("Rh血型名称")
   private String rhName;
   @Excel(
      name = "HBV代码"
   )
   @ApiModelProperty("HBV代码")
   private String hbv;
   @Excel(
      name = "HIV代码"
   )
   @ApiModelProperty("HIV代码")
   private String hiv;
   @Excel(
      name = "HCV代码"
   )
   @ApiModelProperty("HCV代码")
   private String hcv;
   @Excel(
      name = "TP代码"
   )
   @ApiModelProperty("TP代码")
   private String tp;
   @Excel(
      name = "输红细胞量"
   )
   @ApiModelProperty("输红细胞量")
   private BigDecimal redBloodCell;
   @Excel(
      name = "血小板"
   )
   @ApiModelProperty("血小板")
   private BigDecimal platelet;
   @Excel(
      name = "血浆"
   )
   @ApiModelProperty("血浆")
   private BigDecimal plasma;
   @Excel(
      name = "全血"
   )
   @ApiModelProperty("全血")
   private BigDecimal wholeBlood;
   @Excel(
      name = "器械要求"
   )
   @ApiModelProperty("器械要求")
   private String deviceReq;
   @Excel(
      name = "其他注意事项"
   )
   @ApiModelProperty("其他注意事项")
   private String otherNote;
   @Excel(
      name = "参观人员"
   )
   @ApiModelProperty("参观人员")
   private String visitors;
   @Excel(
      name = "状态",
      readConverterExp = "0=1待提交(保存)02待审核（提交"
   )
   @ApiModelProperty("状态（01待提交(保存)02待审核（提交）03已作废  04驳回  05已审核（审核）06已安排 07 已完成(出室) 08入室）")
   private String status;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "实际手术时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   @ApiModelProperty("实际手术时间")
   private Date operDate;
   @Excel(
      name = "审核医师编码"
   )
   @ApiModelProperty("审核医师编码")
   private String auditDocCd;
   @Excel(
      name = "审核医师姓名"
   )
   @ApiModelProperty("审核医师姓名")
   private String auditDocName;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "审核时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   @ApiModelProperty("审核时间")
   private Date auditDate;
   @Excel(
      name = "手术排班护士编码"
   )
   @ApiModelProperty("手术排班护士编码")
   private String shiftNurseCd;
   @Excel(
      name = "手术排班护士姓名"
   )
   @ApiModelProperty("手术排班护士姓名")
   private String shiftNurseName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "排班时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   @ApiModelProperty("排班时间")
   private Date shiftDate;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "修改时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   @ApiModelProperty("修改时间")
   private Date altDate;
   @Excel(
      name = "修改编码"
   )
   @ApiModelProperty("修改编码")
   private String altPerCode;
   @Excel(
      name = "修改人姓名"
   )
   @ApiModelProperty("修改人姓名")
   private String altPerName;
   @Excel(
      name = "审核备注"
   )
   @ApiModelProperty("审核备注")
   private String auditRemark;
   @Excel(
      name = "HBV名称"
   )
   @ApiModelProperty("HBV名称")
   private String hbvName;
   @Excel(
      name = "HIV名称"
   )
   @ApiModelProperty("HIV名称")
   private String hivName;
   @Excel(
      name = "HCV名称"
   )
   @ApiModelProperty("HCV名称")
   private String hcvName;
   @Excel(
      name = "TP名称"
   )
   @ApiModelProperty("TP名称")
   private String tpName;
   @Excel(
      name = "器械护士编码1"
   )
   @ApiModelProperty("器械护士编码1")
   private String deviceNurseCd1;
   @Excel(
      name = "器械护士姓名1"
   )
   @ApiModelProperty("器械护士姓名1")
   private String deviceNurseName1;
   @Excel(
      name = "器械护士编码2"
   )
   @ApiModelProperty("器械护士编码2")
   private String deviceNurseCd2;
   @Excel(
      name = "器械护士姓名2"
   )
   @ApiModelProperty("器械护士姓名2")
   private String deviceNurseName2;
   @Excel(
      name = "麻醉助手編碼"
   )
   @ApiModelProperty("麻醉助手編碼")
   private String anestAidCd;
   @Excel(
      name = "麻醉助手姓名"
   )
   @ApiModelProperty("麻醉助手姓名")
   private String anestAidName;
   @Excel(
      name = "手术开始时间",
      readConverterExp = "手=麻返回"
   )
   @ApiModelProperty("手术开始时间（手麻返回）")
   private Date operBeginDate;
   @Excel(
      name = "手术结束时间",
      readConverterExp = "手=麻返回"
   )
   @ApiModelProperty("手术结束时间（手麻返回）")
   private Date operEndDate;
   @Excel(
      name = "最终手术名称",
      readConverterExp = "手=麻返回"
   )
   @ApiModelProperty("最终手术名称（手麻返回）")
   private String operName;
   @Excel(
      name = "手麻系统回传麻醉方式编码"
   )
   @ApiModelProperty("手麻系统回传麻醉方式编码")
   private Long opeAnestMethCd;
   @Excel(
      name = "手麻系统回传麻醉方式名称"
   )
   @ApiModelProperty("手麻系统回传麻醉方式名称")
   private String opeAnestMethName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "入室时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
   )
   @ApiModelProperty("入室时间")
   private Date inRoomTime;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "出室时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
   )
   @ApiModelProperty("出室时间")
   private Date outRoomTime;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "麻醉开始时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
   )
   @ApiModelProperty("麻醉开始时间")
   private Date anestStartTime;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "麻醉结束时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
   )
   @ApiModelProperty("麻醉结束时间")
   private Date anestEndTime;
   @Excel(
      name = "ASA分级编码"
   )
   @ApiModelProperty("ASA分级编码")
   private String asaTypeCode;
   @Excel(
      name = "ASA分级名称"
   )
   @ApiModelProperty("ASA分级名称")
   private String asaTypeName;

   public void setHospitalCode(String hospitalCode) {
      this.hospitalCode = hospitalCode;
   }

   public String getHospitalCode() {
      return this.hospitalCode;
   }

   public void setApplyFormNo(String applyFormNo) {
      this.applyFormNo = applyFormNo;
   }

   public String getApplyFormNo() {
      return this.applyFormNo;
   }

   public void setOrderNo(String orderNo) {
      this.orderNo = orderNo;
   }

   public String getOrderNo() {
      return this.orderNo;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setCaseNo(String caseNo) {
      this.caseNo = caseNo;
   }

   public String getCaseNo() {
      return this.caseNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public Integer getHospitalizedCount() {
      return this.hospitalizedCount;
   }

   public void setHospitalizedCount(Integer hospitalizedCount) {
      this.hospitalizedCount = hospitalizedCount;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setSex(String sex) {
      this.sex = sex;
   }

   public String getSex() {
      return this.sex;
   }

   public void setAge(String age) {
      this.age = age;
   }

   public String getAge() {
      return this.age;
   }

   public void setApplyDeptCd(String applyDeptCd) {
      this.applyDeptCd = applyDeptCd;
   }

   public String getApplyDeptCd() {
      return this.applyDeptCd;
   }

   public void setApplyDeptName(String applyDeptName) {
      this.applyDeptName = applyDeptName;
   }

   public String getApplyDeptName() {
      return this.applyDeptName;
   }

   public void setApplyDocCd(String applyDocCd) {
      this.applyDocCd = applyDocCd;
   }

   public String getApplyDocCd() {
      return this.applyDocCd;
   }

   public void setApplyDocName(String applyDocName) {
      this.applyDocName = applyDocName;
   }

   public String getApplyDocName() {
      return this.applyDocName;
   }

   public void setApplyDate(Date applyDate) {
      this.applyDate = applyDate;
   }

   public Date getApplyDate() {
      return this.applyDate;
   }

   public void setExecDeptCd(String execDeptCd) {
      this.execDeptCd = execDeptCd;
   }

   public String getExecDeptCd() {
      return this.execDeptCd;
   }

   public void setExecDeptName(String execDeptName) {
      this.execDeptName = execDeptName;
   }

   public String getExecDeptName() {
      return this.execDeptName;
   }

   public void setPlanOperTime(Date planOperTime) {
      this.planOperTime = planOperTime;
   }

   public Date getPlanOperTime() {
      return this.planOperTime;
   }

   public void setOperTypeCd(String operTypeCd) {
      this.operTypeCd = operTypeCd;
   }

   public String getOperTypeCd() {
      return this.operTypeCd;
   }

   public void setUnplanOperFlag(String unplanOperFlag) {
      this.unplanOperFlag = unplanOperFlag;
   }

   public String getUnplanOperFlag() {
      return this.unplanOperFlag;
   }

   public void setMajorOperFlag(String majorOperFlag) {
      this.majorOperFlag = majorOperFlag;
   }

   public String getMajorOperFlag() {
      return this.majorOperFlag;
   }

   public void setPreoperDiag1Cd(String preoperDiag1Cd) {
      this.preoperDiag1Cd = preoperDiag1Cd;
   }

   public String getPreoperDiag1Cd() {
      return this.preoperDiag1Cd;
   }

   public void setPreoperDiag1Name(String preoperDiag1Name) {
      this.preoperDiag1Name = preoperDiag1Name;
   }

   public String getPreoperDiag1Name() {
      return this.preoperDiag1Name;
   }

   public void setPreoperDiag1Prefix(String preoperDiag1Prefix) {
      this.preoperDiag1Prefix = preoperDiag1Prefix;
   }

   public String getPreoperDiag1Prefix() {
      return this.preoperDiag1Prefix;
   }

   public void setPreoperDiag1Suffix(String preoperDiag1Suffix) {
      this.preoperDiag1Suffix = preoperDiag1Suffix;
   }

   public String getPreoperDiag1Suffix() {
      return this.preoperDiag1Suffix;
   }

   public void setPreoperDiag2Cd(String preoperDiag2Cd) {
      this.preoperDiag2Cd = preoperDiag2Cd;
   }

   public String getPreoperDiag2Cd() {
      return this.preoperDiag2Cd;
   }

   public void setPreoperDiag2Name(String preoperDiag2Name) {
      this.preoperDiag2Name = preoperDiag2Name;
   }

   public String getPreoperDiag2Name() {
      return this.preoperDiag2Name;
   }

   public void setPreoperDiag2Prefix(String preoperDiag2Prefix) {
      this.preoperDiag2Prefix = preoperDiag2Prefix;
   }

   public String getPreoperDiag2Prefix() {
      return this.preoperDiag2Prefix;
   }

   public void setPreoperDiag2Suffix(String preoperDiag2Suffix) {
      this.preoperDiag2Suffix = preoperDiag2Suffix;
   }

   public String getPreoperDiag2Suffix() {
      return this.preoperDiag2Suffix;
   }

   public void setPreoperDiag3Cd(String preoperDiag3Cd) {
      this.preoperDiag3Cd = preoperDiag3Cd;
   }

   public String getPreoperDiag3Cd() {
      return this.preoperDiag3Cd;
   }

   public void setPreoperDiag3Name(String preoperDiag3Name) {
      this.preoperDiag3Name = preoperDiag3Name;
   }

   public String getPreoperDiag3Name() {
      return this.preoperDiag3Name;
   }

   public void setPreoperDiag3Prefix(String preoperDiag3Prefix) {
      this.preoperDiag3Prefix = preoperDiag3Prefix;
   }

   public String getPreoperDiag3Prefix() {
      return this.preoperDiag3Prefix;
   }

   public void setPreoperDiag3Suffix(String preoperDiag3Suffix) {
      this.preoperDiag3Suffix = preoperDiag3Suffix;
   }

   public String getPreoperDiag3Suffix() {
      return this.preoperDiag3Suffix;
   }

   public void setPreoperDiag4Cd(String preoperDiag4Cd) {
      this.preoperDiag4Cd = preoperDiag4Cd;
   }

   public String getPreoperDiag4Cd() {
      return this.preoperDiag4Cd;
   }

   public void setPreoperDiag4Name(String preoperDiag4Name) {
      this.preoperDiag4Name = preoperDiag4Name;
   }

   public String getPreoperDiag4Name() {
      return this.preoperDiag4Name;
   }

   public void setPreoperDiag4Prefix(String preoperDiag4Prefix) {
      this.preoperDiag4Prefix = preoperDiag4Prefix;
   }

   public String getPreoperDiag4Prefix() {
      return this.preoperDiag4Prefix;
   }

   public void setPreoperDiag4Suffix(String preoperDiag4Suffix) {
      this.preoperDiag4Suffix = preoperDiag4Suffix;
   }

   public String getPreoperDiag4Suffix() {
      return this.preoperDiag4Suffix;
   }

   public void setPlanOper1Cd(String planOper1Cd) {
      this.planOper1Cd = planOper1Cd;
   }

   public String getPlanOper1Cd() {
      return this.planOper1Cd;
   }

   public void setPlanOper1Name(String planOper1Name) {
      this.planOper1Name = planOper1Name;
   }

   public String getPlanOper1Name() {
      return this.planOper1Name;
   }

   public void setPlanOper1Prefix(String planOper1Prefix) {
      this.planOper1Prefix = planOper1Prefix;
   }

   public String getPlanOper1Prefix() {
      return this.planOper1Prefix;
   }

   public void setPlanOper1Suffix(String planOper1Suffix) {
      this.planOper1Suffix = planOper1Suffix;
   }

   public String getPlanOper1Suffix() {
      return this.planOper1Suffix;
   }

   public void setPlanOper2Cd(String planOper2Cd) {
      this.planOper2Cd = planOper2Cd;
   }

   public String getPlanOper2Cd() {
      return this.planOper2Cd;
   }

   public void setPlanOper2Name(String planOper2Name) {
      this.planOper2Name = planOper2Name;
   }

   public String getPlanOper2Name() {
      return this.planOper2Name;
   }

   public void setPlanOper2Prefix(String planOper2Prefix) {
      this.planOper2Prefix = planOper2Prefix;
   }

   public String getPlanOper2Prefix() {
      return this.planOper2Prefix;
   }

   public void setPlanOper2Suffix(String planOper2Suffix) {
      this.planOper2Suffix = planOper2Suffix;
   }

   public String getPlanOper2Suffix() {
      return this.planOper2Suffix;
   }

   public void setPlanOper3Cd(String planOper3Cd) {
      this.planOper3Cd = planOper3Cd;
   }

   public String getPlanOper3Cd() {
      return this.planOper3Cd;
   }

   public void setPlanOper3Name(String planOper3Name) {
      this.planOper3Name = planOper3Name;
   }

   public String getPlanOper3Name() {
      return this.planOper3Name;
   }

   public void setPlanOper3Prefix(String planOper3Prefix) {
      this.planOper3Prefix = planOper3Prefix;
   }

   public String getPlanOper3Prefix() {
      return this.planOper3Prefix;
   }

   public void setPlanOper3Suffix(String planOper3Suffix) {
      this.planOper3Suffix = planOper3Suffix;
   }

   public String getPlanOper3Suffix() {
      return this.planOper3Suffix;
   }

   public void setPlanOper4Cd(String planOper4Cd) {
      this.planOper4Cd = planOper4Cd;
   }

   public String getPlanOper4Cd() {
      return this.planOper4Cd;
   }

   public void setPlanOper4Name(String planOper4Name) {
      this.planOper4Name = planOper4Name;
   }

   public String getPlanOper4Name() {
      return this.planOper4Name;
   }

   public void setPlanOper4Prefix(String planOper4Prefix) {
      this.planOper4Prefix = planOper4Prefix;
   }

   public String getPlanOper4Prefix() {
      return this.planOper4Prefix;
   }

   public void setPlanOper4Suffix(String planOper4Suffix) {
      this.planOper4Suffix = planOper4Suffix;
   }

   public String getPlanOper4Suffix() {
      return this.planOper4Suffix;
   }

   public void setOperLevelCd(String operLevelCd) {
      this.operLevelCd = operLevelCd;
   }

   public String getOperLevelCd() {
      return this.operLevelCd;
   }

   public void setOperLevelName(String operLevelName) {
      this.operLevelName = operLevelName;
   }

   public String getOperLevelName() {
      return this.operLevelName;
   }

   public void setOperInciCd(String operInciCd) {
      this.operInciCd = operInciCd;
   }

   public String getOperInciCd() {
      return this.operInciCd;
   }

   public void setOperInciName(String operInciName) {
      this.operInciName = operInciName;
   }

   public String getOperInciName() {
      return this.operInciName;
   }

   public void setOperPositionCd(String operPositionCd) {
      this.operPositionCd = operPositionCd;
   }

   public String getOperPositionCd() {
      return this.operPositionCd;
   }

   public void setOperPositionName(String operPositionName) {
      this.operPositionName = operPositionName;
   }

   public String getOperPositionName() {
      return this.operPositionName;
   }

   public void setOperSiteCd(String operSiteCd) {
      this.operSiteCd = operSiteCd;
   }

   public String getOperSiteCd() {
      return this.operSiteCd;
   }

   public void setOperSiteName(String operSiteName) {
      this.operSiteName = operSiteName;
   }

   public String getOperSiteName() {
      return this.operSiteName;
   }

   public void setOperDocCd(String operDocCd) {
      this.operDocCd = operDocCd;
   }

   public String getOperDocCd() {
      return this.operDocCd;
   }

   public void setOperDocName(String operDocName) {
      this.operDocName = operDocName;
   }

   public String getOperDocName() {
      return this.operDocName;
   }

   public void setOperAid1Cd(String operAid1Cd) {
      this.operAid1Cd = operAid1Cd;
   }

   public String getOperAid1Cd() {
      return this.operAid1Cd;
   }

   public void setOperAid1Name(String operAid1Name) {
      this.operAid1Name = operAid1Name;
   }

   public String getOperAid1Name() {
      return this.operAid1Name;
   }

   public void setOperAid2Cd(String operAid2Cd) {
      this.operAid2Cd = operAid2Cd;
   }

   public String getOperAid2Cd() {
      return this.operAid2Cd;
   }

   public void setOperAid2Name(String operAid2Name) {
      this.operAid2Name = operAid2Name;
   }

   public String getOperAid2Name() {
      return this.operAid2Name;
   }

   public void setOperAid3Cd(String operAid3Cd) {
      this.operAid3Cd = operAid3Cd;
   }

   public String getOperAid3Cd() {
      return this.operAid3Cd;
   }

   public void setOperAid3Name(String operAid3Name) {
      this.operAid3Name = operAid3Name;
   }

   public String getOperAid3Name() {
      return this.operAid3Name;
   }

   public void setCirculatNurse1Cd(String circulatNurse1Cd) {
      this.circulatNurse1Cd = circulatNurse1Cd;
   }

   public String getCirculatNurse1Cd() {
      return this.circulatNurse1Cd;
   }

   public void setCirculatNurse1Name(String circulatNurse1Name) {
      this.circulatNurse1Name = circulatNurse1Name;
   }

   public String getCirculatNurse1Name() {
      return this.circulatNurse1Name;
   }

   public void setCirculatNurse2Cd(String circulatNurse2Cd) {
      this.circulatNurse2Cd = circulatNurse2Cd;
   }

   public String getCirculatNurse2Cd() {
      return this.circulatNurse2Cd;
   }

   public void setCirculatNurse2Name(String circulatNurse2Name) {
      this.circulatNurse2Name = circulatNurse2Name;
   }

   public String getCirculatNurse2Name() {
      return this.circulatNurse2Name;
   }

   public void setAnestMethCd(String anestMethCd) {
      this.anestMethCd = anestMethCd;
   }

   public String getAnestMethCd() {
      return this.anestMethCd;
   }

   public void setAnestMethName(String anestMethName) {
      this.anestMethName = anestMethName;
   }

   public String getAnestMethName() {
      return this.anestMethName;
   }

   public void setAnestCd(String anestCd) {
      this.anestCd = anestCd;
   }

   public String getAnestCd() {
      return this.anestCd;
   }

   public void setAnestName(String anestName) {
      this.anestName = anestName;
   }

   public String getAnestName() {
      return this.anestName;
   }

   public void setPlanOperDurationHour(Long planOperDurationHour) {
      this.planOperDurationHour = planOperDurationHour;
   }

   public Long getPlanOperDurationHour() {
      return this.planOperDurationHour;
   }

   public void setPlanOperDurationMin(Long planOperDurationMin) {
      this.planOperDurationMin = planOperDurationMin;
   }

   public Long getPlanOperDurationMin() {
      return this.planOperDurationMin;
   }

   public String getOperRoom() {
      return this.operRoom;
   }

   public void setOperRoom(String operRoom) {
      this.operRoom = operRoom;
   }

   public String getOperRoomCd() {
      return this.operRoomCd;
   }

   public void setOperRoomCd(String operRoomCd) {
      this.operRoomCd = operRoomCd;
   }

   public void setOperTable(String operTable) {
      this.operTable = operTable;
   }

   public String getOperTable() {
      return this.operTable;
   }

   public void setAboCd(String aboCd) {
      this.aboCd = aboCd;
   }

   public String getAboCd() {
      return this.aboCd;
   }

   public void setAboName(String aboName) {
      this.aboName = aboName;
   }

   public String getAboName() {
      return this.aboName;
   }

   public void setRhCd(String rhCd) {
      this.rhCd = rhCd;
   }

   public String getRhCd() {
      return this.rhCd;
   }

   public void setRhName(String rhName) {
      this.rhName = rhName;
   }

   public String getRhName() {
      return this.rhName;
   }

   public void setHbv(String hbv) {
      this.hbv = hbv;
   }

   public String getHbv() {
      return this.hbv;
   }

   public void setHiv(String hiv) {
      this.hiv = hiv;
   }

   public String getHiv() {
      return this.hiv;
   }

   public void setHcv(String hcv) {
      this.hcv = hcv;
   }

   public String getHcv() {
      return this.hcv;
   }

   public void setTp(String tp) {
      this.tp = tp;
   }

   public String getTp() {
      return this.tp;
   }

   public void setRedBloodCell(BigDecimal redBloodCell) {
      this.redBloodCell = redBloodCell;
   }

   public BigDecimal getRedBloodCell() {
      return this.redBloodCell;
   }

   public void setPlatelet(BigDecimal platelet) {
      this.platelet = platelet;
   }

   public BigDecimal getPlatelet() {
      return this.platelet;
   }

   public void setPlasma(BigDecimal plasma) {
      this.plasma = plasma;
   }

   public BigDecimal getPlasma() {
      return this.plasma;
   }

   public void setWholeBlood(BigDecimal wholeBlood) {
      this.wholeBlood = wholeBlood;
   }

   public BigDecimal getWholeBlood() {
      return this.wholeBlood;
   }

   public void setDeviceReq(String deviceReq) {
      this.deviceReq = deviceReq;
   }

   public String getDeviceReq() {
      return this.deviceReq;
   }

   public void setOtherNote(String otherNote) {
      this.otherNote = otherNote;
   }

   public String getOtherNote() {
      return this.otherNote;
   }

   public void setVisitors(String visitors) {
      this.visitors = visitors;
   }

   public String getVisitors() {
      return this.visitors;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String getStatus() {
      return this.status;
   }

   public void setOperDate(Date operDate) {
      this.operDate = operDate;
   }

   public Date getOperDate() {
      return this.operDate;
   }

   public void setAuditDocCd(String auditDocCd) {
      this.auditDocCd = auditDocCd;
   }

   public String getAuditDocCd() {
      return this.auditDocCd;
   }

   public void setAuditDocName(String auditDocName) {
      this.auditDocName = auditDocName;
   }

   public String getAuditDocName() {
      return this.auditDocName;
   }

   public void setAuditDate(Date auditDate) {
      this.auditDate = auditDate;
   }

   public Date getAuditDate() {
      return this.auditDate;
   }

   public void setShiftNurseCd(String shiftNurseCd) {
      this.shiftNurseCd = shiftNurseCd;
   }

   public String getShiftNurseCd() {
      return this.shiftNurseCd;
   }

   public void setShiftNurseName(String shiftNurseName) {
      this.shiftNurseName = shiftNurseName;
   }

   public String getShiftNurseName() {
      return this.shiftNurseName;
   }

   public void setShiftDate(Date shiftDate) {
      this.shiftDate = shiftDate;
   }

   public Date getShiftDate() {
      return this.shiftDate;
   }

   public void setAltDate(Date altDate) {
      this.altDate = altDate;
   }

   public Date getAltDate() {
      return this.altDate;
   }

   public void setAltPerCode(String altPerCode) {
      this.altPerCode = altPerCode;
   }

   public String getAltPerCode() {
      return this.altPerCode;
   }

   public void setAltPerName(String altPerName) {
      this.altPerName = altPerName;
   }

   public String getAltPerName() {
      return this.altPerName;
   }

   public void setAuditRemark(String auditRemark) {
      this.auditRemark = auditRemark;
   }

   public String getAuditRemark() {
      return this.auditRemark;
   }

   public void setHbvName(String hbvName) {
      this.hbvName = hbvName;
   }

   public String getHbvName() {
      return this.hbvName;
   }

   public void setHivName(String hivName) {
      this.hivName = hivName;
   }

   public String getHivName() {
      return this.hivName;
   }

   public void setHcvName(String hcvName) {
      this.hcvName = hcvName;
   }

   public String getHcvName() {
      return this.hcvName;
   }

   public void setTpName(String tpName) {
      this.tpName = tpName;
   }

   public String getTpName() {
      return this.tpName;
   }

   public void setDeviceNurseCd1(String deviceNurseCd1) {
      this.deviceNurseCd1 = deviceNurseCd1;
   }

   public String getDeviceNurseCd1() {
      return this.deviceNurseCd1;
   }

   public void setDeviceNurseName1(String deviceNurseName1) {
      this.deviceNurseName1 = deviceNurseName1;
   }

   public String getDeviceNurseName1() {
      return this.deviceNurseName1;
   }

   public void setDeviceNurseCd2(String deviceNurseCd2) {
      this.deviceNurseCd2 = deviceNurseCd2;
   }

   public String getDeviceNurseCd2() {
      return this.deviceNurseCd2;
   }

   public void setDeviceNurseName2(String deviceNurseName2) {
      this.deviceNurseName2 = deviceNurseName2;
   }

   public String getDeviceNurseName2() {
      return this.deviceNurseName2;
   }

   public void setAnestAidCd(String anestAidCd) {
      this.anestAidCd = anestAidCd;
   }

   public String getAnestAidCd() {
      return this.anestAidCd;
   }

   public void setAnestAidName(String anestAidName) {
      this.anestAidName = anestAidName;
   }

   public String getAnestAidName() {
      return this.anestAidName;
   }

   public void setOperBeginDate(Date operBeginDate) {
      this.operBeginDate = operBeginDate;
   }

   public Date getOperBeginDate() {
      return this.operBeginDate;
   }

   public void setOperEndDate(Date operEndDate) {
      this.operEndDate = operEndDate;
   }

   public Date getOperEndDate() {
      return this.operEndDate;
   }

   public void setOperName(String operName) {
      this.operName = operName;
   }

   public String getOperName() {
      return this.operName;
   }

   public void setOpeAnestMethCd(Long opeAnestMethCd) {
      this.opeAnestMethCd = opeAnestMethCd;
   }

   public Long getOpeAnestMethCd() {
      return this.opeAnestMethCd;
   }

   public void setOpeAnestMethName(String opeAnestMethName) {
      this.opeAnestMethName = opeAnestMethName;
   }

   public String getOpeAnestMethName() {
      return this.opeAnestMethName;
   }

   public void setInRoomTime(Date inRoomTime) {
      this.inRoomTime = inRoomTime;
   }

   public Date getInRoomTime() {
      return this.inRoomTime;
   }

   public void setOutRoomTime(Date outRoomTime) {
      this.outRoomTime = outRoomTime;
   }

   public Date getOutRoomTime() {
      return this.outRoomTime;
   }

   public void setAnestStartTime(Date anestStartTime) {
      this.anestStartTime = anestStartTime;
   }

   public Date getAnestStartTime() {
      return this.anestStartTime;
   }

   public void setAnestEndTime(Date anestEndTime) {
      this.anestEndTime = anestEndTime;
   }

   public Date getAnestEndTime() {
      return this.anestEndTime;
   }

   public void setAsaTypeCode(String asaTypeCode) {
      this.asaTypeCode = asaTypeCode;
   }

   public String getAsaTypeCode() {
      return this.asaTypeCode;
   }

   public void setAsaTypeName(String asaTypeName) {
      this.asaTypeName = asaTypeName;
   }

   public String getAsaTypeName() {
      return this.asaTypeName;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("hospitalCode", this.getHospitalCode()).append("applyFormNo", this.getApplyFormNo()).append("orderNo", this.getOrderNo()).append("patientId", this.getPatientId()).append("caseNo", this.getCaseNo()).append("admissionNo", this.getAdmissionNo()).append("hospitalizedCount", this.getHospitalizedCount()).append("patientName", this.getPatientName()).append("sex", this.getSex()).append("age", this.getAge()).append("applyDeptCd", this.getApplyDeptCd()).append("applyDeptName", this.getApplyDeptName()).append("applyDocCd", this.getApplyDocCd()).append("applyDocName", this.getApplyDocName()).append("applyDate", this.getApplyDate()).append("execDeptCd", this.getExecDeptCd()).append("execDeptName", this.getExecDeptName()).append("planOperTime", this.getPlanOperTime()).append("operTypeCd", this.getOperTypeCd()).append("unplanOperFlag", this.getUnplanOperFlag()).append("majorOperFlag", this.getMajorOperFlag()).append("preoperDiag1Cd", this.getPreoperDiag1Cd()).append("preoperDiag1Name", this.getPreoperDiag1Name()).append("preoperDiag1Prefix", this.getPreoperDiag1Prefix()).append("preoperDiag1Suffix", this.getPreoperDiag1Suffix()).append("preoperDiag2Cd", this.getPreoperDiag2Cd()).append("preoperDiag2Name", this.getPreoperDiag2Name()).append("preoperDiag2Prefix", this.getPreoperDiag2Prefix()).append("preoperDiag2Suffix", this.getPreoperDiag2Suffix()).append("preoperDiag3Cd", this.getPreoperDiag3Cd()).append("preoperDiag3Name", this.getPreoperDiag3Name()).append("preoperDiag3Prefix", this.getPreoperDiag3Prefix()).append("preoperDiag3Suffix", this.getPreoperDiag3Suffix()).append("preoperDiag4Cd", this.getPreoperDiag4Cd()).append("preoperDiag4Name", this.getPreoperDiag4Name()).append("preoperDiag4Prefix", this.getPreoperDiag4Prefix()).append("preoperDiag4Suffix", this.getPreoperDiag4Suffix()).append("planOper1Cd", this.getPlanOper1Cd()).append("planOper1Name", this.getPlanOper1Name()).append("planOper1Prefix", this.getPlanOper1Prefix()).append("planOper1Suffix", this.getPlanOper1Suffix()).append("planOper2Cd", this.getPlanOper2Cd()).append("planOper2Name", this.getPlanOper2Name()).append("planOper2Prefix", this.getPlanOper2Prefix()).append("planOper2Suffix", this.getPlanOper2Suffix()).append("planOper3Cd", this.getPlanOper3Cd()).append("planOper3Name", this.getPlanOper3Name()).append("planOper3Prefix", this.getPlanOper3Prefix()).append("planOper3Suffix", this.getPlanOper3Suffix()).append("planOper4Cd", this.getPlanOper4Cd()).append("planOper4Name", this.getPlanOper4Name()).append("planOper4Prefix", this.getPlanOper4Prefix()).append("planOper4Suffix", this.getPlanOper4Suffix()).append("operLevelCd", this.getOperLevelCd()).append("operLevelName", this.getOperLevelName()).append("operInciCd", this.getOperInciCd()).append("operInciName", this.getOperInciName()).append("operPositionCd", this.getOperPositionCd()).append("operPositionName", this.getOperPositionName()).append("operSiteCd", this.getOperSiteCd()).append("operSiteName", this.getOperSiteName()).append("operDocCd", this.getOperDocCd()).append("operDocName", this.getOperDocName()).append("operAid1Cd", this.getOperAid1Cd()).append("operAid1Name", this.getOperAid1Name()).append("operAid2Cd", this.getOperAid2Cd()).append("operAid2Name", this.getOperAid2Name()).append("operAid3Cd", this.getOperAid3Cd()).append("operAid3Name", this.getOperAid3Name()).append("circulatNurse1Cd", this.getCirculatNurse1Cd()).append("circulatNurse1Name", this.getCirculatNurse1Name()).append("circulatNurse2Cd", this.getCirculatNurse2Cd()).append("circulatNurse2Name", this.getCirculatNurse2Name()).append("anestMethCd", this.getAnestMethCd()).append("anestMethName", this.getAnestMethName()).append("anestCd", this.getAnestCd()).append("anestName", this.getAnestName()).append("planOperDurationHour", this.getPlanOperDurationHour()).append("planOperDurationMin", this.getPlanOperDurationMin()).append("operRoom", this.getOperRoom()).append("operRoomCd", this.getOperRoomCd()).append("operTable", this.getOperTable()).append("aboCd", this.getAboCd()).append("aboName", this.getAboName()).append("rhCd", this.getRhCd()).append("rhName", this.getRhName()).append("hbv", this.getHbv()).append("hiv", this.getHiv()).append("hcv", this.getHcv()).append("tp", this.getTp()).append("redBloodCell", this.getRedBloodCell()).append("platelet", this.getPlatelet()).append("plasma", this.getPlasma()).append("wholeBlood", this.getWholeBlood()).append("deviceReq", this.getDeviceReq()).append("otherNote", this.getOtherNote()).append("visitors", this.getVisitors()).append("status", this.getStatus()).append("operDate", this.getOperDate()).append("auditDocCd", this.getAuditDocCd()).append("auditDocName", this.getAuditDocName()).append("auditDate", this.getAuditDate()).append("shiftNurseCd", this.getShiftNurseCd()).append("shiftNurseName", this.getShiftNurseName()).append("shiftDate", this.getShiftDate()).append("altDate", this.getAltDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("auditRemark", this.getAuditRemark()).append("hbvName", this.getHbvName()).append("hivName", this.getHivName()).append("hcvName", this.getHcvName()).append("tpName", this.getTpName()).append("deviceNurseCd1", this.getDeviceNurseCd1()).append("deviceNurseName1", this.getDeviceNurseName1()).append("deviceNurseCd2", this.getDeviceNurseCd2()).append("deviceNurseName2", this.getDeviceNurseName2()).append("anestAidCd", this.getAnestAidCd()).append("anestAidName", this.getAnestAidName()).append("operBeginDate", this.getOperBeginDate()).append("operEndDate", this.getOperEndDate()).append("operName", this.getOperName()).append("opeAnestMethCd", this.getOpeAnestMethCd()).append("opeAnestMethName", this.getOpeAnestMethName()).append("inRoomTime", this.getInRoomTime()).append("outRoomTime", this.getOutRoomTime()).append("anestStartTime", this.getAnestStartTime()).append("anestEndTime", this.getAnestEndTime()).append("asaTypeCode", this.getAsaTypeCode()).append("asaTypeName", this.getAsaTypeName()).toString();
   }
}
