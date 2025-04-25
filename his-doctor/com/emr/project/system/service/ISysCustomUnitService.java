package com.emr.project.system.service;

import com.emr.project.system.domain.SysCustomSet;
import com.emr.project.system.domain.SysCustomUnit;
import java.util.List;

public interface ISysCustomUnitService {
   SysCustomUnit selectSysCustomUnitById(Long id);

   List selectSysCustomUnitList(SysCustomUnit sysCustomUnit);

   List selectSysCustomUnitListBySetId(Long setId);

   List updateCustomUnitList(Long setId);

   SysCustomUnit selectSysCustomUnitBySetIdAndFieldCd(Long setId, String fieldCd);

   int insertSysCustomUnit(SysCustomUnit sysCustomUnit);

   int updateSysCustomUnit(SysCustomUnit sysCustomUnit);

   int deleteSysCustomUnitByIds(Long[] ids);

   int deleteSysCustomUnitById(Long id);

   void deleteSysCustomUnitBySetId(Long setId);

   void addSysCustomUnit(SysCustomSet sysCustomSet) throws Exception;

   void changeValidFlag(Long id, String validFlag) throws Exception;
}
