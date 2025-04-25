package com.emr.project.qc.service;

import com.emr.project.qc.domain.QcCheckElem;
import java.util.List;

public interface IQcCheckElemService {
   List selectQcCheckElemById(Long checkId);

   List selectQcCheckElemByIds(List checkIdList) throws Exception;

   List selectVQcCheckElemList(QcCheckElem qcCheckElem) throws Exception;

   List selectQcCheckElemByRuleType(Integer ruleType) throws Exception;

   List selectQcCheckElemList(QcCheckElem qcCheckElem);

   void insertQcCheckElem(List qcCheckElemList) throws Exception;

   void updateQcCheckElem(List qcCheckElemList) throws Exception;

   int deleteQcCheckElemByIds(Long[] checkIds);

   void deleteQcCheckElemById(Long checkId) throws Exception;
}
