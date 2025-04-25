package com.emr.project.docOrder.mapper;

import com.emr.project.docOrder.domain.AllergyRecord;
import java.util.List;

public interface AllergyRecordMapper {
   AllergyRecord selectOne(String admissionNo);

   List getList(AllergyRecord record);

   int delByZyh(String admissionNo);

   int insert(AllergyRecord record);

   int insertSelective(AllergyRecord record);

   int updateAllergyRecord(AllergyRecord record);

   int insertList(List allergyRecord);
}
