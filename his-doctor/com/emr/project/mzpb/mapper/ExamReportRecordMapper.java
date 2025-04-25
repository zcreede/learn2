package com.emr.project.mzpb.mapper;

import com.emr.framework.aspectj.lang.annotation.DataSource;
import com.emr.framework.aspectj.lang.enums.DataSourceType;
import com.emr.project.webservice.domain.req.EcgReq;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DataSource(DataSourceType.mzPb)
public interface ExamReportRecordMapper {
   @DataSource(DataSourceType.mzPb)
   List selectExamReportRecordInfo(@Param("applyNo") String applyNo);

   @DataSource(DataSourceType.mzPb)
   List selectSqdByDjh(@Param("applyNo") String applyNo);

   @DataSource(DataSourceType.mzPb)
   int updateByDjh(EcgReq ecgReq);

   @DataSource(DataSourceType.mzPb)
   int insert(EcgReq ecgReq);
}
