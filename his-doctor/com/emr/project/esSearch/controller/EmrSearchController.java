package com.emr.project.esSearch.controller;

import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.esSearch.domain.EmrFile;
import com.emr.project.esSearch.domain.vo.EmrFileSearchVo;
import com.emr.project.esSearch.service.IEmrFileElemService;
import com.emr.project.esSearch.service.IEmrFileService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zxp.esclientrhl.repository.PageList;

@RestController
@RequestMapping({"/es/search"})
public class EmrSearchController extends BaseController {
   @Autowired
   private IEmrFileService emrFileService;
   @Autowired
   private IEmrFileElemService emrFileElemService;

   @PreAuthorize("@ss.hasAnyPermi('es:search:add,es:search:syncall')")
   @PostMapping
   public AjaxResult createEmrFileIndex() {
      try {
         this.emrFileService.createEmrFileIndex();
         this.emrFileElemService.createEmrFileElemIndex();
      } catch (Exception e) {
         this.log.error("创建出现异常", e);
         return AjaxResult.error("创建出现异常");
      }

      return AjaxResult.success("创建成功");
   }

   @PreAuthorize("@ss.hasAnyPermi('es:search:drop')")
   @DeleteMapping
   public AjaxResult dropEmrFileIndex() {
      try {
         this.emrFileService.dropEmrFileIndex();
         this.emrFileElemService.dropEmrFileElemIndex();
      } catch (Exception e) {
         this.log.error("删除出现异常", e);
         return AjaxResult.error("删除出现异常");
      }

      return AjaxResult.success("删除成功");
   }

   @PreAuthorize("@ss.hasAnyPermi('es:search:syncall')")
   @GetMapping({"/syncall"})
   public AjaxResult syncEmrFileAllToEs() {
      try {
         this.emrFileService.syncEmrFileAllToEs();
         this.emrFileElemService.syncEmrFileElemAllToEs();
      } catch (Exception e) {
         this.log.error("病历文件全部同步至es服务器出现异常", e);
         return AjaxResult.error("病历文件全部同步至es服务器出现异常");
      }

      return AjaxResult.success("病历文件全部同步至es服务器成功");
   }

   @PreAuthorize("@ss.hasAnyPermi('es:search:list,es:search:syncadd')")
   @GetMapping({"/syncadd"})
   public AjaxResult syncEmrFileAddToEs(String beginDate, String endDate) {
      try {
         if (StringUtils.isNotBlank(beginDate) || StringUtils.isNotBlank(endDate)) {
            this.emrFileService.syncEmrFileAddToEs(beginDate, endDate);
            this.emrFileElemService.syncEmrFileElemAddToEs(beginDate, endDate);
         }
      } catch (Exception e) {
         this.log.error("病历文件增量同步至es服务器出现异常", e);
         return AjaxResult.error("病历文件增量同步至es服务器出现异常");
      }

      return AjaxResult.success("病历文件增量同步至es服务器成功");
   }

   @PreAuthorize("@ss.hasAnyPermi('es:search:list,es:search:syncadd')")
   @GetMapping({"/syncadd2"})
   public AjaxResult syncEmrFileAddToEs(String patientId) {
      try {
         if (StringUtils.isNotBlank(patientId)) {
            this.emrFileService.syncEmrFileAddToEs(patientId);
         }
      } catch (Exception e) {
         this.log.error("病历文件增量同步至es服务器出现异常", e);
         return AjaxResult.error("病历文件增量同步至es服务器出现异常");
      }

      return AjaxResult.success("病历文件增量同步至es服务器成功");
   }

   @PreAuthorize("@ss.hasAnyPermi('es:search:list')")
   @PostMapping({"/list"})
   public AjaxResult queryEmrFileList(HttpServletRequest request, @RequestBody EmrFileSearchVo emrFileSearchVo) {
      PageList<EmrFile> list = null;

      try {
         list = this.emrFileService.queryHighLight(emrFileSearchVo, emrFileSearchVo.getPageNum(), emrFileSearchVo.getPageSize());
      } catch (Exception e) {
         this.log.error("查询出现异常", e);
         return AjaxResult.error("查询出现异常");
      }

      return AjaxResult.success("查询成功", list);
   }

   @PreAuthorize("@ss.hasAnyPermi('es:search:list')")
   @GetMapping({"/listByCase"})
   public AjaxResult queryEmrFileList(Long caseId, int currentPage, int pageSize) {
      PageList<EmrFile> list = null;

      try {
         list = this.emrFileService.queryHighLight(caseId, currentPage, pageSize);
      } catch (Exception e) {
         this.log.error("查询出现异常", e);
         return AjaxResult.error("查询出现异常");
      }

      return AjaxResult.success("查询成功", list);
   }

   @PreAuthorize("@ss.hasPermi('es:search:export')")
   @Log(
      title = "导出病历检索结果",
      businessType = BusinessType.EXPORT
   )
   @PostMapping({"/export"})
   public AjaxResult export(@RequestBody EmrFileSearchVo emrFileSearchVo) {
      AjaxResult ajaxResult = AjaxResult.success("导出成功");

      try {
         List<EmrFile> list = this.emrFileService.queryHighLightExport(emrFileSearchVo);
         ExcelUtil<EmrFile> util = new ExcelUtil(EmrFile.class);
         ajaxResult = util.exportExcel(list, "病历检索结果");
      } catch (Exception e) {
         this.log.error("到处病历检索结果出现异常", e);
         ajaxResult = AjaxResult.error("导出病历检索结果出现异常");
      }

      return ajaxResult;
   }
}
