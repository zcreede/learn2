package com.emr.project.pat.mapper;

import com.emr.project.pat.domain.Transinfo;
import com.emr.project.system.domain.vo.SqlVo;
import java.util.List;
import java.util.Map;

public interface TransinfoMapper {
   Transinfo selectTransinfoById(Long trId);

   List selectTransinfoList(Transinfo transinfo);

   int insertTransinfo(Transinfo transinfo);

   int updateTransinfo(Transinfo transinfo);

   int deleteTransinfoById(Long trId);

   int deleteTransinfoByIds(Long[] trIds);

   List selectTransinfoByPatientId(String patientId);

   List selectTransinfosByPatientId(String patientId);

   void insertMap(Map map);

   Integer selectTodayTrunInNum(String deptCode);

   Integer selectTodayInhosNum(String deptCode);

   Integer selectTodayTurnOutNum(String deptCode);

   void deleteTransinfo();

   List selectHisTransInfo(SqlVo sqlVo);

   void deleteTransinfoByInpNoList(List inpNoList);
}
