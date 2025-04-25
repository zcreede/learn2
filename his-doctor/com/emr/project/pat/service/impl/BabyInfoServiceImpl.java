package com.emr.project.pat.service.impl;

import com.emr.common.utils.SnowIdUtils;
import com.emr.framework.aspectj.lang.enums.DataSourceType;
import com.emr.framework.datasource.DruidUtil;
import com.emr.project.pat.domain.BabyInfo;
import com.emr.project.pat.mapper.BabyInfoMapper;
import com.emr.project.pat.service.IBabyInfoService;
import com.emr.project.system.domain.SyncDatasource;
import com.emr.project.system.domain.vo.SqlVo;
import com.emr.project.system.service.ISyncDatasourceService;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BabyInfoServiceImpl implements IBabyInfoService {
   protected final Logger log = LoggerFactory.getLogger(BabyInfoServiceImpl.class);
   @Autowired
   private BabyInfoMapper babyInfoMapper;
   @Autowired
   private ISyncDatasourceService syncDatasourceService;

   public BabyInfo selectBabyInfoById(String patientId) {
      return this.babyInfoMapper.selectBabyInfoById(patientId);
   }

   public List selectBabyInfoList(BabyInfo babyInfo) {
      return this.babyInfoMapper.selectBabyInfoList(babyInfo);
   }

   public List selectBabyInfoByDeptCodeList(BabyInfo babyInfo) {
      return this.babyInfoMapper.selectBabyInfoByDeptCodeList(babyInfo);
   }

   public int insertBabyInfo(BabyInfo babyInfo) {
      return this.babyInfoMapper.insertBabyInfo(babyInfo);
   }

   public int updateBabyInfo(BabyInfo babyInfo) {
      return this.babyInfoMapper.updateBabyInfo(babyInfo);
   }

   public int deleteBabyInfoByIds(String[] patientIds) {
      return this.babyInfoMapper.deleteBabyInfoByIds(patientIds);
   }

   public int deleteBabyInfoById(String patientId) {
      return this.babyInfoMapper.deleteBabyInfoById(patientId);
   }

   public List selectBabyTreeList(BabyInfo babyInfo) throws Exception {
      return this.babyInfoMapper.selectBabyTreeList(babyInfo);
   }

   public List selectBabyInfoListByPatientId(String patientId) throws Exception {
      return this.babyInfoMapper.selectBabyInfoListByPatientId(patientId);
   }

   public List selectBabyInfoListGroupByPat(BabyInfo babyInfo) throws Exception {
      return this.babyInfoMapper.selectBabyInfoListGroupByPat(babyInfo);
   }

   public List selectBabyInfoByDeptCodeListGroupByPat(BabyInfo babyInfo) throws Exception {
      return this.babyInfoMapper.selectBabyInfoByDeptCodeListGroupByPat(babyInfo);
   }

   public List selectHisBabyInfoList(List inpNoList) throws Exception {
      List<Map<String, Object>> list = null;

      try {
         SqlVo sqlVo = new SqlVo();
         SyncDatasource syncDatasource = this.syncDatasourceService.selectSyncDatasourceByCode(DataSourceType.hisBabyInfo.toString());
         sqlVo.setSqlStr(syncDatasource.getQuerySql());
         sqlVo.setInpNoList(inpNoList);
         DruidUtil.switchDB(syncDatasource);
         list = this.babyInfoMapper.selectHisBabyInfoList(sqlVo);
      } catch (Exception e) {
         this.log.error("查询PBHIS患者婴儿数据出现异常：", e);
         throw new Exception(e);
      } finally {
         DruidUtil.clearDataSource();
      }

      return list;
   }

   public void deleteBabyInfoByInpNo(List inpNoList) throws Exception {
      this.babyInfoMapper.deleteBabyInfoByInpNo(inpNoList);
   }

   public void insertHisBaby(List hisDataList) throws Exception {
      if (hisDataList != null && !hisDataList.isEmpty()) {
         hisDataList.forEach((t) -> {
            t.put("ID", SnowIdUtils.uniqueLong());
            t.put("OPERATOR_CODE", "admin");
            t.put("OPERATOR_NAME", "admin");
         });
         int i = 0;

         for(Map temp : hisDataList) {
            this.babyInfoMapper.insertMap(temp);
         }
      }

   }

   public List selectListByBabyAdmissionNoList(List admissionNoList) throws Exception {
      List<BabyInfo> list = null;
      if (CollectionUtils.isNotEmpty(admissionNoList)) {
         list = this.babyInfoMapper.selectListByBabyAdmissionNoList(admissionNoList);
      }

      return list;
   }
}
