package com.emr.project.docOrder.controller;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.CDSS.xyt.RequestUtil;
import com.emr.project.docOrder.domain.req.DipCfPageReq;
import com.emr.project.docOrder.service.DipIntService;
import com.emr.project.mrhp.domain.resp.MrHpRecordDipResp;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysEmrConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/doctor/dip"})
public class DipController extends BaseController {
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private DipIntService dipIntService;

   @PreAuthorize("@ss.hasAnyPermi('doctor:dip:list')")
   @Log(
      title = "调用dip医保控费界面",
      businessType = BusinessType.INSERT
   )
   @PostMapping({"/dipCfPage"})
   public AjaxResult dipCfPage(@RequestBody DipCfPageReq req) {
      AjaxResult ajaxResult = AjaxResult.success("调用成功");
      boolean flag = true;

      try {
         if (flag && StringUtils.isBlank(req.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id参数不能为空");
         }

         if (flag) {
            try {
               String dipFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("007402");
               if (dipFlag != null && "1".equals(dipFlag)) {
                  String dipFactory = this.sysEmrConfigService.selectSysEmrConfigByKey("007401");
                  if ("LBDZ".equals(dipFactory)) {
                     SysUser user = SecurityUtils.getLoginUser().getUser();
                     req.setOrgCode(user.getHospital().getChsCode());
                     req.setUserCode(user.getUserName());
                     req.setDeptCode(user.getDeptCode());
                     String methord = RequestUtil.DIP_CF_PAGE;
                     MrHpRecordDipResp dipResp = this.dipIntService.selectDipCfPage(req, methord);
                     if (dipResp != null && 200 == dipResp.getCode()) {
                        ajaxResult = AjaxResult.success("调用成功", dipResp.getData());
                        ajaxResult.put("dipUrl", dipResp.getData());
                     } else {
                        this.log.error("调用dip医保控费界面失败返回信息", dipResp.getMsg());
                     }
                  }
               }
            } catch (Exception e) {
               this.log.error("调用dip医保控费界面异常，请联系系统管理员", e);
            }
         }
      } catch (Exception e) {
         this.log.error("调用dip医保控费界面出现异常", e);
         ajaxResult = AjaxResult.error("调用dip医保控费界面出现异常");
      }

      return ajaxResult;
   }
}
