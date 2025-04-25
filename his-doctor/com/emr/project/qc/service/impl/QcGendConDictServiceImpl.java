package com.emr.project.qc.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.qc.domain.QcGendConDict;
import com.emr.project.qc.mapper.QcGendConDictMapper;
import com.emr.project.qc.service.IQcGendConDictService;
import com.emr.project.system.domain.BasEmployee;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QcGendConDictServiceImpl implements IQcGendConDictService {
   @Autowired
   private QcGendConDictMapper qcGendConDictMapper;

   public QcGendConDict selectQcGendConDictById(Long id) {
      return this.qcGendConDictMapper.selectQcGendConDictById(id);
   }

   public List selectQcGendConDictList(QcGendConDict qcGendConDict) throws Exception {
      return this.qcGendConDictMapper.selectQcGendConDictList(qcGendConDict);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void insertQcGendConDict(List qcGendConDictList) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      this.qcGendConDictMapper.deleteQcGendConDict();

      for(QcGendConDict qcGendConDict : qcGendConDictList) {
         qcGendConDict.setId(SnowIdUtils.uniqueLong());
         qcGendConDict.setCrePerCode(basEmployee.getEmplNumber());
         qcGendConDict.setCrePerName(basEmployee.getEmplName());
      }

      this.qcGendConDictMapper.insertQcGendConDict(qcGendConDictList);
   }

   public int updateQcGendConDict(QcGendConDict qcGendConDict) {
      return this.qcGendConDictMapper.updateQcGendConDict(qcGendConDict);
   }

   public int deleteQcGendConDictByIds(Long[] ids) {
      return this.qcGendConDictMapper.deleteQcGendConDictByIds(ids);
   }

   public int deleteQcGendConDictById(Long id) {
      return this.qcGendConDictMapper.deleteQcGendConDictById(id);
   }
}
