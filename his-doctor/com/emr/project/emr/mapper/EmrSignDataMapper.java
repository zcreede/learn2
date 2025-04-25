package com.emr.project.emr.mapper;

import com.emr.project.emr.domain.EmrSignData;
import com.emr.project.emr.domain.vo.EmrSignDataVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmrSignDataMapper {
   EmrSignData selectEmrSignDataById(Long id);

   List selectEmrSignDataList(EmrSignData emrSignData);

   List selectEmrSignDataListByMrFileIds(EmrSignDataVo emrSignDataVo);

   List selectEmrSignDataForFreeMoveList(EmrSignData emrSignData);

   int insertEmrSignData(EmrSignData emrSignData);

   int updateEmrSignData(EmrSignData emrSignData);

   int updateEmrSignDataForMove(EmrSignData emrSignData);

   int updateEmrSignDataByFileId(EmrSignData emrSignData);

   int deleteEmrSignDataById(Long id);

   int deleteEmrSignDataByIds(Long[] ids);

   void insertList(List list);

   void batchAddFreeMoveList(List list);

   void insertPatList(List list);

   void updateCancelSign(EmrSignDataVo emrSignDataVo);

   void updateCancelSignForFreeMove(EmrSignDataVo emrSignDataVo);

   void updateCancelSignByFile(@Param("signFileId") Long signFileId);

   void updateCancelSignByFreeMove(@Param("signFileId") Long signFileId, @Param("docCode") String docCode);
}
