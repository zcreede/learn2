package com.emr.project.docOrder.mapper;

import com.emr.project.docOrder.domain.vo.TdPaTakeDrugListVO;
import com.emr.project.operation.domain.vo.TdPaTakeDrugListPageVo;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.ibatis.annotations.Param;

public interface TdPaTakeDrugListMapper {
   List selectTakeDrugPatList(TdPaTakeDrugListVO tdPaTakeDrugListVO);

   List selectTakeDrugFYPatList(TdPaTakeDrugListVO tdPaTakeDrugListVO);

   List selectTakeDrugDetailList(TdPaTakeDrugListVO tdPaTakeDrugListVO);

   List selectTakeDrugFYDetailList(TdPaTakeDrugListVO tdPaTakeDrugListVO);

   void updateTakeDrugList(@Param("takeDrugListVOList") List takeDrugListVOList, @Param("vaild") String vaild);

   void updateHisYfkc(List list);

   List selectTakeDrugFYDeptPatList(TdPaTakeDrugListVO takeDrugListVO);

   void insertList(List list);

   void moveToDeleteTable(List idList);

   void deleteByIds(List idList);

   void cancelMoveToDeleteTable(List idList);

   void deleteDelTableByIds(List idList);

   List selectInfoByAdmissionNoList(@Param("list") Set admissionNoList);

   List selectTakeDrugList(TdPaTakeDrugListPageVo tdPaTakeDrugListPageVo);

   List selectDrugListStatusByIds(@Param("idList") List idList);

   void updateTakeDrugStatusApply(@Param("takeDrugTime") Date takeDrugTime, @Param("takeDrugStatus") Integer takeDrugStatus, @Param("oldTakeDrugStatus") Integer oldTakeDrugStatus, @Param("applyNo") String applyNo, @Param("takeDrugOperator") String takeDrugOperator, List list);

   List selectPrintDataByIds(@Param("applyNo") List applyNo);

   void moveToDeleteTableByIds(@Param("idList") List idList);

   void deleteListTableByIds(@Param("idList") List idList);

   void updateStatusByIds(@Param("idList") List idList, @Param("staffCode") String staffCode);

   List selectTakeDrugListByType(@Param("type") String type, @Param("revokeId") List revokeId);

   List selectOrderTakeDrugLists(@Param("orderNoList") List orderList);

   Integer selectMaxGroupNo(@Param("admissionNo") String admissionNo);
}
