package com.emr.project.pat.mapper;

import com.emr.project.pat.domain.Personalinfo;
import com.emr.project.pat.domain.vo.PersonalinfoForSxVo;
import com.emr.project.pat.domain.vo.PersonalinfoVo;
import com.emr.project.system.domain.vo.SqlVo;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface PersonalinfoMapper {
   Personalinfo selectPersonalinfoById(String patientId);

   PersonalinfoVo selectPersonalinfoVoById(String patientId);

   List selectPersonalinfoList(Personalinfo personalinfo);

   int insertPersonalinfo(Personalinfo personalinfo);

   int updatePersonalinfo(Personalinfo personalinfo);

   int updatePersonSave(Personalinfo personalinfo);

   int deletePersonalinfoById(String patientId);

   void deletePersonalinfo(String tableName, String inpNo);

   int deletePersonalinfoByIds(String[] patientIds);

   void insertMap(Map personalinfo);

   void updateMap(Map personalinfo);

   void updateModiFlag(List list);

   List selectPersonListByPatientId(String patientId);

   Personalinfo selectPersonalinfoByInpNo(String inpNo);

   void insertMapList(Map map);

   List selectSyncPersonalInfoList(SqlVo sqlVo);

   List selectHisPersonalInfoList(SqlVo sqlVo);

   List getPatientInfoForSx(PersonalinfoForSxVo personalinfoForSxVo);

   Integer checkIdCardRepeat(@Param("patientId") String patientId, @Param("idCard") String idCard);

   void deletePersonalinfoByInpNo(@Param("tableName") String tableName, @Param("inpNoList") List inpNoList);
}
