package com.emr.project.system.mapper;

import com.emr.project.system.domain.DictStandard;
import java.util.List;

public interface DictStandardMapper {
   List findDictStandard(String typeCode);

   List selectListByConn(DictStandard param);

   List searchDictStand(List arr);

   void save(DictStandard param);

   List selectDictDataByType(String dictType);
}
