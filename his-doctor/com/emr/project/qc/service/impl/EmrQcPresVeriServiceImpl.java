package com.emr.project.qc.service.impl;

import com.emr.common.constant.CommonConstants;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.common.service.ICommonService;
import com.emr.project.emr.domain.vo.IndexVo;
import com.emr.project.emr.service.IIndexService;
import com.emr.project.qc.domain.EmrQcPresVeri;
import com.emr.project.qc.domain.QcAgiEven;
import com.emr.project.qc.domain.QcAgiRule;
import com.emr.project.qc.mapper.EmrQcPresVeriMapper;
import com.emr.project.qc.service.IEmrQcPresVeriService;
import com.emr.project.qc.service.IQcAgiEvenService;
import com.emr.project.qc.service.IQcAgiRuleService;
import com.emr.project.system.domain.BasEmployee;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmrQcPresVeriServiceImpl implements IEmrQcPresVeriService {
   @Autowired
   private EmrQcPresVeriMapper emrQcPresVeriMapper;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private IQcAgiRuleService qcAgiRuleService;
   @Autowired
   private IQcAgiEvenService qcAgiEvenService;
   @Autowired
   private IIndexService indexService;

   public EmrQcPresVeri selectEmrQcPresVeriById(Long id) {
      return this.emrQcPresVeriMapper.selectEmrQcPresVeriById(id);
   }

   public List selectEmrQcPresVeriList(EmrQcPresVeri emrQcPresVeri) {
      return this.emrQcPresVeriMapper.selectEmrQcPresVeriList(emrQcPresVeri);
   }

   public int insertEmrQcPresVeri(EmrQcPresVeri emrQcPresVeri) {
      return this.emrQcPresVeriMapper.insertEmrQcPresVeri(emrQcPresVeri);
   }

   public int updateEmrQcPresVeri(EmrQcPresVeri emrQcPresVeri) {
      return this.emrQcPresVeriMapper.updateEmrQcPresVeri(emrQcPresVeri);
   }

   public int deleteEmrQcPresVeriByIds(Long[] ids) {
      return this.emrQcPresVeriMapper.deleteEmrQcPresVeriByIds(ids);
   }

   public int deleteEmrQcPresVeriById(Long id) {
      return this.emrQcPresVeriMapper.deleteEmrQcPresVeriById(id);
   }

   public void addListBySubmitQc(String patientId) throws Exception {
      List<EmrQcPresVeri> presVeriList = new ArrayList();
      List<IndexVo> indexVoList = this.indexService.selectOvertimeWriteList(patientId);
      Date currDate = this.commonService.getDbSysdate();
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      List<Long> lastFinishRuleIdList = (List)indexVoList.stream().map((t) -> t.getLastFinishRuleId()).collect(Collectors.toList());
      List<QcAgiRule> lastFinishRuleList = this.qcAgiRuleService.selectQcAgiRuleListByIds(lastFinishRuleIdList);
      Map<Long, QcAgiRule> lastFinishRuleMap = (Map)lastFinishRuleList.stream().collect(Collectors.toMap((t) -> t.getId(), Function.identity()));
      List<String> eventCodeList = (List)lastFinishRuleList.stream().map((t) -> t.getAgiEvnt()).collect(Collectors.toList());
      List<QcAgiEven> agiEvenList = this.qcAgiEvenService.selectListByEvenCodes(eventCodeList);
      Map<String, QcAgiEven> agiEvenMap = (Map)agiEvenList.stream().collect(Collectors.toMap((t) -> t.getEvenCode(), Function.identity()));

      for(IndexVo indexVo : indexVoList) {
         EmrQcPresVeri emrQcPresVeri = new EmrQcPresVeri();
         emrQcPresVeri.setId(SnowIdUtils.uniqueLong());
         emrQcPresVeri.setOrgCd(indexVo.getOrgCd());
         emrQcPresVeri.setVeriDate(currDate);
         emrQcPresVeri.setPatientId(indexVo.getPatientId());
         emrQcPresVeri.setMrTypeName(indexVo.getMrTypeName());
         emrQcPresVeri.setMrType(indexVo.getMrType());
         emrQcPresVeri.setMrFileId(String.valueOf(indexVo.getId()));
         emrQcPresVeri.setRecoDate(currDate);
         emrQcPresVeri.setMrFileShowName(indexVo.getMrFileShowName());
         emrQcPresVeri.setMrCreDate(indexVo.getCreDate());
         emrQcPresVeri.setSignTime(indexVo.getFsSignTime());
         emrQcPresVeri.setDeadline(indexVo.getLastFinishTime());
         emrQcPresVeri.setVeriResult(CommonConstants.EMR_QC_PRES_VERI.VERI_RESULT_1);
         int fsotHours = DateUtils.getDateHours(indexVo.getLastFinishTime(), indexVo.getCreDate());
         emrQcPresVeri.setFsotHours((double)fsotHours);
         QcAgiRule qcAgiRule = (QcAgiRule)lastFinishRuleMap.get(indexVo.getLastFinishRuleId());
         QcAgiEven agiEven = (QcAgiEven)agiEvenMap.get(qcAgiRule.getAgiEvnt());
         emrQcPresVeri.setVeriRuleId(indexVo.getLastFinishRuleId());
         emrQcPresVeri.setVeriRuleCd(qcAgiRule.getRuleCode());
         emrQcPresVeri.setVeriRuleName(qcAgiRule.getRuleName());
         emrQcPresVeri.setRuleEventType(qcAgiRule.getAgiEvnt());
         emrQcPresVeri.setRuleEventTypeName(agiEven.getEvenName());
         emrQcPresVeri.setEventId(agiEven.getId());
         emrQcPresVeri.setEventDesc(agiEven.getDesc());
         emrQcPresVeri.setCrePerCode(basEmployee.getEmplNumber());
         emrQcPresVeri.setCrePerName(basEmployee.getEmplName());
         presVeriList.add(emrQcPresVeri);
      }

      if (!presVeriList.isEmpty()) {
         this.emrQcPresVeriMapper.insertList(presVeriList);
      }

   }
}
