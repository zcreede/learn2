package com.emr.project.qc.mapper;

import com.emr.project.qc.domain.EmrQcFlowStatis;
import com.emr.project.qc.domain.vo.StatementVo;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface EmrQcFlowStatisMapper {
   List selectReportComDetailList(Long repId);

   List selectStatisListMap(String sqlStr);

   Map selectStatisMap(String sqlStr);

   Integer selectStatisInteger(String sqlStr);

   Double selectStatisDouble(String sqlStr);

   Integer selectPatTotal(StatementVo statementVo);

   Integer selectEmrQcTotalGroup(StatementVo statementVo);

   Integer selectEmrQcTotal(StatementVo statementVo);

   Integer selectEmrQcListTotal(StatementVo statementVo);

   Integer selectEmrQcListCheckTotal(StatementVo statementVo);

   Integer selectEmrQcListUnCheckTotal(StatementVo statementVo);

   List selectEmrQcDocCodeList(StatementVo statementVo);

   List selectDocFlawHistogram(StatementVo statementVo);

   List selectFlawTablePage(EmrQcFlowStatis emrQcFlowStatis);

   List selectFlawTableInfo(EmrQcFlowStatis emrQcFlowStatis);

   List selectFlawTableCheck(EmrQcFlowStatis emrQcFlowStatis);

   List selectCheckFlowTypePie(EmrQcFlowStatis emrQcFlowStatis);

   Integer selectFlowTypePieTotal(EmrQcFlowStatis emrQcFlowStatis);

   List selectCheckFlowTypeCylinderEle(EmrQcFlowStatis emrQcFlowStatis);

   List selectCheckFlowTypeCylinderList(EmrQcFlowStatis emrQcFlowStatis);

   Integer selectCheckEmrTotal(EmrQcFlowStatis emrQcFlowStatis);

   List selectCheckEmrClassTotal(EmrQcFlowStatis emrQcFlowStatis);

   List selectCheckEmrTypeTopTen(EmrQcFlowStatis emrQcFlowStatis);

   List selectCheckEmrTypeList(EmrQcFlowStatis emrQcFlowStatis);

   List selectCheckDoctList(EmrQcFlowStatis emrQcFlowStatis);

   List selectCheckDoctTable(EmrQcFlowStatis emrQcFlowStatis);

   List selectCheckDeptList(EmrQcFlowStatis emrQcFlowStatis);

   List selectCheckFlowTypeDiaLogTable(EmrQcFlowStatis emrQcFlowStatis);

   List selectCheckFlowTypeDiaLogExport(EmrQcFlowStatis emrQcFlowStatis);

   List selectMissingFileCount();

   List selectMissingFileDeptCount();

   List selectMissingFileTable(EmrQcFlowStatis emrQcFlowStatis);

   List selectTimeOutEmrPie(EmrQcFlowStatis emrQcFlowStatis);

   List selectTimeOutEmrTotal(EmrQcFlowStatis emrQcFlowStatis);

   List selectTimeOutEmrTotalByDoct(EmrQcFlowStatis emrQcFlowStatis);

   List selectTimeOutEmrWrite(EmrQcFlowStatis emrQcFlowStatis);

   List selectTimeOutEmrWriteByDoct(EmrQcFlowStatis emrQcFlowStatis);

   List selectTimeOutEmrUnWrite(EmrQcFlowStatis emrQcFlowStatis);

   List selectTimeOutEmrUnWriteByDoct(EmrQcFlowStatis emrQcFlowStatis);

   List selectTimeOutEmrTableInfo(EmrQcFlowStatis emrQcFlowStatis);

   List selectDoctScorePie(EmrQcFlowStatis emrQcFlowStatis);

   List selectDoctScoreDeptPie(EmrQcFlowStatis emrQcFlowStatis);

   List selectDoctScoreCheckPie(EmrQcFlowStatis emrQcFlowStatis);

   List selectDoctScoreCloumn(EmrQcFlowStatis emrQcFlowStatis);

   List selectDoctScoreDeptCloumn(EmrQcFlowStatis emrQcFlowStatis);

   List selectDoctScoreCheckCloumn(EmrQcFlowStatis emrQcFlowStatis);

   Integer selectScoreEmrTotal(EmrQcFlowStatis emrQcFlowStatis);

   List selectDeptScoreCloumn(EmrQcFlowStatis emrQcFlowStatis);

   List selectDeptScoreDeptCloumn(EmrQcFlowStatis emrQcFlowStatis);

   List selectDeptScoreCheckCloumn(EmrQcFlowStatis emrQcFlowStatis);

   List selectScoreStatisTable(EmrQcFlowStatis emrQcFlowStatis);

   List selectScoreStatisDialogTable(EmrQcFlowStatis emrQcFlowStatis);

   List selectScoreStatisDeptTable(EmrQcFlowStatis emrQcFlowStatis);

   List selectHomeDeptFeeList(@Param("deptCode") String deptCode, @Param("docCd") String docCd, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

   List selectFirstItemList();
}
