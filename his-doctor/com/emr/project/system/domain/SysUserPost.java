package com.emr.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SysUserPost {
   private Long userId;
   private Long postId;

   public Long getUserId() {
      return this.userId;
   }

   public void setUserId(Long userId) {
      this.userId = userId;
   }

   public Long getPostId() {
      return this.postId;
   }

   public void setPostId(Long postId) {
      this.postId = postId;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("userId", this.getUserId()).append("postId", this.getPostId()).toString();
   }
}
