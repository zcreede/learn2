package com.emr.project.pat.service.impl;

import com.emr.project.pat.domain.resp.DsrReturnResp;
import com.emr.project.pat.service.DSRInTerFaceService;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.webservice.utils.WEBServiceNewUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DSRInTerFaceServiceImpl implements DSRInTerFaceService {
   private static final Logger logger = LoggerFactory.getLogger(DSRInTerFaceServiceImpl.class);
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;

   public DsrReturnResp dsrReport(String bah) throws Exception {
      DsrReturnResp resp = new DsrReturnResp();
      String msg = "";
      String sign = this.sysEmrConfigService.selectSysEmrConfigByKey("0134");
      if ("1".equals(sign)) {
         String webUrl = this.sysEmrConfigService.selectSysEmrConfigByKey("013401");
         logger.info("调用国家传染病患者基本webservice服务=======" + bah);
         String syncEmrPatientInfo = WEBServiceNewUtil.remoteInvokePassBook("http://webservice.dsr.com", "syncOracleEmrPatientInfo", bah, 2, webUrl);
         String[] split = syncEmrPatientInfo.split("||");
         String code = split[0];
         resp.setReturnMsg(split[1]);
         if ("1".equals(code)) {
            logger.info("调用国家传染病诊断活动信息webservice服务=======" + bah);
            String syncEmrActivityInfo = WEBServiceNewUtil.remoteInvokePassBook("http://webservice.dsr.com", "syncOracleEmrActivityInfo", bah, 2, webUrl);
            String[] split2 = syncEmrActivityInfo.split("||");
            String code2 = split2[0];
            resp.setReturnMsg(split2[1]);
            if ("1".equals(code2)) {
               resp.setReturnStatus("0");
            } else {
               resp.setReturnStatus("1");
            }
         } else {
            resp.setReturnStatus("1");
         }
      }

      return resp;
   }
}
