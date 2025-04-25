package com.emr.project.pat.service.impl;

import com.emr.project.pat.domain.OperatInfo;
import com.emr.project.pat.mapper.OperatInfoMapper;
import com.emr.project.pat.service.IOperatInfoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperatInfoServiceImpl implements IOperatInfoService {
   @Autowired
   private OperatInfoMapper operatInfoMapper;

   public OperatInfo selectOperatInfoById(Long id) {
      return this.operatInfoMapper.selectOperatInfoById(id);
   }

   public List selectOperatInfoList(OperatInfo operatInfo) throws Exception {
      return this.operatInfoMapper.selectOperatInfoList(operatInfo);
   }

   public int insertOperatInfo(OperatInfo operatInfo) {
      return this.operatInfoMapper.insertOperatInfo(operatInfo);
   }

   public int updateOperatInfo(OperatInfo operatInfo) {
      return this.operatInfoMapper.updateOperatInfo(operatInfo);
   }

   public int deleteOperatInfoByIds(Long[] ids) {
      return this.operatInfoMapper.deleteOperatInfoByIds(ids);
   }

   public int deleteOperatInfoById(Long id) {
      return this.operatInfoMapper.deleteOperatInfoById(id);
   }
}
