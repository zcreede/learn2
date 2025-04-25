package com.emr.project.docOrder.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.docOrder.domain.TdPaOrderItem;
import com.emr.project.docOrder.domain.TdPaOrderOperationLog;
import com.emr.project.docOrder.mapper.TdPaOrderOperationLogMapper;
import com.emr.project.docOrder.service.ITdPaOrderOperationLogService;
import com.emr.project.pat.domain.Visitinfo;
import com.emr.project.system.domain.BasEmployee;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TdPaOrderOperationLogServiceImpl implements ITdPaOrderOperationLogService {
   @Autowired
   private TdPaOrderOperationLogMapper tdPaOrderOperationLogMapper;

   public TdPaOrderOperationLog selectTdPaOrderOperationLogById(Long id) {
      return this.tdPaOrderOperationLogMapper.selectTdPaOrderOperationLogById(id);
   }

   public List selectTdPaOrderOperationLogList(TdPaOrderOperationLog tdPaOrderOperationLog) {
      return this.tdPaOrderOperationLogMapper.selectTdPaOrderOperationLogList(tdPaOrderOperationLog);
   }

   public int insertTdPaOrderOperationLog(TdPaOrderOperationLog tdPaOrderOperationLog) {
      return this.tdPaOrderOperationLogMapper.insertTdPaOrderOperationLog(tdPaOrderOperationLog);
   }

   public int updateTdPaOrderOperationLog(TdPaOrderOperationLog tdPaOrderOperationLog) {
      return this.tdPaOrderOperationLogMapper.updateTdPaOrderOperationLog(tdPaOrderOperationLog);
   }

   public int deleteTdPaOrderOperationLogByIds(Long[] ids) {
      return this.tdPaOrderOperationLogMapper.deleteTdPaOrderOperationLogByIds(ids);
   }

   public int deleteTdPaOrderOperationLogById(Long id) {
      return this.tdPaOrderOperationLogMapper.deleteTdPaOrderOperationLogById(id);
   }

   public void addTdPaOrderOperationLog(Visitinfo visitinfo, List orderItemList, int operationType, String operationTypeName, Date currDate, String operatorDesc) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      List<TdPaOrderOperationLog> list = new ArrayList(orderItemList.size());

      for(TdPaOrderItem orderItem : orderItemList) {
         TdPaOrderOperationLog operationLog = new TdPaOrderOperationLog();
         operationLog.setId(SnowIdUtils.uniqueLong());
         operationLog.setOrderNo(orderItem.getOrderNo());
         operationLog.setOrderSortNumber(orderItem.getOrderSortNumber());
         operationLog.setOrderGroupNo(orderItem.getOrderGroupNo().toString());
         operationLog.setOperationTime(currDate);
         operationLog.setOperationType(operationType);
         operationLog.setOperationName(operationTypeName);
         operationLog.setOperatorCode(basEmployee.getEmplNumber());
         operationLog.setOperatorNo(basEmployee.getEmplNumber());
         operationLog.setOperatorName(basEmployee.getEmplName());
         operationLog.setOperatorDesc(operatorDesc);
         list.add(operationLog);
      }

      if (!list.isEmpty()) {
         this.tdPaOrderOperationLogMapper.insertList(list);
      }

   }

   public void addTdPaOrderOperationLog(Visitinfo visitinfo, List orderItemList, int operationType, String operationTypeName, Date currDate, String operatorDesc, String operatorNo, String operatorName) throws Exception {
      List<TdPaOrderOperationLog> list = new ArrayList(orderItemList.size());

      for(TdPaOrderItem orderItem : orderItemList) {
         TdPaOrderOperationLog operationLog = new TdPaOrderOperationLog();
         operationLog.setId(SnowIdUtils.uniqueLong());
         operationLog.setOrderNo(orderItem.getOrderNo());
         operationLog.setOrderSortNumber(orderItem.getOrderSortNumber());
         operationLog.setOrderGroupNo(orderItem.getOrderGroupNo().toString());
         operationLog.setOperationTime(currDate);
         operationLog.setOperationType(operationType);
         operationLog.setOperationName(operationTypeName);
         operationLog.setOperatorCode(operatorNo);
         operationLog.setOperatorNo(operatorNo);
         operationLog.setOperatorName(operatorName);
         operationLog.setOperatorDesc(operatorDesc);
         list.add(operationLog);
      }

      if (!list.isEmpty()) {
         this.tdPaOrderOperationLogMapper.insertList(list);
      }

   }

   public void addInpatientOrderOperationLog(String orderNo, String orderSortNumber, String orderGroupNo, Integer operationType, String operationName, String operatorDesc, String operatorCode, String operatorNo, String operatorName) {
      TdPaOrderOperationLog temp = new TdPaOrderOperationLog();
      temp.setId(SnowIdUtils.uniqueLong());
      temp.setOrderNo(orderNo);
      temp.setOrderSortNumber(orderSortNumber);
      temp.setOrderGroupNo(orderGroupNo);
      temp.setOperationType(operationType);
      temp.setOperationName(operationName);
      temp.setOperatorDesc(operatorDesc);
      temp.setOperatorCode(operatorCode);
      temp.setOperatorName(operatorName);
      temp.setOperatorNo(operatorNo);
      temp.setOperationTime(new Date());
      this.tdPaOrderOperationLogMapper.insert(temp);
   }

   public TdPaOrderOperationLog getInpatientOrderOperationLog(String orderNo, String orderSortNumber, String orderGroupNo, Integer operationType, String operationName, String operatorDesc, String operatorCode, String operatorNo, String operatorName) {
      TdPaOrderOperationLog temp = new TdPaOrderOperationLog();
      temp.setId(SnowIdUtils.uniqueLong());
      temp.setOrderNo(orderNo);
      temp.setOrderSortNumber(orderSortNumber);
      temp.setOrderGroupNo(orderGroupNo);
      temp.setOperationType(operationType);
      temp.setOperationName(operationName);
      temp.setOperatorDesc(operatorDesc);
      temp.setOperatorCode(operatorCode);
      temp.setOperatorName(operatorName);
      temp.setOperatorNo(operatorNo);
      temp.setOperationTime(new Date());
      return temp;
   }

   public void insertTdPaOrderOperationLogList(List orderOperationLogList) throws Exception {
      if (CollectionUtils.isNotEmpty(orderOperationLogList)) {
         this.tdPaOrderOperationLogMapper.insertList(orderOperationLogList);
      }

   }

   public List selectLogByOrderNo(String orderNo, Date date) {
      DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
      String format = fmt.format(date);
      String startDate = format + " 00:00:00";
      String endDate = format + " 23:59:59";
      return this.tdPaOrderOperationLogMapper.selectLogByOrderNo(orderNo, startDate, endDate);
   }
}
