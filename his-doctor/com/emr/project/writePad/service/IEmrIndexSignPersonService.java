package com.emr.project.writePad.service;

import com.emr.project.writePad.domain.EmrIndexSignPerson;
import java.util.List;

public interface IEmrIndexSignPersonService {
   EmrIndexSignPerson selectEmrIndexSignPersonById(Long id);

   List selectEmrIndexSignPersonList(EmrIndexSignPerson emrIndexSignPerson);

   int insertEmrIndexSignPerson(EmrIndexSignPerson emrIndexSignPerson);

   EmrIndexSignPerson updateEmrIndexSignPerson(EmrIndexSignPerson emrIndexSignPerson) throws Exception;

   int deleteEmrIndexSignPersonByIds(Long[] ids);

   int deleteEmrIndexSignPersonById(Long id);
}
