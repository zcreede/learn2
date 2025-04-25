package com.emr.project.emr.service.impl;

import com.emr.common.constant.CommonConstants;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.common.service.ICommonService;
import com.emr.project.emr.domain.EmrBinary;
import com.emr.project.emr.domain.EmrSignData;
import com.emr.project.emr.domain.EmrSignRecord;
import com.emr.project.emr.domain.EmrTaskInfo;
import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.ModifyAppl;
import com.emr.project.emr.domain.SubfileIndex;
import com.emr.project.emr.domain.vo.EmrSignDataVo;
import com.emr.project.emr.domain.vo.EmrSignRecordVo;
import com.emr.project.emr.domain.vo.IndexSignCancelVo;
import com.emr.project.emr.domain.vo.ModifyApplVo;
import com.emr.project.emr.domain.vo.SysEmrTypeConfigVo;
import com.emr.project.emr.mapper.EmrSignRecordMapper;
import com.emr.project.emr.service.IEmrBinaryService;
import com.emr.project.emr.service.IEmrSignDataService;
import com.emr.project.emr.service.IEmrSignRecordService;
import com.emr.project.emr.service.IEmrTaskInfoService;
import com.emr.project.emr.service.IIndexService;
import com.emr.project.emr.service.IModifyApplService;
import com.emr.project.emr.service.ISubfileIndexService;
import com.emr.project.emr.service.ISysEmrTypeConfigService;
import com.emr.project.other.domain.ImpDoctorTemp;
import com.emr.project.other.service.IImpDoctorTempService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysOrg;
import com.emr.project.system.domain.SysUser;
import com.emr.project.tmpl.domain.TmplIndex;
import com.emr.project.tmpl.service.IElemSignService;
import com.emr.project.tmpl.service.ITmplIndexService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmrSignRecordServiceImpl implements IEmrSignRecordService {
   @Autowired
   private EmrSignRecordMapper emrSignRecordMapper;
   @Autowired
   private IEmrBinaryService emrBinaryService;
   @Autowired
   private IModifyApplService modifyApplService;
   @Autowired
   private IEmrSignDataService emrSignDataService;
   @Autowired
   private IEmrTaskInfoService emrTaskInfoService;
   @Autowired
   private IElemSignService elemSignService;
   @Autowired
   private IIndexService indexService;
   @Autowired
   private ISubfileIndexService subfileIndexService;
   @Autowired
   private ISysEmrTypeConfigService sysEmrTypeConfigService;
   @Autowired
   private IImpDoctorTempService impDoctorTempService;
   @Autowired
   private ITmplIndexService tmplIndexService;
   @Autowired
   private ICommonService commonService;

   public EmrSignRecord selectEmrSignRecordById(Long id) {
      return this.emrSignRecordMapper.selectEmrSignRecordById(id);
   }

   public List selectEmrSignRecordList(EmrSignRecord emrSignRecord) {
      return this.emrSignRecordMapper.selectEmrSignRecordList(emrSignRecord);
   }

   public List selectEmrSignRecordFreeList(EmrSignRecord emrSignRecord) {
      return this.emrSignRecordMapper.selectEmrSignRecordFreeList(emrSignRecord);
   }

   public List selectEmrSignRecordForFeeMoveList(EmrSignRecord emrSignRecord) {
      return this.emrSignRecordMapper.selectEmrSignRecordForFeeMoveList(emrSignRecord);
   }

   public List selectEmrSignRecordListByMainFileId(String mrFileId) {
      return this.emrSignRecordMapper.selectEmrSignRecordListByMainFileId(mrFileId);
   }

   public List selectEmrSignRecordListByMainFileIdForFreeMove(String mrFileId) {
      return this.emrSignRecordMapper.selectEmrSignRecordListByMainFileIdForFree(mrFileId);
   }

   public EmrSignRecord selectEmrSignRecordByFileIdAndDoc(String mrFileId, String docCode) {
      return this.emrSignRecordMapper.selectEmrSignRecordByFileIdAndDoc(mrFileId, docCode);
   }

   public int insertEmrSignRecord(EmrSignRecord emrSignRecord) {
      return this.emrSignRecordMapper.insertEmrSignRecord(emrSignRecord);
   }

   public int updateEmrSignRecord(EmrSignRecord emrSignRecord) {
      return this.emrSignRecordMapper.updateEmrSignRecord(emrSignRecord);
   }

   public int updateEmrSignRecordByMrFileId(EmrSignRecord emrSignRecord) {
      return this.emrSignRecordMapper.updateEmrSignRecordByMrFileId(emrSignRecord);
   }

   public int deleteEmrSignRecordByIds(Long[] ids) {
      return this.emrSignRecordMapper.deleteEmrSignRecordByIds(ids);
   }

   public int deleteEmrSignRecordById(Long id) {
      return this.emrSignRecordMapper.deleteEmrSignRecordById(id);
   }

   public void addList(List signRecordList) {
      this.emrSignRecordMapper.insertList(signRecordList);
   }

   public void batchAddFreeMoveList(List signRecordList) {
      this.emrSignRecordMapper.batchAddFreeMoveList(signRecordList);
   }

   public void addPatList(List signRecordList) {
      this.emrSignRecordMapper.insertPatList(signRecordList);
   }

   public List selectEmrSignRecordListByIdAndFlag(List idList, String signCancFlag) {
      return this.emrSignRecordMapper.selectEmrSignRecordListByIdAndFlag(idList, signCancFlag);
   }

   public Boolean selectSignState(Index index, SubfileIndex subfileIndex, HttpServletRequest request) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      Long id = subfileIndex == null ? index.getId() : subfileIndex.getId();
      Date indexUpdateTime = subfileIndex == null ? (index.getAltDate() == null ? index.getCreDate() : index.getAltDate()) : (subfileIndex.getAltDate() == null ? subfileIndex.getCreDate() : subfileIndex.getAltDate());
      String mrState = subfileIndex == null ? index.getMrState() : subfileIndex.getMrState();
      String crePerCode = subfileIndex == null ? index.getCrePerCode() : subfileIndex.getCrePerCode();
      Boolean signCancleFlag = false;
      Boolean mrStateFlag = mrState.equals("05") || mrState.equals("07") || mrState.equals("08");
      EmrSignData emrSignDataParam = new EmrSignData();
      emrSignDataParam.setSignFileId(id.toString());
      emrSignDataParam.setSignerCd(SecurityUtils.getLoginUser().getUser().getUserName());
      emrSignDataParam.setIsValid("1");
      List<EmrSignData> signDataList = this.emrSignDataService.selectEmrSignDataList(emrSignDataParam);
      Boolean signDataFlag = signDataList != null && !signDataList.isEmpty();
      Date signTime = signDataFlag ? ((EmrSignData)signDataList.get(0)).getSignTime() : null;
      EmrBinary emrBinary = this.emrBinaryService.selectEmrBinaryById(id);
      Date binaryTime = emrBinary.getAltDate() == null ? emrBinary.getCreDate() : emrBinary.getAltDate();
      boolean updateTimeFlag = indexUpdateTime != null && signTime != null && binaryTime != null && indexUpdateTime.compareTo(signTime) == 0 && indexUpdateTime.compareTo(binaryTime) == 0;
      ModifyAppl modifyAppl = this.modifyApplService.selectModifyApplByEndDate(CommonConstants.EMR_MODIFY_APPLY.APP_REASON_1, "1", id, crePerCode, (String)null);
      Boolean modifyApplFlag = modifyAppl != null;
      if (mrStateFlag && signDataFlag && (updateTimeFlag || modifyApplFlag)) {
         signCancleFlag = true;
      }

      return signCancleFlag;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public EmrSignRecordVo cancleEmrSign(Index index, SubfileIndex subfileIndex, List cancelSignList) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      String state = "03";
      Long tempId = subfileIndex == null ? index.getTempId() : subfileIndex.getTempId();
      TmplIndex tempIndex = this.tmplIndexService.selectTmplIndexById(tempId);
      SysEmrTypeConfigVo sysEmrTypeConfigVo = this.sysEmrTypeConfigService.selectSysEmrTypeConfigByTypeId(tempIndex.getTempType());
      String reviewedLevel = sysEmrTypeConfigVo.getReviewedLevel();
      String oldMrType = index.getMrState();
      String mrType = index.getMrType();
      if (mrType.equals("MAINFILE")) {
         oldMrType = subfileIndex.getMrState();
      }

      if ("1".equals(reviewedLevel)) {
         state = "03";
      } else if ("2".equals(reviewedLevel)) {
         if (oldMrType.equals("08")) {
            state = "05";
         } else if (oldMrType.equals("05")) {
            state = "03";
         }
      } else if (oldMrType.equals("08")) {
         state = "07";
      } else if (oldMrType.equals("07")) {
         state = "05";
      } else {
         state = "03";
      }

      if (subfileIndex == null) {
         Index indexTemp = new Index();
         indexTemp.setId(index.getId());
         indexTemp.setMrState(state);
         indexTemp.setAltPerCode(user.getUserName());
         indexTemp.setAltPerName(user.getNickName());
         this.indexService.updateIndex(indexTemp);
      } else {
         SubfileIndex subfileIndexTemp = new SubfileIndex();
         subfileIndexTemp.setId(subfileIndex.getId());
         subfileIndexTemp.setMrState(state);
         subfileIndexTemp.setAltPerCode(user.getUserName());
         subfileIndexTemp.setAltPerName(user.getNickName());
         this.subfileIndexService.updateSubfileIndex(subfileIndexTemp);
      }

      Long id = subfileIndex == null ? index.getId() : subfileIndex.getId();
      EmrSignData paramDataAll = new EmrSignData();
      paramDataAll.setSignFileId(id.toString());
      List<EmrSignData> signDataListAll = this.emrSignDataService.selectEmrSignDataList(paramDataAll);
      EmrSignRecord paramRecordAll = new EmrSignRecord();
      paramRecordAll.setMrFileId(id);
      List<EmrSignRecord> signRecordListAll = this.emrSignRecordMapper.selectEmrSignRecordList(paramRecordAll);
      List<EmrSignRecord> signRecordList = this.emrSignRecordMapper.selectEmrSignRecordListByMainFileId(id.toString());
      Date currEmrDate = ((EmrSignRecord)signRecordList.get(0)).getSignTime();
      List<EmrSignRecord> currDocSignRecList = CollectionUtils.isNotEmpty(signRecordListAll) ? (List)signRecordListAll.stream().filter((t) -> t.getSignTime().compareTo(currEmrDate) == 0).collect(Collectors.toList()) : null;
      List<Long> currDocSignDataIds = (List<Long>)(CollectionUtils.isNotEmpty(currDocSignRecList) ? (List)currDocSignRecList.stream().map((t) -> t.getSignDataId()).collect(Collectors.toList()) : new ArrayList(1));
      List<EmrSignData> currDocSignDataList = CollectionUtils.isNotEmpty(signDataListAll) ? (List)signDataListAll.stream().filter((t) -> currDocSignDataIds.contains(t.getId())).collect(Collectors.toList()) : null;
      if (state.equals("03")) {
         this.emrSignDataService.updateCancelSignByFile(id);
         this.emrSignRecordMapper.updateCancelSignByFile(id);
      } else {
         if (CollectionUtils.isNotEmpty(currDocSignDataList)) {
            EmrSignDataVo vo = new EmrSignDataVo();
            vo.setIdList(currDocSignDataIds);
            this.emrSignDataService.updateCancelSign(vo);
         }

         if (CollectionUtils.isNotEmpty(currDocSignRecList)) {
            List<Long> signRecIds = (List)currDocSignRecList.stream().map((t) -> t.getId()).collect(Collectors.toList());
            this.emrSignRecordMapper.updateCancelSign(signRecIds);
         }
      }

      Date emrDate = null;
      if (!state.equals("03")) {
         for(EmrSignRecord record : signRecordList) {
            if (currEmrDate.compareTo(record.getSignTime()) != 0) {
               emrDate = record.getEmrUpdateTime();
               break;
            }
         }
      }

      if (state.equals("03")) {
         Map<String, List<EmrSignRecord>> signRecMap = CollectionUtils.isNotEmpty(signRecordListAll) ? (Map)signRecordListAll.stream().collect(Collectors.groupingBy((t) -> t.getSignSname())) : null;
         currDocSignRecList = new ArrayList(1);

         for(String signSname : signRecMap.keySet()) {
            EmrSignRecord temp = CollectionUtils.isNotEmpty((Collection)signRecMap.get(signSname)) ? (EmrSignRecord)((List)signRecMap.get(signSname)).get(0) : null;
            if (temp != null) {
               currDocSignRecList.add(temp);
            }
         }
      }

      if (!state.equals("03")) {
         emrDate = emrDate == null ? this.commonService.getDbSysdate() : emrDate;
         EmrBinary emrBinary = new EmrBinary();
         emrBinary.setMrFileId(id);
         emrBinary.setAltDate(emrDate);
         this.emrBinaryService.updateEmrBinary(emrBinary);
         if (subfileIndex == null) {
            Index indexTemp = new Index();
            indexTemp.setId(index.getId());
            indexTemp.setAltDate(emrDate);
            this.indexService.updateIndex(indexTemp);
         } else {
            SubfileIndex subfileIndex1 = new SubfileIndex();
            subfileIndex1.setId(id);
            subfileIndex1.setAltDate(emrDate);
            this.subfileIndexService.updateSubfileIndex(subfileIndex1);
         }
      }

      EmrTaskInfo emrTask = new EmrTaskInfo();
      emrTask.setPatientId(index.getPatientId());
      emrTask.setMrFileId(id);
      emrTask.setTaskType("3");
      emrTask.setTreatFlag("0");
      List<EmrTaskInfo> emrTaskInfos = this.emrTaskInfoService.selectEmrTaskInfoList(emrTask);
      if (emrTaskInfos != null && emrTaskInfos.size() > 0) {
         this.emrTaskInfoService.deleteEmrTaskInfoByMrFileId(id);
      }

      if ("03".equals(state)) {
         this.emrTaskInfoService.deleteEmrTaskInfoByMrFileId(id);
      } else {
         EmrTaskInfo param = new EmrTaskInfo();
         param.setPatientId(index.getPatientId());
         param.setMrFileId(id);
         param.setTaskType("3");
         param.setTreatFlag("1");
         List<EmrTaskInfo> emrTaskInfoList = this.emrTaskInfoService.selectEmrTaskInfoList(param);
         if (CollectionUtils.isNotEmpty(emrTaskInfoList)) {
            List<EmrTaskInfo> emrTaskInfoList2 = (List)emrTaskInfoList.stream().filter((t) -> t.getBeginTime() != null).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(emrTaskInfoList2) && emrTaskInfoList.size() == emrTaskInfoList2.size()) {
               emrTaskInfoList.sort((t1, t2) -> t2.getBeginTime().compareTo(t1.getBeginTime()));
               Long taskId = ((EmrTaskInfo)emrTaskInfoList.get(0)).getId();
               this.emrTaskInfoService.updateTaskInfoBydocCode(id, user.getBasEmployee().getEmplNumber(), taskId);
            }
         } else {
            this.emrTaskInfoService.updateTaskInfoBydocCode(id, user.getBasEmployee().getEmplNumber(), (Long)null);
         }
      }

      String deptCode = index.getDeptCd();
      if (!deptCode.equals(user.getDept().getDeptCode())) {
         List<ImpDoctorTemp> temp = this.impDoctorTempService.selectImpDoctorTemp(user.getUserName(), index.getPatientId());
         if (temp == null || temp.size() == 0) {
            ImpDoctorTemp impDoctorTemp = new ImpDoctorTemp();
            impDoctorTemp.setId(SnowIdUtils.uniqueLong());
            impDoctorTemp.setDocCode(user.getUserName());
            impDoctorTemp.setDocName(user.getNickName());
            impDoctorTemp.setCrePerCode(user.getUserName());
            impDoctorTemp.setCrePerName(user.getNickName());
            SysOrg hospital = user.getHospital();
            impDoctorTemp.setOrgCd(hospital.getOrgCode());
            impDoctorTemp.setValidFlag("1");
            impDoctorTemp.setPatientId(index.getPatientId());
            impDoctorTemp.setPatientName(index.getPatientName());
            impDoctorTemp.setBedNo(index.getBedNo());
            impDoctorTemp.setImpDocCd(user.getUserName());
            impDoctorTemp.setImpDocName(user.getNickName());
            impDoctorTemp.setImpDeptCd(user.getDept().getDeptCode());
            impDoctorTemp.setImpDeptName(user.getDept().getDeptName());
            impDoctorTemp.setImpRange("0");
            impDoctorTemp.setImpBegTime(new Date());
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(new Date());
            calendar.add(5, 7);
            impDoctorTemp.setImpEndTime(calendar.getTime());
            impDoctorTemp.setImpType("2");
            impDoctorTemp.setImpAim("取消病历签名，自动重新授权");
            this.impDoctorTempService.insertImpDoctor(impDoctorTemp);
         }

         if ("3".equals(reviewedLevel) && oldMrType.equals("07")) {
            this.impDoctorTempService.updateImpDoctorTempByThird(user.getUserName(), index.getPatientId());
         }
      } else if ("2".equals(reviewedLevel) && oldMrType.equals("05")) {
         this.impDoctorTempService.updateImpDoctorTempByThird(user.getUserName(), index.getPatientId());
      }

      EmrSignRecordVo emrSignRecordVo = new EmrSignRecordVo();
      emrSignRecordVo.setList(currDocSignRecList);
      emrSignRecordVo.setEmrState(state);
      return emrSignRecordVo;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public EmrSignRecordVo cancleEmrSignForFreeMove(Index index, IndexSignCancelVo indexSignCancelVo) throws Exception {
      Long id = indexSignCancelVo.getSubFileIndexId() == null ? indexSignCancelVo.getId() : indexSignCancelVo.getSubFileIndexId();
      SysUser user = SecurityUtils.getLoginUser().getUser();
      EmrTaskInfo emrTaskInfo = new EmrTaskInfo();
      emrTaskInfo.setMrFileId(id);
      emrTaskInfo.setBusSection("22");
      emrTaskInfo.setDocCd(user.getUserName());
      emrTaskInfo.setDutyDeptCd(user.getDept().getDeptCode());
      this.emrTaskInfoService.updateEmrTaskInfoForCancelSign(emrTaskInfo);
      EmrSignData paramDataAll = new EmrSignData();
      paramDataAll.setSignFileId(id.toString());
      paramDataAll.setSignerCd(user.getUserName());
      paramDataAll.setIsValid("1");
      List<EmrSignData> signDataListAll = this.emrSignDataService.selectEmrSignDataForFreeMoveList(paramDataAll);
      EmrSignRecordVo emrSignRecordVo = new EmrSignRecordVo();
      EmrSignRecord paramRecordAll = new EmrSignRecord();
      paramRecordAll.setMrFileId(id);
      paramRecordAll.setDocCode(user.getUserName());
      paramRecordAll.setSignCancFlag("0");
      List<EmrSignRecord> signRecordListAll = this.emrSignRecordMapper.selectEmrSignRecordFreeList(paramRecordAll);
      List<EmrSignRecord> signRecordList = this.emrSignRecordMapper.selectEmrSignRecordListByMainFileIdForFreeMove(id.toString(), user.getUserName());
      Date currEmrDate = ((EmrSignRecord)signRecordList.get(0)).getSignTime();
      List<EmrSignRecord> currDocSignRecList = CollectionUtils.isNotEmpty(signRecordListAll) ? (List)signRecordListAll.stream().filter((t) -> t.getSignTime().compareTo(currEmrDate) == 0).collect(Collectors.toList()) : null;
      List<Long> currDocSignDataIds = (List<Long>)(CollectionUtils.isNotEmpty(currDocSignRecList) ? (List)currDocSignRecList.stream().map((t) -> t.getSignDataId()).collect(Collectors.toList()) : new ArrayList(1));
      List<EmrSignData> currDocSignDataList = CollectionUtils.isNotEmpty(signDataListAll) ? (List)signDataListAll.stream().filter((t) -> currDocSignDataIds.contains(t.getId())).collect(Collectors.toList()) : null;
      if (CollectionUtils.isNotEmpty(currDocSignDataList)) {
         EmrSignDataVo vo = new EmrSignDataVo();
         vo.setIdList(currDocSignDataIds);
         this.emrSignDataService.updateCancelSignForFreeMove(vo);
      }

      if (CollectionUtils.isNotEmpty(currDocSignRecList)) {
         List<Long> signRecIds = (List)currDocSignRecList.stream().map((t) -> t.getId()).collect(Collectors.toList());
         this.emrSignRecordMapper.updateCancelSignForFreeMove(signRecIds);
      }

      emrSignRecordVo.setList(currDocSignRecList);
      emrSignRecordVo.setEmrState("08");
      emrSignRecordVo.setFreeMoveType("22");
      return emrSignRecordVo;
   }

   public EmrSignRecordVo selectIndexEditState(List modifyApplVoList, Boolean cancleSignFlag, String reason, EmrSignRecordVo emrSignRecordVo) {
      Long nowSecond = DateUtils.getNowDate().getTime();

      for(ModifyApplVo mav : modifyApplVoList) {
         Long endDateTimeToSecond = mav.getEndDatetime().getTime();
         if (nowSecond < endDateTimeToSecond) {
            cancleSignFlag = true;
         } else {
            cancleSignFlag = false;
            reason = "病历修改已超过处理时限，请重新申请";
         }
      }

      if (StringUtils.isNotBlank(reason)) {
         emrSignRecordVo.setReason(reason);
      }

      emrSignRecordVo.setCancleSignFlag(cancleSignFlag);
      return emrSignRecordVo;
   }
}
