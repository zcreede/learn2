package com.emr.project.tmpm.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.docOrder.domain.vo.HerbSaveVo;
import com.emr.project.tmpm.domain.TmPmCipherDetail;
import com.emr.project.tmpm.domain.TmPmCipherMain;
import com.emr.project.tmpm.domain.vo.TmPmCipherMainVo;
import com.emr.project.tmpm.service.ITmPmCipherMainService;
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
@RequestMapping({"/cipher/main"})
public class TmPmCipherMainController extends BaseController {
   @Autowired
   private ITmPmCipherMainService tmPmCipherMainService;

   @PreAuthorize("@ss.hasAnyPermi('cipher:main:list,docOrder:herb:list')")
   @GetMapping({"/list"})
   public AjaxResult list(TmPmCipherMainVo tmPmCipherMain) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         tmPmCipherMain.setEnabled("1");
         List<TmPmCipherMainVo> list = this.tmPmCipherMainService.selectTmPmCipherMainList(tmPmCipherMain);
         ajaxResult = AjaxResult.success((Object)list);
      } catch (Exception e) {
         this.log.error("查询协定处方列表出现异常", e);
         ajaxResult = AjaxResult.error("查询协定处方列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('cipher:main:list')")
   @GetMapping({"/getList"})
   public AjaxResult getList(TmPmCipherMainVo tmPmCipherMain) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         List<TmPmCipherMainVo> list = this.tmPmCipherMainService.selectCipherMainList(tmPmCipherMain);
         ajaxResult = AjaxResult.success((Object)list);
      } catch (Exception e) {
         this.log.error("查询协定处方列表出现异常", e);
         ajaxResult = AjaxResult.error("查询协定处方列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('cipher:main:query')")
   @GetMapping({"/{cipherCd}"})
   public AjaxResult getInfo(@PathVariable("cipherCd") String cipherCd) {
      return AjaxResult.success((Object)this.tmPmCipherMainService.selectTmPmCipherMainById(cipherCd));
   }

   @PreAuthorize("@ss.hasAnyPermi('cipher:main:add')")
   @Log(
      title = "协定处方主",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody HerbSaveVo herbSaveVo) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         if (herbSaveVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(herbSaveVo.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag && StringUtils.isEmpty(herbSaveVo.getCipherName())) {
            flag = false;
            ajaxResult = AjaxResult.error("处方名称不能为空");
         }

         if (flag && StringUtils.isEmpty(herbSaveVo.getShareType())) {
            flag = false;
            ajaxResult = AjaxResult.error("共享方式不能为空");
         }

         if (flag && StringUtils.isEmpty(herbSaveVo.getInputstrphtc())) {
            flag = false;
            ajaxResult = AjaxResult.error("拼音码不能为空");
         }

         if (flag) {
            this.tmPmCipherMainService.saveTmPmCipherMain(herbSaveVo);
         }
      } catch (Exception e) {
         this.log.error("保存协定处方出现异常", e);
         ajaxResult = AjaxResult.error("保存协定处方出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('cipher:main:add')")
   @Log(
      title = "协定处方主",
      businessType = BusinessType.INSERT
   )
   @PostMapping({"/addMain"})
   public AjaxResult addMain(@RequestBody TmPmCipherMain tmPmCipherMain) {
      AjaxResult ajaxResult = AjaxResult.success("添加成功");
      boolean flag = true;

      try {
         if (tmPmCipherMain == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(tmPmCipherMain.getCipherName())) {
            flag = false;
            ajaxResult = AjaxResult.error("处方名称不能为空");
         }

         if (flag && StringUtils.isEmpty(tmPmCipherMain.getShareType())) {
            flag = false;
            ajaxResult = AjaxResult.error("共享方式不能为空");
         }

         if (flag && StringUtils.isEmpty(tmPmCipherMain.getInputstrphtc())) {
            flag = false;
            ajaxResult = AjaxResult.error("拼音码不能为空");
         }

         if (flag && StringUtils.isEmpty(tmPmCipherMain.getEnabled())) {
            flag = false;
            ajaxResult = AjaxResult.error("启用状态不能为空");
         }

         if (flag) {
            this.tmPmCipherMainService.insertTmPmCipherMain(tmPmCipherMain);
            ajaxResult = AjaxResult.success((Object)tmPmCipherMain);
         }
      } catch (Exception e) {
         this.log.error("添加协定处方出现异常", e);
         ajaxResult = AjaxResult.error("添加协定处方出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('cipher:main:edit')")
   @Log(
      title = "协定处方主",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody TmPmCipherMain tmPmCipherMain) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         if (tmPmCipherMain == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(tmPmCipherMain.getShareType())) {
            flag = false;
            ajaxResult = AjaxResult.error("共享类型不能为空");
         }

         if (flag && StringUtils.isEmpty(tmPmCipherMain.getCipherName())) {
            flag = false;
            ajaxResult = AjaxResult.error("协方名称不能为空");
         }

         if (flag && StringUtils.isEmpty(tmPmCipherMain.getInputstrphtc())) {
            flag = false;
            ajaxResult = AjaxResult.error("拼音码不能为空");
         }

         if (flag) {
            this.tmPmCipherMainService.updateTmPmCipherMain(tmPmCipherMain);
            ajaxResult = AjaxResult.success((Object)tmPmCipherMain);
         }
      } catch (Exception e) {
         this.log.error("修改协方出现异常", e);
         ajaxResult = AjaxResult.error("修改协方出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('cipher:main:remove')")
   @Log(
      title = "协定处方主",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{cipherCd}"})
   public AjaxResult remove(@PathVariable String cipherCd) {
      AjaxResult ajaxResult = AjaxResult.success("删除成功");

      try {
         this.tmPmCipherMainService.deleteTmPmCipherMainById(cipherCd);
      } catch (Exception e) {
         this.log.error("删除协定处方主表出现异常", e);
         ajaxResult = AjaxResult.error("删除协定处方主表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('docOrder:herb:list')")
   @GetMapping({"/cipHerInfoByCd"})
   public AjaxResult cipHerInfoByCd(TmPmCipherMainVo tmPmCipherMain) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (tmPmCipherMain == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(tmPmCipherMain.getCipherCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("处方编码不能为空");
         }

         if (flag && StringUtils.isEmpty(tmPmCipherMain.getDeptCode())) {
            flag = false;
            ajaxResult = AjaxResult.error("领药药房不能为空");
         }

         if (flag) {
            TmPmCipherMainVo result = this.tmPmCipherMainService.selectCipHerInfoByCd(tmPmCipherMain);
            ajaxResult = AjaxResult.success((Object)result);
         }
      } catch (Exception e) {
         this.log.error("根据协方编码查询协方详细信息出现异常", e);
         ajaxResult = AjaxResult.error("根据协方编码查询协方详细信息出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('cipher:main:edit')")
   @Log(
      title = "协定处方主",
      businessType = BusinessType.UPDATE
   )
   @PutMapping({"/editFlag"})
   public AjaxResult editFlag(@RequestBody TmPmCipherMain tmPmCipherMain) {
      AjaxResult ajaxResult = AjaxResult.success("修改成功");
      boolean flag = true;

      try {
         if (tmPmCipherMain == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(tmPmCipherMain.getCipherCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("协定处方编号不能为空");
         }

         if (flag && StringUtils.isEmpty(tmPmCipherMain.getEnabled())) {
            flag = false;
            ajaxResult = AjaxResult.error("启用状态不能为空");
         }

         if (flag) {
            this.tmPmCipherMainService.updateCipherMainFlag(tmPmCipherMain);
         }
      } catch (Exception e) {
         this.log.error("协定处方启用禁用出现异常", e);
         ajaxResult = AjaxResult.error("协定处方启用禁用出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('cipher:main:saveAs')")
   @Log(
      title = "草药协定处方明细",
      businessType = BusinessType.INSERT
   )
   @PostMapping({"/saveAs"})
   public AjaxResult saveAs(@RequestBody TmPmCipherMainVo tmPmCipherMain) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         if (tmPmCipherMain == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         TmPmCipherMain saveAsInfo = tmPmCipherMain.getSaveAsInfo();
         if (flag && saveAsInfo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("当前选中协方信息不能为空");
         }

         if (flag && StringUtils.isEmpty(saveAsInfo.getUsageWayCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("用法编码不能为空");
         }

         if (flag && StringUtils.isEmpty(saveAsInfo.getUsageWayName())) {
            flag = false;
            ajaxResult = AjaxResult.error("用法名称不能为空");
         }

         if (flag && StringUtils.isEmpty(saveAsInfo.getFreqCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("频率编码不能为空");
         }

         if (flag && StringUtils.isEmpty(saveAsInfo.getFreqName())) {
            flag = false;
            ajaxResult = AjaxResult.error("频率名称不能为空");
         }

         if (flag && StringUtils.isEmpty(saveAsInfo.getDecoctMethod())) {
            flag = false;
            ajaxResult = AjaxResult.error("煎药方式不能为空");
         }

         if (flag && saveAsInfo.getHerbalDose() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("剂数不能为空");
         }

         List<TmPmCipherDetail> detailVoList = tmPmCipherMain.getInsertList();
         if (flag && (detailVoList == null || detailVoList.isEmpty())) {
            flag = false;
            ajaxResult = AjaxResult.error("药品信息不能为空");
         }

         if (flag) {
            for(TmPmCipherDetail detailVo : detailVoList) {
               if (flag && StringUtils.isEmpty(detailVo.getDrugCd())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("药品编码不能为空");
               }

               if (flag && StringUtils.isEmpty(detailVo.getDrugName())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("医嘱内容不能为空");
               }

               if (flag && detailVo.getActualUsage() == null) {
                  flag = false;
                  ajaxResult = AjaxResult.error("用量不能为空");
               }
            }
         }

         if (flag) {
            TmPmCipherMain info = this.tmPmCipherMainService.saveAsCipherInfo(tmPmCipherMain);
            ajaxResult = AjaxResult.success((Object)info);
         }
      } catch (Exception e) {
         this.log.error("另存草药协定处方出现异常", e);
         ajaxResult = AjaxResult.error("另存草药协定处方出现异常");
      }

      return ajaxResult;
   }
}
