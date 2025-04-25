package com.emr.project.tmpl.mapper;

import com.emr.project.tmpl.domain.TmplElemLinkElem;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TmplElemLinkElemMapper {
   TmplElemLinkElem selectTmplElemLinkElemById(Long id);

   List selectTmplElemLinkElemList(TmplElemLinkElem tmplElemLinkElem);

   int insertTmplElemLinkElem(TmplElemLinkElem tmplElemLinkElem);

   int updateTmplElemLinkElem(TmplElemLinkElem tmplElemLinkElem);

   int deleteTmplElemLinkElemById(Long id);

   int deleteTmplElemLinkElemByIds(Long[] ids);

   void deleteByLinkId(@Param("linkId") Long linkId);

   void deleteByLinkIdAndType(@Param("linkId") Long linkId, @Param("linkType") String linkType);

   void insertList(List list);

   List selectByLinkIdList(List list);
}
