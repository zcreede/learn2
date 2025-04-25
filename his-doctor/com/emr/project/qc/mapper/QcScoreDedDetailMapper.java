package com.emr.project.qc.mapper;

import com.emr.project.qc.domain.QcScoreDedDetail;
import com.emr.project.qc.domain.vo.QcScoreDedDetailVo;
import java.util.List;

public interface QcScoreDedDetailMapper {
   QcScoreDedDetail selectQcScoreDedDetailById(Long id);

   List selectQcScoreDedDetailList(QcScoreDedDetailVo qcScoreDedDetailVo);

   List selectUnScheDedList(QcScoreDedDetail qcScoreDedDetail);

   List selectScheDedList(QcScoreDedDetailVo qcScoreDedDetail);

   List selectDedDetailList(QcScoreDedDetail qcScoreDedDetail);

   int insertQcScoreDedDetail(QcScoreDedDetail qcScoreDedDetail);

   int updateQcScoreDedDetail(QcScoreDedDetail qcScoreDedDetail);

   int deleteQcScoreDedDetailById(Long id);

   int deleteQcScoreDedDetailByIds(Long[] ids);

   void changeDelFlag(Long id);

   List selectDedListGroupByItem(QcScoreDedDetailVo qcScoreDedDetailVo);
}
