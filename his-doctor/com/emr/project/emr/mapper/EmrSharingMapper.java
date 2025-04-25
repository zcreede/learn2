package com.emr.project.emr.mapper;

import com.emr.project.emr.domain.EmrSharing;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmrSharingMapper {
   EmrSharing selectEmrSharingById(Long id);

   List selectEmrSharingList(EmrSharing emrSharing);

   int insertEmrSharing(EmrSharing emrSharing);

   int updateEmrSharing(EmrSharing emrSharing);

   int deleteEmrSharingById(Long id);

   int deleteEmrSharingByIds(Long[] ids);

   void deleteEmrSharingByPatientIdElemIds(@Param("elemIds") Long[] elemIds, @Param("patientId") String patientId);

   List selectEmrSharingListByPatientId(String patientId);

   void insertList(List list);
}
