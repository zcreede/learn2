package com.emr.project.dr.mapper;

import com.emr.project.dr.domain.DrHandoverMain;
import com.emr.project.dr.domain.vo.DrHandoverMainVo;
import java.util.List;

public interface DrHandoverMainMapper {
   DrHandoverMain selectDrHandoverMainById(Long id);

   List selectDrHandoverMainList(DrHandoverMainVo drHandoverMainVo);

   int insertDrHandoverMain(DrHandoverMain drHandoverMain);

   int updateDrHandoverMain(DrHandoverMain drHandoverMain);

   int deleteDrHandoverMainById(Long id);

   int deleteDrHandoverMain(DrHandoverMain drHandoverMain);

   int deleteDrHandoverMainByIds(Long[] ids);

   DrHandoverMain selectHandoverByInfo(DrHandoverMain drHandoverMain);

   void updateState(DrHandoverMain drHandoverMain);

   DrHandoverMainVo selectDeptLastDrHandoverMain(DrHandoverMain drHandoverMain);

   List selectDeptNoDrHandoverMain(DrHandoverMain drHandoverMain);

   List selectRecentRecordList(DrHandoverMain drHandoverMain);

   Integer checkCancel(DrHandoverMain drHandoverMain);
}
