package com.emr.project.operation.mapper;

import com.emr.project.operation.domain.Tfsqb;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TfsqbMapper {
   int insertBatch(@Param("list") List record);

   int updateRefundBatch9(List list);

   int insert(Tfsqb record);

   int insertSelective(Tfsqb record);

   List searchTfsqb(@Param("admissionNo") String admissionNo, @Param("mark") String mark);

   List selectTfsqListByBillNoList(@Param("list") List billNoList);

   void updateRefundToFeeBatch(@Param("tfsqbList") List tfsqbList);

   Tfsqb selectByKey(@Param("orgCd") String orgCd, @Param("admissionNo") String admissionNo, @Param("serialNumber") String serialNumber, @Param("serialNumberXh") String serialNumberXh);
}
