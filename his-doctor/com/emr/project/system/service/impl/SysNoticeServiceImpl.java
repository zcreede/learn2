package com.emr.project.system.service.impl;

import com.emr.project.system.domain.SysNotice;
import com.emr.project.system.mapper.SysNoticeMapper;
import com.emr.project.system.service.ISysNoticeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysNoticeServiceImpl implements ISysNoticeService {
   @Autowired
   private SysNoticeMapper noticeMapper;

   public SysNotice selectNoticeById(Long noticeId) {
      return this.noticeMapper.selectNoticeById(noticeId);
   }

   public List selectNoticeList(SysNotice notice) {
      return this.noticeMapper.selectNoticeList(notice);
   }

   public int insertNotice(SysNotice notice) {
      return this.noticeMapper.insertNotice(notice);
   }

   public int updateNotice(SysNotice notice) {
      return this.noticeMapper.updateNotice(notice);
   }

   public int deleteNoticeById(Long noticeId) {
      return this.noticeMapper.deleteNoticeById(noticeId);
   }

   public int deleteNoticeByIds(Long[] noticeIds) {
      return this.noticeMapper.deleteNoticeByIds(noticeIds);
   }
}
