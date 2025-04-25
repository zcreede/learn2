package com.emr.project.emr.controller;

import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.emr.domain.EmrSharing;
import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.SubfileIndex;
import com.emr.project.emr.domain.vo.IndexVo;
import com.emr.project.emr.service.IEmrSharingService;
import com.emr.project.emr.service.IIndexService;
import com.emr.project.emr.service.ISubfileIndexService;
import com.emr.project.system.service.ISysShareElemService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
@RequestMapping({"/emr/sharing"})
public class EmrSharingController extends BaseController {
   @Autowired
   private IEmrSharingService emrSharingService;
   @Autowired
   private IIndexService indexService;
   @Autowired
   private ISubfileIndexService subfileIndexService;
   @Autowired
   private ISysShareElemService sysShareElemService;

   @PreAuthorize("@ss.hasPermi('emr:sharing:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(EmrSharing emrSharing) {
      this.startPage();
      List<EmrSharing> list = this.emrSharingService.selectEmrSharingList(emrSharing);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('emr:sharing:export')")
   @Log(
      title = "病历内容共享信息用于病历书写过程病历之间数据引用",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(EmrSharing emrSharing) {
      List<EmrSharing> list = this.emrSharingService.selectEmrSharingList(emrSharing);
      ExcelUtil<EmrSharing> util = new ExcelUtil(EmrSharing.class);
      return util.exportExcel(list, "病历内容共享信息用于病历书写过程病历之间数据引用数据");
   }

   @PreAuthorize("@ss.hasPermi('emr:sharing:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.emrSharingService.selectEmrSharingById(id));
   }

   @PreAuthorize("@ss.hasPermi('emr:sharing:add')")
   @Log(
      title = "病历内容共享信息用于病历书写过程病历之间数据引用",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody EmrSharing emrSharing) {
      return this.toAjax(this.emrSharingService.insertEmrSharing(emrSharing));
   }

   @PreAuthorize("@ss.hasPermi('emr:sharing:edit')")
   @Log(
      title = "病历内容共享信息用于病历书写过程病历之间数据引用",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody EmrSharing emrSharing) {
      return this.toAjax(this.emrSharingService.updateEmrSharing(emrSharing));
   }

   @PreAuthorize("@ss.hasPermi('emr:sharing:remove')")
   @Log(
      title = "病历内容共享信息用于病历书写过程病历之间数据引用",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.emrSharingService.deleteEmrSharingByIds(ids));
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:sharing:getEmrSharingList,emr:index:helper')")
   @GetMapping({"/getEmrSharingList"})
   public TableDataInfo getEmrSharingList(String patientId, String elemName) {
      TableDataInfo tableDataInfo = new TableDataInfo();
      Boolean flag = true;

      try {
         if (flag && StringUtils.isBlank(patientId)) {
            flag = false;
            tableDataInfo = new TableDataInfo(500, "当前病历子文件患者就诊id不能为空");
         }

         if (flag) {
            EmrSharing emrSharing = new EmrSharing();
            emrSharing.setPatientId(patientId);
            emrSharing.setElemName(elemName);
            this.startPage();
            tableDataInfo = this.getDataTable(this.emrSharingService.selectEmrSharingList(emrSharing));
         }
      } catch (Exception e) {
         this.log.error("查询病历共享元素出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询病历共享元素出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:sharing:automaticElem,emr:index:helper')")
   @GetMapping({"/automaticElem"})
   public AjaxResult automaticElem(IndexVo indexVo, List emrSharingList, HttpServletRequest request) {
      AjaxResult ajaxResult = new AjaxResult();
      Boolean flag = true;

      try {
         if (indexVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (StringUtils.isNull(indexVo.getId())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历索引id不能为空");
         }

         if (emrSharingList == null) {
            flag = false;
            ajaxResult = AjaxResult.error("智能引用元素不能为空");
         }

         Index index = null;
         if (flag) {
            index = this.indexService.selectIndexById(indexVo.getId());
            if (index == null) {
               flag = false;
               ajaxResult = AjaxResult.error("当前病历文件不存在");
            }
         }

         SubfileIndex subfileIndex = null;
         if (flag && "MAINFILE".equals(index.getMrType()) && org.apache.commons.lang3.StringUtils.isNotBlank(indexVo.getNewSubFileFlag()) && indexVo.getNewSubFileFlag().equals("0")) {
            if (indexVo.getSubFileIndexId() == null) {
               flag = false;
               ajaxResult = AjaxResult.error("病历子文件id不能为空");
            }

            if (flag) {
               subfileIndex = this.subfileIndexService.selectSubfileIndexById(indexVo.getSubFileIndexId());
               if (subfileIndex == null) {
                  flag = false;
                  ajaxResult = AjaxResult.error("当前病历子文件不存在");
               }
            }
         }

         if (flag) {
            ajaxResult = this.indexService.selectIsAuthUpdate(index, subfileIndex, indexVo.getNewSubFileFlag(), indexVo.getMainFileCancelSignFlag(), request, indexVo);
         }
      } catch (Exception e) {
         this.log.error("智能引用元素出现异常", e);
         ajaxResult = AjaxResult.error("智能引用元素出现异常");
      }

      return ajaxResult;
   }
}
