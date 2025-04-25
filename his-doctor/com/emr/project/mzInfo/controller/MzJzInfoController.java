package com.emr.project.mzInfo.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.mzInfo.domain.MzOrderDetail;
import com.emr.project.mzInfo.service.IMzJzInfoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/mz/jzInfo"})
public class MzJzInfoController extends BaseController {
   @Autowired
   private IMzJzInfoService mzJzInfoService;

   @PreAuthorize("@ss.hasAnyPermi('mz:jzInfo:orderList,pat:info:emrAllList')")
   @GetMapping({"/orderList"})
   public AjaxResult list(String visitNo, String mzh, String orderClassCode) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      Boolean flag = true;

      try {
         if (flag && StringUtils.isBlank(mzh)) {
            flag = false;
            ajaxResult = AjaxResult.error("门诊号不能为空！");
         }

         if (flag && StringUtils.isBlank(visitNo)) {
            flag = false;
            ajaxResult = AjaxResult.error("住院id不能为空！");
         }

         if (flag && StringUtils.isBlank(orderClassCode)) {
            flag = false;
            ajaxResult = AjaxResult.error("处方类型不能为空！");
         }

         if (flag) {
            if ("2".equals(orderClassCode)) {
               orderClassCode = "05";
            } else {
               orderClassCode = "01";
            }

            List<MzOrderDetail> list = this.mzJzInfoService.selectMzOrderDetail(visitNo, mzh, orderClassCode);
            ajaxResult = AjaxResult.success("查询成功", list);
         }
      } catch (Exception e) {
         this.log.error("查询门诊处方信息出现异常：", e);
         ajaxResult = AjaxResult.error("查询门诊处方信息出现异常！");
      }

      return ajaxResult;
   }
}
