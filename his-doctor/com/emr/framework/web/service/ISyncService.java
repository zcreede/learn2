package com.emr.framework.web.service;

import com.emr.project.system.domain.vo.SqlVo;
import java.util.List;

public interface ISyncService {
   void syncData(List hisList) throws Exception;

   void syncAddData(List list, SqlVo sqlVo) throws Exception;

   void syncDataMap(List mapList, String tableName) throws Exception;
}
