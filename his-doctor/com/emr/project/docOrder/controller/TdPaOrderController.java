package com.emr.project.docOrder.controller;

import com.emr.common.constant.CommonConstants;
import com.emr.common.exception.YbException;
import com.emr.common.utils.CommonUtils;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.IPAddressUtil;
import com.emr.common.utils.MessageUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.ip.IpUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.redis.OrderRedisCache;
import com.emr.framework.redis.RedisCache;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.domain.TreeSelect;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.PASS.domain.vo.mk.CheckInfoVo;
import com.emr.project.PASS.service.IPassService;
import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.domain.TdCaOperationApply;
import com.emr.project.docOrder.domain.TdPaApplyForm;
import com.emr.project.docOrder.domain.TdPaOrder;
import com.emr.project.docOrder.domain.TdPaOrderItem;
import com.emr.project.docOrder.domain.req.PrintOrderDataListDetail;
import com.emr.project.docOrder.domain.req.PrintOrderDataListReq;
import com.emr.project.docOrder.domain.req.RuleAnalysisVo;
import com.emr.project.docOrder.domain.vo.DrugAndClinPatientVo;
import com.emr.project.docOrder.domain.vo.HerbSaveVo;
import com.emr.project.docOrder.domain.vo.ItemIsOpenResVo;
import com.emr.project.docOrder.domain.vo.OrderCommitVo;
import com.emr.project.docOrder.domain.vo.OrderSaveInfoVo;
import com.emr.project.docOrder.domain.vo.OrderSaveResVo;
import com.emr.project.docOrder.domain.vo.OrderSaveVo;
import com.emr.project.docOrder.domain.vo.OrderSearchVo;
import com.emr.project.docOrder.domain.vo.OrderStopDoReturnVo;
import com.emr.project.docOrder.domain.vo.OrderStopSignVo;
import com.emr.project.docOrder.domain.vo.OrderStopVo;
import com.emr.project.docOrder.domain.vo.TdPaOrderAgentVo;
import com.emr.project.docOrder.domain.vo.TdPaOrderDetailVo;
import com.emr.project.docOrder.domain.vo.TdPaOrderItemVo;
import com.emr.project.docOrder.service.ICheckCDSSService;
import com.emr.project.docOrder.service.ILCLJInfoService;
import com.emr.project.docOrder.service.IRuleAnalysisService;
import com.emr.project.docOrder.service.ITdCaOperationApplyService;
import com.emr.project.docOrder.service.ITdPaApplyFormService;
import com.emr.project.docOrder.service.ITdPaOrderDetailService;
import com.emr.project.docOrder.service.ITdPaOrderItemService;
import com.emr.project.docOrder.service.ITdPaOrderOperationLogService;
import com.emr.project.docOrder.service.ITdPaOrderService;
import com.emr.project.docOrder.service.ITdPaTakeDrugListService;
import com.emr.project.dr.domain.DrAntiApply;
import com.emr.project.dr.domain.TdCaConsApply;
import com.emr.project.dr.service.IDrAntiApplyService;
import com.emr.project.dr.service.ITdCaConsApplyService;
import com.emr.project.esSearch.domain.DrugAndClin;
import com.emr.project.esSearch.service.IDrugAndClinService;
import com.emr.project.esSearch.service.IDrugStockService;
import com.emr.project.his.domain.DrugCheck;
import com.emr.project.his.domain.vo.DrugDoseVo;
import com.emr.project.his.domain.vo.PharmacyVo;
import com.emr.project.operation.service.TakeDrugReturnService;
import com.emr.project.other.domain.GrantOutDoctor;
import com.emr.project.other.service.IGrantOutDoctorService;
import com.emr.project.pat.domain.DiagInfo;
import com.emr.project.pat.domain.Personalinfo;
import com.emr.project.pat.domain.Visitinfo;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.pat.service.IDiagInfoService;
import com.emr.project.pat.service.IPersonalinfoService;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysDept;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysDeptService;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.tmpm.domain.ClinItemDetail;
import com.emr.project.tmpm.domain.ClinItemMain;
import com.emr.project.tmpm.service.IClinItemDetailService;
import com.emr.project.tmpm.service.IClinItemMainService;
import com.emr.project.webservice.domain.resp.WebServiceVteResp;
import com.emr.project.webservice.domain.vo.MedicareAuditVo;
import com.emr.project.webservice.service.IMedicareAuditService;
import com.emr.project.webservice.service.VteInfoService;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/docOrder/order"})
public class TdPaOrderController extends BaseController {
   @Autowired
   private ITdPaOrderService tdPaOrderService;
   @Autowired
   private ITdPaOrderItemService tdPaOrderItemService;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private RedisCache redisCache;
   @Autowired
   private OrderRedisCache orderRedisCache;
   @Autowired
   private ITdPaOrderDetailService tdPaOrderDetailService;
   @Autowired
   private IDrugStockService drugStockService;
   @Autowired
   private IDrAntiApplyService drAntiApplyService;
   @Autowired
   private IDrugAndClinService drugAndClinService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private ITdPaApplyFormService tdPaApplyFormService;
   @Autowired
   private ITdCaOperationApplyService tdCaOperationApplyService;
   @Autowired
   private ITdCaConsApplyService tdCaConsApplyService;
   @Autowired
   private IPassService passService;
   @Autowired
   private IClinItemMainService clinItemMainService;
   @Autowired
   private IGrantOutDoctorService grantOutDoctorService;
   @Autowired
   private ICheckCDSSService checkCDSSService;
   @Autowired
   private ITdPaTakeDrugListService tdPaTakeDrugListService;
   @Autowired
   private TakeDrugReturnService takeDrugReturnService;
   @Autowired
   private IClinItemDetailService clinItemDetailService;
   @Autowired
   private IDiagInfoService diagInfoService;
   @Autowired
   private IPersonalinfoService personalinfoService;
   @Autowired
   private ITdPaOrderOperationLogService orderOperationLogService;
   @Autowired
   private VteInfoService vteInfoService;
   @Autowired
   private ILCLJInfoService lcljInfoService;
   @Autowired
   private ISysDeptService deptService;
   @Autowired
   private IMedicareAuditService medicareAuditService;
   @Autowired
   private IRuleAnalysisService ruleAnalysisService;

   @PreAuthorize("@ss.hasPermi('docOrder:order:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(OrderSearchVo queryParam) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         if (StringUtils.isNotBlank(queryParam.getOrderStatus()) && queryParam.getOrderStatus().length() > 0) {
            String orderStatus = queryParam.getOrderStatus();
            queryParam.setOrderStatus((String)null);
            List<String> orderStatusList = Arrays.asList(orderStatus.split(","));
            queryParam.setStatusList(orderStatusList);
         }

         if (queryParam.getOrderStartTimeEnd() != null) {
            queryParam.setOrderStartTimeEnd(DateUtils.addDays(queryParam.getOrderStartTimeEnd(), 1));
         }

         queryParam.setCommitFlag("1");
         List<OrderSearchVo> list = this.tdPaOrderService.selectOrderSearchVoList(queryParam);
         tableDataInfo = this.getDataTable(list);
         List<OrderSearchVo> pageList = this.tdPaOrderService.selectSubOrderSearchVoList(list);
         list.clear();
         list.addAll(pageList);
      } catch (Exception e) {
         this.log.error("查询患者医嘱开立已提交列表出现异常：", e);
         tableDataInfo = new TableDataInfo(500, "查询患者医嘱开立已提交列表出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasPermi('docOrder:order:list')")
   @GetMapping({"/newList"})
   public AjaxResult newList(OrderSearchVo queryParam) {
      AjaxResult ajaxResult = AjaxResult.success("查询患者医嘱开立未提交列表成功");
      boolean flag = true;

      try {
         if (flag && queryParam == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(queryParam.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag) {
            queryParam.setCommitFlag("0");
            queryParam.setOrderStatus("-1");
            queryParam.setOrderDoctorDepCode(SecurityUtils.getLoginUser().getUser().getDept().getDeptCode());
            List<OrderSearchVo> list = this.tdPaOrderService.selectOrderSearchVoList(queryParam);
            List<OrderSearchVo> pageList = this.tdPaOrderService.selectSubOrderSearchVoList(list);
            ajaxResult.put("list", pageList);
            Integer maxGroupNo = this.tdPaOrderItemService.selectPatientMaxGroupNo(queryParam.getPatientId());
            maxGroupNo = maxGroupNo == null ? 1 : maxGroupNo;
            ajaxResult.put("maxGroupNo", maxGroupNo);
         }
      } catch (Exception e) {
         this.log.error("查询患者医嘱开立未提交列表出现异常", e);
         ajaxResult = AjaxResult.error("查询患者医嘱开立未提交列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('docOrder:order:export')")
   @Log(
      title = "主要记录 医嘱的主要信息 ",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(TdPaOrder tdPaOrder) {
      List<TdPaOrder> list = this.tdPaOrderService.selectTdPaOrderList(tdPaOrder);
      ExcelUtil<TdPaOrder> util = new ExcelUtil(TdPaOrder.class);
      return util.exportExcel(list, "主要记录 医嘱的主要信息 数据");
   }

   @PreAuthorize("@ss.hasPermi('docOrder:order:query')")
   @GetMapping({"/{orderNo}"})
   public AjaxResult getInfo(@PathVariable("orderNo") String orderNo) {
      return AjaxResult.success((Object)this.tdPaOrderService.selectTdPaOrderById(orderNo));
   }

   @PreAuthorize("@ss.hasPermi('docOrder:order:add')")
   @Log(
      title = "获取pass合理用药审查接口参数",
      businessType = BusinessType.INSERT
   )
   @PostMapping({"/passParam"})
   public AjaxResult getPassVerifyParam(@RequestBody List orderSaveVoList) {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         String passFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0042");
         if (StringUtils.isNotBlank(passFlag) && passFlag.equals("1")) {
            ajaxResult = this.verify(orderSaveVoList);
            Boolean flag = ajaxResult.get("code").equals(200);
            if (flag) {
               orderSaveVoList = flag ? (List)orderSaveVoList.stream().filter((t) -> t.getOrderClassCode().equals("01")).collect(Collectors.toList()) : null;
               if (orderSaveVoList != null && !orderSaveVoList.isEmpty()) {
                  String patientId = flag ? ((OrderSaveVo)orderSaveVoList.get(0)).getPatientId() : null;
                  CheckInfoVo checkInfoVo = this.passService.getCheckInfoVo_mk(patientId, orderSaveVoList, (HerbSaveVo)null);
                  ajaxResult = AjaxResult.success("查询成功", checkInfoVo);
               }
            }
         } else {
            ajaxResult = AjaxResult.success("获取pass合理用药审查系统未启用");
         }
      } catch (Exception e) {
         this.log.error("获取pass合理用药审查接口参数出现异常：", e);
         ajaxResult = AjaxResult.error("获取pass合理用药审查接口参数出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('docOrder:order:add')")
   @Log(
      title = "获取pass合理用药审查接口参数",
      businessType = BusinessType.INSERT
   )
   @PostMapping({"/passParamForDrug"})
   public AjaxResult getPassVerifyParamForDrug(@RequestBody List orderSaveVoList) {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         SysUser user = SecurityUtils.getLoginUser().getUser();
         String passFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0042");
         if (StringUtils.isNotBlank(passFlag) && passFlag.equals("1")) {
            ajaxResult = this.verify(orderSaveVoList);
            Boolean flag = ajaxResult.get("code").equals(200);
            if (flag) {
               orderSaveVoList = flag ? (List)orderSaveVoList.stream().filter((t) -> t.getOrderClassCode().equals("01")).collect(Collectors.toList()) : null;
               if (CollectionUtils.isNotEmpty(orderSaveVoList)) {
                  this.passService.checkOrderFirst(orderSaveVoList, user.getHospital().getOrgCode());
                  if (CollectionUtils.isNotEmpty(orderSaveVoList)) {
                     String patientId = flag ? ((OrderSaveVo)orderSaveVoList.get(0)).getPatientId() : null;
                     CheckInfoVo checkInfoVo = this.passService.getCheckInfoForDrug_mk(patientId, orderSaveVoList, (HerbSaveVo)null, user.getHospital().getOrgCode());
                     ajaxResult = AjaxResult.success("查询成功", checkInfoVo);
                  }
               }
            }
         } else {
            ajaxResult = AjaxResult.success("获取pass合理用药审查系统未启用");
         }
      } catch (Exception e) {
         this.log.error("获取pass合理用药审查接口参数出现异常：", e);
         ajaxResult = AjaxResult.error("获取pass合理用药审查接口参数出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('docOrder:order:add')")
   @Log(
      title = "获取pass合理用药患者基本信息接口参数",
      businessType = BusinessType.INSERT
   )
   @GetMapping({"/passParamForInfo"})
   public AjaxResult getPassVerifyParamForInfo(String patientId) {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         if (StringUtils.isNotBlank(patientId)) {
            CheckInfoVo checkInfoVo = this.passService.getCheckInfo_mk(patientId, (List)null, (HerbSaveVo)null);
            ajaxResult = AjaxResult.success("查询成功", checkInfoVo);
         } else {
            ajaxResult = AjaxResult.error("查询患者基本信息失败");
         }
      } catch (Exception e) {
         this.log.error("获取pass合理用药审查接口参数出现异常：", e);
         ajaxResult = AjaxResult.error("获取pass合理用药审查接口参数出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('docOrder:order:add')")
   @PostMapping
   public AjaxResult add(@RequestBody OrderSaveInfoVo orderSaveInfoVo, HttpServletRequest request) {
      AjaxResult ajaxResult = AjaxResult.success("Medical order input saved successfully");
      String patientId = null;
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();

      try {
         Boolean flag = true;
         if (orderSaveInfoVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空，请填写后再保存");
         }

         List<OrderSaveVo> orderSaveVoList = flag ? orderSaveInfoVo.getOrderSaveVoList() : null;
         TdPaOrderAgentVo tdPaOrderAgentVo = flag ? orderSaveInfoVo.getTdPaOrderAgentVo() : null;
         ajaxResult = flag ? this.verify(orderSaveVoList) : ajaxResult;
         flag = flag ? ajaxResult.get("code").equals(200) : flag;
         VisitinfoVo vparam = new VisitinfoVo();
         vparam.setPatientId(((OrderSaveVo)orderSaveVoList.get(0)).getPatientId());
         Visitinfo visitinfo = flag ? this.visitinfoService.selectPatientById(vparam) : null;
         if (flag && visitinfo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("查询不到患者信息，请刷新页面再操作");
         }

         if (flag && !visitinfo.getInpNo().equals(((OrderSaveVo)orderSaveVoList.get(0)).getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者信息不正确，请刷新页面再操作");
            SysUser user = SecurityUtils.getLoginUser().getUser();
            this.orderOperationLogService.addInpatientOrderOperationLog((String)null, (String)null, ((OrderSaveVo)orderSaveVoList.get(0)).getOrderGroupNo() + "", -5, "异常", "医嘱保存出现传入patientId和数据库查询出的admissionNo不一致，传入patientId:" + ((OrderSaveVo)orderSaveVoList.get(0)).getPatientId() + "，数据库查询出来的admissionNo:" + visitinfo.getInpNo(), user.getUserName(), user.getUserName(), user.getNickName());
         }

         boolean userHasPatFlag = flag ? this.commonService.userHasPat(visitinfo, sysUser) : flag;
         if (flag && !userHasPatFlag) {
            flag = false;
            ajaxResult = AjaxResult.error("只有患者所在科医生或者有临时授权的医生才能开立医嘱！");
         }

         patientId = flag ? ((OrderSaveVo)orderSaveVoList.get(0)).getPatientId() : null;
         if (flag) {
            String patientSaveOrderFlag = (String)this.redisCache.getCacheObject("patient_save_order:" + patientId);
            if (StringUtils.isNotBlank(patientSaveOrderFlag) && patientSaveOrderFlag.equals("1")) {
               flag = false;
               ajaxResult = AjaxResult.error("当前患者有正在保存医嘱的操作，请稍后再保存");
            } else {
               this.redisCache.setCacheObject("patient_save_order:" + patientId, "1");
            }
         }

         List<OrderSaveVo> orderSaveVoList23 = flag ? (List)orderSaveVoList.stream().filter((t) -> StringUtils.isNotBlank(t.getOrderClassCode()) && (t.getOrderClassCode().equals("02") || t.getOrderClassCode().equals("03"))).collect(Collectors.toList()) : null;
         if (flag && CollectionUtils.isNotEmpty(orderSaveVoList23)) {
            List<String> itemCdList = (List)orderSaveVoList23.stream().map((s) -> s.getCpNo()).collect(Collectors.toList());
            List<ClinItemDetail> itemDetailList = this.clinItemDetailService.selectClinItemDetailByItemCds(itemCdList);
            Map<String, List<ClinItemDetail>> detailMapList = (Map)itemDetailList.stream().collect(Collectors.groupingBy((s) -> s.getItemCd()));
            List<ClinItemMain> itemMains = this.clinItemMainService.selectClinItemMainByItemCds(itemCdList);
            Map<String, List<ClinItemMain>> mainMapList = (Map)itemMains.stream().collect(Collectors.groupingBy(ClinItemMain::getItemCd));

            for(OrderSaveVo item : orderSaveVoList) {
               String itemCd = item.getCpNo();
               List<ClinItemMain> mainList = (List)mainMapList.get(itemCd);
               if (mainList != null && !mainList.isEmpty()) {
                  ClinItemMain itemMain = (ClinItemMain)mainList.get(0);
                  if (StringUtils.isNotEmpty(itemMain.getItemFlagCd())) {
                     item.setOrderFlagCd(itemMain.getItemFlagCd());
                  }
               }
            }

            for(OrderSaveVo item : orderSaveVoList23) {
               String itemCd = item.getCpNo();
               List<ClinItemDetail> detailList = (List)detailMapList.get(itemCd);
               if (CollectionUtils.isEmpty(detailList)) {
                  flag = false;
                  ajaxResult = AjaxResult.error("检查检验类项目【" + item.getCpName() + "】计费信息不能为空，请联系管理员进行维护");
               }
            }

            if (flag) {
               ajaxResult = this.clinItemMainService.verifyExtendedAttri(patientId, orderSaveVoList23, (List)null, ajaxResult);
               boolean clinAgeFlag = ajaxResult.get("clinAgeFlag") != null ? (Boolean)ajaxResult.get("clinAgeFlag") : true;
               boolean specChangeMsg = ajaxResult.get("specChangeFlag") != null ? (Boolean)ajaxResult.get("specChangeFlag") : true;
               if (flag && !clinAgeFlag) {
                  flag = false;
                  ajaxResult = AjaxResult.error((String)ajaxResult.get("ageMsg"));
               }

               if (flag && !specChangeMsg) {
                  flag = false;
                  ajaxResult = AjaxResult.error((String)ajaxResult.get("specChangeMsg"));
               }
            }
         }

         if (flag) {
            String stockNumRes = this.tdPaOrderService.isStockNum(orderSaveVoList);
            if (StringUtils.isNotBlank(stockNumRes)) {
               flag = false;
               ajaxResult = AjaxResult.error(stockNumRes);
            }
         }

         String cdssFlag = flag ? this.sysEmrConfigService.selectSysEmrConfigByKey("0041") : null;
         String cdssFactory = flag ? this.sysEmrConfigService.selectSysEmrConfigByKey("0039") : null;
         if (flag && StringUtils.isNotBlank(cdssFlag) && cdssFlag.equals("1") && StringUtils.isNotBlank(cdssFactory) && cdssFactory.equals("LB")) {
            flag = this.checkCDSSService.checkCDSSBySex(patientId, orderSaveVoList);
            if (!flag) {
               ajaxResult = AjaxResult.error("当前患者医嘱内容与性别不符！");
            }
         }

         List<OrderSaveVo> orderSaveVoNoOpenList = flag ? (List)orderSaveVoList.stream().filter((t) -> StringUtils.isNotBlank(t.getIsOpenFlag()) && t.getIsOpenFlag().equals("0")).collect(Collectors.toList()) : null;
         if (orderSaveVoNoOpenList != null && !orderSaveVoNoOpenList.isEmpty()) {
            ItemIsOpenResVo vo = null;

            for(OrderSaveVo orderSaveVo : orderSaveVoNoOpenList) {
               DrugAndClin drugAndClinParam = new DrugAndClin();
               drugAndClinParam.setCpNo(orderSaveVo.getCpNo());
               drugAndClinParam.setPerformDepCode(orderSaveVo.getDetailPerformDepCode());
               DrugAndClin drugAndClin = this.drugAndClinService.selectDrugInfoByCd(drugAndClinParam);
               DrugAndClinPatientVo drugAndClinPatientVo = new DrugAndClinPatientVo();
               BeanUtils.copyProperties(drugAndClin, drugAndClinPatientVo);
               drugAndClinPatientVo.setPatientId(orderSaveVo.getPatientId());
               drugAndClinPatientVo.setOrderStartTime(orderSaveVo.getOrderStartTime());
               vo = this.tdPaOrderItemService.selectItemIsOpen(drugAndClinPatientVo);
               vo.setOrderSortNumber(orderSaveVo.getOrderSortNumber());
               if (vo.getWindowFlag().equals("1")) {
                  flag = false;
                  ajaxResult = AjaxResult.error("医嘱开立项目有业务弹窗，请处理后再保存！");
                  ajaxResult.put("ItemIsOpenResVo", vo);
                  break;
               }
            }
         }

         String prescriptionPrintFlag = "0";
         List<String> drugCdList = flag ? (List)orderSaveVoList.stream().filter((t) -> t.getOrderClassCode().equals("01")).map((t) -> t.getCpNo()).collect(Collectors.toList()) : null;
         List<DrugCheck> drugCheckList = null;
         List<OrderSaveVo> paList = null;
         if (flag && drugCdList != null && !drugCdList.isEmpty()) {
            drugCheckList = flag ? this.drugStockService.selectDrugCheckList(sysUser, drugCdList) : null;
            if (drugCheckList != null && !drugCheckList.isEmpty()) {
               List<DrugCheck> drugCheckList1 = (List)drugCheckList.stream().filter((t) -> t.getUseEnabled().equals("0") && t.getApplyFlag().equals("0")).collect(Collectors.toList());
               List<DrugCheck> drugCheckList2 = (List)drugCheckList.stream().filter((t) -> t.getDrugClassCode().equals("03")).collect(Collectors.toList());
               if (drugCheckList1 != null && !drugCheckList1.isEmpty()) {
                  flag = false;
                  ajaxResult = AjaxResult.error("药品【" + ((DrugCheck)drugCheckList1.get(0)).getDrugName() + "】不允许开立，请确认后再保存");
               }

               if (flag && drugCheckList2 != null && !drugCheckList2.isEmpty()) {
                  List<String> drugCodeList = (List)drugCheckList2.stream().map((t) -> t.getDrugCode()).collect(Collectors.toList());
                  paList = (List)orderSaveVoList.stream().filter((t) -> drugCodeList.contains(t.getCpNo())).collect(Collectors.toList());
                  ajaxResult = this.verifyAgent(tdPaOrderAgentVo, paList);
                  flag = flag ? ajaxResult.get("code").equals(200) : flag;
               }
            }
         }

         List<String> checkDrugCd1 = flag && drugCheckList != null ? (List)drugCheckList.stream().filter((t) -> t.getDrugClassCode().equals("01") && t.getUseEnabled().equals("0") && t.getApplyFlag().equals("1")).map((t) -> t.getDrugCode()).distinct().collect(Collectors.toList()) : null;
         List<OrderSaveVo> checkDrugOrderSaveList1 = flag && checkDrugCd1 != null && !checkDrugCd1.isEmpty() ? (List)orderSaveVoList.stream().filter((t) -> checkDrugCd1.contains(t.getCpNo())).collect(Collectors.toList()) : null;
         if (flag && checkDrugOrderSaveList1 != null && !checkDrugOrderSaveList1.isEmpty()) {
            List<DrAntiApply> drAntiApplyList = this.drAntiApplyService.selectByPatientAndDrugCode(new DrAntiApply(((OrderSaveVo)orderSaveVoList.get(0)).getPatientId(), (String)null, (String)null));
            List<String> applyedDrugCdList = (List)drAntiApplyList.stream().map((t) -> t.getDrugCode()).distinct().collect(Collectors.toList());
            List<OrderSaveVo> tempList = (List)checkDrugOrderSaveList1.stream().filter((t) -> !applyedDrugCdList.contains(t.getCpNo())).collect(Collectors.toList());
            if (tempList != null && !tempList.isEmpty()) {
               flag = false;
               ajaxResult = AjaxResult.error("药品【" + ((OrderSaveVo)tempList.get(0)).getCpName() + "】没有抗菌药物申请不能开立医嘱，请确认后再保存");
            }

            List<DrAntiApply> drAntiApplyList0 = flag ? (List)drAntiApplyList.stream().filter((t) -> !t.getState().equals("0")).collect(Collectors.toList()) : null;
            tempList = flag ? (List)checkDrugOrderSaveList1.stream().filter((t) -> drAntiApplyList0.contains(t.getCpNo()) && t.getOrderType().equals("1")).collect(Collectors.toList()) : null;
            if (flag && tempList != null && !tempList.isEmpty()) {
               flag = false;
               ajaxResult = AjaxResult.error("药品【" + ((OrderSaveVo)tempList.get(0)).getCpName() + "】抗菌药物申请没有审核，只能开立临时医嘱，请确认后再保存");
            }

            List<DrAntiApply> drAntiApplyList2 = flag ? (List)drAntiApplyList.stream().filter((t) -> !t.getState().equals("2")).collect(Collectors.toList()) : null;
            tempList = flag ? (List)checkDrugOrderSaveList1.stream().filter((t) -> drAntiApplyList2.contains(t.getCpNo()) && t.getOrderType().equals("1")).collect(Collectors.toList()) : null;
            if (flag && tempList != null && !tempList.isEmpty()) {
               flag = false;
               ajaxResult = AjaxResult.error("药品【" + ((OrderSaveVo)tempList.get(0)).getCpName() + "】抗菌药物申请审核没有通过，不能长期开立医嘱，请确认后再保存");
            }
         }

         List<String> checkDrugCd = flag && drugCheckList != null ? (List)drugCheckList.stream().filter((t) -> t.getDrugClassCode().equals("01")).map((t) -> t.getDrugCode()).distinct().collect(Collectors.toList()) : null;
         List<OrderSaveVo> checkDrugOrderSaveList = flag && checkDrugCd != null && !checkDrugCd.isEmpty() ? (List)orderSaveVoList.stream().filter((t) -> checkDrugCd.contains(t.getCpNo())).collect(Collectors.toList()) : null;
         checkDrugOrderSaveList = flag && checkDrugCd != null && !checkDrugCd.isEmpty() ? (List)checkDrugOrderSaveList.stream().filter((t) -> StringUtils.isBlank(t.getPurposeAntimicrobialUse())).collect(Collectors.toList()) : null;
         if (flag && drugCheckList != null && !drugCheckList.isEmpty() && checkDrugOrderSaveList != null && !checkDrugOrderSaveList.isEmpty()) {
            flag = false;
            ajaxResult = AjaxResult.error("抗菌药物必须写使用目的，请确认后再保存");
         }

         if (flag) {
            Integer count = this.clinItemMainService.selectCountByItemFlagCd("10");
            if (count != null && count > 0) {
               List<String> objectiveList = new ArrayList(2);
               objectiveList.add("1");
               List<OrderSaveVo> antiList = (List<OrderSaveVo>)(checkDrugCd != null ? (List)orderSaveVoList.stream().filter((t) -> checkDrugCd.contains(t.getCpNo())).collect(Collectors.toList()) : new ArrayList());
               if (!antiList.isEmpty()) {
                  List var87 = (List)antiList.stream().filter((t) -> objectiveList.contains(t.getPurposeAntimicrobialUse())).collect(Collectors.toList());
                  if (!var87.isEmpty()) {
                     OrderSaveVo orderSaveVo = (OrderSaveVo)var87.get(0);
                     String cpName = orderSaveVo.getCpName();
                     Integer culture = this.tdPaOrderItemService.selectCultureCount(patientId);
                     if (culture == 0) {
                        flag = false;
                        ajaxResult = AjaxResult.error("医嘱中存在以治疗性应用为目的抗菌药物【" + cpName + "】，但在医嘱中未检测到送检微生物样本的医嘱信息，请确认医嘱录入是否正确！");
                     }

                     if (flag) {
                        Integer countNum = this.tdPaOrderItemService.selectCultureCountBySubmitDate(patientId, orderSaveVo.getOrderStartTime());
                        if (countNum == 0) {
                           flag = false;
                           ajaxResult = AjaxResult.error("医嘱中存在以治疗性应用为目的抗菌药物【" + cpName + "】，但在医嘱中开立时间之前未检测到送检微生物样本的医嘱信息，请确认医嘱录入时间！");
                        }
                     }
                  }
               }
            }
         }

         List<OrderSaveVo> clinOrderList = (List<OrderSaveVo>)(flag ? (List)orderSaveVoList.stream().filter((t) -> !t.getOrderClassCode().equals("01")).collect(Collectors.toList()) : new ArrayList(1));
         if (flag && CollectionUtils.isNotEmpty(clinOrderList)) {
            List<String> cpNoList = (List)clinOrderList.stream().map((t) -> t.getCpNo()).distinct().collect(Collectors.toList());
            List<ClinItemMain> clinItemMainList = this.clinItemMainService.selectClinItemMainByItemCds(cpNoList);
            clinItemMainList = CollectionUtils.isNotEmpty(clinItemMainList) ? (List)clinItemMainList.stream().filter((t) -> t.getNumberLimit() != null && t.getNumberLimit() > 0).collect(Collectors.toList()) : null;
            if (CollectionUtils.isNotEmpty(clinItemMainList)) {
               cpNoList = (List)clinItemMainList.stream().map((t) -> t.getItemCd()).distinct().collect(Collectors.toList());
               TdPaOrderItemVo param = new TdPaOrderItemVo();
               param.setCpNoList(cpNoList);
               List<TdPaOrderItem> clinOrderItemList = this.tdPaOrderItemService.selectCpNoDayOrderList(param);
               Map<String, List<TdPaOrderItem>> clinOrderListMap = (Map<String, List<TdPaOrderItem>>)(CollectionUtils.isNotEmpty(clinOrderItemList) ? (Map)clinOrderItemList.stream().collect(Collectors.groupingBy((t) -> t.getCpNo())) : new HashMap(1));
               Map<String, ClinItemMain> clinItemMainMap = (Map<String, ClinItemMain>)(CollectionUtils.isNotEmpty(clinOrderItemList) ? (Map)clinItemMainList.stream().collect(Collectors.toMap((t) -> t.getItemCd(), Function.identity())) : new HashMap(1));
               Map<String, Integer> clinNumberMap = new HashMap(1);

               for(OrderSaveVo orderSaveVo : clinOrderList) {
                  if (cpNoList.contains(orderSaveVo.getCpNo())) {
                     ClinItemMain clinItemMain = (ClinItemMain)clinItemMainMap.get(orderSaveVo.getCpNo());
                     if (clinItemMain != null) {
                        Integer clinNumber = clinNumberMap.get(clinItemMain.getItemCd()) == null ? 0 : (Integer)clinNumberMap.get(clinItemMain.getItemCd());
                        List<TdPaOrderItem> tempList = (List)clinOrderListMap.get(clinItemMain.getItemCd());
                        if (CollectionUtils.isNotEmpty(tempList)) {
                           for(TdPaOrderItem orderItem : tempList) {
                              Integer clinNumber2 = orderItem.getOrderDoseItem().intValue();
                              clinNumber = clinNumber + clinNumber2;
                           }
                        }

                        clinNumber = clinNumber + orderSaveVo.getOrderTotalDose().intValue();
                        clinNumberMap.put(clinItemMain.getItemCd(), clinNumber);
                        if (clinItemMain.getNumberLimit().compareTo(clinNumber) <= 0) {
                           flag = false;
                           ajaxResult = AjaxResult.error("当前临床项目的每日开立数目不能超过" + clinItemMain.getNumberLimit());
                           break;
                        }
                     }
                  }
               }
            }
         }

         String ip = IPAddressUtil.getIPAddress(request);
         List<OrderSaveVo> nursingLevelOrderList = flag ? (List)orderSaveVoList.stream().filter((t) -> t.getOrderFlagCd().equals("08")).collect(Collectors.toList()) : null;
         if (flag && CollectionUtils.isNotEmpty(nursingLevelOrderList)) {
            List<TdPaOrderItemVo> nlOrderList = this.tdPaOrderItemService.selectPatientDoingNursingLevel(patientId);
            if (CollectionUtils.isNotEmpty(nlOrderList)) {
               flag = false;
               List<String> nlOrderGroupNoList = (List)nlOrderList.stream().map((t) -> t.getOrderGroupNo() + "").collect(Collectors.toList());
               String nlOrderGroupNo = String.join(",", nlOrderGroupNoList);
               ajaxResult = AjaxResult.error("当前患者有正在执行的护理级别医嘱，组号【" + nlOrderGroupNo + "】，请停止后再开立新的护理级别医嘱");
            }
         }

         if (flag) {
            OrderSaveResVo orderSaveResVo = new OrderSaveResVo();
            orderSaveVoList.stream().forEach((t) -> {
               t.setPerformComputerIp(ip);
               String babyAdmissionNo = t.getBabyAdmissionNo();
               babyAdmissionNo = StringUtils.isNotBlank(babyAdmissionNo) && babyAdmissionNo.equals(t.getPatientId()) ? null : babyAdmissionNo;
               t.setBabyAdmissionNo(babyAdmissionNo);
            });
            List<DrugDoseVo> drugDoseVoList = this.tdPaOrderService.saveTdPaOrder(orderSaveVoList, orderSaveResVo, drugCheckList, tdPaOrderAgentVo, visitinfo, ip, orderSaveInfoVo.getOperatingRoomFlag());

            try {
               if (!drugDoseVoList.isEmpty()) {
                  this.drugAndClinService.updateDurgXcsl(drugDoseVoList);
               }
            } catch (Exception e) {
               this.log.error("医嘱录入保存更新es药品数量出现异常", e);
            }

            this.redisCache.deleteObject("patient_save_order:" + patientId);
            List<String> orderFlagCdList = Arrays.asList("01", "02", "03");
            List<OrderSaveVo> orderSaveVoList1 = (List)orderSaveVoList.stream().filter((t) -> orderFlagCdList.contains(t.getOrderFlagCd())).collect(Collectors.toList());
            String stopOrderFlag = orderSaveVoList1 != null && !orderSaveVoList1.isEmpty() ? "1" : "0";
            List<String> windowCodeList = new ArrayList();
            if (prescriptionPrintFlag.equals("1")) {
               windowCodeList.add("01");
            }

            if (stopOrderFlag.equals("1")) {
               windowCodeList.add("02");
               String saveStopTit = null;
               OrderSaveVo orderSaveVo = (OrderSaveVo)orderSaveVoList1.get(0);
               switch (orderSaveVo.getOrderFlagCd()) {
                  case "01":
                     saveStopTit = "出院医嘱";
                     break;
                  case "02":
                     saveStopTit = "转科医嘱";
                     break;
                  case "03":
                     saveStopTit = "术后医嘱";
               }

               orderSaveResVo.setSaveStopTit(saveStopTit);
            }

            orderSaveResVo.setWindowFlag(windowCodeList.isEmpty() ? null : "1");
            orderSaveResVo.setWindowCodeList(windowCodeList);
            ajaxResult = AjaxResult.success("Medical order input saved successfully", orderSaveResVo);
            String config_0005001 = this.sysEmrConfigService.selectSysEmrConfigByKey("000501");
            if (StringUtils.isNotBlank(config_0005001) && "1".equals(config_0005001)) {
               List<String> orderNoList = (List)orderSaveVoList.stream().map((t) -> t.getOrderNo()).distinct().collect(Collectors.toList());
               MedicareAuditVo medicareAuditVo = this.medicareAuditService.getMedicareAuditWebApi(patientId, orderNoList);
               if (medicareAuditVo != null) {
                  ajaxResult.put("medicareAudit", medicareAuditVo);
               }
            }
         }

         this.redisCache.deleteObject("patient_save_order:" + patientId);
      } catch (Exception e) {
         this.log.error("医嘱录入保存出现异常：", e);
         this.redisCache.deleteObject("patient_save_order:" + patientId);
         ajaxResult = AjaxResult.error("医嘱录入保存出现异常");
      }

      return ajaxResult;
   }

   private AjaxResult verify(List orderSaveVoList) throws Exception {
      AjaxResult ajaxResult = AjaxResult.success("医嘱录入保存成功");
      Date dbDate = this.commonService.getDbSysdate();
      this.log.debug("=============医嘱录入保存=======dbDate======" + dbDate);
      this.log.debug("=============医嘱录入保存=======dbDate======" + DateUtils.dateFormat(dbDate, DateUtils.YYYY_MM_DD_HH_MM_SS));
      String orderStartTimePreStr = this.sysEmrConfigService.selectSysEmrConfigByKey("0081");
      Date orderStartTimePre = DateUtils.addMinutes(dbDate, -Integer.parseInt(orderStartTimePreStr));
      this.log.debug("=============医嘱录入保存=======orderStartTimePre======" + orderStartTimePre);
      this.log.debug("=============医嘱录入保存=======orderStartTimePre======" + DateUtils.dateFormat(orderStartTimePre, DateUtils.YYYY_MM_DD_HH_MM_SS));
      Boolean flag = true;
      if (orderSaveVoList == null || orderSaveVoList != null && orderSaveVoList.isEmpty()) {
         flag = false;
         ajaxResult = AjaxResult.error("医嘱集合不能为空，请填写后再保存");
      }

      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      List<PharmacyVo> pharmacyList = flag ? this.drugStockService.selectPharmacyListByDept(sysUser.getHospital().getOrgCode(), sysUser.getDept().getDeptCode()) : null;
      String distributionDeptCode = null;
      if (flag && pharmacyList != null && pharmacyList.size() > 0) {
         for(PharmacyVo pharmacy : pharmacyList) {
            String pharmacyFlag = pharmacy.getPharmacyFlag() != null ? pharmacy.getPharmacyFlag() : "0";
            if (pharmacyFlag.equals("1")) {
               distributionDeptCode = pharmacy.getPharmacyCode();
               break;
            }
         }
      }

      List<TdPaOrderItem> orderItemList = this.tdPaOrderItemService.selectByOrderFlagCdAndStatus(((OrderSaveVo)orderSaveVoList.get(0)).getPatientId(), "5", Arrays.asList("02"));
      TdPaOrderItem changeDeptOrder = null;
      if (CollectionUtils.isNotEmpty(orderItemList)) {
         changeDeptOrder = (TdPaOrderItem)orderItemList.get(0);
      }

      if (flag) {
         List<String> detailPerformDepCodeList = (List)orderSaveVoList.stream().filter((t) -> StringUtils.isNotBlank(t.getDetailPerformDepCode())).map((t) -> t.getDetailPerformDepCode()).collect(Collectors.toList());
         List<SysDept> detailPerformDepList = (List<SysDept>)(CollectionUtils.isNotEmpty(detailPerformDepCodeList) ? this.deptService.selectListByDeptCodeList(SecurityUtils.getLoginUser().getUser().getHospital().getOrgCode(), detailPerformDepCodeList) : new ArrayList(1));
         StringBuffer stringBuffer = new StringBuffer();
         Boolean partFlag = false;
         List<String> itemCdList = (List)orderSaveVoList.stream().map((t) -> t.getCpNo()).collect(Collectors.toList());
         List<ClinItemMain> clinItemMainList = this.clinItemMainService.selectClinItemMainByItemCds(itemCdList);
         Map<String, List<ClinItemMain>> stringListMap = (Map)clinItemMainList.stream().collect(Collectors.groupingBy(ClinItemMain::getItemCd));

         for(OrderSaveVo orderSaveVo : orderSaveVoList) {
            if ("02".equals(orderSaveVo.getOrderClassCode())) {
               ClinItemMain clinItemMain = (ClinItemMain)((List)stringListMap.get(orderSaveVo.getCpNo())).get(0);
               if (clinItemMain != null && StringUtils.isNotBlank(clinItemMain.getExamPartCd())) {
                  List<String> clinPartCdList = Arrays.asList(clinItemMain.getExamPartCd().split(","));
                  if (StringUtils.isNotBlank(orderSaveVo.getExamPartCd())) {
                     for(String partCd : Arrays.asList(orderSaveVo.getExamPartCd().split(","))) {
                        if (!clinPartCdList.contains(partCd)) {
                           partFlag = true;
                           stringBuffer.append(" " + orderSaveVo.getCpName() + "请选择部位:" + clinItemMain.getExamPartName());
                           stringBuffer.append(";");
                           break;
                        }
                     }
                  }
               }
            }

            if (flag && StringUtils.isBlank(orderSaveVo.getCpName())) {
               flag = false;
               ajaxResult = AjaxResult.error("医嘱内容不能为空，请填写后再保存");
               break;
            }

            if (flag && StringUtils.isBlank(orderSaveVo.getOrderType())) {
               flag = false;
               ajaxResult = AjaxResult.error("[" + orderSaveVo.getCpName() + "] 医嘱类型不能为空，请填写后再保存");
               break;
            }

            if (flag && orderSaveVo.getOrderStartTime() == null) {
               flag = false;
               ajaxResult = AjaxResult.error("[" + orderSaveVo.getCpName() + "] 医嘱开始时间不能为空，请填写后再保存");
               break;
            }

            if (!flag || changeDeptOrder == null || orderSaveVo.getOrderStartTime().compareTo(changeDeptOrder.getOrderStartTime()) >= 0 && orderSaveVo.getOrderStartTime().compareTo(changeDeptOrder.getOrderStopTime()) >= 0) {
               if (flag && StringUtils.isBlank(orderSaveVo.getOrderClassCode())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("[" + orderSaveVo.getCpName() + "] 医嘱类别不能为空，请填写后再保存");
                  break;
               }

               if (flag && StringUtils.isBlank(orderSaveVo.getCpNo())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("医嘱内容【" + orderSaveVo.getCpName() + "】的编码不能为空，请填写后再保存");
                  break;
               }

               if (flag && StringUtils.isBlank(orderSaveVo.getOrderSortNumber())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("[" + orderSaveVo.getCpName() + "] 医嘱序号不能为空，请填写后再保存");
                  break;
               }

               if (flag && orderSaveVo.getOrderGroupNo() == null) {
                  flag = false;
                  ajaxResult = AjaxResult.error("[" + orderSaveVo.getCpName() + "] 医嘱组号不能为空，请填写后再保存");
                  break;
               }

               if (flag && StringUtils.isBlank(orderSaveVo.getOrderGroupSortNumber())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("[" + orderSaveVo.getCpName() + "] 医嘱组内序号不能为空，请填写后再保存");
                  break;
               }

               if (flag && orderSaveVo.getOrderActualUsage() != null && orderSaveVo.getOrderActualUsage().compareTo(new BigDecimal("0")) <= 0) {
                  flag = false;
                  ajaxResult = AjaxResult.error("[" + orderSaveVo.getCpName() + "] 医嘱用量要大于0，请重新填写后再保存");
                  break;
               }

               if (flag && orderSaveVo.getOrderDose() == null) {
                  flag = false;
                  ajaxResult = AjaxResult.error("[" + orderSaveVo.getCpName() + "] 医嘱数量必须填写，请重新填写后再保存");
                  break;
               }

               if (flag && orderSaveVo.getOrderDose() != null && orderSaveVo.getOrderDose().compareTo(new BigDecimal("0")) <= 0) {
                  flag = false;
                  ajaxResult = AjaxResult.error("[" + orderSaveVo.getCpName() + "] 医嘱数量要大于0，请重新填写后再保存");
                  break;
               }

               if (flag && StringUtils.isBlank(orderSaveVo.getOrderFlagCd())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("[" + orderSaveVo.getCpName() + "] 医嘱标志不能为空，请重新填写后再保存");
                  break;
               }

               if (flag && StringUtils.isNotBlank(orderSaveVo.getDoctorInstructions()) && StringUtils.getStringLengthByByte(orderSaveVo.getDoctorInstructions()) > 100) {
                  flag = false;
                  ajaxResult = AjaxResult.error("[" + orderSaveVo.getCpName() + "] 医嘱说明请控制在50个汉字以内，请重新填写后再保存");
                  break;
               }

               if (flag && orderSaveVo.getOrderClassCode().equals("01") && !orderSaveVo.getCpNo().equals("NNNNNN")) {
                  if (flag && orderSaveVo.getOrderActualUsage() == null) {
                     flag = false;
                     ajaxResult = AjaxResult.error("[" + orderSaveVo.getCpName() + "] 医嘱用量不能为空，请填写后再保存");
                     break;
                  }

                  if (flag && orderSaveVo.getOrderActualUsage().compareTo(new BigDecimal("0")) <= 0) {
                     flag = false;
                     ajaxResult = AjaxResult.error("[" + orderSaveVo.getCpName() + "] 医嘱用量要大于0，请重新填写后再保存");
                     break;
                  }

                  if (flag && StringUtils.isBlank(orderSaveVo.getUsageUnit())) {
                     flag = false;
                     ajaxResult = AjaxResult.error("[" + orderSaveVo.getCpName() + "] 医嘱用量单位不能为空，请填写后再保存");
                     break;
                  }

                  if (flag && StringUtils.isBlank(orderSaveVo.getOrderUsageWay())) {
                     flag = false;
                     ajaxResult = AjaxResult.error("[" + orderSaveVo.getCpName() + "] 医嘱用法不能为空，请填写后再保存");
                     break;
                  }

                  if (flag && StringUtils.isBlank(orderSaveVo.getOrderFreq())) {
                     flag = false;
                     ajaxResult = AjaxResult.error("[" + orderSaveVo.getCpName() + "] 医嘱频率不能为空，请填写后再保存");
                     break;
                  }

                  if (flag && orderSaveVo.getOrderDose() == null) {
                     flag = false;
                     ajaxResult = AjaxResult.error("[" + orderSaveVo.getCpName() + "] 医嘱数量不能为空，请填写后再保存");
                     break;
                  }

                  if (flag && orderSaveVo.getOrderDose().compareTo(new BigDecimal("0")) <= 0) {
                     flag = false;
                     ajaxResult = AjaxResult.error("[" + orderSaveVo.getCpName() + "] 医嘱数量要大于0，请重新填写后再保存");
                     break;
                  }

                  if (flag && orderSaveVo.getOrderType().equals("1")) {
                  }

                  if (flag && StringUtils.isBlank(orderSaveVo.getUnit())) {
                     flag = false;
                     ajaxResult = AjaxResult.error("[" + orderSaveVo.getCpName() + "] 医嘱单位不能为空，请填写后再保存");
                     break;
                  }

                  if (flag && orderSaveVo.getOrderClassCode().equals("01") && StringUtils.isBlank(orderSaveVo.getTakeDrugMode())) {
                     flag = false;
                     ajaxResult = AjaxResult.error("[" + orderSaveVo.getCpName() + "] 医嘱领药方式不能为空，请填写后再保存");
                     break;
                  }

                  String detailPerformDepCode = flag && StringUtils.isNotBlank(orderSaveVo.getDetailPerformDepCode()) ? orderSaveVo.getDetailPerformDepCode() : "";
                  String orderItemFlag = flag && StringUtils.isNotBlank(orderSaveVo.getOrderItemFlag()) ? orderSaveVo.getOrderItemFlag() : "";
                  if (flag && orderItemFlag.equals("6") && !detailPerformDepCode.equals(distributionDeptCode)) {
                     flag = false;
                     ajaxResult = AjaxResult.error("[" + orderSaveVo.getCpName() + "] 医嘱特殊标志是配送，药房只能选择配送药房，请重新填写后再保存");
                     break;
                  }

                  if (flag && detailPerformDepCode.equals(distributionDeptCode) && !orderItemFlag.equals("6")) {
                     flag = false;
                     ajaxResult = AjaxResult.error("[" + orderSaveVo.getCpName() + "] 药房是配送药房，医嘱特殊标志只能选择配送，请重新填写后再保存");
                     break;
                  }

                  if (flag && orderItemFlag.equals("4") && orderSaveVo.getOrderUsageDays() != null && orderSaveVo.getOrderUsageDays().compareTo(BigDecimal.ONE) < 0) {
                     flag = false;
                     ajaxResult = AjaxResult.error("[" + orderSaveVo.getCpName() + "] 医嘱特殊标志是特殊，用药天数必须填写，且天数必须大于等于1");
                     break;
                  }

                  if (flag && orderItemFlag.equals("4") && orderSaveVo.getTakeDrugMode().equals("2")) {
                     flag = false;
                     ajaxResult = AjaxResult.error("[" + orderSaveVo.getCpName() + "] 医嘱特殊标志是特殊，此组医嘱的领药方式必须是【当天领药】，请修改后再保存");
                     break;
                  }
               }

               if (flag && orderSaveVo.getOrderClassCode().equals("02") && StringUtils.isBlank(orderSaveVo.getExamPartCd())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("[" + orderSaveVo.getCpName() + "] 检查项目检查部位不能为空，请填写后再保存");
                  break;
               }

               if (flag && orderSaveVo.getOrderClassCode().equals("03") && StringUtils.isBlank(orderSaveVo.getSpecCd())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("[" + orderSaveVo.getCpName() + "] 检验项目标本不能为空，请填写后再保存");
                  break;
               }

               if (flag && (orderSaveVo.getOrderClassCode().equals("03") || orderSaveVo.getOrderClassCode().equals("02"))) {
                  if (flag && StringUtils.isBlank(orderSaveVo.getDocumentTypeNo())) {
                     flag = false;
                     ajaxResult = AjaxResult.error("[" + orderSaveVo.getCpName() + "] 检查检验项目单据类型不能为空，请填写后再保存");
                     break;
                  }

                  if (flag && StringUtils.isBlank(orderSaveVo.getDetailPerformDepCode())) {
                     flag = false;
                     ajaxResult = AjaxResult.error("[" + orderSaveVo.getCpName() + "] 检查检验医嘱的执行科室不能为空，联系管理员维护项目的执行科室。项目编码：" + orderSaveVo.getCpNo() + "项目名称：" + orderSaveVo.getCpName() + "；");
                     break;
                  }

                  List<SysDept> deptListTemp = flag ? (List)detailPerformDepList.stream().filter((t) -> t.getDeptCode().equals(orderSaveVo.getDetailPerformDepCode())).collect(Collectors.toList()) : null;
                  if (flag && CollectionUtils.isEmpty(deptListTemp)) {
                     flag = false;
                     ajaxResult = AjaxResult.error("[" + orderSaveVo.getCpName() + "] 检查检验医嘱的执行科室不正确，联系管理员检查项目的执行科室。项目编码：" + orderSaveVo.getCpNo() + "项目名称：" + orderSaveVo.getCpName() + "；");
                     break;
                  }
               }

               if (!flag || !StringUtils.isNotBlank(orderSaveVo.getOrderFlagCd()) || !orderSaveVo.getOrderFlagCd().equals("01") || !StringUtils.isBlank(orderSaveVo.getOutCon()) && orderSaveVo.getOutTime() != null) {
                  if (flag && (orderSaveVo.getOrderClassCode().equals("02") || orderSaveVo.getOrderClassCode().equals("03")) && StringUtils.isBlank(orderSaveVo.getDetailPerformDepCode())) {
                     flag = false;
                     ajaxResult = AjaxResult.error("[" + orderSaveVo.getCpName() + "] 检查检验医嘱的执行科室不能为空，联系管理员维护项目的执行科室。项目编码：" + orderSaveVo.getCpNo() + "项目名称：" + orderSaveVo.getCpName() + "；");
                     break;
                  }

                  String auditDepFlag = orderSaveVo.getAuditDepFlag();
                  String performDepFlag = orderSaveVo.getPerformDepFlag();
                  if (!flag || !StringUtils.isNotBlank(auditDepFlag) || !auditDepFlag.equals("1") || !StringUtils.isBlank(orderSaveVo.getAuditDepCode()) && !StringUtils.isBlank(orderSaveVo.getAuditDepName())) {
                     if (!flag || !StringUtils.isNotBlank(performDepFlag) || !performDepFlag.equals("1") || !StringUtils.isBlank(orderSaveVo.getPerformDepCode()) && !StringUtils.isBlank(orderSaveVo.getPerformDepName())) {
                        continue;
                     }

                     flag = false;
                     ajaxResult = AjaxResult.error("[" + orderSaveVo.getCpName() + "] 跨科执行医嘱，执行科室不能为空");
                     break;
                  }

                  flag = false;
                  ajaxResult = AjaxResult.error("[" + orderSaveVo.getCpName() + "] 跨科审核医嘱，审核科室不能为空");
                  break;
               }

               flag = false;
               ajaxResult = AjaxResult.error("[" + orderSaveVo.getCpName() + "] 出院医嘱出院日期和治疗效果不能为空，请填写后再保存");
               break;
            }

            flag = false;
            ajaxResult = AjaxResult.error("当前患者开立的有转科医嘱 [" + changeDeptOrder.getCpName() + "]，[" + orderSaveVo.getCpName() + "] 医嘱开立时间不能比转科医嘱的开立、停止时间晚，请修改后再保存");
            break;
         }

         if (partFlag) {
            flag = false;
            ajaxResult = AjaxResult.error("有不符合要求的部位：" + stringBuffer.toString());
         }
      }

      if (flag) {
         StringBuffer errorMsg = new StringBuffer("");
         StringBuffer errorMaxMsg = new StringBuffer("");
         List<OrderSaveVo> orderStartTimeErrorList = new ArrayList(1);
         List<OrderSaveVo> orderStartTimeMaxErrorList = new ArrayList(1);
         Date orderStartTimeMax = DateUtils.addDay((Date)dbDate, 1);

         for(OrderSaveVo orderSaveVo : orderSaveVoList) {
            this.log.debug("=============医嘱录入保存=======orderSaveVo.getOrderStartTime().compareTo(orderStartTimePre)======" + orderSaveVo.getOrderStartTime().compareTo(orderStartTimePre));
            if (flag && (StringUtils.isBlank(orderSaveVo.getAdditionalFlag()) || StringUtils.isNotBlank(orderSaveVo.getAdditionalFlag()) && orderSaveVo.getAdditionalFlag().equals("0")) && orderSaveVo.getOrderStartTime().compareTo(orderStartTimePre) < 0) {
               this.log.debug("=============医嘱录入保存=======orderSaveVo.getOrderStartTime().compareTo(orderStartTimePre)======" + orderSaveVo.getOrderStartTime().compareTo(orderStartTimePre));
               if (StringUtils.isBlank(errorMsg)) {
                  errorMsg.append("新开医嘱 ");
               }

               errorMsg.append("[");
               errorMsg.append(orderSaveVo.getCpName());
               errorMsg.append("]、");
               OrderSaveVo temp = new OrderSaveVo();
               temp.setOrderGroupNo(orderSaveVo.getOrderGroupNo());
               temp.setOrderGroupSortNumber(orderSaveVo.getOrderGroupSortNumber());
               orderStartTimeErrorList.add(temp);
            }

            if (flag && (StringUtils.isBlank(orderSaveVo.getAdditionalFlag()) || StringUtils.isNotBlank(orderSaveVo.getAdditionalFlag()) && orderSaveVo.getAdditionalFlag().equals("0")) && orderSaveVo.getOrderStartTime().compareTo(orderStartTimeMax) > 0) {
               if (StringUtils.isBlank(errorMaxMsg)) {
                  errorMaxMsg.append("新开医嘱 ");
               }

               errorMaxMsg.append("[");
               errorMaxMsg.append(orderSaveVo.getCpName());
               errorMaxMsg.append("]、");
               OrderSaveVo temp = new OrderSaveVo();
               temp.setOrderGroupNo(orderSaveVo.getOrderGroupNo());
               temp.setOrderGroupSortNumber(orderSaveVo.getOrderGroupSortNumber());
               orderStartTimeMaxErrorList.add(temp);
            }
         }

         String errorMsgStr = errorMsg.toString();
         if (CollectionUtils.isNotEmpty(orderStartTimeErrorList) && StringUtils.isNotBlank(errorMsgStr)) {
            errorMsgStr = errorMsgStr.substring(0, errorMsg.length() - 1);
            errorMsgStr = errorMsgStr + " 医嘱开始时间不能小于当前时间" + DateUtils.dateFormat(dbDate, DateUtils.YYYY_MM_DD_HH_MM) + "的" + orderStartTimePreStr + "分钟以内! 请修改后再保存";
            ajaxResult = AjaxResult.error(errorMsgStr);
            ajaxResult.put("orderStartTimeErrorList", orderStartTimeErrorList);
         }

         String errorMaxMsgStr = errorMaxMsg.toString();
         if (CollectionUtils.isNotEmpty(orderStartTimeMaxErrorList) && StringUtils.isNotBlank(errorMaxMsgStr)) {
            errorMaxMsgStr = errorMaxMsgStr.substring(0, errorMaxMsg.length() - 1);
            errorMaxMsgStr = errorMaxMsgStr + " 医嘱开始时间不能超过当前时间24小时! 请修改后再保存";
            ajaxResult = AjaxResult.error(errorMaxMsgStr);
            ajaxResult.put("orderStartTimeErrorList", orderStartTimeMaxErrorList);
         }
      }

      return ajaxResult;
   }

   private AjaxResult verifyAgent(TdPaOrderAgentVo tdPaOrderAgentVo, List paList) {
      AjaxResult ajaxResult = AjaxResult.success("医嘱录入保存成功");
      Boolean flag = true;
      if (flag && !paList.isEmpty()) {
         if (flag && tdPaOrderAgentVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("当前保存的药品中有毒麻药品，请填写代办人信息后再保存");
         }

         if (flag && StringUtils.isBlank(tdPaOrderAgentVo.getIdCard())) {
            flag = false;
            ajaxResult = AjaxResult.error("代办人信息中患者身份证号不能为空，请填写后再保存");
         }

         if (flag && StringUtils.isBlank(tdPaOrderAgentVo.getSexCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("代办人信息中患者性别不能为空，请填写后再保存");
         }

         if (flag && tdPaOrderAgentVo.getAge() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("代办人信息中患者年龄不能为空，请填写后再保存");
         }

         if (flag && StringUtils.isBlank(tdPaOrderAgentVo.getAgentName())) {
            flag = false;
            ajaxResult = AjaxResult.error("代办人信息中代办人名称不能为空，请填写后再保存");
         }

         if (flag && StringUtils.isBlank(tdPaOrderAgentVo.getAgentIdCard())) {
            flag = false;
            ajaxResult = AjaxResult.error("代办人信息中代办人身份证号不能为空，请填写后再保存");
         }

         if (flag && StringUtils.isBlank(tdPaOrderAgentVo.getAgentAge())) {
            flag = false;
            ajaxResult = AjaxResult.error("代办人信息中代办人年龄不能为空，请填写后再保存");
         }

         if (flag && StringUtils.isBlank(tdPaOrderAgentVo.getAgentSexCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("代办人信息中代办人性别不能为空，请填写后再保存");
         }

         if (flag && StringUtils.isBlank(tdPaOrderAgentVo.getRelaCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("代办人信息中代办人与患者关系不能为空，请填写后再保存");
         }
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('docOrder:order:edit')")
   @Log(
      title = "医嘱提交",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody OrderCommitVo orderCommitVo) {
      AjaxResult ajaxResult = AjaxResult.success("Medical order input submitted successfully");
      Boolean flag = true;
      List<String> orderNoHandlingList = null;

      try {
         if (flag && orderCommitVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空，请重新操作");
         }

         if (flag) {
            GrantOutDoctor grantOutDoctor = this.grantOutDoctorService.selectGrantOutDoctorBySubNo(SecurityUtils.getLoginUser().getUser().getUserName());
            if (grantOutDoctor != null) {
               flag = false;
               ajaxResult = AjaxResult.error("外来医师不能提交医嘱");
            }
         }

         if (flag && StringUtils.isBlank(orderCommitVo.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空，请重新操作");
         }

         if (flag) {
            OrderSearchVo queryParam = new OrderSearchVo();
            queryParam.setPatientId(orderCommitVo.getPatientId());
            queryParam.setCommitFlag("0");
            queryParam.setOrderStatus("-1");
            queryParam.setOrderDoctorDepCode(SecurityUtils.getLoginUser().getUser().getDept().getDeptCode());
            List<OrderSearchVo> list = this.tdPaOrderService.selectOrderSearchVoList(queryParam);
            List<OrderSearchVo> pageList = this.tdPaOrderService.selectSubOrderSearchVoList(list);
            List<OrderSearchVo> lcljRes = this.lcljInfoService.saveLcljOrderList(pageList, orderCommitVo.getOrderCpVaryList());
            if (CollectionUtils.isNotEmpty(lcljRes)) {
               flag = false;
               ajaxResult = AjaxResult.error("临床路径有变异，请填写变异信息后再提交！");
               ajaxResult.put("orderCpVaryList", lcljRes);
            }
         }

         String caFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0001");
         if (StringUtils.isNotBlank(caFlag) && caFlag.equals("1")) {
            if (flag && StringUtils.isBlank(orderCommitVo.getEncryptedInfo())) {
               flag = false;
               ajaxResult = AjaxResult.error("签名加密后密文不能为空，请重新操作");
            }

            if (flag && StringUtils.isBlank(orderCommitVo.getSignCertificate())) {
               flag = false;
               ajaxResult = AjaxResult.error("签名证书不能为空，请重新操作");
            }

            if (flag && StringUtils.isBlank(orderCommitVo.getSignedText())) {
               flag = false;
               ajaxResult = AjaxResult.error("签名明文不能为空，请重新操作");
            }

            if (flag && orderCommitVo.getSubmitTime() == null) {
               flag = false;
               ajaxResult = AjaxResult.error("提交时间不能为空，请重新操作");
            }
         }

         Visitinfo visitinfo = flag ? this.visitinfoService.selectVisitinfoById(orderCommitVo.getPatientId()) : null;
         if (flag && visitinfo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有这个患者记录，请重新操作");
         }

         List<String> orderNoList = flag ? orderCommitVo.getOrderNoList() : null;
         if (flag && orderNoList == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有待提交的医嘱记录，请重新操作");
         }

         List<TdPaOrderItem> orderItemList = flag ? this.tdPaOrderItemService.selectFromStatusByOrderNoList(orderNoList) : null;
         if (flag && (orderItemList == null || orderItemList != null && orderItemList.isEmpty())) {
            flag = false;
            ajaxResult = AjaxResult.error("没有待提交的医嘱记录，请重新操作");
         }

         if (flag) {
            List var10000 = (List)orderItemList.stream().filter((t) -> !t.getOrderStatus().equals("-1")).collect(Collectors.toList());
         } else {
            Object var33 = null;
         }

         List<TdPaOrderItem> orderItemList2 = flag ? (List)orderItemList.stream().filter((t) -> t.getOrderStatus().equals("-1")).collect(Collectors.toList()) : null;
         if (flag && (orderItemList2 == null || orderItemList2 != null && orderItemList2.isEmpty())) {
            flag = false;
            ajaxResult = AjaxResult.error("没有待提交的医嘱记录，请重新操作");
         }

         if (flag) {
            orderNoHandlingList = new ArrayList(1);

            for(String orderNo : (List)orderItemList2.stream().map((t) -> t.getOrderNo()).distinct().collect(Collectors.toList())) {
               List<TdPaOrderItem> orderItemsTemp = (List)orderItemList2.stream().filter((t) -> t.getOrderNo().equals(orderNo)).collect(Collectors.toList());
               String orderNoHandlingKey = "inpatientOrderNoHandling:" + orderNo;
               Integer orderNoHandling = (Integer)this.orderRedisCache.getCacheObject(orderNoHandlingKey);
               this.log.warn("============" + orderNoHandlingKey + " : " + orderNoHandling);
               if (orderNoHandling != null) {
                  String orderNoHandlingMsg = CommonUtils.getOrderNoHandlingMsg(Integer.valueOf(orderNoHandling));
                  flag = false;
                  ajaxResult = AjaxResult.error("医嘱[" + ((TdPaOrderItem)orderItemsTemp.get(0)).getOrderGroupNo() + "]：【" + ((TdPaOrderItem)orderItemsTemp.get(0)).getCpName() + "】" + orderNoHandlingMsg + ";");
                  break;
               }

               orderNoHandlingList.add(orderNo);
            }
         }

         if (flag) {
            if (CollectionUtils.isNotEmpty(orderNoHandlingList)) {
               for(String orderNo : orderNoHandlingList) {
                  String orderNoHandlingKey = "inpatientOrderNoHandling:" + orderNo;
                  this.orderRedisCache.setCacheObject(orderNoHandlingKey, 1);
               }
            }

            this.tdPaOrderService.commitTdPaOrders(orderCommitVo, orderItemList2, visitinfo);
            if (CollectionUtils.isNotEmpty(orderNoHandlingList)) {
               for(String orderNo : orderNoHandlingList) {
                  String orderNoHandlingKey = "inpatientOrderNoHandling:" + orderNo;
                  this.orderRedisCache.deleteObject(orderNoHandlingKey);
               }
            }
         }
      } catch (Exception e) {
         this.log.error("医嘱录入提交医嘱出现异常：", e);
         ajaxResult = AjaxResult.error("医嘱录入提交医嘱出现异常");
         if (CollectionUtils.isNotEmpty(orderNoHandlingList)) {
            for(String orderNo : orderNoHandlingList) {
               String orderNoHandlingKey = "inpatientOrderNoHandling:" + orderNo;
               this.orderRedisCache.deleteObject(orderNoHandlingKey);
            }
         }
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('docOrder:order:cancel')")
   @Log(
      title = "医嘱撤销提交",
      businessType = BusinessType.UPDATE
   )
   @PutMapping({"/cancel"})
   public AjaxResult cancelCommit(@RequestBody OrderCommitVo orderCommitVo) {
      AjaxResult ajaxResult = AjaxResult.success("医嘱录入医嘱撤销成功");
      Boolean flag = true;
      List<String> orderNoHandlingList = null;

      try {
         if (flag && orderCommitVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空，请重新操作");
         }

         if (flag) {
            GrantOutDoctor grantOutDoctor = this.grantOutDoctorService.selectGrantOutDoctorBySubNo(SecurityUtils.getLoginUser().getUser().getUserName());
            if (grantOutDoctor != null) {
               flag = false;
               ajaxResult = AjaxResult.error("外来医师不能撤销提交医嘱");
            }
         }

         if (flag && StringUtils.isBlank(orderCommitVo.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空，请重新操作");
         }

         Visitinfo visitinfo = flag ? this.visitinfoService.selectVisitinfoById(orderCommitVo.getPatientId()) : null;
         if (flag && visitinfo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有这个患者记录，请重新操作");
         }

         List<String> orderNoList = flag ? orderCommitVo.getOrderNoList() : null;
         List<OrderSearchVo> orderSearchList = flag ? orderCommitVo.getOrderList() : null;
         if (flag && CollectionUtils.isNotEmpty(orderNoList) && CollectionUtils.isNotEmpty(orderSearchList)) {
            flag = false;
            ajaxResult = AjaxResult.error("没有能撤销提交的医嘱记录，请选择状态是待审核的记录后再撤销");
         }

         List<TdPaOrderItem> orderItemList = null;
         if (flag) {
            orderNoList = CollectionUtils.isNotEmpty(orderNoList) ? orderNoList : (List)orderSearchList.stream().filter((t) -> StringUtils.isNotBlank(t.getOrderNo())).map((t) -> t.getOrderNo()).distinct().collect(Collectors.toList());
            orderItemList = this.tdPaOrderItemService.selectFromStatusByOrderNoList(orderNoList);
            if (CollectionUtils.isEmpty(orderItemList)) {
               flag = false;
               ajaxResult = AjaxResult.error("没有能撤销提交的医嘱记录，请选择状态是待审核的记录后再撤销");
            }
         }

         if (flag) {
            List var10000 = (List)orderItemList.stream().filter((t) -> !t.getOrderStatus().equals("0")).collect(Collectors.toList());
         } else {
            Object var40 = null;
         }

         List<TdPaOrderItem> orderItemList2 = flag ? (List)orderItemList.stream().filter((t) -> t.getOrderStatus().equals("0")).collect(Collectors.toList()) : null;
         if (flag && (orderItemList2 == null || orderItemList2 != null && orderItemList2.isEmpty())) {
            flag = false;
            ajaxResult = AjaxResult.error("没有能撤销提交的医嘱记录，请选择状态是待审核的记录后再撤销");
         }

         List<TdPaApplyForm> applyFormList = flag ? this.tdPaApplyFormService.selectListByOrderNos(orderNoList) : null;
         if (flag && applyFormList != null && !applyFormList.isEmpty()) {
            List<TdPaApplyForm> tempList = (List)applyFormList.stream().filter((t) -> !t.getApplyFormStatus().equals("1")).collect(Collectors.toList());
            if (tempList != null && !tempList.isEmpty()) {
               flag = false;
               ajaxResult = AjaxResult.error("医嘱对应的检查检验申请单的状态不是【待审核】，不能撤销提交，请重新选择后再操作");
            }
         }

         List<TdCaOperationApply> operationApplyList = flag ? this.tdCaOperationApplyService.selectListByOrderNos(orderNoList) : null;
         if (flag && operationApplyList != null && !operationApplyList.isEmpty()) {
            flag = false;
            ajaxResult = AjaxResult.error("手术申请生成的医嘱在手术界面撤销提交");
         }

         List<TdPaOrder> orderList = flag ? this.tdPaOrderService.selectByOrderNoList(orderNoList) : null;
         List<Long> caConsApplyIdList = flag && orderList != null ? (List)orderList.stream().filter((t) -> t.getOrderClassCode().equals("07") && StringUtils.isNotEmpty(t.getApplyFormNo())).map((t) -> Long.valueOf(t.getApplyFormNo())).collect(Collectors.toList()) : null;
         List<TdCaConsApply> caConsApplyList = flag ? this.tdCaConsApplyService.selectListByIds(caConsApplyIdList) : null;
         if (flag && caConsApplyList != null && !caConsApplyList.isEmpty()) {
            List<TdCaConsApply> tempList = (List)caConsApplyList.stream().filter((t) -> t.getState().equals("02")).collect(Collectors.toList());
            if (tempList != null && !tempList.isEmpty()) {
               flag = false;
               ajaxResult = AjaxResult.error("医嘱对应的会诊申请单的状态不是【待应邀】，不能撤销提交，请重新选择后再操作");
            }
         }

         if (flag) {
            orderNoHandlingList = new ArrayList(1);

            for(String orderNo : (List)orderItemList2.stream().map((t) -> t.getOrderNo()).distinct().collect(Collectors.toList())) {
               List<TdPaOrderItem> orderItemsTemp = (List)orderItemList2.stream().filter((t) -> t.getOrderNo().equals(orderNo)).collect(Collectors.toList());
               String orderNoHandlingKey = "inpatientOrderNoHandling:" + orderNo;
               Integer orderNoHandling = (Integer)this.orderRedisCache.getCacheObject(orderNoHandlingKey);
               this.log.warn("============" + orderNoHandlingKey + " : " + orderNoHandling);
               if (orderNoHandling != null) {
                  String orderNoHandlingMsg = CommonUtils.getOrderNoHandlingMsg(Integer.valueOf(orderNoHandling));
                  flag = false;
                  ajaxResult = AjaxResult.error("医嘱[" + ((TdPaOrderItem)orderItemsTemp.get(0)).getOrderGroupNo() + "]：【" + ((TdPaOrderItem)orderItemsTemp.get(0)).getCpName() + "】" + orderNoHandlingMsg + ";");
                  break;
               }

               orderNoHandlingList.add(orderNo);
            }
         }

         if (flag) {
            if (CollectionUtils.isNotEmpty(orderNoHandlingList)) {
               for(String orderNo : orderNoHandlingList) {
                  String orderNoHandlingKey = "inpatientOrderNoHandling:" + orderNo;
                  this.orderRedisCache.setCacheObject(orderNoHandlingKey, 0);
               }
            }

            this.tdPaOrderService.cancelCommitTdPaOrders(orderCommitVo, orderItemList2, visitinfo, orderCommitVo.getOperatorDesc(), applyFormList, operationApplyList, caConsApplyList);
            OrderSearchVo queryParam = new OrderSearchVo();
            queryParam.setPatientId(orderCommitVo.getPatientId());
            List<String> orderNoList2 = (List)orderItemList2.stream().map((t) -> t.getOrderNo()).distinct().collect(Collectors.toList());
            queryParam.setDistinctOrderNoList(orderNoList2);
            queryParam.setOrderClassCode01("01");
            queryParam.setMasterOrder("1");
            queryParam.setHerbalFlag("0");
            queryParam.setBabyAdmissionNo(orderCommitVo.getBabyAdmissionNo());
            List<OrderSearchVo> pageListMain = this.tdPaOrderService.selectOrderSearchVoList(queryParam);
            List<OrderSearchVo> pageListAll = this.tdPaOrderService.selectSubOrderSearchVoList(pageListMain);
            ajaxResult.put("cancelCommitList", pageListAll);
            if (CollectionUtils.isNotEmpty(orderNoHandlingList)) {
               for(String orderNo : orderNoHandlingList) {
                  String orderNoHandlingKey = "inpatientOrderNoHandling:" + orderNo;
                  this.orderRedisCache.deleteObject(orderNoHandlingKey);
               }
            }
         }
      } catch (Exception e) {
         if (CollectionUtils.isNotEmpty(orderNoHandlingList)) {
            for(String orderNo : orderNoHandlingList) {
               String orderNoHandlingKey = "inpatientOrderNoHandling:" + orderNo;
               this.orderRedisCache.deleteObject(orderNoHandlingKey);
            }
         }

         this.log.error("医嘱撤销提交医嘱出现异常：", e);
         ajaxResult = AjaxResult.error("医嘱撤销提交医嘱出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('docOrder:order:remove')")
   @Log(
      title = "主要记录 医嘱的主要信息",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/del"})
   public AjaxResult remove(@RequestBody List orderNoList, HttpServletRequest request) {
      AjaxResult ajaxResult = AjaxResult.success("删除成功");
      Boolean flag = true;
      List<String> orderNoHandlingList = null;

      try {
         String ip = IPAddressUtil.getIPAddress(request);
         if (orderNoList == null || orderNoList != null && orderNoList.isEmpty()) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         List<TdPaOrderItem> orderItemList = flag ? this.tdPaOrderItemService.selectFromStatusByOrderNoList(orderNoList) : null;
         if (flag && CollectionUtils.isEmpty(orderItemList)) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         List<TdPaOrderItem> orderItemList2 = flag ? (List)orderItemList.stream().filter((t) -> t.getOrderStatus().equals("-1")).collect(Collectors.toList()) : null;
         if (flag && CollectionUtils.isEmpty(orderItemList2)) {
            flag = false;
            ajaxResult = AjaxResult.error("只有新录和待提交的医嘱才能删除，请重新选择后再提交");
         }

         orderNoList = flag ? (List)orderItemList2.stream().map((t) -> t.getOrderNo()).collect(Collectors.toList()) : null;
         List<TdPaApplyForm> applyFormList = flag ? this.tdPaApplyFormService.selectListByOrderNos(orderNoList) : null;
         if (flag && applyFormList != null && !applyFormList.isEmpty()) {
            List<TdPaApplyForm> tempList = (List)applyFormList.stream().filter((t) -> !t.getApplyFormStatus().equals("0") && !t.getApplyFormStatus().equals("2")).collect(Collectors.toList());
            if (tempList != null && !tempList.isEmpty()) {
               flag = false;
               ajaxResult = AjaxResult.error("医嘱对应的检查检验申请单的状态不是【开立】，不能删除，请重新选择后再操作");
            }
         }

         List<TdCaOperationApply> operationApplyList = flag ? this.tdCaOperationApplyService.selectListByOrderNos(orderNoList) : null;
         if (flag && operationApplyList != null && !operationApplyList.isEmpty()) {
            List<TdCaOperationApply> tempList = (List)operationApplyList.stream().filter((t) -> !t.getStatus().equals("01") && !t.getStatus().equals("03")).collect(Collectors.toList());
            if (tempList != null && !tempList.isEmpty()) {
               flag = false;
               ajaxResult = AjaxResult.error("医嘱对应的手术申请单的状态不是【待提交(保存)】，不能删除，请重新选择后再操作");
            }
         }

         List<TdPaOrder> orderList = flag ? this.tdPaOrderService.selectByOrderNoList(orderNoList) : null;
         List<Long> caConsApplyIdList = flag && orderList != null ? (List)orderList.stream().filter((t) -> t.getOrderClassCode().equals("04")).map((t) -> Long.valueOf(t.getApplyFormNo())).collect(Collectors.toList()) : null;
         List<TdCaConsApply> caConsApplyList = flag ? this.tdCaConsApplyService.selectListByIds(caConsApplyIdList) : null;
         if (flag && caConsApplyList != null && !caConsApplyList.isEmpty()) {
            List<TdCaConsApply> tempList = (List)caConsApplyList.stream().filter((t) -> !t.getState().equals("01") && !t.getState().equals("05")).collect(Collectors.toList());
            if (tempList != null && !tempList.isEmpty()) {
               flag = false;
               ajaxResult = AjaxResult.error("医嘱对应的会诊申请单的状态不是【暂存】，不能删除，请重新选择后再操作");
            }
         }

         if (flag) {
            orderNoHandlingList = new ArrayList(1);

            for(String orderNo : (List)orderItemList2.stream().map((t) -> t.getOrderNo()).distinct().collect(Collectors.toList())) {
               List<TdPaOrderItem> orderItemsTemp = (List)orderItemList2.stream().filter((t) -> t.getOrderNo().equals(orderNo)).collect(Collectors.toList());
               String orderNoHandlingKey = "inpatientOrderNoHandling:" + orderNo;
               Integer orderNoHandling = (Integer)this.orderRedisCache.getCacheObject(orderNoHandlingKey);
               this.log.warn("============" + orderNoHandlingKey + " : " + orderNoHandling);
               if (orderNoHandling != null) {
                  String orderNoHandlingMsg = CommonUtils.getOrderNoHandlingMsg(Integer.valueOf(orderNoHandling));
                  ajaxResult = AjaxResult.error("医嘱[" + ((TdPaOrderItem)orderItemsTemp.get(0)).getOrderGroupNo() + "]：【" + ((TdPaOrderItem)orderItemsTemp.get(0)).getCpName() + "】" + orderNoHandlingMsg + ";");
                  flag = false;
                  break;
               }

               orderNoHandlingList.add(orderNo);
            }
         }

         if (flag) {
            if (CollectionUtils.isNotEmpty(orderNoHandlingList)) {
               for(String orderNo : orderNoHandlingList) {
                  String orderNoHandlingKey = "inpatientOrderNoHandling:" + orderNo;
                  this.orderRedisCache.setCacheObject(orderNoHandlingKey, -1);
               }
            }

            List<DrugDoseVo> drugDoseVoList = this.tdPaOrderService.deleteTdPaOrderByIds(orderNoList, applyFormList, operationApplyList, caConsApplyList);
            if (!drugDoseVoList.isEmpty()) {
               SysUser sysUser = SecurityUtils.getLoginUser().getUser();
               List<DrugDoseVo> drugDoseVos = this.drugStockService.updateDrugDoseByOrderDose(sysUser, drugDoseVoList, "0", ip);

               try {
                  this.drugAndClinService.updateDurgXcsl(drugDoseVos);
               } catch (Exception e) {
                  this.log.error("删除医嘱更新es药品数量出现异常：", e);
               }
            }

            if (CollectionUtils.isNotEmpty(orderNoHandlingList)) {
               for(String orderNo : orderNoHandlingList) {
                  String orderNoHandlingKey = "inpatientOrderNoHandling:" + orderNo;
                  this.orderRedisCache.deleteObject(orderNoHandlingKey);
               }
            }
         }
      } catch (Exception e) {
         ajaxResult = AjaxResult.error("删除医嘱出现异常");
         this.log.error("删除医嘱出现异常,", e);
         if (CollectionUtils.isNotEmpty(orderNoHandlingList)) {
            for(String orderNo : orderNoHandlingList) {
               String orderNoHandlingKey = "inpatientOrderNoHandling:" + orderNo;
               this.orderRedisCache.deleteObject(orderNoHandlingKey);
            }
         }
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('docOrder:order:list')")
   @GetMapping({"/dictList"})
   public AjaxResult dictList(TdPaOrder tdPaOrder) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (StringUtils.isEmpty(tdPaOrder.getPatientId())) {
            ajaxResult = AjaxResult.error("患者id不能为空");
         } else {
            Map<String, Object> list = this.tdPaOrderService.selectOrderDictList(tdPaOrder.getPatientId());
            TdPaOrderItemVo itemParam = new TdPaOrderItemVo();
            itemParam.setPatientId(tdPaOrder.getPatientId());
            List<TdPaOrderItem> itemList = this.tdPaOrderItemService.selectOrderItemAll(itemParam);
            if (CollectionUtils.isEmpty(itemList)) {
               WebServiceVteResp vteResp = this.vteInfoService.vteOrderVerify(tdPaOrder.getPatientId());
               if (vteResp != null && StringUtils.isNotBlank(vteResp.getCode()) && vteResp.getCode().equals("500")) {
                  list.put("vteOrderFlag", "1");
                  list.put("vteMsg", vteResp.getMessage());
               }
            }

            String dipFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("007402");
            list.put("dipFlag", dipFlag);
            String dipFactory = this.sysEmrConfigService.selectSysEmrConfigByKey("007401");
            list.put("dipFactory", dipFactory);
            String drgFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0139");
            list.put("drgFlag", drgFlag);
            ajaxResult = AjaxResult.success((Object)list);
         }
      } catch (Exception e) {
         ajaxResult = AjaxResult.error("医嘱列表字典表信息查询出现异常：");
         this.log.error("医嘱列表字典表信息查询出现异常：", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('docOrder:order:edit')")
   @Log(
      title = "医嘱取消",
      businessType = BusinessType.UPDATE
   )
   @PutMapping({"/orderCancel"})
   public AjaxResult orderCancel(@RequestBody OrderCommitVo orderCommitVo) {
      AjaxResult ajaxResult = AjaxResult.success("医嘱取消成功");
      Boolean flag = true;
      List<String> orderNoHandlingList = null;

      try {
         if (flag && orderCommitVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空，请重新操作");
         }

         if (flag) {
            GrantOutDoctor grantOutDoctor = this.grantOutDoctorService.selectGrantOutDoctorBySubNo(SecurityUtils.getLoginUser().getUser().getUserName());
            if (grantOutDoctor != null) {
               flag = false;
               ajaxResult = AjaxResult.error("外来医师不能取消医嘱");
            }
         }

         if (flag && StringUtils.isBlank(orderCommitVo.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空，请重新操作");
         }

         String caFlag = flag ? this.sysEmrConfigService.selectSysEmrConfigByKey("0001") : null;
         if (flag && StringUtils.isNotBlank(caFlag) && caFlag.equals("1")) {
            if (flag && StringUtils.isBlank(orderCommitVo.getEncryptedInfo())) {
               flag = false;
               ajaxResult = AjaxResult.error("签名加密后密文不能为空，请重新操作");
            }

            if (flag && StringUtils.isBlank(orderCommitVo.getSignCertificate())) {
               flag = false;
               ajaxResult = AjaxResult.error("签名证书不能为空，请重新操作");
            }

            if (flag && StringUtils.isBlank(orderCommitVo.getSignedText())) {
               flag = false;
               ajaxResult = AjaxResult.error("签名明文不能为空，请重新操作");
            }

            if (flag && orderCommitVo.getCancelTime() == null) {
               flag = false;
               ajaxResult = AjaxResult.error("取消时间不能为空，请重新操作");
            }
         }

         Visitinfo visitinfo = flag ? this.visitinfoService.selectVisitinfoById(orderCommitVo.getPatientId()) : null;
         if (flag && visitinfo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有这个患者记录，请重新操作");
         }

         List<String> orderNoList = flag ? orderCommitVo.getOrderNoList() : null;
         List<OrderSearchVo> orderList = flag ? orderCommitVo.getOrderList() : null;
         if (flag && orderNoList == null && orderList == null) {
            flag = false;
            ajaxResult = AjaxResult.error("医嘱记录不能为空，请重新操作");
         }

         orderNoList = flag && orderNoList != null && !orderNoList.isEmpty() ? orderNoList : (flag ? (List)orderList.stream().filter((t) -> StringUtils.isNotBlank(t.getOrderNo())).map((t) -> t.getOrderNo()).distinct().collect(Collectors.toList()) : null);
         List<TdPaOrderItem> orderItemList = flag ? this.tdPaOrderItemService.selectFromStatusByOrderNoList(orderNoList) : null;
         if (flag && (orderItemList == null || orderItemList != null && orderItemList.isEmpty())) {
            flag = false;
            ajaxResult = AjaxResult.error("没有能取消的医嘱记录，请重新操作");
         }

         List<TdPaOrderItem> orderItemList2 = flag ? (List)orderItemList.stream().filter((t) -> (t.getOrderStatus().equals("5") || t.getOrderStatus().equals("4")) && t.getOrderType().equals("1") || t.getOrderStatus().equals("8") && t.getOrderType().equals("2")).collect(Collectors.toList()) : null;
         if (flag && (orderItemList2 == null || orderItemList2 != null && orderItemList2.isEmpty())) {
            flag = false;
            ajaxResult = AjaxResult.error("没有能取消的医嘱记录，请选择【停止】或【停止确认】状态的长期医嘱或者【已执行】的临时医嘱");
         }

         if (flag) {
            orderNoHandlingList = new ArrayList(1);

            for(String orderNo : (List)orderItemList2.stream().map((t) -> t.getOrderNo()).distinct().collect(Collectors.toList())) {
               List<TdPaOrderItem> orderItemsTemp = (List)orderItemList2.stream().filter((t) -> t.getOrderNo().equals(orderNo)).collect(Collectors.toList());
               TdPaOrderItem orderItem = (TdPaOrderItem)orderItemsTemp.get(0);
               String orderNoHandlingKey = "inpatientOrderNoHandling:" + orderNo;
               Integer orderNoHandling = (Integer)this.orderRedisCache.getCacheObject(orderNoHandlingKey);
               if (orderNoHandling != null) {
                  String orderNoHandlingMsg = CommonUtils.getOrderNoHandlingMsg(Integer.valueOf(orderNoHandling));
                  ajaxResult = AjaxResult.error("医嘱[" + orderItem.getOrderGroupNo() + "]：【" + orderItem.getCpName() + "】" + orderNoHandlingMsg + ";");
                  flag = false;
                  break;
               }

               orderNoHandlingList.add(orderNo);
            }
         }

         if (flag) {
            if (CollectionUtils.isNotEmpty(orderNoHandlingList)) {
               for(String orderNo : orderNoHandlingList) {
                  String orderNoHandlingKey = "inpatientOrderNoHandling:" + orderNo;
                  this.orderRedisCache.setCacheObject(orderNoHandlingKey, 6);
               }
            }

            this.tdPaOrderService.cancelDoctorOrder(orderItemList2, visitinfo, orderCommitVo);
            if (CollectionUtils.isNotEmpty(orderNoHandlingList)) {
               for(String orderNo : orderNoHandlingList) {
                  String orderNoHandlingKey = "inpatientOrderNoHandling:" + orderNo;
                  this.orderRedisCache.deleteObject(orderNoHandlingKey);
               }
            }
         }
      } catch (Exception e) {
         if (CollectionUtils.isNotEmpty(orderNoHandlingList)) {
            for(String orderNo : orderNoHandlingList) {
               String orderNoHandlingKey = "inpatientOrderNoHandling:" + orderNo;
               this.orderRedisCache.deleteObject(orderNoHandlingKey);
            }
         }

         this.log.error("医嘱取消出现异常：", e);
         ajaxResult = AjaxResult.error("医嘱取消出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('docOrder:order:list')")
   @GetMapping({"/startTime"})
   public AjaxResult getStartTime() {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         Date startTime = this.commonService.getDbSysdate();
         String timeLimit = this.sysEmrConfigService.selectSysEmrConfigByKey("0081");
         String startTimeStr = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM, startTime);
         int startTimeInt = Integer.parseInt(timeLimit);
         Date before20Min = DateUtils.addMinutes(startTime, -startTimeInt);
         String beforeMinStr = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM, before20Min);
         ajaxResult = AjaxResult.success("查询成功", startTimeStr);
         ajaxResult.put("startTimeStr", startTimeStr);
         ajaxResult.put("beforeMinStr", beforeMinStr);
         ajaxResult.put("timeLimit", timeLimit);
      } catch (Exception e) {
         this.log.error("查询医嘱开始时间出现异常", e);
         ajaxResult = AjaxResult.error("查询医嘱开始时间出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('docOrder:order:stop')")
   @Log(
      title = "医嘱停止",
      businessType = BusinessType.UPDATE
   )
   @PostMapping({"/stop"})
   public AjaxResult stop(@RequestBody OrderStopSignVo orderStopSignVo, HttpServletRequest request) {
      AjaxResult ajaxResult = AjaxResult.success();
      Boolean flag = true;
      List<String> orderNoHandlingList = null;

      try {
         if (flag && orderStopSignVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag) {
            GrantOutDoctor grantOutDoctor = this.grantOutDoctorService.selectGrantOutDoctorBySubNo(SecurityUtils.getLoginUser().getUser().getUserName());
            if (grantOutDoctor != null) {
               flag = false;
               ajaxResult = AjaxResult.error("外来医师不能停止医嘱");
            }
         }

         List<OrderSearchVo> orderList = flag ? orderStopSignVo.getOrderList() : null;
         if (flag && (orderList == null || orderList != null && orderList.isEmpty())) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         String caFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0001");
         if (StringUtils.isNotBlank(caFlag) && caFlag.equals("1")) {
            if (flag && StringUtils.isBlank(orderStopSignVo.getEncryptedInfo())) {
               flag = false;
               ajaxResult = AjaxResult.error("签名加密后密文不能为空，请重新操作");
            }

            if (flag && StringUtils.isBlank(orderStopSignVo.getSignCertificate())) {
               flag = false;
               ajaxResult = AjaxResult.error("签名证书不能为空，请重新操作");
            }

            if (flag && StringUtils.isBlank(orderStopSignVo.getSignedText())) {
               flag = false;
               ajaxResult = AjaxResult.error("签名明文不能为空，请重新操作");
            }
         }

         List<String> orderNoList = flag ? (List)orderList.stream().map((t) -> t.getOrderNo()).collect(Collectors.toList()) : null;
         List<String> orderNoNullList = flag ? (List)orderNoList.stream().filter((t) -> StringUtils.isBlank(t)).collect(Collectors.toList()) : null;
         if (flag && orderNoNullList == null) {
            flag = false;
            ajaxResult = AjaxResult.error("医嘱编码不能为空，请确认后再停止");
         }

         if (flag && orderStopSignVo.getStopTime() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("医嘱停止时间不能为空，请确认后再停止");
         }

         List<TdPaOrderItem> orderItemList = flag ? this.tdPaOrderItemService.selectFromStatusByOrderNoList(orderNoList) : null;
         List<String> patientIds = flag ? (List)orderItemList.stream().map((t) -> t.getPatientId()).distinct().collect(Collectors.toList()) : null;
         if (flag && patientIds != null && patientIds.size() > 1) {
            flag = false;
            ajaxResult = AjaxResult.error("要停止的医嘱有其他患者的医嘱，请确认后再停止");
         }

         List<TdPaOrderItem> orderItemList2 = new ArrayList(1);
         if (flag) {
            List<TdPaOrderItem> orderItemLongList = (List)orderItemList.stream().filter((t) -> t.getOrderType().equals("1") && t.getOrderStatus().equals("3")).collect(Collectors.toList());
            orderItemList2.addAll(orderItemLongList);
         }

         if (flag && orderStopSignVo.getOrderStopVoList() == null) {
            List<TdPaOrderItem> orderItemListTemp = new ArrayList(1);
            List<OrderStopVo> orderStopVoList = new ArrayList(orderItemList2.size());
            Map<String, List<OrderSearchVo>> orderListMap = (Map)orderList.stream().collect(Collectors.groupingBy((t) -> t.getOrderNo()));

            for(TdPaOrderItem orderItem : orderItemList2) {
               OrderStopVo orderStopVo = new OrderStopVo();
               orderStopVo.setOrderNo(orderItem.getOrderNo());
               Date stopTime = orderStopSignVo.getStopTime();
               orderItemListTemp.add(orderItem);
               orderStopVo.setStopTime(stopTime);
               orderStopVoList.add(orderStopVo);
               orderItem.setOrderStopTime(stopTime);
            }

            orderItemList2 = orderItemListTemp;
            orderStopSignVo.setOrderStopVoList(orderStopVoList);
         }

         if (flag && orderItemList2.isEmpty()) {
            flag = false;
            ajaxResult = AjaxResult.error("没有可停止的医嘱，请确认后再停止，只有长期在执行的医嘱可以停止。");
         }

         if (flag) {
            orderNoHandlingList = new ArrayList(1);

            for(String orderNo : (List)orderItemList2.stream().map((t) -> t.getOrderNo()).distinct().collect(Collectors.toList())) {
               List<TdPaOrderItem> orderItemsTemp = (List)orderItemList2.stream().filter((t) -> t.getOrderNo().equals(orderNo)).collect(Collectors.toList());
               TdPaOrderItem orderItem = (TdPaOrderItem)orderItemsTemp.get(0);
               String orderNoHandlingKey = "inpatientOrderNoHandling:" + orderNo;
               Integer orderNoHandling = (Integer)this.orderRedisCache.getCacheObject(orderNoHandlingKey);
               if (orderNoHandling != null) {
                  String orderNoHandlingMsg = CommonUtils.getOrderNoHandlingMsg(Integer.valueOf(orderNoHandling));
                  ajaxResult = AjaxResult.error("医嘱[" + orderItem.getOrderGroupNo() + "]：【" + orderItem.getCpName() + "】" + orderNoHandlingMsg + ";");
                  flag = false;
                  break;
               }

               orderNoHandlingList.add(orderNo);
            }
         }

         if (flag) {
            if (CollectionUtils.isNotEmpty(orderNoHandlingList)) {
               for(String orderNo : orderNoHandlingList) {
                  String orderNoHandlingKey = "inpatientOrderNoHandling:" + orderNo;
                  this.orderRedisCache.setCacheObject(orderNoHandlingKey, 4);
               }
            }

            boolean returnFlag = false;
            OrderStopDoReturnVo returnVo = null;

            try {
               this.log.info("医嘱停止开始");
               String ip = IpUtils.getIpAddr(request);
               this.tdPaOrderDetailService.stopOrder(orderStopSignVo, orderItemList2, ip);
               ajaxResult = AjaxResult.success("医嘱停止成功");
               returnFlag = true;
            } catch (Exception e) {
               this.log.error("医嘱停止出现异常", e);
               ajaxResult = AjaxResult.error("医嘱停止出现异常");
               returnFlag = false;
            }

            if (CollectionUtils.isNotEmpty(orderNoHandlingList)) {
               for(String orderNo : orderNoHandlingList) {
                  String orderNoHandlingKey = "inpatientOrderNoHandling:" + orderNo;
                  this.orderRedisCache.deleteObject(orderNoHandlingKey);
               }
            }
         }
      } catch (Exception e) {
         if (CollectionUtils.isNotEmpty(orderNoHandlingList)) {
            for(String orderNo : orderNoHandlingList) {
               String orderNoHandlingKey = "inpatientOrderNoHandling:" + orderNo;
               this.orderRedisCache.deleteObject(orderNoHandlingKey);
            }
         }

         this.log.error("医嘱停止出现异常", e);
         ajaxResult = AjaxResult.error("医嘱停止出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('docOrder:order:stopAll')")
   @Log(
      title = "医嘱全停",
      businessType = BusinessType.UPDATE
   )
   @PostMapping({"/stopAll"})
   public AjaxResult stopAll(@RequestBody OrderStopSignVo orderStopSignVo, HttpServletRequest request) {
      AjaxResult ajaxResult = AjaxResult.success();
      Boolean flag = true;
      List<String> orderNoHandlingList = null;

      try {
         if (flag && orderStopSignVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(orderStopSignVo.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag) {
            GrantOutDoctor grantOutDoctor = this.grantOutDoctorService.selectGrantOutDoctorBySubNo(SecurityUtils.getLoginUser().getUser().getUserName());
            if (grantOutDoctor != null) {
               flag = false;
               ajaxResult = AjaxResult.error("外来医师不能全停医嘱");
            }
         }

         String caFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0001");
         if (StringUtils.isNotBlank(caFlag) && caFlag.equals("1")) {
            if (flag && StringUtils.isBlank(orderStopSignVo.getEncryptedInfo())) {
               flag = false;
               ajaxResult = AjaxResult.error("签名加密后密文不能为空，请重新操作");
            }

            if (flag && StringUtils.isBlank(orderStopSignVo.getSignCertificate())) {
               flag = false;
               ajaxResult = AjaxResult.error("签名证书不能为空，请重新操作");
            }

            if (flag && StringUtils.isBlank(orderStopSignVo.getSignedText())) {
               flag = false;
               ajaxResult = AjaxResult.error("签名明文不能为空，请重新操作");
            }

            if (flag && StringUtils.isBlank(orderStopSignVo.getSignedText())) {
               flag = false;
               ajaxResult = AjaxResult.error("停止时间不能为空，请重新操作");
            }
         }

         Visitinfo visitinfo = flag ? this.visitinfoService.selectVisitinfoById(orderStopSignVo.getPatientId()) : null;
         if (flag && visitinfo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有这个患者的信息，请确认后再操作");
         }

         List<TdPaOrderItem> orderItemList = flag ? this.tdPaOrderDetailService.getStopAllOrderList(visitinfo) : null;
         if (flag) {
            orderNoHandlingList = new ArrayList(1);

            for(String orderNo : (List)orderItemList.stream().map((t) -> t.getOrderNo()).distinct().collect(Collectors.toList())) {
               List<TdPaOrderItem> orderItemsTemp = (List)orderItemList.stream().filter((t) -> t.getOrderNo().equals(orderNo)).collect(Collectors.toList());
               String orderNoHandlingKey = "inpatientOrderNoHandling:" + orderNo;
               Integer orderNoHandling = (Integer)this.orderRedisCache.getCacheObject(orderNoHandlingKey);
               this.log.warn("============" + orderNoHandlingKey + " : " + orderNoHandling);
               if (orderNoHandling != null) {
                  String orderNoHandlingMsg = CommonUtils.getOrderNoHandlingMsg(Integer.valueOf(orderNoHandling));
                  ajaxResult = AjaxResult.error("医嘱[" + ((TdPaOrderItem)orderItemsTemp.get(0)).getOrderGroupNo() + "]：【" + ((TdPaOrderItem)orderItemsTemp.get(0)).getCpName() + "】" + orderNoHandlingMsg + ";");
                  flag = false;
                  break;
               }

               orderNoHandlingList.add(orderNo);
            }
         }

         if (flag) {
            if (CollectionUtils.isNotEmpty(orderNoHandlingList)) {
               for(String orderNo : orderNoHandlingList) {
                  String orderNoHandlingKey = "inpatientOrderNoHandling:" + orderNo;
                  this.orderRedisCache.setCacheObject(orderNoHandlingKey, 4);
               }
            }

            boolean returnFlag = false;
            OrderStopDoReturnVo returnVo = null;

            try {
               this.log.info("医嘱停止开始");
               String ip = IpUtils.getIpAddr(request);
               this.tdPaOrderDetailService.stopAllOrder(visitinfo, orderStopSignVo, orderItemList, ip);
               ajaxResult = AjaxResult.success("医嘱停止成功");
            } catch (Exception e) {
               this.log.error("医嘱停止出现异常", e);
               ajaxResult = AjaxResult.error("医嘱停止出现异常");
               returnFlag = false;
            }

            if (CollectionUtils.isNotEmpty(orderNoHandlingList)) {
               for(String orderNo : orderNoHandlingList) {
                  String orderNoHandlingKey = "inpatientOrderNoHandling:" + orderNo;
                  this.orderRedisCache.deleteObject(orderNoHandlingKey);
               }
            }
         }
      } catch (Exception e) {
         this.log.error("医嘱全停出现异常", e);
         ajaxResult = AjaxResult.error("医嘱全停出现异常");
         if (CollectionUtils.isNotEmpty(orderNoHandlingList)) {
            for(String orderNo : orderNoHandlingList) {
               String orderNoHandlingKey = "inpatientOrderNoHandling:" + orderNo;
               this.orderRedisCache.deleteObject(orderNoHandlingKey);
            }
         }
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('docOrder:order:rearrange')")
   @PostMapping({"/rearrange"})
   public AjaxResult rearrange(String patientId, HttpServletRequest request) {
      AjaxResult ajaxResult = AjaxResult.success();
      Boolean flag = true;
      List<String> orderNoHandlingList = null;
      Boolean orderNoHandlingFlag = null;
      String patientSaveOrderKey = null;
      Boolean patientSaveOrderKeyFlag = null;

      try {
         if (flag && StringUtils.isBlank(patientId)) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         Visitinfo visitinfo = flag ? this.visitinfoService.selectVisitinfoById(patientId) : null;
         if (flag && visitinfo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有这个患者的信息，请确认后再操作");
         }

         if (flag) {
            patientSaveOrderKey = "patient_save_order:" + patientId;
            String patientSaveOrderFlag = (String)this.redisCache.getCacheObject(patientSaveOrderKey);
            if (StringUtils.isNotBlank(patientSaveOrderFlag) && patientSaveOrderFlag.equals("1")) {
               flag = false;
               ajaxResult = AjaxResult.error("当前患者有正在保存医嘱的操作，请稍后再重整");
            } else {
               this.redisCache.setCacheObject("patient_save_order:" + patientId, "1");
               patientSaveOrderKeyFlag = true;
            }
         }

         TdPaOrderItem outOrderParam = new TdPaOrderItem(patientId, "01");
         List<String> statusList = Arrays.asList("0", "1", "2", "3", "4", "5");
         List<TdPaOrderItem> outOrderList = this.tdPaOrderItemService.selectPatientOutOrder(outOrderParam, statusList);
         if (outOrderList != null && !outOrderList.isEmpty()) {
            flag = false;
            ajaxResult = AjaxResult.error(MessageUtils.message("doctor.order.item.canRearrange.outHos"));
         }

         BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
         if (flag && !visitinfo.getResDocCd().equals(basEmployee.getEmplNumber())) {
            flag = false;
            ajaxResult = AjaxResult.error("只有患者的管床医师" + (StringUtils.isNotBlank(visitinfo.getResDocName()) ? "【" + visitinfo.getResDocName() + "(" + visitinfo.getResDocCd() + ")】" : "") + "才可以重整医嘱，请确认后再操作");
         }

         TdPaOrderItemVo param = new TdPaOrderItemVo();
         param.setOrderType("1");
         List<String> stateList = new ArrayList();
         stateList.add("0");
         stateList.add("1");
         stateList.add("2");
         stateList.add("3");
         stateList.add("10");
         param.setStateList(stateList);
         param.setPatientId(patientId);
         List<TdPaOrderItem> orderItemList = flag ? this.tdPaOrderItemService.selectByOrderTypeAndStatus(param) : null;
         if (flag && (orderItemList == null || orderItemList != null && orderItemList.isEmpty())) {
            flag = false;
            ajaxResult = AjaxResult.error("当前患者没有长期医嘱，不需要重整医嘱");
         }

         if (flag) {
            orderNoHandlingList = new ArrayList(1);

            for(String orderNo : (List)orderItemList.stream().map((t) -> t.getOrderNo()).distinct().collect(Collectors.toList())) {
               List<TdPaOrderItem> orderItemsTemp = (List)orderItemList.stream().filter((t) -> t.getOrderNo().equals(orderNo)).collect(Collectors.toList());
               String orderNoHandlingKey = "inpatientOrderNoHandling:" + orderNo;
               Integer orderNoHandling = (Integer)this.orderRedisCache.getCacheObject(orderNoHandlingKey);
               this.log.warn("============" + orderNoHandlingKey + " : " + orderNoHandling);
               if (orderNoHandling != null) {
                  String orderNoHandlingMsg = CommonUtils.getOrderNoHandlingMsg(Integer.valueOf(orderNoHandling));
                  ajaxResult = AjaxResult.error("医嘱[" + ((TdPaOrderItem)orderItemsTemp.get(0)).getOrderGroupNo() + "]：【" + ((TdPaOrderItem)orderItemsTemp.get(0)).getCpName() + "】" + orderNoHandlingMsg + ";");
                  flag = false;
                  break;
               }

               orderNoHandlingList.add(orderNo);
            }
         }

         if (flag) {
            if (CollectionUtils.isNotEmpty(orderNoHandlingList)) {
               for(String orderNo : orderNoHandlingList) {
                  String orderNoHandlingKey = "inpatientOrderNoHandling:" + orderNo;
                  this.orderRedisCache.setCacheObject(orderNoHandlingKey, 5);
               }

               orderNoHandlingFlag = true;
            }

            String ip = IPAddressUtil.getIPAddress(request);
            this.tdPaOrderDetailService.rearrange(visitinfo, orderItemList, ip);
            if (CollectionUtils.isNotEmpty(orderNoHandlingList) && orderNoHandlingFlag != null && orderNoHandlingFlag) {
               for(String orderNo : orderNoHandlingList) {
                  String orderNoHandlingKey = "inpatientOrderNoHandling:" + orderNo;
                  this.orderRedisCache.deleteObject(orderNoHandlingKey);
               }
            }
         }

         if (StringUtils.isNotBlank(patientSaveOrderKey) && patientSaveOrderKeyFlag != null && patientSaveOrderKeyFlag) {
            this.redisCache.deleteObject(patientSaveOrderKey);
         }
      } catch (Exception e) {
         this.log.error("医嘱重整出现异常", e);
         ajaxResult = AjaxResult.error("查医嘱重整出现异常");
         if (StringUtils.isNotBlank(patientSaveOrderKey) && patientSaveOrderKeyFlag != null && patientSaveOrderKeyFlag) {
            this.redisCache.deleteObject(patientSaveOrderKey);
         }

         if (CollectionUtils.isNotEmpty(orderNoHandlingList) && orderNoHandlingFlag != null && orderNoHandlingFlag) {
            for(String orderNo : orderNoHandlingList) {
               String orderNoHandlingKey = "inpatientOrderNoHandling:" + orderNo;
               this.orderRedisCache.deleteObject(orderNoHandlingKey);
            }
         }
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('docOrder:herb:list')")
   @GetMapping({"/pastHerbTree"})
   public AjaxResult pastHerbTree(TdPaOrder tdPaOrder) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (tdPaOrder == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(tdPaOrder.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag) {
            List<TreeSelect> list = this.tdPaOrderService.selectPastHerbTreeList(tdPaOrder.getPatientId());
            ajaxResult = AjaxResult.success((Object)list);
         }
      } catch (Exception e) {
         this.log.error("查询既往中草药医嘱 --树结构出现异常", e);
         ajaxResult = AjaxResult.error("查询既往中草药医嘱 --树结构出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('docOrder:order:list')")
   @GetMapping({"/patientHistoryList"})
   public AjaxResult patientHistoryList(String patientId) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      Boolean flag = true;

      try {
         if (flag && StringUtils.isBlank(patientId)) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag) {
            List<VisitinfoVo> list = this.visitinfoService.selectVisitinfosById(patientId);
            list = list != null ? (List)list.stream().filter((t) -> !t.getPatientId().equals(patientId)).collect(Collectors.toList()) : list;
            ajaxResult = AjaxResult.success("查询成功", list);
         }
      } catch (Exception e) {
         this.log.error("查询既往医嘱出现异常", e);
         ajaxResult = AjaxResult.error("查询既往医嘱出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('docOrder:order:stop,docOrder:order:stopAll')")
   @PostMapping({"/queryStopBefore"})
   public AjaxResult queryStopBefore(@RequestBody OrderStopSignVo orderStopSignVo) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      Boolean flag = true;

      try {
         if (flag && orderStopSignVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         String patientId = flag ? orderStopSignVo.getPatientId() : null;
         if (flag && StringUtils.isBlank(patientId)) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag) {
            List<OrderSearchVo> orderList = orderStopSignVo.getOrderList();
            List<String> orderNoList = orderList != null && !orderList.isEmpty() ? (List)orderList.stream().map((t) -> t.getOrderNo()).collect(Collectors.toList()) : null;
            List<TdPaOrderDetailVo> orderPerformList = this.tdPaOrderService.selectUnExecPerformList(patientId, orderNoList);
            Map<String, Object> resMap = new HashMap(2);
            resMap.put("orderPerformList", orderPerformList);
            ajaxResult = AjaxResult.success("查询成功", resMap);
         }
      } catch (Exception e) {
         this.log.error("医嘱停止前查询未领药的领药单集合；未执行的执行单集合，出现异常：", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('docOrder:order:list')")
   @GetMapping({"/isBeforeSix"})
   public AjaxResult isBeforeSix(Date date) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         Date sixDate = DateUtils.addHours(this.commonService.getDbSysdate(), -6);
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
         if (date.compareTo(sixDate) < 0) {
            ajaxResult.put("flag", CommonConstants.BOOL_FALSE);
            ajaxResult.put("sixDate", sdf.format(sixDate));
         } else {
            ajaxResult.put("flag", CommonConstants.BOOL_TRUE);
            ajaxResult.put("sixDate", sdf.format(sixDate));
         }
      } catch (Exception e) {
         this.log.error("查询是否为当前时间前六小时内出现异常", e);
         ajaxResult = AjaxResult.error("查询是否为当前时间前六小时内出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('docOrder:order:list')")
   @PostMapping({"/drugStatement"})
   public AjaxResult drugStatement(@RequestBody String inpNo) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         Map<String, Object> map = this.tdPaOrderService.selectDrugBBList(inpNo);
         ajaxResult = AjaxResult.success((Object)map);
      } catch (Exception e) {
         this.log.error("出现异常", e);
         ajaxResult = AjaxResult.error("出现异常");
      }

      return ajaxResult;
   }

   @RequestMapping({"/checkPrintOrder"})
   @ResponseBody
   @PreAuthorize("@ss.hasPermi('docOrder:order:checkPrintOrder')")
   public AjaxResult checkPrintOrder(@RequestBody PrintOrderDataListReq req) {
      AjaxResult ajaxResult = AjaxResult.success("可以打印");
      List<PrintOrderDataListDetail> detailList = req.getDetailList();
      if (detailList == null || detailList.size() == 0) {
         ajaxResult = AjaxResult.error("参数集合不能为空");
      }

      Boolean flag = Boolean.FALSE;

      for(PrintOrderDataListDetail detail : detailList) {
         String orderClassCode = detail.getOrderClassCode();
         if (!StringUtils.isNotEmpty(orderClassCode)) {
            ajaxResult = AjaxResult.error("参数为空，不能打印！");
            return ajaxResult;
         }

         if (!orderClassCode.equals("01")) {
            flag = Boolean.TRUE;
         }
      }

      if (flag) {
         ajaxResult = AjaxResult.error("只有药品才能打印处方！");
         return ajaxResult;
      } else {
         try {
            ajaxResult = this.tdPaOrderService.checkPrintOrder(detailList, ajaxResult);
         } catch (Exception e) {
            this.log.error("判断是否可以打印出现异常:", e);
            ajaxResult = AjaxResult.error("判断是否可以打印出现异常，请联系管理员");
         }

         return ajaxResult;
      }
   }

   @RequestMapping({"/printOrderData"})
   @ResponseBody
   @PreAuthorize("@ss.hasPermi('docOrder:order:printOrderData')")
   public Map printOrderData(@RequestBody PrintOrderDataListReq req) {
      Map<String, Object> map = new HashMap();

      try {
         map = this.tdPaOrderService.printOrderData(req);
      } catch (Exception e) {
         this.log.error("打印失败！", e);
      }

      return map;
   }

   @RequestMapping({"/orderCancel"})
   @ResponseBody
   @PreAuthorize("@ss.hasPermi('docOrder:order:orderCancel')")
   public AjaxResult orderCancel(@RequestBody OrderStopSignVo orderStopSignVo, HttpServletRequest request) {
      AjaxResult ajaxResult = AjaxResult.success();
      Boolean flag = true;
      List<String> orderNoHandlingList = null;
      Boolean orderNoHandlingFlag = null;

      try {
         if (flag && orderStopSignVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag) {
            GrantOutDoctor grantOutDoctor = this.grantOutDoctorService.selectGrantOutDoctorBySubNo(SecurityUtils.getLoginUser().getUser().getUserName());
            if (grantOutDoctor != null) {
               flag = false;
               ajaxResult = AjaxResult.error("外来医师不能作废医嘱");
            }
         }

         List<OrderSearchVo> orderList = flag ? orderStopSignVo.getOrderList() : null;
         if (flag && (orderList == null || orderList != null && orderList.isEmpty())) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         String caFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0001");
         if (StringUtils.isNotBlank(caFlag) && caFlag.equals("1")) {
            if (flag && StringUtils.isBlank(orderStopSignVo.getEncryptedInfo())) {
               flag = false;
               ajaxResult = AjaxResult.error("签名加密后密文不能为空，请重新操作");
            }

            if (flag && StringUtils.isBlank(orderStopSignVo.getSignCertificate())) {
               flag = false;
               ajaxResult = AjaxResult.error("签名证书不能为空，请重新操作");
            }

            if (flag && StringUtils.isBlank(orderStopSignVo.getSignedText())) {
               flag = false;
               ajaxResult = AjaxResult.error("签名明文不能为空，请重新操作");
            }
         }

         List<String> orderNoList = flag ? (List)orderList.stream().map((t) -> t.getOrderNo()).collect(Collectors.toList()) : null;
         List<String> orderNoNullList = flag ? (List)orderNoList.stream().filter((t) -> StringUtils.isBlank(t)).collect(Collectors.toList()) : null;
         if (flag && orderNoNullList == null) {
            flag = false;
            ajaxResult = AjaxResult.error("医嘱编码不能为空，请确认后再作废");
         }

         if (flag && orderStopSignVo.getOrderCancelTime() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("医嘱作废时间不能为空，请确认后再作废");
         }

         if (flag && StringUtils.isBlank(orderStopSignVo.getOrderCancelDesc())) {
            flag = false;
            ajaxResult = AjaxResult.error("医嘱作废原因不能为空，请确认后再作废");
         }

         List<TdPaOrderItem> orderItemList = flag ? this.tdPaOrderItemService.selectFromStatusByOrderNoList(orderNoList) : null;
         List<String> patientIds = flag ? (List)orderItemList.stream().map((t) -> t.getPatientId()).distinct().collect(Collectors.toList()) : null;
         if (flag && patientIds != null && patientIds.size() > 1) {
            flag = false;
            ajaxResult = AjaxResult.error("要作废的医嘱有其他患者的医嘱，请确认后再作废");
         }

         List<TdPaOrderItem> orderItemList2 = new ArrayList(1);
         if (flag) {
            List<TdPaOrderItem> orderItemLongList = (List)orderItemList.stream().filter((t) -> t.getOrderStatus().equals("2")).collect(Collectors.toList());
            orderItemList2.addAll(orderItemLongList);

            for(TdPaOrderItem item : orderItemList) {
               String orderStatus = item.getOrderStatus();
               if (orderStatus.equals("0") || orderStatus.equals("1") || orderStatus.equals("2")) {
                  orderItemList2.add(item);
               }
            }
         }

         if (flag && orderStopSignVo.getOrderStopVoList() == null) {
            List<TdPaOrderItem> orderItemListTemp = new ArrayList(1);
            List<OrderStopVo> orderStopVoList = new ArrayList(orderItemList2.size());
            Map<String, List<OrderSearchVo>> orderListMap = (Map)orderList.stream().collect(Collectors.groupingBy((t) -> t.getOrderNo()));

            for(TdPaOrderItem orderItem : orderItemList2) {
               OrderStopVo orderStopVo = new OrderStopVo();
               orderStopVo.setOrderNo(orderItem.getOrderNo());
               Date stopTime = orderStopSignVo.getStopTime();
               orderItemListTemp.add(orderItem);
               orderStopVo.setStopTime(stopTime);
               orderStopVoList.add(orderStopVo);
               orderItem.setOrderStopTime(stopTime);
            }

            orderItemList2 = orderItemListTemp;
            orderStopSignVo.setOrderStopVoList(orderStopVoList);
         }

         if (flag && orderItemList2.isEmpty()) {
            flag = false;
            ajaxResult = AjaxResult.error("没有可作废的医嘱，请确认后再作废");
         }

         if (flag) {
            orderNoHandlingList = new ArrayList(1);

            for(String orderNo : (List)orderItemList2.stream().map((t) -> t.getOrderNo()).distinct().collect(Collectors.toList())) {
               List<TdPaOrderItem> orderItemsTemp = (List)orderItemList2.stream().filter((t) -> t.getOrderNo().equals(orderNo)).collect(Collectors.toList());
               TdPaOrderItem orderItem = (TdPaOrderItem)orderItemsTemp.get(0);
               String orderNoHandlingKey = "inpatientOrderNoHandling:" + orderNo;
               Integer orderNoHandling = (Integer)this.orderRedisCache.getCacheObject(orderNoHandlingKey);
               this.log.warn("============" + orderNoHandlingKey + " : " + orderNoHandling);
               if (orderNoHandling != null) {
                  String orderNoHandlingMsg = CommonUtils.getOrderNoHandlingMsg(Integer.valueOf(orderNoHandling));
                  ajaxResult = AjaxResult.error("医嘱[" + ((TdPaOrderItem)orderItemsTemp.get(0)).getOrderGroupNo() + "]：【" + ((TdPaOrderItem)orderItemsTemp.get(0)).getCpName() + "】" + orderNoHandlingMsg + ";");
                  flag = false;
                  break;
               }

               orderNoHandlingList.add(orderNo);
            }
         }

         if (flag) {
            if (CollectionUtils.isNotEmpty(orderNoHandlingList)) {
               for(String orderNo : orderNoHandlingList) {
                  String orderNoHandlingKey = "inpatientOrderNoHandling:" + orderNo;
                  this.orderRedisCache.setCacheObject(orderNoHandlingKey, 11);
               }

               orderNoHandlingFlag = true;
            }

            boolean returnFlag = false;
            OrderStopDoReturnVo returnVo = null;

            try {
               this.log.info("医嘱作废开始");
               String ip = IpUtils.getIpAddr(request);
               this.tdPaOrderDetailService.orderCancelDo(orderStopSignVo, orderItemList2, ip);
               ajaxResult = AjaxResult.success("医嘱作废成功");
               returnFlag = true;
            } catch (Exception e) {
               this.log.error("医嘱作废出现异常", e);
               ajaxResult = AjaxResult.error("医嘱作废出现异常");
               returnFlag = false;
            }

            if (CollectionUtils.isNotEmpty(orderNoHandlingList) && orderNoHandlingFlag != null && orderNoHandlingFlag) {
               for(String orderNo : orderNoHandlingList) {
                  String orderNoHandlingKey = "inpatientOrderNoHandling:" + orderNo;
                  this.orderRedisCache.deleteObject(orderNoHandlingKey);
               }
            }
         }
      } catch (Exception e) {
         this.log.error("医嘱作废出现异常", e);
         ajaxResult = AjaxResult.error("医嘱作废出现异常");
         if (CollectionUtils.isNotEmpty(orderNoHandlingList) && orderNoHandlingFlag != null && orderNoHandlingFlag) {
            for(String orderNo : orderNoHandlingList) {
               String orderNoHandlingKey = "inpatientOrderNoHandling:" + orderNo;
               this.orderRedisCache.deleteObject(orderNoHandlingKey);
            }
         }
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('docOrder:order:add')")
   @GetMapping({"/getInfoByPatientId"})
   public AjaxResult getInfoByPatientId(String admissionNo) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      Map map = new HashMap();

      try {
         if (StringUtils.isEmpty(admissionNo)) {
            ajaxResult = AjaxResult.error("患者id不能为空");
         } else {
            Personalinfo personalinfo = this.personalinfoService.selectPersonalinfoById(admissionNo);
            if (personalinfo != null) {
               map.put("personalinfo", personalinfo);
            }

            DiagInfo diagInfo = new DiagInfo();
            diagInfo.setPatientId(admissionNo);
            diagInfo.setDiagClassCd("01");
            diagInfo.setDiagTypeCd("02");
            DiagInfo diag = this.diagInfoService.selectDiagInfo(diagInfo);
            if (diag != null) {
               map.put("diagInfo", diag);
            }

            ajaxResult = AjaxResult.success((Object)map);
         }
      } catch (Exception e) {
         ajaxResult = AjaxResult.error("患者信息查询出现异常：");
         this.log.error("患者信息查询出现异常：", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('docOrder:order:add')")
   @PostMapping({"/ruleAnalysis"})
   public AjaxResult ruleAnalysis(@RequestBody OrderSaveInfoVo orderSaveInfoVo) {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();

      try {
         List<OrderSaveVo> orderSaveVoList = orderSaveInfoVo.getOrderSaveVoList();
         AjaxResult verify = this.verify(orderSaveVoList);
         if (!verify.get("code").equals(200)) {
            return verify;
         } else {
            VisitinfoVo vparam = new VisitinfoVo();
            vparam.setPatientId(((OrderSaveVo)orderSaveVoList.get(0)).getPatientId());
            Visitinfo visitinfo = this.visitinfoService.selectPatientById(vparam);
            if (visitinfo == null) {
               return AjaxResult.error("查询不到患者信息，请刷新页面再操作");
            } else if (!visitinfo.getInpNo().equals(((OrderSaveVo)orderSaveVoList.get(0)).getPatientId())) {
               return AjaxResult.error("患者信息不正确，请刷新页面再操作");
            } else {
               boolean userHasPatFlag = this.commonService.userHasPat(visitinfo, sysUser);
               if (!userHasPatFlag) {
                  return AjaxResult.error("只有患者所在科医生或者有临时授权的医生才能开立医嘱！");
               } else {
                  RuleAnalysisVo vo = this.ruleAnalysisService.ruleAnalysis(orderSaveVoList, visitinfo);
                  return AjaxResult.success((Object)vo);
               }
            }
         }
      } catch (YbException e) {
         this.log.error("事前审核出现异常，", e);
         return AjaxResult.error(e.getMessage());
      } catch (Exception e) {
         this.log.error("事前审核出现异常，", e);
         return AjaxResult.error("事前审核出现异常,请联系管理员！");
      }
   }

   @PreAuthorize("@ss.hasPermi('docOrder:order:add')")
   @GetMapping({"/ruleAnalysisQuery"})
   public AjaxResult ruleAnalysisQuery(String msgid) {
      if (StringUtils.isEmpty(msgid)) {
         return AjaxResult.error("请求参数不能为空！");
      } else {
         try {
            this.ruleAnalysisService.ruleAnalysisQuery(msgid);
            return AjaxResult.success((Object)Boolean.TRUE);
         } catch (YbException e) {
            this.log.error("查询事前审核出现异常，", e);
            return AjaxResult.error(e.getMessage());
         } catch (Exception e) {
            this.log.error("查询事前审核出现异常，", e);
            return AjaxResult.error("查询事前审核出现异常,请联系管理员！");
         }
      }
   }
}
