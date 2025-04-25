package com.emr.project.system.service;

import com.emr.project.system.domain.SysElemStrstore;
import java.util.List;

public interface ISysElemStrstoreService {
   SysElemStrstore selectSysElemStrstoreById(Long id);

   List selectSysElemStrstoreList(SysElemStrstore sysElemStrstore);

   int insertSysElemStrstore(SysElemStrstore sysElemStrstore);

   int updateSysElemStrstore(SysElemStrstore sysElemStrstore);

   int deleteSysElemStrstoreByIds(Long[] ids);

   int deleteSysElemStrstoreById(Long id);
}
