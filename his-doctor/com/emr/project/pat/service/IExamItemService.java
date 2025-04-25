package com.emr.project.pat.service;

import com.emr.project.pat.domain.ExamItem;
import com.emr.project.pat.domain.vo.ExamItemVo;
import com.emr.project.pat.domain.vo.TestExamAlertMsgResVo;
import com.emr.project.system.domain.vo.SqlVo;
import java.util.List;

public interface IExamItemService {
   ExamItem selectExamItemById(String id);

   List selectExamItemList(ExamItemVo examItemVo);

   List selectItemList(String[] patientIds) throws Exception;

   List selectItemListByDate(ExamItemVo examItemVo) throws Exception;

   List selectExamItemResultList(String id) throws Exception;

   int insertExamItem(ExamItem examItem);

   int updateExamItem(ExamItem examItem);

   int deleteExamItemByIds(String[] ids);

   int deleteExamItemById(String id);

   List selectUnLookList(String patientId) throws Exception;

   void updateLookState(ExamItem examItem) throws Exception;

   List selectHisExamItemList(SqlVo sqlVo) throws Exception;

   List selectHisExamItemResult(List list) throws Exception;

   TestExamAlertMsgResVo selectExamAlertMsg(ExamItemVo param) throws Exception;

   List selectByAppCd(String appCd) throws Exception;

   List selectExamItemByAppCdList(List applyFormNoList);

   List selectHisExamAlertList(ExamItemVo examItemVo) throws Exception;

   List selectMzExamItemList(ExamItemVo examItemVo) throws Exception;
}
