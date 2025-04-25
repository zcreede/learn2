package com.emr.project.pat.service.impl;

import com.emr.framework.web.service.ISyncService;
import com.emr.project.pat.domain.AppItem;
import com.emr.project.pat.mapper.AppItemMapper;
import com.emr.project.pat.service.IAppClinService;
import com.emr.project.pat.service.IAppItemService;
import com.emr.project.system.domain.vo.SqlVo;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AppItemServiceImpl implements IAppItemService, ISyncService {
   protected final Logger log = LoggerFactory.getLogger(AppItemServiceImpl.class);
   @Autowired
   private AppItemMapper appItemMapper;
   @Autowired
   private IAppClinService appClinService;

   public AppItem selectAppItemById(String appCd) {
      return this.appItemMapper.selectAppItemById(appCd);
   }

   public List selectAppItemList(AppItem appItem) {
      return this.appItemMapper.selectAppItemList(appItem);
   }

   public int insertAppItem(AppItem appItem) {
      return this.appItemMapper.insertAppItem(appItem);
   }

   public int updateAppItem(AppItem appItem) {
      return this.appItemMapper.updateAppItem(appItem);
   }

   public int deleteAppItemByIds(String[] appCds) {
      return this.appItemMapper.deleteAppItemByIds(appCds);
   }

   public int deleteAppItemById(String appCd) {
      return this.appItemMapper.deleteAppItemById(appCd);
   }

   public void updateStateDateByApp(String appCd, String clinItemCd, String itemState, Date clinRepDate) {
      AppItem appItem = new AppItem();
      appItem.setAppCd(appCd);
      appItem.setItemState(itemState);
      this.appItemMapper.updateAppItem(appItem);
      if (StringUtils.isNotBlank(clinItemCd)) {
         this.appClinService.updateStateDateByApp(appCd, clinItemCd, itemState, clinRepDate);
      }

   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void syncData(List hisList) throws Exception {
      int i = 0;

      for(Map temp : hisList) {
         this.log.info("i-> {}", i++);

         try {
            this.appItemMapper.insertMap(temp);
         } catch (Exception e) {
            for(String key : temp.keySet()) {
               this.log.info("对象数值===== key:{},value:{}", key, temp.get(key).toString());
            }

            this.log.error("同步检查检验申请单出现异常，", e.getMessage());
            throw new Exception(e.getMessage());
         }
      }

   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void syncAddData(List list, SqlVo sqlVo) throws Exception {
      this.syncData(list);
   }

   public void syncDataMap(List mapList, String tableName) throws Exception {
   }
}
