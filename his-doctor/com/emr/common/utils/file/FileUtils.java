package com.emr.common.utils.file;

import com.emr.common.utils.StringUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.ArrayUtils;

public class FileUtils {
   public static String FILENAME_PATTERN = "[a-zA-Z0-9_\\-\\|\\.\\u4e00-\\u9fa5]+";

   public static void writeBytes(String filePath, OutputStream os) throws IOException {
      FileInputStream fis = null;

      try {
         File file = new File(filePath);
         if (!file.exists()) {
            throw new FileNotFoundException(filePath);
         }

         fis = new FileInputStream(file);
         byte[] b = new byte[1024];

         int length;
         while((length = fis.read(b)) > 0) {
            os.write(b, 0, length);
         }
      } catch (IOException e) {
         throw e;
      } finally {
         if (os != null) {
            try {
               os.close();
            } catch (IOException e1) {
               e1.printStackTrace();
            }
         }

         if (fis != null) {
            try {
               fis.close();
            } catch (IOException e1) {
               e1.printStackTrace();
            }
         }

      }

   }

   public static boolean deleteFile(String filePath) {
      boolean flag = false;
      File file = new File(filePath);
      if (file.isFile() && file.exists()) {
         file.delete();
         flag = true;
      }

      return flag;
   }

   public static boolean isValidFilename(String filename) {
      return filename.matches(FILENAME_PATTERN);
   }

   public static boolean checkAllowDownload(String resource) {
      if (StringUtils.contains(resource, "..")) {
         return false;
      } else {
         return ArrayUtils.contains(MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION, FileTypeUtils.getFileType(resource));
      }
   }

   public static String setFileDownloadHeader(HttpServletRequest request, String fileName) throws UnsupportedEncodingException {
      String agent = request.getHeader("USER-AGENT");
      String var4;
      if (agent.contains("MSIE")) {
         var4 = URLEncoder.encode(fileName, "utf-8");
         var4 = var4.replace("+", " ");
      } else if (agent.contains("Firefox")) {
         var4 = new String(fileName.getBytes(), "ISO8859-1");
      } else if (agent.contains("Chrome")) {
         var4 = URLEncoder.encode(fileName, "utf-8");
      } else {
         var4 = URLEncoder.encode(fileName, "utf-8");
      }

      return var4;
   }

   public static void setAttachmentResponseHeader(HttpServletResponse response, String realFileName) throws UnsupportedEncodingException {
      String percentEncodedFileName = percentEncode(realFileName);
      StringBuilder contentDispositionValue = new StringBuilder();
      contentDispositionValue.append("attachment; filename=").append(percentEncodedFileName).append(";").append("filename*=").append("utf-8''").append(percentEncodedFileName);
      response.setHeader("Content-disposition", contentDispositionValue.toString());
   }

   public static String percentEncode(String s) throws UnsupportedEncodingException {
      String encode = URLEncoder.encode(s, StandardCharsets.UTF_8.toString());
      return encode.replaceAll("\\+", "%20");
   }
}
