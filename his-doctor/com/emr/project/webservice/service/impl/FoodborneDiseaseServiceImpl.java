package com.emr.project.webservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.webservice.domain.req.FoodborneReq;
import com.emr.project.webservice.domain.resp.WebServiceFoodborneResp;
import com.emr.project.webservice.service.IFoodborneDiseaseService;
import com.github.pagehelper.util.StringUtil;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FoodborneDiseaseServiceImpl implements IFoodborneDiseaseService {
   private final Logger log = LoggerFactory.getLogger(FoodborneDiseaseServiceImpl.class);
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private IVisitinfoService visitinfoService;

   public WebServiceFoodborneResp getFoodborneDiseaseUrl(FoodborneReq foodborneReq) throws Exception {
      WebServiceFoodborneResp resp = null;
      String isEnable = this.sysEmrConfigService.selectSysEmrConfigByKey("0106");
      String foodborneAddress = this.sysEmrConfigService.selectSysEmrConfigByKey("010604");
      String reportType = this.sysEmrConfigService.selectSysEmrConfigByKey("010601");
      String reportHospitalUrl = this.sysEmrConfigService.selectSysEmrConfigByKey("010602");
      String sentinelHospitalUrl = this.sysEmrConfigService.selectSysEmrConfigByKey("010603");
      if (StringUtils.isNotEmpty(foodborneAddress) && StringUtil.isNotEmpty(isEnable) && "1".equals(isEnable)) {
         RestTemplate restTemplate = new RestTemplate();
         String diagnosisName = foodborneReq.getDiagnosisName();
         foodborneAddress = foodborneAddress.replaceAll("@diagnosisName", diagnosisName);
         ResponseEntity responseEntity = restTemplate.getForEntity(foodborneAddress, WebServiceFoodborneResp.class, new Object[0]);
         if (responseEntity != null && responseEntity.getBody() != null) {
            resp = (WebServiceFoodborneResp)responseEntity.getBody();
            this.log.warn("#####食源性疾病接口resp#####" + resp.toString());
            this.log.warn("#####食源性疾病接口resp#####result{}, fillGuid{}", resp.getResult(), resp.getFillGuid());
            if (StringUtil.isNotEmpty(resp.getResult()) && "true".equals(resp.getResult()) && StringUtil.isNotEmpty(resp.getFillGuid())) {
               VisitinfoVo visitinfoVo = this.visitinfoService.selectVisitinfoById(foodborneReq.getPatientId());
               if (StringUtil.isNotEmpty(reportType)) {
                  if ("1".equals(reportType) && StringUtil.isNotEmpty(sentinelHospitalUrl)) {
                     String url = this.getFoodborneUrl(visitinfoVo, sentinelHospitalUrl, resp.getFillGuid());
                     this.log.warn("#####食源性疾病接口url#####" + url);
                     resp.setFoodborneUrl(url);
                     resp.setStatus("1");
                  } else if ("2".equals(reportType) && StringUtil.isNotEmpty(reportHospitalUrl)) {
                     String url = this.getFoodborneUrl(visitinfoVo, reportHospitalUrl, resp.getFillGuid());
                     this.log.warn("#####食源性疾病接口url#####" + url);
                     resp.setFoodborneUrl(url);
                     resp.setStatus("1");
                  } else {
                     resp.setStatus("0");
                     resp.setResult("食源性接口未配置，请联系系统管理员进行维护！");
                  }
               } else {
                  resp.setStatus("0");
                  resp.setResult("上报医院类型未配置，请联系系统管理员进行维护！");
               }
            } else {
               resp.setStatus("0");
               resp.setResult("不需要弹出嵌入式微服务页面");
            }
         }

         this.log.warn("食源性疾病接口返回##############" + JSONObject.toJSONString(resp));
      } else {
         this.log.error("食源性接口未配置，请联系系统管理员进行维护！");
      }

      return resp;
   }

   public String getFoodborneUrl(VisitinfoVo visitinfoVo, String url, String fillGuid) throws Exception {
      String resultUrl = null;
      Date diseaseDate = visitinfoVo.getInhosTime();
      Date diseaseTreattime = visitinfoVo.getInDeptTime();
      String outPatientNumber = visitinfoVo.getPatientId();
      String patientName = visitinfoVo.getPatientName();
      int diseaseSex = 1;
      if (StringUtil.isNotEmpty(visitinfoVo.getSexCd())) {
         if ("1".equals(visitinfoVo.getSexCd())) {
            diseaseSex = 1;
         } else if ("2".equals(visitinfoVo.getSexCd())) {
            diseaseSex = 0;
         }
      }

      String guarderName = visitinfoVo.getContName();
      int diseaseIsreexam = 0;
      int diseaseIspaint = 0;
      String diseaseHospitalno = "";
      String identityCard = visitinfoVo.getIdCard();
      Date diseaseBirthday = visitinfoVo.getBirDate();
      String phoneNumber = visitinfoVo.getPatientTel();
      String workUnit = "";
      String fillingDoctorName = visitinfoVo.getResDocName();
      String diseaseOccupation = "";
      String medicalInstiSeHospital = visitinfoVo.getOrgCd();
      String diseaseHometown = "";
      String diseaseProvince = visitinfoVo.getNowAddrPro();
      String diseaseCity = visitinfoVo.getNowAddrFlagty();
      String diseaseDistrict = visitinfoVo.getNowAddrCou();
      String diseaseAddress = visitinfoVo.getNowAddr();
      String uName = "";
      url = url.replaceAll("@diseaseDate", diseaseDate != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM, diseaseDate) : "");
      url = url.replaceAll("@diseaseTreattime", diseaseTreattime != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM, diseaseTreattime) : "");
      url = url.replaceAll("@outPatientNumber", outPatientNumber);
      url = url.replaceAll("@patientName", patientName != null ? patientName : "");
      url = url.replaceAll("@diseaseSex", String.valueOf(diseaseSex));
      url = url.replaceAll("@guarderName", guarderName != null ? guarderName : "");
      url = url.replaceAll("@diseaseIsreexam", String.valueOf(diseaseIsreexam));
      url = url.replaceAll("@diseaseIspaint", String.valueOf(diseaseIspaint));
      url = url.replaceAll("@diseaseHospitalno", diseaseHospitalno);
      url = url.replaceAll("@identityCard", identityCard != null ? identityCard : "");
      if (diseaseBirthday != null) {
         url = url.replaceAll("@diseaseBirthday", DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, diseaseBirthday));
      } else {
         url = url.replaceAll("@diseaseBirthday", "");
      }

      url = url.replaceAll("@phoneNumber", phoneNumber != null ? phoneNumber : "");
      url = url.replaceAll("@workUnit", workUnit);
      url = url.replaceAll("@fillingDoctorName", fillingDoctorName != null ? fillingDoctorName : "");
      url = url.replaceAll("@diseaseOccupation", diseaseOccupation);
      url = url.replaceAll("@medicalInstiSeHospital", medicalInstiSeHospital != null ? medicalInstiSeHospital : "");
      url = url.replaceAll("@diseaseHometown", diseaseHometown);
      url = url.replaceAll("@diseaseProvince", diseaseProvince != null ? diseaseProvince : "");
      url = url.replaceAll("@diseaseCity", diseaseCity != null ? diseaseCity : "");
      url = url.replaceAll("@diseaseDistrict", diseaseDistrict != null ? diseaseDistrict : "");
      url = url.replaceAll("@diseaseAddress", diseaseAddress != null ? diseaseAddress : "");
      url = url.replaceAll("@uName", uName);
      url = url.replaceAll("@fillGuid", fillGuid);
      return url;
   }
}
