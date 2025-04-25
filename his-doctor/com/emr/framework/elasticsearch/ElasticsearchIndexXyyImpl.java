package com.emr.framework.elasticsearch;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.collect.ImmutableOpenMap;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zxp.esclientrhl.util.IndexTools;
import org.zxp.esclientrhl.util.MappingData;
import org.zxp.esclientrhl.util.MetaData;

@Component
public class ElasticsearchIndexXyyImpl implements ElasticsearchIndexXyy {
   private Logger logger = LoggerFactory.getLogger(this.getClass());
   @Autowired
   RestHighLevelClient client;
   private static final String NESTED = "nested";
   @Autowired
   private IndexTools indexTools;

   public void createIndex(Class clazz, String indexName) throws Exception {
      MetaData metaData = this.indexTools.getMetaData(clazz);
      MappingSetting mappingSource = this.getMappingSource(clazz, metaData);
      CreateIndexRequest request = null;
      indexName = StringUtils.isNotBlank(indexName) ? indexName : metaData.getIndexname();
      if (metaData.isRollover()) {
         if (metaData.getRolloverMaxIndexAgeCondition() == 0L && metaData.getRolloverMaxIndexDocsCondition() == 0L && metaData.getRolloverMaxIndexSizeCondition() == 0L) {
            throw new RuntimeException("rolloverMaxIndexAgeCondition is zero OR rolloverMaxIndexDocsCondition is zero OR rolloverMaxIndexSizeCondition is zero");
         }

         request = new CreateIndexRequest("<" + indexName + "-{now/d}-000001>");
         Alias alias = new Alias(indexName);
         alias.writeIndex(true);
         request.alias(alias);
      } else {
         request = new CreateIndexRequest(indexName);
      }

      try {
         request.settings(mappingSource.builder);
         request.mapping(metaData.getIndextype(), mappingSource.mappingSource, XContentType.JSON);
         CreateIndexResponse createIndexResponse = this.client.indices().create(request, RequestOptions.DEFAULT);
         boolean acknowledged = createIndexResponse.isAcknowledged();
         this.logger.info("创建索引[" + indexName + "]结果：" + acknowledged);
      } catch (IOException e) {
         this.logger.error("createIndex error", e);
      }

   }

   public boolean exists(Class clazz, String indexname) throws Exception {
      MetaData metaData = this.indexTools.getMetaData(clazz);
      String indextype = metaData.getIndextype();
      GetIndexRequest request = new GetIndexRequest();
      request.indices(new String[]{indexname});
      request.types(new String[]{indextype});
      boolean exists = this.client.indices().exists(request, RequestOptions.DEFAULT);
      return exists;
   }

   public void dropIndex(String indexname) throws Exception {
      DeleteIndexRequest request = new DeleteIndexRequest(indexname);
      this.client.indices().delete(request, RequestOptions.DEFAULT);
   }

   private MappingSetting getMappingSource(Class clazz, MetaData metaData) throws Exception {
      StringBuffer source = new StringBuffer();
      source.append("  {\n    \"" + metaData.getIndextype() + "\": {\n      \"properties\": {\n");
      MappingData[] mappingDataList = this.indexTools.getMappingData(clazz);
      boolean isNgram = false;

      for(int i = 0; i < mappingDataList.length; ++i) {
         MappingData mappingData = mappingDataList[i];
         if (mappingData != null && mappingData.getField_name() != null) {
            source.append(" \"" + mappingData.getField_name() + "\": {\n");
            source.append(" \"type\": \"" + mappingData.getDatatype() + "\"\n");
            if (!mappingData.getDatatype().equals("nested")) {
               if (mappingData.isNgram() && (mappingData.getDatatype().equals("text") || mappingData.getDatatype().equals("keyword"))) {
                  isNgram = true;
               }

               source.append(this.oneField(mappingData));
            } else {
               source.append(" ,\"properties\": { ");
               if (mappingData.getNested_class() == null || mappingData.getNested_class() == Object.class) {
                  throw new Exception("无法识别的Nested_class");
               }

               MappingData[] submappingDataList = this.indexTools.getMappingData(mappingData.getNested_class());

               for(int j = 0; j < submappingDataList.length; ++j) {
                  MappingData submappingData = submappingDataList[j];
                  if (submappingData != null && submappingData.getField_name() != null) {
                     source.append(" \"" + submappingData.getField_name() + "\": {\n");
                     source.append(" \"type\": \"" + submappingData.getDatatype() + "\"\n");
                     if (j == submappingDataList.length - 1) {
                        source.append(" }\n");
                     } else {
                        source.append(" },\n");
                     }
                  }
               }

               source.append(" }");
            }

            if (i == mappingDataList.length - 1) {
               source.append(" }\n");
            } else {
               source.append(" },\n");
            }
         }
      }

      source.append(" }\n");
      source.append(" }\n");
      source.append(" }\n");
      this.logger.info(source.toString());
      Settings.Builder builder = null;
      if (isNgram) {
         builder = Settings.builder().put("index.number_of_shards", metaData.getNumber_of_shards()).put("index.number_of_replicas", metaData.getNumber_of_replicas()).put("index.max_result_window", metaData.getMaxResultWindow()).put("analysis.filter.autocomplete_filter.type", "edge_ngram").put("analysis.filter.autocomplete_filter.min_gram", 1).put("analysis.filter.autocomplete_filter.max_gram", 20).put("analysis.analyzer.autocomplete.type", "custom").put("analysis.analyzer.autocomplete.tokenizer", "standard").putList("analysis.analyzer.autocomplete.filter", new String[]{"lowercase", "autocomplete_filter"});
      } else {
         builder = Settings.builder().put("index.number_of_shards", metaData.getNumber_of_shards()).put("index.number_of_replicas", metaData.getNumber_of_replicas()).put("index.max_result_window", metaData.getMaxResultWindow());
      }

      MappingSetting mappingSetting = new MappingSetting();
      mappingSetting.mappingSource = source.toString();
      mappingSetting.builder = builder;
      return mappingSetting;
   }

   private String oneField(MappingData mappingData) {
      StringBuilder source = new StringBuilder();
      if (!org.springframework.util.StringUtils.isEmpty(mappingData.getCopy_to())) {
         source.append(" ,\"copy_to\": \"" + mappingData.getCopy_to() + "\"\n");
      }

      if (!org.springframework.util.StringUtils.isEmpty(mappingData.getNull_value())) {
         source.append(" ,\"null_value\": \"" + mappingData.getNull_value() + "\"\n");
      }

      if (!mappingData.isAllow_search()) {
         source.append(" ,\"index\": false\n");
      }

      if (!mappingData.isNgram() || !mappingData.getDatatype().equals("text") && !mappingData.getDatatype().equals("keyword")) {
         if (mappingData.getDatatype().equals("text")) {
            source.append(" ,\"analyzer\": \"" + mappingData.getAnalyzer() + "\"\n");
            source.append(" ,\"search_analyzer\": \"" + mappingData.getSearch_analyzer() + "\"\n");
         }
      } else {
         source.append(" ,\"analyzer\": \"autocomplete\"\n");
         source.append(" ,\"search_analyzer\": \"standard\"\n");
      }

      if (mappingData.isKeyword() && !mappingData.getDatatype().equals("keyword") && mappingData.isSuggest()) {
         source.append(" \n");
         source.append(" ,\"fields\": {\n");
         source.append(" \"keyword\": {\n");
         source.append(" \"type\": \"keyword\",\n");
         source.append(" \"ignore_above\": " + mappingData.getIgnore_above());
         source.append(" },\n");
         source.append(" \"suggest\": {\n");
         source.append(" \"type\": \"completion\",\n");
         source.append(" \"analyzer\": \"" + mappingData.getAnalyzer() + "\"\n");
         source.append(" }\n");
         source.append(" }\n");
      } else if (mappingData.isKeyword() && !mappingData.getDatatype().equals("keyword") && !mappingData.isSuggest()) {
         source.append(" \n");
         source.append(" ,\"fields\": {\n");
         source.append(" \"keyword\": {\n");
         source.append(" \"type\": \"keyword\",\n");
         source.append(" \"ignore_above\": " + mappingData.getIgnore_above());
         source.append(" }\n");
         source.append(" }\n");
      } else if (!mappingData.isKeyword() && mappingData.isSuggest()) {
         source.append(" \n");
         source.append(" ,\"fields\": {\n");
         source.append(" \"suggest\": {\n");
         source.append(" \"type\": \"completion\",\n");
         source.append(" \"analyzer\": \"" + mappingData.getAnalyzer() + "\"\n");
         source.append(" }\n");
         source.append(" }\n");
      }

      return source.toString();
   }

   private Map resoveSettings(List settings) {
      Map map = new HashMap();
      if (settings != null && settings.size() > 0) {
         settings.forEach((s) -> {
            String[] split = s.split(":");
            map.put(split[0], split[1]);
         });
      }

      return map;
   }

   public String getSetting(Class clazz, String index, String setting) throws Exception {
      MetaData metaData = this.indexTools.getMetaData(clazz);
      String indextype = metaData.getIndextype();
      GetIndexRequest request = new GetIndexRequest();
      request.indices(new String[]{index});
      request.types(new String[]{indextype});
      GetIndexResponse getIndexResponse = this.client.indices().get(request, RequestOptions.DEFAULT);
      ImmutableOpenMap<String, Settings> map = getIndexResponse.getSettings();
      Settings settings = (Settings)getIndexResponse.getSettings().get(index);
      String settingStr = settings.get(setting);
      return settingStr;
   }

   private static class MappingSetting {
      protected Settings.Builder builder;
      protected String mappingSource;

      private MappingSetting() {
      }
   }
}
