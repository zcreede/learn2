package com.emr.project.qc.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.qc.domain.QcAgeConDict;
import com.emr.project.qc.mapper.QcAgeConDictMapper;
import com.emr.project.qc.service.IQcAgeConDictService;
import com.emr.project.system.domain.BasEmployee;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QcAgeConDictServiceImpl implements IQcAgeConDictService {
   @Autowired
   private QcAgeConDictMapper qcAgeConDictMapper;

   public QcAgeConDict selectQcAgeConDictById(Long id) {
      return this.qcAgeConDictMapper.selectQcAgeConDictById(id);
   }

   public List selectQcAgeConDictList(QcAgeConDict qcAgeConDict) throws Exception {
      return this.qcAgeConDictMapper.selectQcAgeConDictList(qcAgeConDict);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void insertQcAgeConDict(List qcAgeConDictList) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      this.qcAgeConDictMapper.deleteQcAgeConDict();

      for(QcAgeConDict qcAgeConDict : qcAgeConDictList) {
         qcAgeConDict.setId(SnowIdUtils.uniqueLong());
         qcAgeConDict.setCrePerCode(basEmployee.getEmplNumber());
         qcAgeConDict.setCrePerName(basEmployee.getEmplName());
      }

      this.qcAgeConDictMapper.insertQcAgeConDict(qcAgeConDictList);
   }

   public void updateQcAgeConDict(QcAgeConDict qcAgeConDict) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      qcAgeConDict.setAltPerCode(basEmployee.getEmplNumber());
      qcAgeConDict.setAltPerName(basEmployee.getEmplName());
      this.qcAgeConDictMapper.updateQcAgeConDict(qcAgeConDict);
   }

   public int deleteQcAgeConDictByIds(Long[] ids) {
      return this.qcAgeConDictMapper.deleteQcAgeConDictByIds(ids);
   }

   public int deleteQcAgeConDictById(Long id) {
      return this.qcAgeConDictMapper.deleteQcAgeConDictById(id);
   }
}
