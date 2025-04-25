package com.emr.project.qc.mapper;

import com.emr.project.qc.domain.QcBillRecord;
import java.util.List;

public interface QcBillRecordMapper {
   QcBillRecord selectQcBillRecordById(Long id);

   List selectQcBillRecordList(QcBillRecord qcBillRecord);

   int insertQcBillRecord(QcBillRecord qcBillRecord);

   int updateQcBillRecord(QcBillRecord qcBillRecord);

   int deleteQcBillRecordById(Long id);

   int deleteQcBillRecordByIds(Long[] ids);
}
