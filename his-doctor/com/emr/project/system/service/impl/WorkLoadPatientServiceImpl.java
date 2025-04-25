package com.emr.project.system.service.impl;

import com.emr.common.utils.AgeUtil;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.system.domain.WorkLoadPatient;
import com.emr.project.system.domain.req.PatientInfoReq;
import com.emr.project.system.domain.vo.WorkLoadPatientVo;
import com.emr.project.system.mapper.WorkLoadPatientMapper;
import com.emr.project.system.service.IWorkLoadPatientService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkLoadPatientServiceImpl implements IWorkLoadPatientService {
   @Autowired
   private WorkLoadPatientMapper workLoadPatientMapper;

   public WorkLoadPatient selectWorkLoadPatientById(Long id) {
      return this.workLoadPatientMapper.selectWorkLoadPatientById(id);
   }

   public List selectWorkLoadPatientList(WorkLoadPatient workLoadPatient) {
      List<WorkLoadPatientVo> workLoadPatients = this.workLoadPatientMapper.selectWorkLoadPatientList(workLoadPatient);
      if (!workLoadPatients.isEmpty()) {
         for(WorkLoadPatientVo patient : workLoadPatients) {
            String ageStr = AgeUtil.getAgeStr(patient.getPersonAge(), patient.getAgeMonth(), patient.getAgeDay(), patient.getAgeHour(), patient.getAgeBranch());
            patient.setAge(ageStr);
         }
      }

      return workLoadPatients;
   }

   public int insertWorkLoadPatient(WorkLoadPatient workLoadPatient) {
      return this.workLoadPatientMapper.insertWorkLoadPatient(workLoadPatient);
   }

   public int updateWorkLoadPatient(WorkLoadPatient workLoadPatient) {
      return this.workLoadPatientMapper.updateWorkLoadPatient(workLoadPatient);
   }

   public int deleteWorkLoadPatientByIds(Long[] ids) {
      return this.workLoadPatientMapper.deleteWorkLoadPatientByIds(ids);
   }

   public int deleteWorkLoadPatientById(Long id) {
      return this.workLoadPatientMapper.deleteWorkLoadPatientById(id);
   }

   public WorkLoadPatient insertOrUpdateWorkLoadPatient(WorkLoadPatient workLoadPatient) throws Exception {
      Long id = workLoadPatient.getId();
      if (id != null) {
         this.workLoadPatientMapper.updateWorkLoadPatient(workLoadPatient);
      } else {
         workLoadPatient.setId(SnowIdUtils.uniqueLong());
         this.workLoadPatientMapper.insertWorkLoadPatient(workLoadPatient);
      }

      return workLoadPatient;
   }

   public List getPatientInfo(PatientInfoReq req) throws Exception {
      List<WorkLoadPatientVo> list = this.workLoadPatientMapper.getPatientInfo(req);
      if (!list.isEmpty()) {
         for(WorkLoadPatientVo patient : list) {
            String ageStr = AgeUtil.getAgeStr(patient.getPersonAge(), patient.getAgeMonth(), patient.getAgeDay(), patient.getAgeHour(), patient.getAgeBranch());
            patient.setAge(ageStr);
         }
      }

      return list;
   }

   public List selectDeptList() throws Exception {
      return this.workLoadPatientMapper.selectDeptList();
   }
}
