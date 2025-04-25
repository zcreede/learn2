package com.emr.project.tmpl.service.impl;

import com.emr.project.tmpl.domain.ElemGender;
import com.emr.project.tmpl.mapper.ElemGenderMapper;
import com.emr.project.tmpl.service.IElemGenderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ElemGenderServiceImpl implements IElemGenderService {
   @Autowired
   private ElemGenderMapper elemGenderMapper;

   public ElemGender selectElemGenderById(Long id) {
      return this.elemGenderMapper.selectElemGenderById(id);
   }

   public List selectElemGenderList(ElemGender elemGender) {
      return this.elemGenderMapper.selectElemGenderList(elemGender);
   }

   public int insertElemGender(ElemGender elemGender) {
      return this.elemGenderMapper.insertElemGender(elemGender);
   }

   @Async
   public void insertElemGenderList(List elemGenderList) throws Exception {
      this.elemGenderMapper.insertElemGenderList(elemGenderList);
   }

   @Async
   public void insertElemGenderStandardList(List elemGenderList) throws Exception {
      this.elemGenderMapper.insertElemGenderStandardList(elemGenderList);
   }

   public int updateElemGender(ElemGender elemGender) {
      return this.elemGenderMapper.updateElemGender(elemGender);
   }

   public int deleteElemGenderByIds(Long[] ids) {
      return this.elemGenderMapper.deleteElemGenderByIds(ids);
   }

   public int deleteElemGenderById(Long id) {
      return this.elemGenderMapper.deleteElemGenderById(id);
   }

   public int deleteElemGenderByTempId(Long tempId) {
      return this.elemGenderMapper.deleteElemGenderByTempId(tempId);
   }

   public int deleteElemGenderStandardByTempId(Long tempId) {
      return this.elemGenderMapper.deleteElemGenderStandardByTempId(tempId);
   }

   public List selectElemGenderQuaList(Long tempId) {
      return this.elemGenderMapper.selectElemGenderQuaList(tempId);
   }
}
