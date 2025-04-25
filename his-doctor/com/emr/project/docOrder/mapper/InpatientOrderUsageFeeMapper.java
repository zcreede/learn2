package com.emr.project.docOrder.mapper;

import com.emr.project.docOrder.domain.InpatientOrderUsageFee;
import java.util.List;

public interface InpatientOrderUsageFeeMapper {
   int deleteByPrimaryKey(String no);

   int insert(InpatientOrderUsageFee record);

   int insertSelective(InpatientOrderUsageFee record);

   InpatientOrderUsageFee selectByPrimaryKey(String no);

   int updateByPrimaryKeySelective(InpatientOrderUsageFee record);

   int updateByPrimaryKey(InpatientOrderUsageFee record);

   InpatientOrderUsageFee selectByConn(InpatientOrderUsageFee record);

   List selectListByConn(InpatientOrderUsageFee record);

   List selectOrderUsageFeeVoAll();
}
