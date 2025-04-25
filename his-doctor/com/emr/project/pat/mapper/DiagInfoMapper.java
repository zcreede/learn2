package com.emr.project.pat.mapper;

import com.emr.project.pat.domain.DiagInfo;
import com.emr.project.system.domain.vo.SqlVo;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.ibatis.annotations.Param;

public interface DiagInfoMapper {
   DiagInfo selectDiagInfoById(String id);

   List selectHistoryList(DiagInfo diagInfo);

   DiagInfo selectDiagInfo(DiagInfo diagInfo);

   List selectDiagInfoList(DiagInfo diagInfo);

   int insertDiagInfo(DiagInfo diagInfo);

   void insertDiagInfoList(List diagInfoList);

   int updateDiagInfo(DiagInfo diagInfo);

   void updateDiagInfoList(List diagInfoList);

   int deleteDiagInfoById(String id);

   int deleteDiagInfoByPatientId(String patientId);

   int deleteDiagInfoByIds(String[] ids);

   void insertMap(Map map);

   List selectDiagInfoByPatientId(String patientId);

   List selectPatientMainDiag(@Param("patientId") String patientId, @Param("diagTypeCd") String diagTypeCd, @Param("diagClassCd") String diagClassCd) throws Exception;

   void deleteDiagInfoAll();

   List selectHisDiagInfoList(SqlVo sqlVo);

   void deleteDiagInfoByPatientIds(List inpNoList);

   List selectInHosDiagInFoByPatientId(@Param("patientId") String patientId);

   List selectPatientMainDiagByDiagTypeCdList(@Param("patientId") String patientId, @Param("list") Set stringSet);
}
