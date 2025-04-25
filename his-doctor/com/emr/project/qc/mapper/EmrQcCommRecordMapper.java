package com.emr.project.qc.mapper;

import com.emr.project.qc.domain.EmrQcCommRecord;
import java.util.List;

public interface EmrQcCommRecordMapper {
   EmrQcCommRecord selectEmrQcCommRecordById(Long id);

   List selectEmrQcCommRecordByMainIdList(List mainIdList);

   List selectEmrQcCommRecordList(EmrQcCommRecord emrQcCommRecord);

   int insertEmrQcCommRecord(EmrQcCommRecord emrQcCommRecord);

   void insertList(List list);

   int updateEmrQcCommRecord(EmrQcCommRecord emrQcCommRecord);

   int deleteEmrQcCommRecordById(Long id);

   int deleteEmrQcCommRecordByIds(Long[] ids);
}
