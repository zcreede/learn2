package com.emr.project.qc.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.qc.domain.QcRuleIterEmr;
import com.emr.project.qc.domain.vo.QcRuleIterEmrVo;
import com.emr.project.qc.mapper.QcRuleIterEmrMapper;
import com.emr.project.qc.service.IQcRuleIterEmrService;
import com.emr.project.system.domain.BasEmployee;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QcRuleIterEmrServiceImpl implements IQcRuleIterEmrService {
   @Autowired
   private QcRuleIterEmrMapper qcRuleIterEmrMapper;

   public QcRuleIterEmr selectQcRuleIterEmrById(Long id) {
      return this.qcRuleIterEmrMapper.selectQcRuleIterEmrById(id);
   }

   public List selectQcRuleIterEmrList(QcRuleIterEmrVo qcRuleIterEmrVo) throws Exception {
      return this.qcRuleIterEmrMapper.selectQcRuleIterEmrList(qcRuleIterEmrVo);
   }

   public void insertQcRuleIterEmr(QcRuleIterEmr qcRuleIterEmr) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      qcRuleIterEmr.setId(SnowIdUtils.uniqueLong());
      qcRuleIterEmr.setDelFlag("0");
      qcRuleIterEmr.setCrePerCode(basEmployee.getEmplNumber());
      qcRuleIterEmr.setCrePerName(basEmployee.getEmplName());
      this.qcRuleIterEmrMapper.insertQcRuleIterEmr(qcRuleIterEmr);
   }

   public void updateQcRuleIterEmr(QcRuleIterEmr qcRuleIterEmr) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      qcRuleIterEmr.setAltPerCode(basEmployee.getEmplNumber());
      qcRuleIterEmr.setAltPerName(basEmployee.getEmplName());
      this.qcRuleIterEmrMapper.updateQcRuleIterEmr(qcRuleIterEmr);
   }

   public int deleteQcRuleIterEmrByIds(Long[] ids) {
      return this.qcRuleIterEmrMapper.deleteQcRuleIterEmrByIds(ids);
   }

   public void deleteQcRuleIterEmrById(Long id) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      QcRuleIterEmr qcRuleIterEmr = new QcRuleIterEmr();
      qcRuleIterEmr.setId(id);
      qcRuleIterEmr.setDelFlag("1");
      qcRuleIterEmr.setAltPerCode(basEmployee.getEmplNumber());
      qcRuleIterEmr.setAltPerName(basEmployee.getEmplName());
      this.qcRuleIterEmrMapper.updateQcRuleIterEmr(qcRuleIterEmr);
   }

   public List getIterTypeCodeByEmrTypeCode(String emrTypeCode) throws Exception {
      QcRuleIterEmrVo param = new QcRuleIterEmrVo();
      param.setEmrTypeCode(emrTypeCode);
      param.setDelFlag("0");
      List<QcRuleIterEmr> qcRuleIterEmrList = this.selectQcRuleIterEmrList(param);
      List<String> iterTypeCodeListTemp = (List)qcRuleIterEmrList.stream().map((t) -> t.getIterTypeCode()).collect(Collectors.toList());
      List<String> iterTypeCodeList = new ArrayList(1);

      for(String iterTypeCode : iterTypeCodeListTemp) {
         String[] iterTypeCodeArr = iterTypeCode.split(",");
         iterTypeCodeList.addAll(Arrays.asList(iterTypeCodeArr));
      }

      iterTypeCodeList.add(emrTypeCode);
      iterTypeCodeList = (List)iterTypeCodeList.stream().distinct().collect(Collectors.toList());
      return iterTypeCodeList;
   }

   public List getIterTypeCodeByEmrTypeOrIter(String emrTypeCode) throws Exception {
      List<String> iterTypeCodeList = new ArrayList(1);
      QcRuleIterEmrVo param = new QcRuleIterEmrVo();
      param.setEmrTypeCode(emrTypeCode);
      param.setDelFlag("0");
      param.setValidFlag("1");
      List<QcRuleIterEmr> qcRuleIterEmrList = this.selectQcRuleIterEmrList(param);
      List<String> iterTypeCodeListTemp = (List)qcRuleIterEmrList.stream().map((t) -> t.getIterTypeCode()).collect(Collectors.toList());
      param = new QcRuleIterEmrVo();
      param.setIterTypeCodeList(Arrays.asList(emrTypeCode));
      param.setEmrTypeCode((String)null);
      param.setDelFlag("0");
      param.setValidFlag("1");
      List<QcRuleIterEmr> qcRuleIterEmrList2 = this.selectQcRuleIterEmrList(param);
      List<String> iterTypeCodeListTemp2 = CollectionUtils.isNotEmpty(qcRuleIterEmrList2) ? (List)qcRuleIterEmrList2.stream().map((t) -> t.getIterTypeCode()).collect(Collectors.toList()) : null;
      List<String> iterTypeCodeListTemp3 = CollectionUtils.isNotEmpty(qcRuleIterEmrList2) ? (List)qcRuleIterEmrList2.stream().map((t) -> t.getEmrTypeCode()).collect(Collectors.toList()) : null;
      if (CollectionUtils.isNotEmpty(iterTypeCodeListTemp2)) {
         iterTypeCodeListTemp.addAll(iterTypeCodeListTemp2);
      }

      if (CollectionUtils.isNotEmpty(iterTypeCodeListTemp3)) {
         iterTypeCodeList.addAll(iterTypeCodeListTemp3);
      }

      for(String iterTypeCode : iterTypeCodeListTemp) {
         String[] iterTypeCodeArr = iterTypeCode.split(",");
         iterTypeCodeList.addAll(Arrays.asList(iterTypeCodeArr));
      }

      iterTypeCodeList.add(emrTypeCode);
      iterTypeCodeList = (List)iterTypeCodeList.stream().distinct().collect(Collectors.toList());
      return iterTypeCodeList;
   }

   public Map getIterTypeCodeByEmrTypeCodes(List emrTypeCodeList) throws Exception {
      List<QcRuleIterEmr> qcRuleIterEmrList = this.qcRuleIterEmrMapper.selectListByEmrTypeList(emrTypeCodeList);
      Map<String, List<QcRuleIterEmr>> qcRuleIterEmrListMap = (Map)qcRuleIterEmrList.stream().collect(Collectors.groupingBy((t) -> t.getEmrTypeCode()));
      Map<String, List<String>> iterTypeCodeListMap = new HashMap(1);

      for(String emrTypeCode : emrTypeCodeList) {
         List<String> emrTypeCodeListTemp = new ArrayList(1);
         List<QcRuleIterEmr> tempList = (List)qcRuleIterEmrListMap.get(emrTypeCode);
         if (tempList != null) {
            List var11 = (List)tempList.stream().map((t) -> t.getIterTypeCode()).collect(Collectors.toList());
            String iterTypeCodes = StringUtils.join(var11.toArray(), ",");
            String[] iterTypeCodeArr = iterTypeCodes.split(",");
            emrTypeCodeListTemp = new ArrayList(iterTypeCodeArr.length);
            emrTypeCodeListTemp.addAll(Arrays.asList(iterTypeCodeArr));
         }

         emrTypeCodeListTemp.add(emrTypeCode);
         emrTypeCodeListTemp = (List)emrTypeCodeListTemp.stream().distinct().collect(Collectors.toList());
         iterTypeCodeListMap.put(emrTypeCode, emrTypeCodeListTemp);
      }

      return iterTypeCodeListMap;
   }

   public List selectListByEmrTypeList(List emrTypeCodeList) throws Exception {
      return this.qcRuleIterEmrMapper.selectListByEmrTypeList(emrTypeCodeList);
   }
}
