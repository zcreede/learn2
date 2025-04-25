package com.emr.project.pat.service;

import com.emr.project.pat.domain.Personalinfo;
import com.emr.project.pat.domain.vo.PersonalinfoForSxVo;
import com.emr.project.pat.domain.vo.PersonalinfoVo;
import com.emr.project.system.domain.vo.SqlVo;
import java.util.List;
import java.util.Map;

public interface IPersonalinfoService {
   Personalinfo selectPersonalinfoById(String patientId);

   List selectPersonalinfoList(Personalinfo personalinfo);

   int insertPersonalinfo(Personalinfo personalinfo);

   int updatePersonalinfo(Personalinfo personalinfo) throws Exception;

   void updatePersonAndVisit(PersonalinfoVo personalinfo) throws Exception;

   int deletePersonalinfoByIds(String[] patientIds);

   int deletePersonalinfoById(String patientId);

   PersonalinfoVo selectAllList(PersonalinfoVo personalinfoVo) throws Exception;

   void updateModiFlag(List list);

   int updatePersonSave(Personalinfo personalinfo) throws Exception;

   List selectPersonListByPatientId(String patientId) throws Exception;

   PersonalinfoVo selectPersonalinfoVoById(String patientId) throws Exception;

   Personalinfo selectPersonalinfoByInpNo(String inpNo) throws Exception;

   List setSyncPersonalInfoList(SqlVo deptVo) throws Exception;

   List getPatientInfoForSx(PersonalinfoForSxVo personalinfoForSxVo) throws Exception;

   void deletePersonalinfo(String tableName, String inpNo) throws Exception;

   void deletePersonalinfoByInpNo(String tableName, List inpNoList) throws Exception;

   void insertMapList(Map map) throws Exception;

   Boolean checkIdCardRepeat(String patientId, String idCard);
}
