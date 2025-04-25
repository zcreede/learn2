package com.emr.project.recall.service;

import com.emr.project.borrowing.domain.req.BorrowReviewedListReq;
import com.emr.project.borrowing.domain.req.CheckMedicalRecordReq;
import com.emr.project.borrowing.domain.req.SaveApplyAgreeListReq;
import com.emr.project.recall.domain.IndexRecallApply;
import java.util.List;
import java.util.Map;

public interface IIndexRecallApplyService {
   IndexRecallApply selectIndexRecallApplyById(Long id);

   List selectIndexRecallApplyList(IndexRecallApply indexRecallApply);

   int insertIndexRecallApply(IndexRecallApply indexRecallApply);

   int updateIndexRecallApply(IndexRecallApply indexRecallApply);

   int deleteIndexRecallApplyByIds(Long[] ids);

   int deleteIndexRecallApplyById(Long id);

   Map checkMedicalRecord(CheckMedicalRecordReq req) throws Exception;

   List selectReviewedList(BorrowReviewedListReq req);

   List getApplyList(IndexRecallApply indexRecallApply) throws Exception;

   void updateApply(IndexRecallApply indexRecallApply, String recallStatus) throws Exception;

   void updateStatus(SaveApplyAgreeListReq req, String status) throws Exception;

   Boolean checkApplyByAdmissionNo(String admissionNo);

   void saveApply(IndexRecallApply indexRecallApply) throws Exception;
}
