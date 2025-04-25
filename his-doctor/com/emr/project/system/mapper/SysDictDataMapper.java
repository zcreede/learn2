package com.emr.project.system.mapper;

import com.emr.project.system.domain.SysDictData;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysDictDataMapper {
   List selectDictDataList(SysDictData dictData);

   List selectDictDataByType(String dictType);

   List selectDictDataListByType(String[] dictType);

   String selectDictLabel(@Param("dictType") String dictType, @Param("dictValue") String dictValue);

   SysDictData selectDictDataById(Long dictCode);

   int countDictDataByType(String dictType);

   int deleteDictDataById(Long dictCode);

   int deleteDictDataByIds(Long[] dictCodes);

   int insertDictData(SysDictData dictData);

   int updateDictData(SysDictData dictData);

   int updateDictDataType(@Param("oldDictType") String oldDictType, @Param("newDictType") String newDictType);
}
