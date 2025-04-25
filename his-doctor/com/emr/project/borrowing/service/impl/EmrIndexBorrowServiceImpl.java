package com.emr.project.borrowing.service.impl;

import com.emr.common.utils.AgeUtil;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.borrowing.domain.EmrIndexBorrow;
import com.emr.project.borrowing.domain.req.BorrowReviewedListReq;
import com.emr.project.borrowing.domain.req.CheckMedicalRecordReq;
import com.emr.project.borrowing.domain.req.PatientOutReq;
import com.emr.project.borrowing.domain.req.SaveApplyAgreeListReq;
import com.emr.project.borrowing.domain.req.SaveApplyAgreeReq;
import com.emr.project.borrowing.domain.req.UpdateApplyDetailReq;
import com.emr.project.borrowing.domain.resp.PatientOutResp;
import com.emr.project.borrowing.domain.vo.SysDictDataVO;
import com.emr.project.borrowing.mapper.EmrIndexBorrowMapper;
import com.emr.project.borrowing.service.IEmrIndexBorrowService;
import com.emr.project.common.service.ICommonService;
import com.emr.project.pat.service.IAlleInfoService;
import com.emr.project.qc.domain.EmrQcFlow;
import com.emr.project.qc.mapper.EmrQcFlowMapper;
import com.emr.project.system.domain.SysDictData;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.mapper.SysDictDataMapper;
import com.emr.project.system.service.ISysDictDataService;
import com.emr.project.system.service.ISysEmrConfigService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmrIndexBorrowServiceImpl implements IEmrIndexBorrowService {
   @Autowired
   private EmrIndexBorrowMapper emrIndexBorrowMapper;
   @Autowired
   private EmrQcFlowMapper emrQcFlowMapper;
   @Autowired
   private SysDictDataMapper sysDictDataMapper;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private ISysDictDataService sysDictDataService;
   @Autowired
   private IAlleInfoService alleInfoService;
   @Autowired
   private ICommonService commonService;

   public EmrIndexBorrow selectEmrIndexBorrowById(Long id) {
      return this.emrIndexBorrowMapper.selectEmrIndexBorrowById(id);
   }

   public List selectEmrIndexBorrowList(EmrIndexBorrow emrIndexBorrow) {
      return this.emrIndexBorrowMapper.selectEmrIndexBorrowList(emrIndexBorrow);
   }

   public List selectEmrIndexBorrowValidList(EmrIndexBorrow emrIndexBorrow) {
      return this.emrIndexBorrowMapper.selectEmrIndexBorrowValidList(emrIndexBorrow);
   }

   public int insertEmrIndexBorrow(EmrIndexBorrow emrIndexBorrow) {
      return this.emrIndexBorrowMapper.insertEmrIndexBorrow(emrIndexBorrow);
   }

   public int updateEmrIndexBorrow(EmrIndexBorrow emrIndexBorrow) {
      return this.emrIndexBorrowMapper.updateEmrIndexBorrow(emrIndexBorrow);
   }

   public int deleteEmrIndexBorrowByIds(Long[] ids) {
      return this.emrIndexBorrowMapper.deleteEmrIndexBorrowByIds(ids);
   }

   public int deleteEmrIndexBorrowById(Long id) {
      return this.emrIndexBorrowMapper.deleteEmrIndexBorrowById(id);
   }

   public Map checkMedicalRecord(CheckMedicalRecordReq req) {
      Map<String, Boolean> map = new HashMap();
      map.put("flag", Boolean.FALSE);
      map.put("isSubmit", Boolean.FALSE);
      map.put("appStatus", Boolean.FALSE);
      String admissionNo = req.getInpNo();
      EmrQcFlow emrQcFlow = this.emrQcFlowMapper.checkMedicalRecord(admissionNo);
      if (emrQcFlow != null) {
         String mrState = emrQcFlow.getMrState();
         if (StringUtils.isNotEmpty(mrState) && ("14".equals(mrState) || "16".equals(mrState))) {
            map.put("flag", Boolean.TRUE);
            SysUser user = SecurityUtils.getLoginUser().getUser();
            String userName = user.getUserName();
            EmrIndexBorrow emrIndexBorrow = this.emrIndexBorrowMapper.selectEmrIndexBorrowByAdmissionNo(admissionNo, userName);
            if (emrIndexBorrow != null) {
               map.put("isSubmit", Boolean.TRUE);
               if (emrIndexBorrow.getAppStatus().equals("1")) {
                  map.put("appStatus", Boolean.TRUE);
               }
            }
         }
      }

      return map;
   }

   public List getApplyList(EmrIndexBorrow emrIndexBorrow) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      emrIndexBorrow.setAppDocCd(user.getUserName());
      return this.emrIndexBorrowMapper.selectApplyList(emrIndexBorrow);
   }

   public List selectReviewedList(BorrowReviewedListReq req) throws Exception {
      String deptCode = req.getDeptCode();
      String appStatus = req.getAppStatus();
      String status = req.getStatus();
      String searchStr = req.getSearchStr();
      String appDeptCd = req.getAppDeptCd();
      Integer dateType = req.getDateType();
      String appDocName = req.getAppDocName();
      String conDocName = req.getConDocName();
      List<String> list = new ArrayList();
      String startTime = req.getStartTime();
      if (StringUtils.isNotEmpty(startTime)) {
         startTime = startTime + " 00:00:00";
      }

      String endTime = req.getEndTime();
      if (StringUtils.isNotEmpty(endTime)) {
         endTime = endTime + " 23:59:59";
      }

      if ("1".equals(status)) {
         list.add("0");
      } else if ("2".equals(status)) {
         if (StringUtils.isNotEmpty(appStatus)) {
            list.add(appStatus);
         } else {
            list.add("1");
            list.add("2");
            list.add("4");
         }
      } else if (StringUtils.isNotEmpty(appStatus)) {
         list.add(appStatus);
      } else {
         list.add("0");
         list.add("1");
         list.add("2");
         list.add("3");
         list.add("4");
      }

      return this.emrIndexBorrowMapper.selectReviewedList(list, startTime, endTime, searchStr, dateType, appDeptCd, appDocName, conDocName, deptCode);
   }

   public List getBorrowPurposeList() {
      List<SysDictDataVO> list = new ArrayList();

      for(SysDictData data : this.sysDictDataMapper.selectDictDataByType("s083")) {
         SysDictDataVO dataVO = new SysDictDataVO();
         BeanUtils.copyProperties(data, dataVO);
         list.add(dataVO);
      }

      return list;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void updateStatus(SaveApplyAgreeListReq req, String borrowStatus) throws Exception {
      List<SaveApplyAgreeReq> list = req.getList();
      SysUser user = SecurityUtils.getLoginUser().getUser();
      Date dbSysdate = this.commonService.getDbSysdate();

      for(SaveApplyAgreeReq saveReq : list) {
         Long id = saveReq.getId();
         EmrIndexBorrow emrIndexBorrow = this.emrIndexBorrowMapper.selectEmrIndexBorrowById(id);
         if (emrIndexBorrow != null) {
            emrIndexBorrow.setAppStatus(borrowStatus);
            emrIndexBorrow.setBorrowPeriod(saveReq.getBorrowPeriod());
            emrIndexBorrow.setConRemark(StringUtils.isNotEmpty(saveReq.getConRemark()) ? saveReq.getConRemark() : "");
            emrIndexBorrow.setConTime(dbSysdate);
            emrIndexBorrow.setConDeptCd(user.getDept().getDeptCode());
            emrIndexBorrow.setConDocName(user.getNickName());
            emrIndexBorrow.setConDocCd(user.getUserName());
            emrIndexBorrow.setConDeptName(user.getDept().getDeptName());
            Date appTime = emrIndexBorrow.getConTime();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(appTime);
            calendar.add(5, Integer.parseInt(saveReq.getBorrowPeriod().toString()));
            Date date = calendar.getTime();
            emrIndexBorrow.setBorrowEndTime(date);
            this.emrIndexBorrowMapper.updateEmrIndexBorrow(emrIndexBorrow);
         }
      }

   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public Map saveApply(EmrIndexBorrow emrIndexBorrow) throws Exception {
      Map<String, Object> map = new HashMap();
      Boolean flag = Boolean.FALSE;
      String configKey = this.sysEmrConfigService.selectSysEmrConfigByKey("0058");
      if (StringUtils.isNotEmpty(configKey) && configKey.equals("0")) {
         flag = Boolean.TRUE;
      } else if (StringUtils.isNotEmpty(configKey) && configKey.equals("2")) {
         boolean isWorkingTime = this.commonService.dateIsWorkingTime(this.commonService.getDbSysdate());
         if (!isWorkingTime) {
            flag = Boolean.TRUE;
         }
      }

      if (flag) {
         emrIndexBorrow.setAppStatus("1");
         emrIndexBorrow.setConTime(this.commonService.getDbSysdate());
         emrIndexBorrow.setConDocName("SYSTEM");
      } else {
         emrIndexBorrow.setAppStatus("0");
      }

      SysUser user = SecurityUtils.getLoginUser().getUser();
      emrIndexBorrow.setId(SnowIdUtils.uniqueLong());
      emrIndexBorrow.setAppDocCd(user.getUserName());
      emrIndexBorrow.setAppDeptCd(user.getDept().getDeptCode());
      emrIndexBorrow.setAppDeptName(user.getDept().getDeptName());
      emrIndexBorrow.setAppDocName(user.getNickName());
      emrIndexBorrow.setAppTime(new Date());
      emrIndexBorrow.setBorrowPeriodUnit("d");
      emrIndexBorrow.setResidentCode(emrIndexBorrow.getResidentNo());
      emrIndexBorrow.setLeaveHospitalDate(emrIndexBorrow.getLeaveHospitalDateTime());
      Date appTime = emrIndexBorrow.getAppTime();
      Calendar calendar = new GregorianCalendar();
      calendar.setTime(appTime);
      calendar.add(5, Integer.parseInt(emrIndexBorrow.getBorrowPeriod().toString()));
      Date date = calendar.getTime();
      emrIndexBorrow.setBorrowEndTime(date);
      emrIndexBorrow.setCrePerCode(user.getUserName());
      emrIndexBorrow.setCreDate(new Date());
      emrIndexBorrow.setCrePerName(user.getNickName());
      this.emrIndexBorrowMapper.insertEmrIndexBorrow(emrIndexBorrow);
      map.put("flag", flag);
      map.put("id", emrIndexBorrow.getId());
      return map;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void updateApply(UpdateApplyDetailReq req, String borrowStatus, EmrIndexBorrow emrIndexBorrow) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      emrIndexBorrow.setAppStatus(borrowStatus);
      if (StringUtils.isNotEmpty(req.getBorrowPurpose())) {
         emrIndexBorrow.setBorrowPurpose(req.getBorrowPurpose());
      }

      if (req.getBorrowPeriod() != null) {
         Date appTime = emrIndexBorrow.getAppTime();
         Calendar calendar = new GregorianCalendar();
         calendar.setTime(appTime);
         calendar.add(5, Integer.parseInt(req.getBorrowPeriod().toString()));
         Date date = calendar.getTime();
         emrIndexBorrow.setBorrowEndTime(date);
         emrIndexBorrow.setBorrowPeriod(req.getBorrowPeriod());
      }

      emrIndexBorrow.setAltDate(new Date());
      emrIndexBorrow.setAltPerCode(user.getUserName());
      emrIndexBorrow.setAltPerName(user.getNickName());
      this.emrIndexBorrowMapper.updateEmrIndexBorrow(emrIndexBorrow);
   }

   public Boolean checkApplyByAdmissionNo(String admissionNo) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      String userName = user.getUserName();
      List<EmrIndexBorrow> list = this.emrIndexBorrowMapper.checkApplyByAdmissionNo(admissionNo, userName);
      return list != null && list.size() > 0 ? Boolean.FALSE : Boolean.TRUE;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void autoReturn() throws Exception {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
      String dateTime = sdf.format(new Date());
      List<Long> idList = this.emrIndexBorrowMapper.selectAutoReturnList(dateTime);
      if (idList != null && idList.size() > 0) {
         this.emrIndexBorrowMapper.updateAutoReturnByList(idList);
      }

   }

   public List patientOutList(PatientOutReq req) {
      String startTime = req.getStartTime();
      if (StringUtils.isNotEmpty(startTime)) {
         startTime = startTime + " 00:00:00";
      }

      String endTime = req.getEndTime();
      if (StringUtils.isNotEmpty(endTime)) {
         endTime = endTime + " 23:59:59";
      }

      SysUser user = SecurityUtils.getLoginUser().getUser();
      String deptNo = req.getDeptNo();
      String searchStr = req.getSearchStr();
      List<PatientOutResp> list = this.emrIndexBorrowMapper.selectPatientOutList(startTime, endTime, deptNo, searchStr);
      List<String> inpNoList = (List)list.stream().map(PatientOutResp::getInpNo).collect(Collectors.toList());
      List<EmrQcFlow> qcFlowList = this.emrQcFlowMapper.selectEmrQcFlowListByInpNoList(inpNoList, user.getHospital().getOrgCode());
      Map<String, List<EmrQcFlow>> detailListMap = (Map)qcFlowList.stream().collect(Collectors.groupingBy(EmrQcFlow::getInpNo));

      for(PatientOutResp vo : list) {
         Date newDate = new Date();
         if (vo.getOutTime() != null) {
            newDate = vo.getOutTime();
         }

         int dayNum = DateUtils.getDiffDay(newDate, vo.getInhosTime());
         vo.setInhosDayNum(dayNum + 1);
         String age = AgeUtil.getAgeStr(vo.getAgeY(), vo.getAgeM(), vo.getAgeD(), vo.getAgeH(), vo.getAgeMi());
         vo.setAge(age);
         List<EmrQcFlow> emrQcFlowList = (List)detailListMap.get(vo.getInpNo());
         if (emrQcFlowList != null && emrQcFlowList.size() > 0) {
            EmrQcFlow emrQcFlow = (EmrQcFlow)emrQcFlowList.get(0);
            String mrState = emrQcFlow.getMrState();
            String mrStateStr = "";
            switch (emrQcFlow.getMrState()) {
               case "10":
                  mrStateStr = "提交科室质控";
                  break;
               case "00":
                  mrStateStr = "未提交质控";
                  break;
               case "11":
                  mrStateStr = "科室退回";
                  break;
               case "12":
                  mrStateStr = "申请归档";
                  break;
               case "13":
                  mrStateStr = "终末质控退回";
                  break;
               case "15":
                  mrStateStr = "归档退回";
                  break;
               case "17":
                  mrStateStr = "取消归档";
                  break;
               case "16":
               case "14":
                  mrStateStr = "病历归档";
            }

            vo.setMrState(mrState);
            vo.setMrStateStr(mrStateStr);
            Date fileTime = emrQcFlow.getFileTime();
            if (fileTime != null) {
               vo.setFileTime(fileTime);
            }
         } else {
            vo.setMrStateStr("未提交科室质控");
         }
      }

      return list;
   }
}
