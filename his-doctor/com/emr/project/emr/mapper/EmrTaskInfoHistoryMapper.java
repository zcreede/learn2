package com.emr.project.emr.mapper;

import com.emr.project.emr.domain.EmrTaskInfo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmrTaskInfoHistoryMapper {
   EmrTaskInfo selectEmrTaskInfoById(Long id);

   List selectEmrTaskInfoByIds(@Param("ids") List ids);

   List selectEmrTaskInfoList(EmrTaskInfo emrTaskInfo);

   int insertEmrTaskInfo(EmrTaskInfo emrTaskInfo);

   int batchInsert(Long[] ids);

   int updateEmrTaskInfo(EmrTaskInfo emrTaskInfo);

   int updateEmrTaskInfoForRecover(EmrTaskInfo emrTaskInfo);

   int deleteEmrTaskInfoById(Long id);

   int deleteEmrTaskInfoByIds(Long[] ids);

   void deleteEmrTaskInfoByMrFileId(Long mrFileId);
}
