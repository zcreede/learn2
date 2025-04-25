package com.emr.project.operation.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.docOrder.domain.vo.DrugListAndPerformReturnVo;
import com.emr.project.esSearch.service.IDrugStockService;
import com.emr.project.his.domain.vo.DrugDoseVo;
import com.emr.project.operation.domain.Medicalinformation;
import com.emr.project.operation.domain.TakeDrugList;
import com.emr.project.operation.domain.TakeDrugListKey;
import com.emr.project.operation.domain.TakeDrugReturn;
import com.emr.project.operation.domain.req.SaveUnTakeDrugReq;
import com.emr.project.operation.domain.req.UnTakeDrugDetailReq;
import com.emr.project.operation.mapper.TakeDrugListMapper;
import com.emr.project.operation.mapper.TakeDrugReturnMapper;
import com.emr.project.operation.service.HisProcService;
import com.emr.project.operation.service.ICommonOperationService;
import com.emr.project.operation.service.IMedicalinfoService;
import com.emr.project.operation.service.TakeDrugListLogService;
import com.emr.project.operation.service.TakeDrugListService;
import com.emr.project.system.domain.SysUser;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TakeDrugListServiceImpl implements TakeDrugListService {
   @Autowired
   private TakeDrugListMapper takeDrugListMapper;
   @Autowired
   private ICommonOperationService commonOperationService;
   @Autowired
   private TakeDrugReturnMapper takeDrugReturnMapper;
   @Autowired
   private TakeDrugListLogService takeDrugListLogService;
   @Autowired
   private HisProcService hisProcService;
   @Autowired
   private IMedicalinfoService medicalinfoService;
   @Autowired
   private IDrugStockService drugStockService;

   public TakeDrugList queryByPrimaryKey(TakeDrugListKey takeDrugListKey) {
      return this.takeDrugListMapper.selectByPrimaryKey(takeDrugListKey);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void unTakeDrugReturn(SaveUnTakeDrugReq req, SysUser user, String ip) throws Exception {
      List<UnTakeDrugDetailReq> list = req.getDetailList();
      List<TakeDrugReturn> takeDrugReturnList = new ArrayList();

      try {
         this.genTakeDrugReturn(takeDrugReturnList, list, req);
      } catch (Exception e) {
         throw e;
      }

      this.takeDrugReturnMapper.insertList(takeDrugReturnList);
      List<DrugDoseVo> drugDoseVoList = new ArrayList();
      List<TakeDrugList> updateList = new ArrayList();
      List<TakeDrugList> delList = new ArrayList();
      list.forEach((t) -> {
         if (StringUtils.isNotEmpty(t.getApplyDose()) && StringUtils.isNotEmpty(t.getOrderDose())) {
            if ((new BigDecimal(t.getApplyDose())).compareTo(new BigDecimal(t.getOrderDose())) == 0) {
               TakeDrugListKey takeDrugListKey = new TakeDrugListKey(t.getPerformListNo(), Integer.parseInt(t.getPerformListSortNumber()), t.getOrderSortNumber(), t.getOrderGroupSortNumber(), t.getOrderGroupNo());
               TakeDrugList takeDrugList = this.takeDrugListMapper.selectByPrimaryKey(takeDrugListKey);
               delList.add(takeDrugList);
            } else if ((new BigDecimal(t.getApplyDose())).compareTo(new BigDecimal(t.getOrderDose())) < 0) {
               BigDecimal orderDoseNew = (new BigDecimal(t.getOrderDose())).subtract(new BigDecimal(t.getApplyDose()));
               TakeDrugListKey takeDrugListKey = new TakeDrugListKey(t.getPerformListNo(), Integer.parseInt(t.getPerformListSortNumber()), t.getOrderSortNumber(), t.getOrderGroupSortNumber(), t.getOrderGroupNo());
               TakeDrugList takeDrugList = this.takeDrugListMapper.selectByPrimaryKey(takeDrugListKey);
               takeDrugList.setOrderDose(orderDoseNew);
               updateList.add(takeDrugList);
            }

            DrugDoseVo drugDoseVo = new DrugDoseVo();
            drugDoseVo.setYfbm(t.getPharmacyNo());
            drugDoseVo.setYpbm(t.getPharmacopoeiaNo());
            drugDoseVo.setKcbh(t.getDrugStockNo());
            drugDoseVo.setOrderDose((new BigDecimal(t.getApplyDose())).abs());
            drugDoseVo.setOrderNo(t.getOrderNo());
            drugDoseVo.setTakeDrugListId(Long.valueOf(t.getId()));
            drugDoseVoList.add(drugDoseVo);
         }

      });
      if (!delList.isEmpty()) {
         this.takeDrugListMapper.moveToDeleteTable(delList);
         this.takeDrugListMapper.deleteList(delList);
         this.takeDrugListLogService.addTakeDrugListLogList(5, delList);
         this.takeDrugListLogService.addTakeDrugListLogList(2, delList);
      }

      if (!updateList.isEmpty()) {
         this.takeDrugListMapper.updateOrderDose(updateList);
         this.takeDrugListLogService.addTakeDrugListLogList(5, updateList);
      }

      this.drugStockService.updateDrugDoseByOrderDose(user, drugDoseVoList, "0", ip);
   }

   private void genTakeDrugReturn(List takeDrugReturnList, List takeDrugFeeDTOList, SaveUnTakeDrugReq req) throws Exception {
      Map<String, List<UnTakeDrugDetailReq>> takeDrugFeeDTOMap = (Map)takeDrugFeeDTOList.stream().collect(Collectors.groupingBy(UnTakeDrugDetailReq::getPharmacyNo));
      Medicalinformation m = this.medicalinfoService.getMedicalinfo(req.getAdmissionNo());
      SysUser user = SecurityUtils.getLoginUser().getUser();

      for(String key : takeDrugFeeDTOMap.keySet()) {
         List<UnTakeDrugDetailReq> list = (List)takeDrugFeeDTOMap.get(key);
         int i = 0;

         for(UnTakeDrugDetailReq unTakeDrugDTO : list) {
            String documentOrBillNo = this.hisProcService.getDocumentOrBillNo(user.getUserName(), 2);
            TakeDrugReturn takeDrugReturn = new TakeDrugReturn();
            takeDrugReturn.setSerialNumber(documentOrBillNo);
            StringBuilder var10001 = new StringBuilder();
            ++i;
            takeDrugReturn.setSerialNumberXh(var10001.append(i).append("").toString());
            takeDrugReturn.setHospitalCode(m.getHospitalCode());
            takeDrugReturn.setCaseNo(m.getCaseNo());
            takeDrugReturn.setAdmissionNo(m.getAdmissionNo());
            takeDrugReturn.setHospitalizedCount(m.getHospitalizedCount());
            takeDrugReturn.setPatientName(m.getName());
            takeDrugReturn.setResidentCode(m.getResidentCode());
            takeDrugReturn.setResidentNo(m.getResidentNo());
            takeDrugReturn.setResidentName(m.getResidentName());
            takeDrugReturn.setWardNo(req.getPatientDepCode());
            takeDrugReturn.setWardName(req.getPatientDepName());
            takeDrugReturn.setChargeCode(unTakeDrugDTO.getPharmacopoeiaNo());
            takeDrugReturn.setDrugStockNo(unTakeDrugDTO.getDrugStockNo());
            takeDrugReturn.setChargeName(unTakeDrugDTO.getChargeName());
            takeDrugReturn.setStandard(unTakeDrugDTO.getStandard());
            takeDrugReturn.setUnit(unTakeDrugDTO.getUnit());
            takeDrugReturn.setPrice(new BigDecimal(unTakeDrugDTO.getPrice()));
            takeDrugReturn.setDose((new BigDecimal(unTakeDrugDTO.getApplyDose())).negate());
            takeDrugReturn.setTotal(takeDrugReturn.getDose().multiply(takeDrugReturn.getPrice()));
            takeDrugReturn.setOperatorCode(user.getUserName());
            takeDrugReturn.setOperatorNo(user.getUserName());
            takeDrugReturn.setOperatorDate(new Date());
            takeDrugReturn.setOperatorName(user.getNickName());
            takeDrugReturn.setBillsNoOld(unTakeDrugDTO.getOrderNo());
            takeDrugReturn.setBillsNoNew(unTakeDrugDTO.getOrderSortNumber());
            takeDrugReturn.setOperatorNewCode(user.getUserName());
            takeDrugReturn.setOperatorNewDate(new Date());
            takeDrugReturn.setOperatorNewName(user.getNickName());
            takeDrugReturn.setOperatorNewNo(user.getUserName());
            takeDrugReturn.setMark("2");
            takeDrugReturn.setApplyNo(unTakeDrugDTO.getDeptCode());
            takeDrugReturn.setApplyName(unTakeDrugDTO.getDeptName());
            takeDrugReturn.setExecutorDptNo(unTakeDrugDTO.getPharmacyNo());
            takeDrugReturn.setPhysicianName(req.getPatientDepName());
            takeDrugReturn.setTakeDrugWardNo(unTakeDrugDTO.getDeptCode());
            takeDrugReturn.setOrderNo(unTakeDrugDTO.getOrderNo());
            takeDrugReturn.setOrderSortNumber(unTakeDrugDTO.getOrderSortNumber());
            takeDrugReturn.setOrderGroupSortNumber(unTakeDrugDTO.getOrderGroupSortNumber());
            if (StringUtils.isNotBlank(unTakeDrugDTO.getOrderGroupNo())) {
               takeDrugReturn.setOrderGroupNo(Integer.valueOf(unTakeDrugDTO.getOrderGroupNo()));
            }

            takeDrugReturnList.add(takeDrugReturn);
         }
      }

   }

   public String searchTakeDrugListByStockNo(TakeDrugList t) {
      return this.takeDrugListMapper.searchTakeDrugListByStockNo(t);
   }

   public List selectByPerformListNo(String performListNo) {
      return this.takeDrugListMapper.selectByPerformListNo(performListNo);
   }

   public void changeStatusTakeDrugLists(List takeDrugLists, int takeDurgStatus) throws Exception {
      takeDrugLists.stream().forEach((takeDrugList) -> takeDrugList.setTakeDrugStatus(takeDurgStatus));
   }

   public void updateStatusTakeDrugLists(List takeDrugList) {
      if (CollectionUtils.isNotEmpty(takeDrugList)) {
         this.takeDrugListMapper.updateStatusTakeDrugLists(takeDrugList);
      }

   }

   public void insertList(List list) throws Exception {
      if (CollectionUtils.isNotEmpty(list)) {
         this.takeDrugListMapper.insertList(list);
      }

   }

   public List selectListByReturnVo(List returnVoList) throws Exception {
      List<TakeDrugList> list = null;
      if (CollectionUtils.isNotEmpty(returnVoList)) {
         list = this.takeDrugListMapper.selectListByReturnVo(returnVoList);
      }

      return list;
   }

   public void updateOrderDose(List list) throws Exception {
      if (CollectionUtils.isNotEmpty(list)) {
         this.takeDrugListMapper.updateOrderDose(list);
      }

   }

   public List selectOrderTakeDrugLists(List orderNoList) throws Exception {
      List<TakeDrugList> list = null;
      if (CollectionUtils.isNotEmpty(orderNoList)) {
         list = this.takeDrugListMapper.selectOrderTakeDrugLists(orderNoList);
      }

      return list;
   }

   public String verifyOrderCancelTakeDrugList(List orderNoList) throws Exception {
      new StringBuffer("");
      String resStr = null;
      List<String> targetOrderList = new ArrayList(1);
      List<TakeDrugList> list = this.selectOrderTakeDrugLists(orderNoList);
      Map<String, List<TakeDrugList>> listMap = (Map<String, List<TakeDrugList>>)(CollectionUtils.isNotEmpty(list) ? (Map)list.stream().collect(Collectors.groupingBy((t) -> t.getOrderNo())) : new HashMap(1));

      for(String orderNo : orderNoList) {
         List<TakeDrugList> listTemp = (List)listMap.get(orderNo);
         List<Integer> takeDrugStatusList = CollectionUtils.isNotEmpty(listTemp) ? (List)listTemp.stream().map((t) -> t.getTakeDrugStatus()).distinct().collect(Collectors.toList()) : null;
         if (CollectionUtils.isNotEmpty(takeDrugStatusList)) {
            targetOrderList.add(((TakeDrugList)listTemp.get(0)).getOrderGroupNo());
         }
      }

      if (CollectionUtils.isNotEmpty(targetOrderList)) {
         String orderNoStr = String.join(",", targetOrderList);
         resStr = orderNoStr;
      }

      return resStr;
   }
}
