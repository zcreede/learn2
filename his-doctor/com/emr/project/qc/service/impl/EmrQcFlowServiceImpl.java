package com.emr.project.qc.service.impl;

import com.emr.common.utils.AgeUtil;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.ExcelUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.sql.SqlUtil;
import com.emr.framework.aspectj.lang.annotation.DataSource;
import com.emr.framework.aspectj.lang.enums.DataSourceType;
import com.emr.framework.datasource.DruidUtil;
import com.emr.framework.redis.RedisCache;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.PageDomain;
import com.emr.framework.web.page.TableSupport;
import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.domain.vo.PhysignDayVo;
import com.emr.project.emr.domain.vo.EmrTaskInfoVo;
import com.emr.project.emr.domain.vo.IndexVo;
import com.emr.project.emr.service.IEmrTaskInfoService;
import com.emr.project.emr.service.impl.IndexServiceImpl;
import com.emr.project.holiday.domain.TmBsHoliday;
import com.emr.project.holiday.mapper.TmBsHolidayMapper;
import com.emr.project.mrhp.domain.vo.MrHpVo;
import com.emr.project.mrhp.service.IMrHpService;
import com.emr.project.operation.domain.Baseinfomation;
import com.emr.project.operation.domain.Medicalinformation;
import com.emr.project.operation.mapper.MedicalinformationMapper;
import com.emr.project.pat.domain.AlleInfo;
import com.emr.project.pat.domain.Visitinfo;
import com.emr.project.pat.domain.vo.BackLogVo;
import com.emr.project.pat.domain.vo.VisitinfoPersonalVo;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.pat.service.IAlleInfoService;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.qc.domain.DipMidResp;
import com.emr.project.qc.domain.DzblSqgd;
import com.emr.project.qc.domain.EmrQcFlow;
import com.emr.project.qc.domain.EmrQcFlowRecord;
import com.emr.project.qc.domain.EmrQcList;
import com.emr.project.qc.domain.EmrQcRecord;
import com.emr.project.qc.domain.vo.EmrMessageVo;
import com.emr.project.qc.domain.vo.EmrQcFlowScoreVo;
import com.emr.project.qc.domain.vo.EmrQcFlowStatisticVo;
import com.emr.project.qc.domain.vo.EmrQcFlowVo;
import com.emr.project.qc.domain.vo.RevokeToFileReq;
import com.emr.project.qc.mapper.EmrQcFlowMapper;
import com.emr.project.qc.mapper.EmrQcFlowRecordMapper;
import com.emr.project.qc.mapper.EmrQcRecordMapper;
import com.emr.project.qc.service.IEmrMessageService;
import com.emr.project.qc.service.IEmrQcFlowRecordService;
import com.emr.project.qc.service.IEmrQcFlowScoreService;
import com.emr.project.qc.service.IEmrQcFlowService;
import com.emr.project.qc.service.IEmrQcListService;
import com.emr.project.qc.service.IEmrQcPresVeriService;
import com.emr.project.qc.service.IEmrQcRecordService;
import com.emr.project.qc.service.IQcAgiRuleService;
import com.emr.project.qc.util.IndexUtil;
import com.emr.project.revoke.domain.EmrIndexRevokeLog;
import com.emr.project.revoke.service.IEmrIndexRevokeLogService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SyncDatasource;
import com.emr.project.system.domain.SysDept;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.mapper.SysDeptMapper;
import com.emr.project.system.service.ISyncDatasourceService;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.webservice.domain.EmrQcFlowReq;
import com.github.pagehelper.PageHelper;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmrQcFlowServiceImpl implements IEmrQcFlowService {
   protected final Logger log = LoggerFactory.getLogger(this.getClass());
   @Autowired
   private EmrQcFlowMapper emrQcFlowMapper;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private IEmrQcFlowRecordService emrQcFlowRecordService;
   @Autowired
   private IEmrQcRecordService emrQcRecordService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private IEmrQcListService emrQcListService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private IMrHpService mrHpService;
   @Autowired
   private IndexServiceImpl indexService;
   @Autowired
   private IEmrTaskInfoService emrTaskInfoService;
   @Autowired
   private IEmrQcFlowScoreService emrQcFlowScoreService;
   @Autowired
   private IEmrQcPresVeriService emrQcPresVeriService;
   @Autowired
   private IQcAgiRuleService qcAgiRuleService;
   @Autowired
   private IEmrMessageService emrMessageService;
   @Autowired
   private RedisCache redisCache;
   @Autowired
   private EmrQcRecordMapper emrQcRecordMapper;
   @Autowired
   private EmrQcFlowRecordMapper emrQcFlowRecordMapper;
   @Autowired
   private IEmrIndexRevokeLogService emrIndexRevokeLogService;
   @Autowired
   private IAlleInfoService alleInfoService;
   @Autowired
   private TmBsHolidayMapper tmBsHolidayMapper;
   @Autowired
   private SysDeptMapper sysDeptMapper;
   @Autowired
   private MedicalinformationMapper medicalinformationMapper;
   @Autowired
   private ISyncDatasourceService syncDatasourceService;

   public EmrQcFlow selectEmrQcFlowById(Long id) {
      return this.emrQcFlowMapper.selectEmrQcFlowById(id);
   }

   public EmrQcFlow selectEmrQcFlowById(String orgCd, String patientId) {
      return this.emrQcFlowMapper.selectEmrQcFlowByPatientId(orgCd, patientId);
   }

   public List selectEmrQcFlowVoListOrderByOutTime(EmrQcFlowVo emrQcFlowVo) throws Exception {
      List<EmrQcFlowVo> list = this.emrQcFlowMapper.selectEmrQcFlowVoListOrderByOutTime(emrQcFlowVo);
      if (list != null && !list.isEmpty()) {
         this.setVisitinfoAgeAndFlag(list);
      }

      return list;
   }

   public List selectEmrQcFlowVoListOrderByApplyFileTime(EmrQcFlowVo emrQcFlowVo) throws Exception {
      List<EmrQcFlowVo> list = this.emrQcFlowMapper.selectEmrQcFlowVoListOrderByApplyFileTime(emrQcFlowVo);
      if (list != null && !list.isEmpty()) {
         this.setVisitinfoAgeAndFlag(list);
      }

      return list;
   }

   public List selectEmrQcFlowVoListOrderByFileTime(EmrQcFlowVo emrQcFlowVo) throws Exception {
      List<EmrQcFlowVo> list = this.emrQcFlowMapper.selectEmrQcFlowVoListOrderByFileTime(emrQcFlowVo);
      if (list != null && !list.isEmpty()) {
         this.setVisitinfoAgeAndFlag(list);
      }

      return list;
   }

   public List selectEmrQcFlowVoAllList(EmrQcFlowVo emrQcFlowVo) throws Exception {
      List<EmrQcFlowVo> list = this.emrQcFlowMapper.selectEmrQcFlowVoAllList(emrQcFlowVo);
      if (list != null && !list.isEmpty()) {
         List<EmrQcFlowVo> tempList = (List)list.stream().filter((t) -> t.getId() != null).collect(Collectors.toList());
         List<Long> flowIdList = (List)tempList.stream().map((t) -> t.getId()).collect(Collectors.toList());
         List<EmrQcFlowRecord> emrQcFlowRecordList = this.emrQcFlowRecordService.selectEmrQcFlowRecordByQcSns(flowIdList);
         Map<Long, List<EmrQcFlowRecord>> emrQcFlowRecordListMap = (Map)emrQcFlowRecordList.stream().collect(Collectors.groupingBy((t) -> t.getQcSn()));

         for(EmrQcFlowVo rt : list) {
            String age = AgeUtil.getAgeStr(rt.getAgeY(), rt.getAgeM(), rt.getAgeD(), rt.getAgeH(), rt.getAgeMi());
            rt.setAge(age);
            List<EmrQcFlowRecord> recordList = (List)emrQcFlowRecordListMap.get(rt.getId());
            rt.setEmrQcFlowRecordList(recordList);
         }
      }

      return list;
   }

   private void setVisitinfoAgeAndFlag(List list) throws Exception {
      Map<String, List<VisitinfoVo>> visitinfoListMap = this.visitinfoService.selectColorplan();
      List<VisitinfoVo> tempList = (List)visitinfoListMap.get("4");
      Map<String, VisitinfoVo> visitinfoMap = (Map)tempList.stream().collect(Collectors.toMap((t) -> t.getColorTypeCd(), Function.identity()));

      for(EmrQcFlowVo rt : list) {
         String age = AgeUtil.getAgeStr(rt.getAgeY(), rt.getAgeM(), rt.getAgeD(), rt.getAgeH(), rt.getAgeMi());
         rt.setAge(age);
         if (rt.getOutTime() != null) {
            rt.setInhosDayNum(DateUtils.getDiffDay(rt.getOutTime() != null ? rt.getOutTime() : new Date(), rt.getInhosTime()) + 1);
         }

         IndexUtil.setVisitinfoFlag(rt, visitinfoMap);
         AlleInfo alleInfoParam = new AlleInfo();
         alleInfoParam.setPatientId(rt.getPatientId());
         List<AlleInfo> alleInfoList = this.alleInfoService.selectAlleInfoList(alleInfoParam);
         rt.setAlleInfoList(alleInfoList);
      }

   }

   public List selectEmrQcFlowList(EmrQcFlow emrQcFlow) {
      return this.emrQcFlowMapper.selectEmrQcFlowList(emrQcFlow);
   }

   public int insertEmrQcFlow(EmrQcFlow emrQcFlow) {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      emrQcFlow.setCrePerCode(basEmployee.getEmplNumber());
      emrQcFlow.setCrePerName(basEmployee.getEmplName());
      return this.emrQcFlowMapper.insertEmrQcFlow(emrQcFlow);
   }

   public int updateEmrQcFlow(EmrQcFlow emrQcFlow) {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      emrQcFlow.setAltPerCode(basEmployee.getEmplNumber());
      emrQcFlow.setAltPerName(basEmployee.getEmplName());
      return this.emrQcFlowMapper.updateEmrQcFlow(emrQcFlow);
   }

   public int updateEmrQcFlowBySys(EmrQcFlow emrQcFlow) {
      return this.emrQcFlowMapper.updateEmrQcFlow(emrQcFlow);
   }

   public int deleteEmrQcFlowById(Long id) {
      return this.emrQcFlowMapper.deleteEmrQcFlowById(id);
   }

   public void saveScoreInfo(EmrQcFlowScoreVo emrQcFlowScoreVo, EmrQcRecord emrQcRecord) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      BasEmployee basEmployee = user.getBasEmployee();
      EmrQcFlow emrQcFlow = this.selectEmrQcFlowById(user.getHospital().getOrgCode(), emrQcRecord.getPatientId());
      Date dbSysdate = this.commonService.getDbSysdate();
      if (emrQcFlow == null) {
         VisitinfoVo visitinfo = this.visitinfoService.selectVisitinfoById(emrQcRecord.getPatientId());
         emrQcFlow = new EmrQcFlow();
         emrQcFlow.setOrgCd(user.getHospital().getOrgCode());
         emrQcFlow.setPatientId(emrQcRecord.getPatientId());
         emrQcFlow.setPatientName(emrQcRecord.getPatientName());
         emrQcFlow.setInpNo(emrQcRecord.getInpNo());
         emrQcFlow.setVisitId(visitinfo.getVisitId());
         emrQcFlow.setDeptCd(visitinfo.getInDeptCd());
         emrQcFlow.setDeptName(visitinfo.getInDeptName());
         emrQcFlow.setResDocCd(visitinfo.getResDocCd());
         emrQcFlow.setResDocName(visitinfo.getResDocName());
         emrQcFlow.setInDeptTime(visitinfo.getInDeptTime());
         emrQcFlow.setOutDeptTime(visitinfo.getOutTime());
      }

      if (emrQcRecord.getQcSection().equals(emrQcRecord.getQcSection())) {
         emrQcFlow.setCheckQcSn(String.valueOf(emrQcRecord.getId()));
         emrQcFlow.setCheckDocCd(basEmployee.getEmplNumber());
         emrQcFlow.setCheckDocName(basEmployee.getEmplName());
         emrQcFlow.setCheckTime(dbSysdate);
         emrQcFlow.setCheckState(emrQcRecord.getState());
         emrQcFlow.setCheckScore(emrQcFlowScoreVo.getTotalScore());
         emrQcFlow.setCheckGradeNo(emrQcFlowScoreVo.getLevelCode());
         emrQcFlow.setCheckGradeName(emrQcFlowScoreVo.getLevelName());
      }

      if (emrQcFlow.getId() == null) {
         this.insertEmrQcFlow(emrQcFlow);
      } else {
         this.updateEmrQcFlow(emrQcFlow);
      }

   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void saveScoreInfo(EmrQcFlowScoreVo emrQcFlowScoreVo, EmrQcFlowVo emrQcFlow, EmrQcRecord emrQcRecord) throws Exception {
      this.log.warn("质控保存-终末/科室质控归档保存开始" + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      SysUser user = SecurityUtils.getLoginUser().getUser();
      BasEmployee basEmployee = user.getBasEmployee();
      Date dbSysdate = this.commonService.getDbSysdate();
      String configValue0023 = this.sysEmrConfigService.selectSysEmrConfigByKey("0023");
      emrQcRecord.setState("2");
      emrQcRecord.setStateName("质控完成");
      String flowMrStateName = null;
      switch (emrQcRecord.getQcSection()) {
         case "02":
            emrQcFlow.setDeptQcState(emrQcRecord.getState());
            emrQcFlow.setDeptScore(emrQcFlowScoreVo.getTotalScore() != null ? emrQcFlowScoreVo.getTotalScore() : null);
            emrQcFlow.setDeptGradeNo(emrQcFlowScoreVo.getLevelCode());
            emrQcFlow.setDeptGradeName(emrQcFlowScoreVo.getLevelName());
            emrQcFlow.setApplyDeptCd(user.getDept().getDeptCode());
            emrQcFlow.setApplyDeptName(user.getDept().getDeptName());
            emrQcFlow.setApplyDocCd(basEmployee.getEmplNumber());
            emrQcFlow.setApplyDocName(basEmployee.getEmplName());
            emrQcFlow.setApplyFileTime(dbSysdate);
            emrQcFlow.setMrState("12");
            flowMrStateName = "申请归档";
            emrQcFlow.setTermQcState("0");
            break;
         case "05":
            emrQcFlow.setTermQcState(emrQcRecord.getState());
            emrQcFlow.setTermScore(emrQcFlowScoreVo.getTotalScore() != null ? emrQcFlowScoreVo.getTotalScore() : null);
            emrQcFlow.setTermGradeNo(emrQcFlowScoreVo.getLevelCode());
            emrQcFlow.setTermGradeName(emrQcFlowScoreVo.getLevelName());
            emrQcFlow.setFileDocCd(basEmployee.getEmplNumber());
            emrQcFlow.setFileDocName(basEmployee.getEmplName());
            emrQcFlow.setFileTime(dbSysdate);
            String mrstate = configValue0023.equals("1") ? "14" : "16";
            flowMrStateName = configValue0023.equals("1") ? "提交病案室" : "病历归档";
            emrQcFlow.setMrState(mrstate);
      }

      this.updateEmrQcFlow(emrQcFlow);
      if ("05".equals(emrQcRecord.getQcSection())) {
         EmrQcFlow flow = this.emrQcFlowMapper.selectEmrQcFlowById(emrQcFlow.getId());
         if (flow != null) {
            Date firstFileTime = flow.getFirstFileTime();
            if (firstFileTime == null) {
               this.log.warn("质控保存-终末/科室质控归档保存更新flow开始" + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
               this.emrQcFlowMapper.updateFirstFileTimeById(emrQcFlow.getId(), dbSysdate);
            }
         }

         emrQcRecord.setBackoutFileBz("0");
      }

      this.log.warn("质控保存-终末/科室质控归档保存更新record表开始" + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      this.emrQcRecordService.updateEmrQcRecord(emrQcRecord);
      List<EmrQcList> list = this.emrQcListService.selectNoFinishListByPatients(emrQcRecord.getPatientId(), emrQcRecord.getId());
      list = (List)list.stream().filter((t) -> t.getMissingFileFlag().equals(0)).collect(Collectors.toList());
      this.log.warn("质控保存-终末/科室质控归档保存插入消息开始" + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      this.emrMessageService.getSaveEmrMessageList(emrQcRecord, list);
      List<EmrMessageVo> messageList = this.emrMessageService.selectEmrMessageList(new EmrMessageVo());
      if (CollectionUtils.isNotEmpty(messageList)) {
         String messageKey = "message_key:" + user.getUserName();
         this.redisCache.deleteObject(messageKey);
         this.redisCache.setCacheList(messageKey, messageList);
      }

      this.log.warn("质控保存-终末/科室质控归档保存flowScore表开始" + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      this.emrQcFlowScoreService.saveEmrQcFlowScoreVo(emrQcFlowScoreVo);
      emrQcFlow.setQcScore(emrQcFlowScoreVo.getTotalScore() != null ? emrQcFlowScoreVo.getTotalScore() : null);
      emrQcFlow.setQcGradeName(emrQcFlowScoreVo.getLevelName());
      emrQcFlow.setQcGradeNo(emrQcFlowScoreVo.getLevelCode());
      emrQcFlow.setPatientId(emrQcRecord.getPatientId());
      emrQcFlow.setDeptCd(emrQcRecord.getInDeptCd());
      emrQcFlow.setDeptName(emrQcRecord.getInDeptName());
      this.log.warn("质控保存-终末/科室质控归档保存flowRecord开始" + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      this.emrQcFlowRecordService.insertEmrQcFlowRecord(emrQcFlow, emrQcFlow.getMrState(), flowMrStateName, (String)null);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void deptTermGoBack(EmrQcFlowVo emrQcFlow, EmrQcRecord emrQcRecord) throws Exception {
      this.log.warn("质控保存-终末/科室质控退回病历开始" + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      SysUser user = SecurityUtils.getLoginUser().getUser();
      Date currDate = this.commonService.getDbSysdate();
      emrQcRecord.setState("3");
      emrQcRecord.setStateName("已退回");
      if (StringUtils.isNotBlank(emrQcFlow.getBackoutFileBz()) && "1".equals(emrQcFlow.getBackoutFileBz())) {
         emrQcRecord.setBackoutFilePerCode(user.getUserName());
         emrQcRecord.setBackoutFilePerName(user.getUserName());
         emrQcRecord.setBackoutFileDate(this.commonService.getDbSysdate());
         emrQcRecord.setBackoutFileBz("1");
      }

      this.log.warn("质控保存-终末/科室质控退回病历开始更新record表" + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      this.emrQcRecordService.updateEmrQcRecord(emrQcRecord);
      String flowMrStateName = null;
      switch (emrQcRecord.getQcSection()) {
         case "02":
            emrQcFlow.setDeptQcState(emrQcRecord.getState());
            emrQcFlow.setMrState("11");
            flowMrStateName = "科室退回";
            break;
         case "05":
            emrQcFlow.setTermQcState(emrQcRecord.getState());
            emrQcFlow.setMrState("13");
            emrQcFlow.setTermQcBackTime(currDate);
            flowMrStateName = "终末质控退回";
      }

      List<EmrQcList> list = this.emrQcListService.selectNoFinishListByPatients(emrQcRecord.getPatientId(), emrQcRecord.getId());
      list = (List)list.stream().filter((t) -> t.getMissingFileFlag().equals(0)).collect(Collectors.toList());
      this.log.warn("质控保存-终末/科室质控退回病历开始插入消息" + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      this.emrMessageService.getSaveEmrMessageList(emrQcRecord, list);
      List<EmrMessageVo> messageList = this.emrMessageService.selectEmrMessageList(new EmrMessageVo());
      if (CollectionUtils.isNotEmpty(messageList)) {
         String messageKey = "message_key:" + user.getUserName();
         this.redisCache.deleteObject(messageKey);
         this.redisCache.setCacheList(messageKey, messageList);
      }

      if (emrQcFlow.getMrState().equals("13")) {
         EmrQcFlow qcFlow = this.selectEmrQcFlowById(emrQcFlow.getId());
         if (qcFlow != null) {
            EmrIndexRevokeLog emrIndexRevokeLog = new EmrIndexRevokeLog();
            emrIndexRevokeLog.setId(SnowIdUtils.uniqueLong());
            emrIndexRevokeLog.setAdmissionNo(qcFlow.getPatientId());
            emrIndexRevokeLog.setCaseNo(qcFlow.getRecordNo());
            emrIndexRevokeLog.setPatientName(qcFlow.getPatientName());
            emrIndexRevokeLog.setHospitalizedCount(qcFlow.getVisitId() == null ? 1L : Long.valueOf((long)qcFlow.getVisitId()));
            emrIndexRevokeLog.setDischargeDepartmentNo(qcFlow.getDeptCd());
            emrIndexRevokeLog.setDischargeDepartmentName(qcFlow.getDeptName());
            emrIndexRevokeLog.setDischargeDepartmentName(qcFlow.getDeptName());
            emrIndexRevokeLog.setHospitalizedDate(qcFlow.getInDeptTime());
            emrIndexRevokeLog.setLeaveHospitalDate(qcFlow.getOutDeptTime());
            emrIndexRevokeLog.setRecallReason(emrQcFlow.getOperReason());
            emrIndexRevokeLog.setConTime(currDate);
            emrIndexRevokeLog.setConDocCd(user.getUserName());
            emrIndexRevokeLog.setConDocName(user.getNickName());
            this.log.warn("质控保存-终末/科室质控退回病历开始写入记录日志" + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
            this.emrIndexRevokeLogService.insertEmrIndexRevokeLog(emrIndexRevokeLog);
         }
      }

      this.log.warn("质控保存-终末/科室质控退回病历开始更新flow表" + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      this.updateEmrQcFlow(emrQcFlow);
      emrQcFlow.setPatientId(emrQcRecord.getPatientId());
      emrQcFlow.setDeptCd(emrQcRecord.getInDeptCd());
      emrQcFlow.setDeptName(emrQcRecord.getInDeptName());
      this.log.warn("质控保存-终末/科室质控退回病历开始插入flow_record表" + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      this.emrQcFlowRecordService.insertEmrQcFlowRecord(emrQcFlow, emrQcFlow.getMrState(), flowMrStateName, emrQcFlow.getOperReason());
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public EmrQcRecord deptTermQc(EmrQcRecord emrQcRecord, String patientId, String qcSection) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      BasEmployee basEmployee = user.getBasEmployee();
      Medicalinformation medicalinformation = this.medicalinformationMapper.selectMedicalinfoByPatientId(patientId);
      SysDept sysDept = this.sysDeptMapper.getOneByCode(medicalinformation.getDepartmentNo());
      String orgCd = sysDept.getOrgCode();
      EmrQcFlow emrQcFlow = this.selectEmrQcFlowById(orgCd, patientId);
      Date dbSysdate = this.commonService.getDbSysdate();
      if (emrQcRecord == null) {
         emrQcRecord = this.emrQcRecordService.qcDoing(patientId, qcSection, (Long)null);
         switch (emrQcRecord.getQcSection()) {
            case "02":
               emrQcFlow.setDeptQcCd(basEmployee.getEmplNumber());
               emrQcFlow.setDeptQcName(basEmployee.getEmplName());
               emrQcFlow.setDeptQcTime(dbSysdate);
               emrQcFlow.setDeptQcState(emrQcRecord.getState());
               break;
            case "05":
               emrQcFlow.setTermQcCd(basEmployee.getEmplNumber());
               emrQcFlow.setTermQcName(basEmployee.getEmplName());
               emrQcFlow.setTermQcTime(dbSysdate);
               emrQcFlow.setTermQcState(emrQcRecord.getState());
         }

         this.emrQcFlowMapper.updateEmrQcFlow(emrQcFlow);
      } else {
         emrQcRecord.setState("1");
         emrQcRecord.setStateName("质控中");
         this.emrQcRecordService.updateEmrQcRecord(emrQcRecord);
         switch (emrQcRecord.getQcSection()) {
            case "02":
               emrQcFlow.setDeptQcState(emrQcRecord.getState());
               break;
            case "05":
               emrQcFlow.setTermQcState(emrQcRecord.getState());
         }

         this.emrQcFlowMapper.updateEmrQcFlow(emrQcFlow);
      }

      return emrQcRecord;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void saveEmrQcFlow(EmrQcFlowVo emrQcFlowVo, String ip, Long qcBillNo) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      EmrQcFlow flow = this.emrQcFlowMapper.selectEmrQcFlowByPatientId(sysUser.getHospital().getOrgCode(), emrQcFlowVo.getPatientId());
      String flag = "";
      if (flow == null) {
         flag = "TRUE";
         flow = new EmrQcFlow();
         VisitinfoPersonalVo visitinfo = this.visitinfoService.selectVisitinfoPersonalById(emrQcFlowVo.getPatientId());
         flow.setOrgCd(sysUser.getHospital().getOrgCode());
         flow.setPatientId(emrQcFlowVo.getPatientId());
         flow.setPatientName(visitinfo.getPatientName());
         flow.setRecordNo(visitinfo.getRecordNo());
         flow.setInpNo(visitinfo.getInpNo());
         flow.setVisitId(visitinfo.getVisitId());
         flow.setDeptCd(visitinfo.getInDeptCd());
         flow.setDeptName(visitinfo.getInDeptName());
         flow.setResDocCd(visitinfo.getResDocCd());
         flow.setResDocName(visitinfo.getResDocName());
         flow.setInDeptTime(visitinfo.getInDeptTime());
         flow.setOutDeptTime(visitinfo.getOutTime());
         flow.setId(SnowIdUtils.uniqueLong());
         flow.setCrePerCode(sysUser.getBasEmployee().getEmplNumber());
         flow.setCrePerName(sysUser.getBasEmployee().getEmplName());
         flow.setMrState(emrQcFlowVo.getMrState());
      } else {
         flow.setAltPerCode(sysUser.getBasEmployee().getEmplNumber());
         flow.setAltPerName(sysUser.getBasEmployee().getEmplName());
      }

      String flowMrStateName = null;
      String qcSection = null;
      switch (emrQcFlowVo.getMrState()) {
         case "00":
            flow.setCheckDocCd(sysUser.getBasEmployee().getEmplNumber());
            flow.setCheckDocName(sysUser.getBasEmployee().getEmplName());
            flow.setCheckTime(this.commonService.getDbSysdate());
            flow.setCheckScore(emrQcFlowVo.getQcScore());
            flow.setCheckGradeNo(emrQcFlowVo.getQcGradeNo());
            flow.setCheckGradeName(emrQcFlowVo.getQcGradeName());
            flow.setCheckState("2");
            this.emrQcFlowRecordService.insertEmrQcFlowRecord(emrQcFlowVo, emrQcFlowVo.getMrState(), "抽查质控完成", ip);
            qcSection = "03";
            break;
         case "10":
            flow.setMrSubCd(sysUser.getBasEmployee().getEmplNumber());
            flow.setMrSubName(sysUser.getBasEmployee().getEmplName());
            flow.setMrSubTime(this.commonService.getDbSysdate());
            flow.setMrState(emrQcFlowVo.getMrState());
            flow.setDeptQcState("0");
            flowMrStateName = "提交科室质控";
            this.emrQcFlowRecordService.insertEmrQcFlowRecord(flow, emrQcFlowVo.getMrState(), flowMrStateName, ip, sysUser.getBasEmployee());
            if (flow == null || flow != null && flow.getMrState().equals("00")) {
               this.emrQcPresVeriService.addListBySubmitQc(emrQcFlowVo.getPatientId());
            }

            qcSection = "02";
            break;
         case "12":
            flow.setApplyDeptCd(sysUser.getDept().getDeptCode());
            flow.setApplyDeptName(sysUser.getDept().getDeptName());
            flow.setApplyDocCd(sysUser.getBasEmployee().getEmplNumber());
            flow.setApplyDocName(sysUser.getBasEmployee().getEmplName());
            flow.setApplyFileTime(this.commonService.getDbSysdate());
            flow.setMrState(emrQcFlowVo.getMrState());
            flow.setTermQcState("0");
            flowMrStateName = "申请归档";
            this.emrQcFlowRecordService.insertEmrQcFlowRecord(flow, emrQcFlowVo.getMrState(), flowMrStateName, ip, sysUser.getBasEmployee());
            qcSection = "05";
      }

      if (StringUtils.isEmpty(flag)) {
         this.emrQcFlowMapper.updateEmrQcFlow(flow);
      } else {
         this.emrQcFlowMapper.insertEmrQcFlow(flow);
      }

      if (!qcSection.equals("03")) {
         EmrQcRecord emrQcRecord = this.emrQcRecordService.selectEmrQcRecordByPatientSection(emrQcFlowVo.getPatientId(), qcSection, (Long)null);
         if (emrQcRecord != null) {
            emrQcRecord.setState("0");
            emrQcRecord.setStateName("未质控");
            this.emrQcRecordService.updateEmrQcRecord(emrQcRecord);
         }
      }

   }

   public AjaxResult isSubmitQcFlowDecide(EmrQcFlow emrQcFlow) throws Exception {
      boolean flag = true;
      String msg = "可以提交";
      VisitinfoVo visitinfoVo = this.visitinfoService.selectVisitinfoByPatientId(emrQcFlow.getPatientId());
      if (flag && (visitinfoVo == null || visitinfoVo.getOutTime() == null)) {
         flag = false;
         msg = "患者暂未出院，不能提交质控";
      }

      if (flag) {
         String configByKey = this.sysEmrConfigService.selectSysEmrConfigByKey("0109");
         this.log.warn("----> start：DIP开始，住院号：" + emrQcFlow.getPatientId() + ",患者姓名：" + emrQcFlow.getPatientName() + "配置项：" + configByKey);
         if (StringUtils.isNotEmpty(configByKey) && configByKey.equals("1")) {
            SyncDatasource syncDatasource2 = this.syncDatasourceService.selectSyncDatasourceByCode(DataSourceType.dipMidHis2.toString());
            List<DipMidResp> respList = new ArrayList();
            this.log.warn("----> 第一步：DIP查询患者医疗信息，住院号：" + emrQcFlow.getPatientId() + ",患者姓名：" + emrQcFlow.getPatientName());
            if (syncDatasource2 != null) {
               this.log.warn("----> 1：查询数据源信息，不为空！");

               try {
                  PhysignDayVo physignDayVo = new PhysignDayVo();
                  physignDayVo.setSqlStr(syncDatasource2.getQuerySql());
                  physignDayVo.setZyh(emrQcFlow.getPatientId());
                  DruidUtil.switchDB(syncDatasource2);
                  this.log.warn("----> 1.1：查询数据源信息，sql:{},住院号：{}", physignDayVo.getSqlStr(), physignDayVo.getZyh());
                  respList = this.emrQcFlowMapper.selectDipMid(physignDayVo);
                  this.log.warn("----> 1.2：查询数据结束，元素：{}", respList.size());
               } finally {
                  DruidUtil.clearDataSource();
               }
            }

            if (!respList.isEmpty()) {
               this.log.warn("----> 第二步：DIP查询患者医疗信息，住院号：" + emrQcFlow.getPatientId() + ",患者姓名：" + emrQcFlow.getPatientName() + ",医疗信息不为空，继续进行！");
               SyncDatasource syncDatasource = this.syncDatasourceService.selectSyncDatasourceByCode(DataSourceType.dipMidHis.toString());
               if (syncDatasource != null) {
                  this.log.warn("----> 2：查询数据源信息，不为空！");

                  try {
                     DruidUtil.switchDB(syncDatasource);
                     String dipFlag = this.emrQcFlowMapper.selectDipFlag(emrQcFlow.getPatientId());
                     this.log.warn("----> 第三步：DIP查询患者上报状态值为" + dipFlag + "，住院号：" + emrQcFlow.getPatientId() + ",患者姓名：" + emrQcFlow.getPatientName() + ",医疗信息不为空，继续进行！");
                     if (StringUtils.isNotEmpty(dipFlag)) {
                        if (!dipFlag.equals("1")) {
                           flag = false;
                           msg = "当前患者DIP中间库未提交，请提交后再试！";
                           this.log.warn("----> 当前患者DIP中间库未提交，住院号：" + emrQcFlow.getPatientId() + ",患者姓名：" + emrQcFlow.getPatientName() + ",医疗信息不为空，继续进行！");
                        }
                     } else {
                        flag = false;
                        msg = "当前患者DIP中间库未提交，请提交后再试！";
                     }
                  } finally {
                     DruidUtil.clearDataSource();
                  }
               }
            }
         }

         this.log.warn("----> end：DIP结束，住院号：" + emrQcFlow.getPatientId() + ",患者姓名：" + emrQcFlow.getPatientName());
      }

      List<String> qcStateList = new ArrayList();
      qcStateList.add("0");
      qcStateList.add("3");
      List<EmrQcList> emrQcListList = this.emrQcListService.selectByPatientsAndQcState(emrQcFlow.getPatientId(), qcStateList, (Long)null);
      Integer fileFlag = 1;
      List<EmrQcList> emrQcListListQx = new ArrayList();
      if (emrQcListList != null && !emrQcListList.isEmpty()) {
         emrQcListListQx = (List)emrQcListList.stream().filter((s) -> StringUtils.isBlank(s.getMrFileId())).collect(Collectors.toList());
      }

      List var25 = emrQcListList != null && !emrQcListList.isEmpty() ? (List)emrQcListList.stream().filter((s) -> !fileFlag.equals(s.getMissingFileFlag())).collect(Collectors.toList()) : new ArrayList();
      MrHpVo mrHpVo = this.mrHpService.selectMrHpByPatientId(emrQcFlow.getPatientId());
      List<EmrTaskInfoVo> taskList = this.qcAgiRuleService.getUnWriteList(emrQcFlow.getOrgCd(), emrQcFlow.getPatientId());
      String orderFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0050");
      String lbMrHpFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0086");
      String mrisFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("000301");
      if (orderFlag.equals("0") && lbMrHpFlag.equals("1") && mrisFlag.equals("1") && flag && (mrHpVo == null || StringUtils.isEmpty(mrHpVo.getMrState()) || !mrHpVo.getMrState().equals("03"))) {
         flag = false;
         msg = "病案首页还未完成签名，不能提交质控";
      }

      List<IndexVo> indexVoList = this.indexService.selectEmrUnFinishList(emrQcFlow.getPatientId());
      if (flag && var25 != null && !((List)var25).isEmpty()) {
         List<EmrQcList> emrList = (List)((List)var25).stream().filter((s) -> s.getMrFileId().equals("01") || s.getMrFileId().equals("02") || s.getMrFileId().equals("03")).collect(Collectors.toList());
         List<EmrQcList> mrHpList = (List)((List)var25).stream().filter((s) -> StringUtils.isNotEmpty(s.getRecordId())).collect(Collectors.toList());
         if (emrList != null && !emrList.isEmpty()) {
            flag = false;
            msg = "有未完成的医嘱本缺陷，不能提交质控";
         } else if (mrHpList != null && !mrHpList.isEmpty()) {
            flag = false;
            msg = "病案首页有未完成的缺陷信息，不能提交质控！";
         } else {
            flag = false;
            msg = "有未完成的病历缺陷信息，不能提交质控";
         }
      }

      if (flag && emrQcListListQx != null && !emrQcListListQx.isEmpty()) {
         flag = false;
         msg = "有未完成的缺失病历信息，不能提交质控";
      }

      if (flag && taskList != null && !taskList.isEmpty()) {
         flag = false;
         msg = "有未书写的病历，不能提交质控";
      }

      if (flag && indexVoList != null && !indexVoList.isEmpty()) {
         flag = false;
         msg = "有未完成的病历，不能提交质控";
      }

      AjaxResult ajaxResult = flag ? AjaxResult.success(msg) : AjaxResult.error(msg);
      ajaxResult.put("flag", flag);
      return ajaxResult;
   }

   public List selectUnSubmitQcList(String patientId) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      List<BackLogVo> result = new ArrayList();
      List<EmrQcFlowVo> list = this.emrQcFlowMapper.selectUnSubmitQcList(basEmployee.getEmplNumber(), patientId);
      if (list != null && !list.isEmpty()) {
         for(EmrQcFlowVo emrQcFlowVo : list) {
            String bedNo = emrQcFlowVo.getBedNo() == null ? "" : emrQcFlowVo.getBedNo();
            String patientName = emrQcFlowVo.getPatientName() == null ? "" : emrQcFlowVo.getPatientName();
            String sex = emrQcFlowVo.getSex() == null ? "" : emrQcFlowVo.getSex();
            String caseNo = StringUtils.isNotBlank(emrQcFlowVo.getRecordNo()) ? emrQcFlowVo.getRecordNo() : "";
            BackLogVo backLogVo = new BackLogVo();
            backLogVo.setMessageStr("【" + bedNo + "床 " + patientName + " " + sex + " " + caseNo + "】 已出院" + emrQcFlowVo.getLimitHours() + "小时,请及时将患者病历提交科室质控。");
            backLogVo.setPatientMainId(emrQcFlowVo.getPatientMainId());
            backLogVo.setPatientId(emrQcFlowVo.getPatientId());
            backLogVo.setInpNo(emrQcFlowVo.getInpNo());
            backLogVo.setType("4");
            result.add(backLogVo);
         }
      }

      return result;
   }

   public List selectUnReturnRecordList(String patientId) throws Exception {
      List<BackLogVo> result = new ArrayList();
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      List<EmrQcFlowVo> list = this.emrQcFlowMapper.selectUnReturnRecordList(basEmployee.getEmplNumber(), patientId);
      Integer hour = Integer.parseInt(this.sysEmrConfigService.selectSysEmrConfigByKey("0020"));
      if (list != null && !list.isEmpty()) {
         for(EmrQcFlowVo emrQcFlowVo : list) {
            BackLogVo backLogVo = new BackLogVo();
            Integer hours = hour - emrQcFlowVo.getLimitHours();
            if (hours > 0) {
               emrQcFlowVo.setSubLimit(hours + "小时");
            } else {
               emrQcFlowVo.setSubLimit("已超时" + Math.abs(hours) + "小时");
            }

            backLogVo.setType("5");
            backLogVo.setPatientId(emrQcFlowVo.getPatientId());
            backLogVo.setPatientMainId(emrQcFlowVo.getPatientMainId());
            backLogVo.setInpNo(emrQcFlowVo.getInpNo());
            String bedNo = emrQcFlowVo.getBedNo() == null ? "" : emrQcFlowVo.getBedNo();
            String patientName = emrQcFlowVo.getPatientName() == null ? "" : emrQcFlowVo.getPatientName();
            String sex = emrQcFlowVo.getSex() == null ? "" : emrQcFlowVo.getSex();
            String caseNo = StringUtils.isNotBlank(emrQcFlowVo.getRecordNo()) ? emrQcFlowVo.getRecordNo() : "";
            backLogVo.setMessageStr("【" + bedNo + "床 " + patientName + " " + sex + " " + caseNo + "】 已出院" + emrQcFlowVo.getLimitHours() + "小时,请及时归档患者病历。");
            result.add(backLogVo);
         }
      }

      return result;
   }

   public void indexToFile(String patientId, EmrQcFlow emrQcFlow) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      VisitinfoPersonalVo visitinfo = this.visitinfoService.selectVisitinfoPersonalById(patientId);
      if (emrQcFlow != null) {
         emrQcFlow.setMrState("16");
         emrQcFlow.setFileDocCd(sysUser.getUserName());
         emrQcFlow.setFileDocName(sysUser.getNickName());
         emrQcFlow.setFileTime(this.commonService.getDbSysdate());
         this.updateEmrQcFlow(emrQcFlow);
      } else {
         emrQcFlow = new EmrQcFlow();
         emrQcFlow.setId(SnowIdUtils.uniqueLong());
         emrQcFlow.setOrgCd(sysUser.getHospital().getOrgCode());
         emrQcFlow.setPatientId(patientId);
         emrQcFlow.setPatientName(visitinfo.getPatientName());
         emrQcFlow.setRecordNo(visitinfo.getRecordNo());
         emrQcFlow.setInpNo(visitinfo.getInpNo());
         emrQcFlow.setVisitId(visitinfo.getVisitId());
         emrQcFlow.setDeptCd(visitinfo.getInDeptCd());
         emrQcFlow.setDeptName(visitinfo.getInDeptName());
         emrQcFlow.setResDocCd(visitinfo.getResDocCd());
         emrQcFlow.setResDocName(visitinfo.getResDocName());
         emrQcFlow.setInDeptTime(visitinfo.getInDeptTime());
         emrQcFlow.setOutDeptTime(visitinfo.getOutTime());
         emrQcFlow.setMrState("16");
         emrQcFlow.setFileDocCd(sysUser.getUserName());
         emrQcFlow.setFileDocName(sysUser.getNickName());
         emrQcFlow.setFileTime(this.commonService.getDbSysdate());
         emrQcFlow.setCrePerCode(sysUser.getBasEmployee().getEmplNumber());
         emrQcFlow.setCrePerName(sysUser.getBasEmployee().getEmplName());
         this.emrQcFlowMapper.insertEmrQcFlow(emrQcFlow);
      }

      EmrQcFlow flow = this.emrQcFlowMapper.selectEmrQcFlowById(emrQcFlow.getId());
      if (flow != null) {
         Date firstFileTime = flow.getFirstFileTime();
         if (firstFileTime == null) {
            this.emrQcFlowMapper.updateFirstFileTimeById(emrQcFlow.getId(), this.commonService.getDbSysdate());
         }
      }

      EmrQcRecord emrQcRecord = this.emrQcRecordService.selectEmrQcRecordByPatientSection(visitinfo.getPatientId(), "05", (Long)null);
      if (emrQcRecord != null) {
         emrQcRecord.setState("2");
         emrQcRecord.setStateName("质控完成");
         this.emrQcRecordService.updateEmrQcRecord(emrQcRecord);
      } else {
         emrQcRecord = new EmrQcRecord();
         emrQcRecord.setId(SnowIdUtils.uniqueLong());
         emrQcRecord.setPatientId(visitinfo.getPatientId());
         emrQcRecord.setPatientName(visitinfo.getPatientName());
         emrQcRecord.setSex(visitinfo.getSexCd());
         emrQcRecord.setInpNo(visitinfo.getPatientId());
         emrQcRecord.setInDeptCd(visitinfo.getDeptCd());
         emrQcRecord.setInDeptName(visitinfo.getDeptName());
         emrQcRecord.setQcSection("05");
         emrQcRecord.setQcdoctCd("sys");
         emrQcRecord.setQcdoctName("系统");
         emrQcRecord.setCrePerCode("sys");
         emrQcRecord.setCrePerName("系统");
         this.emrQcRecordService.insertEmrQcRecord(emrQcRecord);
      }

   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void autoSubmitDeptQc(List list) throws Exception {
      for(Visitinfo visitInfo : list) {
         EmrQcFlow flow = this.emrQcFlowMapper.selectEmrQcFlowByPatientId(visitInfo.getOrgCd(), visitInfo.getPatientId());
         String flag = "";
         if (flow == null) {
            flag = "TRUE";
            flow = new EmrQcFlow();
            flow.setOrgCd(visitInfo.getOrgCd());
            flow.setPatientId(visitInfo.getPatientId());
            flow.setPatientName(visitInfo.getPatientName());
            flow.setRecordNo(visitInfo.getRecordNo());
            flow.setInpNo(visitInfo.getInpNo());
            flow.setVisitId(visitInfo.getVisitId());
            flow.setDeptCd(visitInfo.getDeptCd());
            flow.setDeptName(visitInfo.getDeptName());
            flow.setResDocCd(visitInfo.getResDocCd());
            flow.setResDocName(visitInfo.getResDocName());
            flow.setInDeptTime(visitInfo.getInDeptTime());
            flow.setOutDeptTime(visitInfo.getOutTime());
            flow.setId(SnowIdUtils.uniqueLong());
            flow.setCrePerCode("sys");
            flow.setCrePerName("系统");
         } else {
            flow.setAltPerCode("sys");
            flow.setAltPerName("系统");
         }

         String flowMrStateName = null;
         String qcSection = null;
         flow.setMrSubCd("sys");
         flow.setMrSubName("系统");
         flow.setMrSubTime(this.commonService.getDbSysdate());
         flow.setMrState("10");
         flow.setDeptQcState("0");
         flowMrStateName = "提交科室质控";
         this.emrQcFlowRecordService.insertEmrQcFlowRecord(flow, "10", flowMrStateName, "localhost", (BasEmployee)null);
         qcSection = "02";
         if (StringUtils.isEmpty(flag)) {
            this.emrQcFlowMapper.updateEmrQcFlow(flow);
         } else {
            this.emrQcFlowMapper.insertEmrQcFlow(flow);
         }

         EmrQcRecord emrQcRecord = this.emrQcRecordService.selectEmrQcRecordByPatientSection(visitInfo.getPatientId(), qcSection, (Long)null);
         if (emrQcRecord != null) {
            emrQcRecord.setState("0");
            emrQcRecord.setStateName("未质控");
            emrQcRecord.setAltPerCode("sys");
            emrQcRecord.setAltPerName("系统");
            this.emrQcRecordMapper.updateEmrQcRecord(emrQcRecord);
         }
      }

   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void updateQcFlowByType(VisitinfoVo visitInfoVo, EmrQcFlow emrQcFlow, EmrQcFlowReq req, EmrQcRecord record) throws Exception {
      String type = req.getType();
      EmrQcRecord emrQcRecord = this.emrQcRecordService.selectEmrQcRecordByPatientSection(visitInfoVo.getPatientId(), "05", (Long)null);
      if (type.equals("1")) {
         if (emrQcFlow != null) {
            emrQcFlow.setMrState("16");
            emrQcFlow.setAltPerCode(StringUtils.isEmpty(req.getOperatorCode()) ? "sys" : req.getOperatorCode());
            emrQcFlow.setAltPerName(StringUtils.isEmpty(req.getOperatorName()) ? "系统" : req.getOperatorName());
            this.emrQcFlowMapper.updateEmrQcFlow(emrQcFlow);
         } else {
            emrQcFlow = new EmrQcFlow();
            emrQcFlow.setId(SnowIdUtils.uniqueLong());
            emrQcFlow.setOrgCd(visitInfoVo.getOrgCd());
            emrQcFlow.setPatientId(visitInfoVo.getPatientId());
            emrQcFlow.setPatientName(visitInfoVo.getPatientName());
            emrQcFlow.setRecordNo(visitInfoVo.getRecordNo());
            emrQcFlow.setInpNo(visitInfoVo.getInpNo());
            emrQcFlow.setVisitId(visitInfoVo.getVisitId());
            emrQcFlow.setDeptCd(visitInfoVo.getInDeptCd());
            emrQcFlow.setDeptName(visitInfoVo.getInDeptName());
            emrQcFlow.setResDocCd(visitInfoVo.getResDocCd());
            emrQcFlow.setResDocName(visitInfoVo.getResDocName());
            emrQcFlow.setInDeptTime(visitInfoVo.getInDeptTime());
            emrQcFlow.setOutDeptTime(visitInfoVo.getOutTime());
            emrQcFlow.setMrState("16");
            emrQcFlow.setFileDocCd(StringUtils.isEmpty(req.getOperatorCode()) ? "sys" : req.getOperatorCode());
            emrQcFlow.setFileDocName(StringUtils.isEmpty(req.getOperatorName()) ? "系统" : req.getOperatorName());
            emrQcFlow.setFileTime(this.commonService.getDbSysdate());
            emrQcFlow.setCrePerCode(StringUtils.isEmpty(req.getOperatorCode()) ? "sys" : req.getOperatorCode());
            emrQcFlow.setCrePerName(StringUtils.isEmpty(req.getOperatorName()) ? "系统" : req.getOperatorName());
            this.emrQcFlowMapper.insertEmrQcFlow(emrQcFlow);
         }

         if (emrQcRecord != null) {
            emrQcRecord.setState("2");
            emrQcRecord.setStateName("质控完成");
            emrQcRecord.setAltPerCode(StringUtils.isEmpty(req.getOperatorCode()) ? "sys" : req.getOperatorCode());
            emrQcRecord.setAltPerName(StringUtils.isEmpty(req.getOperatorName()) ? "系统" : req.getOperatorName());
            this.emrQcRecordMapper.updateEmrQcRecord(emrQcRecord);
         } else {
            emrQcRecord = new EmrQcRecord();
            emrQcRecord.setId(SnowIdUtils.uniqueLong());
            emrQcRecord.setPatientId(visitInfoVo.getPatientId());
            emrQcRecord.setPatientName(visitInfoVo.getPatientName());
            emrQcRecord.setSex(visitInfoVo.getSexCd());
            emrQcRecord.setInpNo(visitInfoVo.getPatientId());
            emrQcRecord.setInDeptCd(visitInfoVo.getDeptCd());
            emrQcRecord.setInDeptName(visitInfoVo.getDeptName());
            emrQcRecord.setQcSection("05");
            emrQcRecord.setQcdoctCd("sys");
            emrQcRecord.setQcdoctName("系统");
            emrQcRecord.setCrePerCode("sys");
            emrQcRecord.setCrePerName("系统");
            this.emrQcRecordService.insertEmrQcRecord(emrQcRecord);
         }
      } else if (type.equals("2")) {
         record.setState("3");
         record.setStateName("已退回");
         record.setAltPerCode(StringUtils.isEmpty(req.getOperatorCode()) ? "sys" : req.getOperatorCode());
         record.setAltPerName(StringUtils.isEmpty(req.getOperatorName()) ? "系统" : req.getOperatorName());
         this.emrQcRecordMapper.updateEmrQcRecord(record);
         String flowMrStateName = "终末质控退回";
         emrQcFlow.setTermQcState(record.getState());
         emrQcFlow.setMrState("13");
         emrQcFlow.setAltPerCode(StringUtils.isEmpty(req.getOperatorCode()) ? "sys" : req.getOperatorCode());
         emrQcFlow.setAltPerName(StringUtils.isEmpty(req.getOperatorName()) ? "系统" : req.getOperatorName());
         this.emrQcFlowMapper.updateEmrQcFlow(emrQcFlow);
         EmrQcFlowRecord emrQcFlowRecord = new EmrQcFlowRecord();
         emrQcFlowRecord.setId(SnowIdUtils.uniqueLong());
         emrQcFlowRecord.setOrgCd(visitInfoVo.getOrgCd());
         emrQcFlowRecord.setPatientId(visitInfoVo.getPatientId());
         emrQcFlowRecord.setOperTypeCd(emrQcFlow.getMrState());
         emrQcFlowRecord.setOperTypeName(flowMrStateName);
         emrQcFlowRecord.setOperDcoCd(StringUtils.isEmpty(req.getOperatorCode()) ? "sys" : req.getOperatorCode());
         emrQcFlowRecord.setOperDcoName(StringUtils.isEmpty(req.getOperatorName()) ? "系统" : req.getOperatorName());
         emrQcFlowRecord.setOperDeptCd("sys");
         emrQcFlowRecord.setOperDeptName("系统");
         emrQcFlowRecord.setOperPcIp("localhost");
         emrQcFlowRecord.setQcSn(emrQcFlow.getId());
         this.emrQcFlowRecordMapper.insertEmrQcFlowRecord(emrQcFlowRecord);
      }

   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void updateMrStatus(EmrQcFlow emrQcFlow, RevokeToFileReq req) throws Exception {
      String revokeType = req.getRevokeType();
      SysUser user = SecurityUtils.getLoginUser().getUser();
      switch (revokeType) {
         case "1":
            emrQcFlow.setMrState("00");
            break;
         case "2":
            String flag = this.sysEmrConfigService.selectSysEmrConfigByKey("0083");
            if (StringUtils.isNotEmpty(flag) && flag.equals("1")) {
               emrQcFlow.setMrState("12");
            } else {
               emrQcFlow.setMrState("00");
            }
      }

      this.updateEmrQcFlow(emrQcFlow);
      if (revokeType.equals("2") && emrQcFlow != null) {
         EmrIndexRevokeLog emrIndexRevokeLog = new EmrIndexRevokeLog();
         emrIndexRevokeLog.setId(SnowIdUtils.uniqueLong());
         emrIndexRevokeLog.setAdmissionNo(emrQcFlow.getPatientId());
         emrIndexRevokeLog.setCaseNo(emrQcFlow.getRecordNo());
         emrIndexRevokeLog.setPatientName(emrQcFlow.getPatientName());
         emrIndexRevokeLog.setHospitalizedCount(emrQcFlow.getVisitId() == null ? 1L : Long.valueOf((long)emrQcFlow.getVisitId()));
         emrIndexRevokeLog.setDischargeDepartmentNo(emrQcFlow.getDeptCd());
         emrIndexRevokeLog.setDischargeDepartmentName(emrQcFlow.getDeptName());
         emrIndexRevokeLog.setDischargeDepartmentName(emrQcFlow.getDeptName());
         emrIndexRevokeLog.setHospitalizedDate(emrQcFlow.getInDeptTime());
         emrIndexRevokeLog.setLeaveHospitalDate(emrQcFlow.getOutDeptTime());
         emrIndexRevokeLog.setConTime(this.commonService.getDbSysdate());
         emrIndexRevokeLog.setConDocCd(user.getUserName());
         emrIndexRevokeLog.setConDocName(user.getNickName());
         this.emrIndexRevokeLogService.insertEmrIndexRevokeLog(emrIndexRevokeLog);
      }

   }

   public List getQcFlowStatistic(EmrQcFlowVo emrQcFlowVo) {
      List<EmrQcFlowStatisticVo> emrQcFlowList = this.emrQcFlowMapper.getQcFlowStatistic(emrQcFlowVo);
      return emrQcFlowList;
   }

   public EmrQcFlowStatisticVo getQcFlowStatisticAllCount(EmrQcFlowVo emrQcFlowVo) {
      EmrQcFlowStatisticVo emrQcFlow = this.emrQcFlowMapper.getQcFlowStatisticAllCount(emrQcFlowVo);
      return emrQcFlow;
   }

   public List getQcFlowStatisticByDept(EmrQcFlowVo emrQcFlowVo) {
      List<EmrQcFlowStatisticVo> emrQcFlowList = this.emrQcFlowMapper.getQcFlowStatistic(emrQcFlowVo);
      TmBsHoliday tmBsHoliday = new TmBsHoliday();
      List<TmBsHoliday> bsHolidayList = this.tmBsHolidayMapper.selectTmBsHolidayList(tmBsHoliday);

      for(EmrQcFlowStatisticVo emrQcFlowStatisticVo : emrQcFlowList) {
         if (emrQcFlowStatisticVo.getCaseFirstFileTime() != null && emrQcFlowStatisticVo.getOutDeptTime() != null) {
            int filingCount = DateUtils.countFilingDays(emrQcFlowStatisticVo.getOutDeptTime(), emrQcFlowStatisticVo.getCaseFirstFileTime(), bsHolidayList);
            emrQcFlowStatisticVo.setFilingCount(filingCount);
         }
      }

      if (StringUtils.isBlank(emrQcFlowVo.getStatics()) || !"tj".equals(emrQcFlowVo.getStatics())) {
         PageDomain pageDomain = TableSupport.buildPageRequest();
         Integer pageNum = pageDomain.getPageNum();
         Integer pageSize = pageDomain.getPageSize();
         if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
         }
      }

      SysDept dept = new SysDept();
      List<SysDept> sysDeptList = this.sysDeptMapper.selectDeptZyList(dept);
      List<SysDept> sysDeptAllList = this.sysDeptMapper.selectDeptZyList(dept);
      List<EmrQcFlowStatisticVo> emrQcFlowStatisticVoList = this.getOutDeptTotalList(emrQcFlowVo);
      List<EmrQcFlowStatisticVo> qcFlowStatisticListRes = new ArrayList();

      for(SysDept sysDept : sysDeptList) {
         EmrQcFlowStatisticVo emrQcFlowStatisticVoRes = new EmrQcFlowStatisticVo();
         List<EmrQcFlowStatisticVo> emrQcFlowTwoList = (List)emrQcFlowList.stream().filter((t) -> sysDept.getDeptCode().equals(t.getDeptCd()) && t.getFilingCount() != null && t.getFilingCount() <= 2).collect(Collectors.toList());
         BigDecimal twoFilingCount = new BigDecimal(emrQcFlowTwoList.size());
         List<EmrQcFlowStatisticVo> emrQcFlowThreeList = (List)emrQcFlowList.stream().filter((t) -> sysDept.getDeptCode().equals(t.getDeptCd()) && t.getFilingCount() != null && t.getFilingCount() <= 3).collect(Collectors.toList());
         BigDecimal threeFilingCount = new BigDecimal(emrQcFlowThreeList.size());
         List<EmrQcFlowStatisticVo> emrQcFlowSevenList = (List)emrQcFlowList.stream().filter((t) -> sysDept.getDeptCode().equals(t.getDeptCd()) && t.getFilingCount() != null && t.getFilingCount() <= 7).collect(Collectors.toList());
         BigDecimal sevenFilingCount = new BigDecimal(emrQcFlowSevenList.size());
         List<EmrQcFlowStatisticVo> emrQcFlowGreateSevenList = (List)emrQcFlowList.stream().filter((t) -> sysDept.getDeptCode().equals(t.getDeptCd()) && t.getFilingCount() != null && t.getFilingCount() >= 7).collect(Collectors.toList());
         BigDecimal greateSevenFilingCount = new BigDecimal(emrQcFlowGreateSevenList.size());
         emrQcFlowStatisticVoRes.setDeptCd(sysDept.getDeptCode());
         emrQcFlowStatisticVoRes.setDeptName(sysDept.getDeptName());
         List<EmrQcFlowStatisticVo> deptCountList = (List)emrQcFlowStatisticVoList.stream().filter((t) -> sysDept.getDeptCode().equals(t.getDeptCd()) && StringUtils.isNotBlank(t.getDeptCd())).collect(Collectors.toList());
         int deptTotal = 0;
         if (deptCountList != null && deptCountList.size() > 0) {
            deptTotal = Integer.valueOf(((EmrQcFlowStatisticVo)deptCountList.get(0)).getOutHospitalTotal());
         }

         if (deptTotal > 0) {
            BigDecimal outDeptCount = new BigDecimal(deptTotal);
            String twoProportion = twoFilingCount.divide(outDeptCount, 4, 1).multiply(new BigDecimal(100)).setScale(2, 4) + "%";
            String threeProportion = threeFilingCount.divide(outDeptCount, 4, 1).multiply(new BigDecimal(100)).setScale(2, 4) + "%";
            String sevenProportion = sevenFilingCount.divide(outDeptCount, 4, 1).multiply(new BigDecimal(100)).setScale(2, 4) + "%";
            String greateSevenProportion = greateSevenFilingCount.divide(outDeptCount, 4, 1).multiply(new BigDecimal(100)).setScale(2, 4) + "%";
            List<EmrQcFlowStatisticVo> emrQcFlowJjList = (List)emrQcFlowList.stream().filter((t) -> sysDept.getDeptCode().equals(t.getDeptCd()) && StringUtils.isNotBlank(t.getTermGradeNo()) && "1".equals(t.getTermGradeNo())).collect(Collectors.toList());
            BigDecimal jjFilingCount = new BigDecimal(emrQcFlowJjList.size());
            List<EmrQcFlowStatisticVo> emrQcFlowYjList = (List)emrQcFlowList.stream().filter((t) -> sysDept.getDeptCode().equals(t.getDeptCd()) && StringUtils.isNotBlank(t.getTermGradeNo()) && "2".equals(t.getTermGradeNo())).collect(Collectors.toList());
            BigDecimal yjFilingCount = new BigDecimal(emrQcFlowYjList.size());
            List<EmrQcFlowStatisticVo> emrQcFlowBjList = (List)emrQcFlowList.stream().filter((t) -> sysDept.getDeptCode().equals(t.getDeptCd()) && StringUtils.isNotBlank(t.getTermGradeNo()) && "3".equals(t.getTermGradeNo())).collect(Collectors.toList());
            BigDecimal bjFilingCount = new BigDecimal(emrQcFlowBjList.size());
            List<EmrQcFlowStatisticVo> emrQcFlowFxList = (List)emrQcFlowList.stream().filter((t) -> sysDept.getDeptCode().equals(t.getDeptCd()) && !(t.getFileTime() != null ? t.getFileTime().toString() : "").equals(t.getFirstFileTime() != null ? t.getFirstFileTime().toString() : "")).collect(Collectors.toList());
            BigDecimal fxFilingCount = new BigDecimal(emrQcFlowFxList.size());
            String jjProportion = jjFilingCount.divide(outDeptCount, 4, 1).multiply(new BigDecimal(100)).setScale(2, 4) + "%";
            String yjProportion = yjFilingCount.divide(outDeptCount, 4, 1).multiply(new BigDecimal(100)).setScale(2, 4) + "%";
            String bjProportion = bjFilingCount.divide(outDeptCount, 4, 1).multiply(new BigDecimal(100)).setScale(2, 4) + "%";
            String fxProportion = fxFilingCount.divide(outDeptCount, 4, 1).multiply(new BigDecimal(100)).setScale(2, 4) + "%";
            List<EmrQcFlowStatisticVo> emrQcFlowDeptList = (List)emrQcFlowList.stream().filter((t) -> sysDept.getDeptCode().equals(t.getDeptCd()) && t.getFilingCount() != null).collect(Collectors.toList());
            int total = emrQcFlowDeptList.stream().mapToInt(EmrQcFlowStatisticVo::getFilingCount).sum();
            BigDecimal totalB = new BigDecimal(total);
            String averageFiling = totalB.divide(outDeptCount, 4, 1).setScale(2, 4) + "";
            emrQcFlowStatisticVoRes.setOutHospitalTotal(String.valueOf(deptTotal));
            emrQcFlowStatisticVoRes.setYgdTotal(String.valueOf(emrQcFlowList.size()));
            emrQcFlowStatisticVoRes.setOutHospitalTotal(String.valueOf(deptTotal));
            List<EmrQcFlowStatisticVo> emrQcFlowYgdList = (List)emrQcFlowList.stream().filter((t) -> sysDept.getDeptCode().equals(t.getDeptCd()) && t.getFirstFileTime() != null && StringUtils.isNotBlank(t.getMrState()) && t.getMrState().equals("16")).collect(Collectors.toList());
            emrQcFlowStatisticVoRes.setYgdTotal(String.valueOf(emrQcFlowYgdList.size()));
            BigDecimal filingCount = new BigDecimal(emrQcFlowYgdList.size());
            String filingProportion = filingCount.divide(new BigDecimal(deptTotal), 4, 1).multiply(new BigDecimal(100)).setScale(2, 4) + "%";
            emrQcFlowStatisticVoRes.setFilingProportion(filingProportion);
            emrQcFlowStatisticVoRes.setTwoFiling(twoFilingCount.toString());
            emrQcFlowStatisticVoRes.setTwoProportion(twoProportion);
            emrQcFlowStatisticVoRes.setThreeFiling(threeFilingCount.toString());
            emrQcFlowStatisticVoRes.setThreeProportion(threeProportion);
            emrQcFlowStatisticVoRes.setSevenFiling(sevenFilingCount.toString());
            emrQcFlowStatisticVoRes.setSevenProportion(sevenProportion);
            emrQcFlowStatisticVoRes.setGreateSevenFiling(greateSevenFilingCount.toString());
            emrQcFlowStatisticVoRes.setGreateSevenProportion(greateSevenProportion);
            emrQcFlowStatisticVoRes.setJjCount(jjFilingCount.toString());
            emrQcFlowStatisticVoRes.setJjProportion(jjProportion);
            emrQcFlowStatisticVoRes.setYjCount(yjFilingCount.toString());
            emrQcFlowStatisticVoRes.setYjProportion(yjProportion);
            emrQcFlowStatisticVoRes.setBjCount(bjFilingCount.toString());
            emrQcFlowStatisticVoRes.setBjProportion(bjProportion);
            emrQcFlowStatisticVoRes.setFxlCount(fxFilingCount.toString());
            emrQcFlowStatisticVoRes.setFxlProportion(fxProportion);
            emrQcFlowStatisticVoRes.setAverageFiling(new BigDecimal(averageFiling));
            emrQcFlowStatisticVoRes.setTotal(sysDeptAllList.size());
         } else {
            emrQcFlowStatisticVoRes.setOutHospitalTotal("0");
            emrQcFlowStatisticVoRes.setYgdTotal("0");
            emrQcFlowStatisticVoRes.setFilingProportion("100%");
            emrQcFlowStatisticVoRes.setTwoFiling("0");
            emrQcFlowStatisticVoRes.setTwoProportion("100%");
            emrQcFlowStatisticVoRes.setThreeFiling("0");
            emrQcFlowStatisticVoRes.setThreeProportion("100%");
            emrQcFlowStatisticVoRes.setSevenFiling("0");
            emrQcFlowStatisticVoRes.setSevenProportion("100%");
            emrQcFlowStatisticVoRes.setGreateSevenFiling("0");
            emrQcFlowStatisticVoRes.setGreateSevenProportion("100%");
            emrQcFlowStatisticVoRes.setJjCount("0");
            emrQcFlowStatisticVoRes.setJjProportion("100%");
            emrQcFlowStatisticVoRes.setYjCount("0");
            emrQcFlowStatisticVoRes.setYjProportion("100%");
            emrQcFlowStatisticVoRes.setBjCount("0");
            emrQcFlowStatisticVoRes.setBjProportion("100%");
            emrQcFlowStatisticVoRes.setFxlCount("0");
            emrQcFlowStatisticVoRes.setFxlProportion("100%");
            emrQcFlowStatisticVoRes.setAverageFiling(new BigDecimal("0"));
            emrQcFlowStatisticVoRes.setTotal(sysDeptAllList.size());
         }

         qcFlowStatisticListRes.add(emrQcFlowStatisticVoRes);
      }

      return qcFlowStatisticListRes;
   }

   public EmrQcFlowVo getQcFlowVoStatistic(EmrQcFlowVo emrQcFlowVo, List emrQcFlowList) {
      if (emrQcFlowVo.getOutDeptTotal() > 0) {
         EmrQcFlowVo emrQcFlowVo1 = this.emrQcFlowMapper.getQcFlowVoStatistic(emrQcFlowVo);
         TmBsHoliday tmBsHoliday = new TmBsHoliday();
         List<TmBsHoliday> bsHolidayList = this.tmBsHolidayMapper.selectTmBsHolidayList(tmBsHoliday);

         for(EmrQcFlowStatisticVo emrQcFlowStatisticVo : emrQcFlowList) {
            if (emrQcFlowStatisticVo.getCaseFirstFileTime() != null && emrQcFlowStatisticVo.getOutDeptTime() != null) {
               int filingCount = DateUtils.countFilingDays(emrQcFlowStatisticVo.getOutDeptTime(), emrQcFlowStatisticVo.getCaseFirstFileTime(), bsHolidayList);
               emrQcFlowStatisticVo.setFilingCount(filingCount);
            }
         }

         List<EmrQcFlowStatisticVo> emrQcFlowTwoList = (List)emrQcFlowList.stream().filter((t) -> t.getFilingCount() != null && t.getFilingCount() <= 2).collect(Collectors.toList());
         BigDecimal twoFilingCount = new BigDecimal(emrQcFlowTwoList.size());
         List<EmrQcFlowStatisticVo> emrQcFlowThreeList = (List)emrQcFlowList.stream().filter((t) -> t.getFilingCount() != null && t.getFilingCount() <= 3).collect(Collectors.toList());
         BigDecimal threeFilingCount = new BigDecimal(emrQcFlowThreeList.size());
         List<EmrQcFlowStatisticVo> emrQcFlowSevenList = (List)emrQcFlowList.stream().filter((t) -> t.getFilingCount() != null && t.getFilingCount() <= 7).collect(Collectors.toList());
         BigDecimal sevenFilingCount = new BigDecimal(emrQcFlowSevenList.size());
         List<EmrQcFlowStatisticVo> emrQcFlowFxList = (List)emrQcFlowList.stream().filter((t) -> !(t.getFileTime() != null ? t.getFileTime().toString() : "").equals(t.getFirstFileTime() != null ? t.getFirstFileTime().toString() : "")).collect(Collectors.toList());
         BigDecimal fxlFilingCount = new BigDecimal(emrQcFlowFxList.size());
         BigDecimal outDeptCount = new BigDecimal(emrQcFlowVo.getOutDeptTotal());
         String twoProportion = twoFilingCount.divide(outDeptCount, 4, 1).multiply(new BigDecimal(100)).setScale(2, 4) + "%";
         emrQcFlowVo1.setTwoProportion(twoProportion);
         String threeProportion = threeFilingCount.divide(outDeptCount, 4, 1).multiply(new BigDecimal(100)).setScale(2, 4) + "%";
         emrQcFlowVo1.setThreeProportion(threeProportion);
         String sevenProportion = sevenFilingCount.divide(outDeptCount, 4, 1).multiply(new BigDecimal(100)).setScale(2, 4) + "%";
         emrQcFlowVo1.setSevenProportion(sevenProportion);
         String fxlProportion = fxlFilingCount.divide(outDeptCount, 4, 1).multiply(new BigDecimal(100)).setScale(2, 4) + "%";
         emrQcFlowVo1.setFxlProportion(fxlProportion);
         List<EmrQcFlowStatisticVo> emrQcFlowYgdList = (List)emrQcFlowList.stream().filter((t) -> t.getFirstFileTime() != null).collect(Collectors.toList());
         emrQcFlowVo1.setYgdTotal(String.valueOf(emrQcFlowYgdList.size()));
         return emrQcFlowVo1;
      } else {
         EmrQcFlowVo emrQcFlowVoRes = new EmrQcFlowVo();
         emrQcFlowVoRes.setOutDeptTotal(0);
         emrQcFlowVoRes.setYgdTotal("0");
         emrQcFlowVoRes.setTwoProportion("-");
         emrQcFlowVoRes.setThreeProportion("-");
         emrQcFlowVoRes.setSevenProportion("-");
         emrQcFlowVoRes.setJjProportion("-");
         emrQcFlowVoRes.setFxlProportion("-");
         return emrQcFlowVoRes;
      }
   }

   public int getOutDeptTotal(EmrQcFlowVo emrQcFlowVo) {
      int outDeptTotal = this.emrQcFlowMapper.getOutDeptTotal(emrQcFlowVo);
      return outDeptTotal;
   }

   public List getOutDeptTotalList(EmrQcFlowVo emrQcFlowVo) {
      List<EmrQcFlowStatisticVo> outDeptListTotal = this.emrQcFlowMapper.getOutDeptListTotal(emrQcFlowVo);
      return outDeptListTotal;
   }

   public AjaxResult getQcFlowStatisticExport(EmrQcFlowVo emrQcFlowVo, HttpServletResponse response) throws Exception {
      List<EmrQcFlowStatisticVo> emrQcFlowList = this.emrQcFlowMapper.getQcFlowStatistic(emrQcFlowVo);
      emrQcFlowList = (List)emrQcFlowList.stream().map((a) -> {
         EmrQcFlowStatisticVo emrQcFlowStatisticVo = new EmrQcFlowStatisticVo();
         BeanUtils.copyProperties(a, emrQcFlowStatisticVo);
         if (a.getInDeptTime() != null) {
            emrQcFlowStatisticVo.setInDeptTimeS(DateUtils.dateFormatS(a.getInDeptTime(), "yyyy-MM-dd HH:mm"));
         }

         if (a.getOutDeptTime() != null) {
            emrQcFlowStatisticVo.setOutDeptTimeS(DateUtils.dateFormatS(a.getOutDeptTime(), "yyyy-MM-dd HH:mm"));
         }

         if (a.getFirstFileTime() != null) {
            emrQcFlowStatisticVo.setFirstFileTimeS(DateUtils.dateFormatS(a.getFirstFileTime(), "yyyy-MM-dd HH:mm"));
         }

         if (a.getFileTime() != null) {
            emrQcFlowStatisticVo.setFileTimeS(DateUtils.dateFormatS(a.getFileTime(), "yyyy-MM-dd HH:mm"));
         }

         if (a.getOperTime() != null) {
            emrQcFlowStatisticVo.setOperTimeS(DateUtils.dateFormatS(a.getOperTime(), "yyyy-MM-dd HH:mm"));
         }

         return emrQcFlowStatisticVo;
      }).collect(Collectors.toList());
      EmrQcFlowStatisticVo outDeptTotal = this.getQcFlowStatisticAllCount(emrQcFlowVo);
      emrQcFlowVo.setOutDeptTotal(outDeptTotal.getTotal());
      EmrQcFlowVo emrQcFlowVo1 = this.getQcFlowVoStatistic(emrQcFlowVo, emrQcFlowList);
      emrQcFlowVo.setOutHospitalTotal(emrQcFlowVo1.getOutHospitalTotal());
      emrQcFlowVo.setYgdTotal(emrQcFlowVo1.getYgdTotal());
      emrQcFlowVo.setJjProportion(emrQcFlowVo1.getJjProportion());
      emrQcFlowVo.setFxlProportion(emrQcFlowVo1.getFxlProportion());
      emrQcFlowVo.setTwoProportion(emrQcFlowVo1.getTwoProportion());
      emrQcFlowVo.setThreeProportion(emrQcFlowVo1.getThreeProportion());
      emrQcFlowVo.setSevenProportion(emrQcFlowVo1.getSevenProportion());
      if (emrQcFlowVo.getOutHospitalTimeBegin() != null) {
         emrQcFlowVo.setOutHospitalTimeBeginS(DateUtils.dateFormatS(emrQcFlowVo.getOutHospitalTimeBegin(), "yyyy-MM-dd"));
      }

      if (emrQcFlowVo.getOutHospitalTimeEnd() != null) {
         Date outTimeEnd = emrQcFlowVo.getOutHospitalTimeEnd();
         emrQcFlowVo.setOutHospitalTimeEnd(DateUtils.addDays(outTimeEnd, -1));
      }

      if (emrQcFlowVo.getOutHospitalTimeEnd() != null) {
         emrQcFlowVo.setOutHospitalTimeEndS(DateUtils.dateFormatS(emrQcFlowVo.getOutHospitalTimeEnd(), "yyyy-MM-dd"));
      }

      List<LinkedHashMap<String, Object>> mapList = new ArrayList();
      List<EmrQcFlowStatisticVo> sizeList = new ArrayList();
      LinkedHashMap<String, Object> tableMap = new LinkedHashMap();
      if (emrQcFlowList != null && !emrQcFlowList.isEmpty()) {
         Map<String, List<EmrQcFlowStatisticVo>> map = (Map)emrQcFlowList.stream().collect(Collectors.groupingBy(EmrQcFlowStatisticVo::getPatientId, LinkedHashMap::new, Collectors.toList()));

         for(String patientId : map.keySet()) {
            List<EmrQcFlowStatisticVo> voList = (List)map.get(patientId);
            if (sizeList.size() < voList.size()) {
               sizeList = voList;
            }
         }

         if (sizeList != null && !sizeList.isEmpty()) {
            for(String patientId : map.keySet()) {
               List<EmrQcFlowStatisticVo> voList = (List)map.get(patientId);
               LinkedHashMap<String, Object> voMap = new LinkedHashMap();
               voMap.put("patientId", ((EmrQcFlowStatisticVo)voList.get(0)).getCaseNo());
               voMap.put("patientName", ((EmrQcFlowStatisticVo)voList.get(0)).getPatientName());
               voMap.put("deptName", ((EmrQcFlowStatisticVo)voList.get(0)).getDeptName());
               voMap.put("inDeptTimeS", ((EmrQcFlowStatisticVo)voList.get(0)).getInDeptTimeS());
               voMap.put("outDeptTimeS", ((EmrQcFlowStatisticVo)voList.get(0)).getOutDeptTimeS());
               voMap.put("resDocName", ((EmrQcFlowStatisticVo)voList.get(0)).getResDocName());
               voMap.put("deptName", ((EmrQcFlowStatisticVo)voList.get(0)).getDeptName());
               voMap.put("firstFileTimeS", ((EmrQcFlowStatisticVo)voList.get(0)).getFirstFileTimeS());
               voMap.put("fileTimeS", ((EmrQcFlowStatisticVo)voList.get(0)).getFileTimeS());
               voMap.put("operTimeS", ((EmrQcFlowStatisticVo)voList.get(0)).getOperTimeS());
               voMap.put("operReason", ((EmrQcFlowStatisticVo)voList.get(0)).getOperReason());
               if (((EmrQcFlowStatisticVo)voList.get(0)).getTermGradeName() != null && ((EmrQcFlowStatisticVo)voList.get(0)).getTermScore() != null) {
                  voMap.put("termScoreRes", ((EmrQcFlowStatisticVo)voList.get(0)).getTermGradeName() + "/" + ((EmrQcFlowStatisticVo)voList.get(0)).getTermScore() + "分");
               }

               if (((EmrQcFlowStatisticVo)voList.get(0)).getDeptScore() != null && ((EmrQcFlowStatisticVo)voList.get(0)).getDeptGradeName() != null) {
                  voMap.put("deptScoreRes", ((EmrQcFlowStatisticVo)voList.get(0)).getDeptGradeName() + "/" + ((EmrQcFlowStatisticVo)voList.get(0)).getDeptScore() + "分");
               }

               if (((EmrQcFlowStatisticVo)voList.get(0)).getCheckGradeName() != null && ((EmrQcFlowStatisticVo)voList.get(0)).getCheckScore() != null) {
                  voMap.put("checkScoreRes", ((EmrQcFlowStatisticVo)voList.get(0)).getCheckGradeName() + "/" + ((EmrQcFlowStatisticVo)voList.get(0)).getCheckScore() + "分");
               }

               mapList.add(voMap);
            }
         }
      }

      tableMap.put("deptName", "科室名称");
      tableMap.put("resDocName", "管床医师名称");
      tableMap.put("patientName", "患者姓名");
      tableMap.put("patientId", "患者住院号");
      tableMap.put("inDeptTimeS", "入院日期");
      tableMap.put("outDeptTimeS", "出院日期");
      tableMap.put("firstFileTimeS", "首次归档日期");
      tableMap.put("fileTimeS", "最终归档日期");
      tableMap.put("operTimeS", "撤销归档时间");
      tableMap.put("operReason", "撤销归档原因");
      tableMap.put("checkScoreRes", "抽查评分");
      tableMap.put("deptScoreRes", "科室质控评分");
      tableMap.put("termScoreRes", "终末质控评分");
      Workbook wb = new SXSSFWorkbook(500);
      String tableName = "归档病历统计";
      ExcelUtils.getQcFlowStatisticExport(wb, mapList, tableMap, emrQcFlowList, emrQcFlowVo, tableName);
      String fileName = tableName + ".xlsx";
      response.setHeader("Content-disposition", "attachment;filename=" + fileName);
      OutputStream stream = new FileOutputStream(ExcelUtils.getAbsoluteFile(fileName));
      if (null != wb && null != stream) {
         wb.write(stream);
         stream.close();
      }

      return AjaxResult.success(fileName);
   }

   public AjaxResult getQcFlowStatisticByDeptExport(EmrQcFlowVo emrQcFlowVo, HttpServletResponse response) throws Exception {
      emrQcFlowVo.setStatics("tj");
      List<EmrQcFlowStatisticVo> emrQcFlowList = this.getQcFlowStatisticByDept(emrQcFlowVo);
      List<EmrQcFlowStatisticVo> emrQcFlowAllCountList = this.getQcFlowStatistic(emrQcFlowVo);
      EmrQcFlowVo emrQcFlowVo1 = this.getQcFlowVoStatistic(emrQcFlowVo, emrQcFlowAllCountList);
      emrQcFlowVo.setOutHospitalTotal(emrQcFlowVo1.getOutHospitalTotal());
      emrQcFlowVo.setYgdTotal(emrQcFlowVo1.getYgdTotal());
      emrQcFlowVo.setJjProportion(emrQcFlowVo1.getJjProportion());
      emrQcFlowVo.setFxlProportion(emrQcFlowVo1.getFxlProportion());
      emrQcFlowVo.setTwoProportion(emrQcFlowVo1.getTwoProportion());
      emrQcFlowVo.setThreeProportion(emrQcFlowVo1.getThreeProportion());
      emrQcFlowVo.setSevenProportion(emrQcFlowVo1.getSevenProportion());
      if (emrQcFlowVo.getOutHospitalTimeBegin() != null) {
         emrQcFlowVo.setOutHospitalTimeBeginS(DateUtils.dateFormatS(emrQcFlowVo.getOutHospitalTimeBegin(), "yyyy-MM-dd"));
      }

      if (emrQcFlowVo.getOutHospitalTimeEnd() != null) {
         Date outTimeEnd = emrQcFlowVo.getOutHospitalTimeEnd();
         emrQcFlowVo.setOutHospitalTimeEnd(DateUtils.addDays(outTimeEnd, -1));
         emrQcFlowVo.setOutHospitalTimeEndS(DateUtils.dateFormatS(emrQcFlowVo.getOutHospitalTimeEnd(), "yyyy-MM-dd"));
      }

      List<LinkedHashMap<String, Object>> mapList = new ArrayList();
      List<EmrQcFlowStatisticVo> sizeList = new ArrayList();
      LinkedHashMap<String, Object> tableMap = new LinkedHashMap();
      if (emrQcFlowList != null && !emrQcFlowList.isEmpty()) {
         Map<String, List<EmrQcFlowStatisticVo>> map = (Map)emrQcFlowList.stream().collect(Collectors.groupingBy(EmrQcFlowStatisticVo::getDeptCd, LinkedHashMap::new, Collectors.toList()));

         for(String id : map.keySet()) {
            List<EmrQcFlowStatisticVo> voList = (List)map.get(id);
            if (sizeList.size() < voList.size()) {
               sizeList = voList;
            }
         }

         if (sizeList != null && !sizeList.isEmpty()) {
            for(String id : map.keySet()) {
               List<EmrQcFlowStatisticVo> voList = (List)map.get(id);
               LinkedHashMap<String, Object> voMap = new LinkedHashMap();
               voMap.put("deptCd", ((EmrQcFlowStatisticVo)voList.get(0)).getDeptCd());
               voMap.put("deptName", ((EmrQcFlowStatisticVo)voList.get(0)).getDeptName());
               voMap.put("outHospitalTotal", ((EmrQcFlowStatisticVo)voList.get(0)).getOutHospitalTotal());
               voMap.put("ygdTotal", ((EmrQcFlowStatisticVo)voList.get(0)).getYgdTotal());
               voMap.put("filingProportion", ((EmrQcFlowStatisticVo)voList.get(0)).getFilingProportion());
               voMap.put("twoFiling", ((EmrQcFlowStatisticVo)voList.get(0)).getTwoFiling());
               voMap.put("twoProportion", ((EmrQcFlowStatisticVo)voList.get(0)).getTwoProportion());
               voMap.put("threeFiling", ((EmrQcFlowStatisticVo)voList.get(0)).getThreeFiling());
               voMap.put("threeProportion", ((EmrQcFlowStatisticVo)voList.get(0)).getThreeProportion());
               voMap.put("sevenFiling", ((EmrQcFlowStatisticVo)voList.get(0)).getSevenFiling());
               voMap.put("sevenProportion", ((EmrQcFlowStatisticVo)voList.get(0)).getSevenProportion());
               voMap.put("greateSevenFiling", ((EmrQcFlowStatisticVo)voList.get(0)).getGreateSevenFiling());
               voMap.put("greateSevenProportion", ((EmrQcFlowStatisticVo)voList.get(0)).getGreateSevenProportion());
               voMap.put("averageFiling", ((EmrQcFlowStatisticVo)voList.get(0)).getAverageFiling());
               if (((EmrQcFlowStatisticVo)voList.get(0)).getJjCount() != null && ((EmrQcFlowStatisticVo)voList.get(0)).getJjProportion() != null) {
                  voMap.put("jjProportionRes", ((EmrQcFlowStatisticVo)voList.get(0)).getJjCount() + "/" + ((EmrQcFlowStatisticVo)voList.get(0)).getJjProportion());
               }

               if (((EmrQcFlowStatisticVo)voList.get(0)).getYjCount() != null && ((EmrQcFlowStatisticVo)voList.get(0)).getYjProportion() != null) {
                  voMap.put("yjProportionRes", ((EmrQcFlowStatisticVo)voList.get(0)).getYjCount() + "/" + ((EmrQcFlowStatisticVo)voList.get(0)).getYjProportion());
               }

               if (((EmrQcFlowStatisticVo)voList.get(0)).getBjCount() != null && ((EmrQcFlowStatisticVo)voList.get(0)).getBjProportion() != null) {
                  voMap.put("bjProportionRes", ((EmrQcFlowStatisticVo)voList.get(0)).getBjCount() + "/" + ((EmrQcFlowStatisticVo)voList.get(0)).getBjProportion());
               }

               if (((EmrQcFlowStatisticVo)voList.get(0)).getFxlCount() != null && ((EmrQcFlowStatisticVo)voList.get(0)).getFxlProportion() != null) {
                  voMap.put("fxlProportionRes", ((EmrQcFlowStatisticVo)voList.get(0)).getFxlCount() + "/" + ((EmrQcFlowStatisticVo)voList.get(0)).getFxlProportion());
               }

               mapList.add(voMap);
            }
         }
      }

      tableMap.put("deptCd", "科室代码");
      tableMap.put("deptName", "科室名称");
      tableMap.put("outHospitalTotal", "出院患者数");
      tableMap.put("ygdTotal", "已归档病历");
      tableMap.put("filingProportion", "归档率");
      tableMap.put("twoFiling", "2日归档数");
      tableMap.put("twoProportion", "2日归档率");
      tableMap.put("threeFiling", "3日归档数");
      tableMap.put("threeProportion", "3日归档率");
      tableMap.put("sevenFiling", "7日归档数");
      tableMap.put("sevenProportion", "7日归档率");
      tableMap.put("greateSevenFiling", ">7日归档数");
      tableMap.put("greateSevenProportion", ">7日归档率");
      tableMap.put("averageFiling", "平均归档日");
      tableMap.put("jjProportionRes", "甲级率");
      tableMap.put("yjProportionRes", "乙级率");
      tableMap.put("bjProportionRes", "丙级率");
      tableMap.put("fxlProportionRes", "返修率");
      Workbook wb = new SXSSFWorkbook(500);
      String tableName = "归档率统计-按科室";
      ExcelUtils.getQcFlowStatisticByDeptExport(wb, mapList, tableMap, emrQcFlowList, emrQcFlowVo, tableName);
      String fileName = tableName + ".xlsx";
      response.setHeader("Content-disposition", "attachment;filename=" + fileName);
      OutputStream stream = new FileOutputStream(ExcelUtils.getAbsoluteFile(fileName));
      if (null != wb && null != stream) {
         wb.write(stream);
         stream.close();
      }

      return AjaxResult.success(fileName);
   }

   @DataSource(DataSourceType.hisExamItem)
   public void saveHisSqgd(Medicalinformation medicalinformation, EmrQcFlowScoreVo emrQcFlowScoreVo, Baseinfomation baseInfo, EmrQcFlow emrQcFlow) throws Exception {
      this.log.warn("质控保存-终末质控归档保存his数据写入开始查询数量" + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      Integer count = this.emrQcFlowMapper.selectCountSqgd(medicalinformation.getAdmissionNo(), medicalinformation.getHospitalizedCount());
      if (count == 0) {
         DzblSqgd dzblSqgd = new DzblSqgd();
         dzblSqgd.setBah(medicalinformation.getCaseNo());
         dzblSqgd.setZyh(medicalinformation.getAdmissionNo());
         dzblSqgd.setRycs(medicalinformation.getHospitalizedCount());
         dzblSqgd.setBrxm(medicalinformation.getName());
         Long year = baseInfo.getPersonAge() != null ? Long.parseLong(baseInfo.getPersonAge().toString()) : null;
         Long month = baseInfo.getAgeMonth() != null ? Long.parseLong(baseInfo.getAgeMonth().toString()) : null;
         Long day = baseInfo.getAgeDay() != null ? Long.parseLong(baseInfo.getAgeDay().toString()) : null;
         Long hour = baseInfo.getAgeHour() != null ? Long.parseLong(baseInfo.getAgeHour().toString()) : null;
         dzblSqgd.setNl(AgeUtil.getAgeStr(year, month, day, hour, (Long)null));
         dzblSqgd.setXb(baseInfo.getSex());
         dzblSqgd.setBqbh(medicalinformation.getDepartmentNo());
         Double totalScore = emrQcFlowScoreVo.getTotalScore();
         dzblSqgd.setZf(totalScore != null ? totalScore.intValue() : null);
         dzblSqgd.setBldj(emrQcFlowScoreVo.getLevelName());
         dzblSqgd.setBlzt("30");
         dzblSqgd.setRyrq(medicalinformation.getHospitalizedDate());
         dzblSqgd.setSqsj(emrQcFlow.getMrSubTime());
         dzblSqgd.setSqys(emrQcFlow.getMrSubName());
         dzblSqgd.setPfsj(emrQcFlow.getDeptQcTime());
         dzblSqgd.setPfczy(emrQcFlow.getDeptQcName());
         dzblSqgd.setGdsj(new Date());
         String userName = "系统";

         try {
            this.log.warn("质控保存-终末质控归档保存his数据写入获取userName" + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
            userName = SecurityUtils.getLoginUser().getUser().getUserName();
         } catch (Exception var14) {
            this.log.warn("质控保存-终末质控归档保存his数据写入获取userName出现异常" + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
         }

         dzblSqgd.setGdczy(userName);
         dzblSqgd.setZksj(emrQcFlow.getTermQcTime());
         dzblSqgd.setZkczy(emrQcFlow.getTermQcName());
         this.log.warn("质控保存-终末质控归档保存his数据插入视图yy_dzbl_sqgd数据" + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
         this.emrQcFlowMapper.insertSqgd(dzblSqgd);
      }

   }

   @DataSource(DataSourceType.hisExamItem)
   public void deleteHisSqgd(String admissionNo, Integer hospitalizedCount) throws Exception {
      this.emrQcFlowMapper.deleteHisSqgd(admissionNo, hospitalizedCount);
   }

   public List selectTermBackLimitHourList(String mrState, String termQcBackTimeBegin) throws Exception {
      List<EmrQcFlow> list = null;
      if (StringUtils.isNotBlank(mrState) && StringUtils.isNotBlank(termQcBackTimeBegin)) {
         list = this.emrQcFlowMapper.selectTermBackLimitHourList(mrState, termQcBackTimeBegin);
      }

      return list;
   }

   public EmrQcFlow getEmrQcFlowByPatientId(String admissionNo) {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      return this.emrQcFlowMapper.selectEmrQcFlowByPatientId(sysUser.getHospital().getOrgCode(), admissionNo);
   }
}
