package com.emr.project.pat.service.impl;

import com.emr.common.constant.CommonConstants;
import com.emr.common.utils.AgeUtil;
import com.emr.common.utils.MessageUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.framework.aspectj.lang.annotation.DataSource;
import com.emr.framework.aspectj.lang.enums.DataSourceType;
import com.emr.framework.web.service.ISyncService;
import com.emr.project.common.domain.vo.ReplaceUrlParamVo;
import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.domain.TdPaApplyFormItem;
import com.emr.project.docOrder.domain.vo.TdPaApplyFormItemVo;
import com.emr.project.docOrder.service.ITdPaApplyFormItemService;
import com.emr.project.pat.domain.ExamItem;
import com.emr.project.pat.domain.vo.ExamItemVo;
import com.emr.project.pat.domain.vo.TestExamAlertMsgResVo;
import com.emr.project.pat.domain.vo.TestExamAlertMsgVo;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.pat.mapper.ExamItemMapper;
import com.emr.project.pat.service.IAppItemService;
import com.emr.project.pat.service.IExamItemService;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.qc.domain.EmrMessage;
import com.emr.project.qc.domain.vo.EmrMessageVo;
import com.emr.project.qc.service.IEmrMessageService;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.vo.SqlVo;
import com.emr.project.tmpm.domain.ClinItemMain;
import com.emr.project.tmpm.service.IClinItemMainService;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExamItemServiceImpl implements IExamItemService, ISyncService {
   protected final Logger log = LoggerFactory.getLogger(ExamItemServiceImpl.class);
   @Autowired
   private ExamItemMapper examItemMapper;
   @Autowired
   private IAppItemService appItemService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private IEmrMessageService emrMessageService;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private ITdPaApplyFormItemService tdPaApplyFormItemService;
   @Autowired
   private IClinItemMainService clinItemMainService;

   public ExamItem selectExamItemById(String id) {
      return this.examItemMapper.selectExamItemById(id);
   }

   public List selectExamItemList(ExamItemVo examItemVo) {
      return this.examItemMapper.selectExamItemList(examItemVo);
   }

   public List selectItemList(String[] patientIds) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      List<ExamItemVo> list = this.examItemMapper.selectItemList(patientIds);
      Map<String, List<TdPaApplyFormItem>> formItemMap = new HashMap(1);
      Map<String, List<ClinItemMain>> clinItemMainMap = new HashMap(1);
      if (CollectionUtils.isNotEmpty(list)) {
         List<String> appCdList = (List)list.stream().map((tx) -> tx.getApplyFormNo()).collect(Collectors.toList());
         new HashMap();
         List<ExamItem> examItemVoList = this.examItemMapper.selectExamItemByAppCdList(appCdList);
         if (CollectionUtils.isNotEmpty(examItemVoList)) {
            Map examItemVo = (Map)examItemVoList.stream().collect(Collectors.groupingBy(ExamItem::getAppCd));

            for(ExamItemVo vo : list) {
               String applyFormNo = vo.getApplyFormNo();
               if (examItemVo.containsKey(applyFormNo)) {
                  List<ExamItem> examItems = (List)examItemVo.get(applyFormNo);
                  if (!examItems.isEmpty()) {
                     vo.setImageWay(((ExamItem)examItems.get(0)).getImageWay());
                     vo.setExamRepDate(((ExamItem)examItems.get(0)).getExamRepDate());
                  }
               }
            }
         }

         List<TdPaApplyFormItem> formItemList = this.tdPaApplyFormItemService.selectItemByApplyNoList(appCdList);
         List<String> itemCdList = (List)formItemList.stream().map((tx) -> tx.getItemCode()).distinct().collect(Collectors.toList());
         List<ClinItemMain> clinItemMainList = this.clinItemMainService.selectClinItemMainByItemCds(itemCdList);
         formItemMap = (Map)formItemList.stream().collect(Collectors.groupingBy((tx) -> tx.getApplyFormNo()));
         clinItemMainMap = (Map)clinItemMainList.stream().collect(Collectors.groupingBy((tx) -> tx.getItemCd()));
      }

      for(ExamItemVo examItemVo : list) {
         if ("9".equals(examItemVo.getItemState())) {
            examItemVo.setItemStateName("报告完成");
            ReplaceUrlParamVo replaceUrlParamVo = new ReplaceUrlParamVo(examItemVo.getInpNo(), examItemVo.getInpNo(), examItemVo.getCaseNo(), examItemVo.getHospitalizedCount().toString(), user.getUserName(), user.getNickName(), user.getDept().getDeptCode(), examItemVo.getApplyFormNo(), examItemVo.getId(), (String)null, (String)null);
            String reportUrl = this.commonService.replaceUrlParam(replaceUrlParamVo, examItemVo.getReportUrl());
            examItemVo.setImageWay(reportUrl);
         } else if ("0".equals(examItemVo.getItemState())) {
            examItemVo.setItemStateName("开立");
         } else if ("1".equals(examItemVo.getItemState())) {
            examItemVo.setItemStateName("已提交");
         } else if ("2".equals(examItemVo.getItemState())) {
            examItemVo.setItemStateName("已撤销");
         } else if ("30".equals(examItemVo.getItemState())) {
            examItemVo.setItemStateName("未记账");
         } else if ("31".equals(examItemVo.getItemState())) {
            examItemVo.setItemStateName("已记账");
         } else if ("4".equals(examItemVo.getItemState())) {
            examItemVo.setItemStateName("已欠费");
         } else if ("5".equals(examItemVo.getItemState())) {
            examItemVo.setItemStateName("已申请退费");
         } else if ("6".equals(examItemVo.getItemState())) {
            examItemVo.setItemStateName("已退费");
         } else if ("7".equals(examItemVo.getItemState())) {
            examItemVo.setItemStateName("医技科室确认");
         } else if ("8".equals(examItemVo.getItemState())) {
            examItemVo.setItemStateName("上级确认");
         } else if ("99".equals(examItemVo.getItemState())) {
            examItemVo.setItemStateName("已删除");
         }

         List<TdPaApplyFormItemVo> formItemList = new ArrayList(1);
         List<TdPaApplyFormItem> formItemListTemp = (List)formItemMap.get(examItemVo.getApplyFormNo());
         if (CollectionUtils.isNotEmpty(formItemListTemp)) {
            for(TdPaApplyFormItem tdPaApplyFormItem : formItemListTemp) {
               TdPaApplyFormItemVo t = new TdPaApplyFormItemVo();
               t.setItemCode(tdPaApplyFormItem.getItemCode());
               t.setItemName(tdPaApplyFormItem.getItemName());
               List<ClinItemMain> clinItemListTemp = (List)clinItemMainMap.get(t.getItemCode());
               ClinItemMain clinItemMain = CollectionUtils.isNotEmpty(clinItemListTemp) ? (ClinItemMain)clinItemListTemp.get(0) : null;
               if (clinItemMain != null) {
                  t.setIndication(clinItemMain.getIndication());
                  t.setExamNote(clinItemMain.getExamNote());
                  t.setSpecCollectionReq(clinItemMain.getSpecCollectionReq());
                  t.setExamSign(clinItemMain.getExamSign());
                  t.setSupportDiag(clinItemMain.getSupportDiag());
                  t.setExclusionDiag(clinItemMain.getExclusionDiag());
               }

               formItemList.add(t);
            }
         }

         examItemVo.setFormItemList(formItemList);
      }

      return list;
   }

   public List selectItemListByDate(ExamItemVo examItemVo) throws Exception {
      return this.examItemMapper.selectItemListByDate(examItemVo);
   }

   public List selectExamItemResultList(String id) throws Exception {
      List<ExamItemVo> examItemVo = this.examItemMapper.selectExamItemResultList(id);
      if (examItemVo != null) {
         for(ExamItemVo vo : examItemVo) {
            if (StringUtils.isNotBlank(vo.getBabyNo())) {
               vo.setPatientName(vo.getBabyName());
               vo.setSex(vo.getBabySex());
               vo.setAge("新生儿");
            } else {
               String age = AgeUtil.getAgeStr(vo.getAgeY(), vo.getAgeM(), vo.getAgeD(), vo.getAgeH(), vo.getAgeMi());
               vo.setAge(age);
            }
         }
      }

      return examItemVo;
   }

   public int insertExamItem(ExamItem examItem) {
      return this.examItemMapper.insertExamItem(examItem);
   }

   public int updateExamItem(ExamItem examItem) {
      return this.examItemMapper.updateExamItem(examItem);
   }

   public int deleteExamItemByIds(String[] ids) {
      return this.examItemMapper.deleteExamItemByIds(ids);
   }

   public int deleteExamItemById(String id) {
      return this.examItemMapper.deleteExamItemById(id);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void syncData(List hisList) throws Exception {
      int i = 0;

      for(Map temp : hisList) {
         this.log.info("i-> {}", i++);

         try {
            this.examItemMapper.insertMap(temp);
            String appCd = temp.get("app_cd") != null ? temp.get("app_cd").toString() : null;
            String itemState = "9";
            Date examRepDate = temp.get("exam_rep_date") != null ? (Date)temp.get("exam_rep_date") : null;
            if (StringUtils.isNotBlank(appCd)) {
               this.appItemService.updateStateDateByApp(appCd, (String)null, itemState, examRepDate);
               this.log.info("更新检验申请单和临床项目的状态");
            }
         } catch (Exception e) {
            for(String key : temp.keySet()) {
               this.log.info("对象数值===== key:{},value:{}", key, temp.get(key).toString());
            }

            this.log.error("同步检查报告出现异常，", e.getMessage());
            throw new Exception(e.getMessage());
         }
      }

   }

   public void syncAddData(List list, SqlVo sqlVo) throws Exception {
      this.syncData(list);
   }

   public void syncDataMap(List mapList, String tableName) throws Exception {
   }

   public List selectUnLookList(String patientId) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      List<ExamItemVo> resultList = new ArrayList();
      List<ExamItemVo> examList = this.examItemMapper.selectExamUnLookList(patientId);
      List<ExamItemVo> testList = this.examItemMapper.selectTestUnLookList(patientId);
      resultList.addAll(examList);
      resultList.addAll(testList);

      for(ExamItemVo examItemVo : resultList) {
         if (examItemVo.getHospitalizedCount() != null) {
            ReplaceUrlParamVo replaceUrlParamVo = new ReplaceUrlParamVo(examItemVo.getInpNo(), examItemVo.getInpNo(), examItemVo.getCaseNo(), examItemVo.getHospitalizedCount().toString(), user.getUserName(), user.getNickName(), user.getDept().getDeptCode(), examItemVo.getApplyFormNo(), examItemVo.getId(), (String)null, (String)null);
            String reportUrl = this.commonService.replaceUrlParam(replaceUrlParamVo, examItemVo.getReportUrl());
            examItemVo.setReportUrl(reportUrl);
         }
      }

      return resultList;
   }

   public void updateLookState(ExamItem examItem) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      examItem.setRecDocCd(sysUser.getUserName());
      this.examItemMapper.updateLookState(examItem);
   }

   @DataSource(DataSourceType.hisExamItem)
   public List selectHisExamItemList(SqlVo sqlVo) throws Exception {
      List<ExamItemVo> list = this.examItemMapper.selectHisExamItemList(sqlVo);
      return list;
   }

   public List selectHisExamItemResult(List list) throws Exception {
      if (null != list && list.size() > 0) {
         ExamItemVo examItemVo = (ExamItemVo)list.stream().findFirst().orElse((Object)null);
         if (null != examItemVo) {
            VisitinfoVo visitinfoVo = this.visitinfoService.selectVisitinfoById(examItemVo.getPatientId());

            for(ExamItemVo vo : list) {
               vo.setPatientName(visitinfoVo.getPatientName());
               vo.setInpNo(visitinfoVo.getInpNo());
               vo.setCaseNo(visitinfoVo.getRecordNo());
               vo.setSex(visitinfoVo.getSex());
               vo.setAge(visitinfoVo.getAge());
               vo.setBedNo(visitinfoVo.getBedNo());
               vo.setDeptName(visitinfoVo.getDeptName());
            }
         }
      }

      return list;
   }

   public TestExamAlertMsgResVo selectExamAlertMsg(ExamItemVo param) throws Exception {
      TestExamAlertMsgResVo result = new TestExamAlertMsgResVo();
      List<String> examAlertIdList = param.getExamIdList();
      param.setExamIdList((List)null);
      List<ExamItemVo> list = this.examItemMapper.selectExamAlertMsg(param);
      Date curnDate = this.commonService.getDbSysdate();
      if (CollectionUtils.isNotEmpty(list)) {
         List<EmrMessage> msgList = new ArrayList(list.size());
         List<EmrMessageVo> msgVoList = new ArrayList(list.size());
         List<TestExamAlertMsgVo> alertMsgList = new ArrayList(list.size());

         for(ExamItemVo t : list) {
            if (CollectionUtils.isEmpty(examAlertIdList) || CollectionUtils.isNotEmpty(examAlertIdList) && !examAlertIdList.contains(t.getId())) {
               EmrMessage emrMessage = this.getExamMessage(t, curnDate);
               EmrMessageVo emrMessageVo = new EmrMessageVo();
               BeanUtils.copyProperties(emrMessage, emrMessageVo);
               if (t.getMsgId() == null) {
                  msgList.add(emrMessage);
               }

               msgVoList.add(emrMessageVo);
            }

            TestExamAlertMsgVo examAlertMsgVo = this.getExamAlertMsg(t);
            alertMsgList.add(examAlertMsgVo);
         }

         result.setExamAlertList(list);
         result.setMsgList(msgVoList);
         result.setAlertMsgVoList(alertMsgList);
      }

      return result;
   }

   public List selectByAppCd(String appCd) throws Exception {
      return this.examItemMapper.selectByAppCd(appCd);
   }

   public List selectExamItemByAppCdList(List applyFormNoList) {
      return this.examItemMapper.selectExamItemByAppCdList(applyFormNoList);
   }

   @DataSource(DataSourceType.hisExamItem)
   public List selectHisExamAlertList(ExamItemVo examItemVo) throws Exception {
      return this.examItemMapper.selectHisExamAlertList(examItemVo);
   }

   public List selectMzExamItemList(ExamItemVo examItemVo) throws Exception {
      return this.examItemMapper.selectMzExamItemList(examItemVo);
   }

   private EmrMessage getExamMessage(ExamItemVo t, Date curnDate) {
      EmrMessage emrMessage = new EmrMessageVo();
      emrMessage.setId(SnowIdUtils.uniqueLong());
      emrMessage.setPatientId(t.getPatientId());
      emrMessage.setPatientName(t.getPatientName());
      emrMessage.setInpNo(t.getInpNo());
      emrMessage.setRoomNo((String)null);
      emrMessage.setBedNo(t.getBedNo());
      emrMessage.setDeptCd(t.getAppDeptCd());
      emrMessage.setDeptName(t.getAppDeptName());
      emrMessage.setMsgType(CommonConstants.EMR_QC_MESSAGE.MSG_TYPE_4);
      String msgContent = MessageUtils.message("pat.testexam.alert.msg", t.getBedNo(), t.getPatientName(), t.getCaseNo(), t.getExamItemName(), t.getExamResSub());
      emrMessage.setMsgContent(msgContent);
      emrMessage.setMsgState(0);
      emrMessage.setBusType(CommonConstants.EMR_QC_MESSAGE.BUS_TYPE_6);
      emrMessage.setBusId(t.getId());
      emrMessage.setCrePerCode(t.getRepDocCd());
      emrMessage.setCrePerName(t.getRepDocName());
      emrMessage.setCreDate(curnDate);
      emrMessage.setDoctCd(t.getAppDocCd());
      emrMessage.setDoctName(t.getAppDocName());
      emrMessage.setMsgWarnType("2");
      return emrMessage;
   }

   private TestExamAlertMsgVo getExamAlertMsg(ExamItemVo t) {
      TestExamAlertMsgVo examAlertMsgVo = new TestExamAlertMsgVo();
      examAlertMsgVo.setId(t.getId());
      examAlertMsgVo.setClassCode("02");
      examAlertMsgVo.setItemCd(t.getExamItemCd());
      examAlertMsgVo.setItemName(t.getExamItemName());
      examAlertMsgVo.setPublicDate(t.getRecDate());
      examAlertMsgVo.setPatientName(t.getPatientName());
      examAlertMsgVo.setInpNo(t.getInpNo());
      examAlertMsgVo.setCaseNo(t.getCaseNo());
      examAlertMsgVo.setBedNo(t.getBedNo());
      examAlertMsgVo.setSex(t.getSex());
      String ageStr = AgeUtil.getAgeStr(t.getAgeY(), t.getAgeM(), t.getAgeD(), t.getAgeH(), t.getAgeMi());
      examAlertMsgVo.setAge(ageStr);
      examAlertMsgVo.setResDocCd(t.getVisitingStaffNo());
      examAlertMsgVo.setResDocName(t.getVisitingStaffName());
      String contentStr = MessageUtils.message("pat.exam.alert.content", t.getExamItemName(), t.getExamResSub());
      examAlertMsgVo.setContentStr(contentStr);
      examAlertMsgVo.setRepDeptCd(t.getExamRepDeptCd());
      examAlertMsgVo.setRepDeptName(t.getExamRepDeptName());
      examAlertMsgVo.setRepDocCd(t.getRepDocCd());
      examAlertMsgVo.setRepDocName(t.getRepDocName());
      examAlertMsgVo.setAppDeptCd(t.getAppDeptCd());
      examAlertMsgVo.setAppDeptName(t.getAppDeptName());
      examAlertMsgVo.setPatientId(t.getPatientId());
      examAlertMsgVo.setPatientMainId(t.getPatientMainId());
      examAlertMsgVo.setMainFileId(t.getMainFileId());
      return examAlertMsgVo;
   }
}
