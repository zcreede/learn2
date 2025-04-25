package com.emr.project.qc.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.qc.domain.QcScoreScheDept;
import com.emr.project.qc.domain.vo.QcScoreScheDeptVo;
import com.emr.project.qc.domain.vo.QcScoreScheVo;
import com.emr.project.qc.mapper.QcScoreScheDeptMapper;
import com.emr.project.qc.service.IQcScoreScheDeptService;
import com.emr.project.system.domain.BasEmployee;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QcScoreScheDeptServiceImpl implements IQcScoreScheDeptService {
   @Autowired
   private QcScoreScheDeptMapper qcScoreScheDeptMapper;
   @Autowired
   private IQcScoreScheDeptService qcScoreScheDeptService;

   public QcScoreScheDept selectQcScoreScheDeptById(Long id) {
      return this.qcScoreScheDeptMapper.selectQcScoreScheDeptById(id);
   }

   public List selectQcScoreScheDeptList(QcScoreScheDept qcScoreScheDept) {
      return this.qcScoreScheDeptMapper.selectQcScoreScheDeptList(qcScoreScheDept);
   }

   public List selectQcScoreScheDeptAllList() throws Exception {
      return this.qcScoreScheDeptMapper.selectQcScoreScheDeptAllList();
   }

   public List selectQcScoreScheDeptListByScheId(Long scheId) throws Exception {
      List<QcScoreScheDept> qcScoreScheDeptList = this.qcScoreScheDeptMapper.selectQcScoreScheDeptListByScheId(scheId);
      return qcScoreScheDeptList;
   }

   public List selectQcScoreScheDeptListScheId(Long scheId) throws Exception {
      List<QcScoreScheDept> qcScoreScheDeptList = this.qcScoreScheDeptMapper.selectQcScoreScheDeptListByScheId(scheId);
      List<QcScoreScheDept> deptAllList = this.qcScoreScheDeptService.selectQcScoreScheDeptAllList();
      List<QcScoreScheDept> resultDeptList = new ArrayList();
      if (qcScoreScheDeptList != null && !qcScoreScheDeptList.isEmpty() && ((QcScoreScheDept)qcScoreScheDeptList.get(0)).getDeptCd().equals("000000")) {
         resultDeptList.addAll(deptAllList);
      } else {
         resultDeptList.addAll(qcScoreScheDeptList);
      }

      return resultDeptList;
   }

   public List selectUnDeptList(QcScoreScheDept qcScoreScheDept) throws Exception {
      List<QcScoreScheDept> resultList = new ArrayList();
      QcScoreScheVo qcScoreScheVo = new QcScoreScheVo();
      qcScoreScheVo.setAppSeg(qcScoreScheDept.getAppSeg());
      List<QcScoreScheDept> qcScoreScheDeptList = this.qcScoreScheDeptMapper.selectScheChooseDept(qcScoreScheVo);
      if (qcScoreScheDeptList == null || qcScoreScheDeptList.isEmpty() || !((QcScoreScheDept)qcScoreScheDeptList.get(0)).getDeptCd().equals("000000")) {
         resultList = this.qcScoreScheDeptMapper.selectUnDeptList(qcScoreScheDept);
      }

      return resultList;
   }

   public int insertQcScoreScheDept(QcScoreScheDept qcScoreScheDept) {
      return this.qcScoreScheDeptMapper.insertQcScoreScheDept(qcScoreScheDept);
   }

   public void insertQcScoreScheDeptList(QcScoreScheDeptVo qcScoreScheDeptVo) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      List<QcScoreScheDept> qcScoreScheDeptList = new ArrayList();

      for(String deptCode : qcScoreScheDeptVo.getDeptList()) {
         QcScoreScheDept qcScoreScheDept = new QcScoreScheDept();
         qcScoreScheDept.setId(SnowIdUtils.uniqueLong());
         qcScoreScheDept.setValidFlag("1");
         qcScoreScheDept.setCrePerCode(basEmployee.getEmplNumber());
         qcScoreScheDept.setCrePerName(basEmployee.getEmplName());
         qcScoreScheDept.setDeptCd(deptCode);
         qcScoreScheDept.setScheId(qcScoreScheDeptVo.getScheId());
         qcScoreScheDeptList.add(qcScoreScheDept);
      }

      this.qcScoreScheDeptMapper.insertQcScoreScheDeptList(qcScoreScheDeptList);
   }

   public int updateQcScoreScheDept(QcScoreScheDept qcScoreScheDept) {
      return this.qcScoreScheDeptMapper.updateQcScoreScheDept(qcScoreScheDept);
   }

   public int deleteQcScoreScheDeptByIds(Long[] ids) {
      return this.qcScoreScheDeptMapper.deleteQcScoreScheDeptByIds(ids);
   }

   public int deleteQcScoreScheDeptById(Long id) {
      return this.qcScoreScheDeptMapper.deleteQcScoreScheDeptById(id);
   }

   public void deleteQcScoreScheDeptByScheId(Long scheId) throws Exception {
      this.qcScoreScheDeptMapper.deleteQcScoreScheDeptByScheId(scheId);
   }

   public List selectScheChooseDept(QcScoreScheVo qcScoreScheVo) throws Exception {
      return this.qcScoreScheDeptMapper.selectScheChooseDept(qcScoreScheVo);
   }
}
