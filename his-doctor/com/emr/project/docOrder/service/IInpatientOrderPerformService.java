package com.emr.project.docOrder.service;

import com.emr.project.docOrder.domain.vo.AllergyRecordDTO;
import com.emr.project.docOrder.domain.vo.InpatientOrderPerformVo;
import com.emr.project.pat.domain.Visitinfo;
import java.util.List;

public interface IInpatientOrderPerformService {
   List selectInpatientOrderPerform(InpatientOrderPerformVo inpatientOrderPerformVo) throws Exception;

   List selectSkinTestResultByPatient(String admissionNo) throws Exception;

   List selectInpatientOrderPerformPageData(InpatientOrderPerformVo inpatientOrderPerformVo, List listAll, Integer pageSize, Integer pageNum) throws Exception;

   void updateInpatientOrderPerform(List inPerform, AllergyRecordDTO allergyRecord, Visitinfo visitinfo, List printList, List handleUplist) throws Exception;
}
