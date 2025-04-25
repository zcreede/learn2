package com.emr.project.pat.service;

import com.emr.project.pat.domain.BabyInfo;
import java.util.List;

public interface IBabyInfoService {
   BabyInfo selectBabyInfoById(String patientId);

   List selectBabyInfoList(BabyInfo babyInfo);

   List selectBabyInfoByDeptCodeList(BabyInfo babyInfo);

   int insertBabyInfo(BabyInfo babyInfo);

   int updateBabyInfo(BabyInfo babyInfo);

   int deleteBabyInfoByIds(String[] patientIds);

   int deleteBabyInfoById(String patientId);

   List selectBabyTreeList(BabyInfo babyInfo) throws Exception;

   List selectBabyInfoListByPatientId(String patientId) throws Exception;

   List selectBabyInfoListGroupByPat(BabyInfo babyInfo) throws Exception;

   List selectBabyInfoByDeptCodeListGroupByPat(BabyInfo babyInfo) throws Exception;

   List selectHisBabyInfoList(List inpNoList) throws Exception;

   void deleteBabyInfoByInpNo(List inpNoList) throws Exception;

   void insertHisBaby(List hisDataList) throws Exception;

   List selectListByBabyAdmissionNoList(List admissionNoList) throws Exception;
}
