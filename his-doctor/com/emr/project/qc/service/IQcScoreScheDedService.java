package com.emr.project.qc.service;

import com.emr.project.qc.domain.QcScoreScheDed;
import com.emr.project.qc.domain.vo.EmrQcFlowScoreVo;
import com.emr.project.qc.domain.vo.QcScoreScheDedVo;
import java.util.List;

public interface IQcScoreScheDedService {
   QcScoreScheDed selectQcScoreScheDedById(Long id);

   List selectScheDedListByItem(Long itemId) throws Exception;

   List selectScheDedListByDedId(Long dedId) throws Exception;

   List selectQcScoreScheDedList(QcScoreScheDed qcScoreScheDed) throws Exception;

   EmrQcFlowScoreVo selectScheDedList(QcScoreScheDed qcScoreScheDed) throws Exception;

   void insertQcScoreScheDed(QcScoreScheDedVo qcScoreScheDedVo) throws Exception;

   int updateQcScoreScheDed(QcScoreScheDed qcScoreScheDed);

   int deleteQcScoreScheDedByIds(Long[] ids);

   int deleteQcScoreScheDedById(Long id) throws Exception;

   void deleteQcScoreScheDedByScheId(Long scheId, Long itemId) throws Exception;

   void deleteScheDedByScheId(Long scheId) throws Exception;

   List selectQcScoreItemList(QcScoreScheDedVo qcScoreScheDedVo) throws Exception;

   List selectScheItemAndDed(Long scheId) throws Exception;

   List selectScheDedGroupByItem(Long scheId) throws Exception;

   List selectScheDedDetailBysche(QcScoreScheDedVo qcScoreScheDedVo) throws Exception;
}
