package com.emr.project.operation.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface CommonOperationMapper {
   Map getFbInfo(String payNo);

   Date getDbTime();

   List selectGuarantee(@Param("zyh") String zyh, @Param("zycs") String zycs);

   int checkPatientIsIn(@Param("admission_no") String admission_no, @Param("hospitalized_count") String hospitalized_count) throws Exception;

   int checkUserPassWord(String userCode, String passWord, String wardNo);
}
