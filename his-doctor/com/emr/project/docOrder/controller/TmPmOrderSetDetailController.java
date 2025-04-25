package com.emr.project.docOrder.controller;

import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.docOrder.domain.TmPmOrderSetDetail;
import com.emr.project.docOrder.domain.vo.OrderSearchVo;
import com.emr.project.docOrder.domain.vo.TmPmOrderSetDetailVo;
import com.emr.project.docOrder.service.ITmPmOrderSetDetailService;
import com.emr.project.esSearch.domain.DrugAndClin;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
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
@RequestMapping({"/tmSet/detail"})
public class TmPmOrderSetDetailController extends BaseController {
   @Autowired
   private ITmPmOrderSetDetailService tmPmOrderSetDetailService;

   @PreAuthorize("@ss.hasPermi('tmSet:detail:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(TmPmOrderSetDetailVo tmPmOrderSetDetail) {
      this.startPage();
      List<TmPmOrderSetDetail> list = this.tmPmOrderSetDetailService.selectTmPmOrderSetDetailList(tmPmOrderSetDetail);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('tmSet:detail:export')")
   @Log(
      title = "医嘱组套明细",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(TmPmOrderSetDetailVo tmPmOrderSetDetail) {
      List<TmPmOrderSetDetail> list = this.tmPmOrderSetDetailService.selectTmPmOrderSetDetailList(tmPmOrderSetDetail);
      ExcelUtil<TmPmOrderSetDetail> util = new ExcelUtil(TmPmOrderSetDetail.class);
      return util.exportExcel(list, "医嘱组套明细数据");
   }

   @PreAuthorize("@ss.hasPermi('tmSet:detail:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.tmPmOrderSetDetailService.selectTmPmOrderSetDetailById(id));
   }

   @PreAuthorize("@ss.hasAnyPermi('tmSet:detail:remove,tmSet:main:remove')")
   @Log(
      title = "医嘱组套明细",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      AjaxResult ajaxResult = AjaxResult.success("删除成功");

      try {
         this.tmPmOrderSetDetailService.deleteTmPmOrderSetDetailByIds(ids);
      } catch (Exception e) {
         this.log.error("删除医嘱组套明细出现异常", e);
         ajaxResult = AjaxResult.error("删除医嘱组套出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('tmSet:detail:list,docOrder:order:list,tmSet:main:list')")
   @GetMapping({"/detailList"})
   public AjaxResult detailList(TmPmOrderSetDetail tmPmOrderSetDetail) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (StringUtils.isEmpty(tmPmOrderSetDetail.getSetCd())) {
            ajaxResult = AjaxResult.error("组套编码不能为空");
         } else {
            List<TmPmOrderSetDetail> list = this.tmPmOrderSetDetailService.selectTmPmOrderSetDetailBySetCd(tmPmOrderSetDetail.getSetCd());
            ajaxResult = AjaxResult.success((Object)list);
         }
      } catch (Exception e) {
         this.log.error("查询组套明细出现异常", e);
         ajaxResult = AjaxResult.error("查询组套明细出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('docOrder:order:list')")
   @GetMapping({"/setAsOrderList"})
   public AjaxResult setAsOrderList(TmPmOrderSetDetail tmPmOrderSetDetail) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (StringUtils.isEmpty(tmPmOrderSetDetail.getSetCd())) {
            ajaxResult = AjaxResult.error("组套编码不能为空");
         } else {
            List<OrderSearchVo> list = this.tmPmOrderSetDetailService.selectSetAsOrderList(tmPmOrderSetDetail.getSetCd());
            ajaxResult = AjaxResult.success((Object)list);
         }
      } catch (Exception e) {
         this.log.error("查询组套明细出现异常", e);
         ajaxResult = AjaxResult.error("查询组套明细出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('tmSet:detail:add')")
   @PostMapping({"/addDetail"})
   public AjaxResult addDetail(@RequestBody TmPmOrderSetDetailVo tmPmOrderSetDetail) {
      AjaxResult ajaxResult = AjaxResult.success("添加成功");

      try {
         ajaxResult = this.verify(tmPmOrderSetDetail);
         Boolean flag = ajaxResult.get("code").equals(200);
         if (flag && tmPmOrderSetDetail.getMasterOrder().equals("2")) {
            if (tmPmOrderSetDetail.getRowId() == null) {
               flag = false;
               ajaxResult = AjaxResult.error("添加子医嘱请选中一条已添加过的医嘱");
            }

            if (flag && !tmPmOrderSetDetail.getItemClassCd().equals("01")) {
               flag = false;
               ajaxResult = AjaxResult.error("子医嘱只能选择药疗项目");
            }
         }

         if (flag) {
            this.tmPmOrderSetDetailService.addTmPmOrderSetDetail(tmPmOrderSetDetail);
            ajaxResult.put("tmPmOrderSetDetail", tmPmOrderSetDetail);
         }
      } catch (Exception e) {
         this.log.error("添加医嘱组套详情出现异常", e);
         ajaxResult = AjaxResult.error("添加医嘱组套详情出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('tmSet:detail:add')")
   @PostMapping({"/insertDetail"})
   public AjaxResult insertDetail(@RequestBody TmPmOrderSetDetailVo tmPmOrderSetDetail) {
      AjaxResult ajaxResult = AjaxResult.success("插入成功");

      try {
         ajaxResult = this.verify(tmPmOrderSetDetail);
         Boolean flag = ajaxResult.get("code").equals(200);
         if (tmPmOrderSetDetail.getRowId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("插入医嘱时请选中一条医嘱");
         }

         if (flag && tmPmOrderSetDetail.getMasterOrder().equals("2") && flag && !tmPmOrderSetDetail.getItemClassCd().equals("01")) {
            flag = false;
            ajaxResult = AjaxResult.error("子医嘱只能选择药疗项目");
         }

         if (flag) {
            this.tmPmOrderSetDetailService.insertTmPmOrderSetDetail(tmPmOrderSetDetail);
            ajaxResult = AjaxResult.success((Object)tmPmOrderSetDetail);
         }
      } catch (Exception e) {
         this.log.error("插入医嘱组套详情出现异常", e);
         ajaxResult = AjaxResult.error("插入医嘱组套详情出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('tmSet:detail:edit')")
   @PutMapping({"/edit"})
   public AjaxResult edit(@RequestBody TmPmOrderSetDetailVo tmPmOrderSetDetail) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");

      try {
         ajaxResult = this.verify(tmPmOrderSetDetail);
         Boolean flag = ajaxResult.get("code").equals(200);
         if (flag) {
            this.tmPmOrderSetDetailService.updateTmPmOrderSetDetail(tmPmOrderSetDetail);
            TmPmOrderSetDetail detail = this.tmPmOrderSetDetailService.selectTmPmOrderSetDetailById(tmPmOrderSetDetail.getId());
            ajaxResult = AjaxResult.success((Object)detail);
         }
      } catch (Exception e) {
         this.log.error("修改医嘱详情出现异常", e);
         ajaxResult = AjaxResult.error("修改医嘱详情出现异常");
      }

      return ajaxResult;
   }

   public AjaxResult verify(TmPmOrderSetDetailVo tmPmOrderSetDetail) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;
      if (tmPmOrderSetDetail == null) {
         flag = false;
         ajaxResult = AjaxResult.error("参数不能为空");
      }

      if (flag && StringUtils.isEmpty(tmPmOrderSetDetail.getItemClassCd())) {
         flag = false;
         ajaxResult = AjaxResult.error("项目类别不能为空");
      }

      if (flag && StringUtils.isEmpty(tmPmOrderSetDetail.getMasterOrder())) {
         flag = false;
         ajaxResult = AjaxResult.error("是否主医嘱不能为空");
      }

      if (flag && StringUtils.isEmpty(tmPmOrderSetDetail.getItemCd())) {
         flag = false;
         ajaxResult = AjaxResult.error("项目编码不能为空");
      }

      if (flag && StringUtils.isEmpty(tmPmOrderSetDetail.getItemName())) {
         flag = false;
         ajaxResult = AjaxResult.error("医嘱内容不能为空");
      }

      if (flag && StringUtils.isEmpty(tmPmOrderSetDetail.getItemFlag())) {
         flag = false;
         ajaxResult = AjaxResult.error("特殊标志不能为空");
      }

      if (flag && tmPmOrderSetDetail.getItemClassCd().equals("01")) {
         if (flag && StringUtils.isEmpty(tmPmOrderSetDetail.getItemOrderUsageWay())) {
            flag = false;
            ajaxResult = AjaxResult.error("用法编码不能为空");
         }

         if (flag && StringUtils.isEmpty(tmPmOrderSetDetail.getItemOrderUsageWayName())) {
            flag = false;
            ajaxResult = AjaxResult.error("用法名称不能为空");
         }

         if (flag && StringUtils.isEmpty(tmPmOrderSetDetail.getFreqCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("频率不能为空");
         }

         if (flag && StringUtils.isEmpty(tmPmOrderSetDetail.getFreqName())) {
            flag = false;
            ajaxResult = AjaxResult.error("频率名称不能为空");
         }
      }

      if (flag && tmPmOrderSetDetail.getItemClassCd().equals("02")) {
         if (flag && StringUtils.isEmpty(tmPmOrderSetDetail.getExamPartCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("部位编码不能为空");
         }

         if (flag && StringUtils.isEmpty(tmPmOrderSetDetail.getExamPartName())) {
            flag = false;
            ajaxResult = AjaxResult.error("部位名称不能为空");
         }
      }

      if (flag && tmPmOrderSetDetail.getItemClassCd().equals("03")) {
         if (flag && StringUtils.isEmpty(tmPmOrderSetDetail.getSpecCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("标本编码不能为空");
         }

         if (flag && StringUtils.isEmpty(tmPmOrderSetDetail.getSpecName())) {
            flag = false;
            ajaxResult = AjaxResult.error("标本部位名称不能为空");
         }
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('tmSet:detail:list,docOrder:check:list,tmSet:main:list')")
   @GetMapping({"/checkDetailList"})
   public AjaxResult checkDetailList(TmPmOrderSetDetail tmPmOrderSetDetail) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (StringUtils.isEmpty(tmPmOrderSetDetail.getSetCd())) {
            ajaxResult = AjaxResult.error("组套编码不能为空");
         } else {
            List<DrugAndClin> list = this.tmPmOrderSetDetailService.selectCheckDetailListBySetCd(tmPmOrderSetDetail.getSetCd());
            ajaxResult = AjaxResult.success((Object)list);
         }
      } catch (Exception e) {
         this.log.error("查询组套明细出现异常", e);
         ajaxResult = AjaxResult.error("查询组套明细出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('tmSet:detail:edit')")
   @PutMapping({"/updateGroupNos"})
   public AjaxResult updateGroupNos(@RequestBody List setDetailList) {
      AjaxResult ajaxResult = AjaxResult.success("操作成功");
      boolean flag = true;

      try {
         if (flag && CollectionUtils.isEmpty(setDetailList)) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空！");
         }

         if (flag) {
            for(TmPmOrderSetDetail detail : setDetailList) {
               if (flag && detail.getId() == null) {
                  flag = false;
                  ajaxResult = AjaxResult.error("组套明细id不能为空！");
               }

               if (flag && detail.getGroupNo() == null) {
                  flag = false;
                  ajaxResult = AjaxResult.error("组套明细组号不能为空！");
               }
            }
         }

         if (flag) {
            this.tmPmOrderSetDetailService.updateGroupNos(setDetailList);
         }
      } catch (Exception e) {
         this.log.error("更新组号出现异常,", e);
         ajaxResult = AjaxResult.error("更新组号出现异常,请联系管理员！");
      }

      return ajaxResult;
   }
}
