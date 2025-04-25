package com.emr.project.operation.service.impl;

import com.emr.common.utils.AgeUtil;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.PinYinUtil;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.operation.domain.PatFee;
import com.emr.project.operation.domain.Tcwh;
import com.emr.project.operation.domain.TcwhMx;
import com.emr.project.operation.domain.TmPmHsxm;
import com.emr.project.operation.domain.dto.DepartAccountDTO;
import com.emr.project.operation.domain.dto.NursingOperationFeeDTO;
import com.emr.project.operation.domain.req.CombinationDetailsSaveReq;
import com.emr.project.operation.domain.req.CombinationListReq;
import com.emr.project.operation.domain.req.CombinationSaveReq;
import com.emr.project.operation.domain.req.NurseWorkloadReq;
import com.emr.project.operation.domain.req.OperationPatientReq;
import com.emr.project.operation.domain.req.SearchDoctorListReq;
import com.emr.project.operation.domain.req.SuppPrintDataListReq;
import com.emr.project.operation.domain.req.SuppPrintDataReq;
import com.emr.project.operation.domain.resp.NurseWorkloadResp;
import com.emr.project.operation.domain.resp.OperationApplyResp;
import com.emr.project.operation.domain.resp.OperationPatientDetailsResp;
import com.emr.project.operation.mapper.DepartAccountMapper;
import com.emr.project.operation.service.ExpenseDetailService;
import com.emr.project.operation.service.HisProcService;
import com.emr.project.operation.service.ICommonOperationService;
import com.emr.project.operation.service.IDepartAccountService;
import com.emr.project.operation.service.ITmPmHsxmSerive;
import com.emr.project.operation.service.TcwhService;
import com.emr.project.pat.domain.ExpenseDetail;
import com.emr.project.pat.service.IPatFeeService;
import com.emr.project.system.domain.SysUser;
import com.google.common.collect.Maps;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DepartAccountServiceImpl implements IDepartAccountService {
   @Autowired
   private DepartAccountMapper departAccountMapper;
   @Autowired
   private ITmPmHsxmSerive tmPmHsxmSerive;
   @Autowired
   private IPatFeeService patFeeService;
   @Autowired
   private ICommonOperationService commonService;
   @Autowired
   private HisProcService hisProcService;
   @Autowired
   private TcwhService tcwhService;
   @Autowired
   private ExpenseDetailService expenseDetailService;

   public List getDepartAccountList(Map param) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      String deptCode = user.getDept().getDeptCode();
      TmPmHsxm tmPmHsxm = new TmPmHsxm();
      tmPmHsxm.setDrugFlag("0");
      List<String> threeLevelCodeList = this.tmPmHsxmSerive.getAttrList(tmPmHsxm, TmPmHsxm::getThreeLevelCode);
      Map<String, Object> map = Maps.newHashMap(param);
      map.put("list", threeLevelCodeList);
      map.put("deptCode", deptCode);
      if (StringUtils.isNotEmpty((String)map.get("startTime")) && StringUtils.isNotEmpty((String)map.get("endTime"))) {
         map.put("startTime", map.get("startTime") + " 00:00:00");
         map.put("endTime", map.get("endTime") + " 23:59:59");
      }

      List<Map<String, Object>> accountList = this.departAccountMapper.getDepartAccountList(map);
      if (accountList != null && accountList.size() > 0) {
         for(int i = 0; i < accountList.size(); ++i) {
            Map<String, Object> objectMap = (Map)accountList.get(i);
            Object standard = objectMap.get("STANDARD");
            objectMap.put("STANDARD", standard == null ? "" : standard);
            Object filingDate = objectMap.get("FILING_DATE");
            if (filingDate != null) {
               Timestamp filingDateTime = (Timestamp)filingDate;
               Instant instant = Instant.ofEpochMilli(((Timestamp)filingDate).getTime());
               LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
               String start = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
               objectMap.put("FILING_DATE", start);
            }
         }
      }

      return accountList;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public Map saveDepartAccount(DepartAccountDTO departAccountDTO, List mapList1) throws Exception {
      Map<String, Object> dataMap = new HashMap();
      String loginDeptCode = SecurityUtils.getLoginUser().getUser().getDept().getDeptCode();
      List<NursingOperationFeeDTO> nursingOperationFeeDTOList = departAccountDTO.getJzList();
      Map<String, List<NursingOperationFeeDTO>> listMap = new HashMap();

      for(NursingOperationFeeDTO nursingOperationFeeDTO : nursingOperationFeeDTOList) {
         if (listMap.containsKey(nursingOperationFeeDTO.getKdysbm() + "@" + nursingOperationFeeDTO.getZxksbm())) {
            ((List)listMap.get(nursingOperationFeeDTO.getKdysbm() + "@" + nursingOperationFeeDTO.getZxksbm())).add(nursingOperationFeeDTO);
         } else {
            List<NursingOperationFeeDTO> list = new ArrayList();
            list.add(nursingOperationFeeDTO);
            listMap.put(nursingOperationFeeDTO.getKdysbm() + "@" + nursingOperationFeeDTO.getZxksbm(), list);
         }
      }

      BigDecimal amount = BigDecimal.ZERO;

      for(String key : listMap.keySet()) {
         amount = this.patFeeService.toFee((List)listMap.get(key), "0303_2", (String)null, (List)null, departAccountDTO);
      }

      dataMap.put("amount", amount);
      dataMap.put("mapList", mapList1);
      return dataMap;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public Map submitDepartAccount(List expenseDetailAppList, List mapList1) throws Exception {
      Map<String, Object> dataMap = new HashMap();
      List<String> billsNoList = (List)expenseDetailAppList.stream().map((t) -> t.getBillsNo()).distinct().collect(Collectors.toList());
      List<String> prescriptionList = (List)expenseDetailAppList.stream().map((t) -> t.getPrescription()).distinct().collect(Collectors.toList());
      String loginDeptCode = SecurityUtils.getLoginUser().getUser().getDept().getDeptCode();
      Map<String, List<ExpenseDetail>> detailListMap = (Map)expenseDetailAppList.stream().collect(Collectors.groupingBy((t) -> t.getPrescription()));
      BigDecimal amount = BigDecimal.ZERO;

      for(String prescriptionNew : detailListMap.keySet()) {
         PatFee patFeeApp = this.patFeeService.selectFeeAppByPrescription(prescriptionNew);
         List<ExpenseDetail> detailAppList = (List)detailListMap.get(prescriptionNew);
         amount = this.patFeeService.toFee(detailAppList, "0303", (String)null, (List)null, (DepartAccountDTO)null);
         if (!loginDeptCode.equals(patFeeApp.getExecutorDptNo())) {
            String prescription = null;

            for(ExpenseDetail detail : detailAppList) {
               String prescriptionDto = detail.getPrescription();
               if (StringUtils.isNotEmpty(prescriptionDto)) {
                  prescription = prescriptionDto;
                  break;
               }
            }

            Map<String, Object> param = new HashMap();
            param.put("case_no", patFeeApp.getCaseNo());
            param.put("admission_no", patFeeApp.getAdmissionNo());
            param.put("hospitalized_count", patFeeApp.getHospitalizedCount());
            if (StringUtils.isNotBlank(patFeeApp.getBabyNo())) {
               param.put("yebz", "2");
            } else {
               param.put("yebz", "1");
            }

            param.put("type", "1");
            param.put("order_no", prescription);
            param.put("order_sort_number", "");
            param.put("filing_date", this.commonService.getDbDate());
            param.put("ward_no", SecurityUtils.getLoginUser().getUser().getDept().getDeptCode());
            param.put("operator", "");
            List<Map<String, Object>> mapList = this.patFeeService.getPrintDate(param);
            Map<String, List<Map<String, Object>>> hashMap = new HashMap();

            for(Map objectMap : mapList) {
               String admission_no = (String)objectMap.get("admission_no");
               String executor_dpt_no = (String)objectMap.get("executor_dpt_no");
               if (hashMap.containsKey(admission_no + executor_dpt_no)) {
                  ((List)hashMap.get(admission_no + executor_dpt_no)).add(objectMap);
               } else {
                  List<Map<String, Object>> list = new ArrayList();
                  list.add(objectMap);
                  hashMap.put(admission_no + executor_dpt_no, list);
               }
            }

            for(String key1 : hashMap.keySet()) {
               List<Map<String, Object>> list = (List)hashMap.get(key1);
               Map<String, List<Map<String, Object>>> map = new HashMap();
               map.put("Table", list);
               mapList1.add(map);
            }
         }
      }

      dataMap.put("amount", amount);
      dataMap.put("mapList", mapList1);
      this.expenseDetailService.delExpenseDetailApplyByBillsNo(billsNoList);
      this.delPatFeeApp(prescriptionList);
      return dataMap;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void delDepartAccount(List expenseDetailAppList) throws Exception {
      List<String> billsNoList = (List)expenseDetailAppList.stream().map((t) -> t.getBillsNo()).distinct().collect(Collectors.toList());
      this.expenseDetailService.delExpenseDetailApplyByBillsNo(billsNoList);
      List<String> prescriptionList = (List)expenseDetailAppList.stream().map((t) -> t.getPrescription()).distinct().collect(Collectors.toList());
      this.delPatFeeApp(prescriptionList);
   }

   private void delPatFeeApp(List prescriptions) throws Exception {
      List<String> prescriptionsDel = new ArrayList(1);
      List<ExpenseDetail> detailList = this.expenseDetailService.selectExpenseDetailApplyByPrescriptions(prescriptions);
      if (CollectionUtils.isNotEmpty(detailList)) {
         Map<String, List<ExpenseDetail>> detailListMap = (Map)detailList.stream().collect(Collectors.groupingBy((t) -> t.getPrescription()));

         for(String prescription : prescriptions) {
            List<ExpenseDetail> details = (List)detailListMap.get(prescription);
            if (CollectionUtils.isEmpty(details)) {
               prescriptionsDel.add(prescription);
            }
         }
      } else {
         prescriptionsDel = prescriptions;
      }

      if (CollectionUtils.isNotEmpty(prescriptionsDel)) {
         this.patFeeService.delFeeAppByPrescription(prescriptionsDel);
      }

   }

   public List getDoctorList(SearchDoctorListReq req) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      String searchStr = req.getSearchStr();
      String type = req.getType();
      String status = req.getStatus();
      String hospitalCode = user.getHospital().getOrgCode();
      String deptCode = user.getDept().getDeptCode();
      return this.departAccountMapper.getDoctorList(searchStr, hospitalCode, type, deptCode, status);
   }

   public List getDeptList(String searchStr) {
      String hospitalCode = SecurityUtils.getLoginUser().getUser().getHospital().getOrgCode();
      return this.departAccountMapper.getDeptList(searchStr, hospitalCode);
   }

   public List getNurseList(String searchStr, String status) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      String hospitalCode = user.getHospital().getOrgCode();
      String deptCode = user.getDept().getDeptCode();
      return this.departAccountMapper.getNurseList(searchStr, hospitalCode, deptCode, status);
   }

   public List getCombinationList(CombinationListReq req) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      String packagePym = req.getPackagePym();
      String shareClass = req.getShareClass();
      String hospitalCode = user.getHospital().getOrgCode();
      String userName = user.getUserName();
      String deptCode = user.getDept().getDeptCode();
      return this.departAccountMapper.getCombinationList(shareClass, packagePym, hospitalCode, userName, deptCode);
   }

   public List getCombinationDetails(String packageNo) {
      return this.departAccountMapper.getCombinationDetails(packageNo);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void saveCombinationPackage(CombinationSaveReq req) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      String packageNo = this.hisProcService.getDocumentOrBillNo(user.getUserName(), 5);
      String packageName = req.getPackageName();
      String shareClass = req.getShareClass();
      String packagePym = req.getPackagePym();
      List<CombinationDetailsSaveReq> list = req.getList();
      Tcwh tcwh = new Tcwh();
      tcwh.setHospitalCode(user.getHospital().getOrgCode());
      tcwh.setFlag("0");
      tcwh.setShareClass(shareClass);
      tcwh.setOperatorNo(user.getUserName());
      tcwh.setPackageName(packageName);
      tcwh.setPackageNo(packageNo);
      tcwh.setPackagePym(StringUtils.isEmpty(packagePym) ? PinYinUtil.getPinYinHeadChar(tcwh.getPackageName()) : packagePym);
      tcwh.setWardNo(user.getDept().getDeptCode());
      List<TcwhMx> tcwhMxList = new ArrayList();
      int i = 0;

      for(CombinationDetailsSaveReq saveReq : list) {
         TcwhMx tcwhMx = new TcwhMx();
         tcwhMx.setWardNo(user.getDept().getDeptCode());
         tcwhMx.setPackageNo(tcwh.getPackageNo());
         tcwhMx.setPackageOrder(i++);
         tcwhMx.setChargeCode(saveReq.getChargeNo());
         tcwhMx.setChargeNo(saveReq.getChargeNo());
         tcwhMx.setChargeName(saveReq.getChargeName());
         tcwhMx.setChargeNamePym(PinYinUtil.getPinYinHeadChar(saveReq.getChargeName()));
         tcwhMx.setHosUpper(saveReq.getHosUpper());
         tcwhMx.setStandard(saveReq.getStandard());
         tcwhMx.setUnit(saveReq.getUnit());
         tcwhMx.setPrice(saveReq.getPrice());
         tcwhMx.setDose(saveReq.getDose());
         tcwhMx.setTotal(saveReq.getTotal());
         tcwhMx.setDepExecNo(saveReq.getDeptCode());
         tcwhMxList.add(tcwhMx);
      }

      this.tcwhService.savePackage(tcwh, tcwhMxList);
   }

   public List getOperationApply(String admissionNo) {
      List<OperationApplyResp> list = new ArrayList();
      List<OperationApplyResp> respList = this.departAccountMapper.getOperationApply(admissionNo);
      Map<String, List<OperationApplyResp>> stringListMap = (Map)respList.stream().collect(Collectors.groupingBy(OperationApplyResp::getOperationName));

      for(String key : stringListMap.keySet()) {
         List<OperationApplyResp> resps = (List)stringListMap.get(key);
         if (resps.size() == 1) {
            list.add(resps.get(0));
         } else {
            List<OperationApplyResp> applyResps = (List)resps.stream().sorted(Comparator.comparing(OperationApplyResp::getPlanOperTime).reversed()).collect(Collectors.toList());
            list.add(applyResps.get(0));
         }
      }

      return list;
   }

   public List getOperationPatientList(OperationPatientReq req) {
      Integer type = req.getType();
      String deptCode = req.getDeptCode();
      String searchStr = req.getSearchStr();
      LocalDateTime startLocalDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
      LocalDateTime endLocalDateTime = LocalDateTime.of(LocalDate.now().plusDays(1L), LocalTime.MIN);
      String start = startLocalDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
      String end = endLocalDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
      List<OperationPatientDetailsResp> resultList = this.departAccountMapper.getOperationPatientList(type, deptCode, searchStr, start, end);
      if (!resultList.isEmpty()) {
         for(OperationPatientDetailsResp resp : resultList) {
            Long year = StringUtils.isEmpty(resp.getPersonAge()) ? 0L : Long.parseLong(resp.getPersonAge());
            String ageStr = AgeUtil.getAgeStr(year, resp.getAgeMonth(), resp.getAgeDay(), resp.getAgeHour(), resp.getAgeBranch());
            resp.setPersonAge(ageStr);
         }
      }

      return resultList;
   }

   public List getSuppPrintData(SuppPrintDataListReq req) {
      Set<String> set = new HashSet();

      for(SuppPrintDataReq objectMap : req.getReqList()) {
         set.add(objectMap.getSerialNumber());
      }

      List<Map<String, List<Map<String, Object>>>> mapList1 = new ArrayList();
      String admissionNo = req.getAdmissionNo();
      String hospitalizedCount = req.getHospitalizedCount();
      List<Map<String, Object>> mapList = new ArrayList();

      for(String serialNumber : set) {
         Map<String, Object> param = new HashMap();
         param.put("type", "1");
         param.put("order_no", serialNumber);
         param.put("admission_no", admissionNo);
         param.put("hospitalized_count", hospitalizedCount);
         List<Map<String, Object>> objectList = this.patFeeService.getPrintDate(param);
         mapList.addAll(objectList);
      }

      this.groupByExecutorDpt(mapList1, mapList, Boolean.TRUE);
      return mapList1;
   }

   public void groupByExecutorDpt(List mapList1, List mapList, Boolean flag) {
      Map<String, List<Map<String, Object>>> hashMap = new HashMap();
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

      for(Map objectMap : mapList) {
         String admission_no = (String)objectMap.get("ADMISSION_NO");
         String executor_dpt_no = (String)objectMap.get("EXECUTOR_DPT_NO");
         Date filing_date = objectMap.get("FILING_DATE") == null ? null : (Date)objectMap.get("FILING_DATE");
         Date printDate = objectMap.get("PRINTDATE") == null ? null : (Date)objectMap.get("PRINTDATE");
         if (filing_date != null) {
            objectMap.put("FILING_DATE", df.format(filing_date));
         }

         if (printDate != null) {
            objectMap.put("PRINTDATE", df.format(printDate));
         }

         if (flag) {
            objectMap.put("flag", "(补)");
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

   public Boolean selectDeptArrearsByDepCode(String patientDepCode) {
      Boolean flag = Boolean.TRUE;
      String arrearsFlag = this.departAccountMapper.selectArrearsByDeptCode(patientDepCode);
      return StringUtils.isNotBlank(arrearsFlag) && "1".equals(arrearsFlag) ? Boolean.FALSE : flag;
   }

   public List queryNurseWorkloadAllList(NurseWorkloadReq req) throws Exception {
      List<NurseWorkloadResp> resultList = new ArrayList();
      String admissionNo = req.getAdmissionNo();
      String startDate = req.getStartDate();
      String endDate = req.getEndDate();
      String nurseNo = req.getNurseNo();
      String queryType = req.getQueryType();
      String queryTypeStatis = req.getFirstTypeStatis();
      String depCode = SecurityUtils.getLoginUser().getUser().getDept().getDeptCode();
      if (StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)) {
         endDate = DateUtils.addDay((String)endDate, 1);
      }

      TmPmHsxm tmPmHsxm = new TmPmHsxm();
      tmPmHsxm.setDrugFlag(queryType);
      tmPmHsxm.setFirstTypeStatis(queryTypeStatis);
      List<String> threeLevelCodeList = this.tmPmHsxmSerive.getAttrList(tmPmHsxm, TmPmHsxm::getThreeLevelCode);
      String returnFlag = req.getReturnFlag();
      String selChargeNo = req.getSelChargeNo();
      List<NurseWorkloadResp> listAll = this.departAccountMapper.queryNurseWorkloadAllList(admissionNo, startDate, endDate, nurseNo, threeLevelCodeList, depCode, returnFlag, selChargeNo);
      Map<String, List<NurseWorkloadResp>> chargeNoListMap = (Map)listAll.stream().collect(Collectors.groupingBy(NurseWorkloadResp::getChargeNo));
      Set<String> chargeNoSet = chargeNoListMap.keySet();
      List<String> chargeNoList = new ArrayList(chargeNoSet);

      for(String chargeNo : (List)chargeNoList.stream().sorted().collect(Collectors.toList())) {
         List<NurseWorkloadResp> list = (List)chargeNoListMap.get(chargeNo);
         resultList.addAll(list);
      }

      return resultList;
   }

   public List queryNurseWorkloadByPage(NurseWorkloadReq req, List allList) throws Exception {
      List<NurseWorkloadResp> dataList = new ArrayList(1);
      if (allList != null && allList.size() > 0) {
         BigDecimal total = (BigDecimal)allList.stream().map(NurseWorkloadResp::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
         List<NurseWorkloadResp> resultList = new ArrayList(1);
         Integer pageSize = req.getPageSize();
         Integer pageNum = req.getPageNum();
         int begin = 0;
         int end = 0;
         if (pageSize != 0) {
            begin = (pageNum - 1) * pageSize;
            end = pageNum * pageSize;
            if (end > allList.size()) {
               end = allList.size();
            }
         }

         if (end == 0) {
            resultList.addAll(allList);
         } else {
            resultList = allList.subList(begin, end);
         }

         Map<String, List<NurseWorkloadResp>> chargeNoListMap = (Map)resultList.stream().collect(Collectors.groupingBy(NurseWorkloadResp::getChargeNo));
         Set<String> chargeNoSet = chargeNoListMap.keySet();
         List<String> chargeNoList = new ArrayList(chargeNoSet);

         for(String chargeNo : (List)chargeNoList.stream().sorted().collect(Collectors.toList())) {
            List<NurseWorkloadResp> list = (List)chargeNoListMap.get(chargeNo);
            BigDecimal chargeTotal = BigDecimal.ZERO;
            BigDecimal doseTotal = BigDecimal.ZERO;

            for(NurseWorkloadResp resp : list) {
               chargeTotal = chargeTotal.add(resp.getTotal());
               doseTotal = doseTotal.add(resp.getDose());
               dataList.add(resp);
            }

            NurseWorkloadResp pageResp = new NurseWorkloadResp();
            pageResp.setDose(doseTotal);
            pageResp.setTotal(chargeTotal);
            dataList.add(pageResp);
         }

         NurseWorkloadResp totalResp = new NurseWorkloadResp();
         totalResp.setTotal(total);
         dataList.add(totalResp);
      }

      return dataList;
   }
}
