package com.emr.project.tmpl.service;

import com.emr.project.tmpl.domain.ElemGender;
import java.util.List;

public interface IElemGenderService {
   ElemGender selectElemGenderById(Long id);

   List selectElemGenderList(ElemGender elemGender);

   int insertElemGender(ElemGender elemGender);

   void insertElemGenderList(List elemGender) throws Exception;

   void insertElemGenderStandardList(List elemGender) throws Exception;

   int updateElemGender(ElemGender elemGender);

   int deleteElemGenderByIds(Long[] ids);

   int deleteElemGenderById(Long id);

   int deleteElemGenderByTempId(Long tempId);

   int deleteElemGenderStandardByTempId(Long tempId);

   List selectElemGenderQuaList(Long tempId);
}
