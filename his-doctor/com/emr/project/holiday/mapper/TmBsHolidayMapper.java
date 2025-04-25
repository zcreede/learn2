package com.emr.project.holiday.mapper;

import com.emr.project.holiday.domain.TmBsHoliday;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TmBsHolidayMapper {
   TmBsHoliday selectTmBsHolidayById(Long id);

   List selectTmBsHolidayList(TmBsHoliday tmBsHoliday);

   int insertTmBsHoliday(TmBsHoliday tmBsHoliday);

   int updateTmBsHoliday(TmBsHoliday tmBsHoliday);

   int deleteTmBsHolidayById(Long id);

   int deleteTmBsHolidayByIds(Long[] ids);

   int selectHolidayCount(@Param("holidayType") String holidayType, @Param("dbSysdate") Date dbSysdate);
}
