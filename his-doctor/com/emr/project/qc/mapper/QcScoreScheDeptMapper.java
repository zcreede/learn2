package com.emr.project.qc.mapper;

import com.emr.project.qc.domain.QcScoreScheDept;
import com.emr.project.qc.domain.vo.QcScoreScheVo;
import java.util.List;

public interface QcScoreScheDeptMapper {
   QcScoreScheDept selectQcScoreScheDeptById(Long id);

   List selectQcScoreScheDeptList(QcScoreScheDept qcScoreScheDept);

   List selectQcScoreScheDeptListByScheId(Long scheId);

   List selectQcScoreScheDeptAllList();

   List selectUnDeptList(QcScoreScheDept qcScoreScheDept);

   int insertQcScoreScheDept(QcScoreScheDept qcScoreScheDept);

   void insertQcScoreScheDeptList(List qcScoreScheDeptList);

   int updateQcScoreScheDept(QcScoreScheDept qcScoreScheDept);

   int deleteQcScoreScheDeptById(Long id);

   int deleteQcScoreScheDeptByIds(Long[] ids);

   void deleteQcScoreScheDeptByScheId(Long scheId);

   List selectScheChooseDept(QcScoreScheVo qcScoreScheVo);
}
