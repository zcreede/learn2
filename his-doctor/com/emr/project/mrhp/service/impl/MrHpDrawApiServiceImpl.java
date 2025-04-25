package com.emr.project.mrhp.service.impl;

import com.emr.project.mrhp.domain.MrHpDrawApi;
import com.emr.project.mrhp.mapper.MrHpDrawApiMapper;
import com.emr.project.mrhp.service.IMrHpDrawApiService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MrHpDrawApiServiceImpl implements IMrHpDrawApiService {
   @Autowired
   private MrHpDrawApiMapper mrHpDrawApiMapper;

   public MrHpDrawApi selectMrHpDrawApiById(Long id) {
      return this.mrHpDrawApiMapper.selectMrHpDrawApiById(id);
   }

   public List selectMrHpDrawApiList(MrHpDrawApi mrHpDrawApi) {
      return this.mrHpDrawApiMapper.selectMrHpDrawApiList(mrHpDrawApi);
   }

   public int insertMrHpDrawApi(MrHpDrawApi mrHpDrawApi) {
      return this.mrHpDrawApiMapper.insertMrHpDrawApi(mrHpDrawApi);
   }

   public int batchInsert(List mrHpDrawApi) {
      return this.mrHpDrawApiMapper.batchInsert(mrHpDrawApi);
   }

   public int updateMrHpDrawApi(MrHpDrawApi mrHpDrawApi) {
      return this.mrHpDrawApiMapper.updateMrHpDrawApi(mrHpDrawApi);
   }

   public int deleteMrHpDrawApiByIds(Long[] ids) {
      return this.mrHpDrawApiMapper.deleteMrHpDrawApiByIds(ids);
   }

   public int deleteMrHpDrawApiById(Long id) {
      return this.mrHpDrawApiMapper.deleteMrHpDrawApiById(id);
   }

   public int deleteMrHpDrawApiByMainId(Long mainId) {
      return this.mrHpDrawApiMapper.deleteMrHpDrawApiByMainId(mainId);
   }
}
