package com.emr.project.esSearch.service.impl;

import com.alibaba.fastjson.JSON;
import com.emr.common.constant.CommonConstants;
import com.emr.common.constant.Constants;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.elasticsearch.ElasticsearchTemplateXyy;
import com.emr.framework.redis.RedisCache;
import com.emr.project.emr.domain.EmrBinary;
import com.emr.project.emr.domain.EmrSearchCaseDetail;
import com.emr.project.emr.domain.EmrSearchSynonym;
import com.emr.project.emr.service.IEmrBinaryService;
import com.emr.project.emr.service.IEmrSearchCaseDetailService;
import com.emr.project.emr.service.IEmrSearchHistoryService;
import com.emr.project.emr.service.IEmrSearchSynonymService;
import com.emr.project.esSearch.domain.EmrFile;
import com.emr.project.esSearch.domain.EmrFileElem;
import com.emr.project.esSearch.domain.vo.EmrFileSearchVo;
import com.emr.project.esSearch.domain.vo.EmrFileVo;
import com.emr.project.esSearch.mapper.EmrFileMapper;
import com.emr.project.esSearch.service.IEmrFileElemService;
import com.emr.project.esSearch.service.IEmrFileService;
import com.emr.project.system.domain.BasEmployee;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder.Type;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zxp.esclientrhl.index.ElasticsearchIndex;
import org.zxp.esclientrhl.repository.ElasticsearchTemplate;
import org.zxp.esclientrhl.repository.HighLight;
import org.zxp.esclientrhl.repository.PageList;
import org.zxp.esclientrhl.repository.PageSortHighLight;

@Service
public class EmrFileServiceImpl implements IEmrFileService {
   @Autowired
   private ElasticsearchTemplate elasticsearchTemplate;
   @Autowired
   private ElasticsearchTemplateXyy elasticsearchTemplateXyy;
   @Autowired
   private ElasticsearchIndex elasticsearchIndex;
   @Autowired
   private EmrFileMapper emrFileMapper;
   @Autowired
   private IEmrBinaryService emrBinaryService;
   @Autowired
   private IEmrSearchCaseDetailService searchCaseDetailService;
   @Autowired
   private IEmrFileElemService emrFileElemService;
   @Autowired
   private RedisCache redisCache;
   @Autowired
   private IEmrSearchSynonymService emrSearchSynonymService;
   @Autowired
   private IEmrSearchHistoryService emrSearchHistoryService;

   public void createEmrFileIndex() throws Exception {
      boolean existsFlag = this.elasticsearchIndex.exists(EmrFile.class);
      if (!existsFlag) {
         this.elasticsearchIndex.createIndex(EmrFile.class);
      }

   }

   public void dropEmrFileIndex() throws Exception {
      boolean existsFlag = this.elasticsearchIndex.exists(EmrFile.class);
      if (existsFlag) {
         this.elasticsearchIndex.dropIndex(EmrFile.class);
      }

   }

   public boolean syncEmrFileAllToEs() throws Exception {
      boolean existsFlag = this.elasticsearchIndex.exists(EmrFile.class);
      if (!existsFlag) {
         this.elasticsearchIndex.createIndex(EmrFile.class);
      }

      List<EmrFile> list = this.emrFileMapper.selectList((EmrFileVo)null);
      if (list != null && !list.isEmpty()) {
         this.toEs(list);
      }

      return true;
   }

   public void syncEmrFileAddToEs(String beginDate, String endDate) throws Exception {
      boolean existsFlag = this.elasticsearchIndex.exists(EmrFile.class);
      if (!existsFlag) {
         this.elasticsearchIndex.createIndex(EmrFile.class);
      }

      EmrFileVo param = new EmrFileVo();
      param.setBeginDate(beginDate);
      param.setEndDate(endDate);
      List<EmrFile> list = this.emrFileMapper.selectList(param);
      if (list != null && !list.isEmpty()) {
         this.toEs(list);
      }

   }

   public void syncEmrFileAddToEs(String patiengId) throws Exception {
      boolean existsFlag = this.elasticsearchIndex.exists(EmrFile.class);
      if (!existsFlag) {
         this.elasticsearchIndex.createIndex(EmrFile.class);
      }

      EmrFileVo param = new EmrFileVo();
      param.setPatientId(patiengId);
      List<EmrFile> list = this.emrFileMapper.selectList(param);
      if (list != null && !list.isEmpty()) {
         this.toEs(list);
      }

   }

   public List queryAll(EmrFile emrFile) throws Exception {
      BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
      if (StringUtils.isNotBlank(emrFile.getMrFileContent())) {
         QueryBuilder qbTemp = QueryBuilders.termQuery("mrFileContent", emrFile.getMrFileContent());
         queryBuilder.should(qbTemp);
      }

      if (StringUtils.isNotBlank(emrFile.getPatientName())) {
         QueryBuilder qbTemp = QueryBuilders.termQuery("patientName", emrFile.getPatientName());
         queryBuilder.should(qbTemp);
      }

      return this.elasticsearchTemplate.search(queryBuilder, EmrFile.class);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public PageList queryHighLight(EmrFileSearchVo emrFileSearchVo, int currentPage, int pageSize) throws Exception {
      String userToken = SecurityUtils.getLoginUser().getToken();
      PageSortHighLight psh = new PageSortHighLight(currentPage, pageSize);
      HighLight highLight = new HighLight();
      HighlightBuilder highlightBuilder = new HighlightBuilder();
      highlightBuilder.field("mrFileContent", 100);
      highlightBuilder.preTags(new String[]{"<span style='color:red'>"});
      highlightBuilder.postTags(new String[]{"</span>"});
      highLight.setHighlightBuilder(highlightBuilder);
      psh.setHighLight(highLight);
      BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
      this.getQbList(queryBuilder, emrFileSearchVo);
      List<QueryBuilder> filter = queryBuilder.filter();
      List<QueryBuilder> must = queryBuilder.must();
      List<QueryBuilder> mustNot = queryBuilder.mustNot();
      List<QueryBuilder> should = queryBuilder.should();
      PageList<EmrFile> pageList = null;
      if (filter.size() == 0 && must.size() == 0 && mustNot.size() == 0 && should.size() == 0) {
         pageList = new PageList();
         pageList.setList(new ArrayList());
      } else {
         Integer searchType1Flag = (Integer)this.redisCache.getCacheObject("es_search_type_1:" + userToken);
         if (StringUtils.isNotEmpty(emrFileSearchVo.getCaseFlag()) && emrFileSearchVo.getCaseFlag().equals("0")) {
            pageList = new PageList();
            pageList.setList(new ArrayList());
            List<EmrFile> emrFileList = this.elasticsearchTemplate.searchMore(queryBuilder, 10000, EmrFile.class);
            if (emrFileList != null && emrFileList.size() > 0) {
               Map<String, List<EmrFile>> collect = (Map)emrFileList.stream().collect(Collectors.groupingBy(EmrFile::getPatientId));
               List<EmrFile> resultList = new ArrayList();

               for(String key : collect.keySet()) {
                  resultList.add(((List)collect.get(key)).get(0));
               }

               pageList.setPageSize(pageSize);
               pageList.setCurrentPage(currentPage);
               pageList.setTotalElements((long)resultList.size());
               int totalPageNum = resultList.size() % pageSize == 0 ? resultList.size() / pageSize : resultList.size() / pageSize + 1;
               pageList.setTotalPages(totalPageNum);
               new ArrayList();
               int begin = 0;
               int end = 0;
               if (pageSize != 0) {
                  begin = (currentPage - 1) * pageSize;
                  end = currentPage * pageSize;
                  if (end > resultList.size()) {
                     end = resultList.size();
                  }
               }

               Object var30;
               if (end == 0) {
                  var30 = resultList;
               } else {
                  var30 = resultList.subList(begin, end);
               }

               pageList.setList((List)var30);
            }
         } else if (emrFileSearchVo.getSearchType() != null && emrFileSearchVo.getSearchType() == 1) {
            List<Long> mrFileIdList = (List)this.redisCache.getCacheObject("es_mr_file_id_list:" + userToken);
            List<Long> mrFileIdList2 = (List)this.redisCache.getCacheObject("es_mr_file_id_list2:" + userToken);
            if (mrFileIdList2 != null) {
               QueryBuilder qbTemp = QueryBuilders.termsQuery("id", mrFileIdList2);
               queryBuilder.must(qbTemp);
               this.redisCache.setCacheObject("es_mr_file_id_list:" + userToken, mrFileIdList2, Constants.ES_EXPIRATION, TimeUnit.MINUTES);
               this.redisCache.deleteObject("es_mr_file_id_list2:" + userToken);
            } else {
               QueryBuilder qbTemp = QueryBuilders.termsQuery("id", mrFileIdList);
               queryBuilder.must(qbTemp);
            }

            pageList = this.elasticsearchTemplateXyy.search(queryBuilder, psh, EmrFile.class);
            this.redisCache.setCacheObject("es_search_type_1:" + userToken, emrFileSearchVo.getSearchType(), Constants.ES_EXPIRATION, TimeUnit.MINUTES);
         } else {
            pageList = this.elasticsearchTemplateXyy.search(queryBuilder, psh, EmrFile.class);
            List<EmrFile> emrFileList = this.elasticsearchTemplate.searchMore(queryBuilder, Long.valueOf(pageList.getTotalElements()).intValue(), EmrFile.class);
            List<Long> mrFileIdList = (List)emrFileList.stream().map((t) -> t.getId()).collect(Collectors.toList());
            if (searchType1Flag != null && searchType1Flag.equals("1")) {
               this.redisCache.setCacheObject("es_mr_file_id_list2:" + userToken, mrFileIdList, Constants.ES_EXPIRATION, TimeUnit.MINUTES);
            } else {
               this.redisCache.setCacheObject("es_mr_file_id_list:" + userToken, mrFileIdList, Constants.ES_EXPIRATION, TimeUnit.MINUTES);
            }
         }

         this.emrSearchHistoryService.saveEmrSearchHistory(emrFileSearchVo);
      }

      return pageList;
   }

   public List queryHighLightExport(EmrFileSearchVo emrFileSearchVo) throws Exception {
      String userToken = SecurityUtils.getLoginUser().getToken();
      BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
      this.getQbList(queryBuilder, emrFileSearchVo);
      if (emrFileSearchVo.getSearchType() != null && emrFileSearchVo.getSearchType() == 1) {
         List<Long> mrFileIdList = (List)this.redisCache.getCacheObject("es_mr_file_id_list:" + userToken);
         List<Long> mrFileIdList2 = (List)this.redisCache.getCacheObject("es_mr_file_id_list2:" + userToken);
         if (mrFileIdList2 != null) {
            QueryBuilder qbTemp = QueryBuilders.termQuery("id", mrFileIdList2);
            queryBuilder.must(qbTemp);
         } else {
            QueryBuilder qbTemp = QueryBuilders.termQuery("id", mrFileIdList);
            queryBuilder.must(qbTemp);
         }
      }

      List<EmrFile> list = this.elasticsearchTemplate.search(queryBuilder, EmrFile.class);
      return list;
   }

   public PageList queryHighLight(Long caseId, int currentPage, int pageSize) throws Exception {
      List<EmrSearchCaseDetail> detailList = this.searchCaseDetailService.selectEmrSearchCaseDetailByCaseId(caseId);
      EmrFileSearchVo param = new EmrFileSearchVo();
      param.setDetailList(detailList);
      return this.queryHighLight(param, currentPage, pageSize);
   }

   public void deleteEmrFileElemAddToEs(String beginDate, String endDate) throws Exception {
      List<Long> ids = this.emrFileMapper.selectRevokeEmrAllIds(beginDate, endDate);
      BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
      QueryBuilder performDepCode = QueryBuilders.termsQuery("id", ids);
      boolQueryBuilder.must(performDepCode);
      this.elasticsearchTemplate.deleteByCondition(boolQueryBuilder, EmrFile.class);
   }

   public void deleteEmrFileElemAllToEs() throws Exception {
      List<Long> ids = this.emrFileMapper.selectRevokeEmrAllIds((String)null, (String)null);
      BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
      QueryBuilder performDepCode = QueryBuilders.termsQuery("id", ids);
      boolQueryBuilder.must(performDepCode);
      this.elasticsearchTemplate.deleteByCondition(boolQueryBuilder, EmrFile.class);
   }

   public void toEs(List list) throws Exception {
      int bulkCount = 100;
      if (list.size() <= bulkCount) {
         this.toEsAsync(list);
      } else {
         int listSize = list.size();
         int bulkNum = listSize / bulkCount + (listSize % bulkCount > 0 ? 1 : 0);

         for(int i = 0; i < bulkNum; ++i) {
            int fromIndex = i * bulkCount;
            int toIndex = i + 1 == bulkNum ? listSize : (i + 1) * bulkCount;
            List<EmrFile> listTemp = list.subList(fromIndex, toIndex);
            this.toEsAsync(listTemp);
         }
      }

   }

   @Async
   public void toEsAsync(List list) throws Exception {
      List<Long> mrFileIds = (List)list.stream().map((t) -> t.getId()).collect(Collectors.toList());
      List<EmrBinary> emrBinaryList = this.emrBinaryService.selectListByIds(mrFileIds);
      Map<Long, String> emrBinaryMap = new HashMap();

      for(EmrBinary emrBinary : emrBinaryList) {
         String mrContent = emrBinary.getMrContent();
         if (StringUtils.isNotEmpty(mrContent)) {
            emrBinaryMap.put(emrBinary.getMrFileId(), mrContent);
         } else {
            Map<String, String> map = (Map)JSON.parse(emrBinary.getMrCon());
            String textStr = (String)map.get("textStr");
            emrBinaryMap.put(emrBinary.getMrFileId(), textStr);
         }
      }

      list.stream().forEach((t) -> {
         String mrCon = (String)emrBinaryMap.get(t.getId());
         t.setMrFileContent(mrCon);
         String mrConSub = StringUtils.isNotBlank(mrCon) ? mrCon.substring(0, mrCon.length() > 200 ? 200 : mrCon.length()) : mrCon;
         t.setMrFileContentSub(mrConSub);
      });
      this.elasticsearchTemplate.saveBatch(list);
   }

   public static String renderString(String content, Map map) {
      Set<Map.Entry<String, String>> entries = map.entrySet();

      for(Map.Entry e : map.entrySet()) {
         String regex = "\\#\\{" + (String)e.getKey() + "\\}";
         Pattern pattern = Pattern.compile(regex);
         Matcher matcher = pattern.matcher(content);
         content = matcher.replaceAll((String)e.getValue());
      }

      return content;
   }

   private void getQbList(BoolQueryBuilder queryBuilder, EmrFileSearchVo emrFileSearchVo) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      EmrSearchSynonym synonym = new EmrSearchSynonym();
      String emplNumber = basEmployee != null ? basEmployee.getEmplNumber() : "admin";
      synonym.setEmplNumber(emplNumber);
      List<EmrSearchSynonym> synonymList = this.emrSearchSynonymService.selectListByEmpl(emplNumber);
      List<EmrSearchCaseDetail> detailList = emrFileSearchVo.getDetailList();
      List<EmrSearchCaseDetail> keyWordList = (List<EmrSearchCaseDetail>)(detailList != null ? (List)detailList.stream().filter((t) -> t.getDetailCode().equals("1")).collect(Collectors.toList()) : new ArrayList(1));
      List<EmrSearchCaseDetail> otherDetailList = (List<EmrSearchCaseDetail>)(detailList != null ? (List)detailList.stream().filter((t) -> !t.getDetailCode().equals("1")).collect(Collectors.toList()) : new ArrayList(1));
      this.getKeyWordQbList(queryBuilder, keyWordList, synonymList);

      for(EmrSearchCaseDetail detail : otherDetailList) {
         switch (detail.getDetailCode()) {
            case "2":
               QueryBuilder qbTemp2 = QueryBuilders.termQuery("sexCd", detail.getTextCode());
               queryBuilder.must(qbTemp2);
               break;
            case "3":
               QueryBuilder qbTemp3 = QueryBuilders.termQuery("deptCd", detail.getTextCode());
               queryBuilder.must(qbTemp3);
               break;
            case "4":
               QueryBuilder qbTemp4 = QueryBuilders.termQuery("pcCd", detail.getTextCode());
               queryBuilder.must(qbTemp4);
               break;
            case "5":
               QueryBuilder qbTemp5 = QueryBuilders.termQuery("inhosWayCd", detail.getTextCode());
               queryBuilder.must(qbTemp5);
               break;
            case "6":
               QueryBuilder qbTemp6 = QueryBuilders.rangeQuery("ageY").gte(detail.getNumMin()).lt(detail.getNumMax());
               queryBuilder.must(qbTemp6);
            case "7":
            default:
               break;
            case "8":
               QueryBuilder qbTemp8 = QueryBuilders.termQuery("pcCd.keyword", detail.getTextCode());
               queryBuilder.must(qbTemp8);
               break;
            case "9":
               QueryBuilder qbTemp9 = QueryBuilders.termQuery("treatType", detail.getTextCode());
               queryBuilder.must(qbTemp9);
               break;
            case "10":
               QueryBuilder qbTemp10 = QueryBuilders.termQuery("ngCd", detail.getTextCode());
               queryBuilder.must(qbTemp10);
               break;
            case "11":
               QueryBuilder qbTemp11 = QueryBuilders.rangeQuery("outTime").gte(detail.getDateMin()).lt(detail.getDateMax());
               queryBuilder.must(qbTemp11);
               break;
            case "12":
               QueryBuilder qbTemp12 = QueryBuilders.rangeQuery("fileTime").gte(detail.getDateMin()).lt(detail.getDateMax());
               queryBuilder.must(qbTemp12);
               break;
            case "13":
               QueryBuilder qbTemp13 = QueryBuilders.rangeQuery("inhosTime").gte(detail.getDateMin()).lt(detail.getDateMax());
               queryBuilder.must(qbTemp13);
         }
      }

   }

   private void getKeyWordQbList(BoolQueryBuilder queryBuilder, List keyWordList, List synonymList) throws Exception {
      for(EmrSearchCaseDetail detail : (List)keyWordList.stream().sorted(Comparator.comparing(EmrSearchCaseDetail::getKeyWordOrder)).collect(Collectors.toList())) {
         String text = detail.getText();
         List<EmrSearchSynonym> detailSynonymList = (List)synonymList.stream().filter((t) -> t.getSynonymS().equals(detail.getText()) || t.getSynonymT().equals(detail.getText())).collect(Collectors.toList());
         if (detailSynonymList != null && !detailSynonymList.isEmpty()) {
            List<String> textList = new ArrayList(1);
            textList.addAll((Collection)detailSynonymList.stream().map((t) -> t.getSynonymS()).collect(Collectors.toList()));
            textList.addAll((Collection)detailSynonymList.stream().map((t) -> t.getSynonymT()).collect(Collectors.toList()));
            text = String.join(" ", textList);
         }

         List<QueryBuilder> qbList = new ArrayList(1);
         switch (detail.getKeyWordCode()) {
            case "0":
               if (StringUtils.isNotBlank(detail.getQueryStatus()) && detail.getQueryStatus().equals("1")) {
                  QueryBuilder qbTemp02 = QueryBuilders.multiMatchQuery(text, new String[]{"mrFileContent"}).operator(Operator.AND).type(Type.PHRASE);
                  qbList.add(qbTemp02);
                  break;
               }

               QueryBuilder qbTemp02 = QueryBuilders.multiMatchQuery(text, new String[]{"mrFileContent"}).operator(Operator.AND);
               qbList.add(qbTemp02);
               break;
            case "1":
               WildcardQueryBuilder qbTem01 = QueryBuilders.wildcardQuery("patientName.keyword", "*" + text + "*");
               qbList.add(qbTem01);
               break;
            case "2":
               detail.setTextCode(String.valueOf(CommonConstants.ELEM.MAIN_SUIT));
               List<Long> mrFileIdList2 = this.getMrFileIdByElem(detail, text);
               QueryBuilder qbTemp2 = QueryBuilders.termsQuery("id", mrFileIdList2);
               qbList.add(qbTemp2);
               break;
            case "3":
               List<Long> mrFileIdList7 = this.getDiasByElem(text);
               if (mrFileIdList7 != null && !mrFileIdList7.isEmpty()) {
                  QueryBuilder qbTemp6 = QueryBuilders.termsQuery("id", mrFileIdList7);
                  qbList.add(qbTemp6);
               }
            case "4":
            default:
               break;
            case "5":
               QueryBuilder qbTemp5 = QueryBuilders.termQuery("mrType", detail.getTextCode());
               WildcardQueryBuilder qbTemp51 = QueryBuilders.wildcardQuery("mrFileContent.keyword", "*" + text + "*");
               qbList.add(qbTemp5);
               qbList.add(qbTemp51);
               break;
            case "6":
               List<Long> mrFileIdList6 = this.getMrFileIdByElem(detail, text);
               if (mrFileIdList6 != null && !mrFileIdList6.isEmpty()) {
                  QueryBuilder qbTemp6 = QueryBuilders.termsQuery("id", mrFileIdList6);
                  qbList.add(qbTemp6);
               }
               break;
            case "7":
               WildcardQueryBuilder qbTem07 = QueryBuilders.wildcardQuery("diagsStr.keyword", "*" + text + "*");
               qbList.add(qbTem07);
         }

         if (qbList != null && !qbList.isEmpty()) {
            if (StringUtils.isNotBlank(detail.getKeyWordCondition()) && detail.getKeyWordCondition().equals("1")) {
               boolean var27 = true;
            } else {
               boolean var10000 = false;
            }

            for(QueryBuilder qb : qbList) {
               queryBuilder.must(qb);
            }
         }
      }

   }

   private List getDiasByElem(String text) throws Exception {
      EmrFileElem param = new EmrFileElem();
      param.setElemName("诊断");
      param.setElemCon(text);
      return this.emrFileElemService.getDiasByElem(param);
   }

   public List getMrFileIdByElem(EmrSearchCaseDetail detail, String text) throws Exception {
      EmrFileElem param = new EmrFileElem();
      param.setElemId(Long.valueOf(detail.getTextCode()));
      param.setElemCon(text);
      return this.emrFileElemService.getMrFileIdByElem(param);
   }
}
