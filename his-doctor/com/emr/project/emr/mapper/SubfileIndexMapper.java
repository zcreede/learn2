package com.emr.project.emr.mapper;

import com.emr.project.emr.domain.SubfileIndex;
import com.emr.project.emr.domain.vo.IndexVo;
import com.emr.project.emr.domain.vo.SubfileIndexVo;
import com.emr.project.qc.domain.vo.PatEventVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SubfileIndexMapper {
   SubfileIndex selectSubfileIndexById(Long id);

   SubfileIndexVo selectSubfileIndexVoById(Long id);

   List selectSubfileIndexList(SubfileIndex subfileIndex);

   int insertSubfileIndex(SubfileIndex subfileIndex);

   int updateSubfileIndex(SubfileIndex subfileIndex);

   void updateSubfileIndexSecLevel(SubfileIndexVo subfileIndexVo);

   int deleteSubfileIndexById(Long id);

   int updateIndexDelFlag(SubfileIndex subfileIndex);

   int deleteSubfileIndexByIds(Long[] ids);

   List selectSubfileIndexByMainId(Long mainId);

   List selectSubfileIndexVoByMainId(Long mainId);

   List selectListByMainIdOrderRecoDate(SubfileIndex subfileIndex);

   List selectListByMainIdAfterRecoDate(SubfileIndex subfileIndex);

   void updateSubFileLockState(SubfileIndex subfileIndex);

   List selectPatIndexList(PatEventVo patEventVo);

   void updateLastFinishTimeList(List list);

   void updateEmrOrderNo(String orderNo);

   void updateOrderNoEmptyByIdList(List mrFileIdList);

   void updateEmrOrderNoByIdList(String orderNo, List mrFileIdList);

   void updateOrderNoByOrderNo(@Param("oldOrderNo") String oldOrderNo, @Param("newOrderNo") String newOrderNo);

   List selectSubFileByPat(IndexVo indexVo);

   List selectSubFileDelList(SubfileIndexVo subfileIndex);

   List selectSubFileByMrType(String patientId, String mrType) throws Exception;

   SubfileIndex selectIndexByTmplId(@Param("patientId") String patientId, @Param("tempId") Long tempId);

   List selectOrderNoByIdList(@Param("list") List idList);
}
