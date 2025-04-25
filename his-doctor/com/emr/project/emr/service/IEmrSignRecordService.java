package com.emr.project.emr.service;

import com.emr.project.emr.domain.EmrSignRecord;
import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.SubfileIndex;
import com.emr.project.emr.domain.vo.EmrSignRecordVo;
import com.emr.project.emr.domain.vo.IndexSignCancelVo;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public interface IEmrSignRecordService {
   EmrSignRecord selectEmrSignRecordById(Long id);

   List selectEmrSignRecordList(EmrSignRecord emrSignRecord);

   List selectEmrSignRecordFreeList(EmrSignRecord emrSignRecord);

   List selectEmrSignRecordForFeeMoveList(EmrSignRecord emrSignRecord);

   List selectEmrSignRecordListByMainFileId(String mainFileId);

   List selectEmrSignRecordListByMainFileIdForFreeMove(String mainFileId);

   EmrSignRecord selectEmrSignRecordByFileIdAndDoc(String mainFileId, String docCode);

   int insertEmrSignRecord(EmrSignRecord emrSignRecord);

   int updateEmrSignRecord(EmrSignRecord emrSignRecord);

   int updateEmrSignRecordByMrFileId(EmrSignRecord emrSignRecord);

   int deleteEmrSignRecordByIds(Long[] ids);

   int deleteEmrSignRecordById(Long id);

   Boolean selectSignState(Index index, SubfileIndex subfileIndex, HttpServletRequest request) throws Exception;

   EmrSignRecordVo cancleEmrSign(Index index, SubfileIndex subfileIndex, List cancelSignList) throws Exception;

   EmrSignRecordVo cancleEmrSignForFreeMove(Index index, IndexSignCancelVo indexSignCancelVo) throws Exception;

   void addList(List signRecordList);

   void addPatList(List signRecordList);

   void batchAddFreeMoveList(List signRecordList);

   List selectEmrSignRecordListByIdAndFlag(List idList, String signCancFlag);
}
