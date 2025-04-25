package com.emr.project.mrhp.mapper;

import com.emr.project.mrhp.domain.TmHsDictData;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TmHsDictDataMapper {
   TmHsDictData selectTmHsDictDataById(Long id);

   List selectTmHsDictDataList(TmHsDictData tmHsDictData);

   int insertTmHsDictData(TmHsDictData tmHsDictData);

   int updateTmHsDictData(TmHsDictData tmHsDictData);

   int deleteTmHsDictDataById(Long id);

   int deleteTmHsDictDataByIds(Long[] ids);

   void deleteByTypeList(@Param("list") List list);

   List selectDataByTypeList(@Param("list") List list);

   void insertAllList(@Param("list") List list);
}
