package com.emr.project.operation.service.impl;

import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.bean.BeanUtils;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.common.mapper.CommonMapper;
import com.emr.project.operation.domain.ApplyForm;
import com.emr.project.operation.domain.PatFee;
import com.emr.project.operation.domain.Tfsqb;
import com.emr.project.operation.domain.TmPmHsxm;
import com.emr.project.operation.domain.req.RefundAccountDetailReq;
import com.emr.project.operation.domain.req.RefundAccountListReq;
import com.emr.project.operation.domain.req.RefundAccountReq;
import com.emr.project.operation.domain.req.RefundApplyReq;
import com.emr.project.operation.domain.req.RefundBatchToVoidDetailsReq;
import com.emr.project.operation.domain.req.RefundBatchToVoidReq;
import com.emr.project.operation.domain.resp.RefundAccountListResp;
import com.emr.project.operation.domain.resp.RefundApplyResp;
import com.emr.project.operation.domain.vo.PrintRefundAccountVo;
import com.emr.project.operation.mapper.ApplyFormMapper;
import com.emr.project.operation.mapper.DepartAccountMapper;
import com.emr.project.operation.mapper.ExpenseDetailMapper;
import com.emr.project.operation.mapper.RefundAccountMapper;
import com.emr.project.operation.mapper.TfsqbMapper;
import com.emr.project.operation.service.HisProcService;
import com.emr.project.operation.service.IRefundAccountService;
import com.emr.project.operation.service.ITmPmHsxmSerive;
import com.emr.project.pat.domain.ExpenseDetail;
import com.emr.project.pat.mapper.PatFeeMapper;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysEmrConfigService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RefundAccountServiceImpl implements IRefundAccountService {
   @Autowired
   private RefundAccountMapper refundAccountMapper;
   @Autowired
   private ITmPmHsxmSerive tmPmHsxmSerive;
   @Autowired
   private ApplyFormMapper applyFormMapper;
   @Autowired
   private DepartAccountMapper departAccountMapper;
   @Autowired
   private HisProcService hisProcService;
   @Autowired
   private TfsqbMapper tfsqbMapper;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private PatFeeMapper patFeeMapper;
   @Autowired
   private ExpenseDetailMapper expenseDetailMapper;
   @Autowired
   private CommonMapper commonMapper;

   public List getRefundList(RefundAccountListReq req) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      String deptCode = user.getDept().getDeptCode();
      String admissionNo = req.getAdmissionNo();
      String chargeName = req.getChargeName();
      String threeLevelCode = req.getThreeLevelCode();
      String startTime = req.getStartTime();
      if (StringUtils.isNotEmpty(startTime)) {
         startTime = startTime + " 00:00:00";
      }

      String endTime = req.getEndTime();
      if (StringUtils.isNotEmpty(endTime)) {
         endTime = endTime + " 23:59:59";
      }

      TmPmHsxm tmPmHsxm = new TmPmHsxm();
      tmPmHsxm.setDrugFlag("0");
      List<TmPmHsxm> threeLevelList = this.tmPmHsxmSerive.selectByConn(tmPmHsxm);
      List<String> threeLevelCodeList = CollectionUtils.isNotEmpty(threeLevelList) ? (List)threeLevelList.stream().map((t) -> t.getThreeLevelCode()).collect(Collectors.toList()) : null;
      List<String> materiallist = null;
      String config0071 = this.sysEmrConfigService.selectSysEmrConfigByKey("0071");
      if (StringUtils.isNotBlank(config0071) && config0071.equals("1")) {
         threeLevelCodeList = CollectionUtils.isNotEmpty(threeLevelList) ? (List)threeLevelList.stream().filter((t) -> !t.getFirstCode().equals("3")).map((t) -> t.getThreeLevelCode()).collect(Collectors.toList()) : null;
         materiallist = CollectionUtils.isNotEmpty(threeLevelList) ? (List)threeLevelList.stream().filter((t) -> t.getFirstCode().equals("3")).map((t) -> t.getThreeLevelCode()).collect(Collectors.toList()) : null;
      }

      List<RefundAccountListResp> dataList = this.refundAccountMapper.getRefundList(admissionNo, chargeName, threeLevelCode, startTime, endTime, deptCode, threeLevelCodeList, materiallist);
      List<RefundAccountListResp> returnList = new ArrayList();

      for(RefundAccountListResp resp : dataList) {
         String applyCount = resp.getRefundCount();
         resp.setTotal(StringUtils.isNotEmpty(resp.getTotal()) ? (new BigDecimal(resp.getTotal())).stripTrailingZeros().toPlainString() : "0");
         resp.setPrice(StringUtils.isNotEmpty(resp.getPrice()) ? (new BigDecimal(resp.getPrice())).stripTrailingZeros().toPlainString() : "0");
         if (StringUtils.isNotEmpty(applyCount)) {
            BigDecimal apply = new BigDecimal(applyCount);
            if (apply.compareTo(BigDecimal.ZERO) > 0) {
               returnList.add(resp);
            }
         }
      }

      return returnList;
   }

   public AjaxResult checkRefundNum(AjaxResult resultMsg, List list) {
      if (list != null && list.size() > 0) {
         for(int i = 0; i < list.size(); ++i) {
            RefundAccountDetailReq detailReq = (RefundAccountDetailReq)list.get(i);
            if (StringUtils.isNotBlank(detailReq.getCopeNo()) && ("02".equals(detailReq.getCopeClass()) || "03".equals(detailReq.getCopeClass()))) {
               ApplyForm applyForm = this.applyFormMapper.searchApplyForm(detailReq.getCopeNo(), detailReq.getCopeSortNumber());
               if ("8".equals(applyForm.getApplyFormStatus())) {
                  resultMsg = AjaxResult.error("已上机，不允许退费");
                  return resultMsg;
               }

               if ("9".equals(applyForm.getApplyFormStatus())) {
                  resultMsg = AjaxResult.error("报告已完成，不允许退费");
                  return resultMsg;
               }
            }

            if (!StringUtils.isNotBlank(detailReq.getBillsNo()) || !StringUtils.isNotBlank(detailReq.getRefundCount()) || !StringUtils.isNotBlank(detailReq.getDose())) {
               resultMsg = AjaxResult.error(this.getMsg(list.size(), i));
               return resultMsg;
            }

            Double checkRefundNum = this.departAccountMapper.checkRefundNum(detailReq.getBillsNo());
            if (checkRefundNum != null && checkRefundNum < (double)0.0F) {
               if (!(Double.parseDouble(detailReq.getRefundCount()) - checkRefundNum <= Double.parseDouble(detailReq.getDose()))) {
                  resultMsg = AjaxResult.error(this.getMsg(list.size(), i));
                  return resultMsg;
               }
            } else if (!(Double.parseDouble(detailReq.getRefundCount()) <= Double.parseDouble(detailReq.getDose()))) {
               resultMsg = AjaxResult.error(this.getMsg(list.size(), i));
               return resultMsg;
            }
         }
      }

      return resultMsg;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public List saveRefundAccount(RefundAccountReq req) throws Exception {
      List<PrintRefundAccountVo> printRefundAccount = new ArrayList();
      SysUser user = SecurityUtils.getLoginUser().getUser();
      List<RefundAccountDetailReq> list = req.getList();
      String bills_no = this.hisProcService.getDocumentOrBillNo(user.getUserName(), 2);
      List<Tfsqb> tfsqbList = new ArrayList();
      List<RefundAccountDetailReq> jsonObjects = new ArrayList();

      for(int i = 0; i < list.size(); ++i) {
         RefundAccountDetailReq detailReq = (RefundAccountDetailReq)list.get(i);
         Tfsqb tfsqb = this.getTfsqb(detailReq, req);
         tfsqb.setSerialNumber(bills_no);
         tfsqb.setSerialNumberXh(i + 1 + "");
         if (StringUtils.isNotBlank(detailReq.getCopeNo()) && ("02".equals(detailReq.getCopeClass()) || "03".equals(detailReq.getCopeClass()))) {
            ApplyForm applyForm = this.applyFormMapper.searchApplyForm(detailReq.getCopeNo(), detailReq.getCopeSortNumber());
            detailReq.setApplyFormNo(applyForm.getApplyFormNo());
            jsonObjects.add(detailReq);
            tfsqb.setPerformListNo(applyForm.getApplyFormNo());
         }

         tfsqbList.add(tfsqb);
      }

      this.tfsqbMapper.insertBatch(tfsqbList);
      this.doReturnFee(tfsqbList);
      if (req.getPrintFlag() != null && req.getPrintFlag().equals("1")) {
         List<String> billNoList = (List)tfsqbList.stream().map((t) -> t.getSerialNumber() + t.getSerialNumberXh()).distinct().collect(Collectors.toList());
         printRefundAccount = this.printRefundAccount(billNoList);
      }

      if (jsonObjects.size() > 0) {
         Set<String> set = new HashSet();
         List<RefundAccountDetailReq> jsonObjectList = new ArrayList();

         for(RefundAccountDetailReq jsonObject : jsonObjects) {
            if (!set.contains(jsonObject.getCopeNo() + jsonObject.getCopeSortNumber())) {
               jsonObjectList.add(jsonObject);
               set.add(jsonObject.getCopeNo() + jsonObject.getCopeSortNumber());
            }
         }

         for(RefundAccountDetailReq jsonObject : jsonObjectList) {
            this.applyFormMapper.updateStatusByOrderNoAndOrderSortNumber(jsonObject.getCopeNo(), jsonObject.getCopeSortNumber(), "5");
            this.applyFormMapper.updateItemStatusByOrderNoAndOrderSortNumber(jsonObject.getApplyFormNo(), jsonObject.getCopeGroup(), jsonObject.getCopeSortNumber(), "5");
         }
      }

      return printRefundAccount;
   }

   public void doReturnFee(List tfsqbList) throws Exception {
      String val0121 = this.sysEmrConfigService.selectSysEmrConfigByKey("0121");
      tfsqbList = CollectionUtils.isNotEmpty(tfsqbList) ? (List)tfsqbList.stream().filter((t) -> t.getMark().equals("0")).collect(Collectors.toList()) : null;
      if (StringUtils.isNotBlank(val0121) && val0121.equals("2") && CollectionUtils.isNotEmpty(tfsqbList)) {
         SysUser user = SecurityUtils.getLoginUser().getUser();
         List<String> billsNoOldList = (List)tfsqbList.stream().map((t) -> t.getBillsNoOld()).collect(Collectors.toList());
         List<ExpenseDetail> detailList = CollectionUtils.isNotEmpty(billsNoOldList) ? this.expenseDetailMapper.selectFeeList(billsNoOldList) : null;
         List<String> prescriptionList = CollectionUtils.isNotEmpty(detailList) ? (List)detailList.stream().map((t) -> t.getPrescription()).distinct().collect(Collectors.toList()) : null;
         List<PatFee> patFeeList = CollectionUtils.isNotEmpty(prescriptionList) ? this.patFeeMapper.selectByPrescriptionList(user.getHospital().getOrgCode(), prescriptionList) : null;
         Map<String, PatFee> patFeeMap = CollectionUtils.isNotEmpty(patFeeList) ? (Map)patFeeList.stream().collect(Collectors.toMap((t) -> t.getPrescription(), Function.identity())) : null;
         Map<String, ExpenseDetail> detailMap = CollectionUtils.isNotEmpty(detailList) ? (Map)detailList.stream().collect(Collectors.toMap((t) -> t.getBillsNo(), Function.identity())) : null;
         List<PatFee> patFeeReturnList = new ArrayList(1);
         List<ExpenseDetail> detailReturnList = new ArrayList(1);

         for(Tfsqb tfsqb : tfsqbList) {
            ExpenseDetail detailOld = (ExpenseDetail)detailMap.get(tfsqb.getBillsNoOld());
            PatFee patFeeOld = (PatFee)patFeeMap.get(detailOld.getPrescription());
            PatFee patFeeReturn = new PatFee();
            BeanUtils.copyProperties(patFeeOld, patFeeReturn);
            String prescription = this.hisProcService.getDocumentOrBillNo(user.getUserName(), 4);
            patFeeReturn.setPrescription("tf" + prescription + "shs");
            Date filingDate = DateUtils.parseDate(this.commonMapper.getSysdate(), new String[]{"yyyy-MM-dd HH:mm:ss.S"});
            patFeeReturn.setFilingDate(filingDate);
            ExpenseDetail detailReturn = new ExpenseDetail();
            BeanUtils.copyProperties(detailOld, detailReturn);
            detailReturn.setPrescription(patFeeReturn.getPrescription());
            detailReturn.setBillsNo(patFeeReturn.getPrescription() + "01");
            detailReturn.setBillsNoOld(detailOld.getBillsNo());
            detailReturn.setPrice(tfsqb.getPrice());
            detailReturn.setDose(tfsqb.getDose());
            detailReturn.setTotal(tfsqb.getTotal());
            detailReturn.setFilingDate(patFeeReturn.getFilingDate());
            detailReturn.setValidity((Date)null);
            detailReturn.setStatisticsDate((Date)null);
            detailReturnList.add(detailReturn);
            patFeeReturnList.add(patFeeReturn);
            tfsqb.setOperatorNewNo(user.getUserName());
            tfsqb.setOperatorNewCode(user.getUserName());
            tfsqb.setOperatorNewName(user.getNickName());
            tfsqb.setOperatorNewDate(patFeeReturn.getFilingDate());
            tfsqb.setBillsNoNew(detailReturn.getBillsNo());
         }

         if (CollectionUtils.isNotEmpty(patFeeReturnList)) {
            this.patFeeMapper.insertBatch(patFeeReturnList);
         }

         if (CollectionUtils.isNotEmpty(detailReturnList)) {
            this.expenseDetailMapper.insertBatch(detailReturnList);
         }

         if (CollectionUtils.isNotEmpty(tfsqbList)) {
            this.tfsqbMapper.updateRefundToFeeBatch(tfsqbList);
         }
      }

   }

   public List printRefundAccount(List billNoList) throws Exception {
      List<PrintRefundAccountVo> list = this.departAccountMapper.selectPrintRefundAccount(billNoList, SecurityUtils.getLoginUser().getUser().getDept().getDeptCode());
      if (list != null && list.size() > 0) {
         for(PrintRefundAccountVo vo : list) {
            vo.setDeptName(SecurityUtils.getLoginUser().getUser().getDept().getDeptName());
            vo.setStaffName(SecurityUtils.getLoginUser().getUser().getNickName());
            vo.setOrgName(SecurityUtils.getLoginUser().getUser().getHospital().getOrgName());
            vo.setTotal(vo.getTotal() == null ? BigDecimal.ZERO : vo.getTotal().negate());
         }
      }

      return list;
   }

   public List getRefundApplyList(RefundApplyReq req) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      String deptCode = user.getDept().getDeptCode();
      String admissionNo = req.getAdmissionNo();
      String startTime = req.getStartTime();
      if (StringUtils.isNotEmpty(startTime)) {
         startTime = startTime + " 00:00:00";
      }

      String endTime = req.getEndTime();
      if (StringUtils.isNotEmpty(endTime)) {
         endTime = endTime + " 23:59:59";
      }

      List<RefundApplyResp> applyList = this.refundAccountMapper.getRefundApplyList(admissionNo, startTime, endTime, deptCode);

      for(RefundApplyResp resp : applyList) {
         resp.setTotal(StringUtils.isNotEmpty(resp.getTotal()) ? (new BigDecimal(resp.getTotal())).stripTrailingZeros().toPlainString() : "0");
         resp.setPrice(StringUtils.isNotEmpty(resp.getPrice()) ? (new BigDecimal(resp.getPrice())).stripTrailingZeros().toPlainString() : "0");
      }

      return applyList;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void updateRefundBatchToVoid(RefundBatchToVoidReq req) {
      String admissionNo = req.getAdmissionNo();
      List<Tfsqb> kssqjzList = new ArrayList();
      List<RefundBatchToVoidDetailsReq> jsonObjects = new ArrayList();

      for(RefundBatchToVoidDetailsReq detailsReq : req.getList()) {
         String mark = detailsReq.getMark();
         if (StringUtils.isNotEmpty(mark) && mark.equals("0")) {
            Tfsqb tfsqb = new Tfsqb();
            tfsqb.setAdmissionNo(detailsReq.getAdmissionNo());
            tfsqb.setHospitalizedCount(Integer.valueOf(detailsReq.getHospitalizedCount()));
            tfsqb.setSerialNumber(detailsReq.getSerialNumber());
            tfsqb.setSerialNumberXh(detailsReq.getSerialNumberXh());
            kssqjzList.add(tfsqb);
            if (StringUtils.isNotBlank(detailsReq.getCaseNo()) && ("02".equals(detailsReq.getCopeClass()) || "03".equals(detailsReq.getCopeClass()))) {
               jsonObjects.add(detailsReq);
            }
         }
      }

      this.tfsqbMapper.updateRefundBatch9(kssqjzList);
      if (jsonObjects.size() > 0) {
         Set<String> set = new HashSet();
         List<RefundBatchToVoidDetailsReq> jsonObjectList = new ArrayList();

         for(RefundBatchToVoidDetailsReq jsonObject : jsonObjects) {
            if (!set.contains(jsonObject.getCaseNo() + jsonObject.getCopeSortNumber())) {
               jsonObjectList.add(jsonObject);
               set.add(jsonObject.getCopeNo() + jsonObject.getCopeSortNumber());
            }
         }

         for(RefundBatchToVoidDetailsReq jsonObject : jsonObjectList) {
            ApplyForm applyForm = this.applyFormMapper.searchApplyForm(jsonObject.getCopeNo(), jsonObject.getCopeSortNumber());
            this.applyFormMapper.updateStatusByOrderNoAndOrderSortNumber(jsonObject.getCopeNo(), jsonObject.getCopeSortNumber(), "31");
            this.applyFormMapper.updateItemStatusByOrderNoAndOrderSortNumber(applyForm.getApplyFormNo(), jsonObject.getCopeGroup(), jsonObject.getCopeSortNumber(), "31");
            this.applyFormMapper.updatesqd(jsonObject.getAdmissionNo(), applyForm.getApplyFormNo(), "3");
         }
      }

   }

   private String getMsg(int size, int num) {
      String msg = "第" + (num + 1) + "行，申请退费数量有误！请重新刷新记账项目信息后，再申请退费！";
      return size == 1 ? "申请退费数量有误！请重新刷新记账项目信息后，再申请退费！" : msg;
   }

   private Tfsqb getTfsqb(RefundAccountDetailReq detailReq, RefundAccountReq req) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      Tfsqb record = new Tfsqb();
      record.setHospitalCode(user.getHospital().getOrgCode());
      record.setCaseNo(req.getCaseNo());
      record.setAdmissionNo(req.getAdmissionNo());
      record.setHospitalizedCount(Integer.parseInt(req.getHospitalizedCount()));
      record.setPatientName(req.getName());
      record.setExpenseCategoryNo(req.getExpenseCategoryCode());
      record.setResidentCode(req.getStaffNo());
      record.setResidentNo(req.getStaffNo());
      record.setResidentName(req.getStaffName());
      record.setWardNo(req.getDeptCode());
      record.setWardName(req.getDeptName());
      record.setProjectNo(detailReq.getProjectNo());
      record.setProjectName(detailReq.getProjectName());
      record.setStandardCode(detailReq.getChargeCode());
      record.setStandardName(detailReq.getChargeName());
      record.setChargeCode(detailReq.getChargeCode());
      record.setChargeNo(detailReq.getChargeCode());
      record.setChargeName(detailReq.getChargeName());
      record.setThreeLevelAccounting(detailReq.getThreeLevelAccounting());
      record.setThreeLevelName(detailReq.getThreeLevelName());
      record.setStandard(detailReq.getStandard());
      record.setUnit(detailReq.getUnit());
      record.setPrice(new BigDecimal(detailReq.getPrice()));
      record.setDose((new BigDecimal(detailReq.getRefundCount())).multiply(new BigDecimal("-1")));
      record.setTotal((new BigDecimal(detailReq.getRefundAmount())).multiply(new BigDecimal("-1")));
      record.setOperatorCode(user.getUserName());
      record.setOperatorNo(user.getUserName());
      record.setOperatorName(user.getNickName());
      record.setBillsNoOld(detailReq.getBillsNo());
      record.setOrderNo(detailReq.getCopeNo());
      record.setOrderType(detailReq.getCopeType());
      record.setOrderSortNumber(detailReq.getCopeSortNumber());
      record.setItemSortNumber(detailReq.getCopeSortNumber());
      record.setBabyNo((String)null);
      record.setMark("0");
      record.setApplyWardNo(user.getDept().getDeptCode());
      record.setApplyWardName(user.getDept().getDeptName());
      record.setApplyNo(user.getDept().getDeptCode());
      record.setApplyName(user.getDept().getDeptName());
      return record;
   }

   public Tfsqb queryByKey(String orgCd, String admissionNo, String serialNnumber, String serialNumberXh) throws Exception {
      return StringUtils.isNotBlank(orgCd) && StringUtils.isNotBlank(admissionNo) && StringUtils.isNotBlank(serialNnumber) && StringUtils.isNotBlank(serialNumberXh) ? this.tfsqbMapper.selectByKey(orgCd, admissionNo, serialNnumber, serialNumberXh) : null;
   }
}
