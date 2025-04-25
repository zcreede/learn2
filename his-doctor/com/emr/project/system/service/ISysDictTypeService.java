package com.emr.project.system.service;

import com.emr.project.system.domain.SysDictData;
import com.emr.project.system.domain.SysDictType;
import com.emr.project.system.domain.req.BsDictTypeReq;
import java.util.List;

public interface ISysDictTypeService {
   List selectDictTypeList(SysDictType dictType);

   List selectDictTypeAll();

   List selectDictDataByType(String dictType);

   List selectDictDataByTypeAndVal(SysDictData dictData);

   SysDictType selectDictTypeById(Long dictId);

   SysDictType selectDictTypeByType(String dictType);

   void deleteDictTypeByIds(Long[] dictIds);

   void loadingDictCache();

   void clearDictCache();

   void resetDictCache();

   int insertDictType(SysDictType dictType);

   int updateDictType(SysDictType dictType);

   String checkDictTypeUnique(SysDictType dictType);

   List selectBsDictDataByTypeAndSearch(BsDictTypeReq req);
}
