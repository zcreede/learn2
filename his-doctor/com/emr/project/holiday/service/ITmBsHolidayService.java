package com.emr.project.holiday.service;

import com.emr.project.holiday.domain.TmBsHoliday;
import java.util.Date;
import java.util.List;

public interface ITmBsHolidayService {
   TmBsHoliday selectTmBsHolidayById(Long id);

   List selectTmBsHolidayList(TmBsHoliday tmBsHoliday);

   int insertTmBsHoliday(TmBsHoliday tmBsHoliday) throws Exception;

   int updateTmBsHoliday(TmBsHoliday tmBsHoliday) throws Exception;

   int deleteTmBsHolidayByIds(Long[] ids);

   int deleteTmBsHolidayById(Long id);

   int selectHolidayCount(Date dbSysdate, String holidayType) throws Exception;
}
