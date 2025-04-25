package com.emr.project.docOrder.mapper;

import com.emr.project.docOrder.domain.TdPaApplyForm;
import com.emr.project.docOrder.domain.vo.TdPaApplyFormVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TdPaApplyFormMapper {
   TdPaApplyFormVo selectTdPaApplyFormById(String applyFormNo);

   List selectTdPaApplyFormList(TdPaApplyForm tdPaApplyForm);

   List selectTdPaApplyFormListAll(TdPaApplyForm tdPaApplyForm);

   void insertTdPaApplyForm(TdPaApplyForm tdPaApplyForm);

   void insertTdPaApplyFormList(List tdPaApplyFormList);

   void insertList(List tdPaApplyFormList);

   int updateTdPaApplyForm(TdPaApplyForm tdPaApplyForm);

   int deleteTdPaApplyFormById(String applyFormNo);

   int deleteTdPaApplyFormByIds(String[] applyFormNos);

   TdPaApplyFormVo selectNewestCheck(String patientId);

   List selectListByOrderNos(List orderNoList);

   void updateStatusByApplyFormNos(List applyFormNoList, @Param("applyFormStatus") String applyFormStatus);

   List selectList(TdPaApplyFormVo tdPaApplyFormVo);

   String selectApplyFormStatus(@Param("orderNo") String orderNo, @Param("orderClassCode") String orderClassCode);

   List selectApplyFormListByPatientId(@Param("patientId") String patientId);

   List testExamList(TdPaApplyFormVo tdPaApplyFormVo);

   List testTmInfo(@Param("applyFormNo") String applyFormNo);
}
