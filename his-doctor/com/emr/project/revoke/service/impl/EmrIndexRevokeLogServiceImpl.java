package com.emr.project.revoke.service.impl;

import com.emr.common.utils.StringUtils;
import com.emr.project.revoke.domain.EmrIndexRevokeLog;
import com.emr.project.revoke.domain.req.EmrIndexRevokeLogReq;
import com.emr.project.revoke.mapper.EmrIndexRevokeLogMapper;
import com.emr.project.revoke.service.IEmrIndexRevokeLogService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmrIndexRevokeLogServiceImpl implements IEmrIndexRevokeLogService {
   @Autowired
   private EmrIndexRevokeLogMapper emrIndexRevokeLogMapper;

   public EmrIndexRevokeLog selectEmrIndexRevokeLogById(Long id) {
      return this.emrIndexRevokeLogMapper.selectEmrIndexRevokeLogById(id);
   }

   public List selectEmrIndexRevokeLogList(EmrIndexRevokeLogReq req) {
      String startTime = req.getStartTime();
      String endTime = req.getEndTime();
      if (StringUtils.isNotEmpty(startTime)) {
         startTime = startTime + "00:00:00";
         req.setStartTime(startTime);
      }

      if (StringUtils.isNotEmpty(endTime)) {
         endTime = endTime + "23:59:59";
         req.setEndTime(endTime);
      }

      return this.emrIndexRevokeLogMapper.selectEmrIndexRevokeLogList(req);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public int insertEmrIndexRevokeLog(EmrIndexRevokeLog emrIndexRevokeLog) {
      return this.emrIndexRevokeLogMapper.insertEmrIndexRevokeLog(emrIndexRevokeLog);
   }

   public int updateEmrIndexRevokeLog(EmrIndexRevokeLog emrIndexRevokeLog) {
      return this.emrIndexRevokeLogMapper.updateEmrIndexRevokeLog(emrIndexRevokeLog);
   }

   public int deleteEmrIndexRevokeLogByIds(Long[] ids) {
      return this.emrIndexRevokeLogMapper.deleteEmrIndexRevokeLogByIds(ids);
   }

   public int deleteEmrIndexRevokeLogById(Long id) {
      return this.emrIndexRevokeLogMapper.deleteEmrIndexRevokeLogById(id);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void insertLogList(List revokeLog) {
      this.emrIndexRevokeLogMapper.insertList(revokeLog);
   }
}
