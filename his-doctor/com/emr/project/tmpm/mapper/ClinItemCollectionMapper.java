package com.emr.project.tmpm.mapper;

import com.emr.project.tmpm.domain.ClinItemCollection;
import java.util.List;

public interface ClinItemCollectionMapper {
   ClinItemCollection selectClinItemCollectionById(Long id);

   List selectClinItemCollectionList(ClinItemCollection clinItemCollection);

   int insertClinItemCollection(ClinItemCollection clinItemCollection);

   int updateClinItemCollection(ClinItemCollection clinItemCollection);

   int deleteClinItemCollectionById(Long id);

   int deleteClinItemCollectionByIds(Long[] ids);

   List selectCommonDrugList(ClinItemCollection clinItemCollection);

   List selectUnDrugItemList(ClinItemCollection clinItemCollection);

   void deleteClinItemCollectionByItemCd(ClinItemCollection clinItemCollection);
}
