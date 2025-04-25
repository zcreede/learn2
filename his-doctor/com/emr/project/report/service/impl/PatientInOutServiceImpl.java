package com.emr.project.report.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.project.operation.domain.TmPmHsxm;
import com.emr.project.operation.service.ITmPmHsxmSerive;
import com.emr.project.report.domain.dto.BeHospitalizedDTO;
import com.emr.project.report.domain.dto.ChangeDeptTableListDTO;
import com.emr.project.report.domain.dto.LeaveHospitalizedTableDTO;
import com.emr.project.report.domain.req.BeHospitalizedTableReq;
import com.emr.project.report.domain.req.ChangeDeptTableListReq;
import com.emr.project.report.domain.req.LeaveHospitalizedTableReq;
import com.emr.project.report.domain.req.PatientWorkloadReq;
import com.emr.project.report.domain.resp.PatientWorkloadResp;
import com.emr.project.report.mapper.PatientInOutMapper;
import com.emr.project.report.service.IPatientInOutService;
import com.emr.project.system.domain.SysUser;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientInOutServiceImpl implements IPatientInOutService {
   @Autowired
   private PatientInOutMapper patientInOutMapper;
   @Autowired
   private ITmPmHsxmSerive tmPmHsxmSerive;

   public List getBeHospitalizedTableList(BeHospitalizedTableReq req) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      String deptCode = user.getDept().getDeptCode();
      String beginDate = req.getBeginDate();
      String endDate = req.getEndDate();
      String caseNo = req.getCaseNo();
      String name = req.getName();
      List<BeHospitalizedDTO> list = this.patientInOutMapper.selectBeHospitalizedTableList(deptCode, beginDate, endDate, caseNo, name);
      return list;
   }

   public Map getLeaveHospitalizedTableList(LeaveHospitalizedTableReq req) {
      Map<String, Object> resultMap = new HashMap();
      SysUser user = SecurityUtils.getLoginUser().getUser();
      String deptCode = user.getDept().getDeptCode();
      String beginDate = req.getBeginDate();
      String endDate = req.getEndDate();
      String status = req.getStatus();
      String residentNo = req.getResidentNo();
      TmPmHsxm tmPmHsxm = new TmPmHsxm();
      tmPmHsxm.setDrugFlag("1");
      List<String> threeLevelCodeList = this.tmPmHsxmSerive.getAttrList(tmPmHsxm, TmPmHsxm::getThreeLevelCode);
      List<LeaveHospitalizedTableDTO> resultList = this.patientInOutMapper.selectLeaveHospitalizedTableList(deptCode, beginDate, endDate, status, threeLevelCodeList, residentNo);
      resultList.forEach((leaveHospitalizedTableDTO) -> {
         BigDecimal drugFee = BigDecimal.ZERO;
         BigDecimal totalFee = BigDecimal.ZERO;
         if (leaveHospitalizedTableDTO.getDrugFee() != null) {
            drugFee = leaveHospitalizedTableDTO.getDrugFee();
         }

         if (leaveHospitalizedTableDTO.getTotalFee() != null) {
            totalFee = leaveHospitalizedTableDTO.getTotalFee();
         }

         if (totalFee.compareTo(BigDecimal.ZERO) != 0 && drugFee.compareTo(BigDecimal.ZERO) != 0) {
            leaveHospitalizedTableDTO.setDrugFeeProportion(drugFee.divide(totalFee, 4, 4).multiply(BigDecimal.valueOf(100L).setScale(2, 4)));
         }

      });
      BigDecimal drugFee = BigDecimal.ZERO;
      BigDecimal days = BigDecimal.ZERO;
      BigDecimal totalFee = BigDecimal.ZERO;

      for(LeaveHospitalizedTableDTO dto : resultList) {
         if (dto.getDrugFee() != null) {
            drugFee = drugFee.add(dto.getDrugFee());
         }

         if (dto.getDays() != null) {
            days = days.add(dto.getDays());
         }

         if (dto.getTotalFee() != null) {
            totalFee = totalFee.add(dto.getTotalFee());
         }
      }

      resultMap.put("days", days);
      resultMap.put("drugFee", drugFee);
      resultMap.put("listAll", resultList);
      resultMap.put("totalFee", totalFee);
      return resultMap;
   }

   public List getChangeDeptTableList(ChangeDeptTableListReq req) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      String deptCode = user.getDept().getDeptCode();
      String beginDate = req.getBeginDate();
      String endDate = req.getEndDate();
      String caseNo = req.getCaseNo();
      String name = req.getName();
      String isChangeIn = req.getIsChangeIn();
      List<ChangeDeptTableListDTO> list = this.patientInOutMapper.selectChangeDeptTableList(deptCode, beginDate, endDate, caseNo, name, isChangeIn);
      return list;
   }

   public List queryPatientWorkload(PatientWorkloadReq req) {
      List<PatientWorkloadResp> list = new ArrayList();
      TmPmHsxm tmPmHsxm = new TmPmHsxm();
      List<String> threeLevelCodeList = this.tmPmHsxmSerive.getAttrList(tmPmHsxm, TmPmHsxm::getCode);
      String beginDate = req.getBeginDate();
      String endDate = req.getEndDate();
      String type = req.getType();
      SysUser user = SecurityUtils.getLoginUser().getUser();
      String depCode = user.getDept().getDeptCode();
      List<PatientWorkloadResp> patientList = new ArrayList();
      String typeMessage = null;
      if (type.equals("1")) {
         patientList = this.patientInOutMapper.selectLeaveHospitalList(depCode, beginDate, endDate, threeLevelCodeList, req.getResidentNo());
         typeMessage = "按出院患者统计";
      } else if (type.equals("2")) {
         patientList = this.patientInOutMapper.selectInHosPatientList(depCode, beginDate, endDate, threeLevelCodeList, req.getResidentNo());
         typeMessage = "按实际费用统计";
      }

      Map<String, BigDecimal> bigMap = new HashMap();
      Map<String, List<PatientWorkloadResp>> collect = (Map)patientList.stream().collect(Collectors.groupingBy(PatientWorkloadResp::getAdmissionNo));

      for(String key : collect.keySet()) {
         List<PatientWorkloadResp> workloadResps = (List)collect.get(key);
         BigDecimal total = (BigDecimal)workloadResps.stream().map(PatientWorkloadResp::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
         bigMap.put(key, total);
      }

      List<String> admissionList = (List)patientList.stream().map(PatientWorkloadResp::getAdmissionNo).distinct().collect(Collectors.toList());

      for(String key : collect.keySet()) {
         for(PatientWorkloadResp patient : (List)collect.get(key)) {
            patient.setBeginDate(req.getBeginDate());
            patient.setEndDate(req.getEndDate());
            patient.setHospitalName(user.getHospital().getOrgName());
            patient.setType(typeMessage);
            patient.setSize(admissionList.size());
            patient.setTotal(patient.getAmount());
            list.add(patient);
         }
      }

      return list;
   }

   public List getLeaveHospitalizedTableListPage(List listAll, LeaveHospitalizedTableReq req) {
      List<LeaveHospitalizedTableDTO> list = new ArrayList();
      if (listAll != null && listAll.size() > 0) {
         Integer pageNum = req.getPageNum();
         Integer pageSize = req.getPageSize();
         int begin = 0;
         int end = 0;
         if (pageSize != 0) {
            begin = (pageNum - 1) * pageSize;
            end = pageNum * pageSize;
            if (end > listAll.size()) {
               end = listAll.size();
            }
         }

         if (end == 0) {
            list.addAll(listAll);
         } else {
            list = listAll.subList(begin, end);
         }
      }

      return list;
   }
}
