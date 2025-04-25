package com.emr.project.system.mapper;

import com.emr.project.system.domain.SysCustomUnit;
import java.util.List;

public interface SysCustomUnitMapper {
   SysCustomUnit selectSysCustomUnitById(Long id);

   List selectSysCustomUnitList(SysCustomUnit sysCustomUnit);

   List selectSysCustomUnitListBySetId(Long setId);

   SysCustomUnit selectSysCustomUnitBySetIdAndFieldCd(Long setId, String fieldCd);

   int insertSysCustomUnit(SysCustomUnit sysCustomUnit);

   int updateSysCustomUnit(SysCustomUnit sysCustomUnit);

   int deleteSysCustomUnitById(Long id);

   int deleteSysCustomUnitByIds(Long[] ids);

   void deleteSysCustomUnitBySetId(Long setId);

   void batchSysCustomUnit(List list);

   void batchEditSysCustomUnit(List list);
}
