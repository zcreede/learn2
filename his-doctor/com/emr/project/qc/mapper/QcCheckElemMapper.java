package com.emr.project.qc.mapper;

import com.emr.project.qc.domain.QcCheckElem;
import java.util.List;

public interface QcCheckElemMapper {
   List selectQcCheckElemById(Long checkId);

   List selectQcCheckElemByIds(List checkIdList);

   List selectQcCheckElemByRuleType(Integer ruleType);

   List selectVQcCheckElemList(QcCheckElem qcCheckElem);

   List selectQcCheckElemList(QcCheckElem qcCheckElem);

   void insertQcCheckElem(List qcCheckElemList);

   void updateQcCheckElem(List qcCheckElemList);

   void deleteQcCheckElemById(Long checkId);

   int deleteQcCheckElemByIds(Long[] checkIds);
}
