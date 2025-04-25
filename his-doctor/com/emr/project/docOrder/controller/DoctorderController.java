package com.emr.project.docOrder.controller;

import com.emr.common.utils.DateUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.aspectj.lang.enums.DataSourceType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.PageDomain;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.framework.web.page.TableSupport;
import com.emr.project.docOrder.domain.Doctorder;
import com.emr.project.docOrder.domain.vo.DoctorderVo;
import com.emr.project.docOrder.domain.vo.OrderSearchVo;
import com.emr.project.docOrder.service.IDoctorderService;
import com.emr.project.docOrder.service.ITdPaOrderService;
import com.emr.project.pat.domain.BabyInfo;
import com.emr.project.system.domain.SyncDatasource;
import com.emr.project.system.service.ISyncDatasourceService;
import com.emr.project.system.service.ISysEmrConfigService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping({"/docOrder/doctorder"})
public class DoctorderController extends BaseController {
   @Autowired
   private IDoctorderService doctorderService;
   @Autowired
   private ISyncDatasourceService syncDatasourceService;
   @Autowired
   private ITdPaOrderService tdPaOrderService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;

   @PreAuthorize("@ss.hasAnyPermi('docOrder:doctorder:list,emr:index:helper')")
   @GetMapping({"/hislist"})
   public TableDataInfo hislist(OrderSearchVo queryParam) {
      new TableDataInfo();
      new ArrayList(1);

      TableDataInfo rspData;
      try {
         if (StringUtils.isEmpty(queryParam.getPatientId())) {
            rspData = new TableDataInfo(500, "患者id不能为空");
         } else {
            if (queryParam.getOrderStartTimeEnd() != null) {
               queryParam.setOrderStartTimeEnd(DateUtils.addDays(queryParam.getOrderStartTimeEnd(), 1));
            }

            String orderFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0050");
            SyncDatasource syncDatasource = this.syncDatasourceService.selectSyncDatasourceByCode(DataSourceType.searchHisDocorder.toString());
            if (orderFlag.equals("1")) {
               queryParam.setSqlStr(syncDatasource.getQuerySql());
               List list = this.doctorderService.selectHisDocorderVoList(queryParam);
               rspData = this.getDataTable(list);
               List<OrderSearchVo> listAll = this.doctorderService.selectHisSubDocorderVoList(queryParam, list);
               list.clear();
               list.addAll(listAll);
            } else {
               if (com.emr.common.utils.StringUtils.isNotBlank(queryParam.getOrderStatus()) && queryParam.getOrderStatus().length() > 0) {
                  String orderStatus = queryParam.getOrderStatus();
                  queryParam.setOrderStatus((String)null);
                  List<String> orderStatusList = Arrays.asList(orderStatus.split(","));
                  queryParam.setStatusList(orderStatusList);
               }

               queryParam.setCommitFlag("1");
               this.startPage();
               List var9 = this.tdPaOrderService.selectOrderSearchVoList(queryParam);
               rspData = this.getDataTable(var9);
               List<OrderSearchVo> pageList = this.tdPaOrderService.selectSubOrderSearchVoList(var9);
               var9.clear();
               var9.addAll(pageList);
            }
         }
      } catch (Exception e) {
         this.log.error("查询医嘱信息列表出现异常：", e);
         rspData = new TableDataInfo(500, "查询医嘱信息列表出现异常");
      }

      return rspData;
   }

   public TableDataInfo hislist_bak(DoctorderVo doctorderVo) {
      TableDataInfo rspData = null;
      List<DoctorderVo> list = new ArrayList(1);

      try {
         String beginTimeStr = doctorderVo.getBeginTimeStr();
         String endTimeStr = doctorderVo.getEndTimeStr();
         if (StringUtils.isNotBlank(beginTimeStr)) {
            beginTimeStr = beginTimeStr + " 00:00:00";
            doctorderVo.setBeginTimeStr(beginTimeStr);
         }

         if (StringUtils.isNotBlank(endTimeStr)) {
            Date endDate = DateUtils.parseDate(endTimeStr, new String[]{DateUtils.YYYY_MM_DD});
            endDate = DateUtils.addDays(endDate, 1);
            endTimeStr = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, endDate);
            endTimeStr = endTimeStr + " 00:00:00";
            doctorderVo.setEndTimeStr(endTimeStr);
         }

         if (StringUtils.isNotBlank(doctorderVo.getPatientId())) {
            SyncDatasource syncDatasource = this.syncDatasourceService.selectSyncDatasourceByCode(DataSourceType.searchHisDocorder.toString());
            doctorderVo.setSqlStr(syncDatasource.getQuerySql());
         }

         if (StringUtils.isNotBlank(doctorderVo.getBelongFlag())) {
            List<BabyInfo> babyInfoList = this.doctorderService.selectBabyInfoByPatientId(doctorderVo.getPatientId());
            List<String> patientIds = (List)babyInfoList.stream().map((t) -> t.getPatBabyId()).collect(Collectors.toList());
            doctorderVo.setPatientIds(patientIds);
            doctorderVo.setPatientId((String)null);
         }

         PageDomain pageDomain = TableSupport.buildPageRequest();
         Integer pageNum = pageDomain.getPageNum();
         Integer pageSize = pageDomain.getPageSize();
         List var12 = this.doctorderService.selectHisDocorderList(doctorderVo, pageNum, pageSize);
         rspData = this.getDataTable(var12);
         List<DoctorderVo> pageList = this.doctorderService.selectHisDocorderChildList(var12, doctorderVo);
         var12.clear();
         var12.addAll(pageList);
      } catch (Exception e) {
         this.log.error("查询医嘱信息列表出现异常：", e);
         rspData = this.getDataTable(list);
      }

      return rspData;
   }

   @PreAuthorize("@ss.hasPermi('docOrder:doctorder:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(Doctorder doctorder) {
      this.startPage();
      List<Doctorder> list = this.doctorderService.selectDoctorderList(doctorder);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('docOrder:doctorder:export')")
   @Log(
      title = "医嘱信息",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(Doctorder doctorder) {
      List<Doctorder> list = this.doctorderService.selectDoctorderList(doctorder);
      ExcelUtil<Doctorder> util = new ExcelUtil(Doctorder.class);
      return util.exportExcel(list, "医嘱信息数据");
   }

   @PreAuthorize("@ss.hasPermi('docOrder:doctorder:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") String id) {
      return AjaxResult.success((Object)this.doctorderService.selectDoctorderById(id));
   }

   @PreAuthorize("@ss.hasPermi('docOrder:doctorder:add')")
   @Log(
      title = "医嘱信息",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody Doctorder doctorder) {
      return this.toAjax(this.doctorderService.insertDoctorder(doctorder));
   }

   @PreAuthorize("@ss.hasPermi('docOrder:doctorder:edit')")
   @Log(
      title = "医嘱信息",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody Doctorder doctorder) {
      return this.toAjax(this.doctorderService.updateDoctorder(doctorder));
   }

   @PreAuthorize("@ss.hasPermi('docOrder:doctorder:remove')")
   @Log(
      title = "医嘱信息",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable String[] ids) {
      return this.toAjax(this.doctorderService.deleteDoctorderByIds(ids));
   }

   @PreAuthorize("@ss.hasPermi('docOrder:doctorder:list')")
   @GetMapping({"/belongList/{patientId}"})
   public AjaxResult patBabyInfoList(@PathVariable("patientId") String patientId) {
      AjaxResult ajaxResult = null;

      try {
         List<BabyInfo> list = this.doctorderService.selectBabyInfoByPatientId(patientId);
         ajaxResult = AjaxResult.success((Object)list);
      } catch (Exception e) {
         this.log.error("查询医嘱所属列表出现异常：", e);
         ajaxResult = AjaxResult.error("查询医嘱所属列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('docOrder:doctorder:getHisDoctorderVoList,emr:index:helper,qc:flow:term,td:apply:add')")
   @GetMapping({"/getHisDoctorderVoList"})
   public TableDataInfo getHisDoctorderVoList(OrderSearchVo queryParam) {
      new TableDataInfo();
      new ArrayList(1);

      TableDataInfo rspData;
      try {
         if (StringUtils.isEmpty(queryParam.getPatientId())) {
            rspData = new TableDataInfo(500, "患者id不能为空");
         } else {
            if (queryParam.getOrderStartTimeEnd() != null) {
               queryParam.setOrderStartTimeEnd(DateUtils.addDays(queryParam.getOrderStartTimeEnd(), 1));
            }

            String orderFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0050");
            SyncDatasource syncDatasource = this.syncDatasourceService.selectSyncDatasourceByCode(DataSourceType.searchHisDocorder.toString());
            if (orderFlag.equals("1")) {
               queryParam.setSqlStr(syncDatasource.getQuerySql());
               List list = this.doctorderService.selectHisDocorderVoList(queryParam);
               rspData = this.getDataTable(list);
               if (CollectionUtils.isNotEmpty(list)) {
                  list.stream().forEach((t) -> t.setOrderGroupSortNumber("01"));
               }

               List<OrderSearchVo> listAll = this.doctorderService.selectHisSubDocorderVoList(queryParam, list);
               list.clear();
               list.addAll(listAll);
            } else {
               if (com.emr.common.utils.StringUtils.isNotBlank(queryParam.getOrderStatus()) && queryParam.getOrderStatus().length() > 0) {
                  String orderStatus = queryParam.getOrderStatus();
                  queryParam.setOrderStatus((String)null);
                  List<String> orderStatusList = Arrays.asList(orderStatus.split(","));
                  queryParam.setStatusList(orderStatusList);
               }

               queryParam.setCommitFlag("1");
               this.startPage();
               List var9 = this.tdPaOrderService.selectOrderSearchVoList(queryParam);
               rspData = this.getDataTable(var9);
               List<OrderSearchVo> pageList = this.tdPaOrderService.selectSubOrderSearchVoList(var9);
               var9.clear();
               var9.addAll(pageList);
            }
         }
      } catch (Exception e) {
         this.log.error("查询医嘱信息列表出现异常：", e);
         rspData = new TableDataInfo(500, "查询医嘱信息列表出现异常");
      }

      return rspData;
   }
}
