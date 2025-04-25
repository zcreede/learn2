package com.emr.project.emr.mapper;

import com.emr.project.emr.domain.EmrSearchHistory;
import java.util.List;

public interface EmrSearchHistoryMapper {
   EmrSearchHistory selectEmrSearchHistoryById(Long id);

   EmrSearchHistory selectEmrSearchHistoryLastInfo(String emplNumber);

   EmrSearchHistory selectEmrSearchHistoryFirstInfo(String emplNumber);

   List selectEmrSearchHistoryList(EmrSearchHistory emrSearchHistory);

   int insertEmrSearchHistory(EmrSearchHistory emrSearchHistory);

   int updateEmrSearchHistory(EmrSearchHistory emrSearchHistory);

   int deleteEmrSearchHistoryById(Long id);

   int deleteEmrSearchHistoryByIds(Long[] ids);
}
