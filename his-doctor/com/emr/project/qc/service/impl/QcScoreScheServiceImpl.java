package com.emr.project.qc.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.qc.domain.QcScoreScheDed;
import com.emr.project.qc.domain.QcScoreScheDept;
import com.emr.project.qc.domain.vo.QcScoreScheDedVo;
import com.emr.project.qc.domain.vo.QcScoreScheDeptVo;
import com.emr.project.qc.domain.vo.QcScoreScheVo;
import com.emr.project.qc.mapper.QcScoreScheMapper;
import com.emr.project.qc.service.IQcScoreScheDedService;
import com.emr.project.qc.service.IQcScoreScheDeptService;
import com.emr.project.qc.service.IQcScoreScheService;
import com.emr.project.system.domain.BasEmployee;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QcScoreScheServiceImpl implements IQcScoreScheService {
   @Autowired
   private QcScoreScheMapper qcScoreScheMapper;
   @Autowired
   private IQcScoreScheDeptService qcScoreScheDeptService;
   @Autowired
   private IQcScoreScheDedService qcScoreScheDedService;

   public QcScoreScheVo selectQcScoreScheById(Long scheId) throws Exception {
      QcScoreScheVo qcScoreScheVo = this.qcScoreScheMapper.selectQcScoreScheById(scheId);
      List<QcScoreScheDept> qcScoreScheDeptList = this.qcScoreScheDeptService.selectQcScoreScheDeptListByScheId(scheId);
      List<QcScoreScheDept> deptAllList = this.qcScoreScheDeptService.selectQcScoreScheDeptAllList();
      List<QcScoreScheDept> resultDeptList = new ArrayList();
      if (qcScoreScheDeptList != null && !qcScoreScheDeptList.isEmpty() && ((QcScoreScheDept)qcScoreScheDeptList.get(0)).getDeptCd().equals("000000")) {
         resultDeptList.addAll(deptAllList);
         qcScoreScheVo.setDeptFlag("0");
      } else {
         resultDeptList.addAll(qcScoreScheDeptList);
         qcScoreScheVo.setDeptFlag("1");
      }

      qcScoreScheVo.setQcScoreScheDeptList(resultDeptList);
      qcScoreScheVo.setDeptNum(resultDeptList != null ? resultDeptList.size() + "" : "0");
      return qcScoreScheVo;
   }

   public List selectQcScoreScheList(QcScoreScheVo qcScoreScheVo) throws Exception {
      qcScoreScheVo.setDelFlag("0");
      List<QcScoreScheVo> qcScoreScheVoList = this.qcScoreScheMapper.selectQcScoreScheList(qcScoreScheVo);
      if (qcScoreScheVoList != null && !qcScoreScheVoList.isEmpty()) {
         List<QcScoreScheDept> qcScoreScheDeptList = this.qcScoreScheDeptService.selectQcScoreScheDeptListByScheId((Long)null);
         if (qcScoreScheDeptList != null && !qcScoreScheDeptList.isEmpty()) {
            List<QcScoreScheDept> deptAllList = this.qcScoreScheDeptService.selectQcScoreScheDeptAllList();
            Map<Long, List<QcScoreScheDept>> deptMap = (Map)qcScoreScheDeptList.stream().collect(Collectors.groupingBy((s) -> s.getScheId()));

            for(QcScoreScheVo sche : qcScoreScheVoList) {
               List<QcScoreScheDept> resultDeptList = new ArrayList();
               List<QcScoreScheDept> deptList = (List)deptMap.get(sche.getScheId());
               if (deptList != null && !deptList.isEmpty() && ((QcScoreScheDept)deptList.get(0)).getDeptCd().equals("000000")) {
                  resultDeptList.addAll(deptAllList);
                  sche.setDeptFlag("0");
               } else {
                  resultDeptList.addAll((Collection)(deptList == null ? new ArrayList() : deptList));
                  sche.setDeptFlag("1");
               }

               sche.setQcScoreScheDeptList(resultDeptList);
               sche.setDeptNum(resultDeptList != null ? resultDeptList.size() + "" : "0");
            }
         }
      }

      return qcScoreScheVoList;
   }

   public void insertQcScoreSche(QcScoreScheVo qcScoreScheVo) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      Long id = SnowIdUtils.uniqueLong();
      qcScoreScheVo.setScheId(id);
      qcScoreScheVo.setDelFlag("0");
      qcScoreScheVo.setCrePerCode(basEmployee.getEmplNumber());
      qcScoreScheVo.setCrePerName(basEmployee.getEmplName());
      if (qcScoreScheVo.getDeptList() != null && qcScoreScheVo.getDeptList().size() > 0) {
         QcScoreScheDeptVo qcScoreScheDeptVo = new QcScoreScheDeptVo();
         qcScoreScheDeptVo.setDeptList(qcScoreScheVo.getDeptList());
         qcScoreScheDeptVo.setScheId(id);
         this.qcScoreScheDeptService.insertQcScoreScheDeptList(qcScoreScheDeptVo);
      }

      this.qcScoreScheMapper.insertQcScoreSche(qcScoreScheVo);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void updateQcScoreSche(QcScoreScheVo qcScoreScheVo) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      Long id = qcScoreScheVo.getScheId();
      qcScoreScheVo.setAltPerCode(basEmployee.getEmplNumber());
      qcScoreScheVo.setAltPerName(basEmployee.getEmplName());
      this.qcScoreScheDeptService.deleteQcScoreScheDeptByScheId(id);
      if (qcScoreScheVo.getDeptList() != null && qcScoreScheVo.getDeptList().size() > 0) {
         QcScoreScheDeptVo qcScoreScheDeptVo = new QcScoreScheDeptVo();
         qcScoreScheDeptVo.setDeptList(qcScoreScheVo.getDeptList());
         qcScoreScheDeptVo.setScheId(id);
         this.qcScoreScheDeptService.insertQcScoreScheDeptList(qcScoreScheDeptVo);
      }

      this.qcScoreScheMapper.updateQcScoreSche(qcScoreScheVo);
   }

   public int deleteQcScoreScheByIds(Long[] scheIds) {
      return this.qcScoreScheMapper.deleteQcScoreScheByIds(scheIds);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void deleteQcScoreScheById(Long scheId) throws Exception {
      QcScoreScheVo qcScoreScheVo = new QcScoreScheVo();
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      qcScoreScheVo.setAltPerCode(basEmployee.getEmplNumber());
      qcScoreScheVo.setAltPerName(basEmployee.getEmplName());
      qcScoreScheVo.setScheId(scheId);
      qcScoreScheVo.setDelFlag("1");
      this.qcScoreScheMapper.updateQcScoreSche(qcScoreScheVo);
      this.qcScoreScheDeptService.deleteQcScoreScheDeptByScheId(scheId);
      this.qcScoreScheDedService.deleteScheDedByScheId(scheId);
   }

   public QcScoreScheVo selectDedEditSearch(Long scheId) throws Exception {
      QcScoreScheVo qcScoreScheVo = this.qcScoreScheMapper.selectQcScoreScheById(scheId);
      if (qcScoreScheVo != null) {
         List<QcScoreScheDept> deptList = this.qcScoreScheDeptService.selectQcScoreScheDeptListScheId(scheId);
         qcScoreScheVo.setDeptNum(deptList != null && !deptList.isEmpty() ? deptList.size() + "" : "0");
         List<QcScoreScheDedVo> qcScoreScheItemList = this.qcScoreScheDedService.selectScheItemAndDed(scheId);
         List<QcScoreScheDedVo> qcScoreScheDedVos = this.qcScoreScheDedService.selectScheDedGroupByItem(scheId);
         Map<Long, List<QcScoreScheDedVo>> map = new HashMap();
         new HashMap();
         if (qcScoreScheDedVos != null && qcScoreScheDedVos.size() > 0) {
            map = (Map)qcScoreScheDedVos.stream().collect(Collectors.groupingBy((s) -> s.getItemId()));
         }

         List<QcScoreScheDedVo> classList = new ArrayList();
         if (qcScoreScheItemList != null && qcScoreScheItemList.size() > 0) {
            Map itemClassMap = (Map)qcScoreScheItemList.stream().collect(Collectors.groupingBy((s) -> s.getItemClassCd()));

            for(String classCd : itemClassMap.keySet()) {
               List<QcScoreScheDedVo> itemClassList = (List)itemClassMap.get(classCd);
               List<QcScoreScheDedVo> itemList = new ArrayList();
               if (itemClassList != null && itemClassList.size() > 0) {
                  Map<Long, List<QcScoreScheDedVo>> itemMap = (Map)itemClassList.stream().collect(Collectors.groupingBy((s) -> s.getItemId()));

                  for(Long itemId : itemMap.keySet()) {
                     List<QcScoreScheDedVo> dedList = (List)itemMap.get(itemId);
                     itemList.add(dedList.get(0));

                     for(QcScoreScheDedVo itemVo : dedList) {
                        List<QcScoreScheDedVo> qcScoreScheDedVoList = new ArrayList();
                        if (map != null && map.size() > 0) {
                           qcScoreScheDedVoList = (List)map.get(itemVo.getItemId());
                        }

                        itemVo.setQcScoreScheDedList(qcScoreScheDedVoList);
                        itemVo.setDedNum(qcScoreScheDedVoList != null ? qcScoreScheDedVoList.size() + "" : "0");
                     }
                  }

                  QcScoreScheDedVo itemClass = new QcScoreScheDedVo();
                  itemClass.setItemClassCd(classCd);
                  itemClass.setItemClassSort(((QcScoreScheDedVo)itemClassList.get(0)).getItemClassSort());
                  itemClass.setItemClassName(((QcScoreScheDedVo)itemClassList.get(0)).getItemClassName());
                  itemList.sort(Comparator.comparing(QcScoreScheDed::getItemSort));
                  itemClass.setQcScoreScheItemList(itemList);
                  classList.add(itemClass);
               }
            }
         }

         if (classList != null && !classList.isEmpty()) {
            classList.sort(Comparator.comparing(QcScoreScheDedVo::getItemClassSort));
         }

         qcScoreScheVo.setQcScoreScheItemClassList(classList);
      }

      return qcScoreScheVo;
   }

   public Boolean selectIsChooseAllDept(QcScoreScheVo qcScoreScheVo) throws Exception {
      Boolean flag = false;
      List<QcScoreScheDept> qcScoreScheDeptList = this.qcScoreScheDeptService.selectScheChooseDept(qcScoreScheVo);
      if (qcScoreScheDeptList != null && !qcScoreScheDeptList.isEmpty()) {
         if (((QcScoreScheDept)qcScoreScheDeptList.get(0)).getDeptCd().equals("000000")) {
            flag = true;
         }
      } else {
         flag = true;
      }

      return flag;
   }
}
