package com.emr.project.tmpl.service;

import com.emr.project.tmpl.domain.ElemPhysign;
import java.util.List;

public interface IElemPhysignService {
   ElemPhysign selectElemPhysignById(Long id);

   List selectElemPhysignList(ElemPhysign elemPhysign);

   int insertElemPhysign(ElemPhysign elemPhysign);

   void insertElemPhysignList(List elemPhysignList) throws Exception;

   void insertElemPhysignStandardList(List elemPhysignList) throws Exception;

   int updateElemPhysign(ElemPhysign elemPhysign);

   int deleteElemPhysignByIds(Long[] ids);

   int deleteElemPhysignById(Long id);

   int deleteElemPhysignByTempId(Long tempId);

   int deleteElemPhysignStandardByTempId(Long tempId);

   List selectElemPhysignListByTempId(Long tempId);
}
