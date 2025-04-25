package com.emr.project.tmpm.service;

import com.emr.project.tmpm.domain.ClinitemUseLog;
import com.emr.project.tmpm.domain.vo.ClinitemUseLogVo;
import java.util.Date;
import java.util.List;

public interface IClinitemUseLogService {
   ClinitemUseLog selectClinitemUseLogById(Long id);

   List selectClinitemUseLogList(ClinitemUseLog clinitemUseLog);

   List selectClinitemUseLogListVo(ClinitemUseLogVo clinitemUseLog) throws Exception;

   int insertClinitemUseLog(ClinitemUseLog clinitemUseLog);

   int updateClinitemUseLog(ClinitemUseLog clinitemUseLog);

   int deleteClinitemUseLogByIds(Long[] ids);

   int deleteClinitemUseLogById(Long id);

   Date selectMaxAltDate() throws Exception;

   void updateClinitemUseLogTask(List orderDetailVoList) throws Exception;
}
