package com.emr.project.pat.mapper;

import com.emr.project.pat.domain.BabyInfo;
import com.emr.project.system.domain.vo.SqlVo;
import java.util.List;
import java.util.Map;

public interface BabyInfoMapper {
   BabyInfo selectBabyInfoById(String patientId);

   List selectBabyInfoList(BabyInfo babyInfo);

   List selectBabyInfoByDeptCodeList(BabyInfo babyInfo);

   int insertBabyInfo(BabyInfo babyInfo);

   int updateBabyInfo(BabyInfo babyInfo);

   int deleteBabyInfoById(String patientId);

   int deleteBabyInfoByIds(String[] patientIds);

   List selectBabyTreeList(BabyInfo babyInfo);

   List selectBabyInfoListByPatientId(String patientId);

   List selectBabyInfoListGroupByPat(BabyInfo babyInfo);

   List selectBabyInfoByDeptCodeListGroupByPat(BabyInfo babyInfo);

   Integer selectBabyInfolistByDeptCd(String deptCode);

   List selectHisBabyInfoList(SqlVo sqlVo);

   void deleteBabyInfoByInpNo(List inpNoList);

   void insertMap(Map map);

   List selectListByBabyAdmissionNoList(List list);
}
