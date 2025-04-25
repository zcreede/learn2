package com.emr.common.utils;

import com.emr.framework.config.EMRConfig;
import com.emr.project.docOrder.domain.vo.OrderListInfoVo;
import com.emr.project.docOrder.domain.vo.OrderListPatientVo;
import com.emr.project.docOrder.domain.vo.OrderListVo;
import com.emr.project.qc.domain.vo.EmrQcFlowStatisticVo;
import com.emr.project.qc.domain.vo.EmrQcFlowVo;
import com.emr.project.qc.domain.vo.EmrQcListStatisticVo;
import com.emr.project.qc.domain.vo.EmrQcListVo;
import com.emr.project.system.domain.SysUser;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExcelUtils {
   public static Map createStyles(Workbook wb) {
      Map<String, CellStyle> styles = new HashMap();
      Font headerFont = wb.createFont();
      headerFont.setFontName("宋体");
      headerFont.setFontHeightInPoints((short)18);
      headerFont.setBold(true);
      headerFont.setColor((short)32767);
      Font contextFont = wb.createFont();
      contextFont.setFontName("宋体");
      contextFont.setFontHeightInPoints((short)12);
      headerFont.setBold(true);
      CellStyle headerStyle = wb.createCellStyle();
      headerStyle.setFont(headerFont);
      headerStyle.setAlignment(HorizontalAlignment.CENTER);
      headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
      headerStyle.setLocked(true);
      headerStyle.setWrapText(false);
      styles.put("headerStyle", headerStyle);
      CellStyle commonStyle = wb.createCellStyle();
      commonStyle.setFont(contextFont);
      commonStyle.setAlignment(HorizontalAlignment.CENTER);
      commonStyle.setVerticalAlignment(VerticalAlignment.CENTER);
      commonStyle.setLocked(true);
      commonStyle.setWrapText(false);
      styles.put("commonStyle", commonStyle);
      CellStyle commonWrapStyle = wb.createCellStyle();
      commonWrapStyle.setFont(contextFont);
      styles.put("commonWrapStyle", commonWrapStyle);
      CellStyle verticalStyle = wb.createCellStyle();
      verticalStyle.setFont(contextFont);
      styles.put("verticalStyle", verticalStyle);
      CellStyle commonStyleNoBorder = wb.createCellStyle();
      commonStyleNoBorder.setFont(contextFont);
      commonStyleNoBorder.setLocked(true);
      commonStyleNoBorder.setWrapText(false);
      styles.put("commonStyleNoBorder", commonStyleNoBorder);
      CellStyle alignLeftStyle = wb.createCellStyle();
      alignLeftStyle.setFont(contextFont);
      alignLeftStyle.setLocked(true);
      alignLeftStyle.setWrapText(false);
      styles.put("alignLeftStyle", alignLeftStyle);
      CellStyle alignLeftNoBorderStyle = wb.createCellStyle();
      alignLeftNoBorderStyle.setFont(contextFont);
      alignLeftNoBorderStyle.setLocked(true);
      alignLeftNoBorderStyle.setWrapText(false);
      styles.put("alignLeftNoBorderStyle", alignLeftNoBorderStyle);
      CellStyle alignRightStyle = wb.createCellStyle();
      alignRightStyle.setFont(contextFont);
      alignRightStyle.setLocked(true);
      alignRightStyle.setWrapText(false);
      styles.put("alignRightStyle", alignRightStyle);
      return styles;
   }

   public static void setSheetHeader(Sheet sheet, Map styles, String headerName, OrderListPatientVo pat) {
      Row r1 = sheet.createRow(0);
      r1.setHeight((short)800);
      Cell r10 = r1.createCell(0);
      r10.setCellValue(headerName);
      r10.setCellStyle((CellStyle)styles.get("headerStyle"));
      Row r2 = sheet.createRow(1);
      r2.setHeight((short)400);
      Cell r20 = r2.createCell(0);
      String bedNo = StringUtils.isEmpty(pat.getBedNo()) ? "" : pat.getBedNo();
      r20.setCellValue("科室：" + pat.getDeptName() + "    姓名：" + pat.getPatientName() + "   性别：" + pat.getSex() + "   年龄：" + pat.getAge() + "   床号：" + bedNo + "   住院号：" + pat.getInpNo());
      r20.setCellStyle((CellStyle)styles.get("alignLeftNoBorderStyle"));
   }

   public static String getAbsoluteFile(String filename) {
      String downloadPath = EMRConfig.getDownloadPath() + filename;
      File desc = new File(downloadPath);
      if (desc.exists()) {
         desc.delete();
      }

      desc.getParentFile().mkdirs();
      return downloadPath;
   }

   public static void setOrderLongExcel(Workbook wb, String orderName, OrderListInfoVo orderListInfoVo, HttpServletResponse httpResponse) {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      Sheet sheet = wb.createSheet(orderName);
      Map<String, CellStyle> styles = createStyles(wb);
      int rowNum = 2;

      for(int i = 0; i < 11; ++i) {
         sheet.setColumnWidth(i, 3000);
      }

      setSheetHeader(sheet, styles, sysUser.getHospital().getOrgName() + orderName, orderListInfoVo.getPatientInfo());
      Row r3 = sheet.createRow(rowNum++);
      r3.setHeight((short)400);
      String[] rowSecond = new String[]{"开                     始", "", "", "", "停   止"};

      for(int i = 0; i < rowSecond.length; ++i) {
         Cell tempCell = r3.createCell(i);
         tempCell.setCellValue(rowSecond[i]);
         tempCell.setCellStyle((CellStyle)styles.get("commonStyle"));
      }

      sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 3));
      sheet.addMergedRegion(new CellRangeAddress(2, 2, 4, 7));
      Row r4 = sheet.createRow(rowNum++);
      String[] rowSecond5 = new String[]{"日期 时间", "医嘱内容", "签字", "", "时间", "日期", "签字", ""};

      for(int i = 0; i < rowSecond5.length; ++i) {
         Cell tempCell = r4.createCell(i);
         tempCell.setCellValue(rowSecond5[i]);
         tempCell.setCellStyle((CellStyle)styles.get("commonStyle"));
      }

      Row r5 = sheet.createRow(rowNum++);
      String[] rowSecond6 = new String[]{"", "", "医师", "护士", "", "", "医师", "护士"};

      for(int i = 0; i < rowSecond6.length; ++i) {
         Cell tempCell = r5.createCell(i);
         tempCell.setCellValue(rowSecond6[i]);
         tempCell.setCellStyle((CellStyle)styles.get("commonStyle"));
      }

      sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));
      sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));
      sheet.addMergedRegion(new CellRangeAddress(3, 4, 0, 0));
      sheet.addMergedRegion(new CellRangeAddress(3, 3, 2, 3));
      sheet.addMergedRegion(new CellRangeAddress(3, 3, 6, 7));
      sheet.addMergedRegion(new CellRangeAddress(3, 4, 1, 1));
      sheet.addMergedRegion(new CellRangeAddress(3, 4, 4, 4));
      sheet.addMergedRegion(new CellRangeAddress(3, 4, 5, 5));
      sheet.setColumnWidth(0, 5000);
      sheet.setColumnWidth(1, 13000);

      for(OrderListVo orderListVo : orderListInfoVo.getList()) {
         Row tempRow = sheet.createRow(rowNum++);
         tempRow.setHeight((short)400);
         List<String> str = new ArrayList();
         str.add(orderListVo.getOrderStartTime() == null ? "   " : DateUtils.parseDateToStr("yy-MM-dd HH:mm", orderListVo.getOrderStartTime()));
         String cpName = StringUtils.isEmpty(orderListVo.getCpName()) ? "   " : orderListVo.getCpName();
         String usageUnit = StringUtils.isEmpty(orderListVo.getUsageUnit()) ? "   " : orderListVo.getUsageUnit();
         String groupStr = StringUtils.isEmpty(orderListVo.getGroupStr()) ? "   " : orderListVo.getGroupStr();
         String usageWay = StringUtils.isEmpty(orderListVo.getItemOrderUsageWayName()) ? "   " : orderListVo.getItemOrderUsageWayName();
         String freqName = StringUtils.isEmpty(orderListVo.getItemOrderFreqName()) ? "   " : orderListVo.getItemOrderFreqName();
         String usage = orderListVo.getOrderActualUsage() == null ? "   " : orderListVo.getOrderActualUsage().toString();
         str.add(cpName + "  " + usage + usageUnit + "  " + groupStr + usageWay + "  " + freqName);
         str.add(orderListVo.getSubmitDoctorName());
         str.add(orderListVo.getOrderAuditDocName());
         str.add(orderListVo.getOrderStopDate() == null ? "" : DateUtils.parseDateToStr("MM.dd", orderListVo.getOrderStopDate()));
         str.add(orderListVo.getOrderStopTime() == null ? "" : DateUtils.parseDateToStr("HH:mm", orderListVo.getOrderStopTime()));
         str.add(orderListVo.getOrderStopDocName());
         str.add(orderListVo.getOrderStopNurseName());
         int cell = 0;

         for(String value : str) {
            Cell tempCell = tempRow.createCell(cell++);
            tempCell.setCellValue(value);
            tempCell.setCellStyle((CellStyle)styles.get("commonStyle"));
         }
      }

   }

   public static void setOrderTempExcel(Workbook wb, String orderName, OrderListInfoVo orderListInfoVo, HttpServletResponse httpResponse) {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      Sheet sheet = wb.createSheet(orderName);
      Map<String, CellStyle> styles = createStyles(wb);
      int rowNum = 2;

      for(int i = 0; i < 11; ++i) {
         sheet.setColumnWidth(i, 3000);
      }

      setSheetHeader(sheet, styles, sysUser.getHospital().getOrgName() + orderName, orderListInfoVo.getPatientInfo());
      Row r4 = sheet.createRow(rowNum++);
      String[] rowSecond5 = new String[]{"日期 时间", "医嘱内容", "签字", "", "执行时间"};

      for(int i = 0; i < rowSecond5.length; ++i) {
         Cell tempCell = r4.createCell(i);
         tempCell.setCellValue(rowSecond5[i]);
         tempCell.setCellStyle((CellStyle)styles.get("commonStyle"));
      }

      Row r5 = sheet.createRow(rowNum++);
      String[] rowSecond6 = new String[]{"", "", "医师", "执行者", ""};

      for(int i = 0; i < rowSecond6.length; ++i) {
         Cell tempCell = r5.createCell(i);
         tempCell.setCellValue(rowSecond6[i]);
         tempCell.setCellStyle((CellStyle)styles.get("commonStyle"));
      }

      sheet.addMergedRegion(new CellRangeAddress(2, 3, 0, 0));
      sheet.addMergedRegion(new CellRangeAddress(2, 3, 1, 1));
      sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
      sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 4));
      sheet.addMergedRegion(new CellRangeAddress(2, 3, 4, 4));
      sheet.addMergedRegion(new CellRangeAddress(2, 2, 2, 3));
      sheet.setColumnWidth(0, 5000);
      sheet.setColumnWidth(1, 15000);
      sheet.setColumnWidth(4, 5000);

      for(OrderListVo orderListVo : orderListInfoVo.getList()) {
         Row tempRow = sheet.createRow(rowNum++);
         tempRow.setHeight((short)400);
         List<String> str = new ArrayList();
         str.add(orderListVo.getOrderStartTime() == null ? "   " : DateUtils.parseDateToStr("yy-MM-dd HH:mm", orderListVo.getOrderStartTime()));
         String cpName = StringUtils.isEmpty(orderListVo.getCpName()) ? "   " : orderListVo.getCpName();
         String usageUnit = StringUtils.isEmpty(orderListVo.getUsageUnit()) ? "   " : orderListVo.getUsageUnit();
         String groupStr = StringUtils.isEmpty(orderListVo.getGroupStr()) ? "   " : orderListVo.getGroupStr();
         String usageWay = StringUtils.isEmpty(orderListVo.getItemOrderUsageWayName()) ? "   " : orderListVo.getItemOrderUsageWayName();
         String freqName = StringUtils.isEmpty(orderListVo.getItemOrderFreqName()) ? "   " : orderListVo.getItemOrderFreqName();
         String usage = orderListVo.getOrderActualUsage() == null ? "   " : orderListVo.getOrderActualUsage().toString();
         str.add(cpName + "  " + usage + usageUnit + "  " + groupStr + usageWay + "  " + freqName);
         str.add(orderListVo.getSubmitDoctorName());
         str.add(orderListVo.getOrderPerformNurseName());
         str.add(orderListVo.getOrderPerformTime() == null ? "" : DateUtils.parseDateToStr("yy-MM-dd HH:mm", orderListVo.getOrderPerformTime()));
         int cell = 0;

         for(String value : str) {
            Cell tempCell = tempRow.createCell(cell++);
            tempCell.setCellValue(value);
            tempCell.setCellStyle((CellStyle)styles.get("commonStyle"));
         }
      }

   }

   public static void setDecoctionExcel(Workbook wb, String orderName, OrderListInfoVo orderListInfoVo, HttpServletResponse httpResponse) {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      Sheet sheet = wb.createSheet(orderName);
      Map<String, CellStyle> styles = createStyles(wb);
      int rowNum = 2;

      for(int i = 0; i < 11; ++i) {
         sheet.setColumnWidth(i, 3000);
      }

      setSheetHeader(sheet, styles, sysUser.getHospital().getOrgName() + orderName, orderListInfoVo.getPatientInfo());
      Row r4 = sheet.createRow(rowNum++);
      String[] rowSecond5 = new String[]{"日期 时间", "医嘱内容", "签字", "", "执行时间"};

      for(int i = 0; i < rowSecond5.length; ++i) {
         Cell tempCell = r4.createCell(i);
         tempCell.setCellValue(rowSecond5[i]);
         tempCell.setCellStyle((CellStyle)styles.get("commonStyle"));
      }

      Row r5 = sheet.createRow(rowNum++);
      String[] rowSecond6 = new String[]{"", "", "医师", "执行者", ""};

      for(int i = 0; i < rowSecond6.length; ++i) {
         Cell tempCell = r5.createCell(i);
         tempCell.setCellValue(rowSecond6[i]);
         tempCell.setCellStyle((CellStyle)styles.get("commonStyle"));
      }

      sheet.addMergedRegion(new CellRangeAddress(2, 3, 0, 0));
      sheet.addMergedRegion(new CellRangeAddress(2, 3, 1, 1));
      sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
      sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 4));
      sheet.addMergedRegion(new CellRangeAddress(2, 3, 4, 4));
      sheet.addMergedRegion(new CellRangeAddress(2, 2, 2, 3));
      sheet.setColumnWidth(0, 5000);
      sheet.setColumnWidth(1, 20000);
      sheet.setColumnWidth(4, 5000);

      for(OrderListVo orderListVo : orderListInfoVo.getList()) {
         Row tempRow = sheet.createRow(rowNum++);
         tempRow.setHeight((short)400);
         List<String> str = new ArrayList();
         str.add(orderListVo.getOrderStartTime() == null ? "   " : DateUtils.parseDateToStr("yy-MM-dd HH:mm", orderListVo.getOrderStartTime()));
         String cpName = "";
         if ((orderListVo.getHerbalList() == null || orderListVo.getHerbalList().isEmpty()) && StringUtils.isNotEmpty(orderListVo.getCpNo())) {
            cpName = cpName + (orderListVo.getHerbalDose() == null ? "" : orderListVo.getHerbalDose().toString());
            cpName = cpName + (StringUtils.isEmpty(orderListVo.getItemOrderUsageWayName()) ? "" : orderListVo.getItemOrderUsageWayName() + " ");
            cpName = cpName + (StringUtils.isEmpty(orderListVo.getItemOrderFreqName()) ? "" : orderListVo.getItemOrderFreqName());
            cpName = cpName + (StringUtils.isEmpty(orderListVo.getOederDesc()) ? "" : orderListVo.getOederDesc());
            cpName = cpName + (StringUtils.isEmpty(orderListVo.getGroupStr()) ? "" : orderListVo.getGroupStr());
            cpName = cpName + "    ";
         } else if (orderListVo.getHerbalList() != null && !orderListVo.getHerbalList().isEmpty()) {
            for(OrderListVo herb : orderListVo.getHerbalList()) {
               cpName = cpName + (StringUtils.isEmpty(herb.getCpName()) ? "" : herb.getCpName());
               cpName = cpName + (StringUtils.isEmpty(herb.getDoctorInstructions()) ? "" : herb.getDoctorInstructions());
               cpName = cpName + (herb.getOrderActualUsage() == null ? "" : "(" + herb.getOrderActualUsage().toString() + ")");
               cpName = cpName + (StringUtils.isEmpty(herb.getUsageUnit()) ? "" : "(" + herb.getUsageUnit() + ")");
               cpName = cpName + "    ";
            }

            cpName = cpName + (StringUtils.isEmpty(orderListVo.getGroupStr()) ? "" : orderListVo.getGroupStr());
         } else {
            cpName = StringUtils.isEmpty(orderListVo.getCpName()) ? "" : orderListVo.getCpName();
         }

         str.add(cpName);
         str.add(orderListVo.getSubmitDoctorName());
         str.add(orderListVo.getOrderPerformNurseName());
         str.add(orderListVo.getOrderPerformTime() == null ? "" : DateUtils.parseDateToStr("yy-MM-dd HH:mm", orderListVo.getOrderPerformTime()));
         int cell = 0;

         for(String value : str) {
            Cell tempCell = tempRow.createCell(cell++);
            tempCell.setCellValue(value);
            tempCell.setCellStyle((CellStyle)styles.get("commonStyle"));
         }
      }

   }

   public static void feeWorkloadExcel(Workbook wb, List mapList, LinkedHashMap map, String total, String tableName) {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      Font headerFont = wb.createFont();
      headerFont.setFontName("宋体");
      headerFont.setFontHeightInPoints((short)12);
      headerFont.setBold(true);
      headerFont.setColor((short)32767);
      CellStyle headerStyle = wb.createCellStyle();
      headerStyle.setFont(headerFont);
      headerStyle.setAlignment(HorizontalAlignment.CENTER);
      headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
      headerStyle.setLocked(true);
      headerStyle.setWrapText(false);
      Sheet sheet = wb.createSheet("工作量统计");
      Map<String, CellStyle> styles = createStyles(wb);
      Row r0 = sheet.createRow(0);
      r0.setHeight((short)400);
      Cell r10 = r0.createCell(0);
      r10.setCellValue(tableName);
      r10.setCellStyle(headerStyle);
      int rowNum = 1;

      for(int i = 0; i < 11; ++i) {
         sheet.setColumnWidth(i, 3000);
      }

      Row r = sheet.createRow(rowNum++);
      int index2 = 0;

      for(String key : map.keySet()) {
         Cell tempCell = r.createCell(index2++);
         tempCell.setCellValue(map.get(key).toString());
         tempCell.setCellStyle((CellStyle)styles.get("commonStyle"));
      }

      for(LinkedHashMap linkedHashMap : mapList) {
         Row r1 = sheet.createRow(rowNum++);
         int index = 0;

         for(String key : map.keySet()) {
            Cell tempCell = r1.createCell(index++);
            tempCell.setCellValue(linkedHashMap.get(key) == null ? "" : linkedHashMap.get(key).toString());
            tempCell.setCellStyle((CellStyle)styles.get("commonStyle"));
         }
      }

      Row r2 = sheet.createRow(rowNum++);
      sheet.addMergedRegion(new CellRangeAddress(rowNum - 1, rowNum, 0, 4));
      sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, map == null ? 0 : map.size() - 1));
      Cell tempCell = r2.createCell(0);
      tempCell.setCellValue("              合计：    " + total);
   }

   public static void getQcFlowStatisticExport(Workbook wb, List mapList, LinkedHashMap map, List emrQcFlowStatisticList, EmrQcFlowVo emrQcFlowVo, String tableName) {
      Font headerFont = wb.createFont();
      headerFont.setFontName("宋体");
      headerFont.setFontHeightInPoints((short)12);
      headerFont.setBold(true);
      headerFont.setColor((short)32767);
      CellStyle headerStyle = wb.createCellStyle();
      headerStyle.setFont(headerFont);
      headerStyle.setAlignment(HorizontalAlignment.CENTER);
      headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
      headerStyle.setLocked(true);
      headerStyle.setWrapText(false);
      Sheet sheet = wb.createSheet("归档病历统计");
      Map<String, CellStyle> styles = createStyles(wb);
      Row r0 = sheet.createRow(0);
      r0.setHeight((short)400);
      Cell r10 = r0.createCell(0);
      sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, map.size() - 1));
      r10.setCellValue(tableName);
      r10.setCellStyle(headerStyle);
      int rowNum = 1;

      for(int i = 0; i < 13; ++i) {
         sheet.setColumnWidth(i, 5000);
      }

      Row r2 = sheet.createRow(1);
      Cell tempCell0 = r2.createCell(0);
      tempCell0.setCellValue("筛选条件");
      if (emrQcFlowVo.getOutTimeBegin() != null) {
         emrQcFlowVo.setOutHospitalTimeBegin(emrQcFlowVo.getOutTimeBegin());
      }

      if (emrQcFlowVo.getOutTimeEnd() != null) {
         emrQcFlowVo.setOutHospitalTimeEnd(emrQcFlowVo.getOutTimeEnd());
      }

      sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 2));
      Cell tempCell1 = r2.createCell(1);
      if (emrQcFlowVo.getOutHospitalTimeBeginS() != null && emrQcFlowVo.getOutHospitalTimeEndS() != null) {
         tempCell1.setCellValue("出院日期：" + emrQcFlowVo.getOutHospitalTimeBeginS() + "~" + emrQcFlowVo.getOutHospitalTimeEndS());
      } else {
         tempCell1.setCellValue("出院日期：-");
      }

      Cell tempCell2 = r2.createCell(3);
      String deptName = emrQcFlowVo.getDeptName() != null ? emrQcFlowVo.getDeptName() : "";
      tempCell2.setCellValue("科室：" + deptName);
      Cell tempCell3 = r2.createCell(4);
      String resDocName = emrQcFlowVo.getResDocName() != null ? emrQcFlowVo.getResDocName() : "";
      tempCell3.setCellValue("管床医师：" + resDocName);
      Cell tempCell4 = r2.createCell(5);
      String mrState = emrQcFlowVo.getMrState() != null ? emrQcFlowVo.getMrState() : "";
      tempCell4.setCellValue("归档状态：" + mrState);
      Cell tempCell5 = r2.createCell(6);
      String patientId = emrQcFlowVo.getPatientId() != null ? emrQcFlowVo.getPatientId() : "";
      tempCell5.setCellValue("患者信息：" + patientId);
      Row r3 = sheet.createRow(2);
      Cell tempCell6 = r3.createCell(0);
      tempCell6.setCellValue("出院患者合计：" + emrQcFlowVo.getOutHospitalTotal());
      Cell tempCell7 = r3.createCell(1);
      tempCell7.setCellValue("已归档患者合计：" + emrQcFlowVo.getYgdTotal());
      Cell tempCell8 = r3.createCell(2);
      tempCell8.setCellValue("两日归档率：" + emrQcFlowVo.getTwoProportion());
      Cell tempCell9 = r3.createCell(3);
      tempCell9.setCellValue("三日归档率：" + emrQcFlowVo.getThreeProportion());
      Cell tempCell10 = r3.createCell(4);
      tempCell10.setCellValue("七日归档率：" + emrQcFlowVo.getSevenProportion());
      Cell tempCell11 = r3.createCell(5);
      tempCell11.setCellValue("甲级率：" + emrQcFlowVo.getJjProportion());
      Cell tempCell12 = r3.createCell(6);
      tempCell12.setCellValue("返修率：" + emrQcFlowVo.getFxlProportion());
      rowNum += 2;
      Row r = sheet.createRow(rowNum++);
      int index2 = 0;

      for(String key : map.keySet()) {
         Cell tempCell = r.createCell(index2++);
         tempCell.setCellValue(map.get(key).toString());
         tempCell.setCellStyle((CellStyle)styles.get("commonStyle"));
      }

      for(LinkedHashMap linkedHashMap : mapList) {
         Row r1 = sheet.createRow(rowNum++);
         int index = 0;

         for(String key : map.keySet()) {
            Cell tempCell = r1.createCell(index++);
            tempCell.setCellValue(linkedHashMap.get(key) == null ? "" : linkedHashMap.get(key).toString());
            tempCell.setCellStyle((CellStyle)styles.get("commonStyle"));
         }
      }

   }

   public static void getCheckDeptDialogExport(Workbook wb, List mapList, LinkedHashMap map, List emrQcListVoList, String tableName) {
      Font headerFont = wb.createFont();
      headerFont.setFontName("宋体");
      headerFont.setFontHeightInPoints((short)12);
      headerFont.setBold(true);
      headerFont.setColor((short)32767);
      CellStyle headerStyle = wb.createCellStyle();
      headerStyle.setFont(headerFont);
      headerStyle.setAlignment(HorizontalAlignment.CENTER);
      headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
      headerStyle.setLocked(true);
      headerStyle.setWrapText(false);
      Sheet sheet = wb.createSheet("科室缺陷统计详情");
      Map<String, CellStyle> styles = createStyles(wb);
      Row r0 = sheet.createRow(0);
      r0.setHeight((short)400);
      Cell r10 = r0.createCell(0);
      sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, map.size() - 1));
      r10.setCellValue(tableName);
      r10.setCellStyle(headerStyle);
      int rowNum = 1;

      for(int i = 0; i < 11; ++i) {
         sheet.setColumnWidth(i, 5000);
      }

      Font contextFont = wb.createFont();
      contextFont.setFontName("宋体");
      contextFont.setFontHeightInPoints((short)12);
      CellStyle commonStyle = wb.createCellStyle();
      commonStyle.setFont(contextFont);
      commonStyle.setAlignment(HorizontalAlignment.LEFT);
      commonStyle.setVerticalAlignment(VerticalAlignment.CENTER);
      commonStyle.setLocked(true);
      commonStyle.setWrapText(true);
      rowNum = rowNum++;
      Row r = sheet.createRow(rowNum++);
      int index2 = 0;

      for(String key : map.keySet()) {
         Cell tempCell = r.createCell(index2++);
         tempCell.setCellValue(map.get(key).toString());
         tempCell.setCellStyle((CellStyle)styles.get("commonStyle"));
      }

      for(LinkedHashMap linkedHashMap : mapList) {
         Row r1 = sheet.createRow(rowNum++);
         int index = 0;

         for(String key : map.keySet()) {
            Cell tempCell = r1.createCell(index++);
            tempCell.setCellValue(linkedHashMap.get(key) == null ? "" : linkedHashMap.get(key).toString());
            if ("flawDesc".equals(key)) {
               tempCell.setCellStyle(commonStyle);
            } else {
               tempCell.setCellStyle((CellStyle)styles.get("commonStyle"));
            }
         }
      }

   }

   public static void getQcCaseStatisticExport(Workbook wb, List mapList, LinkedHashMap map, List emrQcFlowStatisticList, EmrQcFlowVo emrQcFlowVo, String tableName) {
      Font headerFont = wb.createFont();
      headerFont.setFontName("宋体");
      headerFont.setFontHeightInPoints((short)12);
      headerFont.setBold(true);
      headerFont.setColor((short)32767);
      CellStyle headerStyle = wb.createCellStyle();
      headerStyle.setFont(headerFont);
      headerStyle.setAlignment(HorizontalAlignment.CENTER);
      headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
      headerStyle.setLocked(true);
      headerStyle.setWrapText(false);
      Sheet sheet = wb.createSheet("病历缺陷统计");
      Map<String, CellStyle> styles = createStyles(wb);
      Row r0 = sheet.createRow(0);
      r0.setHeight((short)400);
      Cell r10 = r0.createCell(0);
      sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, map.size() - 1));
      r10.setCellValue(tableName);
      r10.setCellStyle(headerStyle);
      int rowNum = 1;

      for(int i = 0; i < 11; ++i) {
         sheet.setColumnWidth(i, 5000);
      }

      Row r2 = sheet.createRow(1);
      Cell tempCell0 = r2.createCell(0);
      tempCell0.setCellValue("筛选条件");
      if (emrQcFlowVo.getOutTimeBegin() != null) {
         emrQcFlowVo.setOutHospitalTimeBegin(emrQcFlowVo.getOutTimeBegin());
      }

      if (emrQcFlowVo.getOutTimeEnd() != null) {
         emrQcFlowVo.setOutHospitalTimeEnd(emrQcFlowVo.getOutTimeEnd());
      }

      sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 2));
      Cell tempCell1 = r2.createCell(1);
      if (emrQcFlowVo.getOutHospitalTimeBeginS() != null && emrQcFlowVo.getOutHospitalTimeEndS() != null) {
         if ("3".equals(emrQcFlowVo.getTimeType())) {
            tempCell1.setCellValue("缺陷反馈日期：" + emrQcFlowVo.getOutHospitalTimeBeginS() + "~" + emrQcFlowVo.getOutHospitalTimeEndS());
         } else if ("3".equals(emrQcFlowVo.getTimeType())) {
            tempCell1.setCellValue("入院日期：" + emrQcFlowVo.getOutHospitalTimeBeginS() + "~" + emrQcFlowVo.getOutHospitalTimeEndS());
         } else {
            tempCell1.setCellValue("出院日期：" + emrQcFlowVo.getOutHospitalTimeBeginS() + "~" + emrQcFlowVo.getOutHospitalTimeEndS());
         }
      } else if ("3".equals(emrQcFlowVo.getTimeType())) {
         tempCell1.setCellValue("缺陷反馈日期：-");
      } else if ("3".equals(emrQcFlowVo.getTimeType())) {
         tempCell1.setCellValue("入院日期：-");
      } else {
         tempCell1.setCellValue("出院日期：-");
      }

      Cell tempCell2 = r2.createCell(3);
      String deptName = emrQcFlowVo.getDeptName() != null ? emrQcFlowVo.getDeptName() : "";
      tempCell2.setCellValue("科室：" + deptName);
      Cell tempCell3 = r2.createCell(4);
      String resDocName = emrQcFlowVo.getResDocName() != null ? emrQcFlowVo.getResDocName() : "";
      if ("1".equals(emrQcFlowVo.getDocType())) {
         tempCell3.setCellValue("管床医师：" + resDocName);
      } else {
         tempCell3.setCellValue("质控医师：" + resDocName);
      }

      Cell tempCell6 = r2.createCell(5);
      String qcSectionName = emrQcFlowVo.getQcSectionName() != null ? emrQcFlowVo.getQcSectionName() : "";
      tempCell6.setCellValue("质控环节：" + qcSectionName);
      Cell tempCell4 = r2.createCell(6);
      String mrState = emrQcFlowVo.getMrState() != null ? emrQcFlowVo.getMrState() : "";
      tempCell4.setCellValue("质控状态：" + mrState);
      Cell tempCell5 = r2.createCell(7);
      String patientId = emrQcFlowVo.getPatientId() != null ? emrQcFlowVo.getPatientId() : "";
      tempCell5.setCellValue("患者信息：" + patientId);
      ++rowNum;
      Row r = sheet.createRow(rowNum++);
      int index2 = 0;

      for(String key : map.keySet()) {
         Cell tempCell = r.createCell(index2++);
         tempCell.setCellValue(map.get(key).toString());
         tempCell.setCellStyle((CellStyle)styles.get("commonStyle"));
      }

      for(LinkedHashMap linkedHashMap : mapList) {
         Row r1 = sheet.createRow(rowNum++);
         int index = 0;

         for(String key : map.keySet()) {
            Cell tempCell = r1.createCell(index++);
            tempCell.setCellValue(linkedHashMap.get(key) == null ? "" : linkedHashMap.get(key).toString());
            tempCell.setCellStyle((CellStyle)styles.get("commonStyle"));
         }
      }

   }

   public static void getQcFlowStatisticByDeptExport(Workbook wb, List mapList, LinkedHashMap map, List emrQcFlowStatisticList, EmrQcFlowVo emrQcFlowVo, String tableName) {
      Font headerFont = wb.createFont();
      headerFont.setFontName("宋体");
      headerFont.setFontHeightInPoints((short)12);
      headerFont.setBold(true);
      headerFont.setColor((short)32767);
      CellStyle headerStyle = wb.createCellStyle();
      headerStyle.setFont(headerFont);
      headerStyle.setAlignment(HorizontalAlignment.CENTER);
      headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
      headerStyle.setLocked(true);
      headerStyle.setWrapText(false);
      Sheet sheet = wb.createSheet("归档率统计-按科室");
      Map<String, CellStyle> styles = createStyles(wb);
      Row r0 = sheet.createRow(0);
      r0.setHeight((short)400);
      Cell r10 = r0.createCell(0);
      sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, map.size() - 1));
      r10.setCellValue(tableName);
      r10.setCellStyle(headerStyle);
      int rowNum = 1;

      for(int i = 0; i < mapList.size(); ++i) {
         sheet.setColumnWidth(i, 5000);
      }

      Row r2 = sheet.createRow(1);
      Cell tempCell0 = r2.createCell(0);
      tempCell0.setCellValue("筛选条件");
      if (emrQcFlowVo.getOutTimeBegin() != null) {
         emrQcFlowVo.setOutHospitalTimeBegin(emrQcFlowVo.getOutTimeBegin());
      }

      if (emrQcFlowVo.getOutTimeEnd() != null) {
         emrQcFlowVo.setOutHospitalTimeEnd(emrQcFlowVo.getOutTimeEnd());
      }

      sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 2));
      Cell tempCell1 = r2.createCell(1);
      if (emrQcFlowVo.getOutHospitalTimeBeginS() != null && emrQcFlowVo.getOutHospitalTimeEndS() != null) {
         tempCell1.setCellValue("出院日期：" + emrQcFlowVo.getOutHospitalTimeBeginS() + "~" + emrQcFlowVo.getOutHospitalTimeEndS());
      } else {
         tempCell1.setCellValue("出院日期：-");
      }

      Row r3 = sheet.createRow(2);
      Cell tempCell6 = r3.createCell(0);
      tempCell6.setCellValue("出院患者合计：" + emrQcFlowVo.getOutHospitalTotal());
      Cell tempCell7 = r3.createCell(1);
      tempCell7.setCellValue("已归档患者合计：" + emrQcFlowVo.getYgdTotal());
      Cell tempCell8 = r3.createCell(2);
      tempCell8.setCellValue("两日归档率：" + emrQcFlowVo.getTwoProportion());
      Cell tempCell9 = r3.createCell(3);
      tempCell9.setCellValue("三日归档率：" + emrQcFlowVo.getThreeProportion());
      Cell tempCell10 = r3.createCell(4);
      tempCell10.setCellValue("七日归档率：" + emrQcFlowVo.getSevenProportion());
      Cell tempCell11 = r3.createCell(5);
      tempCell11.setCellValue("甲级率：" + emrQcFlowVo.getJjProportion());
      Cell tempCell12 = r3.createCell(6);
      tempCell12.setCellValue("返修率：" + emrQcFlowVo.getFxlProportion());
      rowNum += 2;
      Row r = sheet.createRow(rowNum++);
      int index2 = 0;

      for(String key : map.keySet()) {
         Cell tempCell = r.createCell(index2++);
         tempCell.setCellValue(map.get(key).toString());
         tempCell.setCellStyle((CellStyle)styles.get("commonStyle"));
      }

      for(LinkedHashMap linkedHashMap : mapList) {
         Row r1 = sheet.createRow(rowNum++);
         int index = 0;

         for(String key : map.keySet()) {
            Cell tempCell = r1.createCell(index++);
            tempCell.setCellValue(linkedHashMap.get(key) == null ? "" : linkedHashMap.get(key).toString());
            tempCell.setCellStyle((CellStyle)styles.get("commonStyle"));
         }
      }

   }
}
