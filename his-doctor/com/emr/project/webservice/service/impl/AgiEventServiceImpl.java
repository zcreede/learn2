package com.emr.project.webservice.service.impl;

import com.emr.common.utils.StringUtils;
import com.emr.project.docOrder.domain.TdCaOperationApply;
import com.emr.project.docOrder.mapper.TdCaOperationApplyMapper;
import com.emr.project.webservice.domain.vo.OperationInfoVo;
import com.emr.project.webservice.service.AgiEventService;
import javax.jws.WebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@WebService(
   serviceName = "AgiEventService",
   targetNamespace = "http://webservice.project.emr.com",
   name = "AgiEventService",
   endpointInterface = "com.emr.project.webservice.service.AgiEventService"
)
@Service
public class AgiEventServiceImpl implements AgiEventService {
   protected final Logger log = LoggerFactory.getLogger(AgiEventServiceImpl.class);
   @Autowired
   private TdCaOperationApplyMapper operationApplyMapper;

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public int updateOperationInfo(OperationInfoVo operationInfoVo) {
      String applyFormNo = operationInfoVo.getApplyFormNo();
      if (StringUtils.isEmpty(applyFormNo)) {
         return -1;
      } else {
         TdCaOperationApply operationApply = this.operationApplyMapper.selectTdCaOperationApplyById(applyFormNo);
         return operationApply == null ? -1 : this.operationApplyMapper.updateTdCaOperationApplyByVo(operationInfoVo);
      }
   }
}
