package com.emr.project.common.service.impl;

import com.emr.common.exception.CustomException;
import com.emr.project.common.mapper.ProcMapper;
import com.emr.project.common.service.IProcService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IProcServiceImpl implements IProcService {
   @Autowired
   private ProcMapper procMapper;

   public void orderAutoFee(Map param) throws Exception {
      this.procMapper.orderAutoFee(param);
      Integer vi_ret = (Integer)param.get("vi_ret");
      String vs_errtext = (String)param.get("vs_errtext");
      if (vi_ret == -1) {
         throw new CustomException(vs_errtext);
      }
   }
}
