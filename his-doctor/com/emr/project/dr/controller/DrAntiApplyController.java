package com.emr.project.dr.controller;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.dr.domain.DrAntiApply;
import com.emr.project.dr.domain.vo.DrAntiApplyVo;
import com.emr.project.dr.service.IDrAntiApplyService;
import com.emr.project.esSearch.service.IDrugStockService;
import com.emr.project.his.domain.DrugCheck;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.TmBsDeptTypeContrast;
import com.emr.project.system.service.ITmBsDeptTypeContrastService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateUtils;
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
@RequestMapping({"/dr/apply"})
public class DrAntiApplyController extends BaseController {
   @Autowired
   private IDrAntiApplyService drAntiApplyService;
   @Autowired
   private ITmBsDeptTypeContrastService deptTypeContrastService;
   @Autowired
   private IDrugStockService drugStockService;

   @PreAuthorize("@ss.hasAnyPermi('dr:apply:list,dr:apply:add')")
   @GetMapping({"/list"})
   public AjaxResult list(DrAntiApplyVo drAntiApplyVo) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (StringUtils.isEmpty(drAntiApplyVo.getPatientId())) {
            ajaxResult = AjaxResult.error("患者id不能为空");
         } else {
            if (drAntiApplyVo.getAuditBeginTime() != null && drAntiApplyVo.getAuditEndTime() != null) {
               Calendar c = Calendar.getInstance();
               c.setTime(drAntiApplyVo.getAuditEndTime());
               c.add(5, 1);
               drAntiApplyVo.setAuditEndTime(c.getTime());
            }

            List<DrAntiApplyVo> list = this.drAntiApplyService.selectDrAntiApplyList(drAntiApplyVo);
            ajaxResult = AjaxResult.success((Object)list);
         }
      } catch (Exception e) {
         this.log.error("查询某个患者申请记录列表出现异常", e);
         ajaxResult = AjaxResult.error("查询某个患者申请记录列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('dr:apply:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.drAntiApplyService.selectDrAntiApplyById(id));
   }

   @PreAuthorize("@ss.hasPermi('dr:apply:add')")
   @Log(
      title = "【请填写功能名称】",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody DrAntiApply drAntiApply) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         if (drAntiApply == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(drAntiApply.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag && StringUtils.isEmpty(drAntiApply.getPatientName())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者姓名不能为空");
         }

         if (flag && StringUtils.isEmpty(drAntiApply.getRecordNo())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者病案号不能为空");
         }

         if (flag && StringUtils.isEmpty(drAntiApply.getSexCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("性别编码不能为空");
         }

         if (flag && StringUtils.isEmpty(drAntiApply.getSexName())) {
            flag = false;
            ajaxResult = AjaxResult.error("性别名称不能为空");
         }

         if (flag && StringUtils.isEmpty(drAntiApply.getAge())) {
            flag = false;
            ajaxResult = AjaxResult.error("年龄不能为空");
         }

         if (flag && StringUtils.isEmpty(drAntiApply.getClinDiag())) {
            flag = false;
            ajaxResult = AjaxResult.error("临床诊断不能为空");
         }

         if (flag && StringUtils.isEmpty(drAntiApply.getDrugCode())) {
            flag = false;
            ajaxResult = AjaxResult.error("药品字典编码不能为空");
         }

         if (flag && StringUtils.isEmpty(drAntiApply.getDrugName())) {
            flag = false;
            ajaxResult = AjaxResult.error("药品名称不能为空");
         }

         if (flag && StringUtils.isEmpty(drAntiApply.getDrugSpec())) {
            flag = false;
            ajaxResult = AjaxResult.error("药品规格不能为空");
         }

         if (flag && StringUtils.isEmpty(drAntiApply.getDrugGradeCode())) {
            flag = false;
            ajaxResult = AjaxResult.error("药品等级编码不能为空");
         }

         if (flag && StringUtils.isEmpty(drAntiApply.getDrugGradeName())) {
            flag = false;
            ajaxResult = AjaxResult.error("药品等级名称不能为空");
         }

         if (flag && drAntiApply.getApplyAmount() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("申请数量不能为空");
         }

         if (flag && StringUtils.isEmpty(drAntiApply.getUnit())) {
            flag = false;
            ajaxResult = AjaxResult.error("单位不能为空");
         }

         if (flag && StringUtils.isEmpty(drAntiApply.getPurposeAntimicrobialUse())) {
            flag = false;
            ajaxResult = AjaxResult.error("抗菌药物使用目的不能为空");
         }

         if (flag && StringUtils.isEmpty(drAntiApply.getApplyComm())) {
            flag = false;
            ajaxResult = AjaxResult.error("申请说明不能为空");
         }

         if (flag && StringUtils.isEmpty(drAntiApply.getApplyDocCode())) {
            flag = false;
            ajaxResult = AjaxResult.error("申请医师编码不能为空");
         }

         if (flag && StringUtils.isEmpty(drAntiApply.getApplyDocName())) {
            flag = false;
            ajaxResult = AjaxResult.error("申请医师名称不能为空");
         }

         if (flag && StringUtils.isEmpty(drAntiApply.getApplyTitleCode())) {
            flag = false;
            ajaxResult = AjaxResult.error("申请医师职称编码不能为空");
         }

         if (flag && StringUtils.isEmpty(drAntiApply.getApplyTitleName())) {
            flag = false;
            ajaxResult = AjaxResult.error("申请医师职称名称不能为空");
         }

         if (flag && StringUtils.isEmpty(drAntiApply.getDeptCode())) {
            flag = false;
            ajaxResult = AjaxResult.error("科室编码不能为空");
         }

         if (flag && StringUtils.isEmpty(drAntiApply.getDeptName())) {
            flag = false;
            ajaxResult = AjaxResult.error("科室名称不能为空");
         }

         if (flag) {
            this.drAntiApplyService.saveDrAntiApply(drAntiApply);
         }
      } catch (Exception e) {
         this.log.error("抗菌药物申请保存出现异常", e);
         ajaxResult = AjaxResult.error("抗菌药物申请保存出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('dr:apply:edit')")
   @Log(
      title = "【请填写功能名称】",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody DrAntiApply drAntiApply) {
      AjaxResult ajaxResult = AjaxResult.success("修改成功");

      try {
         this.drAntiApplyService.updateDrAntiApply(drAntiApply);
      } catch (Exception e) {
         this.log.error("修改抗菌药物申请记录出现异常", e);
         ajaxResult = AjaxResult.error("修改抗菌药物申请记录出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('dr:apply:remove')")
   @Log(
      title = "删除抗菌药物申请记录",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{id}"})
   public AjaxResult remove(@PathVariable Long id) {
      AjaxResult ajaxResult = AjaxResult.success("删除成功");

      try {
         if (id == null) {
            ajaxResult = AjaxResult.error("申请id不能为空");
         } else {
            this.drAntiApplyService.deleteDrAntiApplyById(id);
         }
      } catch (Exception e) {
         this.log.error("删除抗菌药物申请出现异常", e);
         ajaxResult = AjaxResult.error("删除抗菌药物申请出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('dr:apply:add')")
   @GetMapping({"/antiDrugList"})
   public AjaxResult antiDrugList() {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      new ArrayList();

      try {
         DrAntiApplyVo drAntiApplyVo = new DrAntiApplyVo();
         List list = this.drAntiApplyService.selectDrAntiDrugVoList(drAntiApplyVo);
         ajaxResult = AjaxResult.success((Object)list);
      } catch (Exception e) {
         this.log.error("查询抗菌药物列表出现异常", e);
         ajaxResult = AjaxResult.error("查询抗菌药物列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('dr:apply:list')")
   @GetMapping({"/auditList"})
   public TableDataInfo auditList(DrAntiApplyVo drAntiApplyVo) {
      new TableDataInfo();
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();

      TableDataInfo tableDataInfo;
      try {
         Boolean flag = Boolean.FALSE;
         String deptCode = sysUser.getDept().getDeptCode();
         List<TmBsDeptTypeContrast> tmBsDeptTypeContrasts = this.deptTypeContrastService.selectTmBsDeptTypeContrastById(deptCode);
         if (tmBsDeptTypeContrasts != null && tmBsDeptTypeContrasts.size() > 0) {
            for(TmBsDeptTypeContrast deptTypeContrast : tmBsDeptTypeContrasts) {
               String deptType = deptTypeContrast.getDeptType();
               if (deptType.equals("14")) {
                  flag = Boolean.TRUE;
                  break;
               }
            }
         }

         if (drAntiApplyVo != null && drAntiApplyVo.getDateEnd() != null) {
            drAntiApplyVo.setDateEnd(DateUtils.addDays(drAntiApplyVo.getDateEnd(), 1));
         }

         this.startPage();
         List<DrAntiApplyVo> list = this.drAntiApplyService.selectAuditList(drAntiApplyVo, flag);
         if (CollectionUtils.isNotEmpty(list)) {
            List<String> drugList = (List)list.stream().map((s) -> s.getDrugCode()).distinct().collect(Collectors.toList());
            List<DrugCheck> drugCheckList = this.drugStockService.selectDrugCheckList(sysUser, drugList);
            if (CollectionUtils.isNotEmpty(drugCheckList)) {
               List<String> unUseAuthList = (List)((List)drugCheckList.stream().filter((s) -> s.getUseEnabled().equals("0")).collect(Collectors.toList())).stream().map((s) -> s.getDrugCode().trim()).collect(Collectors.toList());

               for(DrAntiApplyVo applyVo : list) {
                  if (unUseAuthList.contains(applyVo.getDrugCode())) {
                     applyVo.setUseEnabled("0");
                  }
               }
            }
         }

         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询抗菌药物申请待审核列表出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询抗菌药物申请待审核列表出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasPermi('dr:apply:audit')")
   @PutMapping({"/audit"})
   public AjaxResult audit(@RequestBody DrAntiApplyVo drAntiApplyVo) {
      AjaxResult ajaxResult = AjaxResult.success("审核成功");
      boolean flag = true;

      try {
         if (drAntiApplyVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && drAntiApplyVo.getIds() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("id不能为空");
         }

         if (flag) {
            if (StringUtils.isEmpty(drAntiApplyVo.getState())) {
               flag = false;
               ajaxResult = AjaxResult.error("审核状态不能为空");
            } else if (drAntiApplyVo.getState().equals("2") && StringUtils.isEmpty(drAntiApplyVo.getApprComm())) {
               flag = false;
               ajaxResult = AjaxResult.error("审核意见不能为空");
            }
         }

         if (flag) {
            this.drAntiApplyService.updateAuditState(drAntiApplyVo);
         }
      } catch (Exception e) {
         this.log.error("抗菌药物审核出现异常", e);
         ajaxResult = AjaxResult.error("抗菌药物审核出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('dr:apply:list')")
   @GetMapping({"/yetAuditList"})
   public TableDataInfo yetAuditList(DrAntiApplyVo drAntiApplyVo) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         Boolean flag = Boolean.FALSE;
         SysUser sysUser = SecurityUtils.getLoginUser().getUser();
         List<TmBsDeptTypeContrast> deptTypeContrasts = this.deptTypeContrastService.selectTmBsDeptTypeContrastById(sysUser.getDept().getDeptCode());
         if (deptTypeContrasts != null && deptTypeContrasts.size() > 0) {
            for(TmBsDeptTypeContrast deptTypeContrast : deptTypeContrasts) {
               String deptType = deptTypeContrast.getDeptType();
               if (deptType.equals("14")) {
                  flag = Boolean.TRUE;
                  break;
               }
            }
         }

         this.startPage();
         List<DrAntiApplyVo> list = this.drAntiApplyService.selectYetAuditList(drAntiApplyVo, flag);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询抗菌药物申请已审核列表出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询抗菌药物申请已审核列表出现异常");
      }

      return tableDataInfo;
   }
}
