package com.emr.project.webservice.utils;

import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.rmi.RemoteException;
import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WEBServiceNewUtil {
   private static final Logger logger = LoggerFactory.getLogger(WEBServiceNewUtil.class);

   public static String remoteInvokePassBook(String targetNamespace, String operationName, String data, int argCount, String url) throws IOException {
      logger.info("请求webservice ===" + url);
      logger.info("入参webservice ===" + data);
      String endpoint = url;
      System.out.println("endpoint>>>" + url);
      String result = "call failed!";
      Service service = new Service();
      Object[] object = new Object[1];

      try {
         Call call = (Call)service.createCall();
         call.setTargetEndpointAddress(endpoint);
         if (argCount == 1) {
            JSONObject jsobject = JSONObject.parseObject(data);
            object[0] = jsobject + "";
            call.addParameter("args0", XMLType.XSD_STRING, ParameterMode.IN);
         }

         if (argCount == 2) {
            object[0] = data;
            call.addParameter("args0", XMLType.XSD_STRING, ParameterMode.IN);
         }

         call.setOperationName(new QName(targetNamespace, operationName));
         call.setReturnType(XMLType.XSD_STRING);
         result = (String)call.invoke(object);
         logger.info("出参===" + result);
         System.out.println("=====" + result);
      } catch (ServiceException e) {
         logger.info("国家传染病上报异常 ===", e);
         e.printStackTrace();
      } catch (RemoteException e) {
         logger.info("国家传染病上报异常 ===", e);
         e.printStackTrace();
      }

      return result;
   }
}
