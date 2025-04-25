package com.emr.framework.config;

import com.emr.project.webservice.service.AgiEventService;
import com.emr.project.webservice.service.EcgWebService;
import javax.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CxfConfig {
   @Autowired
   private Bus bus;
   @Autowired
   private AgiEventService agiEventService;
   @Autowired
   private EcgWebService ecgWebService;

   @Bean
   public Endpoint endpoint() {
      EndpointImpl endpoint = new EndpointImpl(this.bus, this.agiEventService);
      endpoint.publish("/agi");
      return endpoint;
   }

   @Bean
   public Endpoint endpointEcg() {
      EndpointImpl endpoint = new EndpointImpl(this.bus, this.ecgWebService);
      endpoint.publish("/ecg/reportList");
      return endpoint;
   }
}
