package com.emr.project.pat.service;

import com.emr.project.operation.domain.PatFee;
import com.emr.project.operation.domain.dto.DepartAccountDTO;
import com.emr.project.pat.domain.req.FeeDetailReq;
import com.emr.project.pat.domain.vo.ExpenseDetailVo;
import com.emr.project.pat.domain.vo.PatFeeVo;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface IPatFeeService {
   PatFeeVo selectAmountList(PatFeeVo patFeeVo) throws Exception;

   List selectFeeList(PatFeeVo patFeeVo) throws Exception;

   List selectFeeByChargeCode(ExpenseDetailVo expenseDetailVo) throws Exception;

   List selectFeeByThreeLevelAccounting(ExpenseDetailVo expenseDetailVo) throws Exception;

   List selectFeeByDate(ExpenseDetailVo expenseDetailVo) throws Exception;

   List selectFeeByType(ExpenseDetailVo expenseDetailVo) throws Exception;

   BigDecimal toFee(List dataList, String feeType, String performListNo, List isAccountFlagSuccessList, DepartAccountDTO departAccountDTO) throws Exception;

   List getPrintDate(Map param);

   List selectOperRoomFeeDetailList(FeeDetailReq req) throws Exception;

   PatFee selectFeeAppByPrescription(String prescription) throws Exception;

   void delFeeAppByPrescription(List prescriptions) throws Exception;

   List searchdrugs2Resp(String keyword);

   List searchPriceYy(String keyword);

   List exportFeeDetailList(FeeDetailReq req) throws Exception;
}
