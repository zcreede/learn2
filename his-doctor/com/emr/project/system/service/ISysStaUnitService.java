package com.emr.project.system.service;

import com.emr.project.system.domain.SysStaUnit;
import com.emr.project.system.domain.vo.SysStaUnitVo;
import java.util.List;

public interface ISysStaUnitService {
   SysStaUnit selectSysStaUnitById(Long id);

   List selectSysStaUnitList(SysStaUnitVo sysStaUnitVo) throws Exception;

   int insertSysStaUnit(SysStaUnit sysStaUnit);

   int updateSysStaUnit(SysStaUnit sysStaUnit);

   int deleteSysStaUnitByIds(Long[] ids);

   int deleteSysStaUnitById(Long id);
}
