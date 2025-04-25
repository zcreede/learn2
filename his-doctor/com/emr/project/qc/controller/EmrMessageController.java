package com.emr.project.qc.controller;

import com.alibaba.fastjson.JSONObject;
import com.emr.common.constant.CommonConstants;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.redis.RedisCache;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.common.mapper.CommonMapper;
import com.emr.project.docOrder.service.ITdPaOrderMessageService;
import com.emr.project.dr.service.ITdCaConsApplyService;
import com.emr.project.pat.domain.vo.ExamItemVo;
import com.emr.project.pat.domain.vo.TestAlertReportVo;
import com.emr.project.pat.domain.vo.TestExamAlertMsgResVo;
import com.emr.project.pat.domain.vo.TestExamAlertMsgVo;
import com.emr.project.pat.service.IExamItemService;
import com.emr.project.pat.service.ITestAlertReportService;
import com.emr.project.qc.domain.EmrMessage;
import com.emr.project.qc.domain.PopTime;
import com.emr.project.qc.domain.vo.EmrMessageVo;
import com.emr.project.qc.service.IEmrMessageService;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysEmrConfigService;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/qc/message"})
public class EmrMessageController extends BaseController {
   @Autowired
   private IEmrMessageService emrMessageService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private RedisCache redisCache;
   @Autowired
   private ITestAlertReportService testAlertReportService;
   @Autowired
   private IExamItemService examItemService;
   @Autowired
   private ITdPaOrderMessageService tdPaOrderMessageService;
   @Autowired
   private ITdCaConsApplyService tdCaConsApplyService;
   @Autowired
   private CommonMapper commonMapper;

   @PreAuthorize("@ss.hasPermi('qc:message:list')")
   @GetMapping({"/readList"})
   public AjaxResult readList(EmrMessageVo emrMessageVo) {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      String messageKey = "message_key:" + sysUser.getUserName();

      try {
         if (emrMessageVo.getEndTime() != null && emrMessageVo.getBeginTime() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(emrMessageVo.getEndTime());
            c.add(5, 1);
            emrMessageVo.setEndTime(c.getTime());
         }

         List<EmrMessageVo> list = this.redisCache.getCacheList(messageKey);
         String dictStr = this.sysEmrConfigService.selectSysEmrConfigByKey("0004");
         List<EmrMessageVo> flawList = new ArrayList();
         List<EmrMessageVo> returnList = new ArrayList();
         List<EmrMessageVo> consList = new ArrayList();
         List<EmrMessageVo> testExamAlertList = new ArrayList(1);
         if (list != null && !list.isEmpty()) {
            list = (List)list.stream().filter((s) -> s.getMsgState().equals(1)).collect(Collectors.toList());
            flawList = (List)list.stream().filter((s) -> s.getMsgType().equals(CommonConstants.EMR_QC_MESSAGE.MSG_TYPE_1)).collect(Collectors.toList());
            returnList = (List)list.stream().filter((s) -> s.getMsgType().equals(CommonConstants.EMR_QC_MESSAGE.MSG_TYPE_2)).collect(Collectors.toList());
            consList = (List)list.stream().filter((s) -> s.getMsgType().equals(CommonConstants.EMR_QC_MESSAGE.MSG_TYPE_3) && s.getDeptCd().equals(sysUser.getDept().getDeptCode())).collect(Collectors.toList());
            testExamAlertList = (List)list.stream().filter((t) -> t.getMsgType().equals(CommonConstants.EMR_QC_MESSAGE.MSG_TYPE_4)).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(flawList)) {
               flawList.sort(Comparator.comparing(EmrMessage::getAltDate, Comparator.nullsFirst(Date::compareTo)).reversed());
            }

            if (CollectionUtils.isNotEmpty(returnList)) {
               returnList.sort(Comparator.comparing(EmrMessage::getAltDate, Comparator.nullsFirst(Date::compareTo)).reversed());
            }
         }

         ajaxResult.put("testExamList", testExamAlertList);
         ajaxResult.put("flawList", flawList);
         ajaxResult.put("returnList", returnList);
         ajaxResult.put("consList", consList);
         ajaxResult.put("hospital", dictStr);
      } catch (Exception e) {
         this.log.error("查询已读消息提醒列表异常", e);
         ajaxResult = AjaxResult.error("查询已读消息提醒列表异常");
      }

      return ajaxResult;
   }

   @GetMapping({"/unReadList"})
   public AjaxResult unReadList(EmrMessageVo emrMessageVo) {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      String messageKey = "message_key:" + sysUser.getUserName();
      String lisPacsAlertKey = "lis_pacs_alert_key:" + sysUser.getUserName();
      (new StringBuilder()).append("message_key:").append(sysUser.getDept().getDeptCode()).toString();
      (new StringBuilder()).append("lis_pacs_alert_key:").append(sysUser.getDept().getDeptCode()).toString();

      try {
         List<EmrMessageVo> list = this.redisCache.getCacheList(messageKey);
         String dictStr = this.sysEmrConfigService.selectSysEmrConfigByKey("0004");
         List<EmrMessageVo> flawList = new ArrayList(1);
         List<EmrMessageVo> returnList = new ArrayList(1);
         List<EmrMessageVo> consList = new ArrayList(1);
         List<EmrMessageVo> testExamAlertList = new ArrayList(1);
         List<EmrMessageVo> testExamAlertListNew = new ArrayList(1);
         List<TestExamAlertMsgVo> testExamAlertMsgListNew = new ArrayList(1);
         List<String> testAlertIdList = new ArrayList(1);
         List<String> examAlertIdList = new ArrayList(1);
         if (list != null && !list.isEmpty()) {
            List<EmrMessageVo> listUnRead = (List)list.stream().filter((s) -> s.getMsgState().equals(0)).collect(Collectors.toList());
            flawList = (List)listUnRead.stream().filter((s) -> s.getMsgType().equals(CommonConstants.EMR_QC_MESSAGE.MSG_TYPE_1)).collect(Collectors.toList());
            returnList = (List)listUnRead.stream().filter((s) -> s.getMsgType().equals(CommonConstants.EMR_QC_MESSAGE.MSG_TYPE_2)).collect(Collectors.toList());
            consList = (List)listUnRead.stream().filter((s) -> s.getMsgType().equals(CommonConstants.EMR_QC_MESSAGE.MSG_TYPE_3) && s.getDeptCd().equals(sysUser.getDept().getDeptCode())).collect(Collectors.toList());
            flawList.sort(Comparator.comparing(EmrMessage::getCreDate).reversed());
            returnList.sort(Comparator.comparing(EmrMessage::getCreDate).reversed());
            testExamAlertList = (List)listUnRead.stream().filter((t) -> t.getMsgType().equals(CommonConstants.EMR_QC_MESSAGE.MSG_TYPE_4)).collect(Collectors.toList());
            testAlertIdList = (List)listUnRead.stream().filter((t) -> t.getBusType().equals(CommonConstants.EMR_QC_MESSAGE.BUS_TYPE_5)).map((t) -> t.getBusId()).collect(Collectors.toList());
            examAlertIdList = (List)listUnRead.stream().filter((t) -> t.getBusType().equals(CommonConstants.EMR_QC_MESSAGE.BUS_TYPE_6)).map((t) -> t.getBusId()).collect(Collectors.toList());
         }

         String lisPacsFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0059");
         new ArrayList(1);
         if (StringUtils.isNotBlank(lisPacsFlag) && lisPacsFlag.equals("1")) {
            TestAlertReportVo testParam = new TestAlertReportVo();
            testParam.setResultalertidList(testAlertIdList);
            testParam.setReqDeptno(SecurityUtils.getLoginUser().getUser().getDept().getDeptCode());
            TestExamAlertMsgResVo testAlertMsgVo = this.testAlertReportService.selectTestAlertMsg(testParam);
            if (CollectionUtils.isNotEmpty(testAlertMsgVo.getTestAlertList())) {
               testExamAlertListNew.addAll(testAlertMsgVo.getMsgList());
            }

            if (CollectionUtils.isNotEmpty(testAlertMsgVo.getAlertMsgVoList())) {
               testExamAlertMsgListNew.addAll(testAlertMsgVo.getAlertMsgVoList());
            }

            ExamItemVo examParam = new ExamItemVo();
            examParam.setExamIdList(examAlertIdList);
            examParam.setAppDeptCd(SecurityUtils.getLoginUser().getUser().getDept().getDeptCode());
            TestExamAlertMsgResVo examAlertMsgVo = this.examItemService.selectExamAlertMsg(examParam);
            if (CollectionUtils.isNotEmpty(examAlertMsgVo.getExamAlertList())) {
               testExamAlertListNew.addAll(examAlertMsgVo.getMsgList());
            }

            if (CollectionUtils.isNotEmpty(examAlertMsgVo.getAlertMsgVoList())) {
               testExamAlertMsgListNew.addAll(examAlertMsgVo.getAlertMsgVoList());
            }

            List testExamAlertMsgList = this.redisCache.getCacheList(lisPacsAlertKey);
            if (CollectionUtils.isEmpty((Collection)testExamAlertMsgList)) {
               testExamAlertMsgList = new ArrayList(1);
            }

            if (CollectionUtils.isNotEmpty(testExamAlertMsgListNew)) {
               this.redisCache.deleteObject(lisPacsAlertKey);
               ajaxResult.put("testExamAlertFlag", true);
               testExamAlertMsgList = testExamAlertMsgListNew;
               this.redisCache.setCacheList(lisPacsAlertKey, testExamAlertMsgListNew);
            }

            if (CollectionUtils.isEmpty(testExamAlertMsgListNew)) {
               this.redisCache.deleteObject(lisPacsAlertKey);
               testExamAlertMsgList = testExamAlertMsgListNew;
            }

            ajaxResult.put("testExamAlertList", testExamAlertMsgList);
         }

         if (CollectionUtils.isNotEmpty(testExamAlertListNew)) {
            list.addAll(testExamAlertListNew);
            this.redisCache.deleteObject(messageKey);
            this.redisCache.setCacheList(messageKey, list);
            testExamAlertList.addAll(testExamAlertListNew);
         }

         List<EmrMessageVo> testExamAlertMsgUnReadList = (List)list.stream().filter((t) -> t.getMsgState().equals(0) && t.getBusType().equals(CommonConstants.EMR_QC_MESSAGE.BUS_TYPE_5)).collect(Collectors.toList());
         if (CollectionUtils.isNotEmpty(testExamAlertMsgUnReadList)) {
            List<String> testExamAlertIdList = (List<String>)(CollectionUtils.isNotEmpty(testExamAlertMsgListNew) ? (List)testExamAlertMsgListNew.stream().map((t) -> t.getId().toString()).collect(Collectors.toList()) : new ArrayList(1));
            testExamAlertMsgUnReadList = (List)testExamAlertMsgUnReadList.stream().filter((t) -> StringUtils.isNotBlank(t.getBusId()) && testExamAlertIdList.contains(t.getBusId())).collect(Collectors.toList());
            list = (List)list.stream().filter((t) -> !t.getMsgState().equals(0) || !t.getBusType().equals(CommonConstants.EMR_QC_MESSAGE.BUS_TYPE_5)).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(testExamAlertMsgUnReadList)) {
               list.addAll(testExamAlertMsgUnReadList);
            }

            this.redisCache.deleteObject(messageKey);
            if (CollectionUtils.isNotEmpty(list)) {
               this.redisCache.setCacheList(messageKey, list);
               testExamAlertList = testExamAlertMsgUnReadList;
            } else {
               testExamAlertList = new ArrayList(1);
            }
         }

         boolean bolDoct = testExamAlertList.stream().anyMatch((testExamAlert) -> sysUser.getUserName().equals(testExamAlert.getDoctCd()));
         if (bolDoct) {
            bolDoct = this.queryPop();
         }

         ajaxResult.put("bolDoct", bolDoct);
         ajaxResult.put("testExamList", testExamAlertList);
         ajaxResult.put("flawList", flawList);
         ajaxResult.put("returnList", returnList);
         ajaxResult.put("consList", consList);
         ajaxResult.put("hospital", dictStr);
         ajaxResult.put("returnMessage", new ArrayList(1));
      } catch (Exception e) {
         this.log.error("查询未读消息提醒列表异常", e);
         ajaxResult = AjaxResult.error("查询未读消息提醒列表异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:message:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.emrMessageService.selectEmrMessageById(id));
   }

   @PreAuthorize("@ss.hasPermi('qc:message:add')")
   @Log(
      title = "【请填写功能名称】",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody EmrMessage emrMessage) {
      return this.toAjax(this.emrMessageService.insertEmrMessage(emrMessage));
   }

   @PreAuthorize("@ss.hasPermi('qc:message:edit')")
   @Log(
      title = "【请填写功能名称】",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody EmrMessage emrMessage) {
      AjaxResult ajaxResult = AjaxResult.success("修改成功");

      try {
         ajaxResult = this.toAjax(this.emrMessageService.updateEmrMessage(emrMessage));
      } catch (Exception var4) {
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:message:remove')")
   @Log(
      title = "【请填写功能名称】",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      List<Long> idList = Arrays.asList(ids);
      return this.toAjax(this.emrMessageService.deleteEmrMessageByIds(idList));
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:message:edit,qc:message:list')")
   @Log(
      title = "消息提醒",
      businessType = BusinessType.UPDATE
   )
   @PutMapping({"/editState"})
   public AjaxResult editState(@RequestBody EmrMessage emrMessage) {
      AjaxResult ajaxResult = AjaxResult.success("修改成功");
      boolean flag = true;

      try {
         if (emrMessage == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && emrMessage.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("消息id不能为空");
         }

         if (flag) {
            emrMessage.setMsgState(1);
            this.emrMessageService.updateEmrMessage(emrMessage);
            SysUser user = SecurityUtils.getLoginUser().getUser();
            List<EmrMessageVo> messageList = this.emrMessageService.selectEmrMessageList(new EmrMessageVo());
            String messageKey = "message_key:" + user.getUserName();
            this.redisCache.deleteObject(messageKey);
            this.redisCache.setCacheList(messageKey, messageList);
         }
      } catch (Exception e) {
         this.log.error("修改消息已读状态异常", e);
         ajaxResult = AjaxResult.error("修改消息已读状态异常");
      }

      return ajaxResult;
   }

   public boolean queryPop() throws Exception {
      boolean pop = false;
      String jsonStr = this.sysEmrConfigService.selectSysEmrConfigByKey("0100");
      PopTime resp = (PopTime)JSONObject.parseObject(jsonStr, PopTime.class);
      String currDateStr = this.commonMapper.getSysdate();
      Date currDate = DateUtils.parseDate(currDateStr, new String[]{"yyyy-MM-dd HH:mm:ss.S"});
      LocalTime localDateTime = currDate.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
      LocalTime amStart = LocalTime.parse(resp.getAmBeginTime().trim(), formatter);
      LocalTime amEnd = LocalTime.parse(resp.getAmEndTime().trim(), formatter);
      LocalTime pmStart = LocalTime.parse(resp.getPmBeginTime().trim(), formatter);
      LocalTime pmEnd = LocalTime.parse(resp.getPmEndTime().trim(), formatter);
      pop = localDateTime.isAfter(amStart) && localDateTime.isBefore(amEnd) || localDateTime.isAfter(pmStart) && localDateTime.isBefore(pmEnd);
      return pop;
   }
}
