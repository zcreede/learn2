package com.emr.project.tmpa.service.impl;

import com.emr.common.utils.StringUtils;
import com.emr.project.docOrder.domain.TmPmOrderSetDetail;
import com.emr.project.docOrder.service.ITmPmOrderSetDetailService;
import com.emr.project.esSearch.domain.DrugAndClin;
import com.emr.project.esSearch.domain.vo.DrugAndClinExtendVo;
import com.emr.project.tmpa.domain.VYyBzbmDrugClin;
import com.emr.project.tmpa.mapper.VYyBzbmDrugClinMapper;
import com.emr.project.tmpa.service.IVYyBzbmDrugClinService;
import com.emr.project.tmpm.domain.ClinItemDetail;
import com.emr.project.tmpm.domain.vo.ClinItemDetailVo;
import com.emr.project.tmpm.service.IClinItemDetailService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VYyBzbmDrugClinServiceImpl implements IVYyBzbmDrugClinService {
   @Autowired
   private VYyBzbmDrugClinMapper vYyBzbmDrugClinMapper;
   @Autowired
   private IClinItemDetailService clinItemDetailService;
   @Autowired
   private ITmPmOrderSetDetailService tmPmOrderSetDetailService;

   public List queryDrugAndClinExtendInfo(List list) throws Exception {
      List<DrugAndClinExtendVo> resList = null;
      if (CollectionUtils.isNotEmpty(list)) {
         resList = new ArrayList(list.size());
         List<String> clinItemCdList = (List)list.stream().filter((tx) -> tx.getOrderClassCode().equals("02") || tx.getOrderClassCode().equals("03") || tx.getOrderClassCode().equals("05")).map((tx) -> tx.getCpNo()).collect(Collectors.toList());
         List<ClinItemDetail> itemDetailList = this.clinItemDetailService.selectClinItemDetailByItemCds(clinItemCdList);
         List<String> clinDetailCdList = CollectionUtils.isNotEmpty(itemDetailList) ? (List)itemDetailList.stream().map((tx) -> tx.getStandardCode()).collect(Collectors.toList()) : null;
         List<String> drugCodeList = (List)list.stream().filter((tx) -> tx.getOrderClassCode().equals("01")).map((tx) -> tx.getCpNo()).collect(Collectors.toList());
         List<String> xmbmList = new ArrayList(1);
         if (CollectionUtils.isNotEmpty(clinDetailCdList)) {
            xmbmList.addAll(clinDetailCdList);
         }

         if (CollectionUtils.isNotEmpty(drugCodeList)) {
            xmbmList.addAll(drugCodeList);
         }

         List<VYyBzbmDrugClin> bzbmDrugClinList = new ArrayList(1);
         if (CollectionUtils.isNotEmpty(xmbmList)) {
            xmbmList = (List)xmbmList.stream().filter((tx) -> StringUtils.isNotBlank(tx)).collect(Collectors.toList());
            bzbmDrugClinList = this.vYyBzbmDrugClinMapper.selectBzbmDrugClinListByCpNoList(xmbmList);
         }

         List<String> setCdList = (List)list.stream().filter((tx) -> tx.getOrderClassCode().equals("00")).map((tx) -> tx.getCpNo()).collect(Collectors.toList());
         List<TmPmOrderSetDetail> setDetilList = this.tmPmOrderSetDetailService.selectSetAsOrderListBySets(setCdList);
         Map<String, List<VYyBzbmDrugClin>> bzbmDrugClinMap = (Map<String, List<VYyBzbmDrugClin>>)(CollectionUtils.isNotEmpty(bzbmDrugClinList) ? (Map)bzbmDrugClinList.stream().collect(Collectors.groupingBy((tx) -> tx.getXmbh())) : new HashMap(1));
         Map<String, List<TmPmOrderSetDetail>> setDetailMap = (Map<String, List<TmPmOrderSetDetail>>)(CollectionUtils.isNotEmpty(setDetilList) ? (Map)setDetilList.stream().collect(Collectors.groupingBy((tx) -> tx.getSetCd())) : new HashMap(1));
         Map<String, List<ClinItemDetail>> clinItemDetailMap = (Map<String, List<ClinItemDetail>>)(CollectionUtils.isNotEmpty(itemDetailList) ? (Map)itemDetailList.stream().collect(Collectors.groupingBy((tx) -> tx.getItemCd())) : new HashMap(1));

         for(DrugAndClin drugAndClin : list) {
            DrugAndClinExtendVo t = new DrugAndClinExtendVo();
            BeanUtils.copyProperties(drugAndClin, t);
            if (drugAndClin.getOrderClassCode().equals("01")) {
               List<VYyBzbmDrugClin> tempList = (List)bzbmDrugClinMap.get(t.getCpNo());
               VYyBzbmDrugClin bzbmDrug = CollectionUtils.isNotEmpty(tempList) ? (VYyBzbmDrugClin)tempList.get(0) : new VYyBzbmDrugClin();
               t.setChsCode(bzbmDrug.getBzbm());
               t.setChsName(bzbmDrug.getBzmc());
               t.setChsType(bzbmDrug.getXmdj());
               t.setSelfRatio(bzbmDrug.getZfbl());
               t.setChsDesc(bzbmDrug.getSm());
            } else if (!drugAndClin.getOrderClassCode().equals("02") && !drugAndClin.getOrderClassCode().equals("03") && !drugAndClin.getOrderClassCode().equals("05")) {
               if (drugAndClin.getOrderClassCode().equals("00")) {
                  List<TmPmOrderSetDetail> setDetailList = (List)setDetailMap.get(t.getCpNo());
                  t.setSetDetailList(setDetailList);
               }
            } else {
               List<ClinItemDetail> tempList = (List)clinItemDetailMap.get(t.getCpNo());
               if (CollectionUtils.isNotEmpty(tempList)) {
                  List<ClinItemDetailVo> tempVoList = new ArrayList(tempList.size());

                  for(ClinItemDetail detail : tempList) {
                     ClinItemDetailVo detailVo = new ClinItemDetailVo();
                     BeanUtils.copyProperties(detail, detailVo);
                     List<VYyBzbmDrugClin> clinList = (List)bzbmDrugClinMap.get(detail.getStandardCode());
                     VYyBzbmDrugClin bzbmClin = CollectionUtils.isNotEmpty(clinList) ? (VYyBzbmDrugClin)clinList.get(0) : new VYyBzbmDrugClin();
                     detailVo.setChsCode(bzbmClin.getBzbm());
                     detailVo.setChsName(bzbmClin.getBzmc());
                     detailVo.setChsType(bzbmClin.getXmdj());
                     detailVo.setSelfRatio(bzbmClin.getZfbl());
                     detailVo.setChsDesc(bzbmClin.getSm());
                     tempVoList.add(detailVo);
                  }

                  t.setClinItemDetailVoList(tempVoList);
               }
            }

            resList.add(t);
         }
      }

      return resList;
   }
}
