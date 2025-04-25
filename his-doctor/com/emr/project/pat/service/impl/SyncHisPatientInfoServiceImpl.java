package com.emr.project.pat.service.impl;

import com.emr.common.utils.DateUtils;
import com.emr.project.pat.service.IAlleInfoService;
import com.emr.project.pat.service.IBabyInfoService;
import com.emr.project.pat.service.IDiagInfoService;
import com.emr.project.pat.service.ISyncHisPatientInfoService;
import com.emr.project.pat.service.ITransinfoService;
import com.emr.project.system.service.ISysEmrConfigService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SyncHisPatientInfoServiceImpl implements ISyncHisPatientInfoService {
   protected final Logger log = LoggerFactory.getLogger(SyncHisPatientInfoServiceImpl.class);
   @Autowired
   private IDiagInfoService diagInfoService;
   @Autowired
   private IAlleInfoService alleInfoService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private ITransinfoService transinfoService;
   @Autowired
   private IBabyInfoService babyInfoService;

   @Async
   public void syncHisPatient(List inpNoList) throws Exception {
      this.log.info("selectPatientData========1111" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.sss", new Date()));

      try {
         List<Map<String, Object>> tranList = this.transinfoService.syncHisTransInfoLisT(inpNoList);
         this.log.info("selectPatientData========2222" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.sss", new Date()));
         List<Map<String, Object>> diagList = this.diagInfoService.syncHisDiagIInfoList(inpNoList);
         this.log.info("selectPatientData========3333" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.sss", new Date()));
         List<Map<String, Object>> alleInfoList = this.alleInfoService.syncHisAlleInfoList(inpNoList);
         this.log.info("selectPatientData========4444" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.sss", new Date()));
         List<Map<String, Object>> babyList = this.babyInfoService.selectHisBabyInfoList(inpNoList);
         this.log.info("selectPatientData========5555" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.sss", new Date()));
         this.selectHisPatList(tranList, diagList, alleInfoList, babyList, inpNoList);
         this.log.info("selectPatientData========6666" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.sss", new Date()));
      } catch (Exception e) {
         this.log.error("同步患者的其他信息出现异常：", e);
      }

   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void selectHisPatList(List tranList, List diagList, List alleInfoList, List babyList, List inpNoList) throws Exception {
      if (tranList != null && !tranList.isEmpty()) {
         this.transinfoService.deleteTransinfoByInpNoList(inpNoList);
         this.transinfoService.insertTranInfo(tranList);
         this.log.info("selectPatientData========5555=01" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.sss", new Date()));
      }

      if (diagList != null && !diagList.isEmpty()) {
         this.diagInfoService.deleteDiagInfoByPatientIds(inpNoList);
         this.diagInfoService.insertDiagInfoAdd(diagList);
         this.log.info("selectPatientData========5555=02" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.sss", new Date()));
      }

      if (alleInfoList != null && !alleInfoList.isEmpty()) {
         this.alleInfoService.deleteAlleInfoByInpNoList(inpNoList);
         this.alleInfoService.insertAlleInfoAdd(alleInfoList);
         this.log.info("selectPatientData========5555=03" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.sss", new Date()));
      }

      if (babyList != null && !babyList.isEmpty()) {
         this.babyInfoService.deleteBabyInfoByInpNo(inpNoList);
         this.babyInfoService.insertHisBaby(babyList);
         this.log.info("selectPatientData========5555=04" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.sss", new Date()));
      }

   }
}
