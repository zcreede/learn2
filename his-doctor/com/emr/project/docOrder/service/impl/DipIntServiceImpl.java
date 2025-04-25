package com.emr.project.docOrder.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.emr.common.utils.Base64Util;
import com.emr.common.utils.http.HttpUtils2;
import com.emr.project.docOrder.domain.req.DipCfPageReq;
import com.emr.project.docOrder.service.DipIntService;
import com.emr.project.mrhp.domain.resp.MrHpRecordDipResp;
import com.emr.project.system.service.ISysEmrConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DipIntServiceImpl implements DipIntService {
   private final Logger log = LoggerFactory.getLogger(DipIntServiceImpl.class);
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;

   public MrHpRecordDipResp selectDipCfPage(DipCfPageReq req, String methord) throws Exception {
      String apiUrl = this.sysEmrConfigService.selectSysEmrConfigByKey("0075");
      String url = apiUrl + methord;
      JSONObject conObj = (JSONObject)JSONObject.toJSON(req);
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
