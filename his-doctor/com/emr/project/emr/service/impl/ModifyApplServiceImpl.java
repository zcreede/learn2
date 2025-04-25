package com.emr.project.emr.service.impl;

import com.emr.common.constant.CommonConstants;
import com.emr.common.utils.AgeUtil;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.common.service.ICommonService;
import com.emr.project.emr.domain.ModifyAppl;
import com.emr.project.emr.domain.vo.IndexVo;
import com.emr.project.emr.domain.vo.ModifyApplVo;
import com.emr.project.emr.mapper.ModifyApplMapper;
import com.emr.project.emr.service.IIndexService;
import com.emr.project.emr.service.IModifyApplService;
import com.emr.project.qc.domain.EmrQcFlow;
import com.emr.project.qc.domain.EmrQcRecord;
import com.emr.project.qc.service.IEmrQcFlowService;
import com.emr.project.qc.service.IEmrQcRecordService;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysEmrConfigService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModifyApplServiceImpl implements IModifyApplService {
   @Autowired
   private ModifyApplMapper modifyApplMapper;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private IEmrQcFlowService emrQcFlowService;
   @Autowired
   private IIndexService indexService;
   @Autowired
   private IEmrQcRecordService emrQcRecordService;

   public ModifyAppl selectModifyApplById(Long id) {
      return this.modifyApplMapper.selectModifyApplById(id);
   }

   public List selectModifyApplByIds(List ids) {
      return this.modifyApplMapper.selectModifyApplByIds(ids);
   }

   public List selectModifyApplList(ModifyApplVo modifyApplVo) throws Exception {
      List<ModifyApplVo> list = this.modifyApplMapper.selectModifyApplList(modifyApplVo);
      if (list != null && !list.isEmpty()) {
         this.setIndexEmrTypeName(modifyApplVo, list);
      }

      return list;
   }

   public List selectModifyAppl(ModifyAppl modifyAppl) throws Exception {
      List<ModifyAppl> modifyApplRes = this.modifyApplMapper.selectModifyAppl(modifyAppl);
      return modifyApplRes;
   }

   private void setIndexEmrTypeName(ModifyApplVo modifyApplVo, List list) throws Exception {
      List<ModifyApplVo> listIndex = (List)list.stream().filter((t) -> !t.getAppReasonCd().equals(CommonConstants.EMR_MODIFY_APPLY.APP_REASON_3)).collect(Collectors.toList());
      Map<Long, IndexVo> indexVoMap = new HashMap();
      if (!listIndex.isEmpty()) {
         List<Long> idList = (List)listIndex.stream().map((t) -> t.getMrFileId()).collect(Collectors.toList());
         IndexVo indexVo = new IndexVo();
         indexVo.setPatientId(modifyApplVo.getPatientId());
         indexVo.setIdList(idList);
         List<IndexVo> indexVoList = this.indexService.selectAllIndexList(indexVo);
         indexVoMap = (Map)indexVoList.stream().collect(Collectors.toMap((t) -> t.getId(), Function.identity()));
      }

      for(ModifyApplVo temp : list) {
         String ageStr = AgeUtil.getAgeStr(temp.getAgeY(), temp.getAgeM(), temp.getAgeD(), temp.getAgeH(), temp.getAgeMi());
         temp.setAge(ageStr);
         IndexVo indexVo1 = (IndexVo)indexVoMap.get(temp.getMrFileId());
         temp.setEmrTypeName(indexVo1 != null ? indexVo1.getEmrTypeName() : temp.getEmrTypeName());
      }

   }

   public List selectUnRecordApplList(ModifyApplVo modifyApplVo) throws Exception {
      List<String> mrStateList = new ArrayList();
      mrStateList.add("00");
      mrStateList.add("10");
      mrStateList.add("11");
      modifyApplVo.setMrStateList(mrStateList);
      List<ModifyApplVo> list = this.modifyApplMapper.selectModifyApplList(modifyApplVo);
      if (list != null && !list.isEmpty()) {
         this.setIndexEmrTypeName(modifyApplVo, list);
      } else {
         list = new ArrayList(1);
      }

      return list;
   }

   public List selectRecordApplList(ModifyApplVo modifyApplVo) {
      List<String> mrStateList = new ArrayList();
      mrStateList.add("12");
      mrStateList.add("13");
      mrStateList.add("16");
      modifyApplVo.setMrStateList(mrStateList);
      List<ModifyApplVo> list = this.modifyApplMapper.selectModifyApplList(modifyApplVo);
      if (list != null && !list.isEmpty()) {
         for(ModifyApplVo temp : list) {
            String ageStr = AgeUtil.getAgeStr(temp.getAgeY(), temp.getAgeM(), temp.getAgeD(), temp.getAgeH(), temp.getAgeMi());
            temp.setAge(ageStr);
         }
      }

      return list;
   }

   public List selectUnWriteApplList(ModifyApplVo modifyApplVo) {
      modifyApplVo.setAppReasonCd(CommonConstants.EMR_MODIFY_APPLY.APP_REASON_3);
      List<ModifyApplVo> list = this.modifyApplMapper.selectModifyApplList(modifyApplVo);
      if (list != null && !list.isEmpty()) {
         for(ModifyApplVo temp : list) {
            String ageStr = AgeUtil.getAgeStr(temp.getAgeY(), temp.getAgeM(), temp.getAgeD(), temp.getAgeH(), temp.getAgeMi());
            temp.setAge(ageStr);
         }
      }

      return list;
   }

   public int insertModifyAppl(ModifyAppl modifyAppl) throws Exception {
      modifyAppl.setId(SnowIdUtils.uniqueLong());
      SysUser user = SecurityUtils.getLoginUser().getUser();
      modifyAppl.setCrePerCode(user.getUserName());
      modifyAppl.setCrePerName(user.getNickName());
      modifyAppl.setAppDeptCd(user.getDept().getDeptCode());
      modifyAppl.setAppDeptName(user.getDept().getDeptName());
      modifyAppl.setAppDocCd(user.getUserName());
      modifyAppl.setAppDocName(user.getNickName());
      List<String> stateList = new ArrayList();
      stateList.add("16");
      stateList.add("12");
      String autoFlag = "";
      EmrQcFlow emrQcFlow = this.emrQcFlowService.selectEmrQcFlowById(user.getHospital().getOrgCode(), modifyAppl.getPatientId());
      if (emrQcFlow == null || !stateList.contains(emrQcFlow.getMrState())) {
         autoFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0025");
      }

      if (modifyAppl.getAppReasonCd().equals(CommonConstants.EMR_MODIFY_APPLY.APP_REASON_3)) {
         String createFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0031");
         if (createFlag.equals("1")) {
            autoFlag = "1";
         } else if (createFlag.equals("2")) {
            boolean isWorkingTime = this.commonService.dateIsWorkingTime(this.commonService.getDbSysdate());
            autoFlag = !isWorkingTime ? "1" : "0";
         } else if (createFlag.equals("0")) {
            autoFlag = "0";
         }
      }

      if (StringUtils.isNotBlank(autoFlag) && autoFlag.equals("1")) {
         modifyAppl.setTreatDeadline(24);
         modifyAppl.setDeadlineUnit("H");
         String autoFlag97 = this.sysEmrConfigService.selectSysEmrConfigByKey("0097");
         if (StringUtils.isNotBlank(autoFlag97) && "0".equals(autoFlag97) && modifyAppl.getAppReasonCd().equals(CommonConstants.EMR_MODIFY_APPLY.APP_REASON_1)) {
            EmrQcRecord emrQcRecord = new EmrQcRecord();
            emrQcRecord.setPatientId(modifyAppl.getPatientId());
            emrQcRecord.setQcSection("05");
            emrQcRecord.setBackoutFileBz("1");
            List<EmrQcRecord> emrQcRecordList = this.emrQcRecordService.selectEmrQcRecordList(emrQcRecord);
            if (emrQcRecordList != null && emrQcRecordList.size() > 0) {
               modifyAppl.setConState("0");
            } else {
               modifyAppl.setConState("1");
            }
         } else {
            modifyAppl.setConState("1");
         }

         modifyAppl.setConDocCd(user.getUserName());
         modifyAppl.setConDocName(user.getNickName());
         modifyAppl.setConDate(this.commonService.getDbSysdate());
         modifyAppl.setEndDatetime(this.getEndDateTime(modifyAppl));
         modifyAppl.setAltPerCode(user.getUserName());
         modifyAppl.setAltPerName(user.getNickName());
      } else {
         modifyAppl.setConState("0");
      }

      return this.modifyApplMapper.insertModifyAppl(modifyAppl);
   }

   public int updateModifyAppl(ModifyAppl modifyAppl) {
      return this.modifyApplMapper.updateModifyAppl(modifyAppl);
   }

   public int deleteModifyApplByIds(Long[] ids) {
      return this.modifyApplMapper.deleteModifyApplByIds(ids);
   }

   public int deleteModifyApplById(Long id) {
      return this.modifyApplMapper.deleteModifyApplById(id);
   }

   public void doApproved(ModifyAppl modifyAppl) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      modifyAppl.setConDocCd(user.getUserName());
      modifyAppl.setConDocName(user.getNickName());
      modifyAppl.setEndDatetime(this.getEndDateTime(modifyAppl));
      modifyAppl.setAltPerCode(user.getUserName());
      modifyAppl.setAltPerName(user.getNickName());
      this.modifyApplMapper.updateModifyAppl(modifyAppl);
   }

   private Date getEndDateTime(ModifyAppl modifyAppl) throws Exception {
      Date endDateTime = null;
      Integer treatDeadline = modifyAppl.getTreatDeadline();
      Date currentDate = new Date();
      switch (modifyAppl.getDeadlineUnit()) {
         case "H":
            endDateTime = DateUtils.addHours(currentDate, treatDeadline);
            break;
         case "D":
            endDateTime = DateUtils.addDays(currentDate, treatDeadline);
      }

      return endDateTime;
   }

   public void doApproveds(ModifyApplVo modifyApplVo) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      modifyApplVo.setConDocCd(user.getUserName());
      modifyApplVo.setConDocName(user.getNickName());
      modifyApplVo.setEndDatetime(this.getEndDateTime(modifyApplVo));
      modifyApplVo.setAltPerCode(user.getUserName());
      modifyApplVo.setAltPerName(user.getNickName());
      this.modifyApplMapper.updateModifyApplByIds(modifyApplVo);
   }

   public ModifyAppl selectModifyApplByEndDate(Long appReasonCd, String conState, Long mrFileId, String appDocCd, String appDeptCd) {
      ModifyAppl modifyAppl = new ModifyAppl();
      modifyAppl.setAppReasonCd(appReasonCd);
      modifyAppl.setConState(conState);
      modifyAppl.setMrFileId(mrFileId);
      modifyAppl.setAppDocCd(appDocCd);
      modifyAppl.setAppDeptCd(appDeptCd);
      return this.modifyApplMapper.selectModifyApplByEndDate(modifyAppl);
   }

   public List selectList(ModifyAppl modifyAppl) throws Exception {
      return this.modifyApplMapper.selectList(modifyAppl);
   }

   public List selectSubFileAppls(ModifyApplVo modifyApplVo) throws Exception {
      Date recoDate = modifyApplVo.getRecoDate();
      String recoDateStr = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, recoDate);
      String recoDateStrBegin = recoDateStr + " 00:00:00";
      Date recoDateEnd = DateUtils.addDays(recoDate, 1);
      String recoDateStrEnd = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, recoDateEnd) + " 00:00:00";
      modifyApplVo.setAppReasonCd(CommonConstants.EMR_MODIFY_APPLY.APP_REASON_3);
      modifyApplVo.setRecoDateBegin(recoDateStrBegin);
      modifyApplVo.setRecoDateEnd(recoDateStrEnd);
      modifyApplVo.setMrFlag("0");
      List<String> conStateList = new ArrayList(Arrays.asList("0", "1"));
      modifyApplVo.setConStateList(conStateList);
      return this.modifyApplMapper.selectSubFileAppls(modifyApplVo);
   }

   public ModifyAppl selectModifyApplByAppl(ModifyAppl modifyAppl) throws Exception {
      ModifyApplVo modifyParam = new ModifyApplVo();
      Date recoDate = modifyAppl.getRecoDate();
      if (recoDate != null) {
         String recoDateStr = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, recoDate);
         String recoDateStrBegin = recoDateStr + " 00:00:00";
         Date recoDateEnd = DateUtils.addDays(recoDate, 1);
         String recoDateStrEnd = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, recoDateEnd) + " 00:00:00";
         modifyParam.setRecoDateBegin(recoDateStrBegin);
         modifyParam.setRecoDateEnd(recoDateStrEnd);
      }

      modifyParam.setAppReasonCd(CommonConstants.EMR_MODIFY_APPLY.APP_REASON_3);
      modifyParam.setConState("1");
      modifyParam.setMrFileId(modifyAppl.getMrFileId());
      modifyParam.setAppDocCd((String)null);
      modifyParam.setAppDeptCd((String)null);
      return this.modifyApplMapper.selectModifyApplByAppl(modifyParam);
   }

   public void updateModifyApplVo(ModifyApplVo modifyApplVo) {
      this.modifyApplMapper.updateModifyApplVo(modifyApplVo);
   }
}
