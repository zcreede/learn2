package com.emr.project.qc.controller;

import com.emr.common.utils.IPAddressUtil;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.redis.RedisCache;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.common.service.ICommonService;
import com.emr.project.pat.domain.vo.BackLogVo;
import com.emr.project.qc.domain.EmrQcRecord;
import com.emr.project.qc.domain.vo.EmrQcFlowScoreVo;
import com.emr.project.qc.domain.vo.EmrQcRecordVo;
import com.emr.project.qc.domain.vo.RunTimeQcCheckVo;
import com.emr.project.qc.domain.vo.RunTimeQcCheckedVo;
import com.emr.project.qc.service.IEmrQcFlowService;
import com.emr.project.qc.service.IEmrQcListService;
import com.emr.project.qc.service.IEmrQcRecordService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.service.ISysEmrConfigService;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping({"/system/record"})
public class EmrQcRecordController extends BaseController {
   @Autowired
   private IEmrQcRecordService emrQcRecordService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private IEmrQcFlowService emrQcFlowService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private RedisCache redisCache;
   @Autowired
   private IEmrQcListService emrQcListService;

   @PreAuthorize("@ss.hasPermi('system:record:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(EmrQcRecord emrQcRecord) {
      this.startPage();
      List<EmrQcRecord> list = this.emrQcRecordService.selectEmrQcRecordList(emrQcRecord);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('system:record:export')")
   @Log(
      title = "病历质控记录",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(EmrQcRecord emrQcRecord) {
      List<EmrQcRecord> list = this.emrQcRecordService.selectEmrQcRecordList(emrQcRecord);
      ExcelUtil<EmrQcRecord> util = new ExcelUtil(EmrQcRecord.class);
      return util.exportExcel(list, "病历质控记录数据");
   }

   @PreAuthorize("@ss.hasPermi('system:record:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.emrQcRecordService.selectEmrQcRecordById(id));
   }

   @PreAuthorize("@ss.hasPermi('system:record:add')")
   @Log(
      title = "病历质控记录",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody EmrQcRecord emrQcRecord) {
      return this.toAjax(this.emrQcRecordService.insertEmrQcRecord(emrQcRecord));
   }

   @PreAuthorize("@ss.hasPermi('system:record:edit')")
   @Log(
      title = "病历质控记录",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody EmrQcRecord emrQcRecord) {
      return this.toAjax(this.emrQcRecordService.updateEmrQcRecord(emrQcRecord));
   }

   @PreAuthorize("@ss.hasPermi('system:record:remove')")
   @Log(
      title = "病历质控记录",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.emrQcRecordService.deleteEmrQcRecordByIds(ids));
   }

   @PreAuthorize("@ss.hasPermi('qc:rt:check')")
   @PostMapping({"/randomCheck"})
   public AjaxResult randomCheck(@RequestBody RunTimeQcCheckVo runTimeQcCheckVo) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (flag && runTimeQcCheckVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(runTimeQcCheckVo.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("抽查患者id不能为空");
         }

         EmrQcRecord emrQcRecord = flag ? this.emrQcRecordService.selectEmrQcRecordByPatientSection(runTimeQcCheckVo.getPatientId(), "03", runTimeQcCheckVo.getQcBillNo()) : null;
         if (flag && emrQcRecord != null) {
            BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
            if (!basEmployee.getEmplNumber().equals(emrQcRecord.getQcdoctCd())) {
               flag = false;
               ajaxResult = AjaxResult.error("当前患者的抽查质控状态是：质控中，抽查医师是：" + emrQcRecord.getQcdoctName() + "，您不能重复抽查此患者病历");
            }
         }

         if (flag && emrQcRecord != null && (emrQcRecord.getState().equals("0") || emrQcRecord.getState().equals("3") || emrQcRecord.getState().equals("2"))) {
            flag = false;
            ajaxResult = AjaxResult.success((Object)emrQcRecord);
         }

         if (flag) {
            EmrQcRecord res = emrQcRecord != null ? emrQcRecord : this.emrQcRecordService.randomCheck(runTimeQcCheckVo);
            ajaxResult = AjaxResult.success((Object)res);
         }
      } catch (Exception e) {
         this.log.error("抽查操作出现异常, ", e);
         ajaxResult = AjaxResult.error("抽查操作出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:rt:dept,qc:rt:term')")
   @PostMapping({"/qcCheck"})
   public AjaxResult qcCheck(@RequestBody EmrQcRecord param) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;
      String patSectionKey = "emr_qc_pat_section:" + param.getPatientId() + param.getQcSection();
      boolean patSectionFlag = false;

      try {
         if (flag && param == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(param.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag && StringUtils.isBlank(param.getQcSection())) {
            flag = false;
            ajaxResult = AjaxResult.error("质控环节不能为空");
         }

         EmrQcRecord emrQcRecord = flag ? this.emrQcRecordService.selectEmrQcRecordByPatientSection(param.getPatientId(), param.getQcSection(), (Long)null) : null;
         if (flag && emrQcRecord != null && (emrQcRecord.getState().equals("3") || emrQcRecord.getState().equals("2"))) {
            flag = false;
            ajaxResult = AjaxResult.error("当前患者的质控状态是：" + emrQcRecord.getStateName() + "，您不能重复质控此患者病历");
         }

         String patSection = flag ? (String)this.redisCache.getCacheObject(patSectionKey) : null;
         if (flag && StringUtils.isNotBlank(patSection) && patSection.equals("1")) {
            flag = false;
            ajaxResult = AjaxResult.error("当前患者当前环节的质控正在处理，您不能重复操作，请稍后再试");
         }

         if (flag) {
            this.redisCache.setCacheObject(patSectionKey, "1", 5, TimeUnit.MINUTES);
            patSectionFlag = true;
            emrQcRecord = this.emrQcFlowService.deptTermQc(emrQcRecord, param.getPatientId(), param.getQcSection());
            ajaxResult = AjaxResult.success((Object)emrQcRecord);
            this.redisCache.deleteObject(patSectionKey);
         }
      } catch (Exception e) {
         this.log.error("抽查操作出现异常, ", e);
         ajaxResult = AjaxResult.error("抽查操作出现异常");
         if (patSectionFlag) {
            this.redisCache.deleteObject(patSectionKey);
         }
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:rt:dept,qc:rt:term')")
   @PostMapping({"/qcCheckLook"})
   public AjaxResult qcCheckLook(@RequestBody EmrQcRecord param) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (flag && param == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(param.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag && StringUtils.isBlank(param.getQcSection())) {
            flag = false;
            ajaxResult = AjaxResult.error("质控环节不能为空");
         }

         EmrQcRecord emrQcRecord = flag ? this.emrQcRecordService.selectEmrQcRecordByPatientSection(param.getPatientId(), param.getQcSection(), (Long)null) : null;
         if (emrQcRecord == null) {
            flag = false;
            ajaxResult = AjaxResult.error("未查询到质控记录");
         }

         if (flag) {
            ajaxResult = AjaxResult.success((Object)emrQcRecord);
         }
      } catch (Exception e) {
         this.log.error("查看操作出现异常, ", e);
         ajaxResult = AjaxResult.error("查看操作出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:rt:checked')")
   @PostMapping({"/randomVerify"})
   public AjaxResult randomVerify(@RequestBody RunTimeQcCheckedVo runTimeQcCheckedVo) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (flag && runTimeQcCheckedVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(runTimeQcCheckedVo.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("核验患者id不能为空");
         }

         if (flag && runTimeQcCheckedVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("抽查id不能为空");
         }

         if (flag) {
            this.emrQcRecordService.randomVerify(runTimeQcCheckedVo);
            ajaxResult = AjaxResult.success((Object)runTimeQcCheckedVo.getId());
         }
      } catch (Exception e) {
         this.log.error("抽查操作出现异常, ", e);
         ajaxResult = AjaxResult.error("抽查操作出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:rt:checkpatientList')")
   @GetMapping({"/randomCheckConfig"})
   public AjaxResult getInhosDayLongCostSumHigh() {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         String inhosDayLong = this.sysEmrConfigService.selectSysEmrConfigByKey("0018");
         String costSumHigh = this.sysEmrConfigService.selectSysEmrConfigByKey("0019");
         ajaxResult.put("dayNum", inhosDayLong);
         ajaxResult.put("costSum", Double.parseDouble(costSumHigh) / (double)10000.0F);
         Boolean qcDept = this.commonService.isQCDept();
         ajaxResult.put("isQCDept", qcDept);
      } catch (Exception e) {
         this.log.error("获取抽查查询条件，超长住院和超高花费默认值出现异常：", e);
         ajaxResult = AjaxResult.error("获取抽查查询条件，超长住院和超高花费默认值出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:rt:getQcListFinishFlag,qc:rt:check,qc:rt:checked,qc:rt:dept,qc:rt:term')")
   @GetMapping({"/getQcListFinishFlag"})
   public AjaxResult getQcListFinishFlag(Long id) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (flag && id == null) {
            flag = false;
            ajaxResult = AjaxResult.error("当前抽查id不能为空");
         }

         EmrQcRecord emrQcRecord = flag ? this.emrQcRecordService.selectEmrQcRecordById(id) : null;
         if (flag && emrQcRecord == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有此抽查记录");
         }

         if (flag) {
            ajaxResult = this.emrQcListService.getClickFlagVisibleFLag(emrQcRecord);
         }
      } catch (Exception e) {
         this.log.error("抽查完成出现异常, ", e);
         ajaxResult = AjaxResult.error("抽查完成出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:rt:randomCheckFinish,qc:rt:check,qc:rt:checked')")
   @Log(
      title = "抽查完成",
      businessType = BusinessType.UPDATE
   )
   @PostMapping({"/randomCheckFinish"})
   public AjaxResult randomCheckFinish(@RequestBody EmrQcRecordVo emrQcRecord, @RequestBody EmrQcFlowScoreVo emrQcFlowScoreVo, HttpServletRequest request) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (flag && emrQcRecord.getRecordId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("当前抽查id不能为空");
         }

         if (flag && emrQcRecord.getLimitHours() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("修改时限不能为空");
         }

         EmrQcRecord emrQcRecordDb = flag ? this.emrQcRecordService.selectEmrQcRecordById(emrQcRecord.getRecordId()) : null;
         if (flag && emrQcRecordDb == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有此抽查记录");
         }

         if (flag && (emrQcFlowScoreVo == null || emrQcFlowScoreVo.getTotalScore() == null)) {
            flag = false;
            ajaxResult = AjaxResult.error("评分信息不能为空");
         }

         if (flag) {
            emrQcRecordDb.setLimitHours(emrQcRecord.getLimitHours());
            String ip = IPAddressUtil.getIPAddress(request);
            EmrQcRecord res = this.emrQcRecordService.randomCheckFinish(emrQcRecordDb, emrQcFlowScoreVo, ip);
            ajaxResult = AjaxResult.success("操作成功", res);
         }
      } catch (Exception e) {
         this.log.error("抽查完成出现异常, ", e);
         this.log.warn("质控保存-抽查质控保存出现异常");
         ajaxResult = AjaxResult.error();
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:rt:randomVerifyFinish')")
   @GetMapping({"/randomVerifyFinish"})
   public AjaxResult randomVerifyFinish(Long id) {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         if (id == null) {
            ajaxResult = AjaxResult.error("当前抽查id不能为空");
         }
      } catch (Exception e) {
         this.log.error("抽查完成出现异常, ", e);
         ajaxResult = AjaxResult.error();
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:rt:check,qc:rt:checked')")
   @GetMapping({"/rtQcCheckedPatientOne"})
   public AjaxResult runTimeQcCheckedPatientOne(String patientId, Long id) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (com.emr.common.utils.StringUtils.isBlank(patientId)) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (id == null) {
            flag = false;
            ajaxResult = AjaxResult.error("抽查单id不能为空");
         }

         if (flag) {
            RunTimeQcCheckedVo runTimeQcCheckVoParam = new RunTimeQcCheckedVo();
            runTimeQcCheckVoParam.setPatientId(patientId);
            String inhosDayLong = this.sysEmrConfigService.selectSysEmrConfigByKey("0018");
            String costSumHigh = this.sysEmrConfigService.selectSysEmrConfigByKey("0019");
            runTimeQcCheckVoParam.setCostSum(Double.valueOf(costSumHigh));
            runTimeQcCheckVoParam.setDayNum(Integer.parseInt(inhosDayLong));
            runTimeQcCheckVoParam.setQcBillNo(id);
            RunTimeQcCheckedVo res = this.emrQcRecordService.selectRunTimeQcCheckedOne(runTimeQcCheckVoParam);
            ajaxResult = AjaxResult.success((Object)res);
         }
      } catch (Exception e) {
         this.log.error("患者病历抽查样本查询单个患者查询出现异常：", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:home:list')")
   @GetMapping({"/qcReturn"})
   public TableDataInfo qcReturn() {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<BackLogVo> list = this.emrQcRecordService.selectQcReturnList((String)null);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("质控退回-待办事项出现异常：", e);
         tableDataInfo = new TableDataInfo(500, "质控退回-待办事项出现异常");
      }

      return tableDataInfo;
   }
}
