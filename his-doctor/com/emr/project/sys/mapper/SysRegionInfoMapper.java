package com.emr.project.sys.mapper;

import com.emr.project.sys.domain.SysRegionInfo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRegionInfoMapper {
   SysRegionInfo selectSysRegionInfoById(String code);

   List selectSysRegionInfoList(SysRegionInfo sysRegionInfo);

   List selectSysRegionDefault();

   int insertSysRegionInfo(SysRegionInfo sysRegionInfo);

   int updateSysRegionInfo(SysRegionInfo sysRegionInfo);

   int deleteSysRegionInfoById(String code);

   int deleteSysRegionInfoByIds(String[] codes);

   SysRegionInfo selectSysRegionInfoByName(@Param("province") String province, @Param("city") String city, @Param("county") String county);
}
