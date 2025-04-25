package com.emr.project.system.mapper;

import com.emr.project.system.domain.SyncDatasource;
import com.emr.project.system.domain.vo.SqlVo;
import java.util.List;
import java.util.Map;

public interface SyncDatasourceMapper {
   SyncDatasource selectSyncDatasourceById(Long id);

   List selectSyncDatasourceList(SyncDatasource syncDatasource);

   SyncDatasource selectSyncDatasource(SyncDatasource syncDatasource);

   int insertSyncDatasource(SyncDatasource syncDatasource);

   int updateSyncDatasource(SyncDatasource syncDatasource);

   int deleteSyncDatasourceById(Long id);

   int deleteSyncDatasourceByIds(Long[] ids);

   List selectMapList(SqlVo sqlVo);

   Map selectMap(SqlVo sqlVo);

   List selectMapsList(SqlVo sqlVo);

   List selectMapListNoWhere(SqlVo sqlVo);
}
