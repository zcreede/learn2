package com.emr.project.pat.service.impl;

import com.emr.common.utils.SnowIdUtils;
import com.emr.framework.aspectj.lang.annotation.DataSource;
import com.emr.framework.aspectj.lang.enums.DataSourceType;
import com.emr.framework.datasource.DruidUtil;
import com.emr.framework.web.service.ISyncService;
import com.emr.project.pat.domain.AlleInfo;
import com.emr.project.pat.domain.TdNaAllergyRecord;
import com.emr.project.pat.domain.vo.AlleInfoVo;
import com.emr.project.pat.mapper.AlleInfoMapper;
import com.emr.project.pat.service.IAlleInfoService;
import com.emr.project.system.domain.SyncDatasource;
import com.emr.project.system.domain.SysDictData;
import com.emr.project.system.domain.vo.SqlVo;
import com.emr.project.system.service.ISyncDatasourceService;
import com.emr.project.system.service.ISysDictDataService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlleInfoServiceImpl implements IAlleInfoService, ISyncService {
   protected final Logger log = LoggerFactory.getLogger(AlleInfoServiceImpl.class);
   @Autowired
   private AlleInfoMapper alleInfoMapper;
   @Autowired
   private ISysDictDataService sysDictDataService;
   @Autowired
   private ISyncDatasourceService syncDatasourceService;

   public List selectAlleInfoByPatientId(String recordNo) throws Exception {
      List<AlleInfo> list = this.alleInfoMapper.selectAlleInfoByPatientMainId(recordNo);
      return list;
   }

   public List selectAlleInfoList(AlleInfo alleInfo) throws Exception {
      return this.alleInfoMapper.selectAlleInfoList(alleInfo);
   }

   public int insertAlleInfo(AlleInfo alleInfo) {
      return this.alleInfoMapper.insertAlleInfo(alleInfo);
   }

   public void insertAlleInfoList(List alleInfoList) throws Exception {
      this.alleInfoMapper.insertAlleInfoList(alleInfoList);
   }

   public int updateAlleInfo(AlleInfo alleInfo) {
      return this.alleInfoMapper.updateAlleInfo(alleInfo);
   }

   public int deleteAlleInfoByIds(String[] ids) {
      return this.alleInfoMapper.deleteAlleInfoByIds(ids);
   }

   public int deleteAlleInfoById(String id) {
      return this.alleInfoMapper.deleteAlleInfoById(id);
   }

   public void deleteAlleInfoByPatientId(String patientId) {
      this.alleInfoMapper.deleteAlleInfoByPatientId(patientId);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void syncData(List hisList) throws Exception {
      this.alleInfoMapper.deleteAlleInfo();
      this.insertAlleInfoAdd(hisList);
   }

   public void insertAlleInfoAdd(List hisList) throws Exception {
      hisList.forEach((t) -> {
         t.put("id", SnowIdUtils.uniqueLong());
         t.put("cre_per_code", "admin");
         t.put("cre_per_name", "admin");
      });
      int i = 0;

      for(Map temp : hisList) {
         this.log.info("i-> {}", i++);

         try {
            this.alleInfoMapper.insertMap(temp);
         } catch (Exception e) {
            this.log.info("对象数值=====  {}", temp.toString());
            this.log.error("同步过敏信息出现异常", e.getMessage());
            throw new Exception(e.getMessage());
         }
      }

   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void syncAddData(List list, SqlVo sqlVo) throws Exception {
      this.insertAlleInfoAdd(list);
   }

   public void syncDataMap(List mapList, String tableName) throws Exception {
   }

   public List selectAlleDictList() throws Exception {
      List<SysDictData> dictDataList = this.sysDictDataService.selectDictDataByType("CV05.01.038");
      List<AlleInfoVo> resultList = new ArrayList();

      for(SysDictData sysDictData : dictDataList) {
         AlleInfoVo alleInfoVo = new AlleInfoVo();
         alleInfoVo.setAlleCode(sysDictData.getDictValue());
         alleInfoVo.setAlleType(sysDictData.getRemark());
         alleInfoVo.setInputstrphtc(sysDictData.getInputstrphtc());
         alleInfoVo.setAlleName(sysDictData.getDictLabel());
         resultList.add(alleInfoVo);
      }

      return resultList;
   }

   public List selectAlleInfosByInpNos(List list) throws Exception {
      List<TdNaAllergyRecord> resList = null;
      if (list != null && !list.isEmpty()) {
         resList = this.alleInfoMapper.selectAlleInfosByInpNos(list);
      }

      return resList;
   }

   public List selectAlleInfosByInpNoOrMainId(List param) throws Exception {
      List<AlleInfo> resList = null;
      if (CollectionUtils.isNotEmpty(param)) {
         resList = this.alleInfoMapper.selectAlleInfosByInpNoOrMainId(param);
      }

      return resList;
   }

   public List selectByInpNoAndDrugCode(String admissionNo, String allergyCode) throws Exception {
      return this.alleInfoMapper.selectByInpNoAndDrugCode(admissionNo, allergyCode);
   }

   @DataSource(DataSourceType.hisPatAlle)
   public List selectHisAlleInfoList(SqlVo sqlVo) throws Exception {
      return this.alleInfoMapper.selectHisAlleInfoList(sqlVo);
   }

   public List syncHisAlleInfoList(List inpNoList) throws Exception {
      List<Map<String, Object>> list = null;

      try {
         SqlVo sqlVo = new SqlVo();
         SyncDatasource syncDatasource = this.syncDatasourceService.selectSyncDatasourceByCode(DataSourceType.hisPatAlle.toString());
         sqlVo.setSqlStr(syncDatasource.getQuerySql());
         sqlVo.setInpNoList(inpNoList);
         DruidUtil.switchDB(syncDatasource);
         list = this.alleInfoMapper.selectHisAlleInfoList(sqlVo);
      } catch (Exception e) {
         this.log.error("查询PBHIS患者过敏数据出现异常：", e);
         throw new Exception(e);
      } finally {
         DruidUtil.clearDataSource();
      }

      return list;
   }

   public void deleteAlleInfoByInpNoList(List inpNoList) throws Exception {
      this.alleInfoMapper.deleteAlleInfoByInpNoList(inpNoList);
   }
}
