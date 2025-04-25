package com.emr.project.emr.service;

import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.SubfileIndex;
import com.emr.project.emr.domain.SysEmrLog;
import com.emr.project.emr.domain.vo.InsertIndexLogVo;
import com.emr.project.emr.domain.vo.SysEmrLogVo;
import java.util.List;

public interface ISysEmrLogService {
   SysEmrLog selectSysEmrLogById(Long id);

   List selectSysEmrLogByMrFileId(Long mrFileId, String[] optType) throws Exception;

   List selectSysEmrLogList(SysEmrLogVo sysEmrLogVo);

   void insertSysEmrLog(List sysEmrLogList) throws Exception;

   void insertSysEmrLog(Index index, SubfileIndex subfileIndex, String optType, String optTypeName, String ip) throws Exception;

   int updateSysEmrLog(SysEmrLog sysEmrLog);

   int deleteSysEmrLogByIds(Long[] ids);

   int deleteSysEmrLogById(Long id);

   void insertSysLog(InsertIndexLogVo vo, String ipAddress) throws Exception;
}
