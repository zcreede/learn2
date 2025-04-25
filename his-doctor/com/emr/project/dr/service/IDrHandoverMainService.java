package com.emr.project.dr.service;

import com.emr.project.dr.domain.DrHandoverMain;
import com.emr.project.dr.domain.vo.DrHandoverMainVo;
import java.util.Date;
import java.util.List;

public interface IDrHandoverMainService {
   DrHandoverMain selectDrHandoverMainById(Long id);

   List selectDrHandoverMainList(DrHandoverMainVo drHandoverMainVo) throws Exception;

   int insertDrHandoverMain(DrHandoverMain drHandoverMain) throws Exception;

   int updateDrHandoverMain(DrHandoverMain drHandoverMain) throws Exception;

   int deleteDrHandoverMainByIds(Long[] ids) throws Exception;

   int deleteDrHandoverMainById(Long id) throws Exception;

   DrHandoverMainVo selectHandoverByInfo(String deptCd, Date handoverDate, Long schemeCd) throws Exception;

   void updateState(DrHandoverMain drHandoverMain) throws Exception;

   DrHandoverMainVo selectLastDrHandoverMain(String deptCd) throws Exception;

   List selectNoDrHandoverMain(String deptCd) throws Exception;

   List selectRecentRecordList(DrHandoverMain drHandoverMain) throws Exception;

   List selectDoverMainRecordList(DrHandoverMainVo drHandoverMainVo) throws Exception;

   Boolean checkCancel(DrHandoverMain drHandoverMain);

   List selectDrHandoverMainBySchemeCd(Long schemeCd, String deptCd, Date handoverDate);
}
