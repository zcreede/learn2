package com.emr.project.mrhp.service;

import com.emr.project.mrhp.domain.TmHsDictType;
import java.util.List;

public interface ITmHsDictTypeService {
   TmHsDictType selectTmHsDictTypeById(Long id);

   List selectTmHsDictTypeList(TmHsDictType tmHsDictType);

   int insertTmHsDictType(TmHsDictType tmHsDictType);

   int updateTmHsDictType(TmHsDictType tmHsDictType);

   int deleteTmHsDictTypeByIds(Long[] ids);

   int deleteTmHsDictTypeById(Long id);

   void insertData(List list) throws Exception;
}
