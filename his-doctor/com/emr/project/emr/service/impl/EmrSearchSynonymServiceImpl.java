package com.emr.project.emr.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.emr.domain.EmrSearchSynonym;
import com.emr.project.emr.mapper.EmrSearchSynonymMapper;
import com.emr.project.emr.service.IEmrSearchSynonymService;
import com.emr.project.system.domain.BasEmployee;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmrSearchSynonymServiceImpl implements IEmrSearchSynonymService {
   @Autowired
   private EmrSearchSynonymMapper emrSearchSynonymMapper;

   public EmrSearchSynonym selectEmrSearchSynonymById(Long id) {
      return this.emrSearchSynonymMapper.selectEmrSearchSynonymById(id);
   }

   public List selectEmrSearchSynonymList(EmrSearchSynonym emrSearchSynonym) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      emrSearchSynonym.setEmplNumber(basEmployee.getEmplNumber());
      return this.emrSearchSynonymMapper.selectEmrSearchSynonymList(emrSearchSynonym);
   }

   public int insertEmrSearchSynonym(EmrSearchSynonym emrSearchSynonym) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      emrSearchSynonym.setId(SnowIdUtils.uniqueLong());
      emrSearchSynonym.setEmplNumber(basEmployee.getEmplNumber());
      emrSearchSynonym.setEmplName(basEmployee.getEmplName());
      emrSearchSynonym.setCrePerCode(basEmployee.getEmplNumber());
      emrSearchSynonym.setCrePerName(basEmployee.getEmplName());
      emrSearchSynonym.setTypeFlag(0);
      return this.emrSearchSynonymMapper.insertEmrSearchSynonym(emrSearchSynonym);
   }

   public int updateEmrSearchSynonym(EmrSearchSynonym emrSearchSynonym) {
      return this.emrSearchSynonymMapper.updateEmrSearchSynonym(emrSearchSynonym);
   }

   public int deleteEmrSearchSynonymByIds(Long[] ids) {
      return this.emrSearchSynonymMapper.deleteEmrSearchSynonymByIds(ids);
   }

   public int deleteEmrSearchSynonymById(Long id) throws Exception {
      return this.emrSearchSynonymMapper.deleteEmrSearchSynonymById(id);
   }

   public List selectListByEmpl(String emplNumber) {
      return this.emrSearchSynonymMapper.selectListByEmpl(emplNumber);
   }
}
