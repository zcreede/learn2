package com.emr.project.qc.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.qc.domain.EmrQcFlow;
import com.emr.project.qc.domain.EmrQcFlowRecord;
import com.emr.project.qc.domain.vo.EmrQcFlowRecordVo;
import com.emr.project.qc.domain.vo.EmrQcFlowVo;
import com.emr.project.qc.mapper.EmrQcFlowRecordMapper;
import com.emr.project.qc.service.IEmrQcFlowRecordService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysUser;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmrQcFlowRecordServiceImpl implements IEmrQcFlowRecordService {
   @Autowired
   private EmrQcFlowRecordMapper emrQcFlowRecordMapper;

   public EmrQcFlowRecord selectEmrQcFlowRecordById(Long id) {
      return this.emrQcFlowRecordMapper.selectEmrQcFlowRecordById(id);
   }

   public List selectEmrQcFlowRecordList(EmrQcFlowRecord emrQcFlowRecord) {
      return this.emrQcFlowRecordMapper.selectEmrQcFlowRecordList(emrQcFlowRecord);
   }

   public List selectEmrQcFlowRecordVoList(EmrQcFlowRecordVo emrQcFlowRecordVo) throws Exception {
      return this.emrQcFlowRecordMapper.selectEmrQcFlowRecordVoList(emrQcFlowRecordVo);
   }

   public List selectEmrQcFlowRecordByQcSns(List list) {
      List<EmrQcFlowRecord> resList = new ArrayList(1);
      resList = list != null && !list.isEmpty() ? this.emrQcFlowRecordMapper.selectEmrQcFlowRecordByQcSns(list) : resList;
      return resList;
   }

   public int insertEmrQcFlowRecord(EmrQcFlowRecord emrQcFlowRecord) {
      return this.emrQcFlowRecordMapper.insertEmrQcFlowRecord(emrQcFlowRecord);
   }

   public void insertEmrQcFlowRecord(EmrQcFlowVo emrQcFlowVo, String operTypeCd, String operTypeName, String operReason) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      BasEmployee basEmployee = user.getBasEmployee();
      EmrQcFlowRecord emrQcFlowRecord = new EmrQcFlowRecord();
      emrQcFlowRecord.setId(SnowIdUtils.uniqueLong());
      emrQcFlowRecord.setOrgCd(user.getHospital().getOrgCode());
      emrQcFlowRecord.setPatientId(emrQcFlowVo.getPatientId());
      emrQcFlowRecord.setOperTypeCd(operTypeCd);
      emrQcFlowRecord.setOperTypeName(operTypeName);
      emrQcFlowRecord.setQcScore(emrQcFlowVo.getQcScore());
      emrQcFlowRecord.setQcGradeName(emrQcFlowVo.getQcGradeName());
      emrQcFlowRecord.setQcGradeNo(emrQcFlowVo.getQcGradeNo());
      emrQcFlowRecord.setOperDcoCd(basEmployee.getEmplNumber());
      emrQcFlowRecord.setOperDcoName(basEmployee.getEmplName());
      emrQcFlowRecord.setOperReason(operReason);
      emrQcFlowRecord.setOperDeptCd(emrQcFlowVo.getDeptCd());
      emrQcFlowRecord.setOperDeptName(emrQcFlowVo.getDeptName());
      emrQcFlowRecord.setOperPcIp(emrQcFlowVo.getIp());
      emrQcFlowRecord.setQcSn(emrQcFlowVo.getId());
      this.emrQcFlowRecordMapper.insertEmrQcFlowRecord(emrQcFlowRecord);
   }

   public void insertEmrQcFlowRecord(EmrQcFlow emrQcFlow, String operTypeCd, String operTypeName, String ip, BasEmployee basEmployee) {
      EmrQcFlowRecord emrQcFlowRecord = new EmrQcFlowRecord();
      emrQcFlowRecord.setId(SnowIdUtils.uniqueLong());
      emrQcFlowRecord.setOrgCd(basEmployee == null ? emrQcFlow.getOrgCd() : SecurityUtils.getLoginUser().getUser().getHospital().getOrgCode());
      emrQcFlowRecord.setPatientId(emrQcFlow.getPatientId());
      emrQcFlowRecord.setOperTypeCd(operTypeCd);
      emrQcFlowRecord.setOperTypeName(operTypeName);
      emrQcFlowRecord.setOperDcoCd(basEmployee == null ? "sys" : basEmployee.getEmplNumber());
      emrQcFlowRecord.setOperDcoName(basEmployee == null ? "系统" : basEmployee.getEmplName());
      emrQcFlowRecord.setOperDeptCd(emrQcFlow.getDeptCd());
      emrQcFlowRecord.setOperDeptName(emrQcFlow.getDeptName());
      emrQcFlowRecord.setOperPcIp(ip);
      emrQcFlowRecord.setQcSn(emrQcFlow.getId());
      this.emrQcFlowRecordMapper.insertEmrQcFlowRecord(emrQcFlowRecord);
   }

   public int updateEmrQcFlowRecord(EmrQcFlowRecord emrQcFlowRecord) {
      return this.emrQcFlowRecordMapper.updateEmrQcFlowRecord(emrQcFlowRecord);
   }

   public int deleteEmrQcFlowRecordByIds(Long[] ids) {
      return this.emrQcFlowRecordMapper.deleteEmrQcFlowRecordByIds(ids);
   }

   public int deleteEmrQcFlowRecordById(Long id) {
      return this.emrQcFlowRecordMapper.deleteEmrQcFlowRecordById(id);
   }
}
