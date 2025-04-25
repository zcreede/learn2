package com.emr.project.emr.mapper;

import com.emr.project.emr.domain.EmrSignRecord;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmrSignRecordMapper {
   EmrSignRecord selectEmrSignRecordById(Long id);

   List selectEmrSignRecordList(EmrSignRecord emrSignRecord);

   List selectEmrSignRecordFreeList(EmrSignRecord emrSignRecord);

   List selectEmrSignRecordForFeeMoveList(EmrSignRecord emrSignRecord);

   List selectEmrSignRecordListByMainFileId(String mrFileId);

   List selectEmrSignRecordListByMainFileIdForFree(@Param("mrFileId") String mrFileId);

   List selectEmrSignRecordListByMainFileIdForFreeMove(@Param("mrFileId") String mrFileId, @Param("docCode") String docCode);

   EmrSignRecord selectEmrSignRecordByFileIdAndDoc(String mrFileId, String docCode);

   int insertEmrSignRecord(EmrSignRecord emrSignRecord);

   int updateEmrSignRecord(EmrSignRecord emrSignRecord);

   int updateEmrSignRecordByMrFileId(EmrSignRecord emrSignRecord);

   int updateEmrSignRecordBySignDataId(EmrSignRecord emrSignRecord);

   int updateBySignDataId(EmrSignRecord emrSignRecord);

   int deleteEmrSignRecordById(Long id);

   int deleteEmrSignRecordByIds(Long[] ids);

   void insertList(List list);

   void batchAddFreeMoveList(List list);

   void insertPatList(List list);

   void updateCancelSign(List idList);

   void updateCancelSignForFreeMove(List idList);

   void updateCancelSignByFile(@Param("mrFileId") Long mrFileId);

   void updateCancelSignByFreeMove(@Param("mrFileId") Long mrFileId, @Param("docCode") String docCode);

   List selectEmrSignRecordListByIdAndFlag(@Param("idList") List idList, @Param("signCancFlag") String signCancFlag);
}
