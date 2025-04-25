package com.emr.project.dr.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.dr.domain.DrHandoverDetail;
import com.emr.project.dr.domain.vo.DrHandoverDetailVo;
import com.emr.project.dr.domain.vo.DrHandoverPrintListVo;
import com.emr.project.dr.domain.vo.DrHandoverPrintVo;
import com.emr.project.dr.service.IDrHandoverDetailService;
import com.emr.project.tmpm.domain.PrintTmplInfo;
import com.emr.project.tmpm.service.IPrintTmplInfoService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/handover/detail"})
public class DrHandoverDetailController extends BaseController {
   @Autowired
   private IDrHandoverDetailService drHandoverDetailService;
   @Autowired
   private IPrintTmplInfoService printTmplInfoService;

   @PreAuthorize("@ss.hasAnyPermi('handover:main:info,handover:detail:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(DrHandoverDetailVo drHandoverDetail) {
      List<DrHandoverDetailVo> list = new ArrayList();

      try {
         if (drHandoverDetail.getMainId() != null) {
            this.startPage();
            list = this.drHandoverDetailService.selectDrHandoverDetailList(drHandoverDetail);
         }
      } catch (Exception e) {
         this.log.error("查询医师交接班详细信息列表出现异常，", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasAnyPermi('handover:main:info,handover:detail:list')")
   @GetMapping({"/printList"})
   public AjaxResult printList(DrHandoverDetailVo drHandoverDetailVo) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (drHandoverDetailVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && drHandoverDetailVo.getMainId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("交接班id不能为空");
         }

         if (flag) {
            DrHandoverPrintListVo printVo = this.drHandoverDetailService.selectDrPrintList(drHandoverDetailVo);
            ajaxResult = AjaxResult.success((Object)printVo);
         }
      } catch (Exception e) {
         this.log.error("查询医师交接班详细信息列表出现异常，", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('handover:main:info,handover:detail:list')")
   @GetMapping({"/listByType"})
   public AjaxResult listByType(DrHandoverDetail drHandoverDetail) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      Boolean flag = true;

      try {
         if (drHandoverDetail == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && drHandoverDetail.getMainId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("交班id不能为空");
         }

         if (flag && StringUtils.isEmpty(drHandoverDetail.getPatientTypeCode())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者标识编码不能为空");
         }

         if (flag) {
            List<DrHandoverDetailVo> list = this.drHandoverDetailService.selectListByType(drHandoverDetail);
            ajaxResult = AjaxResult.success("查询成功", list);
         }
      } catch (Exception e) {
         this.log.error("查询医师交接班详细信息列表出现异常,", e);
         ajaxResult = AjaxResult.error("查询医师交接班详细信息列表");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('handover:detail:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.drHandoverDetailService.selectDrHandoverDetailById(id));
   }

   @PreAuthorize("@ss.hasPermi('handover:detail:save')")
   @Log(
      title = "医师交接班详细信息",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody DrHandoverDetailVo drHandoverDetail) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         if (flag && drHandoverDetail.getMainId() == null) {
            flag = false;
            ajaxResult = AjaxResult.success("交班id不能为空");
         }

         if (flag && (drHandoverDetail.getDetailList() == null || drHandoverDetail.getDetailList() != null && drHandoverDetail.getDetailList().isEmpty())) {
            flag = false;
            ajaxResult = AjaxResult.success("患者信息集合不能为空");
         }

         if (flag) {
            Map<String, Integer> map = this.drHandoverDetailService.saveDrHandoverDetail(drHandoverDetail.getDetailList(), drHandoverDetail.getMainId());
            ajaxResult = AjaxResult.success((Object)map);
         }
      } catch (Exception e) {
         this.log.error("保存患者信息出现异常，", e);
         ajaxResult = AjaxResult.error("保存患者信息出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('handover:detail:edit')")
   @Log(
      title = "医师交接班详细信息",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody DrHandoverDetail drHandoverDetail) {
      return this.toAjax(this.drHandoverDetailService.updateDrHandoverDetail(drHandoverDetail));
   }

   @PreAuthorize("@ss.hasPermi('handover:detail:remove')")
   @Log(
      title = "医师交接班详细信息",
      businessType = BusinessType.UPDATE
   )
   @PutMapping({"/delDetail"})
   public AjaxResult remove(@RequestBody DrHandoverDetail drHandoverDetail) {
      AjaxResult ajaxResult = AjaxResult.success("删除成功");
      boolean flag = true;

      try {
         if (drHandoverDetail == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(drHandoverDetail.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag && drHandoverDetail.getMainId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("交接班id不能为空");
         }

         if (flag) {
            Map<String, Integer> map = this.drHandoverDetailService.deleteDrHandoverDetail(drHandoverDetail);
            ajaxResult = AjaxResult.success((Object)map);
         }
      } catch (Exception e) {
         this.log.error("删除交接班患者详细信息出现异常", e);
         ajaxResult = AjaxResult.error("删除交接班患者详细信息出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('handover:main:recordList')")
   @GetMapping({"/mainList"})
   public AjaxResult mainList(DrHandoverDetail drHandoverDetail) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (drHandoverDetail.getMainId() == null) {
            ajaxResult = AjaxResult.error("交班记录id不能为空");
         } else {
            List<DrHandoverDetailVo> list = this.drHandoverDetailService.selectDetailListByMainId(drHandoverDetail.getMainId());
            ajaxResult = AjaxResult.success((Object)list);
         }
      } catch (Exception e) {
         this.log.error("查询交班记录下的所有患者列表出现异常,", e);
         ajaxResult = AjaxResult.error("查询交班记录下的所有患者列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('handover:detail:save')")
   @GetMapping({"/isSavePatient"})
   public AjaxResult isSavePatient(DrHandoverDetailVo drHandoverDetail) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (drHandoverDetail == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && drHandoverDetail.getMainId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("交班记录id不能为空");
         }

         if (flag && StringUtils.isEmpty(drHandoverDetail.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag) {
            List<DrHandoverDetailVo> list = this.drHandoverDetailService.selectIsSavePatientId(drHandoverDetail);
            ajaxResult = AjaxResult.success((Object)list);
            ajaxResult.put("flag", list == null || list.isEmpty());
         }
      } catch (Exception e) {
         this.log.error("判断患者是否可以保存出现异常,", e);
         ajaxResult = AjaxResult.error("判断患者是否可以保存出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('handover:main:info,handover:detail:list')")
   @GetMapping({"/printDetail"})
   public AjaxResult printDetail(DrHandoverDetailVo drHandoverDetailVo) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (drHandoverDetailVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && drHandoverDetailVo.getMainId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("交接班id不能为空");
         }

         if (flag) {
            DrHandoverPrintVo printVo = this.drHandoverDetailService.printDetail(drHandoverDetailVo);
            ajaxResult = AjaxResult.success((Object)printVo);
         }
      } catch (Exception e) {
         this.log.error("查询打印医师交接班详细信息列表出现异常，", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('handover:main:info,handover:detail:list')")
   @GetMapping({"/getPrintTmpl"})
   public AjaxResult getPrintTmpl() {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         List<String> typeCodeList = new ArrayList();
         typeCodeList.add("100025001");
         List<PrintTmplInfo> printTmplInfos = this.printTmplInfoService.selectTmPmPrintTmplInfoByCodes(typeCodeList);
         ajaxResult = AjaxResult.success((Object)printTmplInfos);
      } catch (Exception e) {
         this.log.error("查询打印医师交接班模板出现异常，", e);
         ajaxResult = AjaxResult.error("查询打印医师交接班模板出现异常");
      }

      return ajaxResult;
   }
}
