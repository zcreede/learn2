package com.emr.project.pat.service;

import com.emr.project.pat.domain.OperatRecord;
import com.emr.project.pat.domain.vo.OperatRecordVo;
import java.util.List;

public interface IOperatRecordService {
   OperatRecord selectOperatRecordById(String oprId);

   List selectOperatRecordList(OperatRecord operatRecord);

   int insertOperatRecord(OperatRecord operatRecord);

   int updateOperatRecord(OperatRecord operatRecord);

   int deleteOperatRecordByIds(String[] oprIds);

   int deleteOperatRecordById(String oprId);

   List selectOperatRecordByDate(OperatRecordVo operatRecordVo);
}
