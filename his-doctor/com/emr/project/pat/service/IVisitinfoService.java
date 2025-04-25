package com.emr.project.pat.service;

import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.docOrder.domain.vo.OrderSaveVo;
import com.emr.project.docOrder.domain.vo.TdPaOrderAgentVo;
import com.emr.project.pat.domain.Personalinfo;
import com.emr.project.pat.domain.Visitinfo;
import com.emr.project.pat.domain.vo.OperRoomVisitinfoVo;
import com.emr.project.pat.domain.vo.PatientInfoVo;
import com.emr.project.pat.domain.vo.VisitinfoPersonalVo;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.qc.domain.vo.RunTimeQcCheckVo;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.vo.SqlVo;
import com.emr.project.webEditor.zb.vo.TimeLineDataVo;
import java.util.List;
import java.util.Map;

public interface IVisitinfoService {
   VisitinfoVo selectVisitinfoById(String patientId) throws Exception;

   VisitinfoVo selectVisitinfoByPatientId(String patientId) throws Exception;

   VisitinfoPersonalVo selectVisitinfoPersonalById(String patientId) throws Exception;

   List selectVisitinfoList(Visitinfo visitinfo) throws Exception;

   int insertVisitinfo(Visitinfo visitinfo);

   int updateVisitinfo(Visitinfo visitinfo);

   void updateVisitAndPersonInfo(VisitinfoVo visitinfoVo, Personalinfo personalinfo) throws Exception;

   int deleteVisitinfoByIds(String[] patientIds);

   int deleteVisitinfoById(String patientId);

   List selectVisitinfoVoList(VisitinfoVo visitinfoVo) throws Exception;

   List selectAdminPatList(VisitinfoVo visitinfoVo) throws Exception;

   List selectDeptPatientList(VisitinfoVo visitinfoVo) throws Exception;

   List selectOutHosPatList(VisitinfoVo visitinfoVo) throws Exception;

   List selectImpPatList(VisitinfoVo visitinfoVo) throws Exception;

   List selectPatientGroupList(VisitinfoVo visitinfoVo) throws Exception;

   Map selectColorplan() throws Exception;

   List selectVisitinfosById(String patientId);

   List selectVisitinfosByPatientId(String patientId, String visitNo);

   List inspectReportTimes(String patientId) throws Exception;

   VisitinfoVo selectvistDiagInfo(String patientId) throws Exception;

   Visitinfo selectVisitinfoByInpNo(String inpNo);

   List selectVisitinfoPersonalList(VisitinfoPersonalVo visitinfoPersonalVo) throws Exception;

   List selectSevenDayInfo(Visitinfo visitinfo) throws Exception;

   TimeLineDataVo selectTimeInfo(String startDate, String endDate, List dayVoList, Visitinfo visitinfo) throws Exception;

   BasEmployee getUperDoct(String patientId, Visitinfo visitinfo) throws Exception;

   List selectResDocPersonList(String resDocCd) throws Exception;

   List selectRunTimeQcCheckVo(RunTimeQcCheckVo runTimeQcCheckVo) throws Exception;

   Integer selectRunTimeQcCheckCount(RunTimeQcCheckVo runTimeQcCheckVo) throws Exception;

   List selectRunTimeQcCheckVoNoInfo(RunTimeQcCheckVo runTimeQcCheckVo) throws Exception;

   VisitinfoVo selectResDocPatNumber() throws Exception;

   VisitinfoVo selectGroupPatNumber() throws Exception;

   VisitinfoVo selectDeptPatNumber() throws Exception;

   Boolean selectIsPrivacyLevel(String patientId) throws Exception;

   List selectInHosPersonList(VisitinfoVo visitinfoVo) throws Exception;

   List selectVistinfoListByPatList(List patientIdList) throws Exception;

   List selectVistinfoListByInpNoList(List inpNoList) throws Exception;

   void updateOutInfoFromOrder(OrderSaveVo orderSaveVo) throws Exception;

   VisitinfoVo selectPatientById(VisitinfoVo visitinfoVo) throws Exception;

   List selectHisVisitinfoListByPatientId(String patientId) throws Exception;

   void updateFromOrderAgent(TdPaOrderAgentVo agentVo, String patientId) throws Exception;

   VisitinfoVo selectInhosMedicalData() throws Exception;

   AjaxResult selectHomeBacklogList(String patientId) throws Exception;

   void syncPatData() throws Exception;

   List selectPatientData(SqlVo deptVo) throws Exception;

   Visitinfo selectHisPatByInpNo(String inpNo) throws Exception;

   void syncPatVisitinfo(List hisData, List hisPerData) throws Exception;

   List selectDeptArchiveList(Integer days, List mrStateList) throws Exception;

   List selectPatInfoByOpeList(OperRoomVisitinfoVo visitinfoVo) throws Exception;

   PatientInfoVo selectPatientInfoByOpeList(OperRoomVisitinfoVo visitinfoVo) throws Exception;

   String selectOperRoomQueryType() throws Exception;

   List selectCriticalInfo(SqlVo deptVo) throws Exception;

   void updatePatientLevelCare(VisitinfoVo visitinfo, String levelCare, String levelName) throws Exception;

   void updatePatientIllness(String inpNo, String illness);

   List selectVistinfoListByOuTime(String endDate);

   List selectDiagInfo(String patientId);

   List selectDiagInfoByAdmissionNo(String admissionNo);

   String selectCaMesByUserName(String emplNumber);

   void updateStaffCa(String emplNumber, String ca);
}
