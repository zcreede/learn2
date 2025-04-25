package com.emr.project.report.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.report.domain.dto.DepartmentStatisticsDataDTO;
import com.emr.project.report.domain.req.DepartmentStatisticsDataReq;
import com.emr.project.report.domain.req.PatientWorkloadReq;
import com.emr.project.report.domain.resp.DepartmentStatisticsDataResp;
import com.emr.project.report.domain.resp.DeptWorkloadResp;
import com.emr.project.report.domain.resp.FeeWorkloadAllListResp;
import com.emr.project.report.domain.resp.FeeWorkloadListResp;
import com.emr.project.report.domain.resp.FeeWorkloadResp;
import com.emr.project.report.domain.vo.TmBsAccountThirdVO;
import com.emr.project.report.mapper.DepartmentIncomeMapper;
import com.emr.project.report.service.DepartmentIncomeService;
import com.emr.project.system.domain.SysUser;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections.map.LinkedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentIncomeServiceImpl implements DepartmentIncomeService {
   @Autowired
   private DepartmentIncomeMapper departmentIncomeMapper;

   public List selectDepartmentStatisticsDataList(DepartmentStatisticsDataReq req) throws Exception {
      List<DepartmentStatisticsDataResp> dataListResp = new ArrayList();
      String costType = req.getCostType();
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      String depCode = sysUser.getDept().getDeptCode();
      String departmentType = req.getDepartmentType();
      String dateTimeStart = req.getDateStart() + " 00:00:00";
      String dateTimeEnd = req.getDateEnd() + " 23:59:59";
      new ArrayList();
      List dataList;
      if (costType.equals("2")) {
         dataList = this.departmentIncomeMapper.selectLeaveHospitalDataList(depCode, departmentType, dateTimeStart, dateTimeEnd);
      } else {
         dataList = this.departmentIncomeMapper.selectInHosPatientDataList(depCode, departmentType, dateTimeStart, dateTimeEnd);
      }

      if (dataList.size() > 0) {
         Map<String, List<DepartmentStatisticsDataDTO>> dataMap = (Map)dataList.stream().collect(Collectors.groupingBy((t) -> t.getFirstCode() + "_" + t.getFirsName()));
         BigDecimal totalAll = BigDecimal.ZERO;

         for(String key : dataMap.keySet()) {
            DepartmentStatisticsDataResp dataResp = new DepartmentStatisticsDataResp();
            List<DepartmentStatisticsDataResp> departmentStatisticsDataRespList = new ArrayList();
            String firstCode = key.split("_")[0];
            String name = key.split("_")[1] + "合计";
            BigDecimal total = BigDecimal.ZERO;

            for(DepartmentStatisticsDataDTO dataDTO : (List)dataMap.get(key)) {
               DepartmentStatisticsDataResp resp = new DepartmentStatisticsDataResp();
               resp.setCode(dataDTO.getThreeLevelAccounting());
               resp.setEntryName(dataDTO.getThreeLevelName());
               resp.setAmount(dataDTO.getTotal().toPlainString());
               resp.setFirstCode(firstCode);
               total = dataDTO.getTotal().add(total);
               departmentStatisticsDataRespList.add(resp);
               dataListResp.add(resp);
            }

            totalAll = totalAll.add(total);
            dataResp.setEntryName(name);
            dataResp.setAmount(total.stripTrailingZeros().toPlainString());
            dataResp.setCode(firstCode);
            dataResp.setFirstCode("无");
            dataListResp.add(dataResp);
         }

         DepartmentStatisticsDataResp dataResp = new DepartmentStatisticsDataResp();
         dataResp.setAmount(totalAll.stripTrailingZeros().toPlainString());
         dataResp.setEntryName("科室合计");
         dataListResp.add(dataResp);
      }

      return dataListResp;
   }

   public FeeWorkloadAllListResp selectFeeWorkloadList(DepartmentStatisticsDataReq req) throws Exception {
      FeeWorkloadAllListResp dataListResp = new FeeWorkloadAllListResp();
      List<FeeWorkloadListResp> list = new ArrayList();
      String costType = req.getCostType();
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      String depCode = sysUser.getDept().getDeptCode();
      String departmentType = req.getDepartmentType();
      String dateTimeStart = req.getDateStart() + " 00:00:00";
      String dateTimeEnd = req.getDateEnd() + " 23:59:59";
      new ArrayList();
      List dataList;
      if (costType.equals("2")) {
         dataList = this.departmentIncomeMapper.selectLeaveHospitalFeeWorkloadList(depCode, departmentType, dateTimeStart, dateTimeEnd);
      } else {
         dataList = this.departmentIncomeMapper.selectInHosPatientFeeWorkloadList(depCode, departmentType, dateTimeStart, dateTimeEnd);
      }

      if (dataList.size() > 0) {
         Map<String, List<FeeWorkloadResp>> threeLevelMap = (Map)dataList.stream().collect(Collectors.groupingBy(FeeWorkloadResp::getThreeLevelCode));
         Map<String, TmBsAccountThirdVO> thirdMap = new LinkedMap();

         for(FeeWorkloadResp tmBsAccountThird : dataList) {
            String code = tmBsAccountThird.getThreeLevelCode();
            List<FeeWorkloadResp> workloadResps = (List)threeLevelMap.get(code);
            BigDecimal total = (BigDecimal)workloadResps.stream().map(FeeWorkloadResp::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
            if (StringUtils.isNotEmpty(code)) {
               TmBsAccountThirdVO accountThird = new TmBsAccountThirdVO();
               accountThird.setCode(code);
               accountThird.setName(tmBsAccountThird.getThreeLevelName());
               accountThird.setTotal(total);
               thirdMap.put(code, accountThird);
            }
         }

         Map<String, List<FeeWorkloadResp>> map = (Map)dataList.stream().collect(Collectors.groupingBy(FeeWorkloadResp::getStaffCode));
         BigDecimal allTotal = BigDecimal.ZERO;

         for(String key : map.keySet()) {
            List<FeeWorkloadResp> resps = (List)map.get(key);
            Map<String, FeeWorkloadResp> loadRespMap = new LinkedMap();

            for(FeeWorkloadResp feeWorkloadResp : resps) {
               String threeLevelCode = feeWorkloadResp.getThreeLevelCode();
               if (StringUtils.isNotEmpty(threeLevelCode)) {
                  loadRespMap.put(threeLevelCode, feeWorkloadResp);
               }
            }

            BigDecimal total = BigDecimal.ZERO;
            List<FeeWorkloadResp> list1 = new ArrayList();

            for(String thirdCode : thirdMap.keySet()) {
               FeeWorkloadResp feeWorkloadResp = new FeeWorkloadResp();
               TmBsAccountThirdVO accountThird = (TmBsAccountThirdVO)thirdMap.get(thirdCode);
               feeWorkloadResp.setStaffCode(((FeeWorkloadResp)resps.get(0)).getStaffCode());
               feeWorkloadResp.setStaffName(((FeeWorkloadResp)resps.get(0)).getStaffName());
               feeWorkloadResp.setThreeLevelCode(accountThird.getCode());
               feeWorkloadResp.setThreeLevelName(accountThird.getName());
               if (loadRespMap.containsKey(thirdCode)) {
                  FeeWorkloadResp workloadResp = (FeeWorkloadResp)loadRespMap.get(thirdCode);
                  feeWorkloadResp.setTotal(workloadResp.getTotal());
                  total = total.add(workloadResp.getTotal().stripTrailingZeros());
               } else {
                  feeWorkloadResp.setTotal(BigDecimal.ZERO);
                  total = total.add(BigDecimal.ZERO);
               }

               list1.add(feeWorkloadResp);
            }

            FeeWorkloadListResp listResp = new FeeWorkloadListResp();
            listResp.setStaffCode(((FeeWorkloadResp)resps.get(0)).getStaffCode());
            listResp.setStaffName(((FeeWorkloadResp)resps.get(0)).getStaffName());
            listResp.setList(list1);
            listResp.setTotal(total.toPlainString());
            list.add(listResp);
            allTotal = allTotal.add(total);
         }

         dataListResp.setAllTotal(allTotal.toPlainString());
         dataListResp.setList(list);
         dataListResp.setTableHeader(new ArrayList(thirdMap.values()));
      }

      return dataListResp;
   }

   public List queryDeptWorkload(PatientWorkloadReq req) {
      List<DeptWorkloadResp> list = new ArrayList();
      String beginDate = req.getBeginDate() + " 00:00:00";
      String endDate = req.getEndDate() + " 23:59:59";
      String type = req.getType();
      String departmentType = req.getDepartmentType();
      SysUser user = SecurityUtils.getLoginUser().getUser();
      String depCode = user.getDept().getDeptCode();
      List<DeptWorkloadResp> deptList = new ArrayList();
      String typeMessage = null;
      String departmentTypeStr = null;
      if (departmentType.equals("1")) {
         departmentTypeStr = "（本科开单他科执行）";
      } else {
         departmentTypeStr = "（本科执行）";
      }

      if (type.equals("1")) {
         deptList = this.departmentIncomeMapper.selectDeptLeaveHospitalList(depCode, beginDate, endDate, departmentType);
         typeMessage = "按出院患者统计";
      } else if (type.equals("2")) {
         deptList = this.departmentIncomeMapper.selectDeptInHosPatientList(depCode, beginDate, endDate, departmentType);
         typeMessage = "按实际费用统计";
      }

      Map<String, BigDecimal> bigMap = new HashMap();
      Map<String, List<DeptWorkloadResp>> collect = (Map)deptList.stream().collect(Collectors.groupingBy((t) -> t.getExecutorDptNo() + t.getThreeLevelAccounting()));

      for(String key : collect.keySet()) {
         List<DeptWorkloadResp> workloadResps = (List)collect.get(key);
         BigDecimal total = (BigDecimal)workloadResps.stream().map(DeptWorkloadResp::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
         bigMap.put(key, total);
      }

      for(String key : collect.keySet()) {
         List<DeptWorkloadResp> workloadResps = (List)collect.get(key);
         DeptWorkloadResp deptWorkloadResp = (DeptWorkloadResp)workloadResps.get(0);
         deptWorkloadResp.setType(typeMessage);
         deptWorkloadResp.setTotal((BigDecimal)bigMap.get(key));
         deptWorkloadResp.setBeginDate(req.getBeginDate());
         deptWorkloadResp.setEndDate(req.getEndDate());
         deptWorkloadResp.setAmount((BigDecimal)bigMap.get(key));
         deptWorkloadResp.setDeptName(user.getDept().getDeptName());
         deptWorkloadResp.setHospitalName(user.getHospital().getOrgName());
         deptWorkloadResp.setDepartmentTypeStr(departmentTypeStr);
         list.add(deptWorkloadResp);
      }

      list = (List)list.stream().sorted(Comparator.comparing(DeptWorkloadResp::getExecutorDptNo)).collect(Collectors.toList());
      return list;
   }
}
