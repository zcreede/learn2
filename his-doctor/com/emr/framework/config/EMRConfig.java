package com.emr.framework.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(
   prefix = "emr"
)
public class EMRConfig {
   private String name;
   private String version;
   private String copyrightYear;
   private boolean demoEnabled;
   private static String profile;
   private static boolean addressEnabled;
   private String ssoUrl;
   private static String nfsPdfurl;
   private List wardFileType = new ArrayList();

   public static String getNfsPdfurl() {
      return nfsPdfurl;
   }

   public void setNfsPdfurl(String nfsPdfurl) {
      EMRConfig.nfsPdfurl = nfsPdfurl;
   }

   public String getSsoUrl() {
      return this.ssoUrl;
   }

   public void setSsoUrl(String ssoUrl) {
      this.ssoUrl = ssoUrl;
   }

   public List getWardFileType() {
      return this.wardFileType;
   }

   public void setWardFileType(List wardFileType) {
      this.wardFileType = wardFileType;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getVersion() {
      return this.version;
   }

   public void setVersion(String version) {
      this.version = version;
   }

   public String getCopyrightYear() {
      return this.copyrightYear;
   }

   public void setCopyrightYear(String copyrightYear) {
      this.copyrightYear = copyrightYear;
   }

   public boolean isDemoEnabled() {
      return this.demoEnabled;
   }

   public void setDemoEnabled(boolean demoEnabled) {
      this.demoEnabled = demoEnabled;
   }

   public static String getProfile() {
      return profile;
   }

   public void setProfile(String profile) {
      EMRConfig.profile = profile;
   }

   public static boolean isAddressEnabled() {
      return addressEnabled;
   }

   public void setAddressEnabled(boolean addressEnabled) {
      EMRConfig.addressEnabled = addressEnabled;
   }

   public static String getAvatarPath() {
      return getProfile() + "/avatar";
   }

   public static String getDownloadPath() {
      return getProfile() + "/download/";
   }

   public static String getUploadPath() {
      return getProfile() + "/upload";
   }
}
