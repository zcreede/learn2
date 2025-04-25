package com.emr.project.emr.controller;

import com.emr.common.constant.CommonConstants;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.emr.domain.ModifyAppl;
import com.emr.project.emr.domain.vo.ModifyApplVo;
import com.emr.project.emr.service.IModifyApplService;
import com.emr.project.qc.domain.EmrQcFlow;
import com.emr.project.qc.service.IEmrQcFlowService;
import com.emr.project.system.domain.SysDept;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysDeptService;
import com.emr.project.system.service.ISysEmrConfigService;
import java.util.ArrayList;
import java.util.Date;
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
@RequestMapping({"/emr/appl"})
public class ModifyApplController extends BaseController {
   @Autowired
   private IModifyApplService modifyApplService;
   @Autowired
   private ISysDeptService sysDeptService;
   @Autowired
   private IEmrQcFlowService emrQcFlowService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;

   @PreAuthorize("@ss.hasAnyPermi('emr:appl:list,pat:visitinfo:emrPrintList,pat:visitinfo:emrAllList')")
   @GetMapping({"/list"})
   public TableDataInfo list(ModifyApplVo modifyApplVo) {
      List<ModifyApplVo> list = null;

      try {
         String appTimeBegStr = modifyApplVo.getAppTimeBegStr();
         String appTimeEndStr = modifyApplVo.getAppTimeEndStr();
         if (StringUtils.isNotBlank(appTimeBegStr)) {
            appTimeBegStr = appTimeBegStr + " 00:00:00";
            modifyApplVo.setAppTimeBegStr(appTimeBegStr);
         }

         if (StringUtils.isNotBlank(appTimeEndStr)) {
            Date appTimeEnd = DateUtils.parseDate(appTimeEndStr, new String[]{DateUtils.YYYY_MM_DD});
            appTimeEndStr = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, DateUtils.addDays(appTimeEnd, 1));
            modifyApplVo.setAppTimeEndStr(appTimeEndStr);
         }

         this.startPage();
         list = this.modifyApplService.selectModifyApplList(modifyApplVo);
      } catch (Exception e) {
         this.log.error("查询病历维护申请列表出现异常, ", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('emr:appl:unRecordAppl')")
   @GetMapping({"/unRecordNotApplList"})
   public TableDataInfo unRecordNotApplList(ModifyApplVo modifyApplVo) {
      List<ModifyApplVo> list = null;

      try {
         modifyApplVo.setConState("0");
         Boolean isQCDept = this.isQCDept();
         if (!isQCDept) {
            modifyApplVo.setAppDeptCd(SecurityUtils.getLoginUser().getUser().getDept().getDeptCode());
         }

         modifyApplVo.setMrState("00");
         this.startPage();
         list = this.modifyApplService.selectUnRecordApplList(modifyApplVo);
      } catch (Exception e) {
         this.log.error("查询未归档未审核列表出现异常, ", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('emr:appl:unRecordAppl')")
   @GetMapping({"/unRecordApplList"})
   public TableDataInfo unRecordApplList(ModifyApplVo modifyApplVo) {
      List<ModifyApplVo> list = null;

      try {
         List<String> conStateList = new ArrayList(2);
         conStateList.add("1");
         conStateList.add("2");
         modifyApplVo.setConStateList(conStateList);
         Boolean isQCDept = this.isQCDept();
         if (!isQCDept) {
            modifyApplVo.setAppDeptCd(SecurityUtils.getLoginUser().getUser().getDept().getDeptCode());
         }

         modifyApplVo.setMrState("00");
         this.startPage();
         list = this.modifyApplService.selectUnRecordApplList(modifyApplVo);
      } catch (Exception e) {
         this.log.error("查询未归档已审核列表出现异常", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('emr:appl:recordAppl')")
   @GetMapping({"/recordNotApplList"})
   public TableDataInfo recordNotApplList(ModifyApplVo modifyApplVo) {
      List<ModifyApplVo> list = null;

      try {
         modifyApplVo.setConState("0");
         Boolean isQCDept = this.isQCDept();
         if (!isQCDept && StringUtils.isBlank(modifyApplVo.getAppDeptCd())) {
            modifyApplVo.setAppDeptCd(SecurityUtils.getLoginUser().getUser().getDept().getDeptCode());
         }

         if (StringUtils.isNotBlank(modifyApplVo.getAppDeptCd()) && (modifyApplVo.getAppDeptCd().equals(CommonConstants.SYSTEM.ALL_DEPT_ID.toString()) || modifyApplVo.getAppDeptCd().equals("000000"))) {
            modifyApplVo.setAppDeptCd((String)null);
         }

         this.startPage();
         list = this.modifyApplService.selectRecordApplList(modifyApplVo);
      } catch (Exception e) {
         this.log.error("查询未归档未审核列表出现异常, ", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('emr:appl:recordAppl')")
   @GetMapping({"/recordApplList"})
   public TableDataInfo recordApplList(ModifyApplVo modifyApplVo) {
      List<ModifyApplVo> list = null;

      try {
         List<String> conStateList = new ArrayList(2);
         conStateList.add("1");
         conStateList.add("2");
         modifyApplVo.setConStateList(conStateList);
         Boolean isQCDept = this.isQCDept();
         if (!isQCDept && StringUtils.isBlank(modifyApplVo.getAppDeptCd())) {
            modifyApplVo.setAppDeptCd(SecurityUtils.getLoginUser().getUser().getDept().getDeptCode());
         }

         if (StringUtils.isNotBlank(modifyApplVo.getAppDeptCd()) && (modifyApplVo.getAppDeptCd().equals(CommonConstants.SYSTEM.ALL_DEPT_ID.toString()) || modifyApplVo.getAppDeptCd().equals("000000"))) {
            modifyApplVo.setAppDeptCd((String)null);
         }

         this.startPage();
         list = this.modifyApplService.selectRecordApplList(modifyApplVo);
      } catch (Exception e) {
         this.log.error("查询未归档已审核列表出现异常", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('emr:appl:writeAppl')")
   @GetMapping({"/unWriteNotApplList"})
   public TableDataInfo unWriteNotApplList(ModifyApplVo modifyApplVo) {
      List<ModifyApplVo> list = null;

      try {
         modifyApplVo.setConState("0");
         Boolean isQCDept = this.isQCDept();
         if (!isQCDept && StringUtils.isBlank(modifyApplVo.getAppDeptCd())) {
            modifyApplVo.setAppDeptCd(SecurityUtils.getLoginUser().getUser().getDept().getDeptCode());
         }

         if (StringUtils.isNotBlank(modifyApplVo.getAppDeptCd()) && (modifyApplVo.getAppDeptCd().equals(CommonConstants.SYSTEM.ALL_DEPT_ID.toString()) || modifyApplVo.getAppDeptCd().equals("000000"))) {
            modifyApplVo.setAppDeptCd((String)null);
         }

         this.startPage();
         if (modifyApplVo.getDateEnd() != null) {
            Date dateEnd = modifyApplVo.getDateEnd();
            modifyApplVo.setDateEnd(DateUtils.addDays(dateEnd, 1));
         }

         list = this.modifyApplService.selectUnWriteApplList(modifyApplVo);
         if (CollectionUtils.isNotEmpty(list)) {
            list.stream().forEach((t) -> {
               if (StringUtils.isBlank(t.getFileName())) {
                  t.setFileName(t.getEmrTypeName());
               }

            });
         }
      } catch (Exception e) {
         this.log.error("查询未归档未审核列表出现异常, ", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('emr:appl:writeAppl')")
   @GetMapping({"/unWriteApplList"})
   public TableDataInfo unWriteApplList(ModifyApplVo modifyApplVo) {
      List<ModifyApplVo> list = null;

      try {
         List<String> conStateList = new ArrayList(2);
         conStateList.add("1");
         conStateList.add("2");
         modifyApplVo.setConStateList(conStateList);
         Boolean isQCDept = this.isQCDept();
         if (!isQCDept && StringUtils.isBlank(modifyApplVo.getAppDeptCd())) {
            modifyApplVo.setAppDeptCd(SecurityUtils.getLoginUser().getUser().getDept().getDeptCode());
         }

         if (StringUtils.isNotBlank(modifyApplVo.getAppDeptCd()) && (modifyApplVo.getAppDeptCd().equals(CommonConstants.SYSTEM.ALL_DEPT_ID.toString()) || modifyApplVo.getAppDeptCd().equals("000000"))) {
            modifyApplVo.setAppDeptCd((String)null);
         }

         this.startPage();
         list = this.modifyApplService.selectUnWriteApplList(modifyApplVo);
         if (CollectionUtils.isNotEmpty(list)) {
            list.stream().forEach((t) -> {
               if (StringUtils.isBlank(t.getFileName())) {
                  t.setFileName(t.getEmrTypeName());
               }

            });
            list.stream().forEach((t) -> {
               if (StringUtils.isBlank(t.getEmrTypeNameReal())) {
                  t.setEmrTypeNameReal(t.getEmrTypeName());
               }

            });
         }
      } catch (Exception e) {
         this.log.error("查询未归档已审核列表出现异常", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('emr:appl:export')")
   @Log(
      title = "病历维护申请",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(ModifyApplVo modifyApplVo) {
      List<ModifyApplVo> list = null;

      try {
         list = this.modifyApplService.selectModifyApplList(modifyApplVo);
      } catch (Exception e) {
         this.log.error("病历维护申请数据出现异常", e);
      }

      ExcelUtil<ModifyApplVo> util = new ExcelUtil(ModifyApplVo.class);
      return util.exportExcel(list, "病历维护申请数据");
   }

   @PreAuthorize("@ss.hasPermi('emr:appl:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.modifyApplService.selectModifyApplById(id));
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:appl:add,emr:index:add,emr:index:save,emr:index:editDelFlag')")
   @Log(
      title = "病历维护申请",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody ModifyAppl modifyAppl) {
      AjaxResult ajaxResult = AjaxResult.success("申请成功");
      boolean flag = true;

      try {
         if (flag && modifyAppl == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(modifyAppl.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者ID不能为空");
         }

         if (flag && modifyAppl.getMrFileId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("请选择病历文件");
         }

         if (flag && modifyAppl.getAppReasonCd() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("请选择申请原因");
         }

         if (flag && modifyAppl.getAppReasonCd().equals(CommonConstants.EMR_MODIFY_APPLY.APP_REASON_3) && modifyAppl.getMrFileId() != null) {
            ModifyAppl modifyAppl1 = this.modifyApplService.selectModifyApplByAppl(modifyAppl);
            if (modifyAppl1 != null) {
               flag = false;
               ajaxResult = AjaxResult.success("超时创建申请已创建，可以直接新建此病历");
            }
         }

         if (flag && !modifyAppl.getAppReasonCd().equals(CommonConstants.EMR_MODIFY_APPLY.APP_REASON_3) && StringUtils.isBlank(modifyAppl.getFileName())) {
            flag = false;
            ajaxResult = AjaxResult.error("请选择病历文件");
         }

         if (flag && StringUtils.isBlank(modifyAppl.getAppReason())) {
            flag = false;
            ajaxResult = AjaxResult.error("请填写申请描述");
         }

         SysUser user = SecurityUtils.getLoginUser().getUser();
         EmrQcFlow emrQcFlow = this.emrQcFlowService.selectEmrQcFlowById(user.getHospital().getOrgCode(), modifyAppl.getPatientId());
         List<String> stateList = new ArrayList();
         stateList.add("16");
         stateList.add("12");
         if (flag && emrQcFlow != null && stateList.contains(emrQcFlow.getMrState())) {
            flag = false;
            ajaxResult = AjaxResult.error("已归档病例不允许修改");
         }

         String autoFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0097");
         if (flag && !StringUtils.isEmpty(autoFlag) && "0".equals(autoFlag) && CommonConstants.EMR_MODIFY_APPLY.APP_REASON_1.equals(modifyAppl.getAppReasonCd())) {
            ModifyAppl modifyApplRes = this.modifyApplService.selectModifyApplByEndDate(modifyAppl.getAppReasonCd(), modifyAppl.getConState(), modifyAppl.getMrFileId(), modifyAppl.getAppDocCd(), modifyAppl.getAppDeptCd());
            if (flag && modifyApplRes != null && "0".equals(modifyAppl.getConState())) {
               flag = false;
               ajaxResult = AjaxResult.error("申请已提交，请耐心等待审核");
            }
         }

         if (flag) {
            this.modifyApplService.insertModifyAppl(modifyAppl);
            if (modifyAppl.getConState().equals("1")) {
               ajaxResult = AjaxResult.success("申请审核通过");
            }
         }
      } catch (Exception e) {
         this.log.error("新增病历维护申请出现异常, ", e);
         ajaxResult = AjaxResult.error("新增病历维护申请出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('emr:appl:edit')")
   @Log(
      title = "病历维护申请",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody ModifyAppl modifyAppl) {
      AjaxResult ajaxResult = AjaxResult.success("新增成功");
      boolean flag = true;

      try {
         if (flag && modifyAppl == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && modifyAppl.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag) {
            ModifyAppl temp = this.modifyApplService.selectModifyApplById(modifyAppl.getId());
            if (temp == null) {
               flag = false;
               ajaxResult = AjaxResult.error("没有这条申请记录");
            } else if (!temp.getConState().equals("0")) {
               flag = false;
               ajaxResult = AjaxResult.error("申请记录已审核，不能修改");
            }
         }

         if (flag && modifyAppl.getMrFileId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("请选择病历文件");
         }

         if (flag && StringUtils.isBlank(modifyAppl.getFileName())) {
            flag = false;
            ajaxResult = AjaxResult.error("请选择病历文件");
         }

         if (flag && StringUtils.isBlank(modifyAppl.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("请选择患者信息");
         }

         if (flag && StringUtils.isBlank(modifyAppl.getPatientName())) {
            flag = false;
            ajaxResult = AjaxResult.error("请选择患者信息");
         }

         if (flag && (StringUtils.isBlank(modifyAppl.getAppReason()) || modifyAppl.getAppReasonCd() == null)) {
            flag = false;
            ajaxResult = AjaxResult.error("请选择患者信息");
         }

         if (flag && StringUtils.isBlank(modifyAppl.getPatientName())) {
            flag = false;
            ajaxResult = AjaxResult.error("请选择患者信息");
         }

         if (flag) {
            this.modifyApplService.updateModifyAppl(modifyAppl);
         }
      } catch (Exception e) {
         this.log.error("新增病历维护申请出现异常, ", e);
         ajaxResult = AjaxResult.error("新增病历维护申请出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('emr:appl:remove')")
   @Log(
      title = "病历维护申请",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.modifyApplService.deleteModifyApplByIds(ids));
   }

   @PreAuthorize("@ss.hasPermi('emr:appl:doApproved')")
   @Log(
      title = "病历维护申请",
      businessType = BusinessType.UPDATE
   )
   @PutMapping({"/doApproved"})
   public AjaxResult doApproved(@RequestBody ModifyAppl modifyAppl) {
      AjaxResult ajaxResult = AjaxResult.success("新增成功");
      boolean flag = true;

      try {
         if (flag && modifyAppl == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && modifyAppl.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag) {
            ModifyAppl temp = this.modifyApplService.selectModifyApplById(modifyAppl.getId());
            if (temp == null) {
               flag = false;
               ajaxResult = AjaxResult.error("没有这条申请记录");
            } else if (!temp.getConState().equals("0")) {
               flag = false;
               ajaxResult = AjaxResult.error("申请记录已审核，不能重复审核");
            }
         }

         if (flag && modifyAppl.getTreatDeadline() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("请填写处理期限");
         }

         if (flag && StringUtils.isBlank(modifyAppl.getDeadlineUnit())) {
            flag = false;
            ajaxResult = AjaxResult.error("请填写处理期限单位");
         }

         if (flag) {
            this.modifyApplService.doApproved(modifyAppl);
         }
      } catch (Exception e) {
         this.log.error("新增病历维护申请出现异常, ", e);
         ajaxResult = AjaxResult.error("新增病历维护申请出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('emr:appl:doApproveds')")
   @Log(
      title = "病历维护申请",
      businessType = BusinessType.UPDATE
   )
   @PutMapping({"/doApproveds"})
   public AjaxResult doApproveds(@RequestBody ModifyApplVo modifyAppl) {
      AjaxResult ajaxResult = AjaxResult.success("审核成功");
      boolean flag = true;

      try {
         if (flag && modifyAppl == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && modifyAppl.getIds() == null && !modifyAppl.getIds().isEmpty()) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag) {
            List<ModifyAppl> list = this.modifyApplService.selectModifyApplByIds(modifyAppl.getIds());
            if (list == null) {
               flag = false;
               ajaxResult = AjaxResult.error("没有这条申请记录");
            } else {
               List<ModifyAppl> listTemp = (List)list.stream().filter((t) -> t.getConState().equals("0")).collect(Collectors.toList());
               if (listTemp == null || listTemp.isEmpty()) {
                  flag = false;
                  ajaxResult = AjaxResult.error("申请记录已审核，不能重复审核");
               }
            }
         }

         if (flag && modifyAppl.getTreatDeadline() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("请填写处理期限");
         }

         if (flag && StringUtils.isBlank(modifyAppl.getDeadlineUnit())) {
            flag = false;
            ajaxResult = AjaxResult.error("请填写处理期限单位");
         }

         if (flag) {
            this.modifyApplService.doApproveds(modifyAppl);
         }
      } catch (Exception e) {
         this.log.error("审核病历维护申请出现异常, ", e);
         ajaxResult = AjaxResult.error("审核病历维护申请出现异常");
      }

      return ajaxResult;
   }

   private Boolean isQCDept() throws Exception {
      SysDept sysDept = SecurityUtils.getLoginUser().getUser().getDept();
      List<SysDept> qcDeptList = this.sysDeptService.selectdeptListByTypeCode("14");
      List<String> qcDeptCodeList = (List<String>)(CollectionUtils.isNotEmpty(qcDeptList) ? (List)qcDeptList.stream().map((t) -> t.getDeptCode()).collect(Collectors.toList()) : new ArrayList(1));
      return qcDeptCodeList.contains(sysDept.getDeptCode()) ? true : false;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:appl:baseInfo,emr:appl:unRecordAppl,emr:appl:recordAppl,emr:appl:writeAppl')")
   @GetMapping({"/baseInfo"})
   public AjaxResult getBaseInfo() {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         Boolean isQCDept = this.isQCDept();
         ajaxResult.put("isQCDept", isQCDept);
      } catch (Exception e) {
         this.log.error("病历管理-病历修改审批，查询基础信息出现异常", e);
         ajaxResult = AjaxResult.error("病历管理-病历修改审批，查询基础信息出现异常");
      }

      return ajaxResult;
   }
}
