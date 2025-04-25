package com.emr.project.webEditor.util;

import com.emr.project.emr.domain.vo.IndexVo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class WriteFileUtil {
   public static long getFolderSize(File file) {
      long size = 0L;

      try {
         File[] fileList = file.listFiles();

         for(int i = 0; i < fileList.length; ++i) {
            if (fileList[i].isDirectory()) {
               size += getFolderSize(fileList[i]);
            } else {
               size += fileList[i].length();
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }

      return size;
   }

   public static String readFile(File file, String charset) {
      String fileContent = "";

      try {
         InputStreamReader read = new InputStreamReader(new FileInputStream(file), charset);
         BufferedReader reader = new BufferedReader(read);
         String line = "";

         for(int i = 0; (line = reader.readLine()) != null; ++i) {
            if (i == 0) {
               fileContent = line;
            } else {
               fileContent = fileContent + "\n" + line;
            }
         }

         read.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      return fileContent;
   }

   public static String readFile(File file) {
      return readFile(file, "UTF-8");
   }

   public static String getFileContent(String filePath) {
      File file = new File(filePath);
      if (file.exists()) {
         try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String result = null;

            String s;
            for(s = null; (s = br.readLine()) != null; result = result + "\n" + s) {
            }

            br.close();
            return result;
         } catch (Exception e) {
            e.printStackTrace();
            e.printStackTrace();
            return null;
         }
      } else {
         return null;
      }
   }

   public static String readText(String filePath) {
      String lines = "";

      try {
         FileReader fileReader = new FileReader(filePath);
         BufferedReader bufferedReader = new BufferedReader(fileReader);

         String line;
         for(line = null; (line = bufferedReader.readLine()) != null; lines = lines + line + "\n") {
         }
      } catch (Exception e) {
         e.printStackTrace();
      }

      return lines;
   }

   public static void writeText(String filePath, String content, boolean isAppend) {
      FileOutputStream outputStream = null;
      OutputStreamWriter outputStreamWriter = null;
      BufferedWriter bufferedWriter = null;

      try {
         outputStream = new FileOutputStream(filePath, isAppend);
         outputStreamWriter = new OutputStreamWriter(outputStream);
         bufferedWriter = new BufferedWriter(outputStreamWriter);
         bufferedWriter.write(content);
      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         try {
            if (bufferedWriter != null) {
               bufferedWriter.close();
            }

            if (outputStreamWriter != null) {
               outputStreamWriter.close();
            }

            if (outputStream != null) {
               outputStream.close();
            }
         } catch (Exception e) {
            e.printStackTrace();
         }

      }

   }

   public static Boolean mkdirs(String dirPath) {
      try {
         File file = new File(dirPath);
         if (!file.exists()) {
            file.mkdirs();
         }
      } catch (Exception e) {
         e.printStackTrace();
      }

      return true;
   }

   public static boolean createFile(IndexVo indexVo, String dirPath, String suffix) {
      String destFileName = dirPath + File.separator + indexVo.getMrFileShowName() + suffix;
      File file = new File(destFileName);
      if (file.exists()) {
         return false;
      } else if (destFileName.endsWith(File.separator)) {
         return false;
      } else if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
         return false;
      } else {
         try {
            return file.createNewFile();
         } catch (IOException e) {
            e.printStackTrace();
            return false;
         }
      }
   }
}
