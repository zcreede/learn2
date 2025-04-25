package com.emr.project.report.controller;

import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.framework.web.page.TableSumDataInfo;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.report.domain.dto.BeHospitalizedDTO;
import com.emr.project.report.domain.dto.ChangeDeptTableListDTO;
import com.emr.project.report.domain.dto.LeaveHospitalizedTableDTO;
import com.emr.project.report.domain.req.BeHospitalizedTableReq;
import com.emr.project.report.domain.req.ChangeDeptTableListReq;
import com.emr.project.report.domain.req.LeaveHospitalizedTableReq;
import com.emr.project.report.domain.req.PatientWorkloadReq;
import com.emr.project.report.domain.resp.PatientWorkloadResp;
import com.emr.project.report.domain.vo.VisitinfoExportVo;
import com.emr.project.report.service.IPatientInOutService;
import com.emr.project.tmpm.domain.PrintTmplInfo;
import com.emr.project.tmpm.service.IPrintTmplInfoService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/systemQuery"})
public class PatientInOutController extends BaseController {
   @Autowired
   private IPatientInOutService patientInOutService;
   @Autowired
   private IPrintTmplInfoService printTmplInfoService;
   @Autowired
   private IVisitinfoService visitinfoService;

   @GetMapping({"/getBeHospitalizedTableList"})
   @PreAuthorize("@ss.hasPermi('query:systemQuery:beHospitalizedTableList')")
   public TableDataInfo getBeHospitalizedTableList(BeHospitalizedTableReq req) {
      TableDataInfo tableDataInfo = new TableDataInfo();
      if (req.getPageSize() != null && req.getPageNum() != null) {
         try {
            this.startPage();
            List<BeHospitalizedDTO> list = this.patientInOutService.getBeHospitalizedTableList(req);
            tableDataInfo = this.getDataTable(list);
         } catch (Exception e) {
            this.log.error("查询入院患者统计失败", e);
         }

         return tableDataInfo;
      } else {
         tableDataInfo = new TableDataInfo(500, "分页参数不能为空");
         return tableDataInfo;
      }
   }

   @PreAuthorize("@ss.hasPermi('query:systemQuery:beHospitalizedTableList')")
   @GetMapping({"/inHospitalExport"})
   public AjaxResult inHospitalExport(BeHospitalizedTableReq req) {
      List<BeHospitalizedDTO> list = this.patientInOutService.getBeHospitalizedTableList(req);
      ExcelUtil<BeHospitalizedDTO> util = new ExcelUtil(BeHospitalizedDTO.class);
      return util.exportExcel(list, "入院患者统计");
   }

   @GetMapping({"/getLeaveHospitalizedTableList"})
   @PreAuthorize("@ss.hasPermi('query:systemQuery:leaveHospitalizedTableList')")
   public TableSumDataInfo getLeaveHospitalizedTableList(LeaveHospitalizedTableReq req) {
      TableSumDataInfo tableDataInfo = new TableSumDataInfo();
      if (req.getPageSize() != null && req.getPageNum() != null) {
         try {
            Map<String, Object> map = this.patientInOutService.getLeaveHospitalizedTableList(req);
            List<LeaveHospitalizedTableDTO> listAll = (List)map.get("listAll");
            List<LeaveHospitalizedTableDTO> list = this.patientInOutService.getLeaveHospitalizedTableListPage(listAll, req);
            tableDataInfo.setCode(200);
            tableDataInfo.setMsg("查询成功");
            tableDataInfo.setRows(list);
            tableDataInfo.setTotal((long)listAll.size());
            tableDataInfo.setDays(new BigDecimal(map.get("days").toString()));
            tableDataInfo.setDrugFee(new BigDecimal(map.get("drugFee").toString()));
            tableDataInfo.setTotalFee(new BigDecimal(map.get("totalFee").toString()));
         } catch (Exception e) {
            this.log.error("查询出院患者统计失败", e);
         }

         return tableDataInfo;
      } else {
         tableDataInfo = new TableSumDataInfo(500, "分页参数不能为空");
         return tableDataInfo;
      }
   }

   @PreAuthorize("@ss.hasPermi('query:systemQuery:leaveHospitalizedTableList')")
   @GetMapping({"/leaveHospitalExport"})
   public AjaxResult leaveHospitalExport(LeaveHospitalizedTableReq req) {
      Map<String, Object> map = this.patientInOutService.getLeaveHospitalizedTableList(req);
      List<LeaveHospitalizedTableDTO> listAll = (List)map.get("listAll");
      ExcelUtil<LeaveHospitalizedTableDTO> util = new ExcelUtil(LeaveHospitalizedTableDTO.class);
      return util.exportExcel(listAll, "出院患者统计");
   }

   @GetMapping({"/getChangeDeptTableList"})
   @PreAuthorize("@ss.hasPermi('query:systemQuery:changeDeptTableList')")
   public TableDataInfo getChangeDeptTableList(ChangeDeptTableListReq req) {
      TableDataInfo tableDataInfo = new TableDataInfo();
      if (req.getPageSize() != null && req.getPageNum() != null) {
         try {
            this.startPage();
            List<ChangeDeptTableListDTO> list = this.patientInOutService.getChangeDeptTableList(req);
            tableDataInfo = this.getDataTable(list);
         } catch (Exception e) {
            this.log.error("查询入院患者统计失败", e);
         }

         return tableDataInfo;
      } else {
         tableDataInfo = new TableDataInfo(500, "分页参数不能为空");
         return tableDataInfo;
      }
   }

   @PreAuthorize("@ss.hasPermi('query:systemQuery:changeDeptTableList')")
   @GetMapping({"/changeDeptHospitalExport"})
   public AjaxResult changeDeptHospitalExport(ChangeDeptTableListReq req) {
      List<ChangeDeptTableListDTO> list = this.patientInOutService.getChangeDeptTableList(req);
      ExcelUtil<ChangeDeptTableListDTO> util = new ExcelUtil(ChangeDeptTableListDTO.class);
      return util.exportExcel(list, "转科患者统计");
   }

   @PreAuthorize("@ss.hasPermi('query:systemQuery:inHospitalizedTableList')")
   @GetMapping({"/patientExport"})
   public AjaxResult patientExport(VisitinfoVo visitinfoVo) {
      new ArrayList();
      List<VisitinfoExportVo> resultList = new ArrayList();

      try {
         for(VisitinfoVo vo : this.visitinfoService.selectVisitinfoVoList(visitinfoVo)) {
            VisitinfoExportVo exportVo = new VisitinfoExportVo();
            BeanUtils.copyProperties(vo, exportVo);
            resultList.add(exportVo);
         }
      } catch (Exception e) {
         this.log.error("查询患者一栏导出数据出现异常，", e);
      }

      ExcelUtil<VisitinfoExportVo> util = new ExcelUtil(VisitinfoExportVo.class);
      return util.exportExcel(resultList, "在院患者一览表");
   }

   @GetMapping({"/patient/queryPatientWorkload"})
   @PreAuthorize("@ss.hasPermi('query:systemQuery:patientWorkload')")
   public AjaxResult queryPatientWorkload(PatientWorkloadReq req) {
      AjaxResult resultMsg = AjaxResult.success("查询成功");
      String type = req.getType();
      if (StringUtils.isEmpty(type)) {
         resultMsg = AjaxResult.error("查询类型不能为空！");
         return resultMsg;
      } else {
         try {
            List<PatientWorkloadResp> list = this.patientInOutService.queryPatientWorkload(req);
            resultMsg.put("data", list);
         } catch (Exception e) {
            resultMsg = AjaxResult.error("查询科室收入统计-按患者数据异常!");
            this.log.error("查询科室收入统计-按患者数据异常，请联系管理员！", e);
         }

         return resultMsg;
      }
   }

   @GetMapping({"/patient/queryPrintTmpl"})
   @PreAuthorize("@ss.hasPermi('query:systemQuery:patientWorkload')")
   public AjaxResult queryPrintTmpl() {
      AjaxResult resultMsg = AjaxResult.success("查询成功");

      try {
         List<String> typeCodeList = new ArrayList();
         typeCodeList.add("10001901");
         List<PrintTmplInfo> printTmplInfos = this.printTmplInfoService.selectTmPmPrintTmplInfoByCodes(typeCodeList);
         resultMsg.put("printTmplList", printTmplInfos);
      } catch (Exception var4) {
         resultMsg = AjaxResult.error("查询打印模板异常！");
      }

      return resultMsg;
   }
}
