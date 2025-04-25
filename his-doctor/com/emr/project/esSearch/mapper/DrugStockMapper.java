package com.emr.project.esSearch.mapper;

import com.emr.project.esSearch.domain.DrugAndClin;
import com.emr.project.esSearch.domain.TmPaDrugPre;
import com.emr.project.esSearch.domain.req.FreezeQueryReq;
import com.emr.project.esSearch.domain.vo.DrugAndClinSearchVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DrugStockMapper {
   List selectDrugList(@Param("execDeptCdList") List execDeptCdList, @Param("herbalFlag") String herbalFlag);

   List selectDrugListByDeptDrug(@Param("reqList") List reqList, @Param("allFlag") String allFlag);

   DrugAndClin selectDrugDictByCd(@Param("orgCd") String orgCd, @Param("drugCd") String drugCd);

   List selectDrugDictByCds(@Param("orgCd") String orgCd, @Param("drugCdList") List drugCdList);

   List selectHisDrugStockList(@Param("drugCodeList") List drugCodeList, @Param("execDeptCd") String execDeptCd);

   List selectHisDrugStockByStockList(@Param("execDeptCd") String execDeptCd, @Param("list") List list);

   List selectDrugListForOperation(DrugAndClinSearchVo drugAndClinSearchVo);

   TmPaDrugPre selectDrugPreByCd(@Param("orgCd") String orgCd, @Param("preCd") String preCd);

   List selectDrugPreDetailListByCd(@Param("orgCd") String orgCd, @Param("preCd") String preCd);

   List selectPharmacyListByDept(@Param("orgCd") String orgCd, @Param("deptCode") String deptCode);

   List selectPharmacyListByDeptByFlag(@Param("orgCd") String orgCd, @Param("deptCode") String deptCode);

   int batchInsert(@Param("tmPaFreezeList") List tmPaFreezeList);

   List selectFreezeList(FreezeQueryReq req);

   List selectCheckOrderFirst(@Param("list") List cpNoList);

   void delFreezesByOrderNo(@Param("orderNo") String orderNo);

   List selectNewOrderFreezeList(@Param("orderNo") String orderNo);

   List selectFreezeListByTakeDrugListIds(@Param("takeDrugListIds") List takeDrugListIds);

   List selectDrugsCostType(@Param("orgCd") String orgCd, @Param("drugCdList") List drugCdList);

   void updateFreezeOrderNoByRearrange(@Param("list") List freezeListUpdate);
}
