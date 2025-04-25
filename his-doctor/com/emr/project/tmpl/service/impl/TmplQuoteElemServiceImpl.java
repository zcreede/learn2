package com.emr.project.tmpl.service.impl;

import com.emr.project.tmpl.domain.TmplQuoteElem;
import com.emr.project.tmpl.mapper.TmplQuoteElemMapper;
import com.emr.project.tmpl.service.ITmplQuoteElemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class TmplQuoteElemServiceImpl implements ITmplQuoteElemService {
   @Autowired
   private TmplQuoteElemMapper tmplQuoteElemMapper;

   public TmplQuoteElem selectTmplQuoteElemById(Long id) {
      return this.tmplQuoteElemMapper.selectTmplQuoteElemById(id);
   }

   public List selectTmplQuoteElemList(TmplQuoteElem tmplQuoteElem) {
      return this.tmplQuoteElemMapper.selectTmplQuoteElemList(tmplQuoteElem);
   }

   public int insertTmplQuoteElem(TmplQuoteElem tmplQuoteElem) {
      return this.tmplQuoteElemMapper.insertTmplQuoteElem(tmplQuoteElem);
   }

   @Async
   public void insertTmplQuoteElemList(List tmplQuoteElemList) throws Exception {
      this.tmplQuoteElemMapper.insertTmplQuoteElemList(tmplQuoteElemList);
   }

   @Async
   public void insertTmplQuoteElemStandardList(List tmplQuoteElemList) throws Exception {
      this.tmplQuoteElemMapper.insertTmplQuoteElemStandardList(tmplQuoteElemList);
   }

   public int updateTmplQuoteElem(TmplQuoteElem tmplQuoteElem) {
      return this.tmplQuoteElemMapper.updateTmplQuoteElem(tmplQuoteElem);
   }

   public int deleteTmplQuoteElemByIds(Long[] ids) {
      return this.tmplQuoteElemMapper.deleteTmplQuoteElemByIds(ids);
   }

   public int deleteTmplQuoteElemById(Long id) {
      return this.tmplQuoteElemMapper.deleteTmplQuoteElemById(id);
   }

   public int deleteTmplQuoteElemByTempId(Long tempId) {
      return this.tmplQuoteElemMapper.deleteTmplQuoteElemByTempId(tempId);
   }

   public int deleteTmplQuoteElemStandardByTempId(Long tempId) {
      return this.tmplQuoteElemMapper.deleteTmplQuoteElemStandardByTempId(tempId);
   }

   public List selectTmplQuoteElemListByTempId(Long tempId) {
      return this.tmplQuoteElemMapper.selectTmplQuoteElemListByTempId(tempId);
   }
}
