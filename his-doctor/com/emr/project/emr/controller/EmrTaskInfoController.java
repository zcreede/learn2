package com.emr.project.emr.controller;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.emr.domain.EmrSignData;
import com.emr.project.emr.domain.EmrTaskInfo;
import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.SubfileIndex;
import com.emr.project.emr.domain.vo.EmrTaskInfoVo;
import com.emr.project.emr.service.IEmrTaskInfoService;
import com.emr.project.emr.service.IIndexService;
import com.emr.project.emr.service.ISubfileIndexService;
import com.emr.project.operation.domain.Medicalinformation;
import com.emr.project.operation.service.IMedicalinfoService;
import com.emr.project.pat.domain.vo.BackLogVo;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.qc.domain.EmrQcFlow;
import com.emr.project.qc.service.IEmrQcFlowService;
import com.emr.project.qc.service.IQcAgiRuleService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysEmrConfigService;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
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
@RequestMapping({"/emr/info"})
public class EmrTaskInfoController extends BaseController {
   @Autowired
   private IEmrTaskInfoService emrTaskInfoService;
   @Autowired
   private IQcAgiRuleService qcAgiRuleService;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private IIndexService indexService;
   @Autowired
   private ISubfileIndexService subfileIndexService;
   @Autowired
   private IMedicalinfoService medicalinfoService;
   @Autowired
   private IEmrQcFlowService emrQcFlowService;

   @PreAuthorize("@ss.hasPermi('emr:home:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(EmrTaskInfo emrTaskInfo) {
      this.startPage();
      List<EmrTaskInfo> list = this.emrTaskInfoService.selectEmrTaskInfoList(emrTaskInfo);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('emr:info:export')")
   @Log(
      title = "病历任务流程",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(EmrTaskInfo emrTaskInfo) {
      List<EmrTaskInfo> list = this.emrTaskInfoService.selectEmrTaskInfoList(emrTaskInfo);
      ExcelUtil<EmrTaskInfo> util = new ExcelUtil(EmrTaskInfo.class);
      return util.exportExcel(list, "病历任务流程数据");
   }

   @PreAuthorize("@ss.hasPermi('emr:info:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.emrTaskInfoService.selectEmrTaskInfoById(id));
   }

   @PreAuthorize("@ss.hasPermi('emr:info:add')")
   @Log(
      title = "病历任务流程",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody EmrTaskInfo emrTaskInfo) {
      return this.toAjax(this.emrTaskInfoService.insertEmrTaskInfo(emrTaskInfo));
   }

   @PreAuthorize("@ss.hasPermi('emr:home:list')")
   @Log(
      title = "病历任务流程",
      businessType = BusinessType.INSERT
   )
   @PostMapping({"/batchAdd"})
   public AjaxResult batchAdd(@RequestBody EmrTaskInfoVo emrTaskInfoVo) throws Exception {
      AjaxResult ajaxResult = AjaxResult.success("发送成功");
      boolean flag = true;

      try {
         if (emrTaskInfoVo != null) {
            Index index = null;
            SubfileIndex subfileIndex = null;
            SysUser user = SecurityUtils.getLoginUser().getUser();
            emrTaskInfoVo.setTaskAppDoc(user.getUserName());
            emrTaskInfoVo.setTaskAppDocName(user.getNickName());
            if (emrTaskInfoVo.getMrFileId() == null || StringUtils.isBlank(String.valueOf(emrTaskInfoVo.getMrFileId()))) {
               flag = false;
               ajaxResult = AjaxResult.error("病历id不能为空");
            }

            index = this.indexService.selectIndexById(emrTaskInfoVo.getMrFileId());
            if (flag && index == null) {
               subfileIndex = this.subfileIndexService.selectSubfileIndexById(emrTaskInfoVo.getMrFileId());
               if (flag && subfileIndex == null) {
                  flag = false;
                  ajaxResult = AjaxResult.error("当前病历文件不存在");
               }
            }

            if (flag && (index == null || !"08".equals(index.getMrState())) && (subfileIndex == null || !"08".equals(subfileIndex.getMrState()))) {
               flag = false;
               ajaxResult = AjaxResult.error("当前病历文件不能签名");
            }

            Medicalinformation medicalinformation = this.medicalinfoService.selectMedicalinfoByPatientId(emrTaskInfoVo.getPatientId());
            if (flag && (index == null || !emrTaskInfoVo.getTaskAppDoc().equals(index.getCrePerCode())) && (subfileIndex == null || !emrTaskInfoVo.getTaskAppDoc().equals(subfileIndex.getCrePerCode())) && (medicalinformation == null || !emrTaskInfoVo.getTaskAppDoc().equals(medicalinformation.getResidentCode()))) {
               flag = false;
               ajaxResult = AjaxResult.error("住院医师或者病历创建人可以发起自由流转");
            }

            if (flag) {
               SysUser sysUser = SecurityUtils.getLoginUser().getUser();
               EmrQcFlow emrQcFlow = this.emrQcFlowService.selectEmrQcFlowById(sysUser.getHospital().getOrgCode(), emrTaskInfoVo.getPatientId());
               if (emrQcFlow != null) {
                  if (StringUtils.isNotBlank(emrQcFlow.getMrState()) && emrQcFlow.getMrState().equals("10")) {
                     flag = false;
                     ajaxResult = AjaxResult.error("当前患者已提交科室质控，不可新建病历，请科室质控退回后再试!");
                  }

                  if (StringUtils.isNotBlank(emrQcFlow.getMrState()) && emrQcFlow.getMrState().equals("12")) {
                     flag = false;
                     ajaxResult = AjaxResult.error("当前患者已提交终末质控，不可新建病历，请终末质控退回后再试！");
                  }

                  if (StringUtils.isNotBlank(emrQcFlow.getMrState()) && emrQcFlow.getMrState().equals("14")) {
                     flag = false;
                     ajaxResult = AjaxResult.error("当前患者已归档，不可新建病历！");
                  }

                  if (StringUtils.isNotBlank(emrQcFlow.getMrState()) && emrQcFlow.getMrState().equals("16")) {
                     flag = false;
                     ajaxResult = AjaxResult.error("当前患者已归档，不可新建病历！");
                  }
               }
            }

            if (flag && StringUtils.isBlank(emrTaskInfoVo.getPatientId())) {
               flag = false;
               ajaxResult = AjaxResult.error("未传入患者就诊id");
            }

            if (flag && StringUtils.isBlank(emrTaskInfoVo.getTaskType())) {
               flag = false;
               ajaxResult = AjaxResult.error("未传入任务类型");
            }

            if (flag && StringUtils.isBlank(emrTaskInfoVo.getTaskTypeName())) {
               flag = false;
               ajaxResult = AjaxResult.error("未传入任务类型");
            }

            if (flag && StringUtils.isBlank(emrTaskInfoVo.getBusId())) {
               flag = false;
               ajaxResult = AjaxResult.error("业务id未传递");
            }

            List<BasEmployee> basEmployeeList = emrTaskInfoVo.getBasEmployeeList();
            if (flag && CollectionUtils.isNotEmpty(emrTaskInfoVo.getBasEmployeeList())) {
               EmrSignData emrSignData = new EmrSignData();
               emrSignData.setSignFileId(String.valueOf(emrTaskInfoVo.getMrFileId()));
               emrSignData.setIsValid("1");

               for(BasEmployee basEmployee : basEmployeeList) {
                  if (flag && StringUtils.isBlank(basEmployee.getEmplNumber())) {
                     flag = false;
                     ajaxResult = AjaxResult.error("请选择审签医师");
                  } else {
                     List<EmrTaskInfo> emrTaskInfoList = this.emrTaskInfoService.selectInfoByOdcsAndIndex(emrTaskInfoVo);
                     List<EmrTaskInfo> emrTaskInfoList1 = (List)emrTaskInfoList.stream().filter((s) -> basEmployee.getEmplNumber().equals(s.getDocCd())).collect(Collectors.toList());
                     if (flag && CollectionUtils.isNotEmpty(emrTaskInfoList1)) {
                        flag = false;
                        ajaxResult = AjaxResult.error("签名人（" + basEmployee.getEmplNumber() + "）待办下已有该患者的待签名记录，不允许重复发送");
                     }
                  }

                  if (flag && StringUtils.isBlank(basEmployee.getDeptCode())) {
                     flag = false;
                     ajaxResult = AjaxResult.error("签名人（" + basEmployee.getEmplName() + "）所在科室未传递");
                  }
               }
            }

            if (flag) {
               int add = this.emrTaskInfoService.branchAddTaskList(emrTaskInfoVo);
               ajaxResult.put("data", add);
            }
         }
      } catch (Exception e) {
         this.log.error("发起自由流转出现异常", e);
         ajaxResult = AjaxResult.error(500, "发起自由流转出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('emr:home:list')")
   @Log(
      title = "病历任务流程",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody EmrTaskInfo emrTaskInfo) {
      if (emrTaskInfo != null && "2".equals(emrTaskInfo.getTreatFlag())) {
         SysUser user = SecurityUtils.getLoginUser().getUser();
         EmrTaskInfo emrTaskInfoRes = this.emrTaskInfoService.selectEmrTaskInfoById(emrTaskInfo.getId());
         if (!user.getUserName().equals(emrTaskInfoRes.getTaskAppDoc())) {
            return AjaxResult.error("只有发起人" + emrTaskInfoRes.getTaskAppDocName() + "可以取消流转");
         }
      }

      return this.toAjax(this.emrTaskInfoService.updateEmrTaskInfo(emrTaskInfo));
   }

   @PreAuthorize("@ss.hasPermi('emr:info:remove')")
   @Log(
      title = "病历任务流程",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.emrTaskInfoService.deleteEmrTaskInfoByIds(ids));
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:info:todoEmr,emr:home:list')")
   @GetMapping({"/todoEmr"})
   public TableDataInfo todoEmr() {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<BackLogVo> taskInfoList = this.emrTaskInfoService.selectEmrToDoList((String)null);
         tableDataInfo = this.getDataTable(taskInfoList);
      } catch (Exception e) {
         this.log.error("查询待办事项--待书写病历出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询待办事项--待书写病历出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:index:qcApp')")
   @GetMapping({"/unWriteList"})
   public AjaxResult unWriteList(String patientId) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (flag && StringUtils.isBlank(patientId)) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         VisitinfoVo visitinfoVo = flag ? this.visitinfoService.selectVisitinfoByPatientId(patientId) : null;
         if (flag && visitinfoVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有此患者信息");
         }

         if (flag) {
            List<EmrTaskInfoVo> list = this.qcAgiRuleService.getUnWriteList(visitinfoVo.getOrgCd(), patientId);
            ajaxResult = AjaxResult.success("查询成功", list);
         }
      } catch (Exception e) {
         this.log.error("未书写病历出现异常：", e);
         ajaxResult = AjaxResult.error("未书写病历出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('system:list:list,qc:rt:check,qc:rt:checked,qc:rt:dept,qc:rt:term,docOrder:list:long,docOrder:list:temp,docOrder:list:decoction')")
   @GetMapping({"/overtimeUnWriteList"})
   public AjaxResult overtimeUnWriteList(EmrTaskInfoVo emrTaskInfoVo) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (flag && StringUtils.isBlank(emrTaskInfoVo.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && emrTaskInfoVo.getMainId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(emrTaskInfoVo.getQcSection())) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag) {
            List<EmrTaskInfoVo> list = this.emrTaskInfoService.selectOvertimeUnWriteList(emrTaskInfoVo.getPatientId(), emrTaskInfoVo.getMainId(), emrTaskInfoVo.getQcSection());
            ajaxResult = AjaxResult.success("查询成功", list);
         }
      } catch (Exception e) {
         this.log.error("超时未/已书写病历出现异常：", e);
         ajaxResult = AjaxResult.error("超时未/已书写病历出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:statis:deleteCase')")
   @Log(
      title = "病历任务流程",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/updateById/{id}"})
   public AjaxResult updateById(@PathVariable Long id) {
      AjaxResult ajaxResult = AjaxResult.success("删除成功");

      try {
         this.emrTaskInfoService.updateTaskInfoById(id);
      } catch (Exception e) {
         this.log.error("删除异常", e);
         ajaxResult = AjaxResult.error("缺陷删除异常，请联系系统管理员");
      }

      return ajaxResult;
   }
}
