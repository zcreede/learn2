package com.emr.framework.elasticsearch;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.zxp.esclientrhl.annotation.ESID;
import org.zxp.esclientrhl.config.ElasticsearchProperties;
import org.zxp.esclientrhl.index.ElasticsearchIndex;
import org.zxp.esclientrhl.repository.Attach;
import org.zxp.esclientrhl.repository.HighLight;
import org.zxp.esclientrhl.repository.PageList;
import org.zxp.esclientrhl.repository.PageSortHighLight;
import org.zxp.esclientrhl.repository.Sort;
import org.zxp.esclientrhl.util.BeanTools;
import org.zxp.esclientrhl.util.JsonUtils;
import org.zxp.esclientrhl.util.MetaData;
import org.zxp.esclientrhl.util.Tools;

@Component
public class ElasticsearchTemplateXyyImpl implements ElasticsearchTemplateXyy {
   private Logger logger = LoggerFactory.getLogger(this.getClass());
   @Autowired
   RestHighLevelClient client;
   @Autowired
   ElasticsearchIndex elasticsearchIndex;
   @Autowired
   ElasticsearchProperties elasticsearchProperties;
   private static final String keyword = ".keyword";
   private static Map classIDMap = new ConcurrentHashMap();

   public PageList search(QueryBuilder queryBuilder, PageSortHighLight pageSortHighLight, Class clazz) throws Exception {
      MetaData metaData = this.elasticsearchIndex.getMetaData(clazz);
      String[] indexname = metaData.getSearchIndexNames();
      if (pageSortHighLight == null) {
         throw new NullPointerException("PageSortHighLight不能为空!");
      } else {
         return this.search(queryBuilder, pageSortHighLight, clazz, indexname);
      }
   }

   public PageList search(QueryBuilder queryBuilder, PageSortHighLight pageSortHighLight, Class clazz, String... indexs) throws Exception {
      if (pageSortHighLight == null) {
         throw new NullPointerException("PageSortHighLight不能为空!");
      } else {
         Attach attach = new Attach();
         attach.setPageSortHighLight(pageSortHighLight);
         return this.search(queryBuilder, attach, clazz, indexs);
      }
   }

   public BulkResponse[] saveBatch(List list, String indexname) throws Exception {
      if (list != null && list.size() != 0) {
         T t = (T)list.get(0);
         MetaData metaData = this.elasticsearchIndex.getMetaData(t.getClass());
         String indextype = metaData.getIndextype();
         List<List<T>> lists = Tools.splitList(list, true);
         BulkResponse[] bulkResponses = new BulkResponse[lists.size()];

         for(int i = 0; i < lists.size(); ++i) {
            bulkResponses[i] = this.savePart((List)lists.get(i), indexname, indextype);
         }

         return bulkResponses;
      } else {
         return null;
      }
   }

   public BulkResponse batchUpdate(QueryBuilder queryBuilder, Object t, Class clazz, int limitcount, boolean asyn) throws Exception {
      MetaData metaData = this.elasticsearchIndex.getMetaData(t.getClass());
      String indexname = metaData.getIndexname();
      String indextype = metaData.getIndextype();
      if (queryBuilder == null) {
         throw new NullPointerException();
      } else if (Tools.checkNested(t)) {
         throw new Exception("nested对象更新，请使用覆盖更新");
      } else if (Tools.getESId(t) != null && !"".equals(Tools.getESId(t))) {
         throw new Exception("批量更新请不要给主键传值");
      } else {
         PageSortHighLight psh = new PageSortHighLight(1, limitcount);
         psh.setHighLight((HighLight)null);
         PageList pageList = this.search(queryBuilder, psh, clazz, indexname);
         if (pageList.getTotalElements() > (long)limitcount) {
            throw new Exception("beyond the limitcount");
         } else if (asyn) {
            (new Thread(() -> {
               try {
                  this.batchUpdate(pageList.getList(), indexname, indextype, t);
                  this.logger.info("asyn batch finished update");
               } catch (Exception var6) {
                  this.logger.error("asyn batch update fail", var6);
               }

            })).start();
            return null;
         } else {
            return this.batchUpdate(pageList.getList(), indexname, indextype, t);
         }
      }
   }

   private BulkResponse batchUpdate(List list, String indexname, String indextype, Object tot) throws Exception {
      Map map = Tools.getFieldValue(tot);
      BulkRequest rrr = new BulkRequest();

      for(int i = 0; i < list.size(); ++i) {
         T tt = (T)list.get(i);
         rrr.add((new UpdateRequest(indexname, indextype, Tools.getESId(tt))).doc(map));
      }

      BulkResponse bulkResponse = this.client.bulk(rrr, RequestOptions.DEFAULT);
      return bulkResponse;
   }

   public PageList search(QueryBuilder queryBuilder, Attach attach, Class clazz, String... indexs) throws Exception {
      if (attach == null) {
         throw new NullPointerException("Attach不能为空!");
      } else {
         MetaData metaData = this.elasticsearchIndex.getMetaData(clazz);
         PageList<T> pageList = new PageList();
         List<T> list = new ArrayList();
         PageSortHighLight pageSortHighLight = attach.getPageSortHighLight();
         SearchRequest searchRequest = new SearchRequest(indexs);
         SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
         searchSourceBuilder.query(queryBuilder);
         boolean highLightFlag = false;
         boolean idSortFlag = false;
         if (pageSortHighLight != null) {
            pageList.setCurrentPage(pageSortHighLight.getCurrentPage());
            pageList.setPageSize(pageSortHighLight.getPageSize());
            if (pageSortHighLight.getPageSize() != 0) {
               if (!attach.isSearchAfter()) {
                  searchSourceBuilder.from((pageSortHighLight.getCurrentPage() - 1) * pageSortHighLight.getPageSize());
               }

               searchSourceBuilder.size(pageSortHighLight.getPageSize());
            }

            if (pageSortHighLight.getSort() != null) {
               Sort sort = pageSortHighLight.getSort();
               List<Sort.Order> orders = sort.listOrders();

               for(int i = 0; i < orders.size(); ++i) {
                  if (((Sort.Order)orders.get(i)).getProperty().equals("_id")) {
                     idSortFlag = true;
                  }

                  searchSourceBuilder.sort((new FieldSortBuilder(((Sort.Order)orders.get(i)).getProperty())).order(((Sort.Order)orders.get(i)).getDirection()));
               }
            }

            HighLight highLight = pageSortHighLight.getHighLight();
            if (highLight != null && highLight.getHighlightBuilder() != null) {
               highLightFlag = true;
               searchSourceBuilder.highlighter(highLight.getHighlightBuilder());
            } else if (highLight != null && highLight.getHighLightList() != null && highLight.getHighLightList().size() != 0) {
               HighlightBuilder highlightBuilder = new HighlightBuilder();
               if (!StringUtils.isEmpty(highLight.getPreTag()) && !StringUtils.isEmpty(highLight.getPostTag())) {
                  highlightBuilder.preTags(new String[]{highLight.getPreTag()});
                  highlightBuilder.postTags(new String[]{highLight.getPostTag()});
               }

               for(int i = 0; i < highLight.getHighLightList().size(); ++i) {
                  highLightFlag = true;
                  highlightBuilder.field((String)highLight.getHighLightList().get(i), 0);
               }

               searchSourceBuilder.highlighter(highlightBuilder);
            }
         }

         if (attach.isSearchAfter()) {
            if (pageSortHighLight != null && pageSortHighLight.getPageSize() != 0) {
               searchSourceBuilder.size(pageSortHighLight.getPageSize());
            } else {
               searchSourceBuilder.size(10);
            }

            if (attach.getSortValues() != null && attach.getSortValues().length != 0) {
               searchSourceBuilder.searchAfter(attach.getSortValues());
            }

            if (!idSortFlag) {
               Sort.Order order = new Sort.Order(SortOrder.ASC, "_id");
               pageSortHighLight.getSort().and(new Sort(new Sort.Order[]{order}));
               searchSourceBuilder.sort((new FieldSortBuilder("_id")).order(SortOrder.ASC));
            }
         }

         if (attach.isTrackTotalHits()) {
            searchSourceBuilder.trackTotalHits(attach.isTrackTotalHits());
         }

         if (attach.getExcludes() != null || attach.getIncludes() != null) {
            searchSourceBuilder.fetchSource(attach.getIncludes(), attach.getExcludes());
         }

         searchRequest.source(searchSourceBuilder);
         if (!StringUtils.isEmpty(attach.getRouting())) {
            searchRequest.routing(attach.getRouting());
         }

         if (metaData.isPrintLog()) {
            this.logger.info(searchSourceBuilder.toString());
         }

         SearchResponse searchResponse = this.client.search(searchRequest, RequestOptions.DEFAULT);
         SearchHits hits = searchResponse.getHits();
         SearchHit[] searchHits = hits.getHits();

         for(SearchHit hit : searchHits) {
            T t = (T)JsonUtils.string2Obj(hit.getSourceAsString(), clazz);
            this.correctID(clazz, t, hit.getId());
            if (highLightFlag) {
               Map<String, HighlightField> hmap = hit.getHighlightFields();
               hmap.forEach((k, v) -> {
                  try {
                     Object obj = this.mapToObject(hmap, clazz);
                     BeanUtils.copyProperties(obj, t, BeanTools.getNoValuePropertyNames(obj));
                  } catch (Exception var7) {
                     this.logger.error("convert object error", var7);
                  }

               });
            }

            list.add(t);
            pageList.setSortValues(hit.getSortValues());
         }

         pageList.setList(list);
         pageList.setTotalElements(hits.getTotalHits().value);
         if (pageSortHighLight != null && pageSortHighLight.getPageSize() != 0) {
            pageList.setTotalPages(this.getTotalPages(hits.getTotalHits().value, pageSortHighLight.getPageSize()));
         }

         return pageList;
      }
   }

   private void correctID(Class clazz, Object t, String _id) {
      try {
         if (StringUtils.isEmpty(_id)) {
            return;
         }

         if (classIDMap.containsKey(clazz)) {
            Field field = clazz.getDeclaredField((String)classIDMap.get(clazz));
            field.setAccessible(true);
            if (field.get(t) == null) {
               field.set(t, _id);
            }

            return;
         }

         for(int i = 0; i < clazz.getDeclaredFields().length; ++i) {
            Field field = clazz.getDeclaredFields()[i];
            field.setAccessible(true);
            if (field.getAnnotation(ESID.class) != null) {
               classIDMap.put(clazz, field.getName());
               if (field.get(t) == null) {
                  field.set(t, _id);
               }
            }
         }
      } catch (Exception var6) {
         this.logger.error("correctID error!", var6);
      }

   }

   private Object mapToObject(Map map, Class beanClass) throws Exception {
      if (map == null) {
         return null;
      } else {
         Object obj = beanClass.newInstance();
         Field[] fields = obj.getClass().getDeclaredFields();

         for(Field field : fields) {
            if (map.get(field.getName()) != null && !StringUtils.isEmpty(map.get(field.getName()))) {
               int mod = field.getModifiers();
               if (!Modifier.isStatic(mod) && !Modifier.isFinal(mod)) {
                  field.setAccessible(true);
                  if (map.get(field.getName()) instanceof HighlightField && ((HighlightField)map.get(field.getName())).fragments().length > 0) {
                     field.set(obj, ((HighlightField)map.get(field.getName())).fragments()[0].string());
                  }
               }
            }
         }

         return obj;
      }
   }

   private int getTotalPages(long totalHits, int pageSize) {
      return pageSize == 0 ? 1 : (int)Math.ceil((double)totalHits / (double)pageSize);
   }

   private BulkResponse savePart(List list, String indexname, String indextype) throws Exception {
      BulkRequest rrr = new BulkRequest();
      Class clazz = null;

      for(int i = 0; i < list.size(); ++i) {
         T tt = (T)list.get(i);
         clazz = tt.getClass();
         String id = Tools.getESId(tt);
         String sourceJsonStr = JsonUtils.obj2String(tt);
         rrr.add((new IndexRequest(indexname, indextype, id)).source(sourceJsonStr, XContentType.JSON));
      }

      BulkResponse bulkResponse = this.client.bulk(rrr, RequestOptions.DEFAULT);
      this.elasticsearchIndex.rollover(clazz, true);
      return bulkResponse;
   }
}
