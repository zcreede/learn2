package com.emr.project.system.service;

import com.emr.project.system.domain.BasDictMedicine;
import com.emr.project.system.domain.vo.BasDictMedicineVo;
import java.util.List;

public interface IBasDictMedicineService {
   BasDictMedicineVo selectBasDictMedicineById(Long id);

   BasDictMedicine selectBasDictMedicineByName(String name);

   List selectBasDictMedicineTree(BasDictMedicine basDictMedicine) throws Exception;

   List selectBasDictMedicineTreeByDept(BasDictMedicine basDictMedicine) throws Exception;

   List selectBasDictMedicineList(BasDictMedicine basDictMedicine);

   BasDictMedicine insertBasDictMedicine(BasDictMedicineVo basDictMedicine) throws Exception;

   void updateBasDictMedicine(BasDictMedicineVo basDictMedicine) throws Exception;

   int deleteBasDictMedicineByIds(Long[] ids);

   int deleteBasDictMedicineById(Long id);

   List selectListByIds(List list) throws Exception;

   List selectListByCodes(List list) throws Exception;
}
