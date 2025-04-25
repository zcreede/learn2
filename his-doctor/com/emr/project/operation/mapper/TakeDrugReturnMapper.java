package com.emr.project.operation.mapper;

import com.emr.project.operation.domain.TakeDrugReturn;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface TakeDrugReturnMapper {
   int deleteByPrimaryKey(String serialNumber);

   int insert(TakeDrugReturn record);

   int insertSelective(TakeDrugReturn record);

   TakeDrugReturn selectByPrimaryKey(String serialNumber);

   int updateByPrimaryKeySelective(TakeDrugReturn record);

   int updateByPrimaryKey(TakeDrugReturn record);

   void insertList(List list);

   List selectPageList(@Param("searchStr") String searchStr, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("deptCode") String deptCode);

   int selectPageCount(Map param);

   void cancelTakeDrugReturn(@Param("mark") String mark, TakeDrugReturn takeDrugReturn);

   List selectByBillsNoOldList(List list);

   List searchTtakeDrugReturnByMark(@Param("mark") String mark, @Param("admissionNo") String admissionNo);

   List selectBySerialNumberList(List list);

   void deleteByIdList(List serialNumberList);

   List selectByBillsNoOldListAndMark(@Param("list") List billsNoList);
}
