package com.emr.project.system.mapper;

import com.emr.project.system.domain.SysElemStrstore;
import java.util.List;

public interface SysElemStrstoreMapper {
   SysElemStrstore selectSysElemStrstoreById(Long id);

   List selectSysElemStrstoreList(SysElemStrstore sysElemStrstore);

   int insertSysElemStrstore(SysElemStrstore sysElemStrstore);

   int updateSysElemStrstore(SysElemStrstore sysElemStrstore);

   int deleteSysElemStrstoreById(Long id);

   int deleteSysElemStrstoreByIds(Long[] ids);
}
