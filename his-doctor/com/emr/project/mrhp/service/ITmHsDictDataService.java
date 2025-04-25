package com.emr.project.mrhp.service;

import com.emr.project.mrhp.domain.TmHsDictData;
import java.util.List;

public interface ITmHsDictDataService {
   TmHsDictData selectTmHsDictDataById(Long id);

   List selectTmHsDictDataList(TmHsDictData tmHsDictData);

   int insertTmHsDictData(TmHsDictData tmHsDictData);

   int updateTmHsDictData(TmHsDictData tmHsDictData);

   int deleteTmHsDictDataByIds(Long[] ids);

   int deleteTmHsDictDataById(Long id);

   void deleteByTypeList(List dictTypeList);

   void insertDataByTypeList(List typeList) throws Exception;
}
