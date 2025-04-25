package com.emr.project.webservice.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.webservice.domain.req.ClinicalPathwayReq;
import com.emr.project.webservice.domain.resp.WebServiceResp;
import com.emr.project.webservice.service.IClinicalPathwayService;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.Iterator;
import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import org.apache.axis.client.Call;
import org.apache.axis.encoding.XMLType;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClinicalPathwayServiceImpl implements IClinicalPathwayService {
   private final Logger log = LoggerFactory.getLogger(IClinicalPathwayService.class);
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;

   public WebServiceResp getPathway(ClinicalPathwayReq req) throws Exception {
      WebServiceResp resp = new WebServiceResp();
      String dipFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("007608");
      if (StringUtils.isNotEmpty(dipFlag)) {
         String[] split = dipFlag.split("&");
         if (split.length == 2) {
            String reqUrl = split[0];
            String nameSpace = split[1];
            String admissionNo = req.getAdmissionNo();
            StringBuffer sb1 = new StringBuffer();
            sb1.append("<Requset><Method>");
            sb1.append("LCLJ003");
            sb1.append("</Method></Requset>");
            String reqXML1 = sb1.toString();
            org.apache.axis.client.Service service = new org.apache.axis.client.Service();
            Call call = (Call)service.createCall();
            call.setTargetEndpointAddress(new URL(reqUrl));
            call.setOperationName(new QName(nameSpace, "Process"));
            call.addParameter("xml", XMLType.XSD_STRING, ParameterMode.IN);
            call.setReturnType(javax.xml.rpc.encoding.XMLType.XSD_STRING);
            String result = (String)call.invoke(new Object[]{reqXML1});
            if (StringUtils.isNotEmpty(result)) {
               SysUser user = SecurityUtils.getLoginUser().getUser();
               String ErrorMsg = null;
               String ResultCode = null;
               String Url = null;
               SAXReader reader = new SAXReader();
               Document document = reader.read(new ByteArrayInputStream(result.getBytes()));
               Element rootElement = document.getRootElement();
               Iterator it = rootElement.elementIterator();

               while(it.hasNext()) {
                  Element element = (Element)it.next();
                  String name = element.getQName().getName();
                  String text = element.getText();
                  switch (name) {
                     case "ErrorMsg":
                        ErrorMsg = text;
                        break;
                     case "ResultCode":
                        ResultCode = text;
                        break;
                     case "Url":
                        Url = text;
                  }
               }

               if (ResultCode != null) {
                  if (ResultCode.equals("0")) {
                     Url = Url + "?Zyh=" + admissionNo + "&Ysbm=" + user.getUserName() + "&Bqbm=" + user.getDept().getDeptCode();
                     resp.setCode("0");
                     resp.setSource(Url);
                  } else {
                     resp.setCode("1");
                     resp.setSource(ErrorMsg);
                     this.log.error("调用webServer,返回值不标准！,{}", result);
                  }
               } else {
                  resp.setCode("1");
                  resp.setSource("调用webServer,返回值不标准！");
                  this.log.error("调用webServer,返回值不标准！,{}", result);
               }
            } else {
               resp.setCode("1");
               resp.setSource("调用webServer,返回值为空！");
               this.log.error("调用webServer,返回值为空！,{}", result);
            }
         } else {
            resp.setCode("1");
            resp.setSource("配置临床路径的URL错误，请按照标准规则维护");
            this.log.error("配置临床路径的URL错误，请按照标准规则维护");
         }
      } else {
         resp.setCode("1");
         resp.setSource("配置临床路径的URL为空，请管理员维护！");
         this.log.error("配置临床路径的URL为空，请管理员维护！");
      }

      return resp;
   }

   public WebServiceResp getIsNeedPathway(ClinicalPathwayReq req) throws Exception {
      WebServiceResp resp = new WebServiceResp();
      String lcljFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0076");
      String lcljCj = this.sysEmrConfigService.selectSysEmrConfigByKey("007601");
      if (StringUtils.isNotBlank(lcljFlag) && lcljFlag.equals("1") && StringUtils.isNotBlank(lcljCj)) {
         String dipFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("007608");
         if (StringUtils.isNotEmpty(dipFlag)) {
            String[] split = dipFlag.split("&");
            if (split.length == 2) {
               String reqUrl = split[0];
               String nameSpace = split[1];
               StringBuffer sb1 = new StringBuffer();
               sb1.append("<Requset><Method>");
               sb1.append("LCLJ001");
               sb1.append("</Method><Zyh>");
               sb1.append(req.getZyh());
               sb1.append("</Zyh><Icd>");
               sb1.append(req.getIcd());
               sb1.append("</Icd></Requset>");
               String reqXML1 = sb1.toString();
               org.apache.axis.client.Service service = new org.apache.axis.client.Service();
               Call call = (Call)service.createCall();
               call.setTargetEndpointAddress(new URL(reqUrl));
               call.setOperationName(new QName(nameSpace, "Process"));
               call.addParameter("xml", XMLType.XSD_STRING, ParameterMode.IN);
               call.setReturnType(javax.xml.rpc.encoding.XMLType.XSD_STRING);
               String result = (String)call.invoke(new Object[]{reqXML1});
               if (StringUtils.isNotEmpty(result)) {
                  String ErrorMsg = null;
                  String ResultCode = null;
                  String Bz = null;
                  SAXReader reader = new SAXReader();
                  Document document = reader.read(new ByteArrayInputStream(result.getBytes()));
                  Element rootElement = document.getRootElement();
                  Iterator it = rootElement.elementIterator();

                  while(it.hasNext()) {
                     Element element = (Element)it.next();
                     String name = element.getQName().getName();
                     String text = element.getText();
                     switch (name) {
                        case "ErrorMsg":
                           ErrorMsg = text;
                           break;
                        case "ResultCode":
                           ResultCode = text;
                           break;
                        case "Bz":
                           Bz = text;
                     }
                  }

                  if (ResultCode != null) {
                     if (ResultCode.equals("0") && "1".equals(Bz)) {
                        resp.setCode("0");
                     } else {
                        resp.setCode("1");
                        resp.setSource(ErrorMsg);
                        this.log.error("调用webServer,返回值不标准！,{}", result);
                     }
                  } else {
                     resp.setCode("1");
                     resp.setSource("调用webServer,返回值不标准！");
                     this.log.error("调用webServer,返回值不标准！,{}", result);
                  }
               } else {
                  resp.setCode("1");
                  resp.setSource("调用webServer,返回值为空！");
                  this.log.error("调用webServer,返回值为空！,{}", result);
               }
            } else {
               resp.setCode("1");
               resp.setSource("配置临床路径的URL错误，请按照标准规则维护");
               this.log.error("配置临床路径的URL错误，请按照标准规则维护");
            }
         } else {
            resp.setCode("1");
            resp.setSource("配置临床路径的URL为空，请管理员维护！");
            this.log.error("配置临床路径的URL为空，请管理员维护！");
         }
      } else {
         resp.setCode("500");
         resp.setSource("接口未配置，请联系系统管理员进行维护！");
         this.log.error("接口未配置，请联系系统管理员进行维护！");
      }

      return resp;
   }
}
