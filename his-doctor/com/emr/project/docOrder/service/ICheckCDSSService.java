package com.emr.project.docOrder.service;

import java.util.List;

public interface ICheckCDSSService {
   Boolean checkCDSSBySex(String patientId, List orderSaveVoList);

   Boolean checkCDSSBySexApply(String patientId, List itemList);
}
