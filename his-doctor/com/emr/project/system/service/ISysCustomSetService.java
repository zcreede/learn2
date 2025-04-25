package com.emr.project.system.service;

import com.emr.project.system.domain.SysCustomSet;
import java.util.List;

public interface ISysCustomSetService {
   SysCustomSet selectSysCustomSetById(Long id);

   List selectSysCustomSetList(SysCustomSet sysCustomSet);

   List selectSysCustomSetListBySetName(String setName);

   List selectSysCustomSetByTypeName(SysCustomSet sysCustomSet);

   void insertSysCustomSet(SysCustomSet sysCustomSet) throws Exception;

   int updateSysCustomSet(SysCustomSet sysCustomSet);

   int deleteSysCustomSetByIds(Long[] ids);

   int deleteSysCustomSetById(Long id) throws Exception;
}
