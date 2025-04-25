package com.emr.framework.util;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

public class WebServiceUtil {
   public static Object call(String wsdl, String method, Object... var2) throws Exception {
      JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
      Client client = factory.createClient(wsdl);
      HTTPConduit conduit = (HTTPConduit)client.getConduit();
      HTTPClientPolicy policy = new HTTPClientPolicy();
      long timeout = 600000L;
      policy.setConnectionTimeout(timeout);
      policy.setReceiveTimeout(timeout);
      conduit.setClient(policy);
      Object[] os = client.invoke(method, var2);
      return os[0];
   }
}
