package com.emr.project.docOrder.controller;

import com.emr.common.utils.CommonUtils;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.IPAddressUtil;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.redis.OrderRedisCache;
import com.emr.framework.redis.RedisCache;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.domain.TdPaOrderDetail;
import com.emr.project.docOrder.domain.TdPaOrderItem;
import com.emr.project.docOrder.domain.TdPaTakeDrugApply;
import com.emr.project.docOrder.domain.req.OrderSignTextReq;
import com.emr.project.docOrder.domain.resp.OrderSignTextResp;
import com.emr.project.docOrder.domain.vo.InpatientOrderCheckVo;
import com.emr.project.docOrder.domain.vo.InpatientOrderDTO;
import com.emr.project.docOrder.domain.vo.InpatientOrderResultDetailDTO;
import com.emr.project.docOrder.domain.vo.OrderDoHandleDrugDoseVo;
import com.emr.project.docOrder.domain.vo.OrderDoHandleUpVo;
import com.emr.project.docOrder.domain.vo.OrderOperationVo;
import com.emr.project.docOrder.domain.vo.OrderSignCommitVo;
import com.emr.project.docOrder.domain.vo.TdPaOrderDetailVo;
import com.emr.project.docOrder.service.IInpatientOrderOperationService;
import com.emr.project.docOrder.service.ITdPaOrderDetailService;
import com.emr.project.docOrder.service.ITdPaOrderItemService;
import com.emr.project.docOrder.service.ITdPaOrderSignMainService;
import com.emr.project.docOrder.service.ITdPaTakeDrugApplyService;
import com.emr.project.docOrder.service.ITdPaTakeDrugListService;
import com.emr.project.docOrder.service.ReportConfigService;
import com.emr.project.esSearch.domain.DrugAndClin;
import com.emr.project.esSearch.service.IDrugStockService;
import com.emr.project.operation.service.ExpenseDetailService;
import com.emr.project.pat.domain.Visitinfo;
import com.emr.project.pat.domain.vo.PatFeeVo;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.pat.service.IPatFeeService;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.system.domain.SysDept;
import com.emr.project.system.service.ISysDeptService;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.tmpa.domain.OrderSigStand;
import com.emr.project.tmpa.domain.TmPaOrderUsageFee;
import com.emr.project.tmpa.service.IOrderSigService;
import com.emr.project.tmpa.service.ITmPaOrderUsageFeeService;
import com.emr.project.tmpm.domain.PrintTmplInfo;
import com.emr.project.tmpm.service.IPrintTmplInfoService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/orderOperation"})
public class InpatientOrderOperationController extends BaseController {
   @Autowired
   private IInpatientOrderOperationService orderOperationService;
   @Autowired
   private ISysDeptService sysDeptService;
   @Autowired
   private IOrderSigService orderSigService;
   @Autowired
   private RedisCache redisCache;
   @Autowired
   private ITmPaOrderUsageFeeService orderUsageFeeService;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private ITdPaOrderItemService orderItemService;
   @Autowired
   private ITdPaOrderDetailService orderDetailService;
   @Autowired
   private IDrugStockService drugStockService;
   @Autowired
   private IPatFeeService patFeeService;
   @Autowired
   private ReportConfigService reportConfigService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private IPrintTmplInfoService printTmplInfoService;
   @Autowired
   private ITdPaTakeDrugApplyService tdPaTakeDrugApplyService;
   @Autowired
   private ITdPaOrderSignMainService orderSignMainService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private ITdPaTakeDrugListService takeDrugListService;
   @Autowired
   private ExpenseDetailService expenseDetailService;
   @Autowired
   private OrderRedisCache orderRedisCache;

   @ResponseBody
   @GetMapping({"/orderOperationDict"})
   @PreAuthorize("@ss.hasAnyPermi('docOrder:doctorder:checkList')")
   public AjaxResult orderOperationDict() {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         List<SysDept> pharmacyList = this.sysDeptService.selectdeptListByTypeCode("07");
         List<OrderSigStand> orderSigStandList = this.orderSigService.selectOrderSigStandList();
         ajaxResult.put("pharmacyList", pharmacyList);
         ajaxResult.put("orderSigStandList", orderSigStandList);
         List<String> typeCodeList = new ArrayList();
         typeCodeList.add("100016001");
         typeCodeList.add("100016002");
         typeCodeList.add("100016003");
         List<PrintTmplInfo> printTmplInfos = this.printTmplInfoService.selectTmPmPrintTmplInfoByCodes(typeCodeList);
         ajaxResult.put("printTmplList", printTmplInfos);
         String caFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0001");
         String caType = this.sysEmrConfigService.selectSysEmrConfigByKey("0002");
         ajaxResult.put("caFlag", caFlag);
         ajaxResult.put("caType", caType);
      } catch (Exception e) {
         this.log.error("查询医嘱核对页面的字典信息出现异常，", e);
         ajaxResult = AjaxResult.error("查询医嘱核对页面的字典信息出现异常");
      }

      return ajaxResult;
   }

   @ResponseBody
   @GetMapping({"/selectOrderOperation"})
   @PreAuthorize("@ss.hasAnyPermi('docOrder:doctorder:checkList')")
   public AjaxResult selectOrderOperation(InpatientOrderCheckVo param, Integer pageSize, Integer pageNum) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         param.setDepCode(SecurityUtils.getLoginUser().getUser().getDept().getDeptCode());
         List<InpatientOrderDTO> listAll = this.orderOperationService.inpatientOrderHandleSearch(param);
         List<InpatientOrderDTO> list = this.orderOperationService.inpatientOrderHandlePageData(param, listAll, pageSize, pageNum);
         ajaxResult.put("rows", list);
         ajaxResult.put("total", listAll.size());
      } catch (Exception e) {
         this.log.error("查询医嘱核对列表出现异常，", e);
         ajaxResult = AjaxResult.error("查询医嘱核对列表出现异常");
      }

      return ajaxResult;
   }

   @ResponseBody
   @PostMapping({"/updateOrderOperation"})
   @PreAuthorize("@ss.hasAnyPermi('docOrder:doctorder:doCheck1,docOrder:doctorder:doCheck2,docOrder:doctorder:doCheck3')")
   public AjaxResult updateOrderOperation(@RequestBody OrderOperationVo orderOperationVo, HttpServletRequest request) {
      String msg = "核对成功";
      AjaxResult ajaxResult = AjaxResult.success("核对成功");
      Boolean flag = true;
      List<InpatientOrderResultDetailDTO> inpatientOrderResultDetailDTOS = new ArrayList(1);
      String deptCode = SecurityUtils.getLoginUser().getUser().getDept().getDeptCode();
      List<InpatientOrderDTO> list = new ArrayList();

      try {
         String ip = IPAddressUtil.getIPAddress(request);
         if (flag && StringUtils.isBlank(orderOperationVo.getType())) {
            flag = false;
            ajaxResult = AjaxResult.error("核对类型不能为空");
         }

         String redisDeptCode = (String)this.redisCache.getCacheObject("inpatientOrderHandling:" + deptCode);
         if (StringUtils.isNotBlank(redisDeptCode) && redisDeptCode.equals("1")) {
            flag = false;
            ajaxResult = AjaxResult.error("当前科室正在核对医嘱，请稍后再核对");
         } else {
            this.redisCache.setCacheObject("inpatientOrderHandling:" + deptCode, "1");
         }

         String caFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0001");
         if (flag && StringUtils.isNotBlank(caFlag) && caFlag.equals("1")) {
            List<OrderSignCommitVo> orderSignCommitVoList = orderOperationVo.getOrderSignCommitVoList();
            if (orderSignCommitVoList != null && orderSignCommitVoList.size() > 0) {
               for(OrderSignCommitVo orderSignCommitVo : orderSignCommitVoList) {
                  if (flag && StringUtils.isBlank(orderSignCommitVo.getEncryptedInfo())) {
                     flag = false;
                     ajaxResult = AjaxResult.error("签名加密后密文不能为空，请重新操作");
                  }

                  if (flag && StringUtils.isBlank(orderSignCommitVo.getSignCertificate())) {
                     flag = false;
                     ajaxResult = AjaxResult.error("签名证书不能为空，请重新操作");
                  }

                  if (flag && StringUtils.isBlank(orderSignCommitVo.getSignedText())) {
                     flag = false;
                     ajaxResult = AjaxResult.error("签名明文不能为空，请重新操作");
                  }
               }

               if (flag) {
                  this.orderSignMainService.addTdPaOrderOperationSignText(orderSignCommitVoList, "1");
               }
            }
         }

         if (flag && orderOperationVo.getType().equals("1")) {
            List var61 = orderOperationVo.getSelectedList();
            List<String> orderNoList = (List)var61.stream().filter((t) -> StringUtils.isNotBlank(t.getOrderNo())).map((t) -> t.getOrderNo()).collect(Collectors.toList());
            InpatientOrderCheckVo param = new InpatientOrderCheckVo();
            param.setDepCode(SecurityUtils.getLoginUser().getUser().getDept().getDeptCode());
            param.setOrderType("all");
            param.setOrderNoList(orderNoList);
            list = this.orderOperationService.inpatientOrderHandleSearch(param);
         }

         if (flag && orderOperationVo.getType().equals("2")) {
            InpatientOrderCheckVo param = new InpatientOrderCheckVo();
            param.setDepCode(SecurityUtils.getLoginUser().getUser().getDept().getDeptCode());
            param.setAdmissionNo(orderOperationVo.getAdmissionNo());
            param.setOrderType("all");
            list = this.orderOperationService.inpatientOrderHandleSearch(param);
         }

         if (flag && orderOperationVo.getType().equals("3")) {
            InpatientOrderCheckVo param = new InpatientOrderCheckVo();
            param.setDepCode(SecurityUtils.getLoginUser().getUser().getDept().getDeptCode());
            param.setOrderType("all");
            param.setIsChange("2");
            list = this.orderOperationService.inpatientOrderHandleSearch(param);
         }

         if (flag && CollectionUtils.isEmpty(list)) {
            flag = false;
            ajaxResult = AjaxResult.error("请选择医嘱信息");
         }

         if (flag) {
            new ArrayList(1);
            List<Map<String, Object>> printList = new ArrayList(1);
            List<String> patientIdList = (List)list.stream().filter((t) -> StringUtils.isNotBlank(t.getPatientId())).map((t) -> t.getPatientId()).distinct().collect(Collectors.toList());
            List<Visitinfo> visitinfoList = this.visitinfoService.selectVistinfoListByPatList(patientIdList);
            List<Visitinfo> patientInHospitalList = (List)visitinfoList.stream().filter((t) -> t.getOutTime() == null).collect(Collectors.toList());
            Map<String, Visitinfo> patientInHospitalMap = (Map)patientInHospitalList.stream().collect(Collectors.toMap((t) -> t.getInpNo(), Function.identity()));
            TmPaOrderUsageFee usageFee = new TmPaOrderUsageFee();
            this.orderUsageFeeService.selectTmPaOrderUsageFeeList(usageFee);
            Map<String, List<InpatientOrderDTO>> listMap = CommonUtils.getSameOrder(list);
            Map<String, PatFeeVo> patFeeVoMap = new HashMap(1);
            Map<String, BigDecimal> patFeeAllMap = new HashMap(1);
            Map<String, String> pharmacyNoApplyNoMap = new HashMap(1);
            List<InpatientOrderDTO> drugOrderList = (List)list.stream().filter((t) -> t.getOrderClassCode().equals("01")).collect(Collectors.toList());
            String applyNo = null;
            if (CollectionUtils.isNotEmpty(drugOrderList)) {
               Map<String, List<InpatientOrderDTO>> drugOrderListMap = (Map)drugOrderList.stream().collect(Collectors.groupingBy((t) -> t.getPharmacyNo()));
               SysDept dept = SecurityUtils.getLoginUser().getUser().getDept();
               Date currDate = this.commonService.getDbSysdate();
               TdPaTakeDrugApply lastApply = this.tdPaTakeDrugApplyService.selectLastApply(dept.getOrgCode(), dept.getDeptCode(), currDate);
               Integer applyNum = null;

               for(String pharmacyNo : drugOrderListMap.keySet()) {
                  if (applyNum == null) {
                     applyNum = lastApply != null ? lastApply.getApplyNum() + 1 : 1;
                  } else {
                     applyNum = applyNum + 1;
                  }

                  applyNo = dept.getDeptCode() + DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, currDate) + applyNum;
                  TdPaTakeDrugApply currApply = new TdPaTakeDrugApply();
                  currApply.setId(SnowIdUtils.uniqueLong());
                  currApply.setOrgCode(dept.getOrgCode());
                  currApply.setDeptCode(dept.getDeptCode());
                  currApply.setApplyNum(applyNum);
                  currApply.setApplyDate(currDate);
                  currApply.setApplyNo(applyNo);
                  this.tdPaTakeDrugApplyService.insertTdPaTakeDrugApply(currApply);
                  pharmacyNoApplyNoMap.put(pharmacyNo, applyNo);
               }
            }

            for(String key : listMap.keySet()) {
               Boolean isAllowFlag = true;
               StringBuffer msgSb = new StringBuffer();
               List<InpatientOrderDTO> inpatientOrderDTOS = (List)listMap.get(key);
               if (!inpatientOrderDTOS.isEmpty()) {
                  InpatientOrderDTO dto = (InpatientOrderDTO)inpatientOrderDTOS.get(0);
                  String admissionNo = dto.getAdmissionNo();
                  String orderNo = dto.getOrderNo();
                  Visitinfo visitinfo = (Visitinfo)patientInHospitalMap.get(admissionNo);
                  Boolean patientInHospitalFlag = visitinfo != null && visitinfo.getOutTime() == null;
                  if (!patientInHospitalFlag) {
                     msgSb.append("患者已经出院，不能处理他的医嘱；");
                     isAllowFlag = false;
                  }

                  TdPaOrderItem orderItem = this.orderItemService.selectTdPaOrderItemById(orderNo);
                  Date nowDate = this.commonService.getDbSysdate();
                  boolean mark = CommonUtils.getOrderStatusIsEffective(orderItem, 3, nowDate);
                  if (!mark) {
                     msgSb.append("患者医嘱当前不是可以处理的状态，不能重复进行，请重新提取数据进行处理!");
                     isAllowFlag = false;
                  }

                  int orderStatus = dto.getOrderStatus();
                  String cancelVerifyFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0079");
                  if (StringUtils.isNotBlank(cancelVerifyFlag) && cancelVerifyFlag.equals("1") && (orderStatus == 6 || orderStatus == 11)) {
                     List<String> orderList = (List)inpatientOrderDTOS.stream().map(InpatientOrderDTO::getOrderNo).distinct().collect(Collectors.toList());
                     String orderGroupNoStr = this.expenseDetailService.verifyExpensedetailByOrderNo(orderList);
                     if (StringUtils.isNotBlank(orderGroupNoStr)) {
                        isAllowFlag = false;
                        msgSb.append("计费总额不为0，需要进行退费处理！");
                     }

                     String orderDrugGroupNoStr = this.takeDrugListService.verifyOrderCancelTakeDrugList(orderList);
                     if (StringUtils.isNotBlank(orderDrugGroupNoStr)) {
                        isAllowFlag = false;
                        msgSb.append("有未领药信息,请及时处理！");
                     }
                  }

                  List<OrderDoHandleUpVo> handleUplist = new ArrayList(1);
                  if (isAllowFlag) {
                     PatFeeVo patFeeVo = (PatFeeVo)patFeeVoMap.get(admissionNo);
                     if (patFeeVo == null) {
                        PatFeeVo feeParam = new PatFeeVo();
                        feeParam.setInpNo(admissionNo);
                        patFeeVo = this.patFeeService.selectAmountList(feeParam);
                        patFeeVoMap.put(admissionNo, patFeeVo);
                     }

                     BigDecimal patFeeAll = patFeeAllMap.get(admissionNo) == null ? new BigDecimal("0") : (BigDecimal)patFeeAllMap.get(admissionNo);
                     List<DrugAndClin> drugAndClinList = null;
                     List<DrugAndClin> yfkcHzList = null;
                     Map<String, List<DrugAndClin>> yfkcHzMap = null;
                     Date orderDealTime = dto.getOrderDealTime();
                     Date orderAuditTime = dto.getOrderAuditTime();
                     Date currDate = this.commonService.getDbSysdate();
                     Date operationDateDay = DateUtils.parseDate(DateUtils.dateFormatS(currDate, "yyyy-MM-dd"));
                     if (dto.getOrderClassCode().equals("01") && (dto.getOrderStatus() == Integer.valueOf("3") || dto.getOrderStatus() == 2 && orderDealTime != null && orderAuditTime != null && orderAuditTime.compareTo(operationDateDay) < 0 && orderDealTime.compareTo(operationDateDay) < 0)) {
                        drugAndClinList = new ArrayList(1);
                        List<String> drugCodeList = (List)inpatientOrderDTOS.stream().map((t) -> t.getPharmacopoeiaNo()).collect(Collectors.toList());
                        String takeDrugDeptCode = dto.getDetailPerformDepCode();
                        yfkcHzList = this.drugStockService.selectHisDrugStockList(drugCodeList, takeDrugDeptCode);
                        yfkcHzMap = (Map)yfkcHzList.stream().collect(Collectors.groupingBy((t) -> t.getCpNo()));

                        for(String drugCode : yfkcHzMap.keySet()) {
                           List<DrugAndClin> tempList = (List)yfkcHzMap.get(drugCode);
                           BigDecimal xcsl = new BigDecimal("0");

                           for(DrugAndClin yfkcHz : tempList) {
                              xcsl = xcsl.add(yfkcHz.getXcsl());
                           }

                           DrugAndClin drugAndClin = new DrugAndClin();
                           drugAndClin.setCpNo(drugCode);
                           drugAndClin.setXcsl(xcsl);
                           drugAndClinList.add(drugAndClin);
                        }
                     }

                     String orderNoHandlingKey = "inpatientOrderNoHandling:" + dto.getOrderNo();
                     String orderNoHandling = (String)this.orderRedisCache.getCacheObject(orderNoHandlingKey);
                     if (StringUtils.isNotBlank(orderNoHandling)) {
                        String orderNoHandlingMsg = CommonUtils.getOrderNoHandlingMsg(Integer.valueOf(orderNoHandling));
                        isAllowFlag = false;
                        msgSb.append(orderNoHandlingMsg);
                     } else {
                        this.orderRedisCache.setCacheObject(orderNoHandlingKey, 2, 300, TimeUnit.SECONDS);
                     }

                     OrderDoHandleDrugDoseVo drugDoseVo = null;

                     try {
                        this.orderOperationService.inpatientOrderDoHandle(inpatientOrderDTOS, patFeeVo, patFeeAll, drugAndClinList, handleUplist, printList, pharmacyNoApplyNoMap, ip);
                     } catch (Exception e) {
                        msgSb.append("医嘱处理失败");
                        this.log.error("医嘱处理失败，患者姓名：" + dto.getPatientName() + "，组号：" + dto.getOrderGroupNo() + "。", e);
                        this.log.error("医嘱处理失败", e);
                     }

                     this.orderRedisCache.deleteObject(orderNoHandlingKey);
                     if (!handleUplist.isEmpty()) {
                        for(InpatientOrderDTO inpatientOrderDTO : inpatientOrderDTOS) {
                           InpatientOrderResultDetailDTO inpatientOrderResultDetailDTO = new InpatientOrderResultDetailDTO();
                           inpatientOrderResultDetailDTO.setOrderNo(inpatientOrderDTO.getOrderNo());
                           inpatientOrderResultDetailDTO.setOrderGroupNo(inpatientOrderDTO.getOrderGroupNo());
                           inpatientOrderResultDetailDTO.setAdmissionNo(inpatientOrderDTO.getAdmissionNo());
                           inpatientOrderResultDetailDTO.setChargeName(inpatientOrderDTO.getChargeName());
                           inpatientOrderResultDetailDTO.setPatientName(inpatientOrderDTO.getPatientName());
                           inpatientOrderResultDetailDTO.setErroMsg(((OrderDoHandleUpVo)handleUplist.get(0)).getMsg());
                           inpatientOrderResultDetailDTO.setBedId(inpatientOrderDTO.getBedNum());
                           inpatientOrderResultDetailDTO.setPrice(inpatientOrderDTO.getPrice());
                           inpatientOrderResultDetailDTO.setTotal(inpatientOrderDTO.getOrderTotal());
                           inpatientOrderResultDetailDTO.setOrderTotalDose(inpatientOrderDTO.getOrderTotalDose());
                           inpatientOrderResultDetailDTO.setCaseNo(inpatientOrderDTO.getCaseNo());
                           inpatientOrderResultDetailDTOS.add(inpatientOrderResultDetailDTO);
                        }
                     }
                  }

                  if (StringUtils.isNotBlank(msgSb.toString())) {
                     for(InpatientOrderDTO inpatientOrderDTO : inpatientOrderDTOS) {
                        InpatientOrderResultDetailDTO inpatientOrderResultDetailDTO = new InpatientOrderResultDetailDTO();
                        inpatientOrderResultDetailDTO.setOrderNo(inpatientOrderDTO.getOrderNo());
                        inpatientOrderResultDetailDTO.setOrderGroupNo(inpatientOrderDTO.getOrderGroupNo());
                        inpatientOrderResultDetailDTO.setAdmissionNo(inpatientOrderDTO.getAdmissionNo());
                        inpatientOrderResultDetailDTO.setCaseNo(inpatientOrderDTO.getCaseNo());
                        inpatientOrderResultDetailDTO.setChargeName(inpatientOrderDTO.getChargeName());
                        inpatientOrderResultDetailDTO.setPatientName(inpatientOrderDTO.getPatientName());
                        inpatientOrderResultDetailDTO.setErroMsg(msgSb.toString());
                        inpatientOrderResultDetailDTO.setBedId(inpatientOrderDTO.getBedNum());
                        inpatientOrderResultDetailDTO.setPrice(inpatientOrderDTO.getPrice());
                        inpatientOrderResultDetailDTO.setTotal(inpatientOrderDTO.getOrderTotal());
                        inpatientOrderResultDetailDTO.setOrderTotalDose(inpatientOrderDTO.getOrderTotalDose());
                        inpatientOrderResultDetailDTOS.add(inpatientOrderResultDetailDTO);
                     }
                  }
               }
            }

            List<Map<String, List<Map<String, Object>>>> mapList1 = new ArrayList();
            this.reportConfigService.groupByExecutorDpt(mapList1, printList);
            ajaxResult.put("printList", mapList1);
            ajaxResult.put("failResult", inpatientOrderResultDetailDTOS);
         }

         this.redisCache.deleteObject("inpatientOrderHandling:" + deptCode);
      } catch (Exception e) {
         this.redisCache.deleteObject("inpatientOrderHandling:" + deptCode);
         ajaxResult = AjaxResult.error("医嘱核对出现异常");
         this.log.error("医嘱核对出现异常，", e);
      }

      return ajaxResult;
   }

   @ResponseBody
   @GetMapping({"/personalInfo"})
   @PreAuthorize("@ss.hasAnyPermi('docOrder:doctorder:checkList')")
   public AjaxResult selectPersonalInfo(String patientId, String orderNo) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (flag && StringUtils.isBlank(patientId)) {
            ajaxResult = AjaxResult.error("患者唯一标识不能为空");
            flag = false;
         }

         if (flag && StringUtils.isBlank(orderNo)) {
            ajaxResult = AjaxResult.error("医嘱编码不能为空");
            flag = false;
         }

         if (flag) {
            VisitinfoVo visitinfoVo = this.visitinfoService.selectVisitinfoById(patientId);
            TdPaOrderDetailVo orderDetailParam = new TdPaOrderDetailVo();
            orderDetailParam.setOrderNo(orderNo);
            List<TdPaOrderDetail> orderDetailList = this.orderDetailService.selectTdPaOrderDetailList(orderDetailParam);
            ajaxResult.put("visitinfoVo", visitinfoVo);
            ajaxResult.put("orderDetailList", orderDetailList);
         }
      } catch (Exception e) {
         this.log.error("查询患者信息出现异常：", e);
         ajaxResult = AjaxResult.error("查询患者信息出现异常");
      }

      return ajaxResult;
   }

   @ResponseBody
   @PostMapping({"/orderSignText"})
   @PreAuthorize("@ss.hasAnyPermi('docOrder:doctorder:checkList')")
   public AjaxResult orderSignText(@RequestBody OrderSignTextReq req) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      String signType = req.getSignType();
      if (StringUtils.isBlank(signType)) {
         ajaxResult = AjaxResult.error("签名类型不能为空！");
         return ajaxResult;
      } else if (!signType.equals("1") && !signType.equals("2")) {
         ajaxResult = AjaxResult.error("签名类型错误！");
         return ajaxResult;
      } else if (req.getOrderNoList() == null) {
         ajaxResult = AjaxResult.error("医嘱列表为空！");
         return ajaxResult;
      } else {
         try {
            List<OrderSignTextResp> list = this.orderSignMainService.queryOrderSignText(req);
            ajaxResult.put("data", list);
         } catch (Exception e) {
            this.log.error("医嘱执行/审核 获取CA签名字符串异常,请联系管理员！", e);
            ajaxResult = AjaxResult.error("医嘱执行/审核 获取CA签名字符串异常,请联系管理员！");
         }

         return ajaxResult;
      }
   }
}
