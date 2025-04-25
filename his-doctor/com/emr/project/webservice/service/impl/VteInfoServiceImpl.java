package com.emr.project.webservice.service.impl;

import com.emr.common.utils.StringUtils;
import com.emr.project.operation.domain.Medicalinformation;
import com.emr.project.operation.service.IMedicalinfoService;
import com.emr.project.pat.domain.vo.VisitinfoPersonalVo;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.system.domain.SysOrg;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.system.service.ISysOrgService;
import com.emr.project.webservice.domain.req.VteInfoReq;
import com.emr.project.webservice.domain.resp.WebServiceVteResp;
import com.emr.project.webservice.service.IVteInfoWebService;
import com.emr.project.webservice.service.VteInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VteInfoServiceImpl implements VteInfoService {
   private final Logger log = LoggerFactory.getLogger(VteInfoServiceImpl.class);
   @Autowired
   private IMedicalinfoService medicalinfoService;
   @Autowired
   private IVteInfoWebService vteInfoService;
   @Autowired
   private ISysOrgService sysOrgService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private IVisitinfoService visitinfoService;

   public WebServiceVteResp getVteInfo(VteInfoReq vteInfoReq) {
      WebServiceVteResp webServiceResp = new WebServiceVteResp();

      try {
         Medicalinformation medicalinformation = this.medicalinfoService.selectMedicalinfoByPatientId(vteInfoReq.getHisPatientId());
         if (!StringUtils.isNotBlank(vteInfoReq.getHisPatientId()) || !StringUtils.isNotBlank(vteInfoReq.getAssessmentOpportunityKey()) || medicalinformation == null) {
            webServiceResp.setCode(String.valueOf(500));
            webServiceResp.setMessage("患者信息不存在！请确认后重试");
            return webServiceResp;
         }

         SysOrg sysOrg = this.sysOrgService.checkOrgCodeUnique(medicalinformation.getHospitalCode());
         WebServiceVteResp resp = null;
         if (sysOrg != null && StringUtils.isNotBlank(sysOrg.getChsCode())) {
            vteInfoReq.setHospitalCode(sysOrg.getChsCode());
            resp = this.vteInfoService.getVteInfoWebApi(vteInfoReq);
         }

         if (resp != null) {
            webServiceResp.setCode(resp.getCode());
            webServiceResp.setMessage(resp.getMessage());
         } else {
            webServiceResp.setCode(String.valueOf(500));
            webServiceResp.setMessage("国家医保编码未配置");
         }
      } catch (Exception e) {
         this.log.error("查询vte信息出现异常！", e);
         webServiceResp.setMessage("查询vte信息出现异常！");
      }

      return webServiceResp;
   }

   public WebServiceVteResp vteOrderVerify(String patientId) throws Exception {
      WebServiceVteResp vteResp = null;
      String vteFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0098");
      String vteOrderFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("009803");
      String vteName = this.sysEmrConfigService.selectSysEmrConfigByKey("009802");
      boolean otherInfoFlag = true;
      if (vteName.equals("NTKWH")) {
         VisitinfoPersonalVo personalVo = this.visitinfoService.selectVisitinfoPersonalById(patientId);
         otherInfoFlag = personalVo.getAgeY() != null && personalVo.getAgeY() >= 15L;
      }

      if (StringUtils.isNotBlank(vteFlag) && StringUtils.isNotBlank(vteOrderFlag) && vteFlag.equals("1") && vteOrderFlag.equals("1") && otherInfoFlag) {
         VteInfoReq vteInfoReq = new VteInfoReq();
         vteInfoReq.setHisPatientId(patientId);
         vteInfoReq.setAssessmentOpportunityKey("1");
         vteResp = this.getVteInfo(vteInfoReq);
      }

      return vteResp;
   }
}
