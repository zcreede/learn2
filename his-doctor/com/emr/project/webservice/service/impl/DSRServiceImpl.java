package com.emr.project.webservice.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.util.WebServiceUtil;
import com.emr.project.pat.domain.Visitinfo;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.service.ISysDictDataService;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.webservice.domain.vo.DSRParamVo;
import com.emr.project.webservice.service.IDSRService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DSRServiceImpl implements IDSRService {
   protected final Logger log = LoggerFactory.getLogger(DSRServiceImpl.class);
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private ISysDictDataService sysDictDataService;

   public DSRParamVo judgeWhetherOpen(List icds, Visitinfo visitinfo) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      DSRParamVo dsrParamVo = new DSRParamVo();
      dsrParamVo.setResult("0");
      String url = this.sysEmrConfigService.selectSysEmrConfigByKey("004502");

      for(String icd : icds) {
         String flag = this.sysEmrConfigService.selectSysEmrConfigByKey("0045");
         if ("1".equals(flag)) {
            String dsrUrl = this.sysEmrConfigService.selectSysEmrConfigByKey("004501");
            this.log.info("judgeWhetherOpen-webservice接口请求地址：" + dsrUrl);
            Object invoke = WebServiceUtil.call(dsrUrl, "judgeWhetherOpen", icd, visitinfo.getInpNo(), visitinfo.getRecordNo(), "a");
            this.log.info("judgeWhetherOpen-webservice接口返回：" + invoke.toString());
            if (invoke.toString().equals("1")) {
               dsrParamVo.setResult(invoke.toString());
               url = url.replace("@bh", visitinfo.getInpNo()).replace("@icd", icd).replace("@ysCode", basEmployee.getEmplNumber());
               dsrParamVo.setUrlStr(url);
               break;
            }
         }
      }

      String browserType = this.sysEmrConfigService.selectSysEmrConfigByKey("004504");
      dsrParamVo.setBrowserType(browserType);
      if (StringUtils.isNotBlank(browserType)) {
         String browserTypeName = this.sysDictDataService.selectDictLabel("s097", browserType);
         dsrParamVo.setBrowserTypeName(browserTypeName);
      }

      return dsrParamVo;
   }
}
