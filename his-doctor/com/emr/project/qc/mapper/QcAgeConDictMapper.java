package com.emr.project.qc.mapper;

import com.emr.project.qc.domain.QcAgeConDict;
import java.util.List;

public interface QcAgeConDictMapper {
   QcAgeConDict selectQcAgeConDictById(Long id);

   List selectQcAgeConDictList(QcAgeConDict qcAgeConDict);

   void insertQcAgeConDict(List qcAgeConDictList);

   void updateQcAgeConDict(QcAgeConDict qcAgeConDict);

   int deleteQcAgeConDictById(Long id);

   int deleteQcAgeConDictByIds(Long[] ids);

   void deleteQcAgeConDict();
}
