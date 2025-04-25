package com.emr.project.qc.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.qc.domain.QcScoreDedDetail;
import com.emr.project.qc.domain.QcScoreDedRule;
import com.emr.project.qc.domain.vo.QcScoreDedDetailVo;
import com.emr.project.qc.mapper.QcScoreDedDetailMapper;
import com.emr.project.qc.service.IQcScoreDedDetailService;
import com.emr.project.qc.service.IQcScoreDedRuleService;
import com.emr.project.system.domain.SysUser;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QcScoreDedDetailServiceImpl implements IQcScoreDedDetailService {
   @Autowired
   private QcScoreDedDetailMapper qcScoreDedDetailMapper;
   @Autowired
   private IQcScoreDedRuleService qcScoreDedRuleService;

   public QcScoreDedDetail selectQcScoreDedDetailById(Long id) {
      return this.qcScoreDedDetailMapper.selectQcScoreDedDetailById(id);
   }

   public List selectQcScoreDedDetailList(QcScoreDedDetailVo qcScoreDedDetailVo) throws Exception {
      List<QcScoreDedDetailVo> resultList = this.qcScoreDedDetailMapper.selectQcScoreDedDetailList(qcScoreDedDetailVo);
      if (resultList != null && !resultList.isEmpty()) {
         List<Long> dedIdList = (List)resultList.stream().map((t) -> t.getId()).collect(Collectors.toList());
         List<QcScoreDedRule> scoreDedRuleList = this.qcScoreDedRuleService.selectByDedIds(dedIdList);
         Map<Long, List<QcScoreDedRule>> scoreDedRuleListMap = (Map<Long, List<QcScoreDedRule>>)(scoreDedRuleList != null ? (Map)scoreDedRuleList.stream().collect(Collectors.groupingBy((t) -> t.getDedId())) : new HashMap());

         for(QcScoreDedDetailVo dedDetail : resultList) {
            List<QcScoreDedRule> tempList = (List)scoreDedRuleListMap.get(dedDetail.getId());
            dedDetail.setDedRuleNum(tempList != null ? tempList.size() : 0);
         }
      }

      return resultList;
   }

   public List selectUnScheDedList(QcScoreDedDetail qcScoreDedDetail) {
      return this.qcScoreDedDetailMapper.selectUnScheDedList(qcScoreDedDetail);
   }

   public List selectScheDedList(QcScoreDedDetailVo qcScoreDedDetail) {
      return this.qcScoreDedDetailMapper.selectScheDedList(qcScoreDedDetail);
   }

   public List selectDedDetailList(QcScoreDedDetail qcScoreDedDetail) throws Exception {
      return this.qcScoreDedDetailMapper.selectDedDetailList(qcScoreDedDetail);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public int insertQcScoreDedDetail(QcScoreDedDetailVo qcScoreDedDetail) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      qcScoreDedDetail.setCrePerCode(user.getUserName());
      qcScoreDedDetail.setCrePerName(user.getNickName());
      qcScoreDedDetail.setId(SnowIdUtils.uniqueLong());
      qcScoreDedDetail.setDedCd(String.valueOf(qcScoreDedDetail.getId()));
      qcScoreDedDetail.setDelFlag("0");
      String dedScoreDesc = this.genDedScoreDesc(qcScoreDedDetail);
      qcScoreDedDetail.setDedScoreDesc(dedScoreDesc);
      if (qcScoreDedDetail.getRuleIdList() != null && !qcScoreDedDetail.getRuleIdList().isEmpty()) {
         this.qcScoreDedRuleService.insertList(qcScoreDedDetail.getId(), qcScoreDedDetail.getRuleIdList());
      }

      return this.qcScoreDedDetailMapper.insertQcScoreDedDetail(qcScoreDedDetail);
   }

   private String genDedScoreDesc(QcScoreDedDetailVo qcScoreDedDetail) {
      String dedScoreDesc = "";
      switch (qcScoreDedDetail.getDedType()) {
         case "ITEM_SCORE":
            dedScoreDesc = qcScoreDedDetail.getDedScoreSingle() + "分/项";
            break;
         case "TIME_SCORE":
            dedScoreDesc = qcScoreDedDetail.getDedScoreSingle() + "分/次";
            break;
         case "VETO_CGRADE":
            dedScoreDesc = "单项否决(乙)" + qcScoreDedDetail.getDedScoreSingle() + "分";
            break;
         case "VETO_BGRADE":
            dedScoreDesc = "单项否决(丙)" + qcScoreDedDetail.getDedScoreSingle() + "分";
            break;
         case "ONE_TIME":
            dedScoreDesc = qcScoreDedDetail.getDedScoreSingle() + "分";
            break;
         case "POINT_SCORE":
            dedScoreDesc = qcScoreDedDetail.getDedScoreSingle() + "分/处";
      }

      return dedScoreDesc;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public int updateQcScoreDedDetail(QcScoreDedDetailVo qcScoreDedDetail) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      qcScoreDedDetail.setAltPerCode(user.getUserName());
      qcScoreDedDetail.setAltPerName(user.getNickName());
      String dedScoreDesc = this.genDedScoreDesc(qcScoreDedDetail);
      qcScoreDedDetail.setDedScoreDesc(dedScoreDesc);
      this.qcScoreDedRuleService.deleteByDedId(qcScoreDedDetail.getId());
      if (qcScoreDedDetail.getRuleIdList() != null && !qcScoreDedDetail.getRuleIdList().isEmpty()) {
         this.qcScoreDedRuleService.insertList(qcScoreDedDetail.getId(), qcScoreDedDetail.getRuleIdList());
      }

      return this.qcScoreDedDetailMapper.updateQcScoreDedDetail(qcScoreDedDetail);
   }

   public int deleteQcScoreDedDetailByIds(Long[] ids) {
      return this.qcScoreDedDetailMapper.deleteQcScoreDedDetailByIds(ids);
   }

   public int deleteQcScoreDedDetailById(Long id) {
      return this.qcScoreDedDetailMapper.deleteQcScoreDedDetailById(id);
   }

   public void changeDelFlag(Long id) throws Exception {
      this.qcScoreDedDetailMapper.changeDelFlag(id);
   }
}
