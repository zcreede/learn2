package com.emr.project.qc.service;

import com.emr.project.qc.domain.QcAgiEven;
import java.util.List;

public interface IQcAgiEvenService {
   QcAgiEven selectQcAgiEvenById(Long id);

   List selectQcAgiEvenList(QcAgiEven qcAgiEven);

   List selectListByEvenCodes(List evenCodeList) throws Exception;

   int insertQcAgiEven(QcAgiEven qcAgiEven);

   int updateQcAgiEven(QcAgiEven qcAgiEven);

   int deleteQcAgiEvenByIds(Long[] ids);

   int deleteQcAgiEvenById(Long id);
}
