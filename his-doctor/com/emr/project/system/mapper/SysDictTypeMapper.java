package com.emr.project.system.mapper;

import com.emr.project.system.domain.SysDictType;
import com.emr.project.system.domain.req.BsDictTypeReq;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysDictTypeMapper {
   List selectDictTypeList(SysDictType dictType);

   List selectDictTypeAll();

   SysDictType selectDictTypeById(Long dictId);

   SysDictType selectDictTypeByType(String dictType);

   int deleteDictTypeById(Long dictId);

   int deleteDictTypeByIds(Long[] dictIds);

   int insertDictType(SysDictType dictType);

   int updateDictType(SysDictType dictType);

   SysDictType checkDictTypeUnique(String dictType);

   List selectBsDictDataByTypeAndSearch(BsDictTypeReq req);
}
