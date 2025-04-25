package com.emr.project.qc.service;

import com.emr.project.qc.domain.QcRuleIterEmr;
import com.emr.project.qc.domain.vo.QcRuleIterEmrVo;
import java.util.List;
import java.util.Map;

public interface IQcRuleIterEmrService {
   QcRuleIterEmr selectQcRuleIterEmrById(Long id);

   List selectQcRuleIterEmrList(QcRuleIterEmrVo qcRuleIterEmrVo) throws Exception;

   void insertQcRuleIterEmr(QcRuleIterEmr qcRuleIterEmr) throws Exception;

   void updateQcRuleIterEmr(QcRuleIterEmr qcRuleIterEmr) throws Exception;

   int deleteQcRuleIterEmrByIds(Long[] ids);

   void deleteQcRuleIterEmrById(Long id) throws Exception;

   List getIterTypeCodeByEmrTypeCode(String emrTypeCode) throws Exception;

   List getIterTypeCodeByEmrTypeOrIter(String emrTypeCode) throws Exception;

   Map getIterTypeCodeByEmrTypeCodes(List emrTypeCodeList) throws Exception;

   List selectListByEmrTypeList(List emrTypeCodeList) throws Exception;
}
