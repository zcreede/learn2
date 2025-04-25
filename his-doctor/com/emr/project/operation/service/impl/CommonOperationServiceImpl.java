package com.emr.project.operation.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.ToggleCaseUtils;
import com.emr.project.operation.domain.Baseinfomation;
import com.emr.project.operation.domain.CumulativeCost;
import com.emr.project.operation.domain.Medicalinformation;
import com.emr.project.operation.domain.dto.GuaranteeDTO;
import com.emr.project.operation.mapper.BaseinfomationMapper;
import com.emr.project.operation.mapper.CommonOperationMapper;
import com.emr.project.operation.mapper.MedicalinformationMapper;
import com.emr.project.operation.service.ICommonOperationService;
import com.emr.project.operation.service.ICumulativeCostService;
import com.emr.project.operation.service.IDepartAccountService;
import com.emr.project.operation.service.InpatientOrderService;
import com.emr.project.system.domain.SysDept;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ConfigureDeptService;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonOperationServiceImpl implements ICommonOperationService {
   private static Logger log = LoggerFactory.getLogger(CommonOperationServiceImpl.class);
   @Autowired
   private MedicalinformationMapper medicalinformationMapper;
   @Autowired
   private BaseinfomationMapper baseinfomationMapper;
   @Autowired
   private ICumulativeCostService cumulativeCostService;
   @Autowired
   private CommonOperationMapper commonMapper;
   @Autowired
   private InpatientOrderService inpatientOrderService;
   @Autowired
   private IDepartAccountService departAccountService;
   @Autowired
   private ConfigureDeptService configureDeptService;

   public boolean checkPatientArrears(String zyh, String zycs, BigDecimal jzje) throws Exception {
      if (!StringUtils.isBlank(zyh) && !StringUtils.isBlank(zycs)) {
         Medicalinformation medicalinfo = this.medicalinformationMapper.getMedicalinfo(zyh);
         SysDept sysDept = SecurityUtils.getLoginUser().getUser().getDept();
         Boolean arrears = StringUtils.isNotBlank(sysDept.getArrearsFlag()) && sysDept.getArrearsFlag().equals("1");
         if (arrears) {
            return Boolean.TRUE;
         } else {
            Baseinfomation findBaseInfo = this.baseinfomationMapper.findBaseInfo(zyh);
            Map<String, String> param = new HashMap();
            param.put("admission_no", zyh);
            param.put("hospitalized_count", zycs);
            String categoryNo = findBaseInfo.getExpenseCategoryNo();
            Map<String, Object> fbInfo = this.commonMapper.getFbInfo(categoryNo);
            fbInfo = ToggleCaseUtils.toLowerCase(fbInfo);
            List<GuaranteeDTO> guarantee = this.commonMapper.selectGuarantee(zyh, zycs);
            BigDecimal dbje = BigDecimal.ZERO;
            Date jssj = null;
            if (!guarantee.isEmpty()) {
               for(GuaranteeDTO guaranteeDTO : guarantee) {
                  if ("1".equals(guaranteeDTO.getRemarkCode())) {
                     jssj = guaranteeDTO.getApprovalEndTime();
                  } else if ("2".equals(guaranteeDTO.getRemarkCode())) {
                     jssj = guaranteeDTO.getApprovalEndTime();
                  } else if ("5".equals(guaranteeDTO.getRemarkCode())) {
                     dbje = dbje.add(this.checkObj(guaranteeDTO.getAmountGuaranteed()));
                  }
               }
            }

            CumulativeCost cumulativeCost = this.cumulativeCostService.queryByAdmissionNo(zyh);
            BigDecimal bjje = this.checkObj(fbInfo.get("warning_amount"));
            BigDecimal yjbs = this.checkObj(fbInfo.get("waring_number"));
            Date kssj = this.commonMapper.getDbTime();
            BigDecimal ljfy = this.checkObj(cumulativeCost == null ? null : cumulativeCost.getCumulativeCost());
            BigDecimal yjk = this.checkObj(cumulativeCost == null ? null : cumulativeCost.getDeposIt());
            String arrearageMark = medicalinfo.getInpatArrearageMark();
            if (StringUtils.isNotBlank(arrearageMark) && arrearageMark.equals("1")) {
               return true;
            } else {
               if (null != jssj) {
                  if (!kssj.after(jssj)) {
                     return true;
                  }

                  log.info("患者【" + zyh + "】担保时间过期！");
                  BigDecimal calcul = this.inpatientOrderService.calculationPerformFeeNoPerform(zyh);
                  ljfy = ljfy.add(calcul);
                  if (yjk.multiply(yjbs).subtract(jzje).subtract(ljfy).add(dbje).compareTo(bjje) == -1) {
                     log.info("患者【" + zyh + "】担保金额不足！");
                     return false;
                  }
               } else {
                  BigDecimal calcul = this.inpatientOrderService.calculationPerformFeeNoPerform(zyh);
                  ljfy = ljfy.add(calcul);
                  if (yjk.multiply(yjbs).subtract(jzje).subtract(ljfy).add(dbje).compareTo(bjje) == -1) {
                     log.info("患者【" + zyh + "】担保金额不足！");
                     return false;
                  }
               }

               return true;
            }
         }
      } else {
         log.info("参数异常！");
         return false;
      }
   }

   private BigDecimal checkObj(Object obj) {
      return obj != null ? new BigDecimal(obj.toString()) : new BigDecimal(0);
   }

   private Date checkTime(Object obj) {
      return obj != null ? (Date)obj : null;
   }

   public Date getDbDate() {
      return this.commonMapper.getDbTime();
   }

   public boolean checkPatientIsIn(String admissionNo, String hospitalized_count) throws Exception {
      boolean flag = false;
      if (StringUtils.isNotBlank(admissionNo) && StringUtils.isNotBlank(hospitalized_count)) {
         int checkPatientIsIn = this.commonMapper.checkPatientIsIn(admissionNo, hospitalized_count);
         if (checkPatientIsIn > 0) {
            flag = true;
         }
      }

      return flag;
   }

   public String getCrbz(Medicalinformation medicalinformation) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      Baseinfomation baseinfomation = this.baseinfomationMapper.findBaseInfo(medicalinformation.getAdmissionNo());
      String age = this.configureDeptService.getConfigureValue("1299", user.getHospital().getOrgCode(), user.getDept().getDeptCode());
      int crbzAge = 14;
      if (StringUtils.isNotBlank(age)) {
         crbzAge = Integer.parseInt(age);
      }

      Integer personAge = baseinfomation.getPersonAge();
      return personAge != null && personAge > crbzAge ? "1" : "0";
   }

   public int checkUserPassWord(String userCode, String passWord) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      return this.commonMapper.checkUserPassWord(userCode, passWord, user.getDept().getDeptCode());
   }
}
