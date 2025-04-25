package com.emr.project.webservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.emr.common.utils.StringUtils;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.webservice.domain.req.VteInfoReq;
import com.emr.project.webservice.domain.resp.WebServiceVteResp;
import com.emr.project.webservice.service.IVteInfoWebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VteInfoWebServiceImpl implements IVteInfoWebService {
   private final Logger log = LoggerFactory.getLogger(VteInfoWebServiceImpl.class);
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;

   public WebServiceVteResp getVteInfoWebApi(VteInfoReq vteInfoReq) throws Exception {
      WebServiceVteResp resp = null;
      String vteName = this.sysEmrConfigService.selectSysEmrConfigByKey("009802");
      String vteAddress = this.sysEmrConfigService.selectSysEmrConfigByKey("009801");
      if (StringUtils.isNotEmpty(vteAddress) && "NTKWH".equals(vteName)) {
         RestTemplate restTemplate = new RestTemplate();
         String hisPatientId = vteInfoReq.getHisPatientId();
         String hospitalCode = vteInfoReq.getHospitalCode();
         String assessmentOpportunityKey = vteInfoReq.getAssessmentOpportunityKey();
         vteAddress = vteAddress.replaceAll("@hisPatientId", hisPatientId);
         vteAddress = vteAddress.replaceAll("@assessmentOpportunityKey", assessmentOpportunityKey);
         vteAddress = vteAddress.replaceAll("@hospitalCode", hospitalCode);
         ResponseEntity responseEntity = restTemplate.getForEntity(vteAddress, WebServiceVteResp.class, new Object[0]);
         if (responseEntity != null && responseEntity.getBody() != null) {
            resp = (WebServiceVteResp)responseEntity.getBody();
         }

         this.log.info("vte接口返回##############" + JSONObject.toJSONString(resp));
      } else {
         resp.setCode("500");
         resp.setMessage("VTE调用接口未配置，请联系系统管理员进行维护！");
         this.log.error("VTE调用接口未配置，请联系系统管理员进行维护！");
      }

      return resp;
   }
}
