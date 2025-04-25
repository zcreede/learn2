package com.emr.project.docOrder.mapper;

import com.emr.project.docOrder.domain.TdPaOrderMessage;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TdPaOrderMessageMapper {
   TdPaOrderMessage selectTdPaOrderMessageById(Long id);

   List selectTdPaOrderMessageList(TdPaOrderMessage tdPaOrderMessage);

   int insertTdPaOrderMessage(TdPaOrderMessage tdPaOrderMessage);

   int updateTdPaOrderMessage(TdPaOrderMessage tdPaOrderMessage);

   int deleteTdPaOrderMessageById(Long id);

   int deleteTdPaOrderMessageByIds(Long[] ids);

   void deleteCancelOrderMessage(@Param("admissionNos") List admissionNos);

   void insertList(List list);

   void updateByOrderNo(@Param("orderNoList") List orderNoList, @Param("msgStatus") String msgStatus, @Param("updateOper") String updateOper, @Param("orderStatus") String orderStatus);

   List selectOrderReturnMessageList(@Param("userName") String userName, @Param("orgCode") String orgCode, @Param("msgStatus") String msgStatus, @Param("startTime") Date startTime, @Param("endTime") Date endTime);
}
