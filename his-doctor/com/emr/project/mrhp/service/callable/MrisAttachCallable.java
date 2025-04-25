package com.emr.project.mrhp.service.callable;

import com.alibaba.fastjson.JSON;
import com.emr.common.utils.StringUtils;
import com.emr.framework.datasource.DruidUtil;
import com.emr.project.mrhp.domain.MrHpDrawField;
import com.emr.project.mrhp.domain.MrHpDrawMain;
import com.emr.project.mrhp.domain.mris.TdCmAttachSave;
import com.emr.project.mrhp.service.ITmDsPreserveOutService;
import com.emr.project.system.domain.SyncDatasource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;

public class MrisAttachCallable implements Callable {
   private String recordNo;
   private String inpNo;
   private Integer visitId;
   private ITmDsPreserveOutService tmDsPreserveOutService;
   private List mrHpDrawMainList;
   private List fieldList;

   public MrisAttachCallable(String recordNo, String inpNo, Integer visitId, ITmDsPreserveOutService tmDsPreserveOutService, List mrHpDrawMainList, List fieldList) {
      this.recordNo = recordNo;
      this.inpNo = inpNo;
      this.visitId = visitId;
      this.tmDsPreserveOutService = tmDsPreserveOutService;
      this.mrHpDrawMainList = mrHpDrawMainList;
      this.fieldList = fieldList;
   }

   public TdCmAttachSave call() throws Exception {
      TdCmAttachSave tdCmAttachSave = new TdCmAttachSave();
      Map<Long, List<MrHpDrawField>> mainIdFieldMap = new HashMap();
      if (!this.fieldList.isEmpty()) {
         mainIdFieldMap = (Map)this.fieldList.stream().collect(Collectors.groupingBy(MrHpDrawField::getMainId));
      }

      Map<String, Object> newResult = new HashMap();

      for(MrHpDrawMain main : this.mrHpDrawMainList) {
         Long mainId = main.getId();
         String datasourceSyncCode = main.getDatasourceSyncCode();
         String sqlStatement = main.getSqlStatement();
         if (StringUtils.isNotEmpty(sqlStatement)) {
            sqlStatement = sqlStatement.replaceAll("@RECORD_NO@", "'" + this.recordNo + "'");
            sqlStatement = sqlStatement.replaceAll("@INP_NO@", "'" + this.inpNo + "'");
            sqlStatement = sqlStatement.replaceAll("@VISIT_ID@", "'" + this.visitId + "'");
         }

         new HashMap();

         Map result;
         try {
            SyncDatasource syncDatasource = this.tmDsPreserveOutService.selectSyncDatasourceByCode(datasourceSyncCode);
            DruidUtil.switchDB(syncDatasource);
            result = this.tmDsPreserveOutService.selectSql(sqlStatement);
         } finally {
            DruidUtil.clearDataSource();
         }

         if (result != null) {
            List<MrHpDrawField> drawFieldList = (List)mainIdFieldMap.get(mainId);
            if (!drawFieldList.isEmpty()) {
               for(String key : result.keySet()) {
                  Object o = result.get(key);
                  MrHpDrawField mrHpDrawField = (MrHpDrawField)drawFieldList.stream().filter((t) -> t.getField().toLowerCase().trim().equals(key.toLowerCase())).findFirst().orElse((Object)null);
                  if (mrHpDrawField != null) {
                     String hisDbField = mrHpDrawField.getHisDbField();
                     if (StringUtils.isNotEmpty(hisDbField)) {
                        newResult.put(hisDbField, o);
                     } else {
                        newResult.put(key, o);
                     }
                  } else {
                     newResult.put(key, o);
                  }
               }
            } else {
               newResult = result;
            }
         }
      }

      String ss = JSON.toJSONString(newResult);
      TdCmAttachSave newMrHpAttach = (TdCmAttachSave)JSON.parseObject(ss, TdCmAttachSave.class);
      BeanUtils.copyProperties(newMrHpAttach, tdCmAttachSave);
      tdCmAttachSave.setPatient_id(this.inpNo);
      tdCmAttachSave.setRecord_id(this.recordNo);
      return tdCmAttachSave;
   }

   public String getRecordNo() {
      return this.recordNo;
   }

   public void setRecordNo(String recordNo) {
      this.recordNo = recordNo;
   }

   public String getInpNo() {
      return this.inpNo;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public Integer getVisitId() {
      return this.visitId;
   }

   public void setVisitId(Integer visitId) {
      this.visitId = visitId;
   }

   public List getMrHpDrawMainList() {
      return this.mrHpDrawMainList;
   }

   public void setMrHpDrawMainList(List mrHpDrawMainList) {
      this.mrHpDrawMainList = mrHpDrawMainList;
   }

   public List getFieldList() {
      return this.fieldList;
   }

   public void setFieldList(List fieldList) {
      this.fieldList = fieldList;
   }

   public ITmDsPreserveOutService getTmDsPreserveOutService() {
      return this.tmDsPreserveOutService;
   }

   public void setTmDsPreserveOutService(ITmDsPreserveOutService tmDsPreserveOutService) {
      this.tmDsPreserveOutService = tmDsPreserveOutService;
   }
}
