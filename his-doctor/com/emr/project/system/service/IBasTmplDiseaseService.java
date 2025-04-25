package com.emr.project.system.service;

import com.emr.project.system.domain.BasTmplDisease;
import java.util.List;

public interface IBasTmplDiseaseService {
   BasTmplDisease selectBasTmplDiseaseById(Long id);

   List selectBasTmplDiseaseList(BasTmplDisease basTmplDisease);

   List selectDiseaseList(BasTmplDisease basTmplDisease) throws Exception;

   int insertBasTmplDisease(BasTmplDisease basTmplDisease);

   int updateBasTmplDisease(BasTmplDisease basTmplDisease);

   int deleteBasTmplDiseaseByIds(Long[] ids);

   int deleteBasTmplDiseaseById(Long id);

   void deleteBasTmplDiseaseByMedicineIds(List ids);

   int updateBasTmplDiseaseValidFlag(BasTmplDisease param);

   List selectListByMedicineIds(List ids) throws Exception;
}
