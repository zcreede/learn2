package com.emr.project.system.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.system.domain.WorkLoadMain;
import com.emr.project.system.domain.WorkLoadPatient;
import com.emr.project.system.domain.req.PatientInfoReq;
import com.emr.project.system.domain.resp.WorkLoadDeptResp;
import com.emr.project.system.domain.vo.WorkLoadPatientVo;
import com.emr.project.system.service.IWorkLoadMainService;
import com.emr.project.system.service.IWorkLoadPatientService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/mris"})
public class WorkLoadPatientController extends BaseController {
   @Autowired
   private IWorkLoadPatientService workLoadPatientService;
   @Autowired
   private IWorkLoadMainService workLoadMainService;

   @PreAuthorize("@ss.hasPermi('mris:patient:list')")
   @GetMapping({"/patient/list"})
   public AjaxResult list(WorkLoadPatient workLoadPatient) {
      AjaxResult ajaxResult = AjaxResult.success();
      if (workLoadPatient.getMainId() == null) {
         ajaxResult = AjaxResult.error("主id不能为空");
         return ajaxResult;
      } else {
         try {
            List<WorkLoadPatientVo> list = this.workLoadPatientService.selectWorkLoadPatientList(workLoadPatient);
            if (!list.isEmpty()) {
               ajaxResult.put("data", list);
               List<WorkLoadPatient> inList = (List)list.stream().filter((t) -> t.getItemTypeCode().equals("1")).collect(Collectors.toList());
               ajaxResult.put("inNum", inList.size());
               List<WorkLoadPatient> outList = (List)list.stream().filter((t) -> t.getItemTypeCode().equals("2")).collect(Collectors.toList());
               ajaxResult.put("outNum", outList.size());
            }
         } catch (Exception e) {
            this.log.error("工作量上报患者项目列表出现异常，", e);
            ajaxResult = AjaxResult.error("工作量上报患者项目列表出现异常，请联系管理员！");
         }

         return ajaxResult;
      }
   }

   @PreAuthorize("@ss.hasPermi('mris:patient:add')")
   @Log(
      title = "住院科室工作量上报患者项目",
      businessType = BusinessType.INSERT
   )
   @PostMapping({"/patient"})
   public AjaxResult add(@RequestBody WorkLoadPatient workLoadPatient) {
      AjaxResult ajaxResult = AjaxResult.success();
      if (StringUtils.isEmpty(workLoadPatient.getItemCode())) {
         ajaxResult = AjaxResult.error("项目编码不能为空！");
         return ajaxResult;
      } else if (StringUtils.isEmpty(workLoadPatient.getItemName())) {
         ajaxResult = AjaxResult.error("项目名称不能为空！");
         return ajaxResult;
      } else if (StringUtils.isEmpty(workLoadPatient.getItemTypeCode())) {
         ajaxResult = AjaxResult.error("项目类型编码不能为空！");
         return ajaxResult;
      } else if (StringUtils.isEmpty(workLoadPatient.getItemTypeName())) {
         ajaxResult = AjaxResult.error("项目类型名称不能为空！");
         return ajaxResult;
      } else if (StringUtils.isEmpty(workLoadPatient.getAdmissionNo())) {
         ajaxResult = AjaxResult.error("住院号不能为空！");
         return ajaxResult;
      } else if (workLoadPatient.getMainId() == null) {
         ajaxResult = AjaxResult.error("主id不能为空！");
         return ajaxResult;
      } else {
         try {
            WorkLoadPatient patient = this.workLoadPatientService.insertOrUpdateWorkLoadPatient(workLoadPatient);
            ajaxResult.put("data", patient);
         } catch (Exception e) {
            this.log.error("新增或修改住院科室工作量上报患者项目出现异常，", e);
            ajaxResult = AjaxResult.error("新增或修改住院科室工作量上报患者项目出现异常！");
         }

         return ajaxResult;
      }
   }

   @PreAuthorize("@ss.hasPermi('mris:patient:remove')")
   @Log(
      title = "住院科室工作量上报患者项目",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/patient/{id}"})
   public AjaxResult remove(@PathVariable Long id) {
      AjaxResult ajaxResult = AjaxResult.success();
      if (id == null) {
         ajaxResult = AjaxResult.error("id不能为空！");
         return ajaxResult;
      } else {
         WorkLoadPatient workLoadPatient = this.workLoadPatientService.selectWorkLoadPatientById(id);
         if (workLoadPatient == null) {
            ajaxResult = AjaxResult.error("该项目不存在，请刷新后再试！");
            return ajaxResult;
         } else {
            try {
               this.workLoadPatientService.deleteWorkLoadPatientById(id);
               WorkLoadMain main = this.workLoadMainService.selectMainByUpdate(workLoadPatient.getMainId());
               ajaxResult.put("data", main);
            } catch (Exception e) {
               this.log.error("删除住院科室工作量上报患者项目出现异常，", e);
               ajaxResult = AjaxResult.error("删除住院科室工作量上报患者项目出现异常，请联系管理员！");
            }

            return ajaxResult;
         }
      }
   }

   @PreAuthorize("@ss.hasPermi('mris:patient:info')")
   @GetMapping({"/patient/info"})
   public AjaxResult getPatientInfo(PatientInfoReq req) {
      AjaxResult ajaxResult = AjaxResult.success();
      if (StringUtils.isEmpty(req.getSearchValue())) {
         ajaxResult = AjaxResult.error("查询条件不能为空！");
         return ajaxResult;
      } else if (StringUtils.isEmpty(req.getDateTime())) {
         ajaxResult = AjaxResult.error("统计日期不能为空！");
         return ajaxResult;
      } else {
         try {
            List<WorkLoadPatientVo> list = this.workLoadPatientService.getPatientInfo(req);
            ajaxResult.put("data", list);
         } catch (Exception e) {
            this.log.error("查询患者信息出现异常，", e);
            ajaxResult = AjaxResult.error("查询患者信息出现异常，请联系管理员！");
         }

         return ajaxResult;
      }
   }

   @GetMapping({"/patient/deptList"})
   public AjaxResult getDeptList() {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         List<WorkLoadDeptResp> list = this.workLoadPatientService.selectDeptList();
         ajaxResult.put("data", list);
      } catch (Exception e) {
         this.log.error("查询住院科室列表出现异常，", e);
         ajaxResult = AjaxResult.error("查询住院科室列表出现异常，请联系管理员！");
      }

      return ajaxResult;
   }
}
