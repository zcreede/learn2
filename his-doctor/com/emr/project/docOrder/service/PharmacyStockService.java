package com.emr.project.docOrder.service;

import com.emr.project.docOrder.domain.vo.InpatientOrderPerformDTO;
import com.emr.project.docOrder.domain.vo.OrderDoHandleDrugDoseVo;
import java.util.List;

public interface PharmacyStockService {
   OrderDoHandleDrugDoseVo isHasStock(List takeDrugList, List drugAndClinList, InpatientOrderPerformDTO dto) throws Exception;
}
