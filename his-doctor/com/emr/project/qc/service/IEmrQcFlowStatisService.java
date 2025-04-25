package com.emr.project.qc.service;

import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.dr.domain.vo.TdCaConsApplyVo;
import com.emr.project.qc.domain.EmrQcFlowStatis;
import com.emr.project.qc.domain.vo.StatementVo;
import com.emr.project.qc.domain.vo.StatisticsResultVo;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

public interface IEmrQcFlowStatisService {
   StatisticsResultVo selectWorkloadCircle(StatementVo statementVo) throws Exception;

   StatisticsResultVo selectWorkloadFlawCircle(StatementVo statementVo) throws Exception;

   StatisticsResultVo selectWorkloadCheckCircle(StatementVo statementVo) throws Exception;

   StatisticsResultVo selectDocFlawHistogram(StatementVo statementVo) throws Exception;

   StatisticsResultVo selectCheckFlowTypePie(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   List checkFlowTypeTable(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   List selectQcListByMrType(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   List selectQcListByMrTypeTopSix(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   List selectQcListByMrTypeTopTen(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   List selectWorkloadTable(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   List selectCheckFlowTypeDiaLogTable(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   List selectCheckEmrTypeDiaLogTable(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   List selectCheckEmrTypeDiaLogExport(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   List selectCheckDoctDialogTable(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   List selectCheckDeptDialogTable(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   AjaxResult selectCheckDeptDialogTableForExport(EmrQcFlowStatis emrQcFlowStatis, HttpServletResponse response) throws Exception;

   StatisticsResultVo selectCheckEmrTypePie(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   Object selectQcFlowStatis(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   StatisticsResultVo selectCheckFlowTypeCylinder(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   StatisticsResultVo selectCheckEmrTypeCylinder(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   List selectCheckEmrTypeTable(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   List selectCheckDoctTable(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   StatisticsResultVo selectCheckTopDoctCylinder(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   StatisticsResultVo selectCheckAfterDoctCylinder(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   StatisticsResultVo selectCheckTopDeptCylinder(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   StatisticsResultVo selectCheckAfterDeptCylinder(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   List selectCheckDeptTable(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   void setCharData(Integer count, Integer totalCount, String name, List charData) throws Exception;

   StatisticsResultVo setCylinderData(List emrQcFlowStatisList, List xAxisIdList) throws Exception;

   StatisticsResultVo setCylinderDataNoLine(List emrQcFlowStatisList) throws Exception;

   StatisticsResultVo selectMissingFileCount() throws Exception;

   StatisticsResultVo selectMissingFilePie() throws Exception;

   StatisticsResultVo selectMissingFileColumn() throws Exception;

   List selectMissingFileTable(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   StatisticsResultVo selectTimeOutEmrPie(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   StatisticsResultVo selectTimeOutEmrColumn(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   List selectTimeOutEmrTable(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   List selectTimeOutEmrTableInfo(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   StatisticsResultVo selectDoctScorePie(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   StatisticsResultVo selectDoctScoreDeptPie(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   StatisticsResultVo selectDoctScoreCheckPie(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   StatisticsResultVo selectDoctScoreTopCloumn(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   StatisticsResultVo selectDoctScoreAfterCloumn(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   StatisticsResultVo selectDoctScoreDeptTopCloumn(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   StatisticsResultVo selectDoctScoreDeptAfterCloumn(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   StatisticsResultVo selectDoctScoreCheckTopCloumn(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   StatisticsResultVo selectDoctScoreCheckAfterCloumn(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   StatisticsResultVo selectDeptScoreTopCloumn(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   StatisticsResultVo selectDeptScoreAfterCloumn(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   StatisticsResultVo selectDeptScoreTopDeptCloumn(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   StatisticsResultVo selectDeptScoreAfterDeptCloumn(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   StatisticsResultVo selectDeptScoreTopCheckCloumn(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   StatisticsResultVo selectDeptScoreAfterCheckCloumn(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   List selectScoreStatisTable(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   List selectScoreStatisDialogTable(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   List selectScoreStatisDeptTable(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   Map selectConsCheckPie(TdCaConsApplyVo tdCaConsApply) throws Exception;

   StatisticsResultVo selectConsCylinder(TdCaConsApplyVo tdCaConsApply) throws Exception;

   StatisticsResultVo selectHomeDoctorFeeCloumn() throws Exception;

   StatisticsResultVo selectHomeDeptFeeCloumn() throws Exception;

   List selectTimeOutEmrTableExport(EmrQcFlowStatis emrQcFlowStatis) throws Exception;

   List selectTimeOutEmrTableInfoExport(EmrQcFlowStatis emrQcFlowStatis) throws Exception;
}
