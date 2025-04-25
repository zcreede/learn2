package com.emr.project.his.mapper;

import com.emr.project.docOrder.domain.vo.OpeAuthorityVo;
import com.emr.project.mrhp.domain.vo.MrHpVo;
import com.emr.project.system.domain.vo.SqlVo;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface HisSyncMapper {
   List selectDeptAll();

   List selectPharmacyListByDept(SqlVo sqlVo);

   OpeAuthorityVo selectDocOpeAuth(String docCd, String operLevel);

   void deleteHisMrHpVo(String inpNo);

   void deleteHisMrHpZYDia(String inpNo);

   void deleteHisMrHpXYDia(String inpNo);

   void deleteHisMrHpOpe(String inpNo);

   void deleteHisMrHpFee(String inpNo);

   void insertHisMrHpVo(MrHpVo mrHpVo);

   void insertHisMrHpZYDia(List diaList);

   void insertHisMrHpXYDia(List diaList);

   void insertHisMrHpOpe(List opeList);

   void insertHisMrHpFee(List feeList);

   Map selectDeptByCode(@Param("appDeptCd") String appDeptCd);

   Map selectDocByCode(@Param("appDocCd") String appDocCd);
}
