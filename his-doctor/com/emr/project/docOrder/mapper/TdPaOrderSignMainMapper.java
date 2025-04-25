package com.emr.project.docOrder.mapper;

import com.emr.project.docOrder.domain.TdPaOrderSignMain;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TdPaOrderSignMainMapper {
   TdPaOrderSignMain selectTdPaOrderSignMainById(Long id);

   List selectTdPaOrderSignMainList(TdPaOrderSignMain tdPaOrderSignMain);

   int insertTdPaOrderSignMain(TdPaOrderSignMain tdPaOrderSignMain);

   int updateTdPaOrderSignMain(TdPaOrderSignMain tdPaOrderSignMain);

   int deleteTdPaOrderSignMainById(Long id);

   int deleteTdPaOrderSignMainByIds(Long[] ids);

   List selectOrderSignTextList(List orderNoList);

   List selectSignPicByOrderNos(List orderNoList, List operationTypeList);

   List selectStaffSignPicAll();

   List selectOrderItemList(@Param("list") List orderNoList);

   void insertList(List signMainList);
}
