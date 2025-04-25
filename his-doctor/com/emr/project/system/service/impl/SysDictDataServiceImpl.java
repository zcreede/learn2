package com.emr.project.system.service.impl;

import com.emr.common.utils.DictUtils;
import com.emr.common.utils.PinYinUtil;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.system.domain.SysDictData;
import com.emr.project.system.mapper.SysDictDataMapper;
import com.emr.project.system.service.ISysDictDataService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysDictDataServiceImpl implements ISysDictDataService {
   @Autowired
   private SysDictDataMapper dictDataMapper;

   public List selectDictDataList(SysDictData dictData) {
      return this.dictDataMapper.selectDictDataList(dictData);
   }

   public List selectDictDataListByType(String[] dictType) {
      return this.dictDataMapper.selectDictDataListByType(dictType);
   }

   public String selectDictLabel(String dictType, String dictValue) {
      return this.dictDataMapper.selectDictLabel(dictType, dictValue);
   }

   public SysDictData selectDictDataById(Long dictCode) {
      return this.dictDataMapper.selectDictDataById(dictCode);
   }

   public void deleteDictDataByIds(Long[] dictCodes) {
      for(Long dictCode : dictCodes) {
         SysDictData data = this.selectDictDataById(dictCode);
         this.dictDataMapper.deleteDictDataById(dictCode);
         List<SysDictData> dictDatas = this.dictDataMapper.selectDictDataByType(data.getDictType());
         DictUtils.setDictCache(data.getDictType(), dictDatas);
      }

   }

   public int insertDictData(SysDictData data) {
      data.setDictCode(SnowIdUtils.uniqueLong());
      data.setInputstrphtc(PinYinUtil.getPinYinHeadChar(data.getDictLabel()));
      int row = this.dictDataMapper.insertDictData(data);
      if (row > 0) {
         List<SysDictData> dictDatas = this.dictDataMapper.selectDictDataByType(data.getDictType());
         DictUtils.setDictCache(data.getDictType(), dictDatas);
      }

      return row;
   }

   public int updateDictData(SysDictData data) {
      data.setInputstrphtc(PinYinUtil.getPinYinHeadChar(data.getDictLabel()));
      int row = this.dictDataMapper.updateDictData(data);
      if (row > 0) {
         List<SysDictData> dictDatas = this.dictDataMapper.selectDictDataByType(data.getDictType());
         DictUtils.setDictCache(data.getDictType(), dictDatas);
      }

      return row;
   }

   public List selectDictDataByType(String dictType) {
      return this.dictDataMapper.selectDictDataByType(dictType);
   }

   public List selectDictDataByType(String dictType, String dictLabel) {
      SysDictData param = new SysDictData();
      param.setDictType(dictType);
      param.setDictLabel(dictLabel);
      return this.dictDataMapper.selectDictDataList(param);
   }
}
