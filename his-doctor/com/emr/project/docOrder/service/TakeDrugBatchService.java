package com.emr.project.docOrder.service;

import com.emr.project.docOrder.domain.TakeDrugBatch;
import java.util.Date;

public interface TakeDrugBatchService {
   TakeDrugBatch getDeptLastTakeDrugBatch(String depCode, Date takeDrugDate);

   void addTakeDrugBatch(String depCode, Date takeDrugDate, int lastTakeDrugBatchNum);
}
