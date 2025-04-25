package com.emr.project.his.service;

import com.emr.project.mrhp.domain.vo.MrHpVo;
import java.util.List;
import java.util.Map;

public interface IHisSyncService {
   List selectDeptALL();

   void deleteHisMrHpVo(String inpNo) throws Exception;

   void deleteHisMrHpZYDia(String inpNo) throws Exception;

   void deleteHisMrHpXYDia(String inpNo) throws Exception;

   void deleteHisMrHpOpe(String inpNo) throws Exception;

   void deleteHisMrHpFee(String inpNo) throws Exception;

   void insertHisMrHpVo(MrHpVo mrHpVo) throws Exception;

   void insertHisMrHpZYDia(List diaList) throws Exception;

   void insertHisMrHpXYDia(List diaList) throws Exception;

   void insertHisMrHpOpe(List opeList) throws Exception;

   void insertHisMrHpFee(List feeList) throws Exception;

   Map selectDeptByCode(String appDeptCd);

   Map selectStaffByCode(String appDocCd);
}
