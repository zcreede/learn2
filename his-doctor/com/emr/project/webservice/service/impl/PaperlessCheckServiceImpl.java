package com.emr.project.webservice.service.impl;

import com.emr.common.utils.StringUtils;
import com.emr.framework.util.WebServiceUtil;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.webservice.domain.req.PaperlessCheckReq;
import com.emr.project.webservice.domain.resp.WebServiceResp;
import com.emr.project.webservice.service.PaperlessCheckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaperlessCheckServiceImpl implements PaperlessCheckService {
   private final Logger log = LoggerFactory.getLogger(PaperlessCheckServiceImpl.class);
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;

   public WebServiceResp getPaperlessCheckFlag(PaperlessCheckReq req) throws Exception {
      WebServiceResp resp = new WebServiceResp();
      String paperlessFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0122");
      if ("1".equals(paperlessFlag)) {
         String configUrl = this.sysEmrConfigService.selectSysEmrConfigByKey("012202");
         if (StringUtils.isNotEmpty(configUrl)) {
            try {
               String method = "verRevocation";
               String factory = this.sysEmrConfigService.selectSysEmrConfigByKey("012201");
               if ("LB".equals(factory)) {
                  this.log.info("getPaperlessCheckFlag-webservice接口请求地址：" + configUrl);
                  Object res = WebServiceUtil.call(configUrl, method, req.getZyh());
                  this.log.info("getPaperlessCheckFlag-webservice接口返回：" + res.toString());
                  if (res != null) {
                     String resStr = (String)res;
                     String resCode = resStr.split("|")[0];
                     if ("1".equals(resCode)) {
                        resp.setCode("1");
                        resp.setSource("无纸化已经打印不允许召回修改");
                     } else {
                        resp.setCode("0");
                     }
                  } else {
                     resp.setCode("1");
                     String errorStr = "无纸化webService接口返回为空";
                     resp.setSource(errorStr);
                     this.log.error(errorStr);
                  }
               } else {
                  resp.setCode("0");
                  String errorStr = "未配置无纸化厂家";
                  resp.setSource(errorStr);
                  this.log.error(errorStr);
               }
            } catch (Exception var10) {
               resp.setCode("1");
               String errorStr = "调用无纸化webService接口异常";
               resp.setSource(errorStr);
               this.log.error(errorStr);
            }
         }
      } else {
         resp.setCode("0");
         String errorStr = "未开启无纸化配置";
         resp.setSource(errorStr);
         this.log.error(errorStr);
      }

      return resp;
   }
}
