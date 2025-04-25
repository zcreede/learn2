package com.emr.project.emr.service;

import com.emr.project.emr.domain.EmrSearchSynonym;
import java.util.List;

public interface IEmrSearchSynonymService {
   EmrSearchSynonym selectEmrSearchSynonymById(Long id);

   List selectEmrSearchSynonymList(EmrSearchSynonym emrSearchSynonym) throws Exception;

   int insertEmrSearchSynonym(EmrSearchSynonym emrSearchSynonym) throws Exception;

   int updateEmrSearchSynonym(EmrSearchSynonym emrSearchSynonym);

   int deleteEmrSearchSynonymByIds(Long[] ids);

   int deleteEmrSearchSynonymById(Long id) throws Exception;

   List selectListByEmpl(String emplNumber);
}
