package com.emr.common.utils.file;

import com.emr.common.exception.file.FileNameLengthLimitExceededException;
import com.emr.common.exception.file.FileSizeLimitExceededException;
import com.emr.common.exception.file.InvalidExtensionException;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.UUIDUtils;
import com.emr.framework.config.EMRConfig;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtils {
   public static final long DEFAULT_MAX_SIZE = 52428800L;
   public static final int DEFAULT_FILE_NAME_LENGTH = 100;
   private static String defaultBaseDir = EMRConfig.getProfile();

   public static void setDefaultBaseDir(String defaultBaseDir) {
      FileUploadUtils.defaultBaseDir = defaultBaseDir;
   }

   public static String getDefaultBaseDir() {
      return defaultBaseDir;
   }

   public static final String upload(MultipartFile file) throws IOException {
      try {
         return upload(getDefaultBaseDir(), file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
      } catch (Exception e) {
         throw new IOException(e.getMessage(), e);
      }
   }

   public static final String upload(String baseDir, MultipartFile file) throws IOException {
      try {
         return upload(baseDir, file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
      } catch (Exception e) {
         throw new IOException(e.getMessage(), e);
      }
   }

   public static final String upload(String baseDir, MultipartFile file, String[] allowedExtension) throws FileSizeLimitExceededException, IOException, FileNameLengthLimitExceededException, InvalidExtensionException {
      int fileNamelength = file.getOriginalFilename().length();
      if (fileNamelength > 100) {
         throw new FileNameLengthLimitExceededException(100);
      } else {
         assertAllowed(file, allowedExtension);
         String fileName = extractFilename(file);
         File desc = getAbsoluteFile(baseDir, fileName);
         file.transferTo(desc);
         String pathFileName = getPathFileName(baseDir, fileName);
         return pathFileName;
      }
   }

   public static final String extractFilename(MultipartFile file) {
      String fileName = file.getOriginalFilename();
      String extension = getExtension(file);
      fileName = DateUtils.datePath() + "/" + UUIDUtils.fastUUID() + "." + extension;
      return fileName;
   }

   private static final File getAbsoluteFile(String uploadDir, String fileName) throws IOException {
      File desc = new File(uploadDir + File.separator + fileName);
      if (!desc.exists() && !desc.getParentFile().exists()) {
         desc.getParentFile().mkdirs();
      }

      return desc;
   }

   private static final String getPathFileName(String uploadDir, String fileName) throws IOException {
      int dirLastIndex = EMRConfig.getProfile().length() + 1;
      String currentDir = StringUtils.substring(uploadDir, dirLastIndex);
      String pathFileName = "/profile/" + currentDir + "/" + fileName;
      return pathFileName;
   }

   public static final void assertAllowed(MultipartFile file, String[] allowedExtension) throws FileSizeLimitExceededException, InvalidExtensionException {
      long size = file.getSize();
      if (size > 52428800L) {
         throw new FileSizeLimitExceededException(50L);
      } else {
         String fileName = file.getOriginalFilename();
         String extension = getExtension(file);
         if (allowedExtension != null && !isAllowedExtension(extension, allowedExtension)) {
            if (allowedExtension == MimeTypeUtils.IMAGE_EXTENSION) {
               throw new InvalidExtensionException.InvalidImageExtensionException(allowedExtension, extension, fileName);
            } else if (allowedExtension == MimeTypeUtils.FLASH_EXTENSION) {
               throw new InvalidExtensionException.InvalidFlashExtensionException(allowedExtension, extension, fileName);
            } else if (allowedExtension == MimeTypeUtils.MEDIA_EXTENSION) {
               throw new InvalidExtensionException.InvalidMediaExtensionException(allowedExtension, extension, fileName);
            } else if (allowedExtension == MimeTypeUtils.VIDEO_EXTENSION) {
               throw new InvalidExtensionException.InvalidVideoExtensionException(allowedExtension, extension, fileName);
            } else {
               throw new InvalidExtensionException(allowedExtension, extension, fileName);
            }
         }
      }
   }

   public static final boolean isAllowedExtension(String extension, String[] allowedExtension) {
      for(String str : allowedExtension) {
         if (str.equalsIgnoreCase(extension)) {
            return true;
         }
      }

      return false;
   }

   public static final String getExtension(MultipartFile file) {
      String extension = FilenameUtils.getExtension(file.getOriginalFilename());
      if (StringUtils.isEmpty(extension)) {
         extension = MimeTypeUtils.getExtension(file.getContentType());
      }

      return extension;
   }
}
