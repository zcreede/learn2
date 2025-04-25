package com.emr.project.sys.mapper;

import com.emr.project.sys.domain.QuoteElem;
import com.emr.project.sys.domain.vo.QuoteElemTypeNumVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QuoteElemMapper {
   QuoteElem selectQuoteElemById(Long id);

   List selectQuoteElemList(QuoteElem quoteElem);

   List selectQuoteElemStandardList(QuoteElem quoteElem);

   int insertQuoteElem(QuoteElem quoteElem);

   void insertQuoteElemList(List list);

   int updateQuoteElem(QuoteElem quoteElem);

   void updateQuoteElemList(List list);

   int deleteQuoteElemById(Long id);

   int deleteQuoteElemByIds(Long[] ids);

   void deleteQuoteElemByElemId(@Param("tempType") String tempType, @Param("elemCd") String elemCd);

   List selectNumGroupByType(QuoteElemTypeNumVo quoteElemTypeNumVo);

   List selectQuoteElemVoByTemp(@Param("tempType") String tempType, List list);

   List selectFromQuoteElemForBase64(@Param("tempType") String tempType);
}
