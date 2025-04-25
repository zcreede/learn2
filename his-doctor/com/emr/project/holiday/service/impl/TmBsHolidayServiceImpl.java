package com.emr.project.holiday.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.common.service.ICommonService;
import com.emr.project.holiday.domain.TmBsHoliday;
import com.emr.project.holiday.mapper.TmBsHolidayMapper;
import com.emr.project.holiday.service.ITmBsHolidayService;
import com.emr.project.system.domain.SysUser;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TmBsHolidayServiceImpl implements ITmBsHolidayService {
   @Autowired
   private TmBsHolidayMapper tmBsHolidayMapper;
   @Autowired
   private ICommonService commonService;

   public TmBsHoliday selectTmBsHolidayById(Long id) {
      return this.tmBsHolidayMapper.selectTmBsHolidayById(id);
   }

   public List selectTmBsHolidayList(TmBsHoliday tmBsHoliday) {
      return this.tmBsHolidayMapper.selectTmBsHolidayList(tmBsHoliday);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public int insertTmBsHoliday(TmBsHoliday tmBsHoliday) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      tmBsHoliday.setCreDate(this.commonService.getDbSysdate());
      tmBsHoliday.setCrePerCode(user.getUserName());
      tmBsHoliday.setCrePerName(user.getNickName());
      tmBsHoliday.setId(SnowIdUtils.uniqueLong());
      return this.tmBsHolidayMapper.insertTmBsHoliday(tmBsHoliday);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public int updateTmBsHoliday(TmBsHoliday tmBsHoliday) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      tmBsHoliday.setAltDate(this.commonService.getDbSysdate());
      tmBsHoliday.setAltPerCode(user.getUserName());
      tmBsHoliday.setAltPerName(user.getNickName());
      return this.tmBsHolidayMapper.updateTmBsHoliday(tmBsHoliday);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public int deleteTmBsHolidayByIds(Long[] ids) {
      return this.tmBsHolidayMapper.deleteTmBsHolidayByIds(ids);
   }

   public int deleteTmBsHolidayById(Long id) {
      return this.tmBsHolidayMapper.deleteTmBsHolidayById(id);
   }

   public int selectHolidayCount(Date dbSysdate, String holidayType) throws Exception {
      return this.tmBsHolidayMapper.selectHolidayCount(holidayType, dbSysdate);
   }
}
