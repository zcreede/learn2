package com.emr.project.pat.service;

import com.emr.project.pat.domain.AlleInfo;
import com.emr.project.system.domain.vo.SqlVo;
import java.util.List;

public interface IAlleInfoService {
   List selectAlleInfoByPatientId(String patientId) throws Exception;

   List selectAlleInfoList(AlleInfo alleInfo) throws Exception;

   int insertAlleInfo(AlleInfo alleInfo);

   void insertAlleInfoList(List alleInfoList) throws Exception;

   int updateAlleInfo(AlleInfo alleInfo);

   int deleteAlleInfoByIds(String[] ids);

   int deleteAlleInfoById(String id);

   void deleteAlleInfoByPatientId(String patientId);

   List selectAlleDictList() throws Exception;

   List selectAlleInfosByInpNos(List list) throws Exception;

   List selectAlleInfosByInpNoOrMainId(List param) throws Exception;

   List selectByInpNoAndDrugCode(String admissionNo, String allergyCode) throws Exception;

   List selectHisAlleInfoList(SqlVo sqlVo) throws Exception;

   List syncHisAlleInfoList(List inpNoList) throws Exception;

   void insertAlleInfoAdd(List hisList) throws Exception;

   void deleteAlleInfoByInpNoList(List inpNoList) throws Exception;
}
