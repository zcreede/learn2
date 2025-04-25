package com.emr.project.emr.mapper;

import com.emr.project.emr.domain.EmrSearchCase;
import com.emr.project.emr.domain.vo.EmrSearchCaseVo;
import java.util.List;

public interface EmrSearchCaseMapper {
   EmrSearchCase selectEmrSearchCaseById(Long id);

   List selectEmrSearchCaseList(EmrSearchCase emrSearchCase);

   int insertEmrSearchCase(EmrSearchCase emrSearchCase);

   void updateEmrSearchCase(EmrSearchCaseVo emrSearchCaseVo);

   int deleteEmrSearchCaseById(Long id);

   int deleteEmrSearchCaseByIds(Long[] ids);
}
