package com.emr.project.dr.service;

import com.emr.project.dr.domain.DrHandoverQuotElem;
import java.util.List;

public interface IDrHandoverQuotElemService {
   DrHandoverQuotElem selectDrHandoverQuotElemById(Long id);

   List selectDrHandoverQuotElemList(DrHandoverQuotElem drHandoverQuotElem);

   int insertDrHandoverQuotElem(DrHandoverQuotElem drHandoverQuotElem);

   int updateDrHandoverQuotElem(DrHandoverQuotElem drHandoverQuotElem);

   int deleteDrHandoverQuotElemByIds(Long[] ids);

   int deleteDrHandoverQuotElemById(Long id);

   List selectElemListByTypeCode(Long typeCode) throws Exception;
}
