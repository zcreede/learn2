package com.emr.project.tmpl.mapper;

import com.emr.project.tmpl.domain.ElemSign;
import java.util.List;

public interface ElemSignMapper {
   ElemSign selectElemSignById(Long id);

   List selectElemSignList(ElemSign elemSign);

   int insertElemSign(ElemSign elemSign);

   void insertElemSignList(List elemSignList);

   void insertElemSignStandardList(List elemSignList);

   int updateElemSign(ElemSign elemSign);

   int deleteElemSignById(Long id);

   int deleteElemSignStandardByTempId(Long id);

   int deleteElemSignByIds(Long[] ids);

   int deleteElemSignByTempId(Long tempId);

   List selectElemSignByTempId(Long tempId);
}
