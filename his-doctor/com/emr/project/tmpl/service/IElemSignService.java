package com.emr.project.tmpl.service;

import com.emr.project.tmpl.domain.ElemSign;
import java.util.List;

public interface IElemSignService {
   ElemSign selectElemSignById(Long id);

   List selectElemSignList(ElemSign elemSign);

   int insertElemSign(ElemSign elemSign);

   void insertElemSignList(List elemSignList);

   void insertElemSignStandardList(List elemSignList);

   int updateElemSign(ElemSign elemSign);

   int deleteElemSignByIds(Long[] ids);

   int deleteElemSignById(Long id);

   int deleteElemSignByTempId(Long tempId);

   int deleteElemSignStandardByTempId(Long tempId);

   List selectElemSignByTempId(Long tempId);
}
