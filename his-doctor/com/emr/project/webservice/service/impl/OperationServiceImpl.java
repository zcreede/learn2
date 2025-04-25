package com.emr.project.webservice.service.impl;

import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.domain.TdCaOperationApply;
import com.emr.project.docOrder.mapper.TdCaOperationApplyMapper;
import com.emr.project.webservice.domain.vo.OperationInfoVo;
import com.emr.project.webservice.service.OperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OperationServiceImpl implements OperationService {
   protected final Logger log = LoggerFactory.getLogger(OperationServiceImpl.class);
   @Autowired
   private TdCaOperationApplyMapper operationApplyMapper;
   @Autowired
   private ICommonService commonService;

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public int updateOperationInfo(OperationInfoVo operationInfoVo) throws Exception {
      operationInfoVo.setAltDate(this.commonService.getDbSysdate());
      return this.operationApplyMapper.updateTdCaOperationApplyByVo(operationInfoVo);
   }

   public TdCaOperationApply selectTdCaOperationApplyById(String applyFormNo) {
      return this.operationApplyMapper.selectTdCaOperationApplyById(applyFormNo);
   }
}
