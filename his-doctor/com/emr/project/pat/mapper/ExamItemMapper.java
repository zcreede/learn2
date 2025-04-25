package com.emr.project.pat.mapper;

import com.emr.project.pat.domain.ExamItem;
import com.emr.project.pat.domain.vo.ExamItemVo;
import com.emr.project.system.domain.vo.SqlVo;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ExamItemMapper {
   ExamItem selectExamItemById(String id);

   List selectExamItemList(ExamItemVo examItemVo);

   List selectItemList(String[] patientIds);

   List selectItemListByDate(ExamItemVo examItemVo);

   List selectExamItemResultList(String id);

   int insertExamItem(ExamItem examItem);

   int updateExamItem(ExamItem examItem);

   int deleteExamItemById(String id);

   int deleteExamItemByIds(String[] ids);

   void insertMap(Map map);

   List selectExamUnLookList(String patientId);

   List selectTestUnLookList(String patientId);

   void updateLookState(ExamItem examItem);

   List selectHisExamItemList(SqlVo sqlVo);

   List selectExamAlertMsg(ExamItemVo param);

   List selectByAppCd(@Param("appCd") String appCd);

   List selectExamItemByAppCdList(@Param("list") List appCdList);

   List selectHisExamAlertList(ExamItemVo examItemVo);

   List selectMzExamItemList(ExamItemVo examItemVo);
}
