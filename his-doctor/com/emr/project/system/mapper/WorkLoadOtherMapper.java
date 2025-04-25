package com.emr.project.system.mapper;

import com.emr.project.system.domain.WorkLoadOther;
import com.emr.project.system.domain.req.WorkLoadReportReq;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WorkLoadOtherMapper {
   WorkLoadOther selectWorkLoadOtherById(Long id);

   List selectWorkLoadOtherList(WorkLoadOther workLoadOther);

   int insertWorkLoadOther(WorkLoadOther workLoadOther);

   int updateWorkLoadOther(WorkLoadOther workLoadOther);

   int deleteWorkLoadOtherById(Long id);

   int deleteWorkLoadOtherByIds(Long[] ids);

   void insertWorkLoadOtherList(@Param("list") List otherList);

   void updateWorkLoadOtherList(@Param("list") List otherList);

   List workLoadReport(WorkLoadReportReq req);

   Integer selectOtherInit(@Param("sqlScript") String sqlScript);

   void deleteWorkByMainId(@Param("mainId") Long id);

   List selectWorkLoadOtherByMainId(@Param("mainId") Long id);
}
