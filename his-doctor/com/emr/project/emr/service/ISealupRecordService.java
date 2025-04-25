package com.emr.project.emr.service;

import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.SealupRecord;
import com.emr.project.emr.domain.SubfileIndex;
import com.emr.project.emr.domain.vo.IndexStateVo;
import com.emr.project.emr.domain.vo.PasswordVo;
import com.emr.project.emr.domain.vo.SealupVo;
import com.emr.project.pat.domain.vo.VisitinfoPersonalVo;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public interface ISealupRecordService {
   SealupRecord selectSealupRecordById(Long id);

   List selectSealupRecordList(SealupRecord sealupRecord);

   void insertSealupRecord(PasswordVo passwordVo, HttpServletRequest httpServletRequest) throws Exception;

   int updateSealupRecord(SealupRecord sealupRecord);

   int deleteSealupRecordByIds(Long[] ids);

   int deleteSealupRecordById(Long id);

   SealupRecord selectSealupRecordByInpNo(String inpNo);

   SealupRecord selectSealupRecordByPatientId(String patientId);

   IndexStateVo indexSealupState(VisitinfoPersonalVo visitinfoPersonalVo);

   void againSealupRecord(PasswordVo passwordVo) throws Exception;

   void secureRecord(SealupRecord sealupRecord, HttpServletRequest httpServletRequest) throws Exception;

   List querySealupList(SealupVo sealupVo);

   Boolean addSealupFile(Index index, SubfileIndex subfileIndex) throws Exception;
}
