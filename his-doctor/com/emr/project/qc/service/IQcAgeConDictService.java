package com.emr.project.qc.service;

import com.emr.project.qc.domain.QcAgeConDict;
import java.util.List;

public interface IQcAgeConDictService {
   QcAgeConDict selectQcAgeConDictById(Long id);

   List selectQcAgeConDictList(QcAgeConDict qcAgeConDict) throws Exception;

   void insertQcAgeConDict(List qcAgeConDictList) throws Exception;

   void updateQcAgeConDict(QcAgeConDict qcAgeConDict) throws Exception;

   int deleteQcAgeConDictByIds(Long[] ids);

   int deleteQcAgeConDictById(Long id);
}
