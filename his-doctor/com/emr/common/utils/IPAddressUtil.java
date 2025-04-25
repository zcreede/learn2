package com.emr.common.utils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IPAddressUtil {
   protected final Logger log = LoggerFactory.getLogger(IPAddressUtil.class);

   public static String getIPAddress(HttpServletRequest request) {
      String ip = null;
      String ipAddresses = request.getHeader("X-Forwarded-For");
      if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
         ipAddresses = request.getHeader("Proxy-Client-IP");
      }

      if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
         ipAddresses = request.getHeader("WL-Proxy-Client-IP");
      }

      if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
         ipAddresses = request.getHeader("HTTP_CLIENT_IP");
      }

      if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
         ipAddresses = request.getHeader("X-Real-IP");
      }

      if (ipAddresses != null && ipAddresses.length() != 0) {
         ip = ipAddresses.split(",")[0];
      }

      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
         ip = request.getRemoteAddr();
      }

      return ip;
   }

   public static String getLocalIp() {
      try {
         Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();

         while(allNetInterfaces.hasMoreElements()) {
            NetworkInterface netInterface = (NetworkInterface)allNetInterfaces.nextElement();
            Enumeration addresses = netInterface.getInetAddresses();

            while(addresses.hasMoreElements()) {
               InetAddress ip = (InetAddress)addresses.nextElement();
               if (ip != null && ip instanceof Inet4Address && !"127.0.0.1".equals(ip.getHostAddress())) {
                  return ip.getHostAddress();
               }
            }
         }
      } catch (Exception var4) {
      }

      return null;
   }
}
