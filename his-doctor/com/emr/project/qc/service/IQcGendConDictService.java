package com.emr.project.qc.service;

import com.emr.project.qc.domain.QcGendConDict;
import java.util.List;

public interface IQcGendConDictService {
   QcGendConDict selectQcGendConDictById(Long id);

   List selectQcGendConDictList(QcGendConDict qcGendConDict) throws Exception;

   void insertQcGendConDict(List qcGendConDictList) throws Exception;

   int updateQcGendConDict(QcGendConDict qcGendConDict);

   int deleteQcGendConDictByIds(Long[] ids);

   int deleteQcGendConDictById(Long id);
}
