package com.emr.project.system.domain;

import com.emr.framework.web.domain.BaseEntity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SysNotice extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long noticeId;
   private String noticeTitle;
   private String noticeType;
   private String noticeContent;
   private String status;

   public Long getNoticeId() {
      return this.noticeId;
   }

   public void setNoticeId(Long noticeId) {
      this.noticeId = noticeId;
   }

   public void setNoticeTitle(String noticeTitle) {
      this.noticeTitle = noticeTitle;
   }

   public @NotBlank(
   message = "公告标题不能为空"
) @Size(
   min = 0,
   max = 50,
   message = "公告标题不能超过50个字符"
) String getNoticeTitle() {
      return this.noticeTitle;
   }

   public void setNoticeType(String noticeType) {
      this.noticeType = noticeType;
   }

   public String getNoticeType() {
      return this.noticeType;
   }

   public void setNoticeContent(String noticeContent) {
      this.noticeContent = noticeContent;
   }

   public String getNoticeContent() {
      return this.noticeContent;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String getStatus() {
      return this.status;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("noticeId", this.getNoticeId()).append("noticeTitle", this.getNoticeTitle()).append("noticeType", this.getNoticeType()).append("noticeContent", this.getNoticeContent()).append("status", this.getStatus()).append("createBy", this.getCreateBy()).append("createTime", this.getCreateTime()).append("updateBy", this.getUpdateBy()).append("updateTime", this.getUpdateTime()).append("remark", this.getRemark()).toString();
   }
}
