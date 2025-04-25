package com.emr.project.qc.service;

import com.emr.project.qc.domain.QcScoreDedDetail;
import com.emr.project.qc.domain.vo.QcScoreDedDetailVo;
import java.util.List;

public interface IQcScoreDedDetailService {
   QcScoreDedDetail selectQcScoreDedDetailById(Long id);

   List selectQcScoreDedDetailList(QcScoreDedDetailVo qcScoreDedDetailVo) throws Exception;

   List selectUnScheDedList(QcScoreDedDetail qcScoreDedDetail);

   List selectScheDedList(QcScoreDedDetailVo qcScoreDedDetail);

   List selectDedDetailList(QcScoreDedDetail qcScoreDedDetail) throws Exception;

   int insertQcScoreDedDetail(QcScoreDedDetailVo qcScoreDedDetail) throws Exception;

   int updateQcScoreDedDetail(QcScoreDedDetailVo qcScoreDedDetail) throws Exception;

   int deleteQcScoreDedDetailByIds(Long[] ids);

   int deleteQcScoreDedDetailById(Long id);

   void changeDelFlag(Long id) throws Exception;
}
