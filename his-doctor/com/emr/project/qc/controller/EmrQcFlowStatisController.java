package com.emr.project.qc.controller;

import com.emr.common.utils.DateUtils;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.dr.domain.vo.TdCaConsApplyVo;
import com.emr.project.qc.domain.EmrQcFlowStatis;
import com.emr.project.qc.domain.vo.EmrQcExporByDoctorVo;
import com.emr.project.qc.domain.vo.EmrQcExporByTypeVo;
import com.emr.project.qc.domain.vo.EmrQcExportListVo;
import com.emr.project.qc.domain.vo.EmrQcFlowDeptCodeVo;
import com.emr.project.qc.domain.vo.EmrQcFlowDoctorVo;
import com.emr.project.qc.domain.vo.EmrQcFlowStatisByTypeVo;
import com.emr.project.qc.domain.vo.EmrQcFlowStatisDetailExport;
import com.emr.project.qc.domain.vo.EmrQcFlowStatisSumExport;
import com.emr.project.qc.domain.vo.EmrQcListStatisticByMrTypeVo;
import com.emr.project.qc.domain.vo.EmrQcListVo;
import com.emr.project.qc.domain.vo.StatementVo;
import com.emr.project.qc.domain.vo.StatisticsResultVo;
import com.emr.project.qc.service.IEmrQcFlowStatisService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/qc/statis"})
public class EmrQcFlowStatisController extends BaseController {
   @Autowired
   private IEmrQcFlowStatisService emrQcFlowStatisService;

   @PreAuthorize("@ss.hasAnyPermi('emr:qc:workloadStatis')")
   @GetMapping({"/workloadCircle"})
   public AjaxResult workloadCircle(StatementVo statementVo) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (statementVo.getEndTime() != null && statementVo.getBeginTime() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(statementVo.getEndTime());
            c.add(5, 1);
            statementVo.setEndTime(c.getTime());
         }

         ajaxResult = AjaxResult.success((Object)this.emrQcFlowStatisService.selectWorkloadCircle(statementVo));
      } catch (Exception e) {
         this.log.error("工作量统计---环形图出现异常：", e);
         ajaxResult = AjaxResult.error("工作量统计---环形图出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:qc:workloadStatis')")
   @GetMapping({"/workloadFlawCircle"})
   public AjaxResult workloadFlawCircle(StatementVo statementVo) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (statementVo.getEndTime() != null && statementVo.getBeginTime() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(statementVo.getEndTime());
            c.add(5, 1);
            statementVo.setEndTime(c.getTime());
         }

         ajaxResult = AjaxResult.success((Object)this.emrQcFlowStatisService.selectWorkloadFlawCircle(statementVo));
      } catch (Exception e) {
         this.log.error("工作量缺陷统计---环形图出现异常：", e);
         ajaxResult = AjaxResult.error("工作量缺陷统计---环形图出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:qc:workloadStatis')")
   @GetMapping({"/workloadCheckCircle"})
   public AjaxResult workloadCheckCircle(StatementVo statementVo) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (statementVo.getEndTime() != null && statementVo.getBeginTime() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(statementVo.getEndTime());
            c.add(5, 1);
            statementVo.setEndTime(c.getTime());
         }

         ajaxResult = AjaxResult.success((Object)this.emrQcFlowStatisService.selectWorkloadCheckCircle(statementVo));
      } catch (Exception e) {
         this.log.error("工作量核验统计---环形图出现异常：", e);
         ajaxResult = AjaxResult.error("工作量核验统计---环形图出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:qc:workloadStatis')")
   @GetMapping({"/docFlawHistogram"})
   public AjaxResult docFlawHistogram(StatementVo statementVo) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         ajaxResult = AjaxResult.success((Object)this.emrQcFlowStatisService.selectDocFlawHistogram(statementVo));
      } catch (Exception e) {
         this.log.error("工作量统计---柱状图出现异常：", e);
         ajaxResult = AjaxResult.error("工作量统计---柱状图出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/workloadTable"})
   public TableDataInfo workloadTable(EmrQcFlowStatis emrQcFlowStatis) {
      new TableDataInfo();
      boolean flag = true;

      TableDataInfo tableDataInfo;
      try {
         if (emrQcFlowStatis.getEndTime() != null && emrQcFlowStatis.getBeginTime() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(emrQcFlowStatis.getEndTime());
            c.add(5, 1);
            emrQcFlowStatis.setEndTime(c.getTime());
         }

         this.startPage();
         tableDataInfo = this.getDataTable(this.emrQcFlowStatisService.selectWorkloadTable(emrQcFlowStatis));
      } catch (Exception e) {
         this.log.error("工作量统计---表格出现异常：", e);
         tableDataInfo = new TableDataInfo(500, "工作量统计---表格出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/checkFlowTypePie"})
   public AjaxResult checkFlowTypePie(EmrQcFlowStatis emrQcFlowStatis) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (emrQcFlowStatis == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(emrQcFlowStatis.getQcSection())) {
            flag = false;
            ajaxResult = AjaxResult.error("环节编码不能为空");
         }

         if (flag) {
            if (emrQcFlowStatis.getEndTime() != null && emrQcFlowStatis.getBeginTime() != null) {
               Calendar c = Calendar.getInstance();
               c.setTime(emrQcFlowStatis.getEndTime());
               c.add(5, 1);
               emrQcFlowStatis.setEndTime(c.getTime());
            }

            ajaxResult = AjaxResult.success((Object)this.emrQcFlowStatisService.selectCheckFlowTypePie(emrQcFlowStatis));
         }
      } catch (Exception e) {
         this.log.error("缺陷类型统计---饼图出现异常：", e);
         ajaxResult = AjaxResult.error("缺陷类型统计---饼图出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/checkFlowTypeCylinder"})
   public AjaxResult checkFlowTypeCylinder(EmrQcFlowStatis emrQcFlowStatis) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (emrQcFlowStatis == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(emrQcFlowStatis.getQcSection())) {
            flag = false;
            ajaxResult = AjaxResult.error("环节编码不能为空");
         }

         if (flag) {
            if (emrQcFlowStatis.getEndTime() != null && emrQcFlowStatis.getBeginTime() != null) {
               Calendar c = Calendar.getInstance();
               c.setTime(emrQcFlowStatis.getEndTime());
               c.add(5, 1);
               emrQcFlowStatis.setEndTime(c.getTime());
            }

            ajaxResult = AjaxResult.success((Object)this.emrQcFlowStatisService.selectCheckFlowTypeCylinder(emrQcFlowStatis));
         }
      } catch (Exception e) {
         this.log.error("缺陷类型统计---柱状图出现异常：", e);
         ajaxResult = AjaxResult.error("缺陷类型统计---柱状图出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/checkFlowTypeTable"})
   public TableDataInfo checkFlowTypeTable(EmrQcFlowStatis emrQcFlowStatis) {
      TableDataInfo tableDataInfo = new TableDataInfo();
      boolean flag = true;

      try {
         if (emrQcFlowStatis == null) {
            flag = false;
            tableDataInfo = new TableDataInfo(500, "参数不能为空");
         }

         if (flag && StringUtils.isEmpty(emrQcFlowStatis.getQcSection())) {
            flag = false;
            tableDataInfo = new TableDataInfo(500, "环节编码不能为空");
         }

         if (flag) {
            if (emrQcFlowStatis.getEndTime() != null && emrQcFlowStatis.getBeginTime() != null) {
               Calendar c = Calendar.getInstance();
               c.setTime(emrQcFlowStatis.getEndTime());
               c.add(5, 1);
               emrQcFlowStatis.setEndTime(c.getTime());
            }

            this.startPage();
            tableDataInfo = this.getDataTable(this.emrQcFlowStatisService.checkFlowTypeTable(emrQcFlowStatis));
         }
      } catch (Exception e) {
         this.log.error("缺陷类型统计---表格出现异常：", e);
         tableDataInfo = new TableDataInfo(500, "缺陷类型统计---表格出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/checkFlowTypeTableExport"})
   public AjaxResult checkFlowTypeTableExport(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      new ArrayList();
      if (emrQcFlowStatis.getEndTime() != null && emrQcFlowStatis.getBeginTime() != null) {
         Calendar c = Calendar.getInstance();
         c.setTime(emrQcFlowStatis.getEndTime());
         c.add(5, 1);
         emrQcFlowStatis.setEndTime(c.getTime());
      }

      List emrQcFlowStatisList = this.emrQcFlowStatisService.checkFlowTypeTable(emrQcFlowStatis);
      ExcelUtil<EmrQcFlowStatis> util = new ExcelUtil(EmrQcFlowStatis.class);
      return util.exportExcel(emrQcFlowStatisList, "缺陷类型统计");
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/checkFlowTypeDiaLogTable"})
   public TableDataInfo checkFlowTypeDiaLogTable(EmrQcFlowStatis emrQcFlowStatis) {
      TableDataInfo tableDataInfo = new TableDataInfo();
      boolean flag = true;

      try {
         if (emrQcFlowStatis == null) {
            flag = false;
            tableDataInfo = new TableDataInfo(500, "参数不能为空");
         }

         if (flag && StringUtils.isEmpty(emrQcFlowStatis.getQcSection())) {
            flag = false;
            tableDataInfo = new TableDataInfo(500, "环节编码不能为空");
         }

         if (flag && emrQcFlowStatis.getDbEleId() == null) {
            flag = false;
            tableDataInfo = new TableDataInfo(500, "缺陷类型编码不能为空");
         }

         if (flag) {
            if (emrQcFlowStatis.getEndTime() != null && emrQcFlowStatis.getBeginTime() != null) {
               Calendar c = Calendar.getInstance();
               c.setTime(emrQcFlowStatis.getEndTime());
               c.add(5, 1);
               emrQcFlowStatis.setEndTime(c.getTime());
            }

            this.startPage();
            tableDataInfo = this.getDataTable(this.emrQcFlowStatisService.selectCheckFlowTypeDiaLogTable(emrQcFlowStatis));
         }
      } catch (Exception e) {
         this.log.error("缺陷类型统计---弹框表格出现异常：", e);
         tableDataInfo = new TableDataInfo(500, "缺陷类型统计---弹框表格出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/checkFlowTypeDiaLogExport"})
   public AjaxResult checkFlowTypeDiaLogExport(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      new ArrayList();
      if (emrQcFlowStatis == null) {
         AjaxResult.error(500, "参数不能为空");
      }

      if (StringUtils.isEmpty(emrQcFlowStatis.getQcSection())) {
         AjaxResult.error(500, "环节编码不能为空");
      }

      if (emrQcFlowStatis.getDbEleId() == null) {
         AjaxResult.error(500, "缺陷类型编码不能为空");
      }

      if (emrQcFlowStatis.getEndTime() != null && emrQcFlowStatis.getBeginTime() != null) {
         Calendar c = Calendar.getInstance();
         c.setTime(emrQcFlowStatis.getEndTime());
         c.add(5, 1);
         emrQcFlowStatis.setEndTime(c.getTime());
      }

      List<EmrQcExportListVo> emrQcExporListVoList = this.emrQcFlowStatisService.selectCheckEmrTypeDiaLogExport(emrQcFlowStatis);
      List emrQcExporByTypeListVoList = (List)emrQcExporListVoList.stream().map((a) -> {
         EmrQcExporByTypeVo emrQcExporByTypeVo = new EmrQcExporByTypeVo();
         BeanUtils.copyProperties(a, emrQcExporByTypeVo);
         return emrQcExporByTypeVo;
      }).collect(Collectors.toList());
      ExcelUtil<EmrQcExporByTypeVo> util = new ExcelUtil(EmrQcExporByTypeVo.class);
      return util.exportExcel(emrQcExporByTypeListVoList, "缺陷类型统计详情");
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/checkEmrTypePie"})
   public AjaxResult checkEmrTypePie(EmrQcFlowStatis emrQcFlowStatis) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (emrQcFlowStatis == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(emrQcFlowStatis.getQcSection())) {
            flag = false;
            ajaxResult = AjaxResult.error("环节编码不能为空");
         }

         if (flag) {
            if (emrQcFlowStatis.getEndTime() != null && emrQcFlowStatis.getBeginTime() != null) {
               Calendar c = Calendar.getInstance();
               c.setTime(emrQcFlowStatis.getEndTime());
               c.add(5, 1);
               emrQcFlowStatis.setEndTime(c.getTime());
            }

            ajaxResult = AjaxResult.success((Object)this.emrQcFlowStatisService.selectCheckEmrTypePie(emrQcFlowStatis));
         }
      } catch (Exception e) {
         this.log.error("缺陷类型统计---饼图出现异常：", e);
         ajaxResult = AjaxResult.error("缺陷类型统计---饼图出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/checkEmrTypeCylinder"})
   public AjaxResult checkEmrTypeCylinder(EmrQcFlowStatis emrQcFlowStatis) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (emrQcFlowStatis == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(emrQcFlowStatis.getQcSection())) {
            flag = false;
            ajaxResult = AjaxResult.error("环节编码不能为空");
         }

         if (flag) {
            if (emrQcFlowStatis.getEndTime() != null && emrQcFlowStatis.getBeginTime() != null) {
               Calendar c = Calendar.getInstance();
               c.setTime(emrQcFlowStatis.getEndTime());
               c.add(5, 1);
               emrQcFlowStatis.setEndTime(c.getTime());
            }

            ajaxResult = AjaxResult.success((Object)this.emrQcFlowStatisService.selectCheckEmrTypeCylinder(emrQcFlowStatis));
         }
      } catch (Exception e) {
         this.log.error("病历类型统计---柱状图出现异常：", e);
         ajaxResult = AjaxResult.error("病历类型统计---柱状图出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/checkEmrTypeTable"})
   public TableDataInfo checkEmrTypeTable(EmrQcFlowStatis emrQcFlowStatis) {
      TableDataInfo tableDataInfo = new TableDataInfo();
      boolean flag = true;

      try {
         if (emrQcFlowStatis == null) {
            flag = false;
            tableDataInfo = new TableDataInfo(500, "参数不能为空");
         }

         if (flag && StringUtils.isEmpty(emrQcFlowStatis.getQcSection())) {
            flag = false;
            tableDataInfo = new TableDataInfo(500, "环节编码不能为空");
         }

         if (flag) {
            if (emrQcFlowStatis.getEndTime() != null && emrQcFlowStatis.getBeginTime() != null) {
               Calendar c = Calendar.getInstance();
               c.setTime(emrQcFlowStatis.getEndTime());
               c.add(5, 1);
               emrQcFlowStatis.setEndTime(c.getTime());
            }

            this.startPage();
            tableDataInfo = this.getDataTable(this.emrQcFlowStatisService.selectCheckEmrTypeTable(emrQcFlowStatis));
         }
      } catch (Exception e) {
         this.log.error("缺陷类型统计---弹框表格出现异常：", e);
         tableDataInfo = new TableDataInfo(500, "缺陷类型统计---弹框表格出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/checkEmrTypeExport"})
   public AjaxResult checkEmrTypeExport(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      new ArrayList();
      if (emrQcFlowStatis == null) {
         return AjaxResult.error(500, "参数不能为空");
      } else if (StringUtils.isEmpty(emrQcFlowStatis.getQcSection())) {
         return AjaxResult.error(500, "环节编码不能为空");
      } else {
         if (emrQcFlowStatis.getEndTime() != null && emrQcFlowStatis.getBeginTime() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(emrQcFlowStatis.getEndTime());
            c.add(5, 1);
            emrQcFlowStatis.setEndTime(c.getTime());
         }

         List<EmrQcFlowStatis> emrQcFlowStatisList = this.emrQcFlowStatisService.selectCheckEmrTypeTable(emrQcFlowStatis);
         List emrQcFlowStatisByTypeVoList = (List)emrQcFlowStatisList.stream().map((a) -> {
            EmrQcFlowStatisByTypeVo emrQcFlowStatisByTypeVo = new EmrQcFlowStatisByTypeVo();
            BeanUtils.copyProperties(a, emrQcFlowStatisByTypeVo);
            emrQcFlowStatisByTypeVo.setFlowRatio(String.valueOf(a.getFlowRatio()));
            emrQcFlowStatisByTypeVo.setAddRatio(String.valueOf(a.getAddRatio()));
            return emrQcFlowStatisByTypeVo;
         }).collect(Collectors.toList());
         ExcelUtil<EmrQcFlowStatisByTypeVo> util = new ExcelUtil(EmrQcFlowStatisByTypeVo.class);
         return util.exportExcel(emrQcFlowStatisByTypeVoList, "缺陷病历统计");
      }
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/checkEmrTypeDiaLogTable"})
   public TableDataInfo checkEmrTypeDiaLogTable(EmrQcFlowStatis emrQcFlowStatis) {
      TableDataInfo tableDataInfo = new TableDataInfo();
      boolean flag = true;

      try {
         if (emrQcFlowStatis == null) {
            flag = false;
            tableDataInfo = new TableDataInfo(500, "参数不能为空");
         }

         if (flag && StringUtils.isEmpty(emrQcFlowStatis.getQcSection())) {
            flag = false;
            tableDataInfo = new TableDataInfo(500, "环节编码不能为空");
         }

         if (flag && StringUtils.isEmpty(emrQcFlowStatis.getMrType())) {
            flag = false;
            tableDataInfo = new TableDataInfo(500, "病历类型编码不能为空");
         }

         if (flag) {
            if (emrQcFlowStatis.getEndTime() != null && emrQcFlowStatis.getBeginTime() != null) {
               Calendar c = Calendar.getInstance();
               c.setTime(emrQcFlowStatis.getEndTime());
               c.add(5, 1);
               emrQcFlowStatis.setEndTime(c.getTime());
            }

            this.startPage();
            tableDataInfo = this.getDataTable(this.emrQcFlowStatisService.selectCheckEmrTypeDiaLogTable(emrQcFlowStatis));
         }
      } catch (Exception e) {
         this.log.error("病历类型统计---弹框表格出现异常：", e);
         tableDataInfo = new TableDataInfo(500, "病历类型统计---弹框表格出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/checkEmrTypeDiaLogTableExport"})
   public AjaxResult checkEmrTypeDiaLogTableExport(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      new ArrayList();
      if (emrQcFlowStatis == null) {
         return AjaxResult.error(500, "参数不能为空");
      } else if (StringUtils.isEmpty(emrQcFlowStatis.getQcSection())) {
         return AjaxResult.error(500, "环节编码不能为空");
      } else if (StringUtils.isEmpty(emrQcFlowStatis.getMrType())) {
         return AjaxResult.error(500, "病历类型编码不能为空");
      } else {
         if (emrQcFlowStatis.getEndTime() != null && emrQcFlowStatis.getBeginTime() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(emrQcFlowStatis.getEndTime());
            c.add(5, 1);
            emrQcFlowStatis.setEndTime(c.getTime());
         }

         List emrQcListVoList = this.emrQcFlowStatisService.selectCheckEmrTypeDiaLogExport(emrQcFlowStatis);
         ExcelUtil<EmrQcExportListVo> util = new ExcelUtil(EmrQcExportListVo.class);
         return util.exportExcel(emrQcListVoList, "入院患者缺陷统计");
      }
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/checkDoctCylinder"})
   public AjaxResult checkDoctCylinder(EmrQcFlowStatis emrQcFlowStatis) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (emrQcFlowStatis == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(emrQcFlowStatis.getQcSection())) {
            flag = false;
            ajaxResult = AjaxResult.error("环节编码不能为空");
         }

         if (flag) {
            if (emrQcFlowStatis.getEndTime() != null && emrQcFlowStatis.getBeginTime() != null) {
               Calendar c = Calendar.getInstance();
               c.setTime(emrQcFlowStatis.getEndTime());
               c.add(5, 1);
               emrQcFlowStatis.setEndTime(c.getTime());
            }

            StatisticsResultVo tenVo = this.emrQcFlowStatisService.selectCheckTopDoctCylinder(emrQcFlowStatis);
            StatisticsResultVo afterVo = this.emrQcFlowStatisService.selectCheckAfterDoctCylinder(emrQcFlowStatis);
            ajaxResult.put("top", tenVo);
            ajaxResult.put("after", afterVo);
         }
      } catch (Exception e) {
         this.log.error("责任医师统计---柱状图出现异常：", e);
         ajaxResult = AjaxResult.error("责任医师统计---柱状图出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/checkDoctTable"})
   public TableDataInfo checkDoctTable(EmrQcFlowStatis emrQcFlowStatis) {
      TableDataInfo tableDataInfo = new TableDataInfo();
      boolean flag = true;

      try {
         if (emrQcFlowStatis == null) {
            flag = false;
            tableDataInfo = new TableDataInfo(500, "参数不能为空");
         }

         if (flag && StringUtils.isEmpty(emrQcFlowStatis.getQcSection())) {
            flag = false;
            tableDataInfo = new TableDataInfo(500, "环节编码不能为空");
         }

         if (flag) {
            if (emrQcFlowStatis.getEndTime() != null && emrQcFlowStatis.getBeginTime() != null) {
               Calendar c = Calendar.getInstance();
               c.setTime(emrQcFlowStatis.getEndTime());
               c.add(5, 1);
               emrQcFlowStatis.setEndTime(c.getTime());
            }

            this.startPage();
            tableDataInfo = this.getDataTable(this.emrQcFlowStatisService.selectCheckDoctTable(emrQcFlowStatis));
         }
      } catch (Exception e) {
         this.log.error("责任医师统计---表格出现异常：", e);
         tableDataInfo = new TableDataInfo(500, "责任医师统计---表格出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/checkDoctExport"})
   public AjaxResult checkDoctExport(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      new ArrayList();
      if (emrQcFlowStatis == null) {
         return AjaxResult.error(500, "参数不能为空");
      } else if (StringUtils.isEmpty(emrQcFlowStatis.getQcSection())) {
         return AjaxResult.error(500, "环节编码不能为空");
      } else {
         if (emrQcFlowStatis.getEndTime() != null && emrQcFlowStatis.getBeginTime() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(emrQcFlowStatis.getEndTime());
            c.add(5, 1);
            emrQcFlowStatis.setEndTime(c.getTime());
         }

         List<EmrQcFlowStatis> emrQcFlowStatisList = this.emrQcFlowStatisService.selectCheckDoctTable(emrQcFlowStatis);
         List emrQcFlowDoctorVoList = (List)emrQcFlowStatisList.stream().map((a) -> {
            EmrQcFlowDoctorVo emrQcFlowDoctorVo = new EmrQcFlowDoctorVo();
            BeanUtils.copyProperties(a, emrQcFlowDoctorVo);
            return emrQcFlowDoctorVo;
         }).collect(Collectors.toList());
         ExcelUtil<EmrQcFlowDoctorVo> util = new ExcelUtil(EmrQcFlowDoctorVo.class);
         return util.exportExcel(emrQcFlowDoctorVoList, "责任医师缺陷统计");
      }
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/checkDoctDialogTable"})
   public TableDataInfo checkDoctDialogTable(EmrQcFlowStatis emrQcFlowStatis) {
      TableDataInfo tableDataInfo = new TableDataInfo();
      boolean flag = true;

      try {
         if (emrQcFlowStatis == null) {
            flag = false;
            tableDataInfo = new TableDataInfo(500, "参数不能为空");
         }

         if (flag && StringUtils.isEmpty(emrQcFlowStatis.getQcSection())) {
            flag = false;
            tableDataInfo = new TableDataInfo(500, "环节编码不能为空");
         }

         if (flag && StringUtils.isEmpty(emrQcFlowStatis.getDoctCd())) {
            flag = false;
            tableDataInfo = new TableDataInfo(500, "责任医师编码不能为空");
         }

         if (flag) {
            if (emrQcFlowStatis.getEndTime() != null && emrQcFlowStatis.getBeginTime() != null) {
               Calendar c = Calendar.getInstance();
               c.setTime(emrQcFlowStatis.getEndTime());
               c.add(5, 1);
               emrQcFlowStatis.setEndTime(c.getTime());
            }

            this.startPage();
            tableDataInfo = this.getDataTable(this.emrQcFlowStatisService.selectCheckDoctDialogTable(emrQcFlowStatis));
         }
      } catch (Exception e) {
         this.log.error("责任医师统计---弹框表格出现异常：", e);
         tableDataInfo = new TableDataInfo(500, "责任医师统计---弹框表格出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/checkDoctDialogExport"})
   public AjaxResult checkDoctDialogExport(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      new ArrayList();
      if (emrQcFlowStatis == null) {
         return AjaxResult.error(500, "参数不能为空");
      } else if (StringUtils.isEmpty(emrQcFlowStatis.getQcSection())) {
         return AjaxResult.error(500, "环节编码不能为空");
      } else if (StringUtils.isEmpty(emrQcFlowStatis.getDoctCd())) {
         return AjaxResult.error(500, "责任医师编码不能为空");
      } else {
         if (emrQcFlowStatis.getEndTime() != null && emrQcFlowStatis.getBeginTime() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(emrQcFlowStatis.getEndTime());
            c.add(5, 1);
            emrQcFlowStatis.setEndTime(c.getTime());
         }

         List<EmrQcListVo> emrQcListVoList = this.emrQcFlowStatisService.selectCheckDoctDialogTable(emrQcFlowStatis);
         List emrQcExporByDoctorVoList = (List)emrQcListVoList.stream().map((a) -> {
            EmrQcExporByDoctorVo emrQcExporByDoctorVo = new EmrQcExporByDoctorVo();
            BeanUtils.copyProperties(a, emrQcExporByDoctorVo);
            return emrQcExporByDoctorVo;
         }).collect(Collectors.toList());
         ExcelUtil<EmrQcExporByDoctorVo> util = new ExcelUtil(EmrQcExporByDoctorVo.class);
         return util.exportExcel(emrQcExporByDoctorVoList, "责任医师缺陷统计详情");
      }
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/checkDeptCylinder"})
   public AjaxResult checkDeptCylinder(EmrQcFlowStatis emrQcFlowStatis) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (emrQcFlowStatis == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(emrQcFlowStatis.getQcSection())) {
            flag = false;
            ajaxResult = AjaxResult.error("环节编码不能为空");
         }

         if (flag) {
            if (emrQcFlowStatis.getEndTime() != null && emrQcFlowStatis.getBeginTime() != null) {
               Calendar c = Calendar.getInstance();
               c.setTime(emrQcFlowStatis.getEndTime());
               c.add(5, 1);
               emrQcFlowStatis.setEndTime(c.getTime());
            }

            StatisticsResultVo tenVo = this.emrQcFlowStatisService.selectCheckTopDeptCylinder(emrQcFlowStatis);
            StatisticsResultVo afterVo = this.emrQcFlowStatisService.selectCheckAfterDeptCylinder(emrQcFlowStatis);
            ajaxResult.put("top", tenVo);
            ajaxResult.put("after", afterVo);
         }
      } catch (Exception e) {
         this.log.error("科室缺陷统计---柱状图出现异常：", e);
         ajaxResult = AjaxResult.error("科室缺陷统计---柱状图出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/checkDeptTable"})
   public TableDataInfo checkDeptTable(EmrQcFlowStatis emrQcFlowStatis) {
      TableDataInfo tableDataInfo = new TableDataInfo();
      boolean flag = true;

      try {
         if (emrQcFlowStatis == null) {
            flag = false;
            tableDataInfo = new TableDataInfo(500, "参数不能为空");
         }

         if (flag && StringUtils.isEmpty(emrQcFlowStatis.getQcSection())) {
            flag = false;
            tableDataInfo = new TableDataInfo(500, "环节编码不能为空");
         }

         if (flag) {
            if (emrQcFlowStatis.getEndTime() != null && emrQcFlowStatis.getBeginTime() != null) {
               Calendar c = Calendar.getInstance();
               c.setTime(emrQcFlowStatis.getEndTime());
               c.add(5, 1);
               emrQcFlowStatis.setEndTime(c.getTime());
            }

            this.startPage();
            tableDataInfo = this.getDataTable(this.emrQcFlowStatisService.selectCheckDeptTable(emrQcFlowStatis));
         }
      } catch (Exception e) {
         this.log.error("科室缺陷统计---表格出现异常：", e);
         tableDataInfo = new TableDataInfo(500, "科室缺陷统计---表格出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/checkDeptExport"})
   public AjaxResult checkDeptExport(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      new ArrayList();
      if (emrQcFlowStatis == null) {
         return AjaxResult.error(500, "参数不能为空");
      } else if (StringUtils.isEmpty(emrQcFlowStatis.getQcSection())) {
         return AjaxResult.error(500, "环节编码不能为空");
      } else {
         if (emrQcFlowStatis.getEndTime() != null && emrQcFlowStatis.getBeginTime() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(emrQcFlowStatis.getEndTime());
            c.add(5, 1);
            emrQcFlowStatis.setEndTime(c.getTime());
         }

         List<EmrQcFlowStatis> emrQcFlowStatisList = this.emrQcFlowStatisService.selectCheckDeptTable(emrQcFlowStatis);
         List emrQcFlowDeptCodeVoList = (List)emrQcFlowStatisList.stream().map((a) -> {
            EmrQcFlowDeptCodeVo emrQcFlowDeptCodeVo = new EmrQcFlowDeptCodeVo();
            BeanUtils.copyProperties(a, emrQcFlowDeptCodeVo);
            return emrQcFlowDeptCodeVo;
         }).collect(Collectors.toList());
         ExcelUtil<EmrQcFlowDeptCodeVo> util = new ExcelUtil(EmrQcFlowDeptCodeVo.class);
         return util.exportExcel(emrQcFlowDeptCodeVoList, "科室缺陷统计");
      }
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/checkDeptDialogTable"})
   public TableDataInfo checkDeptDialogTable(EmrQcFlowStatis emrQcFlowStatis) {
      TableDataInfo tableDataInfo = new TableDataInfo();
      boolean flag = true;

      try {
         if (emrQcFlowStatis == null) {
            flag = false;
            tableDataInfo = new TableDataInfo(500, "参数不能为空");
         }

         if (flag && StringUtils.isEmpty(emrQcFlowStatis.getQcSection())) {
            flag = false;
            tableDataInfo = new TableDataInfo(500, "环节编码不能为空");
         }

         if (flag && StringUtils.isEmpty(emrQcFlowStatis.getDeptCd())) {
            flag = false;
            tableDataInfo = new TableDataInfo(500, "科室编码不能为空");
         }

         if (flag) {
            if (emrQcFlowStatis.getEndTime() != null && emrQcFlowStatis.getBeginTime() != null) {
               Calendar c = Calendar.getInstance();
               c.setTime(emrQcFlowStatis.getEndTime());
               c.add(5, 1);
               emrQcFlowStatis.setEndTime(c.getTime());
            }

            this.startPage();
            tableDataInfo = this.getDataTable(this.emrQcFlowStatisService.selectCheckDeptDialogTable(emrQcFlowStatis));
         }
      } catch (Exception e) {
         this.log.error("科室缺陷统计---表格出现异常：", e);
         tableDataInfo = new TableDataInfo(500, "科室缺陷统计---表格出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/checkDeptDialogExport"})
   public AjaxResult checkDeptDialogExport(EmrQcFlowStatis emrQcFlowStatis, HttpServletResponse response) throws Exception {
      AjaxResult ajaxResult = AjaxResult.success("导出成功");
      boolean flag = true;

      try {
         if (emrQcFlowStatis == null) {
            return AjaxResult.error(500, "参数不能为空");
         }

         if (StringUtils.isEmpty(emrQcFlowStatis.getQcSection())) {
            return AjaxResult.error(500, "环节编码不能为空");
         }

         if (StringUtils.isEmpty(emrQcFlowStatis.getDeptCd())) {
            return AjaxResult.error(500, "科室编码不能为空");
         }

         if (emrQcFlowStatis.getEndTime() != null && emrQcFlowStatis.getBeginTime() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(emrQcFlowStatis.getEndTime());
            c.add(5, 1);
            emrQcFlowStatis.setEndTime(c.getTime());
         }

         if (flag) {
            return this.emrQcFlowStatisService.selectCheckDeptDialogTableForExport(emrQcFlowStatis, response);
         }
      } catch (Exception e) {
         ajaxResult = AjaxResult.error("导出科室缺陷统计出现异常");
         this.log.error("导出科室缺陷统计出现异常，", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/missingFileCount"})
   public AjaxResult missingFileCount() {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         ajaxResult = AjaxResult.success((Object)this.emrQcFlowStatisService.selectMissingFileCount());
      } catch (Exception e) {
         this.log.error("超时未书写病历---每个病历分类数量：", e);
         ajaxResult = AjaxResult.error("超时未书写病历---每个病历分类数量");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/missingFilePie"})
   public AjaxResult missingFilePie() {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         ajaxResult = AjaxResult.success((Object)this.emrQcFlowStatisService.selectMissingFilePie());
      } catch (Exception e) {
         this.log.error("超时未书写病历---环形图异常：", e);
         ajaxResult = AjaxResult.error("超时未书写病历---环形图异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/missingFileColumn"})
   public AjaxResult missingFileColumn() {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         ajaxResult = AjaxResult.success((Object)this.emrQcFlowStatisService.selectMissingFileColumn());
      } catch (Exception e) {
         this.log.error("超时未书写病历---环形图异常：", e);
         ajaxResult = AjaxResult.error("超时未书写病历---环形图异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/missingFileTable"})
   public TableDataInfo missingFileTable(EmrQcFlowStatis emrQcFlowStatis) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         tableDataInfo = this.getDataTable(this.emrQcFlowStatisService.selectMissingFileTable(emrQcFlowStatis));
      } catch (Exception e) {
         this.log.error("超时未书写---表格详情异常：", e);
         tableDataInfo = new TableDataInfo(500, "超时未书写---表格详情异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/timeOutEmrPie"})
   public AjaxResult timeOutEmrPie(EmrQcFlowStatis emrQcFlowStatis) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         ajaxResult = AjaxResult.success((Object)this.emrQcFlowStatisService.selectTimeOutEmrPie(emrQcFlowStatis));
      } catch (Exception e) {
         this.log.error("超时病历统计---环形图异常：", e);
         ajaxResult = AjaxResult.error("超时病历统计---环形图异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/timeOutEmrColumn"})
   public AjaxResult timeOutEmrColumn(EmrQcFlowStatis emrQcFlowStatis) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         ajaxResult = AjaxResult.success((Object)this.emrQcFlowStatisService.selectTimeOutEmrColumn(emrQcFlowStatis));
      } catch (Exception e) {
         this.log.error("超时病历统计---柱状图异常：", e);
         ajaxResult = AjaxResult.error("超时病历统计---柱状图异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/timeOutEmrTable"})
   public TableDataInfo timeOutEmrTable(EmrQcFlowStatis emrQcFlowStatis) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         if (emrQcFlowStatis != null && emrQcFlowStatis.getDateEnd() != null) {
            Date dateEnd = emrQcFlowStatis.getDateEnd();
            emrQcFlowStatis.setDateEnd(DateUtils.addDays(dateEnd, 1));
         }

         tableDataInfo = this.getDataTable(this.emrQcFlowStatisService.selectTimeOutEmrTable(emrQcFlowStatis));
      } catch (Exception e) {
         this.log.error("超时病历统计---超时书写病历汇总异常：", e);
         tableDataInfo = new TableDataInfo(500, "超时病历统计---超时书写病历汇总异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/timeOutEmrTableExport"})
   public AjaxResult timeOutEmrTableExport(EmrQcFlowStatis emrQcFlowStatis) {
      AjaxResult ajaxResult = AjaxResult.success("导出成功");

      try {
         if (emrQcFlowStatis != null && emrQcFlowStatis.getDateEnd() != null) {
            Date dateEnd = emrQcFlowStatis.getDateEnd();
            emrQcFlowStatis.setDateEnd(DateUtils.addDays(dateEnd, 1));
         }

         List<EmrQcFlowStatisSumExport> list = this.emrQcFlowStatisService.selectTimeOutEmrTableExport(emrQcFlowStatis);
         ExcelUtil<EmrQcFlowStatisSumExport> util = new ExcelUtil(EmrQcFlowStatisSumExport.class);
         ajaxResult = util.exportExcel(list, "超时书写病历汇总");
      } catch (Exception e) {
         ajaxResult = AjaxResult.error("导出超时书写病历汇总异常！");
         this.log.error("导出超时书写病历汇总异常！", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/timeOutEmrTableInfo"})
   public TableDataInfo timeOutEmrTableInfo(EmrQcFlowStatis emrQcFlowStatis) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         if (emrQcFlowStatis != null && emrQcFlowStatis.getDateEnd() != null) {
            Date dateEnd = emrQcFlowStatis.getDateEnd();
            emrQcFlowStatis.setDateEnd(DateUtils.addDays(dateEnd, 1));
         }

         tableDataInfo = this.getDataTable(this.emrQcFlowStatisService.selectTimeOutEmrTableInfo(emrQcFlowStatis));
      } catch (Exception e) {
         this.log.error("超时病历统计---超时书写病历详情异常：", e);
         tableDataInfo = new TableDataInfo(500, "超时病历统计---超时书写病历详情异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/timeOutEmrTableInfoExport"})
   public AjaxResult timeOutEmrTableInfoExport(EmrQcFlowStatis emrQcFlowStatis) {
      AjaxResult ajaxResult = AjaxResult.success("导出成功");

      try {
         if (emrQcFlowStatis != null && emrQcFlowStatis.getDateEnd() != null) {
            Date dateEnd = emrQcFlowStatis.getDateEnd();
            emrQcFlowStatis.setDateEnd(DateUtils.addDays(dateEnd, 1));
         }

         List<EmrQcFlowStatisDetailExport> list = this.emrQcFlowStatisService.selectTimeOutEmrTableInfoExport(emrQcFlowStatis);
         ExcelUtil<EmrQcFlowStatisDetailExport> util = new ExcelUtil(EmrQcFlowStatisDetailExport.class);
         ajaxResult = util.exportExcel(list, "超时书写病历明细");
      } catch (Exception e) {
         ajaxResult = AjaxResult.error("导出超时书写病历明细异常！");
         this.log.error("导出超时书写病历明细异常！", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/doctScorePie"})
   public AjaxResult doctScorePie(EmrQcFlowStatis emrQcFlowStatis) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (emrQcFlowStatis.getEndTime() != null && emrQcFlowStatis.getBeginTime() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(emrQcFlowStatis.getEndTime());
            c.add(5, 1);
            emrQcFlowStatis.setEndTime(c.getTime());
         }

         ajaxResult = AjaxResult.success((Object)this.emrQcFlowStatisService.selectDoctScorePie(emrQcFlowStatis));
      } catch (Exception e) {
         this.log.error("责任医师评分统计报表---环形图出现异常：", e);
         ajaxResult = AjaxResult.error("责任医师评分统计报表---环形图出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/doctScoreDeptPie"})
   public AjaxResult doctScoreDeptPie(EmrQcFlowStatis emrQcFlowStatis) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (emrQcFlowStatis.getEndTime() != null && emrQcFlowStatis.getBeginTime() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(emrQcFlowStatis.getEndTime());
            c.add(5, 1);
            emrQcFlowStatis.setEndTime(c.getTime());
         }

         ajaxResult = AjaxResult.success((Object)this.emrQcFlowStatisService.selectDoctScoreDeptPie(emrQcFlowStatis));
      } catch (Exception e) {
         this.log.error("责任医师评分统计报表---科室质控环形图出现异常：", e);
         ajaxResult = AjaxResult.error("责任医师评分统计报表---科室质控环形图出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/doctScoreCloumn"})
   public AjaxResult doctScoreCloumn(EmrQcFlowStatis emrQcFlowStatis) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (emrQcFlowStatis.getEndTime() != null && emrQcFlowStatis.getBeginTime() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(emrQcFlowStatis.getEndTime());
            c.add(5, 1);
            emrQcFlowStatis.setEndTime(c.getTime());
         }

         StatisticsResultVo top = this.emrQcFlowStatisService.selectDoctScoreTopCloumn(emrQcFlowStatis);
         StatisticsResultVo after = this.emrQcFlowStatisService.selectDoctScoreAfterCloumn(emrQcFlowStatis);
         ajaxResult.put("top", top);
         ajaxResult.put("after", after);
      } catch (Exception e) {
         this.log.error("责任医师评分统计报表---柱状图出现异常：", e);
         ajaxResult = AjaxResult.error("责任医师评分统计报表---柱状图出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/doctScoreDeptCloumn"})
   public AjaxResult doctScoreDeptCloumn(EmrQcFlowStatis emrQcFlowStatis) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (emrQcFlowStatis.getEndTime() != null && emrQcFlowStatis.getBeginTime() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(emrQcFlowStatis.getEndTime());
            c.add(5, 1);
            emrQcFlowStatis.setEndTime(c.getTime());
         }

         StatisticsResultVo top = this.emrQcFlowStatisService.selectDoctScoreDeptTopCloumn(emrQcFlowStatis);
         StatisticsResultVo after = this.emrQcFlowStatisService.selectDoctScoreDeptAfterCloumn(emrQcFlowStatis);
         ajaxResult.put("top", top);
         ajaxResult.put("after", after);
      } catch (Exception e) {
         this.log.error("责任医师评分统计报表---科室柱状图出现异常：", e);
         ajaxResult = AjaxResult.error("责任医师评分统计报表---科室柱状图出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/doctScoreCheckPie"})
   public AjaxResult doctScoreCheckPie(EmrQcFlowStatis emrQcFlowStatis) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (emrQcFlowStatis.getEndTime() != null && emrQcFlowStatis.getBeginTime() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(emrQcFlowStatis.getEndTime());
            c.add(5, 1);
            emrQcFlowStatis.setEndTime(c.getTime());
         }

         ajaxResult = AjaxResult.success((Object)this.emrQcFlowStatisService.selectDoctScoreCheckPie(emrQcFlowStatis));
      } catch (Exception e) {
         this.log.error("责任医师评分统计报表---检查环形图出现异常：", e);
         ajaxResult = AjaxResult.error("责任医师评分统计报表---检查环形图出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/doctScoreCheckCloumn"})
   public AjaxResult doctScoreCheckCloumn(EmrQcFlowStatis emrQcFlowStatis) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (emrQcFlowStatis.getEndTime() != null && emrQcFlowStatis.getBeginTime() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(emrQcFlowStatis.getEndTime());
            c.add(5, 1);
            emrQcFlowStatis.setEndTime(c.getTime());
         }

         StatisticsResultVo top = this.emrQcFlowStatisService.selectDoctScoreCheckTopCloumn(emrQcFlowStatis);
         StatisticsResultVo after = this.emrQcFlowStatisService.selectDoctScoreCheckAfterCloumn(emrQcFlowStatis);
         ajaxResult.put("top", top);
         ajaxResult.put("after", after);
      } catch (Exception e) {
         this.log.error("责任医师评分统计报表---检查柱状图出现异常：", e);
         ajaxResult = AjaxResult.error("责任医师评分统计报表---检查柱状图出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/deptScoreCloumn"})
   public AjaxResult deptScoreCloumn(EmrQcFlowStatis emrQcFlowStatis) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (emrQcFlowStatis.getEndTime() != null && emrQcFlowStatis.getBeginTime() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(emrQcFlowStatis.getEndTime());
            c.add(5, 1);
            emrQcFlowStatis.setEndTime(c.getTime());
         }

         StatisticsResultVo top = this.emrQcFlowStatisService.selectDeptScoreTopCloumn(emrQcFlowStatis);
         StatisticsResultVo after = this.emrQcFlowStatisService.selectDeptScoreAfterCloumn(emrQcFlowStatis);
         ajaxResult.put("top", top);
         ajaxResult.put("after", after);
      } catch (Exception e) {
         this.log.error("科室评分统计报表---终末柱状图出现异常：", e);
         ajaxResult = AjaxResult.error("科室评分统计报表---终末柱状图出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/deptScoreDeptCloumn"})
   public AjaxResult deptScoreDeptCloumn(EmrQcFlowStatis emrQcFlowStatis) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (emrQcFlowStatis.getEndTime() != null && emrQcFlowStatis.getBeginTime() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(emrQcFlowStatis.getEndTime());
            c.add(5, 1);
            emrQcFlowStatis.setEndTime(c.getTime());
         }

         StatisticsResultVo top = this.emrQcFlowStatisService.selectDeptScoreTopDeptCloumn(emrQcFlowStatis);
         StatisticsResultVo after = this.emrQcFlowStatisService.selectDeptScoreAfterDeptCloumn(emrQcFlowStatis);
         ajaxResult.put("top", top);
         ajaxResult.put("after", after);
      } catch (Exception e) {
         this.log.error("科室评分统计报表---科室柱状图出现异常：", e);
         ajaxResult = AjaxResult.error("科室评分统计报表---科室柱状图出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/deptScoreCheckCloumn"})
   public AjaxResult deptScoreCheckCloumn(EmrQcFlowStatis emrQcFlowStatis) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (emrQcFlowStatis.getEndTime() != null && emrQcFlowStatis.getBeginTime() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(emrQcFlowStatis.getEndTime());
            c.add(5, 1);
            emrQcFlowStatis.setEndTime(c.getTime());
         }

         StatisticsResultVo top = this.emrQcFlowStatisService.selectDeptScoreTopCheckCloumn(emrQcFlowStatis);
         StatisticsResultVo after = this.emrQcFlowStatisService.selectDeptScoreAfterCheckCloumn(emrQcFlowStatis);
         ajaxResult.put("top", top);
         ajaxResult.put("after", after);
      } catch (Exception e) {
         this.log.error("科室评分统计报表---检查柱状图出现异常：", e);
         ajaxResult = AjaxResult.error("科室评分统计报表---检查柱状图出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/scoreStatisTable"})
   public TableDataInfo scoreStatisTable(EmrQcFlowStatis emrQcFlowStatis) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         if (emrQcFlowStatis.getEndTime() != null && emrQcFlowStatis.getBeginTime() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(emrQcFlowStatis.getEndTime());
            c.add(5, 1);
            emrQcFlowStatis.setEndTime(c.getTime());
         }

         this.startPage();
         tableDataInfo = this.getDataTable(this.emrQcFlowStatisService.selectScoreStatisTable(emrQcFlowStatis));
      } catch (Exception e) {
         this.log.error("责任医师评分表格出现异常：", e);
         tableDataInfo = new TableDataInfo(500, "责任医师评分表格出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/scoreStatisDialogTable"})
   public TableDataInfo scoreStatisDialogTable(EmrQcFlowStatis emrQcFlowStatis) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         if (emrQcFlowStatis.getEndTime() != null && emrQcFlowStatis.getBeginTime() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(emrQcFlowStatis.getEndTime());
            c.add(5, 1);
            emrQcFlowStatis.setEndTime(c.getTime());
         }

         this.startPage();
         tableDataInfo = this.getDataTable(this.emrQcFlowStatisService.selectScoreStatisDialogTable(emrQcFlowStatis));
      } catch (Exception e) {
         this.log.error("责任医师评分弹框表格出现异常：", e);
         tableDataInfo = new TableDataInfo(500, "责任医师评分弹框表格出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/scoreStatisDeptTable"})
   public TableDataInfo scoreStatisDeptTable(EmrQcFlowStatis emrQcFlowStatis) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         if (emrQcFlowStatis.getEndTime() != null && emrQcFlowStatis.getBeginTime() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(emrQcFlowStatis.getEndTime());
            c.add(5, 1);
            emrQcFlowStatis.setEndTime(c.getTime());
         }

         this.startPage();
         tableDataInfo = this.getDataTable(this.emrQcFlowStatisService.selectScoreStatisDeptTable(emrQcFlowStatis));
      } catch (Exception e) {
         this.log.error("科室评分表格出现异常：", e);
         tableDataInfo = new TableDataInfo(500, "科室评分表格出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('td:apply:statisQuery')")
   @GetMapping({"/consCheckPie"})
   public AjaxResult consCheckPie(TdCaConsApplyVo tdCaConsApply) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (tdCaConsApply.getStartDate() != null && tdCaConsApply.getEndDate() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(tdCaConsApply.getEndDate());
            c.add(5, 1);
            tdCaConsApply.setEndDate(c.getTime());
         }

         ajaxResult = AjaxResult.success((Object)this.emrQcFlowStatisService.selectConsCheckPie(tdCaConsApply));
      } catch (Exception e) {
         this.log.error("会诊环形图出现异常：", e);
         ajaxResult = AjaxResult.error("会诊环形图出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('td:apply:statisQuery')")
   @GetMapping({"/consCylinder"})
   public AjaxResult consCylinder(TdCaConsApplyVo tdCaConsApply) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (tdCaConsApply.getStartDate() != null && tdCaConsApply.getEndDate() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(tdCaConsApply.getEndDate());
            c.add(5, 1);
            tdCaConsApply.setEndDate(c.getTime());
         }

         ajaxResult = AjaxResult.success((Object)this.emrQcFlowStatisService.selectConsCylinder(tdCaConsApply));
      } catch (Exception e) {
         this.log.error("会诊折线图出现异常：", e);
         ajaxResult = AjaxResult.error("会诊折线图出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:home:list')")
   @GetMapping({"/homeFeeCloumn"})
   public AjaxResult homeFeeCloumn() {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         StatisticsResultVo dept = this.emrQcFlowStatisService.selectHomeDeptFeeCloumn();
         ajaxResult.put("dept", dept);
         StatisticsResultVo doctor = this.emrQcFlowStatisService.selectHomeDoctorFeeCloumn();
         ajaxResult.put("doctor", doctor);
      } catch (Exception e) {
         this.log.error("科室评分统计报表---科室柱状图出现异常：", e);
         ajaxResult = AjaxResult.error("科室评分统计报表---科室柱状图出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/selectQcListByMrType"})
   public TableDataInfo selectQcListByMrType(EmrQcFlowStatis emrQcFlowStatis) {
      TableDataInfo tableDataInfo = new TableDataInfo();
      boolean flag = true;

      try {
         if (emrQcFlowStatis == null) {
            flag = false;
            tableDataInfo = new TableDataInfo(500, "参数不能为空");
         }

         if (flag && StringUtils.isEmpty(emrQcFlowStatis.getQcSection())) {
            flag = false;
            tableDataInfo = new TableDataInfo(500, "环节编码不能为空");
         }

         if (flag) {
            if (emrQcFlowStatis.getDateEnd() != null && emrQcFlowStatis.getDateStart() != null) {
               Calendar c = Calendar.getInstance();
               c.setTime(emrQcFlowStatis.getDateEnd());
               c.add(5, 1);
               emrQcFlowStatis.setDateEnd(c.getTime());
            }

            this.startPage();
            tableDataInfo = this.getDataTable(this.emrQcFlowStatisService.selectQcListByMrType(emrQcFlowStatis));
         }
      } catch (Exception e) {
         this.log.error("病历类型统计出现异常：", e);
         tableDataInfo = new TableDataInfo(500, "病历类型统计出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/selectQcListByMrTypeForRanking"})
   public AjaxResult selectQcListByMrTypeForRanking(EmrQcFlowStatis emrQcFlowStatis) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (emrQcFlowStatis == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(emrQcFlowStatis.getQcSection())) {
            flag = false;
            ajaxResult = AjaxResult.error("环节编码不能为空");
         }

         if (flag) {
            if (emrQcFlowStatis.getDateEnd() != null && emrQcFlowStatis.getDateStart() != null) {
               Calendar c = Calendar.getInstance();
               c.setTime(emrQcFlowStatis.getDateEnd());
               c.add(5, 1);
               emrQcFlowStatis.setDateEnd(c.getTime());
            }

            List<EmrQcListStatisticByMrTypeVo> topSix = this.emrQcFlowStatisService.selectQcListByMrTypeTopSix(emrQcFlowStatis);
            ajaxResult.put("topSix", topSix);
            if (topSix != null && topSix.size() > 0 && ((EmrQcListStatisticByMrTypeVo)topSix.get(0)).getFlawSum() != null) {
               ajaxResult.put("sumTotal", ((EmrQcListStatisticByMrTypeVo)topSix.get(0)).getFlawSum());
            } else {
               ajaxResult.put("sumTotal", 0);
            }

            List<EmrQcListStatisticByMrTypeVo> topTen = this.emrQcFlowStatisService.selectQcListByMrTypeTopTen(emrQcFlowStatis);
            ajaxResult.put("topTen", topTen);
         }
      } catch (Exception e) {
         this.log.error("病历类型统计出现异常：", e);
         ajaxResult = AjaxResult.error("病历类型统计出现异常");
      }

      return ajaxResult;
   }
}
