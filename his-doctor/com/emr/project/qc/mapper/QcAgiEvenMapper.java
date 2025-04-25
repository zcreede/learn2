package com.emr.project.qc.mapper;

import com.emr.project.qc.domain.QcAgiEven;
import java.util.List;

public interface QcAgiEvenMapper {
   QcAgiEven selectQcAgiEvenById(Long id);

   List selectQcAgiEvenList(QcAgiEven qcAgiEven);

   List selectListByEvenCodes(List evenCodeList);

   int insertQcAgiEven(QcAgiEven qcAgiEven);

   int updateQcAgiEven(QcAgiEven qcAgiEven);

   int deleteQcAgiEvenById(Long id);

   int deleteQcAgiEvenByIds(Long[] ids);
}
