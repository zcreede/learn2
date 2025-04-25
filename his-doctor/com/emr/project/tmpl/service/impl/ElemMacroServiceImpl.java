package com.emr.project.tmpl.service.impl;

import com.emr.project.tmpl.domain.ElemMacro;
import com.emr.project.tmpl.domain.vo.ElemMacroVo;
import com.emr.project.tmpl.mapper.ElemMacroMapper;
import com.emr.project.tmpl.service.IElemMacroService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ElemMacroServiceImpl implements IElemMacroService {
   @Autowired
   private ElemMacroMapper elemMacroMapper;

   public ElemMacro selectElemMacroById(Long id) {
      return this.elemMacroMapper.selectElemMacroById(id);
   }

   public List selectElemMacroList(ElemMacro elemMacro) {
      return this.elemMacroMapper.selectElemMacroList(elemMacro);
   }

   public List selectElemMacroListByTempId(Long tempId) {
      return this.elemMacroMapper.selectElemMacroListByTempId(tempId);
   }

   public int insertElemMacro(ElemMacro elemMacro) {
      return this.elemMacroMapper.insertElemMacro(elemMacro);
   }

   @Async
   public void insertElemMacroList(List elemMacroList) throws Exception {
      this.elemMacroMapper.insertElemMacroList(elemMacroList);
   }

   @Async
   public void insertElemMacroStandardList(List elemMacroList) throws Exception {
      this.elemMacroMapper.insertElemMacroStandardList(elemMacroList);
   }

   public int updateElemMacro(ElemMacro elemMacro) {
      return this.elemMacroMapper.updateElemMacro(elemMacro);
   }

   public int deleteElemMacroByIds(Long[] ids) {
      return this.elemMacroMapper.deleteElemMacroByIds(ids);
   }

   public int deleteElemMacroById(Long id) {
      return this.elemMacroMapper.deleteElemMacroById(id);
   }

   public int deleteElemMacroByTempId(Long tempId) {
      return this.elemMacroMapper.deleteElemMacroByTempId(tempId);
   }

   public int deleteElemMacroStandardByTempId(Long tempId) {
      return this.elemMacroMapper.deleteElemMacroStandardByTempId(tempId);
   }

   public Map selectElemMacroInfoByTable(ElemMacroVo elemMacroVo) {
      return this.elemMacroMapper.selectElemMacroInfoByTable(elemMacroVo);
   }

   public Map selectElemMacroInfoByPat(ElemMacroVo elemMacroVo) {
      return this.elemMacroMapper.selectElemMacroInfoByPat(elemMacroVo);
   }

   public List selectListByTempIds(List tempIdList, Long mainId) {
      List<ElemMacro> list = new ArrayList(1);
      if (CollectionUtils.isNotEmpty(tempIdList)) {
         list = this.elemMacroMapper.selectElemMacroListByTempIds(tempIdList, mainId);
      }

      return list;
   }
}
