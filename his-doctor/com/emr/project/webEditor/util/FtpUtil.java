package com.emr.project.webEditor.util;

import com.emr.common.constant.CommonConstants;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FtpUtil {
   public static FTPClient ftpClient = null;

   public static void initFtpClient(String hostname, String username, String password, Integer port) throws Exception {
      ftpClient = new FTPClient();
      ftpClient.setControlEncoding("GBK");
      FTPClientConfig conf = new FTPClientConfig("WINDOWS");
      conf.setServerLanguageCode("zh");

      try {
         ftpClient.connect(hostname, port);
         ftpClient.login(username, password);
         int replyCode = ftpClient.getReplyCode();
         if (!FTPReply.isPositiveCompletion(replyCode)) {
            ftpClient.disconnect();
         }
      } catch (MalformedURLException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }

   }

   public static boolean uploadFile(String pathname, String fileName, InputStream inputStream) {
      boolean flag = false;

      try {
         String hostname = "192.168.123.33";
         Integer port = CommonConstants.EMR.PORT;
         String userName = "lenovo";
         String password = "1988";
         initFtpClient(hostname, userName, password, port);
         ftpClient.setFileType(2);
         ftpClient.enterLocalPassiveMode();
         String newPathname = new String(pathname.getBytes("GBK"), "iso-8859-1");
         CreateDirecroty(newPathname);
         ftpClient.storeFile(fileName, inputStream);
         inputStream.close();
         ftpClient.logout();
         flag = true;
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         if (ftpClient.isConnected()) {
            try {
               ftpClient.disconnect();
            } catch (IOException e) {
               e.printStackTrace();
            }
         }

         if (null != inputStream) {
            try {
               inputStream.close();
            } catch (IOException e) {
               e.printStackTrace();
            }
         }

      }

      return flag;
   }

   public static boolean changeWorkingDirectory(String directory) {
      boolean flag = true;

      try {
         flag = ftpClient.changeWorkingDirectory(directory);
      } catch (IOException ioe) {
         ioe.printStackTrace();
      }

      return flag;
   }

   public static boolean CreateDirecroty(String remote) throws IOException {
      boolean success = true;
      String directory = remote + "/";
      if (!directory.equalsIgnoreCase("/") && !changeWorkingDirectory(new String(directory))) {
         int start = 0;
         int end = 0;
         if (directory.startsWith("/")) {
            start = 1;
         } else {
            start = 0;
         }

         end = directory.indexOf("/", start);
         String path = "";
         String paths = "";

         do {
            String subDirectory = new String(remote.substring(start, end).getBytes("UTF-8"), "iso-8859-1");
            path = path + "/" + subDirectory;
            if (!existFile(path)) {
               if (makeDirectory(subDirectory)) {
                  changeWorkingDirectory(subDirectory);
               } else {
                  changeWorkingDirectory(subDirectory);
               }
            } else {
               changeWorkingDirectory(subDirectory);
            }

            paths = paths + "/" + subDirectory;
            start = end + 1;
            end = directory.indexOf("/", start);
         } while(end > start);
      }

      return success;
   }

   public static boolean existFile(String path) throws IOException {
      boolean flag = false;
      FTPFile[] ftpFileArr = ftpClient.listFiles(path);
      if (ftpFileArr.length > 0) {
         flag = true;
      }

      return flag;
   }

   public static boolean makeDirectory(String dir) {
      boolean flag = true;

      try {
         flag = ftpClient.makeDirectory(dir);
      } catch (Exception e) {
         e.printStackTrace();
      }

      return flag;
   }

   public static boolean downloadFile(String hostname, String username, String password, Integer port, String pathname, String filename, String localpath) {
      boolean flag = false;
      OutputStream os = null;

      try {
         System.out.println("开始下载文件");
         initFtpClient(hostname, username, password, port);
         ftpClient.changeWorkingDirectory(pathname);
         FTPFile[] ftpFiles = ftpClient.listFiles();

         for(FTPFile file : ftpFiles) {
            if (filename.equalsIgnoreCase(file.getName())) {
               File localFile = new File(localpath + "/" + file.getName());
               os = new FileOutputStream(localFile);
               ftpClient.retrieveFile(file.getName(), os);
               os.close();
            }
         }

         ftpClient.logout();
         flag = true;
         System.out.println("下载文件成功");
      } catch (Exception e) {
         System.out.println("下载文件失败");
         e.printStackTrace();
      } finally {
         if (ftpClient.isConnected()) {
            try {
               ftpClient.disconnect();
            } catch (IOException e) {
               e.printStackTrace();
            }
         }

         if (null != os) {
            try {
               os.close();
            } catch (IOException e) {
               e.printStackTrace();
            }
         }

      }

      return flag;
   }

   public static boolean deleteFile(String hostname, String username, String password, Integer port, String pathname, String filename) {
      boolean flag = false;

      try {
         System.out.println("开始删除文件");
         initFtpClient(hostname, username, password, port);
         ftpClient.changeWorkingDirectory(pathname);
         ftpClient.dele(filename);
         ftpClient.logout();
         flag = true;
         System.out.println("删除文件成功");
      } catch (Exception e) {
         System.out.println("删除文件失败");
         e.printStackTrace();
      } finally {
         if (ftpClient.isConnected()) {
            try {
               ftpClient.disconnect();
            } catch (IOException e) {
               e.printStackTrace();
            }
         }

      }

      return flag;
   }
}
