package com.emr.project.emr.service;

import com.emr.project.emr.domain.EmrSignData;
import com.emr.project.emr.domain.vo.EmrSignDataVo;
import java.util.List;

public interface IEmrSignDataService {
   EmrSignData selectEmrSignDataById(Long id);

   List selectEmrSignDataList(EmrSignData emrSignData);

   List selectEmrSignDataListByMrFileIds(EmrSignDataVo emrSignDataVo);

   List selectEmrSignDataForFreeMoveList(EmrSignData emrSignData);

   int insertEmrSignData(EmrSignData emrSignData);

   int updateEmrSignData(EmrSignData emrSignData);

   int updateEmrSignDataForMove(EmrSignData emrSignData);

   int updateEmrSignDataByFileId(EmrSignData emrSignData);

   int deleteEmrSignDataByIds(Long[] ids);

   int deleteEmrSignDataById(Long id);

   void addList(List signDataList);

   void batchAddFreeMoveList(List signDataList);

   void addPatList(List signDataList);

   void updateCancelSign(EmrSignDataVo emrSignDataVo) throws Exception;

   void updateCancelSignForFreeMove(EmrSignDataVo emrSignDataVo) throws Exception;

   void updateCancelSignByFile(Long signFileId) throws Exception;

   void updateCancelSignByFreeMove(Long signFileId, String docCode) throws Exception;
}
