package com.emr.project.docOrder.service.impl;

import com.emr.common.utils.AgeUtil;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.domain.TdCaOperationApply;
import com.emr.project.docOrder.domain.TdPaOrderItem;
import com.emr.project.docOrder.domain.vo.OpeAuthorityVo;
import com.emr.project.docOrder.domain.vo.OperTestResultVo;
import com.emr.project.docOrder.domain.vo.OrderCommitVo;
import com.emr.project.docOrder.domain.vo.OrderStopSignVo;
import com.emr.project.docOrder.domain.vo.TdCaOperWorkloadVo;
import com.emr.project.docOrder.domain.vo.TdCaOperationApplyVo;
import com.emr.project.docOrder.domain.vo.TdPaOrderItemVo;
import com.emr.project.docOrder.mapper.TdCaOperationApplyMapper;
import com.emr.project.docOrder.service.ITdCaOperationApplyService;
import com.emr.project.docOrder.service.ITdPaOrderItemService;
import com.emr.project.docOrder.service.ITdPaOrderOperationLogService;
import com.emr.project.docOrder.service.ITdPaOrderSignMainService;
import com.emr.project.docOrder.service.ITdPaOrderStatusService;
import com.emr.project.emr.domain.vo.EmrTaskInfoVo;
import com.emr.project.emr.domain.vo.IndexVo;
import com.emr.project.emr.service.IEmrTaskInfoService;
import com.emr.project.emr.service.IIndexService;
import com.emr.project.emr.service.ISubfileIndexService;
import com.emr.project.pat.domain.Visitinfo;
import com.emr.project.pat.domain.vo.OperRoomVisitinfoVo;
import com.emr.project.pat.domain.vo.VisitinfoPersonalVo;
import com.emr.project.pat.service.ITestResultService;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.qc.domain.PatEvent;
import com.emr.project.qc.domain.QcAgiRule;
import com.emr.project.qc.domain.QcRuleIterEmr;
import com.emr.project.qc.domain.vo.QcAgiRuleVo;
import com.emr.project.qc.service.IPatEventService;
import com.emr.project.qc.service.IQcAgiRuleService;
import com.emr.project.qc.service.IQcRuleIterEmrService;
import com.emr.project.sys.domain.SysOpeIcd;
import com.emr.project.sys.domain.vo.SysOpeIcdVo;
import com.emr.project.sys.mapper.SysOpeIcdMapper;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.TmBsStaffOperlevel;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.tmpm.service.ILisLabItemHisService;
import com.emr.project.tmpm.service.ILisLabResultHisService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TdCaOperationApplyServiceImpl implements ITdCaOperationApplyService {
   @Autowired
   private TdCaOperationApplyMapper tdCaOperationApplyMapper;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private ITdPaOrderItemService tdPaOrderItemService;
   @Autowired
   private IEmrTaskInfoService emrTaskInfoService;
   @Autowired
   private ITdPaOrderStatusService tdPaOrderStatusService;
   @Autowired
   private ITdPaOrderOperationLogService tdPaOrderOperationLogService;
   @Autowired
   private IQcAgiRuleService qcAgiRuleService;
   @Autowired
   private IQcRuleIterEmrService qcRuleIterEmrService;
   @Autowired
   private IIndexService indexService;
   @Autowired
   private ISubfileIndexService subfileIndexService;
   @Autowired
   private ITdPaOrderSignMainService tdPaOrderSignMainService;
   @Autowired
   private ILisLabItemHisService lisLabItemHisService;
   @Autowired
   private ILisLabResultHisService lisLabResultHisService;
   @Autowired
   private ITestResultService testResultService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private IPatEventService patEventService;
   @Autowired
   private SysOpeIcdMapper sysOpeIcdMapper;

   public TdCaOperationApply selectTdCaOperationApplyById(String applyFormNo) {
      return this.tdCaOperationApplyMapper.selectTdCaOperationApplyById(applyFormNo);
   }

   public List selectTdCaOperationApplyByIds(List admissionNos) {
      return this.tdCaOperationApplyMapper.selectTdCaOperationApplyByIds(admissionNos);
   }

   public List selectTdCaOperationApplyList(TdCaOperationApplyVo tdCaOperationApply) throws Exception {
      List<TdCaOperationApplyVo> list = this.tdCaOperationApplyMapper.selectTdCaOperationApplyList(tdCaOperationApply);
      if (list != null && !list.isEmpty()) {
         List<String> operCdList = (List)list.stream().map(TdCaOperationApply::getPlanOper1Cd).collect(Collectors.toList());
         List<SysOpeIcdVo> opeList = this.sysOpeIcdMapper.selectOpeByOpeCdList(operCdList);
         Map<String, List<SysOpeIcdVo>> icdMap = new HashMap();
         if (opeList != null && opeList.size() > 0) {
            icdMap = (Map)opeList.stream().collect(Collectors.groupingBy(SysOpeIcd::getIcdCd));
         }

         Visitinfo visitinfo = this.visitinfoService.selectVisitinfoByPatientId(tdCaOperationApply.getPatientId());

         for(TdCaOperationApplyVo vo : list) {
            vo.setInpNo(visitinfo.getInpNo());
            vo.setBedNo(visitinfo.getBedNo());
            vo.setPatTypeName(visitinfo.getPatTypeName());
            vo.setArcDocName(visitinfo.getArcDocName());
            if (icdMap.containsKey(vo.getPlanOper1Cd())) {
               SysOpeIcdVo sysOpeIcd = (SysOpeIcdVo)((List)icdMap.get(vo.getPlanOper1Cd())).get(0);
               vo.setOpeType(sysOpeIcd.getOpeType());
               vo.setOpeTypeName(sysOpeIcd.getOpeTypeName());
            }
         }
      }

      return list;
   }

   public List selectOperUnAuditList(TdCaOperationApplyVo tdCaOperationApply) throws Exception {
      tdCaOperationApply.setStatus("02");
      tdCaOperationApply.setOperTypeCd("2");
      return this.tdCaOperationApplyMapper.selectOperAuditList(tdCaOperationApply);
   }

   public List selectOperAuditList(TdCaOperationApplyVo tdCaOperationApply) throws Exception {
      List<String> statusList = new ArrayList();
      if (StringUtils.isNotEmpty(tdCaOperationApply.getStatus())) {
         statusList.add(tdCaOperationApply.getStatus());
      } else {
         statusList.add("04");
         statusList.add("05");
      }

      tdCaOperationApply.setStatusList(statusList);
      return this.tdCaOperationApplyMapper.selectOperAuditList(tdCaOperationApply);
   }

   public List selectOperPlanList(TdCaOperationApplyVo tdCaOperationApply) throws Exception {
      return this.tdCaOperationApplyMapper.selectOperAuditList(tdCaOperationApply);
   }

   public void insertTdCaOperationApply(TdCaOperationApply tdCaOperationApply) throws Exception {
      this.tdCaOperationApplyMapper.insertTdCaOperationApply(tdCaOperationApply);
   }

   public void updateTdCaOperationApply(TdCaOperationApply tdCaOperationApply) throws Exception {
      this.tdCaOperationApplyMapper.updateTdCaOperationApply(tdCaOperationApply);
   }

   public int deleteTdCaOperationApplyByIds(String[] applyFormNos) {
      return this.tdCaOperationApplyMapper.deleteTdCaOperationApplyByIds(applyFormNos);
   }

   public int deleteTdCaOperationApplyById(String applyFormNo) {
      return this.tdCaOperationApplyMapper.deleteTdCaOperationApplyById(applyFormNo);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void saveTdCaOperationApply(TdCaOperationApplyVo tdCaOperationApply, VisitinfoPersonalVo personalVo) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      BasEmployee basEmployee = sysUser.getBasEmployee();
      String applyFormNo = tdCaOperationApply.getApplyFormNo();
      if (StringUtils.isEmpty(applyFormNo)) {
         tdCaOperationApply.setHospitalCode(sysUser.getHospital().getOrgCode());
         String id = DateUtils.getDateStr("");
         tdCaOperationApply.setApplyFormNo(id);
         tdCaOperationApply.setCaseNo(personalVo.getRecordNo());
         tdCaOperationApply.setAdmissionNo(personalVo.getInpNo());
         tdCaOperationApply.setHospitalizedCount(personalVo.getVisitId());
         tdCaOperationApply.setPatientName(personalVo.getPatientName());
         tdCaOperationApply.setSex(personalVo.getSex());
         tdCaOperationApply.setAge(AgeUtil.getAgeStr(personalVo.getAgeY(), personalVo.getAgeM(), personalVo.getAgeD(), personalVo.getAgeH(), personalVo.getAgeMi()));
         tdCaOperationApply.setApplyDate(this.commonService.getDbSysdate());
         tdCaOperationApply.setStatus("01");
         if (tdCaOperationApply.getMrFileList() != null && !tdCaOperationApply.getMrFileList().isEmpty()) {
            List<EmrTaskInfoVo> emrTaskInfoList = new ArrayList();
            QcAgiRule qcAgiRule28 = this.qcAgiRuleService.selectYxQcAgiRuleByCode("R028");
            QcAgiRule qcAgiRule29 = this.qcAgiRuleService.selectYxQcAgiRuleByCode("R029");

            for(IndexVo indexVo : tdCaOperationApply.getMrFileList()) {
               EmrTaskInfoVo emrTaskInfo = this.setEmrTaskInfo(indexVo, personalVo);
               emrTaskInfo.setBusSection("17");
               emrTaskInfo.setBusSectionName("手术预约");
               emrTaskInfo.setEventNo(id);
               if ("5".equals(indexVo.getMrType())) {
                  if (qcAgiRule29 != null) {
                     emrTaskInfo.setLimitTimeUnit(qcAgiRule29.getLimitTimeUnit());
                     emrTaskInfo.setLimitTime(qcAgiRule29.getFinishLimitTime());
                  }
               } else if ("15".equals(indexVo.getMrType()) && qcAgiRule28 != null) {
                  emrTaskInfo.setLimitTimeUnit(qcAgiRule28.getLimitTimeUnit());
                  emrTaskInfo.setLimitTime(qcAgiRule28.getFinishLimitTime());
               }

               emrTaskInfoList.add(emrTaskInfo);
            }

            this.emrTaskInfoService.insertTaskList(emrTaskInfoList);
            List<Long> idList = (List)tdCaOperationApply.getMrFileList().stream().map((s) -> s.getId()).collect(Collectors.toList());
            this.indexService.updateEmrOrderNoByIdList(id, idList);
            this.subfileIndexService.updateEmrOrderNoByIdList(id, idList);
         }
      } else {
         tdCaOperationApply.setAltDate(this.commonService.getDbSysdate());
         tdCaOperationApply.setAltPerCode(basEmployee.getEmplNumber());
         tdCaOperationApply.setAltPerName(basEmployee.getEmplName());
      }

      if (tdCaOperationApply.getSubmitFlag()) {
         String opeOrderFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0082");
         if (opeOrderFlag != null && opeOrderFlag.equals("1")) {
            this.tdPaOrderItemService.insertOrderFromTdCaOperationApply(tdCaOperationApply, new OrderCommitVo());
         }
      }

      if (StringUtils.isEmpty(applyFormNo)) {
         this.insertTdCaOperationApply(tdCaOperationApply);
      } else {
         this.tdCaOperationApplyMapper.updateTdCaOperationApply(tdCaOperationApply);
      }

   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void submitTdCaOperationApply(TdCaOperationApplyVo tdCaOperationApply, List orderItemList, OrderCommitVo orderCommitVo) throws Exception {
      VisitinfoPersonalVo personalVo = this.visitinfoService.selectVisitinfoPersonalById(tdCaOperationApply.getPatientId());
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      Date currDate = orderCommitVo.getSubmitTime() != null ? orderCommitVo.getSubmitTime() : this.commonService.getDbSysdate();
      if (orderItemList != null) {
         List<String> orderNoList = (List)orderItemList.stream().map((t) -> t.getOrderNo()).distinct().collect(Collectors.toList());
         TdPaOrderItemVo param = new TdPaOrderItemVo();
         param.setOrderNoList(orderNoList);
         param.setOrderStatus("0");
         param.setSubmitDoctorCode(basEmployee.getEmplNumber());
         param.setSubmitDoctorNo(basEmployee.getEmplNumber());
         param.setSubmitDoctorName(basEmployee.getEmplName());
         param.setSubmitTime(currDate);
         this.tdPaOrderItemService.updateOrderStatus(param);
         this.tdPaOrderStatusService.addTdPaOrderStatus(personalVo, orderItemList, 0, "提交", currDate, (String)null);
         this.tdPaOrderOperationLogService.addTdPaOrderOperationLog(personalVo, orderItemList, 0, "提交", currDate, (String)null);
         this.tdPaOrderSignMainService.addTdPaOrderOperationSign(orderItemList, (OrderCommitVo)orderCommitVo, personalVo, 0, "提交");
      }

      TdCaOperationApply operationApply = this.tdCaOperationApplyMapper.selectTdCaOperationApplyById(tdCaOperationApply.getApplyFormNo());
      if (operationApply.getOperTypeCd().equals("2")) {
         operationApply.setStatus("02");
      } else {
         operationApply.setStatus("05");
         operationApply.setAuditDate(this.commonService.getDbSysdate());
         operationApply.setAuditDocCd(basEmployee.getEmplNumber());
         operationApply.setAuditDocName(basEmployee.getEmplName());
      }

      this.tdCaOperationApplyMapper.updateTdCaOperationApply(operationApply);
      PatEvent eventParam = new PatEvent();
      eventParam.setPatientId(tdCaOperationApply.getPatientId());
      eventParam.setEventCode("17");
      List<PatEvent> list = this.patEventService.selectPatEventList(eventParam);
      PatEvent patEvent = new PatEvent();
      if (list != null && !list.isEmpty()) {
         patEvent = (PatEvent)list.get(0);
         patEvent.setAltDate(currDate);
         this.patEventService.updatePatEvent(patEvent);
      } else {
         patEvent.setId(SnowIdUtils.uniqueLong());
         patEvent.setOrgCd(personalVo.getOrgCd());
         patEvent.setInpNo(personalVo.getInpNo());
         patEvent.setPatientId(personalVo.getPatientId());
         patEvent.setPatientName(personalVo.getPatientName());
         patEvent.setEventCode("17");
         patEvent.setEventName("手术预约");
         patEvent.setBeginTime(currDate);
         patEvent.setEventNo(tdCaOperationApply.getApplyFormNo());
         patEvent.setPreDocCd(basEmployee.getEmplNumber());
         patEvent.setEventSource("HIS");
         patEvent.setDelFlag("0");
         patEvent.setCreDate(currDate);
         patEvent.setAltDate(currDate);
         this.patEventService.insertPatEvent(patEvent);
      }

   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void cancelSubmitOpeApply(TdCaOperationApply tdCaOperationApply, OrderStopSignVo orderStopSignVo) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      tdCaOperationApply.setStatus("01");
      tdCaOperationApply.setAltDate(this.commonService.getDbSysdate());
      tdCaOperationApply.setAltPerCode(basEmployee.getEmplNumber());
      tdCaOperationApply.setAltPerName(basEmployee.getEmplName());
      this.tdCaOperationApplyMapper.updateTdCaOperationApply(tdCaOperationApply);
      TdCaOperationApply param = this.tdCaOperationApplyMapper.selectTdCaOperationApplyById(tdCaOperationApply.getApplyFormNo());
      String opeOrder = this.sysEmrConfigService.selectSysEmrConfigByKey("0082");
      if (opeOrder != null && opeOrder.equals("1")) {
         this.stopOpeOrder(param);
      }

      this.patEventService.delPatientEventByOrderNos(Arrays.asList(tdCaOperationApply.getApplyFormNo()));
      String oldApplyFormNo = tdCaOperationApply.getApplyFormNo();
      String newApplyFormNo = DateUtils.getDateStr("");
      this.emrTaskInfoService.updateTaskInfoEventNo(oldApplyFormNo, newApplyFormNo);
      this.indexService.updateOrderNoByOrderNo(oldApplyFormNo, newApplyFormNo);
      this.subfileIndexService.updateOrderNoByOrderNo(oldApplyFormNo, newApplyFormNo);
      this.tdCaOperationApplyMapper.updateApplyNoByApplyNo(oldApplyFormNo, newApplyFormNo);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void deleteTdCaOperationApply(TdCaOperationApply tdCaOperationApply, OrderStopSignVo orderStopSignVo) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      tdCaOperationApply.setStatus("03");
      tdCaOperationApply.setAltDate(this.commonService.getDbSysdate());
      tdCaOperationApply.setAltPerCode(basEmployee.getEmplNumber());
      tdCaOperationApply.setAltPerName(basEmployee.getEmplName());
      this.tdCaOperationApplyMapper.updateTdCaOperationApply(tdCaOperationApply);
      this.indexService.updateEmrOrderNo(tdCaOperationApply.getApplyFormNo());
      this.subfileIndexService.updateEmrOrderNo(tdCaOperationApply.getApplyFormNo());
      this.emrTaskInfoService.deleteTaskInfoByEventNo(tdCaOperationApply.getApplyFormNo());
   }

   public void stopOpeOrder(TdCaOperationApply tdCaOperationApply) throws Exception {
      String patientId = tdCaOperationApply.getPatientId();
      Visitinfo visitinfo = this.visitinfoService.selectVisitinfoById(patientId);
      Date currDate = this.commonService.getDbSysdate();
      List<String> orderNoList = new ArrayList();
      orderNoList.add(tdCaOperationApply.getOrderNo());
      List<TdPaOrderItem> orderItemList = new ArrayList();
      TdPaOrderItem tdPaOrderItem = this.tdPaOrderItemService.selectTdPaOrderItemById(tdCaOperationApply.getOrderNo());
      orderItemList.add(tdPaOrderItem);
      this.tdPaOrderStatusService.addTdPaOrderStatus(visitinfo, orderItemList, 11, "作废", currDate, (String)null);
      this.tdPaOrderOperationLogService.addTdPaOrderOperationLog(visitinfo, orderItemList, 11, "作废", currDate, (String)null);
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      TdPaOrderItemVo param = new TdPaOrderItemVo();
      param.setOrderNoList(Arrays.asList(tdCaOperationApply.getOrderNo()));
      param.setOrderStatus("11");
      this.tdPaOrderItemService.updateOrderStatus(param);
   }

   public List selectOperBeforeList(TdCaOperationApply tdCaOperationApply, String eventCd) throws Exception {
      List<QcAgiRuleVo> list = this.qcAgiRuleService.selectOperBeforeList(eventCd);
      List<TdCaOperationApplyVo> result = new ArrayList();
      if (list != null && !list.isEmpty()) {
         List<String> mrTypeList = (List)list.stream().map((s) -> s.getEmrTypeCode()).collect(Collectors.toList());
         List<QcRuleIterEmr> qcRuleIterEmrList = this.qcRuleIterEmrService.selectListByEmrTypeList(mrTypeList);
         List<IndexVo> emrList = new ArrayList();
         if (StringUtils.isNotEmpty(tdCaOperationApply.getApplyFormNo())) {
            emrList = this.indexService.selectOpeIndexByOrderNo(tdCaOperationApply.getApplyFormNo());
         }

         for(QcAgiRuleVo qcAgiRuleVo : list) {
            List<String> typeList = new ArrayList(Arrays.asList(qcAgiRuleVo.getEmrTypeCode()));
            TdCaOperationApplyVo tdCaOperationApplyVo = new TdCaOperationApplyVo();
            tdCaOperationApplyVo.setEmrTypeCode(qcAgiRuleVo.getEmrTypeCode());
            tdCaOperationApplyVo.setEmrTypeName(qcAgiRuleVo.getEmrTypeName());
            tdCaOperationApplyVo.setEmrClassCode(qcAgiRuleVo.getEmrClassCode());
            tdCaOperationApplyVo.setAgiRuleId(qcAgiRuleVo.getId());
            tdCaOperationApplyVo.setAgiRuleName(qcAgiRuleVo.getRuleName());
            if (qcRuleIterEmrList != null && !qcRuleIterEmrList.isEmpty()) {
               List<QcRuleIterEmr> iterEmrs = (List)qcRuleIterEmrList.stream().filter((s) -> s.getEmrTypeCode().equals(qcAgiRuleVo.getEmrTypeCode())).collect(Collectors.toList());
               if (iterEmrs != null && !iterEmrs.isEmpty()) {
                  List<String> iter = (List)iterEmrs.stream().map((s) -> s.getIterTypeCode()).collect(Collectors.toList());
                  typeList.addAll(iter);
               }
            }

            tdCaOperationApplyVo.setEmrTypeList(typeList);
            if (emrList != null && !emrList.isEmpty()) {
               List<IndexVo> emr = (List)emrList.stream().filter((s) -> typeList.contains(s.getMrType())).collect(Collectors.toList());
               tdCaOperationApplyVo.setMrFileList(emr);
            }

            result.add(tdCaOperationApplyVo);
         }
      }

      return result;
   }

   public List selectOpeIndexByEmrType(TdCaOperationApplyVo tdCaOperationApply) throws Exception {
      List<String> mrTypeList = new ArrayList();
      mrTypeList.add(tdCaOperationApply.getEmrTypeCode());
      List<QcRuleIterEmr> qcRuleIterEmrList = this.qcRuleIterEmrService.selectListByEmrTypeList(mrTypeList);
      if (qcRuleIterEmrList != null && !qcRuleIterEmrList.isEmpty()) {
         List<String> iterList = (List)qcRuleIterEmrList.stream().map((s) -> s.getIterTypeCode()).collect(Collectors.toList());
         mrTypeList.addAll(iterList);
      }

      List<IndexVo> indexVos = this.indexService.selectOpeIndexByEmrType(tdCaOperationApply.getPatientId(), mrTypeList);
      if (StringUtils.isNotEmpty(tdCaOperationApply.getApplyFormNo())) {
         List<IndexVo> indexVoList = this.indexService.selectOpeIndexByOrderNo(tdCaOperationApply.getApplyFormNo());
         if (indexVoList != null && !indexVoList.isEmpty()) {
            indexVoList = (List)indexVoList.stream().filter((s) -> mrTypeList.contains(s.getMrType()) && s.getAgiRuleId() != null && s.getAgiRuleId().equals(tdCaOperationApply.getAgiRuleId().toString())).collect(Collectors.toList());
            indexVos.addAll(indexVoList);
         }
      }

      return indexVos;
   }

   public void updateApplyAudit(TdCaOperationApply tdCaOperationApply) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      tdCaOperationApply.setAuditDocCd(basEmployee.getEmplNumber());
      tdCaOperationApply.setAuditDocName(basEmployee.getEmplName());
      tdCaOperationApply.setAuditDate(this.commonService.getDbSysdate());
      this.tdCaOperationApplyMapper.updateTdCaOperationApply(tdCaOperationApply);
   }

   public List selectOperFlowChart(String applyFormNo) throws Exception {
      List<Map<String, Object>> listMap = new ArrayList();
      TdCaOperationApply tdCaOperationApply = this.tdCaOperationApplyMapper.selectTdCaOperationApplyById(applyFormNo);
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
      if (tdCaOperationApply != null) {
         Map<String, Object> map = new HashMap();
         map.put("date", sdf.format(tdCaOperationApply.getApplyDate()));
         map.put("docName", tdCaOperationApply.getApplyDocName());
         map.put("status", "申请");
         listMap.add(map);
         Map<String, Object> map1 = new HashMap();
         map1.put("status", "审核");
         if (tdCaOperationApply.getAuditDate() != null) {
            map1.put("date", sdf.format(tdCaOperationApply.getAuditDate()));
            map1.put("docName", tdCaOperationApply.getAuditDocName());
         }

         listMap.add(map1);
         Map<String, Object> map2 = new HashMap();
         map2.put("status", "安排");
         if (tdCaOperationApply.getShiftDate() != null) {
            map2.put("date", sdf.format(tdCaOperationApply.getShiftDate()));
            map2.put("docName", tdCaOperationApply.getShiftNurseName());
         }

         listMap.add(map2);
         Map<String, Object> map3 = new HashMap();
         map3.put("status", "麻醉");
         listMap.add(map3);
         Map<String, Object> map4 = new HashMap();
         map4.put("status", "手术开始");
         listMap.add(map4);
         Map<String, Object> map5 = new HashMap();
         map5.put("status", "手术结束");
         listMap.add(map5);
      }

      return listMap;
   }

   public Map selectHisCheckResult(String patientId) throws Exception {
      Map<String, Object> map = new HashMap();
      Map<String, Object> paramMap = new HashMap();
      paramMap.put("patientId", patientId);
      paramMap.put("resultList", new HashMap());
      this.tdCaOperationApplyMapper.selectOperResultList(paramMap);
      List<OperTestResultVo> list = (List)paramMap.get("resultList");
      if (list != null && list.size() > 0) {
         for(OperTestResultVo resultVo : list) {
            if (StringUtils.isNotEmpty(resultVo.getHisItemCd())) {
               map.put(resultVo.getHisItemCd(), resultVo.getHisResultCd());
               map.put(resultVo.getHisItemCd() + "Name", resultVo.getHisResultName());
            }
         }
      }

      return map;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void addOperEmr(TdCaOperationApplyVo tdCaOperationApplyVo) throws Exception {
      TdCaOperationApply tdCaOperationApply = this.tdCaOperationApplyMapper.selectTdCaOperationApplyById(tdCaOperationApplyVo.getApplyFormNo());
      VisitinfoPersonalVo personalVo = this.visitinfoService.selectVisitinfoPersonalById(tdCaOperationApply.getPatientId());
      QcAgiRule qcAgiRule = this.qcAgiRuleService.selectQcAgiRuleById(tdCaOperationApplyVo.getAgiRuleId());
      QcAgiRule qcAgiRule28 = this.qcAgiRuleService.selectYxQcAgiRuleByCode("R028");
      QcAgiRule qcAgiRule29 = this.qcAgiRuleService.selectYxQcAgiRuleByCode("R029");
      List<IndexVo> indexVoList = tdCaOperationApplyVo.getMrFileList();
      List<String> mrTypeList = new ArrayList();
      mrTypeList.add(tdCaOperationApplyVo.getEmrTypeCode());
      List<QcRuleIterEmr> qcRuleIterEmrList = this.qcRuleIterEmrService.selectListByEmrTypeList(mrTypeList);
      if (qcRuleIterEmrList != null && !qcRuleIterEmrList.isEmpty()) {
         List<String> iterList = (List)qcRuleIterEmrList.stream().map((s) -> s.getIterTypeCode()).collect(Collectors.toList());
         mrTypeList.addAll(iterList);
      }

      List<IndexVo> indexVos = this.indexService.selectOpeIndexByOrderNo(tdCaOperationApply.getApplyFormNo());
      indexVos = (List<IndexVo>)(indexVos != null && !indexVos.isEmpty() ? (List)indexVos.stream().filter((s) -> mrTypeList.contains(s.getMrType()) && s.getAgiRuleId().equals(qcAgiRule.getId().toString())).collect(Collectors.toList()) : new ArrayList());
      List<Long> taskId = (List<Long>)(indexVos != null && !indexVos.isEmpty() ? (List)indexVos.stream().map((s) -> s.getTaskId()).collect(Collectors.toList()) : new ArrayList());
      if (taskId != null && !taskId.isEmpty()) {
         this.emrTaskInfoService.updateTaskBusByIds(taskId);
         this.indexService.updateOrderNoEmptyByIdList((List)indexVos.stream().map((s) -> s.getId()).collect(Collectors.toList()));
         this.subfileIndexService.updateOrderNoEmptyByIdList((List)indexVos.stream().map((s) -> s.getId()).collect(Collectors.toList()));
      }

      if (indexVoList != null && !indexVoList.isEmpty()) {
         IndexVo indexVo = (IndexVo)indexVoList.get(0);
         indexVo.setAgiRuleId(qcAgiRule.getId().toString());
         indexVo.setAgiRuleName(qcAgiRule.getRuleName());
         new EmrTaskInfoVo();
         EmrTaskInfoVo emrTaskInfo = this.emrTaskInfoService.selectTaskByEventNoAndBus(qcAgiRule.getId().toString(), "0", tdCaOperationApply.getApplyFormNo());
         if (emrTaskInfo != null && emrTaskInfo.getId() != null) {
            Date date = this.commonService.getDbSysdate();
            emrTaskInfo.setMrFileId(indexVo.getId());
            emrTaskInfo.setTreatFlag("1");
            emrTaskInfo.setFinishTime(this.commonService.getDbSysdate());
            emrTaskInfo.setEventNo(tdCaOperationApply.getApplyFormNo());
            this.emrTaskInfoService.updateEmrTaskInfo(emrTaskInfo);
         }

         if (emrTaskInfo == null || emrTaskInfo.getId() == null) {
            EmrTaskInfoVo emrTaskInfoVo = this.setEmrTaskInfo(indexVo, personalVo);
            emrTaskInfoVo.setBusSection(qcAgiRule.getAgiEvnt());
            if (qcAgiRule.getAgiEvnt().equals("17")) {
               emrTaskInfoVo.setBusSectionName("手术预约");
               if ("5".equals(indexVo.getMrType())) {
                  if (qcAgiRule29 != null) {
                     emrTaskInfoVo.setLimitTimeUnit(qcAgiRule29.getLimitTimeUnit());
                     emrTaskInfoVo.setLimitTime(qcAgiRule29.getFinishLimitTime());
                  }
               } else if ("15".equals(indexVo.getMrType()) && qcAgiRule28 != null) {
                  emrTaskInfoVo.setLimitTimeUnit(qcAgiRule28.getLimitTimeUnit());
                  emrTaskInfoVo.setLimitTime(qcAgiRule28.getFinishLimitTime());
               }
            } else {
               emrTaskInfoVo.setBusSectionName("手术");
            }

            emrTaskInfoVo.setEventNo(tdCaOperationApply.getApplyFormNo());
            this.emrTaskInfoService.insertEmrTaskInfo(emrTaskInfoVo);
         }

         List<Long> idList = Arrays.asList(indexVo.getId());
         this.indexService.updateEmrOrderNoByIdList(tdCaOperationApply.getApplyFormNo(), idList);
         this.subfileIndexService.updateEmrOrderNoByIdList(tdCaOperationApply.getApplyFormNo(), idList);
      }

   }

   public List selectListByOrderNos(List orderNoList) throws Exception {
      List<TdCaOperationApply> list = new ArrayList(1);
      if (orderNoList != null && !orderNoList.isEmpty()) {
         list = this.tdCaOperationApplyMapper.selectListByOrderNos(orderNoList);
      }

      return list;
   }

   public void updateStatusByIds(List applyFormNoList, String status) throws Exception {
      if (applyFormNoList != null && !applyFormNoList.isEmpty() && StringUtils.isNotBlank(status)) {
         this.tdCaOperationApplyMapper.updateStatusByIds(applyFormNoList, status);
      }

   }

   public Map selectOperDateTime() throws Exception {
      Map<String, String> map = new HashMap();
      String timeStr = this.sysEmrConfigService.selectSysEmrConfigByKey("0037");
      if (StringUtils.isNotEmpty(timeStr)) {
         String[] time = timeStr.split("~");
         String dateBeginStr = DateUtils.getDate() + " " + time[0] + ":00";
         String dateEndStr = DateUtils.getDate() + " " + time[1] + ":00";
         map.put("beginDateTime", dateBeginStr);
         map.put("endDateTime", dateEndStr);
      }

      return map;
   }

   public EmrTaskInfoVo setEmrTaskInfo(IndexVo indexVo, VisitinfoPersonalVo personalVo) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      EmrTaskInfoVo emrTaskInfo = new EmrTaskInfoVo();
      emrTaskInfo.setId(SnowIdUtils.uniqueLong());
      emrTaskInfo.setInpNo(personalVo.getInpNo());
      emrTaskInfo.setPatientId(personalVo.getPatientId());
      emrTaskInfo.setPatientName(personalVo.getPatientName());
      emrTaskInfo.setDeptCd(personalVo.getDeptCd());
      emrTaskInfo.setDeptName(personalVo.getDeptName());
      emrTaskInfo.setTaskType("1");
      emrTaskInfo.setTaskTypeName("病历书写");
      emrTaskInfo.setTaskAppDoc(basEmployee.getEmplNumber());
      emrTaskInfo.setTaskAppDocName(basEmployee.getEmplName());
      emrTaskInfo.setBeginTime(this.commonService.getDbSysdate());
      emrTaskInfo.setEndTime(this.commonService.getDbSysdate());
      emrTaskInfo.setBusId(indexVo.getAgiRuleId());
      emrTaskInfo.setBusName(indexVo.getAgiRuleName());
      emrTaskInfo.setFinishTime(this.commonService.getDbSysdate());
      emrTaskInfo.setOvertimeFlag(0);
      emrTaskInfo.setTreatFlag("1");
      emrTaskInfo.setMrFileId(indexVo.getId());
      return emrTaskInfo;
   }

   public List selectOperPlanStatusList(OperRoomVisitinfoVo operRoomVisitinfoVo) throws Exception {
      return this.tdCaOperationApplyMapper.selectOperPlanStatusList(operRoomVisitinfoVo);
   }

   public List selectOperRoomDocWorkload(TdCaOperationApplyVo tdCaOperationApplyVo) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      tdCaOperationApplyVo.setExecDeptCd(sysUser.getDept().getDeptCode());
      List<TdCaOperWorkloadVo> resultList = new ArrayList();
      List<TdCaOperationApplyVo> list = this.tdCaOperationApplyMapper.selectOperRoomDocWorkload(tdCaOperationApplyVo);
      if (list != null && !list.isEmpty()) {
         Map<String, List<TdCaOperationApplyVo>> listMap = (Map)list.stream().collect(Collectors.groupingBy((s) -> s.getOperDocCd() + s.getApplyDeptCd()));

         for(String docCd : listMap.keySet()) {
            List<TdCaOperationApplyVo> docList = (List)listMap.get(docCd);
            TdCaOperWorkloadVo tdCaOperWorkloadVo = new TdCaOperWorkloadVo();
            tdCaOperWorkloadVo.setOperDocName(((TdCaOperationApplyVo)docList.get(0)).getOperDocName());
            tdCaOperWorkloadVo.setApplyDeptName(((TdCaOperationApplyVo)docList.get(0)).getApplyDeptName());
            tdCaOperWorkloadVo.setOperNum(docList.size());
            List<TdCaOperationApplyVo> list1 = (List)docList.stream().filter((s) -> s.getOperLevelCd().equals("1")).collect(Collectors.toList());
            List<TdCaOperationApplyVo> list2 = (List)docList.stream().filter((s) -> s.getOperLevelCd().equals("2")).collect(Collectors.toList());
            List<TdCaOperationApplyVo> list3 = (List)docList.stream().filter((s) -> s.getOperLevelCd().equals("3")).collect(Collectors.toList());
            List<TdCaOperationApplyVo> list4 = (List)docList.stream().filter((s) -> s.getOperLevelCd().equals("4")).collect(Collectors.toList());
            tdCaOperWorkloadVo.setOperType1Num(list1 != null && !list1.isEmpty() ? list1.size() : 0);
            tdCaOperWorkloadVo.setOperType2Num(list1 != null && !list2.isEmpty() ? list2.size() : 0);
            tdCaOperWorkloadVo.setOperType3Num(list1 != null && !list3.isEmpty() ? list3.size() : 0);
            tdCaOperWorkloadVo.setOperType4Num(list1 != null && !list4.isEmpty() ? list4.size() : 0);
            resultList.add(tdCaOperWorkloadVo);
         }
      }

      TdCaOperWorkloadVo workloadVo = new TdCaOperWorkloadVo();
      workloadVo.setOperType1Num(resultList != null && !resultList.isEmpty() ? (Integer)resultList.stream().collect(Collectors.summingInt(TdCaOperWorkloadVo::getOperType1Num)) : 0);
      workloadVo.setOperType2Num(resultList != null && !resultList.isEmpty() ? (Integer)resultList.stream().collect(Collectors.summingInt(TdCaOperWorkloadVo::getOperType2Num)) : 0);
      workloadVo.setOperType3Num(resultList != null && !resultList.isEmpty() ? (Integer)resultList.stream().collect(Collectors.summingInt(TdCaOperWorkloadVo::getOperType3Num)) : 0);
      workloadVo.setOperType4Num(resultList != null && !resultList.isEmpty() ? (Integer)resultList.stream().collect(Collectors.summingInt(TdCaOperWorkloadVo::getOperType4Num)) : 0);
      workloadVo.setOperNum(resultList != null && !resultList.isEmpty() ? (Integer)resultList.stream().collect(Collectors.summingInt(TdCaOperWorkloadVo::getOperNum)) : 0);
      resultList.add(workloadVo);
      return resultList;
   }

   public OpeAuthorityVo selectDocOpeAuth(String docCd, String operLevel) throws Exception {
      OpeAuthorityVo opeAuthorityVo = new OpeAuthorityVo();
      if (StringUtils.isNotBlank(docCd) && StringUtils.isNotBlank(operLevel)) {
         opeAuthorityVo.setDocCd(docCd);
         opeAuthorityVo.setOperLevel(operLevel);
         opeAuthorityVo.setApplyAuth("0");
         opeAuthorityVo.setOperationAuth("0");
         List<TmBsStaffOperlevel> operlevelList = this.tdCaOperationApplyMapper.selectDocOpeAuth(docCd);
         if (CollectionUtils.isNotEmpty(operlevelList)) {
            List<String> operGradeCodeList = (List)operlevelList.stream().map((t) -> t.getOperGradeCode()).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(operGradeCodeList) && operGradeCodeList.contains(operLevel)) {
               opeAuthorityVo.setApplyAuth("1");
               opeAuthorityVo.setOperationAuth("1");
            }
         }
      }

      return opeAuthorityVo;
   }
}
