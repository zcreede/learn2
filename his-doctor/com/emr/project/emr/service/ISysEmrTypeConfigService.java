package com.emr.project.emr.service;

import com.emr.project.emr.domain.SysEmrTypeConfig;
import com.emr.project.emr.domain.vo.SysEmrTypeConfigVo;
import java.util.List;

public interface ISysEmrTypeConfigService {
   SysEmrTypeConfig selectSysEmrTypeConfigById(Long id);

   List selectSysEmrTypeConfigList(SysEmrTypeConfig sysEmrTypeConfig);

   List selectListByNameAndClass(String emrClassCode, String emrTypeName);

   int insertSysEmrTypeConfig(SysEmrTypeConfig sysEmrTypeConfig);

   int updateSysEmrTypeConfig(SysEmrTypeConfig sysEmrTypeConfig);

   int deleteSysEmrTypeConfigByIds(Long[] ids);

   int deleteSysEmrTypeConfigById(Long id);

   SysEmrTypeConfigVo selectSysEmrTypeConfigByTypeId(String emrTypeCode);
}
