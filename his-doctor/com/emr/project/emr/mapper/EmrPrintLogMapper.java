package com.emr.project.emr.mapper;

import com.emr.project.emr.domain.EmrPrintLog;
import java.util.List;

public interface EmrPrintLogMapper {
   EmrPrintLog selectEmrPrintLogById(String id);

   List selectEmrPrintLogList(EmrPrintLog emrPrintLog);

   int insertEmrPrintLog(EmrPrintLog emrPrintLog);

   Integer getBeginRow(String fileId);

   int updateEmrPrintLog(EmrPrintLog emrPrintLog);

   int deleteEmrPrintLogById(String id);

   int deleteEmrPrintLogByIds(String[] ids);
}
