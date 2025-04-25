package com.emr.project.webservice.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.operation.domain.Medicalinformation;
import com.emr.project.operation.service.IMedicalinfoService;
import com.emr.project.system.domain.SysOrg;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.system.service.ISysOrgService;
import com.emr.project.webservice.domain.req.VteInfoReq;
import com.emr.project.webservice.domain.resp.WebServiceVteResp;
import com.emr.project.webservice.service.IVteInfoWebService;
import com.emr.project.webservice.service.VteInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/vte"})
public class VTEController extends BaseController {
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private IMedicalinfoService medicalinfoService;
   @Autowired
   private IVteInfoWebService vteInfoWebService;
   @Autowired
   private VteInfoService infoService;
   @Autowired
   private ISysOrgService sysOrgService;

   @GetMapping({"/getVteInfo"})
   public AjaxResult getVteInfo(VteInfoReq vteInfoReq) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         String vteFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0098");
         if (StringUtils.isEmpty(vteFlag) || vteFlag.equals("0")) {
            ajaxResult = AjaxResult.error("VTE配置未开启！");
            return ajaxResult;
         }

         if (StringUtils.isEmpty(vteInfoReq.getHisPatientId())) {
            ajaxResult = AjaxResult.error("住院号不能为空");
            return ajaxResult;
         }

         if (StringUtils.isEmpty(vteInfoReq.getAssessmentOpportunityKey())) {
            ajaxResult = AjaxResult.error("评估时机不能为空！");
            return ajaxResult;
         }

         Medicalinformation medicalinformation = this.medicalinfoService.selectMedicalinfoByPatientId(vteInfoReq.getHisPatientId());
         if (medicalinformation == null) {
            ajaxResult = AjaxResult.error("患者信息不存在！请确认后重试");
            return ajaxResult;
         }

         WebServiceVteResp resp = this.vteInfoWebService.getVteInfoWebApi(vteInfoReq);
         ajaxResult.put("data", resp.getData());
         ajaxResult.put("code", resp.getCode());
         ajaxResult.put("msg", resp.getMessage());
      } catch (Exception e) {
         this.log.error("查询vte信息出现异常！", e);
         ajaxResult = AjaxResult.error("查询vte信息出现异常！");
      }

      return ajaxResult;
   }

   @GetMapping({"/getVteInfoWeb"})
   public WebServiceVteResp getVteInfoWeb(VteInfoReq vteInfoReq) {
      WebServiceVteResp webServiceResp = new WebServiceVteResp();

      try {
         Medicalinformation medicalinformation = this.medicalinfoService.selectMedicalinfoByPatientId(vteInfoReq.getHisPatientId());
         if (!StringUtils.isNotBlank(vteInfoReq.getHisPatientId()) || !StringUtils.isNotBlank(vteInfoReq.getAssessmentOpportunityKey()) || medicalinformation == null) {
            webServiceResp.setCode(String.valueOf(500));
            webServiceResp.setMessage("患者信息不存在！请确认后重试");
            return webServiceResp;
         }

         SysOrg sysOrg = this.sysOrgService.checkOrgCodeUnique(medicalinformation.getHospitalCode());
         if (sysOrg != null) {
            vteInfoReq.setHospitalCode(sysOrg.getChsCode());
         }

         WebServiceVteResp resp = this.infoService.getVteInfo(vteInfoReq);
         if (resp != null && String.valueOf(200).equals(resp.getCode())) {
            webServiceResp.setCode(resp.getCode());
            webServiceResp.setMessage(resp.getMessage());
         } else {
            webServiceResp.setCode(String.valueOf(500));
            webServiceResp.setMessage(resp.getMessage());
         }
      } catch (Exception e) {
         this.log.error("查询vte信息出现异常！", e);
         webServiceResp.setMessage("查询vte信息出现异常！");
      }

      return webServiceResp;
   }
}
