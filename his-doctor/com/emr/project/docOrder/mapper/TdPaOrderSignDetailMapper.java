package com.emr.project.docOrder.mapper;

import com.emr.project.docOrder.domain.TdPaOrderSignDetail;
import java.util.List;

public interface TdPaOrderSignDetailMapper {
   TdPaOrderSignDetail selectTdPaOrderSignDetailById(Long id);

   List selectTdPaOrderSignDetailList(TdPaOrderSignDetail tdPaOrderSignDetail);

   int insertTdPaOrderSignDetail(TdPaOrderSignDetail tdPaOrderSignDetail);

   int updateTdPaOrderSignDetail(TdPaOrderSignDetail tdPaOrderSignDetail);

   int deleteTdPaOrderSignDetailById(Long id);

   int deleteTdPaOrderSignDetailByIds(Long[] ids);

   void insertList(List signDetailList);
}
