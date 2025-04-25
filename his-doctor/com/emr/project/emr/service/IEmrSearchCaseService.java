package com.emr.project.emr.service;

import com.emr.project.emr.domain.EmrSearchCase;
import com.emr.project.emr.domain.vo.EmrSearchCaseVo;
import java.util.List;

public interface IEmrSearchCaseService {
   EmrSearchCase selectEmrSearchCaseById(Long id);

   List selectEmrSearchCaseList(EmrSearchCase emrSearchCase) throws Exception;

   void insertEmrSearchCase(EmrSearchCaseVo emrSearchCaseVo) throws Exception;

   void updateEmrSearchCase(EmrSearchCaseVo emrSearchCaseVo) throws Exception;

   int deleteEmrSearchCaseByIds(Long[] ids);

   void deleteEmrSearchCaseById(Long id) throws Exception;
}
