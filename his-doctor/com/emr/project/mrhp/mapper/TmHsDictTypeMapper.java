package com.emr.project.mrhp.mapper;

import com.emr.project.mrhp.domain.TmHsDictType;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TmHsDictTypeMapper {
   TmHsDictType selectTmHsDictTypeById(Long id);

   List selectTmHsDictTypeList(TmHsDictType tmHsDictType);

   int insertTmHsDictType(TmHsDictType tmHsDictType);

   int updateTmHsDictType(TmHsDictType tmHsDictType);

   int deleteTmHsDictTypeById(Long id);

   int deleteTmHsDictTypeByIds(Long[] ids);

   void deleteByTypeList(@Param("list") List list);

   void insertList(@Param("list") List list);
}
