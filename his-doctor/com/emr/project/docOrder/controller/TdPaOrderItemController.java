package com.emr.project.docOrder.controller;

import com.emr.common.utils.DateUtils;
import com.emr.common.utils.IPAddressUtil;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.redis.RedisCache;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.PASS.domain.vo.mk.CheckInfoVo;
import com.emr.project.PASS.service.IPassService;
import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.domain.TdPaOrder;
import com.emr.project.docOrder.domain.TdPaOrderDetail;
import com.emr.project.docOrder.domain.TdPaOrderItem;
import com.emr.project.docOrder.domain.TdPaOrderStatus;
import com.emr.project.docOrder.domain.vo.DrugAndClinPatientVo;
import com.emr.project.docOrder.domain.vo.HerbSaveVo;
import com.emr.project.docOrder.domain.vo.ItemIsOpenResVo;
import com.emr.project.docOrder.domain.vo.ItemListIsOpenResVo;
import com.emr.project.docOrder.domain.vo.OrderCommitVo;
import com.emr.project.docOrder.domain.vo.OrderSearchVo;
import com.emr.project.docOrder.domain.vo.OrderStopCancelVo;
import com.emr.project.docOrder.domain.vo.OrderStopVo;
import com.emr.project.docOrder.domain.vo.TdPaOrderItemVo;
import com.emr.project.docOrder.service.ITdPaOrderDetailService;
import com.emr.project.docOrder.service.ITdPaOrderItemService;
import com.emr.project.docOrder.service.ITdPaOrderService;
import com.emr.project.dr.domain.vo.DrAntiApplyVo;
import com.emr.project.esSearch.domain.TmPaFreeze;
import com.emr.project.esSearch.service.IDrugAndClinService;
import com.emr.project.esSearch.service.IDrugStockService;
import com.emr.project.his.domain.vo.DrugDoseVo;
import com.emr.project.pat.domain.Visitinfo;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysEmrConfigService;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/docOrder/item"})
public class TdPaOrderItemController extends BaseController {
   @Autowired
   private ITdPaOrderItemService tdPaOrderItemService;
   @Autowired
   private RedisCache redisCache;
   @Autowired
   private IDrugStockService drugStockService;
   @Autowired
   private IDrugAndClinService drugAndClinService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private ITdPaOrderService tdPaOrderService;
   @Autowired
   private IPassService passService;
   @Autowired
   private ITdPaOrderDetailService tdPaOrderDetailService;

   @PreAuthorize("@ss.hasPermi('docOrder:item:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(TdPaOrderItemVo tdPaOrderItem) {
      this.startPage();
      List<TdPaOrderItem> list = null;

      try {
         list = this.tdPaOrderItemService.selectTdPaOrderItemList(tdPaOrderItem);
      } catch (Exception e) {
         this.log.error("查询医嘱关联的项目信息出现异常", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('docOrder:item:export')")
   @Log(
      title = "医嘱关联的项目信息（如：检查、化验、药疗等",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(TdPaOrderItemVo tdPaOrderItem) {
      AjaxResult ajaxResult = AjaxResult.success();
      List<TdPaOrderItem> list = null;

      try {
         list = this.tdPaOrderItemService.selectTdPaOrderItemList(tdPaOrderItem);
         ExcelUtil<TdPaOrderItem> util = new ExcelUtil(TdPaOrderItem.class);
         ajaxResult = util.exportExcel(list, "医嘱关联的项目信息（如：检查、化验、药疗等数据");
      } catch (Exception e) {
         this.log.error("导出医嘱关联的项目信息出现异常", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('docOrder:item:query')")
   @GetMapping({"/{orderNo}"})
   public AjaxResult getInfo(@PathVariable("orderNo") String orderNo) {
      return AjaxResult.success((Object)this.tdPaOrderItemService.selectTdPaOrderItemById(orderNo));
   }

   @PreAuthorize("@ss.hasPermi('docOrder:item:add')")
   @Log(
      title = "医嘱关联的项目信息（如：检查、化验、药疗等",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody TdPaOrderItem tdPaOrderItem) {
      return this.toAjax(this.tdPaOrderItemService.insertTdPaOrderItem(tdPaOrderItem));
   }

   @PreAuthorize("@ss.hasPermi('docOrder:item:edit')")
   @Log(
      title = "医嘱关联的项目信息（如：检查、化验、药疗等",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody TdPaOrderItem tdPaOrderItem) {
      return this.toAjax(this.tdPaOrderItemService.updateTdPaOrderItem(tdPaOrderItem));
   }

   @PreAuthorize("@ss.hasPermi('docOrder:item:remove')")
   @Log(
      title = "医嘱关联的项目信息（如：检查、化验、药疗等",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{orderNos}"})
   public AjaxResult remove(@PathVariable String[] orderNos) {
      return this.toAjax(this.tdPaOrderItemService.deleteTdPaOrderItemByIds(orderNos));
   }

   @PreAuthorize("@ss.hasPermi('docOrder:order:list')")
   @PostMapping({"/itemIsOpen"})
   public AjaxResult itemIsOpen(@RequestBody DrugAndClinPatientVo drugAndClinPatientVo) {
      AjaxResult ajaxResult = AjaxResult.success("医嘱项目可以开立");

      try {
         ItemIsOpenResVo vo = this.tdPaOrderItemService.selectItemIsOpen(drugAndClinPatientVo);
         ajaxResult = AjaxResult.success((Object)vo);
      } catch (Exception e) {
         this.log.error("判断医嘱项目是否可以开立出现异常", e);
         ajaxResult = AjaxResult.error("判断医嘱项目是否可以开立出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('docOrder:order:list')")
   @PostMapping({"/itemListIsOpen"})
   public AjaxResult itemListIsOpen(@RequestBody DrugAndClinPatientVo drugAndClinPatientVo) {
      AjaxResult ajaxResult = AjaxResult.success("医嘱项目可以开立");

      try {
         if (StringUtils.isNotBlank(drugAndClinPatientVo.getPatientId()) && drugAndClinPatientVo != null && drugAndClinPatientVo.getOrderSearchVoList() != null && !drugAndClinPatientVo.getOrderSearchVoList().isEmpty()) {
            ItemListIsOpenResVo vo = this.tdPaOrderItemService.selectItemIsOpenList(drugAndClinPatientVo.getPatientId(), drugAndClinPatientVo.getOrderSearchVoList(), drugAndClinPatientVo.getOrderStartTime());
            ajaxResult = AjaxResult.success((Object)vo);
         }
      } catch (Exception e) {
         this.log.error("判断医嘱项目是否可以开立出现异常", e);
         ajaxResult = AjaxResult.error("判断医嘱项目是否可以开立出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('docOrder:order:list,docOrder:item:status')")
   @GetMapping({"/statusFlow"})
   public AjaxResult statusFlow(DrAntiApplyVo drAntiApplyVo) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (StringUtils.isEmpty(drAntiApplyVo.getOrderNo())) {
            ajaxResult = AjaxResult.error("医嘱编码不能为空");
         } else {
            List<TdPaOrderStatus> list = this.tdPaOrderItemService.selectItemStatusFlow(drAntiApplyVo.getOrderNo());
            ajaxResult = AjaxResult.success((Object)list);
         }
      } catch (Exception e) {
         this.log.error("查询医嘱状态流程图出现异常", e);
         ajaxResult = AjaxResult.error("查询医嘱状态流程图出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('docOrder:herb:list')")
   @GetMapping({"/herbList"})
   public TableDataInfo herbList(TdPaOrderItemVo tdPaOrderItem) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         if (StringUtils.isEmpty(tdPaOrderItem.getPatientId())) {
            tableDataInfo = new TableDataInfo(500, "患者id不能为空");
         } else {
            this.startPage();
            tdPaOrderItem.setHerbalFlag("1");
            List<TdPaOrderItemVo> itemLsit = this.tdPaOrderItemService.selectOrderHerbList(tdPaOrderItem);
            tableDataInfo = this.getDataTable(itemLsit);
         }
      } catch (Exception e) {
         this.log.error("查询中草药医嘱分页列表接口出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询中草药医嘱分页列表接口出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('docOrder:order:stop,docOrder:order:edit')")
   @PostMapping({"/herbSignText"})
   public AjaxResult herbSignText(@RequestBody OrderStopCancelVo orderStopCancelVo) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (flag && orderStopCancelVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空！");
         }

         if (flag && StringUtils.isBlank(orderStopCancelVo.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空！");
         }

         if (flag && StringUtils.isBlank(orderStopCancelVo.getStopCancelFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("签名类型标志不能为空！");
         }

         if (flag && orderStopCancelVo.getStopCancelFlag().equals("0") && (orderStopCancelVo.getOrderStopVoList() == null || orderStopCancelVo.getOrderStopVoList() != null && orderStopCancelVo.getOrderStopVoList().isEmpty())) {
            flag = false;
            ajaxResult = AjaxResult.error("医嘱编码与停止时间集合不能为空！");
         }

         if (flag && (orderStopCancelVo.getStopCancelFlag().equals("1") || orderStopCancelVo.getStopCancelFlag().equals("2")) && (orderStopCancelVo.getOrderNoList() == null || orderStopCancelVo.getOrderNoList() != null && orderStopCancelVo.getOrderNoList().isEmpty())) {
            flag = false;
            ajaxResult = AjaxResult.error("医嘱编码集合不能为空！");
         }

         List<String> orderNoList = orderStopCancelVo.getOrderNoList();
         orderNoList = flag && orderStopCancelVo.getStopCancelFlag().equals("0") ? (List)orderStopCancelVo.getOrderStopVoList().stream().map((t) -> t.getOrderNo()).collect(Collectors.toList()) : orderNoList;
         List<TdPaOrderItem> orderItemList = flag ? this.tdPaOrderItemService.selectFromStatusByOrderNoList(orderNoList) : null;
         if (flag && orderItemList != null && orderStopCancelVo.getStopCancelFlag().equals("0")) {
            List<TdPaOrderItem> tempList = (List)orderItemList.stream().filter((t) -> !t.getOrderStatus().equals("1") && !t.getOrderStatus().equals("3")).collect(Collectors.toList());
            if (tempList != null && !tempList.isEmpty()) {
               flag = false;
               ajaxResult = AjaxResult.error("长期医嘱状态是【已审核】、【在执行】和临时医嘱状态是【已审核】的医嘱可以停止，请检查后再操作！");
            }
         }

         if (flag && orderItemList != null && orderStopCancelVo.getStopCancelFlag().equals("1")) {
            List<TdPaOrderItem> tempList = (List)orderItemList.stream().filter((t) -> !t.getOrderStatus().equals("8") && !t.getOrderStatus().equals("5") && !t.getOrderStatus().equals("4")).collect(Collectors.toList());
            if (tempList != null && !tempList.isEmpty()) {
               flag = false;
               ajaxResult = AjaxResult.error("长期医嘱状态是【停止】、【停止确认】和临时医嘱状态是【已执行】的医嘱可以取消，请检查后再操作！");
            }
         }

         if (flag && orderItemList != null && orderStopCancelVo.getStopCancelFlag().equals("2")) {
            List<TdPaOrderItem> tempList = (List)orderItemList.stream().filter((t) -> !t.getOrderStatus().equals("-1")).collect(Collectors.toList());
            if (tempList != null && !tempList.isEmpty()) {
               flag = false;
               ajaxResult = AjaxResult.error("医嘱状态是【待提交】的医嘱可以提交，请检查后再操作！");
            }
         }

         if (flag && orderItemList != null && orderStopCancelVo.getStopCancelFlag().equals("4")) {
            List<TdPaOrderItem> tempList = (List)orderItemList.stream().filter((t) -> !t.getOrderStatus().equals("0") && !t.getOrderStatus().equals("1") && !t.getOrderStatus().equals("2")).collect(Collectors.toList());
            if (tempList != null && !tempList.isEmpty()) {
               flag = false;
               ajaxResult = AjaxResult.error("医嘱状态是【待审核】、【待执行】的医嘱可以作废，请检查后再操作！");
            }

            if (flag) {
               String orderGroupNoStr = this.tdPaOrderService.verifyExpensedetailByOrderNo(orderNoList);
               if (StringUtils.isNotBlank(orderGroupNoStr)) {
                  flag = false;
                  ajaxResult = AjaxResult.error("医嘱组号【" + orderGroupNoStr + "】计费总额不为0，不能作废！");
               }
            }
         }

         if (flag) {
            Date currDate = this.commonService.getDbSysdate();
            Map<String, OrderStopVo> stopDateMap = null;
            if (orderStopCancelVo.getOrderStopVoList() != null && !orderStopCancelVo.getOrderStopVoList().isEmpty()) {
               stopDateMap = (Map)orderStopCancelVo.getOrderStopVoList().stream().collect(Collectors.toMap((t) -> t.getOrderNo(), Function.identity()));
            }

            if (orderStopCancelVo.getStopCancelFlag().equals("0")) {
               orderStopCancelVo.setOrderNoList(orderNoList);
            }

            String herbSignText = this.tdPaOrderItemService.herbSignText(orderStopCancelVo.getStopCancelFlag(), orderStopCancelVo.getPatientId(), orderStopCancelVo.getOrderNoList(), stopDateMap, currDate);
            Map<String, String> resMap = new HashMap(2);
            resMap.put("herbSignText", herbSignText);
            resMap.put("currTime", DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, currDate));
            ajaxResult = AjaxResult.success("查询成功", resMap);
         }
      } catch (Exception e) {
         this.log.error("中草药停止、取消签名时查询签名字符串信息出现异常", e);
         ajaxResult = AjaxResult.error("中草药停止、取消签名时查询签名字符串信息出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('docOrder:order:stop,docOrder:order:edit')")
   @PostMapping({"/orderSignText"})
   public AjaxResult orderSignText(@RequestBody OrderStopCancelVo orderStopCancelVo) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (flag && orderStopCancelVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空！");
         }

         if (flag && StringUtils.isBlank(orderStopCancelVo.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空！");
         }

         if (flag && StringUtils.isBlank(orderStopCancelVo.getStopCancelFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("签名类型标志不能为空！");
         }

         if (flag && !orderStopCancelVo.getStopCancelFlag().equals("3") && (orderStopCancelVo.getOrderList() == null || orderStopCancelVo.getOrderList() != null && orderStopCancelVo.getOrderList().isEmpty())) {
            flag = false;
            ajaxResult = AjaxResult.error("医嘱集合不能为空！");
         }

         Visitinfo visitinfo = flag ? this.visitinfoService.selectVisitinfoById(orderStopCancelVo.getPatientId()) : null;
         if (flag && visitinfo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有这个患者的信息，请确认后再操作");
         }

         List<TdPaOrderItem> targetOrderItemList = null;
         List<TdPaOrderItem> orderItemList = null;
         List<String> targetOrderNoList = null;
         List<String> orderNoList = null;
         if (flag && !orderStopCancelVo.getStopCancelFlag().equals("3")) {
            orderNoList = flag ? (List)orderStopCancelVo.getOrderList().stream().map((t) -> t.getOrderNo()).collect(Collectors.toList()) : null;
            orderItemList = flag ? this.tdPaOrderItemService.selectFromStatusByOrderNoList(orderNoList) : null;
         }

         if (flag) {
            switch (orderStopCancelVo.getStopCancelFlag()) {
               case "0":
                  List<TdPaOrderItem> orderItemList01 = (List)orderItemList.stream().filter((t) -> t.getOrderType().equals("1") && t.getOrderStatus().equals("3")).collect(Collectors.toList());
                  List<TdPaOrderItem> orderItemList02 = (List)orderItemList.stream().filter((t) -> t.getOrderType().equals("1") && (t.getOrderStatus().equals("1") || t.getOrderStatus().equals("2"))).collect(Collectors.toList());
                  List<TdPaOrderItem> orderItemList03 = (List)orderItemList.stream().filter((t) -> t.getOrderType().equals("2") && (t.getOrderStatus().equals("1") || t.getOrderStatus().equals("2"))).collect(Collectors.toList());
                  targetOrderItemList = new ArrayList(orderItemList01.size() + orderItemList02.size() + orderItemList03.size());
                  targetOrderItemList.addAll(orderItemList01);
                  targetOrderItemList.addAll(orderItemList02);
                  targetOrderItemList.addAll(orderItemList03);
                  break;
               case "1":
                  targetOrderItemList = (List)orderItemList.stream().filter((t) -> t.getOrderStatus().equals("4") || t.getOrderStatus().equals("5") || t.getOrderStatus().equals("8")).collect(Collectors.toList());
                  break;
               case "3":
                  targetOrderItemList = this.tdPaOrderDetailService.getStopAllOrderList(orderStopCancelVo.getPatientId());
                  break;
               case "4":
                  List<TdPaOrderItem> tempList = (List)orderItemList.stream().filter((t) -> !t.getOrderStatus().equals("0") && !t.getOrderStatus().equals("1") && !t.getOrderStatus().equals("2")).collect(Collectors.toList());
                  if (tempList != null && !tempList.isEmpty()) {
                     flag = false;
                     ajaxResult = AjaxResult.error("医嘱状态是【待审核】、【待执行】的医嘱可以作废，请检查后再操作！");
                  }

                  if (flag) {
                     targetOrderItemList = orderItemList;
                  }
            }
         }

         if (flag && (targetOrderItemList == null || targetOrderItemList != null && targetOrderItemList.isEmpty())) {
            flag = false;
            ajaxResult = AjaxResult.error("没有需要签名的医嘱！");
         }

         if (flag) {
            Date currDate = this.commonService.getDbSysdate();
            targetOrderNoList = (List)targetOrderItemList.stream().map((t) -> t.getOrderNo()).collect(Collectors.toList());
            Map<String, OrderSearchVo> stopDateMap = orderStopCancelVo.getStopCancelFlag().equals("0") ? (Map)orderStopCancelVo.getOrderList().stream().collect(Collectors.toMap((t) -> t.getOrderNo(), Function.identity())) : null;
            orderStopCancelVo.setOrderNoList(orderNoList);
            String orderSignText = this.tdPaOrderItemService.orderSignText(orderStopCancelVo.getStopCancelFlag(), orderStopCancelVo.getPatientId(), targetOrderNoList, stopDateMap, currDate);
            Map<String, String> resMap = new HashMap(2);
            resMap.put("orderSignText", orderSignText);
            resMap.put("currTime", DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, currDate));
            ajaxResult = AjaxResult.success("查询成功", resMap);
         }
      } catch (Exception e) {
         this.log.error("停止、取消、全停签名时查询签名字符串信息出现异常", e);
         ajaxResult = AjaxResult.error("停止、取消、全停签名时查询签名字符串信息出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('docOrder:herb:list')")
   @GetMapping({"/queryOrderInfo"})
   public AjaxResult queryOrderInfo(TdPaOrderItem tdPaOrderItem) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (StringUtils.isEmpty(tdPaOrderItem.getOrderNo())) {
            ajaxResult = AjaxResult.error("医嘱编码不能为空");
         } else {
            TdPaOrderItemVo tdPaOrderItemVo = this.tdPaOrderItemService.selectOrderInfoByOrderNo(tdPaOrderItem.getOrderNo());
            TdPaOrder tdPaOrder = this.tdPaOrderService.selectTdPaOrderById(tdPaOrderItem.getOrderNo());
            ajaxResult = AjaxResult.success((Object)tdPaOrderItemVo);
            ajaxResult.put("tdPaOrder", tdPaOrder);
         }
      } catch (Exception e) {
         this.log.error("查询某个医嘱详细信息出现异常", e);
         ajaxResult = AjaxResult.error("查询某个医嘱详细信息出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('docOrder:herb:add')")
   @Log(
      title = "主要记录 医嘱的主要信息",
      businessType = BusinessType.INSERT
   )
   @PostMapping({"/passParam"})
   public AjaxResult getPassVerifyParam(@RequestBody HerbSaveVo herbSaveVo) {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         String passFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0042");
         if (StringUtils.isNotBlank(passFlag) && passFlag.equals("1")) {
            ajaxResult = this.herbVerify(herbSaveVo);
            Boolean flag = ajaxResult.get("code").equals(200);
            String patientId = flag ? herbSaveVo.getPatientId() : null;
            if (flag) {
               CheckInfoVo checkInfoVo = this.passService.getCheckInfoVo_mk(patientId, (List)null, herbSaveVo);
               ajaxResult = AjaxResult.success("查询成功", checkInfoVo);
            }
         } else {
            ajaxResult = AjaxResult.error("获取pass合理用药审查系统未启用");
         }
      } catch (Exception e) {
         this.log.error("获取pass合理用药审查接口参数出现异常：", e);
         ajaxResult = AjaxResult.error("获取pass合理用药审查接口参数出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('docOrder:herb:add')")
   @Log(
      title = "主要记录 医嘱的主要信息",
      businessType = BusinessType.INSERT
   )
   @PostMapping({"/passParamForZcy"})
   public AjaxResult getPassVerifyParamForZcy(@RequestBody HerbSaveVo herbSaveVo) {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         String passFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0042");
         if (StringUtils.isNotBlank(passFlag) && passFlag.equals("1")) {
            ajaxResult = this.herbVerify(herbSaveVo);
            Boolean flag = ajaxResult.get("code").equals(200);
            String patientId = flag ? herbSaveVo.getPatientId() : null;
            if (flag) {
               CheckInfoVo checkInfoVo = this.passService.getCheckInfoForZcyVo_mk(patientId, (List)null, herbSaveVo);
               ajaxResult = AjaxResult.success("查询成功", checkInfoVo);
            }
         } else {
            ajaxResult = AjaxResult.error("获取pass合理用药审查系统未启用");
         }
      } catch (Exception e) {
         this.log.error("获取pass合理用药审查接口参数出现异常：", e);
         ajaxResult = AjaxResult.error("获取pass合理用药审查接口参数出现异常");
      }

      return ajaxResult;
   }

   private AjaxResult herbVerify(HerbSaveVo herbSaveVo) {
      AjaxResult ajaxResult = AjaxResult.success("医嘱录入保存成功");
      Boolean flag = true;
      if (herbSaveVo == null) {
         ajaxResult = AjaxResult.error("参数不能为空");
         flag = false;
      }

      if (flag && StringUtils.isEmpty(herbSaveVo.getPatientId())) {
         ajaxResult = AjaxResult.error("患者id不能为空");
         flag = false;
      }

      if (flag && StringUtils.isEmpty(herbSaveVo.getOrderType())) {
         ajaxResult = AjaxResult.error("医嘱类型不能为空");
         flag = false;
      }

      if (flag && StringUtils.isEmpty(herbSaveVo.getOrderStartDoc())) {
         ajaxResult = AjaxResult.error("开嘱医师编号不能为空");
         flag = false;
      }

      if (flag && StringUtils.isEmpty(herbSaveVo.getOrderStartDocName())) {
         ajaxResult = AjaxResult.error("开嘱医师姓名不能为空");
         flag = false;
      }

      if (flag && StringUtils.isEmpty(herbSaveVo.getItemOrderUsageWay())) {
         ajaxResult = AjaxResult.error("用法不能为空");
         flag = false;
      }

      if (flag && StringUtils.isEmpty(herbSaveVo.getItemOrderUsageWayName())) {
         ajaxResult = AjaxResult.error("用法名称不能为空");
         flag = false;
      }

      if (flag && StringUtils.isEmpty(herbSaveVo.getItemOrderFreq())) {
         ajaxResult = AjaxResult.error("频率不能为空");
         flag = false;
      }

      if (flag && StringUtils.isEmpty(herbSaveVo.getItemOrderFreqName())) {
         ajaxResult = AjaxResult.error("频率名称不能为空");
         flag = false;
      }

      if (flag && StringUtils.isEmpty(herbSaveVo.getDecoctMethod())) {
         ajaxResult = AjaxResult.error("煎药方式不能为空");
         flag = false;
      }

      if (flag && herbSaveVo.getOrderDetailList() == null) {
         ajaxResult = AjaxResult.error("药品列表不能为空");
         flag = false;
      }

      if (flag && StringUtils.isEmpty(herbSaveVo.getOrderStatus())) {
         ajaxResult = AjaxResult.error("医嘱状态不能为空");
         flag = false;
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('docOrder:herb:add')")
   @PostMapping({"/insertHerb"})
   public AjaxResult insertHerb(@RequestBody HerbSaveVo herbSaveVo, HttpServletRequest request) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         String ip = IPAddressUtil.getIPAddress(request);
         SysUser sysUser = SecurityUtils.getLoginUser().getUser();
         ajaxResult = this.herbVerify(herbSaveVo);
         flag = ajaxResult.get("code").equals(200);
         VisitinfoVo vparam = new VisitinfoVo();
         vparam.setPatientId(herbSaveVo.getPatientId());
         Visitinfo visitinfo = flag ? this.visitinfoService.selectPatientById(vparam) : null;
         if (flag && visitinfo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("查询不到患者信息，请刷新页面再操作");
         }

         if (flag && !visitinfo.getInpNo().equals(herbSaveVo.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者信息不正确，请刷新页面再操作");
         }

         boolean userHasPatFlag = flag ? this.commonService.userHasPat(visitinfo, sysUser) : flag;
         if (flag && !userHasPatFlag) {
            flag = false;
            ajaxResult = AjaxResult.error("只有患者所在科医生或者有临时授权的医生才能开立医嘱！");
         }

         if (flag && (herbSaveVo.getOrderDetailList() == null || herbSaveVo.getOrderDetailList().isEmpty())) {
            flag = false;
            ajaxResult = AjaxResult.error("药品不能为空");
         }

         Map<String, List<TdPaOrderDetail>> drugMap = (Map)herbSaveVo.getOrderDetailList().stream().collect(Collectors.groupingBy(TdPaOrderDetail::getChargeNo));
         if (flag && drugMap != null) {
            for(String key : drugMap.keySet()) {
               List<TdPaOrderDetail> orderDetailList = (List)drugMap.get(key);
               if (orderDetailList.size() > 1) {
                  flag = Boolean.FALSE;
                  TdPaOrderDetail orderDetail = (TdPaOrderDetail)orderDetailList.get(0);
                  ajaxResult = AjaxResult.error("同一处方药品【" + orderDetail.getChargeName() + "】重复，请检查后重新开立!");
                  break;
               }
            }
         }

         TdPaOrderItem item = flag && StringUtils.isNotBlank(herbSaveVo.getOrderNo()) ? this.tdPaOrderItemService.selectTdPaOrderItemById(herbSaveVo.getOrderNo()) : null;
         if (flag && item != null && !item.getOrderStatus().equals("-1")) {
            flag = false;
            ajaxResult = AjaxResult.error("此医嘱状态不是新增或保存状态，不能再操作保存或提交");
         }

         if (flag) {
            List<TdPaOrderDetail> detail = this.tdPaOrderItemService.isStockNum(herbSaveVo.getOrderDetailList(), herbSaveVo.getPerformDepCode());
            if (detail != null && !detail.isEmpty()) {
               flag = false;
               StringBuffer sb = new StringBuffer();
               detail.stream().forEach((t) -> sb.append("编码：" + t.getChargeNo() + "，名称：" + t.getChargeName() + "；"));
               ajaxResult = AjaxResult.error(sb.toString() + "药品库存数量不足");
               ajaxResult.put("drugList", detail);
            }
         }

         if (flag) {
            String patientSaveOrderFlag = (String)this.redisCache.getCacheObject("patient_save_order:" + herbSaveVo.getPatientId());
            if (StringUtils.isNotBlank(patientSaveOrderFlag) && patientSaveOrderFlag.equals("1")) {
               flag = false;
               ajaxResult = AjaxResult.error("当前患者有正在保存医嘱的操作，请稍后再保存");
            }
         }

         if (flag) {
            this.redisCache.setCacheObject("patient_save_order:" + herbSaveVo.getPatientId(), "1");
            if (StringUtils.isEmpty(herbSaveVo.getOrderNo())) {
               List<DrugDoseVo> drugDoseVoList = this.tdPaOrderItemService.saveHerbOrder(herbSaveVo);

               try {
                  if (flag && drugDoseVoList != null && !drugDoseVoList.isEmpty()) {
                     List<DrugDoseVo> drugDoseVos = this.drugStockService.updateDrugDoseByOrderDose(sysUser, drugDoseVoList, "1", ip);
                     this.drugAndClinService.updateDurgXcsl(drugDoseVos);
                  }
               } catch (Exception e) {
                  this.log.error("保存中草药医嘱更新es药品数量出现异常", e);
               }
            } else {
               List<DrugDoseVo> drugDoseVoList = this.tdPaOrderItemService.updateHerbOrder(herbSaveVo);

               try {
                  List<TmPaFreeze> freezeList = this.drugStockService.selectNewOrderFreezeList(herbSaveVo.getOrderNo());
                  this.drugStockService.toMsgDeleteFreezeAddStock(freezeList);
                  if (flag && drugDoseVoList != null && !drugDoseVoList.isEmpty()) {
                     List<DrugDoseVo> drugDoseVos = this.drugStockService.updateDrugDoseByOrderDose(sysUser, drugDoseVoList, "1", ip);
                     this.drugAndClinService.updateDurgXcsl(drugDoseVos);
                  }
               } catch (Exception e) {
                  this.log.error("保存修改中草药医嘱更新es药品数量出现异常", e);
               }
            }

            ajaxResult.put("orderNo", herbSaveVo.getOrderNo());
            ajaxResult.put("orderNoList", herbSaveVo.getOrderNoList());
            ajaxResult.put("state", herbSaveVo.getOrderStatus());
            this.redisCache.deleteObject("patient_save_order:" + herbSaveVo.getPatientId());
         }
      } catch (Exception e) {
         this.log.error("保存中草药医嘱出现异常", e);
         this.redisCache.deleteObject("patient_save_order:" + herbSaveVo.getPatientId());
         ajaxResult = AjaxResult.error("保存中草药医嘱出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('docOrder:herb:add')")
   @PostMapping({"/submitHerb"})
   public AjaxResult submitHerb(@RequestBody OrderCommitVo orderCommitVo) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (flag && orderCommitVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空，请重新操作");
         }

         if (flag && StringUtils.isBlank(orderCommitVo.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空，请重新操作");
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
            Object var11 = null;
         }

         List<TdPaOrderItem> orderItemList2 = flag ? (List)orderItemList.stream().filter((t) -> t.getOrderStatus().equals("-1")).collect(Collectors.toList()) : null;
         if (flag && (orderItemList2 == null || orderItemList2 != null && orderItemList2.isEmpty())) {
            flag = false;
            ajaxResult = AjaxResult.error("没有待提交的医嘱记录，请重新操作");
         }

         if (flag) {
            this.tdPaOrderItemService.saveSubmitHerbOrder(orderCommitVo, orderItemList2, visitinfo);
         }
      } catch (Exception e) {
         this.log.error("提交中草药医嘱出现异常", e);
         ajaxResult = AjaxResult.error("提交中草药医嘱出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('docOrder:item:patMaxGroupNo,docOrder:order:list')")
   @GetMapping({"/patMaxGroupNo"})
   public AjaxResult selectPatientMaxGroupNo(String patientId) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (StringUtils.isBlank(patientId)) {
            ajaxResult = AjaxResult.error("患者id不能为空");
         } else {
            Integer maxGroupNo = this.tdPaOrderItemService.selectPatientMaxGroupNo(patientId);
            maxGroupNo = maxGroupNo == null ? 1 : maxGroupNo;
            ajaxResult = AjaxResult.success("查询成功", maxGroupNo);
         }
      } catch (Exception e) {
         this.log.error("查询患者当前医嘱最大组号出现异常：", e);
         ajaxResult = AjaxResult.error("查询患者当前医嘱最大组号出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('docOrder:herb:add')")
   @GetMapping({"/isPatDiag"})
   public AjaxResult selectIsPatDiag(String patientId) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (StringUtils.isBlank(patientId)) {
            ajaxResult = AjaxResult.error("患者id不能为空");
         } else {
            ItemIsOpenResVo itemIsOpenResVo = this.tdPaOrderItemService.selectHrefItemIsOpen(patientId);
            ajaxResult = AjaxResult.success((Object)itemIsOpenResVo);
         }
      } catch (Exception e) {
         this.log.error("判断是否可以开立中草药医嘱出现异常：", e);
         ajaxResult = AjaxResult.error("判断是否可以开立中草药医嘱出现异常");
      }

      return ajaxResult;
   }
}
