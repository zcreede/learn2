package com.emr.project.pat.service;

import com.emr.project.pat.domain.TlNaAdjustLog;
import java.util.List;

public interface ITlNaAdjustLogService {
   TlNaAdjustLog selectTlNaAdjustLogById(Long id);

   List selectTlNaAdjustLogList(TlNaAdjustLog tlNaAdjustLog);

   int insertTlNaAdjustLog(TlNaAdjustLog tlNaAdjustLog);

   int updateTlNaAdjustLog(TlNaAdjustLog tlNaAdjustLog);

   int deleteTlNaAdjustLogByIds(Long[] ids);

   int deleteTlNaAdjustLogById(Long id);

   void addAdjustLog(String mark, String service_type, String operate_type, String oldCode, String oldName, String newCode, String newName, String admission_no, String hospitalizedCount, String caseNo, String patientName);
}
