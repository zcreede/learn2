package com.emr.project.his.controller;

import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.his.service.IHisSyncService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(
   value = "HisDeptController",
   tags = {"同步HIS数据"}
)
@RestController
@RequestMapping({"/his/sync"})
public class HisSyncController {
   @Autowired
   private IHisSyncService hisSyncService;

   @ApiOperation("查询所有的HIS部门")
   @PreAuthorize("@ss.hasPermi('admin')")
   @GetMapping({"/gethisDepts"})
   public AjaxResult queryDeptAll() {
      AjaxResult ajaxResult = AjaxResult.success();
      List<Map<String, Object>> deptList = this.hisSyncService.selectDeptALL();
      ajaxResult.put("deptList", deptList);
      return ajaxResult;
   }
}
