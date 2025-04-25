package com.emr.project.system.service.impl;

import com.emr.project.system.domain.BsStaff;
import com.emr.project.system.domain.vo.BsStaffVo;
import com.emr.project.system.mapper.BsStaffMapper;
import com.emr.project.system.service.IBsStaffService;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BsStaffServiceImpl implements IBsStaffService {
   @Autowired
   private BsStaffMapper bsStaffMapper;

   public List selectStaffList(List staffCodeList) throws Exception {
      List<BsStaffVo> list = null;
      if (CollectionUtils.isNotEmpty(staffCodeList)) {
         list = this.bsStaffMapper.selectStaffList(staffCodeList);
      }

      return list;
   }

   public List selectBsStaffByStaffType(BsStaff bsStaff) throws Exception {
      return this.bsStaffMapper.selectBsStaffByStaffType(bsStaff);
   }
}
