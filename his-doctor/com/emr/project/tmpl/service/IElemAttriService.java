package com.emr.project.tmpl.service;

import com.emr.project.tmpl.domain.ElemAttri;
import java.util.List;

public interface IElemAttriService {
   ElemAttri selectElemAttriById(Long id);

   List selectElemAttriList(ElemAttri elemAttri);

   List selectElemAttriStandardList(ElemAttri elemAttri);

   int insertElemAttri(ElemAttri elemAttri);

   void insertElemAttriList(List elemAttriList) throws Exception;

   void insertElemAttriStandardList(List elemAttriList) throws Exception;

   int updateElemAttri(ElemAttri elemAttri);

   int deleteElemAttriByIds(Long[] ids);

   int deleteElemAttriById(Long id);

   int deleteElemAttriByTempId(Long tempId);

   int deleteElemAttriStandardByTempId(Long tempId);

   List selectElemAttriByTempId(Long tempId);

   List selectElemAttriByTempIdAndContElemName(List paramList) throws Exception;
}
