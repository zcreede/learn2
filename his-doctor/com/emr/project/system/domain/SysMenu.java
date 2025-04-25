package com.emr.project.system.domain;

import com.emr.common.utils.StringUtils;
import com.emr.framework.web.domain.BaseEntity;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SysMenu extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long menuId;
   private String menuName;
   private String parentName;
   private Long parentId;
   private String orderNum;
   private String path;
   private String component;
   private String isFrame;
   private String isCache;
   private String menuType;
   private String visible;
   private String status;
   private String perms;
   private String icon;
   private String browserType;
   private String browserTypeName;
   private List children = new ArrayList();
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

   public Long getMenuId() {
      return this.menuId;
   }

   public void setMenuId(Long menuId) {
      this.menuId = menuId;
   }

   public @NotBlank(
   message = "菜单名称不能为空"
) @Size(
   min = 0,
   max = 50,
   message = "菜单名称长度不能超过50个字符"
) String getMenuName() {
      return this.menuName;
   }

   public void setMenuName(String menuName) {
      this.menuName = menuName;
   }

   public String getParentName() {
      return this.parentName;
   }

   public void setParentName(String parentName) {
      this.parentName = parentName;
   }

   public Long getParentId() {
      return this.parentId;
   }

   public void setParentId(Long parentId) {
      this.parentId = parentId;
   }

   public @NotBlank(
   message = "显示顺序不能为空"
) String getOrderNum() {
      return this.orderNum;
   }

   public void setOrderNum(String orderNum) {
      this.orderNum = orderNum;
   }

   public @Size(
   min = 0,
   max = 200,
   message = "路由地址不能超过200个字符"
) String getPath() {
      return this.path;
   }

   public void setPath(String path) {
      this.path = path;
   }

   public @Size(
   min = 0,
   max = 200,
   message = "组件路径不能超过255个字符"
) String getComponent() {
      return this.component;
   }

   public void setComponent(String component) {
      this.component = component;
   }

   public String getIsFrame() {
      return this.isFrame;
   }

   public void setIsFrame(String isFrame) {
      this.isFrame = isFrame;
   }

   public String getIsCache() {
      return this.isCache;
   }

   public void setIsCache(String isCache) {
      this.isCache = isCache;
   }

   public @NotBlank(
   message = "菜单类型不能为空"
) String getMenuType() {
      return this.menuType;
   }

   public void setMenuType(String menuType) {
      this.menuType = menuType;
   }

   public String getVisible() {
      return this.visible;
   }

   public void setVisible(String visible) {
      this.visible = visible;
   }

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public @Size(
   min = 0,
   max = 100,
   message = "权限标识长度不能超过100个字符"
) String getPerms() {
      return StringUtils.isEmpty(this.perms) ? "" : this.perms;
   }

   public void setPerms(String perms) {
      this.perms = perms;
   }

   public String getIcon() {
      return this.icon;
   }

   public void setIcon(String icon) {
      this.icon = icon;
   }

   public List getChildren() {
      return this.children;
   }

   public void setChildren(List children) {
      this.children = children;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("menuId", this.getMenuId()).append("menuName", this.getMenuName()).append("parentId", this.getParentId()).append("orderNum", this.getOrderNum()).append("path", this.getPath()).append("component", this.getComponent()).append("isFrame", this.getIsFrame()).append("IsCache", this.getIsCache()).append("menuType", this.getMenuType()).append("visible", this.getVisible()).append("status ", this.getStatus()).append("perms", this.getPerms()).append("icon", this.getIcon()).append("createBy", this.getCreateBy()).append("createTime", this.getCreateTime()).append("updateBy", this.getUpdateBy()).append("updateTime", this.getUpdateTime()).append("remark", this.getRemark()).toString();
   }
}
