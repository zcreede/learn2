package com.emr.project.emr.service;

import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.SubfileIndex;
import com.emr.project.emr.domain.vo.IndexVo;
import com.emr.project.emr.domain.vo.SubfileIndexVo;
import com.emr.project.pat.domain.vo.DeptBEDateVo;
import com.emr.project.qc.domain.vo.PatEventVo;
import com.emr.project.tmpl.domain.TmplIndex;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ISubfileIndexService {
   SubfileIndex selectSubfileIndexById(Long id);

   SubfileIndexVo selectSubfileIndexVoById(Long id);

   List selectSubfileIndexList(SubfileIndex subfileIndex);

   int insertSubfileIndex(SubfileIndex subfileIndex);

   int updateSubfileIndex(SubfileIndex subfileIndex) throws Exception;

   void updateSubfileIndexSecLevel(SubfileIndexVo subfileIndexVo) throws Exception;

   void updateSubfileIndexMrState(Long id, String mrState, Date updateDate, Date recoDate);

   int deleteSubfileIndexByIds(Long[] ids);

   int deleteSubfileIndexById(Long id);

   List selectSubfileIndexByMainId(Long mainId);

   List selectSubfileIndexVoByMainId(Long mainId);

   SubfileIndexVo selectIsAllowInsert(SubfileIndex subfileIndex) throws Exception;

   Map insertFile(Index index, SubfileIndex subfileIndex, TmplIndex tmplIndex, String testExamResultId, String babyId) throws Exception;

   DeptBEDateVo getDeptBEDateVoCurn(String patientId, SubfileIndex subfileIndex) throws Exception;

   List getIndexHFInfoPageVoList(String xmlStr, String patientId, DeptBEDateVo deptBEDateVoCurn) throws Exception;

   void setSubFileIndexRecDate(Long tempId, List elemDateList, Date recoDate, List idList, List valueList) throws Exception;

   void updateSubFileLockState(SubfileIndex subfileIndex) throws Exception;

   List selectPatIndexList(PatEventVo patEventVo) throws Exception;

   void updateLastFinishTimeList(List list) throws Exception;

   void updateEmrOrderNo(String orderNo) throws Exception;

   void updateOrderNoEmptyByIdList(List mrFileIdList) throws Exception;

   void updateEmrOrderNoByIdList(String orderNo, List mrFileIdList) throws Exception;

   void updateOrderNoByOrderNo(String oldOrderNo, String newOrderNo) throws Exception;

   List selectSubFileByPat(IndexVo indexVo) throws Exception;

   List selectSubFileDelList(SubfileIndexVo subfileIndex) throws Exception;

   Map selectSubFileRegain(SubfileIndexVo subfileIndex) throws Exception;

   List selectSubFileByMrType(String patientId, String mrType) throws Exception;

   SubfileIndexVo getSubFileInfo(String subFileXmlStr) throws Exception;

   SubfileIndex selectIndexByTmplId(String patientId, Long tempId) throws Exception;

   List selectOrderNoByIdList(List idList);
}
