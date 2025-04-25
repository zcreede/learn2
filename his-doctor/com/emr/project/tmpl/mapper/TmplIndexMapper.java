package com.emr.project.tmpl.mapper;

import com.emr.project.tmpl.domain.TmplIndex;
import com.emr.project.tmpl.domain.vo.TmplIndexSearchVo;
import com.emr.project.tmpl.domain.vo.TmplIndexVo;
import java.util.List;

public interface TmplIndexMapper {
   TmplIndex selectTmplIndexById(Long id);

   TmplIndex selectTmplIndexNoFileById(Long id);

   TmplIndex selectTmplStandardIndexNoFileById(Long id);

   TmplIndexVo selectTmplById(Long id);

   TmplIndexVo selectTmplStandardById(Long id);

   List selectTmplIndexList(TmplIndex tmplIndex);

   List searchTmplIndexList(TmplIndexSearchVo tmplIndex);

   List searchTmplStandardIndexList(TmplIndexSearchVo tmplIndex);

   List selectTmplIndexVoList(TmplIndexVo tmplIndexVo);

   List selectHeaderTreeList(TmplIndex tmplIndex);

   int insertTmplIndex(TmplIndexVo tmplIndexVo);

   int insertTmplStandardIndex(TmplIndexVo tmplIndexVo);

   int updateTmplIndex(TmplIndexVo tmplIndexVo);

   int updateTmplStandardIndex(TmplIndexVo tmplIndexVo);

   int updateTmplIndexState(TmplIndex tmplIndex);

   int deleteTmplIndexById(Long id);

   int deleteTmplStandardIndexById(Long id);

   int deleteTmplIndexByIds(Long[] ids);

   int updateTempStateSave(TmplIndexVo tmplIndexVo);

   TmplIndex selectTmplIndexByEmrId(Long emrId);

   List selectListByTempMajors(TmplIndexSearchVo tmplIndexSearchVo);

   List selectListByIds(List list);
}
