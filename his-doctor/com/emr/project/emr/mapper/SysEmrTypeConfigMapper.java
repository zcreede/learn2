package com.emr.project.emr.mapper;

import com.emr.project.emr.domain.SysEmrTypeConfig;
import com.emr.project.emr.domain.vo.SysEmrTypeConfigVo;
import java.util.List;

public interface SysEmrTypeConfigMapper {
   SysEmrTypeConfig selectSysEmrTypeConfigById(Long id);

   SysEmrTypeConfigVo selectSysEmrTypeConfigByTypeId(String emrTypeCode);

   List selectSysEmrTypeConfigList(SysEmrTypeConfig sysEmrTypeConfig);

   List selectListByNameAndClass(SysEmrTypeConfigVo sysEmrTypeConfigVo);

   int insertSysEmrTypeConfig(SysEmrTypeConfig sysEmrTypeConfig);

   int updateSysEmrTypeConfig(SysEmrTypeConfig sysEmrTypeConfig);

   int deleteSysEmrTypeConfigById(Long id);

   int deleteSysEmrTypeConfigByIds(Long[] ids);
}
