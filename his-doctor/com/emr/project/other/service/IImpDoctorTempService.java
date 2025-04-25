package com.emr.project.other.service;

import com.emr.project.other.domain.ImpDoctorTemp;
import com.emr.project.other.domain.vo.ImpDoctorTempVo;
import java.util.List;

public interface IImpDoctorTempService {
   ImpDoctorTemp selectImpDoctorTempById(Long id);

   List selectImpDoctorTempList(ImpDoctorTempVo impDoctorTempVo);

   int insertImpDoctorTemp(ImpDoctorTemp impDoctorTemp);

   int batchAddImpDoctorTemp(List impDoctorTempList);

   int insertImpDoctor(ImpDoctorTemp impDoctorTemp);

   int updateImpDoctorTemp(ImpDoctorTemp impDoctorTemp);

   int updateImpDoctorTempForMove(ImpDoctorTemp impDoctorTemp);

   int deleteImpDoctorTempByIds(Long[] ids);

   int deleteImpDoctorTempById(Long id);

   List selectDocTempImpowerList(String docCd, String deptCd) throws Exception;

   List selectTmpByPatAndDoc(String patientId, String impDocCd, String impType) throws Exception;

   void updateImpPatAndDoc(String patientId, String impDocCd, String impType) throws Exception;

   Boolean selectImpDocPatBool(String patientId) throws Exception;

   void updateImpDoctorTempByThird(String userName, String patientId);

   List selectImpDoctorTemp(String userName, String patientId);

   List selectTmpByPatAndDocOrDept(String patientId, String docCd, String deptCd) throws Exception;
}
