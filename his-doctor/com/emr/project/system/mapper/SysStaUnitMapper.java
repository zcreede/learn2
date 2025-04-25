package com.emr.project.system.mapper;

import com.emr.project.system.domain.SysStaUnit;
import com.emr.project.system.domain.vo.SysStaUnitVo;
import java.util.List;

public interface SysStaUnitMapper {
   SysStaUnit selectSysStaUnitById(Long id);

   List selectSysStaUnitList(SysStaUnitVo sysStaUnitVo);

   int insertSysStaUnit(SysStaUnit sysStaUnit);

   int updateSysStaUnit(SysStaUnit sysStaUnit);

   int deleteSysStaUnitById(Long id);

   int deleteSysStaUnitByIds(Long[] ids);
}
