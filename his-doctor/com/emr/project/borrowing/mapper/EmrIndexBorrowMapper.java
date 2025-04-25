package com.emr.project.borrowing.mapper;

import com.emr.project.borrowing.domain.EmrIndexBorrow;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmrIndexBorrowMapper {
   EmrIndexBorrow selectEmrIndexBorrowById(Long id);

   EmrIndexBorrow selectEmrIndexBorrowByAdmissionNo(@Param("admissionNo") String admissionNo, @Param("userName") String userName);

   List selectEmrIndexBorrowList(EmrIndexBorrow emrIndexBorrow);

   List selectEmrIndexBorrowValidList(EmrIndexBorrow emrIndexBorrow);

   int insertEmrIndexBorrow(EmrIndexBorrow emrIndexBorrow);

   int updateEmrIndexBorrow(EmrIndexBorrow emrIndexBorrow);

   int deleteEmrIndexBorrowById(Long id);

   int deleteEmrIndexBorrowByIds(Long[] ids);

   List selectApplyList(EmrIndexBorrow emrIndexBorrow);

   List selectReviewedList(@Param("list") List list, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("searchStr") String searchStr, @Param("dateType") Integer dateType, @Param("appDeptCd") String appDeptCd, @Param("appDocName") String appDocName, @Param("conDocName") String conDocName, @Param("deptCode") String deptCode);

   List checkApplyByAdmissionNo(@Param("admissionNo") String admissionNo, @Param("userName") String userName);

   List selectAutoReturnList(@Param("dateTime") String dateTime);

   void updateAutoReturnByList(@Param("list") List idList);

   List selectPatientOutList(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("deptNo") String deptNo, @Param("searchStr") String searchStr);
}
