package com.emr.project.tmpl.mapper;

import com.emr.project.tmpl.domain.ElemMacro;
import com.emr.project.tmpl.domain.vo.ElemMacroVo;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ElemMacroMapper {
   ElemMacro selectElemMacroById(Long id);

   List selectElemMacroList(ElemMacro elemMacro);

   List selectElemMacroListByTempId(Long tempId);

   int insertElemMacro(ElemMacro elemMacro);

   void insertElemMacroList(List elemMacroList);

   void insertElemMacroStandardList(List elemMacroList);

   int updateElemMacro(ElemMacro elemMacro);

   int deleteElemMacroById(Long id);

   int deleteElemMacroByIds(Long[] ids);

   int deleteElemMacroByTempId(Long tempId);

   int deleteElemMacroStandardByTempId(Long tempId);

   Map selectElemMacroInfoByTable(ElemMacroVo elemMacroVo);

   Map selectElemMacroInfoByPat(ElemMacroVo elemMacroVo);

   List selectElemMacroListByTempIds(List list, @Param("mainId") Long mainId);
}
