package com.emr.project.system.mapper;

import com.emr.project.system.domain.BasDictMedicine;
import java.util.List;

public interface BasDictMedicineMapper {
   BasDictMedicine selectBasDictMedicineById(Long id);

   BasDictMedicine selectBasDictMedicineByName(String name);

   List selectBasDictMedicineList(BasDictMedicine basDictMedicine);

   int insertBasDictMedicine(BasDictMedicine basDictMedicine);

   int updateBasDictMedicine(BasDictMedicine basDictMedicine);

   int deleteBasDictMedicineById(Long id);

   void deleteBasDictMedicineByParentCode(String parentCode);

   int deleteBasDictMedicineByIds(Long[] ids);

   List basDictMedicineTreeSelect(BasDictMedicine basDictMedicine);

   List selectListByIds(List list);

   List selectListByCodes(List list);
}
