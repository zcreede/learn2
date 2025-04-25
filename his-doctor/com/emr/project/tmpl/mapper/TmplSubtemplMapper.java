package com.emr.project.tmpl.mapper;

import com.emr.project.tmpl.domain.TmplSubtempl;
import com.emr.project.tmpl.domain.vo.TmplSubtemplVo;
import java.util.List;

public interface TmplSubtemplMapper {
   TmplSubtemplVo selectTmplSubtemplById(Long id);

   List selectTmplSubtemplList(TmplSubtempl tmplSubtempl);

   List selectTmplSubtemplVoList(TmplSubtemplVo tmplSubtemplVo);

   int insertTmplSubtempl(TmplSubtempl tmplSubtempl);

   void updateTmplSubtempl(TmplSubtemplVo tmplSubtemplVo);

   int deleteTmplSubtemplById(Long id);

   int deleteTmplSubtemplByIds(Long[] ids);
}
