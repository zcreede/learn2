package com.emr.project.qc.mapper;

import com.emr.project.qc.domain.QcScoreGrade;
import java.util.List;

public interface QcScoreGradeMapper {
   QcScoreGrade selectQcScoreGradeById(Long id);

   List selectQcScoreGradeList(QcScoreGrade qcScoreGrade);

   int insertQcScoreGrade(QcScoreGrade qcScoreGrade);

   int updateQcScoreGrade(QcScoreGrade qcScoreGrade);

   int deleteQcScoreGradeById(Long id);

   int deleteQcScoreGradeByIds(Long[] ids);
}
