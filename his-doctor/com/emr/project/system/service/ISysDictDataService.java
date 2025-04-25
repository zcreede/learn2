package com.emr.project.system.service;

import com.emr.project.system.domain.SysDictData;
import java.util.List;

public interface ISysDictDataService {
   List selectDictDataList(SysDictData dictData);

   List selectDictDataListByType(String[] dictType);

   String selectDictLabel(String dictType, String dictValue);

   SysDictData selectDictDataById(Long dictCode);

   void deleteDictDataByIds(Long[] dictCodes);

   int insertDictData(SysDictData dictData);

   int updateDictData(SysDictData dictData);

   List selectDictDataByType(String dictType);

   List selectDictDataByType(String dictType, String dictLabel);
}
