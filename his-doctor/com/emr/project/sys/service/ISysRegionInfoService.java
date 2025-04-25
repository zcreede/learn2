package com.emr.project.sys.service;

import com.emr.project.sys.domain.SysRegionInfo;
import java.util.List;

public interface ISysRegionInfoService {
   SysRegionInfo selectSysRegionInfoById(String code);

   List selectSysRegionInfoList(SysRegionInfo sysRegionInfo) throws Exception;

   List selectSysRegionDefault() throws Exception;

   int insertSysRegionInfo(SysRegionInfo sysRegionInfo);

   int updateSysRegionInfo(SysRegionInfo sysRegionInfo);

   int deleteSysRegionInfoByIds(String[] codes);

   int deleteSysRegionInfoById(String code);

   SysRegionInfo selectSysRegionInfoByName(String rprAddrPro, String rprAddrFlagty, String rprAddrCou) throws Exception;
}
