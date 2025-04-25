package com.emr.project.docOrder.service;

import com.emr.project.docOrder.domain.TdPaOrderOperationSign;
import java.util.List;

public interface ITdPaOrderOperationSignService {
   TdPaOrderOperationSign selectTdPaOrderOperationSignById(Long id);

   List selectTdPaOrderOperationSignList(TdPaOrderOperationSign tdPaOrderOperationSign);

   int insertTdPaOrderOperationSign(TdPaOrderOperationSign tdPaOrderOperationSign);

   int updateTdPaOrderOperationSign(TdPaOrderOperationSign tdPaOrderOperationSign);

   int deleteTdPaOrderOperationSignByIds(Long[] ids);

   int deleteTdPaOrderOperationSignById(Long id);
}
