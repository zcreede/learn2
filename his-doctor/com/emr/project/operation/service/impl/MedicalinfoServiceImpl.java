package com.emr.project.operation.service.impl;

import com.emr.common.utils.AgeUtil;
import com.emr.common.utils.StringUtils;
import com.emr.project.mzInfo.domain.OutpatientInfoVO;
import com.emr.project.operation.domain.Medicalinformation;
import com.emr.project.operation.mapper.MedicalinformationMapper;
import com.emr.project.operation.service.IMedicalinfoService;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.system.service.ISysDictDataService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalinfoServiceImpl implements IMedicalinfoService {
   @Autowired
   private MedicalinformationMapper medicalinformationMapper;
   @Autowired
   private ISysDictDataService sysDictDataService;

   public Medicalinformation queryByAdmissionNo(String admissionNo) {
      return this.medicalinformationMapper.getMedicalinfo(admissionNo);
   }

   public Map getMedicalinfoDetail(String admissionNo) {
      return this.medicalinformationMapper.getMedicalinfoDetail(admissionNo);
   }

   public Medicalinformation getMedicalinfo(String admission_no) {
      return this.medicalinformationMapper.getMedicalinfo(admission_no);
   }

   public Medicalinformation getHisMedicalByAdmissionNo(String admission_no) {
      return this.medicalinformationMapper.getHisMedicalByAdmissionNo(admission_no);
   }

   public Medicalinformation selectMedicalinfo(String admissionNo, Integer hospitalizedCount) {
      return this.medicalinformationMapper.selectMedicalinfo(admissionNo, hospitalizedCount);
   }

   public Medicalinformation selectMedicalinfoByPatientId(String admissionNo) {
      return this.medicalinformationMapper.selectMedicalinfoByPatientId(admissionNo);
   }

   public List selectOutpatient(String inpNo, String idCard) {
      List<OutpatientInfoVO> infoVOS = this.medicalinformationMapper.selectOutpatient(inpNo, idCard);

      for(OutpatientInfoVO vo : infoVOS) {
         String age = AgeUtil.getAgeStr(vo.getPersonAge(), vo.getAgeMonth(), vo.getAgeDay(), vo.getAgeHour(), vo.getAgeBranch());
         vo.setAge(age);
      }

      return infoVOS;
   }

   public List selectPatientInfoOther(VisitinfoVo visitinfoVo) {
      return this.medicalinformationMapper.selectPatientInfoOther(visitinfoVo);
   }

   public void updateLCLJInfo(Medicalinformation m) throws Exception {
      if (m != null && StringUtils.isNotBlank(m.getAdmissionNo()) && StringUtils.isNotBlank(m.getCpFlag())) {
         if (m.getCpFlag().equals("1") && StringUtils.isNotBlank(m.getCpNo())) {
            this.medicalinformationMapper.updateLCLJInfo(m);
         }

         if (m.getCpFlag().equals("0")) {
            this.medicalinformationMapper.updateLCLJInfo(m);
         }
      }

   }
}
