package com.emr.project.operation.service;

import com.emr.project.operation.domain.req.ReturnApplyPageReq;
import com.emr.project.operation.domain.req.UnTakeDrugReq;
import java.util.List;

public interface IPatientsReturnDrugService {
   List unTakeDrugList(UnTakeDrugReq req);

   List queryPageList(ReturnApplyPageReq req);
}
