package com.emr.project.system.mapper;

import com.emr.project.system.domain.SysShareElem;
import com.emr.project.system.domain.vo.SysShareElemVo;
import java.util.List;

public interface SysShareElemMapper {
   SysShareElem selectSysShareElemById(Long id);

   List selectSysShareElemList(SysShareElem sysShareElem);

   int insertSysShareElem(SysShareElem sysShareElem);

   int updateSysShareElem(SysShareElem sysShareElem);

   int deleteSysShareElemById(Long id);

   int deleteSysShareElemByIds(Long[] ids);

   List selectSysShareElemByPatientId(SysShareElemVo sysShareElemVo);
}
