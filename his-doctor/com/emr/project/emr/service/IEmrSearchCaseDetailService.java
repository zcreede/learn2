package com.emr.project.emr.service;

import com.emr.project.emr.domain.EmrSearchCaseDetail;
import java.util.List;

public interface IEmrSearchCaseDetailService {
   EmrSearchCaseDetail selectEmrSearchCaseDetailById(Long id);

   List selectEmrSearchCaseDetailList(EmrSearchCaseDetail emrSearchCaseDetail);

   int insertEmrSearchCaseDetail(EmrSearchCaseDetail emrSearchCaseDetail);

   void insertEmrSearchCaseDetailList(List detailList, Long caseId) throws Exception;

   int updateEmrSearchCaseDetail(EmrSearchCaseDetail emrSearchCaseDetail);

   int deleteEmrSearchCaseDetailByIds(Long[] ids);

   int deleteEmrSearchCaseDetailById(Long id);

   void deleteEmrSearchCaseDetailByCaseId(Long caseId) throws Exception;

   List selectEmrSearchCaseDetailByCaseId(Long caseId) throws Exception;
}
