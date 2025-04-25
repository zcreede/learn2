package com.emr.project.webservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.emr.common.utils.StringUtils;
import com.emr.project.mrhp.domain.MrHpDrawApi;
import com.emr.project.mrhp.domain.req.MrHpDrawMainReq;
import com.emr.project.webservice.domain.resp.WebServiceGeneralResp;
import com.emr.project.webservice.service.GeneralWebService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeneralWebServiceImpl implements GeneralWebService {
   private final Logger log = LoggerFactory.getLogger(GeneralWebServiceImpl.class);

   public WebServiceGeneralResp getVteInfoWebApi(MrHpDrawMainReq mrHpDrawMainReq) throws Exception {
      WebServiceGeneralResp resp = null;
      String address = mrHpDrawMainReq.getReqUrl();
      List<MrHpDrawApi> mrHpDrawApiList = mrHpDrawMainReq.getMrHpDrawApiList();
      if (StringUtils.isNotEmpty(address)) {
         RestTemplate restTemplate = new RestTemplate();
         ResponseEntity responseEntity = null;
         if ("post".equals(mrHpDrawMainReq.getRequestMethod())) {
            Map<String, Object> map = new HashMap();

            for(MrHpDrawApi mrHpDrawApi : mrHpDrawApiList) {
               map.put(mrHpDrawApi.getParameterThird(), mrHpDrawApi.getParameter());
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map> entity = new HttpEntity(map, headers);
            restTemplate.postForEntity(address, entity, WebServiceGeneralResp.class, new Object[0]);
         } else {
            if (CollectionUtils.isNotEmpty(mrHpDrawApiList) && mrHpDrawApiList.size() > 0) {
               for(MrHpDrawApi mrHpDrawApi : mrHpDrawApiList) {
                  address = address.replaceAll(mrHpDrawApi.getHisParameter(), mrHpDrawApi.getParameter());
               }

               responseEntity = restTemplate.getForEntity(address, WebServiceGeneralResp.class, new Object[0]);
            }

            if (responseEntity != null && responseEntity.getBody() != null) {
               resp = (WebServiceGeneralResp)responseEntity.getBody();
            }

            this.log.info("接口返回##############" + JSONObject.toJSONString(resp));
         }
      } else {
         resp.setCode("500");
         resp.setMessage("url接口未配置，请联系系统管理员进行维护！");
         this.log.error("url接口未配置，请联系系统管理员进行维护！");
      }

      return resp;
   }
}
