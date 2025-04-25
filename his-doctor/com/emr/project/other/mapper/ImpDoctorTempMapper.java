package com.emr.project.other.mapper;

import com.emr.project.other.domain.ImpDoctorTemp;
import com.emr.project.other.domain.vo.ImpDoctorTempVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ImpDoctorTempMapper {
   ImpDoctorTemp selectImpDoctorTempById(Long id);

   List selectImpDoctorTempList(ImpDoctorTempVo impDoctorTempVo);

   int insertImpDoctorTemp(ImpDoctorTemp impDoctorTemp);

   int batchAddImpDoctorTemp(List impDoctorTempList);

   int updateImpDoctorTemp(ImpDoctorTemp impDoctorTemp);

   int updateImpDoctorTempForMove(ImpDoctorTemp impDoctorTemp);

   int deleteImpDoctorTempById(Long id);

   int deleteImpDoctorTempByIds(Long[] ids);

   List selectDocTempImpowerList(String docCd, String deptCd);

   List selectTmpByPatAndDoc(String patientId, String impDocCd, String impType);

   void updateImpPatAndDoc(String patientId, String impDocCd, String impType);

   void updateImpDoctorTempByThird(@Param("userName") String userName, @Param("patientId") String patientId);

   List selectImpDoctorTemp(@Param("userName") String userName, @Param("patientId") String patientId);

   List selectTmpByPatAndDocOrDept(@Param("patientId") String patientId, @Param("docCd") String docCd, @Param("deptCd") String deptCd);
}
