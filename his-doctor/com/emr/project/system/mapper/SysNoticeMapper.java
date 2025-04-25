package com.emr.project.system.mapper;

import com.emr.project.system.domain.SysNotice;
import java.util.List;

public interface SysNoticeMapper {
   SysNotice selectNoticeById(Long noticeId);

   List selectNoticeList(SysNotice notice);

   int insertNotice(SysNotice notice);

   int updateNotice(SysNotice notice);

   int deleteNoticeById(Long noticeId);

   int deleteNoticeByIds(Long[] noticeIds);
}
