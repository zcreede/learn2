package com.emr.project.tmpl.service;

import com.emr.project.tmpl.domain.TmplElemLinkElem;
import java.util.List;

public interface ITmplElemLinkElemService {
   TmplElemLinkElem selectTmplElemLinkElemById(Long id);

   List selectTmplElemLinkElemList(TmplElemLinkElem tmplElemLinkElem);

   int insertTmplElemLinkElem(TmplElemLinkElem tmplElemLinkElem) throws Exception;

   int updateTmplElemLinkElem(TmplElemLinkElem tmplElemLinkElem);

   int deleteTmplElemLinkElemByIds(Long[] ids);

   int deleteTmplElemLinkElemById(Long id);

   void deleteByLinkId(Long linkId) throws Exception;

   void deleteByLinkIdAndType(Long linkId, String linkType) throws Exception;

   void insertList(List list) throws Exception;

   List selectByLinkIdList(List linkIdList) throws Exception;
}
