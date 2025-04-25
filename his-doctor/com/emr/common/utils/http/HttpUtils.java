package com.emr.common.utils.http;

import com.emr.project.webservice.utils.SignUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtils {
   private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);

   public static String sendGet(String url, String param) {
      return sendGet(url, param, "UTF-8");
   }

   public static String sendGet(String url, String param, String contentType) {
      StringBuilder result = new StringBuilder();
      BufferedReader in = null;

      try {
         String urlNameString = url + "?" + param;
         log.info("sendGet - {}", urlNameString);
         URL realUrl = new URL(urlNameString);
         URLConnection connection = realUrl.openConnection();
         connection.setRequestProperty("accept", "*/*");
         connection.setRequestProperty("connection", "Keep-Alive");
         connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
         connection.connect();
         in = new BufferedReader(new InputStreamReader(connection.getInputStream(), contentType));

         String line;
         while((line = in.readLine()) != null) {
            result.append(line);
         }

         log.info("recv - {}", result);
      } catch (ConnectException e) {
         log.error("调用HttpUtils.sendGet ConnectException, url=" + url + ",param=" + param, e);
      } catch (SocketTimeoutException e) {
         log.error("调用HttpUtils.sendGet SocketTimeoutException, url=" + url + ",param=" + param, e);
      } catch (IOException e) {
         log.error("调用HttpUtils.sendGet IOException, url=" + url + ",param=" + param, e);
      } catch (Exception e) {
         log.error("调用HttpsUtil.sendGet Exception, url=" + url + ",param=" + param, e);
      } finally {
         try {
            if (in != null) {
               in.close();
            }
         } catch (Exception ex) {
            log.error("调用in.close Exception, url=" + url + ",param=" + param, ex);
         }

      }

      return result.toString();
   }

   public static String sendPost(String url, String param, String contentType) {
      PrintWriter out = null;
      BufferedReader in = null;
      StringBuilder result = new StringBuilder();

      try {
         log.info("sendPost - {}", url);
         URL realUrl = new URL(url);
         URLConnection conn = realUrl.openConnection();
         conn.setRequestProperty("accept", "*/*");
         conn.setRequestProperty("connection", "Keep-Alive");
         conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
         conn.setRequestProperty("Accept-Charset", "utf-8");
         conn.setRequestProperty("contentType", "utf-8");
         conn.setRequestProperty("content-type", contentType);
         conn.setDoOutput(true);
         conn.setDoInput(true);
         out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "utf-8"));
         out.print(param);
         out.flush();
         in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

         String line;
         while((line = in.readLine()) != null) {
            result.append(line);
         }

         log.info("recv - {}", result);
      } catch (ConnectException e) {
         log.error("调用HttpUtils.sendPost ConnectException, url=" + url + ",param=" + param, e);
      } catch (SocketTimeoutException e) {
         log.error("调用HttpUtils.sendPost SocketTimeoutException, url=" + url + ",param=" + param, e);
      } catch (IOException e) {
         log.error("调用HttpUtils.sendPost IOException, url=" + url + ",param=" + param, e);
      } catch (Exception e) {
         log.error("调用HttpsUtil.sendPost Exception, url=" + url + ",param=" + param, e);
      } finally {
         try {
            if (out != null) {
               out.close();
            }

            if (in != null) {
               in.close();
            }
         } catch (IOException ex) {
            log.error("调用in.close Exception, url=" + url + ",param=" + param, ex);
         }

      }

      return result.toString();
   }

   public static String sendSSLPost(String url, String param) {
      StringBuilder result = new StringBuilder();
      String urlNameString = url + "?" + param;

      try {
         log.info("sendSSLPost - {}", urlNameString);
         SSLContext sc = SSLContext.getInstance("SSL");
         sc.init((KeyManager[])null, new TrustManager[]{new TrustAnyTrustManager()}, new SecureRandom());
         URL console = new URL(urlNameString);
         HttpsURLConnection conn = (HttpsURLConnection)console.openConnection();
         conn.setRequestProperty("accept", "*/*");
         conn.setRequestProperty("connection", "Keep-Alive");
         conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
         conn.setRequestProperty("Accept-Charset", "utf-8");
         conn.setRequestProperty("contentType", "utf-8");
         conn.setDoOutput(true);
         conn.setDoInput(true);
         conn.setSSLSocketFactory(sc.getSocketFactory());
         conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
         conn.connect();
         InputStream is = conn.getInputStream();
         BufferedReader br = new BufferedReader(new InputStreamReader(is));
         String ret = "";

         while((ret = br.readLine()) != null) {
            if (ret != null && !"".equals(ret.trim())) {
               result.append(new String(ret.getBytes("ISO-8859-1"), "utf-8"));
            }
         }

         log.info("recv - {}", result);
         conn.disconnect();
         br.close();
      } catch (ConnectException e) {
         log.error("调用HttpUtils.sendSSLPost ConnectException, url=" + url + ",param=" + param, e);
      } catch (SocketTimeoutException e) {
         log.error("调用HttpUtils.sendSSLPost SocketTimeoutException, url=" + url + ",param=" + param, e);
      } catch (IOException e) {
         log.error("调用HttpUtils.sendSSLPost IOException, url=" + url + ",param=" + param, e);
      } catch (Exception e) {
         log.error("调用HttpsUtil.sendSSLPost Exception, url=" + url + ",param=" + param, e);
      }

      return result.toString();
   }

   public static String sendPostMap(String url, Map param, String contentType) {
      String mapToString = SignUtil.mapToString(param);
      return sendPost(url, mapToString, contentType);
   }

   private static class TrustAnyTrustManager implements X509TrustManager {
      private TrustAnyTrustManager() {
      }

      public void checkClientTrusted(X509Certificate[] chain, String authType) {
      }

      public void checkServerTrusted(X509Certificate[] chain, String authType) {
      }

      public X509Certificate[] getAcceptedIssuers() {
         return new X509Certificate[0];
      }
   }

   private static class TrustAnyHostnameVerifier implements HostnameVerifier {
      private TrustAnyHostnameVerifier() {
      }

      public boolean verify(String hostname, SSLSession session) {
         return true;
      }
   }
}
