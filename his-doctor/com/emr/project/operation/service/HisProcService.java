package com.emr.project.operation.service;

import java.util.Map;

public interface HisProcService {
   String getDocumentOrBillNo(String staffCode, int type) throws Exception;

   Map savePatFeeAndDetail(String prescription, String admissionNo, int hospitalizedCount, String flag, String operator) throws Exception;
}
