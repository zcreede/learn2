package com.emr.project.system.service.impl;

import com.emr.project.system.domain.SysStaUnit;
import com.emr.project.system.domain.vo.SysStaUnitVo;
import com.emr.project.system.mapper.SysStaUnitMapper;
import com.emr.project.system.service.ISysStaUnitService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysStaUnitServiceImpl implements ISysStaUnitService {
   @Autowired
   private SysStaUnitMapper sysStaUnitMapper;

   public SysStaUnit selectSysStaUnitById(Long id) {
      return this.sysStaUnitMapper.selectSysStaUnitById(id);
   }

   public List selectSysStaUnitList(SysStaUnitVo sysStaUnitVo) throws Exception {
      return this.sysStaUnitMapper.selectSysStaUnitList(sysStaUnitVo);
   }

   public int insertSysStaUnit(SysStaUnit sysStaUnit) {
      return this.sysStaUnitMapper.insertSysStaUnit(sysStaUnit);
   }

   public int updateSysStaUnit(SysStaUnit sysStaUnit) {
      return this.sysStaUnitMapper.updateSysStaUnit(sysStaUnit);
   }

   public int deleteSysStaUnitByIds(Long[] ids) {
      return this.sysStaUnitMapper.deleteSysStaUnitByIds(ids);
   }

   public int deleteSysStaUnitById(Long id) {
      return this.sysStaUnitMapper.deleteSysStaUnitById(id);
   }
}
