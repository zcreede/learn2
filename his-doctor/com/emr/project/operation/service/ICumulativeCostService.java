package com.emr.project.operation.service;

import com.emr.project.operation.domain.CumulativeCost;

public interface ICumulativeCostService {
   CumulativeCost queryByAdmissionNo(String admissionNo) throws Exception;

   void updateCumulativeCost(CumulativeCost cumulativeCost) throws Exception;
}
