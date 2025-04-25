package com.emr.project.operation.controller;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.ip.IpUtils;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.operation.domain.TakeDrugList;
import com.emr.project.operation.domain.TakeDrugListKey;
import com.emr.project.operation.domain.TakeDrugReturn;
import com.emr.project.operation.domain.dto.TakeDrugFeeDTO;
import com.emr.project.operation.domain.req.CancelReturnApplyReq;
import com.emr.project.operation.domain.req.ReturnApplyPageReq;
import com.emr.project.operation.domain.req.SaveTakeDrugFeeReq;
import com.emr.project.operation.domain.req.SaveUnTakeDrugReq;
import com.emr.project.operation.domain.req.TakeDrugFeeReq;
import com.emr.project.operation.domain.req.UnTakeDrugDetailReq;
import com.emr.project.operation.domain.req.UnTakeDrugReq;
import com.emr.project.operation.domain.resp.UnTakeDrugResp;
import com.emr.project.operation.service.ExpenseDetailService;
import com.emr.project.operation.service.IPatientsReturnDrugService;
import com.emr.project.operation.service.TakeDrugListService;
import com.emr.project.operation.service.TakeDrugReturnService;
import com.emr.project.pat.domain.ExpenseDetail;
import com.emr.project.system.domain.SysUser;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/operation/refundDrug"})
public class PatientsReturnDrugController extends BaseController {
   private static Logger log = LoggerFactory.getLogger(PatientsReturnDrugController.class);
   @Autowired
   private IPatientsReturnDrugService patientsReturnDrugService;
   @Autowired
   private TakeDrugListService takeDrugListService;
   @Autowired
   private TakeDrugReturnService takeDrugReturnService;
   @Autowired
   private ExpenseDetailService expenseDetailService;

   @GetMapping({"/unTakeDrugList"})
   @PreAuthorize("@ss.hasAnyPermi('operation:refundDrug:unTakeDrugList')")
   public AjaxResult unTakeDrugList(UnTakeDrugReq req) {
      AjaxResult resultMsg = AjaxResult.success("查询成功");

      try {
         List<UnTakeDrugResp> list = this.patientsReturnDrugService.unTakeDrugList(req);
         resultMsg.put("data", list);
      } catch (Exception e) {
         log.error("查询患者未领药列表失败", e);
         resultMsg = AjaxResult.error("查询患者未领药列表失败，请联系管理员");
      }

      return resultMsg;
   }

   @ResponseBody
   @RequestMapping({"/saveUnTakeDrug"})
   @PreAuthorize("@ss.hasAnyPermi('operation:refundDrug:saveUnTakeDrug')")
   public AjaxResult saveUnTakeDrug(@RequestBody SaveUnTakeDrugReq req, HttpServletRequest request) {
      AjaxResult resultMsg = AjaxResult.success("保存成功");
      List<UnTakeDrugDetailReq> list = req.getDetailList();
      if (list != null && (list == null || !list.isEmpty())) {
         if (StringUtils.isEmpty(req.getAdmissionNo())) {
            resultMsg = AjaxResult.error("住院号不能为空！");
            return resultMsg;
         } else {
            for(UnTakeDrugDetailReq temp : list) {
               TakeDrugListKey takeDrugListKey = new TakeDrugListKey(temp.getPerformListNo(), Integer.parseInt(temp.getPerformListSortNumber()), temp.getOrderSortNumber(), temp.getOrderGroupSortNumber(), temp.getOrderGroupNo());
               TakeDrugList takeDrugList = this.takeDrugListService.queryByPrimaryKey(takeDrugListKey);
               if ((new BigDecimal(temp.getApplyDose())).compareTo(takeDrugList.getOrderDose()) > 0) {
                  resultMsg = AjaxResult.error("申请数量超过了可申请数量，请重新填写！");
                  return resultMsg;
               }
            }

            try {
               SysUser user = SecurityUtils.getLoginUser().getUser();
               String ip = IpUtils.getIpAddr(request);
               this.takeDrugListService.unTakeDrugReturn(req, user, ip);
            } catch (Exception e) {
               resultMsg = AjaxResult.error("保存异常，请联系管理员");
               log.error("保存成功出现异常：", e);
            }

            return resultMsg;
         }
      } else {
         resultMsg = AjaxResult.error("参数不能为空！");
         return resultMsg;
      }
   }

   @ResponseBody
   @RequestMapping({"/returnApplyPage"})
   @PreAuthorize("@ss.hasAnyPermi('operation:refundDrug:returnApplyPage')")
   public AjaxResult returnApplyPage(ReturnApplyPageReq req) {
      AjaxResult resultMsg = AjaxResult.success("查询成功");

      try {
         List<TakeDrugReturn> list = this.patientsReturnDrugService.queryPageList(req);
         resultMsg.put("data", list);
      } catch (Exception e) {
         log.error("查询退药申请列表出现异常：", e);
         resultMsg = AjaxResult.error("查询退药申请列表出现异常，请联系管理员");
      }

      return resultMsg;
   }

   @ResponseBody
   @RequestMapping({"/cancel"})
   @PreAuthorize("@ss.hasAnyPermi('operation:refundDrug:cancel')")
   public AjaxResult cancelReturnApply(@RequestBody CancelReturnApplyReq req) {
      AjaxResult resultMsg = AjaxResult.success("保存退药申请成功");
      if (req == null) {
         resultMsg = AjaxResult.error("参数不能为空！");
         return resultMsg;
      } else {
         List<TakeDrugReturn> params = req.getParams();
         if (params != null && params.size() != 0) {
            try {
               List<TakeDrugReturn> takeDrugReturnList = new ArrayList(1);
               List<String> serialNumberList = (List)params.stream().map(TakeDrugReturn::getSerialNumber).collect(Collectors.toList());
               List<TakeDrugReturn> dbList = this.takeDrugReturnService.queryBySerialNumberList(serialNumberList);
               Map<String, TakeDrugReturn> dbMap = (Map)dbList.stream().collect(Collectors.toMap((t) -> t.getSerialNumber() + "_" + t.getSerialNumberXh(), Function.identity()));

               for(int i = 0; i < params.size(); ++i) {
                  TakeDrugReturn takeDrugReturn = (TakeDrugReturn)params.get(i);
                  TakeDrugReturn temp = (TakeDrugReturn)dbMap.get(takeDrugReturn.getSerialNumber() + "_" + takeDrugReturn.getSerialNumberXh());
                  if (temp.getMark().equals("0")) {
                     takeDrugReturnList.add(takeDrugReturn);
                  }
               }

               if (takeDrugReturnList == null || takeDrugReturnList != null && takeDrugReturnList.isEmpty()) {
                  resultMsg = AjaxResult.error("没有可以作废的申请记录，请重新选择！");
                  return resultMsg;
               }

               this.takeDrugReturnService.cancelTakeDrugReturnList(takeDrugReturnList);
            } catch (Exception e) {
               log.error("保存退药申请出现异常：", e);
               resultMsg = AjaxResult.error("保存退药申请出现异常，请联系管理员");
            }

            return resultMsg;
         } else {
            resultMsg = AjaxResult.error("参数不能为空！");
            return resultMsg;
         }
      }
   }

   @ResponseBody
   @RequestMapping({"/takeDrugFeeList"})
   @PreAuthorize("@ss.hasAnyPermi('operation:refundDrug:takeDrugFeeList')")
   public AjaxResult takeDrugFeeList(@RequestBody TakeDrugFeeReq req) {
      AjaxResult resultMsg = AjaxResult.success("查询成功");
      if (StringUtils.isEmpty(req.getAdmissionNo())) {
         resultMsg = AjaxResult.error("住院号不能为空！");
         return resultMsg;
      } else {
         try {
            List<TakeDrugFeeDTO> list = this.expenseDetailService.queryTakeDrugFeePageList(req);
            resultMsg.put("data", list);
         } catch (Exception e) {
            log.error("查询已领药列表出现异常：", e);
            resultMsg = AjaxResult.error("查询已领药列表出现异常，请联系管理员");
         }

         return resultMsg;
      }
   }

   @ResponseBody
   @RequestMapping({"/saveTakeDrugFee"})
   @PreAuthorize("@ss.hasAnyPermi('operation:refundDrug:saveTakeDrugFee')")
   public AjaxResult saveTakeDrugFee(@RequestBody SaveTakeDrugFeeReq req) {
      AjaxResult resultMsg = AjaxResult.success("保存退药申请成功");
      req.getList();
      if (req == null) {
         resultMsg = AjaxResult.error("参数不能为空！");
         return resultMsg;
      } else {
         List<TakeDrugFeeDTO> list = req.getList();
         if (list != null && list.size() != 0) {
            try {
               List<String> billsNoList = (List)list.stream().map((tx) -> tx.getBillsNo()).collect(Collectors.toList());
               List<TakeDrugReturn> returnedFeeList = this.takeDrugReturnService.selectByBillsNoOldList(billsNoList);
               List<ExpenseDetail> feeList = this.expenseDetailService.queryFeeList(billsNoList);
               Map<String, List<TakeDrugReturn>> returnedFeeMap = (Map)returnedFeeList.stream().collect(Collectors.groupingBy(TakeDrugReturn::getBillsNoOld));
               Map<String, ExpenseDetail> feeMap = (Map)feeList.stream().collect(Collectors.toMap((p) -> p.getBillsNo(), Function.identity()));

               for(TakeDrugFeeDTO t : list) {
                  ExpenseDetail fee = (ExpenseDetail)feeMap.get(t.getBillsNo());
                  BigDecimal dose = fee.getDose();
                  List<TakeDrugReturn> returnedFee = (List)returnedFeeMap.get(t.getBillsNo());
                  BigDecimal returnedDose = returnedFee == null ? new BigDecimal("0") : (BigDecimal)returnedFee.stream().map(TakeDrugReturn::getDose).reduce(BigDecimal.ZERO, BigDecimal::add);
                  BigDecimal applyDose = t.getApplyDose();
                  BigDecimal ableReturnFee = dose.add(returnedDose);
                  if (applyDose.compareTo(ableReturnFee) > 0) {
                     resultMsg = AjaxResult.error("申请数量超过了可申请数量，请重新填写！");
                     return resultMsg;
                  }
               }

               this.takeDrugReturnService.saveTakeDrugReturnList(list);
            } catch (Exception e) {
               resultMsg = AjaxResult.error("保存退药申请出现异常，请联系管理员");
               log.error("保存退药申请出现异常：", e);
            }

            return resultMsg;
         } else {
            resultMsg = AjaxResult.error("参数不能为空！");
            return resultMsg;
         }
      }
   }
}
