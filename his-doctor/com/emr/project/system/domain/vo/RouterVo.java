package com.emr.project.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;

@JsonInclude(Include.NON_EMPTY)
public class RouterVo {
   private String name;
   private String path;
   private boolean hidden;
   private String redirect;
   private String component;
   private Boolean alwaysShow;
   private MetaVo meta;
   private List children;
   private String browserType;
   private String browserTypeName;
   private String isFrame;
   private String skipMode;

   public String getSkipMode() {
      return this.skipMode;
   }

   public void setSkipMode(String skipMode) {
      this.skipMode = skipMode;
   }

   public String getBrowserType() {
      return this.browserType;
   }

   public void setBrowserType(String browserType) {
      this.browserType = browserType;
   }

   public String getBrowserTypeName() {
      return this.browserTypeName;
   }

   public void setBrowserTypeName(String browserTypeName) {
      this.browserTypeName = browserTypeName;
   }

   public String getIsFrame() {
      return this.isFrame;
   }

   public void setIsFrame(String isFrame) {
      this.isFrame = isFrame;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getPath() {
      return this.path;
   }

   public void setPath(String path) {
      this.path = path;
   }

   public boolean getHidden() {
      return this.hidden;
   }

   public void setHidden(boolean hidden) {
      this.hidden = hidden;
   }

   public String getRedirect() {
      return this.redirect;
   }

   public void setRedirect(String redirect) {
      this.redirect = redirect;
   }

   public String getComponent() {
      return this.component;
   }

   public void setComponent(String component) {
      this.component = component;
   }

   public Boolean getAlwaysShow() {
      return this.alwaysShow;
   }

   public void setAlwaysShow(Boolean alwaysShow) {
      this.alwaysShow = alwaysShow;
   }

   public MetaVo getMeta() {
      return this.meta;
   }

   public void setMeta(MetaVo meta) {
      this.meta = meta;
   }

   public List getChildren() {
      return this.children;
   }

   public void setChildren(List children) {
      this.children = children;
   }
}
