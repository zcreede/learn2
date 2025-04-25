package com.emr.project.sys.service;

import com.emr.project.sys.domain.QuoteElem;
import com.emr.project.sys.domain.vo.QuoteElemSaveVo;
import com.emr.project.sys.domain.vo.QuoteElemTypeNumVo;
import java.util.List;

public interface IQuoteElemService {
   QuoteElem selectQuoteElemById(Long id) throws Exception;

   List selectQuoteElemList(QuoteElem quoteElem) throws Exception;

   List selectQuoteElemStandardList(QuoteElem quoteElem) throws Exception;

   int insertQuoteElem(QuoteElem quoteElem) throws Exception;

   void insertQuoteElemList(List list) throws Exception;

   int updateQuoteElem(QuoteElem quoteElem) throws Exception;

   void updateQuoteElemList(QuoteElemSaveVo quoteElemSaveVo, List list) throws Exception;

   int deleteQuoteElemByIds(Long[] ids) throws Exception;

   int deleteQuoteElemById(Long id) throws Exception;

   List selectTypeNumVo(QuoteElemTypeNumVo quoteElemTypeNumVo) throws Exception;

   List selectQuoteElemVoByTemp(String tempType, List elemIdList) throws Exception;

   List selectFromQuoteElemForBase64(String tempType) throws Exception;
}
