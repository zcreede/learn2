package com.emr.project.pat.controller;

import com.emr.common.utils.DateUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.enums.DataSourceType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.pat.domain.ExpenseDetail;
import com.emr.project.pat.domain.req.FeeDetailReq;
import com.emr.project.pat.domain.resp.DrugsAndPriceYyResp;
import com.emr.project.pat.domain.vo.ExpenseDetailVo;
import com.emr.project.pat.domain.vo.PatFeeVo;
import com.emr.project.pat.service.IPatFeeService;
import com.emr.project.system.domain.SyncDatasource;
import com.emr.project.system.service.ISyncDatasourceService;
import com.emr.project.tmpm.domain.PrintTmplInfo;
import com.emr.project.tmpm.service.IPrintTmplInfoService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/pat/fee"})
public class PatFeeController extends BaseController {
   @Autowired
   private IPatFeeService iPatFeeService;
   @Autowired
   private ISyncDatasourceService iSyncDatasourceService;
   @Autowired
   private IPrintTmplInfoService printTmplInfoService;

   @PreAuthorize("@ss.hasPermi('pat:visitinfo:allList')")
   @GetMapping({"/amount"})
   public AjaxResult amount(PatFeeVo patFeeVo) {
      new AjaxResult();

      AjaxResult ajaxResult;
      try {
         if (StringUtils.isEmpty(patFeeVo.getInpNo())) {
            ajaxResult = AjaxResult.error("住院号参数不能为空");
         } else {
            PatFeeVo patFee = this.iPatFeeService.selectAmountList(patFeeVo);
            ajaxResult = AjaxResult.success((Object)patFee);
         }
      } catch (Exception e) {
         this.log.error("查询费用信息列表出现异常,", e);
         ajaxResult = AjaxResult.error("查询费用信息列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('pat:visitinfo:allList')")
   @GetMapping({"/list"})
   public AjaxResult list(PatFeeVo patFeeVo) {
      List<PatFeeVo> list = null;
      AjaxResult ajaxResult = new AjaxResult();

      try {
         if (StringUtils.isEmpty(patFeeVo.getInpNo())) {
            ajaxResult = AjaxResult.error("住院号参数不能为空");
         } else {
            SyncDatasource syncDatasource = this.iSyncDatasourceService.selectSyncDatasourceByCode(DataSourceType.hisPatFees.toString());
            if (syncDatasource != null) {
               patFeeVo.setSqlStr(syncDatasource.getQuerySql());
               list = this.iPatFeeService.selectFeeList(patFeeVo);
               ajaxResult = AjaxResult.success((Object)list);
            }
         }
      } catch (Exception e) {
         this.log.error("查询费用类型出现异常,", e);
         ajaxResult = AjaxResult.error("查询费用类型出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('pat:visitinfo:allList')")
   @GetMapping({"/feeListByChargeCode"})
   public AjaxResult feeListByChargeCode(ExpenseDetailVo expenseDetailVo) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;
      List<ExpenseDetail> detailList = null;

      try {
         if (expenseDetailVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空！");
         }

         if (StringUtils.isBlank(expenseDetailVo.getInpNo())) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空！");
         }

         if (flag) {
            detailList = this.iPatFeeService.selectFeeByChargeCode(expenseDetailVo);
            ajaxResult = AjaxResult.success((Object)detailList);
         }
      } catch (Exception e) {
         this.log.error("首页查询费用按记账项目分类汇总列表出现异常：", e);
         ajaxResult = AjaxResult.error("首页查询费用按记账项目分类汇总列表出现异常，请联系管理员");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('pat:visitinfo:allList')")
   @GetMapping({"/feeListByThreeLevelAccounting"})
   public AjaxResult feeListByThreeLevelAccounting(ExpenseDetailVo expenseDetailVo) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;
      List<ExpenseDetail> detailList = null;

      try {
         if (expenseDetailVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空！");
         }

         if (StringUtils.isBlank(expenseDetailVo.getInpNo())) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空！");
         }

         if (flag) {
            detailList = this.iPatFeeService.selectFeeByThreeLevelAccounting(expenseDetailVo);
            ajaxResult = AjaxResult.success((Object)detailList);
         }
      } catch (Exception e) {
         this.log.error("首页查询费用按三级项目分类汇总列表出现异常：", e);
         ajaxResult = AjaxResult.error("首页查询费用按三级项目分类汇总列表出现异常，请联系管理员");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('pat:visitinfo:allList')")
   @GetMapping({"/feeListByDate"})
   public AjaxResult feeListByDate(ExpenseDetailVo expenseDetailVo) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;
      List<ExpenseDetailVo> detailList = null;

      try {
         if (expenseDetailVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空！");
         }

         if (StringUtils.isBlank(expenseDetailVo.getInpNo())) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空！");
         }

         if (flag) {
            detailList = this.iPatFeeService.selectFeeByDate(expenseDetailVo);
            ajaxResult = AjaxResult.success((Object)detailList);
         }
      } catch (Exception e) {
         this.log.error("首页查询费用按记账日期汇总列表出现异常：", e);
         ajaxResult = AjaxResult.error("首页查询费用按记账日期汇总列表出现异常，请联系管理员");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('pat:visitinfo:allList')")
   @GetMapping({"/feeListByType"})
   public AjaxResult feeListByType(ExpenseDetailVo expenseDetailVo) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;
      List<ExpenseDetail> detailList = null;

      try {
         if (expenseDetailVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空！");
         }

         if (StringUtils.isBlank(expenseDetailVo.getInpNo())) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空！");
         }

         if (StringUtils.isBlank(expenseDetailVo.getFilingDateStr()) && StringUtils.isBlank(expenseDetailVo.getThreeLevelAccounting()) && StringUtils.isBlank(expenseDetailVo.getChargeNo())) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空！");
         }

         if (StringUtils.isNotBlank(expenseDetailVo.getFilingDateStr())) {
            Date filingDate = DateUtils.parseDate(expenseDetailVo.getFilingDateStr());
            expenseDetailVo.setFilingDateStart(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, filingDate));
            expenseDetailVo.setFilingDateEnd(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, DateUtils.addDays(filingDate, 1)));
         }

         if (flag) {
            detailList = this.iPatFeeService.selectFeeByType(expenseDetailVo);
            ajaxResult = AjaxResult.success((Object)detailList);
         }
      } catch (Exception e) {
         this.log.error("首页查询费用明细列表出现异常：", e);
         ajaxResult = AjaxResult.error("首页查询费用明细列表出现异常，请联系管理员");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('oper:room:feeDetailList')")
   @GetMapping({"/feeDetailList"})
   public AjaxResult feeDetailList(FeeDetailReq req) {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         if (StringUtils.isNotBlank(req.getFilingDateStart()) && StringUtils.isNotBlank(req.getFilingDateEnd())) {
            req.setFilingDateStart(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, DateUtils.parseDate(req.getFilingDateStart())));
            req.setFilingDateEnd(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, DateUtils.addDays(DateUtils.parseDate(req.getFilingDateEnd()), 1)));
         }

         req.setGroupType(StringUtils.isBlank(req.getGroupType()) ? null : req.getGroupType());
         List<ExpenseDetailVo> detailList = this.iPatFeeService.selectOperRoomFeeDetailList(req);
         ajaxResult = AjaxResult.success((Object)detailList);
         if (detailList != null && !detailList.isEmpty()) {
            ajaxResult.put("amount", req.getAmount());
         }
      } catch (Exception e) {
         this.log.error("费用明细查询----手术室异常：", e);
         ajaxResult = AjaxResult.error("费用明细查询----手术室异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('oper:room:feeDetailList')")
   @GetMapping({"/exportFeeDetailList"})
   public AjaxResult exportFeeDetailList(FeeDetailReq req) {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         if (StringUtils.isNotBlank(req.getFilingDateStart()) && StringUtils.isNotBlank(req.getFilingDateEnd())) {
            req.setFilingDateStart(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, DateUtils.parseDate(req.getFilingDateStart())));
            req.setFilingDateEnd(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, DateUtils.addDays(DateUtils.parseDate(req.getFilingDateEnd()), 1)));
         }

         req.setGroupType(StringUtils.isBlank(req.getGroupType()) ? null : req.getGroupType());
         List<ExpenseDetailVo> detailList = this.iPatFeeService.exportFeeDetailList(req);
         ajaxResult = AjaxResult.success((Object)detailList);
      } catch (Exception e) {
         this.log.error("导出费用明细查询----手术室异常：", e);
         ajaxResult = AjaxResult.error("导出费用明细查询----手术室异常");
      }

      return ajaxResult;
   }

   @RequestMapping({"/searchDrugsAndPriceYy"})
   @ResponseBody
   public AjaxResult searchDrugsAndPriceYy(String keyword) {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         List<DrugsAndPriceYyResp> resList = this.iPatFeeService.searchdrugs2Resp(keyword);
         if (resList == null) {
            resList = new ArrayList();
         }

         List<DrugsAndPriceYyResp> priceYyList = this.iPatFeeService.searchPriceYy(keyword);
         if (priceYyList != null && !priceYyList.isEmpty()) {
            resList.addAll(priceYyList);
         }

         ajaxResult = AjaxResult.success((Object)resList);
      } catch (Exception e) {
         this.log.error("查询项目列表出现异常，", e);
         ajaxResult = AjaxResult.error("查询项目列表出现异常----手术室异常");
      }

      return ajaxResult;
   }

   @GetMapping({"/getInfo"})
   public AjaxResult getInfo() {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         List<String> typeCodeList = new ArrayList(Arrays.asList("100032001", "100032002", "100032003", "100032004", "100032005", "100032006"));
         List<PrintTmplInfo> printTmplList = this.printTmplInfoService.selectTmPmPrintTmplInfoByCodes(typeCodeList);
         ajaxResult = AjaxResult.success((Object)printTmplList);
      } catch (Exception var4) {
         this.log.error("获取打印费用明细打印模板出现异常！");
         ajaxResult = AjaxResult.error("获取打印费用明细打印模板出现异常！");
      }

      return ajaxResult;
   }
}
