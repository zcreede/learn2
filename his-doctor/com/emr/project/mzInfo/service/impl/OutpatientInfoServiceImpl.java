package com.emr.project.mzInfo.service.impl;

import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.DataSource;
import com.emr.framework.aspectj.lang.enums.DataSourceType;
import com.emr.project.mzInfo.domain.OutpatientInfoVO;
import com.emr.project.mzInfo.mapper.OutpatientInfoMapper;
import com.emr.project.mzInfo.service.IOutpatientInfoService;
import com.emr.project.pat.domain.TestReport;
import com.emr.project.pat.domain.TestResult;
import com.emr.project.pat.domain.vo.ExamItemVo;
import com.emr.project.pat.domain.vo.TestReportVo;
import com.emr.project.pat.service.ITestResultService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutpatientInfoServiceImpl implements IOutpatientInfoService {
   @Autowired
   private OutpatientInfoMapper outpatientInfoMapper;
   @Autowired
   private ITestResultService testResultService;

   @DataSource(DataSourceType.mzdb)
   public List selectOutPatientInfoByVisitNo(String visitNo) throws Exception {
      List<OutpatientInfoVO> infoVOList = this.outpatientInfoMapper.selectOutPatientInfoByVisitNo(visitNo);
      return infoVOList;
   }

   @DataSource(DataSourceType.mzdb)
   public List selectOutPatientInfoByIdCard(String patientId, String idCard) throws Exception {
      List<OutpatientInfoVO> infoVOList = this.outpatientInfoMapper.selectOutPatientInfoByIdCard(patientId, idCard);

      for(OutpatientInfoVO vo : infoVOList) {
         vo.setType("mz_new");
         String sex = vo.getSex();
         if (StringUtils.isNotEmpty(sex)) {
            if (sex.equals("1")) {
               vo.setSex("男");
            } else if (sex.equals("2")) {
               vo.setSex("女");
            } else {
               vo.setSex("未知");
            }
         }
      }

      return infoVOList;
   }

   @DataSource(DataSourceType.lbMzdb)
   public List selectTestReportList(String patientId) throws Exception {
      List<TestReportVo> testReportVos = this.outpatientInfoMapper.selectTestReportList(patientId);
      if (testReportVos != null && testReportVos.size() > 0) {
         List<String> appCdList = (List)testReportVos.stream().map(TestReport::getAppCd).distinct().collect(Collectors.toList());
         List<TestReportVo> reportVos = this.outpatientInfoMapper.selectTestReportResultList(appCdList);
         Map<String, List<TestReportVo>> appCdMap = new HashMap();
         if (reportVos != null && reportVos.size() > 0) {
            appCdMap = (Map)reportVos.stream().collect(Collectors.groupingBy(TestReport::getAppCd));
         }

         for(TestReportVo vo : testReportVos) {
            String appCd = vo.getAppCd();
            List<TestReportVo> reportVo = this.outpatientInfoMapper.selectTestReportDetailByAppCd(appCd);
            if (!reportVo.isEmpty()) {
               TestReportVo testReportVo = (TestReportVo)reportVo.get(0);
               String clinRepDate = testReportVo.getClinRepDate();
               if (StringUtils.isNotEmpty(clinRepDate)) {
                  vo.setClinRepDate(clinRepDate);
                  vo.setItemState("报告完成");
               }
            }

            if (appCdMap.containsKey(appCd)) {
               vo.setNormalSign2("H");
            } else {
               vo.setNormalSign2("M");
            }
         }
      }

      return testReportVos;
   }

   @DataSource(DataSourceType.lbMzdb)
   public List selectTestReportResultListByAppCd(String appCd) throws Exception {
      List<TestReportVo> testReport = this.outpatientInfoMapper.selectTestReportDetailByAppCd(appCd);
      if (testReport != null) {
         for(TestReportVo vo : testReport) {
            List<TestResult> resultList = this.outpatientInfoMapper.selectReportItemListByAppCd(vo.getAppCd());
            vo.setReasultList(resultList);
         }
      }

      return testReport;
   }

   @DataSource(DataSourceType.lbMzdb)
   public List selectItemList(String patientId) throws Exception {
      List<ExamItemVo> list = this.outpatientInfoMapper.selectItemListByPatientId(patientId);
      return list;
   }

   @DataSource(DataSourceType.lbMzdb)
   public List selectExamItemResultList(String id) throws Exception {
      return this.outpatientInfoMapper.selectExamItemResultList(id);
   }
}
