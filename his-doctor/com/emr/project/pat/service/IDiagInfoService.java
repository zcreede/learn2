package com.emr.project.pat.service;

import com.emr.project.pat.domain.DiagInfo;
import java.util.List;
import java.util.Set;

public interface IDiagInfoService {
   DiagInfo selectDiagInfoById(String id);

   List selectDiagInfoList(DiagInfo diagInfo) throws Exception;

   DiagInfo selectDiagInfo(DiagInfo diagInfo);

   int insertDiagInfo(DiagInfo diagInfo);

   int updateDiagInfo(DiagInfo diagInfo);

   int deleteDiagInfoByIds(String[] ids);

   int deleteDiagInfoById(String id);

   List selectHistoryList(DiagInfo diagInfo) throws Exception;

   List selectDiagInfoByPatientId(String patientId) throws Exception;

   void saveDiagInfo(List diagInfoList) throws Exception;

   List selectPatientMainDiag(String patientId, String diagTypeCd, String diagClassCd) throws Exception;

   List syncHisDiagIInfoList(List inpNoList) throws Exception;

   void insertDiagInfoAdd(List list) throws Exception;

   void deleteDiagInfoByPatientIds(List inpNoList) throws Exception;

   List selectInHosDiagInFoByPatientId(String patientId);

   List selectPatientMainDiagByDiagTypeCdList(String patientId, Set stringSet) throws Exception;
}
