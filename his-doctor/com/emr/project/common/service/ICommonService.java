package com.emr.project.common.service;

import com.emr.project.common.domain.vo.ReplaceUrlParamVo;
import com.emr.project.operation.domain.Baseinfomation;
import com.emr.project.pat.domain.Visitinfo;
import com.emr.project.qc.domain.EmrQcStatusMes;
import com.emr.project.sys.domain.TmBsDefineConfigureP;
import com.emr.project.system.domain.SysUser;
import java.util.Date;
import java.util.List;

public interface ICommonService {
   Date getDbSysdate() throws Exception;

   String getSystimestamp() throws Exception;

   String replaceUrlParam(ReplaceUrlParamVo replaceUrlParamVo, String urlStr) throws Exception;

   TmBsDefineConfigureP selectDefineConfigureByCode(String code) throws Exception;

   Boolean isQCDept() throws Exception;

   Date queryFileDate(Date leaveHospitalDate, int days) throws Exception;

   boolean dayIsHoliday(Date day) throws Exception;

   boolean timeIsWorkingTime(Date time) throws Exception;

   boolean dateIsWorkingTime(Date time) throws Exception;

   EmrQcStatusMes queryEmrQcStatus(String patientId) throws Exception;

   List nursingLevelList() throws Exception;

   Baseinfomation findBaseInfo(String patientId) throws Exception;

   List findBaseInfoByIdcard(String idcard) throws Exception;

   Boolean userHasPat(Visitinfo visitinfo, SysUser user) throws Exception;
}
