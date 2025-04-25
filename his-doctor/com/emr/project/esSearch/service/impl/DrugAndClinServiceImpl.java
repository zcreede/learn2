package com.emr.project.esSearch.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.emr.common.constant.CommonConstants;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.framework.elasticsearch.ElasticsearchIndexXyy;
import com.emr.framework.elasticsearch.ElasticsearchTemplateXyy;
import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.domain.TmPmOrderSetMain;
import com.emr.project.docOrder.domain.vo.OrderSearchVo;
import com.emr.project.docOrder.service.ITmPmOrderSetMainService;
import com.emr.project.esSearch.domain.DrugAndClin;
import com.emr.project.esSearch.domain.vo.DrugAndClinSearchVo;
import com.emr.project.esSearch.domain.vo.DrugAndClinVerifyVo;
import com.emr.project.esSearch.service.IDrugAndClinService;
import com.emr.project.esSearch.service.IDrugStockService;
import com.emr.project.his.domain.vo.DrugDoseVo;
import com.emr.project.his.domain.vo.PharmacyVo;
import com.emr.project.system.domain.SysUser;
import com.emr.project.tmpa.domain.OrderSig;
import com.emr.project.tmpa.service.IOrderSigService;
import com.emr.project.tmpm.domain.ClinItemCollection;
import com.emr.project.tmpm.domain.ClinitemUseLog;
import com.emr.project.tmpm.domain.DocumentType;
import com.emr.project.tmpm.domain.vo.ClinitemUseLogVo;
import com.emr.project.tmpm.service.IClinItemCollectionService;
import com.emr.project.tmpm.service.IClinItemMainService;
import com.emr.project.tmpm.service.IClinitemUseLogService;
import com.emr.project.tmpm.service.IDocumentTypeService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ScriptType;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.zxp.esclientrhl.repository.ElasticsearchTemplate;
import org.zxp.esclientrhl.repository.PageList;
import org.zxp.esclientrhl.repository.PageSortHighLight;

@Service
public class DrugAndClinServiceImpl implements IDrugAndClinService {
   protected final Logger log = LoggerFactory.getLogger(this.getClass());
   @Autowired
   private ElasticsearchTemplateXyy elasticsearchTemplateXyy;
   @Autowired
   private ElasticsearchTemplate elasticsearchTemplate;
   @Autowired
   private ElasticsearchIndexXyy elasticsearchIndexXyy;
   @Autowired
   private IDrugStockService drugStockService;
   @Autowired
   private IClinItemMainService clinItemMainService;
   @Autowired
   private IClinItemCollectionService clinItemCollectionService;
   @Autowired
   private IDocumentTypeService documentTypeService;
   @Autowired
   private IClinitemUseLogService clinitemUseLogService;
   @Autowired
   private IOrderSigService orderSigService;
   @Autowired
   private ITmPmOrderSetMainService tmPmOrderSetMainService;
   @Autowired
   private ICommonService commonService;
   @Resource(
      name = "poolTaskExecutor"
   )
   private TaskExecutor taskExecutor;
   @Autowired
   private ElasticsearchOperations operations;

   public void createDrugAndClinIndex(String indexName) throws Exception {
      boolean existsFlag = this.elasticsearchIndexXyy.exists(DrugAndClin.class, indexName);
      if (existsFlag) {
         this.elasticsearchIndexXyy.dropIndex(indexName);
      }

      this.elasticsearchIndexXyy.createIndex(DrugAndClin.class, indexName);
   }

   public void dropDrugAndClinIndex(String indexName) throws Exception {
      boolean existsFlag = this.elasticsearchIndexXyy.exists(DrugAndClin.class, indexName);
      if (existsFlag) {
         this.elasticsearchIndexXyy.dropIndex(indexName);
      }

   }

   public void dropDrugAndClinIndex() throws Exception {
      String indexName = this.getCurrUserIndexName();
      boolean existsFlag = this.elasticsearchIndexXyy.exists(DrugAndClin.class, indexName);
      if (existsFlag) {
         this.elasticsearchIndexXyy.dropIndex(indexName);
      }

   }

   private String getCurrUserIndexName() {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      return "drugandclin_zy_" + user.getUserName();
   }

   private List getClinAllList(SysUser user) throws Exception {
      List<DrugAndClin> clinList = this.clinItemMainService.selectClinList(user.getHospital().getOrgCode(), user.getDept().getDeptCode(), user.getUserName(), (String)null);
      return clinList;
   }

   private List getSetAllList(SysUser user) throws Exception {
      TmPmOrderSetMain tmPmOrderSetMain = new TmPmOrderSetMain();
      tmPmOrderSetMain.setSetType("1");
      tmPmOrderSetMain.setDeptCode(user.getDept().getDeptCode());
      tmPmOrderSetMain.setDocCode(user.getUserName());
      List<DrugAndClin> setList = this.tmPmOrderSetMainService.selectEsItemSetList(tmPmOrderSetMain);
      return setList;
   }

   public boolean syncDrugAndClinAllToEs(String indexName, List execDeptCd, String herbalFlag, String expenseCategoryNo, String asyncFlag) throws Exception {
      this.log.info("syncDrugAndClinAllToEs======1==========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      List<DrugAndClin> list = new ArrayList(1);
      SysUser user = SecurityUtils.getLoginUser().getUser();
      boolean existsFlag = this.elasticsearchIndexXyy.exists(DrugAndClin.class, indexName);

      try {
         if (!existsFlag) {
            this.elasticsearchIndexXyy.createIndex(DrugAndClin.class, indexName);
         }
      } catch (Exception e) {
         this.log.warn("判断医嘱开立项目索引不存在，但是重新创建索引报错：", e);
      }

      this.log.info("syncDrugAndClinAllToEs======2==========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      List<DrugAndClin> drugList1 = this.drugStockService.selectDrugList(execDeptCd, "0");
      this.log.info("syncDrugAndClinAllToEs======3==========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      List<DrugAndClin> drugList2 = this.drugStockService.selectDrugList(execDeptCd, "1");
      this.log.info("syncDrugAndClinAllToEs======4==========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      List<DrugAndClin> drugList = new ArrayList(1);
      drugList.addAll(drugList1);
      drugList.addAll(drugList2);
      OrderSig orderSig = new OrderSig();
      orderSig.setDocCd(user.getUserName());
      List<OrderSig> sigList = this.orderSigService.selectUseTimeOrderSigList(orderSig);
      this.log.info("syncDrugAndClinAllToEs======5==========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      Map<String, List<OrderSig>> sigMap = (Map)sigList.stream().collect(Collectors.groupingBy(OrderSig::getSigCd));
      drugList.stream().forEach((t) -> {
         List<OrderSig> sigList1 = (List)sigMap.get(t.getYongfa());
         if (sigList1 != null && !sigList1.isEmpty()) {
            OrderSig orderSig1 = (OrderSig)sigList1.get(0);
            t.setYongfaName(orderSig1.getSigName());
            t.setYongfaShowName(orderSig1.getSigShowName());
         }

      });
      list.addAll(drugList);
      this.log.info("syncDrugAndClinAllToEs======6==========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      List<DrugAndClin> clinList = this.getClinAllList(user);
      this.log.info("syncDrugAndClinAllToEs======7==========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      List<DrugAndClin> setList = this.getSetAllList(user);
      list.addAll(clinList);
      list.addAll(setList);
      this.log.info("syncDrugAndClinAllToEs======8==========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      this.setClinUsageTimes(list, user);
      this.log.info("syncDrugAndClinAllToEs======9==========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      this.toEs(list, indexName, asyncFlag);
      this.log.info("syncDrugAndClinAllToEs======10==========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      return true;
   }

   private void setClinUsageTimes(List drugList, SysUser user) throws Exception {
      List<String> itemClassCdList = Arrays.asList("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "99", "20");
      ClinitemUseLogVo param = new ClinitemUseLogVo(user.getHospital().getOrgCode(), user.getUserName(), itemClassCdList);
      List<ClinitemUseLog> clinitemUseLogList = this.clinitemUseLogService.selectClinitemUseLogListVo(param);
      Map<String, ClinitemUseLog> clinitemUseLogMap = (Map)clinitemUseLogList.stream().collect(Collectors.toMap((t) -> t.getItemCd(), Function.identity(), (var1, var2) -> var1));
      drugList.stream().forEach((t) -> {
         ClinitemUseLog clinitemUseLog = (ClinitemUseLog)clinitemUseLogMap.get(t.getCpNo());
         t.setUsageTimes(clinitemUseLog != null ? clinitemUseLog.getUsageTimes() : 0);
         t.setItemFlagCd(StringUtils.isBlank(t.getItemFlagCd()) ? "00" : t.getItemFlagCd());
         t.setItemFlagName(StringUtils.isBlank(t.getItemFlagName()) ? "正常医嘱" : t.getItemFlagName());
      });
   }

   public boolean syncDrugAndClinAllToEs(List execDeptCd, String herbalFlag, String expenseCategoryNo, String asyncFlag) throws Exception {
      String indexName = this.getCurrUserIndexName();
      return this.syncDrugAndClinAllToEs(indexName, execDeptCd, herbalFlag, expenseCategoryNo, asyncFlag);
   }

   @Async
   public void syncDrugAndClinAllToEsLogin(SysUser user) throws Exception {
      String indexName = "drugandclin_zy_" + user.getUserName();
      this.log.info("syncDrugAndClinAllToEs======1==========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      List<DrugAndClin> list = new ArrayList(1);
      boolean existsFlag = this.elasticsearchIndexXyy.exists(DrugAndClin.class, indexName);
      if (!existsFlag) {
         this.elasticsearchIndexXyy.createIndex(DrugAndClin.class, indexName);
      } else {
         this.elasticsearchIndexXyy.dropIndex(indexName);
         this.elasticsearchIndexXyy.createIndex(DrugAndClin.class, indexName);
      }

      this.log.info("syncDrugAndClinAllToEs======2==========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      List<DrugAndClin> clinList = this.getClinAllList(user);
      List<DrugAndClin> setList = this.getSetAllList(user);
      list.addAll(clinList);
      list.addAll(setList);
      this.setClinUsageTimes(list, user);
      this.log.info("syncDrugAndClinAllToEs======3==========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      this.toEs(list, indexName, "1");
      this.log.info("syncDrugAndClinAllToEs======4==========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
   }

   public void syncDrugAndClinAllToEsReload(List execDeptCd, String expenseCategoryNo) throws Exception {
      String indexName = this.getCurrUserIndexName();
      this.log.info("syncDrugAndClinAllToEs======1==========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      List<DrugAndClin> list = new ArrayList(1);
      SysUser user = SecurityUtils.getLoginUser().getUser();
      boolean existsFlag = this.elasticsearchIndexXyy.exists(DrugAndClin.class, indexName);
      if (!existsFlag) {
         this.elasticsearchIndexXyy.createIndex(DrugAndClin.class, indexName);
      } else {
         this.elasticsearchIndexXyy.dropIndex(indexName);
         this.elasticsearchIndexXyy.createIndex(DrugAndClin.class, indexName);
      }

      this.log.info("syncDrugAndClinAllToEs======2==========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      List<DrugAndClin> drugList1 = this.drugStockService.selectDrugList(execDeptCd, "0");
      this.log.info("syncDrugAndClinAllToEs======3==========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      List<DrugAndClin> drugList2 = this.drugStockService.selectDrugList(execDeptCd, "1");
      this.log.info("syncDrugAndClinAllToEs======4==========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      List<DrugAndClin> drugList = new ArrayList(1);
      drugList.addAll(drugList1);
      drugList.addAll(drugList2);
      OrderSig orderSig = new OrderSig();
      orderSig.setDocCd(user.getUserName());
      List<OrderSig> sigList = this.orderSigService.selectUseTimeOrderSigList(orderSig);
      this.log.info("syncDrugAndClinAllToEs======5==========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      Map<String, List<OrderSig>> sigMap = (Map)sigList.stream().collect(Collectors.groupingBy(OrderSig::getSigCd));
      drugList.stream().forEach((t) -> {
         List<OrderSig> sigList1 = (List)sigMap.get(t.getYongfa());
         if (sigList1 != null && !sigList1.isEmpty()) {
            OrderSig orderSig1 = (OrderSig)sigList1.get(0);
            t.setYongfaName(orderSig1.getSigName());
            t.setYongfaShowName(orderSig1.getSigShowName());
         }

      });
      list.addAll(drugList);
      this.log.info("syncDrugAndClinAllToEs======6==========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      List<DrugAndClin> clinList = this.clinItemMainService.selectClinList(user.getHospital().getOrgCode(), user.getDept().getDeptCode(), user.getUserName(), (String)null);
      this.log.info("syncDrugAndClinAllToEs======7==========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      TmPmOrderSetMain tmPmOrderSetMain = new TmPmOrderSetMain();
      tmPmOrderSetMain.setSetType("1");
      tmPmOrderSetMain.setDeptCode(user.getDept().getDeptCode());
      tmPmOrderSetMain.setDocCode(user.getUserName());
      List<DrugAndClin> setList = this.tmPmOrderSetMainService.selectEsItemSetList(tmPmOrderSetMain);
      list.addAll(clinList);
      list.addAll(setList);
      this.log.info("syncDrugAndClinAllToEs======8==========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      this.setClinUsageTimes(list, user);
      this.log.info("syncDrugAndClinAllToEs======9==========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      this.toEs(list, indexName, "0");
      this.log.info("syncDrugAndClinAllToEs======10==========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
   }

   public PageList queryDrugAndClinPageList(DrugAndClinSearchVo param, int currentPage, int pageSize) throws Exception {
      String indexName = this.getCurrUserIndexName();
      boolean existsFlag = this.elasticsearchIndexXyy.exists(DrugAndClin.class, indexName);
      if (existsFlag) {
         String creationDateStr = this.getSetting(indexName, "index.creation_date");
         Date currDate = DateUtils.parseDate(DateUtils.dateFormat(this.commonService.getDbSysdate(), DateUtils.YYYY_MM_DD), new String[]{DateUtils.YYYY_MM_DD});
         Date creationDate = new Date(Long.valueOf(creationDateStr));
         if (creationDate.compareTo(currDate) < 0) {
            existsFlag = false;
         }
      }

      if (!existsFlag) {
         SysUser sysUser = SecurityUtils.getLoginUser().getUser();
         List<PharmacyVo> pharmacyList = this.drugStockService.selectPharmacyListByDept(sysUser.getHospital().getOrgCode(), sysUser.getDept().getDeptCode());
         if (pharmacyList != null && pharmacyList.size() > 0) {
            List<String> execDeptCdList = (List)pharmacyList.stream().map((t) -> t.getPharmacyCode()).collect(Collectors.toList());
            this.syncDrugAndClinAllToEs(execDeptCdList, "0", (String)null, "0");
         }
      }

      this.log.info("queryDrugAndClinPageList=====================: " + indexName);
      PageList<DrugAndClin> pageList = new PageList();
      String preStr = StringUtils.isNotBlank(param.getFuzzyMatchFlag()) && param.getFuzzyMatchFlag().equals("0") ? "" : "*";
      BoolQueryBuilder bqb = QueryBuilders.boolQuery();
      List<DrugAndClin> drugAndClinList = new ArrayList();
      new ArrayList();
      if (StringUtils.isNotBlank(param.getPerformDepCode())) {
         drugAndClinList = this.getDrugAndClinsList(param, indexName, preStr);
      }

      QueryBuilder xc = QueryBuilders.termQuery("xcsl", (double)0.0F);
      bqb.mustNot(xc);
      if (StringUtils.isNotBlank(param.getKeyWord())) {
         String keyWord = param.getKeyWord().toLowerCase();
         WildcardQueryBuilder qb1 = QueryBuilders.wildcardQuery("inputstrphtc", preStr + keyWord + "*");
         bqb.should(qb1);
         WildcardQueryBuilder qb2 = QueryBuilders.wildcardQuery("cpName", preStr + keyWord + "*");
         bqb.should(qb2);
         WildcardQueryBuilder qb3 = QueryBuilders.wildcardQuery("cpNo", preStr + keyWord + "*");
         bqb.should(qb3);
         bqb.minimumShouldMatch(1);
      }

      if (StringUtils.isNotBlank(param.getOrderClassCode())) {
         if (param.getOrderClassCode().equals("所有项目")) {
            QueryBuilder qb1 = QueryBuilders.termQuery("orderClassCode", "00");
            bqb.mustNot(qb1);
         } else {
            QueryBuilder qb1 = QueryBuilders.termQuery("orderClassCode", param.getOrderClassCode());
            bqb.must(qb1);
         }
      }

      if (StringUtils.isNotBlank(param.getHerbalFlag())) {
         QueryBuilder qb1 = QueryBuilders.termQuery("herbalFlag", param.getHerbalFlag());
         bqb.must(qb1);
      }

      if (StringUtils.isNotBlank(param.getItemFlagCd())) {
         QueryBuilder qb1 = QueryBuilders.termQuery("itemFlagCd", param.getItemFlagCd());
         bqb.must(qb1);
      }

      if (StringUtils.isNotBlank(param.getPerformDepCode())) {
         QueryBuilder qb1 = QueryBuilders.termQuery("performDepCode", param.getPerformDepCode());
         bqb.mustNot(qb1);
         QueryBuilder qb2 = QueryBuilders.termQuery("orderClassCode", "01");
         bqb.mustNot(qb2);
      }

      if (StringUtils.isNotBlank(param.getOrderItemFlag())) {
         if (param.getOrderItemFlag().equals("5")) {
            QueryBuilder qb1 = QueryBuilders.termQuery("orderClassCode", "01");
            bqb.mustNot(qb1);
         }

         if (param.getOrderItemFlag().equals("4")) {
            QueryBuilder qb1 = QueryBuilders.termQuery("orderClassCode", "01");
            bqb.must(qb1);
            SysUser sysUser = SecurityUtils.getLoginUser().getUser();
            List<PharmacyVo> pharmacyList = this.drugStockService.selectPharmacyListByDept(sysUser.getHospital().getOrgCode(), sysUser.getDept().getDeptCode());
            pharmacyList = pharmacyList != null && !pharmacyList.isEmpty() ? (List)pharmacyList.stream().filter((t) -> StringUtils.isNotBlank(t.getPharmacyFlag()) && t.getPharmacyFlag().equals("1")).collect(Collectors.toList()) : null;
            if (pharmacyList != null && !pharmacyList.isEmpty()) {
               List<String> pharmacyCodeList = (List)pharmacyList.stream().map((t) -> t.getPharmacyCode()).collect(Collectors.toList());
               QueryBuilder qb2 = QueryBuilders.termsQuery("performDepCode", pharmacyCodeList);
               bqb.mustNot(qb2);
            }
         }
      }

      if (StringUtils.isNotBlank(param.getPageType()) && param.getPageType().equals("1")) {
         QueryBuilder qb1 = QueryBuilders.termQuery("orderFlag", "1");
         bqb.must(qb1);
      }

      List otherList = this.elasticsearchTemplate.searchMore(bqb, 10000, DrugAndClin.class, new String[]{indexName});
      pageList = this.createPageObject(pageList, drugAndClinList, otherList, currentPage, pageSize);
      this.setDrugAndClinCollectFlag(pageList, (List)null);
      return pageList;
   }

   private List getDrugAndClinsList(DrugAndClinSearchVo param, String indexName, String preStr) throws Exception {
      BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
      QueryBuilder performDepCode = QueryBuilders.termQuery("performDepCode", param.getPerformDepCode());
      boolQueryBuilder.must(performDepCode);
      QueryBuilder xc = QueryBuilders.termQuery("xcsl", (double)0.0F);
      boolQueryBuilder.mustNot(xc);
      if (StringUtils.isNotBlank(param.getKeyWord())) {
         String keyWord = param.getKeyWord().toLowerCase();
         WildcardQueryBuilder qb1 = QueryBuilders.wildcardQuery("inputstrphtc", preStr + keyWord + "*");
         boolQueryBuilder.should(qb1);
         WildcardQueryBuilder qb2 = QueryBuilders.wildcardQuery("cpName", preStr + keyWord + "*");
         boolQueryBuilder.should(qb2);
         WildcardQueryBuilder qb3 = QueryBuilders.wildcardQuery("cpNo", preStr + keyWord + "*");
         boolQueryBuilder.should(qb3);
         boolQueryBuilder.minimumShouldMatch(1);
      }

      if (StringUtils.isNotBlank(param.getOrderClassCode())) {
         if (param.getOrderClassCode().equals("所有项目")) {
            QueryBuilder orderClassCode = QueryBuilders.termQuery("orderClassCode", "00");
            boolQueryBuilder.mustNot(orderClassCode);
         } else {
            QueryBuilder orderClassCode = QueryBuilders.termQuery("orderClassCode", param.getOrderClassCode());
            boolQueryBuilder.must(orderClassCode);
         }
      }

      if (StringUtils.isNotBlank(param.getHerbalFlag())) {
         QueryBuilder herbalFlag = QueryBuilders.termQuery("herbalFlag", param.getHerbalFlag());
         boolQueryBuilder.must(herbalFlag);
      }

      if (StringUtils.isNotBlank(param.getItemFlagCd())) {
         QueryBuilder itemFlagCd = QueryBuilders.termQuery("itemFlagCd", param.getItemFlagCd());
         boolQueryBuilder.must(itemFlagCd);
      }

      if (StringUtils.isNotBlank(param.getOrderItemFlag())) {
         if (param.getOrderItemFlag().equals("5")) {
            QueryBuilder orderClassCode1 = QueryBuilders.termQuery("orderClassCode", "01");
            boolQueryBuilder.mustNot(orderClassCode1);
         }

         if (param.getOrderItemFlag().equals("4")) {
            QueryBuilder orderClassCode = QueryBuilders.termQuery("orderClassCode", "01");
            boolQueryBuilder.must(orderClassCode);
            SysUser sysUser = SecurityUtils.getLoginUser().getUser();
            List<PharmacyVo> pharmacyList = this.drugStockService.selectPharmacyListByDept(sysUser.getHospital().getOrgCode(), sysUser.getDept().getDeptCode());
            pharmacyList = pharmacyList != null && !pharmacyList.isEmpty() ? (List)pharmacyList.stream().filter((t) -> StringUtils.isNotBlank(t.getPharmacyFlag()) && t.getPharmacyFlag().equals("1")).collect(Collectors.toList()) : null;
            if (pharmacyList != null && !pharmacyList.isEmpty()) {
               List<String> pharmacyCodeList = (List)pharmacyList.stream().map((t) -> t.getPharmacyCode()).collect(Collectors.toList());
               QueryBuilder performDepCodeBuilder = QueryBuilders.termsQuery("performDepCode", pharmacyCodeList);
               boolQueryBuilder.mustNot(performDepCodeBuilder);
            }
         }
      }

      if (StringUtils.isNotBlank(param.getPageType()) && param.getPageType().equals("1")) {
         QueryBuilder orderFlag = QueryBuilders.termQuery("orderFlag", "1");
         boolQueryBuilder.must(orderFlag);
      }

      List<DrugAndClin> drugAndClinList = this.elasticsearchTemplate.searchMore(boolQueryBuilder, 10000, DrugAndClin.class, new String[]{indexName});
      return drugAndClinList;
   }

   private PageList createPageObject(PageList pageList, List drugAndClinList, List otherList, int currentPage, int pageSize) {
      List<DrugAndClin> resultList = new ArrayList();
      if (drugAndClinList.size() > 0) {
         resultList.addAll(drugAndClinList);
      }

      if (otherList.size() > 0) {
         resultList.addAll(otherList);
      }

      if (resultList.size() > 0) {
         Comparator<DrugAndClin> usageTimesComparing = Comparator.comparing(DrugAndClin::getOrderClassCode, Comparator.comparingDouble(Integer::parseInt));
         Comparator<DrugAndClin> cpNoComparing = Comparator.comparing(DrugAndClin::getCpNo);
         resultList = (List)resultList.stream().sorted(usageTimesComparing.thenComparing(cpNoComparing)).collect(Collectors.toList());
         new ArrayList();
         pageList.setPageSize(pageSize);
         pageList.setCurrentPage(currentPage);
         pageList.setTotalElements((long)resultList.size());
         int totalPageNum = resultList.size() % pageSize == 0 ? resultList.size() / pageSize : resultList.size() / pageSize + 1;
         pageList.setTotalPages(totalPageNum);
         int begin = 0;
         int end = 0;
         if (pageSize != 0) {
            begin = (currentPage - 1) * pageSize;
            end = currentPage * pageSize;
            if (end > resultList.size()) {
               end = resultList.size();
            }
         }

         List pages;
         if (end == 0) {
            pages = resultList;
         } else {
            pages = resultList.subList(begin, end);
         }

         pageList.setList(pages);
      }

      return pageList;
   }

   public void toEs(List list, String indexName, String asyncFlag) throws Exception {
      this.log.info("syncDrugAndClinAllToEs======9-1==========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      int bulkCount = 100;
      if (list.size() <= bulkCount) {
         this.log.info("syncDrugAndClinAllToEs======9-2==========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
         if (asyncFlag.equals("1")) {
            this.toEsAsync(list, indexName);
            this.log.info("syncDrugAndClinAllToEs======9-3==========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
         } else {
            this.toEsSync(list, indexName);
            this.log.info("syncDrugAndClinAllToEs======9-4==========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
         }
      } else {
         this.log.info("syncDrugAndClinAllToEs======9-5==========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
         int listSize = list.size();
         int bulkCount2 = 1000;
         int bulkNum2 = listSize / bulkCount2 + (listSize % bulkCount2 > 0 ? 1 : 0);
         List<List<DrugAndClin>> listSubs = new ArrayList(1);

         for(int i = 0; i < bulkNum2; ++i) {
            int fromIndex = i * bulkCount2;
            int toIndex = i + 1 == bulkNum2 ? listSize : (i + 1) * bulkCount2;
            List<DrugAndClin> listSub = list.subList(fromIndex, toIndex);
            listSubs.add(listSub);
         }

         this.log.info("syncDrugAndClinAllToEs======9-6====listSize:" + listSize + "====listSubs.size:" + listSubs.size() + "=========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));

         for(int j = 0; j < listSubs.size(); ++j) {
            this.log.info("syncDrugAndClinAllToEs======9-7==" + j + "==start===========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
            List<DrugAndClin> listSub = (List)listSubs.get(j);
            this.taskExecutor.execute(() -> {
               try {
                  int listSubSize = listSub.size();
                  int bulkNum = listSubSize / bulkCount + (listSubSize % bulkCount > 0 ? 1 : 0);

                  for(int i = 0; i < bulkNum; ++i) {
                     int fromIndex = i * bulkCount;
                     int toIndex = i + 1 == bulkNum ? listSubSize : (i + 1) * bulkCount;
                     List<DrugAndClin> listTemp = listSub.subList(fromIndex, toIndex);
                     if (asyncFlag.equals("1")) {
                        this.toEsAsync(listTemp, indexName);
                        this.log.info("syncDrugAndClinAllToEs======9-8=" + i + "=========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
                     } else {
                        this.toEsSync(listTemp, indexName);
                        this.log.info("syncDrugAndClinAllToEs======9-9=" + i + "=========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
                     }
                  }
               } catch (Exception e) {
                  this.log.error("", e);
               }

            });
            this.log.info("syncDrugAndClinAllToEs======9-10==" + j + "==end===========" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
         }
      }

   }

   @Async
   public void toEsAsync(List list, String indexName) throws Exception {
      this.elasticsearchTemplateXyy.saveBatch(list, indexName);
   }

   public void toEsSync(List list, String indexName) throws Exception {
      this.elasticsearchTemplateXyy.saveBatch(list, indexName);
   }

   public PageList selectQueryCheckList(DrugAndClinSearchVo param, int currentPage, int pageSize) throws Exception {
      String indexName = this.getCurrUserIndexName();
      boolean existsFlag = this.elasticsearchIndexXyy.exists(DrugAndClin.class, indexName);
      if (existsFlag) {
         String creationDateStr = this.getSetting(indexName, "index.creation_date");
         Date currDate = DateUtils.parseDate(DateUtils.dateFormat(this.commonService.getDbSysdate(), DateUtils.YYYY_MM_DD), new String[]{DateUtils.YYYY_MM_DD});
         Date creationDate = new Date(Long.valueOf(creationDateStr));
         if (creationDate.compareTo(currDate) < 0) {
            existsFlag = false;
         }
      }

      if (!existsFlag) {
         SysUser sysUser = SecurityUtils.getLoginUser().getUser();
         List<PharmacyVo> pharmacyList = this.drugStockService.selectPharmacyListByDept(sysUser.getHospital().getOrgCode(), sysUser.getDept().getDeptCode());
         List<String> execDeptCdList = (List)pharmacyList.stream().map((t) -> t.getPharmacyCode()).collect(Collectors.toList());
         this.syncDrugAndClinAllToEs(execDeptCdList, "0", (String)null, "0");
      }

      PageSortHighLight psh = new PageSortHighLight(currentPage, pageSize);
      BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
      if (CollectionUtils.isNotEmpty(param.getItemCdList())) {
         List<String> itemCdList = (List)param.getItemCdList().stream().map((t) -> t.toLowerCase()).collect(Collectors.toList());
         QueryBuilder itemCdQb = QueryBuilders.termsQuery("cpNo", itemCdList);
         queryBuilder.must(itemCdQb);
      }

      if (StringUtils.isNotBlank(param.getAllDocumentType())) {
         List<DocumentType> documentTypes = this.documentTypeService.selectDocumentListByType(param.getAllDocumentType(), "0");
         List<String> documentTypeNo = new ArrayList();
         if (documentTypes != null && !documentTypes.isEmpty()) {
            documentTypeNo = (List)documentTypes.stream().map((s) -> s.getDocumentTypeCd()).collect(Collectors.toList());
         }

         QueryBuilder qb = QueryBuilders.termsQuery("documentTypeNo", documentTypeNo);
         queryBuilder.must(qb);
      }

      if (StringUtils.isNotBlank(param.getCommonType())) {
         ClinItemCollection clinItemCollection = new ClinItemCollection();
         if (param.getCommonType().equals("02")) {
            clinItemCollection.setItemClassCd("02");
         } else {
            clinItemCollection.setItemClassCd("03");
         }

         List<String> itemList = new ArrayList();
         SysUser user = SecurityUtils.getLoginUser().getUser();
         clinItemCollection.setDocCd(user.getUserName());
         List<ClinItemCollection> collectionList = this.clinItemCollectionService.selectClinItemCollectionList(clinItemCollection);
         if (collectionList != null && !collectionList.isEmpty()) {
            itemList = (List)collectionList.stream().map((s) -> s.getItemCd().toLowerCase()).collect(Collectors.toList());
         }

         QueryBuilder itemCdQb = QueryBuilders.termsQuery("cpNo", itemList);
         queryBuilder.must(itemCdQb);
      }

      if (StringUtils.isNotBlank(param.getDocumentTypeNo())) {
         QueryBuilder qb1 = QueryBuilders.termQuery("documentTypeNo", param.getDocumentTypeNo());
         queryBuilder.must(qb1);
      }

      if (StringUtils.isNotBlank(param.getKeyWord())) {
         String keyWord = param.getKeyWord().toLowerCase();
         WildcardQueryBuilder qb1 = QueryBuilders.wildcardQuery("inputstrphtc", "*" + keyWord + "*");
         queryBuilder.should(qb1);
         WildcardQueryBuilder qb2 = QueryBuilders.wildcardQuery("cpName", "*" + keyWord + "*");
         queryBuilder.should(qb2);
         WildcardQueryBuilder qb3 = QueryBuilders.wildcardQuery("cpNo", "*" + keyWord + "*");
         queryBuilder.should(qb3);
         queryBuilder.minimumShouldMatch(1);
      }

      PageList<DrugAndClin> pageList = this.elasticsearchTemplateXyy.search(queryBuilder, psh, DrugAndClin.class, indexName);
      List<String> itemCdList = new ArrayList();
      itemCdList.add("02");
      itemCdList.add("03");
      this.setDrugAndClinCollectFlag(pageList, itemCdList);
      return pageList;
   }

   public void setDrugAndClinCollectFlag(PageList pageList, List itemCdList) {
      if (pageList.getList() != null) {
         SysUser user = SecurityUtils.getLoginUser().getUser();
         ClinItemCollection collection = new ClinItemCollection();
         if (itemCdList != null && !itemCdList.isEmpty()) {
            collection.setItemCdList(itemCdList);
         }

         collection.setDocCd(user.getUserName());
         List<ClinItemCollection> collectionList = this.clinItemCollectionService.selectClinItemCollectionList(collection);
         List<DrugAndClin> drugAndClins = pageList.getList();
         if (collectionList != null && !collectionList.isEmpty()) {
            List<String> cdList = (List)collectionList.stream().map((s) -> s.getItemCd()).collect(Collectors.toList());

            for(DrugAndClin drugAndClin : drugAndClins) {
               if (cdList.contains(drugAndClin.getCpNo())) {
                  drugAndClin.setCollectFlag(CommonConstants.BOOL_TRUE);
               } else {
                  drugAndClin.setCollectFlag(CommonConstants.BOOL_FALSE);
               }
            }
         } else {
            for(DrugAndClin drugAndClin : drugAndClins) {
               drugAndClin.setCollectFlag(CommonConstants.BOOL_FALSE);
            }
         }

         if (CollectionUtils.isNotEmpty(drugAndClins) && drugAndClins.size() > 0) {
            drugAndClins = (List)drugAndClins.stream().sorted(Comparator.comparing((t) -> t.getCpName())).collect(Collectors.toList());
         }

         pageList.setList(drugAndClins);
      }

   }

   public void updateDurgXcsl(List drugDoseVoList) throws Exception {
      Map<String, List<DrugDoseVo>> drugDoseMap = (Map)drugDoseVoList.stream().collect(Collectors.groupingBy((t) -> t.getYpid()));

      for(String drugId : drugDoseMap.keySet()) {
         List<DrugDoseVo> drugDoseVoListTemp = (List)drugDoseMap.get(drugId);
         BigDecimal drugDose = new BigDecimal("0");

         for(DrugDoseVo drugDoseVo : drugDoseVoListTemp) {
            drugDose = drugDoseVo.getOrderDose() != null ? drugDoseVo.getOrderDose() : new BigDecimal("0");
         }

         this.log.debug("{}{}=====================", drugId, drugDose);
         this.esUpdateDrugXcsl(drugId, drugDose);
      }

      this.log.debug("es更新药品成功");
   }

   private void esUpdateDrugXcsl(String drugId, BigDecimal xcsl) {
      QueryBuilder qbTemp = QueryBuilders.termQuery("id", drugId);
      NativeSearchQuery query = (new NativeSearchQueryBuilder()).withQuery(qbTemp).build();
      UpdateQuery updateQuery = UpdateQuery.builder(query).withScriptType(ScriptType.INLINE).withScript("ctx._source['xcsl'] += params['xcsl']").withLang("painless").withParams(Collections.singletonMap("xcsl", xcsl)).withAbortOnVersionConflict(true).build();
      String indexName = this.getCurrUserIndexName();
      IndexCoordinates index = IndexCoordinates.of(new String[]{indexName});
      this.operations.updateByQuery(updateQuery, index);
   }

   public DrugAndClin selectDrugInfoByCd(DrugAndClin drugAndClin) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      String indexName = "drugandclin_zy_" + user.getUserName();
      boolean existsFlag = this.elasticsearchIndexXyy.exists(DrugAndClin.class, indexName);
      if (!existsFlag) {
         SysUser sysUser = SecurityUtils.getLoginUser().getUser();
         List<PharmacyVo> pharmacyList = this.drugStockService.selectPharmacyListByDept(sysUser.getHospital().getOrgCode(), sysUser.getDept().getDeptCode());
         if (pharmacyList != null && pharmacyList.size() > 0) {
            List<String> execDeptCdList = (List)pharmacyList.stream().map((t) -> t.getPharmacyCode()).collect(Collectors.toList());
            this.syncDrugAndClinAllToEs(execDeptCdList, "0", (String)null, "0");
         }
      }

      BoolQueryBuilder bqb = QueryBuilders.boolQuery();
      if (StringUtils.isNotBlank(drugAndClin.getPerformDepCode())) {
         QueryBuilder qb1 = QueryBuilders.termQuery("performDepCode", drugAndClin.getPerformDepCode());
         bqb.must(qb1);
      } else {
         SysUser sysUser = SecurityUtils.getLoginUser().getUser();
         List<PharmacyVo> pharmacyList = this.drugStockService.selectPharmacyListByDept(sysUser.getHospital().getOrgCode(), sysUser.getDept().getDeptCode());
         pharmacyList = pharmacyList != null && !pharmacyList.isEmpty() ? (List)pharmacyList.stream().filter((t) -> StringUtils.isNotBlank(t.getPharmacyFlag()) && t.getPharmacyFlag().equals("1")).collect(Collectors.toList()) : null;
         if (pharmacyList != null && !pharmacyList.isEmpty()) {
            QueryBuilder qb1 = QueryBuilders.termQuery("performDepCode", ((PharmacyVo)pharmacyList.get(0)).getPharmacyCode());
            bqb.mustNot(qb1);
         }
      }

      QueryBuilder qb1 = QueryBuilders.termsQuery("cpNo", new String[]{drugAndClin.getCpNo().toLowerCase()});
      bqb.must(qb1);
      long count = this.elasticsearchTemplate.count(bqb, DrugAndClin.class, new String[]{indexName});
      PageSortHighLight psh = new PageSortHighLight(1, (int)count);
      PageList<DrugAndClin> pageList = this.elasticsearchTemplateXyy.search(bqb, psh, DrugAndClin.class, indexName);
      DrugAndClin result = pageList != null && pageList.getList() != null && !pageList.getList().isEmpty() ? (DrugAndClin)pageList.getList().get(0) : new DrugAndClin();
      return result;
   }

   public JSONArray drugInfoByIds(List orderSearchVoList) throws Exception {
      JSONArray resArr = new JSONArray(orderSearchVoList.size());
      List<OrderSearchVo> subList = (List)orderSearchVoList.stream().filter((t) -> !t.getCpNo().equals("NNNNNN")).collect(Collectors.toList());
      String indexName = this.getCurrUserIndexName();
      boolean existsFlag = this.elasticsearchIndexXyy.exists(DrugAndClin.class, indexName);
      if (existsFlag) {
         String creationDateStr = this.getSetting(indexName, "index.creation_date");
         Date currDate = DateUtils.parseDate(DateUtils.dateFormat(this.commonService.getDbSysdate(), DateUtils.YYYY_MM_DD), new String[]{DateUtils.YYYY_MM_DD});
         Date creationDate = new Date(Long.valueOf(creationDateStr));
         if (creationDate.compareTo(currDate) < 0) {
            existsFlag = false;
         }
      }

      if (!existsFlag) {
         SysUser sysUser = SecurityUtils.getLoginUser().getUser();
         List<PharmacyVo> pharmacyList = this.drugStockService.selectPharmacyListByDept(sysUser.getHospital().getOrgCode(), sysUser.getDept().getDeptCode());
         if (pharmacyList != null && pharmacyList.size() > 0) {
            List<String> execDeptCdList = (List)pharmacyList.stream().map((t) -> t.getPharmacyCode()).collect(Collectors.toList());
            this.syncDrugAndClinAllToEs(execDeptCdList, "0", (String)null, "0");
         }
      }

      List<DrugAndClinVerifyVo> list = new ArrayList();

      for(OrderSearchVo orderSearchVo : orderSearchVoList) {
         if (orderSearchVo.getCpNo().equals("NNNNNN")) {
            orderSearchVo.setItemFlagCd("05");
            orderSearchVo.setItemFlagName("自由录入医嘱");
            resArr.add(JSONObject.parse(JSON.toJSONString(orderSearchVo)));
         } else {
            BigDecimal orderDose = orderSearchVo.getOrderDose();
            DrugAndClinVerifyVo res = new DrugAndClinVerifyVo();
            res.setOrderUsageDays(orderSearchVo.getOrderUsageDays());
            BoolQueryBuilder bqb = QueryBuilders.boolQuery();
            QueryBuilder qb1 = QueryBuilders.termQuery("cpNo", orderSearchVo.getCpNo().toLowerCase());
            bqb.must(qb1);
            if (orderSearchVo.getOrderClassCode().equals("01")) {
               QueryBuilder qb3 = QueryBuilders.termQuery("performDepCode", orderSearchVo.getDetailPerformDepCode());
               bqb.must(qb3);
            }

            PageSortHighLight psh = new PageSortHighLight(1, 200);
            PageList<DrugAndClin> pageList = this.elasticsearchTemplateXyy.search(bqb, psh, DrugAndClin.class, indexName);
            List<DrugAndClin> tempList = pageList.getList();
            if (orderSearchVo.getOrderClassCode().equals("01")) {
               if (tempList != null && !tempList.isEmpty()) {
                  List<DrugAndClin> tempList2 = (List)tempList.stream().filter((t) -> orderDose.compareTo(t.getXcsl()) <= 0).collect(Collectors.toList());
                  DrugAndClin drugAndClin = null;
                  List<DrugAndClin> tempList1 = (List)tempList.stream().filter((t) -> orderSearchVo.getPrice() != null && t.getPrice().compareTo(orderSearchVo.getPrice()) == 0).collect(Collectors.toList());
                  if (tempList1 != null && !tempList1.isEmpty()) {
                     DrugAndClin temp = (DrugAndClin)tempList.get(0);
                     BigDecimal xcsl = temp.getXcsl();
                     drugAndClin = (DrugAndClin)tempList1.get(0);
                     if (orderDose.compareTo(xcsl) > 0 && tempList2 != null && !tempList2.isEmpty()) {
                        drugAndClin = (DrugAndClin)tempList2.get(0);
                        res.setReplaceFlag(CommonConstants.BOOL_TRUE);
                     }
                  } else if (tempList2 != null && !tempList2.isEmpty()) {
                     drugAndClin = (DrugAndClin)tempList2.get(0);
                     res.setReplaceFlag(CommonConstants.BOOL_TRUE);
                  }

                  if (drugAndClin == null) {
                     res.setCpNo(orderSearchVo.getCpNo());
                     res.setPerformDepCode(orderSearchVo.getPerformDepCode());
                     res.setPerformDepName(orderSearchVo.getPerformDepName());
                     res.setStockNo(orderSearchVo.getStockNo());
                     res.setXcsl(new BigDecimal("-1"));
                  } else {
                     if ("2".equals(orderSearchVo.getOrderItemFlag()) || "3".equals(orderSearchVo.getOrderItemFlag())) {
                        drugAndClin.setXcsl(new BigDecimal("-99"));
                     }

                     BeanUtils.copyProperties(drugAndClin, res);
                  }

                  list.add(res);
               } else {
                  res.setCpNo(orderSearchVo.getCpNo());
                  res.setCpName(orderSearchVo.getCpName());
                  res.setPerformDepCode(orderSearchVo.getDetailPerformDepCode());
                  res.setPerformDepName(orderSearchVo.getDetailPerformDepName());
                  res.setStockNo(orderSearchVo.getStockNo());
                  res.setXcsl(new BigDecimal("-1"));
                  list.add(res);
               }
            } else if (tempList != null && !tempList.isEmpty()) {
               DrugAndClin drugAndClin = (DrugAndClin)tempList.get(0);
               BeanUtils.copyProperties(drugAndClin, res);
               if (orderSearchVo.getPrice() != null && drugAndClin.getPrice() != null && orderSearchVo.getPrice().compareTo(drugAndClin.getPrice()) != 0) {
                  res.setReplaceFlag(CommonConstants.BOOL_TRUE);
               }

               if (StringUtils.isBlank(drugAndClin.getPerformDepCode())) {
                  if (StringUtils.isNotBlank(orderSearchVo.getDetailPerformDepCode())) {
                     res.setPerformDepCode(orderSearchVo.getDetailPerformDepCode());
                     res.setPerformDepName(orderSearchVo.getDetailPerformDepName());
                  } else {
                     res.setPerformDepCode(SecurityUtils.getLoginUser().getUser().getDept().getDeptCode());
                     res.setPerformDepName(SecurityUtils.getLoginUser().getUser().getDept().getDeptName());
                     res.setReplacePerformDepFlag(CommonConstants.BOOL_TRUE);
                  }
               }

               list.add(res);
            } else {
               res.setCpNo(orderSearchVo.getCpNo());
               res.setPerformDepCode(orderSearchVo.getPerformDepCode());
               res.setPerformDepName(orderSearchVo.getPerformDepName());
               res.setStockNo(orderSearchVo.getStockNo());
               res.setXcsl(new BigDecimal("-1"));
               list.add(res);
            }

            resArr.add(JSONObject.parse(JSON.toJSONString(res)));
         }
      }

      return resArr;
   }

   public void addDrugAndClin(List list) throws Exception {
      String indexName = this.getCurrUserIndexName();
      this.toEsAsync(list, indexName);
   }

   public String getSetting(String index, String setting) throws Exception {
      return this.elasticsearchIndexXyy.getSetting(DrugAndClin.class, index, setting);
   }
}
