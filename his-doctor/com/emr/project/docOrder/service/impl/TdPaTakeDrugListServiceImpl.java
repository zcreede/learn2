package com.emr.project.docOrder.service.impl;

import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.DataSource;
import com.emr.framework.aspectj.lang.enums.DataSourceType;
import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.domain.TdPaTakeDrugApply;
import com.emr.project.docOrder.domain.TdPaTakeDrugList;
import com.emr.project.docOrder.domain.resp.TdPaTakeDrugListDetailPrint;
import com.emr.project.docOrder.domain.vo.HisYfkcHz;
import com.emr.project.docOrder.domain.vo.TdPaTakeDrugListVO;
import com.emr.project.docOrder.mapper.TdPaTakeDrugListMapper;
import com.emr.project.docOrder.service.ITdPaTakeDrugApplyService;
import com.emr.project.docOrder.service.ITdPaTakeDrugListService;
import com.emr.project.his.domain.vo.DrugDoseVo;
import com.emr.project.operation.domain.TakeDrugList;
import com.emr.project.operation.domain.TakeDrugListKey;
import com.emr.project.operation.domain.TakeDrugReturn;
import com.emr.project.operation.domain.resp.PrintDrugListResp;
import com.emr.project.operation.domain.vo.PrintDrugListVo;
import com.emr.project.operation.domain.vo.TakeDrugListSaveVO;
import com.emr.project.operation.domain.vo.TdPaTakeDrugListPageVo;
import com.emr.project.operation.mapper.MedicalinformationMapper;
import com.emr.project.operation.service.HisProcService;
import com.emr.project.operation.service.TakeDrugListLogService;
import com.emr.project.operation.service.TakeDrugReturnLogService;
import com.emr.project.operation.service.TakeDrugReturnService;
import com.emr.project.pat.domain.Visitinfo;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.system.domain.SysDept;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.tmpa.domain.OrderSig;
import com.emr.project.tmpa.service.IOrderSigService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TdPaTakeDrugListServiceImpl implements ITdPaTakeDrugListService {
   @Autowired
   private TdPaTakeDrugListMapper tdPaTakeDrugListMapper;
   @Autowired
   private HisProcService hisProcService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private TakeDrugReturnService takeDrugReturnService;
   @Autowired
   private TakeDrugReturnLogService takeDrugReturnLogService;
   @Autowired
   private TakeDrugListLogService takeDrugListLogService;
   @Autowired
   private MedicalinformationMapper medicalinformationMapper;
   @Autowired
   private ITdPaTakeDrugApplyService tdPaTakeDrugApplyService;
   @Autowired
   private ISysEmrConfigService emrConfigService;
   @Autowired
   private IOrderSigService orderSigService;

   public List selectTakeDrugPatList(TdPaTakeDrugListVO takeDrugListVO) throws Exception {
      SysDept sysDept = SecurityUtils.getLoginUser().getUser().getDept();
      List<TdPaTakeDrugListVO> resultList = new ArrayList();
      takeDrugListVO.setTakeDrugWardNo(sysDept.getDeptCode());
      switch (takeDrugListVO.getTakeFlag()) {
         case "0":
            resultList = this.tdPaTakeDrugListMapper.selectTakeDrugPatList(takeDrugListVO);
            break;
         case "1":
            resultList = this.tdPaTakeDrugListMapper.selectTakeDrugFYPatList(takeDrugListVO);
            break;
         case "2":
            resultList = this.tdPaTakeDrugListMapper.selectTakeDrugFYDeptPatList(takeDrugListVO);
      }

      return resultList;
   }

   public List selectTakeDetailList(TdPaTakeDrugListVO tdPaTakeDrugListVO) throws Exception {
      List<TdPaTakeDrugListVO> resultList = new ArrayList();
      SysDept sysDept = SecurityUtils.getLoginUser().getUser().getDept();
      tdPaTakeDrugListVO.setTakeDrugWardNo(sysDept.getDeptCode());
      new ArrayList();
      List list;
      if (tdPaTakeDrugListVO.getTakeFlag().equals("0")) {
         list = this.tdPaTakeDrugListMapper.selectTakeDrugDetailList(tdPaTakeDrugListVO);
      } else {
         list = this.tdPaTakeDrugListMapper.selectTakeDrugFYDetailList(tdPaTakeDrugListVO);
      }

      if (list != null && !list.isEmpty()) {
         if (tdPaTakeDrugListVO.getQueryType().equals("0")) {
            Map<String, List<TdPaTakeDrugListVO>> patMap = (Map)list.stream().collect(Collectors.groupingBy((s) -> s.getAdmissionNo()));

            for(String admissionNo : patMap.keySet()) {
               List<TdPaTakeDrugListVO> patList = (List)patMap.get(admissionNo);
               Map<String, List<TdPaTakeDrugListVO>> listMap = (Map)patList.stream().collect(Collectors.groupingBy((s) -> s.getOrderNo() + s.getPerformListNo()));
               String orderNoKey = null;

               for(TdPaTakeDrugListVO takeDrugListPageVo : patList) {
                  String orderNoKeyTemp = takeDrugListPageVo.getOrderNo() + takeDrugListPageVo.getPerformListNo();
                  List<TdPaTakeDrugListVO> listTemp = null;
                  if (!StringUtils.isBlank(orderNoKey) && orderNoKey.equals(orderNoKeyTemp)) {
                     orderNoKey = orderNoKeyTemp;
                  } else {
                     orderNoKey = orderNoKeyTemp;
                     listTemp = (List)listMap.get(orderNoKeyTemp);
                     List zList = (List)listTemp.stream().filter((s) -> s.getOrderGroupSortNumber().equals("01")).collect(Collectors.toList());
                     List sList = (List)listTemp.stream().filter((s) -> !s.getOrderGroupSortNumber().equals("01")).collect(Collectors.toList());
                     TdPaTakeDrugListVO zVo = null;
                     if (zList == null || zList != null && zList.isEmpty()) {
                        if (sList.size() > 1) {
                           zVo = (TdPaTakeDrugListVO)sList.get(0);
                           sList = sList.subList(1, sList.size());
                        } else {
                           zVo = (TdPaTakeDrugListVO)sList.get(0);
                           sList = null;
                        }
                     } else {
                        zVo = (TdPaTakeDrugListVO)zList.get(0);
                     }

                     zVo.setIsGroupFirst("01");
                     zVo.setDrugName(zVo.getDrugName() + "(" + zVo.getOrderStandard() + ")");
                     if (sList != null && !sList.isEmpty()) {
                        zVo.setGroupStr("┓");
                        resultList.add(zVo);
                        sList = (List)sList.stream().sorted(Comparator.comparing(TdPaTakeDrugListVO::getOrderGroupSortNumber)).collect(Collectors.toList());

                        for(int i = 0; i < sList.size(); ++i) {
                           TdPaTakeDrugListVO sVo = (TdPaTakeDrugListVO)sList.get(i);
                           sVo.setDrugName(sVo.getDrugName() + "(" + sVo.getOrderStandard() + ")");
                           if (i == sList.size() - 1) {
                              sVo.setGroupStr("┛");
                           } else {
                              sVo.setGroupStr("┃");
                           }

                           resultList.add(sVo);
                        }
                     } else {
                        zVo.setGroupStr("");
                        resultList.add(zVo);
                     }
                  }
               }
            }
         } else {
            Map<String, List<TdPaTakeDrugListVO>> listMap = (Map)list.stream().collect(Collectors.groupingBy((s) -> s.getDrugCode()));

            for(String drugCode : listMap.keySet()) {
               List<TdPaTakeDrugListVO> drugList = (List)listMap.get(drugCode);
               TdPaTakeDrugListVO takeDrugListVO = (TdPaTakeDrugListVO)drugList.get(0);
               BigDecimal dose = new BigDecimal(0);

               for(TdPaTakeDrugListVO drugListVO : drugList) {
                  dose = dose.add(drugListVO.getOrderDose());
               }

               takeDrugListVO.setOrderDose(dose);
               BigDecimal total = dose.multiply(takeDrugListVO.getOrderPrice());
               takeDrugListVO.setOrderTotal(total.doubleValue());
               resultList.add(takeDrugListVO);
            }
         }
      }

      return resultList;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public List updateTakeDrugList(List takeDrugListVOList, String vaild) throws Exception {
      List<HisYfkcHz> list = new ArrayList();
      List<TakeDrugReturn> takeDrugReturnList = new ArrayList(1);
      Map<String, BigDecimal> sourceDoseMap = new HashMap(1);
      this.genTakeDrugReturn(takeDrugReturnList, takeDrugListVOList, sourceDoseMap);
      this.takeDrugReturnService.addList(takeDrugReturnList);
      this.takeDrugReturnLogService.addTakeDrugListLogList(1, takeDrugReturnList, 1, sourceDoseMap);
      List<Long> takeDrugListIdList = (List)takeDrugListVOList.stream().map((t) -> t.getId()).collect(Collectors.toList());
      this.tdPaTakeDrugListMapper.moveToDeleteTable(takeDrugListIdList);
      this.tdPaTakeDrugListMapper.deleteByIds(takeDrugListIdList);
      this.takeDrugListLogService.addTakeDrugListLogListByVO(5, takeDrugListVOList);
      this.takeDrugListLogService.addTakeDrugListLogListByVO(2, takeDrugListVOList);

      for(TdPaTakeDrugListVO takeDrugListVO : takeDrugListVOList) {
         HisYfkcHz hisYfkcHz = new HisYfkcHz(takeDrugListVO.getDrugCode(), takeDrugListVO.getPharmacyNo(), takeDrugListVO.getDrugStockNo(), takeDrugListVO.getOrderDose().abs());
         list.add(hisYfkcHz);
      }

      return list;
   }

   private void genTakeDrugReturn(List takeDrugReturnList, List takeDrugListVOList, Map sourceDoseMap) throws Exception {
      Map<String, List<TdPaTakeDrugListVO>> takeDrugFeeDTOMap = (Map)takeDrugListVOList.stream().collect(Collectors.groupingBy(TdPaTakeDrugList::getPharmacyNo));
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      SysDept sysDept = sysUser.getDept();
      List<String> admissionNoList = (List)takeDrugListVOList.stream().filter((t) -> StringUtils.isNotBlank(t.getAdmissionNo())).map((t) -> t.getAdmissionNo()).distinct().collect(Collectors.toList());
      List<Visitinfo> visitinfoList = this.visitinfoService.selectVistinfoListByInpNoList(admissionNoList);
      Map<String, Visitinfo> visitinfoMap = (Map<String, Visitinfo>)(CollectionUtils.isNotEmpty(visitinfoList) ? (Map)visitinfoList.stream().collect(Collectors.toMap((t) -> t.getInpNo(), Function.identity())) : new HashMap(1));
      Date currDate = this.commonService.getDbSysdate();

      for(String key : takeDrugFeeDTOMap.keySet()) {
         List<TdPaTakeDrugListVO> list = (List)takeDrugFeeDTOMap.get(key);
         int i = 0;

         for(TdPaTakeDrugListVO takeDrugListVO : list) {
            Visitinfo visitinfo = (Visitinfo)visitinfoMap.get(takeDrugListVO.getAdmissionNo());
            String documentOrBillNo = this.hisProcService.getDocumentOrBillNo(sysUser.getUserName(), 2);
            TakeDrugReturn takeDrugReturn = new TakeDrugReturn();
            takeDrugReturn.setSerialNumber(documentOrBillNo);
            StringBuilder var10001 = new StringBuilder();
            ++i;
            takeDrugReturn.setSerialNumberXh(var10001.append(i).append("").toString());
            takeDrugReturn.setHospitalCode(visitinfo != null ? visitinfo.getOrgCd() : null);
            takeDrugReturn.setCaseNo(takeDrugListVO.getCaseNo());
            takeDrugReturn.setAdmissionNo(takeDrugListVO.getAdmissionNo());
            takeDrugReturn.setHospitalizedCount(takeDrugListVO.getHospitalizedCount());
            takeDrugReturn.setPatientName(takeDrugListVO.getPatientName());
            takeDrugReturn.setResidentCode(visitinfo != null ? visitinfo.getResDocCd() : null);
            takeDrugReturn.setResidentNo(visitinfo != null ? visitinfo.getResDocCd() : null);
            takeDrugReturn.setResidentName(visitinfo != null ? visitinfo.getResDocName() : null);
            takeDrugReturn.setWardNo(sysDept.getDeptCode());
            takeDrugReturn.setWardName(sysDept.getDeptName());
            takeDrugReturn.setChargeCode(takeDrugListVO.getPharmacopoeiaNo());
            takeDrugReturn.setDrugStockNo(takeDrugListVO.getDrugStockNo());
            takeDrugReturn.setChargeName(takeDrugListVO.getDrugName());
            takeDrugReturn.setStandard(takeDrugListVO.getOrderStandard());
            takeDrugReturn.setUnit(takeDrugListVO.getOrderUnit());
            takeDrugReturn.setPrice(takeDrugListVO.getOrderPrice());
            takeDrugReturn.setDose(takeDrugListVO.getOrderDose());
            takeDrugReturn.setTotal(takeDrugReturn.getDose().multiply(takeDrugReturn.getPrice()));
            takeDrugReturn.setOperatorCode(sysUser.getUserName());
            takeDrugReturn.setOperatorNo(sysUser.getUserName());
            takeDrugReturn.setOperatorDate(currDate);
            takeDrugReturn.setOperatorName(sysUser.getNickName());
            takeDrugReturn.setBillsNoOld(takeDrugListVO.getOrderNo());
            takeDrugReturn.setBillsNoNew(takeDrugListVO.getOrderSortNumber());
            takeDrugReturn.setOperatorNewCode(sysUser.getUserName());
            takeDrugReturn.setOperatorNewDate(currDate);
            takeDrugReturn.setOperatorNewName(sysUser.getNickName());
            takeDrugReturn.setOperatorNewNo(sysUser.getUserName());
            takeDrugReturn.setBabyNo(takeDrugListVO.getBabyAdmissionNo());
            takeDrugReturn.setMark("2");
            takeDrugReturn.setApplyNo(sysUser.getUserName());
            takeDrugReturn.setApplyName(sysUser.getNickName());
            takeDrugReturn.setExecutorDptNo(takeDrugListVO.getPharmacyNo());
            takeDrugReturn.setPhysicianName(takeDrugListVO.getOrderDoctorName());
            takeDrugReturn.setTakeDrugWardNo(takeDrugListVO.getTakeDrugWardNo());
            takeDrugReturn.setOrderNo(takeDrugListVO.getOrderNo());
            takeDrugReturn.setOrderSortNumber(takeDrugListVO.getOrderSortNumber());
            takeDrugReturn.setOrderGroupSortNumber(takeDrugListVO.getOrderGroupSortNumber());
            if (takeDrugListVO.getOrderGroupNo() != null) {
               takeDrugReturn.setOrderGroupNo(takeDrugListVO.getOrderGroupNo());
            }

            takeDrugReturnList.add(takeDrugReturn);
            sourceDoseMap.put(takeDrugReturn.getSerialNumber() + "_" + takeDrugReturn.getSerialNumberXh(), takeDrugListVO.getOrderDose());
         }
      }

   }

   @DataSource(DataSourceType.hisDrugStock)
   public void updateHisYfkc(List hisYfkcHzs) throws Exception {
      this.tdPaTakeDrugListMapper.updateHisYfkc(hisYfkcHzs);
   }

   public void insertList(List list) throws Exception {
      if (CollectionUtils.isNotEmpty(list)) {
         this.tdPaTakeDrugListMapper.insertList(list);
      }

   }

   public void moveToDeleteTable(List idList) throws Exception {
      this.tdPaTakeDrugListMapper.moveToDeleteTable(idList);
   }

   public void deleteByIds(List idList) throws Exception {
      this.tdPaTakeDrugListMapper.deleteByIds(idList);
   }

   public void cancelMoveToDeleteTable(List idList) throws Exception {
      this.tdPaTakeDrugListMapper.cancelMoveToDeleteTable(idList);
   }

   public void deleteDelTableByIds(List idList) throws Exception {
      this.tdPaTakeDrugListMapper.deleteDelTableByIds(idList);
   }

   public List printTakeDetailList(TdPaTakeDrugListVO tdPaTakeDrugListVO) throws Exception {
      List<TdPaTakeDrugListDetailPrint> list = new ArrayList();
      List<TdPaTakeDrugListVO> paTakeDrugListVOS = this.selectTakeDetailList(tdPaTakeDrugListVO);
      Map<String, List<TdPaTakeDrugListVO>> collect = (Map)paTakeDrugListVOS.stream().collect(Collectors.groupingBy(TdPaTakeDrugListVO::getAdmissionNo));
      Set<String> admissionNoList = collect.keySet();
      List<TdPaTakeDrugListDetailPrint> infoList = this.tdPaTakeDrugListMapper.selectInfoByAdmissionNoList(admissionNoList);
      Map<String, List<TdPaTakeDrugListDetailPrint>> infoMap = (Map)infoList.stream().collect(Collectors.groupingBy(TdPaTakeDrugListVO::getAdmissionNo));

      for(TdPaTakeDrugListVO vo : paTakeDrugListVOS) {
         TdPaTakeDrugListDetailPrint print = new TdPaTakeDrugListDetailPrint();
         BeanUtils.copyProperties(vo, print);
         String admissionNo = vo.getAdmissionNo();
         List<TdPaTakeDrugListDetailPrint> detailPrints = (List)infoMap.get(admissionNo);
         if (detailPrints.size() > 0) {
            TdPaTakeDrugListDetailPrint detailPrint = (TdPaTakeDrugListDetailPrint)detailPrints.get(0);
            print.setBedid(detailPrint.getBedid());
            print.setDeptName(detailPrint.getDeptName());
         }

         print.setPrintName(SecurityUtils.getLoginUser().getUser().getNickName());
         print.setDept(SecurityUtils.getLoginUser().getUser().getDept().getDeptName());
         list.add(print);
      }

      return list;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public List saveDrug(List list) throws Exception {
      String drugUsageWayCode = this.emrConfigService.selectSysEmrConfigByKey("0088");
      OrderSig orderSig = StringUtils.isNotBlank(drugUsageWayCode) ? this.orderSigService.selectOrderSigById(drugUsageWayCode) : null;
      List<DrugDoseVo> drugDoseVoList = new ArrayList(1);
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      SysDept sysDept = sysUser.getDept();
      List<TakeDrugList> drugList = new ArrayList();
      Date currDate = this.commonService.getDbSysdate();
      Integer orderGroupNo = this.tdPaTakeDrugListMapper.selectMaxGroupNo(((TakeDrugListSaveVO)list.get(0)).getAdmissionNo());
      if (orderGroupNo == null) {
         orderGroupNo = 1;
      } else {
         orderGroupNo = orderGroupNo + 1;
      }

      for(int i = 0; i < list.size(); ++i) {
         String performListNo = this.hisProcService.getDocumentOrBillNo(SecurityUtils.getLoginUser().getUser().getUserName(), 3);
         TakeDrugList drug = new TakeDrugList();
         TakeDrugListSaveVO saveVO = (TakeDrugListSaveVO)list.get(i);
         BeanUtils.copyProperties(saveVO, drug);
         drug.setId(SnowIdUtils.uniqueLong());
         drug.setPerformListNo(performListNo);
         drug.setPerformListSortNumber(1);
         drug.setOrderType("2");
         drug.setTakeDrugWardNo(sysDept.getDeptCode());
         drug.setTakeDrugTime(currDate);
         drug.setDrugEatTime(currDate);
         drug.setTakeDrugOperator(sysUser.getUserName());
         drug.setTakeDrugOperatorName(sysUser.getNickName());
         drug.setOutDrugFlag("0");
         drug.setTakeDrugStatus(0);
         drug.setValid(1);
         drug.setRemark("opeAddDrug");
         drug.setOrderNo(performListNo);
         drug.setOrderGroupNo(orderGroupNo + i + "");
         drug.setOrderGroupSortNumber("01");
         drug.setOrderSortNumber("01");
         drug.setDrugUsageWay(orderSig != null ? orderSig.getSigName() : null);
         drug.setDrugUsageWayCode(drugUsageWayCode);
         drug.setSourceFlag("2");
         drugList.add(drug);
         String ypid = drug.getHospitalCode() + drug.getPharmacyNo() + saveVO.getPharmacopoeiaNo() + drug.getOrderUnit() + drug.getOrderPrice();
         DrugDoseVo drugDoseVo = new DrugDoseVo(ypid, drug.getPharmacyNo(), saveVO.getPharmacopoeiaNo(), saveVO.getDrugStockNo(), drug.getOrderDose().negate(), drug.getOrderPrice());
         drugDoseVo.setOrderNo(drug.getOrderNo());
         drugDoseVo.setTakeDrugListId(drug.getId());
         drugDoseVoList.add(drugDoseVo);
      }

      if (drugList.size() > 0) {
         this.tdPaTakeDrugListMapper.insertList(drugList);
      }

      return drugDoseVoList;
   }

   public List selectTakeListForApply(TdPaTakeDrugListPageVo tdPaTakeDrugListPageVo) throws Exception {
      String drugEatTimeFlag = tdPaTakeDrugListPageVo.getDrugEatTimeFlag();
      if (StringUtils.isNotBlank(drugEatTimeFlag) && (drugEatTimeFlag.equals("1") || drugEatTimeFlag.equals("2"))) {
         Date currDate = this.commonService.getDbSysdate();
         String drugEatTimeBegin = DateUtils.dateFormat(currDate, "yyyy-MM-dd") + " 00:00:00";
         String drugEatTimeEnd = DateUtils.dateFormat(DateUtils.addDay((Date)currDate, 1), "yyyy-MM-dd") + " 00:00:00";
         tdPaTakeDrugListPageVo.setDrugEatTimeBegin(drugEatTimeBegin);
         tdPaTakeDrugListPageVo.setDrugEatTimeEnd(drugEatTimeEnd);
      }

      List<TdPaTakeDrugListPageVo> listAll = this.tdPaTakeDrugListMapper.selectTakeDrugList(tdPaTakeDrugListPageVo);
      return listAll;
   }

   public List selectTakeDrugDetailList(TdPaTakeDrugListPageVo req, List listAll, Integer pageSize, Integer pageNum) {
      List<TdPaTakeDrugListPageVo> resultList = new ArrayList(1);
      List<TdPaTakeDrugListPageVo> listPage = new ArrayList(1);
      int begin = 0;
      int end = 0;
      if (pageSize != 0) {
         begin = (pageNum - 1) * pageSize;
         end = pageNum * pageSize;
         if (end > listAll.size()) {
            end = listAll.size();
         }
      }

      if (end == 0) {
         listPage.addAll(listAll);
      } else {
         listPage = listAll.subList(begin, end);
      }

      if (listPage != null && listPage.size() > 0) {
         Map<String, List<TdPaTakeDrugListPageVo>> listMap = (Map)listPage.stream().collect(Collectors.groupingBy((s) -> s.getOrderNo() + s.getPerformListNo()));
         String orderNoKey = null;

         for(TdPaTakeDrugListPageVo takeDrugListPageVo : listPage) {
            String orderNoKeyTemp = takeDrugListPageVo.getOrderNo() + takeDrugListPageVo.getPerformListNo();
            List<TdPaTakeDrugListPageVo> listTemp = null;
            if (!StringUtils.isBlank(orderNoKey) && orderNoKey.equals(orderNoKeyTemp)) {
               orderNoKey = orderNoKeyTemp;
            } else {
               orderNoKey = orderNoKeyTemp;
               listTemp = (List)listMap.get(orderNoKeyTemp);
               List zList = (List)listTemp.stream().filter((s) -> s.getOrderGroupSortNumber().equals("01")).collect(Collectors.toList());
               List sList = (List)listTemp.stream().filter((s) -> !s.getOrderGroupSortNumber().equals("01")).collect(Collectors.toList());
               TdPaTakeDrugListPageVo zVo = null;
               if (zList == null || zList != null && zList.isEmpty()) {
                  if (sList.size() > 1) {
                     zVo = (TdPaTakeDrugListPageVo)sList.get(0);
                     sList = sList.subList(1, sList.size());
                  } else {
                     zVo = (TdPaTakeDrugListPageVo)sList.get(0);
                     sList = null;
                  }
               } else {
                  zVo = (TdPaTakeDrugListPageVo)zList.get(0);
               }

               zVo.setIsGroupFirst("01");
               zVo.setDrugName(zVo.getDrugName() + "(" + zVo.getOrderStandard() + ")");
               if (sList != null && !sList.isEmpty()) {
                  zVo.setGroupStr("┏");
                  resultList.add(zVo);
                  sList = (List)sList.stream().sorted(Comparator.comparing(TakeDrugListKey::getOrderGroupSortNumber)).collect(Collectors.toList());

                  for(int i = 0; i < sList.size(); ++i) {
                     TdPaTakeDrugListPageVo sVo = (TdPaTakeDrugListPageVo)sList.get(i);
                     if (StringUtils.isNotBlank(sVo.getOrderStandard())) {
                        sVo.setDrugName(sVo.getDrugName() + "(" + sVo.getOrderStandard() + ")");
                     }

                     if (i == sList.size() - 1) {
                        sVo.setGroupStr("┗");
                     } else {
                        sVo.setGroupStr("┃");
                     }

                     resultList.add(sVo);
                  }
               } else {
                  zVo.setGroupStr("∑");
                  resultList.add(zVo);
               }
            }
         }
      }

      return resultList;
   }

   public List selectDrugListStatusByIds(List idList) {
      return this.tdPaTakeDrugListMapper.selectDrugListStatusByIds(idList);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public PrintDrugListResp sendToPharmacy(List takeDrugLists) throws Exception {
      Map<String, List<TakeDrugList>> takeDrugListMap = (Map)takeDrugLists.stream().collect(Collectors.groupingBy((t) -> t.getPharmacyNo()));
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      SysDept sysDept = sysUser.getDept();
      Date currDate = this.commonService.getDbSysdate();
      TdPaTakeDrugApply lastApply = this.tdPaTakeDrugApplyService.selectLastApply(sysUser.getHospital().getOrgCode(), sysDept.getDeptCode(), currDate);
      Integer applyNum = null;
      List<String> applyNoList = new ArrayList(takeDrugListMap.keySet().size());

      for(String pharmacyNo : takeDrugListMap.keySet()) {
         applyNum = lastApply != null ? lastApply.getApplyNum() + 1 : 1;
         String applyNo = sysDept.getDeptCode() + DateUtils.formatYMD(currDate) + applyNum;
         TdPaTakeDrugApply currApply = new TdPaTakeDrugApply();
         currApply.setId(SnowIdUtils.uniqueLong());
         currApply.setOrgCode(sysUser.getHospital().getOrgCode());
         currApply.setDeptCode(sysDept.getDeptCode());
         currApply.setApplyNum(applyNum);
         currApply.setApplyDate(currDate);
         currApply.setApplyNo(applyNo);
         this.tdPaTakeDrugApplyService.insertTdPaTakeDrugApply(currApply);
         List<TakeDrugList> takeDrugListsTemp = (List)takeDrugListMap.get(pharmacyNo);
         this.tdPaTakeDrugListMapper.updateTakeDrugStatusApply(currDate, 1, 0, applyNo, sysUser.getUserName(), takeDrugListsTemp);
         this.takeDrugListLogService.addTakeDrugListLogList(1, takeDrugListsTemp);
         applyNoList.add(applyNo);
      }

      PrintDrugListResp printDrugListResp = this.printDrugList(applyNoList);
      return printDrugListResp;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public List toVoidTakeDrugLists(List list) throws Exception {
      List<DrugDoseVo> drugDoseVoList = new ArrayList();
      String staffCode = SecurityUtils.getLoginUser().getUser().getUserName();
      List<Long> idList = (List)list.stream().map(TakeDrugList::getId).distinct().collect(Collectors.toList());
      this.tdPaTakeDrugListMapper.moveToDeleteTableByIds(idList);
      this.tdPaTakeDrugListMapper.updateStatusByIds(idList, staffCode);
      List<TakeDrugList> takeDrugLists = this.tdPaTakeDrugListMapper.selectTakeDrugListByType("2", (List)idList.stream().map((t) -> t + "").collect(Collectors.toList()));
      this.tdPaTakeDrugListMapper.deleteListTableByIds(idList);
      this.takeDrugListLogService.addTakeDrugListLogList(-1, takeDrugLists);

      for(TakeDrugList drug : takeDrugLists) {
         DrugDoseVo drugDoseVo = new DrugDoseVo(drug.getPharmacopoeiaNo(), drug.getPharmacyNo(), drug.getPharmacopoeiaNo(), drug.getDrugStockNo(), drug.getOrderDose(), drug.getOrderPrice());
         drugDoseVo.setOrderNo(drug.getOrderNo());
         drugDoseVo.setTakeDrugListId(drug.getId());
         drugDoseVoList.add(drugDoseVo);
      }

      return drugDoseVoList;
   }

   public String verifyOrderCancelTakeDrugList(List orderList) {
      String resStr = null;
      List<String> targetOrderList = new ArrayList(1);
      List<TakeDrugList> list = this.tdPaTakeDrugListMapper.selectOrderTakeDrugLists(orderList);
      Map<String, List<TakeDrugList>> listMap = (Map<String, List<TakeDrugList>>)(CollectionUtils.isNotEmpty(list) ? (Map)list.stream().collect(Collectors.groupingBy((t) -> t.getOrderNo())) : new HashMap(1));

      for(String orderNo : orderList) {
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

   private PrintDrugListResp printDrugList(List applyNo) throws Exception {
      PrintDrugListResp resp = new PrintDrugListResp();
      if (applyNo != null && applyNo.size() > 0) {
         List<PrintDrugListVo> list = this.tdPaTakeDrugListMapper.selectPrintDataByIds(applyNo);

         for(PrintDrugListVo vo : list) {
            vo.setPrintName(SecurityUtils.getLoginUser().getUser().getNickName());
            vo.setDept(SecurityUtils.getLoginUser().getUser().getDept().getDeptName());
         }

         resp.setList(list);
      }

      return resp;
   }
}
