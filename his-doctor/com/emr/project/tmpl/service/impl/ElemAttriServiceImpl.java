package com.emr.project.tmpl.service.impl;

import com.emr.project.tmpl.domain.ElemAttri;
import com.emr.project.tmpl.mapper.ElemAttriMapper;
import com.emr.project.tmpl.service.IElemAttriService;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ElemAttriServiceImpl implements IElemAttriService {
   @Autowired
   private ElemAttriMapper elemAttriMapper;

   public ElemAttri selectElemAttriById(Long id) {
      return this.elemAttriMapper.selectElemAttriById(id);
   }

   public List selectElemAttriList(ElemAttri elemAttri) {
      return this.elemAttriMapper.selectElemAttriList(elemAttri);
   }

   public List selectElemAttriStandardList(ElemAttri elemAttri) {
      return this.elemAttriMapper.selectElemAttriStandardList(elemAttri);
   }

   public int insertElemAttri(ElemAttri elemAttri) {
      return this.elemAttriMapper.insertElemAttri(elemAttri);
   }

   @Async
   public void insertElemAttriList(List elemAttriList) throws Exception {
      this.elemAttriMapper.insertElemAttriList(elemAttriList);
   }

   @Async
   public void insertElemAttriStandardList(List elemAttriList) throws Exception {
      this.elemAttriMapper.insertElemAttriStandardList(elemAttriList);
   }

   public int updateElemAttri(ElemAttri elemAttri) {
      return this.elemAttriMapper.updateElemAttri(elemAttri);
   }

   public int deleteElemAttriByIds(Long[] ids) {
      return this.elemAttriMapper.deleteElemAttriByIds(ids);
   }

   public int deleteElemAttriById(Long id) {
      return this.elemAttriMapper.deleteElemAttriById(id);
   }

   public int deleteElemAttriByTempId(Long tempId) {
      return this.elemAttriMapper.deleteElemAttriByTempId(tempId);
   }

   public int deleteElemAttriStandardByTempId(Long tempId) {
      return this.elemAttriMapper.deleteElemAttriStandardByTempId(tempId);
   }

   public List selectElemAttriByTempId(Long tempId) {
      return this.elemAttriMapper.selectElemAttriByTempId(tempId);
   }

   public List selectElemAttriByTempIdAndContElemName(List paramList) throws Exception {
      List<ElemAttri> resList = null;
      if (CollectionUtils.isNotEmpty(paramList)) {
         resList = this.elemAttriMapper.selectElemAttriByTempIdAndContElemName(paramList);
      }

      return resList;
   }
}
