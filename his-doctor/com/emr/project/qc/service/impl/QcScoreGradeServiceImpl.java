package com.emr.project.qc.service.impl;

import com.emr.project.qc.domain.QcScoreGrade;
import com.emr.project.qc.mapper.QcScoreGradeMapper;
import com.emr.project.qc.service.IQcScoreGradeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QcScoreGradeServiceImpl implements IQcScoreGradeService {
   @Autowired
   private QcScoreGradeMapper qcScoreGradeMapper;

   public QcScoreGrade selectQcScoreGradeById(Long id) {
      return this.qcScoreGradeMapper.selectQcScoreGradeById(id);
   }

   public List selectQcScoreGradeList(QcScoreGrade qcScoreGrade) {
      return this.qcScoreGradeMapper.selectQcScoreGradeList(qcScoreGrade);
   }

   public int insertQcScoreGrade(QcScoreGrade qcScoreGrade) {
      return this.qcScoreGradeMapper.insertQcScoreGrade(qcScoreGrade);
   }

   public void updateQcScoreGrade(List qcScoreGradeList) {
      for(QcScoreGrade qcScoreGrade : qcScoreGradeList) {
         this.qcScoreGradeMapper.updateQcScoreGrade(qcScoreGrade);
      }

   }

   public int deleteQcScoreGradeByIds(Long[] ids) {
      return this.qcScoreGradeMapper.deleteQcScoreGradeByIds(ids);
   }

   public int deleteQcScoreGradeById(Long id) {
      return this.qcScoreGradeMapper.deleteQcScoreGradeById(id);
   }
}
