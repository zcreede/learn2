package com.emr.project.tmpl.service;

import com.emr.project.tmpl.domain.TmplQuoteElem;
import java.util.List;

public interface ITmplQuoteElemService {
   TmplQuoteElem selectTmplQuoteElemById(Long id);

   List selectTmplQuoteElemList(TmplQuoteElem tmplQuoteElem);

   int insertTmplQuoteElem(TmplQuoteElem tmplQuoteElem);

   void insertTmplQuoteElemList(List tmplQuoteElemList) throws Exception;

   void insertTmplQuoteElemStandardList(List tmplQuoteElemList) throws Exception;

   int updateTmplQuoteElem(TmplQuoteElem tmplQuoteElem);

   int deleteTmplQuoteElemByIds(Long[] ids);

   int deleteTmplQuoteElemById(Long id);

   int deleteTmplQuoteElemByTempId(Long tempId);

   int deleteTmplQuoteElemStandardByTempId(Long tempId);

   List selectTmplQuoteElemListByTempId(Long tempId);
}
