package com.emr.project.tmpl.mapper;

import com.emr.project.tmpl.domain.ElemGender;
import java.util.List;

public interface ElemGenderMapper {
   ElemGender selectElemGenderById(Long id);

   List selectElemGenderList(ElemGender elemGender);

   int insertElemGender(ElemGender elemGender);

   void insertElemGenderList(List elemGenderList);

   void insertElemGenderStandardList(List elemGenderList);

   int updateElemGender(ElemGender elemGender);

   int deleteElemGenderById(Long id);

   int deleteElemGenderByIds(Long[] ids);

   int deleteElemGenderByTempId(Long tempId);

   int deleteElemGenderStandardByTempId(Long tempId);

   List selectElemGenderQuaList(Long tempId);
}
