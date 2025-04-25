package com.emr.project.emr.controller;

import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.emr.domain.EmrSetDoctor;
import com.emr.project.emr.domain.vo.EmrSetDetailVo;
import com.emr.project.emr.domain.vo.EmrSetDoctorVo;
import com.emr.project.emr.service.IEmrSetDoctorService;
import java.util.List;
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
@RequestMapping({"/emrSet/doctor"})
public class EmrSetDoctorController extends BaseController {
   @Autowired
   private IEmrSetDoctorService emrSetDoctorService;

   @PreAuthorize("@ss.hasPermi('emrSet:doctor:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(EmrSetDoctor emrSetDoctor) {
      this.startPage();
      List<EmrSetDoctor> list = this.emrSetDoctorService.selectEmrSetDoctorList(emrSetDoctor);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('emrSet:doctor:export')")
   @Log(
      title = "【请填写功能名称】",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(EmrSetDoctor emrSetDoctor) {
      List<EmrSetDoctor> list = this.emrSetDoctorService.selectEmrSetDoctorList(emrSetDoctor);
      ExcelUtil<EmrSetDoctor> util = new ExcelUtil(EmrSetDoctor.class);
      return util.exportExcel(list, "【请填写功能名称】数据");
   }

   @PreAuthorize("@ss.hasPermi('emrSet:doctor:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.emrSetDoctorService.selectEmrSetDoctorById(id));
   }

   @PreAuthorize("@ss.hasAnyPermi('emrSet:doctor:add,pat:visitinfo:allList')")
   @Log(
      title = "保存医师病历组套信息",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody EmrSetDoctorVo emrSetDoctorVo) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         if (emrSetDoctorVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(emrSetDoctorVo.getSetCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("组套编码不能为空");
         }

         if (flag && StringUtils.isEmpty(emrSetDoctorVo.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag) {
            Boolean createFlag = this.emrSetDoctorService.saveEmrSetDoctorList(emrSetDoctorVo);
            if (createFlag) {
               ajaxResult.put("createFlag", true);
               EmrSetDetailVo emrSetDetailVo = (EmrSetDetailVo)emrSetDoctorVo.getDetailList().get(0);
               emrSetDetailVo.setMrType(emrSetDetailVo.getEmrTypeId());
               ajaxResult.put("emrSetDetail", emrSetDetailVo);
               if (((EmrSetDetailVo)emrSetDoctorVo.getDetailList().get(0)).getMrType().equals("12")) {
                  ajaxResult.put("isMainFile", "1");
               }
            }
         }
      } catch (Exception e) {
         this.log.error("保存医师病历组套信息出现异常", e);
         ajaxResult = AjaxResult.error("保存医师病历组套信息出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('emrSet:doctor:edit')")
   @Log(
      title = "【请填写功能名称】",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody EmrSetDoctor emrSetDoctor) {
      return this.toAjax(this.emrSetDoctorService.updateEmrSetDoctor(emrSetDoctor));
   }

   @PreAuthorize("@ss.hasPermi('emrSet:doctor:remove')")
   @Log(
      title = "【请填写功能名称】",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.emrSetDoctorService.deleteEmrSetDoctorByIds(ids));
   }

   @PreAuthorize("@ss.hasAnyPermi('emrSet:doctor:remove,emr:index:save')")
   @Log(
      title = "移除医师关联的病历组套",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/deleteCheckSet/{setCd}"})
   public AjaxResult deleteCheckSet(@PathVariable String setCd) {
      AjaxResult ajaxResult = AjaxResult.success("移除成功");

      try {
         this.emrSetDoctorService.deleteEmrSetDoctorBySetCd(setCd);
      } catch (Exception e) {
         this.log.error("移除医师关联的病历组套出现异常", e);
         ajaxResult = AjaxResult.error("移除医师关联的病历组套出现异常");
      }

      return ajaxResult;
   }
}
