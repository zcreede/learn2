package com.emr.project.operation.controller;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.docOrder.domain.TdPaItemDocQuery;
import com.emr.project.docOrder.service.ITdPaItemDocQueryService;
import com.emr.project.operation.domain.Tfsqb;
import com.emr.project.operation.domain.req.RefundAccountDetailReq;
import com.emr.project.operation.domain.req.RefundAccountListReq;
import com.emr.project.operation.domain.req.RefundAccountReq;
import com.emr.project.operation.domain.req.RefundApplyReq;
import com.emr.project.operation.domain.req.RefundBatchToVoidDetailsReq;
import com.emr.project.operation.domain.req.RefundBatchToVoidReq;
import com.emr.project.operation.domain.resp.RefundAccountListResp;
import com.emr.project.operation.domain.resp.RefundApplyResp;
import com.emr.project.operation.domain.resp.ThreeLevelCodeResp;
import com.emr.project.operation.domain.vo.PrintRefundAccountVo;
import com.emr.project.operation.service.ExpenseDetailService;
import com.emr.project.operation.service.IMedicalinfoService;
import com.emr.project.operation.service.IRefundAccountService;
import com.emr.project.operation.service.ITmPmHsxmSerive;
import com.emr.project.pat.domain.ExpenseDetail;
import com.emr.project.system.domain.SysOrg;
import com.emr.project.tmpm.domain.PrintTmplInfo;
import com.emr.project.tmpm.service.IPrintTmplInfoService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/operation/refundAccount"})
public class RefundAccountController {
   private static Logger log = LoggerFactory.getLogger(RefundAccountController.class);
   @Autowired
   private IRefundAccountService refundAccountService;
   @Autowired
   private ITmPmHsxmSerive tmPmHsxmSerive;
   @Autowired
   private IMedicalinfoService medicalinfoService;
   @Autowired
   private ExpenseDetailService expenseDetailService;
   @Autowired
   private IPrintTmplInfoService printTmplInfoService;
   @Autowired
   private ITdPaItemDocQueryService tdPaItemDocQueryService;

   @RequestMapping({"/getThreeLevelCodeList"})
   @ResponseBody
   @PreAuthorize("@ss.hasPermi('query:operation:refundAccount:threeLevelCode')")
   public AjaxResult getThreeLevelCodeList() {
      AjaxResult resultMsg = AjaxResult.success("查询成功");

      try {
         List<ThreeLevelCodeResp> list = this.tmPmHsxmSerive.getThreeLevelCodeList();
         List<String> typeCodeList = new ArrayList();
         typeCodeList.add("100024001");
         List<PrintTmplInfo> printTmplInfos = this.printTmplInfoService.selectTmPmPrintTmplInfoByCodes(typeCodeList);
         resultMsg.put("printTmpl", printTmplInfos);
         resultMsg.put("data", list);
         TdPaItemDocQuery tdPaItemDocQuery = new TdPaItemDocQuery();
         tdPaItemDocQuery.setDocCd(SecurityUtils.getLoginUser().getUser().getDept().getDeptCode());
         tdPaItemDocQuery.setOrderFlag("16");
         List<TdPaItemDocQuery> queryList = this.tdPaItemDocQueryService.selectTdPaItemDocQueryList(tdPaItemDocQuery);
         resultMsg.put("printFlag", queryList != null && !queryList.isEmpty() ? ((TdPaItemDocQuery)queryList.get(0)).getQueryStatus() : "0");
      } catch (Exception e) {
         log.error("查询所有三级项目列表出现异常！", e);
         resultMsg = AjaxResult.error("查询所有三级项目列表出现异常！");
      }

      return resultMsg;
   }

   @RequestMapping({"/refundAccount"})
   @ResponseBody
   @PreAuthorize("@ss.hasPermi('query:operation:refundAccount:refundList')")
   public AjaxResult getRefundList(@RequestBody RefundAccountListReq req) {
      AjaxResult resultMsg = AjaxResult.success("查询成功");
      if (req == null) {
         resultMsg = AjaxResult.error("参数不能为空！");
         return resultMsg;
      } else if (StringUtils.isEmpty(req.getAdmissionNo())) {
         resultMsg = AjaxResult.error("住院号不能为空！");
         return resultMsg;
      } else {
         try {
            List<RefundAccountListResp> list = this.refundAccountService.getRefundList(req);
            resultMsg.put("data", list);
         } catch (Exception e) {
            log.error("查询要退费项目列表异常", e);
            resultMsg = AjaxResult.error("查询要退费项目列表异常，请联系管理员");
         }

         return resultMsg;
      }
   }

   @RequestMapping({"/getRefundApplyList"})
   @ResponseBody
   @PreAuthorize("@ss.hasPermi('query:operation:refundAccount:refundApplyList')")
   public AjaxResult getRefundApplyList(@RequestBody RefundApplyReq req) {
      AjaxResult resultMsg = AjaxResult.success("查询成功");
      if (req == null) {
         resultMsg = AjaxResult.error("参数不能为空！");
         return resultMsg;
      } else if (StringUtils.isEmpty(req.getAdmissionNo())) {
         resultMsg = AjaxResult.error("住院号不能为空！");
         return resultMsg;
      } else {
         try {
            List<RefundApplyResp> list = this.refundAccountService.getRefundApplyList(req);
            resultMsg.put("data", list);
            return resultMsg;
         } catch (Exception e) {
            log.error("查询已退费申请项目列表异常", e);
            resultMsg = AjaxResult.error("查询已退费申请项目列表异常，请联系管理员");
            return resultMsg;
         }
      }
   }

   @RequestMapping({"/saveRefundAccount"})
   @ResponseBody
   @PreAuthorize("@ss.hasPermi('save:operation:refundAccount:saveRefundAccount')")
   public AjaxResult saveRefundAccount(@RequestBody RefundAccountReq req) {
      AjaxResult resultMsg = AjaxResult.success("保存成功");
      if (req == null) {
         resultMsg = AjaxResult.error("参数不能为空！");
         return resultMsg;
      } else {
         List<RefundAccountDetailReq> list = req.getList();
         if (list != null && list.size() != 0) {
            if (StringUtils.isEmpty(req.getAdmissionNo())) {
               resultMsg = AjaxResult.error("住院号不能为空！");
               return resultMsg;
            } else {
               try {
                  Map<String, List<RefundAccountDetailReq>> billsNoListMap = (Map)list.stream().collect(Collectors.groupingBy(RefundAccountDetailReq::getBillsNo));
                  Set<String> billsNoList = billsNoListMap.keySet();
                  List<ExpenseDetail> expenseList = this.expenseDetailService.selectExpenseDetailByBillsNoList(billsNoList);
                  Map<String, List<ExpenseDetail>> expenseListMap = (Map)expenseList.stream().collect(Collectors.groupingBy(ExpenseDetail::getBillsNo));

                  for(int i = 0; i < list.size(); ++i) {
                     RefundAccountDetailReq detailReq = (RefundAccountDetailReq)list.get(i);
                     String billsNo = detailReq.getBillsNo();
                     BigDecimal sqsl = new BigDecimal(detailReq.getRefundCount());
                     List<ExpenseDetail> detailList = (List)expenseListMap.get(billsNo);
                     if (detailList != null && detailList.size() > 0) {
                        BigDecimal dose = (BigDecimal)detailList.stream().map(ExpenseDetail::getDose).reduce(BigDecimal.ZERO, BigDecimal::add);
                        if (sqsl.compareTo(dose) > 0) {
                           int page = i + 1;
                           resultMsg = AjaxResult.error("第" + page + "行数据，申请数量不能超过可退数量！请重新刷新记账项目信息后，再申请退费！");
                           return resultMsg;
                        }
                     }
                  }

                  resultMsg = this.refundAccountService.checkRefundNum(resultMsg, list);
                  if (resultMsg.get("code").toString().equals("200")) {
                     List<PrintRefundAccountVo> printRefundAccountVos = this.refundAccountService.saveRefundAccount(req);
                     resultMsg.put("data", printRefundAccountVos);
                  }
               } catch (Exception e) {
                  log.error("退费申请保存异常", e);
                  resultMsg = AjaxResult.error("退费申请保存异常，请联系管理员");
               }

               return resultMsg;
            }
         } else {
            resultMsg = AjaxResult.error("退费单据不能为空！");
            return resultMsg;
         }
      }
   }

   @RequestMapping({"/updateRefundBatchToVoid"})
   @ResponseBody
   @PreAuthorize("@ss.hasPermi('update:operation:refundAccount:refundBatchToVoid')")
   public AjaxResult updateRefundBatchToVoid(@RequestBody RefundBatchToVoidReq req) {
      AjaxResult resultMsg = AjaxResult.success("保存成功");

      try {
         if (req == null) {
            resultMsg = AjaxResult.error("参数不能为空！");
            return resultMsg;
         }

         List<RefundBatchToVoidDetailsReq> list = req.getList();
         if (list == null || list.size() == 0) {
            resultMsg = AjaxResult.error("退费单据不能为空！");
            return resultMsg;
         }

         if (StringUtils.isEmpty(req.getAdmissionNo())) {
            resultMsg = AjaxResult.error("住院号不能为空！");
            return resultMsg;
         }

         SysOrg sysOrg = SecurityUtils.getLoginUser().getUser().getHospital();

         for(RefundBatchToVoidDetailsReq detailReq : list) {
            Tfsqb tfsqb = this.refundAccountService.queryByKey(sysOrg.getOrgCode(), detailReq.getAdmissionNo(), detailReq.getSerialNumber(), detailReq.getSerialNumberXh());
            if (tfsqb == null) {
               resultMsg = AjaxResult.error("没有这条退费记录，请重新查询后再试！");
               return resultMsg;
            }

            if (tfsqb.getMark().equals("1")) {
               resultMsg = AjaxResult.error("当前退费记录已退费，不允许作废，请重新查询查看！");
               return resultMsg;
            }

            if (tfsqb.getMark().equals("9")) {
               resultMsg = AjaxResult.error("当前退费记录已作废，不允许重复作废，请重新查询查看！");
               return resultMsg;
            }
         }

         Map<String, Object> medicalinfoDetail = this.medicalinfoService.getMedicalinfoDetail(req.getAdmissionNo());
         if (medicalinfoDetail == null) {
            resultMsg = AjaxResult.error("患者信息有误，请刷新重试！");
            return resultMsg;
         }

         this.refundAccountService.updateRefundBatchToVoid(req);
      } catch (Exception e) {
         log.error("退费申请作废异常", e);
         resultMsg = AjaxResult.error("退费申请作废异常，请联系管理员");
      }

      return resultMsg;
   }

   @RequestMapping({"/printRefundAccount"})
   @ResponseBody
   @PreAuthorize("@ss.hasPermi('save:operation:refundAccount:saveRefundAccount')")
   public AjaxResult printRefundAccount(@RequestBody List billNoList) {
      AjaxResult resultMsg = AjaxResult.success("保存成功");
      if (billNoList != null && billNoList.size() != 0) {
         try {
            List<PrintRefundAccountVo> list = this.refundAccountService.printRefundAccount(billNoList);
            resultMsg.put("data", list);
         } catch (Exception e) {
            log.error("退费申请补打出现异常", e);
            resultMsg = AjaxResult.error("退费申请补打出现异常，请联系管理员！");
         }

         return resultMsg;
      } else {
         resultMsg = AjaxResult.error("参数不能为空！");
         return resultMsg;
      }
   }
}
