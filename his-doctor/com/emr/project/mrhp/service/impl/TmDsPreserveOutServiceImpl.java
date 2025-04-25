package com.emr.project.mrhp.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.framework.datasource.DruidUtil;
import com.emr.project.mrhp.domain.MrHpDrawApi;
import com.emr.project.mrhp.domain.TmDsPreserveOut;
import com.emr.project.mrhp.domain.req.TmDsPreserveOutReq;
import com.emr.project.mrhp.mapper.TmDsPreserveOutMapper;
import com.emr.project.mrhp.service.ITmDsPreserveOutService;
import com.emr.project.system.domain.SyncDatasource;
import com.emr.project.system.domain.SysUser;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TmDsPreserveOutServiceImpl implements ITmDsPreserveOutService {
   protected final Logger logger = LoggerFactory.getLogger(this.getClass());
   @Autowired
   private TmDsPreserveOutMapper tmDsPreserveOutMapper;

   public TmDsPreserveOut selectTmDsPreserveOutById(Long id) {
      return this.tmDsPreserveOutMapper.selectTmDsPreserveOutById(id);
   }

   public List selectTmDsPreserveOutList(TmDsPreserveOut tmDsPreserveOut) {
      return this.tmDsPreserveOutMapper.selectTmDsPreserveOutList(tmDsPreserveOut);
   }

   public int insertTmDsPreserveOut(TmDsPreserveOut tmDsPreserveOut) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      tmDsPreserveOut.setId(SnowIdUtils.uniqueLong());
      tmDsPreserveOut.setOrgCd(user.getHospital().getOrgCode());
      tmDsPreserveOut.setCrePerCode(user.getUserName());
      tmDsPreserveOut.setCrePerName(user.getNickName());
      return this.tmDsPreserveOutMapper.insertTmDsPreserveOut(tmDsPreserveOut);
   }

   public int updateTmDsPreserveOut(TmDsPreserveOut tmDsPreserveOut) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      tmDsPreserveOut.setAltPerCode(user.getUserName());
      tmDsPreserveOut.setAltPerName(user.getNickName());
      return this.tmDsPreserveOutMapper.updateTmDsPreserveOut(tmDsPreserveOut);
   }

   public int deleteTmDsPreserveOutByIds(Long[] ids) {
      return this.tmDsPreserveOutMapper.deleteTmDsPreserveOutByIds(ids);
   }

   public int deleteTmDsPreserveOutById(Long id) {
      return this.tmDsPreserveOutMapper.deleteTmDsPreserveOutById(id);
   }

   public List testConn(TmDsPreserveOutReq tmDsPreserveOut) {
      List<Map<Object, Object>> mapList = new ArrayList();
      List<MrHpDrawApi> mrHpDrawApiList = tmDsPreserveOut.getMrHpDrawApiList();
      String sqlStr = tmDsPreserveOut.getSqlStr();
      if (CollectionUtils.isNotEmpty(mrHpDrawApiList) && mrHpDrawApiList.size() > 0) {
         for(MrHpDrawApi mrHpDrawApi : mrHpDrawApiList) {
            if ("String".equals(mrHpDrawApi.getParameterType())) {
               sqlStr = sqlStr.replaceAll(mrHpDrawApi.getHisParameter(), "'" + mrHpDrawApi.getParameter() + "'");
            } else if ("Integer".equals(mrHpDrawApi.getParameterType())) {
               sqlStr = sqlStr.replaceAll(mrHpDrawApi.getHisParameter(), mrHpDrawApi.getParameter());
            } else if ("Date".equals(mrHpDrawApi.getParameterType())) {
               sqlStr = sqlStr.replaceAll(mrHpDrawApi.getHisParameter(), "to_Date('" + mrHpDrawApi.getParameter() + "','yyyy-MM-dd')");
            } else if ("DateTime".equals(mrHpDrawApi.getParameterType())) {
               sqlStr = sqlStr.replaceAll(mrHpDrawApi.getHisParameter(), "to_Date('" + mrHpDrawApi.getParameter() + "','yyyy-MM-dd HH:mi:ss')");
            }
         }
      }

      SyncDatasource syncDatasource = new SyncDatasource();
      syncDatasource.setDatabaseDcn(tmDsPreserveOut.getDriverClass());
      syncDatasource.setDatabaseUrl(tmDsPreserveOut.getDatabaseUrl());
      syncDatasource.setDatabaseUsername(tmDsPreserveOut.getUserName());
      syncDatasource.setDatabasePassword(tmDsPreserveOut.getPassward());
      syncDatasource.setDatabaseType(tmDsPreserveOut.getDatabaseType());
      syncDatasource.setSyncCode(SnowIdUtils.uniqueLongHex());

      try {
         DruidUtil.switchDB(syncDatasource);
         mapList = this.tmDsPreserveOutMapper.testConnSql(sqlStr);
         DruidUtil.clearDataSource();
         DruidUtil.releaseDataSource(syncDatasource.getSyncCode());
      } catch (Exception e) {
         this.logger.error("测试外部数据源链接出现异常,", e);
         DruidUtil.clearDataSource();
         DruidUtil.releaseDataSource(syncDatasource.getSyncCode());
      }

      return mapList;
   }

   public SyncDatasource selectSyncDatasourceByCode(String datasourceSyncCode) {
      TmDsPreserveOut tmDsPreserveOut = new TmDsPreserveOut();
      tmDsPreserveOut.setDataSourceTag(datasourceSyncCode);
      List<TmDsPreserveOut> list = this.tmDsPreserveOutMapper.selectTmDsPreserveOutList(tmDsPreserveOut);
      SyncDatasource syncDatasource = new SyncDatasource();
      if (!list.isEmpty()) {
         TmDsPreserveOut preserveOut = (TmDsPreserveOut)list.get(0);
         syncDatasource.setDatabaseDcn(preserveOut.getDriverClass());
         syncDatasource.setDatabaseUrl(preserveOut.getDatabaseUrl());
         syncDatasource.setDatabaseUsername(preserveOut.getUserName());
         syncDatasource.setDatabasePassword(preserveOut.getPassward());
         syncDatasource.setDatabaseType(preserveOut.getDatabaseType());
         syncDatasource.setSyncCode(SnowIdUtils.uniqueLongHex());
      }

      return syncDatasource;
   }

   public Map selectSql(String sqlStatement) {
      return this.tmDsPreserveOutMapper.selectSql(sqlStatement);
   }
}
