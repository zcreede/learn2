package com.emr.project.system.service.impl;

import com.emr.common.exception.CustomException;
import com.emr.common.utils.DictUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.system.domain.SysDictData;
import com.emr.project.system.domain.SysDictType;
import com.emr.project.system.domain.req.BsDictTypeReq;
import com.emr.project.system.mapper.SysDictDataMapper;
import com.emr.project.system.mapper.SysDictTypeMapper;
import com.emr.project.system.service.ISysDictTypeService;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SysDictTypeServiceImpl implements ISysDictTypeService {
   @Autowired
   private SysDictTypeMapper dictTypeMapper;
   @Autowired
   private SysDictDataMapper dictDataMapper;

   @PostConstruct
   public void init() {
   }

   public List selectDictTypeList(SysDictType dictType) {
      return this.dictTypeMapper.selectDictTypeList(dictType);
   }

   public List selectDictTypeAll() {
      return this.dictTypeMapper.selectDictTypeAll();
   }

   public List selectDictDataByType(String dictType) {
      List<SysDictData> dictDatas = this.dictDataMapper.selectDictDataByType(dictType);
      return dictDatas;
   }

   public List selectDictDataByTypeAndVal(SysDictData dictData) {
      List<SysDictData> dictDatas = this.dictDataMapper.selectDictDataList(dictData);
      return dictDatas;
   }

   public SysDictType selectDictTypeById(Long dictId) {
      return this.dictTypeMapper.selectDictTypeById(dictId);
   }

   public SysDictType selectDictTypeByType(String dictType) {
      return this.dictTypeMapper.selectDictTypeByType(dictType);
   }

   public void deleteDictTypeByIds(Long[] dictIds) {
      for(Long dictId : dictIds) {
         SysDictType dictType = this.selectDictTypeById(dictId);
         if (this.dictDataMapper.countDictDataByType(dictType.getDictType()) > 0) {
            throw new CustomException(String.format("%1$s已分配,不能删除", dictType.getDictName()));
         }

         this.dictTypeMapper.deleteDictTypeById(dictId);
         DictUtils.removeDictCache(dictType.getDictType());
      }

   }

   public void loadingDictCache() {
      for(SysDictType dictType : this.dictTypeMapper.selectDictTypeAll()) {
         List<SysDictData> dictDatas = this.dictDataMapper.selectDictDataByType(dictType.getDictType());
         DictUtils.setDictCache(dictType.getDictType(), dictDatas);
      }

   }

   public void clearDictCache() {
      DictUtils.clearDictCache();
   }

   public void resetDictCache() {
   }

   public int insertDictType(SysDictType dict) {
      int row = this.dictTypeMapper.insertDictType(dict);
      if (row > 0) {
         DictUtils.setDictCache(dict.getDictType(), (List)null);
      }

      return row;
   }

   @Transactional
   public int updateDictType(SysDictType dict) {
      SysDictType oldDict = this.dictTypeMapper.selectDictTypeById(dict.getDictId());
      this.dictDataMapper.updateDictDataType(oldDict.getDictType(), dict.getDictType());
      int row = this.dictTypeMapper.updateDictType(dict);
      if (row > 0) {
         List<SysDictData> dictDatas = this.dictDataMapper.selectDictDataByType(dict.getDictType());
         DictUtils.setDictCache(dict.getDictType(), dictDatas);
      }

      return row;
   }

   public String checkDictTypeUnique(SysDictType dict) {
      Long dictId = StringUtils.isNull(dict.getDictId()) ? -1L : dict.getDictId();
      SysDictType dictType = this.dictTypeMapper.checkDictTypeUnique(dict.getDictType());
      return StringUtils.isNotNull(dictType) && dictType.getDictId() != dictId ? "1" : "0";
   }

   public List selectBsDictDataByTypeAndSearch(BsDictTypeReq req) {
      return this.dictTypeMapper.selectBsDictDataByTypeAndSearch(req);
   }
}
