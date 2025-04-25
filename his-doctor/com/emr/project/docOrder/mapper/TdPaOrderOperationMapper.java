package com.emr.project.docOrder.mapper;

import com.emr.project.docOrder.domain.TdPaOrderOperation;
import java.util.List;

public interface TdPaOrderOperationMapper {
   TdPaOrderOperation selectTdPaOrderOperationById(String orderNo);

   List selectTdPaOrderOperationList(TdPaOrderOperation tdPaOrderOperation);

   int insertTdPaOrderOperation(TdPaOrderOperation tdPaOrderOperation);

   int updateTdPaOrderOperation(TdPaOrderOperation tdPaOrderOperation);

   int deleteTdPaOrderOperationById(String orderNo);

   int deleteTdPaOrderOperationByIds(String[] orderNos);
}
