package com.emr.project.tmpm.service;

import com.emr.project.tmpm.domain.ClinItemCollection;
import java.util.List;

public interface IClinItemCollectionService {
   ClinItemCollection selectClinItemCollectionById(Long id);

   List selectClinItemCollectionList(ClinItemCollection clinItemCollection);

   void insertClinItemCollection(ClinItemCollection clinItemCollection) throws Exception;

   int updateClinItemCollection(ClinItemCollection clinItemCollection);

   int deleteClinItemCollectionByIds(Long[] ids);

   int deleteClinItemCollectionById(Long id);

   List selectCommonDrugList(ClinItemCollection clinItemCollection) throws Exception;

   List selectSetItemList(ClinItemCollection clinItemCollection) throws Exception;

   List selectHerbList(ClinItemCollection clinItemCollection) throws Exception;

   List selectRecipeList(ClinItemCollection clinItemCollection) throws Exception;

   List selectUnDrugItemList(ClinItemCollection clinItemCollection) throws Exception;

   void deleteClinItemCollectionByItemCd(ClinItemCollection clinItemCollection) throws Exception;
}
