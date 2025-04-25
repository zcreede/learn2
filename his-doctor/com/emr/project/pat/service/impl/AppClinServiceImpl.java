package com.emr.project.pat.service.impl;

import com.emr.framework.web.service.ISyncService;
import com.emr.project.pat.domain.AppClin;
import com.emr.project.pat.mapper.AppClinMapper;
import com.emr.project.pat.service.IAppClinService;
import com.emr.project.system.domain.vo.SqlVo;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppClinServiceImpl implements IAppClinService, ISyncService {
   protected final Logger log = LoggerFactory.getLogger(AppClinServiceImpl.class);
   @Autowired
   private AppClinMapper appClinMapper;

   public AppClin selectAppClinById(Long id) {
      return this.appClinMapper.selectAppClinById(id);
   }

   public List selectAppClinList(AppClin appClin) {
      return this.appClinMapper.selectAppClinList(appClin);
   }

   public int insertAppClin(AppClin appClin) {
      return this.appClinMapper.insertAppClin(appClin);
   }

   public int updateAppClin(AppClin appClin) {
      return this.appClinMapper.updateAppClin(appClin);
   }

   public void updateStateDateByApp(String appCd, String clinItemCd, String itemState, Date clinRepDate) {
      AppClin appClin = new AppClin();
      appClin.setAppCd(appCd);
      appClin.setClinItemCd(clinItemCd);
      appClin.setItemState(itemState);
      appClin.setClinRepDate(clinRepDate);
      this.appClinMapper.updateStateDateByApp(appClin);
   }

   public int deleteAppClinByIds(Long[] ids) {
      return this.appClinMapper.deleteAppClinByIds(ids);
   }

   public int deleteAppClinById(Long id) {
      return this.appClinMapper.deleteAppClinById(id);
   }

   public void syncData(List hisList) throws Exception {
      int i = 0;

      for(Map temp : hisList) {
         this.log.info("i-> {}", i++);

         try {
            this.appClinMapper.insertMap(temp);
         } catch (Exception e) {
            for(String key : temp.keySet()) {
               this.log.info("对象数值===== key:{},value:{}", key, temp.get(key).toString());
            }

            this.log.error("同步检查检验申请单临床项目表出现异常，", e.getMessage());
            throw new Exception(e.getMessage());
         }
      }

   }

   public void syncAddData(List list, SqlVo sqlVo) throws Exception {
      this.syncData(list);
   }

   public void syncDataMap(List mapList, String tableName) throws Exception {
   }
}
