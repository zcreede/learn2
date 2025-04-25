package com.emr.project.report.service.impl;

import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.security.LoginUser;
import com.emr.project.report.domain.FeeDayDataDetail;
import com.emr.project.report.domain.resp.FeeSummaryDayResp;
import com.emr.project.report.domain.vo.BillingDetailsVo;
import com.emr.project.report.domain.vo.FeeDayDataVo;
import com.emr.project.report.domain.vo.ReportGrfReqParamVo;
import com.emr.project.report.mapper.ReportGrfMapper;
import com.emr.project.report.service.IReportGrfService;
import com.emr.project.system.domain.BsStaff;
import com.emr.project.system.domain.SysOrg;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.mapper.BsStaffMapper;
import com.emr.project.system.mapper.SysOrgMapper;
import com.emr.project.system.service.ISysEmrConfigService;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportGrfServiceImpl implements IReportGrfService {
   @Autowired
   private ReportGrfMapper reportGrfMapper;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private BsStaffMapper bsStaffMapper;
   @Autowired
   private SysOrgMapper sysOrgMapper;

   public List feeSummaryDayData(Map param) throws Exception {
      this.reportGrfMapper.feeSummaryDayData(param);
      List<Map<String, Object>> data = (List)param.get("cv_results");
      if (data != null && !data.isEmpty()) {
         for(Map map : data) {
            Date inhosDate = (Date)map.get("RYRQ");
            Date outDate = (Date)map.get("CYRQ");
            if (outDate != null) {
               map.put("CYRQ", DateUtils.parseDateToStr("YYYY-MM-dd", outDate));
            }

            if (inhosDate != null) {
               map.put("RYRQ", DateUtils.parseDateToStr("YYYY-MM-dd", inhosDate));
            }
         }
      }

      return data;
   }

   public List feeSummaryDayDataList(ReportGrfReqParamVo paramVo) throws Exception {
      List<Map<String, Object>> list = new ArrayList(1);
      String admissionNo = paramVo.getV_admission_no();
      String hospitalMark = this.reportGrfMapper.selectHospitalMarkByAdmissionNo(admissionNo);
      if (StringUtils.isNotEmpty(hospitalMark)) {
         String tableName = "T_IH_EXPENSEDETAIL";
         if (hospitalMark.equals("3")) {
            tableName = tableName + "_CYBR";
         } else if (hospitalMark.equals("4")) {
            tableName = tableName + "_DRCY";
         }

         list = this.reportGrfMapper.feeSummaryDayDataList(admissionNo, tableName);

         for(Map map : list) {
            String hospitalName = SecurityUtils.getLoginUser().getUser().getHospital().getOrgName();
            map.put("YYMC", hospitalName);
            Timestamp ryrq = (Timestamp)map.get("RYRQ");
            Timestamp cyrq = Timestamp.valueOf(LocalDateTime.now());
            if (map.get("CYRQ") != null) {
               cyrq = (Timestamp)map.get("CYRQ");
            }

            Calendar cc = Calendar.getInstance();
            cc.setTime(cyrq);
            cc.add(5, 1);
            cyrq = new Timestamp(cc.getTime().getTime());
            long nd = 86400000L;
            long ryrqTime = ryrq.getTime();
            long newTime = cyrq.getTime();
            long diff = newTime - ryrqTime;
            Long day = diff / nd;
            map.put("ZYTS", day.intValue());
            Date inhosDate = (Date)map.get("RYRQ");
            Date outDate = (Date)map.get("CYRQ");
            if (outDate != null) {
               map.put("CYRQ", DateUtils.parseDateToStr("YYYY-MM-dd", outDate));
            }

            if (inhosDate != null) {
               map.put("RYRQ", DateUtils.parseDateToStr("YYYY-MM-dd", inhosDate));
            }
         }
      }

      return list;
   }

   public List feeSummaryDayDataListForPdf(ReportGrfReqParamVo paramVo) throws Exception {
      List<Map<String, Object>> resultList = new ArrayList(1);
      String admissionNo = paramVo.getV_admission_no();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      List<Map<String, Object>> list = this.reportGrfMapper.feeSummaryDayDataListForPdf(admissionNo);
      if (list != null && list.size() > 0) {
         Map<String, List<Map<String, Object>>> admissionNoMap = (Map)list.stream().collect(Collectors.groupingBy((t) -> t.get("ADMISSION_NO").toString()));
         String blankStr = this.sysEmrConfigService.selectSysEmrConfigByKey("0064");
         List<BillingDetailsVo> billList = this.reportGrfMapper.selectBillingDetailByAdmissionNoList(admissionNo);
         Map<String, List<BillingDetailsVo>> admissionMap = new HashMap();
         if (!billList.isEmpty()) {
            admissionMap = (Map)billList.stream().collect(Collectors.groupingBy(BillingDetailsVo::getAdmissionNo));
         }

         for(String key : admissionNoMap.keySet()) {
            List<Map<String, Object>> mapList = (List)admissionNoMap.get(key);
            List<BillingDetailsVo> detailsVos = (List)admissionMap.get(key);
            BillingDetailsVo detailsVo = null;
            if (CollectionUtils.isNotEmpty(detailsVos)) {
               detailsVo = (BillingDetailsVo)detailsVos.get(0);
            }

            BigDecimal jToal = BigDecimal.ZERO;
            BigDecimal yToal = BigDecimal.ZERO;
            BigDecimal jDrugToal = BigDecimal.ZERO;
            BigDecimal yDrugToal = BigDecimal.ZERO;
            BigDecimal total = (BigDecimal)mapList.stream().map((t) -> new BigDecimal(t.get("JE").toString())).reduce(BigDecimal.ZERO, BigDecimal::add);
            List<Map<String, Object>> xmdjList = (List)mapList.stream().filter((t) -> t.get("XMDJ") != null).collect(Collectors.toList());
            if (!xmdjList.isEmpty()) {
               List<String> thirdList = this.reportGrfMapper.selectThirdList();
               List<Map<String, Object>> jxmdjList = (List)xmdjList.stream().filter((t) -> t.get("XMDJ").toString().equals("01")).collect(Collectors.toList());
               if (CollectionUtils.isNotEmpty(jxmdjList)) {
                  jToal = (BigDecimal)jxmdjList.stream().map((t) -> new BigDecimal(t.get("JE").toString())).reduce(BigDecimal.ZERO, BigDecimal::add);
               }

               List<Map<String, Object>> yxmdjList = (List)xmdjList.stream().filter((t) -> t.get("XMDJ").toString().equals("02")).collect(Collectors.toList());
               if (CollectionUtils.isNotEmpty(yxmdjList)) {
                  yToal = (BigDecimal)yxmdjList.stream().map((t) -> new BigDecimal(t.get("JE").toString())).reduce(BigDecimal.ZERO, BigDecimal::add);
               }

               List<Map<String, Object>> jDrugxmdjList = (List)xmdjList.stream().filter((t) -> t.get("XMDJ").toString().equals("01") && thirdList.contains(t.get("THREE_LEVEL_ACCOUNTING").toString())).collect(Collectors.toList());
               if (CollectionUtils.isNotEmpty(jDrugxmdjList)) {
                  jDrugToal = (BigDecimal)jDrugxmdjList.stream().map((t) -> new BigDecimal(t.get("JE").toString())).reduce(BigDecimal.ZERO, BigDecimal::add);
               }

               List<Map<String, Object>> yDrugxmdjList = (List)xmdjList.stream().filter((t) -> t.get("XMDJ").toString().equals("02") && thirdList.contains(t.get("THREE_LEVEL_ACCOUNTING").toString())).collect(Collectors.toList());
               if (CollectionUtils.isNotEmpty(yDrugxmdjList)) {
                  yDrugToal = (BigDecimal)yDrugxmdjList.stream().map((t) -> new BigDecimal(t.get("JE").toString())).reduce(BigDecimal.ZERO, BigDecimal::add);
               }
            }

            Map<String, List<Map<String, Object>>> projectMap = (Map)mapList.stream().collect(Collectors.groupingBy((t) -> t.get("THREE_LEVEL_ACCOUNTING").toString()));
            Set<String> threeLevelAccountingSet = projectMap.keySet();
            List<String> threeLevelAccountingList = new ArrayList(threeLevelAccountingSet);

            for(String projectName : (List)threeLevelAccountingList.stream().sorted(Comparator.comparingDouble(Double::parseDouble)).collect(Collectors.toList())) {
               List<Map<String, Object>> feeDayDataVoList = (List)projectMap.get(projectName);
               Map<String, List<Map<String, Object>>> standardCodeMap = (Map)feeDayDataVoList.stream().collect(Collectors.groupingBy((t) -> t.get("CHARGE_NO").toString() + t.get("JG")));
               Set<String> standardCodeSet = standardCodeMap.keySet();
               List<String> standardCodeList = new ArrayList(standardCodeSet);
               List var59 = (List)standardCodeList.stream().sorted().collect(Collectors.toList());
               BigDecimal subTotal = BigDecimal.ZERO;

               for(String standardCode : var59) {
                  List<Map<String, Object>> voList = (List)standardCodeMap.get(standardCode);
                  BigDecimal all = (BigDecimal)voList.stream().map((t) -> new BigDecimal(t.get("JE").toString())).reduce(BigDecimal.ZERO, BigDecimal::add);
                  BigDecimal allDose = (BigDecimal)voList.stream().map((t) -> new BigDecimal(t.get("JL").toString())).reduce(BigDecimal.ZERO, BigDecimal::add);
                  subTotal = subTotal.add(all);
                  Map<String, Object> vo = (Map)voList.get(0);
                  if (detailsVo != null) {
                     vo.put("TOTAL_AMOUNT", detailsVo.getTotalAmount());
                     vo.put("zfje", detailsVo.getZfje());
                     vo.put("t1", BigDecimal.ZERO);
                     vo.put("t2", BigDecimal.ZERO);
                  } else {
                     vo.put("TOTAL_AMOUNT", total);
                     vo.put("zfje", BigDecimal.ZERO);
                     vo.put("t1", BigDecimal.ZERO);
                     vo.put("t2", BigDecimal.ZERO);
                  }

                  vo.put("J_FEE", jToal);
                  vo.put("Y_FEE", yToal);
                  vo.put("J_DRUG_FEE", jDrugToal);
                  vo.put("Y_DRUG_FEE", yDrugToal);
                  vo.put("JE", all);
                  vo.put("JL", allDose);
                  LoginUser loginUser = SecurityUtils.getLoginUser();
                  SysUser user = new SysUser();
                  if (loginUser != null) {
                     user = loginUser.getUser();
                  } else {
                     BsStaff record = new BsStaff();
                     record.setStaffCode("sa");
                     BsStaff bsStaff = this.bsStaffMapper.selectByConn(record);
                     SysOrg sysOrg = this.sysOrgMapper.selectSysOrgByOrgNo(bsStaff.getHospitalNo());
                     user.setHospital(sysOrg);
                  }

                  vo.put("YYMC", user.getHospital().getOrgName());
                  Date ryrq = (Date)vo.get("RYRQ");
                  vo.put("RYRQ", sdf.format(ryrq));
                  Date cyrq = Timestamp.valueOf(LocalDateTime.now());
                  if (vo.get("CYRQ") != null) {
                     cyrq = (Date)vo.get("CYRQ");
                  }

                  if (StringUtils.isNotEmpty(blankStr)) {
                     String[] value = blankStr.split(",");
                     List<String> valueList = Arrays.asList(value);
                     if (valueList.size() > 0) {
                        String chargeCode = vo.get("CHARGE_CODE").toString();
                        if (valueList.contains(chargeCode)) {
                           vo.put("MC", vo.get("CHARGE_NAME"));
                        }
                     }
                  }

                  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                  ryrq = format.parse(format.format(ryrq));
                  Date var63 = format.parse(format.format(cyrq));
                  long nd = 86400000L;
                  long ryrqTime = ryrq.getTime();
                  long newTime = var63.getTime();
                  long diff = newTime - ryrqTime;
                  Long day = diff / nd;
                  if (day == 0L) {
                     day = 1L;
                  }

                  vo.put("ZYTS", day.intValue());
                  Iterator<String> iterator = vo.keySet().iterator();

                  while(iterator.hasNext()) {
                     String platformMapKey = (String)iterator.next();
                     if (vo.get(platformMapKey) == null) {
                        iterator.remove();
                     }
                  }

                  resultList.add(vo);
               }
            }
         }
      }

      return resultList;
   }

   public List feeSummaryDay(ReportGrfReqParamVo reportGrfReqParamVo) throws Exception {
      List<FeeSummaryDayResp> resultList = new ArrayList();
      String admissionNo = reportGrfReqParamVo.getV_admission_no();
      Date now = new Date();
      List<FeeDayDataVo> list = this.reportGrfMapper.selectFeeSummaryDay(admissionNo);
      if (list != null && list.size() > 0) {
         String blankStr = this.sysEmrConfigService.selectSysEmrConfigByKey("0064");
         Map<String, List<FeeDayDataVo>> listMap = (Map)list.stream().collect(Collectors.groupingBy(FeeDayDataVo::getAdmissionNo));

         for(String key : listMap.keySet()) {
            FeeSummaryDayResp resp = new FeeSummaryDayResp();
            List<FeeDayDataVo> dataVoList = (List)listMap.get(key);
            FeeDayDataVo feeDayDataVo = (FeeDayDataVo)dataVoList.get(0);
            BeanUtils.copyProperties(feeDayDataVo, resp);
            Map<String, List<FeeDayDataVo>> projectMap = (Map)dataVoList.stream().collect(Collectors.groupingBy(FeeDayDataVo::getThreeLevelAccounting));
            Set<String> threeLevelAccountingSet = projectMap.keySet();
            List<String> threeLevelAccountingList = new ArrayList(threeLevelAccountingSet);
            List var35 = (List)threeLevelAccountingList.stream().sorted(Comparator.comparingDouble(Double::parseDouble)).collect(Collectors.toList());
            List<FeeDayDataDetail> detailList = new ArrayList(1);
            BigDecimal total = BigDecimal.ZERO;

            for(String projectName : var35) {
               List<FeeDayDataVo> feeDayDataVoList = (List)projectMap.get(projectName);
               Map<String, List<FeeDayDataVo>> standardCodeMap = (Map)feeDayDataVoList.stream().collect(Collectors.groupingBy((t) -> t.getChargeNo() + t.getPrice()));
               Set<String> standardCodeSet = standardCodeMap.keySet();
               List<String> standardCodeList = new ArrayList(standardCodeSet);
               List var45 = (List)standardCodeList.stream().sorted().collect(Collectors.toList());
               BigDecimal subTotal = BigDecimal.ZERO;

               for(String standardCode : var45) {
                  List<FeeDayDataVo> voList = (List)standardCodeMap.get(standardCode);
                  BigDecimal all = (BigDecimal)voList.stream().map(FeeDayDataVo::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
                  BigDecimal allDose = (BigDecimal)voList.stream().map(FeeDayDataVo::getDose).reduce(BigDecimal.ZERO, BigDecimal::add);
                  subTotal = subTotal.add(all);
                  FeeDayDataVo vo = (FeeDayDataVo)voList.get(0);
                  FeeDayDataDetail dataDetail = new FeeDayDataDetail();
                  BeanUtils.copyProperties(vo, dataDetail);
                  dataDetail.setDose(allDose);
                  dataDetail.setTotal(all);
                  if (StringUtils.isNotEmpty(blankStr)) {
                     String[] value = blankStr.split(",");
                     List<String> valueList = Arrays.asList(value);
                     if (valueList.size() > 0) {
                        String chargeCode = vo.getChargeCode();
                        if (valueList.contains(chargeCode)) {
                           dataDetail.setStandardName(vo.getChargeName());
                        }
                     }
                  }

                  detailList.add(dataDetail);
               }

               total = total.add(subTotal);
               FeeDayDataDetail detail = new FeeDayDataDetail();
               detail.setSumName(((FeeDayDataVo)feeDayDataVoList.get(0)).getProjectName() + "小计：");
               detail.setTotal(subTotal);
               detailList.add(detail);
            }

            FeeDayDataDetail detail = new FeeDayDataDetail();
            detail.setSumName("清单合计：");
            detail.setTotal(total);
            detailList.add(detail);
            LoginUser loginUser = SecurityUtils.getLoginUser();
            SysUser user = new SysUser();
            if (loginUser != null) {
               user = loginUser.getUser();
            } else {
               BsStaff record = new BsStaff();
               record.setStaffCode("sa");
               BsStaff bsStaff = this.bsStaffMapper.selectByConn(record);
               SysOrg sysOrg = this.sysOrgMapper.selectSysOrgByOrgNo(bsStaff.getHospitalNo());
               user.setHospital(sysOrg);
               user.setUserName(bsStaff.getStaffCode());
               user.setNickName(bsStaff.getStaffName());
            }

            resp.setHospitalName(user.getHospital().getOrgName());
            resp.setPrintDate(now);
            resp.setPrintUserName(user.getNickName());
            Date hospitalizedDate = resp.getHospitalizedDate();
            Date leaveHospitalDate = resp.getLeaveHospitalDate() == null ? new Date() : resp.getLeaveHospitalDate();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            hospitalizedDate = format.parse(format.format(hospitalizedDate));
            leaveHospitalDate = format.parse(format.format(leaveHospitalDate));
            long nd = 86400000L;
            long ryrqTime = hospitalizedDate.getTime();
            long newTime = leaveHospitalDate.getTime();
            long diff = newTime - ryrqTime;
            Long day = diff / nd;
            if (day == 0L) {
               day = 1L;
            }

            resp.setHospitalizationDays(day.intValue());
            resp.setDetailList(detailList);
            resultList.add(resp);
         }
      }

      return resultList;
   }
}
