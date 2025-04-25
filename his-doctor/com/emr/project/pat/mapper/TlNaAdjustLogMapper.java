package com.emr.project.pat.mapper;

import com.emr.project.pat.domain.TlNaAdjustLog;
import java.util.List;

public interface TlNaAdjustLogMapper {
   TlNaAdjustLog selectTlNaAdjustLogById(Long id);

   List selectTlNaAdjustLogList(TlNaAdjustLog tlNaAdjustLog);

   int insertTlNaAdjustLog(TlNaAdjustLog tlNaAdjustLog);

   int updateTlNaAdjustLog(TlNaAdjustLog tlNaAdjustLog);

   int deleteTlNaAdjustLogById(Long id);

   int deleteTlNaAdjustLogByIds(Long[] ids);
}
