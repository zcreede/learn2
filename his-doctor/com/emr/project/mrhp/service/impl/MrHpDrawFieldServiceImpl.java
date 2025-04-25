package com.emr.project.mrhp.service.impl;

import com.emr.project.mrhp.domain.MrHpDrawField;
import com.emr.project.mrhp.mapper.MrHpDrawFieldMapper;
import com.emr.project.mrhp.service.IMrHpDrawFieldService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MrHpDrawFieldServiceImpl implements IMrHpDrawFieldService {
   @Autowired
   private MrHpDrawFieldMapper mrHpDrawFieldMapper;

   public MrHpDrawField selectMrHpDrawFieldById(Long id) {
      return this.mrHpDrawFieldMapper.selectMrHpDrawFieldById(id);
   }

   public List selectMrHpDrawFieldList(MrHpDrawField mrHpDrawField) {
      return this.mrHpDrawFieldMapper.selectMrHpDrawFieldList(mrHpDrawField);
   }

   public int insertMrHpDrawField(MrHpDrawField mrHpDrawField) {
      return this.mrHpDrawFieldMapper.insertMrHpDrawField(mrHpDrawField);
   }

   public int batchInsert(List mrHpDrawField) {
      return this.mrHpDrawFieldMapper.batchInsert(mrHpDrawField);
   }

   public int updateMrHpDrawField(MrHpDrawField mrHpDrawField) {
      return this.mrHpDrawFieldMapper.updateMrHpDrawField(mrHpDrawField);
   }

   public int deleteMrHpDrawFieldByIds(Long[] ids) {
      return this.mrHpDrawFieldMapper.deleteMrHpDrawFieldByIds(ids);
   }

   public int deleteMrHpDrawFieldById(Long id) {
      return this.mrHpDrawFieldMapper.deleteMrHpDrawFieldById(id);
   }

   public int deleteMrHpDrawFieldByMainId(Long mainId) {
      return this.mrHpDrawFieldMapper.deleteMrHpDrawFieldByMainId(mainId);
   }

   public List selectFieldByMainIdList(List mainIdList) {
      return this.mrHpDrawFieldMapper.selectFieldByMainIdList(mainIdList);
   }
}
