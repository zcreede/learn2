package com.emr.project.esSearch.service;

import com.emr.project.docOrder.domain.vo.ItemIsOpenResVo;
import com.emr.project.esSearch.domain.req.FreezeQueryReq;
import com.emr.project.esSearch.domain.vo.DrugAndClinSearchVo;
import com.emr.project.his.domain.DrugCheck;
import com.emr.project.system.domain.SysUser;
import java.util.List;

public interface IDrugStockService {
   List selectDrugList(List execDeptCdList, String herbalFlag) throws Exception;

   List selectFreezeList(FreezeQueryReq req) throws Exception;

   List updateDrugDoseByOrderDose(SysUser sysUser, List drugDoseVoList, String subtractFlag, String ip) throws Exception;

   void toMsgDeleteFreezeAddStock(List delFreezeList) throws Exception;

   ItemIsOpenResVo antiUseAimIsOpen(SysUser sysUser, String cpNo) throws Exception;

   List selectDrugCheckList(SysUser sysUser, List drugCdList) throws Exception;

   List selectHisDrugStockList(List drugCodeList, String execDeptCd) throws Exception;

   List selectHisDrugStockByStockList(String execDeptCd, List list) throws Exception;

   DrugCheck antiUseAimIsOpenType(SysUser sysUser, String drugCd) throws Exception;

   List selectPharmacyListByDept(String orgCd, String deptCode) throws Exception;

   void batchInsertFreezes(List list) throws Exception;

   void delFreezesByOrderNo(String orderNo) throws Exception;

   List selectNewOrderFreezeList(String orderNo) throws Exception;

   List selectDrugListForOperation(DrugAndClinSearchVo drugAndClinSearchVo) throws Exception;

   List selectDrugsCostType(String orgCd, List drugCdList) throws Exception;

   List selectFreezeListByTakeDrugListIds(List takeDrugListIds) throws Exception;

   void updateFreezeOrderNoByRearrange(List freezeListUpdate) throws Exception;
}
