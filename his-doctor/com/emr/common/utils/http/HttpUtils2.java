package com.emr.common.utils.http;

import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpUtils2 {
   private static PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager();
   private static RequestConfig requestConfig;
   private static final int MAX_TIMEOUT = 7000;
   private static final String UTF_8 = "UTF-8";
   public static final String JSON = "json";
   public static final String XML = "xml";

   public static String doGet(String url) throws IOException {
      return doGet(url, (Map)null);
   }

   public static String doGet(String url, Map params) throws IOException {
      int i = 0;
      if (params != null) {
         StringBuffer param = new StringBuffer();

         for(String key : params.keySet()) {
            if (i == 0) {
               param.append("?");
            } else {
               param.append("&");
            }

            param.append(key).append("=").append((String)params.get(key));
            ++i;
         }

         url = url + param;
      }

      String result = null;
      CloseableHttpClient httpclient = HttpClients.createDefault();
      CloseableHttpResponse response = null;
      HttpEntity entity = null;

      try {
         HttpGet httpGet = new HttpGet(url);
         response = httpclient.execute(httpGet);
         entity = response.getEntity();
         if (entity != null) {
            InputStream instream = entity.getContent();
            result = IOUtils.toString(instream, "UTF-8");
         }
      } finally {
         try {
            EntityUtils.consume(entity);
         } catch (IOException e) {
            e.printStackTrace();
         }

      }

      return result;
   }

   public static String doPost(String apiUrl) throws IOException {
      return doPost(apiUrl, new HashMap());
   }

   public static String doPost(String apiUrl, Map params) throws IOException {
      CloseableHttpClient httpClient = HttpClients.createDefault();
      String httpStr = null;
      HttpPost httpPost = new HttpPost(apiUrl);
      CloseableHttpResponse response = null;

      try {
         httpPost.setConfig(requestConfig);
         List<NameValuePair> pairList = new ArrayList(params.size());

         for(Map.Entry entry : params.entrySet()) {
            NameValuePair pair = new BasicNameValuePair((String)entry.getKey(), (String)entry.getValue());
            pairList.add(pair);
         }

         httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));
         response = httpClient.execute(httpPost);
         HttpEntity entity = response.getEntity();
         httpStr = EntityUtils.toString(entity, "UTF-8");
      } finally {
         if (response != null) {
            try {
               EntityUtils.consume(response.getEntity());
            } catch (IOException e) {
               e.printStackTrace();
            }
         }

      }

      return httpStr;
   }

   public static String doPost(String apiUrl, String content, String type) throws IOException {
      CloseableHttpClient httpClient = HttpClients.createDefault();
      String httpStr = null;
      HttpPost httpPost = new HttpPost(apiUrl);
      CloseableHttpResponse response = null;

      try {
         httpPost.setConfig(requestConfig);
         StringEntity stringEntity = new StringEntity(content, "UTF-8");
         stringEntity.setContentEncoding("UTF-8");
         if ("json".equals(type)) {
            stringEntity.setContentType("application/json");
         } else if ("xml".equals(type)) {
            stringEntity.setContentType("application/xml");
         } else {
            stringEntity.setContentType("text/plain");
         }

         httpPost.setEntity(stringEntity);
         response = httpClient.execute(httpPost);
         HttpEntity entity = response.getEntity();
         httpStr = EntityUtils.toString(entity, "UTF-8");
      } finally {
         if (response != null) {
            try {
               EntityUtils.consume(response.getEntity());
            } catch (IOException e) {
               e.printStackTrace();
            }
         }

      }

      return httpStr;
   }

   public static String doPost(String apiUrl, String content, String type, Map headers) throws IOException {
      CloseableHttpClient httpClient = HttpClients.createDefault();
      String httpStr = null;
      HttpPost httpPost = new HttpPost(apiUrl);
      CloseableHttpResponse response = null;

      try {
         httpPost.setConfig(requestConfig);
         if (headers != null) {
            for(String headerName : headers.keySet()) {
               httpPost.setHeader(headerName, (String)headers.get(headerName));
            }
         }

         StringEntity stringEntity = new StringEntity(content, "UTF-8");
         stringEntity.setContentEncoding("UTF-8");
         if ("json".equals(type)) {
            stringEntity.setContentType("application/json");
         } else if ("xml".equals(type)) {
            stringEntity.setContentType("application/xml");
         } else {
            stringEntity.setContentType("text/plain");
         }

         httpPost.setEntity(stringEntity);
         response = httpClient.execute(httpPost);
         HttpEntity entity = response.getEntity();
         httpStr = EntityUtils.toString(entity, "UTF-8");
      } finally {
         if (response != null) {
            try {
               EntityUtils.consume(response.getEntity());
            } catch (IOException e) {
               e.printStackTrace();
            }
         }

      }

      return httpStr;
   }

   public static String doPostSSL(String apiUrl, Map params) {
      CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
      HttpPost httpPost = new HttpPost(apiUrl);
      CloseableHttpResponse response = null;
      String httpStr = null;

      Object var24;
      try {
         httpPost.setConfig(requestConfig);
         List<NameValuePair> pairList = new ArrayList(params.size());

         for(Map.Entry entry : params.entrySet()) {
            NameValuePair pair = new BasicNameValuePair((String)entry.getKey(), entry.getValue().toString());
            pairList.add(pair);
         }

         httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("utf-8")));
         response = httpClient.execute(httpPost);
         int statusCode = response.getStatusLine().getStatusCode();
         if (statusCode == 200) {
            HttpEntity entity = response.getEntity();
            if (entity == null) {
               Object var26 = null;
               return (String)var26;
            }

            httpStr = EntityUtils.toString(entity, "utf-8");
            return httpStr;
         }

         var24 = null;
      } catch (Exception e) {
         e.printStackTrace();
         return httpStr;
      } finally {
         if (response != null) {
            try {
               EntityUtils.consume(response.getEntity());
            } catch (IOException e) {
               e.printStackTrace();
            }
         }

      }

      return (String)var24;
   }

   public static String doPostSSL(String apiUrl, Object json) {
      CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
      HttpPost httpPost = new HttpPost(apiUrl);
      CloseableHttpResponse response = null;
      String httpStr = null;

      Object entity;
      try {
         httpPost.setConfig(requestConfig);
         StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");
         stringEntity.setContentEncoding("UTF-8");
         stringEntity.setContentType("application/json");
         httpPost.setEntity(stringEntity);
         response = httpClient.execute(httpPost);
         int statusCode = response.getStatusLine().getStatusCode();
         if (statusCode == 200) {
            HttpEntity entity = response.getEntity();
            if (entity == null) {
               Object e = null;
               return (String)e;
            }

            httpStr = EntityUtils.toString(entity, "utf-8");
            return httpStr;
         }

         entity = null;
      } catch (Exception e) {
         e.printStackTrace();
         return httpStr;
      } finally {
         if (response != null) {
            try {
               EntityUtils.consume(response.getEntity());
               response.close();
            } catch (IOException e) {
               e.printStackTrace();
            }
         }

      }

      return (String)entity;
   }

   private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
      SSLConnectionSocketFactory sslsf = null;

      try {
         SSLContext sslContext = (new SSLContextBuilder()).loadTrustMaterial((KeyStore)null, new TrustStrategy() {
            public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
               return true;
            }
         }).build();
         sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {
            public boolean verify(String arg0, SSLSession arg1) {
               return true;
            }

            public void verify(String host, SSLSocket ssl) throws IOException {
            }

            public void verify(String host, X509Certificate cert) throws SSLException {
            }

            public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
            }
         });
      } catch (GeneralSecurityException e) {
         e.printStackTrace();
      }

      return sslsf;
   }

   public static String doPostSSL(String url, String content, String type, Map headers, SSLConnectionSocketFactory sslsf) {
      CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
      HttpPost httpPost = new HttpPost(url);
      CloseableHttpResponse response = null;
      String httpStr = null;

      Object entity;
      try {
         httpPost.setConfig(requestConfig);
         if (headers != null) {
            for(String headerName : headers.keySet()) {
               httpPost.setHeader(headerName, (String)headers.get(headerName));
            }
         }

         StringEntity stringEntity = new StringEntity(content, "UTF-8");
         stringEntity.setContentEncoding("UTF-8");
         if ("json".equals(type)) {
            stringEntity.setContentType("application/json");
         } else if ("xml".equals(type)) {
            stringEntity.setContentType("application/xml");
         } else {
            stringEntity.setContentType("text/plain");
         }

         httpPost.setEntity(stringEntity);
         response = httpClient.execute(httpPost);
         int statusCode = response.getStatusLine().getStatusCode();
         if (statusCode == 200) {
            HttpEntity entity = response.getEntity();
            if (entity == null) {
               Object e = null;
               return (String)e;
            }

            httpStr = EntityUtils.toString(entity, "utf-8");
            return httpStr;
         }

         entity = null;
      } catch (Exception e) {
         e.printStackTrace();
         return httpStr;
      } finally {
         if (response != null) {
            try {
               EntityUtils.consume(response.getEntity());
               response.close();
            } catch (IOException e) {
               e.printStackTrace();
            }
         }

      }

      return (String)entity;
   }

   public static void main(String[] args) {
      String apiUrl = "http://192.168.123.121/api/Examine/TopicUrlGet";
      JSONObject conObj = new JSONObject();
      conObj.put("businessDomainCode", "03");
      conObj.put("topicCode", "");
      conObj.put("entryName", "纤维胃镜检查");
      String content = conObj.toJSONString();
      String type = "json";
      Map<String, String> headers = new HashMap();
      headers.put("BHT_KEY", "MzZCMC0yOTlGLThEOEYtNjU3NS1FNkM5LUU1QTgtMDNCMi1FOEQ5");
      headers.put("DOCTOR_ID", "MDM1NQ==");
      headers.put("DOCTOR_NAME", "6ams6Zu35Yia");
      headers.put("DOCTOR_DEPT", "5YaF5YiG5rOM");

      try {
         String resStr = doPost(apiUrl, content, type, headers);
         System.out.println(resStr);
         JSONObject resObj = JSONObject.parseObject(resStr);
         String resUrl = resObj.get("url").toString();
         System.out.println(resUrl);
      } catch (IOException e) {
         e.printStackTrace();
      }

   }

   static {
      connMgr.setMaxTotal(100);
      connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());
      RequestConfig.Builder configBuilder = RequestConfig.custom();
      configBuilder.setConnectTimeout(7000);
      configBuilder.setSocketTimeout(7000);
      configBuilder.setConnectionRequestTimeout(7000);
      configBuilder.setStaleConnectionCheckEnabled(true);
      requestConfig = configBuilder.build();
   }
}
