package com.emr.project.qc.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.qc.domain.QcGendConDict;
import com.emr.project.qc.domain.vo.QcGendConDictVo;
import com.emr.project.qc.service.IQcGendConDictService;
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
@RequestMapping({"/qc/gend/dict"})
public class QcGendConDictController extends BaseController {
   @Autowired
   private IQcGendConDictService qcGendConDictService;

   @PreAuthorize("@ss.hasPermi('qc:gend:dict:list')")
   @GetMapping({"/list"})
   public AjaxResult list(QcGendConDict qcGendConDict) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         List<QcGendConDict> list = this.qcGendConDictService.selectQcGendConDictList(qcGendConDict);
         ajaxResult = AjaxResult.success((Object)list);
      } catch (Exception e) {
         this.log.error("查询性别专用词典列表出现异常", e);
         ajaxResult = AjaxResult.error("查询性别专用词典列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:gend:dict:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.qcGendConDictService.selectQcGendConDictById(id));
   }

   @PreAuthorize("@ss.hasPermi('qc:gend:dict:add')")
   @Log(
      title = "性别专用词典 ",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody QcGendConDictVo qcGendConDictVo) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         if (qcGendConDictVo.getQcGendConDictList() != null) {
            for(QcGendConDict qcGendConDict : qcGendConDictVo.getQcGendConDictList()) {
               if (!flag) {
                  break;
               }

               if (flag && StringUtils.isEmpty(qcGendConDict.getGenderCode())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("性别编码不能为空");
               }

               if (flag && StringUtils.isEmpty(qcGendConDict.getDictType())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("词典类型不能为空");
               }

               if (flag && StringUtils.isEmpty(qcGendConDict.getItemCon())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("条目内容不能为空");
               }
            }

            if (flag) {
               this.qcGendConDictService.insertQcGendConDict(qcGendConDictVo.getQcGendConDictList());
            }
         } else {
            ajaxResult = AjaxResult.error("参数不能为空");
         }
      } catch (Exception e) {
         this.log.error("保存性别专用词典出现异常", e);
         ajaxResult = AjaxResult.error("保存性别专用词典出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:gend:dict:edit')")
   @Log(
      title = "性别专用词典 ",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody QcGendConDict qcGendConDict) {
      return this.toAjax(this.qcGendConDictService.updateQcGendConDict(qcGendConDict));
   }

   @PreAuthorize("@ss.hasPermi('qc:gend:dict:remove')")
   @Log(
      title = "性别专用词典 ",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.qcGendConDictService.deleteQcGendConDictByIds(ids));
   }
}
