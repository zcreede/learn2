package com.emr.common.utils.ip;

import com.emr.common.utils.StringUtils;
import com.emr.common.utils.html.EscapeUtil;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.servlet.http.HttpServletRequest;

public class IpUtils {
   public static String getIpAddr(HttpServletRequest request) {
      if (request == null) {
         return "unknown";
      } else {
         String ip = request.getHeader("x-forwarded-for");
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
         }

         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
         }

         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
         }

         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
         }

         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
         }

         return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : EscapeUtil.clean(ip);
      }
   }

   public static boolean internalIp(String ip) {
      byte[] addr = textToNumericFormatV4(ip);
      return internalIp(addr) || "127.0.0.1".equals(ip);
   }

   private static boolean internalIp(byte[] addr) {
      if (!StringUtils.isNull(addr) && addr.length >= 2) {
         byte b0 = addr[0];
         byte b1 = addr[1];
         byte SECTION_1 = 10;
         byte SECTION_2 = -84;
         byte SECTION_3 = 16;
         byte SECTION_4 = 31;
         byte SECTION_5 = -64;
         byte SECTION_6 = -88;
         switch (b0) {
            case -84:
               if (b1 >= 16 && b1 <= 31) {
                  return true;
               }
            case -64:
               switch (b1) {
                  case -88:
                     return true;
               }
            default:
               return false;
            case 10:
               return true;
         }
      } else {
         return true;
      }
   }

   public static byte[] textToNumericFormatV4(String text) {
      if (text.length() == 0) {
         return null;
      } else {
         byte[] bytes = new byte[4];
         String[] elements = text.split("\\.", -1);

         try {
            switch (elements.length) {
               case 1:
                  long l = Long.parseLong(elements[0]);
                  if (l < 0L || l > 4294967295L) {
                     return null;
                  }

                  bytes[0] = (byte)((int)(l >> 24 & 255L));
                  bytes[1] = (byte)((int)((l & 16777215L) >> 16 & 255L));
                  bytes[2] = (byte)((int)((l & 65535L) >> 8 & 255L));
                  bytes[3] = (byte)((int)(l & 255L));
                  break;
               case 2:
                  long l = (long)Integer.parseInt(elements[0]);
                  if (l < 0L || l > 255L) {
                     return null;
                  }

                  bytes[0] = (byte)((int)(l & 255L));
                  l = (long)Integer.parseInt(elements[1]);
                  if (l < 0L || l > 16777215L) {
                     return null;
                  }

                  bytes[1] = (byte)((int)(l >> 16 & 255L));
                  bytes[2] = (byte)((int)((l & 65535L) >> 8 & 255L));
                  bytes[3] = (byte)((int)(l & 255L));
                  break;
               case 3:
                  for(int i = 0; i < 2; ++i) {
                     long l = (long)Integer.parseInt(elements[i]);
                     if (l < 0L || l > 255L) {
                        return null;
                     }

                     bytes[i] = (byte)((int)(l & 255L));
                  }

                  long l = (long)Integer.parseInt(elements[2]);
                  if (l < 0L || l > 65535L) {
                     return null;
                  }

                  bytes[2] = (byte)((int)(l >> 8 & 255L));
                  bytes[3] = (byte)((int)(l & 255L));
                  break;
               case 4:
                  for(int i = 0; i < 4; ++i) {
                     long l = (long)Integer.parseInt(elements[i]);
                     if (l < 0L || l > 255L) {
                        return null;
                     }

                     bytes[i] = (byte)((int)(l & 255L));
                  }
                  break;
               default:
                  return null;
            }

            return bytes;
         } catch (NumberFormatException var6) {
            return null;
         }
      }
   }

   public static String getHostIp() {
      try {
         return InetAddress.getLocalHost().getHostAddress();
      } catch (UnknownHostException var1) {
         return "127.0.0.1";
      }
   }

   public static String getHostName() {
      try {
         return InetAddress.getLocalHost().getHostName();
      } catch (UnknownHostException var1) {
         return "未知";
      }
   }
}
