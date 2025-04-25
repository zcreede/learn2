package com.emr.project.emr.mapper;

import com.emr.project.emr.domain.EmrSearchSynonym;
import java.util.List;

public interface EmrSearchSynonymMapper {
   EmrSearchSynonym selectEmrSearchSynonymById(Long id);

   List selectEmrSearchSynonymList(EmrSearchSynonym emrSearchSynonym);

   int insertEmrSearchSynonym(EmrSearchSynonym emrSearchSynonym);

   int updateEmrSearchSynonym(EmrSearchSynonym emrSearchSynonym);

   int deleteEmrSearchSynonymById(Long id);

   int deleteEmrSearchSynonymByIds(Long[] ids);

   List selectListByEmpl(String emplNumber);
}
