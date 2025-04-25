package com.emr.project.system.mapper;

import com.emr.project.system.domain.BasTmplDisease;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasTmplDiseaseMapper {
   BasTmplDisease selectBasTmplDiseaseById(Long id);

   List selectBasTmplDiseaseList(BasTmplDisease basTmplDisease);

   List basTmplDiseaseTreeSelect(BasTmplDisease basTmplDisease);

   int insertBasTmplDisease(BasTmplDisease basTmplDisease);

   int updateBasTmplDisease(BasTmplDisease basTmplDisease);

   int deleteBasTmplDiseaseById(Long id);

   int deleteBasTmplDiseaseByIds(Long[] ids);

   void deleteBasTmplDiseaseByMedicineIds(List ids);

   int selectBasTmplDiseaseIsUse(@Param("id") Long id);

   int getDiseaseIsExistCount(BasTmplDisease basTmplDisease);

   List selectListByMedicineIds(List ids);
}
