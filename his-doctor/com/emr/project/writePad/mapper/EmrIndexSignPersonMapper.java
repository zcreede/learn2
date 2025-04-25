package com.emr.project.writePad.mapper;

import com.emr.project.writePad.domain.EmrIndexSignPerson;
import java.util.List;

public interface EmrIndexSignPersonMapper {
   EmrIndexSignPerson selectEmrIndexSignPersonById(Long id);

   List selectEmrIndexSignPersonList(EmrIndexSignPerson emrIndexSignPerson);

   int insertEmrIndexSignPerson(EmrIndexSignPerson emrIndexSignPerson);

   int updateEmrIndexSignPerson(EmrIndexSignPerson emrIndexSignPerson);

   int updateEmrIndexSignPersonBr(EmrIndexSignPerson emrIndexSignPerson);

   int deleteEmrIndexSignPersonById(Long id);

   int deleteEmrIndexSignPersonByIds(Long[] ids);
}
