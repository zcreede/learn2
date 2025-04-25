package com.emr.project.system.service;

import com.emr.project.system.domain.SyncDatasource;
import com.emr.project.system.domain.vo.SqlVo;
import java.util.List;
import java.util.Map;

public interface ISyncDatasourceService {
   SyncDatasource selectSyncDatasourceById(Long id);

   SyncDatasource selectSyncDatasourceByCode(String syncCode);

   List selectSyncDatasourceList(SyncDatasource syncDatasource);

   int insertSyncDatasource(SyncDatasource syncDatasource);

   int updateSyncDatasource(SyncDatasource syncDatasource);

   int deleteSyncDatasourceByIds(Long[] ids);

   int deleteSyncDatasourceById(Long id);

   List syncDataByCode(String syncCode) throws Exception;

   void syncAddDataBySqlVoAndCode(String syncCode) throws Exception;

   List querySyncDataListByCode(SqlVo sqlVo, String syncCode) throws Exception;

   Map querySyncDataByCode(SqlVo sqlVo, String syncCode) throws Exception;

   List querySyncDataListByCodeNoWhere(SqlVo sqlVo, String syncCode) throws Exception;
}
