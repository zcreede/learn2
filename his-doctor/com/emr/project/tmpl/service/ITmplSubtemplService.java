package com.emr.project.tmpl.service;

import com.emr.project.tmpl.domain.TmplSubtempl;
import com.emr.project.tmpl.domain.vo.TmplSubtemplVo;
import java.util.List;

public interface ITmplSubtemplService {
   TmplSubtemplVo selectTmplSubtemplById(Long id);

   List selectTmplSubtemplList(TmplSubtempl tmplSubtempl);

   void insertTmplSubtempl(TmplSubtemplVo tmplSubtemplVo) throws Exception;

   void insertTmplSubtemplCopySave(TmplSubtemplVo tmplSubtemplVo) throws Exception;

   void updateTmplSubtempl(TmplSubtemplVo tmplSubtemplVo) throws Exception;

   void updateTmplSubtemplSave(TmplSubtemplVo tmplSubtemplVo) throws Exception;

   int deleteTmplSubtemplByIds(Long[] ids);

   int deleteTmplSubtemplById(Long id);

   List selectTreeList(TmplSubtemplVo tmplSubtemplVo);

   List selectTreeHelperList(TmplSubtemplVo tmplSubtemplVo) throws Exception;

   TmplSubtemplVo selectTmplSubtemplVoById(Long id);

   Boolean addEditFlag(Long tempId);
}
