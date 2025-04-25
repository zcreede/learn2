package com.emr.project.mrhp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.emr.common.utils.Base64Util;
import com.emr.common.utils.http.HttpUtils2;
import com.emr.project.CDSS.xyt.RequestUtil;
import com.emr.project.mrhp.domain.req.MrHpRecordDipReq;
import com.emr.project.mrhp.domain.resp.MrHpRecordDipResp;
import com.emr.project.mrhp.service.IMrHpDipQcService;
import com.emr.project.system.service.ISysEmrConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MrHpDipQcServiceImpl implements IMrHpDipQcService {
   private final Logger log = LoggerFactory.getLogger(MrHpDipQcServiceImpl.class);
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;

   public MrHpRecordDipResp selectDipQcUrl(MrHpRecordDipReq mrHpRecordDipReq) throws Exception {
      String apiUrl = this.sysEmrConfigService.selectSysEmrConfigByKey("0075");
      String url = apiUrl + RequestUtil.DIP_QC_PAGE;
      JSONObject conObj = (JSONObject)JSONObject.toJSON(mrHpRecordDipReq);
      String content = conObj.toJSONString();
      this.log.debug("=== dip请求内容: " + content);
      String type = "json";
      String resStr = HttpUtils2.doPost(url, content, type);
      this.log.debug("=== dip请求结果: " + resStr);
      MrHpRecordDipResp mrHpRecordDipResp = (MrHpRecordDipResp)JSONObject.parseObject(resStr, MrHpRecordDipResp.class);
      if (mrHpRecordDipResp != null && 200 == mrHpRecordDipResp.getCode()) {
         mrHpRecordDipResp.setData(Base64Util.decode(mrHpRecordDipResp.getData()));
      }

      return mrHpRecordDipResp;
   }
}
