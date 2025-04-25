package com.emr.project.emr.mapper;

import com.emr.project.emr.domain.EmrSearchCaseDetail;
import java.util.List;

public interface EmrSearchCaseDetailMapper {
   EmrSearchCaseDetail selectEmrSearchCaseDetailById(Long id);

   List selectEmrSearchCaseDetailList(EmrSearchCaseDetail emrSearchCaseDetail);

   List selectEmrSearchCaseDetailByCaseId(Long caseId);

   int insertEmrSearchCaseDetail(EmrSearchCaseDetail emrSearchCaseDetail);

   int updateEmrSearchCaseDetail(EmrSearchCaseDetail emrSearchCaseDetail);

   int deleteEmrSearchCaseDetailById(Long id);

   void deleteEmrSearchCaseDetailByCaseId(Long caseId);

   int deleteEmrSearchCaseDetailByIds(Long[] ids);

   void insertEmrSearchCaseDetailList(List detailList);
}
