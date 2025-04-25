package com.emr.project.system.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.TdPmNotice;
import com.emr.project.system.mapper.TdPmNoticeMapper;
import com.emr.project.system.service.ITdPmNoticeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TdPmNoticeServiceImpl implements ITdPmNoticeService {
   @Autowired
   private TdPmNoticeMapper tdPmNoticeMapper;

   public TdPmNotice selectTdPmNoticeById(Long id) {
      return this.tdPmNoticeMapper.selectTdPmNoticeById(id);
   }

   public List selectTdPmNoticeList(TdPmNotice tdPmNotice) {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      tdPmNotice.setDocCd(sysUser.getUserName());
      tdPmNotice.setDeptCode(sysUser.getDept().getDeptCode());
      return this.tdPmNoticeMapper.selectTdPmNoticeList(tdPmNotice);
   }

   public int insertTdPmNotice(TdPmNotice tdPmNotice) {
      return this.tdPmNoticeMapper.insertTdPmNotice(tdPmNotice);
   }

   public int updateTdPmNotice(TdPmNotice tdPmNotice) {
      return this.tdPmNoticeMapper.updateTdPmNotice(tdPmNotice);
   }

   public int deleteTdPmNoticeByIds(Long[] ids) {
      return this.tdPmNoticeMapper.deleteTdPmNoticeByIds(ids);
   }

   public int deleteTdPmNoticeById(Long id) {
      return this.tdPmNoticeMapper.deleteTdPmNoticeById(id);
   }

   public List selectTopFiveList() throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      return this.tdPmNoticeMapper.selectTopFiveList(sysUser.getUserName(), sysUser.getDept().getDeptCode());
   }
}
