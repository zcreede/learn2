package com.emr.project.docOrder.service;

import com.emr.project.docOrder.domain.TdPaOrderSignMain;
import com.emr.project.docOrder.domain.req.OrderSignTextReq;
import com.emr.project.docOrder.domain.vo.OrderCommitVo;
import com.emr.project.docOrder.domain.vo.OrderStopSignVo;
import com.emr.project.pat.domain.Visitinfo;
import java.util.List;

public interface ITdPaOrderSignMainService {
   TdPaOrderSignMain selectTdPaOrderSignMainById(Long id);

   List selectTdPaOrderSignMainList(TdPaOrderSignMain tdPaOrderSignMain);

   int insertTdPaOrderSignMain(TdPaOrderSignMain tdPaOrderSignMain);

   int updateTdPaOrderSignMain(TdPaOrderSignMain tdPaOrderSignMain);

   int deleteTdPaOrderSignMainByIds(Long[] ids);

   int deleteTdPaOrderSignMainById(Long id);

   void addTdPaOrderOperationSign(List orderItemList, OrderCommitVo orderCommitVo, Visitinfo visitinfo, Integer operationType, String operationTypeName) throws Exception;

   void addTdPaOrderOperationSign(List orderItemList, OrderStopSignVo orderStopSignVo, Visitinfo visitinfo, Integer operationType, String operationTypeName) throws Exception;

   List selectOrderSignTextList(List orderNoList) throws Exception;

   List selectSignPicByOrderNos(List orderNoList, List operationTypeList) throws Exception;

   List selectStaffSignPicAll() throws Exception;

   List queryOrderSignText(OrderSignTextReq req) throws Exception;

   void addTdPaOrderOperationSignText(List orderSignCommitVoList, String signType) throws Exception;
}
