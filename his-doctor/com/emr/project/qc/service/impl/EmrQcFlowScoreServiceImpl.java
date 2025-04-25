package com.emr.project.qc.service.impl;

import com.emr.common.constant.CommonConstants;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.common.service.ICommonService;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.qc.domain.EmrQcFlow;
import com.emr.project.qc.domain.EmrQcFlowRecord;
import com.emr.project.qc.domain.EmrQcFlowScore;
import com.emr.project.qc.domain.EmrQcPresVeri;
import com.emr.project.qc.domain.QcScoreDedDetail;
import com.emr.project.qc.domain.QcScoreGrade;
import com.emr.project.qc.domain.vo.EmrQcFlowScoreListVo;
import com.emr.project.qc.domain.vo.EmrQcFlowScoreVo;
import com.emr.project.qc.domain.vo.EmrQcListVo;
import com.emr.project.qc.domain.vo.QcScoreDedDetailVo;
import com.emr.project.qc.domain.vo.QcScoreDedRuleVo;
import com.emr.project.qc.domain.vo.QcScoreItemVo;
import com.emr.project.qc.domain.vo.QcScoreScheDedVo;
import com.emr.project.qc.mapper.EmrQcFlowRecordMapper;
import com.emr.project.qc.mapper.EmrQcFlowScoreListMapper;
import com.emr.project.qc.mapper.EmrQcFlowScoreMapper;
import com.emr.project.qc.mapper.EmrQcListMapper;
import com.emr.project.qc.service.IEmrQcFlowScoreService;
import com.emr.project.qc.service.IEmrQcFlowService;
import com.emr.project.qc.service.IEmrQcListScoreService;
import com.emr.project.qc.service.IEmrQcPresVeriService;
import com.emr.project.qc.service.IQcScoreDedRuleService;
import com.emr.project.qc.service.IQcScoreGradeService;
import com.emr.project.qc.service.IQcScoreScheDedService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysOrg;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysOrgService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class EmrQcFlowScoreServiceImpl implements IEmrQcFlowScoreService {
   protected final Logger log = LoggerFactory.getLogger(EmrQcFlowScoreServiceImpl.class);
   @Autowired
   private EmrQcFlowScoreMapper emrQcFlowScoreMapper;
   @Autowired
   private EmrQcFlowScoreListMapper emrQcFlowScoreListMapper;
   @Autowired
   private EmrQcListMapper emrQcListMapper;
   @Autowired
   private IQcScoreGradeService qcScoreGradeService;
   @Autowired
   private IQcScoreScheDedService qcScoreScheDedService;
   @Autowired
   private IQcScoreDedRuleService qcScoreDedRuleService;
   @Autowired
   private ISysOrgService sysOrgService;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private IEmrQcPresVeriService emrQcPresVeriService;
   @Autowired
   private IEmrQcFlowService emrQcFlowService;
   @Autowired
   private EmrQcFlowRecordMapper emrQcFlowRecordMapper;
   @Autowired
   private IEmrQcListScoreService emrQcListScoreService;
   @Autowired
   private ICommonService commonService;

   public EmrQcFlowScore selectEmrQcFlowScoreById(Long id) {
      return this.emrQcFlowScoreMapper.selectEmrQcFlowScoreById(id);
   }

   public List selectEmrQcFlowScoreList(EmrQcFlowScore emrQcFlowScore) {
      return this.emrQcFlowScoreMapper.selectEmrQcFlowScoreList(emrQcFlowScore);
   }

   public int insertEmrQcFlowScore(EmrQcFlowScore emrQcFlowScore) {
      return this.emrQcFlowScoreMapper.insertEmrQcFlowScore(emrQcFlowScore);
   }

   public int updateEmrQcFlowScore(EmrQcFlowScore emrQcFlowScore) {
      return this.emrQcFlowScoreMapper.updateEmrQcFlowScore(emrQcFlowScore);
   }

   public int deleteEmrQcFlowScoreByIds(Long[] ids) {
      return this.emrQcFlowScoreMapper.deleteEmrQcFlowScoreByIds(ids);
   }

   public int deleteEmrQcFlowScoreById(Long id) {
      return this.emrQcFlowScoreMapper.deleteEmrQcFlowScoreById(id);
   }

   public EmrQcFlowScoreVo createScoreRecordList(EmrQcFlowScoreVo emrQcFlowScoreVo) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      SysOrg sysOrg = this.sysOrgService.selectSysOrgById(100L);
      EmrQcFlowScore emrQcFlowScore = new EmrQcFlowScore();
      this.log.debug("emrQcFlowScoreVo+++############11111" + emrQcFlowScoreVo);
      emrQcFlowScore.setPatientId(emrQcFlowScoreVo.getPatientId());
      emrQcFlowScore.setOrgCd(sysUser.getHospital().getOrgCode());
      emrQcFlowScore.setQcSn(emrQcFlowScoreVo.getRecordId().toString());
      this.log.debug("createScoreRecordList-1111111111111111111: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      List<QcScoreItemVo> qcScoreItemVoList = new ArrayList();
      List<EmrQcListVo> emrQcListVoList = this.emrQcListMapper.selectEmrQcListScoreVoList(emrQcFlowScoreVo.getAppSeg(), emrQcFlowScoreVo.getPatientId(), (List)null);
      if (emrQcListVoList != null && emrQcListVoList.size() > 0) {
         Double scheTotalScore = ((EmrQcListVo)emrQcListVoList.get(0)).getSchetotalscore();
         List<QcScoreItemVo> qcScoreItemVos = new ArrayList();
         Map<String, List<EmrQcListVo>> itemMap = (Map)emrQcListVoList.stream().collect(Collectors.groupingBy(EmrQcListVo::getItemClassCd));
         scheTotalScore = this.setScoreDedItem(qcScoreItemVos, itemMap, scheTotalScore);
         this.log.debug("createScoreRecordList-555555555555555: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
         this.log.debug("createScoreRecordList-666666666666666666: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
         if (qcScoreItemVos != null && !qcScoreItemVos.isEmpty()) {
            Double var11 = scheTotalScore * (double)100.0F / ((EmrQcListVo)emrQcListVoList.get(0)).getSchetotalscore();
            scheTotalScore = Double.parseDouble(String.format("%.1f", var11));
            this.getlevelName(emrQcFlowScoreVo, scheTotalScore);
         }

         this.log.debug("createScoreRecordList-7777777777777777777777: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
         emrQcFlowScoreVo.setTotalScore(scheTotalScore > (double)0.0F ? scheTotalScore : (double)0.0F);
         emrQcFlowScoreVo.setQcScoreItemVoList(qcScoreItemVoList);
         qcScoreItemVos.sort(Comparator.comparing(QcScoreItemVo::getItemClassSort));
         emrQcFlowScoreVo.setQcScoreItemVoList(qcScoreItemVos);
      }

      this.log.debug("createScoreRecordList-88888888888888888888: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      String operTypeCd = null;
      switch (emrQcFlowScoreVo.getAppSeg()) {
         case "02":
            operTypeCd = "10";
            break;
         case "03":
            operTypeCd = "00";
            break;
         case "05":
            operTypeCd = "16";
      }

      List<EmrQcFlowRecord> flowRecords = this.emrQcFlowRecordMapper.selectEmrQcFlowRecordByPatientId(emrQcFlowScoreVo.getPatientId(), operTypeCd);
      if (!CollectionUtils.isEmpty(flowRecords)) {
         EmrQcFlowRecord record = (EmrQcFlowRecord)flowRecords.get(0);
         emrQcFlowScoreVo.setScoreDoc(record.getOperDcoName());
         emrQcFlowScoreVo.setScoreDate(record.getOperTime());
      } else {
         emrQcFlowScoreVo.setScoreDoc(SecurityUtils.getLoginUser().getUser().getBasEmployee().getEmplName());
         emrQcFlowScoreVo.setScoreDate(this.commonService.getDbSysdate());
      }

      emrQcFlowScoreVo.setHosName(sysOrg.getOrgName());
      this.log.debug("emrQcFlowScoreVo-88888888888888888888: " + emrQcFlowScoreVo);
      if (emrQcFlowScoreVo.getTotalScore() == null) {
         Double total = (double)100.0F;
         emrQcFlowScoreVo.setTotalScore(total);
         this.getlevelName(emrQcFlowScoreVo, total);
      }

      if (emrQcFlowScoreVo.getRecordId() == null) {
         emrQcFlowScoreVo.setRecordId(Long.valueOf(emrQcFlowScoreVo.getQcSn()));
      }

      return emrQcFlowScoreVo;
   }

   public EmrQcFlowScoreVo deptScoreRecordList(EmrQcFlowScoreVo emrQcFlowScoreVo) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      SysOrg sysOrg = this.sysOrgService.selectSysOrgById(100L);
      EmrQcFlowScore emrQcFlowScore = new EmrQcFlowScore();
      emrQcFlowScore.setPatientId(emrQcFlowScoreVo.getPatientId());
      emrQcFlowScore.setOrgCd(sysUser.getHospital().getOrgCode());
      emrQcFlowScore.setQcSn(emrQcFlowScoreVo.getRecordId().toString());
      List<EmrQcFlowScore> emrQcFlowScoreList = this.emrQcFlowScoreMapper.selectEmrQcFlowScoreList(emrQcFlowScore);
      if (emrQcFlowScoreList != null && !emrQcFlowScoreList.isEmpty()) {
         emrQcFlowScoreVo = this.getScoreFlowList(emrQcFlowScoreVo);
      } else {
         this.log.debug("createScoreRecordList-1111111111111111111: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
         VisitinfoVo visitinfoVo = this.visitinfoService.selectVisitinfoByPatientId(emrQcFlowScoreVo.getPatientId());
         List<QcScoreItemVo> qcScoreItemVoList = new ArrayList();
         QcScoreScheDedVo qcScoreScheDedVo = new QcScoreScheDedVo();
         qcScoreScheDedVo.setDeptCode(visitinfoVo.getDeptCd());
         qcScoreScheDedVo.setOrgCode(CommonConstants.SYSTEM.ORG_ID.toString());
         qcScoreScheDedVo.setAppSeg(emrQcFlowScoreVo.getAppSeg());
         List<QcScoreScheDedVo> qcScoreScheDedVoList = this.qcScoreScheDedService.selectScheDedDetailBysche(qcScoreScheDedVo);
         this.log.debug("createScoreRecordList-222222222222222222222: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
         List<QcScoreDedRuleVo> ruleVoList = this.qcScoreDedRuleService.selectQcRuleDedList();
         this.log.debug("createScoreRecordList-33333333333333333: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
         this.log.debug("createScoreRecordList-444444444444444: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
         if (qcScoreScheDedVoList != null && qcScoreScheDedVoList.size() > 0) {
            Double scheTotalScore = ((QcScoreScheDedVo)qcScoreScheDedVoList.get(0)).getSchetotalscore();
            EmrQcListVo emrQcListVo = new EmrQcListVo();
            emrQcListVo.setPatientId(emrQcFlowScoreVo.getPatientId());
            emrQcListVo.setMainId(emrQcFlowScoreVo.getRecordId());
            emrQcListVo.setQcStateList(emrQcFlowScoreVo.getQcStateList());
            List<EmrQcListVo> emrQcListList = this.emrQcListMapper.selectEmrQcListScore(emrQcListVo);
            EmrQcPresVeri emrQcPresVeri = new EmrQcPresVeri();
            emrQcPresVeri.setPatientId(emrQcFlowScoreVo.getPatientId());
            List<EmrQcPresVeri> presVeriList = this.emrQcPresVeriService.selectEmrQcPresVeriList(emrQcPresVeri);
            if (presVeriList != null && !presVeriList.isEmpty()) {
               for(EmrQcPresVeri presVeri : presVeriList) {
                  EmrQcListVo emrQcList = new EmrQcListVo();
                  emrQcList.setRuleId(presVeri.getVeriRuleId());
                  emrQcList.setFlawDesc(presVeri.getEventDesc());
                  emrQcList.setId(presVeri.getId());
                  emrQcListList.add(emrQcList);
               }
            }

            List<QcScoreItemVo> qcScoreItemVos = new ArrayList();
            Map<String, List<QcScoreScheDedVo>> itemMap = (Map)qcScoreScheDedVoList.stream().collect(Collectors.groupingBy((s) -> s.getItemClassCd()));
            this.log.debug("createScoreRecordList-555555555555555: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
            this.log.debug("createScoreRecordList-666666666666666666: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
            if (qcScoreItemVos != null && !qcScoreItemVos.isEmpty()) {
               Double var19 = scheTotalScore * (double)100.0F / ((QcScoreScheDedVo)qcScoreScheDedVoList.get(0)).getSchetotalscore();
               scheTotalScore = Double.parseDouble(String.format("%.1f", var19));
               this.getlevelName(emrQcFlowScoreVo, scheTotalScore);
            }

            this.log.debug("createScoreRecordList-7777777777777777777777: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
            emrQcFlowScoreVo.setScoreDoc(SecurityUtils.getLoginUser().getUser().getBasEmployee().getEmplName());
            emrQcFlowScoreVo.setTotalScore(scheTotalScore > (double)0.0F ? scheTotalScore : (double)0.0F);
            emrQcFlowScoreVo.setScoreDate(DateUtils.getNowDate());
            emrQcFlowScoreVo.setQcScoreItemVoList(qcScoreItemVoList);
            emrQcFlowScoreVo.setScheId(((QcScoreScheDedVo)qcScoreScheDedVoList.get(0)).getScheId().toString());
            emrQcFlowScoreVo.setScheName(((QcScoreScheDedVo)qcScoreScheDedVoList.get(0)).getScheName());
            emrQcFlowScoreVo.setHosName(sysOrg.getOrgName());
            qcScoreItemVos.sort(Comparator.comparing(QcScoreItemVo::getItemClassSort));
            emrQcFlowScoreVo.setQcScoreItemVoList(qcScoreItemVos);
         }

         this.log.debug("createScoreRecordList-88888888888888888888: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      }

      return emrQcFlowScoreVo;
   }

   private Double itemDedScoreType(QcScoreScheDedVo qcScoreScheDedVo, List emrQcListList, List ruleList, QcScoreDedDetailVo qcScoreDedDetailVo) {
      List<EmrQcFlowScoreListVo> emrQcFlowScoreListVos = new ArrayList(1);
      int num = 0;
      Double score = (double)0.0F;
      List<Long> ruleIdList = (List)ruleList.stream().map((s) -> s.getRuleId()).collect(Collectors.toList());

      for(EmrQcListVo emrQcList : emrQcListList) {
         if (ruleIdList.contains(emrQcList.getRuleId())) {
            score = score + qcScoreScheDedVo.getDedScoreSingle();
            ++num;
            new StringBuffer("");
            List<QcScoreDedRuleVo> rules = (List)ruleList.stream().filter((s) -> s.getRuleId().equals(emrQcList.getRuleId())).collect(Collectors.toList());
            EmrQcFlowScoreListVo emrQcFlowScoreListVo = this.setqcFlowScoreList(rules, emrQcList, qcScoreScheDedVo);
            emrQcFlowScoreListVos.add(emrQcFlowScoreListVo);
         }
      }

      qcScoreDedDetailVo.setEmrQcFlowScoreListVos(emrQcFlowScoreListVos);
      qcScoreDedDetailVo.setScoreNum(num);
      qcScoreDedDetailVo.setDedItemDesc("共" + emrQcFlowScoreListVos.size() + "项,扣分" + num + "次");
      return score;
   }

   private Double itemDedScoreTypeFree(EmrQcListVo emrQcList, QcScoreDedDetailVo qcScoreDedDetailVo) {
      List<EmrQcFlowScoreListVo> emrQcFlowScoreListVos = new ArrayList(1);
      Double score = (double)0.0F;
      score = score + emrQcList.getDedScoreSingle();
      EmrQcFlowScoreListVo emrQcFlowScoreListVo = this.setqcFlowScoreListFree(emrQcList);
      emrQcFlowScoreListVos.add(emrQcFlowScoreListVo);
      qcScoreDedDetailVo.setEmrQcFlowScoreListVos(emrQcFlowScoreListVos);
      qcScoreDedDetailVo.setScoreNum(1);
      qcScoreDedDetailVo.setDedItemDesc("共1项,扣分1次");
      return score;
   }

   private Double tiemDedScoreType(QcScoreScheDedVo qcScoreScheDedVo, List emrQcListList, List ruleList, QcScoreDedDetailVo qcScoreDedDetailVo) {
      List<EmrQcFlowScoreListVo> emrQcFlowScoreListVos = new ArrayList(1);
      List<Long> ruleIdList = (List)ruleList.stream().map((s) -> s.getRuleId()).collect(Collectors.toList());
      int num = 0;
      Double score = (double)0.0F;

      for(EmrQcListVo emrQcList : emrQcListList) {
         if (ruleIdList.contains(emrQcList.getRuleId())) {
            score = score + qcScoreScheDedVo.getDedScoreSingle();
            ++num;
            new StringBuffer("");
            List<QcScoreDedRuleVo> rules = (List)ruleList.stream().filter((s) -> s.getRuleId().equals(emrQcList.getRuleId())).collect(Collectors.toList());
            EmrQcFlowScoreListVo emrQcFlowScoreListVo = this.setqcFlowScoreList(rules, emrQcList, qcScoreScheDedVo);
            emrQcFlowScoreListVos.add(emrQcFlowScoreListVo);
         }
      }

      qcScoreDedDetailVo.setEmrQcFlowScoreListVos(emrQcFlowScoreListVos);
      qcScoreDedDetailVo.setScoreNum(num);
      qcScoreDedDetailVo.setDedItemDesc("共" + emrQcFlowScoreListVos.size() + "项,扣分" + num + "次");
      return score;
   }

   private Double oneTimeScoreType(List emrQcListList, QcScoreDedDetailVo qcScoreDedDetailVo) {
      List<EmrQcFlowScoreListVo> emrQcFlowScoreListVos = new ArrayList(1);
      Double score = (double)0.0F;
      int num = 0;

      for(EmrQcListVo emrQcList : emrQcListList) {
         EmrQcFlowScoreListVo emrQcFlowScoreListVo = this.setqcFlowScoreListFree(emrQcList);
         emrQcFlowScoreListVos.add(emrQcFlowScoreListVo);
      }

      if (emrQcFlowScoreListVos != null && !emrQcFlowScoreListVos.isEmpty()) {
         score = ((EmrQcListVo)emrQcListList.get(0)).getDedScoreSingle();
         num = 1;
      }

      qcScoreDedDetailVo.setEmrQcFlowScoreListVos(emrQcFlowScoreListVos);
      qcScoreDedDetailVo.setScoreNum(num);
      qcScoreDedDetailVo.setDedItemDesc("共" + emrQcFlowScoreListVos.size() + "项,扣分" + num + "次");
      return score;
   }

   private EmrQcFlowScoreListVo setqcFlowScoreList(List rules, EmrQcListVo emrQcList, QcScoreScheDedVo qcScoreScheDedVo) {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      EmrQcFlowScoreListVo emrQcFlowScoreListVo = new EmrQcFlowScoreListVo();
      emrQcFlowScoreListVo.setId(SnowIdUtils.uniqueLong());
      emrQcFlowScoreListVo.setEmrTypeName(((QcScoreDedRuleVo)rules.get(0)).getEmrTypeName());
      emrQcFlowScoreListVo.setQcElemName(emrQcList.getEmrEleName());
      emrQcFlowScoreListVo.setDedCd(qcScoreScheDedVo.getDedId().toString());
      emrQcFlowScoreListVo.setDedName(qcScoreScheDedVo.getDedName());
      emrQcFlowScoreListVo.setQcId(emrQcList.getId());
      emrQcFlowScoreListVo.setRuleId(((QcScoreDedRuleVo)rules.get(0)).getRuleId());
      emrQcFlowScoreListVo.setRuleName(((QcScoreDedRuleVo)rules.get(0)).getRuleName());
      emrQcFlowScoreListVo.setFlawDesc(emrQcList.getFlawDesc());
      emrQcFlowScoreListVo.setCrePerCode(basEmployee.getEmplNumber());
      emrQcFlowScoreListVo.setCrePerName(basEmployee.getEmplName());
      emrQcFlowScoreListVo.setCreDate(DateUtils.getNowDate());
      emrQcFlowScoreListVo.setQcSn(emrQcList.getMainId() != null ? emrQcList.getMainId().toString() : "");
      return emrQcFlowScoreListVo;
   }

   private EmrQcFlowScoreListVo setqcFlowScoreListFree(EmrQcListVo emrQcList) {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      EmrQcFlowScoreListVo emrQcFlowScoreListVo = new EmrQcFlowScoreListVo();
      emrQcFlowScoreListVo.setId(SnowIdUtils.uniqueLong());
      emrQcFlowScoreListVo.setEmrTypeName(emrQcList.getMrTypeName());
      emrQcFlowScoreListVo.setQcElemName(!StringUtils.isEmpty(emrQcList.getEmrEleName()) && !emrQcList.getEmrEleName().equals("NNNNNN") && !emrQcList.getMrType().equals("64") ? emrQcList.getEmrEleName() : emrQcList.getEleContext());
      emrQcFlowScoreListVo.setDedCd(emrQcList.getRuleId().toString());
      emrQcFlowScoreListVo.setDedName(emrQcList.getDedDesc());
      emrQcFlowScoreListVo.setQcId(emrQcList.getId());
      emrQcFlowScoreListVo.setRuleId(emrQcList.getRuleId());
      emrQcFlowScoreListVo.setRuleName(StringUtils.isEmpty(emrQcList.getRuleName()) ? emrQcList.getFlawDesc() : emrQcList.getRuleName());
      emrQcFlowScoreListVo.setFlawDesc(emrQcList.getFlawDesc());
      emrQcFlowScoreListVo.setCrePerCode(basEmployee.getEmplNumber());
      emrQcFlowScoreListVo.setCrePerName(basEmployee.getEmplName());
      emrQcFlowScoreListVo.setCreDate(DateUtils.getNowDate());
      emrQcFlowScoreListVo.setQcSn(emrQcList.getMainId() != null ? emrQcList.getMainId().toString() : "");
      return emrQcFlowScoreListVo;
   }

   private Double setScoreDedItem(List qcScoreItemVoList, Map itemMap, Double scheTotalScore) {
      Double DXTotalScore = (double)0.0F;
      Double dedTotalScore = (double)0.0F;

      for(String itemClassCd : itemMap.keySet()) {
         Double itemClassScore = (double)0.0F;
         Double itemClassScoreFree = (double)0.0F;
         Double ICTotalScore = (double)0.0F;
         Double DXTotalScoreClass = (double)0.0F;
         List<EmrQcListVo> itemGroupList = (List)itemMap.get(itemClassCd);
         if (itemGroupList != null && !itemGroupList.isEmpty()) {
            List<QcScoreDedDetailVo> qcScoreDedDetailVoList = new ArrayList();
            Map<Long, List<EmrQcListVo>> dedMap = (Map)itemGroupList.stream().collect(Collectors.groupingBy((s) -> s.getItemId()));

            for(Long itemId : dedMap.keySet()) {
               Double itemScore = (double)0.0F;
               Double itemScoreFree = (double)0.0F;
               Double DXitemScore = (double)0.0F;
               List<EmrQcListVo> itemList = (List)dedMap.get(itemId);
               if (itemList != null && !itemList.isEmpty()) {
                  ICTotalScore = ICTotalScore + ((EmrQcListVo)itemList.get(0)).getItemTotalScore();
                  List<EmrQcListVo> oneList = (List)itemList.stream().filter((s) -> s.getDedType().equals("ONE_TIME") && !s.getRuleId().equals("999999")).collect(Collectors.toList());

                  for(EmrQcListVo emrQcListVo : (List)itemList.stream().filter((s) -> !s.getDedType().equals("ONE_TIME") || s.getDedType().equals("ONE_TIME") && s.getRuleId().equals("999999")).collect(Collectors.toList())) {
                     QcScoreDedDetailVo qcScoreDedDetailVo = new QcScoreDedDetailVo();
                     List<EmrQcFlowScoreListVo> emrQcFlowScoreListVos = new ArrayList(1);
                     EmrQcFlowScoreListVo emrQcFlowScoreListVo = new EmrQcFlowScoreListVo();
                     Double score = (double)0.0F;
                     Double scoreFree = (double)0.0F;
                     Double DXscore = (double)0.0F;
                     switch (emrQcListVo.getDedType()) {
                        case "ITEM_SCORE":
                        case "POINT_SCORE":
                        case "ONE_TIME":
                        case "TIME_SCORE":
                           emrQcFlowScoreListVo = this.setqcFlowScoreListFree(emrQcListVo);
                           if (emrQcListVo.getRuleId().toString().equals("999999")) {
                              scoreFree = emrQcListVo.getDedScoreSingle();
                           } else {
                              score = emrQcListVo.getDedScoreSingle();
                           }
                           break;
                        case "VETO_CGRADE":
                        case "VETO_BGRADE":
                           emrQcFlowScoreListVo = this.setqcFlowScoreListFree(emrQcListVo);
                           DXscore = emrQcListVo.getDedScoreSingle();
                     }

                     emrQcFlowScoreListVos.add(emrQcFlowScoreListVo);
                     qcScoreDedDetailVo.setEmrQcFlowScoreListVos(emrQcFlowScoreListVos);
                     qcScoreDedDetailVo.setScoreNum(1);
                     qcScoreDedDetailVo.setDedItemDesc("共1项,扣分1次");
                     if (score > (double)0.0F) {
                        qcScoreDedDetailVo.setScoreDesc("-" + score);
                        itemScore = itemScore + score;
                     }

                     if (scoreFree > (double)0.0F) {
                        qcScoreDedDetailVo.setScoreDesc("-" + scoreFree);
                        itemScoreFree = itemScoreFree + scoreFree;
                     }

                     if (DXscore > (double)0.0F) {
                        DXitemScore = DXitemScore + DXscore;
                        qcScoreDedDetailVo.setScoreDesc("-" + DXscore);
                     }

                     qcScoreDedDetailVo.setDedDesc(emrQcListVo.getDedDesc());
                     qcScoreDedDetailVo.setDedScoreDesc(emrQcListVo.getDedScoreDesc());
                     qcScoreDedDetailVo.setDedScore(score);
                     qcScoreDedDetailVo.setDedCd(emrQcListVo.getRuleId().toString());
                     qcScoreDedDetailVo.setDedName(emrQcListVo.getDedDesc());
                     qcScoreDedDetailVo.setDedScoreSingle(emrQcListVo.getDedScoreSingle());
                     qcScoreDedDetailVo.setDedType(emrQcListVo.getDedType());
                     qcScoreDedDetailVo.setItemCd(((EmrQcListVo)itemList.get(0)).getItemId().toString());
                     qcScoreDedDetailVo.setItemName(((EmrQcListVo)itemList.get(0)).getItemName());
                     qcScoreDedDetailVo.setItemTotalScore(((EmrQcListVo)itemList.get(0)).getItemTotalScore());
                     qcScoreDedDetailVo.setItemDesc(((EmrQcListVo)itemList.get(0)).getItemDesc());
                     qcScoreDedDetailVo.setItemClassCd(((EmrQcListVo)itemList.get(0)).getItemClassCd());
                     qcScoreDedDetailVo.setItemClassName(((EmrQcListVo)itemList.get(0)).getItemClassName());
                     qcScoreDedDetailVo.setItemSort(((EmrQcListVo)itemList.get(0)).getItemSort());
                     qcScoreDedDetailVo.setSort(emrQcListVo.getDetailSort());
                     qcScoreDedDetailVoList.add(qcScoreDedDetailVo);
                  }

                  if (oneList != null && !oneList.isEmpty()) {
                     Map<Long, List<EmrQcListVo>> oneMap = (Map)oneList.stream().collect(Collectors.groupingBy((s) -> s.getRuleId()));

                     for(Long ruleId : oneMap.keySet()) {
                        QcScoreDedDetailVo qcScoreDedDetailVo = new QcScoreDedDetailVo();
                        new ArrayList(1);
                        Double score = (double)0.0F;
                        List<EmrQcListVo> emrQcListVos = (List)oneMap.get(ruleId);
                        score = this.oneTimeScoreType(emrQcListVos, qcScoreDedDetailVo);
                        if (score > (double)0.0F) {
                           qcScoreDedDetailVo.setScoreDesc("-" + score);
                           itemScore = itemScore + score;
                        }

                        qcScoreDedDetailVo.setDedDesc(((EmrQcListVo)emrQcListVos.get(0)).getDedDesc());
                        qcScoreDedDetailVo.setDedScoreDesc(((EmrQcListVo)emrQcListVos.get(0)).getDedScoreDesc());
                        qcScoreDedDetailVo.setDedScore(score);
                        qcScoreDedDetailVo.setDedCd(((EmrQcListVo)emrQcListVos.get(0)).getRuleId().toString());
                        qcScoreDedDetailVo.setDedName(((EmrQcListVo)emrQcListVos.get(0)).getDedDesc());
                        qcScoreDedDetailVo.setDedScoreSingle(((EmrQcListVo)emrQcListVos.get(0)).getDedScoreSingle());
                        qcScoreDedDetailVo.setDedType(((EmrQcListVo)emrQcListVos.get(0)).getDedType());
                        qcScoreDedDetailVo.setItemCd(((EmrQcListVo)itemList.get(0)).getItemId().toString());
                        qcScoreDedDetailVo.setItemName(((EmrQcListVo)itemList.get(0)).getItemName());
                        qcScoreDedDetailVo.setItemTotalScore(((EmrQcListVo)itemList.get(0)).getItemTotalScore());
                        qcScoreDedDetailVo.setItemDesc(((EmrQcListVo)itemList.get(0)).getItemDesc());
                        qcScoreDedDetailVo.setItemClassCd(((EmrQcListVo)itemList.get(0)).getItemClassCd());
                        qcScoreDedDetailVo.setItemClassName(((EmrQcListVo)itemList.get(0)).getItemClassName());
                        qcScoreDedDetailVo.setItemSort(((EmrQcListVo)itemList.get(0)).getItemSort());
                        qcScoreDedDetailVo.setSort(((EmrQcListVo)emrQcListVos.get(0)).getDetailSort());
                        qcScoreDedDetailVoList.add(qcScoreDedDetailVo);
                     }
                  }

                  if (itemScore > ((EmrQcListVo)itemList.get(0)).getItemTotalScore()) {
                     itemScore = ((EmrQcListVo)itemList.get(0)).getItemTotalScore();
                  }

                  itemClassScore = itemClassScore + itemScore;
                  itemClassScoreFree = itemClassScoreFree + itemScoreFree;
                  DXTotalScoreClass = DXTotalScoreClass + DXitemScore;
               }
            }

            QcScoreItemVo qcScoreItemVo = new QcScoreItemVo();
            qcScoreItemVo.setItemClassCd(((EmrQcListVo)itemGroupList.get(0)).getItemClassCd());
            qcScoreItemVo.setItemClassName(((EmrQcListVo)itemGroupList.get(0)).getItemClassName());
            qcScoreItemVo.setScore(ICTotalScore);
            qcScoreItemVo.setItemClassSort(((EmrQcListVo)itemGroupList.get(0)).getItemClassSort());
            if (itemClassScore > ICTotalScore) {
               itemClassScore = ICTotalScore;
            }

            dedTotalScore = dedTotalScore + itemClassScore + itemClassScoreFree + DXTotalScoreClass;
            qcScoreItemVo.setItemDedScore(itemClassScore + itemClassScoreFree + DXTotalScoreClass);
            qcScoreDedDetailVoList.sort(Comparator.comparing(QcScoreDedDetail::getItemSort));
            qcScoreDedDetailVoList.sort(Comparator.comparing(QcScoreDedDetail::getSort));
            qcScoreItemVo.setQcScoreDedDetailVoList(qcScoreDedDetailVoList);
            qcScoreItemVoList.add(qcScoreItemVo);
         }
      }

      scheTotalScore = scheTotalScore - dedTotalScore;
      return scheTotalScore;
   }

   private void getlevelName(EmrQcFlowScoreVo resultVo, Double totalScore) {
      for(QcScoreGrade qcScoreGrade : this.qcScoreGradeService.selectQcScoreGradeList(new QcScoreGrade())) {
         if (qcScoreGrade.getGradeCd().equals("1")) {
            if (totalScore > qcScoreGrade.getScoreMin()) {
               resultVo.setLevelCode(qcScoreGrade.getGradeCd());
               resultVo.setLevelName(qcScoreGrade.getGradeName());
            }
         } else if (qcScoreGrade.getGradeCd().equals("2")) {
            if (totalScore >= qcScoreGrade.getScoreMin() && totalScore <= qcScoreGrade.getScoreMax()) {
               resultVo.setLevelCode(qcScoreGrade.getGradeCd());
               resultVo.setLevelName(qcScoreGrade.getGradeName());
            }
         } else if (qcScoreGrade.getGradeCd().equals("3") && totalScore < qcScoreGrade.getScoreMax()) {
            resultVo.setLevelCode(qcScoreGrade.getGradeCd());
            resultVo.setLevelName(qcScoreGrade.getGradeName());
         }
      }

   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void saveEmrQcFlowScoreVo(EmrQcFlowScoreVo emrQcFlowScoreVo) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      List<EmrQcFlowScoreListVo> emrQcFlowScoreLists = new ArrayList();
      List<EmrQcFlowScore> emrQcFlowScoreList = new ArrayList();

      for(QcScoreItemVo qcScoreItemVo : emrQcFlowScoreVo.getQcScoreItemVoList()) {
         for(QcScoreDedDetailVo qcScoreDedDetailVo : qcScoreItemVo.getQcScoreDedDetailVoList()) {
            EmrQcFlowScore emrQcFlowScore = new EmrQcFlowScore();
            Long id = SnowIdUtils.uniqueLong();
            emrQcFlowScore.setId(id);
            emrQcFlowScore.setAppSeg(emrQcFlowScoreVo.getAppSeg());
            emrQcFlowScore.setOrgCd(sysUser.getHospital().getOrgCode());
            emrQcFlowScore.setPatientId(emrQcFlowScoreVo.getPatientId());
            emrQcFlowScore.setDeptCd(emrQcFlowScoreVo.getDeptCd());
            emrQcFlowScore.setDeptName(emrQcFlowScoreVo.getDedDesc());
            emrQcFlowScore.setScheId(emrQcFlowScoreVo.getScheId());
            emrQcFlowScore.setScheName(emrQcFlowScoreVo.getScheName());
            emrQcFlowScore.setScheScore(emrQcFlowScoreVo.getTotalScore());
            emrQcFlowScore.setDedCd(qcScoreDedDetailVo.getDedCd());
            emrQcFlowScore.setDedName(qcScoreDedDetailVo.getDedName());
            emrQcFlowScore.setDedType(qcScoreDedDetailVo.getDedType());
            emrQcFlowScore.setDedScoreSingle(qcScoreDedDetailVo.getDedScoreSingle());
            emrQcFlowScore.setDedScoreMax(qcScoreDedDetailVo.getDedScoreMax());
            emrQcFlowScore.setDedScoreDesc(qcScoreDedDetailVo.getDedScoreDesc());
            emrQcFlowScore.setDepNum(qcScoreDedDetailVo.getScoreNum() == null ? 0L : qcScoreDedDetailVo.getScoreNum().longValue());
            emrQcFlowScore.setDedScore(qcScoreDedDetailVo.getDedScore() == null ? "0.0" : qcScoreDedDetailVo.getDedScore().toString());
            emrQcFlowScore.setItemCd(qcScoreDedDetailVo.getItemCd());
            emrQcFlowScore.setItemName(qcScoreDedDetailVo.getItemName());
            emrQcFlowScore.setItemClassCd(qcScoreDedDetailVo.getItemClassCd());
            emrQcFlowScore.setItemClassName(qcScoreDedDetailVo.getItemClassName());
            emrQcFlowScore.setItemDesc(qcScoreDedDetailVo.getItemDesc());
            emrQcFlowScore.setQcSn(emrQcFlowScoreVo.getRecordId().toString());
            emrQcFlowScore.setCrePerCode(sysUser.getBasEmployee().getEmplNumber());
            emrQcFlowScore.setCrePerName(sysUser.getBasEmployee().getEmplName());
            emrQcFlowScoreList.add(emrQcFlowScore);
            List<EmrQcFlowScoreListVo> listVoList = qcScoreDedDetailVo.getEmrQcFlowScoreListVos();

            for(EmrQcFlowScoreListVo emrQcFlowScoreListVo : listVoList) {
               emrQcFlowScoreListVo.setMainId(id);
            }

            emrQcFlowScoreLists.addAll(listVoList);
         }
      }

      if (!emrQcFlowScoreList.isEmpty()) {
         this.emrQcFlowScoreMapper.insertEmrQcFlowScoreList(emrQcFlowScoreList);
      }

      if (!emrQcFlowScoreLists.isEmpty()) {
         this.emrQcFlowScoreListMapper.insertEmrQcFlowScoreLists(emrQcFlowScoreLists);
      }

   }

   private EmrQcFlowScoreVo getScoreFlowList(EmrQcFlowScoreVo emrQcFlowScoreVo) {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      SysOrg sysOrg = this.sysOrgService.selectSysOrgById(100L);
      EmrQcFlowScoreVo resultVo = new EmrQcFlowScoreVo();
      EmrQcFlowScore emrQcFlowScore = new EmrQcFlowScore();
      resultVo.setPatientId(emrQcFlowScoreVo.getPatientId());
      emrQcFlowScore.setPatientId(emrQcFlowScoreVo.getPatientId());
      emrQcFlowScore.setQcSn(emrQcFlowScoreVo.getRecordId().toString());
      List<EmrQcFlowScoreVo> emrQcFlowScoreList = this.emrQcFlowScoreMapper.selectEmrScoreList(emrQcFlowScore);
      EmrQcFlow emrQcFlow = this.emrQcFlowService.selectEmrQcFlowById(sysUser.getHospital().getOrgCode(), emrQcFlowScoreVo.getPatientId());
      List<EmrQcFlowScoreListVo> scoreListList = this.emrQcFlowScoreListMapper.selectScoreListDescList(emrQcFlowScoreVo.getRecordId().toString());
      if (emrQcFlowScoreList != null && !emrQcFlowScoreList.isEmpty()) {
         EmrQcFlowScore qcFlowScore = (EmrQcFlowScore)emrQcFlowScoreList.get(0);
         Map<String, List<EmrQcFlowScoreVo>> itemClassMap = (Map)emrQcFlowScoreList.stream().collect(Collectors.groupingBy((s) -> s.getItemClassCd()));
         Map<Long, List<EmrQcFlowScoreListVo>> scoreListListMap = (Map)scoreListList.stream().collect(Collectors.groupingBy((s) -> s.getMainId()));
         resultVo.setScoreDoc(qcFlowScore.getCrePerName());
         resultVo.setScoreDate(qcFlowScore.getCreDate());
         Double total = emrQcFlowScoreVo.getAppSeg().equals("02") ? emrQcFlow.getDeptScore() : (emrQcFlowScoreVo.getAppSeg().equals("03") ? emrQcFlow.getCheckScore() : emrQcFlow.getTermScore());
         String scoreLevel = emrQcFlowScoreVo.getAppSeg().equals("02") ? emrQcFlow.getDeptGradeName() : (emrQcFlowScoreVo.getAppSeg().equals("03") ? emrQcFlow.getCheckGradeName() : emrQcFlow.getTermGradeName());
         resultVo.setTotalScore(total);
         resultVo.setLevelName(scoreLevel);
         resultVo.setHosName(sysOrg.getOrgName());
         List<QcScoreItemVo> scoreItemClassList = new ArrayList();

         for(String classCd : itemClassMap.keySet()) {
            List<EmrQcFlowScoreVo> itemClassList = (List)itemClassMap.get(classCd);
            QcScoreItemVo qcScoreItemVo = new QcScoreItemVo();
            qcScoreItemVo.setItemClassName(((EmrQcFlowScoreVo)itemClassList.get(0)).getItemClassName());
            Double classTotalScre = (double)0.0F;
            Double classScre = (double)0.0F;
            Double classScreFree = (double)0.0F;
            List<QcScoreDedDetailVo> qcScoreDedDetailVoList = new ArrayList();
            Map<String, List<EmrQcFlowScoreVo>> itemListMap = (Map)itemClassList.stream().collect(Collectors.groupingBy((s) -> s.getItemCd()));

            for(String itemCd : itemListMap.keySet()) {
               List<EmrQcFlowScoreVo> dedList = (List)itemListMap.get(itemCd);
               classTotalScre = classTotalScre + ((EmrQcFlowScoreVo)dedList.get(0)).getItemScore();
               Double itemScore = (double)0.0F;
               Double itemScoreFree = (double)0.0F;

               for(EmrQcFlowScoreVo dedScore : dedList) {
                  QcScoreDedDetailVo qcScoreDedDetailVo = new QcScoreDedDetailVo();
                  qcScoreDedDetailVo.setItemName(dedScore.getItemName());
                  qcScoreDedDetailVo.setItemCd(dedScore.getItemCd());
                  qcScoreDedDetailVo.setDedCd(dedScore.getDedCd());
                  qcScoreDedDetailVo.setItemDesc(dedScore.getItemDesc());
                  qcScoreDedDetailVo.setItemTotalScore(dedScore.getItemScore());
                  qcScoreDedDetailVo.setDedDesc(dedScore.getDedName());
                  qcScoreDedDetailVo.setDedScoreDesc(dedScore.getDedScoreDesc());
                  qcScoreDedDetailVo.setItemSort(dedScore.getItemSort());
                  qcScoreDedDetailVo.setItemClassCd(dedScore.getItemClassCd());
                  qcScoreDedDetailVo.setItemClassName(dedScore.getItemClassName());
                  qcScoreDedDetailVo.setSort(dedScore.getDedSort());
                  Double scoreDed = dedScore.getDedScoreSingle() * (double)dedScore.getDepNum();
                  qcScoreDedDetailVo.setScoreDesc(scoreDed != (double)0.0F && scoreDed != null ? "-" + scoreDed : "0");
                  List<EmrQcFlowScoreListVo> flowScoreDescList = (List)scoreListListMap.get(dedScore.getId());
                  qcScoreDedDetailVo.setDedItemDesc("共1项，扣分1次");
                  qcScoreDedDetailVo.setEmrQcFlowScoreListVos((List)(flowScoreDescList == null ? new ArrayList(1) : flowScoreDescList));
                  qcScoreDedDetailVoList.add(qcScoreDedDetailVo);
                  if (dedScore.getDedCd().equals("999999")) {
                     itemScoreFree = itemScoreFree + scoreDed;
                  } else {
                     itemScore = itemScore + scoreDed;
                  }
               }

               if (itemScore > ((EmrQcFlowScoreVo)dedList.get(0)).getItemScore()) {
                  itemScore = ((EmrQcFlowScoreVo)dedList.get(0)).getItemScore();
               }

               classScre = classScre + itemScore;
               classScreFree = classScreFree + itemScoreFree;
            }

            if (classScre > classTotalScre) {
               classScre = classTotalScre;
            }

            qcScoreItemVo.setScore(classTotalScre);
            qcScoreItemVo.setItemDedScore(classScre + classScreFree);
            qcScoreItemVo.setItemClassSort(((EmrQcFlowScoreVo)itemClassList.get(0)).getItemClassSort());
            qcScoreDedDetailVoList.sort(Comparator.comparing(QcScoreDedDetail::getItemSort));
            qcScoreItemVo.setQcScoreDedDetailVoList(qcScoreDedDetailVoList);
            scoreItemClassList.add(qcScoreItemVo);
         }

         scoreItemClassList.sort(Comparator.comparing(QcScoreItemVo::getItemClassSort));
         resultVo.setQcScoreItemVoList(scoreItemClassList);
      }

      return resultVo;
   }

   public void deleteFromEmrQcFlowScore() {
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void deleteEmrQcListScoreAndListByQcId(String patientId, Long qcId) {
      this.emrQcFlowScoreMapper.deleteEmrQcFlowScoreByQcId(patientId, qcId);
      this.emrQcFlowScoreListMapper.deleteEmrQcFlowScoreByQcId(qcId);
   }
}
