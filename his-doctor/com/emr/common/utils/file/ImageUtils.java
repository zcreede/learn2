package com.emr.common.utils.file;

import com.emr.common.utils.StringUtils;
import com.emr.framework.config.EMRConfig;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageUtils {
   private static final Logger log = LoggerFactory.getLogger(ImageUtils.class);

   public static byte[] getImage(String imagePath) {
      InputStream is = getFile(imagePath);

      Object var3;
      try {
         byte[] e = IOUtils.toByteArray(is);
         return e;
      } catch (Exception e) {
         log.error("图片加载异常 {}", e);
         var3 = null;
      } finally {
         IOUtils.closeQuietly(is);
      }

      return (byte[])var3;
   }

   public static InputStream getFile(String imagePath) {
      try {
         byte[] result = readFile(imagePath);
         result = Arrays.copyOf(result, result.length);
         return new ByteArrayInputStream(result);
      } catch (Exception e) {
         log.error("获取图片异常 {}", e);
         return null;
      }
   }

   public static byte[] readFile(String url) {
      InputStream in = null;
      ByteArrayOutputStream baos = null;

      Object downloadPath;
      try {
         if (url.startsWith("http")) {
            URL urlObj = new URL(url);
            URLConnection urlConnection = urlObj.openConnection();
            urlConnection.setConnectTimeout(30000);
            urlConnection.setReadTimeout(100000);
            urlConnection.setDoInput(true);
            in = urlConnection.getInputStream();
         } else {
            String localPath = EMRConfig.getProfile();
            String downloadPath = localPath + StringUtils.substringAfter(url, "/profile");
            in = new FileInputStream(downloadPath);
         }

         byte[] var11 = IOUtils.toByteArray(in);
         return var11;
      } catch (Exception e) {
         log.error("获取文件路径异常 {}", e);
         downloadPath = null;
      } finally {
         IOUtils.closeQuietly(in);
         IOUtils.closeQuietly(baos);
      }

      return (byte[])downloadPath;
   }
}
