package com.emr.project.emr.service.impl;

import com.emr.project.emr.domain.vo.EmrTimeoutCaseVo;
import com.emr.project.emr.mapper.EmrTimeoutCaseMapper;
import com.emr.project.emr.service.IEmrTimeoutCaseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IEmrTimeoutCaseServiceImpl implements IEmrTimeoutCaseService {
   @Autowired
   private EmrTimeoutCaseMapper emrTimeoutCaseMapper;

   public List getTimeoutCaseList(EmrTimeoutCaseVo emrTimeoutCaseVo) {
      List<EmrTimeoutCaseVo> emrTimeoutCaseVoList = this.emrTimeoutCaseMapper.getTimeoutCaseList(emrTimeoutCaseVo);
      return emrTimeoutCaseVoList;
   }
}
