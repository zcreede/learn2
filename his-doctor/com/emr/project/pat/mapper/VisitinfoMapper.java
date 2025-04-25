package com.emr.project.pat.mapper;

import com.emr.project.pat.domain.Visitinfo;
import com.emr.project.pat.domain.vo.OperRoomVisitinfoVo;
import com.emr.project.pat.domain.vo.PatientInfoVo;
import com.emr.project.pat.domain.vo.VisitinfoPersonalVo;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.qc.domain.vo.RunTimeQcCheckVo;
import com.emr.project.system.domain.vo.SqlVo;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

public interface VisitinfoMapper {
   VisitinfoVo selectVisitinfoById(@Param("patientId") String patientId);

   List selectVisitinfoList(Visitinfo visitinfo);

   int insertVisitinfo(Visitinfo visitinfo);

   int updateVisitinfo(Visitinfo visitinfo);

   int updateVisitSave(VisitinfoVo visitinfoVo);

   int deleteVisitinfoById(String patientId);

   int deleteVisitinfoByIds(String[] patientIds);

   List selectVisitinfoVoList(VisitinfoVo visitinfoVo);

   List selectAdminPatList(VisitinfoVo visitinfoVo);

   VisitinfoVo selectAdminPatListTotal(VisitinfoVo visitinfoVo);

   List selectDeptPatientList(VisitinfoVo visitinfoVo);

   VisitinfoVo selectDeptPatientListTotal(VisitinfoVo visitinfoVo);

   List selectOutHosPatList(VisitinfoVo visitinfoVo);

   VisitinfoVo selectOutHosPatListTotal(VisitinfoVo visitinfoVo);

   List selectOutHosAndNotFiledPatList(VisitinfoVo visitinfoVo);

   VisitinfoVo selectOutHosAndNotFiledPatListTotal(VisitinfoVo visitinfoVo);

   List selectImpPatList(VisitinfoVo visitinfoVo);

   VisitinfoVo selectImpPatListTotal(VisitinfoVo visitinfoVo);

   void insertMap(Map map);

   void insertMapList(Map map);

   void updateMap(Map map);

   List selectColorplan();

   List selectPatientGroupList(VisitinfoVo visitinfoVo);

   VisitinfoVo selectPatientGroupListTotal(VisitinfoVo visitinfoVo);

   List selectBackoutList(VisitinfoVo visitinfoVo);

   VisitinfoVo selectPatientById(VisitinfoVo visitinfoVo);

   List selectVisitinfosById(@Param("patientId") String patientId);

   List selectVisitinfosByPatientId(@Param("patientId") String patientId, @Param("visitNo") String visitNo);

   int getCountByAdmissionNo(@PathVariable("patientId") String patientId);

   PatientInfoVo getPatientInfoDetail(OperRoomVisitinfoVo visitinfoVo);

   PatientInfoVo getPatientInfoDetailFromHis(OperRoomVisitinfoVo visitinfoVo);

   List inspectReportTimes(String patientMainId, String patientId);

   Visitinfo selectPatientByInpNo(@Param("inpNo") String inpNo);

   VisitinfoPersonalVo selectVisitinfoPersonalVoById(@Param("patientId") String patientId);

   List selectVisitinfoPersonalList(VisitinfoPersonalVo visitinfoPersonalVo);

   List selectResDocPersonList(@Param("resDocCd") String resDocCd, @Param("deptCd") String deptCd);

   List selectRunTimeQcCheck(RunTimeQcCheckVo runTimeQcCheckVo);

   Integer selectRunTimeQcCheckCount(RunTimeQcCheckVo runTimeQcCheckVo);

   List selectDeptPatList(String deptCode);

   List selectInHosList(Visitinfo visitinfo);

   List selectInHosPersonList(VisitinfoVo visitinfoVo);

   List selectVistinfoListByPatList(List patientIdList);

   List selectVistinfoListByInpNoList(List inpNoList);

   List selectHisVisitinfoListByPatientId(String patientId);

   Integer selectTodayInhosNum(String deptCode);

   Integer selectTodayOutHosNum(String deptCode);

   void deleteVisitinfoByTableName(String tableName, String inpNo);

   void deleteVisitinfoByInpNoList(@Param("tableName") String tableName, @Param("inpNoList") List inpNoList);

   List selectSyncVisitinfoList(SqlVo sqlVo);

   List selectSyncVisitinfoOutTimeList(List inpNoList, String deptCode);

   List selectHisAddPatientList(SqlVo sqlVo);

   List selectCriticalInfo(SqlVo sqlVo);

   Visitinfo selectHisPatByInpNo(String inpNo);

   List selectDeptArchiveList(Integer days, List mrStateList);

   List selectOperVisitinfoList(OperRoomVisitinfoVo operRoomVisitinfoVo);

   void updatePatientCareLevel(@Param("levelCare") String levelCare, @Param("admissionNo") String admissionNo);

   void updatePatientIllness(@Param("illness") String illness, @Param("admissionNo") String admissionNo);

   List selectVistinfoListByOuTime(@Param("endDate") String endDate);

   List selectDiagInfo(@Param("patientId") String patientId);

   List selectDiagInfoByAdmissionNo(@Param("patientId") String admissionNo);

   String selectCaMesByUserName(@Param("emplNumber") String emplNumber);

   void updateStaffCa(@Param("emplNumber") String emplNumber, @Param("ca") String ca);
}
