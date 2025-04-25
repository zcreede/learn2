package com.emr.project.qc.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.qc.domain.QcAgeConDict;
import com.emr.project.qc.domain.vo.QcAgeConDictVo;
import com.emr.project.qc.service.IQcAgeConDictService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/qc/age/dict"})
public class QcAgeConDictController extends BaseController {
   @Autowired
   private IQcAgeConDictService qcAgeConDictService;

   @PreAuthorize("@ss.hasPermi('qc:age:dict:list')")
   @GetMapping({"/list"})
   public AjaxResult list(QcAgeConDict qcAgeConDict) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         List<QcAgeConDict> list = this.qcAgeConDictService.selectQcAgeConDictList(qcAgeConDict);
         ajaxResult = AjaxResult.success((Object)list);
      } catch (Exception e) {
         this.log.error("查询年龄专用词典列表出现异常", e);
         ajaxResult = AjaxResult.error("查询年龄专用词典列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:age:dict:add')")
   @Log(
      title = "年龄专用词典",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody QcAgeConDictVo qcAgeConDictVo) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         if (qcAgeConDictVo.getQcAgeConDictList() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         for(QcAgeConDict qcAgeConDict : qcAgeConDictVo.getQcAgeConDictList()) {
            if (!flag) {
               break;
            }

            if (flag && StringUtils.isEmpty(qcAgeConDict.getAgeCode())) {
               flag = false;
               ajaxResult = AjaxResult.error("年龄编码不能为空");
            }

            if (flag && StringUtils.isEmpty(qcAgeConDict.getDictType())) {
               flag = false;
               ajaxResult = AjaxResult.error("词典类型不能为空");
            }

            if (flag && StringUtils.isEmpty(qcAgeConDict.getItemCon())) {
               flag = false;
               ajaxResult = AjaxResult.error("条目内容不能为空");
            }
         }

         if (flag) {
            this.qcAgeConDictService.insertQcAgeConDict(qcAgeConDictVo.getQcAgeConDictList());
         }
      } catch (Exception e) {
         this.log.error("保存年龄专用词典出现异常", e);
         ajaxResult = AjaxResult.error("保存年龄专用词典出现异常");
      }

      return ajaxResult;
   }
}
