package com.emr.project.qc.mapper;

import com.emr.project.qc.domain.QcGendConDict;
import java.util.List;

public interface QcGendConDictMapper {
   QcGendConDict selectQcGendConDictById(Long id);

   List selectQcGendConDictList(QcGendConDict qcGendConDict);

   void insertQcGendConDict(List qcGendConDictList);

   int updateQcGendConDict(QcGendConDict qcGendConDict);

   int deleteQcGendConDictById(Long id);

   void deleteQcGendConDict();

   int deleteQcGendConDictByIds(Long[] ids);
}
