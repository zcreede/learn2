package com.emr.project.docOrder.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.enums.DataSourceType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.docOrder.domain.vo.PhysignDayVo;
import com.emr.project.docOrder.domain.vo.PhysignValueVo;
import com.emr.project.docOrder.service.IPhysignValueService;
import com.emr.project.system.domain.SyncDatasource;
import com.emr.project.system.service.ISyncDatasourceService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/docOrder/physignValue"})
public class PhysignValueController extends BaseController {
   @Resource
   private ISyncDatasourceService syncDatasourceService;
   @Resource
   private IPhysignValueService physignValueService;

   @PreAuthorize("@ss.hasAnyPermi('docOrder:physignValue:list,emr:index:helper')")
   @GetMapping({"/list"})
   public TableDataInfo list(PhysignValueVo physignValueVo) {
      new AjaxResult();
      List<PhysignValueVo> list = new ArrayList(1);

      try {
         if (StringUtils.isEmpty(String.valueOf(physignValueVo.getBaseid()))) {
            AjaxResult var6 = AjaxResult.error("参数不能为空");
         } else {
            SyncDatasource syncDatasource = this.syncDatasourceService.selectSyncDatasourceByCode(DataSourceType.physignValueList.toString());
            if (syncDatasource != null) {
               physignValueVo.setSqlStr(syncDatasource.getQuerySql());
               this.startPage();
               list = this.physignValueService.getValueList(physignValueVo);
               AjaxResult var7 = AjaxResult.success((Object)list);
            }
         }
      } catch (Exception e) {
         this.log.error("查询体征信息出现异常,", e);
         AjaxResult ajaxResult = AjaxResult.error("查询体征信息出现异常");
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasAnyPermi('docOrder:physignValue:dayList,emr:index:helper,td:apply:add')")
   @GetMapping({"/dayList"})
   public TableDataInfo dayList(PhysignDayVo physignDayVo) {
      new AjaxResult();
      List<PhysignDayVo> list = new ArrayList(1);

      try {
         if (StringUtils.isEmpty(String.valueOf(physignDayVo.getInpNo()))) {
            AjaxResult var6 = AjaxResult.error("参数不能为空");
         } else {
            SyncDatasource syncDatasource = this.syncDatasourceService.selectSyncDatasourceByCode(DataSourceType.physignDayList.toString());
            if (syncDatasource != null) {
               physignDayVo.setSqlStr(syncDatasource.getQuerySql());
               physignDayVo.setZyh(physignDayVo.getInpNo());
               this.startPage();
               list = this.physignValueService.getDayList(physignDayVo);
               AjaxResult var7 = AjaxResult.success((Object)list);
            }
         }
      } catch (Exception e) {
         this.log.error("查询体征信息出现异常,", e);
         AjaxResult ajaxResult = AjaxResult.error("查询体征信息出现异常");
      }

      return this.getDataTable(list);
   }
}
