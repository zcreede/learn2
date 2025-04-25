package com.emr.project.docOrder.service;

import com.emr.project.docOrder.domain.TdPaOrderSignDetail;
import java.util.List;

public interface ITdPaOrderSignDetailService {
   TdPaOrderSignDetail selectTdPaOrderSignDetailById(Long id);

   List selectTdPaOrderSignDetailList(TdPaOrderSignDetail tdPaOrderSignDetail);

   int insertTdPaOrderSignDetail(TdPaOrderSignDetail tdPaOrderSignDetail);

   int updateTdPaOrderSignDetail(TdPaOrderSignDetail tdPaOrderSignDetail);

   int deleteTdPaOrderSignDetailByIds(Long[] ids);

   int deleteTdPaOrderSignDetailById(Long id);

   void insertList(List signDetailList) throws Exception;
}
