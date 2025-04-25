package com.emr.project.borrowing.service;

import com.emr.project.borrowing.domain.EmrIndexBorrow;
import com.emr.project.borrowing.domain.req.BorrowReviewedListReq;
import com.emr.project.borrowing.domain.req.CheckMedicalRecordReq;
import com.emr.project.borrowing.domain.req.PatientOutReq;
import com.emr.project.borrowing.domain.req.SaveApplyAgreeListReq;
import com.emr.project.borrowing.domain.req.UpdateApplyDetailReq;
import java.util.List;
import java.util.Map;

public interface IEmrIndexBorrowService {
   EmrIndexBorrow selectEmrIndexBorrowById(Long id);

   List selectEmrIndexBorrowList(EmrIndexBorrow emrIndexBorrow);

   List selectEmrIndexBorrowValidList(EmrIndexBorrow emrIndexBorrow);

   int insertEmrIndexBorrow(EmrIndexBorrow emrIndexBorrow);

   int updateEmrIndexBorrow(EmrIndexBorrow emrIndexBorrow);

   int deleteEmrIndexBorrowByIds(Long[] ids);

   int deleteEmrIndexBorrowById(Long id);

   Map checkMedicalRecord(CheckMedicalRecordReq req);

   List getApplyList(EmrIndexBorrow emrIndexBorrow);

   List selectReviewedList(BorrowReviewedListReq req) throws Exception;

   List getBorrowPurposeList();

   void updateStatus(SaveApplyAgreeListReq req, String borrowStatus) throws Exception;

   Map saveApply(EmrIndexBorrow emrIndexBorrow) throws Exception;

   void updateApply(UpdateApplyDetailReq req, String borrowStatus, EmrIndexBorrow emrIndexBorrow);

   Boolean checkApplyByAdmissionNo(String admissionNo);

   void autoReturn() throws Exception;

   List patientOutList(PatientOutReq req);
}
