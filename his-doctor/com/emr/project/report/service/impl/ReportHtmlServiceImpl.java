package com.emr.project.report.service.impl;

import com.emr.common.utils.AgeUtil;
import com.emr.common.utils.ExcelUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.report.domain.resp.FeeWorkloadListResp;
import com.emr.project.report.domain.resp.FeeWorkloadResp;
import com.emr.project.report.domain.vo.FeeItemVoParam;
import com.emr.project.report.domain.vo.FeeWorkloadVo;
import com.emr.project.report.domain.vo.PatientInLeaveInfoVo;
import com.emr.project.report.domain.vo.PatientInLeaveInfoVoParam;
import com.emr.project.report.domain.vo.TmBsAccountThirdVO;
import com.emr.project.report.mapper.ReportHtmlMapper;
import com.emr.project.report.service.IReportHtmlService;
import com.emr.project.system.domain.SysDictData;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysDictDataService;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.LinkedMap;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportHtmlServiceImpl implements IReportHtmlService {
   public static final String QUERY_TYPE_ALL = "all";
   public static final String QUERY_TYPE_IN = "in";
   public static final String QUERY_TYPE_LEAVE = "leave";
   @Autowired
   private ReportHtmlMapper reportHtmlMapper;
   @Autowired
   private ISysDictDataService sysDictDataService;

   public List queryPatient(PatientInLeaveInfoVoParam param) throws Exception {
      List<PatientInLeaveInfoVo> list = new ArrayList(1);
      switch (param.getQueryType()) {
         case "all":
            list = this.reportHtmlMapper.selectPatientAll(param);
            break;
         case "in":
            list = this.reportHtmlMapper.selectPatientIn(param);
            break;
         case "leave":
            list = this.reportHtmlMapper.selectPatientLeave(param);
      }

      List<SysDictData> sexDataList = this.sysDictDataService.selectDictDataByType("s028");
      Map<String, SysDictData> sysDictDataMap = (Map<String, SysDictData>)(CollectionUtils.isNotEmpty(sexDataList) ? (Map)sexDataList.stream().collect(Collectors.toMap((t) -> t.getDictValue(), Function.identity())) : new HashMap());

      for(PatientInLeaveInfoVo patientInLeaveInfoVo : list) {
         String ageStr = AgeUtil.getAgeStr(patientInLeaveInfoVo.getAgeY(), patientInLeaveInfoVo.getAgeM(), patientInLeaveInfoVo.getAgeD(), patientInLeaveInfoVo.getAgeH(), patientInLeaveInfoVo.getAgeMi());
         patientInLeaveInfoVo.setAge(ageStr);
         String sex = patientInLeaveInfoVo.getSex();
         SysDictData sexDictData = (SysDictData)sysDictDataMap.get(sex);
         patientInLeaveInfoVo.setSex(sexDictData != null ? sexDictData.getDictLabel() : null);
      }

      return list;
   }

   public List queryFeeItemList(FeeItemVoParam param) throws Exception {
      return this.reportHtmlMapper.queryFeeItemList(param);
   }

   public Double queryFeeItemTotal(FeeItemVoParam param) throws Exception {
      return this.reportHtmlMapper.queryFeeItemTotal(param);
   }

   public AjaxResult selectFeeWorkloadList(FeeWorkloadVo param) throws Exception {
      List<FeeWorkloadListResp> list = new ArrayList();
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      param.setBillDeptCode(sysUser.getDept().getDeptCode());
      AjaxResult ajaxResult = AjaxResult.success();
      String queryType = param.getQueryType();
      String dateTimeStart = param.getBeginDateTime() + " 00:00:00";
      String dateTimeEnd = param.getEndDateTime() + " 00:00:00";
      param.setBeginDateTime(dateTimeStart);
      param.setEndDateTime(dateTimeEnd);
      new ArrayList();
      List dataList;
      if (queryType.equals("2")) {
         dataList = this.reportHtmlMapper.selectLeaveHospitalFeeWorkloadList(param);
      } else {
         dataList = this.reportHtmlMapper.selectInHosPatientFeeWorkloadList(param);
      }

      List<FeeWorkloadVo> sizeList = new ArrayList();
      List<Map<String, Object>> mapList = new ArrayList();
      BigDecimal decimal = BigDecimal.ZERO;
      if (dataList.size() > 0) {
         Map<String, List<FeeWorkloadVo>> threeLevelMap = (Map)dataList.stream().collect(Collectors.groupingBy(FeeWorkloadVo::getThreeLevelCode));
         Map<String, TmBsAccountThirdVO> thirdMap = new LinkedMap();

         for(FeeWorkloadVo tmBsAccountThird : dataList) {
            String code = tmBsAccountThird.getThreeLevelCode();
            List<FeeWorkloadVo> workloadResps = (List)threeLevelMap.get(code);
            BigDecimal total = (BigDecimal)workloadResps.stream().map(FeeWorkloadVo::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
            if (StringUtils.isNotEmpty(code)) {
               TmBsAccountThirdVO accountThird = new TmBsAccountThirdVO();
               accountThird.setCode(code);
               accountThird.setName(tmBsAccountThird.getThreeLevelName());
               accountThird.setTotal(total);
               thirdMap.put(code, accountThird);
            }
         }

         Map<String, List<FeeWorkloadVo>> map = (Map)dataList.stream().collect(Collectors.groupingBy(FeeWorkloadVo::getStaffCode));

         for(String key : map.keySet()) {
            List<FeeWorkloadVo> resps = (List)map.get(key);
            Map<String, FeeWorkloadVo> loadRespMap = new LinkedMap();

            for(FeeWorkloadVo feeWorkloadResp : resps) {
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
               feeWorkloadResp.setStaffCode(((FeeWorkloadVo)resps.get(0)).getStaffCode());
               feeWorkloadResp.setStaffName(((FeeWorkloadVo)resps.get(0)).getStaffName());
               feeWorkloadResp.setThreeLevelCode(accountThird.getCode());
               feeWorkloadResp.setThreeLevelName(accountThird.getName());
               if (loadRespMap.containsKey(thirdCode)) {
                  FeeWorkloadVo workloadResp = (FeeWorkloadVo)loadRespMap.get(thirdCode);
                  feeWorkloadResp.setTotal(workloadResp.getTotal());
                  total = total.add(workloadResp.getTotal().stripTrailingZeros());
               } else {
                  feeWorkloadResp.setTotal(BigDecimal.ZERO);
                  total = total.add(BigDecimal.ZERO);
               }

               list1.add(feeWorkloadResp);
            }

            FeeWorkloadListResp listResp = new FeeWorkloadListResp();
            listResp.setStaffCode(((FeeWorkloadVo)resps.get(0)).getStaffCode());
            listResp.setStaffName(((FeeWorkloadVo)resps.get(0)).getStaffName());
            listResp.setList(list1);
            listResp.setTotal(total.toPlainString());
            list.add(listResp);
         }

         ArrayList<TmBsAccountThirdVO> accountThirdVOS = new ArrayList(thirdMap.values());

         for(TmBsAccountThirdVO vo : accountThirdVOS) {
            FeeWorkloadVo feeWorkloadVo = new FeeWorkloadVo();
            feeWorkloadVo.setThreeLevelCode(vo.getCode());
            feeWorkloadVo.setThreeLevelName(vo.getName());
            sizeList.add(feeWorkloadVo);
         }

         for(FeeWorkloadListResp resp : list) {
            List<FeeWorkloadResp> respList = resp.getList();
            Map<String, Object> mapData = new HashMap();

            for(FeeWorkloadResp feeWorkloadResp : respList) {
               mapData.put(feeWorkloadResp.getThreeLevelCode(), feeWorkloadResp.getTotal());
            }

            mapData.put("staffCode", resp.getStaffCode());
            mapData.put("staffName", resp.getStaffName());
            mapData.put("total", resp.getTotal());
            mapList.add(mapData);
         }

         decimal = (BigDecimal)accountThirdVOS.stream().map(TmBsAccountThirdVO::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
      }

      ajaxResult.put("table", sizeList);
      ajaxResult.put("allTotal", decimal);
      ajaxResult.put("tableData", mapList);
      return ajaxResult;
   }

   public AjaxResult feeWorkloadListExcel(FeeWorkloadVo param, HttpServletResponse response) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      param.setBillDeptCode(sysUser.getDept().getDeptCode());
      String queryType = param.getQueryType();
      String begin = param.getBeginDateTime();
      String end = param.getEndDateTime();
      String dateTimeStart = param.getBeginDateTime() + " 00:00:00";
      String dateTimeEnd = param.getEndDateTime() + " 00:00:00";
      param.setBeginDateTime(dateTimeStart);
      param.setEndDateTime(dateTimeEnd);
      new ArrayList();
      List list;
      if (queryType.equals("2")) {
         list = this.reportHtmlMapper.selectLeaveHospitalFeeWorkloadList(param);
      } else {
         list = this.reportHtmlMapper.selectInHosPatientFeeWorkloadList(param);
      }

      List<LinkedHashMap<String, Object>> mapList = new ArrayList();
      List<FeeWorkloadVo> sizeList = new ArrayList();
      LinkedHashMap<String, Object> tableMap = new LinkedHashMap();
      tableMap.put("staffCode", "医师编码");
      tableMap.put("staffName", "医师姓名");
      tableMap.put("total", "合计");
      Double allTotal = (double)0.0F;
      if (list != null && !list.isEmpty()) {
         Map<String, List<FeeWorkloadVo>> map = (Map)list.stream().collect(Collectors.groupingBy((s) -> s.getStaffCode()));
         Map<String, List<FeeWorkloadVo>> threeLevelMap = (Map)list.stream().collect(Collectors.groupingBy(FeeWorkloadVo::getThreeLevelCode));

         for(String key : threeLevelMap.keySet()) {
            List<FeeWorkloadVo> workloadVos = (List)threeLevelMap.get(key);
            sizeList.add(workloadVos.get(0));
         }

         for(FeeWorkloadVo feeWorkloadVo : sizeList) {
            tableMap.put(feeWorkloadVo.getThreeLevelCode(), feeWorkloadVo.getThreeLevelName());
         }

         if (sizeList != null && !sizeList.isEmpty()) {
            for(String staffCode : map.keySet()) {
               List<FeeWorkloadVo> voList = (List)map.get(staffCode);
               LinkedHashMap<String, Object> voMap = new LinkedHashMap();
               voMap.put("staffCode", ((FeeWorkloadVo)voList.get(0)).getStaffCode());
               voMap.put("staffName", ((FeeWorkloadVo)voList.get(0)).getStaffName());
               BigDecimal totalBig = (BigDecimal)voList.stream().map(FeeWorkloadVo::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);

               for(FeeWorkloadVo feeWorkloadVo : sizeList) {
                  List<FeeWorkloadVo> list1 = (List)voList.stream().filter((s) -> StringUtils.isNotEmpty(s.getThreeLevelCode()) && s.getThreeLevelCode().equals(feeWorkloadVo.getThreeLevelCode())).collect(Collectors.toList());
                  Double price = list1 != null && !list1.isEmpty() ? ((FeeWorkloadVo)list1.get(0)).getTotal().doubleValue() : (double)0.0F;
                  voMap.put(feeWorkloadVo.getThreeLevelCode(), price);
               }

               voMap.put("total", String.format("%.2f", totalBig.doubleValue()));
               allTotal = allTotal + totalBig.doubleValue();
               mapList.add(voMap);
            }
         }
      }

      Workbook wb = new SXSSFWorkbook(500);
      String tableName = "";
      if (param.getQueryType().equals("1")) {
         tableName = sysUser.getHospital().getOrgName() + sysUser.getDept().getDeptName() + "医师工作量统计表";
      } else {
         tableName = sysUser.getHospital().getOrgName() + sysUser.getDept().getDeptName() + "医师工作量统计表 - 出院病人";
      }

      ExcelUtils.feeWorkloadExcel(wb, mapList, tableMap, String.format("%.2f", allTotal), tableName);
      String fileName = begin + "~" + end + ".xlsx";
      response.setHeader("Content-disposition", "attachment;filename=" + fileName);
      OutputStream stream = new FileOutputStream(ExcelUtils.getAbsoluteFile(fileName));
      if (null != wb && null != stream) {
         wb.write(stream);
         stream.close();
      }

      return AjaxResult.success(fileName);
   }
}
