package com.emr.project.tmpl.mapper;

import com.emr.project.tmpl.domain.ElemAttri;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ElemAttriMapper {
   ElemAttri selectElemAttriById(Long id);

   List selectElemAttriList(ElemAttri elemAttri);

   List selectElemAttriStandardList(ElemAttri elemAttri);

   int insertElemAttri(ElemAttri elemAttri);

   void insertElemAttriList(List elemAttriList);

   void insertElemAttriStandardList(List elemAttriList);

   int updateElemAttri(ElemAttri elemAttri);

   int deleteElemAttriById(Long id);

   int deleteElemAttriByIds(Long[] ids);

   int deleteElemAttriByTempId(Long tempId);

   int deleteElemAttriStandardByTempId(Long tempId);

   List selectElemAttriByTempId(@Param("tempId") Long tempId);

   List selectElemAttriByTempIdAndContElemName(List paramList);
}
