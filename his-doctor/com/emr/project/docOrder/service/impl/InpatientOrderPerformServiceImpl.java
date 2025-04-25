package com.emr.project.docOrder.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.domain.TdPaOrderOperationLog;
import com.emr.project.docOrder.domain.TdPaOrderPerform;
import com.emr.project.docOrder.domain.TdPaOrderPerformDetail;
import com.emr.project.docOrder.domain.TdPaOrderStatus;
import com.emr.project.docOrder.domain.vo.AllergyRecordDTO;
import com.emr.project.docOrder.domain.vo.InpatientOrderPerformDTO;
import com.emr.project.docOrder.domain.vo.InpatientOrderPerformVo;
import com.emr.project.docOrder.domain.vo.InpatientOrderUsageFeeVo;
import com.emr.project.docOrder.domain.vo.OrderDoHandleUpVo;
import com.emr.project.docOrder.domain.vo.TdPaOrderItemVo;
import com.emr.project.docOrder.mapper.InpatientOrderPerformMapper;
import com.emr.project.docOrder.mapper.TdPaOrderPerformDetailMapper;
import com.emr.project.docOrder.mapper.TdPaOrderPerformMapper;
import com.emr.project.docOrder.service.IInpatientOrderPerformService;
import com.emr.project.docOrder.service.IInpatientSuspendOrderService;
import com.emr.project.docOrder.service.ITdPaOrderItemService;
import com.emr.project.docOrder.service.ITdPaOrderOperationLogService;
import com.emr.project.docOrder.service.ITdPaOrderStatusService;
import com.emr.project.docOrder.service.InpatientOrderUsageFeeService;
import com.emr.project.operation.domain.Medicalinformation;
import com.emr.project.operation.domain.dto.DepartAccountDTO;
import com.emr.project.operation.service.ICommonOperationService;
import com.emr.project.pat.domain.Visitinfo;
import com.emr.project.pat.service.IPatFeeService;
import com.emr.project.system.domain.SysUser;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InpatientOrderPerformServiceImpl implements IInpatientOrderPerformService {
   private final Logger log = LoggerFactory.getLogger(InpatientOrderPerformServiceImpl.class);
   @Autowired
   private InpatientOrderPerformMapper inpatientOrderPerformMapper;
   @Autowired
   private IPatFeeService patFeeService;
   @Autowired
   private TdPaOrderPerformMapper tdPaOrderPerformMapper;
   @Autowired
   private TdPaOrderPerformDetailMapper tdPaOrderPerformDetailMapper;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private ITdPaOrderStatusService tdPaOrderStatusService;
   @Autowired
   private ITdPaOrderOperationLogService tdPaOrderOperationLogService;
   @Autowired
   private ITdPaOrderItemService tdPaOrderItemService;
   @Autowired
   private IInpatientSuspendOrderService inpatientSuspendOrderService;
   @Autowired
   private InpatientOrderUsageFeeService inpatientOrderUsageFeeService;
   @Autowired
   private ICommonOperationService commonOperationService;

   public List selectInpatientOrderPerform(InpatientOrderPerformVo inpatientOrderPerformVo) throws Exception {
      return this.inpatientOrderPerformMapper.selectInpatientOrderPerform(inpatientOrderPerformVo);
   }

   public List selectSkinTestResultByPatient(String admissionNo) throws Exception {
      return this.inpatientOrderPerformMapper.selectSkinTestResultByPatient(admissionNo);
   }

   public List selectInpatientOrderPerformPageData(InpatientOrderPerformVo inpatientOrderPerformVo, List listAll, Integer pageSize, Integer pageNum) throws Exception {
      List<InpatientOrderPerformDTO> listPage = new ArrayList(1);
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
         if (begin != 0) {
            InpatientOrderPerformDTO inpatientOrderPerformDTO = (InpatientOrderPerformDTO)listAll.get(begin);

            for(int i = begin - 1; i > 0; --i) {
               InpatientOrderPerformDTO temp = (InpatientOrderPerformDTO)listAll.get(i);
               if (!inpatientOrderPerformDTO.getPerformListNo().equals(temp.getPerformListNo()) || !inpatientOrderPerformDTO.getOrderNo().equals(temp.getOrderNo()) || !inpatientOrderPerformDTO.getOrderGroupNo().equals(temp.getOrderGroupNo())) {
                  break;
               }

               listPage.add(0, temp);
            }
         }

         if (end != listAll.size()) {
            InpatientOrderPerformDTO inpatientOrderPerformDTO = (InpatientOrderPerformDTO)listAll.get(end);

            for(int i = end - 1; i >= begin; --i) {
               InpatientOrderPerformDTO temp = (InpatientOrderPerformDTO)listAll.get(i);
               if (!inpatientOrderPerformDTO.getPerformListNo().equals(temp.getPerformListNo()) || inpatientOrderPerformDTO.getPerformListSortNumber() != temp.getPerformListSortNumber() || !inpatientOrderPerformDTO.getOrderNo().equals(temp.getOrderNo()) || !inpatientOrderPerformDTO.getOrderGroupNo().equals(temp.getOrderGroupNo())) {
                  break;
               }

               try {
                  this.log.info("===============" + listPage.size() + "===============");
                  listPage.remove(listPage.size() - 1);
               } catch (Exception e) {
                  this.log.error("performListNo:  " + temp.getPerformListNo() + "  OrderNo:" + temp.getOrderNo() + "  OrderGroupNo:" + temp.getOrderGroupNo(), e);
               }
            }
         }
      }

      Map<String, String> patCrbzMap = new HashMap(1);
      List<InpatientOrderUsageFeeVo> usageFeeList = this.inpatientOrderUsageFeeService.selectOrderUsageFeeVoAll();
      Map<String, List<InpatientOrderUsageFeeVo>> usageFeeMap = (Map)usageFeeList.stream().collect(Collectors.groupingBy((t) -> t.getYongfaBh()));
      Map<String, List<InpatientOrderPerformDTO>> inpatientOrderGroupMap = (Map)listPage.stream().collect(Collectors.groupingBy((t) -> t.getPerformListNo() + t.getPerformListSortNumber() + t.getOrderGroupNo()));

      for(int i = 0; i < listPage.size(); ++i) {
         boolean usageFeeFlag = false;
         InpatientOrderPerformDTO temp = (InpatientOrderPerformDTO)listPage.get(i);
         List<InpatientOrderPerformDTO> tempList = (List)inpatientOrderGroupMap.get(temp.getPerformListNo() + temp.getPerformListSortNumber() + temp.getOrderGroupNo());
         Integer currIndex = tempList != null ? tempList.indexOf(temp) : null;
         if (tempList.size() > 1) {
            if (currIndex == 0 && temp.getOrderGroupSortNumber().equals("01")) {
               usageFeeFlag = true;
               temp.setOrderGroupStr("┓");
            } else if (currIndex + 1 == tempList.size()) {
               temp.setOrderGroupStr("┛");
            } else {
               temp.setOrderGroupStr("┃");
            }
         } else {
            usageFeeFlag = true;
         }

         if (usageFeeFlag) {
            String admissionNo = StringUtils.isNotBlank(temp.getBabyAdmissionNo()) ? temp.getBabyAdmissionNo() : temp.getAdmissionNo();
            String crbz = (String)patCrbzMap.get(admissionNo);
            if (StringUtils.isBlank(crbz)) {
               if (StringUtils.isNotBlank(temp.getBabyAdmissionNo())) {
                  crbz = "0";
               } else {
                  Medicalinformation m = new Medicalinformation();
                  m.setAdmissionNo(admissionNo);
                  crbz = this.commonOperationService.getCrbz(m);
               }

               patCrbzMap.put(admissionNo, crbz);
            }

            List<InpatientOrderUsageFeeVo> usageFeeListTemp = (List)usageFeeMap.get(temp.getOrderUsageWayNmae());
            if (StringUtils.isNotBlank(crbz) && CollectionUtils.isNotEmpty(usageFeeListTemp)) {
               usageFeeListTemp = (List)usageFeeListTemp.stream().filter((t) -> t.getCrbz().equals(crbz)).collect(Collectors.toList());
            }

            temp.setOrderUsageFeeList(usageFeeListTemp);
         }
      }

      return listPage;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void updateInpatientOrderPerform(List inPerform, AllergyRecordDTO allergyRecord, Visitinfo visitinfo, List printList, List handleUplist) throws Exception {
      InpatientOrderPerformDTO dto = (InpatientOrderPerformDTO)inPerform.get(0);
      String performListNo = dto.getPerformListNo();
      int performListSortNumber = dto.getPerformListSortNumber();
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      Date currDate = dto.getPerformTime() == null ? this.commonService.getDbSysdate() : dto.getPerformTime();
      String operatorCode = sysUser.getUserName();
      String operatorNo = sysUser.getUserName();
      String operatorName = sysUser.getNickName();
      if (allergyRecord != null) {
         operatorCode = allergyRecord.getUserCode();
         operatorNo = allergyRecord.getUserCode();
      }

      int priceFlag = 0;
      if (StringUtils.isNotBlank(dto.getPriceFlag())) {
         priceFlag = Integer.valueOf(dto.getPriceFlag());
      }

      boolean accountFlag = false;
      String status = "1";
      boolean arrea1 = false;
      String orderClassCode = dto.getOrderClassCode();
      if (priceFlag == 0) {
         accountFlag = true;
      } else {
         switch (orderClassCode) {
            case "01":
               new BigDecimal("0");
               BigDecimal operationType;
               if (String.valueOf(dto.getHerbalFlag()).equals("1")) {
                  operationType = this.patFeeService.toFee(inPerform, "0205_2", (String)null, (List)null, (DepartAccountDTO)null);
               } else {
                  operationType = this.patFeeService.toFee(inPerform, "0205", (String)null, (List)null, (DepartAccountDTO)null);
               }

               if (operationType.compareTo(BigDecimal.ZERO) == -1) {
                  arrea1 = true;
                  status = "4";
               } else {
                  accountFlag = true;
               }
               break;
            default:
               if (dto.getOrderType().equals("1") && dto.getOrderItemType().equals("3")) {
                  accountFlag = true;
               } else {
                  BigDecimal qf = this.patFeeService.toFee(inPerform, "0207", (String)null, (List)null, (DepartAccountDTO)null);
                  if (qf.compareTo(BigDecimal.ZERO) == -1) {
                     arrea1 = true;
                     status = "4";
                  } else {
                     accountFlag = true;
                  }
               }
         }
      }

      this.updatePerformStatus(performListNo, performListSortNumber, status, operatorCode, operatorNo, currDate);
      if (arrea1) {
         OrderDoHandleUpVo handleUpVo = new OrderDoHandleUpVo("欠费");
         handleUplist.add(handleUpVo);
      }

      if (accountFlag) {
         List<TdPaOrderStatus> orderStatusList = new ArrayList(1);
         List<TdPaOrderOperationLog> orderOperationLogList = new ArrayList(1);
         String operationType = dto.getOrderType().equals("1") ? String.valueOf(3) : String.valueOf(8);
         String operationName = dto.getOrderType().equals("1") ? "在执行" : "已执行";
         TdPaOrderStatus handleStatus = this.tdPaOrderStatusService.getTdPaOrderStatus(visitinfo, dto.getOrderNo(), dto.getOrderGroupNo(), dto.getOrderSortNumber(), orderClassCode, operationType, operationName, currDate);
         orderStatusList.add(handleStatus);
         TdPaOrderOperationLog handleOperationLog = this.tdPaOrderOperationLogService.getInpatientOrderOperationLog(dto.getOrderNo(), dto.getOrderSortNumber(), dto.getOrderGroupNo(), Integer.valueOf(operationType), operationName, (String)null, sysUser.getUserName(), sysUser.getUserName(), sysUser.getNickName());
         orderOperationLogList.add(handleOperationLog);
         if (dto.getOrderType().equals("1")) {
            if (dto.getOrder_status().equals("2")) {
               TdPaOrderItemVo upParam = new TdPaOrderItemVo();
               upParam.setOrderNoList(Arrays.asList(dto.getOrderNo()));
               upParam.setOrderExecuteDoc(sysUser.getUserName());
               upParam.setOrderExecuteDocName(sysUser.getNickName());
               upParam.setOrderExecuteTime(currDate);
               upParam.setOrderStatus("3");
               this.tdPaOrderItemService.updateOrderStatus(upParam);
            }
         } else {
            TdPaOrderItemVo upParam = new TdPaOrderItemVo();
            upParam.setOrderNoList(Arrays.asList(dto.getOrderNo()));
            upParam.setOrderExecuteDoc(sysUser.getUserName());
            upParam.setOrderExecuteDocName(sysUser.getNickName());
            upParam.setOrderExecuteTime(currDate);
            upParam.setOrderStatus("8");
            this.tdPaOrderItemService.updateOrderStatus(upParam);
         }

         if (CollectionUtils.isNotEmpty(orderStatusList)) {
            this.tdPaOrderStatusService.insertTdPaOrderStatusList(orderStatusList);
         }

         if (CollectionUtils.isNotEmpty(orderOperationLogList)) {
            this.tdPaOrderOperationLogService.insertTdPaOrderOperationLogList(orderOperationLogList);
         }

         if (!dto.getOrderItemType().equals("3") && !sysUser.getDept().getDeptCode().equals(dto.getDetailPerformWardNo()) && "01".equals(dto.getOrderClassCode()) && "1".equals(dto.getHerbalFlag()) && priceFlag == 1) {
            new HashMap();
            Map<String, Object> param = new HashMap();
            param.put("case_no", dto.getCaseNo());
            param.put("admission_no", dto.getAdmissionNo());
            param.put("hospitalized_count", dto.getHospitalizedCount());
            if (StringUtils.isNotBlank(dto.getBabyAdmissionNo())) {
               param.put("yebz", "2");
            } else {
               param.put("yebz", "1");
            }

            param.put("type", "0");
            param.put("order_no", dto.getOrderNo());
            param.put("order_sort_number", dto.getOrderSortNumber());
            param.put("filing_date", currDate);
            param.put("ward_no", sysUser.getDept().getDeptCode());
            param.put("operator", "");
            List<Map<String, Object>> list = this.patFeeService.getPrintDate(param);
            printList.addAll(list);
         }
      }

      if (allergyRecord != null) {
         this.inpatientSuspendOrderService.updateSkinTestResults(allergyRecord);
      }

   }

   public void updatePerformStatus(String performListNo, Integer performListSortNumber, String performListStatus, String operatorCode, String operatorNo, Date currDate) throws Exception {
      TdPaOrderPerform param = new TdPaOrderPerform();
      param.setPerformListNo(performListNo);
      param.setPerformListSortNumber(performListSortNumber);
      param.setPerformListStatus(performListStatus);
      param.setPerformTime(currDate);
      param.setPerformNurseCode(operatorCode);
      param.setPerformNurseNo(operatorNo);
      this.tdPaOrderPerformMapper.updateTdPaOrderPerform(param);
   }

   public void updatePerformDetailStatus(String performListNo, Integer performListSortNumber, String performDetailStatus) throws Exception {
      TdPaOrderPerformDetail param = new TdPaOrderPerformDetail();
      param.setPerformListNo(performListNo);
      param.setPerformListSortNumber(performListSortNumber.toString());
      param.setUpStatus(performDetailStatus);
      this.tdPaOrderPerformDetailMapper.updateTdPaOrderPerformDetail(param);
   }
}
