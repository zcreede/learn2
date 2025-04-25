package com.emr.project.qc.mapper;

import com.emr.project.docOrder.domain.vo.PhysignDayVo;
import com.emr.project.qc.domain.DzblSqgd;
import com.emr.project.qc.domain.EmrQcFlow;
import com.emr.project.qc.domain.vo.EmrQcFlowStatisticVo;
import com.emr.project.qc.domain.vo.EmrQcFlowVo;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmrQcFlowMapper {
   EmrQcFlow selectEmrQcFlowByPatientId(String orgCd, String patientId);

   EmrQcFlow selectEmrQcFlowById(Long id);

   List selectEmrQcFlowList(EmrQcFlow emrQcFlow);

   List selectEmrQcFlowVoListOrderByOutTime(EmrQcFlowVo emrQcFlowVo);

   List selectEmrQcFlowVoListOrderByApplyFileTime(EmrQcFlowVo emrQcFlowVo);

   List selectEmrQcFlowVoListOrderByFileTime(EmrQcFlowVo emrQcFlowVo);

   List selectEmrQcFlowVoAllList(EmrQcFlowVo emrQcFlowVo);

   int insertEmrQcFlow(EmrQcFlow emrQcFlow);

   int updateEmrQcFlow(EmrQcFlow emrQcFlow);

   int deleteEmrQcFlowById(Long id);

   List selectUnSubmitQcList(String docCode, String patientId);

   List selectUnReturnRecordList(String docCode, String patientId);

   EmrQcFlow checkMedicalRecord(@Param("admissionNo") String admissionNo);

   List selectEmrQcFlowListByInpNoList(@Param("list") List inpNoList, @Param("orgCode") String orgCode);

   void updateMrStateByAdmissionNoList(@Param("list") List admissionNoList);

   void updateFirstFileTimeById(@Param("id") Long id, @Param("dbSysdate") Date dbSysdate);

   List getQcFlowStatistic(EmrQcFlowVo emrQcFlowVo);

   EmrQcFlowStatisticVo getQcFlowStatisticAllCount(EmrQcFlowVo emrQcFlowVo);

   EmrQcFlowVo getQcFlowVoStatistic(EmrQcFlowVo emrQcFlowVo);

   int getOutDeptTotal(EmrQcFlowVo emrQcFlowVo);

   List getOutDeptListTotal(EmrQcFlowVo emrQcFlowVo);

   void insertSqgd(DzblSqgd dzblSqgd);

   void deleteHisSqgd(@Param("admissionNo") String admissionNo, @Param("hospitalizedCount") Integer hospitalizedCount);

   Integer selectCountSqgd(@Param("admissionNo") String admissionNo, @Param("hospitalizedCount") Integer hospitalizedCount);

   List selectTermBackLimitHourList(@Param("mrState") String mrState, @Param("termQcBackTimeBegin") String termQcBackTimeBegin);

   String selectDipFlag(@Param("patientId") String patientId);

   List selectDipMid(PhysignDayVo physignDayVo);
}
