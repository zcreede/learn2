package com.emr.project.report.controller;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.report.domain.req.DepartmentStatisticsDataReq;
import com.emr.project.report.domain.req.PatientWorkloadReq;
import com.emr.project.report.domain.resp.DepartmentStatisticsDataResp;
import com.emr.project.report.domain.resp.DeptWorkloadResp;
import com.emr.project.report.domain.resp.FeeWorkloadAllListResp;
import com.emr.project.report.service.DepartmentIncomeService;
import com.emr.project.system.domain.SysUser;
import com.emr.project.tmpm.domain.PrintTmplInfo;
import com.emr.project.tmpm.service.IPrintTmplInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Api(
   value = "departmentIncomeController",
   tags = {"科室收入报表"}
)
@RestController
@RequestMapping({"/department/income"})
public class DepartmentIncomeController {
   private static Logger log = LoggerFactory.getLogger(DepartmentIncomeController.class);
   @Autowired
   private DepartmentIncomeService departmentIncomeService;
   @Autowired
   private IPrintTmplInfoService printTmplInfoService;

   @ApiOperation("查询科室收入汇总数据")
   @ResponseBody
   @PostMapping({"/departmentStatisticsData"})
   @PreAuthorize("@ss.hasPermi('query:department:statistics:data')")
   public AjaxResult departmentStatisticsData(@RequestBody DepartmentStatisticsDataReq req) {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      AjaxResult resultMsg = AjaxResult.success("查询成功");
      if (req == null) {
         resultMsg = AjaxResult.error("参数不能为空！");
         return resultMsg;
      } else if (StringUtils.isBlank(req.getCostType())) {
         resultMsg = AjaxResult.error("费用类型不能为空！");
         return resultMsg;
      } else if (!req.getCostType().equals("1") && !req.getCostType().equals("2")) {
         resultMsg = AjaxResult.error("费用类型不正确！");
         return resultMsg;
      } else if (StringUtils.isBlank(req.getDepartmentType())) {
         resultMsg = AjaxResult.error("科室类型不能为空！");
         return resultMsg;
      } else if (!StringUtils.isBlank(req.getDateStart()) && !StringUtils.isBlank(req.getDateEnd())) {
         try {
            List<DepartmentStatisticsDataResp> dataList = this.departmentIncomeService.selectDepartmentStatisticsDataList(req);
            resultMsg.put("dataList", dataList);
            resultMsg.put("hospitalName", sysUser.getHospital().getOrgName());
            resultMsg.put("depName", sysUser.getDeptName());
            resultMsg.put("staffName", sysUser.getNickName());
         } catch (Exception e) {
            log.error("查询科室收入统计数据出现异常：", e);
            resultMsg = AjaxResult.error("查询科室收入统计数据出现异常，请联系管理员");
         }

         return resultMsg;
      } else {
         resultMsg = AjaxResult.error("统计日期不能为空！");
         return resultMsg;
      }
   }

   @ApiOperation("查询科室收入汇总-按医师数据")
   @ResponseBody
   @PostMapping({"/doctor/queryWorkloadFee"})
   @PreAuthorize("@ss.hasPermi('query:department:statistics:doctor:data')")
   public AjaxResult queryWorkloadFee(@RequestBody DepartmentStatisticsDataReq req) {
      AjaxResult resultMsg = AjaxResult.success("查询成功");
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      if (req == null) {
         resultMsg = AjaxResult.error("参数不能为空！");
         return resultMsg;
      } else if (StringUtils.isBlank(req.getCostType())) {
         resultMsg = AjaxResult.error("费用类型不能为空！");
         return resultMsg;
      } else if (!req.getCostType().equals("1") && !req.getCostType().equals("2")) {
         resultMsg = AjaxResult.error("费用类型不正确！");
         return resultMsg;
      } else if (StringUtils.isBlank(req.getDepartmentType())) {
         resultMsg = AjaxResult.error("科室类型不能为空！");
         return resultMsg;
      } else if (!StringUtils.isBlank(req.getDateStart()) && !StringUtils.isBlank(req.getDateEnd())) {
         try {
            FeeWorkloadAllListResp data = this.departmentIncomeService.selectFeeWorkloadList(req);
            resultMsg.put("data", data);
            resultMsg.put("hospitalName", sysUser.getHospital().getOrgName());
            resultMsg.put("depName", sysUser.getDeptName());
            resultMsg.put("staffName", sysUser.getNickName());
         } catch (Exception e) {
            log.error("查询科室收入统计-按医师统计数据出现异常：", e);
            resultMsg = AjaxResult.error("查询科室收入统计-按医师统计数据出现异常，请联系管理员");
         }

         return resultMsg;
      } else {
         resultMsg = AjaxResult.error("统计日期不能为空！");
         return resultMsg;
      }
   }

   @ApiOperation("查询科室收入汇总-按执行科室")
   @ResponseBody
   @PostMapping({"/queryDeptWorkload"})
   @PreAuthorize("@ss.hasPermi('query:department:statistics:data')")
   public AjaxResult queryDeptWorkload(@RequestBody PatientWorkloadReq req) {
      AjaxResult resultMsg = AjaxResult.success("查询成功");
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      if (req == null) {
         resultMsg = AjaxResult.error("参数不能为空！");
         return resultMsg;
      } else if (StringUtils.isBlank(req.getType())) {
         resultMsg = AjaxResult.error("查询类型不能为空！");
         return resultMsg;
      } else if (StringUtils.isBlank(req.getDepartmentType())) {
         resultMsg = AjaxResult.error("汇总类型不能为空！");
         return resultMsg;
      } else if (StringUtils.isBlank(req.getBeginDate())) {
         resultMsg = AjaxResult.error("开始日期不能为空！");
         return resultMsg;
      } else if (StringUtils.isBlank(req.getEndDate())) {
         resultMsg = AjaxResult.error("结束日期不能为空！");
         return resultMsg;
      } else {
         try {
            List<DeptWorkloadResp> list = this.departmentIncomeService.queryDeptWorkload(req);
            resultMsg.put("data", list);
            resultMsg.put("hospitalName", sysUser.getHospital().getOrgName());
            resultMsg.put("depName", sysUser.getDeptName());
            resultMsg.put("staffName", sysUser.getNickName());
            List<String> typeCodeList = new ArrayList(Arrays.asList("100019002"));
            List<PrintTmplInfo> printTmplList = this.printTmplInfoService.selectTmPmPrintTmplInfoByCodes(typeCodeList);
            resultMsg.put("printTmplList", printTmplList);
         } catch (Exception e) {
            log.error("查询科室收入统计-按执行科室查询数据出现异常：", e);
            resultMsg = AjaxResult.error("查询科室收入统计-按执行科室查询数据出现异常，请联系管理员");
         }

         return resultMsg;
      }
   }
}
