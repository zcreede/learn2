package com.emr.project.tmpm.mapper;

import com.emr.project.tmpm.domain.ClinitemUseLog;
import com.emr.project.tmpm.domain.vo.ClinitemUseLogVo;
import java.util.List;

public interface ClinitemUseLogMapper {
   ClinitemUseLog selectClinitemUseLogById(Long id);

   List selectClinitemUseLogList(ClinitemUseLog clinitemUseLog);

   List selectClinitemUseLogListVo(ClinitemUseLogVo clinitemUseLog);

   int insertClinitemUseLog(ClinitemUseLog clinitemUseLog);

   int updateClinitemUseLog(ClinitemUseLog clinitemUseLog);

   int deleteClinitemUseLogById(Long id);

   int deleteClinitemUseLogByIds(Long[] ids);

   ClinitemUseLog selectMaxAltDate();

   List selectListByDocList(List docCdList);

   void insertList(List list);

   void updateusageTimes(List list);
}
