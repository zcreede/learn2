package com.emr.project.operation.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.project.operation.domain.TakeDrugReturn;
import com.emr.project.operation.domain.TakeDrugReturnLog;
import com.emr.project.operation.mapper.TakeDrugReturnLogMapper;
import com.emr.project.operation.service.TakeDrugReturnLogService;
import com.emr.project.system.domain.SysUser;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TakeDrugReturnLogServiceImpl implements TakeDrugReturnLogService {
   @Autowired
   private TakeDrugReturnLogMapper takeDrugReturnLogMapper;

   public void addTakeDrugListLogList(int operateType, List takeDrugReturns, int returnType, Map sourceDoseMap) throws Exception {
      List<TakeDrugReturnLog> takeDrugReturnLogs = new ArrayList();
      Date operateTime = new Date();
      SysUser user = SecurityUtils.getLoginUser().getUser();

      for(TakeDrugReturn takeDrugReturn : takeDrugReturns) {
         TakeDrugReturnLog takeDrugReturnLog = new TakeDrugReturnLog();
         takeDrugReturnLog.setSerialNumber(takeDrugReturn.getSerialNumber());
         takeDrugReturnLog.setSerialNumberXh(takeDrugReturn.getSerialNumberXh());
         takeDrugReturnLog.setOperateType(operateType);
         takeDrugReturnLog.setOperateTime(operateTime);
         takeDrugReturnLog.setOperator(user.getUserName());
         BigDecimal sourceDose = sourceDoseMap != null && sourceDoseMap.get(takeDrugReturn.getSerialNumber() + "_" + takeDrugReturn.getSerialNumberXh()) != null ? (BigDecimal)sourceDoseMap.get(takeDrugReturn.getSerialNumber() + "_" + takeDrugReturn.getSerialNumberXh()) : null;
         takeDrugReturnLog.setDose(sourceDose);
         takeDrugReturnLog.setReturnDose(takeDrugReturn.getDose());
         takeDrugReturnLog.setReturnType(returnType);
         takeDrugReturnLogs.add(takeDrugReturnLog);
      }

      this.takeDrugReturnLogMapper.insertBatch(takeDrugReturnLogs);
   }
}
