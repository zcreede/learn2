package com.emr.project.dr.mapper;

import com.emr.project.dr.domain.DrHandoverScheme;
import com.emr.project.dr.domain.vo.DrHandoverSchemeVo;
import java.util.List;

public interface DrHandoverSchemeMapper {
   DrHandoverScheme selectDrHandoverSchemeById(Long schemeCd);

   List selectDrHandoverSchemeList(DrHandoverScheme drHandoverScheme);

   List selectDrHandoverSchemeVoList(DrHandoverSchemeVo drHandoverSchemeVo);

   int insertDrHandoverScheme(DrHandoverScheme drHandoverScheme);

   int updateDrHandoverScheme(DrHandoverScheme drHandoverScheme);

   int deleteDrHandoverSchemeById(Long schemeCd);

   int deleteDrHandoverSchemeByIds(Long[] schemeCds);

   List selectListByDept(DrHandoverSchemeVo drHandoverSchemeVo);
}
