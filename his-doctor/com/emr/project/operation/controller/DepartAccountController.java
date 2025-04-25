package com.emr.project.operation.controller;

import com.emr.common.utils.IPAddressUtil;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.ToggleCaseUtils;
import com.emr.framework.redis.RedisCache;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.docOrder.domain.TdPaItemDocQuery;
import com.emr.project.docOrder.service.ITdPaItemDocQueryService;
import com.emr.project.docOrder.service.ITdPaOrderService;
import com.emr.project.docOrder.service.ITdPaTakeDrugListService;
import com.emr.project.esSearch.service.IDrugAndClinService;
import com.emr.project.esSearch.service.IDrugStockService;
import com.emr.project.his.domain.vo.DrugDoseVo;
import com.emr.project.operation.domain.TakeDrugList;
import com.emr.project.operation.domain.dto.DepartAccountDTO;
import com.emr.project.operation.domain.dto.NursingOperationFeeDTO;
import com.emr.project.operation.domain.req.CombinationListReq;
import com.emr.project.operation.domain.req.CombinationSaveReq;
import com.emr.project.operation.domain.req.NurseWorkloadReq;
import com.emr.project.operation.domain.req.OperationPatientReq;
import com.emr.project.operation.domain.req.SearchDoctorListReq;
import com.emr.project.operation.domain.req.SuppPrintDataListReq;
import com.emr.project.operation.domain.resp.CombinationDetailsResp;
import com.emr.project.operation.domain.resp.CombinationListResp;
import com.emr.project.operation.domain.resp.DeptDataResp;
import com.emr.project.operation.domain.resp.DoctorDataResp;
import com.emr.project.operation.domain.resp.NurseDataResp;
import com.emr.project.operation.domain.resp.NurseWorkloadResp;
import com.emr.project.operation.domain.resp.OperationApplyResp;
import com.emr.project.operation.domain.resp.OperationPatientDetailsResp;
import com.emr.project.operation.domain.resp.PrintDrugListResp;
import com.emr.project.operation.domain.vo.PrintDrugListVo;
import com.emr.project.operation.domain.vo.TakeDrugListSaveVO;
import com.emr.project.operation.domain.vo.TdPaTakeDrugListPageVo;
import com.emr.project.operation.domain.vo.VoidTakeDrugVo;
import com.emr.project.operation.service.ExpenseDetailService;
import com.emr.project.operation.service.ICommonOperationService;
import com.emr.project.operation.service.IDepartAccountService;
import com.emr.project.operation.service.IPriceYyService;
import com.emr.project.pat.domain.ExpenseDetail;
import com.emr.project.pat.domain.vo.ExpenseDetailVo;
import com.emr.project.system.domain.SysDept;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysDeptService;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.tmpa.domain.OrderSigStand;
import com.emr.project.tmpa.service.IOrderSigService;
import com.emr.project.tmpm.domain.PrintTmplInfo;
import com.emr.project.tmpm.service.IPrintTmplInfoService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/operation/account"})
public class DepartAccountController extends BaseController {
   private static Logger log = LoggerFactory.getLogger(DepartAccountController.class);
   @Autowired
   private IDepartAccountService departAccountService;
   @Autowired
   private ITdPaTakeDrugListService takeDrugListService;
   @Autowired
   private IPriceYyService priceYyService;
   @Autowired
   private ICommonOperationService commonService;
   @Autowired
   private ITdPaOrderService tdPaOrderService;
   @Autowired
   private IDrugStockService drugStockService;
   @Autowired
   private IDrugAndClinService drugAndClinService;
   @Autowired
   private ISysDeptService sysDeptService;
   @Autowired
   private IPrintTmplInfoService printTmplInfoService;
   @Autowired
   private IOrderSigService orderSigService;
   @Autowired
   private ITdPaItemDocQueryService tdPaItemDocQueryService;
   @Autowired
   private ExpenseDetailService expenseDetailService;
   @Autowired
   private RedisCache redisCache;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;

   @PostMapping({"/getOperationPatientList"})
   @ResponseBody
   @PreAuthorize("@ss.hasAnyPermi('query:operation:account:operationPatient,query:operation:refundAccount:operationPatient,docOrder:doctorder:checkPatList,docOrder:doctorder:performPatList,docOrder:orderList:patList')")
   public AjaxResult getOperationPatientList(@RequestBody OperationPatientReq req) {
      AjaxResult resultMsg = AjaxResult.success("查询成功");
      if (req == null) {
         resultMsg = AjaxResult.error("参数不能为空！");
         return resultMsg;
      } else if (req.getType() == null) {
         resultMsg = AjaxResult.error("查询类型不能为空！");
         return resultMsg;
      } else if (req.getType() != 1 && req.getType() != 2) {
         resultMsg = AjaxResult.error("查询类型错误！");
         return resultMsg;
      } else {
         try {
            if (req.getType() == 1) {
               if (StringUtils.isNotBlank(req.getDeptCode()) || StringUtils.isNotBlank(req.getSearchStr())) {
                  List<OperationPatientDetailsResp> list = this.departAccountService.getOperationPatientList(req);
                  resultMsg.put("data", list);
               }
            } else {
               List<OperationPatientDetailsResp> list = this.departAccountService.getOperationPatientList(req);
               resultMsg.put("data", list);
            }
         } catch (Exception e) {
            log.error("查询患者列表异常", e);
            resultMsg = AjaxResult.error("查询患者列表异常，请联系管理员");
         }

         return resultMsg;
      }
   }

   @PostMapping({"/getPriceYy"})
   @ResponseBody
   @PreAuthorize("@ss.hasAnyPermi('query:operation:account:priceYy,operation:tcwh:list')")
   public TableDataInfo getPriceYy(@RequestBody Map param) {
      TableDataInfo tableDataInfo = new TableDataInfo();
      new ArrayList();

      try {
         if (param != null) {
            this.startPage();
            List resList = this.priceYyService.getPriceList(param);
            resList = ToggleCaseUtils.toLowerCaseList(resList);
            int total = this.priceYyService.getPriceListCount(param);
            tableDataInfo = this.getDataTable(resList, (long)total);
         }
      } catch (Exception e) {
         log.error("查询项目价格列表异常", e);
         tableDataInfo = new TableDataInfo(500, "查询项目价格列表异常，请联系管理员");
      }

      return tableDataInfo;
   }

   @PostMapping({"/saveDepartAccount"})
   @ResponseBody
   @PreAuthorize("@ss.hasPermi('operation:account:save')")
   public AjaxResult saveDepartAccount(@RequestBody DepartAccountDTO departAccountDTO) {
      AjaxResult resultMsg = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         if (flag && departAccountDTO == null) {
            flag = false;
            resultMsg = AjaxResult.error("参数不能为空！");
         }

         if (flag && StringUtils.isBlank(departAccountDTO.getZyh())) {
            flag = false;
            resultMsg = AjaxResult.error("参数不能为空！");
         }

         if (flag && (departAccountDTO.getJzList() == null || departAccountDTO.getJzList() != null && departAccountDTO.getJzList().isEmpty())) {
            flag = false;
            resultMsg = AjaxResult.error("患者费用不足！");
         }

         if (flag) {
            BigDecimal totalAmount = new BigDecimal("0");

            for(NursingOperationFeeDTO nursingOperationFeeDTO : departAccountDTO.getJzList()) {
               totalAmount = totalAmount.add(nursingOperationFeeDTO.getJe());
            }

            boolean checkObj = this.commonService.checkPatientArrears(departAccountDTO.getZyh(), String.valueOf(departAccountDTO.getZycs()), totalAmount);
            if (!checkObj) {
               flag = false;
               resultMsg = AjaxResult.error("患者费用不足，自费用户有1000的报警金额！");
            }
         }

         if (flag) {
            String opeDeptFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0087");
            if (StringUtils.isNotEmpty(opeDeptFlag)) {
               String patientDepCode = departAccountDTO.getPatientDepCode();
               List<NursingOperationFeeDTO> nursingOperationFeeDTOList = departAccountDTO.getJzList();
               Map<String, List<NursingOperationFeeDTO>> collect = (Map)nursingOperationFeeDTOList.stream().collect(Collectors.groupingBy(NursingOperationFeeDTO::getKdksbm));
               Set<String> set = collect.keySet();
               if (set.size() > 1) {
                  flag = false;
                  resultMsg = AjaxResult.error("开单科室设置不正确！请刷新后重新录入！");
               }

               if (flag && opeDeptFlag.equals("1") && !set.contains(patientDepCode)) {
                  flag = false;
                  resultMsg = AjaxResult.error("根据配置,开单科室设置不正确！请刷新后重新录入！");
               }
            }
         }

         if (flag) {
            List<NursingOperationFeeDTO> nursingOperationFeeDTOList = departAccountDTO.getJzList();

            for(int i = 0; i < nursingOperationFeeDTOList.size(); ++i) {
               NursingOperationFeeDTO dto = (NursingOperationFeeDTO)nursingOperationFeeDTOList.get(i);
               String zxksbm = dto.getZxksbm();
               if (StringUtils.isEmpty(zxksbm)) {
                  return AjaxResult.error("第" + (i + 1) + "行数据，执行科室编码为空,请刷新页面再做保存！");
               }

               String zxks = dto.getZxks();
               if (StringUtils.isEmpty(zxks)) {
                  return AjaxResult.error("第" + (i + 1) + "行数据，执行科室为空,请刷新页面再做保存！");
               }
            }
         }

         if (flag) {
            List<Map<String, List<Map<String, Object>>>> mapList1 = new ArrayList();
            Map<String, Object> dataMap = this.departAccountService.saveDepartAccount(departAccountDTO, mapList1);
            resultMsg.put("amount", dataMap.get("amount").toString());
         }
      } catch (Exception e) {
         log.error("保存异常", e);
         resultMsg = AjaxResult.error("保存异常，请联系管理员");
      }

      return resultMsg;
   }

   @PostMapping({"/submitDepartAccount"})
   @ResponseBody
   @PreAuthorize("@ss.hasPermi('operation:account:save')")
   public AjaxResult submitDepartAccount(@RequestBody DepartAccountDTO departAccountDTO) {
      AjaxResult resultMsg = AjaxResult.success("提交成功");
      boolean flag = true;
      String deptCode = SecurityUtils.getLoginUser().getUser().getDept().getDeptCode();

      try {
         String redisDeptCode = (String)this.redisCache.getCacheObject("opeDepartAccountSubmit:" + deptCode);
         if (StringUtils.isNotBlank(redisDeptCode) && redisDeptCode.equals("1")) {
            flag = false;
            resultMsg = AjaxResult.error("当前科室正在提交诊疗记账信息，请稍后再提交");
         } else {
            this.redisCache.setCacheObject("opeDepartAccountSubmit:" + deptCode, "1");
         }

         if (flag && departAccountDTO == null) {
            flag = false;
            resultMsg = AjaxResult.error("参数不能为空！");
         }

         if (flag && CollectionUtils.isEmpty(departAccountDTO.getApplyBillsNoList())) {
            flag = false;
            resultMsg = AjaxResult.error("参数不能为空！");
         }

         List<ExpenseDetail> expenseDetailAppList = flag ? this.expenseDetailService.selectExpenseDetailApplyByBillsNoList(departAccountDTO.getApplyBillsNoList()) : null;
         if (flag && CollectionUtils.isEmpty(expenseDetailAppList)) {
            flag = false;
            resultMsg = AjaxResult.error("没有待提交的记录！");
         }

         if (flag) {
            BigDecimal totalAmount = new BigDecimal("0");

            for(ExpenseDetail expenseDetail : expenseDetailAppList) {
               totalAmount = totalAmount.add(expenseDetail.getTotal());
            }

            boolean checkObj = this.commonService.checkPatientArrears(departAccountDTO.getZyh(), String.valueOf(departAccountDTO.getZycs()), totalAmount);
            if (!checkObj) {
               flag = false;
               resultMsg = AjaxResult.error("患者费用不足，自费用户有1000的报警金额！");
            }
         }

         if (flag) {
            List<Map<String, List<Map<String, Object>>>> mapList1 = new ArrayList();
            Map<String, Object> dataMap = this.departAccountService.submitDepartAccount(expenseDetailAppList, mapList1);
            resultMsg.put("amount", dataMap.get("amount").toString());
            resultMsg.put("list", mapList1);
         }

         this.redisCache.deleteObject("opeDepartAccountSubmit:" + deptCode);
      } catch (Exception e) {
         this.redisCache.deleteObject("opeDepartAccountSubmit:" + deptCode);
         log.error("提交异常", e);
         resultMsg = AjaxResult.error("提交异常，请联系管理员");
      }

      return resultMsg;
   }

   @GetMapping({"/queryDepartAccountApp"})
   @ResponseBody
   @PreAuthorize("@ss.hasPermi('operation:account:save')")
   public AjaxResult queryDepartAccountApp(String admissionNo) {
      AjaxResult resultMsg = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (flag && StringUtils.isBlank(admissionNo)) {
            flag = false;
            resultMsg = AjaxResult.error("患者住院号不能为空！");
         }

         if (flag) {
            String deptCode = SecurityUtils.getLoginUser().getUser().getDept().getDeptCode();
            List<ExpenseDetailVo> list = this.expenseDetailService.selectPatExpenseDetailApplyList(admissionNo, deptCode);
            resultMsg.put("list", list);
         }
      } catch (Exception e) {
         log.error("查询异常", e);
         resultMsg = AjaxResult.error("查询异常，请联系管理员");
      }

      return resultMsg;
   }

   @PostMapping({"/delDepartAccountApp"})
   @ResponseBody
   @PreAuthorize("@ss.hasPermi('operation:account:save')")
   public AjaxResult delDepartAccountApp(@RequestBody DepartAccountDTO departAccountDTO) {
      AjaxResult resultMsg = AjaxResult.success("删除成功");
      boolean flag = true;
      String deptCode = SecurityUtils.getLoginUser().getUser().getDept().getDeptCode();

      try {
         String redisDeptCode = (String)this.redisCache.getCacheObject("opeDepartAccountDel:" + deptCode);
         if (StringUtils.isNotBlank(redisDeptCode) && redisDeptCode.equals("1")) {
            flag = false;
            resultMsg = AjaxResult.error("当前科室正在删除诊疗记账信息，请稍后再删除");
         } else {
            this.redisCache.setCacheObject("opeDepartAccountDel:" + deptCode, "1");
         }

         if (flag && departAccountDTO == null) {
            flag = false;
            resultMsg = AjaxResult.error("参数不能为空！");
         }

         if (flag && CollectionUtils.isEmpty(departAccountDTO.getApplyBillsNoList())) {
            flag = false;
            resultMsg = AjaxResult.error("参数不能为空！");
         }

         List<ExpenseDetail> expenseDetailAppList = flag ? this.expenseDetailService.selectExpenseDetailApplyByBillsNoList(departAccountDTO.getApplyBillsNoList()) : null;
         if (flag && CollectionUtils.isEmpty(expenseDetailAppList)) {
            flag = false;
            resultMsg = AjaxResult.error("没有待删除的记录！");
         }

         if (flag) {
            this.departAccountService.delDepartAccount(expenseDetailAppList);
         }

         this.redisCache.deleteObject("opeDepartAccountDel:" + deptCode);
      } catch (Exception e) {
         this.redisCache.deleteObject("opeDepartAccountDel:" + deptCode);
         log.error("删除异常", e);
         resultMsg = AjaxResult.error("删除异常，请联系管理员");
      }

      return resultMsg;
   }

   @PostMapping({"/getDepartAccountList"})
   @ResponseBody
   @PreAuthorize("@ss.hasPermi('query:operation:account:dataList')")
   public AjaxResult getDepartAccountList(@RequestBody Map param) {
      AjaxResult resultMsg = AjaxResult.success("查询成功");
      List<Map<String, Object>> resList = new ArrayList();

      try {
         if (param != null && StringUtils.isNotBlank((CharSequence)param.get("admissionNo"))) {
            resList = this.departAccountService.getDepartAccountList(param);
         }
      } catch (Exception e) {
         log.error("查询已记账项目列表异常", e);
         resultMsg = AjaxResult.error("查询已记账项目列表异常，请联系管理员");
      }

      List var6 = ToggleCaseUtils.toLowerCaseList(resList);
      resultMsg.put("data", var6);
      return resultMsg;
   }

   @RequestMapping({"/getDoctorList"})
   @ResponseBody
   @PreAuthorize("@ss.hasAnyPermi('query:operation:account:doctorList,emr:index:sign,emr:config:edit,ope:drug:requisition')")
   public AjaxResult getDoctorList(SearchDoctorListReq req) {
      AjaxResult resultMsg = AjaxResult.success("查询成功");

      try {
         List<DoctorDataResp> list = this.departAccountService.getDoctorList(req);
         resultMsg.put("data", list);
      } catch (Exception e) {
         log.error("查询医师列表异常", e);
         resultMsg = AjaxResult.error("查询医师列表异常，请联系管理员");
      }

      return resultMsg;
   }

   @RequestMapping({"/getDeptList"})
   @ResponseBody
   @PreAuthorize("@ss.hasAnyPermi('query:operation:account:deptList,mrhp:hp:mrHpDetail,pat:result:newMrFile,pat:visitinfo:mrhp')")
   public AjaxResult getDeptList(String searchStr) {
      AjaxResult resultMsg = AjaxResult.success("查询成功");

      try {
         List<DeptDataResp> list = this.departAccountService.getDeptList(searchStr);
         resultMsg.put("data", list);
      } catch (Exception e) {
         log.error("查询住院科室列表异常", e);
         resultMsg = AjaxResult.error("查询住院科室列表异常，请联系管理员");
      }

      return resultMsg;
   }

   @RequestMapping({"/getNurseList"})
   @ResponseBody
   @PreAuthorize("@ss.hasAnyPermi('query:operation:account:nurseList,ope:nurse:queryNurseWorkload')")
   public AjaxResult getNurseList(String searchStr, String status) {
      AjaxResult resultMsg = AjaxResult.success("查询成功");

      try {
         List<NurseDataResp> list = this.departAccountService.getNurseList(searchStr, status);
         resultMsg.put("data", list);
      } catch (Exception e) {
         log.error("查询护士列表异常", e);
         resultMsg = AjaxResult.error("查询护士列表异常，请联系管理员");
      }

      return resultMsg;
   }

   @RequestMapping({"/getCombinationList"})
   @ResponseBody
   @PreAuthorize("@ss.hasPermi('query:operation:account:combinationList')")
   public AjaxResult getCombinationList(@RequestBody CombinationListReq req) {
      AjaxResult resultMsg = AjaxResult.success("查询成功");
      if (req == null) {
         resultMsg = AjaxResult.error("参数不能为空！");
         return resultMsg;
      } else if (StringUtils.isEmpty(req.getShareClass())) {
         resultMsg = AjaxResult.error("组合类别不能为空！");
         return resultMsg;
      } else if (!req.getShareClass().equals("1") && !req.getShareClass().equals("2") && !req.getShareClass().equals("3")) {
         resultMsg = AjaxResult.error("组合类别参数错误！");
         return resultMsg;
      } else {
         try {
            List<CombinationListResp> list = this.departAccountService.getCombinationList(req);
            resultMsg.put("data", list);
         } catch (Exception e) {
            log.error("查询组合项目列表异常", e);
            resultMsg = AjaxResult.error("查询组合项目列表异常，请联系管理员");
         }

         return resultMsg;
      }
   }

   @RequestMapping({"/getCombinationDetails"})
   @ResponseBody
   @PreAuthorize("@ss.hasPermi('query:operation:account:combinationDetails')")
   public AjaxResult getCombinationDetails(String packageNo) {
      AjaxResult resultMsg = AjaxResult.success("查询成功");
      if (StringUtils.isEmpty(packageNo)) {
         resultMsg = AjaxResult.error("组合编码不能为空！");
         return resultMsg;
      } else {
         try {
            List<CombinationDetailsResp> list = this.departAccountService.getCombinationDetails(packageNo);
            resultMsg.put("data", list);
         } catch (Exception e) {
            log.error("查询组合项目详情列表异常", e);
            resultMsg = AjaxResult.error("查询组合项目详情列表异常，请联系管理员");
         }

         return resultMsg;
      }
   }

   @PostMapping({"/saveCombinationPackage"})
   @ResponseBody
   @PreAuthorize("@ss.hasPermi('operation:account:savePackage')")
   public AjaxResult saveCombinationPackage(@RequestBody CombinationSaveReq req) {
      AjaxResult resultMsg = AjaxResult.success("保存成功");
      if (req == null) {
         resultMsg = AjaxResult.error("参数不能为空！");
         return resultMsg;
      } else if (StringUtils.isEmpty(req.getShareClass())) {
         resultMsg = AjaxResult.error("组合类别不能为空！");
         return resultMsg;
      } else if (!req.getShareClass().equals("1") && !req.getShareClass().equals("2") && !req.getShareClass().equals("3")) {
         resultMsg = AjaxResult.error("组合类别参数错误！");
         return resultMsg;
      } else if (StringUtils.isEmpty(req.getPackageName())) {
         resultMsg = AjaxResult.error("组合名称不能为空！");
         return resultMsg;
      } else {
         try {
            this.departAccountService.saveCombinationPackage(req);
         } catch (Exception e) {
            log.error("另存为组套异常", e);
            resultMsg = AjaxResult.error("另存为组套异常，请联系管理员");
         }

         return resultMsg;
      }
   }

   @RequestMapping({"/getOperationApply"})
   @ResponseBody
   @PreAuthorize("@ss.hasPermi('query:operation:account:operationApply')")
   public AjaxResult getOperationApply(String admissionNo) {
      AjaxResult resultMsg = AjaxResult.success("查询成功");
      if (StringUtils.isEmpty(admissionNo)) {
         resultMsg = AjaxResult.error("组合编码不能为空！");
         return resultMsg;
      } else {
         try {
            List<OperationApplyResp> list = this.departAccountService.getOperationApply(admissionNo);
            resultMsg.put("data", list);
         } catch (Exception e) {
            log.error("查询患者手术申请列表异常", e);
            resultMsg = AjaxResult.error("查询患者手术申请列表异常，请联系管理员");
         }

         return resultMsg;
      }
   }

   @RequestMapping({"/getSuppPrintData"})
   @ResponseBody
   @PreAuthorize("@ss.hasPermi('operation:account:suppPrintData')")
   public List getSuppPrintData(@RequestBody SuppPrintDataListReq req) {
      List<Map<String, List<Map<String, Object>>>> list = this.departAccountService.getSuppPrintData(req);
      return list;
   }

   @RequestMapping({"/saveDrugRequisition"})
   @ResponseBody
   @PreAuthorize("@ss.hasPermi('ope:drug:requisition')")
   public AjaxResult saveDrugRequisition(@RequestBody List list, HttpServletRequest request) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      if (list != null && list.size() != 0) {
         List<String> pharmacyNoList = (List)list.stream().map(TakeDrugList::getPharmacyNo).distinct().collect(Collectors.toList());
         if (pharmacyNoList.size() > 1) {
            ajaxResult = AjaxResult.error("药品领用保存失败！领药药房需为同一药房，请确认后在进行操作！");
            return ajaxResult;
         } else {
            SysUser user = SecurityUtils.getLoginUser().getUser();

            for(int i = 0; i < list.size(); ++i) {
               TakeDrugListSaveVO saveVO = (TakeDrugListSaveVO)list.get(i);
               saveVO.setHospitalCode(user.getHospital().getOrgCode());
               if (StringUtils.isEmpty(saveVO.getAdmissionNo())) {
                  ajaxResult = AjaxResult.error("第" + (i + 1) + "行，住院号不能为空！");
                  return ajaxResult;
               }

               if (StringUtils.isEmpty(saveVO.getPharmacyNo())) {
                  ajaxResult = AjaxResult.error("第" + (i + 1) + "行，药房编码不能为空！");
                  return ajaxResult;
               }

               if (StringUtils.isEmpty(saveVO.getDrugName())) {
                  ajaxResult = AjaxResult.error("第" + (i + 1) + "行，药品名称不能为空！");
                  return ajaxResult;
               }

               if (StringUtils.isEmpty(saveVO.getPharmacopoeiaNo())) {
                  ajaxResult = AjaxResult.error("第" + (i + 1) + "行，药品编码不能为空！");
                  return ajaxResult;
               }

               if (saveVO.getOrderDose() == null) {
                  ajaxResult = AjaxResult.error("第" + (i + 1) + "行，药品数量不能为空！");
                  return ajaxResult;
               }

               if (StringUtils.isEmpty(saveVO.getPhysicianDptNo())) {
                  ajaxResult = AjaxResult.error("第" + (i + 1) + "行，开单科室编码不能为空！");
                  return ajaxResult;
               }

               if (StringUtils.isEmpty(saveVO.getPhysicianDptName())) {
                  ajaxResult = AjaxResult.error("第" + (i + 1) + "行，开单科室名称不能为空！");
                  return ajaxResult;
               }
            }

            try {
               String opeDeptFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0087");
               if (StringUtils.isNotEmpty(opeDeptFlag)) {
                  String patientDepCode = ((TakeDrugListSaveVO)list.get(0)).getPatientDepCode();
                  if (opeDeptFlag.equals("1")) {
                     Map<String, List<TakeDrugListSaveVO>> collect = (Map)list.stream().collect(Collectors.groupingBy(TakeDrugList::getPhysicianDptNo));
                     if (collect.size() > 1 || !collect.containsKey(patientDepCode)) {
                        ajaxResult = AjaxResult.error("根据配置,开单科室设置不正确！请刷新后重新录入！");
                        return ajaxResult;
                     }
                  }
               }

               String stockNumRes = this.tdPaOrderService.isDrugStockNum(list);
               if (StringUtils.isNotBlank(stockNumRes)) {
                  ajaxResult = AjaxResult.error("药品领用保存失败！" + stockNumRes);
                  return ajaxResult;
               }

               String ip = IPAddressUtil.getIPAddress(request);
               List<DrugDoseVo> doseVos = this.takeDrugListService.saveDrug(list);

               try {
                  if (!doseVos.isEmpty()) {
                     SysUser sysUser = SecurityUtils.getLoginUser().getUser();
                     this.drugStockService.updateDrugDoseByOrderDose(sysUser, doseVos, "1", ip);
                  }
               } catch (Exception e) {
                  log.error("药品领用保存更新药品数量出现异常！", e);
               }
            } catch (Exception e) {
               log.error("药品领用保存出现异常！", e);
               ajaxResult = AjaxResult.error("药品领用保存出现异常！");
            }

            return ajaxResult;
         }
      } else {
         ajaxResult = AjaxResult.error("请求参数为空！");
         return ajaxResult;
      }
   }

   @RequestMapping({"/getPerformDeptList"})
   @ResponseBody
   @PreAuthorize("@ss.hasAnyPermi('operation:account:save')")
   public AjaxResult getPerformDeptList() {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         List<SysDept> deptList = this.sysDeptService.selectOrgDeptList(new SysDept());
         ajaxResult.put("data", deptList);
         List<OrderSigStand> orderSigStandList = this.orderSigService.selectOrderSigStandList();
         ajaxResult.put("orderSigStandList", orderSigStandList);
         List<String> typeCodeList = new ArrayList(Arrays.asList("100020001", "100020002"));
         List<PrintTmplInfo> printTmplList = this.printTmplInfoService.selectTmPmPrintTmplInfoByCodes(typeCodeList);
         ajaxResult.put("printTmplList", printTmplList);
         TdPaItemDocQuery tdPaItemDocQuery = new TdPaItemDocQuery();
         tdPaItemDocQuery.setDocCd(SecurityUtils.getLoginUser().getUser().getBasEmployee().getEmplNumber());
         tdPaItemDocQuery.setOrderFlag("18");
         List<TdPaItemDocQuery> queryList = this.tdPaItemDocQueryService.selectTdPaItemDocQueryList(tdPaItemDocQuery);
         ajaxResult.put("icdFlag", queryList != null && !queryList.isEmpty() ? ((TdPaItemDocQuery)queryList.get(0)).getQueryStatus() : "0");
         String opeDeptFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0087");
         ajaxResult.put("opeDeptFlag", opeDeptFlag);
      } catch (Exception e) {
         log.error("手术室-诊疗计费，查询所有科室出现异常！", e);
         ajaxResult = AjaxResult.error("手术室-诊疗计费，查询所有科室出现异常！");
      }

      return ajaxResult;
   }

   @ResponseBody
   @GetMapping({"/detailForApply"})
   @PreAuthorize("@ss.hasPermi('ope:drug:requisition')")
   public AjaxResult queryDetailForApply(TdPaTakeDrugListPageVo req, Integer pageSize, Integer pageNum) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (pageSize == null && pageNum == null) {
            ajaxResult = AjaxResult.error("分页参数不能为空");
            return ajaxResult;
         }

         req.setTakeDrugWardNo(SecurityUtils.getLoginUser().getUser().getDept().getDeptCode());
         List<TdPaTakeDrugListPageVo> listAll = this.takeDrugListService.selectTakeListForApply(req);
         List<TdPaTakeDrugListPageVo> pageList = this.takeDrugListService.selectTakeDrugDetailList(req, listAll, pageSize, pageNum);
         ajaxResult.put("rows", pageList);
         ajaxResult.put("total", listAll.size());
      } catch (Exception e) {
         log.error("查询待审核领药单明细列表出现异常：", e);
         ajaxResult = AjaxResult.error("查询待审核领药单明细列表出现异常");
      }

      return ajaxResult;
   }

   @ResponseBody
   @RequestMapping({"/sendToPharmacy"})
   @PreAuthorize("@ss.hasPermi('ope:drug:requisition')")
   public AjaxResult sendToPharmacy(@RequestBody List params) {
      AjaxResult ajaxResult = AjaxResult.success("发送成功");

      try {
         if (CollectionUtils.isEmpty(params)) {
            ajaxResult = AjaxResult.error("请选择领药申请");
            return ajaxResult;
         } else {
            for(TakeDrugList t : params) {
               if (t.getId() == null) {
                  ajaxResult = AjaxResult.error("领药申请id不能为空");
                  return ajaxResult;
               }
            }

            List<Long> idList = (List)params.stream().map(TakeDrugList::getId).collect(Collectors.toList());
            List<TakeDrugList> takeDrugList = this.takeDrugListService.selectDrugListStatusByIds(idList);
            if (takeDrugList.isEmpty()) {
               ajaxResult = AjaxResult.error("药房已发药或已作废，请重新刷新页面！");
               return ajaxResult;
            } else {
               Map<Long, List<TakeDrugList>> idListMap = (Map)takeDrugList.stream().collect(Collectors.groupingBy(TakeDrugList::getId));

               for(int i = 0; i < params.size(); ++i) {
                  TakeDrugList drugList = (TakeDrugList)params.get(i);
                  Long id = drugList.getId();
                  if (!idListMap.containsKey(id)) {
                     ajaxResult = AjaxResult.error("药房已发药或已作废，请重新刷新页面！");
                     return ajaxResult;
                  }

                  List<TakeDrugList> drugLists = (List)idListMap.get(id);
                  TakeDrugList drug = (TakeDrugList)drugLists.get(0);
                  Integer drugStatus = drug.getTakeDrugStatus();
                  if (drugStatus != 0) {
                     ajaxResult = AjaxResult.error("第" + (i + 1) + "行数据，药品{" + drug.getDrugName() + "},领药状态不正确，请重新刷新页面！");
                     return ajaxResult;
                  }
               }

               PrintDrugListResp resp = this.takeDrugListService.sendToPharmacy(params);
               if (resp != null) {
                  List<PrintDrugListVo> list = resp.getList();
                  if (list != null && list.size() > 0) {
                     List<String> pharmacyList = (List)list.stream().map(PrintDrugListVo::getPharmacyNoName).distinct().collect(Collectors.toList());
                     Map<String, List<String>> map = new HashMap();
                     map.put("pharmacyList", pharmacyList);
                     ajaxResult.put("pharmacyList", pharmacyList);
                  }
               }

               ajaxResult.put("data", resp);
               return ajaxResult;
            }
         }
      } catch (Exception e) {
         log.error("发送领药申请到药房出现异常：", e);
         ajaxResult = AjaxResult.error("发送领药申请到药房出现异常");
         return ajaxResult;
      }
   }

   @ResponseBody
   @PostMapping({"/toVoidTakeDrugLists"})
   @PreAuthorize("@ss.hasPermi('ope:drug:requisition')")
   public AjaxResult toVoidTakeDrugLists(@RequestBody VoidTakeDrugVo drugVo, HttpServletRequest request) {
      AjaxResult ajaxResult = AjaxResult.success("删除成功");
      if (drugVo == null) {
         ajaxResult = AjaxResult.error("参数不能为空");
         return ajaxResult;
      } else {
         List<TdPaTakeDrugListPageVo> list = drugVo.getList();
         if (list != null && !list.isEmpty()) {
            List<String> admissionList = (List)list.stream().map(TakeDrugList::getAdmissionNo).distinct().collect(Collectors.toList());
            if (admissionList.size() > 1) {
               ajaxResult = AjaxResult.error("只能删除单个患者的领药单！");
               return ajaxResult;
            } else {
               List<Long> idList = (List)list.stream().map(TakeDrugList::getId).collect(Collectors.toList());
               List<TakeDrugList> takeDrugList = this.takeDrugListService.selectDrugListStatusByIds(idList);
               if (takeDrugList.isEmpty()) {
                  ajaxResult = AjaxResult.error("药房已发药或已作废，请重新刷新页面！");
                  return ajaxResult;
               } else {
                  Map<Long, List<TakeDrugList>> idListMap = (Map)takeDrugList.stream().collect(Collectors.groupingBy(TakeDrugList::getId));

                  for(int i = 0; i < list.size(); ++i) {
                     TdPaTakeDrugListPageVo drugList = (TdPaTakeDrugListPageVo)list.get(i);
                     Long id = drugList.getId();
                     if (!idListMap.containsKey(id)) {
                        ajaxResult = AjaxResult.error("药房已发药或已作废，请重新刷新页面！");
                        return ajaxResult;
                     }

                     List<TakeDrugList> drugLists = (List)idListMap.get(id);
                     TakeDrugList drug = (TakeDrugList)drugLists.get(0);
                     Integer drugStatus = drug.getTakeDrugStatus();
                     if (drugStatus != 0) {
                        ajaxResult = AjaxResult.error("第" + (i + 1) + "行数据，药品{" + drug.getDrugName() + "},领药状态不正确，请重新刷新页面！");
                        return ajaxResult;
                     }
                  }

                  try {
                     String ip = IPAddressUtil.getIPAddress(request);
                     List<DrugDoseVo> drugDoseVoList = this.takeDrugListService.toVoidTakeDrugLists(list);

                     try {
                        if (drugDoseVoList != null && drugDoseVoList.size() > 0) {
                           SysUser sysUser = SecurityUtils.getLoginUser().getUser();
                           this.drugStockService.updateDrugDoseByOrderDose(sysUser, drugDoseVoList, "0", ip);
                        }
                     } catch (Exception e) {
                        log.error("药品领用-删除领药单补库存出现异常，请联系管理员！", e);
                     }
                  } catch (Exception e) {
                     ajaxResult = AjaxResult.error("删除领药单出现异常，请联系管理员！");
                     log.error("删除领药单出现异常，请联系管理员！", e);
                  }

                  return ajaxResult;
               }
            }
         } else {
            ajaxResult = AjaxResult.error("参数不能为空");
            return ajaxResult;
         }
      }
   }

   @ResponseBody
   @PostMapping({"/nurse/queryNurseWorkload"})
   @PreAuthorize("@ss.hasPermi('ope:nurse:queryNurseWorkload')")
   public AjaxResult queryNurseWorkload(@RequestBody NurseWorkloadReq req) {
      AjaxResult resultMsg = AjaxResult.success("查询成功");
      if (req.getPageSize() == null || req.getPageNum() == null) {
         resultMsg = AjaxResult.error("分页参数不能为空！");
      }

      try {
         List<NurseWorkloadResp> allList = this.departAccountService.queryNurseWorkloadAllList(req);
         List<NurseWorkloadResp> pageList = this.departAccountService.queryNurseWorkloadByPage(req, allList);
         resultMsg.put("total", allList.size());
         resultMsg.put("rows", pageList);
      } catch (Exception e) {
         resultMsg = AjaxResult.error("查询护士工作量数据异常，请联系管理员！");
         log.error("查询护士工作量数据异常，请联系管理员！", e);
      }

      return resultMsg;
   }

   @ResponseBody
   @GetMapping({"/initData"})
   @PreAuthorize("@ss.hasPermi('operation:account:save')")
   public AjaxResult initData() {
      AjaxResult resultMsg = AjaxResult.success("处理成功！");

      try {
         SysUser e = SecurityUtils.getLoginUser().getUser();
      } catch (Exception e) {
         log.error("初始化药品信息失败,请联系管理员。", e);
         resultMsg = AjaxResult.error("初始化药品信息失败,请联系管理员！");
      }

      return resultMsg;
   }
}
