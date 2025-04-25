package com.emr.project.dr.service;

import com.emr.project.dr.domain.DrHandoverScheme;
import com.emr.project.dr.domain.vo.DrHandoverSchemeVo;
import java.util.Date;
import java.util.List;

public interface IDrHandoverSchemeService {
   DrHandoverScheme selectDrHandoverSchemeById(Long schemeCd);

   List selectDrHandoverSchemeList(DrHandoverScheme drHandoverScheme);

   List selectDrHandoverSchemeVoList(DrHandoverSchemeVo drHandoverSchemeVo) throws Exception;

   void insertDrHandoverScheme(DrHandoverSchemeVo drHandoverSchemeVo) throws Exception;

   void updateDrHandoverScheme(DrHandoverSchemeVo drHandoverSchemeVo) throws Exception;

   int deleteDrHandoverSchemeByIds(Long[] schemeCds) throws Exception;

   int deleteDrHandoverSchemeById(Long schemeCd);

   List selectListByCurrDept(String deptCd) throws Exception;

   DrHandoverScheme getCurrHandoverScheme(String deptCd, Date currDate) throws Exception;

   String getschemeNameStr(DrHandoverScheme drHandoverScheme) throws Exception;
}
