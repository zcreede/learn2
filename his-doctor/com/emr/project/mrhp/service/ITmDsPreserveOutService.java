package com.emr.project.mrhp.service;

import com.emr.project.mrhp.domain.TmDsPreserveOut;
import com.emr.project.mrhp.domain.req.TmDsPreserveOutReq;
import com.emr.project.system.domain.SyncDatasource;
import java.util.List;
import java.util.Map;

public interface ITmDsPreserveOutService {
   TmDsPreserveOut selectTmDsPreserveOutById(Long id);

   List selectTmDsPreserveOutList(TmDsPreserveOut tmDsPreserveOut);

   int insertTmDsPreserveOut(TmDsPreserveOut tmDsPreserveOut);

   int updateTmDsPreserveOut(TmDsPreserveOut tmDsPreserveOut);

   int deleteTmDsPreserveOutByIds(Long[] ids);

   int deleteTmDsPreserveOutById(Long id);

   List testConn(TmDsPreserveOutReq tmDsPreserveOut);

   SyncDatasource selectSyncDatasourceByCode(String datasourceSyncCode);

   Map selectSql(String sqlStatement);
}
