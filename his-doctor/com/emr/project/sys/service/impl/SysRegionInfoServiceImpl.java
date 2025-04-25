package com.emr.project.sys.service.impl;

import com.emr.project.sys.domain.SysRegionInfo;
import com.emr.project.sys.mapper.SysRegionInfoMapper;
import com.emr.project.sys.service.ISysRegionInfoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRegionInfoServiceImpl implements ISysRegionInfoService {
   @Autowired
   private SysRegionInfoMapper sysRegionInfoMapper;

   public SysRegionInfo selectSysRegionInfoById(String code) {
      return this.sysRegionInfoMapper.selectSysRegionInfoById(code);
   }

   public List selectSysRegionInfoList(SysRegionInfo sysRegionInfo) throws Exception {
      return this.sysRegionInfoMapper.selectSysRegionInfoList(sysRegionInfo);
   }

   public List selectSysRegionDefault() throws Exception {
      return this.sysRegionInfoMapper.selectSysRegionDefault();
   }

   public int insertSysRegionInfo(SysRegionInfo sysRegionInfo) {
      return this.sysRegionInfoMapper.insertSysRegionInfo(sysRegionInfo);
   }

   public int updateSysRegionInfo(SysRegionInfo sysRegionInfo) {
      return this.sysRegionInfoMapper.updateSysRegionInfo(sysRegionInfo);
   }

   public int deleteSysRegionInfoByIds(String[] codes) {
      return this.sysRegionInfoMapper.deleteSysRegionInfoByIds(codes);
   }

   public int deleteSysRegionInfoById(String code) {
      return this.sysRegionInfoMapper.deleteSysRegionInfoById(code);
   }

   public SysRegionInfo selectSysRegionInfoByName(String rprAddrPro, String rprAddrFlagty, String rprAddrCou) throws Exception {
      return this.sysRegionInfoMapper.selectSysRegionInfoByName(rprAddrPro, rprAddrFlagty, rprAddrCou);
   }
}
