package com.emr.project.docOrder.controller;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.docOrder.domain.TdPaOrderItem;
import com.emr.project.docOrder.domain.req.OderPerformDetailReq;
import com.emr.project.docOrder.domain.vo.OderPerformResultVo;
import com.emr.project.docOrder.domain.vo.OrderListBaseInfoVo;
import com.emr.project.docOrder.domain.vo.OrderListInfoVo;
import com.emr.project.docOrder.domain.vo.OrderListPrintVo;
import com.emr.project.docOrder.domain.vo.OrderListSearchVo;
import com.emr.project.docOrder.domain.vo.OrderListVo;
import com.emr.project.docOrder.domain.vo.TdPaOrderItemVo;
import com.emr.project.docOrder.service.ITdPaOrderItemService;
import com.emr.project.docOrder.service.ITdPaOrderListService;
import com.emr.project.qc.domain.EmrQcFlow;
import com.emr.project.qc.service.IEmrQcFlowService;
import com.emr.project.system.domain.SysDept;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysEmrConfigService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
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
@RequestMapping({"/docOrder/list"})
public class OrderLisController extends BaseController {
   private int LONG_PAGE_SIZE = 25;
   private int TEMP_PAGE_SIZE = 25;
   private int DECOCTION_PAGE_SIZE = 25;
   @Autowired
   private ITdPaOrderListService tdPaOrderListService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private IEmrQcFlowService emrQcFlowService;
   @Autowired
   private ITdPaOrderItemService tdPaOrderItemService;

   @PreAuthorize("@ss.hasAnyPermi('docOrder:list:long,docOrder:list:temp,docOrder:list:decoction,operRoom:orderList:search,qc:flow:term,pat:info:emrAllList,qc:statis:checkCase')")
   @GetMapping({"/info"})
   public AjaxResult queryOrderListBaseInfo(String patientId) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (flag && StringUtils.isBlank(patientId)) {
            ajaxResult = AjaxResult.error("查询长期医嘱本数据出现异常");
            flag = false;
         }

         if (flag) {
            OrderListBaseInfoVo res = this.tdPaOrderListService.queryOrderListBaseInfo(patientId);
            res.setLongPageSize(this.LONG_PAGE_SIZE);
            res.setTempPageSize(this.TEMP_PAGE_SIZE);
            res.setDecoctionPageSize(this.DECOCTION_PAGE_SIZE);
            String tempDrugDoseViewFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("004703");
            res.setTempDrugDoseViewFlag(tempDrugDoseViewFlag);
            List<SysDept> orderDocDeptList = this.tdPaOrderListService.selectPatOrderDeptList(patientId);
            SysDept sysDept = new SysDept();
            sysDept.setDeptCode("all");
            sysDept.setDeptName("全部");
            orderDocDeptList.add(0, sysDept);
            res.setOrderDocDeptList(orderDocDeptList);
            ajaxResult = AjaxResult.success((Object)res);
         }
      } catch (Exception e) {
         ajaxResult = AjaxResult.error("查询长期医嘱本数据出现异常");
         this.log.error("查询长期医嘱本数据出现异常，", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('docOrder:list:long,docOrder:list:temp,operRoom:orderList:search,qc:flow:term,pat:info:emrAllList')")
   @PostMapping({"/long"})
   public AjaxResult queryLongOrderList(@RequestBody OrderListSearchVo orderListSearchVo) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (flag && StringUtils.isBlank(orderListSearchVo.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag && orderListSearchVo.getPageNum() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("页码不能为空");
         }

         if (flag) {
            String babyAdmissionNo = orderListSearchVo.getBabyAdmissionNo();
            babyAdmissionNo = StringUtils.isNotBlank(babyAdmissionNo) && babyAdmissionNo.equals(orderListSearchVo.getPatientId()) ? null : babyAdmissionNo;
            orderListSearchVo.setBabyAdmissionNo(babyAdmissionNo);
            int pageSize = 0;
            switch (orderListSearchVo.getOrderType()) {
               case "1":
                  pageSize = this.LONG_PAGE_SIZE;
                  break;
               case "2":
                  pageSize = this.TEMP_PAGE_SIZE;
            }

            String blankStr = this.sysEmrConfigService.selectSysEmrConfigByKey("0047");
            String allFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("004702");
            OrderListInfoVo orderListInfoVoTotal = this.tdPaOrderListService.getPageTotal(orderListSearchVo, pageSize, blankStr, allFlag);
            new OrderListInfoVo();
            OrderListInfoVo orderListInfoVo;
            if (orderListInfoVoTotal.getListAll() != null && !orderListInfoVoTotal.getListAll().isEmpty()) {
               Map<String, List<OrderListVo>> groupOrderMap = (Map)orderListInfoVoTotal.getListAll().stream().collect(Collectors.groupingBy((t) -> t.getOrderGroupNo() + "" + t.getReOrganizationNo()));
               List<List<OrderListVo>> pageDataList = orderListInfoVoTotal.getPageDataList();
               int total = pageDataList.stream().mapToInt((t) -> t.size()).sum();
               orderListInfoVo = this.tdPaOrderListService.operationLongOrderList(orderListSearchVo, groupOrderMap, CollectionUtils.isNotEmpty(pageDataList) ? (List)pageDataList.get(orderListSearchVo.getPageNum() - 1) : null, pageSize, blankStr);
               orderListInfoVo.setTotal(total);
            } else {
               orderListInfoVo = this.tdPaOrderListService.operationLongOrderList(orderListSearchVo, (Map)null, (List)null, this.LONG_PAGE_SIZE, blankStr);
               List<OrderListVo> orderListVoList = new ArrayList(pageSize);

               for(int i = 0; i < pageSize; ++i) {
                  orderListVoList.add(new OrderListVo());
               }

               orderListInfoVo.setList(orderListVoList);
               orderListInfoVo.setTotal(0);
            }

            ajaxResult = AjaxResult.success((Object)orderListInfoVo);
         }
      } catch (Exception e) {
         ajaxResult = AjaxResult.error("查询长期/临时医嘱本数据出现异常");
         this.log.error("查询长期/临时医嘱本数据出现异常，", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('docOrder:list:longprint,docOrder:list:tempprint,operRoom:orderList:print,qc:flow:term')")
   @PostMapping({"/print"})
   public AjaxResult queryPrintData(@RequestBody OrderListPrintVo orderListPrintVo) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (flag && orderListPrintVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && orderListPrintVo.getPrintType() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("打印类型不能为空");
         }

         if (flag && orderListPrintVo.getPrintType() == 4 && orderListPrintVo.getPrintPageNum() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("指定页不能为空");
         }

         if (flag && orderListPrintVo.getPrintType() == 5) {
            if (orderListPrintVo.getPrintStartPageNum() == null) {
               flag = false;
               ajaxResult = AjaxResult.error("起始页不能为空");
            }

            if (orderListPrintVo.getPrintStartLineNum() == null) {
               flag = false;
               ajaxResult = AjaxResult.error("起始行不能为空");
            }
         }

         if (flag && orderListPrintVo.getPrintType() == 6 && orderListPrintVo.getPrintPageNum2() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("页码不能为空");
         }

         if (flag && orderListPrintVo.getOrderListSearchVo() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag) {
            String babyAdmissionNo = orderListPrintVo.getOrderListSearchVo().getBabyAdmissionNo();
            babyAdmissionNo = StringUtils.isNotBlank(babyAdmissionNo) && babyAdmissionNo.equals(orderListPrintVo.getOrderListSearchVo().getPatientId()) ? null : babyAdmissionNo;
            orderListPrintVo.getOrderListSearchVo().setBabyAdmissionNo(babyAdmissionNo);
            int pageSize = 0;
            switch (orderListPrintVo.getOrderListType()) {
               case "1":
                  orderListPrintVo.getOrderListSearchVo().setOrderType("1");
                  orderListPrintVo.getOrderListSearchVo().setHerbalFlag("0");
                  pageSize = this.LONG_PAGE_SIZE;
                  break;
               case "2":
                  orderListPrintVo.getOrderListSearchVo().setOrderType("2");
                  orderListPrintVo.getOrderListSearchVo().setHerbalFlag("0");
                  pageSize = this.TEMP_PAGE_SIZE;
                  break;
               case "3":
                  orderListPrintVo.getOrderListSearchVo().setOrderType((String)null);
                  orderListPrintVo.getOrderListSearchVo().setHerbalFlag("1");
                  pageSize = this.DECOCTION_PAGE_SIZE;
            }

            String blankStr = this.sysEmrConfigService.selectSysEmrConfigByKey("0047");
            String allFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("004702");
            List<OrderListInfoVo> list = this.tdPaOrderListService.queryPrintData(orderListPrintVo, pageSize, blankStr, allFlag);
            ajaxResult.put("printDataList", list);
         }
      } catch (Exception e) {
         ajaxResult = AjaxResult.error("查询长期/临时医嘱本打印数据出现异常");
         this.log.error("查询长期/临时医嘱本打印数据出现异常，", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('docOrder:list:decoction,operRoom:orderList:search,qc:flow:term,pat:info:emrAllList')")
   @PostMapping({"/decoction"})
   public AjaxResult queryDecoctionOrderList(@RequestBody OrderListSearchVo orderListSearchVo) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (flag && StringUtils.isBlank(orderListSearchVo.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag && orderListSearchVo.getPageNum() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("页码不能为空");
         }

         if (flag) {
            String babyAdmissionNo = orderListSearchVo.getBabyAdmissionNo();
            babyAdmissionNo = StringUtils.isNotBlank(babyAdmissionNo) && babyAdmissionNo.equals(orderListSearchVo.getPatientId()) ? null : babyAdmissionNo;
            orderListSearchVo.setBabyAdmissionNo(babyAdmissionNo);
            String blankStr = this.sysEmrConfigService.selectSysEmrConfigByKey("0047");
            OrderListInfoVo orderListInfoVoTotal = this.tdPaOrderListService.queryDecoctionTotal(orderListSearchVo, this.DECOCTION_PAGE_SIZE, blankStr);
            List<List<OrderListVo>> pageDataList = orderListInfoVoTotal.getPageDataList();
            new OrderListInfoVo();
            OrderListInfoVo orderListInfoVo;
            if (pageDataList != null && !pageDataList.isEmpty()) {
               orderListInfoVo = this.tdPaOrderListService.operationDecoctionOrderList(orderListSearchVo, orderListInfoVoTotal.getHerbalMap(), (List)pageDataList.get(orderListSearchVo.getPageNum() - 1), this.DECOCTION_PAGE_SIZE, blankStr);
               int total = (pageDataList.size() - 1) * this.DECOCTION_PAGE_SIZE + ((List)pageDataList.get(pageDataList.size() - 1)).size();
               orderListInfoVo.setTotal(total);
            } else {
               orderListInfoVo = this.tdPaOrderListService.operationDecoctionOrderList(orderListSearchVo, (Map)null, (List)null, this.DECOCTION_PAGE_SIZE, blankStr);
               List<OrderListVo> orderListVoList = new ArrayList(this.DECOCTION_PAGE_SIZE);

               for(int i = 0; i < this.DECOCTION_PAGE_SIZE; ++i) {
                  orderListVoList.add(new OrderListVo());
               }

               orderListInfoVo.setList(orderListVoList);
               orderListInfoVo.setTotal(0);
            }

            ajaxResult = AjaxResult.success((Object)orderListInfoVo);
         }
      } catch (Exception e) {
         ajaxResult = AjaxResult.error("查询汤剂医嘱本数据出现异常");
         this.log.error("查询汤剂医嘱本数据出现异常，", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('docOrder:list:long,docOrder:list:temp,operRoom:orderList:export,qc:flow:term')")
   @GetMapping({"/longExcel"})
   public AjaxResult longExcel(OrderListSearchVo orderListSearchVo, HttpServletResponse httpResponse) {
      AjaxResult ajaxResult = AjaxResult.success("导出成功");
      boolean flag = true;

      try {
         if (flag && StringUtils.isBlank(orderListSearchVo.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag) {
            String babyAdmissionNo = orderListSearchVo.getBabyAdmissionNo();
            babyAdmissionNo = StringUtils.isNotBlank(babyAdmissionNo) && babyAdmissionNo.equals(orderListSearchVo.getPatientId()) ? null : babyAdmissionNo;
            orderListSearchVo.setBabyAdmissionNo(babyAdmissionNo);
            String allFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("004702");
            return this.tdPaOrderListService.getExcelOrderTotal(orderListSearchVo, httpResponse, allFlag);
         }
      } catch (Exception e) {
         ajaxResult = AjaxResult.error("导出医嘱本出现异常");
         this.log.error("导出医嘱本出现异常，", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('docOrder:list:decoction,operRoom:orderList:export')")
   @GetMapping({"/decoctionExcel"})
   public AjaxResult decoctionExcel(OrderListSearchVo orderListSearchVo, HttpServletResponse httpResponse) {
      AjaxResult ajaxResult = AjaxResult.success("导出成功");
      boolean flag = true;

      try {
         if (flag && StringUtils.isBlank(orderListSearchVo.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag) {
            String babyAdmissionNo = orderListSearchVo.getBabyAdmissionNo();
            babyAdmissionNo = StringUtils.isNotBlank(babyAdmissionNo) && babyAdmissionNo.equals(orderListSearchVo.getPatientId()) ? null : babyAdmissionNo;
            orderListSearchVo.setBabyAdmissionNo(babyAdmissionNo);
            return this.tdPaOrderListService.getDecoctionExcelTotal(orderListSearchVo, httpResponse);
         }
      } catch (Exception e) {
         ajaxResult = AjaxResult.error("导出汤剂医嘱本出现异常");
         this.log.error("导出汤剂医嘱本出现异常，", e);
      }

      return ajaxResult;
   }

   @ResponseBody
   @PreAuthorize("@ss.hasAnyPermi('docOrder:list:oderPerformDetail')")
   @PostMapping({"/oderPerformDetail"})
   public AjaxResult oderPerform(@RequestBody OderPerformDetailReq req) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      String orderNo = req.getOrderNo();
      if (StringUtils.isEmpty(orderNo)) {
         ajaxResult = AjaxResult.error("医嘱编号不能为空");
         return ajaxResult;
      } else {
         String orderType = req.getOrderType();
         if (StringUtils.isEmpty(orderType)) {
            ajaxResult = AjaxResult.error("医嘱类型不能为空");
            return ajaxResult;
         } else if (StringUtils.isEmpty(req.getHerbalFlag())) {
            ajaxResult = AjaxResult.error("是否草药不能为空！");
            return ajaxResult;
         } else {
            try {
               OderPerformResultVo resultVo = this.tdPaOrderListService.selectOderPerform(req);
               ajaxResult.put("data", resultVo);
            } catch (Exception e) {
               ajaxResult = AjaxResult.error("查询医嘱本 执行记录异常！请联系管理员");
               this.log.error("查询医嘱本 执行记录异常！请联系管理员", e);
            }

            return ajaxResult;
         }
      }
   }

   @ResponseBody
   @PreAuthorize("@ss.hasAnyPermi('docOrder:list:uLongStopTime')")
   @PostMapping({"/uLongStopTime"})
   public AjaxResult updateLongStopTime(@RequestBody TdPaOrderItem tdPaOrderItem) {
      AjaxResult ajaxResult = new AjaxResult();
      String msg = "修改成功！";
      boolean flag = true;

      try {
         if (flag && tdPaOrderItem == null) {
            flag = false;
            msg = "参数不能为空！";
            ajaxResult = AjaxResult.error(msg);
         }

         if (flag && StringUtils.isBlank(tdPaOrderItem.getPatientId())) {
            flag = false;
            msg = "参数不能为空！";
            ajaxResult = AjaxResult.error(msg);
         }

         SysUser sysUser = SecurityUtils.getLoginUser().getUser();
         EmrQcFlow emrQcFlow = flag ? this.emrQcFlowService.selectEmrQcFlowById(sysUser.getHospital().getOrgCode(), tdPaOrderItem.getPatientId()) : null;
         if (flag && emrQcFlow != null && StringUtils.isNotBlank(emrQcFlow.getMrState()) && emrQcFlow.getMrState().equals("14")) {
            flag = false;
            ajaxResult = AjaxResult.error("当前患者已归档，不可以再修改！");
         }

         if (flag && emrQcFlow != null && StringUtils.isNotBlank(emrQcFlow.getMrState()) && emrQcFlow.getMrState().equals("16")) {
            flag = false;
            ajaxResult = AjaxResult.error("当前患者已归档，不可以再修改！");
         }

         if (flag && StringUtils.isBlank(tdPaOrderItem.getOrderNo())) {
            flag = false;
            msg = "医嘱编码不能为空！";
            ajaxResult = AjaxResult.error(msg);
         }

         if (flag && tdPaOrderItem.getOrderStopTime() == null) {
            flag = false;
            msg = "停止时间不能为空！";
            ajaxResult = AjaxResult.error(msg);
         }

         TdPaOrderItemVo param = new TdPaOrderItemVo();
         param.setOrderNo(tdPaOrderItem.getOrderNo());
         List<TdPaOrderItemVo> orderItemList = flag ? this.tdPaOrderItemService.selectTdPaOrderItemListAll(param) : null;
         if (flag && CollectionUtils.isEmpty(orderItemList)) {
            flag = false;
            msg = "没有这个医嘱记录，请确认后再操作！";
            ajaxResult = AjaxResult.error(msg);
         }

         if (flag) {
            List<TdPaOrderItem> noList2 = (List)orderItemList.stream().filter((t) -> StringUtils.isNotBlank(t.getOrderType()) && t.getOrderType().equals("2")).collect(Collectors.toList());
            List<TdPaOrderItem> noList1 = (List)orderItemList.stream().filter((t) -> StringUtils.isNotBlank(t.getOrderStatus()) && !t.getOrderStatus().equals("4") && !t.getOrderStatus().equals("5")).collect(Collectors.toList());
            if (flag && CollectionUtils.isNotEmpty(noList2)) {
               flag = false;
               msg = "不是长期医嘱，请确认后再操作！";
               ajaxResult = AjaxResult.error(msg);
            }

            if (flag && CollectionUtils.isNotEmpty(noList1)) {
               flag = false;
               msg = "医嘱的状态不是停止或停止确认，不能修改停止时间！";
               ajaxResult = AjaxResult.error(msg);
            }
         }

         if (flag) {
            List<TdPaOrderItem> noList1 = (List)orderItemList.stream().filter((t) -> tdPaOrderItem.getOrderStopTime().compareTo(t.getOrderStartTime()) < 0).collect(Collectors.toList());
            if (flag && CollectionUtils.isNotEmpty(noList1)) {
               flag = false;
               msg = "医嘱的停止时间不能小于等于医嘱的开立时间，请修改后再提交！";
               ajaxResult = AjaxResult.error(msg);
            }

            List var14 = (List)orderItemList.stream().filter((t) -> t.getOrderExecuteTime() != null && tdPaOrderItem.getOrderStopTime().compareTo(t.getOrderExecuteTime()) < 0).collect(Collectors.toList());
         }

         if (flag) {
            TdPaOrderItemVo updateParam = new TdPaOrderItemVo();
            updateParam.setOrderNo(tdPaOrderItem.getOrderNo());
            updateParam.setOrderStopTime(tdPaOrderItem.getOrderStopTime());
            updateParam.setTableName(((TdPaOrderItemVo)orderItemList.get(0)).getTableName());
            this.tdPaOrderItemService.updateTdPaOrderItemByTableName(updateParam);
            ajaxResult = AjaxResult.success(msg);
         }
      } catch (Exception e) {
         this.log.error("长期医嘱本修改停止时间出现异常，", e);
         ajaxResult = AjaxResult.error("长期医嘱本修改停止时间出现异常，请联系管理员！");
      }

      return ajaxResult;
   }
}
