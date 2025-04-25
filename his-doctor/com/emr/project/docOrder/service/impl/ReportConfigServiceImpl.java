package com.emr.project.docOrder.service.impl;

import com.emr.project.docOrder.domain.vo.OrderDoHandlePrintVo;
import com.emr.project.docOrder.service.ReportConfigService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class ReportConfigServiceImpl implements ReportConfigService {
   public void groupByExecutorDpt(List mapList1, List mapList) {
      Map<String, List<Map<String, Object>>> hashMap = new HashMap();
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

      for(Map objectMap : mapList) {
         String admission_no = (String)objectMap.get("admission_no");
         String executor_dpt_no = (String)objectMap.get("executor_dpt_no");
         Date filing_date = objectMap.get("filing_date") == null ? null : (Date)objectMap.get("filing_date");
         Date printDate = objectMap.get("printDate") == null ? null : (Date)objectMap.get("printDate");
         if (filing_date != null) {
            objectMap.put("filing_date", format.format(filing_date));
         }

         if (printDate != null) {
            objectMap.put("printDate", format.format(printDate));
         }

         if (hashMap.containsKey(admission_no + executor_dpt_no)) {
            ((List)hashMap.get(admission_no + executor_dpt_no)).add(objectMap);
         } else {
            List<Map<String, Object>> list = new ArrayList();
            list.add(objectMap);
            hashMap.put(admission_no + executor_dpt_no, list);
         }
      }

      for(String key : hashMap.keySet()) {
         List<Map<String, Object>> list = (List)hashMap.get(key);
         Map<String, List<Map<String, Object>>> map = new HashMap();
         map.put("Table", list);
         mapList1.add(map);
      }

   }

   public void groupByExecutorDpt2(List mapList1, List printVoList) {
   }
}
