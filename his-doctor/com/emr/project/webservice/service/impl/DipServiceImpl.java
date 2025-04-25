package com.emr.project.webservice.service.impl;

import com.emr.common.utils.StringUtils;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.webservice.domain.req.DipServiceReq;
import com.emr.project.webservice.domain.resp.WebServiceResp;
import com.emr.project.webservice.service.IDipService;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import org.apache.axis.client.Call;
import org.apache.axis.encoding.XMLType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DipServiceImpl implements IDipService {
   private final Logger log = LoggerFactory.getLogger(DipServiceImpl.class);
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;

   public WebServiceResp updateDIP(DipServiceReq req, String mark) throws Exception {
      WebServiceResp resp = new WebServiceResp();
      String dipFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0075");
      if (StringUtils.isNotEmpty(dipFlag)) {
         String[] split = dipFlag.split("&");
         if (split.length == 2) {
            String reqUrl = split[0];
            String nameSpace = split[1];
            String admissionNo = req.getAdmissionNo();
            String checkType = req.getCheckType();
            org.apache.axis.client.Service service = new org.apache.axis.client.Service();
            Call call = (Call)service.createCall();
            call.setTargetEndpointAddress(new URL(reqUrl));
            call.setOperationName(new QName(nameSpace, "updateDIP"));
            call.addParameter("zyh", XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter("type", XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter("xyyfz", XMLType.XSD_STRING, ParameterMode.IN);
            call.setReturnClass(String.class);
            String result = (String)call.invoke(new Object[]{admissionNo, mark, checkType});
            if (StringUtils.isNotEmpty(result)) {
               String[] resultList = result.split("\\|\\|");
               if (resultList.length == 2) {
                  String param1 = resultList[0];
                  String param2 = resultList[1];
                  if (!param1.equals("0") && !param1.equals("1")) {
                     resp.setCode("1");
                     resp.setSource("调用webServer,返回值不规范！");
                     this.log.error("调用webServer,返回值不规范！{}", result);
                  } else if (param1.equals("1")) {
                     String[] url = param2.split("\\|");
                     String respUrl = url[0];
                     resp.setCode("0");
                     resp.setSource(respUrl);
                  } else {
                     resp.setCode("1");
                     resp.setSource("DIP服务返回：" + param2);
                     this.log.error("调用webServer返回失败,返回状态{},返回值不规范！错误信息{}", param1, param2);
                  }
               } else {
                  resp.setCode("1");
                  resp.setSource("调用webServer,返回值不规范！");
                  this.log.error("调用webServer,返回值不规范！{}", result);
               }
            } else {
               resp.setCode("1");
               resp.setSource("调用webServer,返回值为空！");
               this.log.error("调用webServer,返回值为空！,{}", result);
            }
         } else {
            resp.setCode("1");
            resp.setSource("配置DIP的URL错误，请按照标准规则维护");
            this.log.error("配置DIP的URL错误，请按照标准规则维护");
         }
      } else {
         resp.setCode("1");
         resp.setSource("配置DIP的URL为空，请管理员维护！");
         this.log.error("配置DIP的URL为空，请管理员维护！");
      }

      return resp;
   }
}
