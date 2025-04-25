package com.emr.project.pat.mapper;

import com.emr.project.pat.domain.OperatRecord;
import com.emr.project.pat.domain.vo.OperatRecordVo;
import java.util.List;

public interface OperatRecordMapper {
   OperatRecord selectOperatRecordById(String oprId);

   List selectOperatRecordList(OperatRecord operatRecord);

   int insertOperatRecord(OperatRecord operatRecord);

   int updateOperatRecord(OperatRecord operatRecord);

   int deleteOperatRecordById(String oprId);

   int deleteOperatRecordByIds(String[] oprIds);

   List selectOperatRecordByDate(OperatRecordVo operatRecordVo);
}
