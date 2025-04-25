package com.emr.project.qc.service;

import com.emr.project.qc.domain.vo.QcScoreScheVo;
import java.util.List;

public interface IQcScoreScheService {
   QcScoreScheVo selectQcScoreScheById(Long scheId) throws Exception;

   List selectQcScoreScheList(QcScoreScheVo qcScoreScheVo) throws Exception;

   void insertQcScoreSche(QcScoreScheVo qcScoreScheVo) throws Exception;

   void updateQcScoreSche(QcScoreScheVo qcScoreScheVo) throws Exception;

   int deleteQcScoreScheByIds(Long[] scheIds);

   void deleteQcScoreScheById(Long scheId) throws Exception;

   QcScoreScheVo selectDedEditSearch(Long scheId) throws Exception;

   Boolean selectIsChooseAllDept(QcScoreScheVo qcScoreScheVo) throws Exception;
}
