package com.emr.project.his.service.impl;

import com.emr.framework.aspectj.lang.annotation.DataSource;
import com.emr.framework.aspectj.lang.enums.DataSourceType;
import com.emr.project.his.mapper.HisSyncMapper;
import com.emr.project.his.service.IHisSyncService;
import com.emr.project.mrhp.domain.MrHpDia;
import com.emr.project.mrhp.domain.MrHpFee;
import com.emr.project.mrhp.domain.MrHpOpe;
import com.emr.project.mrhp.domain.vo.MrHpVo;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HisSyncServiceImpl implements IHisSyncService {
   @Autowired
   private HisSyncMapper hisSyncMapper;

   @DataSource(DataSourceType.hisDept)
   public List selectDeptALL() {
      return this.hisSyncMapper.selectDeptAll();
   }

   public void deleteHisMrHpVo(String inpNo) {
      this.hisSyncMapper.deleteHisMrHpVo(inpNo);
   }

   public void deleteHisMrHpZYDia(String inpNo) {
      this.hisSyncMapper.deleteHisMrHpZYDia(inpNo);
   }

   public void deleteHisMrHpXYDia(String inpNo) {
      this.hisSyncMapper.deleteHisMrHpXYDia(inpNo);
   }

   public void deleteHisMrHpOpe(String inpNo) {
      this.hisSyncMapper.deleteHisMrHpOpe(inpNo);
   }

   public void deleteHisMrHpFee(String inpNo) {
      this.hisSyncMapper.deleteHisMrHpFee(inpNo);
   }

   public void insertHisMrHpVo(MrHpVo mrHpVo) throws Exception {
      this.hisSyncMapper.insertHisMrHpVo(mrHpVo);
   }

   public void insertHisMrHpZYDia(List diaList) throws Exception {
      this.hisSyncMapper.insertHisMrHpZYDia(diaList);
   }

   public void insertHisMrHpXYDia(List diaList) throws Exception {
      this.hisSyncMapper.insertHisMrHpXYDia(diaList);
   }

   public void insertHisMrHpOpe(List opeList) throws Exception {
      this.hisSyncMapper.insertHisMrHpOpe(opeList);
   }

   public void insertHisMrHpFee(List feeList) throws Exception {
      this.hisSyncMapper.insertHisMrHpFee(feeList);
   }

   @DataSource(DataSourceType.hisDept)
   public Map selectDeptByCode(String appDeptCd) {
      return this.hisSyncMapper.selectDeptByCode(appDeptCd);
   }

   @DataSource(DataSourceType.hisDept)
   public Map selectStaffByCode(String appDocCd) {
      return this.hisSyncMapper.selectDocByCode(appDocCd);
   }
}
