package com.emr.project.pat.controller;

import com.emr.common.constant.CommonConstants;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.pat.domain.Otherinfo;
import com.emr.project.pat.domain.req.KeyPatientsReq;
import com.emr.project.pat.domain.resp.KeyPatientsResp;
import com.emr.project.pat.domain.resp.keyPatientsDetailResp;
import com.emr.project.pat.service.IOtherinfoService;
import java.util.Date;
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
@RequestMapping({"/pat/otherinfo"})
public class OtherinfoController extends BaseController {
   @Autowired
   private IOtherinfoService otherinfoService;

   @PreAuthorize("@ss.hasPermi('pat:otherinfo:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(Otherinfo otherinfo) {
      this.startPage();
      List<Otherinfo> list = this.otherinfoService.selectOtherinfoList(otherinfo);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('pat:otherinfo:export')")
   @Log(
      title = "患者附加信息",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(Otherinfo otherinfo) {
      List<Otherinfo> list = this.otherinfoService.selectOtherinfoList(otherinfo);
      ExcelUtil<Otherinfo> util = new ExcelUtil(Otherinfo.class);
      return util.exportExcel(list, "患者附加信息数据");
   }

   @PreAuthorize("@ss.hasPermi('pat:otherinfo:query')")
   @GetMapping({"/{patientId}"})
   public AjaxResult getInfo(@PathVariable("patientId") Long patientId) {
      return AjaxResult.success((Object)this.otherinfoService.selectOtherinfoById(patientId));
   }

   @PreAuthorize("@ss.hasPermi('pat:otherinfo:add')")
   @Log(
      title = "患者附加信息",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody Otherinfo otherinfo) {
      return this.toAjax(this.otherinfoService.insertOtherinfo(otherinfo));
   }

   @PreAuthorize("@ss.hasPermi('pat:otherinfo:edit')")
   @Log(
      title = "患者附加信息",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody Otherinfo otherinfo) {
      return this.toAjax(this.otherinfoService.updateOtherinfo(otherinfo));
   }

   @PreAuthorize("@ss.hasPermi('pat:otherinfo:remove')")
   @Log(
      title = "患者附加信息",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{patientIds}"})
   public AjaxResult remove(@PathVariable Long[] patientIds) {
      return this.toAjax(this.otherinfoService.deleteOtherinfoByIds(patientIds));
   }

   @PreAuthorize("@ss.hasPermi('pat:otherinfo:keyPatients')")
   @PostMapping({"/keyPatients"})
   public AjaxResult keyPatients(@RequestBody KeyPatientsReq req) {
      AjaxResult ajaxResult = AjaxResult.success();
      if (req == null) {
         ajaxResult = AjaxResult.error("参数不能为空");
         return ajaxResult;
      } else if (StringUtils.isEmpty(req.getFlag())) {
         ajaxResult = AjaxResult.error("查询类型不能为空");
         return ajaxResult;
      } else if (!req.getFlag().equals("1") && !req.getFlag().equals("2")) {
         ajaxResult = AjaxResult.error("查询类型错误！");
         return ajaxResult;
      } else if (!req.getFlag().equals("2") || req.getStartDate() != null && req.getEndDate() != null) {
         try {
            if (req.getEndDate() != null) {
               Date hospitalizedDateEnd = DateUtils.addDays(req.getEndDate(), 1);
               req.setEndDate(hospitalizedDateEnd);
            }

            List<KeyPatientsResp> list = this.otherinfoService.selectKeyPatientsList(req);
            ajaxResult.put("data", list);
            ajaxResult.put("keyMap", CommonConstants.KEY_PATIENTS_MAP);
         } catch (Exception e) {
            this.log.error("查询重点患者统计汇总表出现异常", e);
            ajaxResult = AjaxResult.error("查询重点患者统计汇总表出现异常");
         }

         return ajaxResult;
      } else {
         ajaxResult = AjaxResult.error("出院时间不能为空！");
         return ajaxResult;
      }
   }

   @PreAuthorize("@ss.hasPermi('pat:otherinfo:keyPatientsDetail')")
   @PostMapping({"/keyPatientsDetail"})
   public AjaxResult keyPatientsDetail(@RequestBody KeyPatientsReq req) {
      AjaxResult ajaxResult = AjaxResult.success();
      if (req == null) {
         ajaxResult = AjaxResult.error("参数不能为空");
         return ajaxResult;
      } else if (StringUtils.isEmpty(req.getFlag())) {
         ajaxResult = AjaxResult.error("查询类型不能为空");
         return ajaxResult;
      } else if (!req.getFlag().equals("1") && !req.getFlag().equals("2")) {
         ajaxResult = AjaxResult.error("查询类型错误！");
         return ajaxResult;
      } else if (!req.getFlag().equals("2") || req.getStartDate() != null && req.getEndDate() != null) {
         if (StringUtils.isEmpty(req.getDeptCode())) {
            ajaxResult = AjaxResult.error("科室不能为空！");
            return ajaxResult;
         } else if (StringUtils.isEmpty(req.getFieldName())) {
            ajaxResult = AjaxResult.error("患者类型不能为空");
            return ajaxResult;
         } else {
            try {
               if (req.getEndDate() != null) {
                  Date hospitalizedDateEnd = DateUtils.addDays(req.getEndDate(), 1);
                  req.setEndDate(hospitalizedDateEnd);
               }

               List<keyPatientsDetailResp> respList = this.otherinfoService.selectKeyPatientsDetail(req);
               ajaxResult.put("data", respList);
            } catch (Exception e) {
               this.log.error("查询重点患者统计明细表出现异常", e);
               ajaxResult = AjaxResult.error("查询重点患者统计明细表出现异常");
            }

            return ajaxResult;
         }
      } else {
         ajaxResult = AjaxResult.error("出院时间不能为空！");
         return ajaxResult;
      }
   }
}
