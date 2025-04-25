package com.emr.project.docOrder.service.impl;

import com.emr.project.docOrder.domain.vo.InpatientOrderPerformDTO;
import com.emr.project.docOrder.domain.vo.OrderDoHandleDrugDoseVo;
import com.emr.project.docOrder.mapper.PharmacyStockMapper;
import com.emr.project.docOrder.service.PharmacyStockService;
import com.emr.project.esSearch.domain.DrugAndClin;
import com.emr.project.his.domain.vo.DrugDoseVo;
import com.emr.project.operation.domain.TakeDrugList;
import com.emr.project.operation.service.TakeDrugListService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PharmacyStockServiceImpl implements PharmacyStockService {
   @Autowired
   private PharmacyStockMapper pharmacyStockMapper;
   @Autowired
   private TakeDrugListService takeDrugListService;

   public OrderDoHandleDrugDoseVo isHasStock(List takeDrugLists, List drugAndClinList, InpatientOrderPerformDTO dto) throws Exception {
      OrderDoHandleDrugDoseVo res = new OrderDoHandleDrugDoseVo();
      Boolean hasStockFlag = true;
      if (dto.getOrderClassCode().equals("01")) {
         Map<String, BigDecimal> drugDoseMap = new HashMap(1);
         List<DrugDoseVo> drugDoseVoList = new ArrayList(1);
         List<String> drugDoseNoList = new ArrayList(1);

         for(TakeDrugList takeDrugList : takeDrugLists) {
            String drugCode = takeDrugList.getPharmacopoeiaNo();
            BigDecimal drugDose = takeDrugList.getOrderDose();
            BigDecimal drugDoseAll = (BigDecimal)drugDoseMap.get(drugCode);
            drugDoseAll = drugDoseAll == null ? drugDose : drugDoseAll.add(drugDose);
            drugDoseMap.put(drugCode, drugDoseAll);
         }

         if (drugAndClinList != null) {
            Map<String, DrugAndClin> drugAndClinMap = (Map)drugAndClinList.stream().collect(Collectors.toMap((t) -> t.getCpNo(), Function.identity()));

            for(String drugCode : drugDoseMap.keySet()) {
               BigDecimal drugDose = (BigDecimal)drugDoseMap.get(drugCode);
               DrugAndClin drugAndClin = (DrugAndClin)drugAndClinMap.get(drugCode);
               BigDecimal drugDoseTemp = drugAndClin != null ? drugAndClin.getXcsl() : new BigDecimal("0");
               if (drugDose.compareTo(drugDoseTemp) > 0) {
                  drugDoseNoList.add(drugCode);
               }

               List<TakeDrugList> takeDrugListTemp = (List)takeDrugLists.stream().filter((t) -> t.getPharmacopoeiaNo().equals(drugCode)).collect(Collectors.toList());
               TakeDrugList takeDrugList = (TakeDrugList)takeDrugListTemp.get(0);
               DrugDoseVo drugDoseVo = new DrugDoseVo((String)null, takeDrugList.getPharmacyNo(), takeDrugList.getPharmacopoeiaNo(), takeDrugList.getDrugStockNo(), drugDose.negate(), takeDrugList.getOrderNo(), takeDrugList.getId(), takeDrugList.getOrderPrice());
               drugDoseVoList.add(drugDoseVo);
            }
         }

         if (CollectionUtils.isNotEmpty(drugDoseNoList)) {
            hasStockFlag = false;
         } else {
            res.setDrugDoseVoList(drugDoseVoList);
         }
      }

      res.setNoStockFlag(hasStockFlag ? "1" : "0");
      return res;
   }
}
