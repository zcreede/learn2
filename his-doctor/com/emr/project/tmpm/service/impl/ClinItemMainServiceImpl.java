package com.emr.project.tmpm.service.impl;

import com.emr.common.utils.StringUtils;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.docOrder.domain.vo.OrderSaveVo;
import com.emr.project.esSearch.domain.DrugAndClin;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.tmpm.domain.ClinItemMain;
import com.emr.project.tmpm.mapper.ClinItemMainMapper;
import com.emr.project.tmpm.service.IClinItemMainService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClinItemMainServiceImpl implements IClinItemMainService {
   @Autowired
   private ClinItemMainMapper clinItemMainMapper;
   @Autowired
   private IVisitinfoService visitinfoService;

   public ClinItemMain selectClinItemMainById(String itemCd) {
      return this.clinItemMainMapper.selectClinItemMainById(itemCd);
   }

   public List selectClinItemMainByItemCds(List itemCdList) throws Exception {
      List<ClinItemMain> list = null;
      if (itemCdList != null && !itemCdList.isEmpty()) {
         list = this.clinItemMainMapper.selectClinItemMainByItemCds(itemCdList);
      }

      return list;
   }

   public List selectClinItemMainList(ClinItemMain clinItemMain) {
      return this.clinItemMainMapper.selectClinItemMainList(clinItemMain);
   }

   public int insertClinItemMain(ClinItemMain clinItemMain) {
      return this.clinItemMainMapper.insertClinItemMain(clinItemMain);
   }

   public int updateClinItemMain(ClinItemMain clinItemMain) {
      return this.clinItemMainMapper.updateClinItemMain(clinItemMain);
   }

   public int deleteClinItemMainByIds(String[] itemCds) {
      return this.clinItemMainMapper.deleteClinItemMainByIds(itemCds);
   }

   public int deleteClinItemMainById(String itemCd) {
      return this.clinItemMainMapper.deleteClinItemMainById(itemCd);
   }

   public List selectClinList(String hospitalCode, String deptCode, String docCd, String itemFlagCd) throws Exception {
      return this.clinItemMainMapper.selectClinList(hospitalCode, deptCode, docCd, itemFlagCd);
   }

   public AjaxResult verifyExtendedAttri(String patientId, List orderSaveVoList, List drugAndClinList, AjaxResult ajaxResult) throws Exception {
      VisitinfoVo param = new VisitinfoVo();
      param.setPatientId(patientId);
      VisitinfoVo visitinfoVo = this.visitinfoService.selectPatientById(param);
      int ageY = visitinfoVo.getAgeY() != null ? visitinfoVo.getAgeY().intValue() : 0;
      List<String> itemCdList = CollectionUtils.isNotEmpty(orderSaveVoList) ? (List)orderSaveVoList.stream().map((t) -> t.getCpNo()).collect(Collectors.toList()) : (List)drugAndClinList.stream().map((t) -> t.getCpNo()).collect(Collectors.toList());
      List<ClinItemMain> clinItemMainList = this.clinItemMainMapper.selectClinItemMainByItemCds(itemCdList);
      Map<String, List<OrderSaveVo>> OrderSaveMap = (Map<String, List<OrderSaveVo>>)(CollectionUtils.isNotEmpty(orderSaveVoList) ? (Map)orderSaveVoList.stream().collect(Collectors.groupingBy((t) -> t.getCpNo())) : new HashMap(1));
      Map<String, List<DrugAndClin>> drugAndClinMap = (Map<String, List<DrugAndClin>>)(CollectionUtils.isNotEmpty(drugAndClinList) ? (Map)drugAndClinList.stream().collect(Collectors.groupingBy((t) -> t.getCpNo())) : new HashMap(1));
      String ageMsg = "";
      String specChangeMsg = "";

      for(ClinItemMain clinItemMain : clinItemMainList) {
         boolean ageFlag = true;
         String ageStr = null;
         if (clinItemMain.getAgeStart() != null && clinItemMain.getAgeEnd() != null) {
            ageFlag = ageY >= clinItemMain.getAgeStart() && ageY <= clinItemMain.getAgeEnd();
            ageStr = "[" + clinItemMain.getAgeStart() + "-" + clinItemMain.getAgeEnd() + "]";
         } else if (clinItemMain.getAgeStart() != null && clinItemMain.getAgeEnd() == null) {
            ageFlag = ageY >= clinItemMain.getAgeStart();
            ageStr = "[" + clinItemMain.getAgeStart() + "-]";
         } else if (clinItemMain.getAgeStart() == null && clinItemMain.getAgeEnd() != null) {
            ageFlag = ageY <= clinItemMain.getAgeEnd();
            ageStr = "[-" + clinItemMain.getAgeEnd() + "]";
         }

         if (!ageFlag) {
            ageMsg = (StringUtils.isBlank(ageMsg) ? "" : ageMsg) + "【" + clinItemMain.getItemName() + "】的适用年龄是" + ageStr + "；";
         }

         boolean specChangeFlag = true;
         if (StringUtils.isNotBlank(clinItemMain.getChangeFlag()) && clinItemMain.getChangeFlag().equals("0")) {
            List<OrderSaveVo> orderSaveVos = (List)OrderSaveMap.get(clinItemMain.getItemCd());
            if (CollectionUtils.isNotEmpty(orderSaveVos)) {
               for(OrderSaveVo orderSaveVo : orderSaveVos) {
                  specChangeFlag = orderSaveVo.getSpecCd().equals(clinItemMain.getSpecCd());
                  if (!specChangeFlag) {
                     break;
                  }
               }
            }

            List<DrugAndClin> drugAndClins = (List)drugAndClinMap.get(clinItemMain.getItemCd());
            if (CollectionUtils.isNotEmpty(drugAndClins)) {
               for(DrugAndClin drugAndClin : drugAndClins) {
                  specChangeFlag = drugAndClin.getSpecCd().equals(clinItemMain.getSpecCd());
                  if (!specChangeFlag) {
                     break;
                  }
               }
            }
         }

         if (!specChangeFlag) {
            specChangeMsg = specChangeMsg + "【" + clinItemMain.getItemName() + "】的标本应该是[" + clinItemMain.getSpecName() + "]；";
         }
      }

      if (StringUtils.isNotBlank(ageMsg)) {
         ageMsg = "不能开立此临床项目，当前患者年龄是 " + ageY + "岁，临床项目：" + ageMsg;
         ajaxResult.put("clinAgeFlag", false);
         ajaxResult.put("ageMsg", ageMsg);
      }

      if (StringUtils.isNotBlank(specChangeMsg)) {
         specChangeMsg = "不能修改此临床项目的标本信息：" + specChangeMsg;
         ajaxResult.put("specChangeFlag", false);
         ajaxResult.put("specChangeMsg", specChangeMsg);
      }

      return ajaxResult;
   }

   public Integer selectCountByItemFlagCd(String itemFlagCd) {
      return this.clinItemMainMapper.selectCountByItemFlagCd(itemFlagCd);
   }
}
