package com.emr.project.emr.service;

import com.emr.project.emr.domain.EmrSearchHistory;
import com.emr.project.esSearch.domain.vo.EmrFileSearchVo;
import java.util.List;

public interface IEmrSearchHistoryService {
   EmrSearchHistory selectEmrSearchHistoryById(Long id);

   List selectEmrSearchHistoryList(EmrSearchHistory emrSearchHistory) throws Exception;

   void insertEmrSearchHistory(EmrSearchHistory emrSearchHistory) throws Exception;

   void insertEmrSearchHistoryList(List emrSearchHistoryDetailList) throws Exception;

   int updateEmrSearchHistory(EmrSearchHistory emrSearchHistory);

   int deleteEmrSearchHistoryByIds(Long[] ids);

   int deleteEmrSearchHistoryById(Long id);

   void saveEmrSearchHistory(EmrFileSearchVo emrFileSearchVo) throws Exception;
}
