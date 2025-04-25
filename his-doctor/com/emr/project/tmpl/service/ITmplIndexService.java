package com.emr.project.tmpl.service;

import com.emr.project.tmpl.domain.TmplIndex;
import com.emr.project.tmpl.domain.vo.TempIndexSaveElemVo;
import com.emr.project.tmpl.domain.vo.TmplIndexSearchVo;
import com.emr.project.tmpl.domain.vo.TmplIndexVo;
import java.util.List;

public interface ITmplIndexService {
   TmplIndex selectTmplIndexById(Long id);

   TmplIndex selectTmplIndexNoFileById(Long id);

   TmplIndex selectTmplStandardIndexNoFileById(Long id);

   List selectTmplIndexList(TmplIndex tmplIndex);

   List selectTmplIndexVoList(TmplIndexVo tmplIndexVo) throws Exception;

   List selectHeaderTreeList(TmplIndex tmplIndex) throws Exception;

   int insertTmplIndex(TmplIndexVo tmplIndexVo) throws Exception;

   int insertTmplIndexFromStandard(TmplIndexVo tmplIndexVo) throws Exception;

   int insertTmplStandardIndex(TmplIndexVo tmplIndexVo) throws Exception;

   int updateTmplIndex(TmplIndexVo tmplIndexVo) throws Exception;

   int updateTmplStandardIndex(TmplIndexVo tmplIndexVo) throws Exception;

   void updateTmplEditState(TmplIndexVo tmplIndexVo) throws Exception;

   int updateTmplIndexState(TmplIndex tmplIndex) throws Exception;

   int deleteTmplIndexByIds(Long[] ids);

   int deleteTmplIndexById(Long id) throws Exception;

   int deleteTmplStandardIndexById(Long id) throws Exception;

   List getTempIndexTree(TmplIndex tmplIndex, int treeType) throws Exception;

   List getTempIndexTrees(TmplIndexSearchVo tmplIndex, int treeType) throws Exception;

   List getTempStandardIndexTrees(TmplIndexSearchVo tmplIndex, int treeType) throws Exception;

   boolean getIsAllFlag() throws Exception;

   List getTempIndexTrees(TmplIndexSearchVo tmplIndex) throws Exception;

   List getTempStandardIndexTrees(TmplIndexSearchVo tmplIndex) throws Exception;

   List selectTempTypeList(TmplIndex tmplIndex) throws Exception;

   void updateTempStateSave(TmplIndexVo tmplIndexVo) throws Exception;

   void insertTmplDeptList(List deptCodeList, Long tempId) throws Exception;

   void insertTmplElem(TmplIndexVo tmplIndexVo) throws Exception;

   void insertTmplStandardElem(TmplIndexVo tmplIndexVo) throws Exception;

   void copyTmplIndex(TmplIndexVo tmplIndexVo) throws Exception;

   void copyStaTmplToHosIndex(TmplIndexVo tmplIndexVo) throws Exception;

   void copyHosTmplToStandardIndex(TmplIndexVo tmplIndexVo) throws Exception;

   String judgeElemStrstore(TmplIndexVo tmplIndexVo, TempIndexSaveElemVo tempIndexSaveElemVo) throws Exception;

   TmplIndexVo updateTempStateSubmit(TmplIndexVo tmplIndexVo, TempIndexSaveElemVo tempIndexSaveElemVo) throws Exception;

   TmplIndexVo backoutTempStateSubmit(TmplIndexVo tmplIndexVo) throws Exception;

   TmplIndexVo selectTmplIndexVoById(Long id) throws Exception;

   TmplIndexVo selectTmplStandardIndexVoById(Long id) throws Exception;

   Boolean addEditFlag(TmplIndexVo tmplIndexVo) throws Exception;

   Boolean addEditFlag_old(TmplIndex tmplIndexVo);

   Boolean addEditFlagNew(TmplIndex tmplIndexVo, Boolean isAllFlag) throws Exception;

   TmplIndex selectTmplIndexByEmrId(Long emrId);

   void addTmplElem(TempIndexSaveElemVo tempIndexSaveElemVo, Long id, String tempName, String tempType) throws Exception;

   List selectListByIds(List list) throws Exception;
}
