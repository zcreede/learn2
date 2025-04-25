package com.emr.project.qc.mapper;

import com.emr.project.qc.domain.vo.QcScoreScheVo;
import java.util.List;

public interface QcScoreScheMapper {
   QcScoreScheVo selectQcScoreScheById(Long scheId);

   List selectQcScoreScheList(QcScoreScheVo qcScoreScheVo);

   void insertQcScoreSche(QcScoreScheVo qcScoreScheVo);

   void updateQcScoreSche(QcScoreScheVo qcScoreScheVo);

   int deleteQcScoreScheById(Long scheId);

   int deleteQcScoreScheByIds(Long[] scheIds);

   void changeDelFlag(Long scheId);
}
