package com.emr.project.emr.mapper;

import com.emr.project.emr.domain.EmrSearchHistoryDetail;
import java.util.List;

public interface EmrSearchHistoryDetailMapper {
   EmrSearchHistoryDetail selectEmrSearchHistoryDetailById(Long id);

   List selectEmrSearchHistoryDetailList(EmrSearchHistoryDetail emrSearchHistoryDetail);

   int insertEmrSearchHistoryDetail(EmrSearchHistoryDetail emrSearchHistoryDetail);

   int updateEmrSearchHistoryDetail(EmrSearchHistoryDetail emrSearchHistoryDetail);

   int deleteEmrSearchHistoryDetailById(Long id);

   int deleteEmrSearchHistoryDetailByIds(Long[] ids);

   void insertEmrSearchHistoryDetailList(List detailList);

   List selectEmrSearchHistoryDetailByHistoryId(Long historyId);

   void deleteEmrSearchHistoryDetailByHisId(Long historyId);
}
