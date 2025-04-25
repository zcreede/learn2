package com.emr.project.tmpm.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.tmpm.domain.TmPmCipherDetail;
import com.emr.project.tmpm.domain.vo.TmPmCipherDetailVo;
import com.emr.project.tmpm.domain.vo.TmPmCipherMainVo;
import com.emr.project.tmpm.service.ITmPmCipherDetailService;
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
@RequestMapping({"/cipher/detail"})
public class TmPmCipherDetailController extends BaseController {
   @Autowired
   private ITmPmCipherDetailService tmPmCipherDetailService;

   @PreAuthorize("@ss.hasPermi('cipher:detail:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(TmPmCipherDetail tmPmCipherDetail) {
      this.startPage();
      List<TmPmCipherDetail> list = this.tmPmCipherDetailService.selectTmPmCipherDetailList(tmPmCipherDetail);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('cipher:detail:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.tmPmCipherDetailService.selectTmPmCipherDetailById(id));
   }

   @PreAuthorize("@ss.hasPermi('cipher:detail:save')")
   @Log(
      title = "草药协定处方明细",
      businessType = BusinessType.INSERT
   )
   @PostMapping({"/saveCipher"})
   public AjaxResult saveCipher(@RequestBody TmPmCipherMainVo tmPmCipherMain) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         if (tmPmCipherMain == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(tmPmCipherMain.getUsageWayCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("用法编码不能为空");
         }

         if (flag && StringUtils.isEmpty(tmPmCipherMain.getUsageWayName())) {
            flag = false;
            ajaxResult = AjaxResult.error("用法名称不能为空");
         }

         if (flag && StringUtils.isEmpty(tmPmCipherMain.getFreqCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("频率编码不能为空");
         }

         if (flag && StringUtils.isEmpty(tmPmCipherMain.getFreqName())) {
            flag = false;
            ajaxResult = AjaxResult.error("频率名称不能为空");
         }

         if (flag && StringUtils.isEmpty(tmPmCipherMain.getDecoctMethod())) {
            flag = false;
            ajaxResult = AjaxResult.error("煎药方式不能为空");
         }

         if (flag && tmPmCipherMain.getHerbalDose() == null) {
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
            this.tmPmCipherDetailService.saveTmPmCipher(tmPmCipherMain);
         }
      } catch (Exception e) {
         this.log.error("保存协方详细信息出现异常", e);
         ajaxResult = AjaxResult.error("保存协方详细信息出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('cipherdetail:edit')")
   @Log(
      title = "草药协定处方明细",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody TmPmCipherDetail tmPmCipherDetail) {
      return this.toAjax(this.tmPmCipherDetailService.updateTmPmCipherDetail(tmPmCipherDetail));
   }

   @PreAuthorize("@ss.hasPermi('cipher:detail:remove')")
   @Log(
      title = "草药协定处方明细",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.tmPmCipherDetailService.deleteTmPmCipherDetailByIds(ids));
   }

   @PreAuthorize("@ss.hasAnyPermi('docOrder:herb:list')")
   @GetMapping({"/detailList"})
   public AjaxResult detailList(TmPmCipherDetailVo tmPmCipherDetail) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (tmPmCipherDetail == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(tmPmCipherDetail.getCipherCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("协定处方编码不能为空");
         }

         if (flag && StringUtils.isEmpty(tmPmCipherDetail.getExecDeptCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("领药药房不能为空");
         }

         if (flag) {
            List<TmPmCipherDetailVo> list = this.tmPmCipherDetailService.selectTmPmCipherDetailByCdList(tmPmCipherDetail);
            ajaxResult = AjaxResult.success((Object)list);
         }
      } catch (Exception e) {
         this.log.error("根据协定处方编码查询详情集合出现异常", e);
         ajaxResult = AjaxResult.error("根据协定处方编码查询详情集合出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('docOrder:herb:list')")
   @GetMapping({"/detailDrugList"})
   public AjaxResult detailDrugList(TmPmCipherDetailVo tmPmCipherDetail) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (tmPmCipherDetail == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && tmPmCipherDetail.getIdList() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("id集合不能为空");
         }

         if (flag && StringUtils.isEmpty(tmPmCipherDetail.getExecDeptCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("领药药房不能为空");
         }

         if (flag) {
            List<TmPmCipherDetailVo> list = this.tmPmCipherDetailService.selectDetailDrugList(tmPmCipherDetail);
            ajaxResult = AjaxResult.success((Object)list);
         }
      } catch (Exception e) {
         this.log.error("根据详情集合id查询详情药品集合出现异常", e);
         ajaxResult = AjaxResult.error("根据详情集合id查询详情药品集合出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('docOrder:herb:list,cipher:main:list')")
   @GetMapping({"/detailInfoList"})
   public AjaxResult detailInfoList(TmPmCipherDetail tmPmCipherDetail) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (tmPmCipherDetail == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(tmPmCipherDetail.getCipherCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("协定处方编码不能为空");
         }

         if (flag) {
            List<TmPmCipherDetailVo> list = this.tmPmCipherDetailService.selectTmPmCipherDetailByCipherCd(tmPmCipherDetail.getCipherCd());
            ajaxResult = AjaxResult.success((Object)list);
         }
      } catch (Exception e) {
         this.log.error("根据协定处方编码查询详情集合出现异常", e);
         ajaxResult = AjaxResult.error("根据协定处方编码查询详情集合出现异常");
      }

      return ajaxResult;
   }
}
