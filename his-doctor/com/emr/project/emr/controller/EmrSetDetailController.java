package com.emr.project.emr.controller;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.emr.domain.EmrSetDetail;
import com.emr.project.emr.domain.EmrSetMain;
import com.emr.project.emr.domain.vo.EmrSetDetailVo;
import com.emr.project.emr.service.IEmrSetDetailService;
import com.emr.project.emr.service.IEmrSetMainService;
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
@RequestMapping({"/emrSet/detail"})
public class EmrSetDetailController extends BaseController {
   @Autowired
   private IEmrSetDetailService emrSetDetailService;
   @Autowired
   private IEmrSetMainService emrSetMainService;

   @PreAuthorize("@ss.hasPermi('emrSet:detail:list')")
   @GetMapping({"/list"})
   public AjaxResult list(EmrSetDetail emrSetDetail) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         List<EmrSetDetail> list = this.emrSetDetailService.selectEmrSetDetailList(emrSetDetail);
         ajaxResult = AjaxResult.success((Object)list);
      } catch (Exception e) {
         this.log.error("查询病历组套详情出现异常", e);
         ajaxResult = AjaxResult.error("查询病历组套详情出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('emrSet:detail:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.emrSetDetailService.selectEmrSetDetailById(id));
   }

   @PreAuthorize("@ss.hasPermi('emrSet:detail:add')")
   @Log(
      title = "保存病历组套详情",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody EmrSetDetailVo emrSetDetailVo) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         if (emrSetDetailVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(emrSetDetailVo.getSetCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历组套编码不能为空");
         }

         if (flag) {
            EmrSetMain emrSetMain = this.emrSetMainService.selectEmrSetMainById(emrSetDetailVo.getSetCd());
            if (emrSetMain == null) {
               flag = false;
               ajaxResult = AjaxResult.error("未查询到病历组套信息");
            } else if (!emrSetMain.getCrePerCode().equals(SecurityUtils.getLoginUser().getUser().getBasEmployee().getEmplNumber())) {
               flag = false;
               ajaxResult = AjaxResult.error("不是组套创建人不可添加");
            }
         }

         if (flag) {
            for(EmrSetDetail emrSetDetail : emrSetDetailVo.getEmrSetDetailList()) {
               if (flag && StringUtils.isEmpty(emrSetDetail.getTempName())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("模板名称不能为空");
               }

               if (flag && emrSetDetail.getTempId() == null) {
                  flag = false;
                  ajaxResult = AjaxResult.error("模板id不能为空");
               }

               if (flag && emrSetDetail.getSort() == null) {
                  flag = false;
                  ajaxResult = AjaxResult.error("序号不能为空");
               }
            }
         }

         if (flag) {
            this.emrSetDetailService.saveEmrSetDetailList(emrSetDetailVo.getEmrSetDetailList(), emrSetDetailVo.getSetCd());
         }
      } catch (Exception e) {
         this.log.error("保存病历组套出现异常", e);
         ajaxResult = AjaxResult.error("保存病历组套出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('emrSet:detail:edit')")
   @Log(
      title = "【请填写功能名称】",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody EmrSetDetail emrSetDetail) {
      return this.toAjax(this.emrSetDetailService.updateEmrSetDetail(emrSetDetail));
   }

   @PreAuthorize("@ss.hasPermi('emrSet:detail:remove')")
   @Log(
      title = "删除病历组套详情",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{id}"})
   public AjaxResult remove(@PathVariable Long id) {
      AjaxResult ajaxResult = AjaxResult.success("删除成功");

      try {
         this.emrSetDetailService.deleteEmrSetDetailById(id);
      } catch (Exception e) {
         this.log.error("删除病历组套详情出现异常", e);
         ajaxResult = AjaxResult.success("删除病历组套详情出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emrSet:detail:list,emr:index:save')")
   @Log(
      title = "查询病历组套详情树",
      businessType = BusinessType.DELETE
   )
   @GetMapping({"/setDetailTree"})
   public AjaxResult setDetailTree(EmrSetDetailVo emrSetDetail) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (emrSetDetail == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(emrSetDetail.getSetCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("组套编码不能为空");
         }

         if (flag) {
            List<EmrSetDetailVo> list = this.emrSetDetailService.selectEmrSetDetailTreeList(emrSetDetail);
            ajaxResult = AjaxResult.success((Object)list);
         }
      } catch (Exception e) {
         this.log.error("查询病历组套详情树出现异常", e);
         ajaxResult = AjaxResult.success("查询病历组套详情树出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emrSet:detail:list,emr:index:save,pat:visitinfo:allList')")
   @Log(
      title = "查询病历组套详情树",
      businessType = BusinessType.DELETE
   )
   @GetMapping({"/checkedSetTree"})
   public AjaxResult checkedSetTree(EmrSetDetailVo emrSetDetail) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (emrSetDetail == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(emrSetDetail.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag) {
            List<EmrSetDetailVo> list = this.emrSetDetailService.selectEmrCheckedSetTree(emrSetDetail);
            ajaxResult = AjaxResult.success((Object)list);
         }
      } catch (Exception e) {
         this.log.error("查询病历组套详情树出现异常", e);
         ajaxResult = AjaxResult.error("查询病历组套详情树出现异常");
      }

      return ajaxResult;
   }
}
