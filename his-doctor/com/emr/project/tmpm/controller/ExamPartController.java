package com.emr.project.tmpm.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.tmpm.domain.ClinItemMain;
import com.emr.project.tmpm.domain.ExamPart;
import com.emr.project.tmpm.domain.ExamPartMain;
import com.emr.project.tmpm.domain.vo.ExamPartVo;
import com.emr.project.tmpm.service.IClinItemMainService;
import com.emr.project.tmpm.service.IExamPartService;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
@RequestMapping({"/tmpm/part"})
public class ExamPartController extends BaseController {
   @Autowired
   private IExamPartService examPartService;
   @Autowired
   private IClinItemMainService clinItemMainService;

   @PreAuthorize("@ss.hasAnyPermi('tmpm:part:list,docOrder:order:list')")
   @GetMapping({"/list"})
   public AjaxResult list(ExamPartVo examPart) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      Boolean flag = true;
      if (StringUtils.isEmpty(examPart.getDocumentTypeNo())) {
         ajaxResult = AjaxResult.error("单据类型编码不能为空");
      }

      try {
         if (flag) {
            Map<String, List<ExamPart>> map = new HashMap();
            if (StringUtils.isNotBlank(examPart.getItemCd())) {
               ClinItemMain clinItemMain = this.clinItemMainService.selectClinItemMainById(examPart.getItemCd());
               if (clinItemMain != null && StringUtils.isNotBlank(clinItemMain.getExamPartCd())) {
                  String examPartCd = clinItemMain.getExamPartCd();
                  List<String> examPartCdList = Arrays.asList(examPartCd.split(","));
                  List<ExamPart> examPartList = this.examPartService.selectByExamPartCdList(examPartCdList);
                  if (CollectionUtils.isNotEmpty(examPartList) && examPartList.size() > 0) {
                     map = (Map)examPartList.stream().collect(Collectors.groupingBy((s) -> s.getPartClassName()));
                  }
               } else {
                  map = this.examPartService.selectExamPartMapList(examPart);
               }
            } else {
               map = this.examPartService.selectExamPartMapList(examPart);
            }

            List<ExamPartMain> partMainList = this.examPartService.selectExamPartMainList();
            ExamPartMain all = new ExamPartMain();
            all.setPartClassName("全部");
            partMainList.add(all);
            ajaxResult = AjaxResult.success((Object)map);
            ajaxResult.put("partMainList", partMainList);
         }
      } catch (Exception e) {
         this.log.error("查询检查部位列表出现异常", e);
         ajaxResult = AjaxResult.error("查询检查部位列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('tmpm:part:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.examPartService.selectExamPartById(id));
   }

   @PreAuthorize("@ss.hasPermi('tmpm:part:add')")
   @Log(
      title = "检查部位",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody ExamPart examPart) {
      return this.toAjax(this.examPartService.insertExamPart(examPart));
   }

   @PreAuthorize("@ss.hasPermi('tmpm:part:edit')")
   @Log(
      title = "检查部位",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody ExamPart examPart) {
      return this.toAjax(this.examPartService.updateExamPart(examPart));
   }

   @PreAuthorize("@ss.hasPermi('tmpm:part:remove')")
   @Log(
      title = "检查部位",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.examPartService.deleteExamPartByIds(ids));
   }
}
