package com.emr.project.qc.service;

import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.SubfileIndex;
import com.emr.project.mrhp.domain.MrHp;
import com.emr.project.qc.domain.EmrQcCommRecord;
import com.emr.project.system.domain.SysUser;
import java.util.List;

public interface IEmrQcCommRecordService {
   EmrQcCommRecord selectEmrQcCommRecordById(Long id);

   List selectEmrQcCommRecordByMainId(List mainIdList) throws Exception;

   List selectEmrQcCommRecordList(EmrQcCommRecord emrQcCommRecord);

   void insertEmrQcCommRecord(EmrQcCommRecord emrQcCommRecord, MrHp mrHp, Index index, SubfileIndex subfileIndex, SysUser user) throws Exception;

   void insertOrderQcCommRecord(EmrQcCommRecord emrQcCommRecord, SysUser user) throws Exception;

   void insertList(List list) throws Exception;

   int updateEmrQcCommRecord(EmrQcCommRecord emrQcCommRecord);

   int deleteEmrQcCommRecordByIds(Long[] ids);

   int deleteEmrQcCommRecordById(Long id);

   void insertEmrQcListByMrHp(EmrQcCommRecord emrQcCommRecord, SysUser user) throws Exception;
}
