package com.emr.project.mrhp.service;

import com.emr.project.mrhp.domain.MrHpFee;
import com.emr.project.mrhp.domain.vo.MrHpFeePbVo;
import com.emr.project.mrhp.domain.vo.TdCmFeeVo;
import com.emr.project.system.domain.vo.SqlVo;
import java.util.List;
import java.util.Map;

public interface IMrHpFeeService {
   MrHpFee selectMrHpFeeById(String feeId);

   TdCmFeeVo selectMrHpFeeByRecordId(String recordId);

   List selectMrHpFeeList(MrHpFee mrHpFee);

   List selectMrHpFeeListByProc(Map param) throws Exception;

   int insertMrHpFee(MrHpFee mrHpFee);

   int updateMrHpFee(MrHpFee mrHpFee);

   int deleteMrHpFeeByIds(String[] feeIds);

   int deleteMrHpFeeById(String feeId);

   List selectMrHpHisList(SqlVo sqlVo) throws Exception;

   void saveHpFeeList(List mrHpFeeList) throws Exception;

   List selectHisPbMrHpFeeList(SqlVo sqlVo) throws Exception;

   MrHpFeePbVo selectHisPbMrHpFeeKJList(SqlVo sqlVo) throws Exception;

   MrHpFeePbVo selectHisPbMrHpFeeZFList(SqlVo sqlVo) throws Exception;
}
