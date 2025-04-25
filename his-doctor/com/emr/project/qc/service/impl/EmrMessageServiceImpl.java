package com.emr.project.qc.service.impl;

import com.emr.common.constant.CommonConstants;
import com.emr.common.utils.AgeUtil;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.borrowing.domain.EmrIndexBorrow;
import com.emr.project.borrowing.service.IEmrIndexBorrowService;
import com.emr.project.common.service.ICommonService;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.qc.domain.EmrMessage;
import com.emr.project.qc.domain.EmrQcList;
import com.emr.project.qc.domain.EmrQcRecord;
import com.emr.project.qc.domain.vo.EmrMessageVo;
import com.emr.project.qc.mapper.EmrMessageMapper;
import com.emr.project.qc.service.IEmrMessageService;
import com.emr.project.system.domain.BasEmployee;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmrMessageServiceImpl implements IEmrMessageService {
   @Autowired
   private EmrMessageMapper emrMessageMapper;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private IEmrIndexBorrowService emrIndexBorrowService;

   public EmrMessage selectEmrMessageById(Long id) {
      return this.emrMessageMapper.selectEmrMessageById(id);
   }

   public List selectEmrMessageList(EmrMessageVo emrMessageVo) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      emrMessageVo.setDoctCd(basEmployee.getEmplNumber());
      List<EmrMessageVo> msgList = this.emrMessageMapper.selectEmrMessageList(emrMessageVo);
      List<EmrMessageVo> msgListRecall = (List)msgList.stream().filter((t) -> StringUtils.isNotBlank(t.getMrState()) && ("16".equals(t.getMrState()) || "14".equals(t.getMrState()))).collect(Collectors.toList());
      List<String> patientIds = (List)msgListRecall.stream().map((t) -> t.getPatientId()).distinct().collect(Collectors.toList());
      EmrIndexBorrow emrIndexBorrow = new EmrIndexBorrow();
      emrIndexBorrow.setAdmissionNoList(patientIds);
      List<EmrIndexBorrow> emrIndexBorrowList = this.emrIndexBorrowService.selectEmrIndexBorrowValidList(emrIndexBorrow);
      List<String> borrowPatients = (List)emrIndexBorrowList.stream().map((t) -> t.getAdmissionNo()).distinct().collect(Collectors.toList());

      for(EmrMessageVo emrMessageVo1 : msgList) {
         if (!borrowPatients.contains(emrMessageVo1.getPatientId()) && ("16".equals(emrMessageVo1.getMrState()) || "14".equals(emrMessageVo1.getMrState()))) {
            emrMessageVo1.setIsRecallApply("0");
            emrMessageVo1.setAge(AgeUtil.getAgeStr(emrMessageVo1.getAgeY(), emrMessageVo1.getAgeM(), emrMessageVo1.getAgeD(), emrMessageVo1.getAgeH(), emrMessageVo1.getAgeMi()));
         }
      }

      return msgList;
   }

   public List selectEmrMessageByDoctCd(EmrMessageVo emrMessageVo) throws Exception {
      List<EmrMessageVo> msgList = this.emrMessageMapper.selectEmrMessageList(emrMessageVo);
      return msgList;
   }

   public int insertEmrMessage(EmrMessage emrMessage) {
      return this.emrMessageMapper.insertEmrMessage(emrMessage);
   }

   public int updateEmrMessage(EmrMessage emrMessage) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      emrMessage.setAltPerCode(basEmployee.getEmplNumber());
      emrMessage.setAltPerName(basEmployee.getEmplName());
      emrMessage.setAltDate(this.commonService.getDbSysdate());
      return this.emrMessageMapper.updateEmrMessage(emrMessage);
   }

   public void updateEmrMessageByBusId(EmrMessage emrMessage) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      emrMessage.setAltPerCode(basEmployee.getEmplNumber());
      emrMessage.setAltPerName(basEmployee.getEmplName());
      emrMessage.setAltDate(this.commonService.getDbSysdate());
      this.emrMessageMapper.updateEmrMessageByBusId(emrMessage);
   }

   public int deleteEmrMessageByIds(List ids) {
      return this.emrMessageMapper.deleteEmrMessageByIds(ids);
   }

   public int deleteEmrMessageById(Long id) {
      return this.emrMessageMapper.deleteEmrMessageById(id);
   }

   public void getSaveEmrMessageList(EmrQcRecord qcRecord, List list) throws Exception {
      List<EmrMessage> insertList = new ArrayList();
      VisitinfoVo visitinfoVo = this.visitinfoService.selectVisitinfoByPatientId(qcRecord.getPatientId());
      String bedNo = visitinfoVo.getBedNo() == null ? "" : visitinfoVo.getBedNo();
      if (list != null && !list.isEmpty()) {
         Map<String, List<EmrQcList>> listMap = (Map)list.stream().collect(Collectors.groupingBy((s) -> s.getMrFileId()));

         for(String mrFileId : listMap.keySet()) {
            List<EmrQcList> emrList = (List)listMap.get(mrFileId);
            EmrMessage emrMessage = this.setEmrMessageInfo(visitinfoVo, qcRecord);
            emrMessage.setMsgType(CommonConstants.EMR_QC_MESSAGE.MSG_TYPE_1);
            String section = this.getQcSectionName(qcRecord.getQcSection());
            emrMessage.setMsgContent(bedNo + "床 " + qcRecord.getPatientName() + " " + qcRecord.getInpNo() + " " + qcRecord.getInDeptName() + "在" + section + "中反馈了病历" + ((EmrQcList)emrList.get(0)).getMrFileName() + "的缺陷，请及时处理！");
            emrMessage.setMsgState(0);
            if ("61".equals(((EmrQcList)emrList.get(0)).getMrType())) {
               emrMessage.setBusType(CommonConstants.EMR_QC_MESSAGE.BUS_TYPE_2);
            } else {
               emrMessage.setBusType(CommonConstants.EMR_QC_MESSAGE.BUS_TYPE_1);
            }

            emrMessage.setBusId(mrFileId);
            insertList.add(emrMessage);
         }
      }

      if (qcRecord.getState().equals("3")) {
         EmrMessage emrMessage = this.setEmrMessageInfo(visitinfoVo, qcRecord);
         emrMessage.setMsgType(CommonConstants.EMR_QC_MESSAGE.MSG_TYPE_2);
         String section = this.getQcSectionName(qcRecord.getQcSection());
         emrMessage.setMsgContent(bedNo + "床 " + qcRecord.getPatientName() + " " + qcRecord.getInpNo() + " " + qcRecord.getInDeptName() + "病历在" + section + "中被退回，请修改缺陷后及时重新提交" + section);
         emrMessage.setBusType(CommonConstants.EMR_QC_MESSAGE.BUS_TYPE_3);
         emrMessage.setBusId(qcRecord.getId().toString());
         insertList.add(emrMessage);
      }

      this.insertEmrMessageList(insertList);
   }

   private EmrMessage setEmrMessageInfo(VisitinfoVo visitinfoVo, EmrQcRecord qcRecord) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      Date nowDate = this.commonService.getDbSysdate();
      EmrMessage emrMessage = new EmrMessage();
      emrMessage.setId(SnowIdUtils.uniqueLong());
      emrMessage.setPatientId(qcRecord.getPatientId());
      emrMessage.setPatientName(qcRecord.getPatientName());
      emrMessage.setInpNo(qcRecord.getInpNo());
      emrMessage.setRoomNo(visitinfoVo.getRoomNo());
      emrMessage.setBedNo(visitinfoVo.getBedNo());
      emrMessage.setDeptCd(qcRecord.getInDeptCd());
      emrMessage.setDeptName(qcRecord.getInDeptName());
      emrMessage.setDoctCd(visitinfoVo.getResDocCd());
      emrMessage.setDoctName(visitinfoVo.getResDocName());
      emrMessage.setMsgState(0);
      emrMessage.setCrePerCode(basEmployee.getEmplNumber());
      emrMessage.setCrePerName(basEmployee.getEmplName());
      emrMessage.setCreDate(nowDate);
      return emrMessage;
   }

   @Async
   public void insertEmrMessageList(List emrMessageList) throws Exception {
      if (emrMessageList != null && !emrMessageList.isEmpty()) {
         this.emrMessageMapper.insertEmrMessageList(emrMessageList);
      }

   }

   private String getQcSectionName(String qcSectionCd) throws Exception {
      String str = "";
      switch (qcSectionCd) {
         case "01":
            str = "实时质控";
            break;
         case "02":
            str = "科室质控";
            break;
         case "03":
            str = "抽查质控";
            break;
         case "04":
            str = "离线质控";
            break;
         case "05":
            str = "终末质控";
      }

      return str;
   }

   @Async
   public void updateEmrMessageList(List idList) throws Exception {
      if (idList != null && !idList.isEmpty()) {
         this.emrMessageMapper.updateEmrMessageList(idList);
      }

   }

   public List selectByBusId(EmrMessage emrMessage) throws Exception {
      return this.emrMessageMapper.selectByBusId(emrMessage);
   }
}
