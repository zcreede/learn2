package com.emr.project.recall.mapper;

import com.emr.project.recall.domain.IndexRecallApply;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IndexRecallApplyMapper {
   IndexRecallApply selectIndexRecallApplyById(Long id);

   List selectIndexRecallApplyList(IndexRecallApply indexRecallApply);

   int insertIndexRecallApply(IndexRecallApply indexRecallApply);

   int updateIndexRecallApply(IndexRecallApply indexRecallApply);

   int deleteIndexRecallApplyById(Long id);

   int deleteIndexRecallApplyByIds(Long[] ids);

   IndexRecallApply selectRecallApplyByAdmissionNo(@Param("admissionNo") String admissionNo, @Param("userName") String userName);

   List selectReviewedList(@Param("list") List list, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("searchStr") String searchStr, @Param("dateType") Integer dateType, @Param("appDeptCd") String appDeptCd, @Param("appDocName") String appDocName);
}
