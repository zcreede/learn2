package com.emr.framework.config;

import com.emr.project.system.service.ISysEmrConfigService;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DSRClientConfig {
   protected final Logger log = LoggerFactory.getLogger(DSRClientConfig.class);
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;

   @Bean({"dSRClient"})
   public Client client() throws Exception {
      Client client = null;

      try {
         String flag = this.sysEmrConfigService.selectSysEmrConfigByKey("0045");
         if (flag.equals("1")) {
            String DSR_URL = this.sysEmrConfigService.selectSysEmrConfigByKey("004501");
            JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
            this.log.info("不良事件 webService url : {}", DSR_URL);
            client = clientFactory.createClient(DSR_URL);
            HTTPConduit conduit = (HTTPConduit)client.getConduit();
            HTTPClientPolicy policy = new HTTPClientPolicy();
            policy.setAllowChunking(false);
            policy.setConnectionTimeout(10000L);
            policy.setReceiveTimeout(20000L);
            conduit.setClient(policy);
         }
      } catch (Exception e) {
         this.log.error("创建不良事件webService请求客户端出现异常：", e);
      }

      return client;
   }
}
