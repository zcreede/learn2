package com.emr.project.webservice.service;

import com.emr.project.webservice.domain.vo.OperationInfoVo;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface AgiEventService {
   @WebMethod
   int updateOperationInfo(OperationInfoVo operationInfoVo);
}
