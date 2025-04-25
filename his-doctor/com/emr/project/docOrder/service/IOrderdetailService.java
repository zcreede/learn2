package com.emr.project.docOrder.service;

import com.emr.project.docOrder.domain.Orderdetail;
import java.util.List;

public interface IOrderdetailService {
   Orderdetail selectOrderdetailById(String id);

   List selectOrderdetailList(Orderdetail orderdetail);

   int insertOrderdetail(Orderdetail orderdetail);

   int updateOrderdetail(Orderdetail orderdetail);

   int deleteOrderdetailByIds(String[] ids);

   int deleteOrderdetailById(String id);
}
