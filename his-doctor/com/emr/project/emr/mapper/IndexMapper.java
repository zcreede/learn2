package com.emr.project.emr.mapper;

import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.vo.IndexMzVo;
import com.emr.project.emr.domain.vo.IndexVo;
import com.emr.project.qc.domain.vo.PatEventVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IndexMapper {
   Index selectIndexById(Long id);

   IndexVo selectIndexVoById(Long id);

   Index selectIndexInfoById(Long id);

   Index selectIndexByPatientId(String patientId);

   List selectDelIndexPageList(IndexVo indexVo);

   List selectTodoList(IndexVo indexVo);

   List selectTodoListFromTask(IndexVo indexVo);

   List selectYetEmrList(IndexVo indexVo);

   List selectTodoSubList(IndexVo indexVo);

   List selectIndexList(Index index);

   int insertIndex(Index index);

   int updateIndex(Index index);

   void updateIndexSecLevel(IndexVo indexVo);

   int updateIndexById(Long id);

   int updateIndexByIds(Long[] ids);

   List selectSealupIndexList(IndexVo indexVo);

   List selectEmrUnFinishList(String patientId);

   List selectEmrUnUpdateList(String patientId);

   void updateIndexLockState(Index index);

   void updateIndexDelFlag(Index index);

   List selectQcRulesList(List ruleTypeList, String mrType);

   List selectUnSignEmrList(String docCode, String patientId);

   List selectPatIndexList(PatEventVo patEventVo);

   void updatePrintInfo(@Param("id") Long id);

   List selectOvertimeWriteList(String patientId);

   void updateLastFinishTimeList(List list);

   List selectOpeIndexByEmrType(String patientId, List emrTypeCodeList);

   List selectOpeIndexByOrderNo(String applyFormNo);

   void updateEmrOrderNo(String orderNo);

   void updateEmrOrderNoByIdList(String orderNo, List mrFileIdList);

   void updateOrderNoEmptyByIdList(List mrFileIdList);

   void updateOrderNoByOrderNo(@Param("oldOrderNo") String oldOrderNo, @Param("newOrderNo") String newOrderNo);

   List selectEmrListByPat(IndexVo indexVo);

   List selectEmrListByVisitNo(IndexVo indexVo);

   List selectSubEmrListByPat(String patientId);

   List selectEmrPdfPath();

   List selectUnSavePdfEmr();

   List selectUnConsultation(@Param("patientId") String patientId);

   List selectAllIndexList(IndexVo indexVo);

   List selectIndexListByTmplList(@Param("list") List elemList, @Param("patientId") String patientId);

   Index selectIndexByTmplId(@Param("patientId") String patientId, @Param("tempId") Long tempId);

   List selectOrderNoByIdList(@Param("list") List idList);

   IndexMzVo selectIndexMzVoList(@Param("patientId") String patientId);

   int deleteByIds(@Param("list") List idList);
}
