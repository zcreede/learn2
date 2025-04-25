package com.emr.project.emr.mapper;

import com.emr.project.emr.domain.SealupRecord;
import com.emr.project.emr.domain.vo.SealupVo;
import java.util.List;

public interface SealupRecordMapper {
   SealupRecord selectSealupRecordById(Long id);

   List selectSealupRecordList(SealupRecord sealupRecord);

   int insertSealupRecord(SealupRecord sealupRecord);

   int updateSealupRecord(SealupRecord sealupRecord);

   int deleteSealupRecordById(Long id);

   int deleteSealupRecordByIds(Long[] ids);

   SealupRecord selectSealupRecordByInpNo(String inpNo);

   SealupRecord selectSealupRecordByPatientId(String patientId);

   List querySealupList(SealupVo sealupVo);
}
