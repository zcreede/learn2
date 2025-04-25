package com.emr.project.pat.service;

import com.emr.project.pat.domain.Transinfo;
import com.emr.project.pat.domain.vo.TransinfoVo;
import com.emr.project.system.domain.SysDept;
import java.util.List;

public interface ITransinfoService {
   Transinfo selectTransinfoById(Long trId);

   List selectTransinfoList(Transinfo transinfo);

   int insertTransinfo(Transinfo transinfo);

   int updateTransinfo(Transinfo transinfo);

   int deleteTransinfoByIds(Long[] trIds);

   int deleteTransinfoById(Long trId);

   List selectTransinfoByPatientId(String patientId) throws Exception;

   SysDept selectDeptByDate(TransinfoVo transinfoVo) throws Exception;

   List selectDetpDate(String patientId) throws Exception;

   List syncHisTransInfoLisT(List inpNoList) throws Exception;

   void insertTranInfo(List list) throws Exception;

   void deleteTransinfoByInpNoList(List inpNoList) throws Exception;
}
