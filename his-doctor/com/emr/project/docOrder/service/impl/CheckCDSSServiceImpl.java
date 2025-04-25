package com.emr.project.docOrder.service.impl;

import com.emr.common.utils.StringUtils;
import com.emr.project.docOrder.domain.vo.OrderSaveVo;
import com.emr.project.docOrder.service.ICheckCDSSService;
import com.emr.project.esSearch.domain.DrugAndClin;
import com.emr.project.operation.domain.Baseinfomation;
import com.emr.project.operation.mapper.BaseinfomationMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckCDSSServiceImpl implements ICheckCDSSService {
   @Autowired
   private BaseinfomationMapper baseinfomationMapper;

   public Boolean checkCDSSBySex(String patientId, List orderSaveVoList) {
      Boolean flag = Boolean.TRUE;
      Baseinfomation medicalinfo = this.baseinfomationMapper.findBaseInfo(patientId);
      String sex = medicalinfo.getSex();

      for(OrderSaveVo orderSaveVo : orderSaveVoList) {
         String examSex = orderSaveVo.getExamSex();
         if (StringUtils.isNotBlank(examSex) && !"3".equals(examSex) && !sex.equals(examSex)) {
            return Boolean.FALSE;
         }
      }

      return flag;
   }

   public Boolean checkCDSSBySexApply(String patientId, List itemList) {
      Boolean flag = Boolean.TRUE;
      Baseinfomation medicalinfo = this.baseinfomationMapper.findBaseInfo(patientId);
      String sex = medicalinfo.getSex();

      for(DrugAndClin drugAndClin : itemList) {
         String examSex = drugAndClin.getExamSex();
         if (StringUtils.isNotBlank(examSex) && !"3".equals(examSex) && !sex.equals(examSex)) {
            flag = Boolean.FALSE;
         }
      }

      return flag;
   }
}
