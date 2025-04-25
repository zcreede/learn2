package com.emr.project.docOrder.mapper;

import com.emr.project.docOrder.domain.TdPaOrderOperationSign;
import java.util.List;

public interface TdPaOrderOperationSignMapper {
   TdPaOrderOperationSign selectTdPaOrderOperationSignById(Long id);

   List selectTdPaOrderOperationSignList(TdPaOrderOperationSign tdPaOrderOperationSign);

   int insertTdPaOrderOperationSign(TdPaOrderOperationSign tdPaOrderOperationSign);

   int updateTdPaOrderOperationSign(TdPaOrderOperationSign tdPaOrderOperationSign);

   int deleteTdPaOrderOperationSignById(Long id);

   int deleteTdPaOrderOperationSignByIds(Long[] ids);

   void insertList(List list);
}
