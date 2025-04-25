package com.emr.project.webservice.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.operation.domain.Medicalinformation;
import com.emr.project.operation.service.IMedicalinfoService;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.webservice.domain.req.DipServiceReq;
import com.emr.project.webservice.domain.resp.WebServiceResp;
import com.emr.project.webservice.service.IDipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/dip"})
public class DIPController extends BaseController {
   @Autowired
   private IDipService dipService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private IMedicalinfoService medicalinfoService;

   @PostMapping({"/updateDIP"})
   @PreAuthorize("@ss.hasAnyPermi('mrhp:hp:dipPath,qc:rt:checkpatientList,qc:flow:dept,qc:flow:term,pat:visitinfo:mrhp')")
   public AjaxResult updateDIP(@RequestBody DipServiceReq req) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         String drugFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0074");
         if (StringUtils.isEmpty(drugFlag) || drugFlag.equals("0")) {
            ajaxResult = AjaxResult.error("DIP未配置或未开启！");
            return ajaxResult;
         }

         if (StringUtils.isEmpty(req.getAdmissionNo())) {
            ajaxResult = AjaxResult.error("住院号不能为空");
            return ajaxResult;
         }

         if (StringUtils.isEmpty(req.getCheckType())) {
            ajaxResult = AjaxResult.error("校验类型不能为空！");
            return ajaxResult;
         }

         Medicalinformation medicalinformation = this.medicalinfoService.selectMedicalinfo(req.getAdmissionNo(), req.getHospitalizedCount());
         if (medicalinformation == null) {
            ajaxResult = AjaxResult.error("患者信息不存在！请确认后重试");
            return ajaxResult;
         }

         String mark = null;
         String hospitalMark = medicalinformation.getHospitalMark();
         if (!hospitalMark.equals("1") && !hospitalMark.equals("2") && !hospitalMark.equals("0")) {
            mark = "1";
         } else {
            mark = "0";
         }

         WebServiceResp resp = this.dipService.updateDIP(req, mark);
         ajaxResult.put("data", resp);
      } catch (Exception e) {
         this.log.error("查询DIP 分组出现异常！", e);
         ajaxResult = AjaxResult.error("查询DIP 分组出现异常！");
      }

      return ajaxResult;
   }
}
