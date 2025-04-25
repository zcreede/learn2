package com.emr.project.emr.mapper;

import com.emr.project.emr.domain.SysEmrLog;
import com.emr.project.emr.domain.vo.SysEmrLogVo;
import java.util.List;

public interface SysEmrLogMapper {
   SysEmrLog selectSysEmrLogById(Long id);

   List selectSysEmrLogByMrFileId(Long mrFileId, String[] optTypeList);

   List selectSysEmrLogList(SysEmrLogVo sysEmrLogVo);

   int insertSysEmrLog(SysEmrLog sysEmrLog);

   void insertSysEmrLogList(List sysEmrLogList);

   int updateSysEmrLog(SysEmrLog sysEmrLog);

   int deleteSysEmrLogById(Long id);

   int deleteSysEmrLogByIds(Long[] ids);
}
