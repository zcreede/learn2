package com.emr.project.esSearch.service.impl;

import com.emr.project.emr.domain.EmrElemstoe;
import com.emr.project.emr.service.IEmrElemstoeService;
import com.emr.project.esSearch.domain.EmrFileElem;
import com.emr.project.esSearch.service.IEmrFileElemService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.zxp.esclientrhl.index.ElasticsearchIndex;
import org.zxp.esclientrhl.repository.ElasticsearchTemplate;
import org.zxp.esclientrhl.repository.PageList;
import org.zxp.esclientrhl.repository.PageSortHighLight;

@Service
public class EmrFileElemServiceImpl implements IEmrFileElemService {
   @Autowired
   private ElasticsearchTemplate elasticsearchTemplate;
   @Autowired
   private ElasticsearchIndex elasticsearchIndex;
   @Autowired
   private IEmrElemstoeService emrElemstoeService;

   public void createEmrFileElemIndex() throws Exception {
      boolean existsFlag = this.elasticsearchIndex.exists(EmrFileElem.class);
      if (!existsFlag) {
         this.elasticsearchIndex.createIndex(EmrFileElem.class);
      }

   }

   public void dropEmrFileElemIndex() throws Exception {
      boolean existsFlag = this.elasticsearchIndex.exists(EmrFileElem.class);
      if (existsFlag) {
         this.elasticsearchIndex.dropIndex(EmrFileElem.class);
      }

   }

   public boolean syncEmrFileElemAllToEs() throws Exception {
      boolean existsFlag = this.elasticsearchIndex.exists(EmrFileElem.class);
      if (!existsFlag) {
         this.elasticsearchIndex.createIndex(EmrFileElem.class);
      }

      List<EmrElemstoe> elemstoeList = this.emrElemstoeService.selectListByDate((String)null, (String)null);
      List<EmrFileElem> list = new ArrayList(elemstoeList.size());
      elemstoeList.stream().forEach((t) -> {
         EmrFileElem elem = new EmrFileElem();
         BeanUtils.copyProperties(t, elem);
         list.add(elem);
      });
      this.toEs(list);
      return false;
   }

   public void syncEmrFileElemAddToEs(String beginDate, String endDate) throws Exception {
      boolean existsFlag = this.elasticsearchIndex.exists(EmrFileElem.class);
      if (!existsFlag) {
         this.elasticsearchIndex.createIndex(EmrFileElem.class);
      }

      List<EmrElemstoe> elemstoeList = this.emrElemstoeService.selectListByDate(beginDate, endDate);
      List<EmrFileElem> list = new ArrayList(elemstoeList.size());
      elemstoeList.stream().forEach((t) -> {
         EmrFileElem elem = new EmrFileElem();
         BeanUtils.copyProperties(t, elem);
         list.add(elem);
      });
      this.toEs(list);
   }

   public List getMrFileIdByElem(EmrFileElem elem) throws Exception {
      BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
      QueryBuilder qbTemp1 = QueryBuilders.termQuery("elemId", elem.getElemId());
      WildcardQueryBuilder qbTemp2 = QueryBuilders.wildcardQuery("elemCon.keyword", "*" + elem.getElemCon() + "*");
      queryBuilder.must(qbTemp1);
      queryBuilder.must(qbTemp2);
      PageSortHighLight psh = new PageSortHighLight(1, 10);
      PageList<EmrFileElem> pageList = this.elasticsearchTemplate.search(queryBuilder, psh, EmrFileElem.class);
      int limitSize = Long.valueOf(pageList.getTotalElements()).intValue();
      List<EmrFileElem> list = this.elasticsearchTemplate.searchMore(queryBuilder, limitSize, EmrFileElem.class);
      List<Long> mrFileIdList = (List)list.stream().map((t) -> t.getMrFileId()).distinct().collect(Collectors.toList());
      PageList var10 = null;
      return mrFileIdList;
   }

   public List getDiasByElem(EmrFileElem param) throws Exception {
      BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
      WildcardQueryBuilder qbTemp1 = QueryBuilders.wildcardQuery("elemName.keyword", "*" + param.getElemName() + "*");
      WildcardQueryBuilder qbTemp2 = QueryBuilders.wildcardQuery("elemCon.keyword", "*" + param.getElemCon() + "*");
      queryBuilder.must(qbTemp1);
      queryBuilder.must(qbTemp2);
      PageSortHighLight psh = new PageSortHighLight(1, 10);
      PageList<EmrFileElem> pageList = this.elasticsearchTemplate.search(queryBuilder, psh, EmrFileElem.class);
      int limitSize = Long.valueOf(pageList.getTotalElements()).intValue();
      List<EmrFileElem> list = this.elasticsearchTemplate.searchMore(queryBuilder, limitSize, EmrFileElem.class);
      List<Long> mrFileIdList = (List)list.stream().map((t) -> t.getMrFileId()).distinct().collect(Collectors.toList());
      PageList var10 = null;
      return mrFileIdList;
   }

   public void toEs(List list) throws Exception {
      int bulkCount = 500;
      if (list.size() <= bulkCount) {
         this.toEsAsync(list);
      } else {
         int listSize = list.size();
         int bulkNum = listSize / bulkCount + (listSize % bulkCount > 0 ? 1 : 0);

         for(int i = 0; i < bulkNum; ++i) {
            int fromIndex = i * bulkCount;
            int toIndex = i + 1 == bulkNum ? listSize : (i + 1) * bulkCount;
            List<EmrFileElem> listTemp = list.subList(fromIndex, toIndex);
            this.toEsAsync(listTemp);
         }
      }

   }

   @Async
   public void toEsAsync(List list) throws Exception {
      this.elasticsearchTemplate.saveBatch(list);
   }
}
