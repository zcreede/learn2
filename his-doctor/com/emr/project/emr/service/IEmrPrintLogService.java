package com.emr.project.emr.service;

import com.emr.project.emr.domain.EmrPrintLog;
import java.util.List;

public interface IEmrPrintLogService {
   EmrPrintLog selectEmrPrintLogById(String id);

   List selectEmrPrintLogList(EmrPrintLog emrPrintLog);

   int insertEmrPrintLog(EmrPrintLog emrPrintLog) throws Exception;

   Integer getBeginRow(String fileId) throws Exception;

   int updateEmrPrintLog(EmrPrintLog emrPrintLog);

   int deleteEmrPrintLogByIds(String[] ids);

   int deleteEmrPrintLogById(String id);
}
