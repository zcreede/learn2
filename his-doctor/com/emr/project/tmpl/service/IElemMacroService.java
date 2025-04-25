package com.emr.project.tmpl.service;

import com.emr.project.tmpl.domain.ElemMacro;
import com.emr.project.tmpl.domain.vo.ElemMacroVo;
import java.util.List;
import java.util.Map;

public interface IElemMacroService {
   ElemMacro selectElemMacroById(Long id);

   List selectElemMacroList(ElemMacro elemMacro);

   List selectElemMacroListByTempId(Long tempId);

   int insertElemMacro(ElemMacro elemMacro);

   void insertElemMacroList(List elemMacroList) throws Exception;

   void insertElemMacroStandardList(List elemMacroList) throws Exception;

   int updateElemMacro(ElemMacro elemMacro);

   int deleteElemMacroByIds(Long[] ids);

   int deleteElemMacroById(Long id);

   int deleteElemMacroByTempId(Long tempId);

   int deleteElemMacroStandardByTempId(Long tempId);

   Map selectElemMacroInfoByTable(ElemMacroVo elemMacroVo);

   Map selectElemMacroInfoByPat(ElemMacroVo elemMacroVo);

   List selectListByTempIds(List tempIdList, Long mainId) throws Exception;
}
