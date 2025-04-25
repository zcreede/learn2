package com.emr.project.emr.controller;

import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.emr.domain.vo.EmrTimeoutCaseVo;
import com.emr.project.emr.service.IEmrTimeoutCaseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/emr/timeoutCase"})
public class EmrTimeoutCaseController extends BaseController {
   @Autowired
   private IEmrTimeoutCaseService emrTimeoutCaseService;

   @PreAuthorize("@ss.hasAnyPermi('emr:timeoutCase:list')")
   @GetMapping({"/getTimeoutCaseList"})
   public TableDataInfo getTimeoutCaseList(EmrTimeoutCaseVo timeoutCaseVo) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<EmrTimeoutCaseVo> list = this.emrTimeoutCaseService.getTimeoutCaseList(timeoutCaseVo);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("超时病历查询出现异常", e);
         tableDataInfo = new TableDataInfo(200, "超时病历查询出现异常，请联系系统管理员");
      }

      return tableDataInfo;
   }
}
