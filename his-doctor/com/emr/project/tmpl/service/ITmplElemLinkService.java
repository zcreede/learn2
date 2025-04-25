package com.emr.project.tmpl.service;

import com.emr.project.emr.domain.vo.IndexVo;
import com.emr.project.tmpl.domain.TmplElemLink;
import com.emr.project.tmpl.domain.vo.TmplElemLinkVo;
import java.util.List;

public interface ITmplElemLinkService {
   TmplElemLink selectTmplElemLinkById(Long id) throws Exception;

   List selectTmplElemLinkVoByTempId(Long tempId) throws Exception;

   List selectTmplElemLinkVoByPatientId(String patientId) throws Exception;

   List selectTmplElemLinkVoByXmlStr(List tmplElemLinkVoList, List elemAttris, IndexVo indexVo) throws Exception;

   TmplElemLinkVo selectByTempIdElemId(Long tempId, String contName) throws Exception;

   List selectTmplElemLinkList(TmplElemLink tmplElemLink) throws Exception;

   void insertTmplElemLink(TmplElemLinkVo tmplElemLinkVo) throws Exception;

   int updateTmplElemLink(TmplElemLink tmplElemLink) throws Exception;

   int deleteTmplElemLinkByIds(Long[] ids) throws Exception;

   int deleteTmplElemLinkById(Long id) throws Exception;

   void copyToNewTmplIndex(Long sourceTmplId, Long targetTmplId, String targetTmplName) throws Exception;

   List getTmplElemLinkElemList(List base64IdList, List mrFileIdList) throws Exception;
}
