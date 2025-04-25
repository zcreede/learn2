package com.emr.project.tmpl.service.impl;

import com.emr.project.tmpl.domain.ElemSign;
import com.emr.project.tmpl.mapper.ElemSignMapper;
import com.emr.project.tmpl.service.IElemSignService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ElemSignServiceImpl implements IElemSignService {
   @Autowired
   private ElemSignMapper elemSignMapper;

   public ElemSign selectElemSignById(Long id) {
      return this.elemSignMapper.selectElemSignById(id);
   }

   public List selectElemSignList(ElemSign elemSign) {
      return this.elemSignMapper.selectElemSignList(elemSign);
   }

   public int insertElemSign(ElemSign elemSign) {
      return this.elemSignMapper.insertElemSign(elemSign);
   }

   @Async
   public void insertElemSignList(List elemSignList) {
      this.elemSignMapper.insertElemSignList(elemSignList);
   }

   @Async
   public void insertElemSignStandardList(List elemSignList) {
      this.elemSignMapper.insertElemSignStandardList(elemSignList);
   }

   public int updateElemSign(ElemSign elemSign) {
      return this.elemSignMapper.updateElemSign(elemSign);
   }

   public int deleteElemSignByIds(Long[] ids) {
      return this.elemSignMapper.deleteElemSignByIds(ids);
   }

   public int deleteElemSignById(Long id) {
      return this.elemSignMapper.deleteElemSignById(id);
   }

   public int deleteElemSignStandardByTempId(Long id) {
      return this.elemSignMapper.deleteElemSignStandardByTempId(id);
   }

   public int deleteElemSignByTempId(Long tempId) {
      return this.elemSignMapper.deleteElemSignByTempId(tempId);
   }

   public List selectElemSignByTempId(Long tempId) {
      return this.elemSignMapper.selectElemSignByTempId(tempId);
   }
}
