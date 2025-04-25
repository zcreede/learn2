package com.emr.project.qc.service.impl;

import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.qc.domain.QcScoreDedDetail;
import com.emr.project.qc.domain.QcScoreScheDed;
import com.emr.project.qc.domain.vo.EmrQcFlowScoreListVo;
import com.emr.project.qc.domain.vo.EmrQcFlowScoreVo;
import com.emr.project.qc.domain.vo.QcScoreDedDetailVo;
import com.emr.project.qc.domain.vo.QcScoreItemVo;
import com.emr.project.qc.domain.vo.QcScoreScheDedVo;
import com.emr.project.qc.domain.vo.QcScoreScheVo;
import com.emr.project.qc.mapper.QcScoreScheDedMapper;
import com.emr.project.qc.service.IQcScoreScheDedService;
import com.emr.project.qc.service.IQcScoreScheService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysUser;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QcScoreScheDedServiceImpl implements IQcScoreScheDedService {
   @Autowired
   private QcScoreScheDedMapper qcScoreScheDedMapper;
   @Autowired
   private IQcScoreScheService qcScoreScheService;

   public QcScoreScheDed selectQcScoreScheDedById(Long id) {
      return this.qcScoreScheDedMapper.selectQcScoreScheDedById(id);
   }

   public List selectScheDedListByItem(Long itemId) throws Exception {
      return this.qcScoreScheDedMapper.selectScheDedListByItem(itemId);
   }

   public List selectScheDedListByDedId(Long dedId) throws Exception {
      return this.qcScoreScheDedMapper.selectScheDedListByDedId(dedId);
   }

   public List selectQcScoreScheDedList(QcScoreScheDed qcScoreScheDed) throws Exception {
      return this.qcScoreScheDedMapper.selectQcScoreScheDedList(qcScoreScheDed);
   }

   public EmrQcFlowScoreVo selectScheDedList(QcScoreScheDed qcScoreScheDed) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      EmrQcFlowScoreVo emrQcFlowScoreVo = new EmrQcFlowScoreVo();
      QcScoreScheVo qcScoreSche = this.qcScoreScheService.selectQcScoreScheById(qcScoreScheDed.getScheId());
      List<QcScoreScheDedVo> qcScoreScheDedVoList = this.qcScoreScheDedMapper.selectScheDedList(qcScoreScheDed);
      Double totalScore = qcScoreSche.getScore();
      List<QcScoreItemVo> qcScoreItemClassVoList = new ArrayList();
      if (qcScoreScheDedVoList != null && qcScoreScheDedVoList.size() > 0) {
         totalScore = ((QcScoreScheDedVo)qcScoreScheDedVoList.get(0)).getSchetotalscore();
         Map<String, List<QcScoreScheDedVo>> itemClassMap = (Map)qcScoreScheDedVoList.stream().collect(Collectors.groupingBy((s) -> s.getItemClassCd()));

         for(String itemClassCd : itemClassMap.keySet()) {
            Double itemClassTotal = (double)0.0F;
            List<QcScoreDedDetailVo> qcScoreDedDetailVoList = new ArrayList();
            List<QcScoreScheDedVo> dedVoList = (List)itemClassMap.get(itemClassCd);
            if (dedVoList != null & !dedVoList.isEmpty()) {
               Map<Long, List<QcScoreScheDedVo>> itemMap = (Map)dedVoList.stream().collect(Collectors.groupingBy((s) -> s.getItemId()));

               for(Long itemId : itemMap.keySet()) {
                  List<QcScoreScheDedVo> itemList = (List)itemMap.get(itemId);
                  if (itemList != null && !itemList.isEmpty()) {
                     itemClassTotal = itemClassTotal + ((QcScoreScheDedVo)itemList.get(0)).getItemTotalScore();

                     for(QcScoreScheDedVo item : itemList) {
                        QcScoreDedDetailVo qcScoreDedDetailVo = new QcScoreDedDetailVo();
                        qcScoreDedDetailVo.setDedScoreDesc(item.getDedScoreDesc());
                        qcScoreDedDetailVo.setItemName(item.getItemName());
                        qcScoreDedDetailVo.setItemCd(item.getItemId().toString());
                        qcScoreDedDetailVo.setItemTotalScore(item.getItemTotalScore());
                        qcScoreDedDetailVo.setItemDesc(item.getItemDesc());
                        qcScoreDedDetailVo.setDedName(item.getDedName());
                        qcScoreDedDetailVo.setDedDesc(item.getDedDesc());
                        qcScoreDedDetailVo.setItemSort(item.getItemSort().intValue());
                        qcScoreDedDetailVo.setItemClassName(item.getItemClassName());
                        qcScoreDedDetailVo.setScoreDesc("-1");
                        qcScoreDedDetailVo.setDedItemDesc("共2项，扣分2次");
                        List<EmrQcFlowScoreListVo> emrQcFlowScoreListVos = new ArrayList();
                        EmrQcFlowScoreListVo emrQcFlowScoreListVo = new EmrQcFlowScoreListVo();
                        emrQcFlowScoreListVo.setQcElemName("主诉、现病史、既往史");
                        emrQcFlowScoreListVo.setEmrTypeName("入院记录");
                        emrQcFlowScoreListVo.setRuleElemName("主诉");
                        emrQcFlowScoreListVo.setRuleName("入院记录必填");
                        EmrQcFlowScoreListVo emrQcFlowScoreListVo1 = new EmrQcFlowScoreListVo();
                        emrQcFlowScoreListVo1.setQcElemName("主诉、现病史、既往史");
                        emrQcFlowScoreListVo1.setEmrTypeName("入院记录");
                        emrQcFlowScoreListVo1.setRuleElemName("主诉");
                        emrQcFlowScoreListVo1.setRuleName("入院记录必填");
                        emrQcFlowScoreListVos.add(emrQcFlowScoreListVo);
                        emrQcFlowScoreListVos.add(emrQcFlowScoreListVo1);
                        qcScoreDedDetailVo.setEmrQcFlowScoreListVos(emrQcFlowScoreListVos);
                        qcScoreDedDetailVoList.add(qcScoreDedDetailVo);
                     }
                  }
               }
            }

            QcScoreItemVo qcScoreItemVo = new QcScoreItemVo();
            qcScoreItemVo.setItemClassCd(((QcScoreScheDedVo)dedVoList.get(0)).getItemClassCd());
            qcScoreItemVo.setItemClassName(((QcScoreScheDedVo)dedVoList.get(0)).getItemClassName());
            qcScoreItemVo.setItemClassSort(((QcScoreScheDedVo)dedVoList.get(0)).getItemClassSort());
            qcScoreItemVo.setScore(itemClassTotal);
            qcScoreItemVo.setItemDedScore((double)0.0F);
            qcScoreDedDetailVoList.sort(Comparator.comparing(QcScoreDedDetail::getItemSort));
            qcScoreItemVo.setQcScoreDedDetailVoList(qcScoreDedDetailVoList);
            qcScoreItemClassVoList.add(qcScoreItemVo);
         }
      }

      emrQcFlowScoreVo.setScheName(qcScoreSche.getScheName());
      emrQcFlowScoreVo.setAppSeg(qcScoreSche.getAppSegName());
      emrQcFlowScoreVo.setScoreDoc(sysUser.getBasEmployee().getEmplName());
      emrQcFlowScoreVo.setScoreDate(DateUtils.getNowDate());
      emrQcFlowScoreVo.setTotalScore(totalScore);
      emrQcFlowScoreVo.setLevelName("乙级");
      qcScoreItemClassVoList.sort(Comparator.comparing(QcScoreItemVo::getItemClassSort));
      emrQcFlowScoreVo.setQcScoreItemVoList(qcScoreItemClassVoList);
      return emrQcFlowScoreVo;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void insertQcScoreScheDed(QcScoreScheDedVo qcScoreScheDedVo) throws Exception {
      this.qcScoreScheDedMapper.deleteQcScoreScheDedByScheId(qcScoreScheDedVo.getScheId(), qcScoreScheDedVo.getItemId());
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      List<QcScoreScheDed> result = new ArrayList();

      for(QcScoreScheDedVo qcScoreScheDedVo1 : qcScoreScheDedVo.getQcScoreScheDedList()) {
         QcScoreScheDed qcScoreScheDed = new QcScoreScheDed();
         qcScoreScheDed.setId(SnowIdUtils.uniqueLong());
         qcScoreScheDed.setScheId(qcScoreScheDedVo.getScheId());
         qcScoreScheDed.setScheName(qcScoreScheDedVo.getScheName());
         qcScoreScheDed.setItemId(qcScoreScheDedVo.getItemId());
         qcScoreScheDed.setItemName(qcScoreScheDedVo.getItemName());
         qcScoreScheDed.setItemSort(qcScoreScheDedVo.getItemSort());
         qcScoreScheDed.setItemTotalScore(qcScoreScheDedVo.getItemTotalScore());
         qcScoreScheDed.setDedId(qcScoreScheDedVo1.getDedId());
         qcScoreScheDed.setDedName(qcScoreScheDedVo1.getDedName());
         qcScoreScheDed.setDedSort(qcScoreScheDedVo1.getDedSort());
         qcScoreScheDed.setCrePerCode(basEmployee.getEmplNumber());
         qcScoreScheDed.setCrePerName(basEmployee.getEmplName());
         qcScoreScheDed.setValidFlag("1");
         result.add(qcScoreScheDed);
      }

      this.qcScoreScheDedMapper.insertQcScoreScheDedList(result);
   }

   public int updateQcScoreScheDed(QcScoreScheDed qcScoreScheDed) {
      return this.qcScoreScheDedMapper.updateQcScoreScheDed(qcScoreScheDed);
   }

   public int deleteQcScoreScheDedByIds(Long[] ids) {
      return this.qcScoreScheDedMapper.deleteQcScoreScheDedByIds(ids);
   }

   public int deleteQcScoreScheDedById(Long id) throws Exception {
      return this.qcScoreScheDedMapper.deleteQcScoreScheDedById(id);
   }

   public void deleteQcScoreScheDedByScheId(Long scheId, Long itemId) throws Exception {
      this.qcScoreScheDedMapper.deleteQcScoreScheDedByScheId(scheId, itemId);
   }

   public void deleteScheDedByScheId(Long scheId) throws Exception {
      this.qcScoreScheDedMapper.deleteScheDedByScheId(scheId);
   }

   public List selectQcScoreItemList(QcScoreScheDedVo qcScoreScheDedVo) throws Exception {
      return this.qcScoreScheDedMapper.selectQcScoreItemList(qcScoreScheDedVo);
   }

   public List selectScheItemAndDed(Long scheId) {
      return this.qcScoreScheDedMapper.selectScheItemAndDed(scheId);
   }

   public List selectScheDedGroupByItem(Long scheId) {
      return this.qcScoreScheDedMapper.selectScheDedGroupByItem(scheId);
   }

   public List selectScheDedDetailBysche(QcScoreScheDedVo qcScoreScheDedVo) throws Exception {
      return this.qcScoreScheDedMapper.selectScheDedDetailBysche(qcScoreScheDedVo);
   }
}
