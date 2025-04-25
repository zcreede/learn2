package com.emr.project.sys.mapper;

import com.emr.project.sys.domain.SysOpeIcd;
import com.emr.project.sys.domain.vo.SysOpeIcdVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysOpeIcdMapper {
   SysOpeIcd selectSysOpeIcdById(Long icdId);

   List selectSysOpeIcdList(SysOpeIcd sysOpeIcd);

   List selectOpeIcdList(SysOpeIcd sysOpeIcd);

   int insertSysOpeIcd(SysOpeIcd sysOpeIcd);

   int updateSysOpeIcd(SysOpeIcd sysOpeIcd);

   int deleteSysOpeIcdById(Long icdId);

   int deleteSysOpeIcdByIds(Long[] icdIds);

   List selectOpeIcdListByIcdCdList(List list);

   List selectOpeCommonList(SysOpeIcdVo sysOpeIcd);

   List selectOpeIcdAllList(SysOpeIcdVo sysOpeIcd);

   List selectOpeByOpeCdList(@Param("list") List operCdList);
}
