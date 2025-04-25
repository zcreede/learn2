package com.emr.project.pat.mapper;

import com.emr.project.operation.domain.PatFee;
import com.emr.project.pat.domain.req.FeeDetailReq;
import com.emr.project.pat.domain.vo.ExpenseDetailVo;
import com.emr.project.pat.domain.vo.PatFeeVo;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface PatFeeMapper {
   PatFeeVo selectAmount(PatFeeVo patFeeVo);

   List selectFeeList(PatFeeVo patFeeVo);

   List selectFeeByChargeCode(ExpenseDetailVo expenseDetailVo);

   List selectFeeByThreeLevelAccounting(ExpenseDetailVo expenseDetailVo);

   List selectFeeByDate(ExpenseDetailVo expenseDetailVo);

   List selectFeeByType(ExpenseDetailVo expenseDetailVo);

   int insertBatch(List record) throws Exception;

   int insertBatchApp(List record) throws Exception;

   List getTempFyxxYz(Map params);

   List getTempFyxxZlf(Map params);

   List selectOperRoomFeeDetailList(FeeDetailReq req);

   List selectOperRoomFeeDetailGroupProject(FeeDetailReq req);

   PatFee selectFeeByPrescription(@Param("prescription") String prescription);

   PatFee selectFeeAppByPrescription(@Param("prescription") String prescription);

   void delFeeAppByPrescription(@Param("list") List prescriptions);

   List selectListPerformNo(Map param);

   List selectByPrescriptionList(@Param("orgCd") String orgCd, @Param("prescriptionList") List prescriptionList);

   List searchPriceYy(Map param);

   List searchdrugs2Resp(@Param("keyword") String keyword);
}
