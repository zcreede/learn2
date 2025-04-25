package com.emr.project.pat.mapper;

import com.emr.project.pat.domain.AlleInfo;
import com.emr.project.system.domain.vo.SqlVo;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface AlleInfoMapper {
   List selectAlleInfoByPatientMainId(String patientMainId);

   List selectAlleInfoList(AlleInfo alleInfo);

   int insertAlleInfo(AlleInfo alleInfo);

   void insertAlleInfoList(List alleInfoList);

   int updateAlleInfo(AlleInfo alleInfo);

   int deleteAlleInfoById(String id);

   int deleteAlleInfoByIds(String[] ids);

   void insertMap(Map map);

   void deleteAlleInfoByPatientId(String patientId);

   List selectAlleInfosByInpNos(List list);

   List selectAlleInfosByInpNoOrMainId(List list);

   List selectByInpNoAndDrugCode(@Param("admissionNo") String admissionNo, @Param("allergyCode") String allergyCode);

   void deleteAlleInfo();

   List selectHisAlleInfoList(SqlVo sqlVo);

   void deleteAlleInfoByInpNoList(List inpNoList);
}
