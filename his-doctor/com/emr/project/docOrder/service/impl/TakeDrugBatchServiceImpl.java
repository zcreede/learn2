package com.emr.project.docOrder.service.impl;

import com.emr.common.utils.SnowIdUtils;
import com.emr.project.docOrder.domain.TakeDrugBatch;
import com.emr.project.docOrder.mapper.TakeDrugBatchMapper;
import com.emr.project.docOrder.service.TakeDrugBatchService;
import java.util.Date;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TakeDrugBatchServiceImpl implements TakeDrugBatchService {
   @Autowired
   private TakeDrugBatchMapper takeDrugBatchMapper;

   public TakeDrugBatch getDeptLastTakeDrugBatch(String depCode, Date takeDrugDate) {
      TakeDrugBatch param = new TakeDrugBatch();
      param.setDepCode(depCode);
      param.setTakeDrugDateStr(DateUtils.formatDate(takeDrugDate));
      TakeDrugBatch takeDrugBatch = this.takeDrugBatchMapper.selectLastBatch(param);
      return takeDrugBatch;
   }

   public void addTakeDrugBatch(String depCode, Date takeDrugDate, int lastTakeDrugBatchNum) {
      TakeDrugBatch param = new TakeDrugBatch();
      param.setId(SnowIdUtils.uniqueLong());
      param.setDepCode(depCode);
      param.setTakeDrugDateStr(DateUtils.formatDate(takeDrugDate));
      param.setTakeDrugBatchNum(lastTakeDrugBatchNum);
      this.takeDrugBatchMapper.insert(param);
   }
}
