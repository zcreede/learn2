package com.emr.project.dr.mapper;

import com.emr.project.dr.domain.DrHandoverQuotElem;
import java.util.List;

public interface DrHandoverQuotElemMapper {
   DrHandoverQuotElem selectDrHandoverQuotElemById(Long id);

   List selectDrHandoverQuotElemList(DrHandoverQuotElem drHandoverQuotElem);

   int insertDrHandoverQuotElem(DrHandoverQuotElem drHandoverQuotElem);

   int updateDrHandoverQuotElem(DrHandoverQuotElem drHandoverQuotElem);

   int deleteDrHandoverQuotElemById(Long id);

   int deleteDrHandoverQuotElemByIds(Long[] ids);

   List selectElemListByTypeCode(Long typeCode);
}
