package com.emr.project.qc.service;

import com.emr.project.qc.domain.QcScoreGrade;
import java.util.List;

public interface IQcScoreGradeService {
   QcScoreGrade selectQcScoreGradeById(Long id);

   List selectQcScoreGradeList(QcScoreGrade qcScoreGrade);

   int insertQcScoreGrade(QcScoreGrade qcScoreGrade);

   void updateQcScoreGrade(List qcScoreGradeList);

   int deleteQcScoreGradeByIds(Long[] ids);

   int deleteQcScoreGradeById(Long id);
}
