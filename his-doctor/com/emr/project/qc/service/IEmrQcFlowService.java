package com.emr.project.qc.service;

import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.operation.domain.Baseinfomation;
import com.emr.project.operation.domain.Medicalinformation;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.qc.domain.EmrQcFlow;
import com.emr.project.qc.domain.EmrQcRecord;
import com.emr.project.qc.domain.vo.EmrQcFlowScoreVo;
import com.emr.project.qc.domain.vo.EmrQcFlowStatisticVo;
import com.emr.project.qc.domain.vo.EmrQcFlowVo;
import com.emr.project.qc.domain.vo.RevokeToFileReq;
import com.emr.project.webservice.domain.EmrQcFlowReq;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

public interface IEmrQcFlowService {
   EmrQcFlow selectEmrQcFlowById(Long id);

   EmrQcFlow selectEmrQcFlowById(String orgCd, String patientId);

   List selectEmrQcFlowVoListOrderByOutTime(EmrQcFlowVo emrQcFlowVo) throws Exception;

   List selectEmrQcFlowVoListOrderByApplyFileTime(EmrQcFlowVo emrQcFlowVo) throws Exception;

   List selectEmrQcFlowVoListOrderByFileTime(EmrQcFlowVo emrQcFlowVo) throws Exception;

   List selectEmrQcFlowVoAllList(EmrQcFlowVo emrQcFlowVo) throws Exception;

   List selectEmrQcFlowList(EmrQcFlow emrQcFlow);

   int insertEmrQcFlow(EmrQcFlow emrQcFlow);

   int updateEmrQcFlow(EmrQcFlow emrQcFlow);

   int updateEmrQcFlowBySys(EmrQcFlow emrQcFlow);

   int deleteEmrQcFlowById(Long id);

   void saveScoreInfo(EmrQcFlowScoreVo emrQcFlowScoreVo, EmrQcRecord emrQcRecord) throws Exception;

   void saveScoreInfo(EmrQcFlowScoreVo emrQcFlowScoreVo, EmrQcFlowVo emrQcFlowVo, EmrQcRecord emrQcRecord) throws Exception;

   void deptTermGoBack(EmrQcFlowVo emrQcFlowVo, EmrQcRecord emrQcRecord) throws Exception;

   EmrQcRecord deptTermQc(EmrQcRecord emrQcRecord, String patientId, String qcSection) throws Exception;

   void saveEmrQcFlow(EmrQcFlowVo emrQcFlowVo, String ip, Long qcBillNo) throws Exception;

   AjaxResult isSubmitQcFlowDecide(EmrQcFlow emrQcFlow) throws Exception;

   List selectUnSubmitQcList(String patientId) throws Exception;

   List selectUnReturnRecordList(String patientId) throws Exception;

   void indexToFile(String patientId, EmrQcFlow emrQcFlow) throws Exception;

   void autoSubmitDeptQc(List list) throws Exception;

   void updateQcFlowByType(VisitinfoVo visitInfoVo, EmrQcFlow emrQcFlow, EmrQcFlowReq req, EmrQcRecord emrQcRecord) throws Exception;

   void updateMrStatus(EmrQcFlow emrQcFlow, RevokeToFileReq req) throws Exception;

   List getQcFlowStatistic(EmrQcFlowVo emrQcFlowVo);

   EmrQcFlowStatisticVo getQcFlowStatisticAllCount(EmrQcFlowVo emrQcFlowVo);

   List getQcFlowStatisticByDept(EmrQcFlowVo emrQcFlowVo);

   EmrQcFlowVo getQcFlowVoStatistic(EmrQcFlowVo emrQcFlowVo, List emrQcFlowList);

   int getOutDeptTotal(EmrQcFlowVo emrQcFlowVo);

   AjaxResult getQcFlowStatisticExport(EmrQcFlowVo emrQcFlowVo, HttpServletResponse response) throws Exception;

   AjaxResult getQcFlowStatisticByDeptExport(EmrQcFlowVo emrQcFlowVo, HttpServletResponse response) throws Exception;

   void saveHisSqgd(Medicalinformation medicalinformation, EmrQcFlowScoreVo emrQcFlowScoreVo, Baseinfomation baseInfo, EmrQcFlow emrQcFlow) throws Exception;

   void deleteHisSqgd(String admissionNo, Integer hospitalizedCount) throws Exception;

   List selectTermBackLimitHourList(String mrState, String termQcBackTimeBegin) throws Exception;

   EmrQcFlow getEmrQcFlowByPatientId(String admissionNo);
}
