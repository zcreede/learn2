package com.emr.project.docOrder.controller;

import com.emr.common.utils.CommonUtils;
import com.emr.common.utils.EncryptPSWUtil;
import com.emr.common.utils.IPAddressUtil;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.ip.IpUtils;
import com.emr.framework.redis.OrderRedisCache;
import com.emr.framework.redis.RedisCache;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.borrowing.domain.vo.SysDictDataVO;
import com.emr.project.docOrder.domain.TdPaOrder;
import com.emr.project.docOrder.domain.TdPaOrderItem;
import com.emr.project.docOrder.domain.req.InpatientSuspendOrderListReq;
import com.emr.project.docOrder.domain.req.ProcessingOrdersReq;
import com.emr.project.docOrder.domain.req.SuppPrintOrderDataListReq;
import com.emr.project.docOrder.domain.req.SuppPrintOrderDataReq;
import com.emr.project.docOrder.domain.req.SuspendOrderListReq;
import com.emr.project.docOrder.domain.req.TodayOrderOperationReq;
import com.emr.project.docOrder.domain.vo.AllergyRecordDTO;
import com.emr.project.docOrder.domain.vo.InpatientOrderPerformDTO;
import com.emr.project.docOrder.domain.vo.InpatientOrderResultDetailDTO;
import com.emr.project.docOrder.domain.vo.InpatientTodayOrderPerformDTO;
import com.emr.project.docOrder.domain.vo.OrderDoHandleDrugDoseVo;
import com.emr.project.docOrder.domain.vo.UpdateExecutionTimeVo;
import com.emr.project.docOrder.service.IInpatientSuspendOrderService;
import com.emr.project.docOrder.service.IOrderPerFormVoidRevokeService;
import com.emr.project.docOrder.service.ITdPaApplyFormService;
import com.emr.project.docOrder.service.ITdPaOrderStatusService;
import com.emr.project.docOrder.service.ITdPaTakeDrugListService;
import com.emr.project.docOrder.service.ReportConfigService;
import com.emr.project.esSearch.domain.DrugAndClin;
import com.emr.project.esSearch.service.IDrugStockService;
import com.emr.project.operation.domain.TakeDrugList;
import com.emr.project.operation.service.ICommonOperationService;
import com.emr.project.operation.service.TakeDrugListService;
import com.emr.project.operation.service.TakeDrugReturnService;
import com.emr.project.qc.domain.EmrQcFlow;
import com.emr.project.qc.service.IEmrQcFlowService;
import com.emr.project.system.domain.BsStaff;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.mapper.BsStaffMapper;
import com.emr.project.system.service.ISysEmrConfigService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/suspendOrder"})
public class InpatientSuspendOrderController extends BaseController {
   @Autowired
   private IInpatientSuspendOrderService inpatientSuspendOrderService;
   @Autowired
   private RedisCache redisCache;
   @Autowired
   private ICommonOperationService commonService;
   @Autowired
   private ITdPaOrderStatusService tdPaOrderStatusService;
   @Autowired
   private ReportConfigService reportConfigService;
   @Autowired
   private BsStaffMapper staffMapper;
   @Autowired
   private ITdPaApplyFormService applyFormService;
   @Autowired
   private IOrderPerFormVoidRevokeService orderPerFormVoidRevokeService;
   @Autowired
   private ITdPaTakeDrugListService tdPaTakeDrugListService;
   @Autowired
   private TakeDrugReturnService takeDrugReturnService;
   @Autowired
   private IDrugStockService drugStockService;
   @Autowired
   private TakeDrugListService takeDrugListService;
   @Autowired
   private OrderRedisCache orderRedisCache;
   @Autowired
   private IEmrQcFlowService emrQcFlowService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;

   @ResponseBody
   @GetMapping({"/selectSuspendOrderList"})
   @PreAuthorize("@ss.hasAnyPermi('docOrder:doctorder:suspendOrderList')")
   public TableDataInfo selectSuspendOrderList(SuspendOrderListReq req) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<InpatientOrderPerformDTO> list = this.inpatientSuspendOrderService.selectSuspendOrderList(req);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         tableDataInfo = new TableDataInfo(500, "查询挂起医嘱列表异常");
         this.log.error("查询挂起医嘱列表异常", e);
      }

      return tableDataInfo;
   }

   @ResponseBody
   @RequestMapping({"/updatePerformStatus"})
   @PreAuthorize("@ss.hasAnyPermi('docOrder:doctorder:updatePerformStatus')")
   public AjaxResult updatePerformStatus(@RequestBody InpatientSuspendOrderListReq req) {
      AjaxResult ajaxResult = AjaxResult.success("作废成功");
      String deptCode = SecurityUtils.getLoginUser().getUser().getDept().getDeptCode();
      StringBuffer msgSb = new StringBuffer();
      Map<String, Boolean> patientInHospitalFlagMap = new HashMap();

      try {
         String redisDeptCode = (String)this.redisCache.getCacheObject("inpatientOrderPerformCancel:" + deptCode);
         if (StringUtils.isNotBlank(redisDeptCode) && redisDeptCode.equals("1")) {
            ajaxResult = AjaxResult.error("当前科室正在核对医嘱，请稍后再核对");
            return ajaxResult;
         }

         this.redisCache.setCacheObject("inpatientOrderPerformCancel:" + deptCode, "1");
         List<InpatientOrderPerformDTO> performList = req.getList();
         Map<String, List<InpatientOrderPerformDTO>> performListMap = new HashMap();

         for(InpatientOrderPerformDTO inpatientOrderPerformDTO : performList) {
            if (performListMap.containsKey(inpatientOrderPerformDTO.getPerformListNo() + inpatientOrderPerformDTO.getPerformListSortNumber())) {
               List<InpatientOrderPerformDTO> inpatientOrderPerformDTOList = (List)performListMap.get(inpatientOrderPerformDTO.getPerformListNo() + inpatientOrderPerformDTO.getPerformListSortNumber());
               inpatientOrderPerformDTOList.add(inpatientOrderPerformDTO);
            } else {
               List<InpatientOrderPerformDTO> inpatientOrderPerformDTOList = new ArrayList();
               inpatientOrderPerformDTOList.add(inpatientOrderPerformDTO);
               performListMap.put(inpatientOrderPerformDTO.getPerformListNo() + inpatientOrderPerformDTO.getPerformListSortNumber(), inpatientOrderPerformDTOList);
            }
         }

         for(String key : performListMap.keySet()) {
            List<InpatientOrderPerformDTO> inpatientOrderPerformList = (List)performListMap.get(key);
            InpatientOrderPerformDTO dto = (InpatientOrderPerformDTO)inpatientOrderPerformList.get(0);
            String admissionNo = dto.getAdmissionNo();
            Boolean patientInHospitalFlag = patientInHospitalFlagMap.containsKey(admissionNo) ? (Boolean)patientInHospitalFlagMap.get(dto.getAdmissionNo()) : null;
            if (patientInHospitalFlag == null) {
               patientInHospitalFlag = this.commonService.checkPatientIsIn(admissionNo, dto.getHospitalizedCount() + "");
               patientInHospitalFlagMap.put(admissionNo, patientInHospitalFlag);
            }

            if (!patientInHospitalFlag) {
               msgSb.append("患者" + dto.getPatientName() + "(" + dto.getAdmissionNo() + ")已经出院，不能处理他的医嘱；");
            } else {
               try {
                  this.inpatientSuspendOrderService.updatePerformStart(inpatientOrderPerformList);
               } catch (Exception e) {
                  msgSb.append("医嘱处理失败，患者姓名：" + ((InpatientOrderPerformDTO)inpatientOrderPerformList.get(0)).getPatientName() + " ； orderNo：" + ((InpatientOrderPerformDTO)inpatientOrderPerformList.get(0)).getOrderNo() + " 。");
                  this.log.error("医嘱处理失败，患者姓名：" + dto.getPatientName() + " ； orderNo：" + dto.getOrderNo() + " 。", e);
               }
            }
         }

         this.redisCache.deleteObject("inpatientOrderPerformCancel:" + deptCode);
      } catch (Exception e) {
         this.redisCache.deleteObject("inpatientOrderPerformCancel:" + deptCode);
         ajaxResult = AjaxResult.error("医嘱处理作废出现异常，请联系管理员!");
         this.log.error("医嘱处理作废出现异常异常", e);
      }

      return ajaxResult;
   }

   @ResponseBody
   @RequestMapping({"/updatePerformStartUse"})
   @PreAuthorize("@ss.hasAnyPermi('docOrder:doctorder:updatePerformStartUse')")
   public AjaxResult updatePerformStartUse(@RequestBody InpatientSuspendOrderListReq req, HttpServletRequest request) {
      AjaxResult ajaxResult = AjaxResult.success("启用成功");
      List<InpatientOrderResultDetailDTO> inpatientOrderResultDetailDTOS = new ArrayList();
      List<Map<String, Object>> printList = new ArrayList();
      Map<String, Boolean> patientInHospitalFlagMap = new HashMap();
      String deptCode = SecurityUtils.getLoginUser().getUser().getDept().getDeptCode();

      try {
         String ip = IPAddressUtil.getIPAddress(request);
         String redisDeptCode = (String)this.redisCache.getCacheObject("inpatientOrderPerform:" + deptCode);
         if (StringUtils.isNotBlank(redisDeptCode) && redisDeptCode.equals("1")) {
            ajaxResult = AjaxResult.error("当前科室正在核对医嘱，请稍后再核对");
            return ajaxResult;
         }

         this.redisCache.setCacheObject("inpatientOrderPerform:" + deptCode, "1");
         List<InpatientOrderPerformDTO> performList = req.getList();
         Map<String, List<InpatientOrderPerformDTO>> performListMap = new HashMap();

         for(InpatientOrderPerformDTO inpatientOrderPerformDTO : performList) {
            if (performListMap.containsKey(inpatientOrderPerformDTO.getPerformListNo())) {
               List<InpatientOrderPerformDTO> inpatientOrderPerformDTOList = (List)performListMap.get(inpatientOrderPerformDTO.getPerformListNo());
               inpatientOrderPerformDTOList.add(inpatientOrderPerformDTO);
            } else {
               List<InpatientOrderPerformDTO> inpatientOrderPerformDTOList = new ArrayList();
               inpatientOrderPerformDTOList.add(inpatientOrderPerformDTO);
               performListMap.put(inpatientOrderPerformDTO.getPerformListNo(), inpatientOrderPerformDTOList);
            }
         }

         SysUser user = SecurityUtils.getLoginUser().getUser();

         for(String key : performListMap.keySet()) {
            StringBuffer msgSb = new StringBuffer();
            List<InpatientOrderPerformDTO> inpatientOrderPerformList = (List)performListMap.get(key);
            InpatientOrderPerformDTO dto = (InpatientOrderPerformDTO)inpatientOrderPerformList.get(0);
            String admissionNo = dto.getAdmissionNo();
            Boolean patientInHospitalFlag = patientInHospitalFlagMap.containsKey(admissionNo) ? (Boolean)patientInHospitalFlagMap.get(dto.getAdmissionNo()) : null;
            if (patientInHospitalFlag == null) {
               patientInHospitalFlag = this.commonService.checkPatientIsIn(admissionNo, dto.getHospitalizedCount() + "");
               patientInHospitalFlagMap.put(admissionNo, patientInHospitalFlag);
            }

            if (!patientInHospitalFlag) {
               msgSb.append("患者已经出院，不能处理他的医嘱；");
            } else {
               boolean mark = this.tdPaOrderStatusService.getOrderStatusIsEffective(dto.getOrderNo(), dto.getOrderSortNumber(), dto.getOrderGroupNo(), "3", (String)null);
               if (!mark) {
                  msgSb.append("患者医嘱当前不是可以处理的状态。");
               } else {
                  String orderNoHandlingKey = "inpatientOrderNoHandling:" + dto.getOrderNo();
                  String orderNoHandling = (String)this.orderRedisCache.getCacheObject(orderNoHandlingKey);
                  if (StringUtils.isNotBlank(orderNoHandling)) {
                     String orderNoHandlingMsg = CommonUtils.getOrderNoHandlingMsg(Integer.valueOf(orderNoHandling));
                     msgSb.append(orderNoHandlingMsg);
                  } else {
                     this.orderRedisCache.setCacheObject(orderNoHandlingKey, 10, 300, TimeUnit.SECONDS);
                     List<DrugAndClin> drugAndClinList = null;
                     List<DrugAndClin> yfkcHzList = null;
                     List<TakeDrugList> takeDrugList = null;
                     Map<String, List<DrugAndClin>> yfkcHzMap = null;
                     Boolean performFirstGenFlag = dto.getOrderType().equals("1") && StringUtils.isBlank(dto.getPerformFirstGenFlag()) || StringUtils.isNotBlank(dto.getPerformFirstGenFlag()) && dto.getPerformFirstGenFlag().equals("0");
                     if (dto.getOrderClassCode().equals("01") && (dto.getOrder_status().equals(String.valueOf(3)) || dto.getOrder_status().equals(String.valueOf(2)) && performFirstGenFlag)) {
                        String performListNo = ((InpatientOrderPerformDTO)inpatientOrderPerformList.get(0)).getPerformListNo();
                        takeDrugList = this.takeDrugListService.selectByPerformListNo(performListNo);
                        if (takeDrugList != null && takeDrugList.size() > 0) {
                           List<String> drugCodeList = (List)takeDrugList.stream().map((t) -> t.getPharmacopoeiaNo()).collect(Collectors.toList());
                           String takeDrugDeptCode = dto.getDetailPerformDepCode();
                           yfkcHzList = this.drugStockService.selectHisDrugStockList(drugCodeList, takeDrugDeptCode);
                           yfkcHzMap = (Map)yfkcHzList.stream().collect(Collectors.groupingBy((t) -> t.getCpNo()));
                           drugAndClinList = new ArrayList(1);

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
                     }

                     OrderDoHandleDrugDoseVo drugDoseVo = null;

                     try {
                        drugDoseVo = this.inpatientSuspendOrderService.updatePerformStartUse(inpatientOrderPerformList, printList, drugAndClinList, user, ip);
                        msgSb.append(drugDoseVo.getErrorMsg());
                     } catch (Exception e) {
                        msgSb.append("医嘱处理失败");
                        this.log.error("医嘱处理失败", e);
                     }

                     this.orderRedisCache.deleteObject(orderNoHandlingKey);
                     if (StringUtils.isNotBlank(msgSb.toString())) {
                        if (takeDrugList == null) {
                           for(InpatientOrderPerformDTO inpatientOrderPerformDTO : inpatientOrderPerformList) {
                              InpatientOrderResultDetailDTO inpatientOrderResultDetailDTO = new InpatientOrderResultDetailDTO();
                              inpatientOrderResultDetailDTO.setOrderNo(inpatientOrderPerformDTO.getOrderNo());
                              inpatientOrderResultDetailDTO.setOrderGroupNo(inpatientOrderPerformDTO.getOrderGroupNo());
                              inpatientOrderResultDetailDTO.setAdmissionNo(inpatientOrderPerformDTO.getAdmissionNo());
                              inpatientOrderResultDetailDTO.setCaseNo(inpatientOrderPerformDTO.getCaseNo());
                              inpatientOrderResultDetailDTO.setChargeName(inpatientOrderPerformDTO.getChargeName());
                              inpatientOrderResultDetailDTO.setPatientName(inpatientOrderPerformDTO.getPatientName());
                              inpatientOrderResultDetailDTO.setErroMsg(msgSb.toString());
                              inpatientOrderResultDetailDTO.setBedId(inpatientOrderPerformDTO.getBedid());
                              inpatientOrderResultDetailDTO.setPrice(inpatientOrderPerformDTO.getPrice());
                              inpatientOrderResultDetailDTO.setTotal(inpatientOrderPerformDTO.getOrderTotal());
                              inpatientOrderResultDetailDTO.setOrderTotalDose(inpatientOrderPerformDTO.getOrderDose());
                              inpatientOrderResultDetailDTOS.add(inpatientOrderResultDetailDTO);
                           }
                        } else {
                           for(TakeDrugList inpatientOrderPerformDTO : takeDrugList) {
                              InpatientOrderResultDetailDTO inpatientOrderResultDetailDTO = new InpatientOrderResultDetailDTO();
                              inpatientOrderResultDetailDTO.setOrderNo(inpatientOrderPerformDTO.getOrderNo());
                              inpatientOrderResultDetailDTO.setOrderGroupNo(inpatientOrderPerformDTO.getOrderGroupNo());
                              inpatientOrderResultDetailDTO.setAdmissionNo(inpatientOrderPerformDTO.getAdmissionNo());
                              inpatientOrderResultDetailDTO.setCaseNo(inpatientOrderPerformDTO.getCaseNo());
                              inpatientOrderResultDetailDTO.setChargeName(inpatientOrderPerformDTO.getDrugName());
                              inpatientOrderResultDetailDTO.setPatientName(inpatientOrderPerformDTO.getPatientName());
                              inpatientOrderResultDetailDTO.setErroMsg(msgSb.toString());
                              inpatientOrderResultDetailDTO.setBedId(inpatientOrderPerformDTO.getBedid());
                              inpatientOrderResultDetailDTO.setPrice(inpatientOrderPerformDTO.getOrderPrice());
                              inpatientOrderResultDetailDTO.setTotal(inpatientOrderPerformDTO.getOrderPrice().multiply(inpatientOrderPerformDTO.getOrderDose()).setScale(3, 1).stripTrailingZeros());
                              inpatientOrderResultDetailDTO.setOrderTotalDose(inpatientOrderPerformDTO.getOrderDose());
                              inpatientOrderResultDetailDTOS.add(inpatientOrderResultDetailDTO);
                           }
                        }
                     }
                  }
               }
            }
         }

         List<Map<String, List<Map<String, Object>>>> mapList1 = new ArrayList();
         this.reportConfigService.groupByExecutorDpt(mapList1, printList);
         ajaxResult.put("printList", mapList1);
         ajaxResult.put("failResult", inpatientOrderResultDetailDTOS);
         this.redisCache.deleteObject("inpatientOrderPerform:" + deptCode);
      } catch (Exception e) {
         this.redisCache.deleteObject("inpatientOrderPerform:" + deptCode);
         ajaxResult = AjaxResult.error("医嘱处理启用出现异常，请联系管理员!");
         this.log.error("医嘱处理启用出现异常", e);
      }

      return ajaxResult;
   }

   @ResponseBody
   @RequestMapping({"/selectTodayOrderList"})
   @PreAuthorize("@ss.hasAnyPermi('docOrder:doctorder:selectTodayOrderOperation')")
   public AjaxResult selectTodayOrderList(TodayOrderOperationReq req, Integer pageNum, Integer pageSize) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         List<InpatientTodayOrderPerformDTO> totalList = this.inpatientSuspendOrderService.selectTodayOrderOperationAllList(req);
         List<InpatientTodayOrderPerformDTO> list = this.inpatientSuspendOrderService.selectTodayOrderOperation(totalList, pageNum, pageSize);
         ajaxResult.put("rows", list);
         ajaxResult.put("total", totalList.size());
      } catch (Exception e) {
         ajaxResult = AjaxResult.error("查询执行记录列表异常");
         this.log.error("查询执行记录列表异常", e);
      }

      return ajaxResult;
   }

   @ResponseBody
   @RequestMapping({"/selectTodayOrderListDetail"})
   @PreAuthorize("@ss.hasAnyPermi('docOrder:doctorder:selectTodayOrderListDetail')")
   public AjaxResult selectTodayOrderListDetail(InpatientTodayOrderPerformDTO req) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      if (StringUtils.isEmpty(req.getOrderNo())) {
         ajaxResult = AjaxResult.error("医嘱编码不能为空");
         return ajaxResult;
      } else if (StringUtils.isEmpty(req.getPerformListNo())) {
         ajaxResult = AjaxResult.error("执行单编码不能为空");
         return ajaxResult;
      } else if (StringUtils.isEmpty(req.getAdmissionNo())) {
         ajaxResult = AjaxResult.error("住院号不能为空");
         return ajaxResult;
      } else {
         try {
            Map<String, Object> data = this.inpatientSuspendOrderService.selectTodayOrderListDetail(req);
            ajaxResult.put("data", data);
            return ajaxResult;
         } catch (Exception e) {
            ajaxResult = AjaxResult.error("查询执行单详情异常，请联系管理员");
            this.log.error("查询执行单详情异常，请联系管理员", e);
            return ajaxResult;
         }
      }
   }

   @RequestMapping({"/suppPrintData"})
   @ResponseBody
   @PreAuthorize("@ss.hasPermi('docOrder:doctorder:suppPrintData')")
   public AjaxResult suppPrintData(@RequestBody SuppPrintOrderDataListReq req) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      List<SuppPrintOrderDataReq> reqList = req.getList();
      if (reqList != null && !reqList.isEmpty()) {
         Map<String, List<SuppPrintOrderDataReq>> listMap = (Map)reqList.stream().collect(Collectors.groupingBy(SuppPrintOrderDataReq::getAdmissionNo));
         Set<String> keySet = listMap.keySet();
         if (keySet.size() > 1) {
            ajaxResult = AjaxResult.error("只针对单个患者打印，请重新选择！");
            return ajaxResult;
         } else {
            try {
               List<Map<String, List<Map<String, Object>>>> list = this.inpatientSuspendOrderService.getSuppPrintData(req);
               if (list != null && list.size() > 0) {
                  ajaxResult.put("data", list);
               } else {
                  ajaxResult = AjaxResult.error("没有记账单信息,无法打印数据！");
               }
            } catch (Exception e) {
               ajaxResult = AjaxResult.error("补打记账单出现异常，请联系管理员！");
               this.log.error("补打记账单出现异常，请联系管理员！", e);
            }

            return ajaxResult;
         }
      } else {
         ajaxResult = AjaxResult.error("打印数据不能为空");
         return ajaxResult;
      }
   }

   @ResponseBody
   @RequestMapping({"/updateSkinTestResults"})
   @PreAuthorize("@ss.hasAnyPermi('docOrder:doctorder:updateSkinTestResults')")
   public AjaxResult updateSkinTestResults(AllergyRecordDTO req) {
      AjaxResult ajaxResult = AjaxResult.success("修改成功");
      if (StringUtils.isEmpty(req.getAdmissionNo())) {
         ajaxResult = AjaxResult.error("住院号不能为空");
         return ajaxResult;
      } else if (StringUtils.isEmpty(req.getOrderSortNumber())) {
         ajaxResult = AjaxResult.error("医嘱序号不能为空");
         return ajaxResult;
      } else if (StringUtils.isEmpty(req.getOrderNo())) {
         ajaxResult = AjaxResult.error("医嘱编号不能为空");
         return ajaxResult;
      } else if (StringUtils.isEmpty(req.getUserCode())) {
         ajaxResult = AjaxResult.error("工号不能为空");
         return ajaxResult;
      } else if (StringUtils.isEmpty(req.getPassWord())) {
         ajaxResult = AjaxResult.error("密码不能为空");
         return ajaxResult;
      } else if (StringUtils.isEmpty(req.getSkinTestResults())) {
         ajaxResult = AjaxResult.error("皮试结果不能为空");
         return ajaxResult;
      } else if (StringUtils.isEmpty(req.getChargeNo())) {
         ajaxResult = AjaxResult.error("医嘱编码不能为空");
         return ajaxResult;
      } else if (StringUtils.isEmpty(req.getChargeName())) {
         ajaxResult = AjaxResult.error("医嘱名称不能为空");
         return ajaxResult;
      } else if (req.getOperatorDate() == null) {
         ajaxResult = AjaxResult.error("修改皮试结果时间不能为空");
         return ajaxResult;
      } else {
         String passWord = req.getPassWord();
         BsStaff record = new BsStaff();
         record.setStaffNo(req.getUserCode());
         BsStaff staff = this.staffMapper.selectByConn(record);
         int count = 0;
         if ("2".equals(staff.getPasswordHis() == null ? "" : staff.getPasswordHis().trim())) {
            if ("0".equals(passWord)) {
               count = 1;
            }
         } else {
            passWord = EncryptPSWUtil.createEncryptPSW(passWord);
            String staffPassword = staff.getPassword();
            if (!staffPassword.equals(passWord)) {
               ajaxResult = AjaxResult.error("账号密码有误");
               return ajaxResult;
            }
         }

         try {
            this.inpatientSuspendOrderService.updateSkinTestResults(req);
         } catch (Exception e) {
            ajaxResult = AjaxResult.error("修改皮试结果异常，请联系管理员");
            this.log.error("修改皮试结果异常，请联系管理员", e);
         }

         return ajaxResult;
      }
   }

   @ResponseBody
   @RequestMapping({"/voidOrderPerform"})
   @PreAuthorize("@ss.hasAnyPermi('docOrder:doctorder:voidOrderPerform')")
   public AjaxResult voidOrderPerform(ProcessingOrdersReq req, HttpServletRequest request) {
      AjaxResult ajaxResult = AjaxResult.success("作废成功");
      if (StringUtils.isEmpty(req.getAdmissionNo())) {
         ajaxResult = AjaxResult.error("住院号不能为空");
         return ajaxResult;
      } else if (StringUtils.isEmpty(req.getOrderSortNumber())) {
         ajaxResult = AjaxResult.error("医嘱序号不能为空");
         return ajaxResult;
      } else if (StringUtils.isEmpty(req.getOrderNo())) {
         ajaxResult = AjaxResult.error("医嘱编号不能为空");
         return ajaxResult;
      } else if (StringUtils.isEmpty(req.getPerformListNo())) {
         ajaxResult = AjaxResult.error("执行单不能为空");
         return ajaxResult;
      } else if (StringUtils.isEmpty(req.getOrderClassCode())) {
         ajaxResult = AjaxResult.error("医嘱类别不能为空");
         return ajaxResult;
      } else if (req.getPerformListSortNumberList() != null && req.getPerformListSortNumberList().size() != 0) {
         if (StringUtils.isEmpty(req.getOrderGroupNo())) {
            ajaxResult = AjaxResult.error("医嘱组号不能为空");
            return ajaxResult;
         } else if (req.getHospitalizedCount() == null) {
            ajaxResult = AjaxResult.error("住院次数不能为空");
            return ajaxResult;
         } else {
            List<String> sortNumberList = req.getPerformListSortNumberList();
            Boolean performStatus = this.inpatientSuspendOrderService.selectPerformStatus(req.getPerformListNo(), sortNumberList, "2");
            if (performStatus) {
               ajaxResult = AjaxResult.error("只有待执行的执行单才能作废");
               return ajaxResult;
            } else {
               try {
                  Boolean patientInHospitalFlag = this.commonService.checkPatientIsIn(req.getAdmissionNo(), req.getHospitalizedCount() + "");
                  if (!patientInHospitalFlag) {
                     ajaxResult = AjaxResult.error("患者已经出院，不能处理他的医嘱");
                     return ajaxResult;
                  }

                  String orderClassCode = req.getOrderClassCode();
                  if (orderClassCode.equals("02") || orderClassCode.equals("03")) {
                     String status = this.applyFormService.selectApplyForm(req.getOrderNo(), orderClassCode);
                     if (StringUtils.isNotEmpty(status) && !status.equals("30") && !status.equals("31")) {
                        ajaxResult = AjaxResult.error("该执行单不允许作废");
                        return ajaxResult;
                     }
                  }

                  String ip = IpUtils.getIpAddr(request);
                  this.inpatientSuspendOrderService.voidOrderPerform(req, ip);
               } catch (Exception e) {
                  ajaxResult = AjaxResult.error("作废执行单异常，请联系管理员");
                  this.log.error("作废执行单异常，请联系管理员", e);
               }

               return ajaxResult;
            }
         }
      } else {
         ajaxResult = AjaxResult.error("执行单序号不能为空");
         return ajaxResult;
      }
   }

   @ResponseBody
   @RequestMapping({"/revokeOrderPerform"})
   @PreAuthorize("@ss.hasAnyPermi('docOrder:doctorder:revokeOrderPerform')")
   public AjaxResult revokeOrderPerform(ProcessingOrdersReq req, HttpServletRequest request) {
      AjaxResult ajaxResult = AjaxResult.success("撤销成功");
      if (StringUtils.isEmpty(req.getAdmissionNo())) {
         ajaxResult = AjaxResult.error("住院号不能为空");
         return ajaxResult;
      } else if (StringUtils.isEmpty(req.getOrderSortNumber())) {
         ajaxResult = AjaxResult.error("医嘱序号不能为空");
         return ajaxResult;
      } else if (StringUtils.isEmpty(req.getOrderNo())) {
         ajaxResult = AjaxResult.error("医嘱编号不能为空");
         return ajaxResult;
      } else if (StringUtils.isEmpty(req.getPerformListNo())) {
         ajaxResult = AjaxResult.error("执行单不能为空");
         return ajaxResult;
      } else if (StringUtils.isEmpty(req.getOrderClassCode())) {
         ajaxResult = AjaxResult.error("医嘱类别不能为空");
         return ajaxResult;
      } else if (req.getPerformListSortNumberList() != null && req.getPerformListSortNumberList().size() != 0) {
         if (StringUtils.isEmpty(req.getOrderGroupNo())) {
            ajaxResult = AjaxResult.error("医嘱组号不能为空");
            return ajaxResult;
         } else if (req.getHospitalizedCount() == null) {
            ajaxResult = AjaxResult.error("住院次数不能为空");
            return ajaxResult;
         } else {
            List<String> sortNumberList = req.getPerformListSortNumberList();
            Boolean performStatus = this.inpatientSuspendOrderService.selectPerformStatus(req.getPerformListNo(), sortNumberList, "1");
            if (performStatus) {
               ajaxResult = AjaxResult.error("只有已执行的执行单才能撤销");
               return ajaxResult;
            } else {
               try {
                  Boolean patientInHospitalFlag = this.commonService.checkPatientIsIn(req.getAdmissionNo(), req.getHospitalizedCount() + "");
                  if (!patientInHospitalFlag) {
                     ajaxResult = AjaxResult.error("患者已经出院，不能处理他的医嘱");
                     return ajaxResult;
                  }

                  String orderClassCode = req.getOrderClassCode();
                  if (!orderClassCode.equals("02") && !orderClassCode.equals("03") && !orderClassCode.equals("07")) {
                     this.inpatientSuspendOrderService.revokeOrderPerform(req);
                  } else {
                     String status = this.applyFormService.selectApplyForm(req.getOrderNo(), orderClassCode);
                     if (StringUtils.isNotEmpty(status) && !status.equals("30") && !status.equals("31")) {
                        ajaxResult = AjaxResult.error("该执行单不允许撤销");
                        return ajaxResult;
                     }

                     String ip = IpUtils.getIpAddr(request);
                     this.inpatientSuspendOrderService.voidOrderPerform(req, ip);
                  }
               } catch (Exception e) {
                  ajaxResult = AjaxResult.error("撤销执行单异常，请联系管理员");
                  this.log.error("撤销执行单异常，请联系管理员", e);
               }

               return ajaxResult;
            }
         }
      } else {
         ajaxResult = AjaxResult.error("执行单序号不能为空");
         return ajaxResult;
      }
   }

   @ResponseBody
   @RequestMapping({"/revokeAndVoidOrderPerform"})
   @PreAuthorize("@ss.hasAnyPermi('docOrder:doctorder:revokeAndVoidOrderPerform')")
   public AjaxResult revokeAndVoidOrderPerform(ProcessingOrdersReq req, HttpServletRequest request) {
      AjaxResult ajaxResult = AjaxResult.success("撤销并作废成功");
      if (StringUtils.isEmpty(req.getAdmissionNo())) {
         ajaxResult = AjaxResult.error("住院号不能为空");
         return ajaxResult;
      } else if (StringUtils.isEmpty(req.getOrderSortNumber())) {
         ajaxResult = AjaxResult.error("医嘱序号不能为空");
         return ajaxResult;
      } else if (StringUtils.isEmpty(req.getOrderNo())) {
         ajaxResult = AjaxResult.error("医嘱编号不能为空");
         return ajaxResult;
      } else if (StringUtils.isEmpty(req.getPerformListNo())) {
         ajaxResult = AjaxResult.error("执行单不能为空");
         return ajaxResult;
      } else if (StringUtils.isEmpty(req.getOrderClassCode())) {
         ajaxResult = AjaxResult.error("医嘱类别不能为空");
         return ajaxResult;
      } else if (req.getPerformListSortNumberList() != null && req.getPerformListSortNumberList().size() != 0) {
         if (StringUtils.isEmpty(req.getOrderGroupNo())) {
            ajaxResult = AjaxResult.error("医嘱组号不能为空");
            return ajaxResult;
         } else if (req.getHospitalizedCount() == null) {
            ajaxResult = AjaxResult.error("住院次数不能为空");
            return ajaxResult;
         } else {
            List<String> sortNumberList = req.getPerformListSortNumberList();
            Boolean performStatus = this.inpatientSuspendOrderService.selectPerformStatus(req.getPerformListNo(), sortNumberList, "1");
            if (performStatus) {
               ajaxResult = AjaxResult.error("只有已执行的执行单才能撤销并作废");
               return ajaxResult;
            } else {
               try {
                  Boolean patientInHospitalFlag = this.commonService.checkPatientIsIn(req.getAdmissionNo(), req.getHospitalizedCount() + "");
                  if (!patientInHospitalFlag) {
                     ajaxResult = AjaxResult.error("患者已经出院，不能处理他的医嘱");
                     return ajaxResult;
                  }

                  String orderClassCode = req.getOrderClassCode();
                  if (orderClassCode.equals("02") || orderClassCode.equals("03")) {
                     String status = this.applyFormService.selectApplyForm(req.getOrderNo(), orderClassCode);
                     if (StringUtils.isNotEmpty(status) && !status.equals("30") && !status.equals("31")) {
                        ajaxResult = AjaxResult.error("该执行单不允许撤销并作废");
                        return ajaxResult;
                     }
                  }

                  String ip = IpUtils.getIpAddr(request);
                  this.orderPerFormVoidRevokeService.revokeAndVoidOrderPerform(req, ip);
               } catch (Exception e) {
                  ajaxResult = AjaxResult.error("撤销并作废执行单异常，请联系管理员");
                  this.log.error("撤销并作废执行单异常，请联系管理员", e);
               }

               return ajaxResult;
            }
         }
      } else {
         ajaxResult = AjaxResult.error("执行单序号不能为空");
         return ajaxResult;
      }
   }

   @ResponseBody
   @PreAuthorize("@ss.hasPermi('doctorder:doctorder:sysDictDataList')")
   @GetMapping({"/getSysDictDataList"})
   public AjaxResult getSysDictDataList() {
      AjaxResult resultMsg = AjaxResult.success("查询成功");

      try {
         List<SysDictDataVO> list = this.inpatientSuspendOrderService.getSysDictDataList();
         resultMsg.put("data", list);
      } catch (Exception e) {
         resultMsg = AjaxResult.error("作废/撤销原因查询异常，请联系管理员");
         this.log.error("作废/撤销原因查询出现异常：", e);
      }

      return resultMsg;
   }

   @RequestMapping({"/updateExecutionTime"})
   @ResponseBody
   @PreAuthorize("@ss.hasPermi('doctorder:doctorder:updateExecutionTime')")
   public AjaxResult updateExecutionTime(@RequestBody UpdateExecutionTimeVo vo) {
      AjaxResult resultMsg = AjaxResult.success("修改成功");
      List<String> orderNoList = vo.getOrderNoList();
      if (orderNoList != null && !orderNoList.isEmpty()) {
         Date executionTime = vo.getUpdateExecutionTime();
         if (executionTime == null) {
            resultMsg = AjaxResult.error("执行时间不能为空！");
            return resultMsg;
         } else {
            List<TdPaOrderItem> itemDtoList = this.inpatientSuspendOrderService.selectOrderItemByOrderNoList(orderNoList);
            if (itemDtoList.size() == 0) {
               resultMsg = AjaxResult.error("没有对应医嘱信息，请刷新后重试！");
               return resultMsg;
            } else {
               List<TdPaOrderItem> otherOrderList = (List)itemDtoList.stream().filter((m) -> !m.getOrderType().equals("2")).collect(Collectors.toList());
               if (otherOrderList.size() > 0) {
                  resultMsg = AjaxResult.error("只有临时医嘱才能修改执行时间！");
                  return resultMsg;
               } else {
                  List<TdPaOrderItem> otherStatusList = (List)itemDtoList.stream().filter((m) -> !m.getOrderStatus().equals(String.valueOf(8))).collect(Collectors.toList());
                  if (otherStatusList.size() > 0) {
                     resultMsg = AjaxResult.error("只有已执行的临时医嘱才能修改执行时间！");
                     return resultMsg;
                  } else {
                     TdPaOrder inpatientOrder = this.inpatientSuspendOrderService.selectOrderByOrderNo((String)orderNoList.get(0));
                     if (inpatientOrder != null) {
                        EmrQcFlow emrQcFlow = this.emrQcFlowService.getEmrQcFlowByPatientId(inpatientOrder.getAdmissionNo());
                        if (emrQcFlow != null && emrQcFlow.getMrState().equals("16")) {
                           resultMsg = AjaxResult.error("患者已归档，不允许修改医嘱时间");
                           return resultMsg;
                        }
                     }

                     try {
                        String configFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0090");
                        if (StringUtils.isNotBlank(configFlag) && configFlag.equals("1")) {
                           long errorCount1 = itemDtoList.stream().filter((ex) -> {
                              Date orderStartTime = ex.getOrderStartTime();
                              return null != orderStartTime && executionTime.getTime() < orderStartTime.getTime();
                           }).count();
                           if (errorCount1 > 0L) {
                              resultMsg = AjaxResult.error("修改的时间不能早于开始时间！");
                              return resultMsg;
                           }
                        }

                        this.inpatientSuspendOrderService.updateExecTimeByOrderNoList(orderNoList, executionTime);
                     } catch (Exception e) {
                        resultMsg = AjaxResult.error("批量修改执行记录时间出现异常！");
                        this.log.error("批量修改执行记录时间出现异常！", e);
                     }

                     return resultMsg;
                  }
               }
            }
         }
      } else {
         resultMsg = AjaxResult.error("医嘱编号不能为空！");
         return resultMsg;
      }
   }
}
