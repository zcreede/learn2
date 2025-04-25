package com.emr.project.operation.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.docOrder.domain.vo.TdPaTakeDrugListVO;
import com.emr.project.operation.domain.TakeDrugList;
import com.emr.project.operation.domain.TakeDrugListLog;
import com.emr.project.operation.mapper.TakeDrugListLogMapper;
import com.emr.project.operation.service.TakeDrugListLogService;
import com.emr.project.system.domain.SysUser;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TakeDrugListLogServiceImpl implements TakeDrugListLogService {
   @Autowired
   private TakeDrugListLogMapper takeDrugListLogMapper;

   public List addTakeDrugListLogList(int operateType, List takeDrugLists) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      List<TakeDrugListLog> takeDrugListLogList = new ArrayList();

      for(TakeDrugList takeDrugList : takeDrugLists) {
         TakeDrugListLog takeDrugListLog = new TakeDrugListLog();
         takeDrugListLog.setId(SnowIdUtils.uniqueLong());
         takeDrugListLog.setPerformListNo(takeDrugList.getPerformListNo());
         takeDrugListLog.setPerformListSortNumber(takeDrugList.getPerformListSortNumber());
         takeDrugListLog.setOperateType(operateType);
         takeDrugListLog.setOperateTime(new Date());
         takeDrugListLog.setOperator(user.getUserName());
         takeDrugListLogList.add(takeDrugListLog);
      }

      this.takeDrugListLogMapper.insertBatch(takeDrugListLogList);
      return takeDrugListLogList;
   }

   public void addTakeDrugListLogListByVO(int operateType, List takeDrugLists) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      List<TakeDrugListLog> takeDrugListLogList = new ArrayList();

      for(TdPaTakeDrugListVO takeDrugList : takeDrugLists) {
         TakeDrugListLog takeDrugListLog = new TakeDrugListLog();
         takeDrugListLog.setId(SnowIdUtils.uniqueLong());
         takeDrugListLog.setPerformListNo(takeDrugList.getPerformListNo());
         takeDrugListLog.setPerformListSortNumber(takeDrugList.getPerformListSortNumber());
         takeDrugListLog.setOperateType(operateType);
         takeDrugListLog.setOperateTime(new Date());
         takeDrugListLog.setOperator(user.getUserName());
         takeDrugListLogList.add(takeDrugListLog);
      }

      this.takeDrugListLogMapper.insertBatch(takeDrugListLogList);
   }

   public void deleteByIdList(List list) throws Exception {
      if (CollectionUtils.isNotEmpty(list)) {
         this.takeDrugListLogMapper.deleteByIdList(list);
      }

   }
}
