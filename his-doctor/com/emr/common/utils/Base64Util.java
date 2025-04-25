package com.emr.common.utils;

import com.emr.framework.config.EMRConfig;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.stream.FileImageInputStream;
import javax.xml.bind.DatatypeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Util {
   private static final Logger log = LoggerFactory.getLogger(Base64Util.class);

   public static byte[] decode(String base64Str, String folder, String fileName) throws Exception {
      log.warn(folder + "/" + fileName + "写入文件服务器开始: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      File file = null;
      String filePath = EMRConfig.getProfile() + folder;
      File dir = new File(filePath);
      if (!dir.exists() && !dir.isDirectory()) {
         dir.mkdirs();
      }

      BufferedWriter out = null;
      FileOutputStream fos = null;
      byte[] b = null;
      new BASE64Decoder();

      try {
         file = new File(filePath + "/" + fileName);
         fos = new FileOutputStream(file);
         out = new BufferedWriter(new OutputStreamWriter(fos));
         out.write(base64Str);
      } catch (IOException e) {
         throw new IOException(e);
      } finally {
         if (out != null) {
            try {
               out.close();
            } catch (IOException e) {
               throw new IOException(e);
            }
         }

         if (fos != null) {
            try {
               fos.close();
            } catch (IOException e) {
               throw new IOException(e);
            }
         }

      }

      log.warn(folder + "/" + fileName + "写入文件服务器结束: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      return b;
   }

   public static String encode(byte[] image) {
      BASE64Encoder decoder = new BASE64Encoder();
      return replaceEnter(decoder.encode(image));
   }

   public static String encode(String uri) {
      BASE64Encoder encoder = new BASE64Encoder();
      return replaceEnter(encoder.encode(uri.getBytes()));
   }

   public static byte[] imageTobyte(String path) {
      byte[] data = null;
      FileImageInputStream input = null;

      try {
         input = new FileImageInputStream(new File(path));
         ByteArrayOutputStream output = new ByteArrayOutputStream();
         byte[] buf = new byte[1024];
         int numBytesRead = 0;

         while((numBytesRead = input.read(buf)) != -1) {
            output.write(buf, 0, numBytesRead);
         }

         data = output.toByteArray();
         output.close();
         input.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      return data;
   }

   public static String replaceEnter(String str) {
      String reg = "[\n-\r]";
      Pattern p = Pattern.compile(reg);
      Matcher m = p.matcher(str);
      return m.replaceAll("");
   }

   public static String getFileBase64(String folder, String fileName) throws IOException {
      log.warn(folder + "/" + fileName + "读取文件服务器开始: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      String filePath = EMRConfig.getProfile() + folder;
      byte[] bytes = null;
      String base64String = null;
      filePath = filePath + "/" + fileName;
      File file = new File(filePath);
      if (file.exists()) {
         FileInputStream fileInputStream = new FileInputStream(file);
         int size = fileInputStream.available();
         bytes = new byte[size];
         fileInputStream.read(bytes);
         fileInputStream.close();
         base64String = new String(bytes);
         log.warn(folder + "/" + fileName + "读取文件服务器结束: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
         return base64String;
      } else {
         throw new IOException("找不到病历base64文件");
      }
   }

   public static String createLocalImageMethod(String destUrl, String emrFileName, String patFile) {
      FileOutputStream fos = null;
      BufferedInputStream bis = null;
      HttpURLConnection httpUrl = null;
      URL url = null;
      int BUFFER_SIZE = 1024;
      byte[] buf = new byte[BUFFER_SIZE];
      int size = 0;
      String filePath = "";

      try {
         System.out.println("原始图片URL为：" + destUrl);
         String[] fileNameArray = destUrl.split("\\/");
         if (fileNameArray.length > 1) {
            filePath = EMRConfig.getProfile() + "image/" + patFile + "/";
            File file = new File(filePath);
            if (!file.exists() && !file.isDirectory()) {
               file.mkdirs();
            }

            filePath = filePath + emrFileName + ".pdf";
            File pdf = new File(filePath);
            url = new URL(destUrl);
            httpUrl = (HttpURLConnection)url.openConnection();
            httpUrl.connect();
            bis = new BufferedInputStream(httpUrl.getInputStream());
            fos = new FileOutputStream(pdf);

            while((size = bis.read(buf)) != -1) {
               fos.write(buf, 0, size);
            }

            fos.flush();
         }
      } catch (IOException e) {
         e.printStackTrace();
      } catch (ClassCastException e) {
         e.printStackTrace();
      } finally {
         try {
            fos.close();
            bis.close();
            httpUrl.disconnect();
         } catch (IOException var27) {
         } catch (NullPointerException var28) {
         }

      }

      return filePath;
   }

   public static String createLocalImage(String destUrl, String emrFileName, String patFile) {
      FileOutputStream fos = null;
      BufferedInputStream bis = null;
      HttpURLConnection httpUrl = null;
      URL url = null;
      int BUFFER_SIZE = 1024;
      byte[] buf = new byte[BUFFER_SIZE];
      int size = 0;
      String filePath = "";

      try {
         System.out.println("原始图片URL为：" + destUrl);
         filePath = EMRConfig.getProfile() + "image/" + patFile + "/";
         File file = new File(filePath);
         if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
         }

         filePath = filePath + emrFileName + ".pdf";
         File oldfile = new File(destUrl);
         FileInputStream fileInputStream = new FileInputStream(oldfile);
         File newfile = new File(filePath);
         File fileParent = newfile.getParentFile();
         System.out.println(fileParent);
         if (!fileParent.exists()) {
            fileParent.mkdirs();
         }

         if (!newfile.exists()) {
            newfile.createNewFile();
         }

         FileOutputStream fileOutputStream = new FileOutputStream(newfile);
         byte[] buffer = new byte[1024];

         int len;
         while((len = fileInputStream.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, len);
            fileOutputStream.flush();
         }

         fileInputStream.close();
         fileOutputStream.close();
         return filePath;
      } catch (FileNotFoundException ex) {
         throw new RuntimeException(ex);
      } catch (IOException ex) {
         throw new RuntimeException(ex);
      }
   }

   public static String decode(String base64Str) {
      String str = "";
      if (base64Str != null && base64Str.length() > 0) {
         byte[] base64Bytes = DatatypeConverter.parseBase64Binary(base64Str);

         try {
            str = new String(base64Bytes, "utf-8");
         } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
         }
      }

      return str;
   }
}
