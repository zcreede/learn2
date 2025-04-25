package com.emr.project.operation.mapper;

import com.emr.project.operation.domain.Baseinfomation;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaseinfomationMapper {
   Baseinfomation findBaseInfo(@Param("admissionNo") String admission_no);

   List findBaseInfoByIdcard(@Param("idcard") String idcard);
}
