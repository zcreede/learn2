package com.emr.project.tmpm.service.impl;

import com.emr.common.utils.SnowIdUtils;
import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.domain.vo.TdPaOrderDetailVo;
import com.emr.project.tmpm.domain.ClinitemUseLog;
import com.emr.project.tmpm.domain.vo.ClinitemUseLogVo;
import com.emr.project.tmpm.mapper.ClinitemUseLogMapper;
import com.emr.project.tmpm.service.IClinitemUseLogService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClinitemUseLogServiceImpl implements IClinitemUseLogService {
   @Autowired
   private ClinitemUseLogMapper clinitemUseLogMapper;
   @Autowired
   private ICommonService commonService;

   public ClinitemUseLog selectClinitemUseLogById(Long id) {
      return this.clinitemUseLogMapper.selectClinitemUseLogById(id);
   }

   public List selectClinitemUseLogList(ClinitemUseLog clinitemUseLog) {
      return this.clinitemUseLogMapper.selectClinitemUseLogList(clinitemUseLog);
   }

   public List selectClinitemUseLogListVo(ClinitemUseLogVo clinitemUseLog) throws Exception {
      return this.clinitemUseLogMapper.selectClinitemUseLogListVo(clinitemUseLog);
   }

   public int insertClinitemUseLog(ClinitemUseLog clinitemUseLog) {
      return this.clinitemUseLogMapper.insertClinitemUseLog(clinitemUseLog);
   }

   public int updateClinitemUseLog(ClinitemUseLog clinitemUseLog) {
      return this.clinitemUseLogMapper.updateClinitemUseLog(clinitemUseLog);
   }

   public int deleteClinitemUseLogByIds(Long[] ids) {
      return this.clinitemUseLogMapper.deleteClinitemUseLogByIds(ids);
   }

   public int deleteClinitemUseLogById(Long id) {
      return this.clinitemUseLogMapper.deleteClinitemUseLogById(id);
   }

   public Date selectMaxAltDate() throws Exception {
      ClinitemUseLog clinitemUseLog = this.clinitemUseLogMapper.selectMaxAltDate();
      return clinitemUseLog != null ? clinitemUseLog.getAltDate() : null;
   }

   public void updateClinitemUseLogTask(List orderDetailVoList) throws Exception {
      Date currDate = this.commonService.getDbSysdate();
      List<ClinitemUseLog> addList = new ArrayList(1);
      List<ClinitemUseLog> updateList = new ArrayList(1);
      List<String> docCdList = (List)orderDetailVoList.stream().map((t) -> t.getOrderStartDoc()).distinct().collect(Collectors.toList());
      List<ClinitemUseLog> clinitemUseLogList = this.clinitemUseLogMapper.selectListByDocList(docCdList);
      Map<String, List<TdPaOrderDetailVo>> docDrugMap = (Map)orderDetailVoList.stream().filter((t) -> StringUtils.isNotBlank(t.getHospitalCode()) && StringUtils.isNotBlank(t.getOrderStartDoc()) && StringUtils.isNotBlank(t.getChargeNo()) && StringUtils.isNotBlank(t.getOrderClassCode()) && !t.getChargeNo().equals("NNNNNN")).filter((t) -> t.getOrderClassCode().equals("01")).collect(Collectors.groupingBy((t) -> t.getHospitalCode() + "_" + t.getOrderStartDoc() + "_" + t.getChargeNo()));
      Map<String, List<TdPaOrderDetailVo>> docDrugMap2 = (Map)orderDetailVoList.stream().filter((t) -> StringUtils.isNotBlank(t.getHospitalCode()) && StringUtils.isNotBlank(t.getOrderStartDoc()) && StringUtils.isNotBlank(t.getCpNo()) && StringUtils.isNotBlank(t.getOrderClassCode())).filter((t) -> !t.getOrderClassCode().equals("01")).collect(Collectors.groupingBy((t) -> t.getHospitalCode() + "_" + t.getOrderStartDoc() + "_" + t.getCpNo()));
      docDrugMap.putAll(docDrugMap2);
      List<String> itemClassCdList = Arrays.asList("30", "40", "00", "20");
      List<ClinitemUseLog> orderUseLogList = (List)clinitemUseLogList.stream().filter((t) -> !itemClassCdList.contains(t.getItemClassCd())).collect(Collectors.toList());
      this.handleClinitemUseLog(orderUseLogList, docDrugMap, currDate, (String)null, addList, updateList);
      Map<String, List<TdPaOrderDetailVo>> setMap = (Map)orderDetailVoList.stream().filter((t) -> StringUtils.isNotBlank(t.getPrescribeNo()) && !t.getHerbalFlag().equals("1")).collect(Collectors.groupingBy((t) -> t.getHospitalCode() + "_" + t.getOrderStartDoc() + "_" + t.getPrescribeNo()));
      List<ClinitemUseLog> setUseLogList = (List)clinitemUseLogList.stream().filter((t) -> t.getItemClassCd().equals("00")).collect(Collectors.toList());
      this.handleClinitemUseLog(setUseLogList, setMap, currDate, "00", addList, updateList);
      Map<String, List<TdPaOrderDetailVo>> herbalSetMap = (Map)orderDetailVoList.stream().filter((t) -> StringUtils.isNotBlank(t.getPrescribeNo()) && t.getHerbalFlag().equals("1")).collect(Collectors.groupingBy((t) -> t.getHospitalCode() + "_" + t.getOrderStartDoc() + "_" + t.getPrescribeNo()));
      List<ClinitemUseLog> herbalSetUseLogList = (List)clinitemUseLogList.stream().filter((t) -> t.getItemClassCd().equals("20")).collect(Collectors.toList());
      this.handleClinitemUseLog(herbalSetUseLogList, herbalSetMap, currDate, "20", addList, updateList);
      Map<String, List<TdPaOrderDetailVo>> freqMap = (Map)orderDetailVoList.stream().filter((t) -> StringUtils.isNotBlank(t.getOrderFreq())).collect(Collectors.groupingBy((t) -> t.getHospitalCode() + "_" + t.getOrderStartDoc() + "_" + t.getOrderFreq()));
      List<ClinitemUseLog> freqUseLogList = (List)clinitemUseLogList.stream().filter((t) -> t.getItemClassCd().equals("30")).collect(Collectors.toList());
      this.handleClinitemUseLog(freqUseLogList, freqMap, currDate, "30", addList, updateList);
      Map<String, List<TdPaOrderDetailVo>> sigMap = (Map)orderDetailVoList.stream().filter((t) -> StringUtils.isNotBlank(t.getOrderUsageWay())).collect(Collectors.groupingBy((t) -> t.getHospitalCode() + "_" + t.getOrderStartDoc() + "_" + t.getOrderUsageWay()));
      List<ClinitemUseLog> sigUseLogList = (List)clinitemUseLogList.stream().filter((t) -> t.getItemClassCd().equals("40")).collect(Collectors.toList());
      this.handleClinitemUseLog(sigUseLogList, sigMap, currDate, "40", addList, updateList);
      int operNum = 500;
      if (CollectionUtils.isNotEmpty(addList)) {
         Integer subSize = addList.size();
         int a = subSize / operNum;
         int b = subSize % operNum;
         if (b > 0) {
            ++a;
         }

         for(int i = 0; i < a; ++i) {
            int beginIndex = i * operNum;
            int endIndex = (i + 1) * operNum;
            if (i + 1 == a) {
               endIndex = subSize;
            }

            List<ClinitemUseLog> addSubList = addList.subList(beginIndex, endIndex);
            this.clinitemUseLogMapper.insertList(addSubList);
         }
      }

      if (CollectionUtils.isNotEmpty(updateList)) {
         Integer subSize = updateList.size();
         int a = subSize / operNum;
         int b = subSize % operNum;
         if (b > 0) {
            ++a;
         }

         for(int i = 0; i < a; ++i) {
            int beginIndex = i * operNum;
            int endIndex = (i + 1) * operNum;
            if (i + 1 == a) {
               endIndex = subSize;
            }

            List<ClinitemUseLog> updateSubList = updateList.subList(beginIndex, endIndex);
            this.clinitemUseLogMapper.updateusageTimes(updateSubList);
         }
      }

   }

   private void handleClinitemUseLog(List useLogList, Map orderDetailVoMap, Date currDate, String itemClassCd, List addList, List updateList) {
      Map<String, List<ClinitemUseLog>> useLogMap = (Map)useLogList.stream().collect(Collectors.groupingBy((t) -> t.getHospitalCode() + "_" + t.getDocCd() + "_" + t.getItemCd()));

      for(String docFreq : orderDetailVoMap.keySet()) {
         List<TdPaOrderDetailVo> freqListTemp = (List)orderDetailVoMap.get(docFreq);
         List<ClinitemUseLog> useLogListTemp = (List)useLogMap.get(docFreq);
         ClinitemUseLog clinitemUseLog = useLogListTemp != null && !useLogListTemp.isEmpty() ? (ClinitemUseLog)useLogListTemp.get(0) : null;
         Integer usageTimes = freqListTemp.size();
         if (clinitemUseLog == null) {
            String itemClassCdTemp = StringUtils.isNotBlank(itemClassCd) ? itemClassCd : ((TdPaOrderDetailVo)freqListTemp.get(0)).getOrderClassCode();
            ClinitemUseLog useLog = this.getAddClinitemUseLog((TdPaOrderDetailVo)freqListTemp.get(0), usageTimes, currDate, itemClassCdTemp);
            if (StringUtils.isNotBlank(useLog.getDocCd()) && StringUtils.isNotBlank(useLog.getItemClassCd()) && StringUtils.isNotBlank(useLog.getItemCd())) {
               addList.add(useLog);
            }
         } else {
            usageTimes = clinitemUseLog.getUsageTimes() + usageTimes;
            ClinitemUseLog useLog = new ClinitemUseLog();
            useLog.setId(clinitemUseLog.getId());
            useLog.setUsageTimes(usageTimes);
            useLog.setAltDate(currDate);
            updateList.add(useLog);
         }
      }

   }

   private ClinitemUseLog getAddClinitemUseLog(TdPaOrderDetailVo orderDetailVo, int usageTimes, Date currDate, String itemClassCd) {
      ClinitemUseLog clinitemUseLog = new ClinitemUseLog();
      clinitemUseLog.setId(SnowIdUtils.uniqueLong());
      clinitemUseLog.setHospitalCode(orderDetailVo.getHospitalCode());
      clinitemUseLog.setDocCd(orderDetailVo.getOrderStartDoc());
      clinitemUseLog.setDocName(orderDetailVo.getOrderStartDocName());
      clinitemUseLog.setItemClassCd(itemClassCd);
      String itemClassName = this.getItemClassName(itemClassCd);
      clinitemUseLog.setItemClassName(itemClassName);
      clinitemUseLog.setUsageTimes(usageTimes);
      clinitemUseLog.setHerbalFlag(orderDetailVo.getHerbalFlag());
      clinitemUseLog.setAltDate(currDate);
      this.setItemCdName(orderDetailVo, itemClassCd, clinitemUseLog);
      return clinitemUseLog;
   }

   private void setItemCdName(TdPaOrderDetailVo orderDetailVo, String itemClassCd, ClinitemUseLog clinitemUseLog) {
      switch (itemClassCd) {
         case "00":
            clinitemUseLog.setItemCd(orderDetailVo.getPrescribeNo());
            clinitemUseLog.setItemName(orderDetailVo.getPrescribeName());
            break;
         case "20":
            clinitemUseLog.setItemCd(orderDetailVo.getPrescribeNo());
            clinitemUseLog.setItemName(orderDetailVo.getPrescribeName());
            break;
         case "30":
            clinitemUseLog.setItemCd(orderDetailVo.getOrderFreq());
            clinitemUseLog.setItemName(orderDetailVo.getItemOrderFreqName());
            break;
         case "40":
            clinitemUseLog.setItemCd(orderDetailVo.getOrderUsageWay());
            clinitemUseLog.setItemName(orderDetailVo.getItemOrderUsageWayName());
            break;
         case "01":
            clinitemUseLog.setItemCd(orderDetailVo.getChargeNo());
            clinitemUseLog.setItemName(orderDetailVo.getChargeName());
            break;
         default:
            clinitemUseLog.setItemCd(orderDetailVo.getCpNo());
            clinitemUseLog.setItemName(orderDetailVo.getCpName());
      }

   }

   private String getItemClassName(String itemClassCd) {
      String itemClassName = null;
      switch (itemClassCd) {
         case "00":
            itemClassName = "组套";
            break;
         case "01":
            itemClassName = "药疗";
            break;
         case "02":
            itemClassName = "检查";
            break;
         case "03":
            itemClassName = "检验";
            break;
         case "04":
            itemClassName = "手术";
            break;
         case "05":
            itemClassName = "处置";
            break;
         case "06":
            itemClassName = "材料";
            break;
         case "07":
            itemClassName = "嘱托";
            break;
         case "08":
            itemClassName = "输血";
            break;
         case "09":
            itemClassName = "协方";
            break;
         case "99":
            itemClassName = "其他";
            break;
         case "20":
            itemClassName = "协定处方";
            break;
         case "30":
            itemClassName = "频次";
            break;
         case "40":
            itemClassName = "用法";
      }

      return itemClassName;
   }
}
