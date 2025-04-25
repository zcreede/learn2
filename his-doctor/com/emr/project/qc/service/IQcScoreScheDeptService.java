package com.emr.project.qc.service;

import com.emr.project.qc.domain.QcScoreScheDept;
import com.emr.project.qc.domain.vo.QcScoreScheDeptVo;
import com.emr.project.qc.domain.vo.QcScoreScheVo;
import java.util.List;

public interface IQcScoreScheDeptService {
   QcScoreScheDept selectQcScoreScheDeptById(Long id);

   List selectQcScoreScheDeptList(QcScoreScheDept qcScoreScheDept);

   List selectQcScoreScheDeptListByScheId(Long scheId) throws Exception;

   List selectQcScoreScheDeptListScheId(Long scheId) throws Exception;

   List selectQcScoreScheDeptAllList() throws Exception;

   List selectUnDeptList(QcScoreScheDept qcScoreScheDept) throws Exception;

   int insertQcScoreScheDept(QcScoreScheDept qcScoreScheDept);

   void insertQcScoreScheDeptList(QcScoreScheDeptVo qcScoreScheDeptVo) throws Exception;

   int updateQcScoreScheDept(QcScoreScheDept qcScoreScheDept);

   int deleteQcScoreScheDeptByIds(Long[] ids);

   int deleteQcScoreScheDeptById(Long id);

   void deleteQcScoreScheDeptByScheId(Long scheId) throws Exception;

   List selectScheChooseDept(QcScoreScheVo qcScoreScheVo) throws Exception;
}
