package com.emr.project.operation.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.domain.TdPaOrderPerformDetail;
import com.emr.project.docOrder.domain.vo.DrugListAndPerformReturnResultVo;
import com.emr.project.docOrder.domain.vo.DrugListAndPerformReturnVo;
import com.emr.project.docOrder.domain.vo.HisYfkcHz;
import com.emr.project.docOrder.service.ITdPaOrderPerformDetailService;
import com.emr.project.docOrder.service.ITdPaTakeDrugListService;
import com.emr.project.esSearch.domain.TmPaFreeze;
import com.emr.project.esSearch.domain.req.FreezeQueryReq;
import com.emr.project.esSearch.service.IDrugAndClinService;
import com.emr.project.esSearch.service.IDrugStockService;
import com.emr.project.his.domain.vo.DrugDoseVo;
import com.emr.project.operation.domain.TakeDrugList;
import com.emr.project.operation.domain.TakeDrugListLog;
import com.emr.project.operation.domain.TakeDrugReturn;
import com.emr.project.operation.domain.dto.TakeDrugFeeDTO;
import com.emr.project.operation.mapper.TakeDrugReturnMapper;
import com.emr.project.operation.service.HisProcService;
import com.emr.project.operation.service.TakeDrugListLogService;
import com.emr.project.operation.service.TakeDrugListService;
import com.emr.project.operation.service.TakeDrugReturnLogService;
import com.emr.project.operation.service.TakeDrugReturnService;
import com.emr.project.pat.domain.Visitinfo;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.system.domain.SysDept;
import com.emr.project.system.domain.SysUser;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TakeDrugReturnServiceImpl implements TakeDrugReturnService {
   protected final Logger log = LoggerFactory.getLogger(this.getClass());
   @Autowired
   private TakeDrugReturnMapper takeDrugReturnMapper;
   @Autowired
   private HisProcService hisProcService;
   @Autowired
   private TakeDrugReturnLogService takeDrugReturnLogService;
   @Autowired
   private TakeDrugListService takeDrugListService;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private ITdPaTakeDrugListService tdPaTakeDrugListService;
   @Autowired
   private TakeDrugListLogService takeDrugListLogService;
   @Autowired
   private ITdPaOrderPerformDetailService orderPerformDetailService;
   @Autowired
   private IDrugStockService drugStockService;
   @Autowired
   private IDrugAndClinService drugAndClinService;

   public List queryBySerialNumberList(List list) {
      return this.takeDrugReturnMapper.selectBySerialNumberList(list);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void cancelTakeDrugReturnList(List takeDrugReturns) throws Exception {
      for(TakeDrugReturn returnDrug : takeDrugReturns) {
         this.takeDrugReturnMapper.cancelTakeDrugReturn("9", returnDrug);
      }

      this.takeDrugReturnLogService.addTakeDrugListLogList(9, takeDrugReturns, 2, (Map)null);
   }

   public List queryByBillsNoOldList(List list) {
      return this.takeDrugReturnMapper.selectByBillsNoOldList(list);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void saveTakeDrugReturnList(List list) throws Exception {
      List<TakeDrugReturn> takeDrugReturnList = new ArrayList();
      Map<String, BigDecimal> sourceDoseMap = new HashMap();
      this.genTakeDrugReturn(takeDrugReturnList, list, sourceDoseMap);
      this.takeDrugReturnMapper.insertList(takeDrugReturnList);
      this.takeDrugReturnLogService.addTakeDrugListLogList(0, takeDrugReturnList, 2, sourceDoseMap);
   }

   private void genTakeDrugReturn(List takeDrugReturnList, List takeDrugFeeDTOList, Map sourceDoseMap) throws Exception {
      Map<String, List<TakeDrugFeeDTO>> takeDrugFeeDTOMap = (Map)takeDrugFeeDTOList.stream().collect(Collectors.groupingBy(TakeDrugFeeDTO::getExecutorDptNo));
      SysUser user = SecurityUtils.getLoginUser().getUser();

      for(String executorDptNo : takeDrugFeeDTOMap.keySet()) {
         List<TakeDrugFeeDTO> tempList = (List)takeDrugFeeDTOMap.get(executorDptNo);
         String documentOrBillNo = this.hisProcService.getDocumentOrBillNo(user.getUserName(), 2);

         for(int i = 0; i < tempList.size(); ++i) {
            TakeDrugFeeDTO temp = (TakeDrugFeeDTO)tempList.get(i);
            TakeDrugReturn takeDrugReturn = new TakeDrugReturn();
            takeDrugReturn.setSerialNumber(documentOrBillNo);
            takeDrugReturn.setSerialNumberXh(i + 1 + "");
            takeDrugReturn.setHospitalCode(temp.getHospitalCode());
            takeDrugReturn.setCaseNo(temp.getCaseNo());
            takeDrugReturn.setPatientId(temp.getPatientId());
            takeDrugReturn.setAdmissionNo(temp.getAdmissionNo());
            takeDrugReturn.setHospitalizedCount(temp.getHospitalizedCount());
            takeDrugReturn.setPatientName(temp.getPatientName());
            takeDrugReturn.setResidentCode(temp.getVisitingStaffCode());
            takeDrugReturn.setResidentNo(temp.getVisitingStaffNo());
            takeDrugReturn.setResidentName(temp.getVisitingStaffName());
            takeDrugReturn.setWardNo(temp.getWardNo());
            takeDrugReturn.setWardName(temp.getWardName());
            takeDrugReturn.setChargeCode(temp.getChargeCode());
            takeDrugReturn.setDrugStockNo(temp.getDrugStockNo());
            takeDrugReturn.setChargeName(temp.getChargeName());
            takeDrugReturn.setStandard(temp.getStandard());
            takeDrugReturn.setUnit(temp.getUnit());
            takeDrugReturn.setPrice(temp.getPrice());
            takeDrugReturn.setDose(temp.getApplyDose().multiply(new BigDecimal("-1")));
            takeDrugReturn.setTotal(temp.getPrice().multiply(temp.getApplyDose()).multiply(new BigDecimal("-1")));
            takeDrugReturn.setOperatorCode(user.getUserName());
            takeDrugReturn.setOperatorNo(user.getUserName());
            takeDrugReturn.setOperatorName(user.getNickName());
            takeDrugReturn.setOperatorDate(new Date());
            takeDrugReturn.setBillsNoOld(temp.getBillsNo());
            takeDrugReturn.setMark("0");
            takeDrugReturn.setApplyName(user.getDept().getDeptName());
            takeDrugReturn.setApplyNo(user.getDept().getDeptCode());
            takeDrugReturn.setExecutorDptNo(temp.getExecutorDptNo());
            takeDrugReturn.setPhysicianName(temp.getOrderName());
            takeDrugReturn.setTakeDrugWardNo(temp.getTakeDrugWardNo());
            takeDrugReturn.setOrderNo(temp.getCopeNo());
            takeDrugReturn.setOrderSortNumber(temp.getCopeSortNumber());
            takeDrugReturn.setOrderGroupSortNumber(temp.getItemSortNumber());
            if (StringUtils.isNotBlank(temp.getCopeGroup())) {
               takeDrugReturn.setOrderGroupNo(Integer.valueOf(temp.getCopeGroup()));
            }

            takeDrugReturn.setPrescriptionOld(temp.getPrescription());
            takeDrugReturnList.add(takeDrugReturn);
            sourceDoseMap.put(takeDrugReturn.getSerialNumber() + "_" + takeDrugReturn.getSerialNumberXh(), temp.getDose().subtract(temp.getReturnedDose()));
         }
      }

   }

   public void addList(List list) throws Exception {
      this.takeDrugReturnMapper.insertList(list);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public DrugListAndPerformReturnResultVo doReturnDrug(List returnVoList, String ip) throws Exception {
      DrugListAndPerformReturnResultVo resultVo = new DrugListAndPerformReturnResultVo();
      List<DrugDoseVo> drugDoseVoList = new ArrayList(1);
      Date currDate = this.commonService.getDbSysdate();
      List<DrugListAndPerformReturnVo> returnVoListNew = new ArrayList(returnVoList.size());
      List<TakeDrugList> deleteList = new ArrayList(1);
      List<TakeDrugList> updateList = new ArrayList(1);
      List<DrugListAndPerformReturnVo> returnVoList2 = new ArrayList(1);
      List<DrugListAndPerformReturnVo> returnVoListTmpl1 = (List)returnVoList.stream().filter((t) -> StringUtils.isNotBlank(t.getPerformListNo()) && t.getPerformListSortNumber() != null).collect(Collectors.toList());
      List<DrugListAndPerformReturnVo> returnVoListTmpl2 = (List)returnVoList.stream().filter((t) -> !StringUtils.isNotBlank(t.getPerformListNo()) || t.getPerformListSortNumber() == null).collect(Collectors.toList());
      if (CollectionUtils.isNotEmpty(returnVoListTmpl2)) {
         returnVoList2.addAll(returnVoListTmpl2);
      }

      if (CollectionUtils.isNotEmpty(returnVoListTmpl1)) {
         Map<String, List<DrugListAndPerformReturnVo>> map1 = (Map)returnVoListTmpl1.stream().collect(Collectors.groupingBy((t) -> t.getPerformListNo() + t.getPerformListSortNumber()));

         for(String key : map1.keySet()) {
            List<DrugListAndPerformReturnVo> listTemp = (List)map1.get(key);
            returnVoList2.add(listTemp.get(0));
         }
      }

      List<TdPaOrderPerformDetail> performDetailListParam = new ArrayList(1);

      for(DrugListAndPerformReturnVo drugListAndPerformReturnVo : returnVoList2) {
         if (StringUtils.isNotBlank(drugListAndPerformReturnVo.getPerformListNo()) && drugListAndPerformReturnVo.getPerformListSortNumber() != null) {
            TdPaOrderPerformDetail performDetail = new TdPaOrderPerformDetail();
            performDetail.setPerformListNo(drugListAndPerformReturnVo.getPerformListNo());
            performDetail.setPerformListSortNumber(drugListAndPerformReturnVo.getPerformListSortNumber().toString());
            performDetailListParam.add(performDetail);
         }
      }

      List<TdPaOrderPerformDetail> performDetailList = null;
      if (CollectionUtils.isNotEmpty(performDetailListParam)) {
         performDetailList = this.orderPerformDetailService.selectPerformDetailList(performDetailListParam);
      }

      Map<String, List<TdPaOrderPerformDetail>> performDetailListMap = (Map<String, List<TdPaOrderPerformDetail>>)(CollectionUtils.isNotEmpty(performDetailList) ? (Map)performDetailList.stream().collect(Collectors.groupingBy((t) -> t.getPerformListNo() + t.getPerformListSortNumber())) : new HashMap(1));
      List<TakeDrugList> takeDrugLists = this.takeDrugListService.selectListByReturnVo(returnVoList2);
      if (CollectionUtils.isNotEmpty(takeDrugLists)) {
         Map<Long, TakeDrugList> takeDrugListMap = (Map<Long, TakeDrugList>)(CollectionUtils.isNotEmpty(takeDrugLists) ? (Map)takeDrugLists.stream().collect(Collectors.toMap((t) -> t.getId(), Function.identity())) : new HashMap(1));
         Map<String, List<TakeDrugList>> takeDrugListPerformMap = (Map<String, List<TakeDrugList>>)(CollectionUtils.isNotEmpty(takeDrugLists) ? (Map)takeDrugLists.stream().collect(Collectors.groupingBy((t) -> t.getPerformListNo())) : new HashMap(1));
         Map<String, TakeDrugList> takeDrugListPerformMap2 = (Map<String, TakeDrugList>)(CollectionUtils.isNotEmpty(takeDrugLists) ? (Map)takeDrugLists.stream().collect(Collectors.toMap((t) -> t.getPerformListNo() + t.getOrderSortNumber() + t.getOrderGroupNo() + t.getOrderGroupSortNumber() + t.getPharmacopoeiaNo(), Function.identity())) : new HashMap(1));

         for(DrugListAndPerformReturnVo drugListAndPerformReturnVo : returnVoList2) {
            if (drugListAndPerformReturnVo.getId() != null && drugListAndPerformReturnVo.getDrugReturnDose() == null) {
               TakeDrugList takeDrugList = (TakeDrugList)takeDrugListMap.get(drugListAndPerformReturnVo.getId());
               if (takeDrugList != null) {
                  DrugListAndPerformReturnVo drugListAndPerformReturnVoNew = new DrugListAndPerformReturnVo();
                  drugListAndPerformReturnVoNew.setId(takeDrugList.getId());
                  drugListAndPerformReturnVoNew.setDrugReturnDose(takeDrugList.getOrderDose());
                  returnVoListNew.add(drugListAndPerformReturnVoNew);
               }
            }

            if (drugListAndPerformReturnVo.getId() != null && drugListAndPerformReturnVo.getDrugReturnDose() != null) {
               TakeDrugList takeDrugList = (TakeDrugList)takeDrugListMap.get(drugListAndPerformReturnVo.getId());
               if (takeDrugList != null) {
                  returnVoListNew.add(drugListAndPerformReturnVo);
               }
            }

            if (StringUtils.isNotBlank(drugListAndPerformReturnVo.getPerformListNo()) && drugListAndPerformReturnVo.getPerformListSortNumber() == null) {
               List<TakeDrugList> takeDrugListsTemp = (List)takeDrugListPerformMap.get(drugListAndPerformReturnVo.getPerformListNo());
               if (CollectionUtils.isNotEmpty(takeDrugListsTemp)) {
                  for(TakeDrugList takeDrugList : takeDrugListsTemp) {
                     DrugListAndPerformReturnVo drugListAndPerformReturnVoNew = new DrugListAndPerformReturnVo();
                     drugListAndPerformReturnVoNew.setId(takeDrugList.getId());
                     drugListAndPerformReturnVoNew.setDrugReturnDose(takeDrugList.getOrderDose());
                     returnVoListNew.add(drugListAndPerformReturnVoNew);
                  }
               }
            }

            if (StringUtils.isNotBlank(drugListAndPerformReturnVo.getPerformListNo()) && drugListAndPerformReturnVo.getPerformListSortNumber() != null) {
               List<TdPaOrderPerformDetail> performDetailListTemp = (List)performDetailListMap.get(drugListAndPerformReturnVo.getPerformListNo() + drugListAndPerformReturnVo.getPerformListSortNumber());
               if (CollectionUtils.isNotEmpty(performDetailListTemp)) {
                  for(TdPaOrderPerformDetail performDetail : performDetailListTemp) {
                     TakeDrugList takeDrugList = (TakeDrugList)takeDrugListPerformMap2.get(performDetail.getPerformListNo() + performDetail.getOrderSortNumber() + performDetail.getOrderGroupNo() + performDetail.getOrderGroupSortNumber() + performDetail.getChargeNo());
                     if (takeDrugList != null) {
                        DrugListAndPerformReturnVo drugListAndPerformReturnVoNew = new DrugListAndPerformReturnVo();
                        drugListAndPerformReturnVoNew.setId(takeDrugList.getId());
                        drugListAndPerformReturnVoNew.setDrugReturnDose(performDetail.getOrderDose());
                        returnVoListNew.add(drugListAndPerformReturnVoNew);
                     }
                  }
               }
            }
         }

         List<HisYfkcHz> yfkcHzList = new ArrayList();
         List<TakeDrugReturn> drugReturnList = this.genTakeDrugReturnList(takeDrugLists, returnVoListNew, currDate, yfkcHzList, deleteList, updateList, drugDoseVoList);
         this.takeDrugReturnMapper.insertList(drugReturnList);
         if (CollectionUtils.isNotEmpty(drugDoseVoList)) {
            SysUser sysUser = SecurityUtils.getLoginUser().getUser();
            List<DrugDoseVo> drugDoseVoListNew = new ArrayList(1);

            for(DrugDoseVo drugDoseVo : drugDoseVoList) {
               FreezeQueryReq freezeQueryReq = new FreezeQueryReq();
               freezeQueryReq.setOrgCd(sysUser.getHospital().getOrgCode());
               freezeQueryReq.setFreezeIdList(drugDoseVo.getTakeDrugListId() != null ? Arrays.asList(drugDoseVo.getTakeDrugListId()) : null);
               freezeQueryReq.setFreezeSerialList(Arrays.asList(drugDoseVo.getOrderNo()));
               freezeQueryReq.setDrugCd(drugDoseVo.getYpbm());
               freezeQueryReq.setDeptCode(drugDoseVo.getYfbm());
               List<TmPaFreeze> freezeListDbTemp = this.drugStockService.selectFreezeList(freezeQueryReq);
               if (CollectionUtils.isNotEmpty(freezeListDbTemp)) {
                  drugDoseVoListNew.add(drugDoseVo);
               }
            }

            List<DrugDoseVo> drugDoseVos = this.drugStockService.updateDrugDoseByOrderDose(sysUser, drugDoseVoListNew, "0", ip);

            try {
               this.drugAndClinService.updateDurgXcsl(drugDoseVos);
            } catch (Exception e) {
               this.log.error("领药单退药更新es药品数量出现异常，", e);
            }
         }

         List<TakeDrugListLog> takeDrugListLogList = new ArrayList(1);
         if (CollectionUtils.isNotEmpty(deleteList)) {
            List<Long> deleteIdList = (List)deleteList.stream().map((t) -> t.getId()).collect(Collectors.toList());
            this.tdPaTakeDrugListService.moveToDeleteTable(deleteIdList);
            this.tdPaTakeDrugListService.deleteByIds(deleteIdList);
            List<TakeDrugListLog> takeDrugListLogList1 = this.takeDrugListLogService.addTakeDrugListLogList(5, deleteList);
            List<TakeDrugListLog> takeDrugListLogList2 = this.takeDrugListLogService.addTakeDrugListLogList(2, deleteList);
            takeDrugListLogList.addAll(takeDrugListLogList1);
            takeDrugListLogList.addAll(takeDrugListLogList2);
         }

         if (CollectionUtils.isNotEmpty(updateList)) {
            this.takeDrugListService.updateOrderDose(updateList);
            List<TakeDrugListLog> takeDrugListLogList3 = this.takeDrugListLogService.addTakeDrugListLogList(5, updateList);
            takeDrugListLogList.addAll(takeDrugListLogList3);
         }

         resultVo.setDeleteList(deleteList);
         resultVo.setUpdateList(updateList);
         resultVo.setDrugReturnList(drugReturnList);
         resultVo.setTakeDrugListLogList(takeDrugListLogList);
         resultVo.setTakeDrugLists(takeDrugLists);
         resultVo.setYfkcHzList(yfkcHzList);
      }

      return resultVo;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void doReturnDrugCancel(DrugListAndPerformReturnResultVo resultVo) throws Exception {
      List<TakeDrugList> deleteList = resultVo.getDeleteList();
      List<TakeDrugList> updateList = resultVo.getUpdateList();
      List<TakeDrugListLog> takeDrugListLogList = resultVo.getTakeDrugListLogList();
      List<TakeDrugReturn> drugReturnList = resultVo.getDrugReturnList();
      List<Long> logIdList = (List)takeDrugListLogList.stream().map((t) -> t.getId()).collect(Collectors.toList());
      this.takeDrugListLogService.deleteByIdList(logIdList);
      List<String> serialNumberList = (List)drugReturnList.stream().map((t) -> t.getSerialNumber()).collect(Collectors.toList());
      this.takeDrugReturnMapper.deleteByIdList(serialNumberList);
      if (CollectionUtils.isNotEmpty(deleteList)) {
         List<Long> deleteIdList = (List)deleteList.stream().map((t) -> t.getId()).collect(Collectors.toList());
         this.tdPaTakeDrugListService.cancelMoveToDeleteTable(deleteIdList);
         this.tdPaTakeDrugListService.deleteDelTableByIds(deleteIdList);
      }

      if (CollectionUtils.isNotEmpty(updateList)) {
         List<Long> updateIdList = (List)updateList.stream().map((t) -> t.getId()).collect(Collectors.toList());
         List<TakeDrugList> takeDrugLists = (List)resultVo.getTakeDrugLists().stream().filter((t) -> updateIdList.contains(t.getId())).collect(Collectors.toList());
         this.takeDrugListService.updateOrderDose(takeDrugLists);
      }

   }

   public List selectByBillsNoOldList(List billsNoList) throws Exception {
      return this.takeDrugReturnMapper.selectByBillsNoOldListAndMark(billsNoList);
   }

   private List genTakeDrugReturnList(List takeDrugLists, List returnVoList, Date currDate, List yfkcHzList, List deleteList, List updateList, List drugDoseVoList) throws Exception {
      List<TakeDrugReturn> returnList = new ArrayList(1);
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      SysDept sysDept = sysUser.getDept();
      List<String> admissionNoList = (List)takeDrugLists.stream().filter((t) -> StringUtils.isNotBlank(t.getAdmissionNo())).map(TakeDrugList::getAdmissionNo).distinct().collect(Collectors.toList());
      List<Visitinfo> visitinfoList = this.visitinfoService.selectVistinfoListByInpNoList(admissionNoList);
      Map<String, Visitinfo> visitinfoMap = (Map<String, Visitinfo>)(CollectionUtils.isNotEmpty(visitinfoList) ? (Map)visitinfoList.stream().collect(Collectors.toMap(Visitinfo::getInpNo, Function.identity())) : new HashMap(1));
      Map<String, List<TakeDrugList>> pharmacyNoMap = (Map)takeDrugLists.stream().collect(Collectors.groupingBy(TakeDrugList::getPharmacyNo));
      Map<Long, List<DrugListAndPerformReturnVo>> returnVoMap = (Map)returnVoList.stream().collect(Collectors.groupingBy(DrugListAndPerformReturnVo::getId));
      Map<Long, DrugListAndPerformReturnVo> drugMap = new HashMap();

      for(Long id : returnVoMap.keySet()) {
         DrugListAndPerformReturnVo vo = new DrugListAndPerformReturnVo();
         List<DrugListAndPerformReturnVo> voList = (List)returnVoMap.get(id);
         BigDecimal add = (BigDecimal)voList.stream().map(DrugListAndPerformReturnVo::getDrugReturnDose).reduce(BigDecimal.ZERO, BigDecimal::add);
         vo.setDrugReturnDose(add);
         drugMap.put(id, vo);
      }

      for(String pharmacyNo : pharmacyNoMap.keySet()) {
         List<TakeDrugList> takeDrugListsTemp = (List)pharmacyNoMap.get(pharmacyNo);
         String documentOrBillNo = this.hisProcService.getDocumentOrBillNo(sysUser.getUserName(), 2);

         for(int i = 0; i < takeDrugListsTemp.size(); ++i) {
            TakeDrugList takeDrugList = (TakeDrugList)takeDrugListsTemp.get(i);
            Visitinfo visitinfo = (Visitinfo)visitinfoMap.get(takeDrugList.getAdmissionNo());
            DrugListAndPerformReturnVo returnVo = (DrugListAndPerformReturnVo)drugMap.get(takeDrugList.getId());
            if (returnVo != null) {
               TakeDrugReturn takeDrugReturn = new TakeDrugReturn();
               takeDrugReturn.setSerialNumber(documentOrBillNo);
               takeDrugReturn.setSerialNumberXh(i + 1 + "");
               takeDrugReturn.setCaseNo(takeDrugList.getCaseNo());
               takeDrugReturn.setPatientId(visitinfo.getPatientId());
               takeDrugReturn.setAdmissionNo(takeDrugList.getAdmissionNo());
               takeDrugReturn.setHospitalizedCount(takeDrugList.getHospitalizedCount());
               takeDrugReturn.setPatientName(takeDrugList.getPatientName());
               takeDrugReturn.setResidentCode(visitinfo.getResDocCd());
               takeDrugReturn.setResidentNo(visitinfo.getResDocCd());
               takeDrugReturn.setResidentName(visitinfo.getResDocName());
               takeDrugReturn.setWardNo(sysDept.getDeptCode());
               takeDrugReturn.setWardName(sysDept.getDeptName());
               takeDrugReturn.setChargeCode(takeDrugList.getPharmacopoeiaNo());
               takeDrugReturn.setDrugStockNo(takeDrugList.getDrugStockNo());
               takeDrugReturn.setChargeName(takeDrugList.getDrugName());
               takeDrugReturn.setStandard(takeDrugList.getOrderStandard());
               takeDrugReturn.setUnit(takeDrugList.getOrderUnit());
               takeDrugReturn.setPrice(takeDrugList.getOrderPrice());
               takeDrugReturn.setDose(returnVo.getDrugReturnDose().multiply(new BigDecimal("-1")));
               takeDrugReturn.setTotal(takeDrugList.getOrderPrice().multiply(returnVo.getDrugReturnDose()).multiply(new BigDecimal("-1")));
               takeDrugReturn.setOperatorCode(sysUser.getUserName());
               takeDrugReturn.setOperatorNo(sysUser.getUserName());
               takeDrugReturn.setOperatorName(sysUser.getNickName());
               takeDrugReturn.setOperatorDate(currDate);
               takeDrugReturn.setBillsNoOld(takeDrugList.getOrderNo());
               takeDrugReturn.setBillsNoNew(takeDrugList.getOrderSortNumber());
               takeDrugReturn.setOperatorNewCode(sysUser.getUserName());
               takeDrugReturn.setOperatorNewNo(sysUser.getUserName());
               takeDrugReturn.setOperatorNewName(sysUser.getNickName());
               takeDrugReturn.setOperatorNewDate(currDate);
               takeDrugReturn.setBabyNo(takeDrugList.getBabyAdmissionNo());
               takeDrugReturn.setMark("2");
               takeDrugReturn.setApplyNo(takeDrugList.getTakeDrugWardNo());
               takeDrugReturn.setApplyName(takeDrugList.getDeptName());
               takeDrugReturn.setExecutorDptNo(takeDrugList.getPharmacyNo());
               takeDrugReturn.setPhysicianName(takeDrugList.getOrderDoctorName());
               takeDrugReturn.setTakeDrugWardNo(takeDrugList.getTakeDrugWardNo());
               takeDrugReturn.setOrderNo(takeDrugList.getOrderNo());
               takeDrugReturn.setOrderSortNumber(takeDrugList.getOrderSortNumber());
               takeDrugReturn.setOrderGroupNo(Integer.valueOf(takeDrugList.getOrderGroupNo()));
               takeDrugReturn.setOrderGroupSortNumber(takeDrugList.getOrderGroupSortNumber());
               returnList.add(takeDrugReturn);
               HisYfkcHz hisYfkcHz = new HisYfkcHz(takeDrugList.getPharmacopoeiaNo(), takeDrugList.getPharmacyNo(), takeDrugList.getDrugStockNo(), returnVo.getDrugReturnDose().abs());
               yfkcHzList.add(hisYfkcHz);
               DrugDoseVo drugDoseVo = new DrugDoseVo();
               drugDoseVo.setYfbm(takeDrugList.getPharmacyNo());
               drugDoseVo.setYpbm(takeDrugList.getPharmacopoeiaNo());
               drugDoseVo.setKcbh(takeDrugList.getDrugStockNo());
               drugDoseVo.setOrderNo(takeDrugList.getOrderNo());
               drugDoseVo.setPrice(takeDrugList.getOrderPrice());
               drugDoseVo.setTakeDrugListId(takeDrugList.getId());
               if (takeDrugList.getOrderDose().compareTo(returnVo.getDrugReturnDose()) == 0) {
                  deleteList.add(takeDrugList);
               } else if (takeDrugList.getOrderDose().compareTo(returnVo.getDrugReturnDose()) > 0) {
                  TakeDrugList temp = new TakeDrugList();
                  BeanUtils.copyProperties(takeDrugList, temp);
                  temp.setOrderDose(takeDrugList.getOrderDose().subtract(returnVo.getDrugReturnDose()));
                  updateList.add(temp);
               }

               drugDoseVo.setOrderDose(returnVo.getDrugReturnDose().abs());
               drugDoseVoList.add(drugDoseVo);
            }
         }
      }

      return returnList;
   }
}
