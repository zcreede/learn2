package com.emr.project.webservice.service.impl;

import com.emr.common.utils.StringUtils;
import com.emr.project.pat.domain.vo.TestReportKbVo;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.webservice.service.TestReportWebService;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TestReportWebServiceImpl implements TestReportWebService {
   private final Logger log = LoggerFactory.getLogger(TestReportWebServiceImpl.class);
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;

   public TestReportKbVo resultKnowledgeBase(String rptItemId) throws Exception {
      TestReportKbVo testReportKbVo = new TestReportKbVo();
      String knowledgeUrl = this.sysEmrConfigService.selectSysEmrConfigByKey("0124");
      if (knowledgeUrl != null) {
         RestTemplate restTemplate = new RestTemplate();
         HttpHeaders headers = new HttpHeaders();
         MediaType type = MediaType.parseMediaType("text/xml;charset=UTF-8");
         headers.setContentType(type);
         StringBuilder requestData = new StringBuilder();
         requestData.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
         requestData.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.checkout.lbdz.project.lis.com/\">");
         requestData.append("<soapenv:Header/>");
         requestData.append("<soapenv:Body>");
         requestData.append("<ser:getItemDescription>");
         requestData.append("<rptItemId>").append(rptItemId).append("</rptItemId>");
         requestData.append("</ser:getItemDescription>");
         requestData.append("</soapenv:Body>");
         requestData.append("</soapenv:Envelope>");
         this.log.info("请求报文为：" + requestData.toString());

         try {
            HttpEntity<String> request = new HttpEntity(requestData.toString(), headers);
            String str = (String)restTemplate.postForObject(knowledgeUrl, request, String.class, new Object[0]);
            this.log.info("-----------Response content-----------: " + str);
            Document doc = DocumentHelper.parseText(str);
            Map map = new HashMap();
            Element root = doc.getRootElement();

            for(Element e : Collections.singletonList(root.element("Body").element("getItemDescriptionResponse"))) {
               for(Element item : e.elements()) {
                  if (!StringUtils.isEmpty(item.getText())) {
                     map.put(item.getName(), item.getText());
                  }
               }
            }

            if (map != null) {
               String result = (String)map.get("return");
               if (result != null) {
                  int index = result.indexOf("||");
                  if (index != -1) {
                     String code = result.substring(0, index);
                     testReportKbVo.setCode(code);
                     if ("\"0".equals(code)) {
                        testReportKbVo.setCode("0");
                        String resultText = result.substring(index, result.length() - 1);
                        int indexFirst = resultText.indexOf("@");
                        if (indexFirst != -1) {
                           String referenceRange = resultText.substring(2, indexFirst - 2);
                           testReportKbVo.setReferenceRange(referenceRange);
                           String resultTwo = resultText.substring(indexFirst + 1, resultText.length());
                           int indexTwo = resultTwo.indexOf("@");
                           if (indexTwo != -1) {
                              testReportKbVo.setInfluenceFactor(resultTwo.substring(0, indexTwo));
                              testReportKbVo.setClinicalSignificance(resultTwo.substring(indexTwo + 1, resultTwo.length()));
                           }
                        }
                     } else {
                        testReportKbVo.setCode("-1");
                        String errorMessage = result.substring(5, result.length());
                        testReportKbVo.setErrorMessage(errorMessage);
                     }
                  }
               }
            }

            return testReportKbVo;
         } catch (Exception e) {
            this.log.error("baseWebServiceRequest出现异常：" + e.getMessage(), e);
         }
      }

      return testReportKbVo;
   }
}
