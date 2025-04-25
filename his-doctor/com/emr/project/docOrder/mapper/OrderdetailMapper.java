package com.emr.project.docOrder.mapper;

import com.emr.project.docOrder.domain.Orderdetail;
import java.util.List;

public interface OrderdetailMapper {
   Orderdetail selectOrderdetailById(String id);

   List selectOrderdetailList(Orderdetail orderdetail);

   int insertOrderdetail(Orderdetail orderdetail);

   int updateOrderdetail(Orderdetail orderdetail);

   int deleteOrderdetailById(String id);

   int deleteOrderdetailByIds(String[] ids);
}
