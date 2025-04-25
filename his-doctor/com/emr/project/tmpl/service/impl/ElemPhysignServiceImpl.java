package com.emr.project.tmpl.service.impl;

import com.emr.project.tmpl.domain.ElemPhysign;
import com.emr.project.tmpl.mapper.ElemPhysignMapper;
import com.emr.project.tmpl.service.IElemPhysignService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ElemPhysignServiceImpl implements IElemPhysignService {
   @Autowired
   private ElemPhysignMapper elemPhysignMapper;

   public ElemPhysign selectElemPhysignById(Long id) {
      return this.elemPhysignMapper.selectElemPhysignById(id);
   }

   public List selectElemPhysignList(ElemPhysign elemPhysign) {
      return this.elemPhysignMapper.selectElemPhysignList(elemPhysign);
   }

   public int insertElemPhysign(ElemPhysign elemPhysign) {
      return this.elemPhysignMapper.insertElemPhysign(elemPhysign);
   }

   @Async
   public void insertElemPhysignList(List elemPhysignList) throws Exception {
      this.elemPhysignMapper.insertElemPhysignList(elemPhysignList);
   }

   @Async
   public void insertElemPhysignStandardList(List elemPhysignList) throws Exception {
      this.elemPhysignMapper.insertElemPhysignStandardList(elemPhysignList);
   }

   public int updateElemPhysign(ElemPhysign elemPhysign) {
      return this.elemPhysignMapper.updateElemPhysign(elemPhysign);
   }

   public int deleteElemPhysignByIds(Long[] ids) {
      return this.elemPhysignMapper.deleteElemPhysignByIds(ids);
   }

   public int deleteElemPhysignById(Long id) {
      return this.elemPhysignMapper.deleteElemPhysignById(id);
   }

   public int deleteElemPhysignByTempId(Long tempId) {
      return this.elemPhysignMapper.deleteElemPhysignByTempId(tempId);
   }

   public int deleteElemPhysignStandardByTempId(Long tempId) {
      return this.elemPhysignMapper.deleteElemPhysignStandardByTempId(tempId);
   }

   public List selectElemPhysignListByTempId(Long tempId) {
      return this.elemPhysignMapper.selectElemPhysignListByTempId(tempId);
   }
}
