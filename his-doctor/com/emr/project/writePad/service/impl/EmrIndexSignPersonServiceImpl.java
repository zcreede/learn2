package com.emr.project.writePad.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.writePad.domain.EmrIndexSignPerson;
import com.emr.project.writePad.mapper.EmrIndexSignPersonMapper;
import com.emr.project.writePad.service.IEmrIndexSignPersonService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmrIndexSignPersonServiceImpl implements IEmrIndexSignPersonService {
   @Autowired
   private EmrIndexSignPersonMapper emrIndexSignPersonMapper;

   public EmrIndexSignPerson selectEmrIndexSignPersonById(Long id) {
      return this.emrIndexSignPersonMapper.selectEmrIndexSignPersonById(id);
   }

   public List selectEmrIndexSignPersonList(EmrIndexSignPerson emrIndexSignPerson) {
      List<EmrIndexSignPerson> emrIndexSignPersonList = this.emrIndexSignPersonMapper.selectEmrIndexSignPersonList(emrIndexSignPerson);

      for(EmrIndexSignPerson emrIndexSignPerson1 : emrIndexSignPersonList) {
         if (emrIndexSignPerson1.getRelationCode().equals("0")) {
            emrIndexSignPerson1.setRelationCodeType("0");
         } else {
            emrIndexSignPerson1.setRelationCodeType("1");
         }
      }

      return emrIndexSignPersonList;
   }

   public int insertEmrIndexSignPerson(EmrIndexSignPerson emrIndexSignPerson) {
      return this.emrIndexSignPersonMapper.insertEmrIndexSignPerson(emrIndexSignPerson);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public EmrIndexSignPerson updateEmrIndexSignPerson(EmrIndexSignPerson emrIndexSignPerson) throws Exception {
      EmrIndexSignPerson emrIndexSignPersonRes = new EmrIndexSignPerson();
      BasEmployee sysUser = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      EmrIndexSignPerson emrIndexSignPerson1 = new EmrIndexSignPerson();
      emrIndexSignPerson1.setAdmissionNo(emrIndexSignPerson.getAdmissionNo());
      emrIndexSignPerson1.setIdCard(emrIndexSignPerson.getIdCard());
      new ArrayList();
      List list;
      if ("0".equals(emrIndexSignPerson.getRelationCode())) {
         EmrIndexSignPerson emrIndexSignPerson2 = new EmrIndexSignPerson();
         emrIndexSignPerson2.setAdmissionNo(emrIndexSignPerson.getAdmissionNo());
         list = this.emrIndexSignPersonMapper.selectEmrIndexSignPersonList(emrIndexSignPerson2);
      } else {
         list = this.emrIndexSignPersonMapper.selectEmrIndexSignPersonList(emrIndexSignPerson1);
      }

      if (list != null && list.size() > 0) {
         emrIndexSignPerson.setCrePerCode(sysUser.getEmplNumber());
         emrIndexSignPerson.setCrePerName(sysUser.getEmplName());
         emrIndexSignPerson.setCreDate(new Date());
         if ("0".equals(emrIndexSignPerson.getRelationCode())) {
            List<EmrIndexSignPerson> signPersonBr = (List)list.stream().filter((t) -> "0".equals(t.getRelationCode())).collect(Collectors.toList());
            emrIndexSignPerson.setId(((EmrIndexSignPerson)signPersonBr.get(0)).getId());
            this.emrIndexSignPersonMapper.updateEmrIndexSignPersonBr(emrIndexSignPerson);
         } else {
            emrIndexSignPerson.setId(((EmrIndexSignPerson)list.get(0)).getId());
            this.emrIndexSignPersonMapper.updateEmrIndexSignPerson(emrIndexSignPerson);
         }
      } else {
         emrIndexSignPerson.setId(SnowIdUtils.uniqueLong());
         emrIndexSignPerson.setCrePerCode(sysUser.getEmplNumber());
         emrIndexSignPerson.setCrePerName(sysUser.getEmplName());
         emrIndexSignPerson.setCreDate(new Date());
         this.emrIndexSignPersonMapper.insertEmrIndexSignPerson(emrIndexSignPerson);
      }

      BeanUtils.copyProperties(emrIndexSignPerson, emrIndexSignPersonRes);
      return emrIndexSignPersonRes;
   }

   public int deleteEmrIndexSignPersonByIds(Long[] ids) {
      return this.emrIndexSignPersonMapper.deleteEmrIndexSignPersonByIds(ids);
   }

   public int deleteEmrIndexSignPersonById(Long id) {
      return this.emrIndexSignPersonMapper.deleteEmrIndexSignPersonById(id);
   }
}
