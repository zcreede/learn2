package com.emr.project.system.mapper;

import com.emr.project.system.domain.WorkLoadMain;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WorkLoadMainMapper {
   WorkLoadMain selectWorkLoadMainById(Long id);

   List selectWorkLoadMainList(WorkLoadMain workLoadMain);

   int insertWorkLoadMain(WorkLoadMain workLoadMain);

   int updateWorkLoadMain(WorkLoadMain workLoadMain);

   int deleteWorkLoadMainById(Long id);

   int deleteWorkLoadMainByIds(Long[] ids);

   WorkLoadMain queryMain(@Param("deptCode") String deptCode, @Param("dateTime") String dateTime);

   Integer selectCountByDeptCode(@Param("deptCode") String deptCode, @Param("status") String status);

   Integer selectLastNowNum(@Param("deptCode") String deptCode, @Param("dateTime") String dateTime);

   Integer selectCountBySubminDate(@Param("sumDate") Date sumDate, @Param("deptCode") String deptCode);

   void updateStatusByTask();

   Integer selectCancelCountBySubminDate(@Param("sumDate") Date sumDate, @Param("deptCode") String deptCode);

   void updateCount(@Param("id") Long id, @Param("num") int i);

   void updateOtherMain(@Param("sumDate") Date sumDate, @Param("num") int i, @Param("deptCode") String deptCode);
}
