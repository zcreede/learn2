package com.emr.project.system.service;

import com.emr.project.system.domain.SysShareElem;
import com.emr.project.system.domain.vo.SysShareElemVo;
import java.util.List;

public interface ISysShareElemService {
   SysShareElem selectSysShareElemById(Long id);

   List selectSysShareElemList(SysShareElem sysShareElem);

   int insertSysShareElem(SysShareElem sysShareElem);

   int updateSysShareElem(SysShareElem sysShareElem);

   int deleteSysShareElemByIds(Long[] ids);

   int deleteSysShareElemById(Long id);

   List selectSysShareElemByPatientId(SysShareElemVo sysShareElemVo) throws Exception;
}
