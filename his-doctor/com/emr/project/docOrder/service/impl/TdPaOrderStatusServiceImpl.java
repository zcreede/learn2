package com.emr.project.docOrder.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.docOrder.domain.TdPaOrderItem;
import com.emr.project.docOrder.domain.TdPaOrderStatus;
import com.emr.project.docOrder.mapper.TdPaOrderItemMapper;
import com.emr.project.docOrder.mapper.TdPaOrderStatusMapper;
import com.emr.project.docOrder.service.ITdPaOrderStatusService;
import com.emr.project.operation.service.ICommonOperationService;
import com.emr.project.pat.domain.Visitinfo;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ConfigureDeptService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TdPaOrderStatusServiceImpl implements ITdPaOrderStatusService {
   @Autowired
   private TdPaOrderStatusMapper tdPaOrderStatusMapper;
   @Autowired
   private ICommonOperationService commonService;
   @Autowired
   private TdPaOrderItemMapper orderItemMapper;
   @Autowired
   private ConfigureDeptService configureDeptService;

   public List selectTdPaOrderStatusById(String orderNo) {
      return this.tdPaOrderStatusMapper.selectTdPaOrderStatusById(orderNo);
   }

   public List selectTdPaOrderStatusList(TdPaOrderStatus tdPaOrderStatus) {
      return this.tdPaOrderStatusMapper.selectTdPaOrderStatusList(tdPaOrderStatus);
   }

   public int insertTdPaOrderStatus(TdPaOrderStatus tdPaOrderStatus) {
      return this.tdPaOrderStatusMapper.insertTdPaOrderStatus(tdPaOrderStatus);
   }

   public int updateTdPaOrderStatus(TdPaOrderStatus tdPaOrderStatus) {
      return this.tdPaOrderStatusMapper.updateTdPaOrderStatus(tdPaOrderStatus);
   }

   public int deleteTdPaOrderStatusByIds(String[] orderNos) {
      return this.tdPaOrderStatusMapper.deleteTdPaOrderStatusByIds(orderNos);
   }

   public int deleteTdPaOrderStatusById(String orderNo) {
      return this.tdPaOrderStatusMapper.deleteTdPaOrderStatusById(orderNo);
   }

   public void addTdPaOrderStatus(Visitinfo visitinfo, List orderItemList, int operationType, String operationTypeName, Date currDate, String operatorDesc) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      List<TdPaOrderStatus> list = new ArrayList(orderItemList.size());

      for(TdPaOrderItem orderItem : orderItemList) {
         TdPaOrderStatus tdPaOrderStatus = new TdPaOrderStatus();
         tdPaOrderStatus.setId(SnowIdUtils.uniqueLong());
         tdPaOrderStatus.setPatientWardNo(visitinfo.getAreaCode());
         tdPaOrderStatus.setPatientDepCode(visitinfo.getDeptCd());
         tdPaOrderStatus.setPatientId(visitinfo.getPatientId());
         tdPaOrderStatus.setCaseNo(visitinfo.getRecordNo());
         tdPaOrderStatus.setHospitalizedCount(visitinfo.getVisitId());
         tdPaOrderStatus.setAdmissionNo(visitinfo.getInpNo());
         tdPaOrderStatus.setOrderNo(orderItem.getOrderNo());
         tdPaOrderStatus.setOrderGroupNo(orderItem.getOrderGroupNo());
         tdPaOrderStatus.setOrderSortNumber(orderItem.getOrderSortNumber());
         tdPaOrderStatus.setOrderClassCode(orderItem.getOrderClassCode());
         tdPaOrderStatus.setOperationType(String.valueOf(operationType));
         tdPaOrderStatus.setOperationTypeName(operationTypeName);
         tdPaOrderStatus.setOperationTime(currDate);
         tdPaOrderStatus.setOperatorType("2");
         tdPaOrderStatus.setOperatorNo(basEmployee.getEmplNumber());
         tdPaOrderStatus.setOperatorName(basEmployee.getEmplName());
         tdPaOrderStatus.setOperatorDesc(operatorDesc);
         list.add(tdPaOrderStatus);
      }

      if (!list.isEmpty()) {
         this.tdPaOrderStatusMapper.insertList(list);
      }

   }

   public void addTdPaOrderStatus(Visitinfo visitinfo, List orderItemList, int operationType, String operationTypeName, Date currDate, String operatorDesc, String operatorNo, String operatorName) throws Exception {
      List<TdPaOrderStatus> list = new ArrayList(orderItemList.size());

      for(TdPaOrderItem orderItem : orderItemList) {
         TdPaOrderStatus tdPaOrderStatus = new TdPaOrderStatus();
         tdPaOrderStatus.setId(SnowIdUtils.uniqueLong());
         tdPaOrderStatus.setPatientWardNo(visitinfo.getAreaCode());
         tdPaOrderStatus.setPatientDepCode(visitinfo.getDeptCd());
         tdPaOrderStatus.setPatientId(visitinfo.getPatientId());
         tdPaOrderStatus.setCaseNo(visitinfo.getRecordNo());
         tdPaOrderStatus.setHospitalizedCount(visitinfo.getVisitId());
         tdPaOrderStatus.setAdmissionNo(visitinfo.getInpNo());
         tdPaOrderStatus.setOrderNo(orderItem.getOrderNo());
         tdPaOrderStatus.setOrderGroupNo(orderItem.getOrderGroupNo());
         tdPaOrderStatus.setOrderSortNumber(orderItem.getOrderSortNumber());
         tdPaOrderStatus.setOrderClassCode(orderItem.getOrderClassCode());
         tdPaOrderStatus.setOperationType(String.valueOf(operationType));
         tdPaOrderStatus.setOperationTypeName(operationTypeName);
         tdPaOrderStatus.setOperationTime(currDate);
         tdPaOrderStatus.setOperatorType("2");
         tdPaOrderStatus.setOperatorNo(operatorNo);
         tdPaOrderStatus.setOperatorName(operatorName);
         tdPaOrderStatus.setOperatorDesc(operatorDesc);
         list.add(tdPaOrderStatus);
      }

      if (!list.isEmpty()) {
         this.tdPaOrderStatusMapper.insertList(list);
      }

   }

   public void updateTdPaOrderStatus(List orderNoList, int operationType, String operationTypeName, Date currDate, String operatorDesc) throws Exception {
      this.tdPaOrderStatusMapper.updateTdPaOrderStatusByOrderNoList(orderNoList, String.valueOf(operationType), operationTypeName, currDate, operatorDesc);
   }

   public List selectByOrderNoList(List orderNoList) {
      List<TdPaOrderStatus> list = null;
      if (orderNoList != null && !orderNoList.isEmpty()) {
         list = this.tdPaOrderStatusMapper.selectByOrderNoList(orderNoList);
      }

      return list;
   }

   public List selectByOrderNoListAndStatusList(List orderNoList, List operationTypeList) throws Exception {
      List<TdPaOrderStatus> list = null;
      if (orderNoList != null && !orderNoList.isEmpty()) {
         list = this.tdPaOrderStatusMapper.selectByOrderNoListAndStatusList(orderNoList, operationTypeList);
      }

      return list;
   }

   public List selectByOperationTypes(String patientId) throws Exception {
      List<TdPaOrderStatus> list = new ArrayList(1);
      if (StringUtils.isNotBlank(patientId)) {
         list = this.tdPaOrderStatusMapper.selectByOperationTypes(patientId);
      }

      return list;
   }

   public boolean getOrderStatusIsEffective(String orderNo, String orderSortNumber, String orderGroupNumer, String flag, String performTime) throws Exception {
      TdPaOrderItem inpatientOrderItem = this.orderItemMapper.selectByParam(orderNo, orderSortNumber, orderGroupNumer);
      Date nowDate = new Date();
      if (StringUtils.isNotBlank(performTime)) {
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
         nowDate = sdf.parse(performTime);
      }

      return this.getOrderStatusIsEffective(inpatientOrderItem, flag, nowDate);
   }

   public boolean getOrderStatusIsEffective(TdPaOrderItem inpatientOrderItem, String flag, Date nowDate) throws Exception {
      int inpatientOrderStatus = Integer.parseInt(inpatientOrderItem.getOrderStatus());
      boolean mark = false;
      switch (flag) {
         case "1":
            if (0 == inpatientOrderStatus || 4 == inpatientOrderStatus || 6 == inpatientOrderStatus) {
               mark = true;
            }
            break;
         case "3":
            SysUser user = SecurityUtils.getLoginUser().getUser();
            String orderProcessingType = this.configureDeptService.getConfigureValue("1239", user.getHospital().getOrgCode(), user.getDept().getDeptCode());
            if ("1".equals(orderProcessingType)) {
               long time = 0L;
               Date date = null;
               if (2 == inpatientOrderStatus) {
                  date = inpatientOrderItem.getOrderDealTime();
               }

               if (4 == inpatientOrderStatus) {
                  date = inpatientOrderItem.getOrderStopTime();
               }

               if (date != null) {
                  time = date.getTime() - nowDate.getTime();
               }

               if (0 == inpatientOrderStatus || 4 == inpatientOrderStatus || 6 == inpatientOrderStatus || 1 == inpatientOrderStatus || 3 == inpatientOrderStatus || 2 == inpatientOrderStatus && time < 0L || 4 == inpatientOrderStatus && time > 0L) {
                  mark = true;
               }
            } else {
               long time = 0L;
               Date date = null;
               if (2 == inpatientOrderStatus) {
                  date = inpatientOrderItem.getOrderDealTime();
               }

               if (4 == inpatientOrderStatus) {
                  date = inpatientOrderItem.getOrderStopTime();
               }

               if (date != null) {
                  time = date.getTime() - nowDate.getTime();
               }

               if (1 == inpatientOrderStatus || 3 == inpatientOrderStatus || 2 == inpatientOrderStatus && time < 0L || 4 == inpatientOrderStatus && time > 0L) {
                  mark = true;
               }
            }
            break;
         case "7":
            if (4 != inpatientOrderStatus && 5 != inpatientOrderStatus) {
               if (3 == inpatientOrderStatus || 2 == inpatientOrderStatus || 8 == inpatientOrderStatus) {
                  mark = true;
               }
            } else if (nowDate.before(inpatientOrderItem.getOrderStopTime())) {
               mark = true;
            }
         case "2":
         case "9":
         case "8":
         case "10":
      }

      return mark;
   }

   public void insertSelective(TdPaOrderStatus inpatientOrderStatus) throws Exception {
      Integer operationType = Integer.parseInt(inpatientOrderStatus.getOperationType());
      inpatientOrderStatus.setId(SnowIdUtils.uniqueLong());
      switch (operationType) {
         case 1:
            this.tdPaOrderStatusMapper.insertSelective(inpatientOrderStatus);
            break;
         case 2:
            this.tdPaOrderStatusMapper.insertSelective(inpatientOrderStatus);
            break;
         case 3:
            this.tdPaOrderStatusMapper.insertSelective(inpatientOrderStatus);
            break;
         default:
            this.tdPaOrderStatusMapper.insertSelective(inpatientOrderStatus);
      }

   }

   public void updateItemTime(String orderNo, String orderSortNumber, String orderGroupNo, String staffCode, Date dbDate, int i, boolean isUpdateStatus, String stopUser) {
      TdPaOrderItem inpatientOrderItem = new TdPaOrderItem();
      inpatientOrderItem.setOrderNo(orderNo);
      inpatientOrderItem.setOrderGroupNo(Integer.parseInt(orderGroupNo));
      inpatientOrderItem.setOrderSortNumber(orderSortNumber);
      SysUser user = SecurityUtils.getLoginUser().getUser();
      boolean isUpdate = true;
      if (i == 1) {
         inpatientOrderItem.setOrderAuditTime(dbDate);
         inpatientOrderItem.setOrderAuditDoc(staffCode);
      } else if (i == 2) {
         inpatientOrderItem.setOrderDealTime(dbDate);
         inpatientOrderItem.setOrderDealDoc(staffCode);
         inpatientOrderItem.setOrderDealDocName(user.getUserName());
      } else if (i == 3) {
         Integer orderStatus = this.tdPaOrderStatusMapper.getOrderStatus(orderNo, orderSortNumber);
         if (orderStatus != null && orderStatus != 3) {
            inpatientOrderItem.setOrderExecuteTime(dbDate);
            inpatientOrderItem.setOrderExecuteDoc(staffCode);
         } else {
            isUpdate = false;
         }
      } else if (i == 5 || i == 8) {
         if (i == 5) {
            inpatientOrderItem.setOrderStopDoc(stopUser);
            inpatientOrderItem.setOrderStopTime(dbDate);
         }

         inpatientOrderItem.setOrderDealTime(dbDate);
         inpatientOrderItem.setOrderDealDoc(staffCode);
         inpatientOrderItem.setOrderDealDocName(user.getUserName());
         inpatientOrderItem.setOrderExecuteTime(dbDate);
         inpatientOrderItem.setOrderExecuteDoc(staffCode);
      }

      if (isUpdateStatus) {
         inpatientOrderItem.setOrderStatus(String.valueOf(i));
      }

      if (isUpdate) {
         this.tdPaOrderStatusMapper.updateItemTime(inpatientOrderItem);
      }

   }

   public void updateItemOrderStatus(String orderNo, String orderSortNumber, String orderGroupNo, String orderStatus, String flag) {
      if (flag.equals("0")) {
         this.tdPaOrderStatusMapper.updateItemOrderCheckStatus(orderNo, orderSortNumber, orderGroupNo, orderStatus);
      } else {
         this.tdPaOrderStatusMapper.updateItemOrderStatus(orderNo, orderSortNumber, orderGroupNo, orderStatus);
      }

   }

   public TdPaOrderStatus getTdPaOrderStatus(Visitinfo visitinfo, String orderNo, String orderGroupNo, String orderSortNumber, String orderClassCode, String operationType, String operationName, Date currDate) throws Exception {
      TdPaOrderStatus tdPaOrderStatus = new TdPaOrderStatus();
      tdPaOrderStatus.setId(SnowIdUtils.uniqueLong());
      tdPaOrderStatus.setPatientWardNo(visitinfo.getAreaCode());
      tdPaOrderStatus.setPatientDepCode(visitinfo.getDeptCd());
      tdPaOrderStatus.setPatientId(visitinfo.getPatientId());
      tdPaOrderStatus.setCaseNo(visitinfo.getRecordNo());
      tdPaOrderStatus.setHospitalizedCount(visitinfo.getVisitId());
      tdPaOrderStatus.setAdmissionNo(visitinfo.getInpNo());
      tdPaOrderStatus.setOrderNo(orderNo);
      tdPaOrderStatus.setOrderGroupNo(Integer.valueOf(orderGroupNo));
      tdPaOrderStatus.setOrderSortNumber(orderSortNumber);
      tdPaOrderStatus.setOrderClassCode(orderClassCode);
      tdPaOrderStatus.setOperationType(operationType);
      tdPaOrderStatus.setOperationTypeName(operationName);
      tdPaOrderStatus.setOperationTime(currDate);
      tdPaOrderStatus.setOperatorType("2");
      tdPaOrderStatus.setOperatorCode(SecurityUtils.getLoginUser().getUser().getUserName());
      tdPaOrderStatus.setOperatorNo(SecurityUtils.getLoginUser().getUser().getUserName());
      tdPaOrderStatus.setOperatorName(SecurityUtils.getLoginUser().getUser().getNickName());
      tdPaOrderStatus.setOperatorDesc((String)null);
      return tdPaOrderStatus;
   }

   public void insertTdPaOrderStatusList(List orderStatusList) throws Exception {
      if (CollectionUtils.isNotEmpty(orderStatusList)) {
         this.tdPaOrderStatusMapper.insertList(orderStatusList);
      }

   }

   public String selectOrderType(String orderNo, String orderSortNumber, String orderGroupNo) {
      return this.tdPaOrderStatusMapper.selectOrderType(orderNo, orderSortNumber, orderGroupNo);
   }
}
