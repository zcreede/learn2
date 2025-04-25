package com.emr.project.qc.service.impl;

import com.emr.common.utils.DateUtils;
import com.emr.common.utils.ExcelUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.sql.SqlUtil;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.PageDomain;
import com.emr.framework.web.page.TableSupport;
import com.emr.project.dr.domain.vo.TdCaConsApplyVo;
import com.emr.project.dr.service.ITdCaConsApplyService;
import com.emr.project.qc.domain.EmrQcFlowStatis;
import com.emr.project.qc.domain.SysReportCompDetail;
import com.emr.project.qc.domain.vo.EmrQcExportListVo;
import com.emr.project.qc.domain.vo.EmrQcFlowStatisDetailExport;
import com.emr.project.qc.domain.vo.EmrQcFlowStatisSumExport;
import com.emr.project.qc.domain.vo.EmrQcListStatisticByMrTypeVo;
import com.emr.project.qc.domain.vo.EmrQcListVo;
import com.emr.project.qc.domain.vo.StatementVo;
import com.emr.project.qc.domain.vo.StatisticsResultVo;
import com.emr.project.qc.mapper.EmrQcFlowStatisMapper;
import com.emr.project.qc.mapper.EmrQcListStatisMapper;
import com.emr.project.qc.service.IEmrQcFlowStatisService;
import com.emr.project.system.domain.SysDept;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysDeptService;
import com.github.pagehelper.PageHelper;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmrQcFlowStatisServiceImpl implements IEmrQcFlowStatisService {
   @Autowired
   private EmrQcFlowStatisMapper emrQcFlowStatisMapper;
   @Autowired
   private ITdCaConsApplyService tdCaConsApplyService;
   @Autowired
   private ISysDeptService sysDeptService;
   @Autowired
   private EmrQcListStatisMapper emrQcListStatisMapper;

   public StatisticsResultVo selectWorkloadCircle(StatementVo statementVo) throws Exception {
      StatisticsResultVo result = new StatisticsResultVo();
      Integer patTotal = this.emrQcFlowStatisMapper.selectPatTotal(statementVo);
      Integer emrQcTotal = this.emrQcFlowStatisMapper.selectEmrQcTotalGroup(statementVo);
      Integer unTotal = patTotal - emrQcTotal;
      List<StatementVo> data = new ArrayList();
      this.setCharData(emrQcTotal, patTotal, "抽查病例", data);
      this.setCharData(unTotal, patTotal, "未抽查病例", data);
      result.setTotal(patTotal);
      result.setCharData(data);
      return result;
   }

   public StatisticsResultVo selectWorkloadFlawCircle(StatementVo statementVo) throws Exception {
      StatisticsResultVo resultVo = new StatisticsResultVo();
      Integer Total = this.emrQcFlowStatisMapper.selectEmrQcTotal(statementVo);
      Integer flawTotal = this.emrQcFlowStatisMapper.selectEmrQcListTotal(statementVo);
      Integer unFlawTotal = Total - flawTotal;
      List<StatementVo> data = new ArrayList();
      this.setCharData(flawTotal, Total, "有缺陷病例", data);
      this.setCharData(unFlawTotal, Total, "无缺陷病例", data);
      resultVo.setTotal(Total);
      resultVo.setCharData(data);
      return resultVo;
   }

   public StatisticsResultVo selectWorkloadCheckCircle(StatementVo statementVo) throws Exception {
      StatisticsResultVo resultVo = new StatisticsResultVo();
      Integer total = this.emrQcFlowStatisMapper.selectEmrQcListTotal(statementVo);
      Integer checkTotal = this.emrQcFlowStatisMapper.selectEmrQcListCheckTotal(statementVo);
      Integer unCheckTotal = this.emrQcFlowStatisMapper.selectEmrQcListUnCheckTotal(statementVo);
      List<StatementVo> data = new ArrayList();
      this.setCharData(checkTotal, total, "已核验病例", data);
      this.setCharData(unCheckTotal, total, "未核验病例", data);
      resultVo.setTotal(total);
      resultVo.setCharData(data);
      return resultVo;
   }

   public StatisticsResultVo selectDocFlawHistogram(StatementVo statementVo) throws Exception {
      StatisticsResultVo resultVo = new StatisticsResultVo();
      List<String> docList = this.emrQcFlowStatisMapper.selectEmrQcDocCodeList(statementVo);
      List<StatisticsResultVo> statisticsResultVo = this.emrQcFlowStatisMapper.selectDocFlawHistogram(statementVo);
      if (statisticsResultVo != null && !statisticsResultVo.isEmpty()) {
         List<String> xAxis = new ArrayList(1);
         List<Integer> data1 = new ArrayList(1);
         List<Integer> data2 = new ArrayList(1);
         List<Integer> data3 = new ArrayList(1);
         List<Integer> data4 = new ArrayList(1);
         List<StatementVo> statementVoList = new ArrayList(1);
         Map<String, List<StatisticsResultVo>> map = (Map)statisticsResultVo.stream().collect(Collectors.groupingBy((s) -> s.getQcdoctCd()));

         for(String docCode : docList) {
            List<StatisticsResultVo> mapList = (List)map.get(docCode);
            xAxis.add(((StatisticsResultVo)mapList.get(0)).getQcdoctName());
            List<StatisticsResultVo> flawList = (List)mapList.stream().filter((s) -> s.getFlaws() == 0 && s.getState().equals("2")).collect(Collectors.toList());
            Integer flaws = flawList == null ? 0 : flawList.size();
            data1.add(flaws);
            List<StatisticsResultVo> unCheckList = (List)mapList.stream().filter((s) -> s.getFlaws() > 0 && !s.getState().equals("2")).collect(Collectors.toList());
            Integer unChecks = unCheckList == null ? 0 : unCheckList.size();
            data2.add(unChecks);
            List<StatisticsResultVo> checkList = (List)mapList.stream().filter((s) -> s.getFlaws() > 0 && s.getState().equals("2")).collect(Collectors.toList());
            Integer checks = checkList == null ? 0 : checkList.size();
            data3.add(checks);
            data4.add(flaws + unChecks + checks);
         }

         StatementVo state1 = new StatementVo("无缺陷病例", data1);
         StatementVo state2 = new StatementVo("未核验病例", data2);
         StatementVo state3 = new StatementVo("核验病例", data3);
         StatementVo state4 = new StatementVo("全部数量", data4);
         statementVoList.add(state3);
         statementVoList.add(state2);
         statementVoList.add(state1);
         resultVo.setTotalData(state4);
         resultVo.setSeries(statementVoList);
         resultVo.setxAxis(xAxis);
      }

      return resultVo;
   }

   public StatisticsResultVo selectCheckFlowTypePie(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      StatisticsResultVo statisticsResultVo = new StatisticsResultVo();
      Integer total = this.emrQcFlowStatisMapper.selectFlowTypePieTotal(emrQcFlowStatis);
      statisticsResultVo.setTotal(total);
      List<EmrQcFlowStatis> emrQcFlowStatisList = this.emrQcFlowStatisMapper.selectCheckFlowTypePie(emrQcFlowStatis);
      if (emrQcFlowStatisList != null && !emrQcFlowStatisList.isEmpty()) {
         List<StatementVo> charData = new ArrayList(1);
         Integer otherTotal = total;

         for(EmrQcFlowStatis statis : emrQcFlowStatisList) {
            this.setCharData(statis.getValueTotal(), total, statis.getxAxisName(), charData);
            otherTotal = otherTotal - statis.getValueTotal();
         }

         this.setCharData(otherTotal, total, "其他缺陷", charData);
         statisticsResultVo.setCharData(charData);
      }

      return statisticsResultVo;
   }

   public List checkFlowTypeTable(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      List<EmrQcFlowStatis> emrQcFlowStatisList = this.emrQcFlowStatisMapper.selectCheckFlowTypeCylinderList(emrQcFlowStatis);
      List<EmrQcFlowStatis> emrQcFlowStatisList1 = this.emrQcFlowStatisMapper.selectCheckFlowTypeCylinderList(emrQcFlowStatis);
      if (emrQcFlowStatisList != null && !emrQcFlowStatisList.isEmpty()) {
         this.setFlowTable(emrQcFlowStatisList, emrQcFlowStatisList1);
      }

      return emrQcFlowStatisList;
   }

   public List selectQcListByMrTypeTopSix(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      List<EmrQcListStatisticByMrTypeVo> emrQcListList = this.emrQcListStatisMapper.selectQcListByMrType(emrQcFlowStatis);
      List<EmrQcListStatisticByMrTypeVo> emrQcListRes = new ArrayList();
      if (emrQcListList != null && emrQcListList.size() > 0) {
         List<EmrQcListStatisticByMrTypeVo> emrQcListListD = (List)emrQcListList.stream().filter((t) -> t.getFlawTotal() != null && t.getFlawTotal() >= 0).collect(Collectors.toList());
         Integer sumTotal = emrQcListListD.stream().mapToInt(EmrQcListStatisticByMrTypeVo::getFlawTotal).sum();
         if (sumTotal > 0) {
            BigDecimal grandProportion = new BigDecimal(0);

            for(EmrQcListStatisticByMrTypeVo emrQcList : emrQcListList) {
               emrQcList.setFlawSum(sumTotal);
               BigDecimal sumTotalB = new BigDecimal(sumTotal);
               if (emrQcList.getFlawTotal() != null && emrQcList.getFlawTotal() > 0) {
                  BigDecimal flawTotal = new BigDecimal(emrQcList.getFlawTotal());
                  BigDecimal zb = flawTotal.divide(sumTotalB, 3, 4).multiply(new BigDecimal(100)).setScale(1);
                  grandProportion = zb.add(grandProportion);
                  emrQcList.setFlawProportionB(zb.doubleValue());
                  emrQcList.setFlawProportion(zb + "%");
                  emrQcList.setGrandTotalProportion(grandProportion + "%");
               } else {
                  emrQcList.setFlawTotal(0);
                  emrQcList.setFlawFileTotal(0);
                  emrQcList.setFlawYxgTotal(0);
                  emrQcList.setFlawYwTotal(0);
                  emrQcList.setFlawWxgTotal(0);
                  emrQcList.setFlawProportionB((new BigDecimal(0)).doubleValue());
                  emrQcList.setFlawProportion("-");
                  emrQcList.setGrandTotalProportion(grandProportion + "%");
               }
            }
         }

         for(int i = 0; i < emrQcListList.size(); ++i) {
            if (i < 6) {
               emrQcListRes.add(emrQcListList.get(i));
            }
         }

         List<EmrQcListStatisticByMrTypeVo> emrQcListSix = (List)emrQcListRes.stream().filter((t) -> t.getFlawTotal() != null && t.getFlawTotal() >= 0).collect(Collectors.toList());
         Integer sixSun = 0;
         Double sixSunB = (double)0.0F;
         if (emrQcListSix != null && emrQcListSix.size() > 0 && sumTotal > 0) {
            sixSun = emrQcListSix.stream().mapToInt(EmrQcListStatisticByMrTypeVo::getFlawTotal).sum();
            sixSunB = emrQcListSix.stream().mapToDouble(EmrQcListStatisticByMrTypeVo::getFlawProportionB).sum();
         }

         EmrQcListStatisticByMrTypeVo emrQcListseven = new EmrQcListStatisticByMrTypeVo();
         if (emrQcListSix != null && emrQcListSix.size() > 0 && sumTotal > 0) {
            emrQcListseven.setFlawTotal(((EmrQcListStatisticByMrTypeVo)emrQcListRes.get(0)).getFlawSum() - sixSun);
         } else {
            emrQcListseven.setFlawTotal(0);
         }

         emrQcListseven.setFlawProportion((new BigDecimal(100)).subtract(new BigDecimal(Double.toString(sixSunB))) + "%");
         emrQcListRes.add(emrQcListseven);
      }

      return emrQcListRes;
   }

   public List selectQcListByMrTypeTopTen(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      List<EmrQcListStatisticByMrTypeVo> emrQcListList = this.emrQcListStatisMapper.selectQcListByMrType(emrQcFlowStatis);
      List<EmrQcListStatisticByMrTypeVo> emrQcListTopTen = new ArrayList();
      List<EmrQcListStatisticByMrTypeVo> emrQcListLaterTen = new ArrayList();
      if (emrQcListList != null && emrQcListList.size() > 0) {
         List<EmrQcListStatisticByMrTypeVo> emrQcListListD = (List)emrQcListList.stream().filter((t) -> t.getFlawTotal() != null && t.getFlawTotal() >= 0).collect(Collectors.toList());
         Integer sumTotal = emrQcListListD.stream().mapToInt(EmrQcListStatisticByMrTypeVo::getFlawTotal).sum();
         if (sumTotal > 0) {
            BigDecimal grandProportion = new BigDecimal(0);

            for(EmrQcListStatisticByMrTypeVo emrQcList : emrQcListList) {
               emrQcList.setFlawSum(sumTotal);
               BigDecimal sumTotalB = new BigDecimal(sumTotal);
               if (emrQcList.getFlawTotal() != null && emrQcList.getFlawTotal() > 0) {
                  BigDecimal flawTotal = new BigDecimal(emrQcList.getFlawTotal());
                  BigDecimal zb = flawTotal.divide(sumTotalB, 3, 4).multiply(new BigDecimal(100)).setScale(1);
                  grandProportion = zb.add(grandProportion);
                  emrQcList.setFlawProportionB(zb.doubleValue());
                  emrQcList.setFlawProportion(zb + "%");
                  emrQcList.setGrandTotalProportionB(grandProportion);
                  emrQcList.setGrandTotalProportion(grandProportion + "%");
               } else {
                  emrQcList.setFlawTotal(0);
                  emrQcList.setFlawFileTotal(0);
                  emrQcList.setFlawYxgTotal(0);
                  emrQcList.setFlawYwTotal(0);
                  emrQcList.setFlawWxgTotal(0);
                  emrQcList.setFlawProportionB((new BigDecimal(0)).doubleValue());
                  emrQcList.setFlawProportion("-");
                  emrQcList.setGrandTotalProportionB(grandProportion);
                  emrQcList.setGrandTotalProportion(grandProportion + "%");
               }
            }
         }

         for(int i = 0; i < emrQcListList.size(); ++i) {
            if (i < 10) {
               emrQcListTopTen.add(emrQcListList.get(i));
            } else {
               emrQcListLaterTen.add(emrQcListList.get(i));
            }
         }

         List<EmrQcListStatisticByMrTypeVo> emrQcListLasterTenYxg = (List)emrQcListLaterTen.stream().filter((t) -> t.getFlawYxgTotal() != null && t.getFlawYxgTotal() >= 0).collect(Collectors.toList());
         Integer yxgSun = emrQcListLasterTenYxg.stream().mapToInt(EmrQcListStatisticByMrTypeVo::getFlawYxgTotal).sum();
         List<EmrQcListStatisticByMrTypeVo> emrQcListLasterTenYw = (List)emrQcListLaterTen.stream().filter((t) -> t.getFlawYwTotal() != null && t.getFlawYwTotal() >= 0).collect(Collectors.toList());
         Integer ywSun = emrQcListLasterTenYw.stream().mapToInt(EmrQcListStatisticByMrTypeVo::getFlawYwTotal).sum();
         List<EmrQcListStatisticByMrTypeVo> emrQcListLasterTenWxg = (List)emrQcListLaterTen.stream().filter((t) -> t.getFlawWxgTotal() != null && t.getFlawWxgTotal() >= 0).collect(Collectors.toList());
         Integer wxgSun = emrQcListLasterTenWxg.stream().mapToInt(EmrQcListStatisticByMrTypeVo::getFlawWxgTotal).sum();
         EmrQcListStatisticByMrTypeVo emrQcListLaster = new EmrQcListStatisticByMrTypeVo();
         emrQcListLaster.setFlawYxgTotal(yxgSun);
         emrQcListLaster.setFlawYwTotal(ywSun);
         emrQcListLaster.setFlawWxgTotal(wxgSun);
         emrQcListLaster.setGrandTotalProportion("100%");
         emrQcListTopTen.add(emrQcListLaster);
      }

      return emrQcListTopTen;
   }

   public List selectQcListByMrType(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      List<EmrQcListStatisticByMrTypeVo> emrQcListList = this.emrQcListStatisMapper.selectQcListByMrType(emrQcFlowStatis);
      if (emrQcListList != null && emrQcListList.size() > 0) {
         List<EmrQcListStatisticByMrTypeVo> emrQcListListD = (List)emrQcListList.stream().filter((t) -> t.getFlawTotal() != null && t.getFlawTotal() >= 0).collect(Collectors.toList());
         Integer sumTotal = emrQcListListD.stream().mapToInt(EmrQcListStatisticByMrTypeVo::getFlawTotal).sum();
         if (sumTotal > 0) {
            BigDecimal grandProportion = new BigDecimal(0);

            for(EmrQcListStatisticByMrTypeVo emrQcList : emrQcListList) {
               emrQcList.setFlawSum(sumTotal);
               BigDecimal sumTotalB = new BigDecimal(sumTotal);
               if (emrQcList.getFlawTotal() != null && emrQcList.getFlawTotal() > 0) {
                  BigDecimal flawTotal = new BigDecimal(emrQcList.getFlawTotal());
                  BigDecimal zb = flawTotal.divide(sumTotalB, 2, 4).multiply((new BigDecimal(100)).setScale(1));
                  grandProportion = zb.add(grandProportion);
                  emrQcList.setFlawProportion(zb + "%");
                  emrQcList.setGrandTotalProportion(grandProportion + "%");
               } else {
                  emrQcList.setFlawTotal(0);
                  emrQcList.setFlawFileTotal(0);
                  emrQcList.setFlawYxgTotal(0);
                  emrQcList.setFlawYwTotal(0);
                  emrQcList.setFlawWxgTotal(0);
                  emrQcList.setFlawProportion("-");
                  emrQcList.setGrandTotalProportion(grandProportion + "%");
               }
            }
         }
      }

      return emrQcListList;
   }

   public List selectWorkloadTable(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      List<EmrQcFlowStatis> emrQcFlowStatisList = this.emrQcFlowStatisMapper.selectFlawTablePage(emrQcFlowStatis);
      List<EmrQcFlowStatis> infoList = this.emrQcFlowStatisMapper.selectFlawTableInfo(emrQcFlowStatis);
      List<EmrQcFlowStatis> checkList = this.emrQcFlowStatisMapper.selectFlawTableCheck(emrQcFlowStatis);
      if (emrQcFlowStatisList != null && !emrQcFlowStatisList.isEmpty()) {
         Map<String, List<EmrQcFlowStatis>> infoMap = (Map)infoList.stream().collect(Collectors.groupingBy((s) -> s.getDoctCd()));
         Map<String, List<EmrQcFlowStatis>> checkMap = (Map)checkList.stream().collect(Collectors.groupingBy((s) -> s.getDoctCd()));

         for(EmrQcFlowStatis statis : emrQcFlowStatisList) {
            List<EmrQcFlowStatis> flowList = (List)infoMap.get(statis.getDoctCd());
            List<EmrQcFlowStatis> checkMapList = (List)checkMap.get(statis.getDoctCd());
            Integer flowCount = flowList != null && !flowList.isEmpty() ? (Integer)flowList.stream().collect(Collectors.summingInt(EmrQcFlowStatis::getValueTotal)) : 0;
            Integer flowListSize = flowList != null && !flowList.isEmpty() ? flowList.size() : 0;
            statis.setFlow2(flowListSize);
            Integer unFlowList = statis.getValueTotal() - flowListSize;
            statis.setFlow1(unFlowList);
            statis.setFlowRatio1(String.format("%.1f", (double)flowListSize * (double)100.0F / (double)statis.getValueTotal()) + "%");
            statis.setFlow3(flowCount);
            Integer checkCount = checkMapList != null && !checkMapList.isEmpty() ? checkMapList.size() : 0;
            statis.setFlow4(checkCount);
            statis.setFlowRatio2(String.format("%.1f", (double)checkCount * (double)100.0F / (double)flowListSize) + "%");
         }
      }

      return emrQcFlowStatisList;
   }

   private void setFlowTable(List emrQcFlowStatisList, List emrQcFlowStatisList1) {
      Integer total = (Integer)emrQcFlowStatisList1.stream().collect(Collectors.summingInt(EmrQcFlowStatis::getValueTotal));
      Double addRatio = (double)0.0F;

      for(EmrQcFlowStatis statis : emrQcFlowStatisList) {
         BigDecimal bg = new BigDecimal((double)statis.getValueTotal() * (double)100.0F / (double)total);
         Double ratio = bg.setScale(2, 4).doubleValue();
         statis.setFlowRatio(ratio);
         BigDecimal bg1 = new BigDecimal(addRatio = addRatio + ratio);
         Double ratio1 = bg1.setScale(1, 4).doubleValue();
         statis.setAddRatio(ratio1);
      }

   }

   public List selectCheckFlowTypeDiaLogTable(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      if (StringUtils.isNotEmpty(emrQcFlowStatis.getQcState())) {
         String[] qcStateList = emrQcFlowStatis.getQcState().split(",");
         emrQcFlowStatis.setQcStateList(qcStateList);
      }

      return this.emrQcFlowStatisMapper.selectCheckFlowTypeDiaLogTable(emrQcFlowStatis);
   }

   public List selectCheckEmrTypeDiaLogTable(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      if (StringUtils.isNotEmpty(emrQcFlowStatis.getQcState())) {
         String[] qcStateList = emrQcFlowStatis.getQcState().split(",");
         emrQcFlowStatis.setQcStateList(qcStateList);
      }

      return this.emrQcFlowStatisMapper.selectCheckFlowTypeDiaLogTable(emrQcFlowStatis);
   }

   public List selectCheckEmrTypeDiaLogExport(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      if (StringUtils.isNotEmpty(emrQcFlowStatis.getQcState())) {
         String[] qcStateList = emrQcFlowStatis.getQcState().split(",");
         emrQcFlowStatis.setQcStateList(qcStateList);
      }

      List<EmrQcListVo> emrQcListVoList = this.emrQcFlowStatisMapper.selectCheckFlowTypeDiaLogTable(emrQcFlowStatis);
      List<EmrQcExportListVo> emrQcExportListVoList = (List)emrQcListVoList.stream().map((a) -> {
         EmrQcExportListVo emrQcExportListVo = new EmrQcExportListVo();
         BeanUtils.copyProperties(a, emrQcExportListVo);
         return emrQcExportListVo;
      }).collect(Collectors.toList());
      if (emrQcExportListVoList != null && emrQcExportListVoList.size() > 0) {
         for(EmrQcExportListVo emrQcExportListVo : emrQcExportListVoList) {
            if ("0".equals(emrQcExportListVo.getQcState())) {
               emrQcExportListVo.setQcStateName("未处理");
            } else if ("1".equals(emrQcExportListVo.getQcState())) {
               emrQcExportListVo.setQcStateName("未处理");
            } else if ("2".equals(emrQcExportListVo.getQcState())) {
               emrQcExportListVo.setQcStateName("已转人工");
            } else if ("3".equals(emrQcExportListVo.getQcState())) {
               emrQcExportListVo.setQcStateName("未处理");
            } else if ("4".equals(emrQcExportListVo.getQcState())) {
               emrQcExportListVo.setQcStateName("未处理");
            } else if ("5".equals(emrQcExportListVo.getQcState())) {
               emrQcExportListVo.setQcStateName("确认已修改");
            } else if ("6".equals(emrQcExportListVo.getQcState())) {
               emrQcExportListVo.setQcStateName("已确认有误");
            }
         }
      }

      return emrQcExportListVoList;
   }

   public List selectCheckDoctDialogTable(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      if (StringUtils.isNotEmpty(emrQcFlowStatis.getQcState())) {
         String[] qcStateList = emrQcFlowStatis.getQcState().split(",");
         emrQcFlowStatis.setQcStateList(qcStateList);
      }

      List<EmrQcListVo> emrQcListVoList = this.emrQcFlowStatisMapper.selectCheckFlowTypeDiaLogTable(emrQcFlowStatis);
      if (emrQcListVoList != null && emrQcListVoList.size() > 0) {
         for(EmrQcListVo emrQcListVo : emrQcListVoList) {
            if ("0".equals(emrQcListVo.getQcState())) {
               emrQcListVo.setQcStateName("未处理");
            } else if ("1".equals(emrQcListVo.getQcState())) {
               emrQcListVo.setQcStateName("未处理");
            } else if ("2".equals(emrQcListVo.getQcState())) {
               emrQcListVo.setQcStateName("已转人工");
            } else if ("3".equals(emrQcListVo.getQcState())) {
               emrQcListVo.setQcStateName("未处理");
            } else if ("4".equals(emrQcListVo.getQcState())) {
               emrQcListVo.setQcStateName("未处理");
            } else if ("5".equals(emrQcListVo.getQcState())) {
               emrQcListVo.setQcStateName("确认已修改");
            } else if ("6".equals(emrQcListVo.getQcState())) {
               emrQcListVo.setQcStateName("已确认有误");
            }
         }
      }

      return emrQcListVoList;
   }

   public List selectCheckDeptDialogTable(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      if (StringUtils.isNotEmpty(emrQcFlowStatis.getQcState())) {
         String[] qcStateList = emrQcFlowStatis.getQcState().split(",");
         emrQcFlowStatis.setQcStateList(qcStateList);
      }

      List<EmrQcListVo> emrQcListVoList = this.emrQcFlowStatisMapper.selectCheckFlowTypeDiaLogTable(emrQcFlowStatis);
      if (emrQcListVoList != null && emrQcListVoList.size() > 0) {
         for(EmrQcListVo emrQcListVo : emrQcListVoList) {
            if ("0".equals(emrQcListVo.getQcState())) {
               emrQcListVo.setQcStateName("未处理");
            } else if ("1".equals(emrQcListVo.getQcState())) {
               emrQcListVo.setQcStateName("未处理");
            } else if ("2".equals(emrQcListVo.getQcState())) {
               emrQcListVo.setQcStateName("已转人工");
            } else if ("3".equals(emrQcListVo.getQcState())) {
               emrQcListVo.setQcStateName("未处理");
            } else if ("4".equals(emrQcListVo.getQcState())) {
               emrQcListVo.setQcStateName("未处理");
            } else if ("5".equals(emrQcListVo.getQcState())) {
               emrQcListVo.setQcStateName("确认已修改");
            } else if ("6".equals(emrQcListVo.getQcState())) {
               emrQcListVo.setQcStateName("已确认有误");
            }
         }
      }

      return emrQcListVoList;
   }

   public AjaxResult selectCheckDeptDialogTableForExport(EmrQcFlowStatis emrQcFlowStatis, HttpServletResponse response) throws Exception {
      List<EmrQcListVo> emrQcListVoList = this.selectCheckDeptDialogTable(emrQcFlowStatis);
      List<LinkedHashMap<String, Object>> mapList = new ArrayList();
      List<EmrQcListVo> sizeList = new ArrayList();
      LinkedHashMap<String, Object> tableMap = new LinkedHashMap();
      if (emrQcListVoList != null && !emrQcListVoList.isEmpty()) {
         Map<Long, List<EmrQcListVo>> map = (Map)emrQcListVoList.stream().collect(Collectors.groupingBy((s) -> s.getId()));

         for(Long id : map.keySet()) {
            List<EmrQcListVo> voList = (List)map.get(id);
            if (sizeList.size() < voList.size()) {
               sizeList = voList;
            }
         }

         if (sizeList != null && !sizeList.isEmpty()) {
            for(Long id : map.keySet()) {
               List<EmrQcListVo> voList = (List)map.get(id);
               LinkedHashMap<String, Object> voMap = new LinkedHashMap();
               voMap.put("inDeptName", ((EmrQcListVo)voList.get(0)).getInDeptName());
               voMap.put("inpNo", ((EmrQcListVo)voList.get(0)).getInpNo());
               voMap.put("patientName", ((EmrQcListVo)voList.get(0)).getPatientName());
               voMap.put("doctName", ((EmrQcListVo)voList.get(0)).getDoctName());
               voMap.put("mrTypeName", ((EmrQcListVo)voList.get(0)).getMrTypeName());
               voMap.put("ruleName", ((EmrQcListVo)voList.get(0)).getRuleName());
               voMap.put("flawDesc", ((EmrQcListVo)voList.get(0)).getFlawDesc());
               voMap.put("mrFileName", ((EmrQcListVo)voList.get(0)).getMrFileName());
               voMap.put("emrEleName", ((EmrQcListVo)voList.get(0)).getEmrEleName());
               voMap.put("qcStateName", ((EmrQcListVo)voList.get(0)).getQcStateName());
               mapList.add(voMap);
            }
         }
      }

      tableMap.put("inDeptName", "科室");
      tableMap.put("inpNo", "患者住院号");
      tableMap.put("patientName", "患者姓名");
      tableMap.put("doctName", "责任医师");
      tableMap.put("mrTypeName", "病历类型");
      tableMap.put("ruleName", "缺陷名称");
      tableMap.put("flawDesc", "缺陷描述");
      tableMap.put("mrFileName", "缺陷病历名称");
      tableMap.put("emrEleName", "缺陷类型");
      tableMap.put("qcStateName", "缺陷状态");
      Workbook wb = new SXSSFWorkbook(500);
      String tableName = "科室缺陷统计详情";
      ExcelUtils.getCheckDeptDialogExport(wb, mapList, tableMap, emrQcListVoList, tableName);
      String fileName = tableName + ".xlsx";
      response.setHeader("Content-disposition", "attachment;filename=" + fileName);
      OutputStream stream = new FileOutputStream(ExcelUtils.getAbsoluteFile(fileName));
      if (null != wb && null != stream) {
         wb.write(stream);
         stream.close();
      }

      return AjaxResult.success(fileName);
   }

   public Object selectQcFlowStatis(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      Object obj = new Object();
      List<SysReportCompDetail> repDetailList = this.emrQcFlowStatisMapper.selectReportComDetailList(emrQcFlowStatis.getRepId());
      if (repDetailList != null && !repDetailList.isEmpty()) {
         for(SysReportCompDetail sysReportCompDetail : repDetailList) {
            switch (sysReportCompDetail.getDataSource()) {
               case "1":
                  obj = this.getSqlTypeData(sysReportCompDetail);
               case "2":
               case "3":
               case "4":
            }
         }
      }

      return obj;
   }

   public Object getSqlTypeData(SysReportCompDetail sysReportCompDetail) {
      List<Map<String, Object>> map = new ArrayList();
      switch (sysReportCompDetail.getReturnDataType()) {
         case 1:
            map = this.emrQcFlowStatisMapper.selectStatisListMap(sysReportCompDetail.getSqlStr());
            break;
         case 2:
            this.emrQcFlowStatisMapper.selectStatisMap(sysReportCompDetail.getSqlStr());
            break;
         case 3:
            this.emrQcFlowStatisMapper.selectStatisInteger(sysReportCompDetail.getSqlStr());
            break;
         case 4:
            this.emrQcFlowStatisMapper.selectStatisDouble(sysReportCompDetail.getSqlStr());
      }

      return map;
   }

   public StatisticsResultVo selectCheckFlowTypeCylinder(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      List<String> elemIdList = this.emrQcFlowStatisMapper.selectCheckFlowTypeCylinderEle(emrQcFlowStatis);
      List<EmrQcFlowStatis> emrQcFlowStatisList = this.emrQcFlowStatisMapper.selectCheckFlowTypeCylinderList(emrQcFlowStatis);
      StatisticsResultVo statisticsResultVo = this.setCylinderData(emrQcFlowStatisList, elemIdList);
      return statisticsResultVo;
   }

   public void setCharData(Integer count, Integer totalCount, String name, List charData) throws Exception {
      Double big = totalCount == 0 ? (double)0.0F : (double)count * (double)100.0F / (double)totalCount;
      BigDecimal bg = new BigDecimal(big);
      Double ratio = bg.setScale(2, 4).doubleValue();
      StatementVo statementVo = new StatementVo(name, count, ratio);
      charData.add(statementVo);
   }

   public StatisticsResultVo setCylinderData(List emrQcFlowStatisList, List xAxisIdList) throws Exception {
      StatisticsResultVo resultVo = new StatisticsResultVo();
      if (emrQcFlowStatisList != null && !emrQcFlowStatisList.isEmpty()) {
         Integer total = (Integer)emrQcFlowStatisList.stream().collect(Collectors.summingInt(EmrQcFlowStatis::getValueTotal));
         List<EmrQcFlowStatis> tenList = (List)emrQcFlowStatisList.stream().filter((s) -> xAxisIdList.contains(s.getxAxisID())).collect(Collectors.toList());
         List<EmrQcFlowStatis> otherList = (List)emrQcFlowStatisList.stream().filter((s) -> !xAxisIdList.contains(s.getxAxisID())).collect(Collectors.toList());
         Integer other1 = (Integer)otherList.stream().collect(Collectors.summingInt(EmrQcFlowStatis::getFlow1));
         Integer other2 = (Integer)otherList.stream().collect(Collectors.summingInt(EmrQcFlowStatis::getFlow2));
         Integer other3 = (Integer)otherList.stream().collect(Collectors.summingInt(EmrQcFlowStatis::getFlow3));
         Integer other5 = (Integer)otherList.stream().collect(Collectors.summingInt(EmrQcFlowStatis::getFlow5));
         Integer other6 = (Integer)otherList.stream().collect(Collectors.summingInt(EmrQcFlowStatis::getFlow6));
         Integer other7 = (Integer)otherList.stream().collect(Collectors.summingInt(EmrQcFlowStatis::getFlow7));
         Integer otherTotal = (Integer)otherList.stream().collect(Collectors.summingInt(EmrQcFlowStatis::getValueTotal));
         Map<String, List<EmrQcFlowStatis>> mapList = (Map)((List)tenList.stream().filter((s) -> s.getxAxisID() != null).collect(Collectors.toList())).stream().collect(Collectors.groupingBy((t) -> t.getxAxisID()));
         Double line = (double)0.0F;
         List<String> xAxis = new ArrayList(1);
         List<Integer> totalData = new ArrayList(1);
         List<Double> lineData = new ArrayList(1);
         List<Integer> data1 = new ArrayList(1);
         List<Integer> data2 = new ArrayList(1);
         List<Integer> data3 = new ArrayList(1);
         List<Integer> data5 = new ArrayList(1);
         List<Integer> data6 = new ArrayList(1);
         List<Integer> data7 = new ArrayList(1);
         List<StatementVo> series = new ArrayList(1);

         for(String xAxisId : xAxisIdList) {
            List<EmrQcFlowStatis> flowList = (List)mapList.get(xAxisId);
            if (flowList != null && !flowList.isEmpty()) {
               xAxis.add(((EmrQcFlowStatis)flowList.get(0)).getxAxisName());
               data1.add(((EmrQcFlowStatis)flowList.get(0)).getFlow1());
               data2.add(((EmrQcFlowStatis)flowList.get(0)).getFlow2());
               data3.add(((EmrQcFlowStatis)flowList.get(0)).getFlow3());
               data5.add(((EmrQcFlowStatis)flowList.get(0)).getFlow5());
               data6.add(((EmrQcFlowStatis)flowList.get(0)).getFlow6());
               data7.add(((EmrQcFlowStatis)flowList.get(0)).getFlow7());
               totalData.add(((EmrQcFlowStatis)flowList.get(0)).getValueTotal());
               BigDecimal bg = new BigDecimal((double)((EmrQcFlowStatis)flowList.get(0)).getValueTotal() * (double)100.0F / (double)total);
               Double ratio = bg.setScale(2, 4).doubleValue();
               BigDecimal bg1 = new BigDecimal(line = line + ratio);
               Double ratio1 = bg1.setScale(1, 4).doubleValue();
               lineData.add(ratio1);
            }
         }

         if (xAxisIdList.size() >= 10) {
            xAxis.add("其他病历类型");
            data1.add(other1);
            data2.add(other2);
            data3.add(other3);
            data5.add(other5);
            data6.add(other6);
            data7.add(other7);
            totalData.add(otherTotal);
            lineData.add((double)100.0F);
            StatementVo lineState = new StatementVo("累计占比%", lineData, 0);
            resultVo.setLineData(lineState);
         }

         StatementVo totalState = new StatementVo("总数", totalData);
         StatementVo state1 = new StatementVo("已修改缺陷", data1);
         StatementVo state2 = new StatementVo("未修改缺陷", data2);
         StatementVo state3 = new StatementVo("质控有误", data3);
         StatementVo state5 = new StatementVo("确认已修改", data5);
         StatementVo state6 = new StatementVo("已确认有误", data6);
         StatementVo state7 = new StatementVo("病历已删除", data7);
         series.add(state1);
         series.add(state2);
         series.add(state3);
         series.add(state5);
         series.add(state6);
         series.add(state7);
         resultVo.setxAxis(xAxis);
         resultVo.setSeries(series);
         resultVo.setTotalData(totalState);
      }

      return resultVo;
   }

   public StatisticsResultVo setCylinderDataNoLine(List emrQcFlowStatisList) throws Exception {
      StatisticsResultVo resultVo = new StatisticsResultVo();
      List<StatementVo> series = new ArrayList();
      List<Integer> data1 = new ArrayList();
      List<Integer> data2 = new ArrayList();
      List<Integer> data3 = new ArrayList();
      List<Integer> data4 = new ArrayList();
      List<Integer> data5 = new ArrayList();
      List<Integer> data7 = new ArrayList();
      List<Integer> totalData = new ArrayList();
      List<String> xAxis = new ArrayList();

      for(int i = 0; i < emrQcFlowStatisList.size() && i < 10; ++i) {
         xAxis.add(((EmrQcFlowStatis)emrQcFlowStatisList.get(i)).getxAxisName());
         data1.add(((EmrQcFlowStatis)emrQcFlowStatisList.get(i)).getFlow1());
         data2.add(((EmrQcFlowStatis)emrQcFlowStatisList.get(i)).getFlow2());
         data3.add(((EmrQcFlowStatis)emrQcFlowStatisList.get(i)).getFlow3());
         data4.add(((EmrQcFlowStatis)emrQcFlowStatisList.get(i)).getFlow4());
         data5.add(((EmrQcFlowStatis)emrQcFlowStatisList.get(i)).getFlow5());
         data7.add(((EmrQcFlowStatis)emrQcFlowStatisList.get(i)).getFlow7());
         totalData.add(((EmrQcFlowStatis)emrQcFlowStatisList.get(i)).getValueTotal());
      }

      StatementVo state1 = new StatementVo("已修改缺陷", data1);
      StatementVo state2 = new StatementVo("未修改缺陷", data2);
      StatementVo state3 = new StatementVo("质控有误", data3);
      StatementVo state4 = new StatementVo("确认已修改", data4);
      StatementVo state5 = new StatementVo("已确认有误", data5);
      StatementVo state7 = new StatementVo("病历已删除", data7);
      StatementVo stateTotal = new StatementVo("总数", totalData);
      series.add(state1);
      series.add(state2);
      series.add(state3);
      series.add(state4);
      series.add(state5);
      series.add(state7);
      resultVo.setSeries(series);
      resultVo.setxAxis(xAxis);
      resultVo.setTotalData(stateTotal);
      return resultVo;
   }

   public StatisticsResultVo selectCheckEmrTypePie(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      Integer total = this.emrQcFlowStatisMapper.selectCheckEmrTotal(emrQcFlowStatis);
      List<EmrQcFlowStatis> emrClassList = this.emrQcFlowStatisMapper.selectCheckEmrClassTotal(emrQcFlowStatis);
      StatisticsResultVo resultVo = this.setScorePie(emrClassList, total);
      return resultVo;
   }

   public StatisticsResultVo selectCheckEmrTypeCylinder(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      List<String> typeList = this.emrQcFlowStatisMapper.selectCheckEmrTypeTopTen(emrQcFlowStatis);
      List<EmrQcFlowStatis> emrTypeList = this.emrQcFlowStatisMapper.selectCheckEmrTypeList(emrQcFlowStatis);
      StatisticsResultVo statisticsResultVo = this.setCylinderData(emrTypeList, typeList);
      return statisticsResultVo;
   }

   public List selectCheckEmrTypeTable(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      List<EmrQcFlowStatis> emrTypeList = this.emrQcFlowStatisMapper.selectCheckEmrTypeList(emrQcFlowStatis);
      List<EmrQcFlowStatis> emrTypeList1 = this.emrQcFlowStatisMapper.selectCheckEmrTypeList(emrQcFlowStatis);
      if (emrTypeList != null && !emrTypeList.isEmpty()) {
         this.setFlowTable(emrTypeList, emrTypeList1);
      }

      return emrTypeList;
   }

   public List selectCheckDoctTable(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      return this.emrQcFlowStatisMapper.selectCheckDoctTable(emrQcFlowStatis);
   }

   public StatisticsResultVo selectCheckTopDoctCylinder(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      StatisticsResultVo resultVo = new StatisticsResultVo();
      List<EmrQcFlowStatis> doctList = this.emrQcFlowStatisMapper.selectCheckDoctList(emrQcFlowStatis);
      if (doctList != null && !doctList.isEmpty()) {
         doctList.sort(Comparator.comparing(EmrQcFlowStatis::getValueTotal).reversed());
         resultVo = this.setCylinderDataNoLine(doctList);
      }

      return resultVo;
   }

   public StatisticsResultVo selectCheckAfterDoctCylinder(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      StatisticsResultVo resultVo = new StatisticsResultVo();
      List<EmrQcFlowStatis> doctList = this.emrQcFlowStatisMapper.selectCheckDoctList(emrQcFlowStatis);
      if (doctList != null && !doctList.isEmpty()) {
         doctList.sort(Comparator.comparing(EmrQcFlowStatis::getValueTotal));
         List<EmrQcFlowStatis> afterList = new ArrayList();

         for(int i = 0; i < doctList.size() && i < 10; ++i) {
            afterList.add(doctList.get(i));
         }

         afterList.sort(Comparator.comparing(EmrQcFlowStatis::getValueTotal).reversed());
         resultVo = this.setCylinderDataNoLine(afterList);
      }

      return resultVo;
   }

   public StatisticsResultVo selectCheckTopDeptCylinder(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      StatisticsResultVo resultVo = new StatisticsResultVo();
      List<EmrQcFlowStatis> doctList = this.emrQcFlowStatisMapper.selectCheckDeptList(emrQcFlowStatis);
      if (doctList != null && !doctList.isEmpty()) {
         doctList.sort(Comparator.comparing(EmrQcFlowStatis::getValueTotal).reversed());
         resultVo = this.setCylinderDataNoLine(doctList);
      }

      return resultVo;
   }

   public StatisticsResultVo selectCheckAfterDeptCylinder(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      StatisticsResultVo resultVo = new StatisticsResultVo();
      List<EmrQcFlowStatis> doctList = this.emrQcFlowStatisMapper.selectCheckDeptList(emrQcFlowStatis);
      if (doctList != null && !doctList.isEmpty()) {
         doctList.sort(Comparator.comparing(EmrQcFlowStatis::getValueTotal));
         List<EmrQcFlowStatis> afterList = new ArrayList();

         for(int i = 0; i < doctList.size() && i < 10; ++i) {
            afterList.add(doctList.get(i));
         }

         afterList.sort(Comparator.comparing(EmrQcFlowStatis::getValueTotal).reversed());
         resultVo = this.setCylinderDataNoLine(afterList);
      }

      return resultVo;
   }

   public List selectCheckDeptTable(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      return this.emrQcFlowStatisMapper.selectCheckDeptList(emrQcFlowStatis);
   }

   public StatisticsResultVo selectMissingFileCount() throws Exception {
      StatisticsResultVo statisticsResultVo = new StatisticsResultVo();
      List<EmrQcFlowStatis> emrQcFlowStatisList = this.emrQcFlowStatisMapper.selectMissingFileCount();
      if (emrQcFlowStatisList != null && !emrQcFlowStatisList.isEmpty()) {
         Integer total = (Integer)emrQcFlowStatisList.stream().collect(Collectors.summingInt(EmrQcFlowStatis::getValueTotal));
         statisticsResultVo.setTotal(total);
         statisticsResultVo.setEmrQcFlowStatisList(emrQcFlowStatisList);
      }

      return statisticsResultVo;
   }

   public StatisticsResultVo selectMissingFilePie() throws Exception {
      StatisticsResultVo statisticsResultVo = new StatisticsResultVo();
      List<EmrQcFlowStatis> emrQcFlowStatisList = this.emrQcFlowStatisMapper.selectMissingFileCount();
      if (emrQcFlowStatisList != null && !emrQcFlowStatisList.isEmpty()) {
         Integer total = (Integer)emrQcFlowStatisList.stream().collect(Collectors.summingInt(EmrQcFlowStatis::getValueTotal));
         statisticsResultVo = this.setScorePie(emrQcFlowStatisList, total);
      }

      return statisticsResultVo;
   }

   public StatisticsResultVo selectMissingFileColumn() throws Exception {
      StatisticsResultVo statisticsResultVo = new StatisticsResultVo();
      List<EmrQcFlowStatis> emrQcFlowStatisList = this.emrQcFlowStatisMapper.selectMissingFileDeptCount();
      if (emrQcFlowStatisList != null && !emrQcFlowStatisList.isEmpty()) {
         List<String> xAxis = new ArrayList();
         List<Integer> data = new ArrayList();
         List<StatementVo> statementVoList = new ArrayList();

         for(EmrQcFlowStatis emrQcFlowStatis : emrQcFlowStatisList) {
            xAxis.add(emrQcFlowStatis.getDeptName());
            data.add(emrQcFlowStatis.getValueTotal());
         }

         StatementVo statementVo = new StatementVo("数量", data);
         statementVoList.add(statementVo);
         statisticsResultVo.setxAxis(xAxis);
         statisticsResultVo.setSeries(statementVoList);
      }

      return statisticsResultVo;
   }

   public List selectMissingFileTable(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      return this.emrQcFlowStatisMapper.selectMissingFileTable(emrQcFlowStatis);
   }

   public StatisticsResultVo selectTimeOutEmrPie(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      StatisticsResultVo statisticsResultVo = new StatisticsResultVo();
      List<EmrQcFlowStatis> emrQcFlowStatisList = this.emrQcFlowStatisMapper.selectTimeOutEmrPie(emrQcFlowStatis);
      if (emrQcFlowStatisList != null && !emrQcFlowStatisList.isEmpty()) {
         Integer total = (Integer)emrQcFlowStatisList.stream().collect(Collectors.summingInt(EmrQcFlowStatis::getValueTotal));
         statisticsResultVo = this.setScorePie(emrQcFlowStatisList, total);
      }

      return statisticsResultVo;
   }

   public StatisticsResultVo selectTimeOutEmrColumn(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      StatisticsResultVo statisticsResultVo = new StatisticsResultVo();
      List<EmrQcFlowStatis> emrQcFlowStatisList = this.emrQcFlowStatisMapper.selectTimeOutEmrTotalByDoct(emrQcFlowStatis);
      List<EmrQcFlowStatis> writeEmr = this.emrQcFlowStatisMapper.selectTimeOutEmrWriteByDoct(emrQcFlowStatis);
      List<EmrQcFlowStatis> notWriteEmr = this.emrQcFlowStatisMapper.selectTimeOutEmrUnWriteByDoct(emrQcFlowStatis);
      if (emrQcFlowStatisList != null && !emrQcFlowStatisList.isEmpty()) {
         List<Integer> data1 = new ArrayList();
         List<Integer> data2 = new ArrayList();
         List<Integer> totalData = new ArrayList();
         List<StatementVo> statementVoList = new ArrayList();
         List<String> xAxis = new ArrayList();

         for(int z = 0; z < emrQcFlowStatisList.size() && z < 10; ++z) {
            xAxis.add(((EmrQcFlowStatis)emrQcFlowStatisList.get(z)).getDoctName());
            String doctCd = ((EmrQcFlowStatis)emrQcFlowStatisList.get(z)).getDoctCd() == null ? "" : ((EmrQcFlowStatis)emrQcFlowStatisList.get(z)).getDoctCd();
            List<EmrQcFlowStatis> writeList = (List)((List)writeEmr.stream().filter((s) -> s.getDoctCd() != null).collect(Collectors.toList())).stream().filter((s) -> doctCd.equals(s.getDoctCd())).collect(Collectors.toList());
            List<EmrQcFlowStatis> unWriteList = (List)((List)notWriteEmr.stream().filter((s) -> s.getDoctCd() != null).collect(Collectors.toList())).stream().filter((s) -> s.getDoctCd().equals(doctCd)).collect(Collectors.toList());
            data1.add(writeList != null && !writeList.isEmpty() ? ((EmrQcFlowStatis)writeList.get(0)).getValueTotal() : 0);
            data2.add(unWriteList != null && !unWriteList.isEmpty() ? ((EmrQcFlowStatis)unWriteList.get(0)).getValueTotal() : 0);
            totalData.add(((EmrQcFlowStatis)emrQcFlowStatisList.get(z)).getValueTotal());
         }

         StatementVo totalState = new StatementVo("总数", totalData);
         StatementVo state1 = new StatementVo("超时已书写", data1);
         StatementVo state2 = new StatementVo("超时未书写", data2);
         statementVoList.add(state1);
         statementVoList.add(state2);
         statisticsResultVo.setTotalData(totalState);
         statisticsResultVo.setSeries(statementVoList);
         statisticsResultVo.setxAxis(xAxis);
      }

      return statisticsResultVo;
   }

   public List selectTimeOutEmrTable(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      PageDomain pageDomain = TableSupport.buildPageRequest();
      Integer pageNum = pageDomain.getPageNum();
      Integer pageSize = pageDomain.getPageSize();
      if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
         String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
         PageHelper.startPage(pageNum, pageSize, orderBy);
      }

      List<EmrQcFlowStatis> emrQcFlowStatisList = this.emrQcFlowStatisMapper.selectTimeOutEmrTotal(emrQcFlowStatis);
      List<EmrQcFlowStatis> writeEmr = this.emrQcFlowStatisMapper.selectTimeOutEmrWrite(emrQcFlowStatis);
      List<EmrQcFlowStatis> notWriteEmr = this.emrQcFlowStatisMapper.selectTimeOutEmrUnWrite(emrQcFlowStatis);
      if (emrQcFlowStatisList != null && !emrQcFlowStatisList.isEmpty()) {
         for(EmrQcFlowStatis statis : emrQcFlowStatisList) {
            List<EmrQcFlowStatis> writeList = (List)writeEmr.stream().filter((s) -> s.getDoctCd().equals(statis.getDoctCd()) && s.getDeptCd().equals(statis.getDeptCd())).collect(Collectors.toList());
            List<EmrQcFlowStatis> unWriteList = (List)((List)notWriteEmr.stream().filter((s) -> s.getDoctCd() != null).collect(Collectors.toList())).stream().filter((s) -> s.getDoctCd().equals(statis.getDoctCd()) && s.getDeptCd().equals(statis.getDeptCd())).collect(Collectors.toList());
            statis.setFlow1(writeList != null && !writeList.isEmpty() ? ((EmrQcFlowStatis)writeList.get(0)).getValueTotal() : 0);
            statis.setFlow2(unWriteList != null && !unWriteList.isEmpty() ? ((EmrQcFlowStatis)unWriteList.get(0)).getValueTotal() : 0);
         }
      }

      return emrQcFlowStatisList;
   }

   public List selectTimeOutEmrTableInfo(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      PageDomain pageDomain = TableSupport.buildPageRequest();
      Integer pageNum = pageDomain.getPageNum();
      Integer pageSize = pageDomain.getPageSize();
      if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
         String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
         PageHelper.startPage(pageNum, pageSize, orderBy);
      }

      return this.emrQcFlowStatisMapper.selectTimeOutEmrTableInfo(emrQcFlowStatis);
   }

   public StatisticsResultVo selectDoctScorePie(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      Integer total = this.emrQcFlowStatisMapper.selectScoreEmrTotal(emrQcFlowStatis);
      List<EmrQcFlowStatis> emrQcFlowStatisList = this.emrQcFlowStatisMapper.selectDoctScorePie(emrQcFlowStatis);
      StatisticsResultVo resultVo = this.setScorePie(emrQcFlowStatisList, total);
      return resultVo;
   }

   public StatisticsResultVo selectDoctScoreDeptPie(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      Integer total = this.emrQcFlowStatisMapper.selectScoreEmrTotal(emrQcFlowStatis);
      List<EmrQcFlowStatis> emrQcFlowStatisList = this.emrQcFlowStatisMapper.selectDoctScoreDeptPie(emrQcFlowStatis);
      StatisticsResultVo resultVo = this.setScorePie(emrQcFlowStatisList, total);
      return resultVo;
   }

   public StatisticsResultVo selectDoctScoreCheckPie(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      Integer total = this.emrQcFlowStatisMapper.selectScoreEmrTotal(emrQcFlowStatis);
      List<EmrQcFlowStatis> emrQcFlowStatisList = this.emrQcFlowStatisMapper.selectDoctScoreCheckPie(emrQcFlowStatis);
      StatisticsResultVo resultVo = this.setScorePie(emrQcFlowStatisList, total);
      return resultVo;
   }

   private StatisticsResultVo setScorePie(List emrQcFlowStatisList, Integer total) throws Exception {
      StatisticsResultVo resultVo = new StatisticsResultVo();
      resultVo.setTotal(total);
      if (emrQcFlowStatisList != null && !emrQcFlowStatisList.isEmpty()) {
         List<StatementVo> charData = new ArrayList();

         for(EmrQcFlowStatis statis : emrQcFlowStatisList) {
            this.setCharData(statis.getValueTotal(), total, statis.getxAxisName(), charData);
         }

         resultVo.setCharData(charData);
      }

      return resultVo;
   }

   private StatisticsResultVo setScoreCloumn(List emrQcFlowStatisList) {
      StatisticsResultVo resultVo = new StatisticsResultVo();
      List<String> xAxis = new ArrayList();
      List<StatementVo> series = new ArrayList();
      List<Double> data1 = new ArrayList();

      for(int i = 0; i < emrQcFlowStatisList.size() && i < 10; ++i) {
         xAxis.add(((EmrQcFlowStatis)emrQcFlowStatisList.get(i)).getxAxisName());
         data1.add(((EmrQcFlowStatis)emrQcFlowStatisList.get(i)).getAvgScore());
      }

      StatementVo stateTotal = new StatementVo("平均分", data1, 0);
      series.add(stateTotal);
      resultVo.setSeries(series);
      resultVo.setxAxis(xAxis);
      return resultVo;
   }

   private List setScoreReversed(List emrQcFlowStatisList) {
      new StatisticsResultVo();
      emrQcFlowStatisList.sort(Comparator.comparing(EmrQcFlowStatis::getAvgScore));
      List<EmrQcFlowStatis> afterList = new ArrayList();

      for(int i = 0; i < emrQcFlowStatisList.size() && i < 10; ++i) {
         afterList.add(emrQcFlowStatisList.get(i));
      }

      afterList.sort(Comparator.comparing(EmrQcFlowStatis::getAvgScore).reversed());
      return afterList;
   }

   public StatisticsResultVo selectDoctScoreTopCloumn(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      StatisticsResultVo resultVo = new StatisticsResultVo();
      List<EmrQcFlowStatis> emrQcFlowStatisList = this.emrQcFlowStatisMapper.selectDoctScoreCloumn(emrQcFlowStatis);
      if (emrQcFlowStatisList != null && !emrQcFlowStatisList.isEmpty()) {
         resultVo = this.setScoreCloumn(emrQcFlowStatisList);
      }

      return resultVo;
   }

   public StatisticsResultVo selectDoctScoreAfterCloumn(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      StatisticsResultVo resultVo = new StatisticsResultVo();
      List<EmrQcFlowStatis> emrQcFlowStatisList = this.emrQcFlowStatisMapper.selectDoctScoreCloumn(emrQcFlowStatis);
      if (emrQcFlowStatisList != null && !emrQcFlowStatisList.isEmpty()) {
         List<EmrQcFlowStatis> afterList = this.setScoreReversed(emrQcFlowStatisList);
         resultVo = this.setScoreCloumn(afterList);
      }

      return resultVo;
   }

   public StatisticsResultVo selectDoctScoreDeptTopCloumn(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      StatisticsResultVo resultVo = new StatisticsResultVo();
      List<EmrQcFlowStatis> emrQcFlowStatisList = this.emrQcFlowStatisMapper.selectDoctScoreDeptCloumn(emrQcFlowStatis);
      if (emrQcFlowStatisList != null && !emrQcFlowStatisList.isEmpty()) {
         resultVo = this.setScoreCloumn(emrQcFlowStatisList);
      }

      return resultVo;
   }

   public StatisticsResultVo selectDoctScoreDeptAfterCloumn(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      StatisticsResultVo resultVo = new StatisticsResultVo();
      List<EmrQcFlowStatis> emrQcFlowStatisList = this.emrQcFlowStatisMapper.selectDoctScoreDeptCloumn(emrQcFlowStatis);
      if (emrQcFlowStatisList != null && !emrQcFlowStatisList.isEmpty()) {
         List<EmrQcFlowStatis> afterList = this.setScoreReversed(emrQcFlowStatisList);
         resultVo = this.setScoreCloumn(afterList);
      }

      return resultVo;
   }

   public StatisticsResultVo selectDoctScoreCheckTopCloumn(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      StatisticsResultVo resultVo = new StatisticsResultVo();
      List<EmrQcFlowStatis> emrQcFlowStatisList = this.emrQcFlowStatisMapper.selectDoctScoreCheckCloumn(emrQcFlowStatis);
      if (emrQcFlowStatisList != null && !emrQcFlowStatisList.isEmpty()) {
         resultVo = this.setScoreCloumn(emrQcFlowStatisList);
      }

      return resultVo;
   }

   public StatisticsResultVo selectDoctScoreCheckAfterCloumn(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      StatisticsResultVo resultVo = new StatisticsResultVo();
      List<EmrQcFlowStatis> emrQcFlowStatisList = this.emrQcFlowStatisMapper.selectDoctScoreCheckCloumn(emrQcFlowStatis);
      if (emrQcFlowStatisList != null && !emrQcFlowStatisList.isEmpty()) {
         List<EmrQcFlowStatis> afterList = this.setScoreReversed(emrQcFlowStatisList);
         resultVo = this.setScoreCloumn(afterList);
      }

      return resultVo;
   }

   public StatisticsResultVo selectDeptScoreTopCloumn(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      StatisticsResultVo resultVo = new StatisticsResultVo();
      List<EmrQcFlowStatis> emrQcFlowStatisList = this.emrQcFlowStatisMapper.selectDeptScoreCloumn(emrQcFlowStatis);
      if (emrQcFlowStatisList != null && !emrQcFlowStatisList.isEmpty()) {
         resultVo = this.setScoreCloumn(emrQcFlowStatisList);
      }

      return resultVo;
   }

   public StatisticsResultVo selectDeptScoreAfterCloumn(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      StatisticsResultVo resultVo = new StatisticsResultVo();
      List<EmrQcFlowStatis> emrQcFlowStatisList = this.emrQcFlowStatisMapper.selectDeptScoreCloumn(emrQcFlowStatis);
      if (emrQcFlowStatisList != null && !emrQcFlowStatisList.isEmpty()) {
         List<EmrQcFlowStatis> afterList = this.setScoreReversed(emrQcFlowStatisList);
         resultVo = this.setScoreCloumn(afterList);
      }

      return resultVo;
   }

   public StatisticsResultVo selectDeptScoreTopDeptCloumn(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      StatisticsResultVo resultVo = new StatisticsResultVo();
      List<EmrQcFlowStatis> emrQcFlowStatisList = this.emrQcFlowStatisMapper.selectDeptScoreDeptCloumn(emrQcFlowStatis);
      if (emrQcFlowStatisList != null && !emrQcFlowStatisList.isEmpty()) {
         resultVo = this.setScoreCloumn(emrQcFlowStatisList);
      }

      return resultVo;
   }

   public StatisticsResultVo selectDeptScoreAfterDeptCloumn(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      StatisticsResultVo resultVo = new StatisticsResultVo();
      List<EmrQcFlowStatis> emrQcFlowStatisList = this.emrQcFlowStatisMapper.selectDeptScoreDeptCloumn(emrQcFlowStatis);
      if (emrQcFlowStatisList != null && !emrQcFlowStatisList.isEmpty()) {
         List<EmrQcFlowStatis> afterList = this.setScoreReversed(emrQcFlowStatisList);
         resultVo = this.setScoreCloumn(afterList);
      }

      return resultVo;
   }

   public StatisticsResultVo selectDeptScoreTopCheckCloumn(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      StatisticsResultVo resultVo = new StatisticsResultVo();
      List<EmrQcFlowStatis> emrQcFlowStatisList = this.emrQcFlowStatisMapper.selectDeptScoreCheckCloumn(emrQcFlowStatis);
      if (emrQcFlowStatisList != null && !emrQcFlowStatisList.isEmpty()) {
         resultVo = this.setScoreCloumn(emrQcFlowStatisList);
      }

      return resultVo;
   }

   public StatisticsResultVo selectDeptScoreAfterCheckCloumn(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      StatisticsResultVo resultVo = new StatisticsResultVo();
      List<EmrQcFlowStatis> emrQcFlowStatisList = this.emrQcFlowStatisMapper.selectDeptScoreCheckCloumn(emrQcFlowStatis);
      if (emrQcFlowStatisList != null && !emrQcFlowStatisList.isEmpty()) {
         List<EmrQcFlowStatis> afterList = this.setScoreReversed(emrQcFlowStatisList);
         resultVo = this.setScoreCloumn(afterList);
      }

      return resultVo;
   }

   public List selectScoreStatisTable(EmrQcFlowStatis emrQcFlowStatis) {
      List<EmrQcFlowStatis> resList = this.emrQcFlowStatisMapper.selectScoreStatisTable(emrQcFlowStatis);
      if (resList != null && !resList.isEmpty()) {
         for(EmrQcFlowStatis statis : resList) {
            Integer total = statis.getValueTotal();
            if (StringUtils.isEmpty(statis.getCheckAvg())) {
               statis.setCheckAvg("-");
            } else {
               Double check = Double.parseDouble(statis.getCheckAvg());
               statis.setCheckAvg(String.format("%.1f", check / (double)total));
            }

            if (StringUtils.isEmpty(statis.getDeptAvg())) {
               statis.setDeptAvg("-");
            } else {
               Double dept = Double.parseDouble(statis.getDeptAvg());
               statis.setDeptAvg(String.format("%.1f", dept / (double)total));
            }

            if (StringUtils.isEmpty(statis.getTermAvg())) {
               statis.setTermAvg("-");
            } else {
               Double term = Double.parseDouble(statis.getTermAvg());
               statis.setTermAvg(String.format("%.1f", term / (double)total));
            }
         }
      }

      return resList;
   }

   public List selectScoreStatisDialogTable(EmrQcFlowStatis emrQcFlowStatis) {
      return this.emrQcFlowStatisMapper.selectScoreStatisDialogTable(emrQcFlowStatis);
   }

   public List selectScoreStatisDeptTable(EmrQcFlowStatis emrQcFlowStatis) {
      List<EmrQcFlowStatis> resList = this.emrQcFlowStatisMapper.selectScoreStatisDeptTable(emrQcFlowStatis);
      if (resList != null && !resList.isEmpty()) {
         for(EmrQcFlowStatis statis : resList) {
            Integer checkTotal = statis.getCheckTotal();
            Integer deptTotal = statis.getDeptTotal();
            Integer termTotal = statis.getTermTotal();
            if (StringUtils.isEmpty(statis.getCheckAvg())) {
               statis.setCheckAvg("-");
            } else {
               Double check = Double.parseDouble(statis.getCheckAvg());
               statis.setCheckAvg(String.format("%.2f", check / (double)checkTotal));
            }

            if (StringUtils.isEmpty(statis.getDeptAvg())) {
               statis.setDeptAvg("-");
            } else {
               Double dept = Double.parseDouble(statis.getDeptAvg());
               statis.setDeptAvg(String.format("%.2f", dept / (double)deptTotal));
            }

            if (StringUtils.isEmpty(statis.getTermAvg())) {
               statis.setTermAvg("-");
            } else {
               Double term = Double.parseDouble(statis.getTermAvg());
               statis.setTermAvg(String.format("%.2f", term / (double)termTotal));
            }
         }
      }

      return resList;
   }

   public Map selectConsCheckPie(TdCaConsApplyVo tdCaConsApply) throws Exception {
      Map<String, StatisticsResultVo> map = new HashMap();
      tdCaConsApply.setState("04");
      List<TdCaConsApplyVo> finishList = this.tdCaConsApplyService.selectTdCaConsApplyStatisList(tdCaConsApply);
      StatisticsResultVo statisticsResultVo1 = new StatisticsResultVo();
      StatisticsResultVo statisticsResultVo2 = new StatisticsResultVo();
      StatisticsResultVo statisticsResultVo3 = new StatisticsResultVo();
      List<StatementVo> charData1 = new ArrayList();
      List<StatementVo> charData2 = new ArrayList();
      List<StatementVo> charData3 = new ArrayList();
      Integer totalCount1 = 0;
      Integer count1 = 0;
      Integer count2 = 0;
      Integer count3 = 0;
      Integer count4 = 0;
      Integer count5 = 0;
      Integer count6 = 0;
      Integer count7 = 0;
      Integer totalCount = finishList == null ? 0 : finishList.size();
      if (finishList != null && !finishList.isEmpty()) {
         List<TdCaConsApplyVo> list1 = (List)finishList.stream().filter((s) -> s.getConsTypeCd().equals("1")).collect(Collectors.toList());
         if (list1 != null && !list1.isEmpty()) {
            totalCount1 = list1.size();
            List<TdCaConsApplyVo> countList1 = (List)list1.stream().filter((s) -> DateUtils.getDateHours(s.getConsDate(), s.getApplyDate()) > 24).collect(Collectors.toList());
            count2 = countList1 == null ? 0 : countList1.size();
            count1 = totalCount1 - count2;
            count3 = list1.size();
            count4 = totalCount - count3;
         }

         List<TdCaConsApplyVo> stis1 = (List)finishList.stream().filter((s) -> s.getConsSatiCd().equals("1")).collect(Collectors.toList());
         List<TdCaConsApplyVo> stis2 = (List)finishList.stream().filter((s) -> s.getConsSatiCd().equals("2")).collect(Collectors.toList());
         List<TdCaConsApplyVo> stis3 = (List)finishList.stream().filter((s) -> s.getConsSatiCd().equals("3")).collect(Collectors.toList());
         count5 = stis1 == null ? 0 : stis1.size();
         count6 = stis2 == null ? 0 : stis2.size();
         count7 = stis3 == null ? 0 : stis3.size();
      }

      this.setCharData(count1, totalCount1, "未超时", charData1);
      this.setCharData(count2, totalCount1, "已超时", charData1);
      this.setCharData(count3, totalCount, "普通会诊", charData2);
      this.setCharData(count4, totalCount, "紧急会诊", charData2);
      this.setCharData(count5, totalCount, "优", charData3);
      this.setCharData(count6, totalCount, "良", charData3);
      this.setCharData(count7, totalCount, "差", charData3);
      statisticsResultVo1.setTotal(totalCount1);
      statisticsResultVo2.setTotal(totalCount);
      statisticsResultVo3.setTotal(totalCount);
      statisticsResultVo1.setCharData(charData1);
      statisticsResultVo2.setCharData(charData2);
      statisticsResultVo3.setCharData(charData3);
      map.put("result1", statisticsResultVo1);
      map.put("result2", statisticsResultVo2);
      map.put("result3", statisticsResultVo3);
      return map;
   }

   public StatisticsResultVo selectConsCylinder(TdCaConsApplyVo tdCaConsApply) throws Exception {
      StatisticsResultVo result = new StatisticsResultVo();
      StatementVo lineData = new StatementVo();
      List<Double> doubleList = new ArrayList();
      List<String> xAxis = new ArrayList();
      tdCaConsApply.setState("04");
      tdCaConsApply.setConsTypeCd("2");
      List<TdCaConsApplyVo> finishList = this.tdCaConsApplyService.selectTdCaConsApplyStatisList(tdCaConsApply);
      xAxis.add("2小时");
      xAxis.add("4小时");
      xAxis.add("8小时");
      xAxis.add("12小时");
      xAxis.add("16小时");
      xAxis.add("20小时");
      xAxis.add("24小时");
      xAxis.add("24小时以外");
      result.setxAxis(xAxis);
      if (finishList != null && !finishList.isEmpty()) {
         List<TdCaConsApplyVo> list2 = (List)finishList.stream().filter((s) -> DateUtils.getDateHours(s.getConsSignDate(), s.getApplyDate()) <= 2).collect(Collectors.toList());
         List<TdCaConsApplyVo> list4 = (List)finishList.stream().filter((s) -> DateUtils.getDateHours(s.getConsSignDate(), s.getApplyDate()) <= 4).collect(Collectors.toList());
         List<TdCaConsApplyVo> list8 = (List)finishList.stream().filter((s) -> DateUtils.getDateHours(s.getConsSignDate(), s.getApplyDate()) <= 8).collect(Collectors.toList());
         List<TdCaConsApplyVo> list12 = (List)finishList.stream().filter((s) -> DateUtils.getDateHours(s.getConsSignDate(), s.getApplyDate()) <= 12).collect(Collectors.toList());
         List<TdCaConsApplyVo> list16 = (List)finishList.stream().filter((s) -> DateUtils.getDateHours(s.getConsSignDate(), s.getApplyDate()) <= 16).collect(Collectors.toList());
         List<TdCaConsApplyVo> list20 = (List)finishList.stream().filter((s) -> DateUtils.getDateHours(s.getConsSignDate(), s.getApplyDate()) <= 20).collect(Collectors.toList());
         List<TdCaConsApplyVo> list24 = (List)finishList.stream().filter((s) -> DateUtils.getDateHours(s.getConsSignDate(), s.getApplyDate()) <= 24).collect(Collectors.toList());
         doubleList.add(this.getDoubleRatio(list2.size(), finishList.size()));
         doubleList.add(this.getDoubleRatio(list4.size(), finishList.size()));
         doubleList.add(this.getDoubleRatio(list8.size(), finishList.size()));
         doubleList.add(this.getDoubleRatio(list12.size(), finishList.size()));
         doubleList.add(this.getDoubleRatio(list16.size(), finishList.size()));
         doubleList.add(this.getDoubleRatio(list20.size(), finishList.size()));
         doubleList.add(this.getDoubleRatio(list24.size(), finishList.size()));
         doubleList.add((double)100.0F);
      }

      lineData.setLineData(doubleList);
      lineData.setName("累计占比%");
      result.setLineData(lineData);
      return result;
   }

   private Double getDoubleRatio(Integer count, Integer totalCount) {
      BigDecimal bg = new BigDecimal((double)count * (double)100.0F / (double)totalCount);
      Double ratio = bg.setScale(2, 4).doubleValue();
      return ratio;
   }

   private Double getDoubleRatioD(Double count, Double totalCount) {
      BigDecimal bg = new BigDecimal(count * (double)100.0F / totalCount);
      Double ratio = bg.setScale(2, 4).doubleValue();
      return ratio;
   }

   public StatisticsResultVo selectHomeDoctorFeeCloumn() throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
      DateTimeFormatter df1 = DateTimeFormatter.ofPattern("yyyy-MM");
      LocalDateTime nowTime = LocalDateTime.of(LocalDate.now().withDayOfMonth(1), LocalTime.MIN);
      LocalDateTime time1 = nowTime.minusMonths(1L);
      LocalDateTime time2 = nowTime.minusMonths(2L);
      LocalDateTime time3 = nowTime.plusMonths(1L);
      List<EmrQcFlowStatis> allList = this.emrQcFlowStatisMapper.selectHomeDeptFeeList((String)null, sysUser.getUserName(), df.format(time2), df.format(time3));
      String format1 = df1.format(time1);
      String format2 = df1.format(time2);
      String format3 = df1.format(nowTime);
      Map<String, List<EmrQcFlowStatis>> listMap = new HashMap();
      listMap.put(format1, new ArrayList());
      listMap.put(format2, new ArrayList());
      listMap.put(format3, new ArrayList());
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

      for(EmrQcFlowStatis flowStatis : allList) {
         Date filingDate = flowStatis.getFilingDate();
         String format = sdf.format(filingDate);
         if (listMap.containsKey(format)) {
            List<EmrQcFlowStatis> statis = (List)listMap.get(format);
            statis.add(flowStatis);
         }
      }

      StatisticsResultVo resultVo = this.setFeeResult((List)listMap.get(format2), (List)listMap.get(format1), (List)listMap.get(format3));
      return resultVo;
   }

   public StatisticsResultVo selectHomeDeptFeeCloumn() throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
      DateTimeFormatter df1 = DateTimeFormatter.ofPattern("yyyy-MM");
      LocalDateTime nowTime = LocalDateTime.of(LocalDate.now().withDayOfMonth(1), LocalTime.MIN);
      LocalDateTime time1 = nowTime.minusMonths(1L);
      LocalDateTime time2 = nowTime.minusMonths(2L);
      LocalDateTime time3 = nowTime.plusMonths(1L);
      List<EmrQcFlowStatis> allList = this.emrQcFlowStatisMapper.selectHomeDeptFeeList(sysUser.getDept().getDeptCode(), (String)null, df.format(time2), df.format(time3));
      String format1 = df1.format(time1);
      String format2 = df1.format(time2);
      String format3 = df1.format(nowTime);
      Map<String, List<EmrQcFlowStatis>> listMap = new HashMap();
      listMap.put(format1, new ArrayList());
      listMap.put(format2, new ArrayList());
      listMap.put(format3, new ArrayList());
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

      for(EmrQcFlowStatis flowStatis : allList) {
         Date filingDate = flowStatis.getFilingDate();
         String format = sdf.format(filingDate);
         if (listMap.containsKey(format)) {
            List<EmrQcFlowStatis> statis = (List)listMap.get(format);
            statis.add(flowStatis);
         }
      }

      StatisticsResultVo resultVo = this.setFeeResult((List)listMap.get(format2), (List)listMap.get(format1), (List)listMap.get(format3));
      return resultVo;
   }

   public List selectTimeOutEmrTableExport(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      if (emrQcFlowStatis != null && emrQcFlowStatis.getDeptCd() != null) {
         SysDept sysDept = this.sysDeptService.selectDeptById(Long.valueOf(emrQcFlowStatis.getDeptCd()));
         if (sysDept != null && StringUtils.isNotBlank(sysDept.getDeptCode())) {
            emrQcFlowStatis.setDeptCd(sysDept.getDeptCode());
         }
      }

      List<EmrQcFlowStatisSumExport> list = new ArrayList(1);
      List<EmrQcFlowStatis> emrQcFlowStatisList = this.emrQcFlowStatisMapper.selectTimeOutEmrTotal(emrQcFlowStatis);
      List<EmrQcFlowStatis> writeEmr = this.emrQcFlowStatisMapper.selectTimeOutEmrWrite(emrQcFlowStatis);
      List<EmrQcFlowStatis> notWriteEmr = this.emrQcFlowStatisMapper.selectTimeOutEmrUnWrite(emrQcFlowStatis);
      if (emrQcFlowStatisList != null && !emrQcFlowStatisList.isEmpty()) {
         for(EmrQcFlowStatis statis : emrQcFlowStatisList) {
            List<EmrQcFlowStatis> writeList = (List)writeEmr.stream().filter((s) -> s.getDoctCd().equals(statis.getDoctCd())).collect(Collectors.toList());
            List<EmrQcFlowStatis> unWriteList = (List)((List)notWriteEmr.stream().filter((s) -> s.getDoctCd() != null).collect(Collectors.toList())).stream().filter((s) -> s.getDoctCd().equals(statis.getDoctCd())).collect(Collectors.toList());
            statis.setFlow1(!writeList.isEmpty() ? ((EmrQcFlowStatis)writeList.get(0)).getValueTotal() : 0);
            statis.setFlow2(!unWriteList.isEmpty() ? ((EmrQcFlowStatis)unWriteList.get(0)).getValueTotal() : 0);
            EmrQcFlowStatisSumExport export = new EmrQcFlowStatisSumExport();
            BeanUtils.copyProperties(statis, export);
            list.add(export);
         }
      }

      return list;
   }

   public List selectTimeOutEmrTableInfoExport(EmrQcFlowStatis emrQcFlowStatis) throws Exception {
      if (emrQcFlowStatis != null && emrQcFlowStatis.getDeptCd() != null) {
         SysDept sysDept = this.sysDeptService.selectDeptById(Long.valueOf(emrQcFlowStatis.getDeptCd()));
         if (sysDept != null && StringUtils.isNotBlank(sysDept.getDeptCode())) {
            emrQcFlowStatis.setDeptCd(sysDept.getDeptCode());
         }
      }

      List<EmrQcFlowStatisDetailExport> list = new ArrayList(1);

      for(EmrQcFlowStatis flowStatis : this.emrQcFlowStatisMapper.selectTimeOutEmrTableInfo(emrQcFlowStatis)) {
         EmrQcFlowStatisDetailExport export = new EmrQcFlowStatisDetailExport();
         BeanUtils.copyProperties(flowStatis, export);
         list.add(export);
      }

      return list;
   }

   private StatisticsResultVo setFeeResult(List monthList, List month1List, List month2List) {
      StatisticsResultVo resultVo = new StatisticsResultVo();
      List<StatementVo> statementVoList = new ArrayList(1);
      List<EmrQcFlowStatis> firstItemList = this.emrQcFlowStatisMapper.selectFirstItemList();
      List<String> xAxis = new ArrayList(1);
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(new Date());
      calendar.add(2, -2);
      int month = calendar.get(2) + 1;
      calendar.add(2, 1);
      int month1 = calendar.get(2) + 1;
      calendar.add(2, 1);
      int month2 = calendar.get(2) + 1;
      xAxis.add(month + "月");
      xAxis.add(month1 + "月");
      xAxis.add(month2 + "月");
      Double total = monthList != null && !monthList.isEmpty() ? (Double)monthList.stream().collect(Collectors.summingDouble(EmrQcFlowStatis::getTotal)) : (double)0.0F;
      Double total1 = month1List != null && !month1List.isEmpty() ? (Double)month1List.stream().collect(Collectors.summingDouble(EmrQcFlowStatis::getTotal)) : (double)0.0F;
      Double total2 = month2List != null && !month2List.isEmpty() ? (Double)month2List.stream().collect(Collectors.summingDouble(EmrQcFlowStatis::getTotal)) : (double)0.0F;
      total = (new BigDecimal(total)).setScale(2, 4).doubleValue();
      total1 = (new BigDecimal(total1)).setScale(2, 4).doubleValue();
      total2 = (new BigDecimal(total2)).setScale(2, 4).doubleValue();
      Map<String, List<EmrQcFlowStatis>> mapList = (Map<String, List<EmrQcFlowStatis>>)(monthList != null && !monthList.isEmpty() ? (Map)monthList.stream().collect(Collectors.groupingBy((s) -> s.getFirstCode())) : new HashMap());
      Map<String, List<EmrQcFlowStatis>> map1List = (Map<String, List<EmrQcFlowStatis>>)(month1List != null && !month1List.isEmpty() ? (Map)month1List.stream().collect(Collectors.groupingBy((s) -> s.getFirstCode())) : new HashMap());
      Map<String, List<EmrQcFlowStatis>> map2List = (Map<String, List<EmrQcFlowStatis>>)(month2List != null && !month2List.isEmpty() ? (Map)month2List.stream().collect(Collectors.groupingBy((s) -> s.getFirstCode())) : new HashMap());

      for(EmrQcFlowStatis item : firstItemList) {
         List<Double> data = new ArrayList(1);
         List<Double> lineMap = new ArrayList();
         List<EmrQcFlowStatis> list = (List)mapList.get(item.getFirstCode());
         List<EmrQcFlowStatis> list1 = (List)map1List.get(item.getFirstCode());
         List<EmrQcFlowStatis> list2 = (List)map2List.get(item.getFirstCode());
         Double amount = list != null && !list.isEmpty() ? (Double)list.stream().collect(Collectors.summingDouble(EmrQcFlowStatis::getTotal)) : (double)0.0F;
         Double amount1 = list1 != null && !list1.isEmpty() ? (Double)list1.stream().collect(Collectors.summingDouble(EmrQcFlowStatis::getTotal)) : (double)0.0F;
         Double amount2 = list2 != null && !list2.isEmpty() ? (Double)list2.stream().collect(Collectors.summingDouble(EmrQcFlowStatis::getTotal)) : (double)0.0F;
         amount = (new BigDecimal(amount)).setScale(2, 4).doubleValue();
         amount1 = (new BigDecimal(amount1)).setScale(2, 4).doubleValue();
         amount2 = (new BigDecimal(amount2)).setScale(2, 4).doubleValue();
         Double ratio = amount > (double)0.0F ? this.getDoubleRatioD(amount, total) : (double)0.0F;
         Double ratio1 = amount1 > (double)0.0F ? this.getDoubleRatioD(amount1, total1) : (double)0.0F;
         Double ratio2 = amount2 > (double)0.0F ? this.getDoubleRatioD(amount2, total2) : (double)0.0F;
         data.add(amount);
         data.add(amount1);
         data.add(amount2);
         lineMap.add(ratio);
         lineMap.add(ratio1);
         lineMap.add(ratio2);
         StatementVo state = new StatementVo(item.getFirsName(), data, lineMap);
         statementVoList.add(state);
      }

      List<Double> data1 = new ArrayList(1);
      data1.add(total);
      data1.add(total1);
      data1.add(total2);
      StatementVo state = new StatementVo("全部数量", data1, new ArrayList());
      resultVo.setTotalData(state);
      resultVo.setSeries(statementVoList);
      resultVo.setxAxis(xAxis);
      return resultVo;
   }
}
