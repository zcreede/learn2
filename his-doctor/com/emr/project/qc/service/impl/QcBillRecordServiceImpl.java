package com.emr.project.qc.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.qc.domain.EmrQcRecord;
import com.emr.project.qc.domain.QcBillRecord;
import com.emr.project.qc.domain.vo.QcBillRecordVo;
import com.emr.project.qc.domain.vo.RunTimeQcCheckVo;
import com.emr.project.qc.mapper.QcBillRecordMapper;
import com.emr.project.qc.service.IEmrQcRecordService;
import com.emr.project.qc.service.IQcBillRecordService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysUser;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QcBillRecordServiceImpl implements IQcBillRecordService {
   @Autowired
   private QcBillRecordMapper qcBillRecordMapper;
   @Autowired
   private IEmrQcRecordService emrQcRecordService;
   @Autowired
   private IVisitinfoService visitinfoService;

   public QcBillRecord selectQcBillRecordById(Long id) {
      return this.qcBillRecordMapper.selectQcBillRecordById(id);
   }

   public List selectQcBillRecordList(QcBillRecord qcBillRecord) {
      return this.qcBillRecordMapper.selectQcBillRecordList(qcBillRecord);
   }

   public List selectQcBillRecordVoList(QcBillRecordVo qcBillRecord) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      qcBillRecord.setOrgCd(user.getHospital().getOrgCode());
      qcBillRecord.setQcDepartCode(user.getDept().getDeptCode());
      List<QcBillRecord> list = this.qcBillRecordMapper.selectQcBillRecordList(qcBillRecord);
      List<QcBillRecordVo> resList = new ArrayList(list.size());
      if (list != null && !list.isEmpty()) {
         List<Long> qcBillNoList = (List)list.stream().map((t) -> t.getId()).collect(Collectors.toList());
         List<EmrQcRecord> qcRecordList = this.emrQcRecordService.selectListByQcBillNos(qcBillNoList);
         Map<Long, List<EmrQcRecord>> qcRecordListMap = (Map)qcRecordList.stream().collect(Collectors.groupingBy((t) -> t.getQcBillNo()));

         for(QcBillRecord qbr : list) {
            List<EmrQcRecord> tempList = (List)qcRecordListMap.get(qbr.getId());
            if (tempList != null && !tempList.isEmpty()) {
               Map<String, List<EmrQcRecord>> tempListStateMap = (Map)tempList.stream().collect(Collectors.groupingBy((t) -> t.getState()));
               QcBillRecordVo qbrVo = new QcBillRecordVo();
               BeanUtils.copyProperties(qbr, qbrVo);
               Integer checkingNum = tempListStateMap.get("1") != null ? ((List)tempListStateMap.get("1")).size() : 0;
               Integer checkedNum = tempListStateMap.get("3") != null ? ((List)tempListStateMap.get("3")).size() : 0;
               Integer checkModifiedNum = tempListStateMap.get("31") != null ? ((List)tempListStateMap.get("31")).size() : 0;
               Integer checkFinishNum = tempListStateMap.get("2") != null ? ((List)tempListStateMap.get("2")).size() : 0;
               qbrVo.setCheckingNum(checkingNum);
               qbrVo.setCheckedNum(checkedNum);
               qbrVo.setCheckModifiedNum(checkModifiedNum);
               qbrVo.setCheckFinishNum(checkFinishNum);
               resList.add(qbrVo);
            }
         }
      }

      return resList;
   }

   public List selectQcBillRecordVoListPage(List listAll, Integer pageNum, Integer pageSize) throws Exception {
      List<QcBillRecordVo> list = new ArrayList();
      if (listAll != null && listAll.size() > 0) {
         int begin = 0;
         int end = 0;
         if (pageSize != 0) {
            begin = (pageNum - 1) * pageSize;
            end = pageNum * pageSize;
            if (end > listAll.size()) {
               end = listAll.size();
            }
         }

         if (end == 0) {
            list.addAll(listAll);
         } else {
            list = listAll.subList(begin, end);
         }
      }

      return list;
   }

   public int insertQcBillRecord(QcBillRecord qcBillRecord) {
      return this.qcBillRecordMapper.insertQcBillRecord(qcBillRecord);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void insertQcBillRecord(QcBillRecordVo qcBillRecord, RunTimeQcCheckVo runTimeQcCheckVo) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      BasEmployee employee = user.getBasEmployee();
      qcBillRecord.setId(SnowIdUtils.uniqueLong());
      List<RunTimeQcCheckVo> runTimeQcCheckVoList = this.visitinfoService.selectRunTimeQcCheckVoNoInfo(runTimeQcCheckVo);
      if (qcBillRecord.getPatientFlag().equals("1")) {
         runTimeQcCheckVoList = (List)runTimeQcCheckVoList.stream().filter((t) -> t.getCheckNum() == null).collect(Collectors.toList());
      } else if (qcBillRecord.getPatientFlag().equals("2")) {
         runTimeQcCheckVoList = (List)runTimeQcCheckVoList.stream().filter((t) -> t.getCheckNum() != null).collect(Collectors.toList());
      }

      int total = runTimeQcCheckVoList.size();
      int checkNum = 0;
      if (qcBillRecord.getCheckWay().equals("1")) {
         checkNum = qcBillRecord.getSampleNum() > total ? total : qcBillRecord.getSampleNum();
      } else if (qcBillRecord.getCheckWay().equals("2")) {
         checkNum = (new Double(qcBillRecord.getSampleRate() * (double)total)).intValue() / 100;
      }

      List<RunTimeQcCheckVo> resList = new ArrayList(checkNum);
      List<EmrQcRecord> emrQcRecordList = new ArrayList(checkNum);

      for(Integer n : this.getRandomSet(checkNum, runTimeQcCheckVoList.size())) {
         RunTimeQcCheckVo visitinfoVo = (RunTimeQcCheckVo)runTimeQcCheckVoList.get(n);
         resList.add(visitinfoVo);
         EmrQcRecord emrQcRecord = new EmrQcRecord();
         emrQcRecord.setId(SnowIdUtils.uniqueLong());
         emrQcRecord.setPatientId(visitinfoVo.getPatientId());
         emrQcRecord.setPatientName(visitinfoVo.getPatientName());
         emrQcRecord.setSex(visitinfoVo.getSex());
         emrQcRecord.setInpNo(visitinfoVo.getInpNo());
         emrQcRecord.setInDeptCd(visitinfoVo.getDeptCd());
         emrQcRecord.setInDeptName(visitinfoVo.getDeptName());
         emrQcRecord.setQcSection("03");
         emrQcRecord.setQcdoctCd(employee.getEmplNumber());
         emrQcRecord.setQcdoctName(employee.getEmplName());
         emrQcRecord.setCrePerCode(employee.getEmplNumber());
         emrQcRecord.setCrePerName(employee.getEmplName());
         emrQcRecord.setState("1");
         emrQcRecord.setStateName("质控中");
         emrQcRecord.setQcBillNo(qcBillRecord.getId());
         emrQcRecordList.add(emrQcRecord);
      }

      qcBillRecord.setOrgCd(user.getHospital().getOrgCode());
      qcBillRecord.setQcDepartCode(user.getDept().getDeptCode());
      qcBillRecord.setQcDepartName(user.getDept().getDeptName());
      qcBillRecord.setQcdoctCd(employee.getEmplNumber());
      qcBillRecord.setQcdoctName(employee.getEmplName());
      qcBillRecord.setCheckWayName(qcBillRecord.getCheckWay().equals("1") ? "指定数量" : "指定比例");
      qcBillRecord.setCheckCount(checkNum);
      qcBillRecord.setCrePerCode(employee.getEmplNumber());
      qcBillRecord.setCrePerName(employee.getEmplName());
      this.qcBillRecordMapper.insertQcBillRecord(qcBillRecord);
      if (emrQcRecordList != null && !emrQcRecordList.isEmpty()) {
         this.emrQcRecordService.insertList(emrQcRecordList);
      }

   }

   public Set getRandomSet(int size, int max) {
      Set<Integer> result = new LinkedHashSet(size);
      if (size == max) {
         for(int i = 0; i < max; ++i) {
            result.add(i);
         }
      } else {
         Random random = new Random();

         while(result.size() < size) {
            Integer next = random.nextInt(max);
            result.add(next);
         }
      }

      return result;
   }

   public int updateQcBillRecord(QcBillRecord qcBillRecord) {
      return this.qcBillRecordMapper.updateQcBillRecord(qcBillRecord);
   }

   public int deleteQcBillRecordByIds(Long[] ids) {
      return this.qcBillRecordMapper.deleteQcBillRecordByIds(ids);
   }

   public int deleteQcBillRecordById(Long id) {
      return this.qcBillRecordMapper.deleteQcBillRecordById(id);
   }
}
