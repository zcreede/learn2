package com.emr.project.esSearch.service;

import com.alibaba.fastjson.JSONArray;
import com.emr.project.esSearch.domain.DrugAndClin;
import com.emr.project.esSearch.domain.vo.DrugAndClinSearchVo;
import com.emr.project.system.domain.SysUser;
import java.util.List;
import org.zxp.esclientrhl.repository.PageList;

public interface IDrugAndClinService {
   void createDrugAndClinIndex(String indexName) throws Exception;

   void dropDrugAndClinIndex(String indexName) throws Exception;

   void dropDrugAndClinIndex() throws Exception;

   boolean syncDrugAndClinAllToEs(String indexName, List execDeptCd, String herbalFlag, String expenseCategoryNo, String asyncFlag) throws Exception;

   boolean syncDrugAndClinAllToEs(List execDeptCd, String herbalFlag, String expenseCategoryNo, String asyncFlag) throws Exception;

   void syncDrugAndClinAllToEsLogin(SysUser user) throws Exception;

   void syncDrugAndClinAllToEsReload(List execDeptCd, String expenseCategoryNo) throws Exception;

   PageList queryDrugAndClinPageList(DrugAndClinSearchVo param, int currentPage, int pageSize) throws Exception;

   PageList selectQueryCheckList(DrugAndClinSearchVo param, int currentPage, int pageSize) throws Exception;

   void updateDurgXcsl(List drugDoseVoList) throws Exception;

   DrugAndClin selectDrugInfoByCd(DrugAndClin drugAndClin) throws Exception;

   JSONArray drugInfoByIds(List orderSearchVoList) throws Exception;

   void addDrugAndClin(List list) throws Exception;

   String getSetting(String index, String setting) throws Exception;
}
