package com.emr.project.emr.service;

import com.emr.project.emr.domain.EmrSharing;
import com.emr.project.emr.domain.Index;
import com.emr.project.system.domain.SysUser;
import java.util.List;

public interface IEmrSharingService {
   EmrSharing selectEmrSharingById(Long id);

   List selectEmrSharingList(EmrSharing emrSharing);

   int insertEmrSharing(EmrSharing emrSharing);

   int updateEmrSharing(EmrSharing emrSharing);

   int deleteEmrSharingByIds(Long[] ids);

   int deleteEmrSharingById(Long id);

   void insertList(List list);

   Boolean judgeEmrSharing();

   void indexSaveEmrSharingElems(String mrType, Index index, SysUser user, List elemAttriVoList);

   List selectEmrSharingListByPatientId(String patientId);
}
