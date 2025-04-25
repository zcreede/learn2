package com.emr.project.pat.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.pat.domain.TlNaAdjustLog;
import com.emr.project.pat.mapper.TlNaAdjustLogMapper;
import com.emr.project.pat.service.ITlNaAdjustLogService;
import com.emr.project.system.domain.SysUser;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TlNaAdjustLogServiceImpl implements ITlNaAdjustLogService {
   @Autowired
   private TlNaAdjustLogMapper tlNaAdjustLogMapper;

   public TlNaAdjustLog selectTlNaAdjustLogById(Long id) {
      return this.tlNaAdjustLogMapper.selectTlNaAdjustLogById(id);
   }

   public List selectTlNaAdjustLogList(TlNaAdjustLog tlNaAdjustLog) {
      return this.tlNaAdjustLogMapper.selectTlNaAdjustLogList(tlNaAdjustLog);
   }

   public int insertTlNaAdjustLog(TlNaAdjustLog tlNaAdjustLog) {
      return this.tlNaAdjustLogMapper.insertTlNaAdjustLog(tlNaAdjustLog);
   }

   public int updateTlNaAdjustLog(TlNaAdjustLog tlNaAdjustLog) {
      return this.tlNaAdjustLogMapper.updateTlNaAdjustLog(tlNaAdjustLog);
   }

   public int deleteTlNaAdjustLogByIds(Long[] ids) {
      return this.tlNaAdjustLogMapper.deleteTlNaAdjustLogByIds(ids);
   }

   public int deleteTlNaAdjustLogById(Long id) {
      return this.tlNaAdjustLogMapper.deleteTlNaAdjustLogById(id);
   }

   public void addAdjustLog(String mark, String service_type, String operate_type, String oldCode, String oldName, String newCode, String newName, String admission_no, String hospitalizedCount, String caseNo, String patientName) {
      TlNaAdjustLog adLog = new TlNaAdjustLog();
      adLog.setId(SnowIdUtils.uniqueLong());
      adLog.setMark(mark);
      adLog.setServiceCodeNew(newCode);
      adLog.setServiceNameNew(newName);
      adLog.setServiceCodeOld(oldCode);
      adLog.setServiceNameOld(oldName);
      adLog.setServiceType(service_type);
      adLog.setOperateType(operate_type);
      adLog.setAdmissionNo(admission_no);
      adLog.setOperatorDate(new Date());
      SysUser user = SecurityUtils.getLoginUser().getUser();
      adLog.setOperatorName(user.getNickName());
      adLog.setOperatorNo(user.getUserName());
      adLog.setHospitalCode(user.getHospital().getOrgCode());
      adLog.setWardNo(user.getDept().getDeptCode());
      adLog.setWardName(user.getDept().getDeptName());
      adLog.setHospitalizedCount(Integer.valueOf(hospitalizedCount));
      adLog.setPatientName(patientName);
      adLog.setCaseNo(caseNo);
      this.tlNaAdjustLogMapper.insertTlNaAdjustLog(adLog);
   }
}
