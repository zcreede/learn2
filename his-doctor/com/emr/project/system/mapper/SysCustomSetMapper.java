package com.emr.project.system.mapper;

import com.emr.project.system.domain.SysCustomSet;
import java.util.List;

public interface SysCustomSetMapper {
   SysCustomSet selectSysCustomSetById(Long id);

   List selectSysCustomSetList(SysCustomSet sysCustomSet);

   List selectSysCustomSetListBySetName(String setName);

   List selectSysCustomSetByTypeName(SysCustomSet sysCustomSet);

   int insertSysCustomSet(SysCustomSet sysCustomSet);

   int updateSysCustomSet(SysCustomSet sysCustomSet);

   int deleteSysCustomSetById(Long id);

   int deleteSysCustomSetByIds(Long[] ids);
}
