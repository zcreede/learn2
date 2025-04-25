package com.emr.project.mrhp.mapper;

import com.emr.project.mrhp.domain.MrHpFee;
import com.emr.project.mrhp.domain.vo.MrHpFeePbVo;
import com.emr.project.mrhp.domain.vo.TdCmFeeVo;
import com.emr.project.system.domain.vo.SqlVo;
import java.util.List;
import java.util.Map;

public interface MrHpFeeMapper {
   MrHpFee selectMrHpFeeById(String feeId);

   TdCmFeeVo selectMrHpFeeByRecordId(String recordId);

   List selectMrHpFeeList(MrHpFee mrHpFee);

   int insertMrHpFee(MrHpFee mrHpFee);

   int updateMrHpFee(MrHpFee mrHpFee);

   int deleteMrHpFeeById(String feeId);

   int deleteMrHpFeeByIds(String[] feeIds);

   int deleteMrHpFeeByRescordId(String rescordId);

   int insertMrHpFeeList(List mrHpFeeList);

   void selectMrHpFeeListByProc(Map param);

   List selectHisPbMrHpFeeList(SqlVo sqlVo);

   MrHpFeePbVo selectHisPbMrHpFeeKJList(SqlVo sqlVo);

   MrHpFeePbVo selectHisPbMrHpFeeZFList(SqlVo sqlVo);
}
