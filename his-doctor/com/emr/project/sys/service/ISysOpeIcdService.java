package com.emr.project.sys.service;

import com.emr.project.sys.domain.SysOpeIcd;
import com.emr.project.sys.domain.vo.SysOpeIcdVo;
import java.util.List;

public interface ISysOpeIcdService {
   SysOpeIcd selectSysOpeIcdById(Long icdId);

   List selectSysOpeIcdList(SysOpeIcd sysOpeIcd) throws Exception;

   List selectOpeIcdList(SysOpeIcd sysOpeIcd) throws Exception;

   int insertSysOpeIcd(SysOpeIcd sysOpeIcd);

   int updateSysOpeIcd(SysOpeIcd sysOpeIcd);

   int deleteSysOpeIcdByIds(Long[] icdIds);

   int deleteSysOpeIcdById(Long icdId);

   List selectOpeIcdListByIcdCdList(List icdCdList) throws Exception;

   List selectDiagCommonList(SysOpeIcdVo sysOpeIcd) throws Exception;

   List selectOpeIcdAllList(SysOpeIcdVo sysOpeIcd) throws Exception;
}
