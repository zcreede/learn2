package com.emr.project.webservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.webservice.domain.Ybconfigure;
import com.emr.project.webservice.domain.req.MedicareAuditDataReq;
import com.emr.project.webservice.domain.req.MedicareAuditDiagnoseDtosReq;
import com.emr.project.webservice.domain.req.MedicareAuditEncounterDtosReq;
import com.emr.project.webservice.domain.req.MedicareAuditHisDataDtosReq;
import com.emr.project.webservice.domain.req.MedicareAuditInputReq;
import com.emr.project.webservice.domain.req.MedicareAuditOrderDtosReq;
import com.emr.project.webservice.domain.req.MedicareAuditPatientDtosReq;
import com.emr.project.webservice.domain.req.MedicareAuditReq;
import com.emr.project.webservice.domain.vo.MedicareAuditResultVo;
import com.emr.project.webservice.domain.vo.MedicareAuditVo;
import com.emr.project.webservice.mapper.MedicareAuditMapper;
import com.emr.project.webservice.mapper.YbconfigureMapper;
import com.emr.project.webservice.service.IMedicareAuditService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MedicareAuditServiceImpl implements IMedicareAuditService {
   private final Logger log = LoggerFactory.getLogger(MedicareAuditServiceImpl.class);
   @Autowired
   private MedicareAuditMapper medicareAuditMapper;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private YbconfigureMapper ybconfigureMapper;

   public MedicareAuditVo getMedicareAuditWebApi(String admissionNo, List orderNoList) throws Exception {
      MedicareAuditVo medicareAuditVo = new MedicareAuditVo();
      List<MedicareAuditResultVo> respList = new ArrayList();
      String path_url = this.sysEmrConfigService.selectSysEmrConfigByKey("00050101");
      String apikey = this.sysEmrConfigService.selectSysEmrConfigByKey("00050102");
      if (StringUtils.isNotEmpty(path_url)) {
         RestTemplate restTemplate = new RestTemplate();
         MedicareAuditDataReq dataReq = new MedicareAuditDataReq();
         MedicareAuditReq medicareAuditReq = new MedicareAuditReq();
         MedicareAuditInputReq input = new MedicareAuditInputReq();
         MedicareAuditPatientDtosReq patientDtos = this.selectPatientDtos(admissionNo);
         MedicareAuditEncounterDtosReq encounterDtos = this.selectEncounterDtos(admissionNo);
         List<MedicareAuditDiagnoseDtosReq> diagnoseDtos = this.selectDiagnoseDtos(admissionNo);
         List<MedicareAuditOrderDtosReq> orderDtos = this.selectOrderDtos(admissionNo, orderNoList);
         MedicareAuditHisDataDtosReq hisData = this.selectHisDataDtos();
         if (CollectionUtils.isNotEmpty(orderDtos) && orderDtos.size() > 0 && patientDtos != null && StringUtils.isNotBlank(patientDtos.getPatn_id())) {
            encounterDtos.setFsi_diagnose_dtos(diagnoseDtos);
            encounterDtos.setFsi_order_dtos(orderDtos);
            patientDtos.setFsi_encounter_dtos(encounterDtos);
            medicareAuditReq.setPatient_dtos(patientDtos);
            medicareAuditReq.setRule_ids("3101".split(","));
            medicareAuditReq.setTrig_scen("5");
            input.setData(medicareAuditReq);
            BeanUtils.copyProperties(hisData, dataReq);
            dataReq.setInput(input);
            this.log.info("医保审查请求参数##############JSONObject.toJSONString(medicareAuditReq):{}", JSONObject.toJSONString(dataReq));
            restTemplate.getInterceptors().add((ClientHttpRequestInterceptor)(request, body, execution) -> {
               request.getHeaders().set("apikey", apikey);
               return execution.execute(request, body);
            });
            ResponseEntity responseEntity = restTemplate.postForEntity(path_url, dataReq, String.class, new Object[0]);
            this.log.info("医保审查接口返回##############JSONObject.toJSONString(responseEntity):{}", JSONObject.toJSONString(responseEntity));
            this.log.info("医保审查接口返回##############JSONObject.toJSONString(responseEntity.getBody()):{}", JSONObject.toJSONString(responseEntity.getBody()));
            if (responseEntity != null && responseEntity.getBody() != null) {
               JSONObject responseson = JSONObject.parseObject(String.valueOf(responseEntity.getBody()));
               Integer infcode = responseson.getInteger("infcode");
               this.log.info("医保审查接口返回##############infcode:{}", infcode);
               if (0 == infcode) {
                  JSONObject outputJson = responseson.getJSONObject("output");
                  Object resultObj = outputJson.get("result");
                  if (resultObj != null && !resultObj.equals((Object)null)) {
                     respList = JSONObject.parseArray(resultObj.toString(), MedicareAuditResultVo.class);
                     medicareAuditVo.setMedicareAuditResultVoList(respList);
                  }

                  this.log.info("医保审查接口返回resultList##############:" + respList);
               } else if (-1 == infcode) {
                  Object errorMsg = responseson.get("err_msg");
                  medicareAuditVo.setErrorMsg(errorMsg.toString());
                  this.log.info("医保审查接口返回resultList：errorJson##############:" + errorMsg);
               }
            }

            this.log.info("医保审查接口最终返回##############" + JSONObject.toJSONString(medicareAuditVo));
         }
      } else {
         this.log.error("医保审查调用接口未配置，请联系系统管理员进行维护！");
      }

      return medicareAuditVo;
   }

   public MedicareAuditPatientDtosReq selectPatientDtos(String admissionNo) throws Exception {
      return this.medicareAuditMapper.selectPatientDtos(admissionNo);
   }

   public MedicareAuditEncounterDtosReq selectEncounterDtos(String admissionNo) throws Exception {
      return this.medicareAuditMapper.selectEncounterDtos(admissionNo);
   }

   public List selectDiagnoseDtos(String admissionNo) throws Exception {
      return this.medicareAuditMapper.selectDiagnoseDtos(admissionNo);
   }

   public List selectOrderDtos(String admissionNo, List orderNoList) throws Exception {
      return this.medicareAuditMapper.selectOrderDtos(admissionNo, orderNoList);
   }

   public MedicareAuditHisDataDtosReq selectHisDataDtos() throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      MedicareAuditHisDataDtosReq req = new MedicareAuditHisDataDtosReq();
      SimpleDateFormat sim = new SimpleDateFormat("yyyyMMddHHmmss");
      SimpleDateFormat simIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      new SimpleDateFormat("yyyy-MM-dd");
      List<Ybconfigure> ybconfigureList = this.ybconfigureMapper.selectYbconfigureList(new Ybconfigure());
      Map<String, List<Ybconfigure>> listMap = (Map)ybconfigureList.stream().collect(Collectors.groupingBy(Ybconfigure::getBz));
      if (CollectionUtils.isNotEmpty(ybconfigureList) && ybconfigureList.size() > 0) {
         String mdtrtarea_admvs = listMap.get("mdtrtarea_admvs") != null ? ((Ybconfigure)((List)listMap.get("mdtrtarea_admvs")).get(0)).getName() : "";
         req.setMdtrtarea_admvs(mdtrtarea_admvs);
         String insuplc_admdvs = listMap.get("insuplc_admdvs") != null ? ((Ybconfigure)((List)listMap.get("insuplc_admdvs")).get(0)).getName() : "";
         req.setInsuplc_admdvs(insuplc_admdvs);
         String recer_sys_code = listMap.get("recer_sys_code") != null ? ((Ybconfigure)((List)listMap.get("recer_sys_code")).get(0)).getName() : "";
         req.setRecer_sys_code(recer_sys_code);
         String infver = listMap.get("infver") != null ? ((Ybconfigure)((List)listMap.get("infver")).get(0)).getName() : "";
         req.setInfver(infver);
         String fixmedins_code = listMap.get("fixmedins_code") != null ? ((Ybconfigure)((List)listMap.get("fixmedins_code")).get(0)).getName() : "";
         req.setFixmedins_code(fixmedins_code);
         String fixmedins_name = listMap.get("fixmedins_name") != null ? ((Ybconfigure)((List)listMap.get("fixmedins_name")).get(0)).getName() : "";
         req.setFixmedins_name(fixmedins_name);
         String opercode = user.getUserName();
         String oper = user.getNickName();
         String ifcpjs = listMap.get("ifcpjs") != null ? ((Ybconfigure)((List)listMap.get("ifcpjs")).get(0)).getName() : "";
         if ("1".equals(ifcpjs)) {
            opercode = "cpjs01";
            oper = "床旁结算";
         }

         req.setOpter("system");
         req.setOpter_name("系统管理员");
         String msgid = fixmedins_code + sim.format(new Date()) + (int)((Math.random() * (double)9.0F + (double)1.0F) * (double)1000.0F);
         String sign_no = "";
         req.setInfno("3101");
         req.setMsgid(msgid);
         req.setDev_no("");
         req.setDev_safe_info("");
         req.setCainfo("");
         req.setSigntype("");
         req.setInfver(infver);
         req.setOpter_type("1");
         req.setInf_time(simIn.format(new Date()));
         req.setSign_no(sign_no.trim());
      }

      return req;
   }
}
