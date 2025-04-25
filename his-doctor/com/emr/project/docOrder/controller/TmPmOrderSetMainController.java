package com.emr.project.docOrder.controller;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.docOrder.domain.TmPmOrderSetMain;
import com.emr.project.docOrder.domain.vo.OrderSearchVo;
import com.emr.project.docOrder.domain.vo.TmPmOrderSetMainVo;
import com.emr.project.docOrder.service.ITmPmOrderSetMainService;
import com.emr.project.esSearch.domain.DrugAndClin;
import com.emr.project.esSearch.service.IDrugAndClinService;
import com.emr.project.esSearch.service.IDrugStockService;
import com.emr.project.his.domain.vo.PharmacyVo;
import com.emr.project.system.domain.SysUser;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
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
@RequestMapping({"/tmSet/main"})
public class TmPmOrderSetMainController extends BaseController {
   @Autowired
   private ITmPmOrderSetMainService tmPmOrderSetMainService;
   @Autowired
   private IDrugAndClinService drugAndClinService;
   @Autowired
   private IDrugStockService drugStockService;

   @PreAuthorize("@ss.hasAnyPermi('tmSet:main:list,docOrder:order:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(TmPmOrderSetMain tmPmOrderSetMain) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<TmPmOrderSetMainVo> list = this.tmPmOrderSetMainService.selectTmPmOrderSetMainList(tmPmOrderSetMain);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询医嘱组套分页出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询医嘱组套分页出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasPermi('tmSet:main:query')")
   @GetMapping({"/{setCd}"})
   public AjaxResult getInfo(@PathVariable("setCd") String setCd) {
      return AjaxResult.success((Object)this.tmPmOrderSetMainService.selectTmPmOrderSetMainById(setCd));
   }

   @PreAuthorize("@ss.hasPermi('tmSet:main:add')")
   @Log(
      title = "医嘱组套主",
      businessType = BusinessType.INSERT
   )
   @PostMapping({"/mainAdd"})
   public AjaxResult mainAdd(@RequestBody TmPmOrderSetMainVo tmPmOrderSetMainVo) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         if (tmPmOrderSetMainVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(tmPmOrderSetMainVo.getSetName())) {
            flag = false;
            ajaxResult = AjaxResult.error("组套名称不能为空");
         }

         if (flag && StringUtils.isEmpty(tmPmOrderSetMainVo.getInputstrphtc())) {
            flag = false;
            ajaxResult = AjaxResult.error("拼音码不能为空");
         }

         if (flag && StringUtils.isEmpty(tmPmOrderSetMainVo.getShareType())) {
            flag = false;
            ajaxResult = AjaxResult.error("共享类型不能为空");
         }

         if (flag) {
            this.tmPmOrderSetMainService.insertSetMain(tmPmOrderSetMainVo);
            ajaxResult = AjaxResult.success((Object)tmPmOrderSetMainVo);
         }
      } catch (Exception e) {
         this.log.error("组套维护页面新增出现异常", e);
         ajaxResult = AjaxResult.error("组套维护页面新增出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('tmSet:main:add')")
   @Log(
      title = "医嘱组套主",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody TmPmOrderSetMainVo tmPmOrderSetMainVo) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         if (tmPmOrderSetMainVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(tmPmOrderSetMainVo.getSetName())) {
            flag = false;
            ajaxResult = AjaxResult.error("组套名称不能为空");
         }

         if (flag && StringUtils.isEmpty(tmPmOrderSetMainVo.getInputstrphtc())) {
            flag = false;
            ajaxResult = AjaxResult.error("拼音码不能为空");
         }

         if (flag && StringUtils.isEmpty(tmPmOrderSetMainVo.getShareType())) {
            flag = false;
            ajaxResult = AjaxResult.error("共享类型不能为空");
         }

         if (flag && StringUtils.isEmpty(tmPmOrderSetMainVo.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag && (tmPmOrderSetMainVo.getOrderSaveList() == null || tmPmOrderSetMainVo.getOrderSaveList().isEmpty())) {
            flag = false;
            ajaxResult = AjaxResult.error("医嘱信息不能为空");
         }

         List<OrderSearchVo> updateList = tmPmOrderSetMainVo.getOrderSaveList();

         for(int i = 0; i < updateList.size(); ++i) {
            OrderSearchVo setDetail = (OrderSearchVo)updateList.get(i);
            String itemFlag = setDetail.getOrderItemFlag();
            if (flag && itemFlag.equals("4") && setDetail.getOrderUsageDays() != null && setDetail.getOrderUsageDays().compareTo(BigDecimal.ONE) < 0) {
               flag = false;
               ajaxResult = AjaxResult.error("第" + (i + 1) + "行医嘱中，医嘱特殊标志是特殊，用药天数必须填写，且天数必须大于等于1");
               break;
            }

            if (flag && itemFlag.equals("4") && setDetail.getTakeDrugMode().equals("2")) {
               flag = false;
               ajaxResult = AjaxResult.error("第" + (i + 1) + "行医嘱中，医嘱特殊标志是特殊，此组医嘱的领药方式必须是【当天领药】，请修改后再保存");
               break;
            }
         }

         if (flag) {
            this.tmPmOrderSetMainService.insertTmPmOrderSetMain(tmPmOrderSetMainVo);
            DrugAndClin drugAndClin = new DrugAndClin();
            drugAndClin.setId(tmPmOrderSetMainVo.getSetCd());
            drugAndClin.setHospitalCode(tmPmOrderSetMainVo.getHospitalCode());
            drugAndClin.setCpNo(tmPmOrderSetMainVo.getSetCd());
            drugAndClin.setCpName(tmPmOrderSetMainVo.getSetName());
            drugAndClin.setOrderClassCode("00");
            drugAndClin.setOrderClassName("组套");
            drugAndClin.setInputstrphtc(tmPmOrderSetMainVo.getInputstrphtc());
            drugAndClin.setHerbalFlag("0");
            drugAndClin.setOrderFlag("1");
            this.drugAndClinService.addDrugAndClin(Arrays.asList(drugAndClin));
         }
      } catch (Exception e) {
         this.log.error("新增医嘱组套出现异常", e);
         ajaxResult = AjaxResult.error("新增医嘱组套出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('tmSet:main:edit')")
   @Log(
      title = "医嘱组套主",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody TmPmOrderSetMain tmPmOrderSetMain) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         if (tmPmOrderSetMain == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(tmPmOrderSetMain.getSetCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("医嘱编码不能为空");
         }

         if (flag && StringUtils.isEmpty(tmPmOrderSetMain.getSetName())) {
            flag = false;
            ajaxResult = AjaxResult.error("医嘱名称不能为空");
         }

         if (flag && StringUtils.isEmpty(tmPmOrderSetMain.getInputstrphtc())) {
            flag = false;
            ajaxResult = AjaxResult.error("拼音码不能为空");
         }

         if (flag && StringUtils.isEmpty(tmPmOrderSetMain.getShareType())) {
            flag = false;
            ajaxResult = AjaxResult.error("共享类型不能为空");
         }

         if (flag) {
            this.tmPmOrderSetMainService.updateTmPmOrderSetMain(tmPmOrderSetMain);
            ajaxResult = AjaxResult.success((Object)tmPmOrderSetMain);
         }
      } catch (Exception e) {
         this.log.error("修改医嘱组套出现异常", e);
         ajaxResult = AjaxResult.error("修改医嘱组套出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('tmSet:main:remove')")
   @Log(
      title = "医嘱组套主",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{setCd}"})
   public AjaxResult remove(@PathVariable String setCd) {
      AjaxResult ajaxResult = AjaxResult.success("删除成功");

      try {
         this.tmPmOrderSetMainService.deleteTmPmOrderSetMainById(setCd);
      } catch (Exception e) {
         this.log.error("删除医嘱组套出现异常", e);
         ajaxResult = AjaxResult.error("删除医嘱组套出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('tmSet:main:list,docOrder:check:list,docOrder:order:list')")
   @GetMapping({"/orderSetList"})
   public AjaxResult orderSetList(TmPmOrderSetMainVo tmPmOrderSetMain) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (tmPmOrderSetMain == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(tmPmOrderSetMain.getShareType())) {
            flag = false;
            ajaxResult = AjaxResult.error("共享类型不能为空");
         }

         if (flag && StringUtils.isEmpty(tmPmOrderSetMain.getSetType())) {
            flag = false;
            ajaxResult = AjaxResult.error("套餐类型不能为空");
         }

         if (flag) {
            List<TmPmOrderSetMainVo> list = this.tmPmOrderSetMainService.selectOrderSetListByClassCd(tmPmOrderSetMain);
            ajaxResult = AjaxResult.success((Object)list);
         }
      } catch (Exception e) {
         this.log.error("根据医嘱分类查询组套列表出现异常", e);
         ajaxResult = AjaxResult.error("根据医嘱分类查询组套列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('tmSet:main:edit')")
   @Log(
      title = "医嘱组套主",
      businessType = BusinessType.UPDATE
   )
   @PutMapping({"/editEnabled"})
   public AjaxResult editEnabled(@RequestBody TmPmOrderSetMainVo tmPmOrderSetMainVo) {
      AjaxResult ajaxResult = AjaxResult.success("修改成功");
      boolean flag = true;

      try {
         if (tmPmOrderSetMainVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(tmPmOrderSetMainVo.getSetCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("组套编码不能为空");
         }

         if (flag && StringUtils.isEmpty(tmPmOrderSetMainVo.getEnabled())) {
            flag = false;
            ajaxResult = AjaxResult.error("禁用状态不能为空");
         }

         if (flag) {
            this.tmPmOrderSetMainService.updateTmPmOrderSetMain(tmPmOrderSetMainVo);
         }
      } catch (Exception e) {
         this.log.error("禁用启用组套出现异常", e);
         ajaxResult = AjaxResult.error("禁用启用组套出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('tmSet:main:add')")
   @GetMapping({"/dictList"})
   public AjaxResult dictList() {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         Map<String, Object> map = this.tmPmOrderSetMainService.selectSetMainDictList();
         ajaxResult = AjaxResult.success((Object)map);
         SysUser sysUser = SecurityUtils.getLoginUser().getUser();
         List<PharmacyVo> pharmacyList = this.drugStockService.selectPharmacyListByDept(sysUser.getHospital().getOrgCode(), sysUser.getDept().getDeptCode());
         if (pharmacyList != null && pharmacyList.size() > 0) {
            List<String> execDeptCdList = (List)pharmacyList.stream().map((t) -> t.getPharmacyCode()).collect(Collectors.toList());
            this.drugAndClinService.syncDrugAndClinAllToEs(execDeptCdList, "0", (String)null, "1");
         }
      } catch (Exception e) {
         this.log.error("医嘱组套统一查询接口出现异常", e);
         ajaxResult = AjaxResult.error("医嘱组套统一查询接口出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('tmSet:main:saveAs')")
   @GetMapping({"/mainListByType"})
   public AjaxResult detailListByType(TmPmOrderSetMain tmPmOrderSetMain) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (StringUtils.isEmpty(tmPmOrderSetMain.getShareType())) {
            ajaxResult = AjaxResult.error("共享类型不能为空");
         } else {
            List<TmPmOrderSetMain> list = this.tmPmOrderSetMainService.selectDetailListByType(tmPmOrderSetMain);
            ajaxResult = AjaxResult.success((Object)list);
         }
      } catch (Exception e) {
         this.log.error("根据共享类型查询综合组套下拉出现异常", e);
         ajaxResult = AjaxResult.error("根据共享类型查询综合组套下拉出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('tmSet:main:saveAs')")
   @PostMapping({"/saveAsOrderSet"})
   public AjaxResult saveAsOrderSet(@RequestBody TmPmOrderSetMainVo tmPmOrderSetMainVo) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         if (tmPmOrderSetMainVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(tmPmOrderSetMainVo.getCopySetCd())) {
            if (StringUtils.isEmpty(tmPmOrderSetMainVo.getShareType())) {
               flag = false;
               ajaxResult = AjaxResult.error("共享类型不能为空");
            }

            if (flag && StringUtils.isEmpty(tmPmOrderSetMainVo.getInputstrphtc())) {
               flag = false;
               ajaxResult = AjaxResult.error("拼音码不能为空");
            }

            if (flag && StringUtils.isEmpty(tmPmOrderSetMainVo.getSetName())) {
               flag = false;
               ajaxResult = AjaxResult.error("组套名称不能为空");
            }

            if (flag && StringUtils.isEmpty(tmPmOrderSetMainVo.getEnabled())) {
               flag = false;
               ajaxResult = AjaxResult.error("启用状态不能为空");
            }
         }

         if (flag && StringUtils.isEmpty(tmPmOrderSetMainVo.getSetCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("当前选中组套编码不能为空");
         }

         if (flag) {
            this.tmPmOrderSetMainService.saveAsOrderSet(tmPmOrderSetMainVo);
            TmPmOrderSetMain tmPmOrderSetMain = this.tmPmOrderSetMainService.selectTmPmOrderSetMainById(tmPmOrderSetMainVo.getSetCd());
            ajaxResult = AjaxResult.success((Object)tmPmOrderSetMain);
         }
      } catch (Exception e) {
         this.log.error("另存为组套出现异常", e);
         ajaxResult = AjaxResult.error("另存为组套出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('tmSet:main:edit')")
   @PutMapping({"/updateGroupNos"})
   public AjaxResult updateGroupNos(@RequestBody List setDetailList) {
      try {
         if (CollectionUtils.isEmpty(setDetailList)) {
            return AjaxResult.error("参数不能为空！");
         } else {
            for(TmPmOrderSetMain main : setDetailList) {
               if (main.getSetCd() == null) {
                  return AjaxResult.error("组套编码不能为空！");
               }

               if (main.getGroupSort() == null) {
                  return AjaxResult.error("组套序号不能为空！");
               }
            }

            this.tmPmOrderSetMainService.updateGroupNos(setDetailList);
            return AjaxResult.success();
         }
      } catch (Exception e) {
         this.log.error("更新组号出现异常,", e);
         return AjaxResult.error("更新组号出现异常,请联系管理员！");
      }
   }
}
