package com.emr.project.tmpl.mapper;

import com.emr.project.tmpl.domain.TmplElemLink;
import java.util.List;

public interface TmplElemLinkMapper {
   TmplElemLink selectTmplElemLinkById(Long id);

   List selectTmplElemLinkList(TmplElemLink tmplElemLink);

   List selectTmplElemLinkVoList(TmplElemLink tmplElemLink);

   int insertTmplElemLink(TmplElemLink tmplElemLink);

   void insertList(List list);

   int updateTmplElemLink(TmplElemLink tmplElemLink);

   int deleteTmplElemLinkById(Long id);

   int deleteTmplElemLinkByIds(Long[] ids);
}
