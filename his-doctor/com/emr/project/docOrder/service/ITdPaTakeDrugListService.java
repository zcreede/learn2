package com.emr.project.docOrder.service;

import com.emr.project.docOrder.domain.vo.TdPaTakeDrugListVO;
import com.emr.project.operation.domain.resp.PrintDrugListResp;
import com.emr.project.operation.domain.vo.TdPaTakeDrugListPageVo;
import java.util.List;

public interface ITdPaTakeDrugListService {
   List selectTakeDrugPatList(TdPaTakeDrugListVO tdPaTakeDrugListVO) throws Exception;

   List selectTakeDetailList(TdPaTakeDrugListVO tdPaTakeDrugListVO) throws Exception;

   List updateTakeDrugList(List takeDrugListVOList, String vaild) throws Exception;

   void updateHisYfkc(List hisYfkcHzs) throws Exception;

   void insertList(List list) throws Exception;

   void moveToDeleteTable(List idList) throws Exception;

   void deleteByIds(List idList) throws Exception;

   void cancelMoveToDeleteTable(List idList) throws Exception;

   void deleteDelTableByIds(List idList) throws Exception;

   List printTakeDetailList(TdPaTakeDrugListVO tdPaTakeDrugListVO) throws Exception;

   List saveDrug(List list) throws Exception;

   List selectTakeListForApply(TdPaTakeDrugListPageVo req) throws Exception;

   List selectTakeDrugDetailList(TdPaTakeDrugListPageVo req, List listAll, Integer pageSize, Integer pageNum);

   List selectDrugListStatusByIds(List idList);

   PrintDrugListResp sendToPharmacy(List params) throws Exception;

   List toVoidTakeDrugLists(List list) throws Exception;

   String verifyOrderCancelTakeDrugList(List orderList);
}
