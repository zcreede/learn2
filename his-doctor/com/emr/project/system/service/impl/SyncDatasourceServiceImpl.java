package com.emr.project.system.service.impl;

import com.emr.common.utils.DateUtils;
import com.emr.common.utils.spring.SpringUtils;
import com.emr.framework.datasource.DruidUtil;
import com.emr.framework.web.service.ISyncService;
import com.emr.project.common.service.ICommonService;
import com.emr.project.monitor.domain.SysJobLog;
import com.emr.project.monitor.service.ISysJobLogService;
import com.emr.project.system.domain.SyncDatasource;
import com.emr.project.system.domain.vo.SqlVo;
import com.emr.project.system.mapper.SyncDatasourceMapper;
import com.emr.project.system.service.ISyncDatasourceService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SyncDatasourceServiceImpl implements ISyncDatasourceService {
   private static final Logger log = LoggerFactory.getLogger(SyncDatasourceServiceImpl.class);
   @Autowired
   private SyncDatasourceMapper syncDatasourceMapper;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private ISysJobLogService sysJobLogService;

   public SyncDatasource selectSyncDatasourceById(Long id) {
      return this.syncDatasourceMapper.selectSyncDatasourceById(id);
   }

   public SyncDatasource selectSyncDatasourceByCode(String syncCode) {
      SyncDatasource param = new SyncDatasource();
      param.setSyncCode(syncCode);
      return this.syncDatasourceMapper.selectSyncDatasource(param);
   }

   public List selectSyncDatasourceList(SyncDatasource syncDatasource) {
      return this.syncDatasourceMapper.selectSyncDatasourceList(syncDatasource);
   }

   public int insertSyncDatasource(SyncDatasource syncDatasource) {
      return this.syncDatasourceMapper.insertSyncDatasource(syncDatasource);
   }

   public int updateSyncDatasource(SyncDatasource syncDatasource) {
      return this.syncDatasourceMapper.updateSyncDatasource(syncDatasource);
   }

   public int deleteSyncDatasourceByIds(Long[] ids) {
      return this.syncDatasourceMapper.deleteSyncDatasourceByIds(ids);
   }

   public int deleteSyncDatasourceById(Long id) {
      return this.syncDatasourceMapper.deleteSyncDatasourceById(id);
   }

   public List syncDataByCode(String syncCode) throws Exception {
      log.info("同步数据的信息，syncCode={}", syncCode);
      SyncDatasource syncDatasource = this.selectSyncDatasourceByCode(syncCode);
      DruidUtil.switchDB(syncDatasource);
      SqlVo sqlVo = new SqlVo(syncDatasource.getQuerySql());
      String beanNamesStr = syncDatasource.getClassMethod();
      ISyncService syncService = (ISyncService)SpringUtils.getBean(beanNamesStr);
      switch (syncCode) {
         case "T_AR_BASEINFOMATION":
         case "T_AR_BASEINFOMATION_DAY":
         case "T_AR_BASEINFOMATION_H":
         case "T_AR_MEDICALINFORMATION":
         case "T_AR_MEDICALINFORMATION_DAY":
         case "T_AR_MEDICALINFORMATION_H":
            List<Map<String, Object>> map = null;

            try {
               map = this.syncDatasourceMapper.selectMapListNoWhere(sqlVo);
            } catch (Exception e) {
               throw new Exception(e);
            } finally {
               DruidUtil.clearDataSource();
            }

            if (map != null && !map.isEmpty()) {
               syncService.syncDataMap(map, syncCode);
            }
            break;
         default:
            List<Map<String, Object>> list = null;

            try {
               list = this.syncDatasourceMapper.selectMapList(sqlVo);
            } catch (Exception e) {
               throw new Exception(e);
            } finally {
               DruidUtil.clearDataSource();
            }

            if (list != null && !list.isEmpty()) {
               Map<String, Object> first = (Map)list.get(0);
               log.info("数据对象", first.toString());
               syncService.syncData(list);
            }
      }

      return null;
   }

   public void syncAddDataBySqlVoAndCode(String syncCode) throws Exception {
      SqlVo sqlVo = new SqlVo();
      SyncDatasource syncDatasource = this.selectSyncDatasourceByCode(syncCode);
      sqlVo.setSqlStr(syncDatasource.getQuerySql());
      switch (syncCode) {
         case "hisPersonalInfoAdd":
            this.setTableName("T_AR_BASEINFOMATION", sqlVo, syncCode);
            break;
         case "hisPersonalInfoDayAdd":
            this.setTableName("T_AR_BASEINFOMATION_DAY", sqlVo, syncCode);
            break;
         case "hisPersonalInfoHAdd":
            this.setTableName("T_AR_BASEINFOMATION_H", sqlVo, syncCode);
            break;
         case "hisVisitinfoAdd":
            this.setTableName("T_AR_MEDICALINFORMATION", sqlVo, syncCode);
            break;
         case "hisVisitinfoDayAdd":
            this.setTableName("T_AR_MEDICALINFORMATION_DAY", sqlVo, syncCode);
            break;
         case "hisVisitinfoHAdd":
            this.setTableName("T_AR_MEDICALINFORMATION_H", sqlVo, syncCode);
            break;
         default:
            this.setTableName(syncCode, sqlVo, syncCode);
      }

      DruidUtil.switchDB(syncDatasource);
      List<Map<String, Object>> list = this.syncDatasourceMapper.selectMapList(sqlVo);
      DruidUtil.clearDataSource();
      if (list != null && !list.isEmpty()) {
         Map<String, Object> first = (Map)list.get(0);
         log.info("同步数据的信息，syncCode={}", syncCode);

         for(String column : first.keySet()) {
            log.info("column名称：{},  值：{}", column, first.get(column));
         }

         String classMethod = syncDatasource.getClassMethod();
         ISyncService syncService = (ISyncService)SpringUtils.getBean(classMethod);
         syncService.syncAddData(list, sqlVo);
      }

   }

   public void setTableName(String tableName, SqlVo sqlVo, String syncCode) throws Exception {
      sqlVo.setTableName(tableName);
      String tagStr = "syncDatasourceServiceImpl.syncAddDataBySqlVoAndCode('" + syncCode + "')";
      SysJobLog sysJobLog = this.sysJobLogService.selectJobLogByTagStr(tagStr);
      Date endTime = this.commonService.getDbSysdate();
      Date beginTime = null;
      if (sysJobLog == null) {
         beginTime = DateUtils.addHours(endTime, -6);
      } else {
         beginTime = sysJobLog.getCreateTime();
      }

      sqlVo.setBeginDateTime(beginTime);
      sqlVo.setEndDateTime(endTime);
   }

   public List querySyncDataListByCode(SqlVo sqlVo, String syncCode) throws Exception {
      SyncDatasource syncDatasource = this.selectSyncDatasourceByCode(syncCode);
      DruidUtil.switchDB(syncDatasource);
      sqlVo.setSqlStr(syncDatasource.getQuerySql());
      List<Map<String, Object>> list = null;

      try {
         list = this.syncDatasourceMapper.selectMapList(sqlVo);
      } catch (Exception e) {
         throw new Exception(e);
      } finally {
         DruidUtil.clearDataSource();
      }

      return list;
   }

   public Map querySyncDataByCode(SqlVo sqlVo, String syncCode) throws Exception {
      SyncDatasource syncDatasource = this.selectSyncDatasourceByCode(syncCode);
      DruidUtil.switchDB(syncDatasource);
      sqlVo.setSqlStr(syncDatasource.getQuerySql());
      Map<String, Object> map = null;

      try {
         map = this.syncDatasourceMapper.selectMap(sqlVo);
      } catch (Exception e) {
         throw new Exception(e);
      } finally {
         DruidUtil.clearDataSource();
      }

      return map;
   }

   public List querySyncDataListByCodeNoWhere(SqlVo sqlVo, String syncCode) throws Exception {
      SyncDatasource syncDatasource = this.selectSyncDatasourceByCode(syncCode);
      DruidUtil.switchDB(syncDatasource);
      sqlVo.setSqlStr(syncDatasource.getQuerySql());
      List<Map<String, Object>> list = null;

      try {
         list = this.syncDatasourceMapper.selectMapListNoWhere(sqlVo);
      } catch (Exception e) {
         throw new Exception(e);
      } finally {
         DruidUtil.clearDataSource();
      }

      return list;
   }
}
