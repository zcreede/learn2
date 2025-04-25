package com.emr.common.utils;

import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Map;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpPostGetUtil {
   private static Logger logger = LoggerFactory.getLogger(HttpPostGetUtil.class);

   public static String httpRequest(String url, String requestMethod, Map headerMap, JSONObject contentMap) throws IOException {
      String result = "";
      URL restURL = new URL(url);
      HttpURLConnection connection = (HttpURLConnection)restURL.openConnection();
      connection.setRequestMethod(requestMethod);
      connection.setDoInput(true);
      connection.setDoOutput(true);

      for(Map.Entry elem : headerMap.entrySet()) {
         connection.setRequestProperty((String)elem.getKey(), (String)elem.getValue());
      }

      OutputStreamWriter outer = null;
      outer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
      outer.write(contentMap.toString());
      outer.flush();
      outer.close();
      InputStream ips = connection.getInputStream();
      BufferedReader in = new BufferedReader(new InputStreamReader(ips, "UTF-8"));
      StringBuffer buffer = new StringBuffer();
      String line = "";

      while((line = in.readLine()) != null) {
         buffer.append(line);
         buffer.append("\r\n");
      }

      in.close();
      ips.close();
      connection.disconnect();
      result = buffer.toString();
      return result;
   }

   public static String sendPost(String url, JSONObject jsonObject, String encoding, String token) throws IOException {
      String body = "";
      CloseableHttpClient client = HttpClients.createDefault();
      HttpPost httpPost = new HttpPost(url);
      StringEntity s = new StringEntity(jsonObject.toString(), "utf-8");
      s.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
      httpPost.setEntity(s);
      System.out.println("请求地址：" + url);
      httpPost.setHeader("Content-type", "application/json");
      httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
      httpPost.addHeader("token", token);
      CloseableHttpResponse response = client.execute(httpPost);
      HttpEntity entity = response.getEntity();
      if (entity != null) {
         body = EntityUtils.toString(entity, encoding);
      }

      EntityUtils.consume(entity);
      response.close();
      return body;
   }

   public static String sendPost2(String url, JSONObject jsonObject, String encoding) throws IOException {
      String body = null;
      CloseableHttpResponse httpResponse = null;
      InputStream inStream = null;
      CloseableHttpClient client = null;

      try {
         body = "";
         logger.info("[接口参数]" + jsonObject.toString());
         client = HttpClients.createDefault();
         HttpPost httpPost = new HttpPost(url);
         RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(180000).setSocketTimeout(180000).build();
         httpPost.setConfig(requestConfig);
         StringEntity s = new StringEntity(jsonObject.toString(), encoding);
         s.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
         httpPost.setEntity(s);
         logger.info("请求地址：" + url);
         httpPost.setHeader("Content-type", "application/json");
         httpPost.addHeader("Connection", "keep-alive");
         httpResponse = client.execute(httpPost);
         inStream = httpResponse.getEntity().getContent();
         BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, encoding));
         StringBuilder strber = new StringBuilder();

         String line;
         while((line = reader.readLine()) != null) {
            strber.append(line + "\n");
         }

         inStream.close();
         body = strber.toString();
         if (httpResponse.getStatusLine().getStatusCode() == 200) {
            logger.info("请求服务器成功，做相应处理");
         } else {
            logger.info("请求服务端失败");
         }

         logger.info("[返回结果]" + body);
      } catch (UnsupportedCharsetException e) {
         logger.error("发送医保请求异常1：", e);
      } catch (IOException e) {
         logger.error("发送医保请求异常2：", e);
      } catch (UnsupportedOperationException e) {
         logger.error("发送医保请求异常3：", e);
      } finally {
         if (httpResponse != null) {
            try {
               httpResponse.close();
            } catch (IOException e) {
               logger.error("发送医保请求，关闭流异常1：", e);
            }
         }

         if (client != null) {
            try {
               client.close();
            } catch (IOException e) {
               logger.error("发送医保请求，关闭流异常2：", e);
            }
         }

         if (inStream != null) {
            try {
               inStream.close();
            } catch (IOException e) {
               logger.error("发送医保请求，关闭流异常3：", e);
            }
         }

      }

      return body;
   }

   public static String invokeService(String url, String jsonPara, int waitTime) {
      CloseableHttpClient httpclient = HttpClients.createDefault();
      CloseableHttpResponse response = null;
      String result = null;

      try {
         HttpPost httppost = new HttpPost(url);
         RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(waitTime).setSocketTimeout(waitTime).build();
         httppost.setConfig(requestConfig);
         ByteArrayEntity entity = new ByteArrayEntity(jsonPara.getBytes(Consts.UTF_8));
         entity.setContentType("text/plain");
         httppost.setEntity(entity);
         response = httpclient.execute(httppost);
         int statusCode = response.getStatusLine().getStatusCode();
         if (statusCode != 200) {
            httppost.abort();
            throw new RuntimeException("HttpClient,error status code :" + statusCode);
         }

         HttpEntity responseEntity = response.getEntity();
         if (responseEntity != null) {
            result = EntityUtils.toString(responseEntity, "UTF-8");
            System.out.println(result);
         }

         EntityUtils.consume(entity);
      } catch (ClientProtocolException e) {
         throw new RuntimeException("提交给服务器的请求，不符合 HTTP 协议：" + e.getMessage(), e);
      } catch (IOException e) {
         throw new RuntimeException("向服务器承保接口发起 http 请求,执行 post 请求异常：" + e.getMessage(), e);
      } finally {
         if (response != null) {
            try {
               response.close();
            } catch (IOException e) {
               e.printStackTrace();
            }
         }

         if (httpclient != null) {
            try {
               httpclient.close();
            } catch (IOException e) {
               e.printStackTrace();
            }
         }

      }

      return result;
   }

   public static String sendGet(String url) throws IOException {
      String body = "";
      CloseableHttpClient client = HttpClients.createDefault();
      HttpGet httpGet = new HttpGet(url);
      httpGet.setHeader("Content-type", "application/json");
      httpGet.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
      CloseableHttpResponse response = client.execute(httpGet);
      HttpEntity entity = response.getEntity();
      if (entity != null) {
         body = EntityUtils.toString(entity, StandardCharsets.UTF_8.name());
      }

      EntityUtils.consume(entity);
      response.close();
      return body;
   }
}
