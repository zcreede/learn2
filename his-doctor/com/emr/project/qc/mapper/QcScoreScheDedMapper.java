package com.emr.project.qc.mapper;

import com.emr.project.qc.domain.QcScoreScheDed;
import com.emr.project.qc.domain.vo.QcScoreScheDedVo;
import java.util.List;

public interface QcScoreScheDedMapper {
   QcScoreScheDed selectQcScoreScheDedById(Long id);

   List selectScheDedListByItem(Long itemId);

   List selectScheDedListByDedId(Long dedId);

   List selectQcScoreScheDedList(QcScoreScheDed qcScoreScheDed);

   List selectScheDedList(QcScoreScheDed qcScoreScheDed);

   int insertQcScoreScheDed(QcScoreScheDed qcScoreScheDed);

   void insertQcScoreScheDedList(List qcScoreScheDedList);

   int updateQcScoreScheDed(QcScoreScheDed qcScoreScheDed);

   int deleteQcScoreScheDedById(Long id);

   void deleteQcScoreScheDedByScheId(Long scheId, Long itemId);

   void deleteScheDedByScheId(Long scheId);

   int deleteQcScoreScheDedByIds(Long[] ids);

   List selectQcScoreItemList(QcScoreScheDedVo qcScoreScheDedVo);

   List selectScheItemAndDed(Long scheId);

   List selectScheDedGroupByItem(Long scheId);

   List selectScheDedDetailBysche(QcScoreScheDedVo qcScoreScheDedVo);
}
