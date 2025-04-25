package com.emr.project.dr.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.emr.common.utils.AgeUtil;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.framework.redis.RedisCache;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.service.ITdPaOrderItemService;
import com.emr.project.docOrder.service.ITdPaOrderService;
import com.emr.project.dr.domain.TdCaConsApply;
import com.emr.project.dr.domain.vo.TdCaConsApplyVo;
import com.emr.project.dr.mapper.TdCaConsApplyMapper;
import com.emr.project.dr.service.ITdCaConsApplyService;
import com.emr.project.emr.domain.EmrSignData;
import com.emr.project.emr.domain.EmrSignRecord;
import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.SubfileIndex;
import com.emr.project.emr.domain.vo.ElemSignVo;
import com.emr.project.emr.domain.vo.EmrSignRecordVo;
import com.emr.project.emr.domain.vo.IndexSaveReturnVo;
import com.emr.project.emr.domain.vo.IndexSignVo;
import com.emr.project.emr.mapper.EmrSignRecordMapper;
import com.emr.project.emr.service.IEmrSignDataService;
import com.emr.project.emr.service.IEmrSignRecordService;
import com.emr.project.emr.service.IIndexService;
import com.emr.project.other.domain.ImpDoctorTemp;
import com.emr.project.other.service.IImpDoctorTempService;
import com.emr.project.pat.domain.vo.BackLogVo;
import com.emr.project.pat.domain.vo.VisitinfoPersonalVo;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.qc.domain.EmrMessage;
import com.emr.project.qc.domain.vo.EmrMessageVo;
import com.emr.project.qc.service.IEmrMessageService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysDictData;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.vo.BasEmployeeVo;
import com.emr.project.system.domain.vo.BsStaffVo;
import com.emr.project.system.service.IBasEmployeeService;
import com.emr.project.system.service.IBsStaffService;
import com.emr.project.system.service.ISysDeptService;
import com.emr.project.system.service.ISysDictDataService;
import com.emr.project.system.service.ISysEmrConfigService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TdCaConsApplyServiceImpl implements ITdCaConsApplyService {
   @Autowired
   private TdCaConsApplyMapper tdCaConsApplyMapper;
   @Autowired
   private IIndexService indexService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private IEmrSignDataService emrSignDataService;
   @Autowired
   private IEmrSignRecordService emrSignRecordService;
   @Autowired
   private IBasEmployeeService basEmployeeService;
   @Autowired
   private ISysDeptService sysDeptService;
   @Autowired
   private IEmrMessageService emrMessageService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private EmrSignRecordMapper emrSignRecordMapper;
   @Autowired
   private IImpDoctorTempService impDoctorTempService;
   @Autowired
   private RedisCache redisCache;
   @Autowired
   private ISysDictDataService sysDictDataService;
   @Autowired
   private IBsStaffService bsStaffService;
   @Autowired
   private ITdPaOrderItemService tdPaOrderItemService;
   @Autowired
   private ITdPaOrderService tdPaOrderService;

   public TdCaConsApply selectTdCaConsApplyById(Long id) {
      return this.tdCaConsApplyMapper.selectTdCaConsApplyById(id);
   }

   public TdCaConsApply selectTdCaConsApplyByMrFileId(Long id) {
      return this.tdCaConsApplyMapper.selectTdCaConsApplyByMrFileId(id);
   }

   public List selectTdCaConsApplyStatisList(TdCaConsApplyVo tdCaConsApplyVo) {
      return this.tdCaConsApplyMapper.selectTdCaConsApplyStatisList(tdCaConsApplyVo);
   }

   public List selectTdCaConsApplyList(TdCaConsApplyVo tdCaConsApplyVo) {
      return this.tdCaConsApplyMapper.selectTdCaConsApplyList(tdCaConsApplyVo);
   }

   public List selectUnFinishInviteList(TdCaConsApplyVo tdCaConsApply) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      tdCaConsApply.setState("03");
      tdCaConsApply.setAssignDocFlag("1");
      tdCaConsApply.setConsDeptCd(sysUser.getDept().getDeptCode());
      List<TdCaConsApplyVo> list = this.tdCaConsApplyMapper.selectTdCaConsApplyList(tdCaConsApply);
      this.setRemainHours(list);
      return list;
   }

   public List selectFinishInviteList(TdCaConsApplyVo tdCaConsApply) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      tdCaConsApply.setState("04");
      tdCaConsApply.setConsDeptCd(sysUser.getDept().getDeptCode());
      List<TdCaConsApplyVo> list = this.tdCaConsApplyMapper.selectTdCaConsApplyList(tdCaConsApply);
      this.setExceedState(list);
      return list;
   }

   public List selectUnFinishApplyList(TdCaConsApplyVo tdCaConsApply) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      List<String> stateList = new ArrayList();
      stateList.add("02");
      stateList.add("03");
      tdCaConsApply.setStateList(stateList);
      tdCaConsApply.setApplyDeptCd(sysUser.getDept().getDeptCode());
      List<TdCaConsApplyVo> list = this.tdCaConsApplyMapper.selectTdCaConsApplyList(tdCaConsApply);
      this.setRemainHours(list);
      return list;
   }

   public List selectFinishApplyList(TdCaConsApplyVo tdCaConsApply) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      List<String> stateList = new ArrayList();
      stateList.add("04");
      tdCaConsApply.setStateList(stateList);
      tdCaConsApply.setApplyDeptCd(sysUser.getDept().getDeptCode());
      List<TdCaConsApplyVo> list = this.tdCaConsApplyMapper.selectTdCaConsApplyList(tdCaConsApply);
      this.setExceedState(list);
      return list;
   }

   public void insertTdCaConsApply(TdCaConsApplyVo tdCaConsApplyVo) throws Exception {
      VisitinfoPersonalVo visitinfoVo = this.visitinfoService.selectVisitinfoPersonalById(tdCaConsApplyVo.getPatientId());
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      tdCaConsApplyVo.setId(SnowIdUtils.uniqueLong());
      tdCaConsApplyVo.setInpNo(visitinfoVo.getInpNo());
      tdCaConsApplyVo.setVisitId(visitinfoVo.getVisitId());
      tdCaConsApplyVo.setPatientName(visitinfoVo.getPatientName());
      tdCaConsApplyVo.setSex(visitinfoVo.getSex());
      tdCaConsApplyVo.setSexCd(visitinfoVo.getSexCd());
      String age = AgeUtil.getAgeStr(visitinfoVo.getAgeY(), visitinfoVo.getAgeM(), visitinfoVo.getAgeD(), visitinfoVo.getAgeH(), visitinfoVo.getAgeMi());
      tdCaConsApplyVo.setAge(age);
      tdCaConsApplyVo.setBedNo(visitinfoVo.getBedNo());
      tdCaConsApplyVo.setInhosTime(visitinfoVo.getInhosTime());
      tdCaConsApplyVo.setCrePerCode(sysUser.getBasEmployee().getEmplNumber());
      tdCaConsApplyVo.setCrePerName(sysUser.getBasEmployee().getEmplName());
      this.tdCaConsApplyMapper.insertTdCaConsApply(tdCaConsApplyVo);
   }

   public void updateTdCaConsApply(TdCaConsApplyVo tdCaConsApplyVo) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      tdCaConsApplyVo.setAltPerCode(basEmployee.getEmplNumber());
      tdCaConsApplyVo.setAltPerName(basEmployee.getEmplName());
      this.tdCaConsApplyMapper.updateTdCaConsApply(tdCaConsApplyVo);
   }

   public int deleteTdCaConsApplyByIds(Long[] ids) {
      return this.tdCaConsApplyMapper.deleteTdCaConsApplyByIds(ids);
   }

   public void deleteTdCaConsApplyById(Long id) throws Exception {
      this.tdCaConsApplyMapper.deleteTdCaConsApplyById(id);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void deleteTdCaConsApply(TdCaConsApplyVo tdCaConsApplyVo) throws Exception {
      TdCaConsApply tdCaConsApply = new TdCaConsApply();
      tdCaConsApply.setId(tdCaConsApplyVo.getId());
      tdCaConsApply.setDelDate(this.commonService.getDbSysdate());
      tdCaConsApply.setState("05");
      this.tdCaConsApplyMapper.updateTdCaConsApply(tdCaConsApply);
      this.indexService.delIndexById(tdCaConsApplyVo.getMrFileId());
      TdCaConsApply consApply = this.tdCaConsApplyMapper.selectTdCaConsApplyById(tdCaConsApplyVo.getId());
      if (consApply != null && StringUtils.isNotEmpty(consApply.getOrderNo())) {
         this.tdPaOrderService.deleteTdPaOrderByIds(Arrays.asList(consApply.getOrderNo()), (List)null, (List)null, (List)null);
      }

      EmrMessage emrMessage = new EmrMessage();
      emrMessage.setBusId(tdCaConsApplyVo.getId().toString());
      List<EmrMessage> emrMessageList = this.emrMessageService.selectByBusId(emrMessage);
      if (CollectionUtils.isNotEmpty(emrMessageList)) {
         List<Long> emrMsgIdList = (List)emrMessageList.stream().map((t) -> t.getId()).collect(Collectors.toList());
         this.emrMessageService.deleteEmrMessageByIds(emrMsgIdList);
      }

      if (StringUtils.isNotBlank(consApply.getInvDocCd())) {
         EmrMessageVo emrMessageVo = new EmrMessageVo();
         emrMessageVo.setDoctCd(consApply.getInvDocCd());
         List<EmrMessageVo> messageList = this.emrMessageService.selectEmrMessageByDoctCd(emrMessageVo);
         String messageKey = "message_key:" + consApply.getInvDocCd();
         if (messageList != null && !messageList.isEmpty()) {
            this.redisCache.deleteObject(messageKey);
            this.redisCache.setCacheList(messageKey, messageList);
         }
      }

   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public IndexSaveReturnVo saveTdCaConsApply(TdCaConsApplyVo tdCaConsApplyVo, HttpServletRequest request) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      tdCaConsApplyVo.setMrType("17");
      List<SysDictData> dataList = this.sysDictDataService.selectDictDataByType("s048");
      if (StringUtils.isNotEmpty(tdCaConsApplyVo.getApplyDocTitleCd())) {
         for(SysDictData sysDictData : dataList) {
            if (sysDictData.getDictValue().equals(tdCaConsApplyVo.getApplyDocTitleCd())) {
               tdCaConsApplyVo.setApplyDocTitleName(sysDictData.getDictLabel());
               break;
            }
         }
      }

      IndexSaveReturnVo returnVo = this.indexService.saveConsIndex(tdCaConsApplyVo, request);
      if (tdCaConsApplyVo.getId() == null) {
         tdCaConsApplyVo.setMrFileId(returnVo.getIndex().getId());
         this.insertTdCaConsApply(tdCaConsApplyVo);
      } else {
         this.updateTdCaConsApply(tdCaConsApplyVo);
      }

      if (tdCaConsApplyVo.getState().equals("02")) {
         this.saveConsMessageList(tdCaConsApplyVo);
      }

      return returnVo;
   }

   public void saveConsMessageList(TdCaConsApplyVo tdCaConsApplyVo) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      List<EmrMessage> insertList = new ArrayList();
      VisitinfoVo visitinfoVo = this.visitinfoService.selectVisitinfoByPatientId(tdCaConsApplyVo.getPatientId());
      Date nowDate = this.commonService.getDbSysdate();
      if (StringUtils.isEmpty(tdCaConsApplyVo.getInvDocCd())) {
         BasEmployeeVo basEmployeeVo = new BasEmployeeVo();
         basEmployeeVo.setDeptId(tdCaConsApplyVo.getConsDeptCd());
         basEmployeeVo.setConsType(tdCaConsApplyVo.getConsTypeCd());

         for(BasEmployee base : this.basEmployeeService.selectDoctors(basEmployeeVo)) {
            EmrMessage emrMessage = new EmrMessage(tdCaConsApplyVo);
            emrMessage.setId(SnowIdUtils.uniqueLong());
            emrMessage.setRoomNo(visitinfoVo.getRoomNo());
            emrMessage.setDoctCd(base.getEmplNumber());
            emrMessage.setDoctName(base.getEmplName());
            emrMessage.setMsgContent("【" + tdCaConsApplyVo.getApplyDocName() + " " + tdCaConsApplyVo.getApplyDocCd() + "  " + tdCaConsApplyVo.getApplyDeptName() + "】向本科室发起了会诊申请，请及时进行会诊，详情查看会诊申请单。");
            emrMessage.setCrePerCode(basEmployee.getEmplNumber());
            emrMessage.setCrePerName(basEmployee.getEmplName());
            emrMessage.setCreDate(nowDate);
            insertList.add(emrMessage);
         }
      } else {
         EmrMessage emrMessage = new EmrMessage(tdCaConsApplyVo);
         emrMessage.setId(SnowIdUtils.uniqueLong());
         emrMessage.setRoomNo(visitinfoVo.getRoomNo());
         emrMessage.setDoctCd(tdCaConsApplyVo.getInvDocCd());
         emrMessage.setDoctName(tdCaConsApplyVo.getInvDocName());
         emrMessage.setMsgContent("【" + tdCaConsApplyVo.getApplyDocName() + " " + tdCaConsApplyVo.getApplyDocCd() + "  " + tdCaConsApplyVo.getApplyDeptName() + "】向你发起了会诊申请，请及时进行会诊，详情查看会诊申请单。");
         emrMessage.setCrePerCode(basEmployee.getEmplNumber());
         emrMessage.setCrePerName(basEmployee.getEmplName());
         emrMessage.setCreDate(nowDate);
         insertList.add(emrMessage);
      }

      this.emrMessageService.insertEmrMessageList(insertList);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void signTdCaConsApply(TdCaConsApplyVo tdCaConsApplyVo, IndexSignVo indexSignVo, Date updateTime, HttpServletRequest request) throws Exception {
      Date signTime = updateTime == null ? this.commonService.getDbSysdate() : updateTime;
      this.saveTdCaConsApply(tdCaConsApplyVo, request);
      String syncStr = this.sysEmrConfigService.selectSysEmrConfigByKey("0055");
      if (StringUtils.isEmpty(tdCaConsApplyVo.getOrderNo()) && syncStr.equals("1")) {
         this.tdPaOrderItemService.insertOrderFromTdCaConsApply(tdCaConsApplyVo);
         TdCaConsApply tdCaConsApply = new TdCaConsApply();
         tdCaConsApply.setId(tdCaConsApplyVo.getId());
         tdCaConsApply.setOrderNo(tdCaConsApplyVo.getOrderNo());
         this.tdCaConsApplyMapper.updateTdCaConsApply(tdCaConsApply);
      }

      SysUser user = SecurityUtils.getLoginUser().getUser();
      BasEmployee employee = user.getBasEmployee();
      Long mrFileId = tdCaConsApplyVo.getMrFileId();
      List<ElemSignVo> signElemList = indexSignVo.getSignElemList();
      List<EmrSignRecord> signRecordList = new ArrayList(signElemList.size());
      List<EmrSignData> signDataList = new ArrayList(signElemList.size());

      for(ElemSignVo signElem : signElemList) {
         EmrSignData emrSignData = new EmrSignData();
         emrSignData.setId(SnowIdUtils.uniqueLong());
         emrSignData.setTypeCd("1");
         emrSignData.setCertType("1");
         emrSignData.setSignFileId(mrFileId.toString());
         emrSignData.setSignerCd(user.getUserName());
         emrSignData.setSignerName(user.getNickName());
         emrSignData.setOldText(indexSignVo.getOldText());
         emrSignData.setSignText(indexSignVo.getSignText());
         emrSignData.setTimeStamp(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, signTime));
         emrSignData.setSignTime(signTime);
         emrSignData.setCertInfo(indexSignVo.getCertInfo());
         emrSignData.setCertSn(indexSignVo.getCertSn());
         emrSignData.setIsValid("1");
         emrSignData.setCreDate(signTime);
         emrSignData.setCrePerCode(user.getUserName());
         emrSignData.setCrePerName(user.getNickName());
         signDataList.add(emrSignData);
         EmrSignRecord emrSignRecord = new EmrSignRecord();
         emrSignRecord.setId(SnowIdUtils.uniqueLong());
         emrSignRecord.setSignDataId(emrSignData.getId());
         emrSignRecord.setMrFileId(mrFileId);
         emrSignRecord.setDocLevelCd(employee.getTitleCode());
         emrSignRecord.setDocLevelName(employee.getTitleName());
         emrSignRecord.setDocCode(employee.getEmplNumber());
         emrSignRecord.setDocName(employee.getEmplName());
         emrSignRecord.setSignPos(String.valueOf(signElem.getElemId()));
         emrSignRecord.setSignTime(signTime);
         emrSignRecord.setSignCancFlag("0");
         emrSignRecord.setCrePerCode(user.getUserName());
         emrSignRecord.setCrePerName(user.getNickName());
         emrSignRecord.setCreDate(signTime);
         emrSignRecord.setSignImagePos(signElem.getSignImageName());
         emrSignRecord.setSignSname(signElem.getContElemName());
         signRecordList.add(emrSignRecord);
      }

      if (!signRecordList.isEmpty()) {
         this.emrSignRecordService.addList(signRecordList);
      }

      if (!signDataList.isEmpty()) {
         this.emrSignDataService.addList(signDataList);
      }

      if (tdCaConsApplyVo.getConsSignDate() != null) {
         this.impDoctorTempService.updateImpPatAndDoc(tdCaConsApplyVo.getPatientId(), employee.getEmplNumber(), "1");
      }

   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public IndexSaveReturnVo cancleSignTdCaConsApply(Index index, TdCaConsApplyVo tdCaConsApplyVo, HttpServletRequest request, AjaxResult ajaxResult) throws Exception {
      new EmrSignRecordVo();
      List<String> signElemNameList = tdCaConsApplyVo.getSignElemNameList();
      EmrSignRecordVo emrSignRecordVo = this.emrSignRecordService.cancleEmrSign(index, (SubfileIndex)null, signElemNameList);
      ajaxResult.put("emrSignRecord", emrSignRecordVo.getList());
      ajaxResult.put("mrState", emrSignRecordVo.getEmrState());
      if (emrSignRecordVo.getEmrState().equals("05") || emrSignRecordVo.getEmrState().equals("07") || emrSignRecordVo.getEmrState().equals("08")) {
         ajaxResult.put("switchRecension", true);
      }

      this.tdCaConsApplyMapper.updateStatusByIds(Arrays.asList(tdCaConsApplyVo.getId()), "03");
      tdCaConsApplyVo.setMrState(emrSignRecordVo.getEmrState());
      tdCaConsApplyVo.setPatientId(index.getPatientId());
      IndexSaveReturnVo returnVo = this.indexService.saveConsIndex(tdCaConsApplyVo, request);
      ajaxResult.put("consApplyMrState", tdCaConsApplyVo.getMrState());
      return returnVo;
   }

   public List selectUnFinishConsList(String patientId) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      List<BackLogVo> result = new ArrayList();
      List<String> titleList = new ArrayList();
      titleList.add("001");
      titleList.add("002");
      titleList.add("003");
      TdCaConsApplyVo tdCaConsApply = new TdCaConsApplyVo();
      tdCaConsApply.setDocCd(basEmployee.getEmplNumber());
      tdCaConsApply.setConsDeptCd(SecurityUtils.getLoginUser().getUser().getDept().getDeptCode());
      String docFlag = titleList.contains(basEmployee.getTitleCode()) ? "TRUE" : "FALSE";
      tdCaConsApply.setArchiaterFlag(docFlag);
      tdCaConsApply.setPatientId(patientId);
      List<TdCaConsApplyVo> list = this.tdCaConsApplyMapper.selectUnFinishConsList(tdCaConsApply);
      this.setRemainHours(list);

      for(TdCaConsApplyVo vo : list) {
         BackLogVo backLogVo = new BackLogVo();
         String consStr = "";
         if (StringUtils.isEmpty(vo.getInvDocCd())) {
            consStr = basEmployee.getEmplNumber().equals(vo.getApplyDocCd()) ? "【" + vo.getConsDeptName() + "】" : "本科室";
         } else {
            consStr = basEmployee.getEmplNumber().equals(vo.getApplyDocCd()) ? "【" + vo.getConsDeptName() + "】" + vo.getApplyDocTitleName() + "【" + vo.getInvDocName() + "】" : "您";
         }

         if (vo.getApplyDocCd().equals(basEmployee.getEmplNumber())) {
            vo.setConsQuality("申请会诊");
            backLogVo.setMessageStr("您在【" + vo.getApplyDeptName() + "】向" + consStr + "发出的【" + vo.getConsTypeName() + "】会诊申请，请查看");
         } else {
            vo.setConsQuality("受邀会诊");
            backLogVo.setMessageStr("【" + vo.getApplyDeptName() + "】" + vo.getApplyDocTitleName() + "【" + vo.getApplyDocName() + "】向" + consStr + "发出【" + vo.getConsTypeName() + "】会诊申请，请查看");
         }

         backLogVo.setType("7");
         backLogVo.setPatientId(vo.getPatientId());
         backLogVo.setPatientMainId(vo.getPatientMainId());
         backLogVo.setInpNo(vo.getInpNo());
         backLogVo.setTdCaConsApplyVo(vo);
         result.add(backLogVo);
      }

      return result;
   }

   private void setRemainHours(List list) {
      if (list != null && !list.isEmpty()) {
         for(TdCaConsApplyVo vo : list) {
            int hour = DateUtils.getDateHours(new Date(), vo.getApplyDate());
            int minutes = DateUtils.getDateMinutes(new Date(), vo.getApplyDate());
            if ("1".equals(vo.getConsTypeCd())) {
               if (hour < 1) {
                  vo.setRemainHours(minutes + "分钟");
               } else {
                  vo.setRemainHours(hour <= 24 ? 24 - hour + "小时" : "已超时" + (hour - 24) + "小时");
               }
            } else if (hour < 1) {
               vo.setRemainHours("已用时" + minutes + "分钟");
            } else {
               vo.setRemainHours("已用时" + hour + "小时");
            }
         }
      }

   }

   private void setExceedState(List list) {
      if (list != null && !list.isEmpty()) {
         for(TdCaConsApplyVo vo : list) {
            int hour = DateUtils.getDateHours(vo.getConsSignDate(), vo.getApplyDate());
            int minutes = DateUtils.getDateMinutes(vo.getConsSignDate(), vo.getApplyDate());
            if (hour < 1) {
               vo.setRemainHours(minutes + "分钟");
            } else {
               vo.setRemainHours(hour + "小时");
            }

            if (vo.getConsTypeCd().equals("1")) {
               vo.setExceedState(hour <= 24 ? "未超时" : "已超时");
            } else {
               vo.setExceedState("-");
            }
         }
      }

   }

   public String selectIsConsOrApply(Long id) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      String result = "0";
      TdCaConsApply tdCaConsApply = this.tdCaConsApplyMapper.selectTdCaConsApplyById(id);
      if (tdCaConsApply.getApplyDocCd().equals(basEmployee.getEmplNumber())) {
         result = "1";
      } else if (basEmployee.getEmplNumber().equals(tdCaConsApply.getConsDocCd())) {
         result = "2";
      }

      return result;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void acceptApplyUpdate(TdCaConsApplyVo tdCaConsApplyVo) throws Exception {
      TdCaConsApply tdCaConsApply = this.tdCaConsApplyMapper.selectTdCaConsApplyById(tdCaConsApplyVo.getId());
      VisitinfoPersonalVo personalVo = this.visitinfoService.selectVisitinfoPersonalById(tdCaConsApply.getPatientId());
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      BasEmployee basEmployee = sysUser.getBasEmployee();
      tdCaConsApplyVo.setState("03");
      tdCaConsApplyVo.setConsDocCd(basEmployee.getEmplNumber());
      tdCaConsApplyVo.setConsDocName(basEmployee.getEmplName());
      tdCaConsApplyVo.setInvDocCd(basEmployee.getEmplNumber());
      tdCaConsApplyVo.setInvDocName(basEmployee.getEmplName());
      tdCaConsApplyVo.setInvDocTitleCd(basEmployee.getTitleCode());
      tdCaConsApplyVo.setInvDocTitleName(basEmployee.getTitleName());
      tdCaConsApplyVo.setAltPerCode(basEmployee.getEmplNumber());
      tdCaConsApplyVo.setAltPerName(basEmployee.getEmplName());
      tdCaConsApplyVo.setAcceptDate(this.commonService.getDbSysdate());
      this.tdCaConsApplyMapper.updateTdCaConsApply(tdCaConsApplyVo);
      EmrMessage emrMessage = new EmrMessage();
      emrMessage.setBusId(tdCaConsApplyVo.getId().toString());
      emrMessage.setMsgState(1);
      emrMessage.setAltDate(this.commonService.getDbSysdate());
      this.emrMessageService.updateEmrMessageByBusId(emrMessage);
      List<EmrMessageVo> messageList = this.emrMessageService.selectEmrMessageList(new EmrMessageVo());
      String messageKey = "message_key:" + sysUser.getUserName();
      if (CollectionUtils.isNotEmpty(messageList)) {
         this.redisCache.deleteObject(messageKey);
         this.redisCache.setCacheList(messageKey, messageList);
      }

      if (!personalVo.getDeptCd().equals(sysUser.getDept().getDeptCode())) {
         ImpDoctorTemp impDoctorTemp = new ImpDoctorTemp();
         impDoctorTemp.setId(SnowIdUtils.uniqueLong());
         impDoctorTemp.setOrgCd(sysUser.getHospital().getOrgCode());
         impDoctorTemp.setPatientId(tdCaConsApply.getPatientId());
         impDoctorTemp.setPatientName(tdCaConsApply.getPatientName());
         impDoctorTemp.setBedNo(tdCaConsApply.getBedNo());
         impDoctorTemp.setDocCode(personalVo.getResDocCd());
         impDoctorTemp.setDocName(personalVo.getResDocName());
         impDoctorTemp.setImpDocCd(basEmployee.getEmplNumber());
         impDoctorTemp.setImpDocName(basEmployee.getEmplName());
         impDoctorTemp.setImpRange("0");
         impDoctorTemp.setImpBegTime(this.commonService.getDbSysdate());
         impDoctorTemp.setImpEndTime(DateUtils.addDays(this.commonService.getDbSysdate(), 2));
         impDoctorTemp.setValidFlag("1");
         impDoctorTemp.setImpType("1");
         impDoctorTemp.setImpAim("会诊医师授权");
         impDoctorTemp.setCrePerCode(basEmployee.getEmplNumber());
         impDoctorTemp.setCrePerName(basEmployee.getEmplName());
         this.impDoctorTempService.insertImpDoctor(impDoctorTemp);
      }

   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void returnApplyUpdate(TdCaConsApplyVo tdCaConsApplyVo) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      tdCaConsApplyVo.setState("01");
      tdCaConsApplyVo.setAltPerCode(sysUser.getUserName());
      tdCaConsApplyVo.setAltPerName(sysUser.getNickName());
      this.tdCaConsApplyMapper.updateToReturn(tdCaConsApplyVo);
      EmrMessage emrMessage = new EmrMessage();
      emrMessage.setBusId(tdCaConsApplyVo.getId().toString());
      emrMessage.setMsgState(0);
      emrMessage.setAltDate(this.commonService.getDbSysdate());
      this.emrMessageService.updateEmrMessageByBusId(emrMessage);
      List<EmrMessageVo> messageList = this.emrMessageService.selectEmrMessageList(new EmrMessageVo());
      String messageKey = "message_key:" + sysUser.getUserName();
      this.redisCache.deleteObject(messageKey);
      this.redisCache.setCacheList(messageKey, messageList);
      List<ImpDoctorTemp> impList = this.impDoctorTempService.selectTmpByPatAndDoc(tdCaConsApplyVo.getPatientId(), sysUser.getUserName(), (String)null);
      if (impList != null && !impList.isEmpty()) {
         this.impDoctorTempService.updateImpPatAndDoc(tdCaConsApplyVo.getPatientId(), sysUser.getUserName(), (String)null);
      }

   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public IndexSaveReturnVo updateCancelApply(TdCaConsApplyVo tdCaConsApplyVo, AjaxResult ajaxResult, HttpServletRequest request) throws Exception {
      tdCaConsApplyVo.setState("01");
      TdCaConsApply tdCaConsApply = this.tdCaConsApplyMapper.selectTdCaConsApplyById(tdCaConsApplyVo.getId());
      if (StringUtils.isNotEmpty(tdCaConsApply.getOrderNo())) {
         this.tdPaOrderService.deleteTdPaOrderByIds(Arrays.asList(tdCaConsApply.getOrderNo()), (List)null, (List)null, (List)null);
      }

      tdCaConsApplyVo.setOrderNo("");
      this.tdCaConsApplyMapper.updateTdCaConsApply(tdCaConsApplyVo);
      SysUser user = SecurityUtils.getLoginUser().getUser();
      EmrSignData emrSignDataParam = new EmrSignData();
      emrSignDataParam.setSignFileId(tdCaConsApplyVo.getMrFileId().toString());
      emrSignDataParam.setSignerCd(user.getBasEmployee().getEmplNumber());
      emrSignDataParam.setIsValid("1");
      List<EmrSignData> signDataList = this.emrSignDataService.selectEmrSignDataList(emrSignDataParam);
      List<EmrSignRecord> signRecordList = this.emrSignRecordMapper.selectEmrSignRecordListByMainFileId(tdCaConsApplyVo.getMrFileId().toString());

      for(EmrSignData emrSignData : signDataList) {
         EmrSignData emrSignDataTemp = new EmrSignData();
         emrSignDataTemp.setId(emrSignData.getId());
         emrSignDataTemp.setIsValid("0");
         this.emrSignDataService.updateEmrSignData(emrSignDataTemp);
         EmrSignRecord signRecordParam = new EmrSignRecord();
         signRecordParam.setSignDataId(emrSignData.getId());
         signRecordParam.setSignCancFlag("1");
         signRecordParam.setSignCancTime(DateUtils.getNowDate());
         signRecordParam.setAltPerCode(user.getBasEmployee().getEmplNumber());
         signRecordParam.setAltPerName(user.getBasEmployee().getEmplName());
         this.emrSignRecordMapper.updateBySignDataId(signRecordParam);
      }

      EmrSignRecordVo emrSignRecordVo = new EmrSignRecordVo();
      List<Long> signDataIdList = (List)signDataList.stream().map((t) -> t.getId()).collect(Collectors.toList());
      signRecordList = (List)signRecordList.stream().filter((t) -> signDataIdList.contains(t.getSignDataId())).collect(Collectors.toList());
      emrSignRecordVo.setList(signRecordList);
      tdCaConsApplyVo.setMrState("03");
      tdCaConsApplyVo.setPatientId(tdCaConsApply.getPatientId());
      IndexSaveReturnVo returnVo = this.indexService.saveConsIndex(tdCaConsApplyVo, request);
      ajaxResult.put("emrSignRecord", emrSignRecordVo);
      return returnVo;
   }

   public Map getConsElemValue(TdCaConsApply tdCaConsApply) throws Exception {
      Map<String, List<String>> map = new HashMap();
      List<String> codeList = new ArrayList();
      List<String> valueList = new ArrayList();
      Map<String, Object> objMap = JSONObject.parseObject(JSON.toJSONString(tdCaConsApply));
      String elemJson = this.sysEmrConfigService.selectSysEmrConfigByKey("0033");
      JSONObject jsonObject = JSONObject.parseObject(elemJson);

      for(String key : jsonObject.keySet()) {
         codeList.add(key);
         Object value = objMap.get(jsonObject.get(key) == null ? "" : jsonObject.get(key).toString());
         if (value != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String valueStr = "";

            try {
               valueStr = sdf.format(value);
            } catch (Exception var14) {
               valueStr = value.toString();
            }

            valueList.add(valueStr);
         } else {
            valueList.add("");
         }
      }

      map.put("codeList", codeList);
      map.put("valueList", valueList);
      return map;
   }

   public List selectConsFlowChart(Long id) throws Exception {
      TdCaConsApply tdCaConsApply = this.tdCaConsApplyMapper.selectTdCaConsApplyById(id);
      List<String> staffCodeList = new ArrayList(1);
      staffCodeList.add(tdCaConsApply.getCrePerCode());
      staffCodeList.add(tdCaConsApply.getApplyDocCd());
      if (StringUtils.isNotEmpty(tdCaConsApply.getConsDocCd())) {
         staffCodeList.add(tdCaConsApply.getConsDocCd());
      }

      if (tdCaConsApply.getConsSignDate() != null) {
         staffCodeList.add(tdCaConsApply.getConsDocCd());
      }

      List<BsStaffVo> staffVoList = this.bsStaffService.selectStaffList(staffCodeList);
      List<TdCaConsApplyVo> result = new ArrayList();
      TdCaConsApplyVo tdCaConsApplyVo = new TdCaConsApplyVo();
      tdCaConsApplyVo.setTimeNode(tdCaConsApply.getCreDate());
      tdCaConsApplyVo.setStateNode("开立申请单");
      List<BsStaffVo> staffVoList2 = CollectionUtils.isNotEmpty(staffVoList) ? (List)staffVoList.stream().filter((t) -> t.getStaffCode().equals(tdCaConsApply.getCrePerCode())).collect(Collectors.toList()) : null;
      String titleName = CollectionUtils.isNotEmpty(staffVoList2) ? "(" + ((BsStaffVo)staffVoList2.get(0)).getTitleName() + ")" : "";
      tdCaConsApplyVo.setDocName(tdCaConsApply.getCrePerName() + "(" + tdCaConsApply.getCrePerCode() + ")" + titleName);
      result.add(tdCaConsApplyVo);
      TdCaConsApplyVo tdCaConsApplyVo1 = new TdCaConsApplyVo();
      tdCaConsApplyVo1.setTimeNode(tdCaConsApply.getApplySignDate());
      tdCaConsApplyVo1.setStateNode("发送申请");
      staffVoList2 = CollectionUtils.isNotEmpty(staffVoList) ? (List)staffVoList.stream().filter((t) -> t.getStaffCode().equals(tdCaConsApply.getApplyDocCd())).collect(Collectors.toList()) : null;
      titleName = CollectionUtils.isNotEmpty(staffVoList2) ? "(" + ((BsStaffVo)staffVoList2.get(0)).getTitleName() + ")" : "";
      tdCaConsApplyVo1.setDocName(tdCaConsApply.getApplyDocName() + "(" + tdCaConsApply.getApplyDocCd() + ")" + titleName);
      result.add(tdCaConsApplyVo1);
      if (StringUtils.isNotEmpty(tdCaConsApply.getConsDocCd())) {
         TdCaConsApplyVo tdCaConsApplyVo2 = new TdCaConsApplyVo();
         tdCaConsApplyVo2.setTimeNode(tdCaConsApply.getAcceptDate());
         tdCaConsApplyVo2.setStateNode("会诊接收");
         staffVoList2 = CollectionUtils.isNotEmpty(staffVoList) ? (List)staffVoList.stream().filter((t) -> t.getStaffCode().equals(tdCaConsApply.getConsDocCd())).collect(Collectors.toList()) : null;
         titleName = CollectionUtils.isNotEmpty(staffVoList2) ? "(" + ((BsStaffVo)staffVoList2.get(0)).getTitleName() + ")" : "";
         tdCaConsApplyVo2.setDocName(tdCaConsApply.getConsDocName() + "(" + tdCaConsApply.getConsDocCd() + ")" + titleName);
         result.add(tdCaConsApplyVo2);
      }

      if (tdCaConsApply.getConsSignDate() != null) {
         TdCaConsApplyVo tdCaConsApplyVo2 = new TdCaConsApplyVo();
         tdCaConsApplyVo2.setTimeNode(tdCaConsApply.getConsSignDate());
         tdCaConsApplyVo2.setStateNode("完成会诊");
         staffVoList2 = CollectionUtils.isNotEmpty(staffVoList) ? (List)staffVoList.stream().filter((t) -> t.getStaffCode().equals(tdCaConsApply.getConsDocCd())).collect(Collectors.toList()) : null;
         titleName = CollectionUtils.isNotEmpty(staffVoList2) ? "(" + ((BsStaffVo)staffVoList2.get(0)).getTitleName() + ")" : "";
         tdCaConsApplyVo2.setDocName(tdCaConsApply.getConsDocName() + "(" + tdCaConsApply.getConsDocCd() + ")" + titleName);
         result.add(tdCaConsApplyVo2);
      }

      return result;
   }

   public List selectConsStatisList(TdCaConsApplyVo tdCaConsApplyVo) throws Exception {
      tdCaConsApplyVo.setState("04");
      List<TdCaConsApplyVo> list = this.tdCaConsApplyMapper.selectTdCaConsApplyStatisList(tdCaConsApplyVo);

      for(TdCaConsApplyVo vo : list) {
         int hour = DateUtils.getDateHours(vo.getConsSignDate(), vo.getApplyDate());
         if (hour < 1) {
            int minutes = DateUtils.getDateMinutes(vo.getConsSignDate(), vo.getApplyDate());
            vo.setRemainHours(minutes + "分钟");
         } else {
            vo.setRemainHours(hour + "小时");
         }

         if (vo.getConsTypeCd().equals("1")) {
            vo.setExceedState(hour <= 24 ? "未超时" : "已超时");
         } else {
            vo.setExceedState("-");
         }
      }

      return list;
   }

   public List selectListByIds(List idList) throws Exception {
      List<TdCaConsApply> list = new ArrayList(1);
      if (idList != null && !idList.isEmpty()) {
         this.tdCaConsApplyMapper.selectListByIds(idList);
      }

      return list;
   }

   public void updateStatusByIds(List idList, String state) throws Exception {
      if (idList != null && !idList.isEmpty() && StringUtils.isNotBlank(state)) {
         this.tdCaConsApplyMapper.updateStatusByIds(idList, state);
      }

   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void updateStateById(TdCaConsApply tdCaConsApply) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      BasEmployee basEmployee = sysUser.getBasEmployee();
      if (tdCaConsApply != null && tdCaConsApply.getInvDocCd() != null && tdCaConsApply.getInvDocCd().equals(basEmployee.getEmplNumber())) {
         TdCaConsApplyVo tdCaConsApplyVo = new TdCaConsApplyVo();
         tdCaConsApplyVo.setId(tdCaConsApply.getId());
         tdCaConsApplyVo.setState("03");
         tdCaConsApplyVo.setConsDocCd(basEmployee.getEmplNumber());
         tdCaConsApplyVo.setConsDocName(basEmployee.getEmplName());
         tdCaConsApplyVo.setInvDocCd(basEmployee.getEmplNumber());
         tdCaConsApplyVo.setInvDocName(basEmployee.getEmplName());
         tdCaConsApplyVo.setInvDocTitleCd(basEmployee.getTitleCode());
         tdCaConsApplyVo.setInvDocTitleName(basEmployee.getTitleName());
         tdCaConsApplyVo.setAltPerCode(basEmployee.getEmplNumber());
         tdCaConsApplyVo.setAltPerName(basEmployee.getEmplName());
         tdCaConsApplyVo.setAcceptDate(this.commonService.getDbSysdate());
         this.tdCaConsApplyMapper.updateTdCaConsApply(tdCaConsApplyVo);
      }

   }
}
