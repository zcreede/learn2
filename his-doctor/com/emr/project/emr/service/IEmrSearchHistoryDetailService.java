package com.emr.project.emr.service;

import com.emr.project.emr.domain.EmrSearchHistoryDetail;
import java.util.List;

public interface IEmrSearchHistoryDetailService {
   EmrSearchHistoryDetail selectEmrSearchHistoryDetailById(Long id);

   List selectEmrSearchHistoryDetailList(EmrSearchHistoryDetail emrSearchHistoryDetail);

   int insertEmrSearchHistoryDetail(EmrSearchHistoryDetail emrSearchHistoryDetail);

   void insertEmrSearchHistoryDetailList(List emrSearchHistoryDetailList, Long historyId);

   int updateEmrSearchHistoryDetail(EmrSearchHistoryDetail emrSearchHistoryDetail);

   int deleteEmrSearchHistoryDetailByIds(Long[] ids);

   void deleteEmrSearchHistoryDetailByHisId(Long historyId) throws Exception;

   int deleteEmrSearchHistoryDetailById(Long id);

   List selectEmrSearchHistoryDetailByHistoryId(Long historyId) throws Exception;
}
