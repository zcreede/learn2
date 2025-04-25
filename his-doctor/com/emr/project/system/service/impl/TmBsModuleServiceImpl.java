package com.emr.project.system.service.impl;

import com.emr.common.utils.DateUtils;
import com.emr.project.system.domain.TmBsModule;
import com.emr.project.system.mapper.TmBsModuleMapper;
import com.emr.project.system.service.ITmBsModuleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TmBsModuleServiceImpl implements ITmBsModuleService {
   @Autowired
   private TmBsModuleMapper tmBsModuleMapper;

   public TmBsModule selectTmBsModuleById(Long id) {
      return this.tmBsModuleMapper.selectTmBsModuleById(id);
   }

   public List selectTmBsModuleList(TmBsModule tmBsModule) {
      return this.tmBsModuleMapper.selectTmBsModuleList(tmBsModule);
   }

   public int insertTmBsModule(TmBsModule tmBsModule) {
      tmBsModule.setCreateTime(DateUtils.getNowDate());
      return this.tmBsModuleMapper.insertTmBsModule(tmBsModule);
   }

   public int updateTmBsModule(TmBsModule tmBsModule) {
      tmBsModule.setUpdateTime(DateUtils.getNowDate());
      return this.tmBsModuleMapper.updateTmBsModule(tmBsModule);
   }

   public int deleteTmBsModuleByIds(Long[] ids) {
      return this.tmBsModuleMapper.deleteTmBsModuleByIds(ids);
   }

   public int deleteTmBsModuleById(Long id) {
      return this.tmBsModuleMapper.deleteTmBsModuleById(id);
   }

   public List selectModuleListByUser(String userName) {
      return this.tmBsModuleMapper.selectModuleListByUser(userName);
   }
}
