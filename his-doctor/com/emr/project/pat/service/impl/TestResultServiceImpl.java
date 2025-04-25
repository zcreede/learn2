package com.emr.project.pat.service.impl;

import com.emr.common.utils.OrderUtils;
import com.emr.framework.aspectj.lang.annotation.DataSource;
import com.emr.framework.aspectj.lang.enums.DataSourceType;
import com.emr.framework.web.service.ISyncService;
import com.emr.project.pat.domain.TestResult;
import com.emr.project.pat.domain.vo.ExamItemVo;
import com.emr.project.pat.domain.vo.TestResultVo;
import com.emr.project.pat.mapper.TestResultMapper;
import com.emr.project.pat.service.ITestResultService;
import com.emr.project.system.domain.vo.SqlVo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestResultServiceImpl implements ITestResultService, ISyncService {
   protected final Logger log = LoggerFactory.getLogger(TestResultServiceImpl.class);
   @Autowired
   private TestResultMapper testResultMapper;

   public TestResult selectTestResultById(String id) {
      return this.testResultMapper.selectTestResultById(id);
   }

   public List selectTestResultList(TestResult testResult) {
      return this.testResultMapper.selectTestResultList(testResult);
   }

   public int insertTestResult(TestResult testResult) {
      return this.testResultMapper.insertTestResult(testResult);
   }

   public int updateTestResult(TestResult testResult) {
      return this.testResultMapper.updateTestResult(testResult);
   }

   public int deleteTestResultByIds(String[] ids) {
      return this.testResultMapper.deleteTestResultByIds(ids);
   }

   public int deleteTestResultById(String id) {
      return this.testResultMapper.deleteTestResultById(id);
   }

   public List analysisResultList(String[] patientIds, String clinItemCd) {
      List<TestResultVo> list = this.testResultMapper.analysisResultList(patientIds, clinItemCd);
      List<Map<String, Object>> resultList = new ArrayList();
      if (list.size() > 0) {
         List<String> resultDateList = new ArrayList();
         Map<String, List<TestResultVo>> mapItemList = (Map)list.stream().collect(Collectors.groupingBy(TestResult::getTestItemCd));
         Map<String, Long> mapItemCount = (Map)list.stream().collect(Collectors.groupingBy(TestResultVo::getResultDate, Collectors.counting()));

         for(String date : mapItemCount.keySet()) {
            resultDateList.add(date + "时");
         }

         for(String key : mapItemList.keySet()) {
            Map<String, Object> mapList = new HashMap();
            Map<String, TestResultVo> map = new HashMap();
            List<String> resExpList = new ArrayList();
            List<TestResultVo> valueList = (List)mapItemList.get(key);

            for(int z = 0; z < resultDateList.size(); ++z) {
               TestResultVo result1 = new TestResultVo();
               mapList.put(resultDateList.get(z), "");
               result1.setTestResExp("");
               map.put(resultDateList.get(z), result1);
            }

            for(int i = 0; i < valueList.size(); ++i) {
               TestResultVo result = new TestResultVo();
               result.setTestResExp(((TestResultVo)valueList.get(i)).getTestResExp());
               result.setNormalSign2(((TestResultVo)valueList.get(i)).getNormalSign2());
               map.put(((TestResultVo)valueList.get(i)).getResultDate() + "时", result);
            }

            map = (Map)map.entrySet().stream().sorted(Entry.comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oleValue, newValue) -> oleValue, LinkedHashMap::new));

            for(String vokey : map.keySet()) {
               resExpList.add(((TestResultVo)map.get(vokey)).getTestResExp());
            }

            mapList.put("resExpList", resExpList);
            mapList.put("testItemName", ((TestResultVo)((List)mapItemList.get(key)).get(0)).getTestItemName());
            mapList.put("valueUnit2", ((TestResultVo)((List)mapItemList.get(key)).get(0)).getValueUnit2());
            mapList.put("valueRange2", ((TestResultVo)((List)mapItemList.get(key)).get(0)).getValueRange2());

            try {
               String[] valueUnits = ((TestResultVo)((List)mapItemList.get(key)).get(0)).getValueRange2().split("--");
               Double max = Double.parseDouble(valueUnits[1]);
               Double min = Double.parseDouble(valueUnits[0]);
               mapList.put("max", max);
               mapList.put("min", min);
            } catch (Exception var17) {
               mapList.put("max", (Object)null);
               mapList.put("min", (Object)null);
            }

            for(String keyStr : map.keySet()) {
               mapList.put(keyStr, map.get(keyStr));
            }

            mapList = OrderUtils.orderMapAsc(mapList);
            resultList.add(mapList);
         }
      }

      return resultList;
   }

   public List selectResultDateList(String[] patientIds, String clinItemCd) {
      List<TestResultVo> list = this.testResultMapper.analysisResultList(patientIds, clinItemCd);
      List<String> dateList = new ArrayList();
      if (list.size() > 0) {
         Map<String, List<TestResultVo>> mapItemList = (Map)list.stream().collect(Collectors.groupingBy(TestResultVo::getResultDate));

         for(String key : mapItemList.keySet()) {
            dateList.add(key + "时");
         }

         Collections.sort(dateList, new Comparator() {
            public int compare(String o1, String o2) {
               return o1.compareTo(o2);
            }
         });
      }

      return dateList;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void syncData(List hisList) throws Exception {
      int i = 0;

      for(Map temp : hisList) {
         this.log.info("i-> {}", i++);

         try {
            this.testResultMapper.insertMap(temp);
         } catch (Exception e) {
            for(String key : temp.keySet()) {
               this.log.info("对象数值===== key:{},value:{}", key, temp.get(key).toString());
            }

            this.log.error("同步检查检验申请单出现异常，", e.getMessage());
            throw new Exception(e.getMessage());
         }
      }

   }

   public void syncAddData(List list, SqlVo sqlVo) throws Exception {
      this.syncData(list);
   }

   public void syncDataMap(List mapList, String tableName) throws Exception {
   }

   public List selectHisCheckResult(String patientId) throws Exception {
      return this.testResultMapper.selectHisCheckResult(patientId);
   }

   public List selectOrderCheckResult(TestResultVo testResult) throws Exception {
      return this.testResultMapper.selectOrderCheckResult(testResult);
   }

   @DataSource(DataSourceType.hisTestResult)
   public List selectHisResultList(SqlVo sqlVo) throws Exception {
      return this.testResultMapper.selectHisResultList(sqlVo);
   }

   @DataSource(DataSourceType.hisTestResult)
   public List selectTestAertList(ExamItemVo examItemVo) throws Exception {
      return this.testResultMapper.selectTestAertList(examItemVo);
   }
}
