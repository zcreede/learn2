package com.emr.project.system.controller;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.WorkLoadMain;
import com.emr.project.system.domain.WorkLoadOther;
import com.emr.project.system.domain.req.WorkLoadOtherReq;
import com.emr.project.system.service.IWorkLoadMainService;
import com.emr.project.system.service.IWorkLoadOtherService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/mris"})
public class WorkLoadOtherController extends BaseController {
   @Autowired
   private IWorkLoadOtherService workLoadOtherService;
   @Autowired
   private IWorkLoadMainService workLoadMainService;

   @PreAuthorize("@ss.hasPermi('mris:other:list')")
   @GetMapping({"/other/list"})
   public AjaxResult list(WorkLoadOther workLoadOther) {
      AjaxResult ajaxResult = AjaxResult.success();
      if (workLoadOther.getMainId() == null) {
         ajaxResult = AjaxResult.error("主id不能为空");
         return ajaxResult;
      } else {
         try {
            List<WorkLoadOther> list = this.workLoadOtherService.selectWorkLoadOtherList(workLoadOther);
            ajaxResult.put("data", list);
         } catch (Exception e) {
            this.log.error("查询住院科室工作量上报其他项目列表出现异常，", e);
            ajaxResult = AjaxResult.error("查询住院科室工作量上报其他项目列表出现异常，请联系管理员！");
         }

         return ajaxResult;
      }
   }

   @PreAuthorize("@ss.hasPermi('mris:other:add')")
   @Log(
      title = "住院科室工作量上报其他项目",
      businessType = BusinessType.INSERT
   )
   @PostMapping({"/other"})
   public AjaxResult add(@RequestBody WorkLoadOtherReq req) {
      AjaxResult ajaxResult = AjaxResult.success();
      if (req.getMainId() == null) {
         ajaxResult = AjaxResult.error("主id不能为空！");
         return ajaxResult;
      } else {
         WorkLoadMain workLoadMain = this.workLoadMainService.selectWorkLoadMainById(req.getMainId());
         if (workLoadMain == null) {
            ajaxResult = AjaxResult.error("工作量主数据不存在！");
            return ajaxResult;
         } else if (workLoadMain.getStatus().equals("1")) {
            ajaxResult = AjaxResult.error("工作量已上报，不允许新增！");
            return ajaxResult;
         } else {
            List<WorkLoadOther> otherList = req.getOtherList();
            if (otherList.isEmpty()) {
               ajaxResult = AjaxResult.error("录入项目不能为空！");
               return ajaxResult;
            } else {
               SysUser loginUser = SecurityUtils.getLoginUser().getUser();
               String orgCode = loginUser.getHospital().getOrgCode();

               for(int i = 0; i < otherList.size(); ++i) {
                  WorkLoadOther workLoadOther = (WorkLoadOther)otherList.get(i);
                  workLoadOther.setMainId(req.getMainId());
                  workLoadOther.setOrgCd(orgCode);
                  if (StringUtils.isEmpty(workLoadOther.getItemCode())) {
                     ajaxResult = AjaxResult.error("第" + (i + 1) + "个，项目编码不能为空！");
                     return ajaxResult;
                  }

                  if (StringUtils.isEmpty(workLoadOther.getItemName())) {
                     ajaxResult = AjaxResult.error("第" + (i + 1) + "个，项目名称不能为空！");
                     return ajaxResult;
                  }

                  if (workLoadOther.getItemNum() == null) {
                     ajaxResult = AjaxResult.error("第" + (i + 1) + "个，项目数量不能为空！");
                     return ajaxResult;
                  }
               }

               try {
                  this.workLoadOtherService.insertWorkLoadOther(otherList);
               } catch (Exception e) {
                  this.log.error("新增住院科室工作量上报其他项目出现异常，", e);
                  ajaxResult = AjaxResult.error("新增住院科室工作量上报其他项目出现异常，请联系管理员！");
               }

               return ajaxResult;
            }
         }
      }
   }
}
