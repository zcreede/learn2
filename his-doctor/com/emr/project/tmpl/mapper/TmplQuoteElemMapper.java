package com.emr.project.tmpl.mapper;

import com.emr.project.tmpl.domain.TmplQuoteElem;
import java.util.List;

public interface TmplQuoteElemMapper {
   TmplQuoteElem selectTmplQuoteElemById(Long id);

   List selectTmplQuoteElemList(TmplQuoteElem tmplQuoteElem);

   int insertTmplQuoteElem(TmplQuoteElem tmplQuoteElem);

   void insertTmplQuoteElemList(List tmplQuoteElemList);

   void insertTmplQuoteElemStandardList(List tmplQuoteElemList);

   int updateTmplQuoteElem(TmplQuoteElem tmplQuoteElem);

   int deleteTmplQuoteElemById(Long id);

   int deleteTmplQuoteElemByIds(Long[] ids);

   int deleteTmplQuoteElemByTempId(Long tempId);

   int deleteTmplQuoteElemStandardByTempId(Long tempId);

   List selectTmplQuoteElemListByTempId(Long tempId);
}
