package com.emr.project.qc.service;

import com.emr.project.qc.domain.QcBillRecord;
import com.emr.project.qc.domain.vo.QcBillRecordVo;
import com.emr.project.qc.domain.vo.RunTimeQcCheckVo;
import java.util.List;

public interface IQcBillRecordService {
   QcBillRecord selectQcBillRecordById(Long id);

   List selectQcBillRecordList(QcBillRecord qcBillRecord);

   List selectQcBillRecordVoList(QcBillRecordVo qcBillRecord) throws Exception;

   List selectQcBillRecordVoListPage(List listAll, Integer pageNum, Integer pageSize) throws Exception;

   int insertQcBillRecord(QcBillRecord qcBillRecord);

   void insertQcBillRecord(QcBillRecordVo qcBillRecord, RunTimeQcCheckVo runTimeQcCheckVo) throws Exception;

   int updateQcBillRecord(QcBillRecord qcBillRecord);

   int deleteQcBillRecordByIds(Long[] ids);

   int deleteQcBillRecordById(Long id);
}
