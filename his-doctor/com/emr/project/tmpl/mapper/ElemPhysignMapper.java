package com.emr.project.tmpl.mapper;

import com.emr.project.tmpl.domain.ElemPhysign;
import java.util.List;

public interface ElemPhysignMapper {
   ElemPhysign selectElemPhysignById(Long id);

   List selectElemPhysignList(ElemPhysign elemPhysign);

   int insertElemPhysign(ElemPhysign elemPhysign);

   void insertElemPhysignList(List elemPhysignList);

   void insertElemPhysignStandardList(List elemPhysignList);

   int updateElemPhysign(ElemPhysign elemPhysign);

   int deleteElemPhysignById(Long id);

   int deleteElemPhysignByIds(Long[] ids);

   int deleteElemPhysignByTempId(Long tempId);

   int deleteElemPhysignStandardByTempId(Long tempId);

   List selectElemPhysignListByTempId(Long tempId);
}
