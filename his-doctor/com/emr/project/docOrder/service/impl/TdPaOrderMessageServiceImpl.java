package com.emr.project.docOrder.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.docOrder.domain.TdPaOrder;
import com.emr.project.docOrder.domain.TdPaOrderMessage;
import com.emr.project.docOrder.mapper.TdPaOrderMessageMapper;
import com.emr.project.docOrder.service.ITdPaOrderMessageService;
import com.emr.project.pat.domain.Visitinfo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TdPaOrderMessageServiceImpl implements ITdPaOrderMessageService {
   @Autowired
   private TdPaOrderMessageMapper tdPaOrderMessageMapper;

   public TdPaOrderMessage selectTdPaOrderMessageById(Long id) {
      return this.tdPaOrderMessageMapper.selectTdPaOrderMessageById(id);
   }

   public List selectTdPaOrderMessageList(TdPaOrderMessage tdPaOrderMessage) {
      return this.tdPaOrderMessageMapper.selectTdPaOrderMessageList(tdPaOrderMessage);
   }

   public int insertTdPaOrderMessage(TdPaOrderMessage tdPaOrderMessage) {
      return this.tdPaOrderMessageMapper.insertTdPaOrderMessage(tdPaOrderMessage);
   }

   public int updateTdPaOrderMessage(TdPaOrderMessage tdPaOrderMessage) {
      return this.tdPaOrderMessageMapper.updateTdPaOrderMessage(tdPaOrderMessage);
   }

   public int deleteTdPaOrderMessageByIds(Long[] ids) {
      return this.tdPaOrderMessageMapper.deleteTdPaOrderMessageByIds(ids);
   }

   public int deleteTdPaOrderMessageById(Long id) {
      return this.tdPaOrderMessageMapper.deleteTdPaOrderMessageById(id);
   }

   public void insertList(List list) throws Exception {
      if (CollectionUtils.isNotEmpty(list)) {
         this.tdPaOrderMessageMapper.insertList(list);
      }

   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void addMsgs(List orderList, Visitinfo visitinfo, int operationType, String operationTypeName) throws Exception {
      List<TdPaOrderMessage> msgList = new ArrayList(orderList.size());
      String msgContent = this.getMsgContent(visitinfo, operationType, operationTypeName);

      for(TdPaOrder order : orderList) {
         String receiveDeptCode = order.getOrderPerformFlag().equals("3") ? order.getPerformDepCode() : order.getPatientDepCode();
         TdPaOrderMessage tdPaOrderMessage = new TdPaOrderMessage();
         tdPaOrderMessage.setId(SnowIdUtils.uniqueLong());
         tdPaOrderMessage.setOrderNo(order.getOrderNo());
         tdPaOrderMessage.setMsgContent(msgContent);
         tdPaOrderMessage.setReceiveDeptCode(receiveDeptCode);
         tdPaOrderMessage.setPatDeptCode(order.getPatientDepCode());
         tdPaOrderMessage.setCaseNo(visitinfo.getRecordNo());
         tdPaOrderMessage.setHospitalizedCount(visitinfo.getVisitId());
         tdPaOrderMessage.setAdmissionNo(visitinfo.getInpNo());
         tdPaOrderMessage.setPatientName(visitinfo.getPatientName());
         tdPaOrderMessage.setMsgStatus("0");
         tdPaOrderMessage.setMsgType("1");
         tdPaOrderMessage.setOrderType(order.getOrderType());
         String orderTypeName = order.getOrderType().equals("1") ? "长期" : "临时";
         tdPaOrderMessage.setOrderTypeName(orderTypeName);
         tdPaOrderMessage.setOrderClassCode(order.getOrderClassCode());
         String orderClassCodeName = this.getOrderClassCodeName(order.getOrderClassCode());
         tdPaOrderMessage.setOrderClassCodeName(orderClassCodeName);
         tdPaOrderMessage.setOrderStatus(String.valueOf(operationType));
         tdPaOrderMessage.setOrderStatusName(operationTypeName);
         tdPaOrderMessage.setCreateOper(SecurityUtils.getUsername());
         msgList.add(tdPaOrderMessage);
      }

      this.tdPaOrderMessageMapper.insertList(msgList);
   }

   public void updateMsgStatus(List orderNoList, String msgStatus, String orderStatus) throws Exception {
      if (CollectionUtils.isNotEmpty(orderNoList) && StringUtils.isNotBlank(msgStatus)) {
         this.tdPaOrderMessageMapper.updateByOrderNo(orderNoList, msgStatus, SecurityUtils.getUsername(), orderStatus);
      }

   }

   public List selectOrderReturnMessageList(String msgStatus, Date startTime, Date endTime) {
      String userName = SecurityUtils.getLoginUser().getUser().getUserName();
      String orgCode = SecurityUtils.getLoginUser().getUser().getDept().getDeptCode();
      return this.tdPaOrderMessageMapper.selectOrderReturnMessageList(userName, orgCode, msgStatus, startTime, endTime);
   }

   public List getCurrentDeptOrderMsgList() {
      TdPaOrderMessage param = new TdPaOrderMessage();
      param.setReceiveDeptCode(SecurityUtils.getLoginUser().getUser().getDept().getDeptCode());
      param.setMsgStatus("0");
      param.setMsgType("1");
      return this.tdPaOrderMessageMapper.selectTdPaOrderMessageList(param);
   }

   private String getMsgContent(Visitinfo visitinfo, int operationType, String operationTypeName) {
      StringBuffer msgContent = new StringBuffer("[");
      msgContent.append(visitinfo.getBedNo());
      msgContent.append(",");
      msgContent.append(visitinfo.getPatientName());
      msgContent.append("]");
      msgContent.append("有一条");
      msgContent.append(operationType == 0 ? "新开" : operationTypeName);
      msgContent.append("的医嘱");
      return msgContent.toString();
   }

   private String getOrderClassCodeName(String orderClassCode) {
      String orderClassCodeName = "";
      switch (orderClassCode) {
         case "00":
            orderClassCodeName = "组套";
            break;
         case "01":
            orderClassCodeName = "药疗";
            break;
         case "02":
            orderClassCodeName = "检查";
            break;
         case "03":
            orderClassCodeName = "检验";
            break;
         case "04":
            orderClassCodeName = "手术";
            break;
         case "05":
            orderClassCodeName = "处置";
            break;
         case "06":
            orderClassCodeName = "材料";
            break;
         case "07":
            orderClassCodeName = "嘱托";
            break;
         case "08":
            orderClassCodeName = "输血";
            break;
         case "09":
            orderClassCodeName = "协方";
            break;
         case "99":
            orderClassCodeName = "其他";
            break;
         case "30":
            orderClassCodeName = "频次";
            break;
         case "40":
            orderClassCodeName = "用法";
            break;
         case "50":
            orderClassCodeName = "诊断";
      }

      return orderClassCodeName;
   }
}
