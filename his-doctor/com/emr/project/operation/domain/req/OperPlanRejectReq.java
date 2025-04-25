package com.emr.project.operation.domain.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class OperPlanRejectReq {
   @ApiModelProperty("申请单号")
   private String applyFormNo;
   @ApiModelProperty("接口类型，1退回，2安排，3撤销安排，4入室，5撤销入室，6出室")
   private String type;
   @ApiModelProperty("状态（01待提交(保存)02待审核（提交）03已作废  04驳回  05已审核（审核）06已安排 07 已完成(出室) 08入室）")
   private String status;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @ApiModelProperty("实际手术时间,ps:手术室手术安排存放的是手术安排时间")
   private Date operDate;
   @ApiModelProperty("手术间编码")
   private String operRoomCd;
   @ApiModelProperty("手术间名称")
   private String operRoom;
   @ApiModelProperty("手术间名称")
   private String operRoomName;
   @ApiModelProperty("手术台")
   private String operTable;
   @ApiModelProperty("预计手术持续时间-小时")
   private Long planOperDurationHour;
   @ApiModelProperty("持续时间--分钟")
   private Long planOperDurationMin;
   @ApiModelProperty("麻醉方式代码")
   private String anestMethCd;
   @ApiModelProperty("麻醉方式名称")
   private String anestMethName;
   @ApiModelProperty("麻醉医师编码")
   private String anestCd;
   @ApiModelProperty("麻醉医师姓名")
   private String anestName;
   @ApiModelProperty("麻醉助手编码")
   private String anestAidCd;
   @ApiModelProperty("麻醉助手姓名")
   private String anestAidName;
   @ApiModelProperty("巡护1代码")
   private String circulatNurse1Cd;
   @ApiModelProperty("巡护1 姓名")
   private String circulatNurse1Name;
   @ApiModelProperty("巡护2代码")
   private String circulatNurse2Cd;
   @ApiModelProperty("巡护2 姓名")
   private String circulatNurse2Name;
   @ApiModelProperty("器械护士编码1")
   private String deviceNurseCd1;
   @ApiModelProperty("器械护士姓名1")
   private String deviceNurseName1;
   @ApiModelProperty("器械护士编码2")
   private String deviceNurseCd2;
   @ApiModelProperty("器械护士姓名2")
   private String deviceNurseName2;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @ApiModelProperty("手术开始时间")
   private Date operBeginDate;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @ApiModelProperty("手术结束时间")
   private Date operEndDate;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @ApiModelProperty("入室时间")
   private Date inRoomTime;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @ApiModelProperty("麻醉开始时间")
   private Date anestStartTime;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @ApiModelProperty("麻醉结束时间")
   private Date anestEndTime;
   @ApiModelProperty("ASA分级编码")
   private String asaTypeCode;
   @ApiModelProperty("ASA分级名称")
   private String asaTypeName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @ApiModelProperty("出室时间")
   private Date outRoomTime;

   public String getApplyFormNo() {
      return this.applyFormNo;
   }

   public void setApplyFormNo(String applyFormNo) {
      this.applyFormNo = applyFormNo;
   }

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String getType() {
      return this.type;
   }

   public String getOperRoomName() {
      return this.operRoomName;
   }

   public void setOperRoomName(String operRoomName) {
      this.operRoomName = operRoomName;
   }

   public void setType(String type) {
      this.type = type;
   }

   public Date getOperDate() {
      return this.operDate;
   }

   public void setOperDate(Date operDate) {
      this.operDate = operDate;
   }

   public String getOperRoom() {
      return this.operRoom;
   }

   public void setOperRoom(String operRoom) {
      this.operRoom = operRoom;
   }

   public String getOperTable() {
      return this.operTable;
   }

   public void setOperTable(String operTable) {
      this.operTable = operTable;
   }

   public Long getPlanOperDurationHour() {
      return this.planOperDurationHour;
   }

   public void setPlanOperDurationHour(Long planOperDurationHour) {
      this.planOperDurationHour = planOperDurationHour;
   }

   public Long getPlanOperDurationMin() {
      return this.planOperDurationMin;
   }

   public void setPlanOperDurationMin(Long planOperDurationMin) {
      this.planOperDurationMin = planOperDurationMin;
   }

   public String getAnestMethCd() {
      return this.anestMethCd;
   }

   public void setAnestMethCd(String anestMethCd) {
      this.anestMethCd = anestMethCd;
   }

   public String getAnestMethName() {
      return this.anestMethName;
   }

   public void setAnestMethName(String anestMethName) {
      this.anestMethName = anestMethName;
   }

   public String getAnestCd() {
      return this.anestCd;
   }

   public void setAnestCd(String anestCd) {
      this.anestCd = anestCd;
   }

   public String getAnestName() {
      return this.anestName;
   }

   public void setAnestName(String anestName) {
      this.anestName = anestName;
   }

   public String getAnestAidCd() {
      return this.anestAidCd;
   }

   public void setAnestAidCd(String anestAidCd) {
      this.anestAidCd = anestAidCd;
   }

   public String getAnestAidName() {
      return this.anestAidName;
   }

   public void setAnestAidName(String anestAidName) {
      this.anestAidName = anestAidName;
   }

   public String getCirculatNurse1Cd() {
      return this.circulatNurse1Cd;
   }

   public void setCirculatNurse1Cd(String circulatNurse1Cd) {
      this.circulatNurse1Cd = circulatNurse1Cd;
   }

   public String getCirculatNurse1Name() {
      return this.circulatNurse1Name;
   }

   public void setCirculatNurse1Name(String circulatNurse1Name) {
      this.circulatNurse1Name = circulatNurse1Name;
   }

   public String getOperRoomCd() {
      return this.operRoomCd;
   }

   public void setOperRoomCd(String operRoomCd) {
      this.operRoomCd = operRoomCd;
   }

   public String getCirculatNurse2Cd() {
      return this.circulatNurse2Cd;
   }

   public void setCirculatNurse2Cd(String circulatNurse2Cd) {
      this.circulatNurse2Cd = circulatNurse2Cd;
   }

   public String getCirculatNurse2Name() {
      return this.circulatNurse2Name;
   }

   public void setCirculatNurse2Name(String circulatNurse2Name) {
      this.circulatNurse2Name = circulatNurse2Name;
   }

   public String getDeviceNurseCd1() {
      return this.deviceNurseCd1;
   }

   public void setDeviceNurseCd1(String deviceNurseCd1) {
      this.deviceNurseCd1 = deviceNurseCd1;
   }

   public String getDeviceNurseName1() {
      return this.deviceNurseName1;
   }

   public void setDeviceNurseName1(String deviceNurseName1) {
      this.deviceNurseName1 = deviceNurseName1;
   }

   public String getDeviceNurseCd2() {
      return this.deviceNurseCd2;
   }

   public void setDeviceNurseCd2(String deviceNurseCd2) {
      this.deviceNurseCd2 = deviceNurseCd2;
   }

   public String getDeviceNurseName2() {
      return this.deviceNurseName2;
   }

   public void setDeviceNurseName2(String deviceNurseName2) {
      this.deviceNurseName2 = deviceNurseName2;
   }

   public Date getOperBeginDate() {
      return this.operBeginDate;
   }

   public void setOperBeginDate(Date operBeginDate) {
      this.operBeginDate = operBeginDate;
   }

   public Date getOperEndDate() {
      return this.operEndDate;
   }

   public void setOperEndDate(Date operEndDate) {
      this.operEndDate = operEndDate;
   }

   public Date getInRoomTime() {
      return this.inRoomTime;
   }

   public void setInRoomTime(Date inRoomTime) {
      this.inRoomTime = inRoomTime;
   }

   public Date getAnestStartTime() {
      return this.anestStartTime;
   }

   public void setAnestStartTime(Date anestStartTime) {
      this.anestStartTime = anestStartTime;
   }

   public Date getAnestEndTime() {
      return this.anestEndTime;
   }

   public void setAnestEndTime(Date anestEndTime) {
      this.anestEndTime = anestEndTime;
   }

   public String getAsaTypeCode() {
      return this.asaTypeCode;
   }

   public void setAsaTypeCode(String asaTypeCode) {
      this.asaTypeCode = asaTypeCode;
   }

   public String getAsaTypeName() {
      return this.asaTypeName;
   }

   public void setAsaTypeName(String asaTypeName) {
      this.asaTypeName = asaTypeName;
   }

   public Date getOutRoomTime() {
      return this.outRoomTime;
   }

   public void setOutRoomTime(Date outRoomTime) {
      this.outRoomTime = outRoomTime;
   }
}
