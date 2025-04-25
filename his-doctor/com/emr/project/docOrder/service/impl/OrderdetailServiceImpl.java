package com.emr.project.docOrder.service.impl;

import com.emr.project.docOrder.domain.Orderdetail;
import com.emr.project.docOrder.mapper.OrderdetailMapper;
import com.emr.project.docOrder.service.IOrderdetailService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderdetailServiceImpl implements IOrderdetailService {
   @Autowired
   private OrderdetailMapper orderdetailMapper;

   public Orderdetail selectOrderdetailById(String id) {
      return this.orderdetailMapper.selectOrderdetailById(id);
   }

   public List selectOrderdetailList(Orderdetail orderdetail) {
      return this.orderdetailMapper.selectOrderdetailList(orderdetail);
   }

   public int insertOrderdetail(Orderdetail orderdetail) {
      return this.orderdetailMapper.insertOrderdetail(orderdetail);
   }

   public int updateOrderdetail(Orderdetail orderdetail) {
      return this.orderdetailMapper.updateOrderdetail(orderdetail);
   }

   public int deleteOrderdetailByIds(String[] ids) {
      return this.orderdetailMapper.deleteOrderdetailByIds(ids);
   }

   public int deleteOrderdetailById(String id) {
      return this.orderdetailMapper.deleteOrderdetailById(id);
   }
}
