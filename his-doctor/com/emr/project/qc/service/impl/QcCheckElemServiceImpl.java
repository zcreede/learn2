package com.emr.project.qc.service.impl;

import com.emr.project.qc.domain.QcCheckElem;
import com.emr.project.qc.mapper.QcCheckElemMapper;
import com.emr.project.qc.service.IQcCheckElemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QcCheckElemServiceImpl implements IQcCheckElemService {
   @Autowired
   private QcCheckElemMapper qcCheckElemMapper;

   public List selectQcCheckElemById(Long checkId) {
      return this.qcCheckElemMapper.selectQcCheckElemById(checkId);
   }

   public List selectQcCheckElemByIds(List checkIdList) throws Exception {
      return this.qcCheckElemMapper.selectQcCheckElemByIds(checkIdList);
   }

   public List selectVQcCheckElemList(QcCheckElem qcCheckElem) throws Exception {
      return this.qcCheckElemMapper.selectVQcCheckElemList(qcCheckElem);
   }

   public List selectQcCheckElemByRuleType(Integer ruleType) throws Exception {
      return this.qcCheckElemMapper.selectQcCheckElemByRuleType(ruleType);
   }

   public List selectQcCheckElemList(QcCheckElem qcCheckElem) {
      return this.qcCheckElemMapper.selectQcCheckElemList(qcCheckElem);
   }

   public void insertQcCheckElem(List qcCheckElemList) throws Exception {
      this.qcCheckElemMapper.insertQcCheckElem(qcCheckElemList);
   }

   public void updateQcCheckElem(List qcCheckElemList) throws Exception {
      this.qcCheckElemMapper.updateQcCheckElem(qcCheckElemList);
   }

   public int deleteQcCheckElemByIds(Long[] checkIds) {
      return this.qcCheckElemMapper.deleteQcCheckElemByIds(checkIds);
   }

   public void deleteQcCheckElemById(Long checkId) throws Exception {
      this.qcCheckElemMapper.deleteQcCheckElemById(checkId);
   }
}
